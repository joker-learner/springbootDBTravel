package com.lc.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleUserDao {

    int deleteRoleUserByRoleId(Long roleId);

}

