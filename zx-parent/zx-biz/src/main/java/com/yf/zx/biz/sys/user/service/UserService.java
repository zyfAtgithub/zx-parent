package com.yf.zx.biz.sys.user.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yf.zx.biz.sys.user.dao.UserMapper;
import com.yf.zx.biz.sys.user.entity.User;
import com.yf.zx.biz.sys.user.entity.UserRole;
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
	 * 根据Id查询
	 *  
	 * @author zhang.yifeng 
	 * @param id
	 * @return
	 */
	public User findById(Long id) {
		return userDao.selectById(id);
	}
	
	
	public ResultReturn editById(User user) {
		ResultReturn ret = new ResultReturn();
		if (user == null || user.getId() == null) {
			ret.setResultCode("0");
			ret.setResultMsg("用户id不能为空！");
			return ret;
		}
		
		try {
			userDao.updateById(user);
			ret.setResultCode("200");
			ret.setResultMsg("用户修改成功！");
		} catch (Exception e) {
			logger.error("用户修改失败！", e);
			ret.setResultCode("0");
			ret.setResultMsg("用户修改失败！");
		}
		
		return ret;
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
			String[] idArr = ids.split("\\|");
			userDao.delByIds(idArr);
			ret.setResultCode("200");
			ret.setResultMsg("删除用户成功！");
		} catch (Exception e) {
			logger.error("删除用户失败！", e);
			ret.setResultCode("0");
			ret.setResultMsg("删除用户失败！");
		}
		return ret;
	}
	
	/**
	 * 获取用户角色id
	 *  
	 * @author zhang.yifeng 
	 * @param userId
	 * @return
	 */
	public String getRoleidsByUserId(Long userId) {
		List<Long> roleids = userDao.selectRoleidsByUserId(userId);
		return StringUtils.join(roleids.toArray(), ",");
	}

	/**
	 * 获取用户权限
	 *  
	 * @author zhang.yifeng 
	 * @param userId
	 * @return
	 */
	public String getPermsByUserId(Long userId) {
		List<Long> roleids = userDao.selectRoleidsByUserId(userId);
		return StringUtils.join(roleids.toArray(), ",");
	}
	
	/**
	 * 授予角色[事务控制]
	 *  
	 * @author zhang.yifeng 
	 * @param user
	 */
	@Transactional(rollbackFor=Exception.class)
	public ResultReturn grantRole(User user) {
		ResultReturn ret = new ResultReturn();
		if (user == null || user.getId() == null) {
			ret.setResultCode("0");
			ret.setResultMsg("用户信息不能为空！");
			return ret;
		}
		
		//删除之前的用户角色关系
		userDao.delUserRoleByUserId(user.getId());
		//建立新的用户角色关系
		if (StringUtils.isNotNullAndEmpty(user.getRoleids())) {
			List<UserRole> userroles = new ArrayList<UserRole>();
			List<String> roleids = StringUtils.split2List(user.getRoleids(), ",");
			for (String roleId : roleids) {
				UserRole userRole = new UserRole();
				userRole.setUserId(user.getId());
				userRole.setRoleId(Long.valueOf(roleId));
				userroles.add(userRole);
			}
			userDao.batchInserUserRole(userroles);
		}
		ret.setResultCode("200");
		ret.setResultMsg("角色授予成功！");
		return ret;
	}
}
