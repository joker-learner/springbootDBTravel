package com.lc.service.serviceImpl;

import com.lc.mapper.UserMapper;
import com.lc.pojo.PageObject;
import com.lc.pojo.SysUsersDeptVo;
import com.lc.service.UserService;
import com.lc.utils.ServiceException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceimple implements UserService {

    @Autowired
    private UserMapper userMapper;

//    @Autowired

    @Override
    public void login(String username, String pass) {
        Subject subject = SecurityUtils.getSubject();
//        SecurityUtils.setSecurityManager();
        UsernamePasswordToken token = new UsernamePasswordToken(username, pass);
        try {
            subject.login(token);
            System.out.println("登录成功。。");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ServiceException("登录失败，密码或账号错误");
        }
    }

    @Override
    public PageObject<SysUsersDeptVo> findPageObject(String username, Integer pageCurrent) {
        if (pageCurrent == null || pageCurrent < 1) {
            throw new IllegalArgumentException("当前页码不正确");
        }

        PageObject<SysUsersDeptVo> pageObject = new PageObject<>();
        Integer rowCount = userMapper.findRowCount(username);
        if (rowCount == 0)
            throw new ServiceException("系统没有查到对应记录");

        Integer pageSize = 5;
        Integer pageCount = (rowCount - 1) / pageSize + 1;
        Integer startIndex = (pageCurrent - 1) * 5;
        List<SysUsersDeptVo> usersList = userMapper.findPageObject(username, startIndex, pageSize);

        pageObject.setPageCount(pageCount);              //一共多少页
        pageObject.setPageCurrent(pageCurrent);     //当前页
        pageObject.setRecords(usersList);           //查出来的数据
        pageObject.setRowCount(rowCount);               //记录条数
        pageObject.setPageSize(pageSize);

        return pageObject;
    }
}
