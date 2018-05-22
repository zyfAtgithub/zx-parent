<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../../common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<%@include file="../../common/include.jsp"%>
	<style type="text/css">
		.layui-container{
			width:80%;
			padding:6px 8px;
		}
		#treeMenu{
			height:240px;
			overflow:auto;
			border:1px solid #C5C5C5;
		}
	</style>
</head>
<body>

<div class="layui-container">
		<form class="layui-form layui-form-pane" action="" onsubmit = "return false;" >
		  <div class="layui-form-item">
		    <label class="layui-form-label">角色名称<font style="color: red;font-size: 14px">*</font></label>
		    <div class="layui-input-block">
		      <input type="text" name="role"  
		       lay-verify="required" placeholder="请输入角色名称" value="${role.role }" autocomplete="off" class="layui-input">
		      <input type="hidden" name="id" value="${role.id }"/>
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">角色描述<font style="color: red;font-size: 14px">*</font></label>
		    <div class="layui-input-block">
		      <input type="text" name="description" value="${role.description }"
		       lay-verify="required" placeholder="请输入角色描述" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		  	<ul id="treeMenu" class="ztree"></ul>
		  </div>
		  <div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn layui-btn-normal layui-btn-small" lay-submit lay-filter="saveRole">提交</button>
		      <button type="reset" class="layui-btn layui-btn-primary layui-btn-small">重置</button>
		    </div>
		  </div>
		</form>
	</div>

	<script src="${ctx }/webResources/plugins/layui/layui.all.js" type="text/javascript"></script>
	<script src="${ctx }/webResources/js/sys/role/role_addOrEdit.js" type="text/javascript" ></script>
	<script type="text/javascript">
	var permids = '${role.permids}';
	
	var setting = {
			check: {
				enable: true,
				chkboxType: { "Y": "s", "N": "s" }
			},
			data: {
				simpleData: {
					enable: true
				},
			},
		};
		
		$(function(){
			loadMenuTree();
		});
		
		
		//加载菜单树
		function loadMenuTree() {
			$.ajax({
				url:'${ctx}/sys/menu/menuTree',
				type:'GET',
				dataType : "json",
				data:{},
				success:function(result){
					if (result.resultCode == '200') {
						var zNodeArr = [];
						var permidArr = [];
						if (permids != '') {
							permidArr = permids.split(',');
						}
						$.each(result.data, function(index, topMenu){
							//第一级
							var zNode = {};
							zNode.id = topMenu.id;
							zNode.pId = topMenu.parentid;
							zNode.name = topMenu.menuname;
							zNode._level = topMenu.level;
							zNode.iconSkin = topMenu.treeiconskin;
							if ($.inArray(zNode.id + '', permidArr) != -1) {
								zNode.checked = true;
							}
							zNodeArr.push(zNode);
							if (topMenu.children && topMenu.children.length) {
								//第二级
								$.each(topMenu.children, function(idx, subMenu){
									var zNode = {};
									zNode.id = subMenu.id;
									zNode.pId = subMenu.parentid;
									zNode.name = subMenu.menuname;
									zNode._level = subMenu.level;
									zNode.iconSkin = subMenu.treeiconskin;
									if ($.inArray(zNode.id + '', permidArr) != -1) {
										zNode.checked = true;
									}
									zNodeArr.push(zNode);
									if (subMenu.children && subMenu.children.length) {
										//第三级，按钮
										$.each(subMenu.children, function(idx, btn){
											var zNode = {};
											zNode.id = btn.id;
											zNode.pId = btn.parentid;
											zNode.name = btn.menuname;
											zNode._level = btn.level;
											zNode.iconSkin = btn.treeiconskin;
											if ($.inArray(zNode.id + '', permidArr) != -1) {
												zNode.checked = true;
											}
											zNodeArr.push(zNode);
										});
									}
								});
							}
							$.fn.zTree.init($("#treeMenu"), setting, zNodeArr);
						});
					}
					else {
						layer.alert(ret.resultMsg);
					}
				},
				error:function(e) {
					layer.open({
						title : "请求出错！",
						content : e.responseText
					});
				}
			});
		}
	</script>

</body>
</html>