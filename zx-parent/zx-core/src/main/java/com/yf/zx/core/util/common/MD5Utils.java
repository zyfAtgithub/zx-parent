package com.yf.zx.core.util.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

public class MD5Utils {
	/** 
	   * @param str 
	   * @return  
	   * @Description: 32位小写MD5 
	   */
	  public static String parseStrToMd5L32(String str){ 
	    String reStr = null; 
	    try { 
	      MessageDigest md5 = MessageDigest.getInstance("MD5"); 
	      byte[] bytes = md5.digest(str.getBytes("UTF-8")); 
	      StringBuffer stringBuffer = new StringBuffer(); 
	      for (byte b : bytes){ 
	        int bt = b&0xff; 
	        if (bt < 16){ 
	          stringBuffer.append(0); 
	        }  
	        stringBuffer.append(Integer.toHexString(bt)); 
	      } 
	      reStr = stringBuffer.toString(); 
	    } catch (Exception e) { 
	      e.printStackTrace(); 
	    } 
	    return reStr; 
	  } 
	  
	  /**
	   *  32位小写的md5[入参为 byte数组] 方法重载
	   * @param input
	   * @return
	   */
	  public static String parseStrToMd5L32(byte[] input){ 
		  String reStr = null; 
		  try { 
			  MessageDigest md5 = MessageDigest.getInstance("MD5"); 
			  byte[] bytes = md5.digest(input); 
			  StringBuffer stringBuffer = new StringBuffer(); 
			  for (byte b : bytes){ 
				  int bt = b&0xff; 
				  if (bt < 16){ 
					  stringBuffer.append(0); 
				  }  
				  stringBuffer.append(Integer.toHexString(bt)); 
			  } 
			  reStr = stringBuffer.toString(); 
		  } catch (Exception e) { 
			  e.printStackTrace(); 
		  } 
		  return reStr; 
	  } 
	  
	  /**
	   * 工信部算法
	   * @param input
	   * @return
	   */
	  public static String getMd5(byte[] input) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(input);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		
		byte[] bytes = md.digest();
		StringBuilder ret = new StringBuilder(bytes.length << 1);
		for (int i = 0; i < bytes.length; i++) {
			ret.append(Character.forDigit((bytes[i] >> 4) & 0xf, 16));
			ret.append(Character.forDigit(bytes[i] & 0xf, 16));
		}
		
		return ret.toString();
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		byte[] input = {80, 75, 3, 4, 20, 0, 8, 8, 8, 0, 101, 121, -123, 74, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 48, 93, -110, 65, 75, -61, 48, 24, -122, -1, 74, -23, 125, -74, -39, -100, -45, -111, 101, -56, 68, -40, 101, 8, -50, -117, -73, -104, 68, 13, -84, 73, 105, -38, 97, -113, 122, 18, -90, 120, 16, -15, 50, -16, -28, 77, 16, 15, -126, -116, -127, 127, -58, -43, -7, 47, 108, -110, 118, -99, -26, -12, 126, -49, -5, 38, 95, -14, -75, -80, 123, 17, -116, -100, 49, -117, 20, -105, -94, -29, -126, 13, -33, 117, -104, 32, -110, 114, 113, -42, 113, -113, -122, -5, -75, 109, -41, 81, 49, 22, 20, -113, -92, 96, 29, 55, 101, -54, -19, 34, 120, -126, 21, 39, 125, 113, 42, 17, 76, 66, -118, 99, 102, 53, -95, -94, 79, 17, -88, 55, -96, 103, -91, 38, 3, 28, 48, 68, -104, 98, 6, -102, 74, -29, 93, 74, 43, -86, 11, 72, 100, 20, 34, 66, -103, -54, -111, -106, -16, 92, 38, -86, 56, -39, 74, -118, 90, -48, 43, -91, 101, -26, -68, -20, 125, -78, 124, 125, -56, -90, -77, -20, -6, 19, 20, 9, -37, -56, -56, 97, 26, 50, 84, 114, 83, -64, -120, -119, -72, 39, -125, 16, -117, -44, 4, -21, -7, -126, -34, 127, -70, 30, -21, -17, -11, 6, 73, -128, -80, -94, -7, -5, 26, 127, -78, -123, 101, -101, 29, 68, 114, -52, 5, 97, -88, 81, -9, -13, 85, 116, 93, 81, 27, -22, -15, 56, -43, 1, -80, 10, 24, 82, -104, 50, 17, -91, -67, 85, -38, -106, -39, -128, -98, 86, -10, -10, -76, -100, -36, 125, 79, 47, 23, -73, -113, 95, -77, -105, -59, -57, -43, -49, -4, 62, 123, -98, 47, 110, 102, -59, 22, 51, 83, -93, -114, 121, -104, 63, -80, 89, -35, 70, -125, 114, -112, 102, -68, -34, -6, 87, -116, 121, -64, 14, 99, 28, -28, -101, 124, -48, -86, -7, -101, 53, -65, -23, -128, 102, 27, -128, -74, -65, 3, -67, -54, -122, 94, -11, 31, -4, 2, 80, 75, 7, 8, -64, 44, -8, -128, 84, 1, 0, 0, 74, 2, 0, 0};
		String md51 = parseStrToMd5L32(input);
		String md52 = getMd5(input);
		System.out.println("md51.equals(md52):" + md51.equals(md52));
	}
	  
	  /** 
	   * @param str 
	   * @return  
	   * @Description: 32位大写MD5 
	   */
	  public static String parseStrToMd5U32(String str){ 
	    String reStr = parseStrToMd5L32(str); 
	    if (reStr != null){ 
	      reStr = reStr.toUpperCase(); 
	    } 
	    return reStr; 
	  } 
	    
	  /** 
	   * @param str 
	   * @return 
	   * @Description: 16位小写MD5 
	   */
	  public static String parseStrToMd5U16(String str){ 
	    String reStr = parseStrToMd5L32(str); 
	    if (reStr != null){ 
	      reStr = reStr.toUpperCase().substring(8, 24); 
	    } 
	    return reStr; 
	  } 
	    
	  /** 
	   * @param str 
	   * @return 
	   * @Description: 16位大写MD5 
	   */
	  public static String parseStrToMd5L16(String str){ 
	    String reStr = parseStrToMd5L32(str); 
	    if (reStr != null){ 
	      reStr = reStr.substring(8, 24); 
	    } 
	    return reStr; 
	  } 
}
