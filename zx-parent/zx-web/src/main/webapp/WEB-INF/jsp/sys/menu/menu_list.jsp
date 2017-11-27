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
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${ctx }/webResources/plugins/zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
<style type="text/css">
	
	.container {
		width:90%;
		margin: auto;
	}
	.left-treeview {
		height:532px;
		overflow-x:auto;
		overflow-y:auto; 
		border:1px solid #D1D7DC;
		position: relative;
	}
	
	.right-listview {
		padding-left: 4px;
	}
	
	.right-listview .query-panel{
		border: 1px solid #D9D9D9;
		padding:8px 12px 0px;
		background: #FCFCFC;
	}
	
	.right-listview .batch-operation{
		border-top: 1px solid #D9D9D9;
		border-left: 1px solid #D9D9D9;
		border-right: 1px solid #D9D9D9;
		border-top-left-radius:4px;
		border-top-right-radius:4px;
		margin-top: 3px;
		margin-bottom: -11px; 
	}
	
	.ztree li span.button.pIcon01_ico_docu{
		margin-right:2px; 
		background: url(${ctx }/webResources/plugins/zTree_v3/css/zTreeStyle/img/diy/1_close.png) no-repeat scroll 0 0 transparent; 
		vertical-align:top; 
		*vertical-align:middle
	}

	.ztree li span.button.pIconadd_ico_docu{
		margin-right:2px; 
		background: url(${ctx }/webResources/images/tabicons.png) no-repeat scroll 0 0 transparent; 
		vertical-align:top;
		*vertical-align:middle;
		background-position: -20px 0px;
		width:16px;
		line-height:16px; 
		display:inline-block;
	}
	.ztree li span.button.pIconedit_ico_docu{
		margin-right:2px; 
		background: url(${ctx }/webResources/images/tabicons.png) no-repeat scroll 0 0 transparent; 
		vertical-align:top;
		*vertical-align:middle;
		background-position:-380px -320px;
		width:16px;
		line-height:16px; 
		display:inline-block;
	}
	.ztree li span.button.pIcondel_ico_docu{
		margin-right:2px; 
		background: url(${ctx }/webResources/images/tabicons.png) no-repeat scroll 0 0 transparent; 
		vertical-align:top;
		*vertical-align:middle;
		background-position:-140px -120px;
		width:16px;
		line-height:16px; 
		display:inline-block;
	}
</style>
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
								<label class="layui-form-label">登录名</label>
					    		<div class="layui-input-inline">	
					    			<input type="text" name="loginname" value="${loginname}" autocomplete="off"
					    			 placeholder="请输入登录名" autocomplete="off" 
					    			 class="layui-input">
					    		</div>
					    	</div>
							<div class="layui-inline">
								<label class="layui-form-label">用户名</label>
					    		<div class="layui-input-inline">	
					    			<input type="text" name="username" value="${username}" autocomplete="off"
					    			 placeholder="请输入用户名" autocomplete="off" 
					    			 class="layui-input">
					    		</div>
					    	</div>
				    		<div class="layui-inline">	
				    			<button class="layui-btn layui-btn-small" lay-submit lay-filter="queryUser">查询</button>
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
			</div>
		</div>
	</div>

	<script type="text/javascript" src="${ctx }/webResources/js/jquery-2.1.1.min.js"></script>
	<script src="${ctx }/webResources/plugins/layui/layui.all.js" type="text/javascript"></script>
	<script type="text/javascript" src="${ctx }/webResources/plugins/zTree_v3/js/jquery.ztree.core.js"></script>
	<script type="text/javascript">
		var setting = {
				data: {
					simpleData: {
						enable: true
					},
				}
			};
	
			var zNodes =[
				{ id:1, pId:0, name:"首页", iconSkin:"pIcon01"},
				{ id:2, pId:0, name:"系统管理"},
				{ id:22, pId:2, name:"用户管理"},
				{ id:221, pId:22, name:"新增",iconSkin:"pIconadd" },
				{ id:222, pId:22, name:"修改",iconSkin:"pIconedit" },
				{ id:223, pId:22, name:"删除",iconSkin:"pIcondel" },
				{ id:23, pId:2, name:"角色管理"},
				{ id:24, pId:2, name:"权限管理"},
				{ id:25, pId:2, name:"菜单管理" },
			];
	
			$(document).ready(function(){
				$.ajax({
					url:'menuTree',
					type:'GET',
					dataType : "json",
					data:{},
					success:function(result){
						console.log(result);
						var zNodeArr = [];
						$.each(result, function(index, item){
							//第一级
							var zNode = {};
							zNode.id = item.id;
							zNode.pId = item.parentid;
							zNode.name = item.menuname;
							zNode.iconSkin = item.
						});
					},
					error:function(e) {
						layer.open({
							title : "请求出错！",
							content : e.responseText
						});
					}
				});
				$.fn.zTree.init($("#treeMenu"), setting, zNodes);
			});
			
			layui.use([ 'form', 'table' ], function() {
				var table = layui.table, form = layui.form, $ = layui.jquery;
				var tbIns = table.render({
					id : 'dataTableUser',
					elem : '#menuTb',
					url : '/zx-web/sys/user/list',
					height : 'full-120',
					size : 'sm',
					cols : [ [ {
						checkbox : true,
						fixed : 'left'
					}, {
						field : 'loginname',
						sort : true,
						width : 120,
						title : '登录名'
					}, {
						field : 'username',
						width : 120,
						title : '用户名'
					}, {
						field : 'phone',
						width : 180,
						title : '手机号'
					}, {
						field : 'email',
						width : 180,
						title : '邮箱'
					}, {
						field : 'lastloginTime',
						align : 'center',
						width : 150,
						title : '上次成功登录时间',
						templet : '#timeTpl'
					}, {
						field : 'locked',
						width : 100,
						title : '是否锁定',
						templet : '#lockedTpl'
					}, {
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
			});
			
	</script>
</body>
</html>