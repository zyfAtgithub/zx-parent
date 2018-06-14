package com.jshx.core.base.entity;

import java.io.Serializable;
import java.util.Date;


public class BaseModel implements Serializable {
    private static final long serialVersionUID = -6263479185850015392L;
    protected String id;
    protected Date createTime;
    protected Date updateTime;
    protected String createUserID;
    protected String updateUserID;

    public BaseModel() {
    }


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    public String getCreateUserID() {
        return this.createUserID;
    }

    public void setCreateUserID(String createUserID) {
        this.createUserID = createUserID;
    }


    public String getUpdateUserID() {
        return this.updateUserID;
    }

    public void setUpdateUserID(String updateUserID) {
        this.updateUserID = updateUserID;
    }
}
