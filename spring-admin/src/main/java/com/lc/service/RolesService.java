package com.lc.service;

import com.lc.common.CheckBox;
import com.lc.pojo.PageObject;
import com.lc.pojo.Rolers;
import com.lc.pojo.SysRoleMenuVo;

import java.util.List;

public interface RolesService {

    PageObject<Rolers> findPageObj(String name, Integer pageCurrent);

    int deleteObjectById(Long id);


    int insertRoleObject(Rolers rolers, Integer... menuIds);

    int updatRoleObject(Rolers rolers, Integer... menuIds);

    SysRoleMenuVo findObjectById(Long id);

    List<CheckBox> findObjects();
}
