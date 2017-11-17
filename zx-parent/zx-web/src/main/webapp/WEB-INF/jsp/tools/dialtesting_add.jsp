<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../common/taglib.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@include file="../common/include.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${ctx }/webResources/css/form.css">
<script type="text/javascript"
	src="${ctx }/webResources/js/tools/dialtesting.js"></script>
</head>
<body>
	<div class="easyui-panel query-panel" title="新增拨测任务"
		data-options="collapsible:true"
		style="width: 500px; height: 200px; padding: 10px;">
		<form>
			<table>
				<tr>
					<th>拨测URL</th>
					<td colspan="3"><input class="easyui-textbox" style="width: 240px" /></td>
				</tr>
				<tr>
					<th>代理IP</th>
					<td colspan="3"><input class="easyui-textbox" style="width: 240px" /></td>
				</tr>
				<tr>
					<th>拨测批次数</th>
					<td><input class="easyui-numberbox" style="width: 100px" /></td>
					<th>拨测数</th>
					<td><input class="easyui-numberbox" style="width: 100px" /></td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a id="btnEp" class="easyui-linkbutton" data-options="icon:'icon-ok'" href="javascript:void(0)" >确定</a>
						<a id="btnCancel" class="easyui-linkbutton" data-options="icon:icon-undo" href="javascript:void(0)">取消</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
