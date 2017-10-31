package com.yf.zx.biz.sys.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yf.zx.biz.sys.user.dao.UserMapper;
import com.yf.zx.biz.sys.user.entity.User;

@Service("userService")
public class UserService {

	@Autowired
	UserMapper userDao;
	
	public boolean existUser(String userName) {
		List<User> userList = userDao.selectByName(userName);
		return !userList.isEmpty();
	}
}
