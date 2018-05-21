package com.yf.zx.biz.log.syslog.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yf.zx.biz.log.syslog.dao.SysLogMapper;
import com.yf.zx.biz.log.syslog.entity.SysLog;
import com.yf.zx.biz.log.syslog.entity.SysLogVo;
import com.yf.zx.biz.sys.user.entity.UserVo;
import com.yf.zx.core.base.web.PageReturn;
import com.yf.zx.core.util.common.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sysLogService")
public class SysLogService {

    Logger logger = LoggerFactory.getLogger(SysLogService.class);

    @Autowired
    SysLogMapper sysLogDao;

    public PageReturn<SysLog> findByPage(SysLogVo sysLogVo) {
        PageHelper.startPage(sysLogVo.getPage(), sysLogVo.getRows());

        //排序
        if (StringUtils.isNotNull(sysLogVo.getOrderBy())) {
            PageHelper.orderBy(sysLogVo.getOrderBy());
        }
        List<SysLog> sysLogList = sysLogDao.selectSysLog(sysLogVo);
        PageReturn<SysLog> page = new PageReturn<SysLog>(new PageInfo<SysLog>(sysLogList));
        return page;
    }

}
