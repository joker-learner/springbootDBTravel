package com.lc.service.serviceImpl;

import com.lc.mapper.RolersMapper;
import com.lc.mapper.RoleMenuDao;
import com.lc.pojo.PageObject;
import com.lc.pojo.Rolers;
import com.lc.service.RolesService;
import com.lc.utils.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    private RolersMapper rolersMapper;

    @Autowired
    private RoleMenuDao roleMenuDao;

    @Override
    public PageObject<Rolers> findPageObj(String name, Integer pageCurrent) {
        Integer pageSize = 2;   //每页条数
        Integer rowCount = rolersMapper.getRowCount();  //一共有多少条记录
        Integer pageCount = rowCount / pageSize;        //有多少页
        Integer startIndex = (pageCurrent - 1) * pageSize;
        List<Rolers> records = rolersMapper.findPageObj(name, startIndex, pageSize); //查询结果集

        if ((rowCount % pageSize) != 0) {
            pageCount++;   //
        }
        PageObject<Rolers> pageObject = new PageObject<Rolers>(rowCount, records, pageCount, pageSize, pageCurrent);
        return pageObject;
    }

    @Override
    public int deleteObjectById(Long id) {     //role_id
        int i = rolersMapper.delteObjectById(id);
        if (i == 0) {
            throw new ServiceException("没有收到待删除的ID...");
        }
        int j = roleMenuDao.deleteRoleMenueByRoleId(id);
        return i;
    }

    @Override
    public Rolers findObjectById(Long id) {
        Rolers rolers = rolersMapper.findObjectById(id);
        return rolers;
    }

    @Override
    public int insertRoleObject(Rolers rolers, Integer... menu_id) {
        int i = rolersMapper.insertRoleObject(rolers);
        return 0;
    }
}
