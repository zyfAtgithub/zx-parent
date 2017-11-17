<%@ page contentType="text/html; charset=UTF-8"  %>
<%@include file="common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@include file="common/include.jsp" %> 
<body>
	<h2>请求页面未找到！</h2>
	<shiro:user>  
		欢迎[<shiro:principal/>]登录，<a href="${pageContext.request.contextPath}/logout">退出</a>  
	</shiro:user>   
</body>
</html>
