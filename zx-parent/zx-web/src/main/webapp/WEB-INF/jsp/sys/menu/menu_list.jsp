<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../../common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>菜单管理</title>

<link rel="stylesheet" href="${ctx }/webResources/plugins/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="${ctx }/webResources/plugins/layui/css/layui.css">
<link rel="stylesheet" href="${ctx }/webResources/css/right-content.css">
<link rel="stylesheet" href="${ctx }/webResources/css/sys/menu/menu.css">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${ctx }/webResources/plugins/zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
</head>
<body>
	<div class="container">
		<div class="layui-row">
			<div class="layui-col-md2 left-treeview">
				<ul id="treeMenu" class="ztree"></ul>
			</div>
			<div class="layui-col-md10 right-listview">
				<div class="layui-row query-panel">
					<form class="layui-form layui-form-pane" action="" >
						<div class="layui-form-item">
							<div class="layui-inline">
								<label class="layui-form-label">菜单名称</label>
					    		<div class="layui-input-inline">	
					    			<input type="text" name="menuname" value="${menuname}" autocomplete="off"
					    			 placeholder="请输入菜单名称" autocomplete="off" 
					    			 class="layui-input">
					    		</div>
					    	</div>
				    		<div class="layui-inline">	
				    			<button class="layui-btn layui-btn-small" lay-submit lay-filter="queryMenu">查询</button>
				    			<button class="layui-btn layui-btn-primary layui-btn-small" type="reset">重置</button>
				    		</div>
				    	</div>			
					</form>
				</div>
				<div class="layui-row">
					<div class="batch-operation">
						<div class="layui-btn-group">
						  <button class="layui-btn  layui-btn-small" id="btn-add">
						    <i class="layui-icon">&#xe654;</i>
						  </button>
						  <button class="layui-btn layui-btn-danger layui-btn-small" id="btn-batch-del">
						    <i class="layui-icon">&#xe640;</i>
						  </button>
						  <button class="layui-btn layui-btn-normal layui-btn-small" id="btn-refresh">
						    <i class="layui-icon">&#x1002;</i>
						  </button>
						</div>
					</div>
					<div class="table-container">
						<table id="menuTb" lay-filter="menuTbFilter"></table>
					</div>
				</div>
				<input type="hidden" id="addMenuRes"/>
				<input type="hidden" id="editMenuRes"/>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="${ctx }/webResources/js/jquery-2.1.1.min.js"></script>
	<script src="${ctx }/webResources/plugins/layui/layui.all.js" type="text/javascript"></script>
	<script type="text/javascript" src="${ctx }/webResources/plugins/zTree_v3/js/jquery.ztree.core.js"></script>
	
	<!-- 图标可视化 -->
	<script type="text/html" id="iconTpl">
		<i class="{{d.menuicon}}"></i>
	</script>

	<script type="text/html" id="toolBar">
		  <a class="layui-btn layui-btn-mini" lay-event="detail">查看</a>
		  <a class="layui-btn layui-btn-mini" lay-event="edit">编辑</a>
		  <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
		  
		  <!-- 这里同样支持 laytpl 语法，如： -->
		  {{#  if(d.auth > 2){ }}
		    <a class="layui-btn layui-btn-mini" lay-event="check">审核</a>
		  {{#  } }}
	</script>
	
	<script type="text/javascript">
		layui.use([ 'form', 'table' ], function() {
			var table = layui.table, form = layui.form, $ = layui.jquery, curquery = {level:1,partentid:0};
			var tbIns = table.render({
				id : 'dataTableMenu',
				elem : '#menuTb',
				url : 'menuPage?level=1',
				height : 'full-120',
				size : 'sm',
				cols : [ [ {
					checkbox : true,
					fixed : 'left'
				}, {
					field : 'menuname',
					width : 120,
					title : '菜单名称'
				}, {
					field : 'menuurl',
					width : 120,
					title : '菜单url'
				}, {
					field : 'level',
					width : 100,
					title : '菜单层级'
				}, {
					field : 'menuorder',
					width : 100,
					title : '排序'
				}, {
					field : 'menuicon',
					align : 'center',
					width : 180,
					title : '菜单显示图标',
					templet : '#iconTpl'
				}, {
					field : 'isbtn',
					align : 'center',
					width : 150,
					title : '是否是按钮'
				},{
					fixed : 'right',
					width : 152,
					align : 'center',
					toolbar : '#toolBar'
				} ] ],
				limit : 10,
				limits : [ 5, 10, 15, 20 ],
				request : {
					pageName : 'page', // 页码的参数名称，默认：page
					limitName : 'rows' // 每页数据量的参数名，默认：limit
				},
				response : {
					statusName : 'code', // 数据状态的字段名称，默认：code
					statusCode : 200, // 成功的状态码，默认：0
					msgName : 'msg', // 状态信息的字段名称，默认：msg
					countName : 'total', // 数据总数的字段名称，默认：count
					dataName : 'rows' // 数据列表的字段名称，默认：data
				},
				page : true,
				even : true,// 隔行背景
			});
			
			form.on('submit(queryMenu)', function(data) {
				data.field.level = curquery.level;
				data.field.parentid = curquery.parentid;
				tbIns.reload({
					url : 'menuPage',
					where : data.field
				});
				return false;
			});
			
			//新增菜单
			$('#btn-add').click(function() {
				if (curquery.level - 1 == 3) {
					layer.alert('按钮下不能添加菜单！', {icon:2});
					return;
				}
				
				console.log(curquery);
				
				// 多窗口模式，层叠置顶
				layer.open({
					type : 2, // iframe
					title : '新增菜单',
					area : [ '550px', '500px' ],
					// shade: 0,
					// maxmin: true,
					content : 'toadd',
					yes : function() {
						$(that).click();
					},
					btn2 : function() {
						layer.closeAll();
					},
					zIndex : layer.zIndex,
					success : function(layero) {
						layer.setTop(layero);
					},
					end : function() {
						console.log($("#addMenuRes").val());
						if ("200" == $("#addMenuRes").val()) {
							$("#addMenuRes").val('');
							layer.msg('新增用户成功', {
								icon : 1,
								time : 1000
							}, function() {
								tbIns.reload();
							});
						}
					}
				});
				
			});

			//新增菜单
			$('#btn-batch-del').click(function() {
				layer.msg('删除菜单啦！');
			});
			
			$('#btn-refresh').click(function() {
				tbIns.reload();
			});
			
			// 监听工具条
			table.on('tool(menuTbFilter)', function(obj) {
				var data = obj.data;
				if (obj.event === 'detail') {
					layer.msg(JSON.stringify(data), {icon: 1});
				}
				else if (obj.event === 'del') {
					layer.msg(JSON.stringify(data), {icon: 2});
				}
				else if (obj.event === 'edit') {
					layer.msg(JSON.stringify(data), {icon: 3});
				}
			});
			
			var setting = {
					data: {
						simpleData: {
							enable: true
						},
					},
					callback: {
						onClick: zTreeOnClick
					}
				};
				
				function zTreeOnClick(event, treeId, treeNode) {
				    curquery.parentid = treeNode.id;
				    curquery.level = treeNode._level + 1;
				    tbIns.reload({url:'menuPage',where:curquery});
				};
				
				$(function(){
					$.ajax({
						url:'menuTree',
						type:'GET',
						dataType : "json",
						data:{},
						success:function(result){
							if (result.resultCode == '200') {
								var zNodeArr = [];
								$.each(result.data, function(index, topMenu){
									//第一级
									var zNode = {};
									zNode.id = topMenu.id;
									zNode.pId = topMenu.parentid;
									zNode.name = topMenu.menuname;
									zNode._level = topMenu.level;
									zNode.iconSkin = topMenu.treeiconskin;
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
				});
			
		});
			
	</script>
</body>
</html>