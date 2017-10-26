package com.yf.zx.web.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class CommonController {

	@RequestMapping("/hello")
	public String hello(Model model) {
		model.addAttribute("msg", "你好，欢迎回来！！");
		return "hello";
	}
}
