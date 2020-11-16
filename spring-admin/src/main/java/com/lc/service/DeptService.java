package com.lc.service;

import com.lc.pojo.Depts;
import com.lc.pojo.Node;

import java.util.List;
import java.util.Map;

public interface DeptService {

    List<Map<String, Object>> findAllObject();

    int deleteDepById(Integer id);

    List<Node> findZTreeDept();

    int saveDepeObject(Depts deptsObject);

    int updateDeptObject(Depts deptsObject);

}
