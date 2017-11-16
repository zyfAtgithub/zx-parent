package com.yf.zx.biz.sys.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yf.zx.biz.sys.user.dao.UserMapper;
import com.yf.zx.biz.sys.user.entity.User;
import com.yf.zx.biz.sys.user.entity.UserVo;
import com.yf.zx.core.util.common.StringUtils;

@Service("userService")
public class UserService {

	@Autowired
	UserMapper userDao;
	
	public PageInfo<User> findByPage(UserVo userVo) {
		PageHelper.startPage(userVo.getPageNumber(), userVo.getPageSize());
		
		//排序
		if (StringUtils.isNotNull(userVo.getOrderBy())) {
			PageHelper.orderBy(userVo.getOrderBy());
		}
		List<User> userList = userDao.selectUser(userVo);
		PageInfo<User> page = new PageInfo<User>(userList);
		return page;  
	}
	
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
