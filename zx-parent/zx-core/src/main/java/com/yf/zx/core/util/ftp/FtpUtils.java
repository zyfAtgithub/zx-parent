package com.yf.zx.core.util.ftp;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yf.zx.core.util.common.StringUtils;

/**
 * FTPUtils [FTP工具类，支持FTP协议]
 * 
 * @author zhang.yifeng
 * @CreateDate 2017年6月20日
 * @version 1.0.0
 * @since 1.0.0
 * @see com.yf.core.util.ftp
 *
 */
public class FtpUtils {

	/**
	 * logger
	 */
	private static Logger logger = LoggerFactory.getLogger(FtpUtils.class);

	/** 本地文件系统字符编码 */
	private static String LOCAL_FILEENCODING = System.getProperty("file.encoding");

	/** FTP路径分割符  */
	private static final String FTP_PATH_SEPARATOR = "/";
	
	/**
	 * 获取FTP客户端
	 * 
	 * @author zhang.yifeng
	 * @param ftpInfo
	 *            FTP信息
	 * @return FTPClient
	 */
	private static FTPClient getFtpClient(FtpInfo ftpInfo) {
		if (ftpInfo == null) {
			logger.warn("FTP连接信息不能为空！");
			return null;
		}

		logger.debug("FTP地址:[{}]，FTP端口:[{}]", ftpInfo.getFtpIp(), ftpInfo.getFtpPort());
		logger.debug("FTP登录名:[{}]，FTP登录密码:[{}]", ftpInfo.getLoginName(), ftpInfo.getLoginPwd());

		FTPClient ftpClient = new FTPClient();

		// 建立FTP连接
		try {
			// 默认21端口
			if (ftpInfo.getFtpPort() == null) {
				ftpClient.connect(ftpInfo.getFtpIp(), 21);
			} else {
				ftpClient.connect(ftpInfo.getFtpIp(), ftpInfo.getFtpPort());
			}
			logger.debug("=======FTP连接成功！=======");
		} catch (SocketException e) {
			logger.error("=======FTP连接异常[Socket异常]！=======", e);
			return null;
		} catch (IOException e) {
			logger.error("=======FTP连接异常[IO异常]！=======", e);
			return null;
		}

		// 登录FTP服务器
		try {
			boolean loginRes = ftpClient.login(ftpInfo.getLoginName(), ftpInfo.getLoginPwd());
			if (!loginRes) {// 登录失败
				logger.warn("=======FTP登录失败!=======");
				// 断开连接
				disconnectFtp(ftpClient);
				return null;
			}
			logger.debug("=======FTP登录成功!=======");
		} catch (IOException e) {
			logger.error("=======FTP登录异常！=======", e);
			return null;
		}

		try {
			// 开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码.
			if (FTPReply.isPositiveCompletion(ftpClient.sendCommand("OPTS UTF8", "ON"))) {
				LOCAL_FILEENCODING = "UTF-8";
			}
			ftpClient.setControlEncoding(LOCAL_FILEENCODING);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 检查连接是否成功
		int reply = ftpClient.getReplyCode();
		logger.debug("=======replyCode:{}=======", reply);
		if (!FTPReply.isPositiveCompletion(reply)) {
			// 断开连接
			disconnectFtp(ftpClient);
			return null;
		}
		return ftpClient;
	}

	/**
	 * FTP断连
	 * 
	 * @author zhang.yifeng
	 * @param ftpClient
	 */
	private static void disconnectFtp(FTPClient ftpClient) {
		if (ftpClient == null) {
			logger.warn("FtpClient不存在！");
			return;
		}
		if (ftpClient != null && ftpClient.isConnected()) {
			try {
				ftpClient.logout();
				ftpClient.disconnect();
			} catch (IOException e) {
				logger.error("=======FTP断连失败！=======", e);
			}
			logger.debug("=======FTP断连成功！=======");
		}
	}

	/**
	 * 下载FTP文件
	 * 
	 * @author zhang.yifeng
	 * @param ftpInfo
	 */
	public static boolean downLoadFile(FtpInfo ftpInfo) {
		boolean downflag = false;
		
		if (StringUtils.isNullOrEmpty(ftpInfo.getFtpPath())) {
			logger.warn("=======下载路径不能为空！=======");
			return downflag;
		}

		// 1、获取FTP客户端
		FTPClient ftpClient = getFtpClient(ftpInfo);

		if (ftpClient == null) {
			logger.warn("=======FTP客户端获取失败！=======");
			return downflag;
		}


		// 2、FTP客户端设置
		ftpClient.setRemoteVerificationEnabled(false);
		ftpClient.enterLocalPassiveMode();
		try {
			ftpClient.setFileType(FTP.ASCII_FILE_TYPE);
		} catch (IOException e) {
			logger.error("=======FTP设置文件类型异常！=======", e);
			return downflag;
		}

		boolean changed = false;
		try {
			//3、切换工作目录
			changed = ftpClient.changeWorkingDirectory(
					new String(ftpInfo.getFtpPath().getBytes(ftpInfo.getFileEncoding()), "iso-8859-1"));
		} catch (UnsupportedEncodingException e) {
			logger.error("编码格式异常", e);
			return downflag;
		} catch (IOException e) {
			logger.error("=======FTP工作目录切换异常！=======", e);
			return downflag;
		}

		//4、切换工作目录成功，执行下载
		if (changed) {
			try {
				FTPFile[] f = ftpClient.listFiles();
				logger.info("远程文件个数:[{}]", f.length);
				for (FTPFile ftpFile : f) {
					try {
						File localFile = new File(ftpInfo.getLocalPath() + ftpFile.getName());
						OutputStream is = new FileOutputStream(localFile);
						ftpClient.retrieveFile(ftpFile.getName(), is);
						is.close();
					} catch (IOException e) {
						logger.error("=======FTP下载文件时异常！=======", e);
					}
				}
			} catch (IOException e) {
				logger.error("=======FTP下载文件时异常！=======", e);
			} finally {
				disconnectFtp(ftpClient);
			}
		} 
		else {
			logger.warn("=======FTP工作目录[{}]切换失败！=======", ftpInfo.getFtpPath());
		}
		return downflag;
	}

	/**
	 * FTP文件上传
	 * 
	 * @param ftpInfo
	 * @author zhang.yifeng
	 */
	public static boolean uploadFile(FtpInfo ftpInfo) {
		boolean flag = false;
		if (StringUtils.isNullOrEmpty(ftpInfo.getFileEncoding())) {
			ftpInfo.setFileEncoding(LOCAL_FILEENCODING);
		}
		
		if (StringUtils.isNullOrEmpty(ftpInfo.getFtpPath())) {
			logger.warn("=======上传路径不能为空！=======");
			return flag;
		}

		if (StringUtils.isNullOrEmpty(ftpInfo.getFtpFileName())) {
			logger.warn("=======ftp文件名不能为空！=======");
			return flag;
		}
		
		if (StringUtils.isNullOrEmpty(ftpInfo.getUploadContent()) && StringUtils.isNull(ftpInfo.getLocalFile())) {
			logger.warn("=======上传内容不能为空！=======");
			return flag;
		}
		
		// 1、获取FTP客户端
		FTPClient ftpClient = getFtpClient(ftpInfo);

		if (ftpClient == null) {
			logger.warn("=======FTP连接失败！=======");
			return flag;
		}

		InputStream is = null;
		
		// 2、FTP客户端设置
		ftpClient.setRemoteVerificationEnabled(false);
		ftpClient.enterLocalPassiveMode();
		try {
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			
			//3、切换目录，不存在就创建	
			logger.debug("FTP当前目录[{}]", ftpClient.printWorkingDirectory());
			if (!createDir(ftpClient, ftpInfo)) {
				logger.debug("FTP当前目录[{}]", ftpClient.printWorkingDirectory());
				throw new RuntimeException("切入FTP目录失败：" + ftpInfo.getFtpPath());  
			}
			
			ftpClient.enterLocalPassiveMode();
			
			//优先使用UploadContent
			if (StringUtils.isNotNullAndEmpty(ftpInfo.getUploadContent())) {
				is = new ByteArrayInputStream(ftpInfo.getUploadContent().getBytes("UTF-8"));
			}
			else if (StringUtils.isNotNull(ftpInfo.getLocalFile())) {
				is = new FileInputStream(ftpInfo.getLocalFile());
			}
			String fileName = new String(ftpInfo.getFtpFileName().getBytes(ftpInfo.getFileEncoding()),"iso-8859-1");
			flag = ftpClient.storeFile(fileName, is);
			if (flag) {
				logger.info("=======上传成功!=======");
		 	}
			else {
				logger.info("=======FTP上传上报数据时出现错误，上传失败！=======");
	        }
		} catch (IOException e) {
			logger.error("=======FTP设置文件类型异常！=======", e);
			return false;
		}
		finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					logger.error("=======输入流关闭异常！=======", e);
				}
			}
			disconnectFtp(ftpClient);
		}
		
		return flag;
		
	}

	private static boolean createDir(FTPClient ftpClient, FtpInfo ftpInfo) {
		if (StringUtils.isNullOrEmpty(ftpInfo.getFtpPath())) {
			return false;
		}
		
		try {
			String dir = new String(ftpInfo.getFtpPath().getBytes(ftpInfo.getFileEncoding()), "iso-8859-1");
			
			//尝试切入目录
			if (ftpClient.changeWorkingDirectory(dir)) {
				//1、成功切入。直接return true
				return true;
			}
			//2、切入失败，循环创建子目录
			String[] subDirs = dir.split(FTP_PATH_SEPARATOR);
			StringBuffer sbfDir = new StringBuffer(FTP_PATH_SEPARATOR);
			for (String subDir : subDirs) {
				sbfDir.append(FTP_PATH_SEPARATOR).append(subDir);
				//3、该级目录已存在，进入下一级目录
				if (ftpClient.changeWorkingDirectory(sbfDir.toString())) {
					continue;
				}
				//4、该级目录不存在，创建
				if (!ftpClient.makeDirectory(sbfDir.toString())) {
					//创建失败
					logger.warn("=======目录[{}]创建失败！=======", sbfDir.toString());
					return false;
				}
				logger.debug("=======成功创建目录[{}]！=======", sbfDir.toString());
				logger.debug("切换到跟目录[{}]",ftpClient.changeWorkingDirectory("/"));
			}
			
			//将目录切换至指定路径
			return ftpClient.changeWorkingDirectory(dir); 
		
		}  catch (UnsupportedEncodingException e) {
			logger.error("=======编码格式异常！=======", e);
			return false;
		} catch (IOException e) {
			logger.error("=======FTP工作目录切换异常！=======", e);
			return false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		FtpInfo ftpGov = new FtpInfo("10.0.0.2", 21, "zftp", "123456", "gbk", "cdn_home/1", "", "F:\\ftp\\", 1);
		downLoadFile(ftpGov);
		File f = new File("F:\\BaiduNetdiskDownload\\传智播客_Activiti工作流视频（企业开发实例讲解）.zip");
		FtpInfo ftpGov2 = new FtpInfo("10.0.0.2", 21, "zftp", "123456", "gbk", "cdn_home/1/99", f, "传智播客_Activiti工作流视频（企业开发实例讲解）.zip");
		uploadFile(ftpGov2);
	}
}
