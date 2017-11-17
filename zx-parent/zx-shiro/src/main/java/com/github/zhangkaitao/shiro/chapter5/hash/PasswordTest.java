package com.github.zhangkaitao.shiro.chapter5.hash;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.converters.AbstractConverter;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.junit.Test;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-27
 * <p>Version: 1.0
 */
public class PasswordTest extends BaseTest {

    @Test
    public void testPasswordServiceWithMyRealm() {
        login("classpath:chapt5/shiro-passwordservice.ini", "wu", "123");
    }

    @Test
    public void testPasswordServiceWithJdbcRealm() {
        login("classpath:chapt5/shiro-jdbc-passwordservice.ini", "wu", "123");
    }

    @Test
    public void testGeneratePassword() {
        String algorithmName = "md5";
        String username = "zyf";
        String password = "qazxsw123.";
        String salt1 = username;
        String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
        int hashIterations = 2;

        SimpleHash hash = new SimpleHash(algorithmName, password, salt1 + salt2, hashIterations);
        String encodedPassword = hash.toHex();
        System.out.println("私盐：" + salt2);
        System.out.println("加密密码：" + encodedPassword);
    }

    @Test
    public void testHashedCredentialsMatcherWithMyRealm2() {
        //使用testGeneratePassword生成的散列密码
        login("classpath:chapt5/shiro-hashedCredentialsMatcher.ini", "liu", "123");
    }

    @Test
    public void testHashedCredentialsMatcherWithJdbcRealm() {

        BeanUtilsBean.getInstance().getConvertUtils().register(new EnumConverter(), JdbcRealm.SaltStyle.class);

        //使用testGeneratePassword生成的散列密码
        login("classpath:chapt5/shiro-jdbc-hashedCredentialsMatcher.ini", "liu", "123");
    }


    private class EnumConverter extends AbstractConverter {
    	@SuppressWarnings("rawtypes")
        @Override
        protected String convertToString(final Object value) throws Throwable {
            return ((Enum) value).name();
        }
        
        @SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
        protected Object convertToType(final Class type, final Object value) throws Throwable {
            return Enum.valueOf(type, value.toString());
        }

        @SuppressWarnings("rawtypes")
		@Override
        protected Class getDefaultType() {
            return null;
        }

    }

    @Test//(expected = ExcessiveAttemptsException.class)
    public void testRetryLimitHashedCredentialsMatcherWithMyRealm() {
//        for(int i = 1; i <= 6; i++) {
//            try {
//                login("classpath:chapt5/shiro-retryLimitHashedCredentialsMatcher.ini", "liu", "123");
//            } catch (Exception e) {
//            	System.out.println("第" + i + "登录失败！");
//            }
//        }
        login("classpath:chapt5/shiro-retryLimitHashedCredentialsMatcher.ini", "zyf", "qazxsw123.");
    }
}
