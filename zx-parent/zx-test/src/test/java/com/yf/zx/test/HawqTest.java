package com.yf.zx.test;

import com.jshx.core.base.entity.IsmIllegalinfoFilterLog;
import com.jshx.core.base.entity.QueryAccesslog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:applicationContext-hawqjdbc.xml"
})
public class HawqTest {

    @Autowired
    private JdbcTemplate jdbcTemplate4Hawq;
    
    @Autowired
    private JdbcTemplate jdbcTemplate4Hawq2;

    @Test
    public void qryFileterLog() {
        String sql = "SELECT logid, logtype, cdnid, commandid, cnt as commandnum, \n" +
                " srcip, destip, domainname as domain, illegalinfo as content,  \n " +
                "url, recordtime as gathertime, snapshoturl, partnercode as partnertype \n" +
                "FROM ism_monitorlog_p \n " +
                " WHERE logtype = '3'  \n " +
                " and commandsrc = '0' \n " +
                "and logid > ? \n " +
                "order by logid asc   limit 20000 OFFSET 0";
        Object[] params = {"5807570"};
        jdbcTemplate4Hawq.setQueryTimeout(55);

        List<IsmIllegalinfoFilterLog> list = jdbcTemplate4Hawq.query(sql, params, new BeanPropertyRowMapper(IsmIllegalinfoFilterLog.class));
        System.out.println(list.size());
        if (list.size()>0) {
            System.out.println(list.get(0));
        }
    }

    @Test
    public void qryFilterLogCount() {
        String countSql = "select count(*) from ( SELECT logid, logtype, cdnid, commandid, cnt as commandnum, \n" +
                "srcip, destip, domainname as domain, illegalinfo as content, \n" +
                "url, recordtime as gathertime, snapshoturl, partnercode as partnertype \n" +
                "FROM ism_monitorlog_p \n" +
                "WHERE logtype = '3' \n" +
                "and commandsrc = '0' \n" +
                " and logid > ?) t\n";
        Object[] params = {"5807570"};
        jdbcTemplate4Hawq.setQueryTimeout(200);
        int totalCount = jdbcTemplate4Hawq.queryForObject(countSql, Integer.class,params);
        System.out.println(totalCount);
    }
    
    @Test
    public void qryAccessLogCount() {
        String countSql = "select count(*) from (SELECT logid,\n" +
                "  to_char(to_timestamp(logtimestamp), 'YYYY-MM-DD HH24:MI:SS') accesstime,\n" +
                "  clientip srcip,\n" +
                "  clientport srcport,\n" +
                "  regionip as destip,\n" +
                "  backport as destport,\n" +
                "  urlstr as url,\n" +
                "  (case when totservertime <= 1000 THEN 1 ELSE totservertime / 1000 END) as duration,\n" +
                "  partnercode as partner,\n" +
                "  responsecode as httpStatusCode,\n" +
                "  1 as protocoltype\n" +
                "  FROM videolog_p\n" +
                "  where 2 = 2\n" +
                " and logtimestamp >= 1528878599.000\n" +
                " and logdate >= '2018-06-13 16:00:00' \n" +
                " and logtimestamp <=1528878599.000\n" +
                " and partnercode = 'T00003'\n" +
                " and logdate <= '2018-06-13 16:29:59')t";
        Object[] params = {};
        jdbcTemplate4Hawq2.setQueryTimeout(600);
        int totalCount = jdbcTemplate4Hawq2.queryForObject(countSql, Integer.class,params);
        System.out.println(totalCount);
    }

}
