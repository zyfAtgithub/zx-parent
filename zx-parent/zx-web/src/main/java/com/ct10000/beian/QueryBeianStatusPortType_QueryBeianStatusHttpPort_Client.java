
package com.ct10000.beian;

import java.net.URL;

import javax.xml.namespace.QName;

import com.yf.zx.core.util.ssl.SslUtils;

/**
 * This class was generated by Apache CXF (incubator) 2.0.4-incubator Fri Jul 14
 * 11:21:24 CST 2017 Generated source version: 2.0.4-incubator
 * 
 */

public final class QueryBeianStatusPortType_QueryBeianStatusHttpPort_Client {

	private static final QName SERVICE_NAME = new QName("http://beian.ct10000.com/queryBeianStatus",
			"queryBeianStatus");

	private QueryBeianStatusPortType_QueryBeianStatusHttpPort_Client() {
	}
	//
	// /**
	// * 设置证书
	// */
	// static {
	// javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
	// new javax.net.ssl.HostnameVerifier() {
	// @Override
	// public boolean verify(String hostname, SSLSession session) {
	// if ("42.99.16.26".equals(hostname)) {
	// return true;
	// }
	// return false;
	// }
	// });
	//
	// String path = System.getProperty("java.home") + File.separatorChar +
	// "lib" + File.separatorChar + "security" + File.separatorChar +
	// "jssecacerts";
	// System.setProperty("javax.net.ssl.trustStore", path);
	// System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
	// }

	public static String querybeianstatus(String condition) throws Exception {

//		SslUtils.ignoreSsl();
		
		URL wsdlURL = new URL("https://42.99.16.26:8443/SbsInterface/ws/queryBeianStatus?wsdl");

		QueryBeianStatus ss = new QueryBeianStatus(wsdlURL, SERVICE_NAME);
		QueryBeianStatusPortType port = ss.getQueryBeianStatusHttpPort();

		System.out.println("Invoking jrdQuerybeianstatus...");
		java.lang.Long _jrdQuerybeianstatus_jrdId = null;
		java.lang.String _jrdQuerybeianstatus_userName = "";
		java.lang.String _jrdQuerybeianstatus_randVal = "";
		long _jrdQuerybeianstatus_time = 0;
		int _jrdQuerybeianstatus_hashAlgorithm = 0;
		int _jrdQuerybeianstatus_queryConditionType = 0;
		java.lang.String _jrdQuerybeianstatus_queryCondition = "";
		java.lang.String _jrdQuerybeianstatus_sign = "";
		java.lang.String _jrdQuerybeianstatus__return = port.jrdQuerybeianstatus(_jrdQuerybeianstatus_jrdId,
				_jrdQuerybeianstatus_userName, _jrdQuerybeianstatus_randVal, _jrdQuerybeianstatus_time,
				_jrdQuerybeianstatus_hashAlgorithm, _jrdQuerybeianstatus_queryConditionType,
				_jrdQuerybeianstatus_queryCondition, _jrdQuerybeianstatus_sign);
		System.out.println("jrdQuerybeianstatus.result=" + _jrdQuerybeianstatus__return);
		System.out.println(System.getProperty("javax.net.ssl.trustStore"));
		System.out.println(System.getProperty("javax.net.ssl.trustStorePassword"));
		return _jrdQuerybeianstatus__return;
	}

	public static void main(String args[]) throws Exception {

		if (args != null) {
			SslUtils.ignoreSsl();
		}

		URL wsdlURL = new URL("https://42.99.16.26:8443/SbsInterface/ws/queryBeianStatus?wsdl");

		QueryBeianStatus ss = new QueryBeianStatus(wsdlURL, SERVICE_NAME);
		QueryBeianStatusPortType port = ss.getQueryBeianStatusHttpPort();

		System.out.println("Invoking jrdQuerybeianstatus...");
		java.lang.Long _jrdQuerybeianstatus_jrdId = null;
		java.lang.String _jrdQuerybeianstatus_userName = "";
		java.lang.String _jrdQuerybeianstatus_randVal = "";
		long _jrdQuerybeianstatus_time = 0;
		int _jrdQuerybeianstatus_hashAlgorithm = 0;
		int _jrdQuerybeianstatus_queryConditionType = 0;
		java.lang.String _jrdQuerybeianstatus_queryCondition = "";
		java.lang.String _jrdQuerybeianstatus_sign = "";
		java.lang.String _jrdQuerybeianstatus__return = port.jrdQuerybeianstatus(_jrdQuerybeianstatus_jrdId,
				_jrdQuerybeianstatus_userName, _jrdQuerybeianstatus_randVal, _jrdQuerybeianstatus_time,
				_jrdQuerybeianstatus_hashAlgorithm, _jrdQuerybeianstatus_queryConditionType,
				_jrdQuerybeianstatus_queryCondition, _jrdQuerybeianstatus_sign);
		System.out.println("jrdQuerybeianstatus.result=" + _jrdQuerybeianstatus__return);
		System.out.println(System.getProperty("javax.net.ssl.trustStore"));
		System.out.println(System.getProperty("javax.net.ssl.trustStorePassword"));
	}

}
