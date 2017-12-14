package com.yf.zx.web.common.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yf.zx.biz.sys.menu.service.MenuService;
import com.yf.zx.biz.sys.user.service.UserService;
import com.yf.zx.core.base.web.ResultReturn;

@Controller
@RequestMapping("")
public class CommonController {

	@Autowired
	MenuService menuService;

	@Autowired
	UserService userService;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/welcome")
	public String welcome() {
		return "index2";
	}

	@RequestMapping("/menu")
	@ResponseBody
	public String menu() {
		Subject subject = SecurityUtils.getSubject();
		List<Long> permMenuIds = userService.getPermIdsByLoginName((String) subject.getPrincipal());
		ResultReturn ret = menuService.loadMenuList(permMenuIds);
		return JSONObject.toJSONString(ret);
	}
}
