package com.yf.zx.web.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yf.zx.biz.sys.menu.entity.Menu;
import com.yf.zx.biz.sys.menu.service.MenuService;

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
		return "welcome";
	}
	
	@RequestMapping("/menu")
	@ResponseBody
	public String menu() {
//		JSONArray menu = new JSONArray();
//		JSONObject menu1 = new JSONObject();
//		JSONObject menu2 = new JSONObject();
//		
//		JSONArray children = new JSONArray();
//		JSONObject menu21 = new JSONObject();
//		JSONObject menu22 = new JSONObject();
//		JSONObject menu23 = new JSONObject();
//		
//		menu1.put("id", "1");
//		menu1.put("iconCls", "fa fa-home");
//		menu1.put("url", "#");
//		menu1.put("title", "首页");
//		menu1.put("active", "false");
//		
//		menu2.put("id", "2");
//		menu2.put("iconCls", "fa fa-cog");
//		menu2.put("url", "");
//		menu2.put("title", "系统管理");
//		menu2.put("active", "true");
//		
//		menu21.put("id", "21");
//		menu21.put("iconCls", "fa fa-user");
//		menu21.put("url", "sys/user/tolist");
//		menu21.put("title", "用户管理");
//		menu21.put("active", "false");
//
//		menu22.put("id", "22");
//		menu22.put("iconCls", "fa fa-users");
//		menu22.put("url", "http://www.sohu.com");
//		menu22.put("title", "角色管理");
//		menu22.put("active", "false");
//
//		menu23.put("id", "23");
//		menu23.put("iconCls", "fa fa-user-plus");
//		menu23.put("url", "http://www.163.com");
//		menu23.put("title", "权限管理");
//		menu23.put("active", "false");
//		
//		children.add(menu21);
//		children.add(menu22);
//		children.add(menu23);
//
//		menu2.put("children", children);
//		
//		menu.add(menu1);
//		menu.add(menu2);
		List<Menu> list = menuService.loadMenuList();
    	System.out.println(JSONObject.toJSONString(list));
		return JSONObject.toJSONString(list);
	}
}
