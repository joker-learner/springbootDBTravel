package com.lc.service.serviceImpl;

import com.alibaba.druid.util.StringUtils;
import com.lc.mapper.MenuMapper;
import com.lc.mapper.SysRoleMenuDao;
import com.lc.pojo.Node;
import com.lc.pojo.SysMenus;
import com.lc.service.SysMenuService;
import com.lc.utils.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysMenuServiceImpl implements SysMenuService {
    //菜单本身操作
    @Autowired
    private MenuMapper menuMapper;
    //菜单相关联的role操作
    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;

    @Override//查emnu表全部
    public List<Map<String, Object>> findObjects() {
        List<Map<String, Object>> menusList = menuMapper.findObjects();
        if (menusList.size() == 0) {
            throw new ServiceException("菜单表为空...");
        }
        return menusList;
    }

    @Override //删除menu表的一条，连带menu关系表也删了
    public int deleteMenuObject(Long id) {
        //1.验证数据的合法性
        if (id == null || id <= 0)
            throw new IllegalArgumentException("请先选择");
        //2.基于id进行子元素查询
        int count = menuMapper.checkIfHaschild(id);
        if (count > 0)
            throw new ServiceException("请先删除子菜单");
        //3.删除角色,菜单关系数据
        sysRoleMenuDao.deleteRoleMenueById(id);
        //4.删除菜单元素
        int rows = menuMapper.doDeleteById(id);
        if (rows == 0)
            throw new ServiceException("此菜单可能已经不存在");
        //5.返回结果
        return rows;
    }

    @Override
    public int saveObject(SysMenus entity) {
        if (entity == null)
            throw new IllegalArgumentException("保存对象不能为空");
        if (StringUtils.isEmpty(entity.getName()))
            throw new IllegalArgumentException("菜单名不能为空");
        //2.保存数据
        int rows = menuMapper.insertObject(entity);
        //3.返回数据
        return rows;
    }

    @Override
    public int updateMenuObject(SysMenus entiy) {
        if (entiy == null) {
            throw new ServiceException("保存对象不能为空");
        }
        if (StringUtils.isEmpty(entiy.getName())) {
            throw new ServiceException("菜单名不能为空");
        }
        int i = menuMapper.updateMenuEntity(entiy);
        if (i == 0) {
            throw new ServiceException("数据已存在");
        }
        return i;
    }

    @Override
    public List<Node> findZtreeMenuNodes() {
        return menuMapper.findZtreeMenuNodes();
    }
}
