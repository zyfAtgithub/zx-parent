package com.yf.zx.web.sys.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yf.zx.biz.sys.user.entity.User;
import com.yf.zx.biz.sys.user.entity.UserVo;
import com.yf.zx.biz.sys.user.service.UserService;
import com.yf.zx.core.base.web.BaseController;
import com.yf.zx.core.base.web.PageReturn;
import com.yf.zx.core.base.web.ResultReturn;
import com.yf.zx.core.util.common.StringUtils;
import com.yf.zx.web.shiro.encrypt.PasswordEncrypt;

@Controller
@RequestMapping("sys/user")
public class UserController extends BaseController {

	
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@RequestMapping("tolist")
	public String toList() {
		return "sys/user/user_list";
	}

	@RequestMapping("toadd")
	public String toAdd() {
		return "sys/user/user_addOrEdit";
	}

	@RequestMapping("toedit")
	public String toEdit(@RequestParam(value="id", required=true) Long id, Model model) {
		User user = userService.findById(id);
		model.addAttribute("user", user);
		return "sys/user/user_addOrEdit";
	}

	@RequestMapping("toview")
	public String toView(@RequestParam(value="id", required=true) Long id, Model model) {
		User user = userService.findById(id);
		model.addAttribute("user", user);
		return "sys/user/user_view";
	}
	
	@RequestMapping("list")
	@ResponseBody //处理 AJAX请求，返回响应的内容，而不是 View Name
	public String list(UserVo userVo) {
		
		if (StringUtils.isNotNullAndEmpty(userVo.getLoginname())) {
			userVo.setLoginname("%" + userVo.getLoginname() + "%");
		}

		if (StringUtils.isNotNullAndEmpty(userVo.getUsername())) {
			userVo.setUsername("%" + userVo.getUsername() + "%");
		}

		if (StringUtils.isNotNullAndEmpty(userVo.getPhone())) {
			userVo.setPhone("%" + userVo.getPhone() + "%");
		}

		if (StringUtils.isNotNullAndEmpty(userVo.getEmail())) {
			userVo.setEmail("%" + userVo.getEmail() + "%");
		}
		
		PageReturn<User> page = userService.findByPage(userVo);
		return JSONObject.toJSONString(page);
	}
	
	@RequestMapping(value = "save", method=RequestMethod.POST)
	@ResponseBody
	public String save(User user) {
		ResultReturn ret = null;
		if (null ==user.getId()) {
			PasswordEncrypt.initPassword(user);
			ret = userService.addUser(user);
		}
		else {
			ret = userService.editById(user);
		}
		return JSONObject.toJSONString(ret);
	}

	
	@RequestMapping(value = "initPassword", method=RequestMethod.POST)
	@ResponseBody
	public String initPassword(User user) {
		ResultReturn ret = null;
		PasswordEncrypt.initPassword(user);
		ret = userService.editById(user);
		return JSONObject.toJSONString(ret);
	}
	
	@RequestMapping(value = "del", method=RequestMethod.POST)
	@ResponseBody
	public String del(String delIds) {
		ResultReturn ret = userService.deleteUserByIds(delIds);
		return JSONObject.toJSONString(ret);
	}
	
	
	@RequestMapping("toModifypasswordView")
	public String toModifypasswordView(@RequestParam(value="loginname", required=true) String loginname, Model model) {
		model.addAttribute("loginname", loginname);
		return "modifyPassword";
	}
	
	@RequestMapping(value = "modifyPassword", method=RequestMethod.POST)
	@ResponseBody
	public String modifyPassword(@RequestParam(value="loginname", required=true) String loginname, @RequestParam(value="pwdOrigin", required=true) String pwdOrigin, 
			@RequestParam(value="pwdNew", required=true) String pwdNew) {
		ResultReturn ret = new ResultReturn();
		User user = userService.getUserByName(loginname);
		if (!PasswordEncrypt.checkPassword(user, pwdOrigin)) {
			ret.setResultCode("0");
			ret.setResultMsg("原始密码不正确！");
		}
		else {
			user.setPassword(pwdNew);
			PasswordEncrypt.encrypt(user);
			ret = userService.editById(user);
		}
		return JSONObject.toJSONString(ret);
	}

	@RequestMapping("tograntrole")
	public String toGrantRole(@RequestParam(value="id", required=true) Long id, Model model) {
		ResultReturn ret = userService.loadRoles(id);
		model.addAttribute("roles", ret.getData());
		model.addAttribute("id", id);
		return "sys/user/user_roleGrant";
	}
	
	@RequestMapping(value = "grantrole", method=RequestMethod.POST)
	@ResponseBody
	public String grantRole(User user) {
		ResultReturn ret = userService.grantRole(user);
		return JSONObject.toJSONString(ret);
	}
}
