package com.yf.zx.biz.log.syslog.entity;

import com.yf.zx.core.base.vo.BaseVo;

import java.util.Date;

public class SysLogVo extends BaseVo {

    private Long id;

    private String createTimeBegin;

    private String createTimeEnd;

    private String ipAddress;

    private String generateTimeBegin;

    private String generateTimeEnd;

    private String logLevel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateTimeBegin() {
        return createTimeBegin;
    }

    public void setCreateTimeBegin(String createTimeBegin) {
        this.createTimeBegin = createTimeBegin;
    }

    public String getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(String createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getGenerateTimeBegin() {
        return generateTimeBegin;
    }

    public void setGenerateTimeBegin(String generateTimeBegin) {
        this.generateTimeBegin = generateTimeBegin;
    }

    public String getGenerateTimeEnd() {
        return generateTimeEnd;
    }

    public void setGenerateTimeEnd(String generateTimeEnd) {
        this.generateTimeEnd = generateTimeEnd;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }
}
