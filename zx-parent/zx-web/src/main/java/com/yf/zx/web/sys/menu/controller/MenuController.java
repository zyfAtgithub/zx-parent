package com.yf.zx.web.sys.menu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yf.zx.biz.sys.menu.entity.Menu;
import com.yf.zx.biz.sys.menu.entity.MenuVo;
import com.yf.zx.biz.sys.menu.service.MenuService;
import com.yf.zx.biz.sys.user.entity.User;
import com.yf.zx.core.base.web.PageReturn;
import com.yf.zx.core.base.web.ResultReturn;

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

	@RequestMapping("toadd")
	public String toadd(@RequestParam(value="level", required=true) Integer level,
			@RequestParam(value="parentid", required=true) Long parentid, Model model){
		Menu menu = new Menu();
		menu.setLevel(level);
		menu.setParentid(parentid);
		model.addAttribute("menu", menu);
		return "sys/menu/menu_addOrEdit";
	}
	
	@RequestMapping("toedit")
	public String toEdit(@RequestParam(value="id", required=true) Long id, Model model) {
		Menu menu = menuService.findById(id);
		model.addAttribute("menu", menu);
		return "sys/menu/menu_addOrEdit";
	}

	@RequestMapping("toview")
	public String toView(@RequestParam(value="id", required=true) Long id, Model model) {
		Menu menu = menuService.findById(id);
		model.addAttribute("menu", menu);
		return "sys/menu/menu_view";
	}
	
	@RequestMapping("menuTree")
	@ResponseBody
	public String getMenuTree() {
		ResultReturn ret = menuService.loadMenuTree();
		return JSONObject.toJSONString(ret);
	}
	
	@RequestMapping("menuPage")
	@ResponseBody
	 public String findMenuByPage(MenuVo menuVo) {
    	PageReturn<Menu> page = menuService.findMenuByPage(menuVo);
    	return JSONObject.toJSONString(page);
    }
	
	
	@RequestMapping(value = "save", method=RequestMethod.POST)
	@ResponseBody
	public String save(Menu menu) {
		ResultReturn ret = null;
		if (null == menu.getId()) {
			ret = menuService.addMenu(menu);
		}
		else {
			ret = menuService.editById(menu);
		}
		return JSONObject.toJSONString(ret);
	}
	
	@RequestMapping(value = "del", method=RequestMethod.POST)
	@ResponseBody
	public String del(String delIds) {
		ResultReturn ret = menuService.deleteMenuByIds(delIds);
		return JSONObject.toJSONString(ret);
	}
}
