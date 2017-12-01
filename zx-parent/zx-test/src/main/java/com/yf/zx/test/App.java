package com.yf.zx.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hello world!
 *
 */
public class App {

	private static void parseDate() throws Exception {
		String viewtime = "27/Nov/2017:09:58:26 +0800";

		Date time = null;
		// Z 对于格式化来说，使用 RFC 822 4-digit 时区格式 ,Locale.US表示使用了美国时间
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z", Locale.US);

		time = sdf.parse(viewtime);

		SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");

		viewtime = sdf2.format(time);
		System.out.println(time);
		System.out.println(viewtime);
	}

	public static void main(String[] args) throws Exception {
		parseDate();
		String line = "106.39.160.116 26599 [27/Nov/2017:09:58:26 +0800] \"live.aishang.ctlcdn.com\"  \"http://219.147.68.163/live.aishang.ctlcdn.com/00000110240308_1/playlist.m3u8?CONTENTID=00000110240308_1\" 302 154 GET HTTP/1.1 36.110.146.52 80  \"-\"  \"curl/7.29.0\"  -  0.000   HIT T00003 71ad40dfb4ec4d488c9b76861f7efb06";
		
		String regex = "(\\S+)\\s+(\\d+)\\s+\\[(.+)\\]\\s+\"(.+)\"\\s+\"(.+)\"\\s+(\\d+)\\s+(\\d+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\d+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)";
		Pattern pattern = Pattern.compile(regex);
		
		Matcher match = pattern.matcher(line);
		
		if (match.find()) {
			int groupCnt = match.groupCount();
			System.out.println("分组个数：" + groupCnt);
			for (int i = 1; i <= groupCnt; i++) {
				System.out.println("match.group("+i+"):"+ match.group(i) + ",length:" + match.group(i).length());
			}
		}
		else {
			System.out.println("NO MATCH");
		}
	}
}
