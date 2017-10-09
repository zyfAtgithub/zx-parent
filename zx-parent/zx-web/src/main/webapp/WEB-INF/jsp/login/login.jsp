<%@ page contentType="text/html; charset=UTF-8"  %>
<%@include file="../common/header.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@include file="../common/include.jsp" %>
<title>登录</title>

<style type="text/css">
	.login-page {
	  width: 350px;
	  padding: 10% 0 0;
	  margin: auto;
	}
	.form {
	  position: relative;
	  z-index: 1;
	  background: #FFFFFF;
	  max-width: 360px;
	  margin: 0 auto 100px;
	  padding: 45px;
	  text-align: center;
	  box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
	}
	body {
	  background: #76b852; /* fallback for old browsers */
	  background: -webkit-linear-gradient(right, #76b852, #8DC26F);
	  background: -moz-linear-gradient(right, #76b852, #8DC26F);
	  background: -o-linear-gradient(right, #76b852, #8DC26F);
	  background: linear-gradient(to left, #76b852, #8DC26F);
	  font-family: "Roboto", sans-serif;
	  -webkit-font-smoothing: antialiased;
	  -moz-osx-font-smoothing: grayscale;      
	}
	.shake_effect{
		-webkit-animation-name: shake;
		animation-name: shake;
		-webkit-animation-duration: 1s;
		animation-duration: 1s;
	}
	@-webkit-keyframes shake {
	  from, to {
		-webkit-transform: translate3d(0, 0, 0);
		transform: translate3d(0, 0, 0);
	  }

	  10%, 30%, 50%, 70%, 90% {
		-webkit-transform: translate3d(-10px, 0, 0);
		transform: translate3d(-10px, 0, 0);
	  }

	  20%, 40%, 60%, 80% {
		-webkit-transform: translate3d(10px, 0, 0);
		transform: translate3d(10px, 0, 0);
	  }
	}

	@keyframes shake {
	  from, to {
		-webkit-transform: translate3d(0, 0, 0);
		transform: translate3d(0, 0, 0);
	  }

	  10%, 30%, 50%, 70%, 90% {
		-webkit-transform: translate3d(-10px, 0, 0);
		transform: translate3d(-10px, 0, 0);
	  }

	  20%, 40%, 60%, 80% {
		-webkit-transform: translate3d(10px, 0, 0);
		transform: translate3d(10px, 0, 0);
	  }
	}
	p.center{
		color: #fff;font-family: "Microsoft YaHei";
	}
</style>

</head>
<body>
<div class="htmleaf-container">
	<div class="login-page">
	  <div id="login_form" class="form">
		<form class="login-form" method="post" role="form" action="${ctx}/login/validateLogin">
			<div class="form-group" style="margin-bottom: 13px">
		  		<input type="text"  class="form-control" placeholder="用户名" name="userName" value="${userName}">
			</div>
			<div class="form-group" style="margin-bottom: 13px">
			  <input type="password" class="form-control" placeholder="密码" name="password" value="${password}">
			</div>
			<div class="form-group" style="margin-bottom: 13px">
			  <input name="vertifyCode" class="form-control" placeholder="验证码" type="text" value="${vertifyCode}" id="kaptcha" maxlength="4" style="width:170px;display: inline" />
			  	<img src="${ctx}/code/captcha-image" id="kaptchaImage" style="margin-left:5px;" />
			</div>
			<p class="text-error">${msg }</p>
			<div class="form-group" style="width:100%">
				<button class="btn btn-success" style="width:50%">登　录</button><br>
			</div>
		</form>
	  </div>
	</div>
</div>
</body>
<script src="${ctx}/webResources/js/jquery-2.1.1.min.js" type="text/javascript"></script>
<script type="text/javascript">

$(function(){  //生成验证码         
    $('#kaptchaImage').click(function () {  
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