package com.yf.zx.biz.log.login.entity;

import com.yf.zx.core.base.vo.BaseVo;

import java.util.Date;

public class LoginLogVo extends BaseVo {
    private Long id;

    private String logintimeBegin;

    private String logintimeEnd;

    private String logouttimeBegin;

    private String logouttimeEnd;

    private String ip;

    private String loginuser;

    private String logindevice;

    /** 登录结果 */
    private String loginResult;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogintimeBegin() {
        return logintimeBegin;
    }

    public void setLogintimeBegin(String logintimeBegin) {
        this.logintimeBegin = logintimeBegin;
    }

    public String getLogintimeEnd() {
        return logintimeEnd;
    }

    public void setLogintimeEnd(String logintimeEnd) {
        this.logintimeEnd = logintimeEnd;
    }

    public String getLogouttimeBegin() {
        return logouttimeBegin;
    }

    public void setLogouttimeBegin(String logouttimeBegin) {
        this.logouttimeBegin = logouttimeBegin;
    }

    public String getLogouttimeEnd() {
        return logouttimeEnd;
    }

    public void setLogouttimeEnd(String logouttimeEnd) {
        this.logouttimeEnd = logouttimeEnd;
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
