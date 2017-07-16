package com.yf.zx.web.webservice;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;

import javax.net.ssl.SSLSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.log4j.Logger;

import com.yf.zx.core.util.common.MD5Utils;

/**
 * 备案域名查询 ws 工具类
 * @author zyf
 *
 */
public class QueryBeianStatusWsUtil {


	private static final Logger log = Logger.getLogger(QueryBeianStatusWsUtil.class);
	
	/** 接入端的标识  */
	private static final Long JRD_ID = 10020l;

	/** 用户名，由网站备案系统维护管理 */
	private static final String USERNAME = "gzdx";

	/** 密码 */
	private static final String PWD = "123qwe";
	
	/** wsdl路径 */
	public static String QUERY_STATUS_WSDL_URL = "https://42.99.16.26:8443/SbsInterface/ws/queryBeianStatus?wsdl";

	/**
	 * 设置证书
	 */
	static {
		javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
				new javax.net.ssl.HostnameVerifier() {
					@Override
					public boolean verify(String hostname, SSLSession session) {
						System.out.println(hostname);
						//直接返回true
						return true;
					}
				});
	
		String path = System.getProperty("java.home") + File.separatorChar + "lib" + File.separatorChar + "security" + File.separatorChar + "jssecacerts";
		System.out.println("Path=" + path);
		System.setProperty("javax.net.ssl.trustStore", path);
		System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
	}
	
	/**
	 * 生成20个字节的随机数
	 * [省自建备案等相关系统调用该方法时生成的随机字符串，长度是20字节]
	 * @return
	 */
	public static String generateRandomVal() {
		char[] BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
		SecureRandom random = new SecureRandom();
		StringBuffer buffer = new StringBuffer(20);
		for (int i = 0; i < 20; i++) {
			buffer.append(BASE62[random.nextInt(BASE62.length)]);
		}
		return buffer.toString();
	}
	
	/**
	 * 客户端请求时当前的unix时间串
	 * @return
	 */
	private static Long getUnixTimestamp() {
		// 1.Unix时间戳
		return System.currentTimeMillis() / 1000;
	}
	
	private static String getSign(int hashAlgorithm, String userName, String randVal, String time, String pwd, String queryCondition) {
		String sign = "";
		try {
			if (0 == hashAlgorithm) {
				sign=MD5Utils.parseStrToMd5L32((userName+randVal+time+pwd+queryCondition).getBytes("GBK"));
			}
		} catch (UnsupportedEncodingException e) {
			log.error(e);
		}
		return new String(Base64.encodeBase64(sign.getBytes()));
	}
	
	/**
	 * 调用远程WebService服务
	 * @param queryCondition 参数
	 * @return 远程返回结果
	 */
	public static String querybeianstatus(String queryCondition) {
		String ret = "";
		try {
			String randVal = generateRandomVal();
			Long time = getUnixTimestamp();
			int hashAlgorithm = 0;
			int queryConditionType = 0;
			String sign = getSign(hashAlgorithm, USERNAME, randVal, time+"", PWD, queryCondition);

	        Object[] params = new Object[8];
	        params[0] = JRD_ID;
	        params[1] = USERNAME;
	        params[2] = randVal;
	        params[3] = time;
	        params[4] = hashAlgorithm;
	        params[5] = queryConditionType;
	        params[6] = queryCondition;
	        params[7] = sign;
	        System.out.println("jrdId=" + params[0]);
	        System.out.println("userName=" + params[1]);
	        System.out.println("randVal=" + params[2]);
	        System.out.println("time=" + params[3]);
	        System.out.println("hashAlgorithm=" + params[4]);
	        System.out.println("queryConditionType=" + params[5]);
	        System.out.println("queryCondition=" + params[6]);
	        System.out.println("sign=" + params[7]);
	        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
			Client client = dcf.createClient(QUERY_STATUS_WSDL_URL);
			Object[] res = client.invoke("jrd_querybeianstatus", params);
			System.out.println("after invoke.." + res[0]);
			if (res != null && res.length > 0) {
				ret = String.valueOf(res[0]);
			}
		} catch (Exception e) {
			//调用远程WebService服务出错
			String alertType = "向集团请求查询域名备案信息";
			String alertDesc = "调用远程WebService服务出错[调用url:"+QUERY_STATUS_WSDL_URL+"，执行操作:"+alertType+"]";
			System.out.println(alertDesc);
			e.printStackTrace();
			log.error(e);
		}
		System.out.println("服务端返回：" + ret);
		return ret;
		// SslUtils.ignoreSsl();
//		String ret = "";
//		try {
//			JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
//			Client client = dcf.createClient("https://10.0.0.2:8443/zx-web/service/cde?wsdl");
//			Object[] res = client.invoke("say", "yf");
//			ret = String.valueOf(res[0]);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println("服务端返回：" + ret);
//		return ret;
	}
	
	public static void main(String[] args) throws Exception {
	
	}
}
