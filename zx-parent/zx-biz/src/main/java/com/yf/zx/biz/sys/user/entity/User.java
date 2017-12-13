package com.yf.zx.biz.sys.user.entity;

import java.util.Date;

public class User {
    private Long id;

    private String loginname;

    private String username;

    private String password;

    private String salt;

    private String phone;

    private String email;

    private Boolean locked;

    private Date lastloginTime;

    private String roleids;

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
        this.loginname = loginname == null ? null : loginname.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Date getLastloginTime() {
        return lastloginTime;
    }

    public void setLastloginTime(Date lastloginTime) {
        this.lastloginTime = lastloginTime;
    }

	public String getRoleids() {
		return roleids;
	}

	public void setRoleids(String roleids) {
		this.roleids = roleids;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", loginname=" + loginname + ", username=" + username + ", password=" + password
				+ ", salt=" + salt + ", phone=" + phone + ", email=" + email + ", locked=" + locked + ", lastloginTime="
				+ lastloginTime + ", roleids=" + roleids + "]";
	}

}