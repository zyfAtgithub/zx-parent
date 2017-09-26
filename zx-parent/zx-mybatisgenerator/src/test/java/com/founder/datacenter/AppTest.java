package com.founder.datacenter;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yf.zx.user.service.UserService;

public class AppTest {
    BeanFactory factory;

    @Before
    public void before() {
        factory = new ClassPathXmlApplicationContext("conf/spring-*.xml");
        System.out.println("@Before");
    }

    @After
    public void after() {
        System.out.println("@After");
    }

    @Test
    public void exitTest() {
        UserService service = factory.getBean("userService", UserService.class);
        assertEquals(service.existUser("mahuan2"), true);
    }
}