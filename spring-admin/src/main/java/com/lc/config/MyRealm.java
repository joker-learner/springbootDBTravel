//package com.lc.config;
//
//import com.lc.mapper.UserMapper;
//import com.lc.pojo.SysUsersDeptVo;
//import org.apache.shiro.authc.*;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.apache.shiro.util.ByteSource;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.util.Set;
//
///**
// * 自定义realm（比对库）  需要自己去通过用户名查数据库，找到角色名  ， 找到权限名
// */
//
//
//public class MyRealm extends AuthorizingRealm {
//
//    @Resource
//    private UserMapper sysUserMapper;
//    /**
//     * @param principalCollection
//     * @return 获取授权数据
//     */
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        //从principalCollection 拿到用户名
//        String username = (String) principalCollection.iterator().next();
//        Set<String> roleByName = sysUserMapper.findRoleByName(username);
//        //根据username查找该用户的角色名，根据角色查找用户权限名
//        Set<String> permissionByName = sysUserMapper.findPermissionByName(username);
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.setRoles(roleByName);
//        info.setStringPermissions(permissionByName);
//
//        return info;
//    }
//
//    /**
//     * @param authenticationToken
//     * @return
//     * @throws AuthenticationException 获取认证信息
//     */
//    @Override
//    //获取用户信息存在  AuthenticationToken authenticationToken
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
//        String username = usernamePasswordToken.getUsername();
//        SysUsersDeptVo user = sysUserMapper.findByName(username);
//        //如果password_salt 盐字段有值还需将盐加入AuthenticationInfo构造器
//        AuthenticationInfo info =
//                new SimpleAuthenticationInfo(username, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
//        return info;
//    }
//
//    @Override
//    public String getName() {
//        return "myreal";
//    }
//}