package com.yf.zx.biz.log.login.entity;

import java.util.Date;

public class LoginLog {
    private Long id;

    private Date logintime;

    private Date logouttime;

    private String ip;

    private String loginuser;

    private String logindevice;

    /**
     * 登录结果
     * 1-登录成功
     * 0-登录失败
     */
    private String loginResult;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }

    public Date getLogouttime() {
        return logouttime;
    }

    public void setLogouttime(Date logouttime) {
        this.logouttime = logouttime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getLoginuser() {
        return loginuser;
    }

    public void setLoginuser(String loginuser) {
        this.loginuser = loginuser == null ? null : loginuser.trim();
    }

    public String getLogindevice() {
        return logindevice;
    }

    public void setLogindevice(String logindevice) {
        this.logindevice = logindevice == null ? null : logindevice.trim();
    }

    public String getLoginResult() {
        return loginResult;
    }

    public void setLoginResult(String loginResult) {
        this.loginResult = loginResult;
    }
}