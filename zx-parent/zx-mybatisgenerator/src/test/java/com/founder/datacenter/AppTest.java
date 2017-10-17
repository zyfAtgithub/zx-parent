package com.founder.datacenter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppTest {
	
	Logger logger = LoggerFactory.getLogger(AppTest.class);
	
    BeanFactory factory;

    @Before
    public void before() {
        factory = new ClassPathXmlApplicationContext("conf/spring-*.xml");
        logger.info("@Before");
    }

    @After
    public void after() {
        logger.info("@After");
    }

    @Test
    public void exitTest() {
//        UserService service = factory.getBean("userService", UserService.class);
//        assertEquals(service.existUser("mahuan2"), true);
    }
}