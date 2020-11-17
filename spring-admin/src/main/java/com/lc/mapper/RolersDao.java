package com.lc.mapper;

import com.lc.common.CheckBox;
import com.lc.pojo.Rolers;
import com.lc.pojo.SysRoleMenuVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RolersDao {

    Integer getRowCount();

    List<Rolers> findPageObj(String name, Integer startIndex, Integer pageSize);

    int delteObjectById(Long id);

    SysRoleMenuVo findObjectById(Long id);

    int insertRoleObject(Rolers rolers);

    int updateRoleObject(Rolers rolers);

    List<CheckBox> findObjects();
}
