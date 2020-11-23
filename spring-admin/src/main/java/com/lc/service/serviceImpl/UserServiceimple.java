package com.lc.service.serviceImpl;

import com.lc.common.annotation.RequiredLog;
import com.lc.mapper.RoleUserDao;
import com.lc.mapper.UserDao;
import com.lc.pojo.PageObject;
import com.lc.pojo.SysUser;
import com.lc.pojo.SysUsersDeptVo;
import com.lc.service.UserService;
import com.lc.utils.ServiceException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceimple implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleUserDao roleUserDao;

//    @Autowired

    @Override
    public void login(String username, String pass, boolean isRememberMe) {
        Subject subject = SecurityUtils.getSubject();
//        SecurityUtils.setSecurityManager();
        UsernamePasswordToken token = new UsernamePasswordToken(username, pass);
        if (isRememberMe) {
            token.setRememberMe(true);
        }

        try {
            subject.login(token);
            System.out.println("登录成功。。");
        } catch (Exception e) {
//            System.out.println(e.getMessage());
            throw new ServiceException("登录失败，密码或账号错误");
        }
    }
    @RequiredLog(operation = "分页查询用户信息")
    @Override
    public PageObject<SysUsersDeptVo> findPageObject(String username, Integer pageCurrent) {
        if (pageCurrent == null || pageCurrent < 1) {
            throw new IllegalArgumentException("当前页码不正确");
        }

        PageObject<SysUsersDeptVo> pageObject = new PageObject<>();
        Integer rowCount = userDao.findRowCount(username);
        if (rowCount == 0)
            throw new ServiceException("系统没有查到对应记录");

        Integer pageSize = 5;
        Integer pageCount = (rowCount - 1) / pageSize + 1;
        Integer startIndex = (pageCurrent - 1) * 5;
        List<SysUsersDeptVo> usersList = userDao.findPageObject(username, startIndex, pageSize);
        pageObject.setPageCount(pageCount);              //一共多少页
        pageObject.setPageCurrent(pageCurrent);     //当前页
        pageObject.setRecords(usersList);           //查出来的数据
        pageObject.setRowCount(rowCount);               //记录条数
        pageObject.setPageSize(pageSize);
        return pageObject;
    }
//    @Override
//    public PageObject<SysUsersDeptVo> findPageObject(String username, Integer pageCurrent){
//        if (pageCurrent == null || pageCurrent <1){
//            throw new IllegalArgumentException("页码不合法");
//        }
//        int pageSize = 3;
//        /**
//         * PageHelper 分页
//         */
//        Page<SysUsersDeptVo> page = PageHelper.startPage(pageCurrent, pageSize);
//        List<SysUsersDeptVo> records = userDao.findPageObjectByPageHelper(username);
//        System.out.println("总页数" + page.getPageNum());
//        return new PageObject<SysUsersDeptVo>((int)page.getTotal(), records, page.getPageNum(), pageSize, pageCurrent);
//    }//总记录数                         //总页数

    @Override
    public int saveObject(SysUser entity, Integer[] roleIds) {
        long start = System.currentTimeMillis();
//        log.info("start:" + start);
        //1.参数校验
        if (entity == null)
            throw new IllegalArgumentException("保存对象不能为空");
        if (StringUtils.isEmpty(entity.getUsername()))
            throw new IllegalArgumentException("用户名不能为空");
        if (StringUtils.isEmpty(entity.getPassword()))
            throw new IllegalArgumentException("密码不能为空");
        if (roleIds == null || roleIds.length == 0)
            throw new IllegalArgumentException("至少要为用户分配角色");

        //2.保存用户自身信息
        //2.1对密码进行加密
        String sourcePwd = entity.getPassword();
        String salt = UUID.randomUUID().toString();
        SimpleHash sh = new SimpleHash(//Shiro框架
                "MD5",//algorithmName 算法
                sourcePwd,//原密码
                salt, //盐值
                1);//hashIterations表示加密次数
        entity.setSalt(salt);
        entity.setPassword(sh.toHex());
        int rows = userDao.insertObject(entity);

        //3.保存用户角色关系数据
        roleUserDao.insertObjects(entity.getId(), roleIds);
        long end = System.currentTimeMillis();
//        log.info("end:" + end);
//        log.info("total time :" + (end - start));
        //4.返回结果
        return rows;
    }

    @Override
    public Map<String, Object> findObjectById(Integer userId) {
        if (userId == null || userId <= 0)
            throw new IllegalArgumentException("参数数据不合法,userId=" + userId);
        //2.业务查询
        SysUsersDeptVo user = userDao.findObjectById(userId);
        if (user == null)
            throw new ServiceException("此用户已经不存在");
        List<Integer> roleIds = roleUserDao.findRoleIdsByUserId(userId);
        //3.数据封装
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("roleIds", roleIds);
        return map;
    }

    @Override
    public int updateObject(SysUser entity, Integer[] roleIds) {
        if (entity == null)
            throw new IllegalArgumentException("保存对象不能为空");

        if (StringUtils.isEmpty(entity.getUsername()))
            throw new IllegalArgumentException("用户名不能为空");

        if (roleIds == null || roleIds.length == 0)
            throw new IllegalArgumentException("必须为其指定角色");

        //其它验证自己实现，例如用户名已经存在，密码长度，...
        //2.更新用户自身信息
        int rows = userDao.updateObject(entity);

        //3.保存用户与角色关系数据
        roleUserDao.deleteObjectsByUserId(entity.getId());

        roleUserDao.insertObjects(entity.getId(), roleIds);

        //4.返回结果
        return rows;
    }
    @RequiredLog(operation = "禁用启用")//此注解描述的方法为日志切入点方法
    @RequiresPermissions("sys:user:update")
    @Override
    public int validById(Integer id, Integer valid) {
        if (id == null || id <= 0)
            throw new ServiceException("参数不合法,id=" + id);

        if (valid == null || (valid != 1 && valid != 0))

            throw new ServiceException("参数不合法,valid=" + valid);

        //2.执行禁用或启用操作(admin为后续登陆用户）
        int rows = userDao.validById(id, valid, "admin");

        //3.判定结果,并返回
        if (rows == 0)
            throw new ServiceException("此记录可能已经不存在");
        return rows;
    }

    @Override
    public int upudatePwd(String pwd, String newPwd, String cfgPwd) {
        //1、先判断改密码与数据库中密码相同不（md5+salt 加密后与数据库密码对比）
        Subject subject = SecurityUtils.getSubject();
        SysUser principal = (SysUser) subject.getPrincipal();
        String oldPawd = principal.getPassword();
        String oldSalt = principal.getSalt();
        Integer id = principal.getId();
        SimpleHash simpleHash = new SimpleHash("md5", pwd, oldSalt);
        if(oldPawd.equals(simpleHash.toHex())){   //与密码验证成功
            String newPwdSec = simpleHash.toHex();
            String newSalt = UUID.randomUUID().toString();
           int i = userDao.updatePwd(newPwdSec, newSalt , Long.valueOf(id));
        }

        return 1;
    }
}
