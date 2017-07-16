package com.yf.zx.core.bussiness.sys.user.dao;

import com.yf.zx.core.bussiness.sys.user.entity.User;

public interface UserDao {
	public User getUser(User user);

	public void addUser(User user);

	public void updateUser(User user);

	public void deleteUser(int UserId);
}