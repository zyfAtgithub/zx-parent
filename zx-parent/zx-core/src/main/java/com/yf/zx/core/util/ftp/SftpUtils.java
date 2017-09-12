package com.yf.zx.core.util.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * SftpUtils
 * 
 * @author zhang.yifeng
 * @CreateDate 2017年6月21日
 * @version 1.0.0
 * @since 1.0.0
 * @see com.yf.core.util.ftp
 *
 */
public class SftpUtils {
	
	/**
	 * logger
	 */
	private static Logger logger = LoggerFactory.getLogger(SftpUtils.class);

	/** Sftp主机 */
	private String host;
	
	/** Sftp主机端口 */
	private int port;
	
	/** Sftp 用户名 */
	private String username;

	/** Sftp 密码 */
	private String password;
	
	private Session sshSession;
	
	private Channel channel;

	private ChannelSftp sftp;

	public SftpUtils(String host, int port, String username, String password) {
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
	}

	/**
	 * 连接sftp服务器
	 * 
	 * @param host
	 *            主机
	 * @param port
	 *            端口
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 * @throws JSchException
	 */
	private boolean connect() {
		boolean connflag = false;
		JSch jsch = new JSch();
		try {
			sshSession = jsch.getSession(username, host, port);
			
			logger.info("========getSession ok！=======");
			
			sshSession.setPassword(password);
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			sshSession.setConfig(sshConfig);
			sshSession.connect();
			channel = sshSession.openChannel("sftp");
			logger.info("========openChannel ok！=======");
			channel.connect();
			logger.info("========connect ok！=======");
			sftp = (ChannelSftp) channel;
			connflag = true;
		} catch (JSchException e) {
			logger.error("========sftp连接异常！======", e);
			connflag = false;
		}
		return connflag;
	}

	/**
	 * 
	 * 上传文件
	 *  
	 * @author zhang.yifeng 
	 * 
	 * @param directory
	 *            上传的目录
	 * @param uploadFile
	 *            要上传的文件
	 * @return boolean [上传结果]
	 * @throws FileNotFoundException
	 * @throws SftpException
	 * @throws JSchException
	 */
	public boolean sftpUploadFile(String directory, String uploadFile) throws FileNotFoundException, SftpException, JSchException {
		if (!connect()) {
			return false;
		}
		sftp.cd(directory);
		File file = new File(uploadFile);
		sftp.put(new FileInputStream(file), file.getName());
		return true;
	}

	/**
	 * 下载文件
	 * 
	 * @param directory
	 *            下载目录
	 * @param downloadFile
	 *            下载的文件
	 * @param saveFile
	 *            存在本地的路径
	 * @param sftp
	 */
	public boolean download(String host, int port, String userName, String password, String directory, String downloadFile,
			String saveFile) {
		boolean downloadflag = false;
		try {
			if (!connect()) {
				return downloadflag;
			}
			sftp.cd(directory);
			File file = new File(saveFile);
			sftp.get(downloadFile, new FileOutputStream(file));
			logger.info("=======sftp 文件下载成功！=======");
			sftp.quit();
		} catch (Exception e) {
			downloadflag = false;
			logger.error("=======sftp 文件下载出现异常！=======", e);
		} finally {
			close();
		}
		return downloadflag;
	}

	/**
	 * 关闭SFTP的链接
	 *
	 */
	public void close() {
		if (channel != null) {
			channel.disconnect();
		}
		if (sshSession != null) {
			sshSession.disconnect();
		}
	}

	public static void main(String[] args) throws JSchException {
		String host = "10.0.0.2";
		int port = 22;
		String username = "mysftp";
		String password = "123";
		new SftpUtils(host, port, username, password).download(host, port, username, password, "/upload/cdn_home/4/2017-09-11", "15051146029276428.xml",
		 "d:\\15051146029276428.xml");
	}
}
