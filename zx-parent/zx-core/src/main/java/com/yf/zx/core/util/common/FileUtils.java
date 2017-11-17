package com.yf.zx.core.util.common;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yf.zx.core.util.constants.Constants;

/**
 * 
 * FileUtils [文件工具类]
 *  
 * @author zhang.yifeng
 *  2017年6月25日
 * @version 1.0.0
 * @since  1.0.0 
 * @see com.yf.zx.core.util.common 
 *
 */
public class FileUtils {
	
	/**
	 * logger
	 */
	private static Logger logger = LoggerFactory.getLogger(FileUtils.class);
	
	/**
	 * 根据所给路径判断文件[目录]的存在性
	 * @param fileAbsPath 文件绝对路径
	 * @return true/false
	 */
	public static boolean judgeFileExist(String fileAbsPath) {
		if (StringUtils.isNullOrEmpty(fileAbsPath)) {
			return false;
		}
		return new File(fileAbsPath).exists();
	}
	
	/**
	 * 生成文件
	 * @param fileAbsPath
	 */
	private static void generateFile(String fileAbsPath) {
		
		if (judgeFileExist(fileAbsPath)) {
			return;
		}
		
		String pdirPath = fileAbsPath.substring(0,fileAbsPath.lastIndexOf(File.separator) + 1);
//		System.out.println("pdirPath:" + pdirPath);
		if (!judgeFileExist(pdirPath)) {
			File pdir = new File(pdirPath);
			boolean b = pdir.mkdirs();
			if (!b) {
				return;
			}
		}

		File f = new File(fileAbsPath);
		try {
			f.createNewFile();
		} catch (IOException e) {
			logger.error("文件创建失败！", e);
			return;
		}
	}
	
	/**
	 * 获取文件大小
	 *  
	 * @author zhang.yifeng 
	 * @param fileAbsPath 文件绝对路径
	 * @return long 文件大小
	 */
	public static long getFileSize(String fileAbsPath) {
		RandomAccessFile randomFile = null;
			// 打开一个随机访问文件流，按读写方式
			try {
				randomFile = new RandomAccessFile(fileAbsPath, "rw");
				return randomFile.length();
			} catch (IOException e) {
				logger.error("文件访问出错！", e);
			}
			finally {
				if (randomFile != null) {
					try {
						randomFile.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			// 文件长度，字节数
			
			return 0;
	}
	
	/**
	 * 文件重命名
	 * @param fileAbsPath 文件绝对路径
	 * @return true/false
	 */
	private static boolean renameFile(String fileAbsPath) {
		if (!judgeFileExist(fileAbsPath)) {
			//文件不存在，重命名失败
			return false;
		}
		
		String pdirPath = fileAbsPath.substring(0,fileAbsPath.lastIndexOf(File.separator) + 1);
		String fileName = fileAbsPath.substring(fileAbsPath.lastIndexOf(File.separator)  + 1);
	
		//使用时分秒命名
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		String newName = pdirPath + fileName.substring(0, fileName.lastIndexOf(".")) + "-" + sf.format(new Date()) + fileName.substring(fileName.lastIndexOf("."));
		File f = new File(fileAbsPath);
		File newF = new File(newName);

//		//只保留一个文件
//		if (deleteFile(historyFileName)) {
//			System.out.println("成功删除" + historyFileName + "!");
//		}
//		
//		historyFileName = newName;
		
		return f.renameTo(newF);
	}
	
	/**
	 *  
	 * @author zhang.yifeng 
	 * @param fileAbsPath 文件绝对路径
	 * @return true/false
	 */
	public static boolean deleteFile(String fileAbsPath) {
		if (!judgeFileExist(fileAbsPath)) {
			//文件不存在
			return false;
		}
		File f = new File(fileAbsPath);
		return f.delete();
	}
	
	/**
	 * 写文件
	 * @param fileName 文件路径
	 * @param content 内容
	 * @return true/false
	 */
	public static synchronized boolean appentContent2File(String fileName, String content) {
		return appentContent2File(fileName, Constants.FILESIZE_THRESHOLD, content);
	
	}

	/**
	 *  写文件
	 * @author zhang.yifeng 
	 * @param fileName 文件名
	 * @param size 文件上限
	 * @param content 内容
	 * @return true/false
	 */
	public static synchronized boolean appentContent2File(String fileName, long size, String content) {
		//文件对应目录不存在就创建
		generateFile(fileName);
		
		RandomAccessFile randomFile = null;
		try {
			// 打开一个随机访问文件流，按读写方式
			randomFile = new RandomAccessFile(fileName, "rw");
			// 文件长度，字节数
			long fileLength = randomFile.length();
			//文件大小超出限制
			if (fileLength + content.getBytes().length + 2 >= Constants.FILESIZE_THRESHOLD) {
				System.out.println("文件大小到达上限！！");
				if (randomFile != null) {
					try {
						randomFile.close();
					} catch (IOException e) {
						logger.error("文件流关闭出错！", e);
						return false;
					}
				}
				renameFile(fileName);
				return true;
			}
			
			// 将写文件指针移到文件尾。
			randomFile.seek(fileLength);
			randomFile.write((content + "\r\n").getBytes());
			return true;
		} catch (IOException e) {
			logger.error("文件访问异常！", e);
			return false;
		}
		finally {
			if (randomFile != null) {
				try {
					randomFile.close();
				} catch (IOException e) {
					logger.error("文件流关闭出错！", e);
					return false;
				}
			}
		}
	}
	
	/**
	 * 创建多级目录[目录不存在就创建，不在直接返回true]
	 *  
	 * @author zhang.yifeng 
	 * @param file 文件
	 * @return true/false
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
	 * @param file 文件
	 * @return boolean true/false
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
