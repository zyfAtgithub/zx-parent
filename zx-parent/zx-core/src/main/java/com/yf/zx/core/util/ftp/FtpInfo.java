package com.yf.zx.core.util.ftp;

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
	
	/** 要下载保存的本地目录 */
	private String localPath;
	
	/** 上次内容 */
	private String uploadContent;

	/** 上次文件名 */
	private String uploadFileName;
	
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
	
	public FtpInfo(String ftpIp, Integer ftpPort, String loginName, String loginPwd, String fileEncoding,
			String ftpPath, String localPath) {
		this.ftpIp = ftpIp;
		this.ftpPort = ftpPort;
		this.loginName = loginName;
		this.loginPwd = loginPwd;
		this.fileEncoding = fileEncoding;
		this.ftpPath = ftpPath;
		this.localPath = localPath;
	}

	public FtpInfo(String ftpIp, Integer ftpPort, String loginName, String loginPwd, String fileEncoding,
			String ftpPath, String uploadContent, String uploadFileName) {
		this.ftpIp = ftpIp;
		this.ftpPort = ftpPort;
		this.loginName = loginName;
		this.loginPwd = loginPwd;
		this.fileEncoding = fileEncoding;
		this.ftpPath = ftpPath;
		this.uploadContent = uploadContent;
		this.uploadFileName = uploadFileName;
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

	public String getLocalPath() {
		return localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

	public String getUploadContent() {
		return uploadContent;
	}

	public void setUploadContent(String uploadContent) {
		this.uploadContent = uploadContent;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	@Override
	public String toString() {
		return "FtpInfo [ftpIp=" + ftpIp + ", ftpPort=" + ftpPort + ", loginName=" + loginName + ", loginPwd="
				+ loginPwd + ", fileEncoding=" + fileEncoding + ", ftpPath=" + ftpPath + ", localPath=" + localPath
				+ ", uploadContent=" + uploadContent + ", uploadFileName=" + uploadFileName + "]";
	}
	
}
