package com.lc.config;//package com.lc.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShireConfi {


    @Bean
    public MyRealm myRealm() {

        return new MyRealm();
    }

    @Bean //加密规则,返给realm
    public HashedCredentialsMatcher getHashedCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1);               //一次hash
        return matcher;
    }

    @Bean   //开启shiro注解 @RequirePermission
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager defaultWebSecurityManager) {
        AuthorizationAttributeSourceAdvisor advisor =
                new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(defaultWebSecurityManager);
        return advisor;
    }

    @Bean   //让shiro的注解得到加载
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator
                = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    @Bean   //spring整合比对库，通过shiro规定的表名，格式意思就是它自己去查数据库找 找到角色名，权限名
    public JdbcRealm jdbcRealm(DataSource dataSource, HashedCredentialsMatcher matcher) {
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setCredentialsMatcher(matcher);
        jdbcRealm.setDataSource(dataSource);
        jdbcRealm.setPermissionsLookupEnabled(true);
        return jdbcRealm;
    }

    @Bean    //session管理注入bean并放入defaultSecurityManager
    public DefaultWebSessionManager getDefaultWebSessionManager() {
        DefaultWebSessionManager webSessionManager = new DefaultWebSessionManager();
        webSessionManager.setGlobalSessionTimeout(15 * 10000);
        return webSessionManager;
    }

//    @Bean
//    public CookieRememberMeManager getCookieRememberMeManager() {
//        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
//        SimpleCookie simpleCookie = new SimpleCookie();
//        simpleCookie.setMaxAge(30 * 24 * 60 * 60);
//        cookieRememberMeManager.setCookie(simpleCookie);
//        return cookieRememberMeManager;
//    }

    @Bean  //JdbcRealm jdbcRealm
    public DefaultWebSecurityManager defaultSecurityManager(MyRealm myRealm) {
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
//        defaultSecurityManager.setCacheManager(ehCacheManager);
//        defaultSecurityManager.setRealm(jdbcRealm);
        defaultSecurityManager.setRealm(myRealm);
        defaultSecurityManager.setSessionManager(getDefaultWebSessionManager());
//        defaultSecurityManager.setRememberMeManager(getCookieRememberMeManager());
        return defaultSecurityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultSecurityManager defaultSecurityManager) {
        ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();
        filter.setSecurityManager(defaultSecurityManager);
        Map<String, String> filterMap = new HashMap<>();  //拦截规则
        //map里添加  键：是访问路径  ， 值：是权限值  预定值
        // anon   表示未认证可以访问
        // user   表示记住我可以访问
        // authc  表示认证可以访问

        //放行static资源
        filterMap.put("*.json", "anon");
        filterMap.put("*.js", "anon");
        filterMap.put("*.css", "anon");
        filterMap.put("*.css.map", "anon");
        filterMap.put("*.png","anon");
        filterMap.put("/templates/**", "anon");
        filterMap.put("/bower_components/*","anon");
        filterMap.put("/build/*","anon");
        filterMap.put("/dist/*","anon");
        filterMap.put("/plugins/*","anon");
        //放行请求
//        filterMap.put("/dept/**","authc");
//        filterMap.put("/login", "anon");
//        filterMap.put("/user/**", "authc");
//        filterMap.put("/menu/**", "authc");
//        filterMap.put("/log/**", "authc");
//        filterMap.put("/role/**","authc");
//        filterMap.put("/doIndexUI", "anon");
//        filterMap.put("/index", "authc");
//        filterMap.put("/", "anon");  //anon  匿名可以访问
//        filterMap.put("/**", "authc");  //登录用户可以访问

        //点击退出后 自动跳的界面
        filterMap.put("/logout", "logout");
        //登录页面不拦截
        filterMap.put("/modules/pages/login.html","anon");
        //登录请求不拦截
        filterMap.put("/*","anon");
        //登录请求不拦截
        filterMap.put("/user/login","anon");
        //主页面请求不拦截
        //静态资源不拦截
        filterMap.put("/static/**","anon");
        //其他请求页面全部拦截
        filterMap.put("/*","anon");
        filter.setLoginUrl("login.html");
        //如果没有权限则调到
        filter.setUnauthorizedUrl("/tolessparmis");
        filter.setFilterChainDefinitionMap(filterMap);

        return filter;
    }
}
