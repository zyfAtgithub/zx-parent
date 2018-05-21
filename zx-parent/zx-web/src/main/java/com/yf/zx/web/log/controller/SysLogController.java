package com.yf.zx.web.log.controller;


import com.alibaba.fastjson.JSONObject;
import com.yf.zx.biz.log.login.entity.LoginLog;
import com.yf.zx.biz.log.login.entity.LoginLogVo;
import com.yf.zx.biz.log.login.service.LoginLogService;
import com.yf.zx.biz.log.syslog.entity.SysLog;
import com.yf.zx.biz.log.syslog.entity.SysLogVo;
import com.yf.zx.biz.log.syslog.service.SysLogService;
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
@RequestMapping("/log/syslog")
public class SysLogController {

    Logger logger = LoggerFactory.getLogger(SysLogController.class);

    @Autowired
    SysLogService sysLogService;

    @RequestMapping("tolist")
    public String toList() {
        return "log/syslog/syslog_list";
    }


    @RequestMapping("list")
    @ResponseBody //处理 AJAX请求，返回响应的内容，而不是 View Name
    public String list(SysLogVo sysLogVo) {
        PageReturn<SysLog> page = sysLogService.findByPage(sysLogVo);
        return JSONObject.toJSONString(page);
    }
}