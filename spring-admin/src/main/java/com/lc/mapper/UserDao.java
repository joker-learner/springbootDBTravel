package com.lc.mapper;

import com.lc.pojo.SysUser;
import com.lc.pojo.SysUsersDeptVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

@Mapper
public interface UserDao {
    //跟据username 找 角色名
    Set<String>  findRoleByName(String username);
    //跟据usernmae 找权限名
    Set<String> findPermissionByName(String name);

    SysUser findByName(String name);

    int findRowCount(@Param("username") String username);

    List<SysUsersDeptVo> findPageObject(@Param("username")String username ,
                                        @Param("startIndex")Integer startIndex ,
                                        @Param("pageSize")Integer pageSize);


    List<SysUsersDeptVo> findPageObjectByPageHelper(@Param("username")String username);

    int insertObject(SysUser entity);

    SysUsersDeptVo findObjectById(Integer userId);

    int updateObject(SysUser entity);

    int validById(@Param("id")Integer id,
                  @Param("valid")Integer valid,
                  @Param("modifiedUser")String modifiedUser);

    int updatePwd(String newPwdSec,String newSalt ,Long id);
}