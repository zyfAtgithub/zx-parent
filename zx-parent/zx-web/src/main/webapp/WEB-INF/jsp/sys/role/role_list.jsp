<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../../common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>角色管理</title>

<link rel="stylesheet" href="${ctx }/webResources/plugins/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="${ctx }/webResources/plugins/layui/css/layui.css">
<link rel="stylesheet" href="${ctx }/webResources/css/sys/role/role.css">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${ctx }/webResources/plugins/zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
</head>
<body>
	<div class="container">
		<div class="layui-row">
			<div class="layui-col-md10 right-listview">
				<div class="layui-row query-panel">
					<form class="layui-form layui-form-pane" action="" >
						<div class="layui-form-item">
							<div class="layui-inline">
								<label class="layui-form-label">角色名称</label>
					    		<div class="layui-input-inline">	
					    			<input type="text" name="role" value="${role}" autocomplete="off"
					    			 placeholder="请输入角色名称" autocomplete="off" 
					    			 class="layui-input">
					    		</div>
					    	</div>
				    		<div class="layui-inline">	
				    			<button class="layui-btn layui-btn-small" lay-submit lay-filter="queryRole">查询</button>
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
						<table id="roleTb" lay-filter="roleTbFilter"></table>
					</div>
				</div>
				<input type="hidden" id="addRoleRes"/>
				<input type="hidden" id="editRoleRes"/>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="${ctx }/webResources/js/jquery-2.1.1.min.js"></script>
	<script src="${ctx }/webResources/plugins/layui/layui.all.js" type="text/javascript"></script>
	<script type="text/javascript" src="${ctx }/webResources/plugins/zTree_v3/js/jquery.ztree.core.js"></script>
	
	<script type="text/html" id="toolBar">
		  <a class="layui-btn layui-btn-mini" lay-event="detail">查看</a>
		  <a class="layui-btn layui-btn-mini" lay-event="edit">编辑</a>
		  <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
	</script>
	
	<script type="text/javascript">
		layui.use([ 'form', 'table' ], function() {
			var table = layui.table, form = layui.form, $ = layui.jquery, curquery = {};
			var tbIns = table.render({
				id : 'dataTableRole',
				elem : '#roleTb',
				url : 'list',
				height : 'full-120',
				size : 'sm',
				cols : [ [ {
					checkbox : true,
					fixed : 'left'
				}, {
					field : 'role',
					width : 150,
					title : '角色名称'
				}, {
					field : 'description',
					width : 240,
					title : '描述'
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
			
			form.on('submit(queryRole)', function(data) {
				tbIns.reload({
					url : 'list',
					where : data.field
				});
				return false;
			});
			
			//新增菜单
			$('#btn-add').click(function() {
				
				// 多窗口模式，层叠置顶
				layer.open({
					type : 2, // iframe
					title : '新增角色',
					//anim: 1,
					area : [ '780px', '500px' ],
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
						if ("200" == $("#addRoleRes").val()) {
							$("#addRoleRes").val('');
							layer.msg('新增角色成功', {
								icon : 1,
								time : 1000
							}, function() {
								tbIns.reload();
							});
						}
					}
				});
				
			});

			//删除角色
			$('#btn-batch-del').click(function() {
				var checkStatus = table.checkStatus('dataTableRole');
				var delMenus = checkStatus.data;
				if (!delMenus.length) {
					layer.alert('请选择要删除的角色！');
					return;
				}
				
				layer.confirm('确定要删除吗？', {
					icon : 3,
					btn : [ '是', '否' ]
				// 按钮
				}, function() {
					var ids = "";
					$.each(delMenus, function(index, item) {
						console.log(item.id);
						ids += item.id + "|";
					});
					$.ajax({
						url : "del",
						type : "POST",
						dataType : "json",
						data : {
							delIds : ids
						},
						success : function(resultRet) {
							if (resultRet) {
								layer.msg(resultRet.resultMsg, {
									icon : 6,
									time : 1000
								}, function() {
									tbIns.reload();
								});
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
			
			$('#btn-refresh').click(function() {
				tbIns.reload();
			});
			
			// 监听工具条
			table.on('tool(roleTbFilter)', function(obj) {
				var data = obj.data;
				if (obj.event === 'detail') {
					layer.msg(JSON.stringify(data), {icon: 1});
				}
				else if (obj.event === 'del') {
					layer.confirm('确定要删除吗？', {
						icon : 3,
						btn : [ '是', '否' ]
					// 按钮
					}, function() {
						var ids = data.id;
						$.ajax({
							url : "del",
							type : "POST",
							dataType : "json",
							data : {
								delIds : ids
							},
							success : function(resultRet) {
								if (resultRet) {
									layer.msg(resultRet.resultMsg, {
										icon : 6,
										time : 1000
									}, function() {
										tbIns.reload();
									});
								}
							},
							error : function(e) {
								layer.open({
									title : "请求出错！",
									content : e.responseText
								});
							}
						});
					}, function() {
						// layer.msg('不删除', {icon: 2});
					});
				}
				else if (obj.event === 'edit') {
					// 多窗口模式，层叠置顶
					layer.open({
						type : 2, // iframe
						//anim: 1,
						title : '修改角色',
						area : [ '780px', '500px' ],
						// shade: 0,
						// maxmin: true,
						content : 'toedit?id='+data.id,
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
							if ("200" == $("#editRoleRes").val()) {
								$("#editRoleRes").val('');
								layer.msg('修改角色成功', {
									icon : 1,
									time : 1000
								}, function() {
									tbIns.reload();
								});
							}
						}
					});
				}
			});
		});
			
	</script>
</body>
</html>