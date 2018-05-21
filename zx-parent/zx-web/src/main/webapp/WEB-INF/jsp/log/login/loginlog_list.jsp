<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../../common/taglib.jsp"%>
<%@include file="../../common/include.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>登录日志管理</title>
<link rel="stylesheet" href="${ctx }/webResources/css/sys/user/user.css">
</head>
<body>
	<div class="container">
		<div class="layui-row query-panel">
			<form class="layui-form layui-form-pane" action="" >
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">登录用户</label>
			    		<div class="layui-input-inline">	
			    			<input type="text" name="loginuser" value="${loginuser}" placeholder="请输入登录用户" autocomplete="off"
								   class="layui-input">
			    		</div>
			    	</div>
					<div class="layui-inline">
						<label class="layui-form-label">登录IP</label>
			    		<div class="layui-input-inline">	
			    			<input type="text" name="ip" value="${ip}" autocomplete="off" placeholder="请输入登录IP" class="layui-input">
			    		</div>
			    	</div>
					<div class="layui-inline">
						<label class="layui-form-label">登录时间</label>
			    		<div class="layui-input-inline">
			    			<input type="text" name="logintime" id="logintime" placeholder="yyyy-MM-dd HH:mm:ss" class="layui-input">
			    		</div>
			    	</div>
					<div class="layui-inline">
						<label class="layui-form-label">登出时间</label>
			    		<div class="layui-input-inline">
			    			<input type="text" name="logouttime" id="logouttime" placeholder="yyyy-MM-dd HH:mm:ss" class="layui-input">
			    		</div>
			    	</div>
		    		<div class="layui-inline">
		    			<shiro:hasPermission name="log:loginlog:query">
			    			<button class="layui-btn layui-btn-small" lay-submit lay-filter="queryLoginLog">查询</button>
		    			</shiro:hasPermission>
		    			<shiro:hasPermission name="log:loginlog:reset">
			    			<button class="layui-btn layui-btn-primary layui-btn-small" type="reset">重置</button>
		    			</shiro:hasPermission>
		    		</div>
		    	</div>
				<input type="hidden" name="orderBy" value="logintime desc" />
			</form>
		</div>
		<div class="layui-row">
			<div class="batch-operation">
				<div class="layui-btn-group">
					<shiro:hasPermission name="log:loginlog:batchdel">
						<button class="layui-btn layui-btn-danger layui-btn-small" id="btn-batch-del">
							<i class="layui-icon">&#xe640;</i>
						</button>
					</shiro:hasPermission>
				  <button class="layui-btn layui-btn-normal layui-btn-small" id="btn-refresh">
				    <i class="layui-icon">&#x1002;</i>
				  </button>
				</div>
			</div>
			<div class="table-container">
				<table id="loginLogTb" lay-filter="loginLogTbFilter"></table>
			</div>
		</div>
		<input type="hidden" name="logintimeBegin"/>
		<input type="hidden" name="logintimeEnd"/>
		<input type="hidden" name="logouttimeBegin"/>
		<input type="hidden" name="logouttimeEnd"/>
	</div>


	<!-- 日期格式化 -->
	<script type="text/html" id="logintimeTpl">
		{{ formatDatebox(d.logintime) }}
	</script>
	<script type="text/html" id="logouttimeTpl">
		{{ formatDatebox(d.logouttime) }}
	</script>

	<script src="${ctx }/webResources/plugins/layui/layui.all.js" type="text/javascript"></script>
	<script src="${ctx }/webResources/js/log/loginlog.js" type="text/javascript"></script>
</body>
</html>