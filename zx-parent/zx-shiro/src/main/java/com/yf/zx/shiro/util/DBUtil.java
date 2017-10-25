package com.yf.zx.shiro.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	private static String url = "jdbc:mysql://localhost/shiro?useUnicode=true&amp;characterEncoding=UTF-8";
	private static String username = "root";
	private static String password = "123456";
	private static String dirverClassName = "com.mysql.jdbc.Driver";
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(dirverClassName);
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("获取Connection成功！");
		} catch (ClassNotFoundException e) {
			System.err.println("获取Connection异常！[" + e.getMessage() + "]");
		} catch (SQLException e) {
			System.err.println("获取Connection异常！[" + e.getMessage() + "]");
		}
		return conn;
	}
	
	public static void closeConnection(Connection conn) {
		closeConnection(conn, null);
	}

	public static void closeConnection(Connection conn, Statement stmt) {
		closeConnection(conn, stmt, null);
	}

	public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) {
		if (conn != null) {
			try {
				if (!conn.isClosed()) {
					conn.close();
				}
				System.out.println("close Connection成功！");
			} catch (SQLException e) {
				System.err.println("close Connection异常！[" + e.getMessage() + "]");
			}
		}
		
		if (stmt != null) {
			try {
				if (!stmt.isClosed()) {
					stmt.close();
				}
				System.out.println("close Statement成功！");
			} catch (SQLException e) {
				System.err.println("close Statement异常！[" + e.getMessage() + "]");
			}
		}

		if (rs != null) {
			try {
				if (!rs.isClosed()) {
					rs.close();
				}
				System.out.println("close ResultSet成功！");
			} catch (SQLException e) {
				System.err.println("close ResultSet异常！[" + e.getMessage() + "]");
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		Connection conn = getConnection();
		closeConnection(conn, null);
	}
}
