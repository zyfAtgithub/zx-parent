<%@ page contentType="text/html; charset=UTF-8"  %>
<%@include file="common/taglib.jsp"%>
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
		<form class="layui-form layui-form-pane" action="">
	      <input type="hidden" name="loginname" value="${loginname}"/>
		  <div class="layui-form-item">
		    <label class="layui-form-label">原始密码</label>
		    <div class="layui-input-block">
		      <input type="password" name="pwdOrigin" class="layui-input" autocomplete="off" lay-verify="required|pass" placeholder="请输入原始密码">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">新密码</label>
		    <div class="layui-input-block">
		      <input type="password" name="pwdNew" class="layui-input" autocomplete="off" lay-verify="required|pass" placeholder="请输入新密码">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">确认密码</label>
		    <div class="layui-input-block">
		      <input type="password" name="pwdNewConfirm" class="layui-input" autocomplete="off" lay-verify="required|pass" placeholder="请输入确认密码">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn layui-btn-normal layui-btn-small" lay-submit lay-filter="modifypwd">确认</button>
		      <button class="layui-btn layui-btn-primary layui-btn-small" onclick="closeWin();">取消</button>
		    </div>
		  </div>
		</form>
	</div>
	
	<script src="${ctx }/webResources/js/jquery-2.1.1.min.js" type="text/javascript"></script>
	<script src="${ctx }/webResources/plugins/layui/layui.all.js" type="text/javascript"></script>
	<script type="text/javascript">
	
		layui.use('form', function(){
		  var form = layui.form;
		  //自定义密码校验
		  form.verify({
			  //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
			  pass: [
			    /^[\S]{6,12}$/,
			    '密码必须6到12位，且不能出现空格'
			  ] 
			});  

		  form.on('submit(modifypwd)', function(data){
		  	  if (data.field.pwdNew != data.field.pwdNewConfirm) {
		  		  layer.msg("两次密码不一致", {icon:5, anim: 6});
		  		  return false;
		  	  }
		  	  
			  	$.ajax({
					url:'${ctx}/sys/user/modifyPassword',
					type:"POST",
					dataType:"json",
					data: data.field,
					success:function(resultRet){
						if (resultRet.resultCode == '200') {
							parent.document.getElementById('pwdmodifyRes').value = "200";
							closeWin();
						}
						else {
							layer.msg(resultRet.resultMsg, {icon:5, anim: 6});
						}
					},
					error:function(e) {
						layer.open({
							title:"请求出错！",
							content:e.responseText
						});
					}
				});
		  	  
    		  return false;
		  });
		});
	
		function closeWin() {
			var index=parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
		}
		
	</script>	 
</body>
</html>
