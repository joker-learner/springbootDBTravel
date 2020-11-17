package com.lc.service.serviceImpl;

import com.lc.mapper.SysDeptsDao;
import com.lc.pojo.Depts;
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
    private SysDeptsDao sysDeptsDao;

    @Override
    public List<Map<String, Object>> findAllObject() {
        List<Map<String, Object>> deptList = sysDeptsDao.findAllObject();
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
        int i = sysDeptsDao.deleteDepById(id);
        return i;
    }

    @Override
    public List<Node> findZTreeDept() {
        List<Node> list = sysDeptsDao.findZtreeDepe();
        return list;
    }

    @Override
    public int saveDepeObject(Depts deptsObject) {
        if (deptsObject != null) {
            int i = sysDeptsDao.saveDeptObject(deptsObject);
            return 1;
        } else {
            throw new ServiceException("待保存的对象不能为空");
        }
    }

    @Override
    public int updateDeptObject(Depts deptsObject) {
        if (deptsObject != null) {
            int i = sysDeptsDao.updateDeptObject(deptsObject);
        } else {
            throw new ServiceException("待更新的对象不能为空..");
        }
        return 0;
    }
}
