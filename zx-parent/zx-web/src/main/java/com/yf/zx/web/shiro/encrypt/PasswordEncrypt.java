package com.yf.zx.web.shiro.encrypt;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.yf.zx.biz.sys.user.entity.User;

/**
 * 
 * PasswordEncrypt 
 * 用户密码加密 
 * @author zhang.yifeng
 * @CreateDate 2017年10月27日
 * @version 1.0.0
 * @since  1.0.0 
 * @see com.yf.zx.web.shiro.encrypt 
 *
 */
public class PasswordEncrypt {

    //随机数生成器
    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    /** 默认散列算法 md5 */
    private String DEFAULT_ALGORITHM = "MD5";

    /** 默认散列迭代次数 */
    private int DEFAULT_HASH_ITERATIONS = 2;
    
    //指定散列算法为md5
    private String algorithmName = DEFAULT_ALGORITHM;
    //散列迭代次数
    private int hashIterations = DEFAULT_HASH_ITERATIONS;
    
	/**
     * 生成随机盐值对密码进行加密
     *  
     * @author zhang.yifeng 
     * @param username[用户名]
     * @param password[用户 明文密码]
     * @return
     */
    public User encrypt(String username, String passwordPlain) {
    	User user = new User();
    	user.setUsername(username);
    	user.setPassword(passwordPlain);
    	user.setSalt(randomNumberGenerator.nextBytes().toHex());
        String newPassword =  new SimpleHash(algorithmName, user.getPassword(),
        ByteSource.Util.bytes(user.getUsername() + user.getSalt()), hashIterations).toHex();
        user.setPassword(newPassword);
        return user;
    }
    
    public static void main(String[] args) {
    	User user = new PasswordEncrypt().encrypt("admin", "qazxsw123.");
		System.out.println(user);
	}
    
    
    public String getAlgorithmName() {
		return algorithmName;
	}

	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}

	public int getHashIterations() {
		return hashIterations;
	}

	public void setHashIterations(int hashIterations) {
		this.hashIterations = hashIterations;
	}
}
