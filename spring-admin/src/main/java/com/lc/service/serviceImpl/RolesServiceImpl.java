package com.lc.service.serviceImpl;

import com.lc.mapper.RoleUserDao;
import com.lc.mapper.RolersDao;
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
    private RolersDao rolersDao;

    @Autowired
    private RoleMenuDao roleMenuDao;

    @Autowired
    private RoleUserDao roleUserDao;

    @Override
    public PageObject<Rolers> findPageObj(String name, Integer pageCurrent) {
        Integer pageSize = 2;   //每页条数
        Integer rowCount = rolersDao.getRowCount();  //一共有多少条记录
        Integer pageCount = rowCount / pageSize;        //有多少页
        Integer startIndex = (pageCurrent - 1) * pageSize;
        List<Rolers> records = rolersDao.findPageObj(name, startIndex, pageSize); //查询结果集

        if ((rowCount % pageSize) != 0) {
            pageCount++;   //
        }
        PageObject<Rolers> pageObject = new PageObject<Rolers>(rowCount, records, pageCount, pageSize, pageCurrent);
        return pageObject;
    }

    @Override
    public int deleteObjectById(Long id) {     //role_id
        int i = rolersDao.delteObjectById(id);
        int j = roleMenuDao.deleteRoleMenueByRoleId(id);
        int k = roleUserDao.deleteRoleUserByRoleId(id);
        if (i == 0) {
            throw new ServiceException("没有收到待删除的ID...");
        }
        return i;
    }

    @Override
    public Rolers findObjectById(Long id) {
        Rolers rolers = rolersDao.findObjectById(id);
        return rolers;
    }

    @Override
    public int insertRoleObject(Rolers rolers, Integer... menu_id) {
        int i = rolersDao.insertRoleObject(rolers);

        //给角色授权
        roleMenuDao.insertMenuRole(rolers.getId(), menu_id);
        return i;
    }
}
