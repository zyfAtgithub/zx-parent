<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../../common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="${ctx }/webResources/plugins/layui/css/layui.css">
	<style type="text/css">
		.layui-container{
			width:80%;
			padding:6px 8px;
		}
	</style>
</head>
<body>

<div class="layui-container">
		<form class="layui-form layui-form-pane" action="" onsubmit = "return false;" >
		  <div class="layui-form-item">
		    <label class="layui-form-label">登录名</label>
		    <div class="layui-input-block">
		      <input type="text" name="loginname"  
		       lay-verify="required" placeholder="请输入登录名" value="${user.loginname }" autocomplete="off" class="layui-input">
		      <input type="hidden" name="id" value="${user.id }"/>
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">用户名</label>
		    <div class="layui-input-block">
		      <input type="text" name="username" value="${user.username }"
		       lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">手机号</label>
		    <div class="layui-input-block">
		      <input type="text" name="phone" value="${user.phone }" autocomplete="off"  lay-verify="required|phone" placeholder="请输入手机号" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">邮箱</label>
		    <div class="layui-input-block">
		      <input type="email" name="email" value="${user.email }" autocomplete="off"  lay-verify="required|email" placeholder="请输入邮箱" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn layui-btn-normal layui-btn-small" lay-submit lay-filter="saveUser">提交</button>
		      <button type="reset" class="layui-btn layui-btn-primary layui-btn-small">重置</button>
		      <c:if test="${not empty user.id }">
			      <button class="layui-btn layui-btn-normal layui-btn-small" onclick="initPassword();">初始化密码</button>
		      </c:if>
		    </div>
		  </div>
		</form>
	</div>
	
	<script src="${ctx }/webResources/js/jquery-2.1.1.min.js" type="text/javascript"></script>
	<script src="${ctx }/webResources/plugins/layui/layui.all.js" type="text/javascript"></script>
	<script src="${ctx }/webResources/js/sys/user/user_addOrEdit.js" type="text/javascript" ></script>

</body>
</html>