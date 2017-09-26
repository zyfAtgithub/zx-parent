package com.yf.zx.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yf.zx.user.dao.UserMapper;
import com.yf.zx.user.model.User;
import com.yf.zx.user.model.UserExample;

@Service("userService")
public class UserService {

	@Autowired
	UserMapper userDao;
	public boolean existUser(String name) {
        UserExample userE = new UserExample();
        userE.createCriteria().andNameEqualTo(name);
        userE.setDistinct(true);
        List<User> user = userDao.selectByExample(userE);
        System.out.println(user);
        if (user.size() > 0)
            return true;
        else
            return false;
    }
}
