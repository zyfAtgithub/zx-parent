package com.yf.zx.biz.sys.role.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yf.zx.biz.sys.role.dao.RoleMapper;
import com.yf.zx.biz.sys.role.entity.Role;
import com.yf.zx.biz.sys.role.entity.RolePermission;
import com.yf.zx.biz.sys.role.entity.RoleVo;
import com.yf.zx.core.base.web.PageReturn;
import com.yf.zx.core.base.web.ResultReturn;
import com.yf.zx.core.util.common.StringUtils;

@Service("roleService")
public class RoleService {

	Logger logger = LoggerFactory.getLogger(RoleService.class);

	@Autowired
	RoleMapper roleDao;
	
	public PageReturn<Role> findByPage(RoleVo roleVo) {
		//设置分页参数
		PageHelper.startPage(roleVo.getPage(), roleVo.getRows());
		if (StringUtils.isNotNullAndEmpty(roleVo.getOrderBy())) {
			//排序
			PageHelper.orderBy(roleVo.getOrderBy());
		}
		List<Role> roleList = roleDao.selectRole(roleVo);
		PageReturn<Role> page = new PageReturn<Role>(new PageInfo<Role>(roleList));
		return page;
	}
	
	
	/**
	 * 新增角色[需对角色名做唯一性校验]
	 *  
	 * @author zhang.yifeng 
	 * @param role
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public ResultReturn addRole(Role role) {
		ResultReturn ret = new ResultReturn();
		if (role == null || StringUtils.isNullOrEmpty(role.getRole())) {
			logger.error("角色信息不能为空！");
			ret.setResultCode("0");
			ret.setResultMsg("角色不能为空！");
			return ret;
		}
		
		if (existRole(role)) {
			logger.error("角色名重复！");
			ret.setResultCode("0");
			ret.setResultMsg("角色名重复！");
			return ret;
		}
		
		//以下几个操作由事务控制
		roleDao.insertAutoId(role);
		roleDao.delRolePermByRoleId(role.getId());
		if (role.getPermids() != null && role.getPermids().length > 0) {
			List<RolePermission> roleperms = new ArrayList<RolePermission>();
			for (Long permId : role.getPermids()) {
				RolePermission roleperm = new RolePermission();
				roleperm.setRoleId(role.getId());
				roleperm.setPermId(permId);
				roleperms.add(roleperm);
			}
			roleDao.batchInserRolePerm(roleperms);
		}
		
		ret.setResultCode("200");
		ret.setResultMsg("新增角色成功！");
			
			
		return ret;
	}
	
	public boolean existRole(Role role) {
		if (role == null || StringUtils.isNullOrEmpty(role.getRole())) {
			return false;
		}
		List<Role> roleList = roleDao.selectByRoleName(role);
		return !roleList.isEmpty();
	}
	
	@Transactional(rollbackFor=Exception.class)
	public ResultReturn updateRoleById(Role role) {
		ResultReturn ret = new ResultReturn();
		if (role == null || role.getId() == null 
				|| StringUtils.isNullOrEmpty(role.getRole())) {
			logger.error("角色信息不能为空！");
			ret.setResultCode("0");
			ret.setResultMsg("角色不能为空！");
			return ret;
		}
		
		if (existRole(role)) {
			logger.error("角色名重复！");
			ret.setResultCode("0");
			ret.setResultMsg("角色名重复！");
			return ret;
		}
		
		//以下几个操作由事务控制
		roleDao.updateById(role);
		roleDao.delRolePermByRoleId(role.getId());
		if (role.getPermids() != null && role.getPermids().length > 0) {
			List<RolePermission> roleperms = new ArrayList<RolePermission>();
			for (Long permId : role.getPermids()) {
				RolePermission roleperm = new RolePermission();
				roleperm.setRoleId(role.getId());
				roleperm.setPermId(permId);
				roleperms.add(roleperm);
			}
			roleDao.batchInserRolePerm(roleperms);
		}
		
		ret.setResultCode("200");
		ret.setResultMsg("修改角色成功！");
		
		return ret;
	}
	
	/**
	 * 根据角色id查询角色信息[带权限id列表]
	 *  
	 * @author zhang.yifeng 
	 * @param roleId
	 * @return
	 */
	public ResultReturn getRoleById(Long roleId) {
		ResultReturn ret = new ResultReturn();
		if (StringUtils.isNullOrEmpty(roleId)) {
			ret.setResultCode("0");
			ret.setResultMsg("roleId不能为空！");
			return ret;
		}
		try {
			Role role = roleDao.selectByPrimaryKey(roleId);
			List<Long> permIds = roleDao.selectPermidsByRoleId(roleId);
			if (permIds != null && !permIds.isEmpty()) {
				role.setPermids(permIds.toArray(new Long[permIds.size()]));
			}
			ret.setData(role);
			ret.setResultCode("200");
			ret.setResultMsg("查询成功！");
		} catch (Exception e) {
			logger.error("查询失败！", e);
			ret.setResultCode("0");
			ret.setResultMsg("查询失败！");
		}
		return ret;
	}
	
	/**
	 * 删除角色
	 *  
	 * @author zhang.yifeng 
	 * @param ids [多个以 "|"分割]
	 * @return
	 */
	public ResultReturn deleteRoleByIds(String ids) {
		ResultReturn ret = new ResultReturn();
		if (StringUtils.isNullOrEmpty(ids)) {
			ret.setResultCode("0");
			ret.setResultMsg("id列表不能为空！");
			return ret;
		}
		
		try {
			String[] idArr = ids.split("\\|");
			roleDao.delByIds(idArr);
			ret.setResultCode("200");
			ret.setResultMsg("删除角色成功！");
		} catch (Exception e) {
			logger.error("删除角色失败！", e);
			ret.setResultCode("0");
			ret.setResultMsg("删除角色失败！");
		}
		return ret;
	}
	
	/**
	 * 根据角色id查询权限
	 *  
	 * @author zhang.yifeng 
	 * @param roleId
	 * @return
	 */
	public ResultReturn getPermidsByRoleids(Long roleId) {
		ResultReturn ret = new ResultReturn();
		if (StringUtils.isNullOrEmpty(roleId)) {
			ret.setResultCode("0");
			ret.setResultMsg("roleId不能为空！");
			return ret;
		}
		try {
			List<Long> permIdList = roleDao.selectPermidsByRoleId(roleId);
			ret.setData(permIdList);
			ret.setResultCode("200");
			ret.setResultMsg("查询成功！");
		} catch (Exception e) {
			logger.error("查询失败！", e);
			ret.setResultCode("0");
			ret.setResultMsg("查询失败！");
		}
		return ret;
	}
}
