package com.yf.zx.core.util.email;

// 文件名 SendEmail.java
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	
	/** 发件人电子邮箱 */
	private static final String MAIL_SENDER = "system_hdp@ctmcdn.cn";

	/** 发件人密码 */
	private static final String MAIL_SENDER_PWD = "04e9cd91354c3c4d";

	/** 发件人密码 */
	private static final String MAIL_SMTP_AUTH = "true";
	
	/** 邮件服务器 */
	private static final String MAIL_SMTP_HOST = "mail.ctmcdn.cn";
	
	/** 收件人电子邮箱*/
	private static final String MAIL_RECEIVER = "15295778261@163.com";
	
	
	public static void main(String[] args) {

		// 获取系统属性
		Properties properties = System.getProperties();

		// 设置邮件服务器
		properties.setProperty("mail.smtp.host", MAIL_SMTP_HOST);
		properties.put("mail.smtp.auth", MAIL_SMTP_AUTH);

		// 获取默认session对象
		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(MAIL_SENDER, MAIL_SENDER_PWD); // 发件人邮件用户名、密码
			}
		});

		try {
			// 创建默认的 MimeMessage 对象
			MimeMessage message = new MimeMessage(session);

			// Set From: 头部头字段
			message.setFrom(new InternetAddress(MAIL_SENDER));

			// Set To: 头部头字段
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(MAIL_RECEIVER));

			// Set Subject: 头部头字段
			message.setSubject("This is the Subject Line!");

			// 设置消息体
			message.setText("This is actual message");

			// 发送消息
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
}