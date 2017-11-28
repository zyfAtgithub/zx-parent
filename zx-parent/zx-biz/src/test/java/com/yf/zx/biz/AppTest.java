package com.yf.zx.biz;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.yf.zx.biz.sys.menu.entity.Menu;
import com.yf.zx.biz.sys.menu.entity.MenuVo;
import com.yf.zx.biz.sys.menu.service.MenuService;
import com.yf.zx.biz.sys.user.entity.User;
import com.yf.zx.biz.sys.user.entity.UserVo;
import com.yf.zx.biz.sys.user.service.UserService;
import com.yf.zx.core.base.web.PageReturn;
import com.yf.zx.core.base.web.ResultReturn;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
    "classpath:conf/spring-*.xml"
})
public class AppTest {
	
	Logger logger = LoggerFactory.getLogger(AppTest.class);
	
	@Autowired
	UserService userService;

	@Autowired
	MenuService menuService;

    @Before
    public void before() {
//        logger.info("Before");
    }

    @After
    public void after() {
//        logger.info("After");
    }

    @Test
    public void exitTest() {
        assertEquals(userService.existUser("admin"), true);
    }

    @Test
    public void selectTest() {
    	User user = userService.getUserByName("admin");
    	logger.info(user.toString());
    }
    
    @Test
    public void addUsertest() {
    	User user = new User();
    	user.setLoginname("lm2");
    	user.setUsername("刘明2");
    	user.setPassword("6168db58405a9eab7828e6a47ff73383");//123456
    	user.setSalt("28495018162ad6328783c3bde7364346");
    	ResultReturn ret = userService.addUser(user);
    	System.out.println(ret);
    }
    
    @Test
    public void delUser() {
    	ResultReturn ret = userService.deleteUserByIds("16|18");
    	System.out.println(JSONObject.toJSONString(ret));
    }
    
    
    @Test
    public void findUserByPage() {
    	UserVo userVo = new UserVo();
    	userVo.setPage(0);
    	userVo.setRows(1);
//    	userVo.setUsername("张益峰");
//    	userVo.setLoginname("%a%");
    	userVo.setOrderBy("lastlogin_time desc");
    	
    	PageReturn<User> page = userService.findByPage(userVo);
    	String json = JSONObject.toJSONString(page);
    	
    	JSONObject jsonObject = new JSONObject();
    	jsonObject.put("code",0);
    	jsonObject.put("count",100);
    	jsonObject.put("msg","");
    	System.out.println( jsonObject.toString());
    	System.out.println( jsonObject.toJSONString());
    	System.out.println(json);
    }
    
    @Test
    public void addMenu() {
    	Menu menu = new Menu();
    	menu.setMenuname("菜单管理");
    	menu.setMenuurl("sys/menu/tolist");
    	menu.setMenuicon("fa fa-sitemap faa-flash");
    	menu.setMenuorder(4);
    	menu.setIsshow(true);
    	menu.setParentid(2l);
    	menu.setLevel(2);
    	menu.setIsbtn(false);
    	ResultReturn ret = menuService.addMenu(menu);
    	System.out.println(JSONObject.toJSONString(ret));
    }

    @Test
    public void getMenu() {
    	ResultReturn ret = menuService.loadMenuList();
    	System.out.println(JSONObject.toJSONString(ret));
    }

    @Test
    public void loadMenuTree() {
    	ResultReturn ret = menuService.loadMenuTree();
    	System.out.println(JSONObject.toJSONString(ret));
    }
    
    @Test
    public void getSubMenu() {
    	Long parentId = 2l;
    	MenuVo menuVo = new MenuVo();
    	menuVo.setParentid(parentId);
    	menuVo.setLevel(2);
    	PageReturn<Menu> page = menuService.findMenuByPage(menuVo);
    	System.out.println(JSONObject.toJSONString(page));
    }

    @Test
    public void getOperationBtn() {
    	Long parentId = 3l;
    	MenuVo menuVo = new MenuVo();
    	menuVo.setParentid(parentId);
    	menuVo.setLevel(3);
    	menuVo.setIsbtn(true);
    	PageReturn<Menu> page = menuService.findMenuByPage(menuVo);
    	System.out.println(JSONObject.toJSONString(page));
    }
    
    @Test
    public void findMenuById() {
    	Menu menu= menuService.findById(2l);
    	System.out.println(JSONObject.toJSONString(menu));
    }
    
    @Test
    public void updateMenu() {
    	Menu menu = new Menu();
    	menu.setId(11l);
    	menu.setMenuname("菜单管理");
    	ResultReturn ret = menuService.editById(menu);
    	System.out.println(JSONObject.toJSONString(ret));
    }
    
    @Test
    public void delMenu() {
    	String ids = "11|13|14";
    	ResultReturn ret = menuService.deleteMenuByIds(ids);
    	System.out.println(JSONObject.toJSONString(ret));
    }
}