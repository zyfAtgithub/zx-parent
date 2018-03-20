package com.yf.zx.web.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yf.zx.biz.log.login.entity.LoginLog;
import com.yf.zx.biz.log.login.service.LoginLogService;
import com.yf.zx.core.util.http.UserAgentUtils;
import com.yf.zx.core.util.ip.IPUtil;
import nl.bitwalker.useragentutils.UserAgent;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yf.zx.biz.sys.user.entity.User;
import com.yf.zx.biz.sys.user.service.UserService;
import com.yf.zx.core.util.common.DateUtils;
import com.yf.zx.core.util.constants.Constants;
import com.yf.zx.core.util.http.HttpUtil;
import com.yf.zx.web.shiro.exception.CaptchaException;
import com.yf.zx.web.shiro.token.UsernamePasswordCaptchaToken;

import java.util.Date;

public class FormAuthenticationCaptchaFilter extends FormAuthenticationFilter {
    private static final Logger logger = LoggerFactory.getLogger(FormAuthenticationCaptchaFilter.class);  

    private boolean vertifyCodeEnabled;
    
	@Autowired
    private UserService userService;

	@Autowired
	private LoginLogService loginLogService;

    @Override  
    /** 
     * 登录验证 
     */  
    protected boolean executeLogin(ServletRequest request,  
            ServletResponse response) throws Exception {  
    	UsernamePasswordCaptchaToken token = createToken(request, response);
		LoginLog loginLog = new LoginLog();
    	try {
			loginLog.setLogintime(new Date());
    		loginLog.setLoginuser(token.getUsername());
    		loginLog.setIp(IPUtil.getClientIp((HttpServletRequest) request));
			UserAgent userAgent = UserAgentUtils.getClientInfo((HttpServletRequest) request);
			loginLog.setLogindevice(userAgent.getOperatingSystem().getName());
        	if (vertifyCodeEnabled) {
        		/*图形验证码验证*/  
        		doCaptchaValidate((HttpServletRequest) request, token);  
        	}
            Subject subject = getSubject(request, response);  
            subject.login(token);//正常验证  
            logger.info(token.getUsername()+"登录成功");
			loginLog.setLoginResult("1");
			loginLogService.addLog(loginLog);
            User user = userService.getUserByName(token.getUsername());
            user.setLastloginTime(DateUtils.getNowTime());
            userService.editById(user);
            Session session = subject.getSession();
            session.setAttribute(Constants.CUR_LOGIN_USER, user);
            session.setAttribute(Constants.CUR_LOGIN_LOGID, loginLog.getId());
            return onLoginSuccess(token, subject, request, response);
        }catch (AuthenticationException e) {  
        	logger.info(token.getUsername()+"登录失败--"+e);
			loginLog.setLoginResult("0");
			loginLogService.addLog(loginLog);
            return onLoginFailure(token, e, request, response);  
        }  
    }  
  
    // 验证码校验  
    protected void doCaptchaValidate(HttpServletRequest request,  
    		UsernamePasswordCaptchaToken token) {  
		//session中的图形码字符串  
        String captcha = (String) request.getSession().getAttribute(  
                com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);  
    	//比对  
        if (captcha != null && !captcha.equalsIgnoreCase(token.getCaptcha())) {  
            throw new CaptchaException("验证码错误！");  
        }  
    }  
  
	public static final String DEFAULT_CAPTCHA_PARAM = "captcha";
	 
	private String captchaParam = DEFAULT_CAPTCHA_PARAM;
 
	public boolean isVertifyCodeEnabled() {
		return vertifyCodeEnabled;
	}

	public void setVertifyCodeEnabled(boolean vertifyCodeEnabled) {
		this.vertifyCodeEnabled = vertifyCodeEnabled;
	}

	public String getCaptchaParam() {
		return captchaParam;
	}
 
	public void setCaptchaParam(String captchaParam) {
		this.captchaParam = captchaParam;
	}

	protected String getCaptcha(ServletRequest request) {
		return WebUtils.getCleanParam(request, getCaptchaParam());
	}
 
	protected UsernamePasswordCaptchaToken createToken(ServletRequest request,
			ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);
		String captcha = getCaptcha(request);
		boolean rememberMe = isRememberMe(request);
		String host = getHost(request);
		return new UsernamePasswordCaptchaToken(username,
				password.toCharArray(), rememberMe, host, captcha);
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpReq = (HttpServletRequest)request;
		HttpServletResponse httpRsp = (HttpServletResponse)response;
		if (HttpUtil.isAjax(httpReq)) {
			//ajax请求
			String nologin = "{\"noLogin\":true}";
			httpRsp.setCharacterEncoding("UTF-8");
			httpRsp.setContentType("application/json");
			httpRsp.getWriter().write(nologin);
			return false;
		}
		return super.onAccessDenied(request, response);
	}
	
}
