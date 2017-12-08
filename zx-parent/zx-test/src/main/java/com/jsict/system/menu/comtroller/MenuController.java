package com.jsict.system.menu.comtroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping()
public class MenuController {

	@RequestMapping(value="/toMenuPage.do",method=RequestMethod.GET)
	public String toMenuPage()
	{
		return "system/menu/menuPage";
	}
	
	@RequestMapping(value="/searchMenu.do",method=RequestMethod.POST)
	public String searchRole()
	{
		return "system2/menu/menuList";
	}
}
