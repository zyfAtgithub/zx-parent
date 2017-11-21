package com.yf.zx.biz.sys.user.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yf.zx.biz.sys.user.dao.UserMapper;
import com.yf.zx.biz.sys.user.entity.User;
import com.yf.zx.biz.sys.user.entity.UserVo;
import com.yf.zx.core.base.web.PageReturn;
import com.yf.zx.core.base.web.ResultReturn;
import com.yf.zx.core.util.common.StringUtils;

@Service("userService")
public class UserService {

	Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	UserMapper userDao;
	
	public PageReturn<User> findByPage(UserVo userVo) {
		PageHelper.startPage(userVo.getPage(), userVo.getRows());
		
		//排序
		if (StringUtils.isNotNull(userVo.getOrderBy())) {
			PageHelper.orderBy(userVo.getOrderBy());
		}
		List<User> userList = userDao.selectUser(userVo);
		PageReturn<User> page = new PageReturn<User>(new PageInfo<User>(userList));
		return page;  
	}
	
	public boolean existUser(String userName) {
		List<User> userList = userDao.selectByLoginName(userName);
		return !userList.isEmpty();
	}

	/**
	 * 根据登录名查询用户[多个时返回第一个]
	 *  
	 * @author zhang.yifeng 
	 * @param userName
	 * @return
	 */
	public User getUserByName(String loginName) {
		List<User> userList = userDao.selectByLoginName(loginName);
		return userList.isEmpty()?null:userList.get(0);
	}

	/**
	 * 新增用户[需对用户登录名做唯一性校验]
	 *  
	 * @author zhang.yifeng 
	 * @param user
	 * @return
	 */
	public ResultReturn addUser(User user) {
		ResultReturn ret = new ResultReturn();
		if (user == null || StringUtils.isNullOrEmpty(user.getLoginname())) {
			logger.error("用户信息不能为空！");
			ret.setResultCode("0");
			ret.setResultMsg("用户信息不能为空！");
			return ret;
		}
		
		try {
			if (existUser(user.getLoginname())) {
				logger.error("用户名重复！");
				ret.setResultCode("0");
				ret.setResultMsg("用户名重复！");
				return ret;
			}
			userDao.insertAutoId(user);
			ret.setResultCode("200");
			ret.setResultMsg("新增成功！");
		} catch (Exception e) {
			logger.error("新增用户失败！", e);
			ret.setResultCode("0");
			ret.setResultMsg("新增用户失败！");
		}
		return ret;
	}
	
	/**
	 * 删除用户
	 *  
	 * @author zhang.yifeng 
	 * @param ids [多个以 "|"分割]
	 * @return
	 */
	public ResultReturn deleteUserByIds(String ids) {
		ResultReturn ret = new ResultReturn();
		if (StringUtils.isNullOrEmpty(ids)) {
			ret.setResultCode("0");
			ret.setResultMsg("id列表不能为空！");
			return ret;
		}
		
		try {
			StringBuffer strBuf = new StringBuffer("");
			String[] idArr = ids.split("\\|");
			for (String id : idArr) {
				strBuf.append(id).append(",");
			}
			strBuf.deleteCharAt(strBuf.length() - 1);
			userDao.delByIds(strBuf.toString());
			ret.setResultCode("200");
			ret.setResultMsg("删除用户成功！");
		} catch (Exception e) {
			logger.error("删除用户失败！", e);
			ret.setResultCode("0");
			ret.setResultMsg("删除用户失败！");
		}
		return ret;
	}
}
