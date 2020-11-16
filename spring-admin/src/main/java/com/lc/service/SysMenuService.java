package com.lc.service;


import com.lc.pojo.Node;
import com.lc.pojo.SysMenus;

import java.util.List;
import java.util.Map;


public interface SysMenuService {

    List<Map<String,Object>> findObjects();

    //连带menu关系表的数据也删了
    int deleteMenuObject(Long id);

    int saveObject(SysMenus entity);

    int updateMenuObject(SysMenus entiy);

    List<Node> findZtreeMenuNodes();
}
