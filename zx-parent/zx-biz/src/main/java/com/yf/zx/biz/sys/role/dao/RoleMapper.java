package com.yf.zx.biz.sys.role.dao;

import java.util.List;

import com.yf.zx.biz.sys.role.entity.Role;
import com.yf.zx.biz.sys.role.entity.RolePermission;
import com.yf.zx.biz.sys.role.entity.RoleVo;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateById(Role record);

    int updateByPrimaryKey(Role record);
    
    List<Role> selectByRoleName(Role role);

    List<Role> selectRole(RoleVo roleVo);
    
    int insertAutoId(Role record);
    		
    int delByIds(String[] idArr);

    List<Long> selectPermidsByRoleId(Long roleId);
    
    int delRolePermByRoleId(Long roleId);
    
    int batchInserRolePerm(List<RolePermission> list);
    
}