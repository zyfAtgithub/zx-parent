package com.yf.zx.biz.log.syslog.dao;

import com.yf.zx.biz.log.syslog.entity.SysLog;
import com.yf.zx.biz.log.syslog.entity.SysLogKey;
import com.yf.zx.biz.log.syslog.entity.SysLogVo;

import java.util.List;

public interface SysLogMapper {
    int deleteByPrimaryKey(SysLogKey key);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    SysLog selectByPrimaryKey(SysLogKey key);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKeyWithBLOBs(SysLog record);

    int updateByPrimaryKey(SysLog record);


    List<SysLog> selectSysLog(SysLogVo sysLogVo);

}