package com.yf.zx.core.util.constants;

import com.yf.zx.core.util.system.OperSystem;
import com.yf.zx.core.util.system.OperSystemType;

public final class Constants {
	/**
	 * 日志文件大小阀值（G）
	 */
	public static long FILESIZE_THRESHOLD = 1 * 1024 * 1024 * 1024l;
	
	public static final String STOR_DIR;
	static {
		if (OperSystemType.WINDOWS == OperSystem.judgeSystemType()) {
			STOR_DIR = "f:\\diatesting\\";
		}
		else {
			STOR_DIR = "/root/diatesting/";
		}
	}
	public static void main(String[] args) {
		String fileName = "f:\\diatesting\\9890-78.txt";
		int pos1 = fileName.lastIndexOf("-");
		int pos2 = fileName.lastIndexOf(".txt");
		System.out.println(pos1);
		System.out.println(pos2);
		System.out.println(fileName.substring(pos1 + 1, pos2));
	}
}