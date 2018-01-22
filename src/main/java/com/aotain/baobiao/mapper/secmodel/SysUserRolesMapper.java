package com.aotain.baobiao.mapper.secmodel;

import com.aotain.baobiao.model.secmodel.SysUserRoles;

public interface SysUserRolesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUserRoles record);

    int insertSelective(SysUserRoles record);

    SysUserRoles selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUserRoles record);

    int updateByPrimaryKey(SysUserRoles record);
}