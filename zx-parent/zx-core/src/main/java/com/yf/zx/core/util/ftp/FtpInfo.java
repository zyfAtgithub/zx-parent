package com.yf.zx.core.util.ftp;

import java.io.File;

/**
 * FtpInfo [Ftp信息]
 * 
 * @author zhang.yifeng
 * @CreateDate 2017年6月20日
 * @version 1.0.0
 * @since  1.0.0 
 * @see com.yf.core.util.ftp 
 *
 */
public class FtpInfo {

	/** FTP地址 */
	private String ftpIp;
	
	/** 端口 */
	private Integer ftpPort;
	
	/**登录名 */
	private String loginName;
	
	/**登录密码 */
	private String loginPwd;
	
	/**文件系统编码 */
	private String fileEncoding;
	
	/**要切换的FTP目录*/
	private String ftpPath;

	/** ftp文件名 */
	private String ftpFileName;
	
	/** 本地目录 */
	private String localPath;
	
	/** 本地文件名 */
	private String localFileName;

	/** 上传内容 */
	private String uploadContent;

	/** 本地待上传的文件 */
	private File localFile;
	
	public FtpInfo(String ftpIp, int ftpPort, String loginName, String loginPwd) {
		this.ftpIp = ftpIp;
		this.ftpPort = ftpPort;
		this.loginName = loginName;
		this.loginPwd = loginPwd;
		//获取系统文件编码
		this.fileEncoding = System.getProperty("file.encoding");
	}

	public FtpInfo(String ftpIp, Integer ftpPort, String loginName, String loginPwd, String fileEncoding) {
		this.ftpIp = ftpIp;
		this.ftpPort = ftpPort;
		this.loginName = loginName;
		this.loginPwd = loginPwd;
		this.fileEncoding = fileEncoding;
	}
	
	/**
	 * ftp 上传
	 * @param ftpIp FTP地址
	 * @param ftpPort 端口
	 * @param loginName 登录名
	 * @param loginPwd 登录密码
	 * @param fileEncoding 文件系统编码
	 * @param ftpPath 要切换的FTP目录
	 * @param uploadFile
	 * @param ftpFileName
	 */
	public FtpInfo(String ftpIp, Integer ftpPort, String loginName, String loginPwd, String fileEncoding,
			String ftpPath, File localFile, String ftpFileName) {
		this.ftpIp = ftpIp;
		this.ftpPort = ftpPort;
		this.loginName = loginName;
		this.loginPwd = loginPwd;
		this.fileEncoding = fileEncoding;
		this.ftpPath = ftpPath;
		this.localFile = localFile;
		this.ftpFileName = ftpFileName;
	}

	/**
	 * ftp 上传/下载
	 * @param ftpIp FTP地址
	 * @param ftpPort 端口
	 * @param loginName 登录名
	 * @param loginPwd 登录密码
	 * @param fileEncoding 文件系统编码
	 * @param ftpPath 要切换的FTP目录
	 * @param para1 [参数1，mode=1时，表示上传内容 ,mode=2时，表示要下载的ftp文件名]
	 * @param para2 [参数1，mode=1时，表示上传文件名 ,mode=2时，表示要下载保存的本地目录]
	 * @param mode [1：上传，2：下载]
	 */
	public FtpInfo(String ftpIp, Integer ftpPort, String loginName, String loginPwd, String fileEncoding,
			String ftpPath, String para1, String para2, int mode) {
		this.ftpIp = ftpIp;
		this.ftpPort = ftpPort;
		this.loginName = loginName;
		this.loginPwd = loginPwd;
		this.fileEncoding = fileEncoding;
		this.ftpPath = ftpPath;
		if (1 == mode) {
			//上传
			this.uploadContent = para1;
			this.ftpFileName = para2;
		}
		else if (2 == mode) {
			//下载
			this.localFileName = para1;
			this.localPath = para2;
		}
	}

	public String getFtpIp() {
		return ftpIp;
	}

	public void setFtpIp(String ftpIp) {
		this.ftpIp = ftpIp;
	}

	public Integer getFtpPort() {
		return ftpPort;
	}

	public void setFtpPort(Integer ftpPort) {
		this.ftpPort = ftpPort;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getFileEncoding() {
		return fileEncoding;
	}

	public void setFileEncoding(String fileEncoding) {
		this.fileEncoding = fileEncoding;
	}

	public String getFtpPath() {
		return ftpPath;
	}

	public void setFtpPath(String ftpPath) {
		this.ftpPath = ftpPath;
	}

	public String getFtpFileName() {
		return ftpFileName;
	}

	public void setFtpFileName(String ftpFileName) {
		this.ftpFileName = ftpFileName;
	}

	public String getLocalPath() {
		return localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

	public String getLocalFileName() {
		return localFileName;
	}

	public void setLocalFileName(String localFileName) {
		this.localFileName = localFileName;
	}

	public String getUploadContent() {
		return uploadContent;
	}

	public void setUploadContent(String uploadContent) {
		this.uploadContent = uploadContent;
	}

	public File getLocalFile() {
		return localFile;
	}

	public void setLocalFile(File localFile) {
		this.localFile = localFile;
	}
}
