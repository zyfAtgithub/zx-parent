package org.yf.zx.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.engine.impl.ExtendedSqlMapClient;
import com.ibatis.sqlmap.engine.mapping.statement.MappedStatement;
import com.ibatis.sqlmap.engine.scope.SessionScope;
import com.ibatis.sqlmap.engine.scope.StatementScope;

@SuppressWarnings("deprecation")
public class JdbcUtil4Hawq {

	protected Logger logger = LoggerFactory.getLogger(JdbcUtil4Hawq.class);

	private JdbcTemplate jdbcTemplate4Hawq;

	private SqlMapClient sqlMapClient4Hawq;

	/**
	 * 根据参入对象动态生成sql，并将参数Map转为数组
	 * 
	 * @param sqlId
	 *            在sql-map中保存的sql语句的id
	 * @param paraMap
	 *            传入参数Map必须为LinkedHashMap，其插入顺序应与iBatis配置文件中的SQL查询条件顺序一致
	 * @return Map 返回Map包括两部分，一个是key为 “SQL”的sql语句，一个是key为“PARAMS”的参数数组
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Map getDynamicStatement(String sqlId, Map<String, Object> paraMap) {
		if (sqlMapClient4Hawq == null) {
			logger.error("No IBATIS sqlMapClient setted!");
			throw new BasalException(BasalException.ERROR, "IBATIS_NOT_FIND");
		}
		Map map = new HashMap();
		String dynamicSql = null;
		ExtendedSqlMapClient extendedSqlMapClient = (ExtendedSqlMapClient) sqlMapClient4Hawq;
		MappedStatement mappedStatement = extendedSqlMapClient.getMappedStatement(sqlId);
		if (mappedStatement != null) {

			SessionScope sessionScope = new SessionScope();
			sessionScope.incrementRequestStackDepth();
			StatementScope statementScope = new StatementScope(sessionScope);
			statementScope.setStatement(mappedStatement);
			dynamicSql = mappedStatement.getSql().getSql(statementScope, paraMap);

		}

		// 处理参数，将其变为数组
		if (paraMap != null) {
			Object[] params = new Object[paraMap.keySet().size()];
			Iterator<String> keyIt = paraMap.keySet().iterator();
			int i = 0;
			while (keyIt.hasNext()) {
				String key = keyIt.next();
				params[i] = paraMap.get(key);
				String id = ":" + key;
				dynamicSql = dynamicSql.replaceAll(id, "?");
				i++;
			}
			map.put("PARAMS", params);
			map.put("SQL", dynamicSql);
		}
		return map;
	}

	
	/**
	 * 根据iBatis配置翻页查询
	 * 
	 * @param sqlId
	 *            在sql-map中保存的sql语句的id
	 * @param page
	 *            分页对象，需要设置分页大小，页数
	 * @param paraMap
	 *            传入参数Map必须为LinkedHashMap，其插入顺序应与iBatis配置文件中的SQL查询条件顺序一致
	 * @return Pagination 查询结果，数据集保存在对象的list中，总记录数保存在totalCnt中
	 */
	public Integer findCount(String sql) {
		int totalCount = 0;
		try {
			// 查找总记录数
			String countSql = this.sqlCountRows(sql);
			totalCount = jdbcTemplate4Hawq.queryForObject(countSql, Integer.class);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e.getCause());
			throw new BasalException(BasalException.ERROR, "JDBC_PAGE_ERROR", e.getCause());
		}
		return totalCount;
	}

	/**
	 * 简单的去除order by语句，并生成计数语句<br>
	 * 注：在sql语句中如果有排序，排序关键字需要设为"order by"
	 * 
	 * @param querySql
	 *            查询语句
	 * @return String 查询总记录数的语句
	 */
	private String sqlCountRows(String querySql) {
		int pos = querySql.toLowerCase().lastIndexOf("order by");
		if (pos == -1)
			return "select count(*) from (" + querySql + ") t";
		else {
			return "select count(*) from (" + querySql.substring(0, pos) + ") t";
		}
	}

	/**
	 * 根据SQL查找指定位置的记录集，返回为实体类对象列表
	 * 
	 * @param querySql
	 *            查询的sql语句
	 * @param startRow
	 *            查询记录的起始
	 * @param pageSize
	 *            分页数
	 * @param params
	 *            查询参数
	 * @return List<Map<String,Object>>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List findListBySizeAndSql(Class<?> clz, String querySql, final int startRow, final int pageSize) {
		try {
			String pageSql = preparePageSql(querySql, startRow, pageSize);
			return jdbcTemplate4Hawq.query(pageSql, new BeanPropertyRowMapper(clz));
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e.getCause());
			throw new BasalException(BasalException.ERROR, "JDBC_LIST_ERROR", e.getCause());
		}
	}

	/**
	 * 分页Sql准备
	 * 
	 * @author zhang.yifeng
	 * @param querySql
	 * @param startRow
	 * @param pageSize
	 * @return
	 */
	private String preparePageSql(String querySql, final int startRow, final int pageSize) {
		StringBuffer pageSql = new StringBuffer("select t.* from (");
		pageSql.append(querySql).append(") t limit ").append(pageSize + " OFFSET ").append(startRow + "");
		return pageSql.toString();
	}

	public JdbcTemplate getJdbcTemplate4Hawq() {
		return jdbcTemplate4Hawq;
	}

	public void setJdbcTemplate4Hawq(JdbcTemplate jdbcTemplate4Hawq) {
		this.jdbcTemplate4Hawq = jdbcTemplate4Hawq;
	}

	public SqlMapClient getSqlMapClient4Hawq() {
		return sqlMapClient4Hawq;
	}

	public void setSqlMapClient4Hawq(SqlMapClient sqlMapClient4Hawq) {
		this.sqlMapClient4Hawq = sqlMapClient4Hawq;
	}
}
