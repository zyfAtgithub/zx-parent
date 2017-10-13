package com.yf.zx.web.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.code.kaptcha.Constants;
import com.yf.zx.biz.sys.user.service.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value="")
	public String login() {
		
		return "login/login";
	}

	@RequestMapping(value="validateLogin")
	public String validateLogin(String userName, String password, String vertifyCode,
			HttpServletRequest request, Model model) {
		System.out.println("用户名：" + userName);
		System.out.println("密码：" + password);
		System.out.println("验证码：" + vertifyCode);
		
		model.addAttribute("userName", userName);
		model.addAttribute("password", password);
		
		HttpSession session = request.getSession();
		String sessionCode = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);  
		if (!sessionCode.equalsIgnoreCase(vertifyCode)) {
			model.addAttribute("msg", "验证码错误！");
		}
		else {
			if (!userService.existUser(userName)) {
				model.addAttribute("msg", "用户名或密码错误！");
			}
//			if ("admin".equals(userName) && "admin".equals(password)) {
//				model.addAttribute("msg", "");
//			}
//			else {
//				model.addAttribute("msg", "用户名或密码错误！");
//			}
		}
		return "login/login";
	}
	
	
}