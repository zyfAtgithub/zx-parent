package com.yf.zx.biz.log.syslog.entity;

import java.util.Date;

public class SysLogKey {
    private Long id;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}