package spring.druid.junitTest;  
  
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  
  
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(value = { "classpath:spring.xml" })  
public class SpringDruidJunitTest {  
    
	@Autowired  
    private JdbcTemplate jdbcTemplate;  

//    @Test  
    public void testQuery() {  
    	String sql = "SELECT\r\n" + 
    			" count(1)\r\n" + 
    			" FROM\r\n" + 
    			" 	videolog_p\r\n" + 
    			" WHERE \r\n" + 
    			"  1 = 1\r\n" + 
    			" and logtimestamp >= 1505990772.000\r\n" + 
    			" and logdate >= '2017-09-21 18:00:00' \r\n" + 
    			" and logtimestamp <=1506077172.000\r\n" + 
    			" and logdate >= '2017-09-22 18:46:12'";
    	long begin = System.currentTimeMillis();
        int totalCnt = jdbcTemplate.queryForObject(sql, Integer.class);  
        long end = System.currentTimeMillis();
        System.out.println("cost:" + (end - begin) / 1000 + "秒");  
        System.out.println(totalCnt);  
    }  

//    @Test
    public void qryMysql() {
    	String sql = "select * from ism_seq where no_type=?";
    	//IsmSeq
    	List<IsmSeq> list = jdbcTemplate.query(sql, new Object[] {"CD"}, new int[] {Types.VARCHAR}, new RowMapper<IsmSeq>() {
    		@Override
    		public IsmSeq mapRow(ResultSet rs, int rowNum) throws SQLException {
    			return new IsmSeq();
    		}
		});
    	System.out.println(list.size());
    }

//    @Test
    public void qryPostGres() {
    	String sql = "select * from ism_baseinfo where type=?";
    	//IsmSeq
    	List<BaseInfo> list = jdbcTemplate.query(sql, new Object[] {"0"}, new int[] {Types.VARCHAR}, new RowMapper<BaseInfo>() {
    		@Override
    		public BaseInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
    			return new BaseInfo();
    		}
    	});
    	System.out.println(list.size());
    }

//    @Test
    public void qryPostGresAccesslog() {
    	long begin = System.currentTimeMillis();

    	String sql = "select logId,\r\n" + 
    			"	to_char(\r\n" + 
    			"		to_timestamp(logtimestamp),\r\n" + 
    			"		'YYYY-MM-DD HH24:MI:SS'\r\n" + 
    			"	) accesstime,\r\n" + 
    			"	clientip srcip,\r\n" + 
    			"	clientport srcport,\r\n" + 
    			"	regionip AS destip,\r\n" + 
    			"	backport AS destport,\r\n" + 
    			"	urlstr AS url,\r\n" + 
    			"	(\r\n" + 
    			"		CASE\r\n" + 
    			"		WHEN totservertime <= 1000 THEN\r\n" + 
    			"			1\r\n" + 
    			"		ELSE\r\n" + 
    			"			totservertime / 1000\r\n" + 
    			"		END\r\n" + 
    			"	) AS duration,\r\n" + 
    			"	partnercode AS partner,\r\n" + 
    			"	responsecode AS httpStatusCode,\r\n" + 
    			"	1 AS protocoltype from videolog_p\r\n" + 
    			"where logtimestamp >= ?\r\n" + 
    			"AND logdate >= ?\r\n" + 
    			"AND logtimestamp <= ?\r\n" + 
    			"AND logdate <= ?\r\n" + 
    			"ORDER BY logtimestamp DESC\r\n" + 
    			"LIMIT 10 OFFSET 0";
    	List<QueryAccesslog> list = jdbcTemplate.query(sql, new Object[] {"1506182400", "2017-09-24 00:00:00","1506268800","2017-09-25 00:00:00"}, new int[] {Types.VARCHAR, Types.VARCHAR,Types.VARCHAR,Types.VARCHAR}, new RowMapper<QueryAccesslog>() {
    		@Override
    		public QueryAccesslog mapRow(ResultSet rs, int rowNum) throws SQLException {
    			return new QueryAccesslog(rs);
    		}
    	});
    	long end = System.currentTimeMillis();
    	System.out.println("qryPostGresAccesslog cost:" + (end - begin) / 1000 + "秒");  

    	System.out.println("共查到" + list.size() + "条记录！");
//    	System.out.println(list);
    }

    @Test
    public void qryPostGresAccesslog2() {
    	long begin = System.currentTimeMillis();

    	String sql = "SELECT logid,\r\n" + 
    			"		to_char(to_timestamp(logtimestamp), 'YYYY-MM-DD HH24:MI:SS') accesstime,\r\n" + 
    			"		clientip srcip,\r\n" + 
    			"		clientport srcport,\r\n" + 
    			"		regionip as destip,\r\n" + 
    			"		backport as destport,\r\n" + 
    			"		urlstr as url,\r\n" + 
    			"		(case when totservertime <= 1000 THEN 1 ELSE totservertime / 1000 END) as duration,\r\n" + 
    			"		partnercode as partner,\r\n" + 
    			"		responsecode as httpStatusCode,\r\n" + 
    			"		1 as protocoltype\r\n" + 
    			"		FROM videolog_p\r\n" + 
    			"		where 1 = 1\r\n" + 
    			" and logtimestamp >= 1505990772.000\r\n" + 
    			" and logdate >= '2017-09-21 18:00:00' \r\n" + 
    			" and logtimestamp <=1506077172.000\r\n" + 
    			" and logdate >= '2017-09-22 18:46:12' \r\n" + 
    			"order by logtimestamp desc limit 10 OFFSET 0";
    	List<QueryAccesslog> list = jdbcTemplate.query(sql, new RowMapper<QueryAccesslog>() {
    		@Override
    		public QueryAccesslog mapRow(ResultSet rs, int rowNum) throws SQLException {
    			return new QueryAccesslog();
    		}
    	});
    	long end = System.currentTimeMillis();
    	System.out.println("qryPostGresAccesslog2 cost:" + (end - begin) / 1000 + "秒");  

    	System.out.println("共查到" + list.size() + "条记录！");
    }
