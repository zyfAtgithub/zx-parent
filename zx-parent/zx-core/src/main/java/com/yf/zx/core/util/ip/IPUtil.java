package com.yf.zx.core.util.ip;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.yf.zx.core.util.common.StringUtils;

/**
 * IPUtil [IP工具类]
 *  
 * @author zhang.yifeng
 * @CreateDate 2017年7月19日
 * @version 1.0.0
 * @since  1.0.0 
 * @see com.yf.zx.core.util.ip 
 *
 */
public class IPUtil {

	private final static String IP_REG = "^(?:(?:1[0-9][0-9]\\.)|(?:2[0-4][0-9]\\.)|(?:25[0-5]\\.)|(?:[1-9][0-9]\\.)|(?:[0-9]\\.)){3}(?:(?:1[0-9][0-9])|(?:2[0-4][0-9])|(?:25[0-5])|(?:[1-9][0-9])|(?:[0-9]))$";
	
	private final static Pattern IP_PATTERN = Pattern.compile(IP_REG);
	
	/**
	 * 校验IP地址合法性
	 *  
	 * @author zhang.yifeng 
	 * @param ipStr
	 * @return
	 */
	public static boolean validateIp(String ipStr) {
		return IP_PATTERN.matcher(ipStr).matches();
	}
	
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
	
	
	/**
	 * 获取客户端Ip地址
	 * 从Request对象中获得客户端IP，处理了HTTP代理服务器和Nginx的反向代理截取了ip
	 * @author zhang.yifeng 
	 * @param request
	 * @return ip
	 */
	public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotNullAndEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if(index != -1){
                return ip.substring(0,index);
            }else{
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(StringUtils.isNotNullAndEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }
        return request.getRemoteAddr();
    }
	
	public static void main(String[] args) {
		getLocalIP();
		getLocalHostName();
		System.out.println(validateIp(getLocalIP()));
		System.out.println(System.currentTimeMillis());
		System.out.println(new Date(1502503200000l));
	}
}
