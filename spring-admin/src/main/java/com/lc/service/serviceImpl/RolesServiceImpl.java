package com.lc.service.serviceImpl;

import com.lc.mapper.RolersMapper;
import com.lc.pojo.PageObject;
import com.lc.pojo.Rolers;
import com.lc.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    private RolersMapper rolersMapper;

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
}