//    @Test
//    public void qryPostGresAccesslog2() {
//    	long begin = System.currentTimeMillis();
//    	
//    	String sql = "select logId,\r\n" + 
//    			" 	to_char(\r\n" + 
//    			" to_timestamp(logtimestamp),\r\n" + 
//    			"  		'YYYY-MM-DD HH24:MI:SS'\r\n" + 
//    			"    			) accesstime,\r\n" + 
//    			"    			clientip srcip,\r\n" + 
//    			"    				clientport srcport,\r\n" + 
//    			"    				regionip AS destip,\r\n" + 
//    			"    				backport AS destport,\r\n" + 
//    			"    				urlstr AS url,\r\n" + 
//    			"    				(\r\n" + 
//    			"    					CASE\r\n" + 
//    			"    					WHEN totservertime <= 1000 THEN\r\n" + 
//    			"    						1\r\n" + 
//    			"    				ELSE\r\n" + 
//    			"    						totservertime / 1000\r\n" + 
//    			"    				END\r\n" + 
//    			"    			) AS duration,\r\n" + 
//    			"    				partnercode AS partner,\r\n" + 
//    			"    				responsecode AS httpStatusCode,\r\n" + 
//    			"    				1 AS protocoltype from videolog_p\r\n" + 
//    			"where logtimestamp >= 1505923200\r\n" + 
//    			"AND logdate >= '2017-09-24 00:00:00'\r\n" + 
//    			"AND logtimestamp <= 1506268800\r\n" + 
//    			"AND logdate <= '2017-09-25 00:00:00'\r\n" + 
//    			"ORDER BY logtimestamp DESC\r\n" + 
//    			"LIMIT 10 OFFSET 0";
//    	List<QueryAccesslog> list = jdbcTemplate.query(sql, new RowMapper<QueryAccesslog>() {
//    		@Override
//    		public QueryAccesslog mapRow(ResultSet rs, int rowNum) throws SQLException {
//    			return new QueryAccesslog();
//    		}
//    	});
//    	long end = System.currentTimeMillis();
//    	System.out.println("qryPostGresAccesslog2 cost:" + (end - begin) / 1000 + "秒");  
//    	
//    	System.out.println("共查到" + list.size() + "条记录！");
//    }
}  