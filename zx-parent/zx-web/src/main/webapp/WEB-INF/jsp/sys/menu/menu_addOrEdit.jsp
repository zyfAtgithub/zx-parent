<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../../common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="${ctx }/webResources/plugins/layui/css/layui.css">
<link rel="stylesheet" href="${ctx }/webResources/plugins/zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css">

	<style type="text/css">
		.layui-container{
			width:100%;
			padding:6px 8px;
		}
		
		ul.ztree {
		    margin-top: 10px;
		    border: 1px solid #c5c5c5;
		    background: #f5f5f5;
		    width: 220px;
		    height: 150px;
		    overflow: auto;
		}
	</style>
</head>
<body>

<div class="layui-container">
	<form class="layui-form" action="" onsubmit = "return false;" >
		<div class="layui-row">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">资源名称</label>
					<div class="layui-input-inline">
						<input type="text" name="menuname"  
					   	lay-verify="required" placeholder="请输入菜单名" value="${menu.menuname }" autocomplete="off" class="layui-input">
						<input type="hidden" name="id" value="${menu.id }"/>
					</div>
				</div>
				<div class="layui-inline">
				    <label class="layui-form-label">菜单url</label>
				    <div class="layui-input-inline">
				      <input type="text" name="menuurl" value="${menu.menuurl }"
				        placeholder="请输入菜单url" autocomplete="off" class="layui-input">
				    </div>
				</div>
			</div>
		</div>
		<div class="layui-row">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">父级</label>
					<div class="layui-input-inline">
						<input type="hidden" name="parentid" value="${menu.parentid }" />
						<input type="text" id="parentSel" readonly style="width:150px;display:inline"  autocomplete="off"  
					  	 placeholder="请选择父级" autocomplete="off" class="layui-input">
					  	&nbsp;<a id="clearParentSel" href="#">清空</a>
					</div>
				</div>
				<div class="layui-inline">
				    <label class="layui-form-label">菜单层级</label>
				    <div class="layui-input-inline">
				      <input type="number" name="level" readonly value="${menu.level }" autocomplete="off"  
				      lay-verify="required|number" autocomplete="off" class="layui-input">
				    </div>
				</div>
			</div>
		</div>
		<div class="layui-row">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">显示图标</label>
					<div class="layui-input-inline">
					<input type="text" name="menuicon" value="${menu.menuicon }" autocomplete="off"  
					   placeholder="请输入菜单显示图标" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-inline">
				   <label class="layui-form-label">树型图标</label>
				   <div class="layui-input-inline">
				     <input type="text" name="treeiconskin" value="${menu.treeiconskin }" autocomplete="off"  
				      placeholder="请输入菜单树显示图标" autocomplete="off" class="layui-input">
				    </div>
				</div>
			</div>
		</div>
		<div class="layui-row">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">排序</label>
					<div class="layui-input-inline">
					  <input type="number" name="menuorder" value="${menu.menuorder }" autocomplete="off"  
					  lay-verify="required|number" placeholder="请输入排序" min="0" max="99" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">权限表达式</label>
					<div class="layui-input-inline">
					  <input type="text" name="permexp" value="${menu.permexp }" autocomplete="off"  
					   placeholder="请输入权限表达式" autocomplete="off" class="layui-input">
					</div>
				</div>
			</div>
		</div>
		<div class="layui-row">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">是否为按钮</label>
					<div class="layui-input-inline">
						<c:if test="${menu.isbtn }">
							<input type="checkbox" name="isbtn" value="true" checked lay-verify="required" lay-skin="switch" lay-text="是|否">
						</c:if>
						<c:if test="${not menu.isbtn }">
							<input type="checkbox" name="isbtn" value="true" lay-verify="required" lay-skin="switch" lay-text="是|否">
						</c:if>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">是否展示</label>
					<div class="layui-input-inline">
						<c:if test="${menu.isshow }">
							<input type="checkbox" name="isshow" value="true" checked lay-verify="required" lay-skin="switch" lay-text="是|否">
						</c:if>
						<c:if test="${not menu.isshow }">
							<input type="checkbox" name="isshow" value="true" lay-verify="required" lay-skin="switch" lay-text="是|否">
						</c:if>
					</div>
				</div>
		</div>
		<div class="layui-row">
			<div class="layui-col-sm4 layui-col-sm-offset4">
				<div class="layui-form-item">
			    	<div class="layui-input-block">
						<button class="layui-btn layui-btn-normal layui-btn-small" lay-submit lay-filter="saveMenu">提交</button>
						<button type="reset" class="layui-btn layui-btn-primary layui-btn-small">重置</button>
				    </div>
			    </div>
			</div>
		</div>
	</form>
	<div id="menuContent" class="menuContent" style="display:none; position: absolute;">
		<ul id="menuTreeSel" class="ztree" style="margin-top:0; width:160px;"></ul>
	</div>
