package com.lc.mapper;

import com.lc.pojo.LogEntity;
import com.lc.pojo.SysUsers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

@Mapper
public interface SysUserMapper {
    //跟据username 找 角色名
    Set<String>  findRoleByName(String username);
    //跟据usernmae 找权限名
    Set<String> findPermissionByName(String name);

    SysUsers findByName(String name);

    int findRowCount(@Param("username") String username);

    List<SysUsers> findPageObject(@Param("username")String username ,
                                   @Param("startIndex")Integer startIndex , @Param("pageSize")Integer pageSize);
}