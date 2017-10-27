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

import com.yf.zx.biz.sys.user.entity.User;
import com.yf.zx.biz.sys.user.service.UserService;

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
        logger.info("Before");
    }

    @After
    public void after() {
        logger.info("After");
    }

    @Test
    public void exitTest() {
        assertEquals(userService.existUser("admin"), true);
    }

    @Test
    public void selectTest() {
    	User user = userService.getUserByName("admin");
    	logger.info(user.toString());
    }
    
    @Test
    public void addUsertest() {
    	User user = new User();
    	user.setUsername("lyf");
    	user.setPassword("6168db58405a9eab7828e6a47ff73383");//123456
    	user.setSalt("28495018162ad6328783c3bde7364346");
    	userService.addUser(user);
    	System.out.println(user.getId());
    }
}