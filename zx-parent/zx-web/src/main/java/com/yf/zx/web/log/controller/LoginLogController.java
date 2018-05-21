package com.yf.zx.web.log.controller;


import com.alibaba.fastjson.JSONObject;
import com.yf.zx.biz.log.login.entity.LoginLog;
import com.yf.zx.biz.log.login.entity.LoginLogVo;
import com.yf.zx.biz.log.login.service.LoginLogService;
import com.yf.zx.biz.sys.user.entity.User;
import com.yf.zx.biz.sys.user.entity.UserVo;
import com.yf.zx.core.base.web.PageReturn;
import com.yf.zx.core.base.web.ResultReturn;
import com.yf.zx.core.util.common.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/log/loginlog")
public class LoginLogController {

    Logger logger = LoggerFactory.getLogger(LoginLogController.class);

    @Autowired
    LoginLogService loginLogService;

    @RequestMapping("tolist")
    public String toList() {
        return "log/login/loginlog_list";
    }


    @RequestMapping("list")
    @ResponseBody //处理 AJAX请求，返回响应的内容，而不是 View Name
    public String list(LoginLogVo loginLogVo) {

        if (StringUtils.isNotNullAndEmpty(loginLogVo.getLoginuser())) {
            loginLogVo.setLoginuser("%" + loginLogVo.getLoginuser() + "%");
        }

        if (StringUtils.isNotNullAndEmpty(loginLogVo.getLogindevice())) {
            loginLogVo.setLogindevice("%" + loginLogVo.getLogindevice() + "%");
        }

        PageReturn<LoginLog> page = loginLogService.findByPage(loginLogVo);
        return JSONObject.toJSONString(page);
    }


    @RequestMapping(value = "del", method= RequestMethod.POST)
    @ResponseBody
    public String del(String delIds) {
        ResultReturn ret = loginLogService.deleteLoginLogByIds(delIds);
        return JSONObject.toJSONString(ret);
    }
}
