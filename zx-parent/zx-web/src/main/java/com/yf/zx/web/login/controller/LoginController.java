package com.yf.zx.web.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.code.kaptcha.Constants;
import com.yf.zx.biz.sys.user.entity.User;
import com.yf.zx.biz.sys.user.service.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Value("${checkCode}")
	private String checkCode;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="")
	public String login() {
		return "login/login";
	}

	@RequestMapping(value="validateLogin", method=RequestMethod.POST)
	public String validateLogin(String userName, String password, String vertifyCode,
			HttpServletRequest request, Model model) {
		System.out.println("用户名：" + userName);
		System.out.println("密码：" + password);
		System.out.println("验证码：" + vertifyCode);
		
		model.addAttribute("userName", userName);
		model.addAttribute("password", password);
		
		HttpSession session = request.getSession();
		String sessionCode = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);  
		if ("true".equals(checkCode) && !vertifyCode.equalsIgnoreCase(sessionCode)) {
			model.addAttribute("msg", "验证码错误！");
			return "login/login";
		}
		else {
			User user = userService.getUserByName(userName);
			if (user == null || !password.equals(user.getPassword())) {
				model.addAttribute("msg", "用户名或密码错误！");
				return "login/login";
			}
		}
		
		return "redirect:/hello";
	}
	
	
}
