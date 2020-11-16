package com.lc.service.serviceImpl;

import com.lc.mapper.LogServiceDao;
import com.lc.pojo.LogEntity;
import com.lc.pojo.PageObject;
import com.lc.service.LogService;
import com.lc.utils.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service()
public class LogServiceImpl implements LogService {

    @Autowired
    private LogServiceDao logServiceDao;

    @Override
    public PageObject<LogEntity> findPageObjecLog(String username, Integer pageCurrent) {
        if (pageCurrent == null || pageCurrent < 1) {
            throw new IllegalArgumentException("当前页码不正确");
        }
        //2.基于条查询总记录数
        //2.1) 执行查询
        int rowCount = logServiceDao.getRowCount(username);
        if (rowCount == 0)
            throw new ServiceException("系统没有查到对应记录");

        int pageSize = 11;
        int startIndex = (pageCurrent - 1) * pageSize;
        //条件查询 ， limit(startIndex , pageSize);
        //records  存的是List<LogEntity>
        List<LogEntity> records = logServiceDao.findPageObject(username, startIndex, pageSize);
        PageObject<LogEntity> pageObject = new PageObject<>();
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setPageSize(pageSize);
        pageObject.setRowCount(rowCount);
        pageObject.setRecords(records);
        pageObject.setPageCount((rowCount - 1) / pageSize + 1);
        //5.返回封装结果。
        return pageObject;
    }

    @Override
    public int deletLogByIds(Integer... ids) {
        if(ids==null||ids.length==0)
            throw new IllegalArgumentException("请选择一个");
        int rows;
        try {
            rows = logServiceDao.deleteLogByIds(ids);
        }
        catch (Exception e){
            e.printStackTrace();
            //发出报警信息(例如给运维人员发短信)
            throw new ServiceException("系统故障，正在恢复中...");
        }
        if(rows==0)
            throw new ServiceException("记录可能已经不存在");
        //5.返回结果
        return rows;
    }

    @Override
    public void saveObject(LogEntity entity) {
        logServiceDao.insertObject(entity);
    }
}
