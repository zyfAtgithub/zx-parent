package com.yf.zx.biz.sys.user.entity;

/**
 * UserRole [用户角色]
 *  
 * @author zhang.yifeng
 * @CreateDate 2017年12月13日
 * @version 1.0.0
 * @since  1.0.0 
 * @see com.yf.zx.biz.sys.user.entity 
 *
 */
public class UserRole {

	private Long userId;
	
	private Long roleId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "UserRole [userId=" + userId + ", roleId=" + roleId + "]";
	}
}
