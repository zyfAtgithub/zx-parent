package com.yf.zx.core.util.ftp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FTPUtils [FTP工具类，支持FTP协议]
 *  
 * @author zhang.yifeng
 * @CreateDate 2017年6月20日
 * @version 1.0.0
 * @since  1.0.0 
 * @see com.yf.core.util.ftp 
 *
 */
public class FTPUtils {

	/**
	 * logger
	 */
	private static Logger logger = LoggerFactory.getLogger(FTPUtils.class);

	
	/** 本地文件系统字符编码 */
	private static String LOCAL_CHARSET = System.getProperty("file.encoding");

	/**
	 * 获取FTP客户端
	 *  
	 * @author zhang.yifeng 
	 * @param ftpInfo FTP信息
	 * @return FTPClient
	 */
	private static FTPClient getFtpClient(FTPInfo ftpInfo) {
		if (ftpInfo == null) {
			logger.warn("FTP连接信息不能为空！");
			return null;
		}
		
		logger.info("FTP地址:[{}]，FTP端口:[{}]", ftpInfo.getFtpIp(), ftpInfo.getFtpPort());
		logger.info("FTP登录名:[{}]，FTP登录密码:[{}]", ftpInfo.getLoginName(), ftpInfo.getLoginPwd());
		
		FTPClient ftpClient =  new FTPClient();
		
		//建立FTP连接
		try {
			//默认21端口
			if (ftpInfo.getFtpPort() == null) {
				ftpClient.connect(ftpInfo.getFtpIp(), 21);
			}
			else {
				ftpClient.connect(ftpInfo.getFtpIp(), ftpInfo.getFtpPort());
			}
			logger.info("=======FTP连接成功！=======");
		} catch (SocketException e) {
			logger.error("=======FTP连接异常[Socket异常]！=======", e);
			return null;
		} catch (IOException e) {
			logger.error("=======FTP连接异常[IO异常]！=======", e);
			return null;
		}
		
		//登录FTP服务器
		try {
			boolean loginRes = ftpClient.login(ftpInfo.getLoginName(), ftpInfo.getLoginPwd());
			if (!loginRes){//登录失败
				logger.warn("=======FTP登录失败!=======");
				//断开连接
				disconnectFtp(ftpClient);
				return null;
			}
			logger.info("=======FTP登录成功!=======");
		} catch (IOException e) {
			logger.error("=======FTP登录异常！=======", e);
			return null;
		}

		try {
			// 开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码.
			if (FTPReply.isPositiveCompletion(ftpClient.sendCommand("OPTS UTF8", "ON"))) {
				LOCAL_CHARSET = "UTF-8";
			}
			ftpClient.setControlEncoding(LOCAL_CHARSET);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//检查连接是否成功
		int reply = ftpClient.getReplyCode();
		logger.info("replyCode:" + reply);
		if (!FTPReply.isPositiveCompletion(reply)) {
			//断开连接
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
			logger.info("=======FTP断连成功！=======");
		}
	}
	
	/**
	 * 下载FTP文件
	 *  
	 * @author zhang.yifeng 
	 * @param ftpInfo
	 */
	public static void downLoadFile(FTPInfo ftpInfo) {
		//1、获取FTP客户端
		FTPClient ftpClient = getFtpClient(ftpInfo);

		if (ftpClient == null) {
			logger.error("=======FTP客户端获取失败！=======");
			return;
		}
		
		if (ftpInfo.getFtpPath() == null) {
			logger.error("=======请设置要切换的FTP目录！=======");
			return;
		}
		
		//2、FTP客户端设置
		ftpClient.setRemoteVerificationEnabled(false);
		ftpClient.enterLocalPassiveMode();
		try {
			ftpClient.setFileType(FTP.ASCII_FILE_TYPE);
		} catch (IOException e) {
			logger.error("=======FTP设置文件类型异常！=======", e);
			return;
		}
		
		
		boolean changed = false;
		try {
			//切换工作目录
			changed = ftpClient.changeWorkingDirectory(new String(ftpInfo.getFtpPath().getBytes(ftpInfo.getFileEncoding()),"iso-8859-1"));
		} catch (UnsupportedEncodingException e) {
			logger.error("编码格式异常", e);
			return;
		} catch (IOException e) {
			logger.error("=======FTP工作目录切换异常！=======", e);
			return;
		}
		
		//切换成功，执行下载
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
			logger.error("=======FTP工作目录切换失败！=======");
		}
	}
	
	/**
	 * FTP文件上传 TODO
	 *  
	 * @author zhang.yifeng
	 */
	public static void uploadFile() {
		
	}
	
	public static void main(String[] args) throws IOException {
//		FTPInfo ftpGov = new FTPInfo("211.99.132.176", 10221, "ftpadmin", "ftpadmin");
//		FTPInfo ftpLocal = new FTPInfo("192.168.1.105", 21, "zftp", "zftp", "gbk", "aspnet_client/system_web/4_0_30319", "d:/");
		FTPInfo ftpGov = new FTPInfo("localhost", 21, "zftp", "1111", "gbk", "/", "e:\\ftpclient\\");
		downLoadFile(ftpGov);
		System.out.println("Hello");
	}
}
