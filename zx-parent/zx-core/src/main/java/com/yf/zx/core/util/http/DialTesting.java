package com.yf.zx.core.util.http;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.yf.zx.core.util.common.DateUtils;

import net.sf.json.JSONObject;

/**
 * 拨测程序
 * @author Administrator
 *
 */
public class DialTesting implements Runnable {

	public static final String[] URL_LIST = {
			"http://cdntest.ctdns.net/"
	};

	public static void main(String[] args) {
    	DialTesting task = new DialTesting();
		String prefix = DateUtils.getNowTimeStr("yyyyMMddHHssS");
		for (int i = 1; i <= 1; i++) {
			Thread th = new Thread(task, prefix + "-" + i + ".txt");
			th.start();
		}
	}

	public void visit(String fileName) {
		//每次任务执行时，拨测1000次
		String currThreadIndex = fileName.split("-")[1].substring(0, fileName.split("-")[1].length() - 4);
		List<String> lines = new ArrayList<String>();
//		for (String url : URL_LIST) {
			int i = 1;
			int count200Ok = 0;
			int countOther = 0;
			while (i <= 1000) {
				JSONObject res = null;
					res = HttpclientUtil.get(URL_LIST[0], 1000, 2000);
					if (res.getString("statusCode").equals("200")) {
						count200Ok++;
					}
					else if (res.getString("statusCode").equals("999")) {
						countOther++;
					}
					lines.add("线程" + currThreadIndex + "（" + i + "）-->" + "\t" + res.getString("begin") + "\t" + res.getString("end") + "\t" + URL_LIST[0] + "\t"
					+ res.getString("cost") + "\t" + res.getString("statusCode"));
					System.out.println(lines.get(lines.size() - 1));
					i++;
					
					//访问完后，sleep 10ms
//					try {
//						Thread.sleep(10);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
			}
//		}
		try {
			lines.add("统计：count:[" + (i - 1) +"]" + ", 200 OK count:[" + (count200Ok) + "], " 
					+ "other count:[" + (countOther) +"]");
			FileUtils.writeLines(new File("d:/dialtest/" + fileName), lines);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		visit(Thread.currentThread().getName());
	}
}
