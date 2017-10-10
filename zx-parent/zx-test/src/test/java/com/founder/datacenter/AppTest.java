package com.founder.datacenter;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yf.zx.user.model.User;
import com.yf.zx.user.service.PeopleService;
import com.yf.zx.user.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
    "classpath:conf/spring-*.xml"
})
public class AppTest {
	
	Logger logger = LoggerFactory.getLogger(AppTest.class);
	
	@Autowired
	UserService userService;

    @Autowired
    PeopleService peopleService;
    
    @Before
    public void before() {
        logger.info("@Before");
    }

    @After
    public void after() {
        logger.info("@After");
    }

    @Test
    public void exitTest() {
        assertEquals(userService.existUser("AppTest添加用户"), true);
    }

    @Test
    public void addUser() {
    	User user = new User();
    	user.setName("AppTest添加用户");
    	user.setPassword("123");
    	user.setLastlogintime(new Date());
    	peopleService.addUser(user);
		throw new RuntimeException("AppTest 抛出的异常");
    }
}