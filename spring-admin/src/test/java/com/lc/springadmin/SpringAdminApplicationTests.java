package com.lc.springadmin;

import com.lc.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringAdminApplicationTests {

    @Autowired
    private UserService userServiceimple;

    @Test
    void contextLoads() {
//        PageObject<SysUsersDeptVo> pageObject = userServiceimple.findPageObject(null, 1);
//        System.out.println(pageObject);

    }

}
