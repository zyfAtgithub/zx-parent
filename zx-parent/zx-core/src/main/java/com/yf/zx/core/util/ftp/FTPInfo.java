package com.yf.zx.core.util.ftp;

/**
 * FTPInfo [FTP信息]
 * 
 * @author zhang.yifeng
 * @CreateDate 2017年6月20日
 * @version 1.0.0
 * @since  1.0.0 
 * @see com.yf.core.util.ftp 
 *
 */
public class FTPInfo {

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
	
	public FTPInfo(String ftpIp, int ftpPort, String loginName, String loginPwd) {
		this.ftpIp = ftpIp;
		this.ftpPort = ftpPort;
		this.loginName = loginName;
		this.loginPwd = loginPwd;
		//获取系统文件编码
		this.fileEncoding = System.getProperty("file.encoding");
	}

	public FTPInfo(String ftpIp, Integer ftpPort, String loginName, String loginPwd, String fileEncoding) {
		this.ftpIp = ftpIp;
		this.ftpPort = ftpPort;
		this.loginName = loginName;
		this.loginPwd = loginPwd;
		this.fileEncoding = fileEncoding;
	}
	
	public FTPInfo(String ftpIp, Integer ftpPort, String loginName, String loginPwd, String fileEncoding,
			String ftpPath, String localPath) {
		this.ftpIp = ftpIp;
		this.ftpPort = ftpPort;
		this.loginName = loginName;
		this.loginPwd = loginPwd;
		this.fileEncoding = fileEncoding;
		this.ftpPath = ftpPath;
		this.localPath = localPath;
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

	@Override
	public String toString() {
		return "FTPInfo [ftpIp=" + ftpIp + ", ftpPort=" + ftpPort + ", loginName=" + loginName + ", loginPwd="
				+ loginPwd + ", fileEncoding=" + fileEncoding + ", ftpPath=" + ftpPath + ", localPath=" + localPath
				+ "]";
	}
	
}
