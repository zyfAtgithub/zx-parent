package com.yf.zx.web.webservice.test;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface Hello {
	public String say(@WebParam(name = "name") String name);
}
