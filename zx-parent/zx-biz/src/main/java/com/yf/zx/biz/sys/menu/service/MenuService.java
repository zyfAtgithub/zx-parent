package com.yf.zx.biz.sys.menu.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yf.zx.biz.sys.menu.dao.MenuMapper;
import com.yf.zx.biz.sys.menu.entity.Menu;
import com.yf.zx.biz.sys.menu.entity.MenuVo;
import com.yf.zx.core.base.web.PageReturn;
import com.yf.zx.core.base.web.ResultReturn;
import com.yf.zx.core.util.common.StringUtils;

@Service("menuService")
public class MenuService {

	Logger logger = LoggerFactory.getLogger(MenuService.class);
	
	@Autowired
	MenuMapper menuDao;
	
	/**
	 * 加载菜单
	 *  
	 * @author zhang.yifeng 
	 * @return
	 */
	public ResultReturn loadMenuList() {
		ResultReturn ret = new ResultReturn();
		try {
			List<Menu> menuList = getTopMenuList();
			for (Menu topMenu : menuList) {
				List<Menu> subMenuList = getSubMenuList(topMenu.getId());
				topMenu.setChildren(subMenuList.toArray(new Menu[subMenuList.size()]));
			}
			ret.setResultCode("200");
			ret.setResultMsg("查询成功！");
			ret.setData(menuList);
		} catch (Exception e) {
			logger.error("菜单加载失败！", e);
			ret.setResultCode("0");
			ret.setResultMsg("菜单加载失败！");
		}
		return ret;
	}

	/**
	 * 加载菜单树
	 *  
	 * @author zhang.yifeng 
	 * @return
	 */
	public ResultReturn loadMenuTree() {
		ResultReturn ret = new ResultReturn();
		try {
			List<Menu> menuList = getTopMenuList();
			for (Menu topMenu : menuList) {
				List<Menu> subMenuList = getSubMenuList(topMenu.getId());
				for (Menu subMenu : subMenuList) {
					List<Menu> btnList = getOperationBtn(subMenu.getId());
					subMenu.setChildren(btnList.toArray(new Menu[btnList.size()]));
				}
				topMenu.setChildren(subMenuList.toArray(new Menu[subMenuList.size()]));
			}
			ret.setResultCode("200");
			ret.setResultMsg("查询成功！");
			ret.setData(menuList);
		} catch (Exception e) {
			logger.error("菜单树加载失败！", e);
			ret.setResultCode("0");
			ret.setResultMsg("菜单树加载失败！");
		}
		return ret;
	}
	
	/**
	 * 获取顶级菜单
	 *  
	 * @author zhang.yifeng 
	 * @param menu
	 * @return
	 */
	private List<Menu> getTopMenuList() {
		MenuVo menuVo = new MenuVo();
		menuVo.setParentid(0l);
		menuVo.setLevel(1);
		List<Menu> menuList = menuDao.selectMenu(menuVo);
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
		MenuVo menuVo = new MenuVo();
		menuVo.setParentid(parentId);
		menuVo.setLevel(2);
		menuVo.setIsbtn(false);
		menuVo.setIsshow(true);
		List<Menu> menuList = menuDao.selectMenu(menuVo);
		return menuList;
	}

	/**
	 * 获取按钮
	 *  
	 * @author zhang.yifeng 
	 * @param parentId
	 * @return
	 */
	private List<Menu> getOperationBtn(Long parentId) {
		MenuVo menuVo = new MenuVo();
		menuVo.setParentid(parentId);
		menuVo.setLevel(3);
		menuVo.setIsbtn(true);
//		menuVo.setIsshow(false);
		List<Menu> menuList = menuDao.selectMenu(menuVo);
		return menuList;
	}
	
	
	public PageReturn<Menu> findMenuByPage(MenuVo menuVo) {
		PageHelper.startPage(menuVo.getPage(), menuVo.getRows());
		
		//排序
		if (StringUtils.isNotNull(menuVo.getOrderBy())) {
			PageHelper.orderBy(menuVo.getOrderBy());
		}
		List<Menu> userList = menuDao.selectMenu(menuVo);
		PageReturn<Menu> page = new PageReturn<Menu>(new PageInfo<Menu>(userList));
		return page;  
	}
	
	/**
	 * 新增菜单
	 *  
	 * @author zhang.yifeng 
	 * @param menu
	 * @return
	 */
	public ResultReturn addMenu(Menu menu) {
		ResultReturn ret = new ResultReturn();
		if (menu == null) {
			logger.error("菜单信息不能为空！");
			ret.setResultCode("0");
			ret.setResultMsg("菜单信息不能为空！");
			return ret;
		}
		
		try {
			menuDao.insertAutoId(menu);
			ret.setResultCode("200");
			ret.setResultMsg("新增成功！");
		} catch (Exception e) {
			logger.error("新增菜单失败！", e);
			ret.setResultCode("0");
			ret.setResultMsg("新增菜单失败！");
		}
		return ret;
	}
	
	/**
	 * 根据Id查询
	 *  
	 * @author zhang.yifeng 
	 * @param id
	 * @return
	 */
	public Menu findById(Long id) {
		return menuDao.selectById(id);
	}
	
	/**
	 * 根据Id修改
	 *  
	 * @author zhang.yifeng 
	 * @param menu
	 * @return
	 */
	public ResultReturn editById(Menu menu) {
		ResultReturn ret = new ResultReturn();
		if (menu == null || menu.getId() == null) {
			ret.setResultCode("0");
			ret.setResultMsg("菜单id不能为空！");
			return ret;
		}
		
		try {
			menuDao.updateById(menu);
			ret.setResultCode("200");
			ret.setResultMsg("菜单修改成功！");
		} catch (Exception e) {
			logger.error("菜单修改失败！", e);
			ret.setResultCode("0");
			ret.setResultMsg("菜单修改失败！");
		}
		
		return ret;
	}
	
	/**
	 * 删除菜单
	 *  
	 * @author zhang.yifeng 
	 * @param ids [多个以 "|"分割]
	 * @return
	 */
	public ResultReturn deleteMenuByIds(String ids) {
		ResultReturn ret = new ResultReturn();
		if (StringUtils.isNullOrEmpty(ids)) {
			ret.setResultCode("0");
			ret.setResultMsg("id列表不能为空！");
			return ret;
		}
		
		try {
			String[] idArr = ids.split("\\|");
			menuDao.delByIds(idArr);
			ret.setResultCode("200");
			ret.setResultMsg("删除菜单成功！");
		} catch (Exception e) {
			logger.error("删除菜单失败！", e);
			ret.setResultCode("0");
			ret.setResultMsg("删除菜单失败！");
		}
		return ret;
	}
	
}
