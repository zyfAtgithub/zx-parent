package com.yf.zx.biz;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.yf.zx.biz.sys.role.entity.Role;
import com.yf.zx.biz.sys.role.entity.RoleVo;
import com.yf.zx.biz.sys.role.service.RoleService;
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

	@Autowired
	RoleService roleService;

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
        assertEquals(userService.existUser("admin"), false);
    }

    @Test
    public void selectTest() {
    	User user = userService.getUserByName("admin");
    	logger.info(user.toString());
    }
    
    @Test
    public void addUsertest() {
    	User user = new User();
    	user.setLoginname("admin");
    	user.setUsername("系统管理员");
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
    	List<Long> menuIds = new ArrayList<Long>();
    	menuIds.add(2l);
    	menuIds.add(4l);
    	ResultReturn ret = menuService.loadMenuList(menuIds);
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
    
    @Test
    public void operator() {
    	char c = '\u0000';
     	System.out.println(c == ' ');
    	int res = 10 << 3;
    	System.out.println("2 * 8 = " + res);
    }
    
    @Test
    public void selectRole() {
    	RoleVo roleVo = new RoleVo();
    	roleVo.setPage(0);
    	roleVo.setRows(20);
    	roleVo.setOrderBy("id asc");
    	PageReturn<Role> ret = roleService.findByPage(roleVo);
    	System.out.println(JSONObject.toJSONString(ret));
    }
    
    @Test
    public void addRole() {
    	Role role = new Role();
    	role.setRole("3");
    	role.setDescription("角色3");
//    	role.setPermids(new Long[] {2L,12L});
    	ResultReturn ret = roleService.addRole(role);
    	System.out.println(JSONObject.toJSONString(ret));
    }
    
    @Test
    public void delRole() {
    	String ids = "1|2|232|3|4";
    	ResultReturn ret = roleService.deleteRoleByIds(ids);
    	System.out.println(JSONObject.toJSONString(ret));
    }
    
    @Test
    public void getRoleById() {
    	ResultReturn ret = roleService.getRoleById(5l);
    	System.out.println(JSONObject.toJSONString(ret));
    }
    
    @Test
    public void editRole() {
    	Role role = new Role();
    	role.setId(5l);
    	role.setRole("22434de");
    	role.setPermids("44");
    	ResultReturn ret = roleService.updateRoleById(role);
    	System.out.println(JSONObject.toJSONString(ret));
    }
    
    @Test
    public void getPermids() {
    	ResultReturn ret = roleService.getPermidsByRoleids(4l);
    	System.out.println(JSONObject.toJSONString(ret));
    }
    
    
    @Test
    public void getRoles() {
    	Set<String> roles = userService.getRolesByLoginName("lm2");
    	System.out.println(JSONObject.toJSONString(roles));
    }
    
    @Test
    public void getPerms() {
    	Set<String> roles = userService.getPermsByLoginName("zhangyifeng");
    	System.out.println(JSONObject.toJSONString(roles));
    	
    	Map<String, String> map = new HashMap<String, String>();
    	map.put(null, "nihao");
    	System.out.println(map.get(null));
    }
    
    
}