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

	/**
	 * 根据用户名查询用户[多个时返回第一个]
	 *  
	 * @author zhang.yifeng 
	 * @param userName
	 * @return
	 */
	public User getUserByName(String userName) {
		List<User> userList = userDao.selectByName(userName);
		return userList.isEmpty()?null:userList.get(0);
	}

	
	public void addUser(User user) {
		userDao.insertAutoId(user);
	}
}
