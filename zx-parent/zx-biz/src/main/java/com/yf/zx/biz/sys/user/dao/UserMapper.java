package com.yf.zx.biz.sys.user.dao;

import java.util.List;

import com.yf.zx.biz.sys.user.entity.User;
import com.yf.zx.biz.sys.user.entity.UserVo;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> selectByLoginName(String loginname);

    List<User> selectUser(UserVo userVo);
    
    
    int insertAutoId(User record);
}