</div>
	
	<script src="${ctx }/webResources/js/jquery-2.1.1.min.js" type="text/javascript"></script>
	<script src="${ctx }/webResources/plugins/dropDownMenu.js" type="text/javascript"></script>
	<script src="${ctx }/webResources/plugins/layui/layui.all.js" type="text/javascript"></script>
	<script type="text/javascript" src="${ctx }/webResources/plugins/zTree_v3/js/jquery.ztree.core.js"></script>
	<script type="text/javascript" >
	layui.use('form', function() {
		var $ = layui.jquery, form = layui.form;
		form.on('submit(saveMenu)', function(data){
			if (data.field) {
				data.field.isbtn = data.field.isbtn ? data.field.isbtn : "false";
				data.field.isshow = data.field.isshow ? data.field.isshow : "false";
			}
			layer.alert(JSON.stringify(data.field));
			$.ajax({
				url:"save",
				type:'POST',
				data:data.field,
				dataType:'json',
				success:function(resultRet){
					if (resultRet.resultCode == '200') {
						if (data.field.id) {
							parent.document.getElementById('editMenuRes').value = "200";
						} else {
							parent.document.getElementById('addMenuRes').value = "200";
						}
						closeWin();
					} else {
						layer.alert(resultRet.resultMsg);
					}
				},
				error:function(e){
					layer.open({
						title : "请求出错！",
						content : e.responseText
					});
				}
			});
		});
		
		
		var setting = {
				view: {
					dblClickExpand: false,
					selectedMulti: false
				},
				data: {
					simpleData: {
						enable: true
					}
				},
				callback: {
					onClick: onClick
				}
			};
	
			function onClick(e, treeId, treeNode) {
				var zTree = $.fn.zTree.getZTreeObj("menuTreeSel"),
				nodes = zTree.getSelectedNodes();
				var selectedNode = nodes[0];
				$("#parentSel").val(selectedNode.name);
				$("input[name=parentid]").val(selectedNode.id);
				$("input[name=level]").val(selectedNode._level + 1);
			}
	
			function showMenu() {
				var cityObj = $("#parentSel");
				var cityOffset = $("#parentSel").offset();
				$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
	
				$("body").bind("mousedown", onBodyDown);
			}
			function hideMenu() {
				$("#menuContent").fadeOut("fast");
				$("body").unbind("mousedown", onBodyDown);
			}
			function onBodyDown(event) {
				if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
					hideMenu();
				}
			}
	
			$(function(){
				$.ajax({
					url : "/zx-web/menu",// json文件位置
					type : "GET",// 请求方式为get
					dataType : "json", // 返回数据格式为json
					success : function(result) {// 请求成功完成后要执行的方法
						var pid = '${menu.parentid }';
						if (result.resultCode == '200') {
							var zNodeArr = [];
							var zNode = {};
							zNode.id = 0;
							zNode.pId = -1;
							zNode.name = '所有菜单';
							zNode._level = '';
							zNodeArr.push(zNode);
							$.each(result.data, function(index, topMenu){
								//第一级
								var zNode = {};
								zNode.id = topMenu.id;
								zNode.pId = topMenu.parentid;
								zNode.name = topMenu.menuname;
								zNode._level = topMenu.level;
								if (pid && (pid == zNode.id)) {
									$('#parentSel').val(zNode.name);
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
										if (pid && (pid == zNode.id)) {
											$('#parentSel').val(zNode.name);
										}
										zNodeArr.push(zNode);
									});
								}
							});
							
							$.fn.zTree.init($("#menuTreeSel"), setting, zNodeArr);
							var treeObj = $.fn.zTree.getZTreeObj("menuTreeSel");
							var nodes = treeObj.getNodes();
							if (nodes.length>0) {
								//选择一级菜单
								$.each(nodes, function(idx, node){
									if (pid && (pid == node.id)) {
										treeObj.selectNode(node);
										return false;
									}
									if (node.children && node.children.length) {
										
										//选择二级菜单
										$.each(node.children, function(idx, subNode){
											if (pid && (pid == subNode.id)) {
												treeObj.selectNode(subNode);
												return false;
											}
											
											if (subNode.children && subNode.children.length) {
												
												//选择三级菜单
												$.each(subNode.children, function(idx, btn){
													if (pid && (pid == btn.id)) {
														treeObj.selectNode(btn);
														return false;
													}
												});
											}
										});
									}
								});
							}
						}
					}
				});
				$('#parentSel').click(function(){
					console.log($(this).attr('id'));
					var popDisplay = $("#menuContent").css('display');
					if (popDisplay == 'none') {
						var cityObj = $("#parentSel");
						var cityOffset = $("#parentSel").offset();
						$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
			
						$("body").bind("mousedown", onBodyDown);
					}
					else {
						$("#menuContent").fadeOut("fast");
					}
				});
				
				$("#clearParentSel").click(function(){
					$('#parentSel').val('');
					$("input[name=parentid]").val('');
					$("input[name=level]").val('1');
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