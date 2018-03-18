package com.yf.zx.web.job;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.yf.zx.core.util.common.DateUtils;

@Service("callWsTask")
public class CallWsTask {
	
	public String TASK_TIME_FLAG;

	public String TASK_NAME = "";

	public boolean openFlag = true;
	
	/**
	 * Logger for this class
	 */
	private static final Logger log = Logger.getLogger(CallWsTask.class);

	public void runTask() {
		TASK_NAME = "[调用远程webservice]";
        TASK_TIME_FLAG = "[" + DateUtils.getNowTimeStr(DateUtils.DATETIME_FORMAT2) + "]";  
      
        // 判断任务定时器启动标志是否为正在启动
        if (openFlag) {
        	openFlag = false;
            try {	
            	
            	System.out.println(DateUtils.getNowTimeStr(DateUtils.DATETIME_FORMAT2)+" "+TASK_NAME+"-开始");
            	log.error(DateUtils.getNowTimeStr(DateUtils.DATETIME_FORMAT2)+" "+TASK_NAME+"-开始"); 
            	long begin = System.currentTimeMillis();

            	//业务代码
            	
            	long end=System.currentTimeMillis();           
            	System.out.println(DateUtils.getNowTimeStr(DateUtils.DATETIME_FORMAT2)+" "+TASK_NAME+"-结束[耗时："+(end-begin)/1000+"秒]");
            	log.error(DateUtils.getNowTimeStr(DateUtils.DATETIME_FORMAT2)+" "+TASK_NAME+"-结束[耗时："+(end-begin)/1000+"秒]");
         
            }
            catch (Exception ex) {
            	ex.printStackTrace();
            	System.out.println(DateUtils.getNowTimeStr(DateUtils.DATETIME_FORMAT2)+" "+TASK_NAME+"任务发生异常："+ex.getMessage());
                if (log.isEnabledFor(Level.ERROR)) {
                    log.error(DateUtils.getNowTimeStr(DateUtils.DATETIME_FORMAT2)+" "+TASK_NAME+"任务发生异常："+ex.getMessage());
                }
                throw new RuntimeException(ex);
            }
            finally {
            	openFlag = true;
            }
        }
        else {
            if (log.isDebugEnabled()) {
            	 log.debug(""+TASK_NAME+"正在执行，直接跳出本次任务执行" + TASK_TIME_FLAG);
            }
        }
	}

}
