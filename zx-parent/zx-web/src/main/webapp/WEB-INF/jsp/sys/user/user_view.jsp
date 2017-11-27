<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../../common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@include file="../../common/include.jsp"%>
<style type="text/css">
.layui-container {
	width: 80%;
	padding: 6px 8px;
}

table.layui-table tr th{
	font-weight: bold;
	text-align: center;
}

</style>

</head>
<body>
	<div class="layui-container">
		<div class="layui-row">
			<table class="layui-table">
				<tr><th>登录名</th><td>${user.loginname }</td></tr>
				<tr><th>用户名</th><td>${user.username }</td></tr>
				<tr><th>手机</th><td>${user.phone }</td></tr>
				<tr><th>邮箱</th><td>${user.email }</td></tr>
			</table>			
		</div>
	</div>
</body>
</html>