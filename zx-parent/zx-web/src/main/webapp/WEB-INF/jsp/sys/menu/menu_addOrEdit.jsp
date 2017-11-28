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
		    <label class="layui-form-label">菜单名</label>
		    <div class="layui-input-block">
		      <input type="text" name="menuname"  
		       lay-verify="required" placeholder="请输入菜单名" value="${menu.menuname }" autocomplete="off" class="layui-input">
		      <input type="hidden" name="id" value="${menu.id }"/>
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">菜单url</label>
		    <div class="layui-input-block">
		      <input type="text" name="menuurl" value="${menu.menuurl }"
		       lay-verify="required" placeholder="请输入菜单url" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">父级id</label>
		    <div class="layui-input-block">
		      <input type="text" name="parentid" value="${menu.parentid }" autocomplete="off"  
		      lay-verify="required" placeholder="请输入父级id" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">显示图标</label>
		    <div class="layui-input-block">
		      <input type="text" name="menuicon" value="${menu.menuicon }" autocomplete="off"  
		      lay-verify="required" placeholder="请输入菜单显示图标" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">菜单层级</label>
		    <div class="layui-input-block">
		      <input type="number" name="level" value="${menu.level }" autocomplete="off"  
		      lay-verify="required|number" placeholder="请输入菜单层级" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">排序</label>
		    <div class="layui-input-block">
		      <input type="number" name="menuorder" value="${menu.menuorder }" autocomplete="off"  
		      lay-verify="required|number" placeholder="请输入排序" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">是否展示</label>
		    <div class="layui-input-block">
		      <input type="number" name="ishow" value="${menu.ishow }" autocomplete="off"  
		      lay-verify="required" placeholder="请输入是否展示" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">是否为按钮</label>
		    <div class="layui-input-block">
		      <input type="number" name="isbtn" value="${menu.isbtn }" autocomplete="off"  
		      lay-verify="required" placeholder="请输入是否为按钮" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">树型图标</label>
		    <div class="layui-input-block">
		      <input type="text" name="treeiconskin" value="${menu.treeiconskin }" autocomplete="off"  
		      lay-verify="required" placeholder="请输入菜单树显示图标" autocomplete="off" class="layui-input">
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
	<script type="text/javascript" >
		
	</script>

</body>
</html>