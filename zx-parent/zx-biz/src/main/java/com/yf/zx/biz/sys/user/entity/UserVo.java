package com.yf.zx.biz.sys.user.entity;

import com.yf.zx.core.base.vo.BaseVo;

public class UserVo extends BaseVo {

	private Long id;
	
	private String loginname;

	private String username;
	
	private String phone;
	
	private String email;
	
	private Boolean locked;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getLocked() {
		return locked;
	}

	public void setLock(Boolean locked) {
		this.locked = locked;
	}

	@Override
	public String toString() {
		return "UserVo [id=" + id + ", loginname=" + loginname + ", username=" + username + ", phone=" + phone
				+ ", email=" + email + ", locked=" + locked + ", toString()=" + super.toString() + "]";
	}
	
}
