package com.yf.zx.core.util.http;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.HttpHost;

import com.yf.zx.core.util.common.DateUtils;
import com.yf.zx.core.util.common.FileUtil;
import com.yf.zx.core.util.common.StringUtils;
import com.yf.zx.core.util.constants.Constants;
import com.yf.zx.core.util.ip.IPUtil;

import net.sf.json.JSONObject;

/**
 * 拨测程序
 * @author Administrator
 *
 */
public class DialTesting implements Runnable {

	protected String dayDir;
	protected long totalCnt = 0;
	protected long count200Ok = 0;
	protected long count403 = 0;
	protected long countOther = 0;
	
	int count20dr = 0;	
	protected String fileName;
	protected HttpHost host;
	
	public static int urlIndex;
	public static int batchCnt;
	public static int dialCount;
	public static long singlesleepTime;
	public static long batchsleepTime;
	private final String STOR_DIR = Constants.STOR_DIR;
	
	public DialTesting(String dayDir, String fileName, HttpHost host) {
		this.dayDir = dayDir;
		this.fileName = fileName;
		this.host = host;
	}

	private static String URL;

	//代理服务器
	private static String[] PROXY_LIST = {
//			"58.53.219.5",
//			"61.172.237.37",//
//			"61.172.237.133",//
//			"106.41.94.131",//
//			"106.41.94.139",//
//			"59.49.41.165",//
//			"59.49.41.167",//
//			"124.126.250.71",//
//			"219.153.18.157", //设备已坏
	};
	
	

	public static void main(String[] args) {
		if (args == null || args.length != 6) {
			System.err.println("请输入拨测url，代理IP列表，拨测轮次，每次任务执行时拨测的次数，单次播完休眠的时间，每批次播完休眠的时间\n[拨测url]\n[代理IP：多个以\"|\"分隔]\n[拨测轮次：0-无限制拨测，1+ 多次拨测]");
			System.exit(-1);
		}
		
		
		URL = StringUtils.convert2String(args[0]);
		String proxyList = StringUtils.convert2String(args[1]);
		
		PROXY_LIST = proxyList.split("\\|");
		
		boolean useProxy = IPUtil.validateIp(PROXY_LIST[0]);
		if (useProxy) {
			for (int i = 0; i < PROXY_LIST.length;i++) {
				if (!IPUtil.validateIp(PROXY_LIST[i])) {
					System.err.println("proxy [" + PROXY_LIST[i] + "] error");
					System.exit(-1);
				}
			}
		}
		
		int threadPoolNum = PROXY_LIST.length;
		
		batchCnt = Integer.parseInt(args[2]);
		dialCount = Integer.parseInt(args[3]);
		singlesleepTime = Long.parseLong(args[4]);
		batchsleepTime = Long.parseLong(args[5]);
		
		ExecutorService executorService;
		String dayDir = DateUtils.getNowTimeStr("yyyyMMddHHmmss");
		if (useProxy) {
			threadPoolNum = PROXY_LIST.length;
			executorService = Executors.newFixedThreadPool(threadPoolNum + 1);
			for (int i = 1; i <= threadPoolNum; i++) {
				HttpHost host = new HttpHost(PROXY_LIST[i - 1], 80);
				executorService.execute(new DialTesting(dayDir, host.getHostName() + "-" + i + ".txt", host));
			}
			executorService.shutdown(); 
		}
		else {
			threadPoolNum = 9;
			executorService = Executors.newFixedThreadPool(threadPoolNum + 1);
			for (int i = 1; i <= threadPoolNum; i++) {
				executorService.execute(new DialTesting(dayDir, i + ".txt", null));
			}
			executorService.shutdown(); 
		}
	}

	public void visit(int batchCnt) {
		//每次任务执行时，拨测1000次
		int pos1 = fileName.lastIndexOf("-");
		int pos2 = fileName.lastIndexOf(".txt");
		String currThreadIndex = fileName.substring(pos1 + 1, pos2);
		int cnt = 0;
		String begin = DateUtils.getNowTimeStr("yyyy-MM-dd HH:mm:ss.SSS");
		while (cnt < dialCount) {
			JSONObject res = null;
			cnt++;
			totalCnt++;
			res = HttpclientUtil.get(URL, host, 2000, 3000);
			if (res.getString("statusCode").equals("200")) {
				count200Ok++;
			}
			else if (res.getString("statusCode").equals("403")) {
				count403++;
			}
			else if (res.getString("statusCode").equals("999")) {
				countOther++;
			}
			
			String content = "线程" + currThreadIndex + "（第" + batchCnt + "批次，第" + cnt + "次访问）-->" + "\t" + res.getString("begin") + "\t" + res.getString("end") + "\t" + URL + "\t"
					+ res.getString("cost") + "\t" + res.getString("statusCode");
			if (null != host) {
				content += "\tproxy:" + host.getHostName();
			}
			
			System.out.println(content);
			
//			FileUtil.appentContent2File(STOR_DIR + dayDir + File.separator + fileName, content);
			
			//访问完后，sleep一下
			try {
				Thread.sleep(singlesleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		//访问完后，sleep，等待下一轮次
		try {
			Thread.sleep(batchsleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		StringBuffer buf = new StringBuffer();
		if (null != host) {
			buf.append("线程号：").append(currThreadIndex).append("\n代理Ip：" + host.getHostName());
		}
		else {
			buf.append("\n线程号：").append(currThreadIndex);
		}
		buf.append("\n开始时间：");
		buf.append(begin).append("\n结束时间：").append(DateUtils.getNowTimeStr("yyyy-MM-dd HH:mm:ss.SSS"));
		buf.append("\n第").append(batchCnt).append("批次，统计：count:[").append(totalCnt).append("]")
		.append(", 200 OK count:[").append(count200Ok).append("], 403 count:[").append(count403)
		.append("], other count:[").append(countOther).append("]");
		FileUtil.appentContent2File(STOR_DIR + dayDir + File.separator + fileName, buf.toString());
	}

	@Override
	public void run() {
		if (batchCnt == 0) {
			int i = 1;
			while (true) {
				visit(i);
				i++;
			}
		}
		else {
			int i = 1;
			while (i <= batchCnt) {
				visit(i);
				i++;
			}
		}
	}
}
