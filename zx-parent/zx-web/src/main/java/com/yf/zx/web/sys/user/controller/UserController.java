package com.yf.zx.web.sys.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yf.zx.biz.sys.user.entity.User;
import com.yf.zx.biz.sys.user.entity.UserVo;
import com.yf.zx.biz.sys.user.service.UserService;
import com.yf.zx.core.base.web.BaseController;
import com.yf.zx.core.base.web.PageReturn;
import com.yf.zx.core.base.web.ResultReturn;
import com.yf.zx.web.shiro.encrypt.PasswordEncrypt;

@Controller
@RequestMapping("sys/user")
public class UserController extends BaseController {

	
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@RequestMapping("list")
	@ResponseBody //处理 AJAX请求，返回响应的内容，而不是 View Name
	public String list(UserVo userVo) {
		PageReturn<User> page = userService.findByPage(userVo);
    	System.out.println(JSONObject.toJSONString(page));
		return JSONObject.toJSONString(page);
	}
	
	
	
	@RequestMapping(value = "add", method=RequestMethod.POST)
	@ResponseBody
	public String add(User user) {
		PasswordEncrypt.encrypt(user);
		ResultReturn ret = userService.addUser(user);
		return JSONObject.toJSONString(ret);
	}

	@RequestMapping(value = "del", method=RequestMethod.POST)
	@ResponseBody
	public String del(String delIds) {
		ResultReturn ret = userService.deleteUserByIds(delIds);
		return JSONObject.toJSONString(ret);
	}
	

}
