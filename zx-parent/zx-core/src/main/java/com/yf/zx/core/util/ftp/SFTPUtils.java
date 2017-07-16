package com.yf.zx.core.util.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * SFTPUtils 
 *  
 * @author zhang.yifeng
 * @CreateDate 2017年6月21日
 * @version 1.0.0
 * @since  1.0.0 
 * @see com.yf.core.util.ftp 
 *
 */
public class SFTPUtils {
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
	public static ChannelSftp connect(String host, int port, String username, String password) throws JSchException {
		ChannelSftp sftp = null;
		JSch jsch = new JSch();
		jsch.getSession(username, host, port);
		Session sshSession = jsch.getSession(username, host, port);
		sshSession.setPassword(password);
		Properties sshConfig = new Properties();
		sshConfig.put("StrictHostKeyChecking", "no");
		sshSession.setConfig(sshConfig);
		sshSession.connect();
		Channel channel = sshSession.openChannel("sftp");
		channel.connect();
		sftp = (ChannelSftp) channel;
		return sftp;
	}

	/**
	 * 上传文件
	 * 
	 * @param directory
	 *            上传的目录
	 * @param uploadFile
	 *            要上传的文件
	 * @param sftp
	 * @throws SftpException
	 * @throws FileNotFoundException
	 * @throws JSchException
	 */
	public static void sftpUploadFile(String host, int port, String userName, String password, String directory,
			String uploadFile) throws FileNotFoundException, SftpException, JSchException {
		ChannelSftp sftp = connect(host, port, userName, password);
		sftp.cd(directory);
		File file = new File(uploadFile);
		sftp.put(new FileInputStream(file), file.getName());
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
	public static void download(String directory, String downloadFile, String saveFile, ChannelSftp sftp) {
		try {
			sftp.cd(directory);
			File file = new File(saveFile);
			sftp.get(downloadFile, new FileOutputStream(file));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sftp.disconnect();
			sftp.quit();
			sftp.exit();
		}
	}

	public static void main(String[] args) throws JSchException {
		String host = "42.123.92.9";
		int port = 12321;
		String username = "ismmysftp";
		String password = "TBPoBgObWXvx5ZEcVbb9jrmOn40W2zAX";
		ChannelSftp sftp = connect(host, port, username, password);
		download("upload/bak/3", "201705151020403.txt.bak", "d:\\aa.txt", sftp);
	}
}
