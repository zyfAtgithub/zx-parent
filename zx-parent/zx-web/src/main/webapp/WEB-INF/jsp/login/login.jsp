<%@ page contentType="text/html; charset=UTF-8"  %>
<%@include file="../common/header.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
<%@include file="../common/include.jsp" %>
<title>请登录</title>

<style type="text/css">
	body {
	    width:100%;  
	    height:100%;
	    margin: 0 auto;
	    background-color: #6b9a3d;
	    background: url(${ctx}/webResources/images/bg2.jpg);
	}
	.input-group-addon a {
		text-decoration: none;
	}
	.input-lg {
		font-size: 14px;
	}
	.page-container {
	    height: auto;
	    margin-top: 15%;
	    margin-bottom: auto;
	    margin-left: 70%;
	}
</style>

</head>
<body>
<div class="container a ">  
    <div class="row center-vertical">  
        <div class="col-sm-3 col-sm-offset-3 page-container"> 
		<form class="login-form" method="post" role="form" onsubmit="return checkLogin(this);" action="${ctx}/login">
	  		<div class="input-group" style="margin-bottom: 23px">
				<span class="input-group-addon">  
			  		<a class="glyphicon glyphicon-user"></a>  
		       </span> 
				<input name="userName" type="text" class="form-control  input-lg" placeholder="请输入用户名" value="${userName}" >
			</div>
			<div class="input-group" style="margin-bottom: 23px">
				<span class="input-group-addon">  
       	            <a class="glyphicon glyphicon-lock"></a>
                   </span>
				<input name="password" type="password" class="form-control  input-lg" placeholder="请输入密码" value="${password}" >
			</div>
			<div class="input-group" style="margin-bottom: 23px">
			    <span class="input-group-addon">  
					<a class="glyphicon glyphicon-check"></a>  
                </span>
			  	<input name="vertifyCode" class="form-control input-lg" placeholder="验证码" type="text" value="${vertifyCode}" id="kaptcha" maxlength="4"/>
			  	<span class="input-group-addon">
				  	<img src="${ctx}/code/captcha-image" id="kaptchaImage" />
			  	</span>
			</div>
			<p class="text-danger" id="loginMsg">${error }</p>
			<div class="form-group" style="width:100%">
				<button class="btn  btn-md btn-primary" style="width:100%">登录</button><br>
			</div>
		</form>
	  </div>
	</div>
</div>
</body>
<%-- <script src='<c:url value="/webResources/js/jquery-2.1.1.min.js"></c:url>' type="text/javascript"></script>
 --%>
 <script src="${ctx}/webResources/js/jquery-2.1.1.min.js" type="text/javascript"></script>
<script type="text/javascript">

$(function(){  //生成验证码         
    $('#kaptchaImage, #captcha').click(function () {  
    $(this).hide().attr('src', '${ctx}/code/captcha-image?' + Math.floor(Math.random()*100) ).fadeIn(); });     
	$("#submitBtn").click(function(){
		alert("提交表单");
		var name=$("input[name='userName']").val();
		 var pass=$("input[name='password']").val();
		 var code=$("input[name='vertifyCode']").val();
		 if (!name) {
			 $("#loginMsg").text("请输入用户名！");
		 	return false;
		 }
		 
		 if (!pass) {
			 $("#loginMsg").text("请输入密码！");
		 	return false;
		 }
		 
		 if (!code) {
			 $("#loginMsg").text("请输入验证码！");
		 	return false;
		 }
	});
});   

  		  
function changeCode() {  //刷新
    $('#kaptchaImage').hide().attr('src', '${ctx}/code/captcha-image?' + Math.floor(Math.random()*100) ).fadeIn();  
    event.cancelBubble=true;  
}  

function checkLogin() {
	 var name=$("input[name='userName']").val();
	 var pass=$("input[name='password']").val();
	 var code=$("input[name='vertifyCode']").val();
	 if (!name) {
		 $("#loginMsg").text("请输入用户名！");
	 	return false;
	 }
	 
	 if (!pass) {
		 $("#loginMsg").text("请输入密码！");
	 	return false;
	 }
	 
	 if (!code) {
		 $("#loginMsg").text("请输入验证码！");
	 	return false;
	 }
	 
	 return true;
}
</script>
</html>