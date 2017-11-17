package com.yf.zx.web.webservice.test;

import javax.jws.WebService;

@WebService(endpointInterface = "com.yf.zx.web.webservice.test.Hello")
public class HelloImp implements Hello {

	public String say(String name) {
		return "hello," + name;
	}

}