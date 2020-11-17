package com.lc.mapper;

import com.lc.pojo.Node;
import com.lc.pojo.SysMenus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MenuDao {

    int insertObject(SysMenus entity);

    List<Map<String, Object>> findObjects();

    int checkIfHaschild(Long id);

    int doDeleteById(Long id);

    int updateMenuEntity(SysMenus entity);

    List<Node> findZtreeMenuNodes();
}
