package com.lc.service;

import com.lc.pojo.PageObject;
import com.lc.pojo.Rolers;

public interface RolesService {

    PageObject<Rolers> findPageObj(String name, Integer pageCurrent);


}
