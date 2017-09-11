package com.yf.zx.core.util.ftp;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.Vector;
 
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
 
public class Demo003 {
 
    /**
     * @param args
     */
    public static void main(String[] args) throws Exception{
//        JSch jsch = new JSch();
//        
//        Session session = jsch.getSession("ismmysftp", "42.123.92.9");
//        session.setPort(12321);
//        session.setPassword("HoZf0uiWZ8rP4t159HPVBd1z0CxPWzyf");
//        Properties config = new Properties();
//        config.put("StrictHostKeyChecking", "no");
//        session.setConfig(config);
//        session.connect();
//        
//        ChannelSftp channelSftp = (ChannelSftp)session.openChannel("sftp");
//        channelSftp.connect();
////        channelSftp.setFilenameEncoding("gbk");
//        
//        Vector vector  = channelSftp.ls("/");
//        try{
//            for(Object obj :vector){
//                if(obj instanceof com.jcraft.jsch.ChannelSftp.LsEntry){
//                    String fileName = ((com.jcraft.jsch.ChannelSftp.LsEntry)obj).getFilename();
//                    System.out.println(fileName);
//                }
//            }
//        }
//        finally{
//            channelSftp.quit();
//            session.disconnect();
//        }
    	test();
    }
 
    
    public static void test() throws JSchException, UnsupportedEncodingException, SftpException {
    	  //1、创建JSch类，好比是FlashFXP工具
    	   JSch jsch = new JSch();
    	   //2、创建本次的文件传输会话对象，并连接到SFTP服务器。它好比是通过FlashFXP工具连接到SFTP服务器
           Session session = jsch.getSession("ismmysftp", "42.123.92.9", 12321);
    	   session.setPassword("HoZf0uiWZ8rP4t159HPVBd1z0CxPWzyf");
    	   Properties config = new Properties();
    	   config.put("StrictHostKeyChecking", "no");
    	   session.setConfig(config);
    	   session.connect();
    	   ChannelSftp channelSftp;
    	   try{
    	   //3、在该session会话中开启一个SFTP通道，之后就可以在该通道中进行文件传输了
    		   channelSftp = (ChannelSftp)session.openChannel("sftp");
    	       channelSftp.connect();
    	   }catch(Exception e){
    	       e.printStackTrace();
    	       session.disconnect();
    	       throw e;
    	   }
    	        
    	   InputStream is = new ByteArrayInputStream("2222".getBytes("UTF-8"));
    	   //4、进行文件传输操作：put()、get()....
    	   channelSftp.put(is, "/upload/cdn_home/1/a.txt", ChannelSftp.OVERWRITE);
    	        
    	   //5、操作完毕后，关闭通道并退出本次会话
    	   if(channelSftp!=null && channelSftp.isConnected()){
    	        channelSftp.disconnect();
    	   }
    	        
    	   if(session!=null && session.isConnected()){
    	        session.disconnect();
    	   }
    }
}  