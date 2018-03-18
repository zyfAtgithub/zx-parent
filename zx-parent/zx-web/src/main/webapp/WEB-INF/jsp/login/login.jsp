<%@ page contentType="text/html; charset=UTF-8"  %>
<%@include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@include file="../common/include.jsp" %>
<link href="${ctx }/webResources/plugins/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

<title>请登录</title>

<style type="text/css">
	body {
	    width:100%;  
	    height:100%;
	    margin: 0 auto;
	    /* background-color: #6b9a3d; */
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
	
	#logindev{
	  position: absolute;
	  top: 50%;
	  -webkit-transform: translateY(-50%);
	  -moz-transform:  translateY(-50%);
	  -ms-transform:  translateY(-50%);
	  -o-transform:  translateY(-50%);
	  transform:  translateY(-50%);
	}
</style>

</head>
<body>
<div class="container a ">  
    <div class="row center-vertical">  
        <div class="col-sm-3 col-sm-offset-4 " id="logindev"> 
		<form class="login-form" method="post" role="form" onsubmit="return checkLogin(this);" action="${ctx}/login">
	  		<div class="input-group" style="margin-bottom: 15px">
				<span class="input-group-addon">  
			  		<a class="glyphicon glyphicon-user"></a>  
		       </span> 
				<input name="userName" type="text" class="form-control" placeholder="请输入用户名" value="${userName}" >
			</div>
			<div class="input-group" style="margin-bottom: 15px">
				<span class="input-group-addon">  
       	            <a class="glyphicon glyphicon-lock"></a>
                   </span>
				<input name="password" type="password" class="form-control" placeholder="请输入密码" value="${password}" >
			</div>
			<c:if test="${vertifyCodeEnabled }">
				<div class="row">
					<div class="col-lg-7" style="padding-right: 0px;">
				      <div class="input-group" style="margin-bottom: 15px">
					    <span class="input-group-addon">  
							<a class="glyphicon glyphicon-check"></a>  
		                </span>
					  	<input name="vertifyCode" class="form-control" placeholder="验证码" type="text" value="${vertifyCode}" id="kaptcha" maxlength="4"/>
					  </div>
				    </div>
				    <div class="col-lg-5" style="padding-left: 5px;">
						<img src="${ctx}/code/captcha-image" id="kaptchaImage" />
					</div>
				</div>
			</c:if>
			<p class="text-danger" id="loginMsg">${error }</p>
			<div class="form-group" style="width:100%">
				<button class="btn  btn-md btn-primary" style="width:100%">登录</button><br>
			</div>
		</form>
	  </div>
	</div>
</div>
	<script  type="text/javascript" src="${ctx }/webResources/js/jquery-2.1.1.min.js"></script>
	<script type="text/javascript">
	
	$(function(){ 
		//生成验证码         
	    $('#kaptchaImage, #captcha').click(function () {  
	    	$(this).attr('src', '${ctx}/code/captcha-image?' + new Date().getTime() );
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
		
		 if ( 'true' == '${vertifyCodeEnabled }' && !code) {
			 $("#loginMsg").text("请输入验证码！");
		 	return false;
		 }
		 
		 return true;
	}
	</script>
</body>

</html>