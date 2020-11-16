package com.lc.mapper;

import com.lc.pojo.Rolers;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RolersMapper {

    Integer getRowCount();

    List<Rolers> findPageObj(String name, Integer startIndex, Integer pageSize);

}
