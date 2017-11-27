package com.yf.zx.biz.sys.menu.dao;

import java.util.List;

import com.yf.zx.biz.sys.menu.entity.Menu;

public interface MenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Menu record);

    Menu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
    
    List<Menu> selectMenu(Menu record);
}