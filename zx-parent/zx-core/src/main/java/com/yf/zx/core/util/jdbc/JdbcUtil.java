package com.yf.zx.core.util.jdbc;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * JdbcUtil [Jdbc工具类]
 * 需在spring配置文件中配置jdbcTemplate 
 * @author zhang.yifeng
 *  2017年8月13日
 * @version 1.0.0
 * @since  1.0.0 
 * @see com.yf.zx.core.util.jdbc 
 *
 */
public class JdbcUtil {

	@Resource(name="jdbcTemplate")
	JdbcTemplate jdbcTemplate;
	
	public List<Object> findList() {
//		jdbcTemplate.queryForList(sql, args);
		return null;
	}
	
	
}
