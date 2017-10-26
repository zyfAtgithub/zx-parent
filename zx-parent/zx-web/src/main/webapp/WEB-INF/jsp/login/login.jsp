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
		<form class="login-form" method="post" role="form" action="${ctx}/login/validateLogin">
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
				<input name="password" type="password" class="form-control  input-lg" placeholder="请输入密码" value="${密码}" >
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
			<p class="text-danger">${msg }</p>
			<div class="form-group" style="width:100%">
				<button class="btn  btn-md btn-primary" style="width:100%">登录</button><br>
			</div>
		</form>
	  </div>
	</div>
</div>
</body>
<script src='<c:url value="/webResources/js/jquery-2.1.1.min.js"></c:url>' type="text/javascript"></script>
<%-- <script src="${ctx}/webResources/js/jquery-2.1.1.min.js" type="text/javascript"></script> --%>
<script type="text/javascript">

$(function(){  //生成验证码         
    $('#kaptchaImage, #captcha').click(function () {  
    $(this).hide().attr('src', '${ctx}/code/captcha-image?' + Math.floor(Math.random()*100) ).fadeIn(); });      
});   

window.onbeforeunload = function(){  
    //关闭窗口时自动退出  
    if(event.clientX>360&&event.clientY<0||event.altKey){     
        alert(parent.document.location);  
    }  
};  
  		  
function changeCode() {  //刷新
    $('#kaptchaImage').hide().attr('src', '${ctx}/code/captcha-image?' + Math.floor(Math.random()*100) ).fadeIn();  
    event.cancelBubble=true;  
}  

function check_login()
{
 var name=$("#user_name").val();
 var pass=$("#password").val();
 if(name=="1" && pass=="1")
 {
  alert("登录成功！");
  $("#user_name").val("");
  $("#password").val("");

 }
 else
 {
  $("#login_form").removeClass('shake_effect');  
  setTimeout(function()
  {
   $("#login_form").addClass('shake_effect')
  },1);  
 }
}
function check_register(){
	var name = $("#r_user_name").val();
	var pass = $("#r_password").val();
	var email = $("r_email").val();
	if(name!="" && pass=="" && email != "")
	 {
	  alert("注册成功！");
	  $("#user_name").val("");
	  $("#password").val("");
	 }
	 else
	 {
	  $("#login_form").removeClass('shake_effect');  
	  setTimeout(function()
	  {
	   $("#login_form").addClass('shake_effect')
	  },1);  
	 }
}
$(function(){
	$("#login").click(function(){
		check_login();
		return false;
	})
})
</script>
</html>