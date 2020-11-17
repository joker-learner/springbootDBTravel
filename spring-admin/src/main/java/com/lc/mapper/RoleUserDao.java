package com.lc.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleUserDao {

    int deleteRoleUserByRoleId(Long roleId);

    int insertObjects(@Param("userId")Integer userId,
                      @Param("roleIds")Integer[] roleIds);

    List<Integer> findRoleIdsByUserId(Integer userId);

    int deleteObjectsByUserId(Integer userId);
}

