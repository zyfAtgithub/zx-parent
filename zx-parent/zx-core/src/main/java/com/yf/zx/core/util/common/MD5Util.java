package com.yf.zx.core.util.common;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
 
 
public class MD5Util {
 
    /*
     * 注：
     * 1、 Message Digest Algorithm MD5将任意长度的“字节串”映射为一个128bit的大整数，并且是通过该128bit反推原始字符串是困难的
     * 2、2004年8月17日的美国加州圣巴巴拉的国际密码学会议（Crypto’2004）上，来自中国山东大学的王小云教授做了破译MD5、HAVAL-128、 
     * MD4和RIPEMD算法的报告，公布了MD系列算法的破解结果。宣告了固若金汤的世界通行密码标准MD5的堡垒轰然倒塌，
     * 引发了密码学界的轩然大波。(注意:并非是真正的破解，只是加速了杂凑冲撞）
     */
     
    public static final String ALGORITHM = "MD5";
     
    public static byte[] encode(byte[] content){
        try{
            MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
            digest.update(content);
            return digest.digest();
        }catch(NoSuchAlgorithmException e){
             
        }
        return null;
    }
     
    public static void main(String[] args) {
 
        System.out.println(Arrays.toString(encode("Message Digest Algorithm MD5（中文名为消息摘要算法第五版）为计算机安全领域广泛使用的一种散列函数".getBytes())));
        System.out.println(HexUtil.encode(encode("Message Digest Algorithm MD5（中文名为消息摘要算法第五版）为计算机安全领域广泛使用的一种散列函数".getBytes())));
         
        /*
         * 控制台输出：
         * 
         * [95, 17, 75, 31, 32, -4, -66, 12, -49, 38, -59, -64, 17, 54, -97, -115]
         * 5F114B1F20FCBE0CCF26C5C011369F8D
         */
    }
 
}