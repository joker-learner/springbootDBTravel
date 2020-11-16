//package com.lc.config;//package com.lc.config;
//
//import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
//import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
//import org.apache.shiro.mgt.DefaultSecurityManager;
//import org.apache.shiro.realm.jdbc.JdbcRealm;
//import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.CookieRememberMeManager;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.apache.shiro.web.servlet.SimpleCookie;
//import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
//import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class ShireConfi {
//
//
//    @Bean
//    public MyRealm myRealm(){
//
//        return new MyRealm();
//    }
//    @Bean //加密规则,返给realm
//    public HashedCredentialsMatcher getHashedCredentialsMatcher() {
//        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
//        matcher.setHashAlgorithmName("md5");
//        matcher.setHashIterations(1);               //一次hash
//        return matcher;
//    }
//
//    @Bean   //开启shiro注解 @RequirePermission
//    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager defaultWebSecurityManager) {
//        AuthorizationAttributeSourceAdvisor advisor =
//                new AuthorizationAttributeSourceAdvisor();
//        advisor.setSecurityManager(defaultWebSecurityManager);
//        return advisor;
//    }
//
//    @Bean   //让shiro的注解得到加载
//    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){
//        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator
//                = new DefaultAdvisorAutoProxyCreator();
//        advisorAutoProxyCreator.setProxyTargetClass(true);
//        return advisorAutoProxyCreator;
//    }
//
//    @Bean
//    public ShiroDialect shiroDialect() {
//        return new ShiroDialect();
//    }
//
//    @Bean   //spring整合比对库，通过shiro规定的表名，格式意思就是它自己去查数据库找 找到角色名，权限名
//    public JdbcRealm jdbcRealm(DataSource dataSource, HashedCredentialsMatcher matcher) {
//        JdbcRealm jdbcRealm = new JdbcRealm();
//        jdbcRealm.setCredentialsMatcher(matcher);
//        jdbcRealm.setDataSource(dataSource);
//        jdbcRealm.setPermissionsLookupEnabled(true);
//        return jdbcRealm;
//    }
//
//    @Bean    //session管理注入bean并放入defaultSecurityManager
//    public DefaultWebSessionManager getDefaultWebSessionManager() {
//        DefaultWebSessionManager webSessionManager = new DefaultWebSessionManager();
//        webSessionManager.setGlobalSessionTimeout(15 * 10000);
//        return webSessionManager;
//    }
//
////    @Bean
////    public CookieRememberMeManager getCookieRememberMeManager() {
////        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
////        SimpleCookie simpleCookie = new SimpleCookie();
////        simpleCookie.setMaxAge(30 * 24 * 60 * 60);
////        cookieRememberMeManager.setCookie(simpleCookie);
////        return cookieRememberMeManager;
////    }
//
//    @Bean  //JdbcRealm jdbcRealm
//    public DefaultWebSecurityManager defaultSecurityManager(MyRealm myRealm) {
//        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
////        defaultSecurityManager.setCacheManager(ehCacheManager);
////        defaultSecurityManager.setRealm(jdbcRealm);
//        defaultSecurityManager.setRealm(myRealm);
//        defaultSecurityManager.setSessionManager(getDefaultWebSessionManager());
////        defaultSecurityManager.setRememberMeManager(getCookieRememberMeManager());
//        return defaultSecurityManager;
//    }
//
//    @Bean
//    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultSecurityManager defaultSecurityManager) {
//        ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();
//        filter.setSecurityManager(defaultSecurityManager);
//        Map<String, String> filterMap = new HashMap<>();  //拦截规则
//        //map里添加  键：是访问路径  ， 值：是权限值  预定值
//        // anon   表示未认证可以访问
//        // user   表示记住我可以访问
//        // authc  表示认证可以访问
//
//        filterMap.put("/", "anon");  //anon  匿名可以访问
//        filterMap.put("/index", "anon");
//        filterMap.put("/login", "anon");
//        filterMap.put("/static/**", "anon");
//        filterMap.put("/user/**", "anon");
//        filterMap.put("/menu/**" , "anon");
//        filterMap.put("/log/**" , "anon");
//        filterMap.put("/templates/**", "anon");
////        filterMap.put("/torigister", "anon");
//        filterMap.put("/**", "authc");  //登录用户可以访问
//        filterMap.put("/tobackndex", "anon");
//        filterMap.put("/doIndexUI", "anon");
//
//        //点击退出后 自动跳的界面
//        filterMap.put("/logout", "logout");
//        //拥有sys:c:add 权限才能访问c_add.html 界面
////        filterMap.put("/cangku/list", "perms[sys:c:save]");
//        //登录界面
//        filter.setLoginUrl("login.html");
//        //如果没有权限则调到
//        filter.setUnauthorizedUrl("/tolessparmis");
//        filter.setFilterChainDefinitionMap(filterMap);
//        filter.setLoginUrl("login.html");
//
//        return filter;
//    }
//}
