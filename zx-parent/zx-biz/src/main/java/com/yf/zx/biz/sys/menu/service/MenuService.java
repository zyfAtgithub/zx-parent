package com.yf.zx.biz.sys.menu.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yf.zx.biz.sys.menu.dao.MenuMapper;
import com.yf.zx.biz.sys.menu.entity.Menu;

@Service("menuService")
public class MenuService {

	Logger logger = LoggerFactory.getLogger(MenuService.class);
	
	@Autowired
	MenuMapper menuDao;
	
	public List<Menu> getMenuList() {
		List<Menu> menuList = menuDao.selectMenu();
		return menuList;
	}

	public void addMenu(Menu menu) {
		menuDao.insert(menu);
	}
	
}
