package com.yf.zx.web.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
    "classpath:conf/applicationContext.xml",
    "classpath:conf/spring-mybatis.xml",
    "classpath:conf/spring-shiro-web.xml",
    "classpath:conf/spring-mvc.xml"
})
public class AppTest {

	@Value("${vertifyCode.Enabled}")
	private String vartifyCodeEnabled;
	
	@Test
	public void testReadPropeties() {
		System.out.println(vartifyCodeEnabled);
	}
}
