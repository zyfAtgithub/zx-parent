<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../common/taglib.jsp"%>
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
		<form class="layui-form layui-form-pane" action="" onsubmit = "return saveUser();" >
		  <div class="layui-form-item">
		    <label class="layui-form-label">登录名</label>
		    <div class="layui-input-block">
		      <input type="text" name="loginname" required  
		       lay-verify="required" placeholder="请输入登录名" value="${user.loginname }" autocomplete="off" class="layui-input">
		      <input type="hidden" name="id" value="${user.id }"/>
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">用户名</label>
		    <div class="layui-input-block">
		      <input type="text" name="username" required value="${user.username }"
		       lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">手机号</label>
		    <div class="layui-input-block">
		      <input type="text" name="phone" required value="${user.phone }" autocomplete="off"  lay-verify="required" placeholder="请输入手机号" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">邮箱</label>
		    <div class="layui-input-block">
		      <input type="email" name="email" required value="${user.email }" autocomplete="off"  lay-verify="required" placeholder="请输入邮箱" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn layui-btn-normal" lay-submit>提交</button>
		      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		  </div>
		</form>
	</div>
	
	<script src="${ctx }/webResources/js/jquery-2.1.1.min.js" type="text/javascript"></script>
	<script src="${ctx }/webResources/plugins/layui/layui.all.js" type="text/javascript"></script>

	<script type="text/javascript">
		
		function saveUser() {
			var id = $("input[name='id']").val();
			var loginName = $("input[name='loginname']").val();
			var userName = $("input[name='username']").val();
			var phone = $("input[name='phone']").val();
			var email = $("input[name='email']").val();
			var data = {};
			data.id = id;
			data.loginname = loginName;
			data.username = userName;
			data.phone = phone;
			data.email = email;
			$.ajax({
				url:"/zx-web/sys/user/save",
				type:"POST",
				dataType:"json",
				data:data,
				success:function(resultRet) {
					if (resultRet.resultCode == '200') {
						if (id) {
							parent.document.getElementById('editUserRes').value = "200";
						}
						else {
							parent.document.getElementById('addUserRes').value = "200";
						}
						closeWin();
					}
					else {
						layer.alert(resultRet.resultMsg);
					}
				}
			});
			
			return false;
		}
	
		function closeWin() {
			var index=parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
		}
	</script>

</body>
</html>