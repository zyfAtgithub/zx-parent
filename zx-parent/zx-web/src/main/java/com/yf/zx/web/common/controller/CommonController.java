package com.yf.zx.web.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yf.zx.biz.sys.menu.service.MenuService;
import com.yf.zx.core.base.web.ResultReturn;

@Controller
@RequestMapping("")
public class CommonController {

	@Autowired
	MenuService menuService;
	
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
		ResultReturn ret = menuService.loadMenuList();
		return JSONObject.toJSONString(ret);
	}
}
