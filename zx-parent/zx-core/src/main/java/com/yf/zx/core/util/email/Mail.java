package com.yf.zx.core.util.email;
import java.util.Properties;
 
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
 
public class Mail {
 
    private MimeMessage mimeMsg; // MIME邮件对象
    private Session session; // 邮件会话对象
    private Properties props; // 系统属性
    // smtp认证用户名和密码
    private String username;
    private String password;
    private Multipart mp; // Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成MimeMessage对象
 
    /**
     * Constructor
     * 
     * @param smtp
     *            邮件发送服务器
     */
    public Mail(String smtp) {
        setSmtpHost(smtp);
        createMimeMessage();
    }
 
    /**
     * 设置邮件发送服务器
     * 
     * @param hostName
     *            String
     */
    public void setSmtpHost(String hostName) {
        if (props == null)
            props = System.getProperties(); // 获得系统属性对象
        props.put("mail.smtp.host", hostName); // 设置SMTP主机
    }
 
    /**
     * 创建MIME邮件对象
     * 
     * @return boolean
     */
    public boolean createMimeMessage() {
        try {
            session = Session.getDefaultInstance(props, null); // 获得邮件会话对象
        } catch (Exception e) {
            System.err.println("获取邮件会话对象时发生错误！" + e);
            return false;
        }
 
        try {
            mimeMsg = new MimeMessage(session); // 创建MIME邮件对象
            mp = new MimeMultipart();
 
            return true;
        } catch (Exception e) {
            System.err.println("创建MIME邮件对象失败！" + e);
            return false;
        }
    }
 
    /**
     * 设置SMTP是否需要验证
     * 
     * @param need 是否需要进行身份校验
     */
    public void setNeedAuth(boolean need) {
        if (props == null)
            props = System.getProperties();
        if (need) {
            props.put("mail.smtp.auth", "true");
        } else {
            props.put("mail.smtp.auth", "false");
        }
    }
 
    /**
     * 设置用户名和密码
     * 
     * @param name 用户名
     * @param pass 密码
     */
    public void setNamePass(String name, String pass) {
        username = name;
        password = pass;
    }
 
    /**
     * 设置邮件主题
     * 
     * @param mailSubject 主题
     * @return boolean
     */
    public boolean setSubject(String mailSubject) {
        try {
            mimeMsg.setSubject(mailSubject);
            return true;
        } catch (Exception e) {
            System.err.println("设置邮件主题发生错误！");
            return false;
        }
    }
 
    /**
     * 设置邮件正文
     * 
     * @param mailBody 正文
     *            String
     * @return boolean
     */
    public boolean setBody(String mailBody) {
        try {
            BodyPart bp = new MimeBodyPart();
            bp.setContent("" + mailBody, "text/html;charset=utf-8");
            mp.addBodyPart(bp);
 
            return true;
        } catch (Exception e) {
            System.err.println("设置邮件正文时发生错误！" + e);
            return false;
        }
    }
 
    /**
     * 添加附件
     * 
     * @param filename 附件路径 String
     * @return boolean
     */
    public boolean addFileAffix(String filename) {
 
        try {
            BodyPart bp = new MimeBodyPart();
            FileDataSource fileds = new FileDataSource(filename);
            bp.setDataHandler(new DataHandler(fileds));
            bp.setFileName(fileds.getName());
 
            mp.addBodyPart(bp);
 
            return true;
        } catch (Exception e) {
            System.err.println("增加邮件附件：" + filename + "发生错误！" + e);
            return false;
        }
    }
 
    /**
     * 设置发信人
     * 
     * @param from 发信人
     *            String
     * @return boolean
     */
    public boolean setFrom(String from) {
        try {
            mimeMsg.setFrom(new InternetAddress(from)); // 设置发信人
            return true;
        } catch (Exception e) {
            return false;
        }
    }
 
