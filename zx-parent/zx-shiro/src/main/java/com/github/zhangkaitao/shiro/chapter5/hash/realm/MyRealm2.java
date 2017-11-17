package com.github.zhangkaitao.shiro.chapter5.hash.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-27
 * <p>Version: 1.0
 */
public class MyRealm2 extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null; 
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = "zyf"; //用户名及salt1
        String password = "eebbf2ce4157afa7587b334e57b26a46"; //加密后的密码
        String salt2 = "24a1dc0b9d96552d5ea76b6d868d3971";
        SimpleAuthenticationInfo ai = new SimpleAuthenticationInfo(username, password, ByteSource.Util.bytes(username+salt2), getName());
//        ai.setCredentialsSalt(ByteSource.Util.bytes(username+salt2)); //盐是用户名+随机数
        System.out.println(ByteSource.Util.bytes(username+salt2));
        System.out.println("用户名：" + ai.getPrincipals());
        System.out.println("密码：" + ai.getCredentials());
        System.out.println("盐：" + ai.getCredentialsSalt());
        return ai;
    }
}
