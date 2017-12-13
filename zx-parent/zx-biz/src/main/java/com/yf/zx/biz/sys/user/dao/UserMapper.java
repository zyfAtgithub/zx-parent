package com.yf.zx.biz.sys.user.dao;

import java.util.List;

import com.yf.zx.biz.sys.user.entity.User;
import com.yf.zx.biz.sys.user.entity.UserRole;
import com.yf.zx.biz.sys.user.entity.UserVo;

public interface UserMapper {

    User selectById(Long id);

    int updateById(User record);
    
    List<User> selectByLoginName(String loginname);

    List<User> selectUser(UserVo userVo);
    
    int insertAutoId(User record);

    int delByIds(String[] idArr);
    
    List<Long> selectRoleidsByUserId(Long userId);
    
    int delUserRoleByUserId(Long userId);
    
    int batchInserUserRole(List<UserRole> list);
}