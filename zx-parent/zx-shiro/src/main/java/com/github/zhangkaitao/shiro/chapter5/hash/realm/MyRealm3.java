package com.github.zhangkaitao.shiro.chapter5.hash.realm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.security.auth.login.AccountNotFoundException;

import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.yf.zx.shiro.util.DBUtil;

public class MyRealm3 extends AuthorizingRealm {

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rst = null;
		String sql = "select username, password, password_salt from users where username = ?";
		
		UsernamePasswordCredentials credentials = (UsernamePasswordCredentials) token.getCredentials();
		String username = credentials.getUserName();
		String password = credentials.getPassword();
		String pwd = "";
		String salt = "";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(0, credentials.getUserName());
			rst = pst.executeQuery();
			if (rst.next()) {
				pwd = rst.getString(1);
				salt = rst.getString(2);
				
			}
			else {
				throw new AuthenticationException();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
