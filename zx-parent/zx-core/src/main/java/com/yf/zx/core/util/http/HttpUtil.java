package com.yf.zx.core.util.http;

import javax.servlet.http.HttpServletRequest;

public class HttpUtil {

	
	/** 
     * 判断是否是  Ajax 请求 
     * @param request 
     * @return 
     */  
    public static boolean isAjax(HttpServletRequest request) {  
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));  
    }  
}
