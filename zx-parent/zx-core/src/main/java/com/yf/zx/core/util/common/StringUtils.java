package com.yf.zx.core.util.common;

import java.util.Arrays;
import java.util.List;

public final class StringUtils {

	/**
	 * 空判断
	 * 
	 * @author zhang.yifeng
	 * @param obj
	 *            参数
	 * @return boolean
	 */
	public static boolean isNull(Object obj) {
		return obj == null;
	}

	/**
	 * 空判断
	 * 
	 * @author zhang.yifeng
	 * @param obj
	 *            参数
	 * @return boolean
	 */
	public static boolean isNotNull(Object obj) {
		return !isNull(obj);
	}

	/**
	 * 空判断
	 * 
	 * @author zhang.yifeng
	 * @param str
	 *            参数
	 * @return boolean
	 */
	public static boolean isEmpty(String str) {
		return "".equals(str);
	}

	/**
	 * 空判断
	 * 
	 * @author zhang.yifeng
	 * @param str
	 *            参数
	 * @return boolean
	 */
	public static boolean isNullOrEmpty(String str) {
		return isNull(str) || isEmpty(str);
	}

	/**
	 * 空判断
	 * 
	 * @author zhang.yifeng
	 * @param str
	 *            参数
	 * @return boolean
	 */
	public static boolean isNotNullAndEmpty(String str) {
		return !isNullOrEmpty(str);
	}

	/**
	 * 空判断
	 * 
	 * @author zhang.yifeng
	 * @param obj
	 *            参数
	 * @return boolean
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
	 *            参数
	 * @return boolean
	 */
	public static String convert2String(Object obj) {
		if (isNull(obj)) {
			return "";
		}
		return obj.toString();
	}

	/**
	 * trim功能升级[支持传入指定要trim的字串]
	 * 
	 * @author zhang.yifeng
	 * @param str
	 *            原字串
	 * @param trimStr
	 *            要trim的字串
	 * @return trim后的字串
	 */
	public static String trim(String str, String trimStr) {
		if (isNullOrEmpty(str)) {
			return "";
		}
		if (" ".equals(trimStr)) {
			return str.trim();
		}

		str = trimStart(str, trimStr);
		str = trimEnd(str, trimStr);
		return str;
	}

	/**
	 * trim头部
	 * 
	 * @author zhang.yifeng
	 * @param str
	 *            待trim的字符串
	 * @param trimStr
	 *            需要trim的字符
	 * @return trim结果
	 */
	private static String trimStart(String str, String trimStr) {
		if (!str.startsWith(trimStr)) {
			return str;
		}
		int trimedStartIndex = 0;
		trimedStartIndex = str.indexOf(trimStr);
		str = str.substring(trimedStartIndex + trimStr.length(), str.length());
		return trimStart(str, trimStr);
	}

	/**
	 * trim尾部
	 * 
	 * @author zhang.yifeng
	 * @param str
	 *            待trim的字符串
	 * @param trimStr
	 *            需要trim的字符
	 * @return trim结果
	 */
	private static String trimEnd(String str, String trimStr) {
		if (!str.endsWith(trimStr)) {
			return str;
		}
		int trimedEndIndex = 0;
		trimedEndIndex = str.lastIndexOf(trimStr);
		str = str.substring(0, trimedEndIndex);
		return trimEnd(str, trimStr);
	}

	/**
	 * 判断字符串是否包含
	 * 
	 * @author zhang.yifeng
	 * @param str
	 * @param searchStr
	 * @return
	 */
	public static boolean contains(String str, String searchStr) {
		return (str.indexOf(searchStr) != -1);
	}

	/**
	 * 摘自 org.apache.commons.lang.StringUtils.join
	 * 
	 * @author zhang.yifeng
	 * @param array
	 * @param separator
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	public static String join(Object[] array, String separator) {
		if (array == null) {
			return null;
		}

		return join(array, separator, 0, array.length);
	}

	/**
	 * 摘自 org.apache.commons.lang.StringUtils.join
	 * 
	 * @author zhang.yifeng
	 * @param array
	 * @param separator
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	public static String join(Object[] array, String separator, int startIndex, int endIndex) {
		if (array == null) {
			return null;
		}
		if (separator == null) {
			separator = "";
		}

		// endIndex - startIndex > 0: Len = NofStrings *(len(firstString) +
		// len(separator))
		// (Assuming that all Strings are roughly equally long)
		int bufSize = (endIndex - startIndex);
		if (bufSize <= 0) {
			return "";
		}

		bufSize *= ((array[startIndex] == null ? 16 : array[startIndex].toString().length()) + separator.length());

		StringBuffer buf = new StringBuffer(bufSize);

		for (int i = startIndex; i < endIndex; i++) {
			if (i > startIndex) {
				buf.append(separator);
			}
			if (array[i] != null) {
				buf.append(array[i]);
			}
		}
		return buf.toString();
	}

	/**
	 * 字符串分割成 list
	 *  
	 * @author zhang.yifeng 
	 * @param str
	 * @param separator
	 * @return
	 */
	public static List<String> split2List(String str, String separator) {
		if (isNullOrEmpty(str)) {
			return null;
		}
		
		if (isNullOrEmpty(separator)) {
			separator = ",";
		}
		
		String[] arr = str.split(separator);
		return Arrays.asList(arr);
	}
	
	public static void main(String[] args) {
		System.out.print(trim("xxsxajkl  xasxxx", "x"));
	}
}
