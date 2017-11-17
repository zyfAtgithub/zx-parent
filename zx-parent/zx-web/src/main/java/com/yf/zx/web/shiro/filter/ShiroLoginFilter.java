package com.yf.zx.web.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.AdviceFilter;

public class ShiroLoginFilter extends AdviceFilter {

	/**
	 * 在访问controller前判断是否登录，返回json，不进行重定向。
	 * 
	 * @param request
	 * @param response
	 * @return true-继续往下执行，false-该filter过滤器已经处理，不继续执行其他过滤器
	 * @throws Exception
	 */
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated() && !StringUtils.contains(httpServletRequest.getRequestURI(), "/login")) {
			String requestedWith = httpServletRequest.getHeader("X-Requested-With");
			if (StringUtils.isNotEmpty(requestedWith) && StringUtils.equals(requestedWith, "XMLHttpRequest")) {// 如果是ajax返回指定数据
				String nologin = "{\"noLogin\":true}";
				httpServletResponse.setCharacterEncoding("UTF-8");
				httpServletResponse.setContentType("application/json");
				httpServletResponse.getWriter().write(nologin);
			} else {// 不是ajax进行重定向处理
				httpServletResponse.sendRedirect("/login");
			}
			return false;
		}
		return true;
	}
}
