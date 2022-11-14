package com.study.ssm_total;

import com.study.pojo.User;
import com.study.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MyTest {
    @Test
    public void test(){
        ApplicationContext context
                = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) context.getBean("userServiceIml");
        User user = userService.getUserById(1);
        System.out.println(user);
    }
}
