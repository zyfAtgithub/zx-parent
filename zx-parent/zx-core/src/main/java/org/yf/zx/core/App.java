package org.yf.zx.core;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		// 获取IoC容器中JdbcTemplate实例
		JdbcTemplate jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate4Hawq");
		long begin = System.currentTimeMillis();
		JdbcUtil4Hawq util = new JdbcUtil4Hawq();
		util.setJdbcTemplate4Hawq(jdbcTemplate);
		String sql = "SELECT\r\n" + 
				"	logid,\r\n" + 
				"	logtype,\r\n" + 
				"	cdnid,\r\n" + 
				"	commandid,\r\n" + 
				"	cnt AS commandnum,\r\n" + 
				"	srcip,\r\n" + 
				"	destip,\r\n" + 
				"	domainname AS DOMAIN,\r\n" + 
				"	illegalinfo AS CONTENT,\r\n" + 
				"	url,\r\n" + 
				"	recordtime AS gathertime,\r\n" + 
				"	snapshoturl,\r\n" + 
				"	partnercode AS partnertype\r\n" + 
				"FROM\r\n" + 
				"	ism_monitorlog_p\r\n" + 
				"WHERE\r\n" + 
				"	logtype = '2'\r\n" + 
				"AND commandsrc = '0'\r\n" + 
				"\r\n" + 
				"ORDER BY\r\n" + 
				"	logid ASC";
		int count = util.findCount(sql);
		
		long end = System.currentTimeMillis();
		System.out.println("耗时：" + (end - begin));
		System.out.println(count);
		
		ctx.close();
	}
}
