package com.yf.zx.core.util.ip;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * IPUtil [IP工具类]
 *  
 * @author zhang.yifeng
 * @CreateDate 2017年7月18日
 * @version 1.0.0
 * @since  1.0.0 
 * @see com.jshx.utils 
 *
 */
public class IPUtil {

	/**
	 * 获取本机IP
	 *  
	 * @author zhang.yifeng 
	 * @return
	 */
	public static String getLocalIP() {
		String localIp = "unknown";
		try {
			InetAddress address = InetAddress.getLocalHost();// 获取的是本地的IP地址 //PC-20140317PXKX/192.168.0.121
			localIp = address.getHostAddress();// 192.168.0.121
			System.out.println("本机IP：" + localIp);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return localIp;
	}

	/**
	 * 获取主机名
	 * 
	 * @author zhang.yifeng 
	 * @return
	 */
	public static String getLocalHostName() {
		String hostName = "unknown";
		try {
			InetAddress address = InetAddress.getLocalHost();// 获取的是本地的IP地址 //PC-20140317PXKX/192.168.0.121
			hostName = address.getHostName();
			System.out.println("本机名：" + address.getHostName());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return hostName;
	}

	public static void main(String[] args) {
		getLocalIP();
		getLocalHostName();
	}
}
