<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../common/taglib.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@include file="../common/include.jsp"%>

<style type="text/css">

	.main-content {
		padding-bottom: 5px;
	}
	.query-panel {
		margin-bottom: 10px;
		height:105px;
	}
</style>

</head>
<body>
	<div class="main-content">
		<!-- 查询面板 -->
		<div class="easyui-panel query-panel" data-options="fit:true">
			<form id="ff" method="post">
				<div style="margin-bottom: 20px">
					<input class="easyui-textbox" name="name" style="width: 100%"
						data-options="label:'Name:',required:true">
				</div>
				<div style="margin-bottom: 20px">
					<input class="easyui-textbox" name="email" style="width: 100%"
						data-options="label:'Email:',required:true,validType:'email'">
				</div>
				<div style="margin-bottom: 20px">
					<input class="easyui-textbox" name="subject" style="width: 100%"
						data-options="label:'Subject:',required:true">
				</div>
				<div style="margin-bottom: 20px">
					<input class="easyui-textbox" name="message"
						style="width: 100%; height: 60px"
						data-options="label:'Message:',multiline:true">
				</div>
				<div style="margin-bottom: 20px">
					<select class="easyui-combobox" name="language" label="Language"
						style="width: 100%"><option value="ar">Arabic</option>
						<option value="bg">Bulgarian</option>
					</select>
				</div>
			</form>
			<div style="text-align: center; padding: 5px 0">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="submitForm()" style="width: 80px">Submit</a> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					onclick="clearForm()" style="width: 80px">Clear</a>
			</div>
		</div>
		<table id="dg" title="拨测记录"
			style="width: 700px; height: 250px"
			data-options="rownumbers:true,singleSelect:true,pagination:true,url:'${ctx }/webResources/json/dial.json',method:'get'">
			<thead>
				<tr>
					<th data-options="field:'dialStartTime',align:'center',width:150">拨测开始时间</th>
					<th data-options="field:'dialEndTime',align:'center',width:150">拨测结束时间</th>
					<th data-options="field:'dialUrl',align:'center',width:300">拨测URL</th>
					<th data-options="field:'dialCount',align:'center',width:130">拨测数</th>
					<th data-options="field:'dialSuccCount',align:'center',width:130">拨测成功数</th>
					<th data-options="field:'dialSuccRate',align:'center',width:100">成功率（%）</th>
				</tr>
			</thead>
		</table>
	</div>
	<script type="text/javascript">
		$(function() {
			$('#dg').datagrid().datagrid({
				fit: true
			});
			
			//设置分页控件 
			var pager = $('#dg').datagrid('getPager'); 
			$(pager).pagination({ 
		        pageSize: 10,//每页显示的记录条数，默认为10 
		        pageList: [5,10,15],//可以设置每页记录条数的列表 
		        beforePageText: '第',//页数文本框前显示的汉字 
		        afterPageText: '页    共 {pages} 页', 
		        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
		        onBeforeRefresh:function(){
		          /*   $(this).pagination('loading');
		            alert('before refresh');
		            $(this).pagination('loaded'); */
		        } 
		    }); 
		})
	</script>
</body>
</html>