    /**
     * 设置收信人
     * 
     * @param to 收信人 String
     * @return boolean
     */
    public boolean setTo(String to) {
        if (to == null)
            return false;
        try {
            mimeMsg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
 
    /**
     * 设置抄送人
     * 
     * @param copyto 抄送人 String
     * @return boolean
     */
    public boolean setCopyTo(String copyto) {
        if (copyto == null)
            return false;
        try {
            mimeMsg.setRecipients(Message.RecipientType.CC,
                    (Address[]) InternetAddress.parse(copyto));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
 
    /**
     * 发送邮件
     * @return boolean
     */
    public boolean sendOut() {
        try {
            mimeMsg.setContent(mp);
            mimeMsg.saveChanges();
            System.out.println("正在发送邮件....");
 
            Session mailSession = Session.getInstance(props, null);
            Transport transport = mailSession.getTransport("smtp");
            transport.connect((String) props.get("mail.smtp.host"), username,
                    password);
            transport.sendMessage(mimeMsg,
                    mimeMsg.getRecipients(Message.RecipientType.TO));
//          如果抄送人为null  不添加抄送人
            if(mimeMsg.getRecipients(Message.RecipientType.CC) != null)
                transport.sendMessage(mimeMsg,mimeMsg.getRecipients(Message.RecipientType.CC));
            // transport.send(mimeMsg);
 
            System.out.println("发送邮件成功！");
            transport.close();
 
            return true;
        } catch (Exception e) {
            System.err.println("邮件发送失败！" + e);
            e.printStackTrace();
            return false;
        }
    }
 
    /**
     * 调用sendOut方法完成邮件发送
     * 
     * @param smtp smtp邮件服务器
     * @param from 发件人
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     * @param username 用户名
     * @param password 密码
     * @return boolean
     */
    public static boolean send(String smtp, String from, String to,
            String subject, String content, String username, String password) {
        Mail theMail = new Mail(smtp);
        theMail.setNeedAuth(true); // 需要验证
 
        if (!theMail.setSubject(subject))
            return false;
        if (!theMail.setBody(content))
            return false;
        if (!theMail.setTo(to))
            return false;
        if (!theMail.setFrom(from))
            return false;
        theMail.setNamePass(username, password);
 
        if (!theMail.sendOut())
            return false;
        return true;
    }
 
    /**
     * 调用sendOut方法完成邮件发送,带抄送
     * 
     * @param smtp smtp邮件服务器
     * @param from 发件人
     * @param to 收件人
     * @param copyto 抄送
     * @param subject 主题
     * @param content 内容
     * @param username 用户名
     * @param password 密码
     * @return boolean
     */
    public static boolean sendAndCc(String smtp, String from, String to,
            String copyto, String subject, String content, String username,
            String password) {
        Mail theMail = new Mail(smtp);
        theMail.setNeedAuth(true); // 需要验证
 
        if (!theMail.setSubject(subject))
            return false;
        if (!theMail.setBody(content))
            return false;
        if (!theMail.setTo(to))
            return false;
        if (!theMail.setCopyTo(copyto))
            return false;
        if (!theMail.setFrom(from))
            return false;
        theMail.setNamePass(username, password);
 
        if (!theMail.sendOut())
            return false;
        return true;
    }
 
    /**
     * 调用sendOut方法完成邮件发送,带附件
     * @param smtp smtp邮件服务器
     * @param from 发件人
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     * @param username 用户名
     * @param password 密码
     * @param filename 附件路径
     * @return boolean
     */
    public static boolean send(String smtp, String from, String to,
            String subject, String content, String username, String password,
            String filename) {
        Mail theMail = new Mail(smtp);
        theMail.setNeedAuth(true); // 需要验证
 
        if (!theMail.setSubject(subject))
            return false;
        if (!theMail.setBody(content))
            return false;
        if (!theMail.addFileAffix(filename))
            return false;
        if (!theMail.setTo(to))
            return false;
        if (!theMail.setFrom(from))
            return false;
        theMail.setNamePass(username, password);
 
        if (!theMail.sendOut())
            return false;
        return true;
    }
 
    /**
     * 调用sendOut方法完成邮件发送,带附件和抄送
     * @param smtp smtp邮件服务器
     * @param from 发件人
     * @param to 收件人
     * @param copyto 抄送
     * @param subject 主题
     * @param content 内容
     * @param username 用户名
     * @param password 密码
     * @param filename 附件路径
     * @return boolean
     */
    public static boolean sendAndCc(String smtp, String from, String to,
            String copyto, String subject, String content, String username,
            String password, String filename) {
        Mail theMail = new Mail(smtp);
        theMail.setNeedAuth(true); // 需要验证
 
        if (!theMail.setSubject(subject))
            return false;
        if (!theMail.setBody(content))
            return false;
        if (!theMail.addFileAffix(filename))
            return false;
        if (!theMail.setTo(to))
            return false;
        if (!theMail.setCopyTo(copyto))
            return false;
        if (!theMail.setFrom(from))
            return false;
        theMail.setNamePass(username, password);
 
        if (!theMail.sendOut())
            return false;
        return true;
    }
 
    public static void main(String[] args) {
////        SMTP服务器
        String smtp = "smtp.163.com";
//      发信人
        String from = "ismalert@163.com";
        String to = "15295778261@163.com";
        String subject = "xxx";
        String content = "xxx";
        String username = "ismalert@163.com";
        String password = "1q2w3e4r";
        Mail.send(smtp, from, to, subject, content, username, password);
    }
}