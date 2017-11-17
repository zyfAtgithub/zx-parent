package com.yf.zx.web.sys.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yf.zx.biz.sys.user.entity.User;
import com.yf.zx.biz.sys.user.entity.UserVo;
import com.yf.zx.biz.sys.user.service.UserService;
import com.yf.zx.core.base.web.BaseController;
import com.yf.zx.core.base.web.PageReturn;

@Controller
@RequestMapping("sys/user")
public class UserController extends BaseController {

	@Autowired
	UserService userService;
	
	@RequestMapping("list")
	@ResponseBody //处理 AJAX请求，返回响应的内容，而不是 View Name
	public String list(UserVo userVo) {
		PageReturn<User> page = userService.findByPage(userVo);
    	System.out.println(JSONObject.toJSONString(page));
		return JSONObject.toJSONString(page);
	}
}
