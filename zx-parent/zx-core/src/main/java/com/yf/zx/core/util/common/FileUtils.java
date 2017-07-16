package com.yf.zx.core.util.common;

import java.io.File;
import java.io.IOException;

/**
 * 
 * FileUtils [文件工具类]
 *  
 * @author zhang.yifeng
 * @CreateDate 2017年6月25日
 * @version 1.0.0
 * @since  1.0.0 
 * @see com.yf.core.util.common 
 *
 */
public class FileUtils {

	/**
	 * 创建多级目录[目录不存在就创建，不在直接返回true]
	 *  
	 * @author zhang.yifeng 
	 * @param file
	 * @return
	 */
	public static boolean mkdirs(File file) {
		if (StringUtils.isNull(file)) {
			return false;
		}
		
		return file.mkdirs();
	}
	
	/**
	 * 判断文件的存在性
	 *  
	 * @author zhang.yifeng 
	 * @param file
	 * @return
	 */
	public static boolean fileExists(File file) {
		if (StringUtils.isNull(file)) {
			return false;
		}
		return file.exists();
	}
	
	public static void main(String[] args) throws IOException {
		File f = new File("d:\\dir\\test\\a");
		System.out.println(mkdirs(f));
	}

}
