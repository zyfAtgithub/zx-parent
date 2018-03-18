package com.yf.zx.biz.sys.menu.dao;

import java.util.List;

import com.yf.zx.biz.sys.menu.entity.Menu;
import com.yf.zx.biz.sys.menu.entity.MenuVo;

public interface MenuMapper {
    int deleteByPrimaryKey(Long id);

    int insertAutoId(Menu record);

    Menu selectById(Long id);

    int updateByPrimaryKey(Menu record);
    
    List<Menu> selectMenu(MenuVo menuVo);

	int updateById(Menu menu);
	
	int delByIds(String[] idArr);
}