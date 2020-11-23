package com.lc.service;

import com.lc.pojo.LogEntity;
import com.lc.pojo.PageObject;

public interface LogService {

    PageObject<LogEntity> findPageObjecLog(String username, Integer pageCurrent);

    int deletLogByIds(Integer... ids);

    void saveObject(LogEntity entity);

}
