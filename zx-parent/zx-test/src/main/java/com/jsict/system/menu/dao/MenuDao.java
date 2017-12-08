package com.jsict.system.menu.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MenuDao {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
}
