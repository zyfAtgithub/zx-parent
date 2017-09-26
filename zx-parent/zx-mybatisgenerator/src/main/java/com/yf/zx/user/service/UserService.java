package com.yf.zx.user.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yf.zx.user.dao.UserMapper;
import com.yf.zx.user.model.User;
import com.yf.zx.user.model.UserExample;

@Service("userService")
public class UserService {

	Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	UserMapper userDao;
	public boolean existUser(String name) {
        UserExample userE = new UserExample();
        userE.createCriteria().andNameEqualTo(name);
        userE.setDistinct(true);
        List<User> user = userDao.selectByExample(userE);
        logger.info(user.toString());
        if (user.size() > 0)
            return true;
        else
            return false;
    }
}
