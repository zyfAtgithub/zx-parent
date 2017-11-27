package com.yf.zx.web.sys.menu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yf.zx.biz.sys.menu.entity.Menu;
import com.yf.zx.biz.sys.menu.service.MenuService;

/**
 * MenuController [菜单管理控制器]
 *  
 * @author zhang.yifeng
 * @CreateDate 2017年11月27日
 * @version 1.0.0
 * @since  1.0.0 
 * @see com.yf.zx.web.sys.menu.controller 
 *
 */
@Controller
@RequestMapping("/sys/menu")
public class MenuController {

	@Autowired
	MenuService menuService;
	
	@RequestMapping("tolist")
	public String tolist() {
		return "sys/menu/menu_list";
	}
	
	@RequestMapping("menuTree")
	@ResponseBody //处理 AJAX请求，返回响应的内容，而不是 View Name
	public String getMenuTree() {
		List<Menu> menuList = menuService.loadMenuWithBtn();
		return JSONObject.toJSONString(menuList);
	}
}
