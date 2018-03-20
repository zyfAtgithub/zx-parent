package com.yf.zx.biz.log.login.dao;

import com.yf.zx.biz.log.login.entity.LoginLog;
import com.yf.zx.biz.log.login.entity.LoginLogVo;

import java.util.List;

public interface LoginLogMapper {

    int deleteByPrimaryKey(Long id);

    int insert(LoginLog record);

    int insertSelective(LoginLog record);

    LoginLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LoginLog record);

    int updateByPrimaryKey(LoginLog record);

    List<LoginLog> selectLoginLog(LoginLogVo loginLogVo);

    int delByIds(String[] idArr);
}