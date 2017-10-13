package com.yf.zx.biz.user.model;

import java.util.Date;

public class User {
    private Integer id;

    private String name;

    private String password;

    private Date lastlogintime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(Date lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", lastlogintime=" + lastlogintime
				+ "]";
	}
}