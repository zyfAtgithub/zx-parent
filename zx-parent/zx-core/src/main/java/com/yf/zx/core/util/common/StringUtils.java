package com.yf.zx.core.util.common;

import java.util.ArrayList;
import java.util.List;

public final class StringUtils {

	/**
	 * 空判断
	 *  
	 * @author zhang.yifeng 
	 * @param obj
	 * @return boolean
	 */
	public static boolean isNull(Object obj) {
		return obj == null;
	}
	
	/**
	 * 空判断
	 *  
	 * @author zhang.yifeng 
	 * @param str
	 * @return boolean
	 */
	public static boolean isNullOrEmpty(String str) {
		return isNull(str) || "".equals(str);
	}
	
	/**
	 * 空判断
	 *  
	 * @author zhang.yifeng 
	 * @param obj
	 * @return
	 */
	public static boolean isNullOrEmpty(Object obj) {
		if (isNull(obj)) {
			return true;
		}
		return isNullOrEmpty(obj.toString());
	}
	
	/**
	 * 转String，若是入参为 null，则返回 ""，否则返回 obj.toString()
	 *  
	 * @author zhang.yifeng 
	 * @param obj
	 * @return boolean
	 */
	public static String convert2String(Object obj) {
		if (isNull(obj)) {
			return "";
		}
		return obj.toString();
	}
	
	public static void main(String[] args) {
		List<String> al = new ArrayList<String>();
		al.add("a");
		al.add("a");
		al.add("a");
		System.out.println(StringUtils.convert2String(al));
	}
}
