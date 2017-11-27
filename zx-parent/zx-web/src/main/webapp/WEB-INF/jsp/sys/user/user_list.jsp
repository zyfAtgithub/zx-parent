<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../../common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>用户管理</title>

<link rel="stylesheet" href="${ctx }/webResources/plugins/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="${ctx }/webResources/plugins/layui/css/layui.css">
<link rel="stylesheet" href="${ctx }/webResources/css/right-content.css">

<style type="text/css">
	.container{
		width:90%;
		margin: auto;
	}
	.query-panel{
		border: 1px solid #D9D9D9;
		padding:8px 12px 0px;
		background: #FCFCFC;
	}
	.batch-operation{
		border-top: 1px solid #D9D9D9;
		border-left: 1px solid #D9D9D9;
		border-right: 1px solid #D9D9D9;
		border-top-left-radius:4px;
		border-top-right-radius:4px;
		margin-top: 3px;
		margin-bottom: -11px; 
	}
	
	.table-container{
	}
</style>
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
		    			<button class="layui-btn layui-btn-small" lay-submit lay-filter="queryUser">查询</button>
		    			<button class="layui-btn layui-btn-primary layui-btn-small" type="reset">重置</button>
		    		</div>
		    	</div>			
			</form>
		</div>
		<div class="layui-row">
			<div class="batch-operation">
				<div class="layui-btn-group">
				  <button class="layui-btn  layui-btn-small" id="btn-add">
				    <i class="layui-icon">&#xe654;</i>
				  </button>
				  <button class="layui-btn layui-btn-danger layui-btn-small" id="btn-batch-del">
				    <i class="layui-icon">&#xe640;</i>
				  </button>
				  <button class="layui-btn layui-btn-normal layui-btn-small" id="btn-refresh">
				    <i class="layui-icon">&#x1002;</i>
				  </button>
				</div>
			</div>
			<div class="table-container">
				<table id="userTb" lay-filter="userTbFilter"></table>
			</div>
		</div>
		<input type="hidden" id="addUserRes"/>
		<input type="hidden" id="editUserRes"/>
		
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
		  <a class="layui-btn layui-btn-mini" lay-event="detail">查看</a>
		  <a class="layui-btn layui-btn-mini" lay-event="edit">编辑</a>
		  <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
		  
		  <!-- 这里同样支持 laytpl 语法，如： -->
		  {{#  if(d.auth > 2){ }}
		    <a class="layui-btn layui-btn-mini" lay-event="check">审核</a>
		  {{#  } }}
	</script>
	
	<script src="${ctx }/webResources/plugins/layui/layui.all.js" type="text/javascript"></script>
	<script src="${ctx }/webResources/js/user/userlist.js" type="text/javascript"></script>
</body>
</html>