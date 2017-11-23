<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>用户管理</title>

<link rel="stylesheet" href="${ctx }/webResources/plugins/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="${ctx }/webResources/plugins/layui/css/layui.css">
<link rel="stylesheet" href="${ctx }/webResources/css/right-content.css">

<style type="text/css">
	.container{
		width:90%;
		margin: auto;
	}
	.query-panel{
		border: 1px solid #D9D9D9;
		padding:8px 12px 0px;
		background: #FCFCFC;
	}
	.batch-operation{
		border-top: 1px solid #D9D9D9;
		border-left: 1px solid #D9D9D9;
		border-right: 1px solid #D9D9D9;
		border-top-left-radius:4px;
		border-top-right-radius:4px;
		margin-top: 3px;
		margin-bottom: -11px; 
	}
	
	.table-container{
	}
</style>
</head>
<body>
	<div class="container">
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
				<table id="dataTb" lay-filter="userTbFilter"></table>
			</div>
		</div>
		<input type="hidden" id="addUserRes"/>
		<input type="hidden" id="editUserRes"/>
		
	</div>


	<!-- 日期格式化 -->
	<script type="text/html" id="timeTpl">
		{{ formatDatebox(d.lastloginTime) }}
	</script>

	<!-- 是否锁定 -->
	<script type="text/html" id="lockedTpl">
		{{ d.locked? '是' : '否' }}
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
	
	<script src="${ctx }/webResources/plugins/layui/layui.all.js" type="text/javascript"></script>
	<script>
	
	layui.use(['form','table'], function(){
		  var table = layui.table, form = layui.form, $ = layui.jquery;
		  var tbIns = table.render({
			id:'dataTableUser',
		    elem: '#dataTb',
		    url:'/zx-web/sys/user/list',
		    height: 'full-220',
		    size:'sm',
		    cols: [[
		      {checkbox: true, fixed:'left'},
		      {field:'loginname', sort:true,width:80, title: '登录名'},
		      {field:'username', width:120, title: '用户名'},
		      {field:'phone', width:180, title: '手机号'},
		      {field:'email', width:180, title: '邮箱'},
		      {field:'lastloginTime', align:'center', width:180, title: '上次成功登录时间', templet:'#timeTpl'},
		      {field:'locked', width:100, title: '是否锁定',templet:'#lockedTpl'},
		      {fixed: 'right', width:152, align:'center', toolbar: '#toolBar'}
		    ]],
		    limit:10,
		    limits:[5,10,15,20],
		    request: {
		      pageName: 'page' //页码的参数名称，默认：page
		      ,limitName: 'rows' //每页数据量的参数名，默认：limit
		    } ,             
		    response: {
		    	  statusName: 'code' //数据状态的字段名称，默认：code
		    	  ,statusCode: 200 //成功的状态码，默认：0
		    	  ,msgName: 'msg' //状态信息的字段名称，默认：msg
		    	  ,countName: 'total' //数据总数的字段名称，默认：count
		    	  ,dataName: 'rows' //数据列表的字段名称，默认：data
		    	},      
		    page: true,
		    even:true,//隔行背景
		  });
		  
		  $('#btn-add').click(function(){
			  //layer.msg('您点击了新增按钮');

			//多窗口模式，层叠置顶
		      layer.open({
		        type: 2, //iframe
		        title: '新增用户',
		        area: ['480px', '320px'],
		        //shade: 0,
		        //maxmin: true,
		        content: 'toadd',
		        yes: function(){
		          $(that).click(); 
		        },
		        btn2: function(){
		          layer.closeAll();
		        },
		        zIndex: layer.zIndex,
		        success: function(layero){
		          layer.setTop(layero);
		        },
		        end : function() {
		        	console.log($("#addUserRes").val());
		        	if ("200" == $("#addUserRes").val()) {
		        		$("#addUserRes").val('');
		        		layer.msg('新增用户成功', {icon:1}, function(){
		        			tbIns.reload();
			        	});
		       		 }
		        }
		      });
		  });
		  
		  $('#btn-batch-del').click(function(){
			  var checkStatus = table.checkStatus('dataTableUser');
		      var delUsers = checkStatus.data;
		      if (!delUsers.length) {
		    	  layer.alert('请选择要删除的用户！');
		    	  return;
		      }
		      
		      layer.confirm('确定要删除吗？', {
		    	  icon:3,
			      btn: ['是','否'] //按钮
		      }, function(){
		        var ids = "";
			      $.each(delUsers, function(index, item){
			    	  console.log(item.id);
			    	  ids += item.id + "|";
			      });
			      $.ajax({
			    	  url:"/zx-web/sys/user/del",
			    	  type:"POST",
			    	  dataType:"json",
			    	  data:{delIds:ids},
			    	  success:function(resultRet){
			    		  if (resultRet) {
							  layer.msg(resultRet.resultMsg, {icon: 6, time: 1000}, function(){
					 		      tbIns.reload({});
							  });
			    		  }
			    	  },
						error:function(e) {
							layer.open({
								title:"请求出错！",
								content:e.responseText
							});
						}
			      });
		      });
		  });

		  form.on('submit(queryUser)', function(data){
			  tbIns.reload({
				  url:'/zx-web/sys/user/list',
				  where:data.field
				  });
			  return false;
		  });
		  
		  $('#btn-refresh').click(function(){
			  tbIns.reload({ url:'/zx-web/sys/user/list'});
		  });
		  
		  
		  //监听工具条
		  table.on('tool(userTbFilter)', function(obj){
		    var data = obj.data;
		    if(obj.event === 'detail'){
		    	layer.open({
			        type: 2,
			        title: '查看用户',
			        area: ['440px', '280px'],
			        //shade: 0,
			        //maxmin: true,
			        content: 'toview?id=' + data.id,
			        zIndex: layer.zIndex, //重点1
			        success: function(layero){
			          layer.setTop(layero); //重点2
			        },
			        end : function() {}
			      });
		    } 
		    else if(obj.event === 'del'){
		    	layer.confirm('确定要删除吗？', {
		    		  icon:3,
		    		  btn: ['是','否'] //按钮
		    		}, function(){
		    		  var ids = data.id;
		    		  $.ajax({
				    	  url:"del",
				    	  type:"POST",
				    	  dataType:"json",
				    	  data:{delIds:ids},
				    	  success:function(resultRet){
				    		  if (resultRet) {
								  layer.msg(resultRet.resultMsg, {icon: 6, time: 1000}, function(){
						 		      tbIns.reload({});
								  });
				    		  }
				    	  },
							error:function(e) {
								layer.open({
									title:"请求出错！",
									content:e.responseText
								});
							}
				      });
		    		}, function(){
		    		  //layer.msg('不删除', {icon: 2});
		    		});
		    } else if(obj.event === 'edit'){
		      layer.open({
			        type: 2,
			        title: '修改用户',
			        area: ['480px', '320px'],
			        //shade: 0,
			        //maxmin: true,
			        content: 'toedit?id=' + data.id,
			        zIndex: layer.zIndex, //重点1
			        success: function(layero){
			          layer.setTop(layero); //重点2
			        },
			        end : function() {
			        	if ("200" == $("#editUserRes").val()) {
			        		$("#editUserRes").val('');
			        		layer.msg('修改用户成功', {icon:1}, function(){
			        			tbIns.reload();
				        	});
			       		 }
			        }
			      });
		    }
		  });
		});
	
	Date.prototype.format = function (format) {  
	    var o = {  
	        "M+": this.getMonth() + 1, // month  
	        "d+": this.getDate(), // day  
	        "h+": this.getHours(), // hour  
	        "m+": this.getMinutes(), // minute  
	        "s+": this.getSeconds(), // second  
	        "q+": Math.floor((this.getMonth() + 3) / 3), // quarter  
	        "S": this.getMilliseconds()  //millisecond  
	    }  
	    if (/(y+)/.test(format))  
	        format = format.replace(RegExp.$1, (this.getFullYear() + "")  
	            .substr(4 - RegExp.$1.length));  
	    for (var k in o)  
	        if (new RegExp("(" + k + ")").test(format))  
	            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));  
	    return format;  
	}  
	function formatDatebox(value) {  
	    if (value == null || value == '') {  
	        return '-';  
	    }  
	    var dt;  
	    if (value instanceof Date) {  
	        dt = value;  
	    } else {  
	        dt = new Date(value);  
	    }  
	  
	    return dt.format("yyyy-MM-dd hh:mm:ss"); //扩展的Date的format方法(上述插件实现)  
	}  
	
	</script>
</body>
</html>