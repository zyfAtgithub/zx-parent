package com.yf.zx.core.util.common;
import java.security.SecureRandom;
import java.util.Arrays;
 
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
 
 
public class HmacMD5Util {
 
    /*
     * 注：
     * 1、mac（Message Authentication Code，消息认证码算法）是含有密钥散列函数算法，兼容了MD和SHA算法的特性，并在此基础上加上了密钥。
     * 因此MAC算法也经常被称作HMAC算法。
     * 2、经过mac算法得到的摘要值的长度与实现算法的摘要值长度相同
     * 3、MAC系列算法支持表
     * 算法   摘要长度    备注
     * HmacMD5  128 JAVA6实现
     * HmacSHA1 160 JAVA6实现
     * HmacSHA256   256 JAVA6实现
     * HmacSHA384   384 JAVA6实现
     * HmacSHA512   512 JAVA6实现
     * HmacMD2  128 BouncyCastle实现
     * HmacMD4  128 BouncyCastle实现
     * HmacSHA224   224 BouncyCastle实现
     */
 
    public static final String ALGORITHM = "HmacMD5";
     
    public static byte[] generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey.getEncoded();
    }
     
    public static byte[] generateKey(byte[] seed) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(new SecureRandom(seed));
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey.getEncoded();
    }
     
    public static byte[] encode(byte[] content, byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        return mac.doFinal(content);
    }
     
    public static void main(String[] args) throws Exception {
         
        byte[] key = generateKey();
        System.out.println(Arrays.toString(key));
        System.out.println(key.length);
        System.out.println(Arrays.toString(encode("兼容了MD和SHA算法的特性，并在此基础上加上了密钥。".getBytes(), key)));
         
        /*
         * 控制台输出：
         *
         * [-83, 61, 100, -52, -103, 38, 68, 18, 8, -49, 80, 52, 12, 63, -15, 75, 109, -27, 122, -83, 18, -101, 118, -83, 29, -80, -90, -23, 19, -25, 40, 1, 58, -33, -5, 9, 71, 114, 105, 29, -88, -128, -115, -29, 6, 107, 53, -47, -34, 31, 116, 55, -112, -64, 120, 27, -74, 106, -60, -7, 86, -35, 88, 103]
         * 64
         * [109, -70, 24, 124, -72, -71, 34, -43, -111, 7, 57, 90, 59, -77, 85, 57]
         */
    }
 
}