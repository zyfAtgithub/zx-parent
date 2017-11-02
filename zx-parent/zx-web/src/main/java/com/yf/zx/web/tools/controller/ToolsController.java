package com.yf.zx.web.tools.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ToolsController 实用工具
 *  
 * @author zhang.yifeng
 * @CreateDate 2017年11月2日
 * @version 1.0.0
 * @since  1.0.0 
 * @see com.yf.zx.web.tools.controller 
 *
 */
@Controller
@RequestMapping("/tools")
public class ToolsController {

	
	@RequestMapping("/dialtesting")
	public String toDialTestingView() {
		return "tools/dialtesting";
	}
}
