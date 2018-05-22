<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../../common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>用户管理</title>
	<%@include file="../../common/include.jsp"%>
	<link rel="stylesheet" href="${ctx }/webResources/css/sys/user/user.css">
</head>
<body>
	<div class="container">
		<div class="layui-row query-panel">
			<form class="layui-form layui-form-pane" action="" >
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">登录名</label>
			    		<div class="layui-input-inline">	
			    			<input type="text" name="loginname" value="${loginname}" autocomplete="off"
			    			 placeholder="请输入登录名" autocomplete="off" 
			    			 class="layui-input">
			    		</div>
			    	</div>
					<div class="layui-inline">
						<label class="layui-form-label">用户名</label>
			    		<div class="layui-input-inline">	
			    			<input type="text" name="username" value="${username}" autocomplete="off"
			    			 placeholder="请输入用户名" autocomplete="off" 
			    			 class="layui-input">
			    		</div>
			    	</div>
		    		<div class="layui-inline">	
		    			<shiro:hasPermission name="sys:user:query">
			    			<button class="layui-btn layui-btn-small" lay-submit lay-filter="queryUser">查询</button>
		    			</shiro:hasPermission>
		    			<shiro:hasPermission name="sys:user:reset">
			    			<button class="layui-btn layui-btn-primary layui-btn-small" type="reset">重置</button>
		    			</shiro:hasPermission>
		    		</div>
		    	</div>			
			</form>
		</div>
		<div class="layui-row">
			<div class="batch-operation">
				<div class="layui-btn-group">
				<shiro:hasPermission name="sys:user:add">
				  <button class="layui-btn  layui-btn-small" id="btn-add">
				    <i class="layui-icon">&#xe654;</i>
				  </button>
				</shiro:hasPermission>
				<shiro:hasPermission name="sys:user:batchdel">
				  <button class="layui-btn layui-btn-danger layui-btn-small" id="btn-batch-del">
				    <i class="layui-icon">&#xe640;</i>
				  </button>
				</shiro:hasPermission>
				<shiro:hasPermission name="sys:user:refresh">
				  <button class="layui-btn layui-btn-normal layui-btn-small" id="btn-refresh">
				    <i class="layui-icon">&#x1002;</i>
				  </button>
				</shiro:hasPermission>
				</div>
			</div>
			<div class="table-container">
				<table id="userTb" lay-filter="userTbFilter"></table>
			</div>
		</div>
		<input type="hidden" id="addUserRes"/>
		<input type="hidden" id="editUserRes"/>
		<input type="hidden" id="rolegrantRes"/>
	</div>


	<!-- 日期格式化 -->
	<script type="text/html" id="timeTpl">
		{{ formatDatebox(d.lastloginTime) }}
	</script>

	<!-- 是否锁定 -->
	<script type="text/html" id="lockedTpl">
		{{ d.locked? '是' : '否' }}
	</script>

	<script type="text/html" id="toolBar">
		<shiro:hasPermission name="sys:user:add">
			<a class="layui-btn layui-btn-mini" lay-event="detail">查看</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="sys:user:update">
			<a class="layui-btn layui-btn-mini" lay-event="edit">编辑</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="sys:user:rolegrant">
		<a class="layui-btn layui-btn-mini" lay-event="rolegrant">角色授权</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="sys:user:del">
			<a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
		</shiro:hasPermission>
	</script>
	
	<script src="${ctx }/webResources/js/sys/user/userlist.js" type="text/javascript"></script>
</body>
</html>