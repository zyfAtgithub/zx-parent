package com.yf.zx.biz.sys.role.entity;

/**
 * RolePermission 
 *  角色权限
 * @author zhang.yifeng
 * @CreateDate 2017年12月12日
 * @version 1.0.0
 * @since  1.0.0 
 * @see com.yf.zx.biz.sys.role.entity 
 *
 */
public class RolePermission {

	private Long roleId;
	
	private Long permId;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getPermId() {
		return permId;
	}

	public void setPermId(Long permId) {
		this.permId = permId;
	}
	
}
