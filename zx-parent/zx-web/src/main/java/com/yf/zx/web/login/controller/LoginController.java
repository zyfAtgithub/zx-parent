package com.yf.zx.web.login.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yf.zx.biz.sys.user.service.UserService;
import com.yf.zx.core.util.conf.SysPropertyConf;
import com.yf.zx.web.shiro.exception.CaptchaException;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	UserService userService;
	
	@Autowired
	SysPropertyConf conf;
	
	@RequestMapping(value="")
	public String login(HttpServletRequest req, Model model) {
        String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
        req.setAttribute("vertifyCodeEnabled", conf.getProperty("vertifyCode.Enabled"));  
        String error = null;
        if(CaptchaException.class.getName().equals(exceptionClassName)) {
            error = "验证码错误！";
        } else if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误！";
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误！";
        } else if (LockedAccountException.class.getName().equals(exceptionClassName)) {
        	error = "账号已锁定！";
		} else if (ExcessiveAttemptsException.class.getName().equals(exceptionClassName)) {
			error = "错误次数达到上限，请稍后再试！";
		} else if(exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        model.addAttribute("error", error);
        
		return "login/login";
	}

}
