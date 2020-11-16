package com.lc.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysRoleMenuDao {

    //删除一条菜单记录，需要把他的关联role也删了
    Long deleteRoleMenueById(Long menuId);

}