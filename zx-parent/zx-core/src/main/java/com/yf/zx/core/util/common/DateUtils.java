package com.yf.zx.core.util.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtils {
	
	/**
	 * logger
	 */
	private static Logger logger = LoggerFactory.getLogger(DateUtils.class);
	
	/** 日期格式 yyyy-MM-dd */
	public final static String DATE_FORMAT1 = "yyyy-MM-dd";

	/** 日期格式 yyyy.MM.dd */
	public final static String DATE_FORMAT2 = "yyyy.MM.dd";

	/** 日期格式 yyyy/MM/dd */
	public final static String DATE_FORMAT3 = "yyyy/MM/dd";

	/** 日期格式 yyyy_MM_dd */
	public final static String DATE_FORMAT4 = "yyyy_MM_dd";

	/** 日期格式 yyyyMMdd */
	public final static String DATE_FORMAT5 = "yyyyMMdd";

	/** 日期时间格式 yyyy-MM-dd HH:mm:ss */
	public final static String DATETIME_FORMAT1 = "yyyy-MM-dd HH:mm:ss";

	/** 日期时间格式 yyyy-MM-dd HH:mm:ss.S */
	public final static String DATETIME_FORMAT2 = "yyyy-MM-dd HH:mm:ss.S";

	/** 日期时间格式 yyyy-MM-dd HH:mm:ss a */
	public final static String DATETIME_FORMAT3 = "yyyy-MM-dd hh:mm:ss a";

	/** 日期时间格式 yyyy-MM-dd HH:mm:ss.S a */
	public final static String DATETIME_FORMAT4 = "yyyy-MM-dd hh:mm:ss.S a";

	/** 日期时间格式 yyyy_MM_dd_hh_mm_ss */
	public final static String DATETIME_FORMAT5 = "yyyy_MM_dd_hh_mm_ss";

	/** 日期时间格式 yyyyMMddhhmmss */
	public final static String DATETIME_FORMAT6 = "yyyyMMddhhmmss";

	/**
	 * 日期转String[默认个格式：yyyy-MM-dd HH:mm:ss]
	 *  
	 * @author zhang.yifeng 
	 * @param date 待转换的日期
	 * @return string 日期字符串
	 */
	public static String date2String(Date date) {
		return date2String(date, DATETIME_FORMAT1);
	}

	/**
	 * 日期转String[默认个格式：yyyy-MM-dd HH:mm:ss]
	 *  
	 * @author zhang.yifeng 
	 * @param date 待转换的日期
	 * @param dateFormat 日期格式
	 * @return 日期字符串
	 */
	public static String date2String(Date date, String dateFormat) {
		if (date == null || StringUtils.isNullOrEmpty(dateFormat)) {
			logger.warn("parameter date or dateFormat is null.");
			return "";
		}
		try {
			SimpleDateFormat format = new SimpleDateFormat(dateFormat);
			return format.format(date);
		} catch (Exception e) {
			logger.error("date2String error.", e);
		}
		return "";
		
	}
	
	/**
	 * str转日期
	 *  
	 * @author zhang.yifeng 
	 * @param dateStr 日期字符串
	 * @param dateFormat 日期格式
	 * @return 日期 (java.util.Date)
	 */
	public static Date str2Date(String dateStr, String dateFormat) {
		if (StringUtils.isNullOrEmpty(dateStr) || StringUtils.isNullOrEmpty(dateFormat)) {
			logger.warn("parameter dateStr or dateFormat is null.");
			return null;
		}
		Date date = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat(dateFormat);
			date = format.parse(dateStr);
		} catch (ParseException e) {
			logger.error("str parse to date error.", e);
		}
		return date;
	}

	/**
	 * str转java.sql.Date
	 *  
	 * @author zhang.yifeng 
	 * @param dateStr 日期字符串
	 * @param dateFormat 日期格式
	 * @return 日期 (java.sql.Date)
	 */
	public static java.sql.Date str2SQLDate(String dateStr, String dateFormat) {
		if (StringUtils.isNullOrEmpty(dateStr) || StringUtils.isNullOrEmpty(dateFormat)) {
			logger.warn("parameter dateStr or dateFormat is null.");
			return null;
		}
		java.sql.Date sqlDate = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat(dateFormat);
			Date utilDate = format.parse(dateStr);
			sqlDate = new java.sql.Date(utilDate.getTime());
		} catch (ParseException e) {
			logger.error("str parse to date error.", e);
		}
		return sqlDate ;
	}
	
	/**
	 * 获取当前时间
	 *  
	 * @author zhang.yifeng 
	 * @return Date 当前日期
	 */
	public static Date getNowTime() {
		long now = System.currentTimeMillis();
		return new Date(now);
	}
	
	/**
	 * 获取当前时间
	 *  
	 * @author zhang.yifeng 
	 * @param dateFormat 日期格式
	 * @return String 当前日期字符串
	 */
	public static String getNowTimeStr(String dateFormat) {
		if (StringUtils.isNullOrEmpty(dateFormat)) {
			logger.warn("parameter dateFormat is null.");
			return null;
		}
		Date now = getNowTime();
		return date2String(now, dateFormat);
	}
	
	/**
	 * 返回当前时间毫秒数[距离1970-01-01]
	 *  
	 * @author zhang.yifeng 
	 * @return String 毫秒数
	 */
	public static String getNowTimeMillSec() {
		long now = System.currentTimeMillis();
		return now + "";
	}
	
	public static void main(String[] args) {
		String now = getNowTimeMillSec();
		System.out.println("now:" + now);
		logger.info(now);
	}
}
