package com.yf.zx.core.util.bigdata.hbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.util.Bytes;

public class HBaseUtil {
    public static Configuration configuration;
    static {
        configuration = HBaseConfiguration.create();
        configuration.set("hbase.rootdir", "hdfs://master:9000/hbase");
        configuration.set("hbase.zookeeper.quorum", "10.0.0.150,10.0.0.151,10.0.0.152");
        //System.setProperty("hadoop.home.dir", "F:\\stu\\bigdata\\hadoop-2.7.1");
    }

    public static void main(String[] args) throws Exception {
        String tableName="student1sss1";
        String[] family={"lie01","lie02"};
        createTable(tableName, family);
    }
    public static void createTable(String tableName, String[] family) throws Exception {
        Connection connection = ConnectionFactory.createConnection(configuration);
        HBaseAdmin admin = (HBaseAdmin) connection.getAdmin();
//        HBaseAdmin admin=new HBaseAdmin(configuration);
        HTableDescriptor desc =new HTableDescriptor(TableName.valueOf(tableName));
        for(int i=0;i<family.length;i++){
            desc.addFamily(new HColumnDescriptor(family[i]));
            System.out.println("11111111111"+family[i]);
        }
        if(admin.tableExists(tableName)){
            System.out.println("表已经存在，别瞎输行吗");
//          System.exit(0);
        }else{
            admin.createTable(desc);
            System.out.println("表创建成功");
        }
    }

}
