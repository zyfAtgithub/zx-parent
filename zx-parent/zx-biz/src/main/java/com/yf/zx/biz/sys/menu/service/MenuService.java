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
	
	public List<Menu> loadMenuList() {
		List<Menu> menuList = getTopMenuList();
		for (Menu topMenu : menuList) {
			List<Menu> subMenuList = getSubMenuList(topMenu.getId());
			topMenu.setChildren(subMenuList.toArray(new Menu[subMenuList.size()]));
		}
		return menuList;
	}

	public List<Menu> loadMenuWithBtn() {
		List<Menu> menuList = getTopMenuList();
		for (Menu topMenu : menuList) {
			List<Menu> subMenuList = getSubMenuList(topMenu.getId());
			for (Menu subMenu : subMenuList) {
				List<Menu> btnList = getBtn(subMenu.getId());
				subMenu.setChildren(btnList.toArray(new Menu[btnList.size()]));
			}
			topMenu.setChildren(subMenuList.toArray(new Menu[subMenuList.size()]));
		}
		return menuList;
	}
	
	/**
	 * 获取顶级菜单
	 *  
	 * @author zhang.yifeng 
	 * @param menu
	 * @return
	 */
	private List<Menu> getTopMenuList() {
		Menu menu = new Menu();
		menu.setParentid(0l);
		menu.setLevel(1);
		List<Menu> menuList = menuDao.selectMenu(menu);
		return menuList;
	}

	/**
	 * 获取子菜单
	 *  
	 * @author zhang.yifeng 
	 * @param parentId
	 * @return
	 */
	private List<Menu> getSubMenuList(Long parentId) {
		Menu menu = new Menu();
		menu.setParentid(parentId);
		menu.setLevel(2);
		menu.setIsshow(true);
		List<Menu> menuList = menuDao.selectMenu(menu);
		return menuList;
	}

	/**
	 * 获取按钮
	 *  
	 * @author zhang.yifeng 
	 * @param parentId
	 * @return
	 */
	private List<Menu> getBtn(Long parentId) {
		Menu menu = new Menu();
		menu.setParentid(parentId);
		menu.setLevel(3);
		menu.setIsbtn(true);
		menu.setIsshow(false);
		List<Menu> menuList = menuDao.selectMenu(menu);
		return menuList;
	}
	
	public void addMenu(Menu menu) {
		menuDao.insert(menu);
	}
	
}
