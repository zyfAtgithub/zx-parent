package com.yf.zx.core.util.http;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Date;
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
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import com.yf.zx.core.util.common.DateUtils;
import com.yf.zx.core.util.common.StringUtils;

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
		httpclient.setHttpRequestRetryHandler(requestRetryHandler);
		return httpclient;
	}
	
	public static void main(String[] args) {
		JSONObject res = HttpclientUtil.get("http://whois.pconline.com.cn/ipJson.jsp?json=true", 200, 400);
		System.out.println(res.toString());
	}
}
