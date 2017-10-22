package com.yf.zx.biz.user.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yf.zx.biz.user.dao.UserMapper;
import com.yf.zx.biz.user.entity.User;

/**
 * UserService 用户信息服务类
 *  
 * @author zhang.yifeng
 * @CreateDate 2017年10月13日
 * @version 1.0.0
 * @since  1.0.0 
 * @see com.yf.zx.biz.user.service 
 *
 */
@Service("userService")
public class UserService {
	
	Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	UserMapper userDao;
	
	/**
	 * 根据用户名判断用户是否存在
	 *  
	 * @author zhang.yifeng 
	 * @param name 用户名
	 * @return boolean
	 */
	public boolean existUser(String name) {
        List<User> userList = userDao.selectUserByName(name);
        logger.info(userList.toString());
        if (userList.size() > 0)
            return true;
        else
            return false;
    }
	
	/**
	 * 根据用户名查询用户信息[返回第一个]
	 *  
	 * @author zhang.yifeng 
	 * @param name 用户名
	 * @return user
	 */
	public User getUserByName(String name) {
		List<User> userList = userDao.selectUserByName(name);
		if (!userList.isEmpty()) {
			return userList.get(0);
		}
		return null;
	}
}
