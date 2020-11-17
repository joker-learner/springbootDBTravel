package com.lc.mapper;

import com.lc.pojo.Rolers;
import com.lc.service.RolesService;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RolersMapper {

    Integer getRowCount();

    List<Rolers> findPageObj(String name, Integer startIndex, Integer pageSize);

    int delteObjectById(Long id);

    Rolers findObjectById(Long id);

    int insertRoleObject(Rolers rolers);
}
