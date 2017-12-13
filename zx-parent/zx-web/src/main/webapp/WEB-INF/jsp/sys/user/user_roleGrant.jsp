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
		.layui-inline select{
			width:180px;
			height:240px;
			padding:4px;
		}
		.right-sel{
			margin-left: 20px;
		}
	</style>
</head>
<body>

<div class="layui-container">
	cscsc${roles.unselectedRole[0]}
	<form class="layui-form layui-form-pane" action="" onsubmit = "return false;" >
	  <div class="layui-form-item">
	  	<div class="layui-inline">
  	 	  <label for="select1">待选</label>
	  	  <div>
	          <select multiple="multiple"id="select1" lay-ignore>
	            <option value="超级管理员">超级管理员xxxxxxxxxxxxxxxxxxx</option>
	            <option value="普通管理员">普通管理员</option>
	            <option value="信息发布员">信息发布员</option>
	            <option value="财务管理员">财务管理员</option>
	            <option value="产品管理员">产品管理员</option>
	            <option value="资源管理员">资源管理员</option>
	            <option value="管理员">管理员</option>
	          </select>
	  	  </div>
	  	</div>
	  	<div class="layui-inline">
	 		<label  for="select2">已选</label>
	  		<div>
	         	<select multiple="multiple" id="select2" lay-ignore></select>
	  		</div>
	  	</div>
	  </div>
	  <div class="layui-form-item">
	    <div class="layui-input-block">
	      <button class="layui-btn layui-btn-normal layui-btn-small" lay-submit lay-filter="saveUser">提交</button>
	      <button type="reset" class="layui-btn layui-btn-primary layui-btn-small">重置</button>
	    </div>
	  </div>
	</form>
</div>
	
	<script src="${ctx }/webResources/js/jquery-2.1.1.min.js" type="text/javascript"></script>
	<script src="${ctx }/webResources/plugins/layui/layui.all.js" type="text/javascript"></script>
	<script type="text/javascript">
	//下拉框交换JQuery
	$(function(){
	    //双击选项
	    $('#select1').dblclick(function(){ //绑定双击事件
	        //获取全部的选项,删除并追加给对方
	        $("option:selected",this).appendTo('#select2'); //追加给对方
	    });
	    //双击选项
	    $('#select2').dblclick(function(){
	       $("option:selected",this).appendTo('#select1');
	    });
	});
	</script>
</body>
</html>