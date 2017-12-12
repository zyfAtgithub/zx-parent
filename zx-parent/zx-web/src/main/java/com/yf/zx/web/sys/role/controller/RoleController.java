package com.yf.zx.web.sys.role.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yf.zx.biz.sys.role.entity.Role;
import com.yf.zx.biz.sys.role.entity.RoleVo;
import com.yf.zx.biz.sys.role.service.RoleService;
import com.yf.zx.core.base.web.PageReturn;
import com.yf.zx.core.base.web.ResultReturn;

@Controller
@RequestMapping("/sys/role")
public class RoleController {

	Logger logger = LoggerFactory.getLogger(RoleController.class);
	
	@Autowired
	RoleService roleService;
	
	@RequestMapping("tolist")
	public String toList() {
		return "sys/role/role_list";
	}
	
	@RequestMapping("list")
	@ResponseBody //处理 AJAX请求，返回响应的内容，而不是 View Name
	public String list(RoleVo roleVo) {
		
//		if (StringUtils.isNotNullAndEmpty(userVo.getLoginname())) {
//			userVo.setLoginname("%" + userVo.getLoginname() + "%");
//		}
//
//		if (StringUtils.isNotNullAndEmpty(userVo.getUsername())) {
//			userVo.setUsername("%" + userVo.getUsername() + "%");
//		}
//
//		if (StringUtils.isNotNullAndEmpty(userVo.getPhone())) {
//			userVo.setPhone("%" + userVo.getPhone() + "%");
//		}
//
//		if (StringUtils.isNotNullAndEmpty(userVo.getEmail())) {
//			userVo.setEmail("%" + userVo.getEmail() + "%");
//		}
		PageReturn<Role> page = roleService.findByPage(roleVo);
		return JSONObject.toJSONString(page);
	}
	
	@RequestMapping(value = "del", method=RequestMethod.POST)
	@ResponseBody
	public String del(String delIds) {
		ResultReturn ret = roleService.deleteRoleByIds(delIds);
		return JSONObject.toJSONString(ret);
	}
	
	@RequestMapping("toadd")
	public String toAdd() {
		return "sys/role/role_addOrEdit";
	}
}
