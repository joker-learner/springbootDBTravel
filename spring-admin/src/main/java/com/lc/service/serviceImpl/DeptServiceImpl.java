package com.lc.service.serviceImpl;

import com.lc.mapper.SysDeptsMapper;
import com.lc.pojo.Depts;
import com.lc.pojo.JsonResult;
import com.lc.pojo.Node;
import com.lc.service.DeptService;
import com.lc.utils.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private SysDeptsMapper sysDeptsMapper;

    @Override
    public List<Map<String, Object>> findAllObject() {
        List<Map<String, Object>> deptList = sysDeptsMapper.findAllObject();
        if (deptList == null) {
            throw new ServiceException("查无此记录。。");
        } else {
            return deptList;
        }
    }

    @Override
    public int deleteDepById(Integer id) {
        if (id == 0 || id == null) {
            throw new ServiceException("未选取id");
        }
        int i = sysDeptsMapper.deleteDepById(id);
        return i;
    }

    @Override
    public List<Node> findZTreeDept() {
        List<Node> list = sysDeptsMapper.findZtreeDepe();
        return list;
    }

    @Override
    public int saveDepeObject(Depts deptsObject) {
        if (deptsObject != null) {
            int i = sysDeptsMapper.saveDeptObject(deptsObject);
            return 1;
        } else {
            throw new ServiceException("待保存的对象不能为空");
        }
    }

    @Override
    public int updateDeptObject(Depts deptsObject) {
        if (deptsObject != null) {
            int i = sysDeptsMapper.updateDeptObject(deptsObject);
        } else {
            throw new ServiceException("待更新的对象不能为空..");
        }
        return 0;
    }
}
