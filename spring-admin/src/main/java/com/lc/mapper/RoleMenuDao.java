package com.lc.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMenuDao {

    //删除一条菜单记录，需要把他的关联role也删了
    Long deleteRoleMenueById(Long menuId);

    //插入一条角色时，需要给角色授权
    int insertMenuRole(Long role_id, Integer[] menuIds);

    //当删除一条角色时，需要删除其相关的menu   在sys_role_menus
    int deleteRoleMenueByRoleId(Long role_id);

    Long findMenuIdsByRoleId(Long roleId);

}