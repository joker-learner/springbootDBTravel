package com.lc.service;

import com.lc.pojo.PageObject;
import com.lc.pojo.Rolers;

public interface RolesService {

    PageObject<Rolers> findPageObj(String name, Integer pageCurrent);

    int deleteObjectById(Long id);

    Rolers findObjectById(Long id);

    int insertRoleObject(Rolers rolers , Integer... menu_id);

}
