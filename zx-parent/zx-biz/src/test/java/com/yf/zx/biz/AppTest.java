package com.yf.zx.biz;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yf.zx.biz.user.model.User;
import com.yf.zx.biz.user.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
    "classpath:conf/spring-*.xml"
})
public class AppTest {
	
	Logger logger = LoggerFactory.getLogger(AppTest.class);
	
	@Autowired
	UserService userService;

    
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
        assertEquals(userService.existUser("mahuan2"), true);
    }

    @Test
    public void selectTest() {
    	User user = userService.getUserByName("mahuan2");
    	logger.info(user.toString());
    }
}