<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../../common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<%@include file="../../common/include.jsp" %>
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
	<form class="layui-form layui-form-pane" action="" onsubmit = "return false;" >
	  <input name="id" type="hidden" value="${id}"/>
	  <div class="layui-form-item">
	  	<div class="layui-inline">
  	 	  <label for="select1">待选</label>
	  	  <div>
	          <select multiple="multiple"id="select1" lay-ignore>
	          	<c:forEach items="${roles.unselectedRole}" var="role">
		            <option value="${role.id }">${role.role }</option>
	          	</c:forEach>
	          </select>
	  	  </div>
	  	</div>
	  	<div class="layui-inline">
	 		<label  for="select2">已选</label>
	  		<div>
	         	<select multiple="multiple" id="select2" lay-ignore>
		          	<c:forEach items="${roles.selectedRole}" var="role">
			            <option value="${role.id }">${role.role }</option>
		          	</c:forEach>
	         	</select>
	  		</div>
	  	</div>
	  </div>
	  <div class="layui-form-item">
	    <div class="layui-input-block">
	      <button class="layui-btn layui-btn-normal layui-btn-small" lay-submit lay-filter="saveRoleSel">提交</button>
	    </div>
	  </div>
	</form>
</div>
	
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
	
	layui.use('form', function() {
		var form = layui.form;
		form.on('submit(saveRoleSel)', function(data) {
			var id = $("input[name='id']").val();
			var selroles = $('#select2').find('option');
			var roleids = '';
			if (selroles) {
				$.each(selroles, function(idx, role){
					roleids += $(role).val() + ',';
				}); 
			}
			roleids = roleids.substring(0, roleids.length - 1);
			var user = {};
			user.id = id;
			user.roleids = roleids;
			$.ajax({
				url : "grantrole",
				type : "POST",
				dataType : "json",
				data : user,
				success : function(resultRet) {
							console.log(resultRet);
							if (resultRet.resultCode == '200') {
									parent.document.getElementById('rolegrantRes').value = "200";
								closeWin();
							} else {
								layer.alert(resultRet.resultMsg);
							}
						},
				error : function(e) {
							layer.open({
								title : "请求出错！",
								content : e.responseText
							});
						}
				});
		});
	});
	
	function closeWin() {
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	}
	</script>
</body>
</html>