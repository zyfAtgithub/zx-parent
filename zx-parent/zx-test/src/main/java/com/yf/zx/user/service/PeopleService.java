package com.yf.zx.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yf.zx.user.model.User;

@Service("peopleService")
public class PeopleService {

	@Autowired
	UserService userService;
	
	@Transactional
	public void addUser(User user) {
		try {
			userService.addUser(user);
//			throw new RuntimeException("peopleService 抛出的异常");
		}
		catch (Exception e) {
			throw e;
		}
	}
}
