package com.yf.zx.biz.sys.user.dao;

import com.yf.zx.biz.sys.user.entity.User;

public interface UserDao {
	public User getUser(User user);

	public void addUser(User user);

	public void updateUser(User user);

	public void deleteUser(int UserId);
}