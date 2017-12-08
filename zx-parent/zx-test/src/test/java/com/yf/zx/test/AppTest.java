package com.yf.zx.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
    "classpath:conf/spring-mybatis.xml"
})
public class AppTest {
	Logger logger = LoggerFactory.getLogger(AppTest.class);
	
	 @Autowired
	 JdbcTemplate jdbcTemplate;
	 
	 @Test
	 public void loadMenu() {
		 String sql = "SELECT unit.id as unit_id,menu.id as menu_id,unit.sid, unit.name,unit.description,unit.perm_exp,unit.isbutton,menu.parent,menu.mrl\r\n" + 
		 		"  FROM cdn_resource.sys_menu menu, cdn_resource.security_unit unit\r\n" + 
		 		" where menu.security_units = unit.sid\r\n" + 
		 		"order by menu.id";
	 }
	 
}
