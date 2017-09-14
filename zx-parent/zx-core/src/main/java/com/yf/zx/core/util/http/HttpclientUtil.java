package com.yf.zx.core.util.http;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLHandshakeException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.yf.zx.core.util.common.DateUtils;
import com.yf.zx.core.util.common.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
* Apache Httpclient 4.0 工具包装类
* 
* @author shezy
*/
@SuppressWarnings("all")
public class HttpclientUtil {
	private static final String CHARSET_UTF8 = "UTF-8";
	private static final String CHARSET_GBK = "GBK";
	private static final String SSL_DEFAULT_SCHEME = "https";
	private static final int SSL_DEFAULT_PORT = 443;
	
	
	
	// 异常自动恢复处理, 使用HttpRequestRetryHandler接口实现请求的异常恢复
	private static HttpRequestRetryHandler requestRetryHandler = new HttpRequestRetryHandler() {
		// 自定义的恢复策略
		public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
			// 设置恢复策略，在发生异常时候将自动重试3次
			if (executionCount >= 3) {
				// Do not retry if over max retry count
				return false;
			}
			if (exception instanceof NoHttpResponseException) {
				// Retry if the server dropped connection on us
				return true;
			}
			if (exception instanceof SSLHandshakeException) {
				// Do not retry on SSL handshake exception
				return false;
			}
			HttpRequest request = (HttpRequest) context.getAttribute(ExecutionContext.HTTP_REQUEST);
			boolean idempotent = (request instanceof HttpEntityEnclosingRequest);
			if (!idempotent) {
				// Retry if the request is considered idempotent
				return true;
			}
			return false;
		}
	};
	// 使用ResponseHandler接口处理响应，HttpClient使用ResponseHandler会自动管理连接的释放，解决了对连接的释放管理
	private static ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
		// 自定义响应处理
		public String handleResponse(HttpResponse response)	throws ClientProtocolException, IOException {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String charset = EntityUtils.getContentCharSet(entity) == null ? CHARSET_GBK : EntityUtils.getContentCharSet(entity);
				return new String(EntityUtils.toByteArray(entity), charset);
			} else {
				return null;
			}
		}
	};
	
	
	
	/**
	* 获取DefaultHttpClient实例
	* 
	* @param charset
	* 参数编码集, 可空
	* @return DefaultHttpClient 对象
	*/
	public static DefaultHttpClient getDefaultHttpClient(final String charset){
		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		//模拟浏览器，解决一些服务器程序只允许浏览器访问的问题
		httpclient.getParams().setParameter(CoreProtocolPNames.USER_AGENT, "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
		httpclient.getParams().setParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, Boolean.FALSE);
		httpclient.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, charset == null ? CHARSET_GBK : charset);
		httpclient.setHttpRequestRetryHandler(requestRetryHandler);
		return httpclient;
	}
	
	/**
	* 释放HttpClient连接
	* 
	* @param hrb
	* 请求对象
	* @param httpclient
	* 			client对象
	*/
	private static void abortConnection(final HttpRequestBase hrb, final HttpClient httpclient){
		if (hrb != null) {
			hrb.abort();
		}
		if (httpclient != null) {
			httpclient.getConnectionManager().shutdown();
		}
	}
	
	/**
	* 从给定的路径中加载此 KeyStore
	* 
	* @param url
	* keystore URL路径
	* @param password
	* keystore访问密钥
	* @return keystore 对象
	*/
	private static KeyStore createKeyStore(final URL url, final String password)
			throws KeyStoreException, NoSuchAlgorithmException,	CertificateException, IOException {
		if (url == null) {
			throw new IllegalArgumentException("Keystore url may not be null");
		}
		KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
		InputStream is = null;
		try {
			is = url.openStream();
			keystore.load(is, password != null ? password.toCharArray() : null);
		} finally {
			if (is != null){
				is.close();
				is = null;
			}
		}
		return keystore;
	}
	
	/**
	* 将传入的键/值对参数转换为NameValuePair参数集
	* 
	* @param paramsMap
	* 参数集, 键/值对
	* @return NameValuePair参数集
	*/
	private static List<NameValuePair> getParamsList(Map<String, String> paramsMap) {
		if (paramsMap == null || paramsMap.size() == 0) {
			return null;
		}
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> map : paramsMap.entrySet()) {
			params.add(new BasicNameValuePair(map.getKey(), map.getValue()));
		}
		return params;
	}
	
	/**
	* Post方式提交,忽略URL中包含的参数,解决SSL双向数字证书认证
	* 
	* @param url
	* 提交地址
	* @param params
	* 提交参数集, 键/值对
	* @param charset
	* 参数编码集
	* @param keystoreUrl
	* 密钥存储库路径
	* @param keystorePassword
	* 密钥存储库访问密码
	* @param truststoreUrl
	* 信任存储库绝路径
	* @param truststorePassword
	* 信任存储库访问密码, 可为null
	* @return 响应消息
	* @throws NetServiceException
	*/
	public static JSONObject post(String url, JSONObject params) throws Exception {
		if (url == null || StringUtils.isEmpty(url)) {
			return null;
		}
		HttpClient httpclient = new SSLClient();
		StringEntity entity = null;
		try {
			entity = new StringEntity(params.toString(), CHARSET_UTF8);
		} catch (UnsupportedEncodingException e) {
			throw new Exception("不支持的编码集", e);
		}
		HttpPost hp = null;
		HttpResponse response = null;
		String result = null;
		JSONObject callResult = null;
		try {
			hp = new HttpPost(url);
			hp.setEntity(entity);
			response = httpclient.execute(hp);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity resEntity = response.getEntity();
				result = EntityUtils.toString(resEntity, CHARSET_UTF8); 
				callResult = JSONObject.fromObject(result);
			} else {
				throw new Exception("调用失败，返回状态码为：" + response.getStatusLine().getStatusCode());
			}
		} catch (FileNotFoundException e) {
			throw new Exception("keystore文件不存在", e);
		} catch (IOException e) {
			throw new Exception("I/O操作失败或中断 ", e);
		} finally {
			abortConnection(hp, httpclient);
		}
		return callResult;
	}
	
	/**
	* Get方式提交,URL中不包含查询参数
	* 
	* @param url
	* 提交地址
	* @param params
	* 查询参数集, 键/值对
	* @param charset
	* 参数提交编码集
	* @return 响应消息
	 * @throws NetServiceException 
	*/
	public static JSONObject get(String url, int contimeout, int sotimeout) {
		if (url == null || StringUtils.isEmpty(url)) {
			return null;
		}
		 //get请求返回结果
        JSONObject jsonResult = new JSONObject();
		HttpClient	httpclient = getDefaultHttpClient(contimeout, sotimeout);
		
		HttpGet hg = new HttpGet(url);
		// 发送请求，得到响应
		long begin = System.currentTimeMillis();
		jsonResult.put("begin", DateUtils.date2String(new Date(begin), "yyyyMMddHHmmssSSS"));
		try {
			HttpResponse response = httpclient.execute(hg);
			String result=EntityUtils.toString(response.getEntity());
			jsonResult.putAll(JSONObject.fromObject(result));
			long end = System.currentTimeMillis();
			jsonResult.put("end", DateUtils.date2String(new Date(end), "yyyyMMddHHmmssSSS"));
			jsonResult.put("statusCode", response.getStatusLine().getStatusCode());
			jsonResult.put("cost", end - begin);
		} catch (Exception e) {
			long end = System.currentTimeMillis();
			jsonResult.put("end", DateUtils.date2String(new Date(end), "yyyyMMddHHmmssSSS"));
			jsonResult.put("statusCode", "999");
			jsonResult.put("cost", end - begin);
			jsonResult.put("excep", e.getMessage());
		} finally {
			if (hg != null) {
				hg.abort();
			}
			if (httpclient != null) {
				httpclient.getConnectionManager().shutdown();
			}
		}
		return jsonResult;
	}
	
	public static HttpClient getDefaultHttpClient(int contimeout, int sotimeout) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		//模拟浏览器，解决一些服务器程序只允许浏览器访问的问题
		httpclient.getParams().setParameter(CoreProtocolPNames.USER_AGENT, "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
		httpclient.getParams().setParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, Boolean.FALSE);
		httpclient.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, CHARSET_GBK);
		httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, contimeout);
		httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, sotimeout);
		httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, sotimeout);
		httpclient.setHttpRequestRetryHandler(requestRetryHandler);
		return httpclient;
	}
	
	public static JSONObject callOKHttpClient(String reqUrl, String token, JSONObject params) {
		JSONObject callResult = new JSONObject();
		
		//必填参数校验
		if (StringUtils.isNullOrEmpty(reqUrl)) {
			callResult.put("errorMsg", "reqUrl必填");
			return callResult;
		}
		
		if (StringUtils.isNullOrEmpty(token)) {
			callResult.put("errorMsg", "token必填");
			return callResult;
		}
		
		StringBuffer sbf = new StringBuffer("");
		sbf.append(reqUrl).append("?token=").append(token);
		
		if (params != null) {
			Iterator<String> sIterator = params.keys();  
			while(sIterator.hasNext()){  
				// 获得key  
				String key = sIterator.next();  
				// 根据key获得value 
				String value = params.getString(key);  
				sbf.append("&").append(key).append("=").append(value);
			}  
		}
		OkHttpClient client = new OkHttpClient(); 
		MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW"); 
		RequestBody body = RequestBody.create(mediaType, "------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"urls[]\"\r\n\r\nhttp://www.test.com//stat/http/get,http://www.test.com//stat/pv/get,http://www.test.com//stat/http/detail\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--"); 
		Request request = new Request.Builder() 
		.url(sbf.toString()) 
		.post(body) 
		.addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW") 
		.addHeader("cache-control", "no-cache") 
		.build(); 
		try {
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				String rsp = response.body().string();
				JSONArray jsArr = JSONArray.fromObject(rsp);
				callResult.put("jsonArr", jsArr);
			} else {
				callResult.put("errorMsg", "请求失败！");
			}
		} catch (IOException e) {
			e.printStackTrace();
			callResult.put("errorMsg", "请求出错！");
		} 
		finally {
		}
		
		return callResult;
	}
	
	/**
	 * 清除缓存
	 * @param urls
	 * 	urls	需要更新的url列表，多个使用逗号隔开
	 * @return
	 */
	public static JSONObject purge(String urls) {
		JSONObject params = new JSONObject();
		params.put("urls[]", urls);
		return callOKHttpClient("https://purge.qingcdn.com/purge/purge", "e586ccd02fe74b7955568f1d092a04fd", params);
	}

	/**
	 * 查询推送状态状态
	 * @param ids 
	 * 需要查询的id列表。多个时使用半角逗号隔开，例如：231,332,455
	 * @return
	 */
	public static JSONObject qryState(String ids) {
		JSONObject params = new JSONObject();
		params.put("ids", ids);
		return callOKHttpClient("http://purge.qingcdn.com//purge/purge", "e586ccd02fe74b7955568f1d092a04fd", params);
	}
	
	public static void main(String[] args) throws Exception {
		String urls = "http://www.189.cn/images/2014/09/10/000000009BD261189B6F4DCEA86285BEBFE597C0.png,"
				+ "http://www.189.cn/image/189cnv2/indexv2/images/title_02.png,"
				+ "http://www.189.cn/image/189cnv2/indexv2/images/title_03.png";
		String ids = "640451222,640453768";
		JSONObject jsRes1 = purge(urls);
		System.out.println(jsRes1);
		JSONObject jsRes2 = qryState(ids);
		System.out.println(jsRes2);
		System.out.println(jsRes2.getJSONArray("jsonArr").get(0));
	}
}
