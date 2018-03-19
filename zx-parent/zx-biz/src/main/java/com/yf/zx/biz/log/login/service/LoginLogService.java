package com.yf.zx.biz.log.login.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yf.zx.biz.log.login.dao.LoginLogMapper;
import com.yf.zx.biz.log.login.entity.LoginLog;
import com.yf.zx.biz.log.login.entity.LoginLogVo;
import com.yf.zx.biz.sys.user.entity.User;
import com.yf.zx.biz.sys.user.entity.UserVo;
import com.yf.zx.core.base.web.PageReturn;
import com.yf.zx.core.util.common.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("loginLogService")
public class LoginLogService {


    @Autowired
    private LoginLogMapper loginLogDao;

    public PageReturn<LoginLog> findByPage(LoginLogVo loginLogVo) {
        PageHelper.startPage(loginLogVo.getPage(), loginLogVo.getRows());

         //排序
        if (StringUtils.isNotNull(loginLogVo.getOrderBy())) {
            PageHelper.orderBy(loginLogVo.getOrderBy());
        }
        List<LoginLog> loginLogList = loginLogDao.selectLoginLog(loginLogVo);
        PageReturn<LoginLog> page = new PageReturn<LoginLog>(new PageInfo<LoginLog>(loginLogList));
        return page;
    }

}
