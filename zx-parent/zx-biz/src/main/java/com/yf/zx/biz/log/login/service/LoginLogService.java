package com.yf.zx.biz.log.login.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yf.zx.biz.log.login.dao.LoginLogMapper;
import com.yf.zx.biz.log.login.entity.LoginLog;
import com.yf.zx.biz.log.login.entity.LoginLogVo;
import com.yf.zx.core.base.web.PageReturn;
import com.yf.zx.core.base.web.ResultReturn;
import com.yf.zx.core.util.common.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("loginLogService")
public class LoginLogService {

    Logger logger = LoggerFactory.getLogger(LoginLogService.class);

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

    /**
     * 新增日志
     * @param loginLog
     * @return
     */
    public ResultReturn addLog(LoginLog loginLog) {
        ResultReturn resultReturn = new ResultReturn();
        if (StringUtils.isNullOrEmpty(loginLog.getIp()) ||
                StringUtils.isNullOrEmpty(loginLog.getLoginuser())) {
            resultReturn.setResultCode("0");
            resultReturn.setResultMsg("登录ip或登录用户名不能为空！");
            return resultReturn;
        }

        try {
            loginLogDao.insert(loginLog);
            resultReturn.setResultCode("200");
            resultReturn.setResultMsg("新增登录日志成功！");
        } catch (Exception e) {
            logger.error("新增登录日志失败！", e);
            resultReturn.setResultMsg("0");
            resultReturn.setResultMsg("新增登录日志失败！");
        }

        return  resultReturn;
    }

    public LoginLog findById(Long id) {
        return loginLogDao.selectByPrimaryKey(id);
    }

    public ResultReturn editById(LoginLog loginLog) {
        ResultReturn ret = new ResultReturn();
        if (loginLog == null || loginLog.getId() == null) {
            ret.setResultCode("0");
            ret.setResultMsg("日志id不能为空！");
            return ret;
        }

        try {
            loginLogDao.updateByPrimaryKey(loginLog);
            ret.setResultCode("1");
            ret.setResultMsg("更新登录日志成功！");
        } catch (Exception e) {
            logger.error("更新登录日志出错！", e);
        }

        return ret;
    }

    /**
     * 删除登录日志
     *
     * @author zhang.yifeng
     * @param ids [多个以 "|"分割]
     * @return
     */
    public ResultReturn deleteLoginLogByIds(String ids) {
        ResultReturn ret = new ResultReturn();
        if (StringUtils.isNullOrEmpty(ids)) {
            ret.setResultCode("0");
            ret.setResultMsg("id列表不能为空！");
            return ret;
        }

        try {
            String[] idArr = ids.split("\\|");
            loginLogDao.delByIds(idArr);
            ret.setResultCode("200");
            ret.setResultMsg("删除登录日志成功！");
        } catch (Exception e) {
            logger.error("删除登录日志失败！", e);
            ret.setResultCode("0");
            ret.setResultMsg("删除登录日志失败！");
        }
        return ret;
    }
}
