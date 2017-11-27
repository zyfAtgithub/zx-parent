<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<%@include file="common/include.jsp"%>
</head>
<body >
	<!-- 顶部导航栏 -->
  	<div class="layui-layout layui-layout-admin">
	  	<div class="layui-header">
	    	<div class="layui-logo">汽车品牌发展追踪系统</div>
			<ul class="layui-nav layui-layout-right">
	     			<li class="layui-nav-item">
	       			<a href="javascript:;">
	        				<img src="${ctx }/webResources/images/user.jpg" class="layui-nav-img">
	        				${CurLoginUser.username }
	       			</a>
	        		<dl class="layui-nav-child">
	          			<dd><a href="javascript:;" onclick="modifyPassword('<shiro:principal/>');">修改密码</a>
		          			<input type="hidden" id="pwdmodifyRes"/>
	          			</dd>
	        		</dl>
	     			</li>
	     			<li class="layui-nav-item"><a href="${ctx }/logout">退了</a></li>
	   		</ul>
		</div>
		
		<!-- 左侧菜单栏 -->
		<div class="sidebar-outer">
			<div  class="sidebar-inner">
				<ul class="sidebar-menu"></ul> 
			</div>
		</div>

		<div class="layui-body">
		  	<div class="right-topnav">
				<span class="layui-breadcrumb">
					<a href="#">首页</a>
				</span>
		  	</div>
		  	<div class="right-content"></div>
	  	</div>
	</div>
	<script  type="text/javascript" src="${ctx }/webResources/js/jquery-2.1.1.min.js"></script>
	<script src="${ctx }/webResources/plugins/layui/layui.all.js" type="text/javascript"></script>
	<script src="${ctx }/webResources/js/sidebar-menu.js"></script>
	<script src="${ctx }/webResources/js/main.js"></script>
</body>
</html>