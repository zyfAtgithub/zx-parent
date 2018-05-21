package com.yf.zx.web.shiro.encrypt;

import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.yf.zx.biz.sys.user.entity.User;
import com.yf.zx.core.util.common.StringUtils;
import com.yf.zx.core.util.conf.SysPropertyConf;
import com.yf.zx.core.util.spring.ApplicationContextUtil;

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
    private static final String DEFAULT_ALGORITHM = "MD5";

    /** 默认散列迭代次数 */
    private static final int DEFAULT_HASH_ITERATIONS = 2;
    
    //指定散列算法为md5
    private static String algorithmName = DEFAULT_ALGORITHM;
    //散列迭代次数
    private static int hashIterations = DEFAULT_HASH_ITERATIONS;

    /** 系统初始化密码密码 */
    private static String defaultPassword = "12345678";
    
    static {
    	SysPropertyConf conf = ApplicationContextUtil.getBean("sysPropertyConf");
    	algorithmName = conf.getProperty("credentialsMatcher.hashAlgorithmName");
    	hashIterations = conf.getIntValue("credentialsMatcher.hashIterations");
    	defaultPassword = conf.getProperty("defaultPassword");
    }
    
    /**
     * 用户密码初始化
     *  
     * @author zhang.yifeng 
     * @param user
     */
	public static void initPassword(User user) {
		if (user == null || StringUtils.isNullOrEmpty(user.getLoginname())) {
    		return;
    	}
    	user.setSalt(randomNumberGenerator.nextBytes().toHex());
        String newPassword =  new SimpleHash(algorithmName, defaultPassword,
        ByteSource.Util.bytes(user.getLoginname() + user.getSalt()), hashIterations).toHex();
        user.setPassword(newPassword);
	}
    
    /**
     * 生成随机盐值对密码进行加密
     *  
     * @author zhang.yifeng 
     * @param user [用户名 用户 明文密码]
     */
    public static void encrypt(User user) {
    	if (user == null || StringUtils.isNullOrEmpty(user.getLoginname()) 
    			|| StringUtils.isNullOrEmpty(user.getPassword())) {
    		return;
    	}
    	user.setSalt(randomNumberGenerator.nextBytes().toHex());
        String newPassword =  new SimpleHash(algorithmName, user.getPassword(),
        ByteSource.Util.bytes(user.getLoginname() + user.getSalt()), hashIterations).toHex();
        user.setPassword(newPassword);
    }
    
    /**
     * 原始密码正确性校验
     * token-输入
     * info-实际
     *  
     * @author zhang.yifeng 
     * @return
     */
    public static boolean checkPassword(User user, String pwdCheck) {
    	if (user == null || StringUtils.isNullOrEmpty(user.getLoginname()) 
    			|| StringUtils.isNullOrEmpty(user.getPassword())
    			|| StringUtils.isNullOrEmpty(pwdCheck)) {
    		return false;
    	}
        
        UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginname(), pwdCheck);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                user.getLoginname(), //用户名
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getLoginname() + user.getSalt()),//salt=username+salt
                "checkpwd"
        );
        
        //密码认证器，同登录认证
        HashedCredentialsMatcher macher = new HashedCredentialsMatcher(algorithmName);
        macher.setHashIterations(hashIterations);
        return macher.doCredentialsMatch(token, info);
    }
    
    public static void main(String[] args) {
    	User user = new User();
    	user.setLoginname("zyf");
    	user.setPassword("qazxsw123.");
    	encrypt(user);
		System.out.println(user);
		boolean isCorrect = checkPassword(user, "qazxsw123.");
		System.out.println("密码正确否？" + isCorrect);
    }
    
    
    public String getAlgorithmName() {
		return algorithmName;
	}

	public int getHashIterations() {
		return hashIterations;
	}
}
