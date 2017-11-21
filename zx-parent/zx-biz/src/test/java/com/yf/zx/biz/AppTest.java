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

import com.alibaba.fastjson.JSONObject;
import com.yf.zx.biz.sys.user.entity.User;
import com.yf.zx.biz.sys.user.entity.UserVo;
import com.yf.zx.biz.sys.user.service.UserService;
import com.yf.zx.core.base.web.PageReturn;
import com.yf.zx.core.base.web.ResultReturn;

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
//        logger.info("Before");
    }

    @After
    public void after() {
//        logger.info("After");
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
    	user.setLoginname("刘明");
    	user.setUsername("lm");
    	user.setPassword("6168db58405a9eab7828e6a47ff73383");//123456
    	user.setSalt("28495018162ad6328783c3bde7364346");
    	ResultReturn ret = userService.addUser(user);
    	System.out.println(ret);
    }
    
    @Test
    public void findUserByPage() {
    	UserVo userVo = new UserVo();
    	userVo.setPage(0);
    	userVo.setRows(1);
//    	userVo.setUsername("张益峰");
//    	userVo.setLoginname("%a%");
    	userVo.setOrderBy("lastlogin_time desc");
    	
    	PageReturn<User> page = userService.findByPage(userVo);
    	String json = JSONObject.toJSONString(page);
    	
    	JSONObject jsonObject = new JSONObject();
    	jsonObject.put("code",0);
    	jsonObject.put("count",100);
    	jsonObject.put("msg","");
    	System.out.println( jsonObject.toString());
    	System.out.println( jsonObject.toJSONString());
//    	System.out.println(json);
    }
    
    
}