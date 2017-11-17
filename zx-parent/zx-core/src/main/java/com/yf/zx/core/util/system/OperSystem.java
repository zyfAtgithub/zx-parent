package com.yf.zx.core.util.system;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.httpclient.util.DateParseException;

public class OperSystem {

	public static OperSystemType judgeSystemType() {
		try {
			System.out.println("===========操作系统是:" + System.getProperties().getProperty("os.name"));
			String osName = System.getProperty("os.name");
			if (osName == null) {
				throw new IOException("os.name not found");
			}
			osName = osName.toLowerCase();
			// match
			if (osName.indexOf("windows") != -1) {
				return OperSystemType.WINDOWS;
			} else if (osName.indexOf("linux") != -1 || osName.indexOf("sun os") != -1 || osName.indexOf("sunos") != -1
					|| osName.indexOf("solaris") != -1 || osName.indexOf("mpe/ix") != -1
					|| osName.indexOf("freebsd") != -1 || osName.indexOf("irix") != -1
					|| osName.indexOf("digital unix") != -1 || osName.indexOf("unix") != -1
					|| osName.indexOf("mac os x") != -1) {
				return OperSystemType.UNIX;
			} else if (osName.indexOf("hp-ux") != -1 || osName.indexOf("aix") != -1) {
				return OperSystemType.POSIX_UNIX;
			} else {
				return OperSystemType.OTHER;
			}

		} catch (Exception ex) {
			return OperSystemType.INIT_PROBLEM;
		}
	}
	public static void main(String[] args) throws DateParseException {
		Date d = new Date(1502431000000l);
		System.out.println(d);
	}
}
