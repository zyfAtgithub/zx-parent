package com.yf.zx.base.hashmap;

import java.util.HashMap;

public class Apple {
	private String color;

	public Apple(String color) {
		this.color = color;
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof Apple))
			return false;
		if (obj == this)
			return true;
		return this.color == ((Apple) obj).color;
	}

	public int hashCode() {
		return this.color.length();
	}

	public static void main(String[] args) {
		// Apple a1 = new Apple("green");
		// Apple a2 = new Apple("red");
		//
		// // hashMap stores apple type and its quantity
		// HashMap m = new HashMap();
		// m.put(a1, 10);
		// m.put(a2, 20);
		//
		// System.out.println(a1.hashCode());
		//
		// Apple a3 = new Apple("green");
		// System.out.println(a3.hashCode());
		// System.out.println(m.get(new Apple("green")));
		// System.out.println(m.get(new Apple("green")));

		String str = new String("Test");
		System.out.println(str.hashCode());
		System.out.println(testGetHashcodeByCharArray(str));
	}

	//char[0] + (31 * char[0] + char[1]) + (31 * (31 + char[0] + char[1]) + char[2])
	
	public static int testGetHashcodeByCharArray(String str) {
		int h = 0;
		int len = str.length();
		int hash = 0;
		if (h == 0 && len > 0) {
			int off = 0;
			char[] val = str.toCharArray();
			for (int i = 0; i < len; i++) {
				h = 31 * h + val[off++];
			}
			hash = h;
		}
		return h;
	}
}