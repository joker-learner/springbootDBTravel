package com.lc.mapper;

import com.lc.pojo.LogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LogServiceDao {

    //根据name查询一共有多少条，后面进行分页
    int getRowCount(@Param("username") String username);

    List<LogEntity> findPageObject(@Param("username")String username ,
                                   @Param("startIndex")Integer startIndex ,
                                   @Param("pageSize")Integer pageSize);

    int deleteLogByIds(@Param("ids") Integer... ids);  //@Param对应前端的数据

    int insertObject(LogEntity entity);
}