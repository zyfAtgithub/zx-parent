package com.yf.zx.core.util.common;

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
	 * @param obj
	 * @return boolean
	 */
	public static boolean isNotNull(Object obj) {
		return !isNull(obj);
	}
	
	/**
	 * 空判断
	 *  
	 * @author zhang.yifeng 
	 * @param obj
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
	
	/**
	 * trim功能升级[支持传入指定要trim的字串]
	 *  
	 * @author zhang.yifeng 
	 * @param str 原字串
	 * @param trimStr 要trim的字串
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
	 * @param trimStr
	 * @return
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
	 * @param trimStr
	 * @return
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
	
	public static void main(String[] args) {
		System.out.print(trim("xxsxajkl  xasxxx", "x"));
		/**
		 * 1、确定建设方案，申请部门与维护中心讨论确定建设方案；
		 * 2、平台立项——申请部门在鸿信OA系统专项申请中申请填写It专项申请，需写明申请It资源需求，专项申请须
		 * 经由申请部门经理、维护中心经理，申请部门分管领导审批通过；
		 * 3、云资源使用——云化项目It资源的申请统一由维护中心在省公司云资源综合支撑系统中申请，申请成功后，
		 * 由维护中心分发交付给使用部门；
		 * 4、非云化项目——设备采购由公司上商务组负责采购，机房和带宽的申请交由维护中心负责，设备到货后，
		 * 维护中心负责实施和交付。
		 * 
		 * 1、确定测试需求——申请部门与维护中心讨论测试需求；
		 * 2、资源申请——申请部门在鸿信OA系统专项申请中填写资源申请，专项申请需由申请部门经理、维护中心经理，
		 * 申请部门公司分管领导
		 */
	}
}
