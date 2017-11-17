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
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
import com.yf.zx.core.util.common.FileUtils;

/**
 * SftpUtils
 * 
 * @author zhang.yifeng
 *  2017年6月21日
 * @version 1.0.0
 * @since 1.0.0
 * @see com.yf.zx.core.util.ftp
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

			logger.debug("========username:[{}]=======", username);
			logger.debug("========host:[{}]=======", host);
			logger.debug("========port:[{}]=======", port);
			logger.debug("========password:[{}]=======", password);
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
			logger.info("========connect to [{}] successfully！=======", host);
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
	 */
	public boolean sftpUploadFile(String directory, String uploadFile) {
		try {

			File file = new File(uploadFile);

			if (!FileUtils.judgeFileExist(uploadFile)) {
				logger.warn("=======文件[{}]不存在！=======", uploadFile);
				return false;
			}

			if (!connect()) {
				return false;
			}

			if (!createDir(directory, sftp)) {
				logger.warn("=======ftp目录[{}]创建失败！=======", directory);
				return false;
			}

			sftp.put(new FileInputStream(file), file.getName(), ChannelSftp.OVERWRITE);
		} catch (FileNotFoundException e) {
			logger.error("上传的文件不存在", e);
			return false;
		} catch (SftpException e) {
			logger.error("文件上传异常", e);
			return false;
		} finally {
			close();
		}
		return true;
	}

	/**
	 * 创建一个文件目录
	 *  
	 * @author zhang.yifeng 
	 * @param createpath 创建路径
	 * @param sftp ChannelSftp
	 * @return true/false
	 */
	public boolean createDir(String createpath, ChannelSftp sftp) {
		boolean succ = false;
		try {
			if (isDirExist(createpath, sftp)) {
				sftp.cd(createpath);
			}
			String pathArry[] = createpath.split("/");
			StringBuffer filePath = new StringBuffer("/");
			for (String path : pathArry) {
				if (path.equals("")) {
					continue;
				}
				filePath.append(path + "/");
				if (isDirExist(filePath.toString(), sftp)) {
					sftp.cd(filePath.toString());
				} else {
					// 建立目录
					sftp.mkdir(new String(filePath.toString()));
					// 进入并设置为当前目录
					sftp.cd(filePath.toString());
				}
			}
			sftp.cd(createpath);
			succ = true;
		} catch (SftpException e) {
			logger.error("创建路径错误：", e);
			succ = false;
		}
		return succ;
	}

	/**
	 * 判断目录是否存在
	 *  
	 * @author zhang.yifeng 
	 * @param directory 目录
	 * @param sftp ChannelSftp
	 * @return true/false
	 */
	public boolean isDirExist(String directory, ChannelSftp sftp) {
		boolean isDirExistFlag = false;
		try {
			SftpATTRS sftpATTRS = sftp.lstat(directory);
			isDirExistFlag = true;
			return sftpATTRS.isDir();
		} catch (Exception e) {
			if (e.getMessage().toLowerCase().equals("no such file")) {
				isDirExistFlag = false;
			}
		}
		return isDirExistFlag;
	}

	/**
	 * 下载文件
	 * 
	 * @param directory
	 *            下载目录
	 * @param downloadFile
	 *            下载的文件名
	 * @param saveFile
	 *            存在本地的路径[文件全路径]
	 * @return true/false
	 */
	public boolean download(String directory, String downloadFile, String saveFile) {
		boolean downloadflag = false;
		try {
			if (!connect()) {
				return downloadflag;
			}
			sftp.cd(directory);

			File file = new File(saveFile);
			if (!FileUtils.judgeFileExist(file.getParent())) {
				// 目录不存在
				if (!FileUtils.mkdirs(new File(file.getParent()))) {
					sftp.quit();
					logger.warn("=======本地下载目录[{}]创建失败！=======", file.getParent());
					return downloadflag;
				}
			}
			sftp.get(downloadFile, new FileOutputStream(file));
			logger.info("=======sftp 文件下载成功！=======");
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
		if (sftp != null) {
			sftp.quit();
			logger.info("=======sftp.quit ok！=======");
		}
		if (channel != null) {
			channel.disconnect();
			logger.info("=======channel.disconnect ok！=======");
		}
		if (sshSession != null) {
			sshSession.disconnect();
			logger.info("=======sshSession.disconnect ok！=======");
		}
	}

	public static void main(String[] args) throws Exception {
		String host = "10.0.0.2";
		int port = 22;
		String username = "mysftp";
		String password = "123";
		 new SftpUtils(host, port, username,
		 password).download("/upload/打算离开大陆客/daskll", "15051146029276428.xml",
		 "f:\\dddede\\15051146029276428.xml");

		new SftpUtils(host, port, username, password).sftpUploadFile("/upload/打算离开大陆客/daskll",
				"d:\\15051146029276428.xml");
	}
}
