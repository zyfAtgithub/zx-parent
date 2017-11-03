
var ctx = $("#ctxValue").val();

$(function() {
	$('#datagridDial').datagrid({
		title : '拨测历史记录',
		height : 390,
		url : ctx + '/webResources/json/dial.json',
		method : 'GET',
		// queryParams: { 'id': id },
		// idField: '产品ID',
		striped : true,
		fitColumns : true,
		singleSelect : false,
		rownumbers : true,
		pagination : true,
		nowrap : false,
		pageSize : 10,
		pageList : [ 10, 20, 50, 100, 150, 200 ],
		showFooter : true,
		columns : [ [ {
			field : 'dialStartTime',
			title : '拨测开始时间',
			width : 150,
			align : 'left'
		}, {
			field : 'dialEndTime',
			title : '拨测结束时间',
			width : 150,
			align : 'left'
		}, {
			field : 'dialUrl',
			title : '拨测URL',
			width : 180,
			align : 'center'
		}, {
			field : 'dialCount',
			title : '拨测数',
			width : 100,
			align : 'center'
		}, {
			field : 'dialSuccCount',
			title : '拨测成功数',
			width : 100,
			align : 'center'
		}, {
			field : 'dialSuccRate',
			title : '成功率（%）',
			width : 80,
			align : 'center'
		}, ] ],
		onBeforeLoad : function(param) {
		},
		onLoadSuccess : function(data) {

		},
		onLoadError : function() {

		},
		onClickCell : function(rowIndex, field, value) {

		}
	});

	// 分页

	// 提交事件
	$("#submitBtn").click(function() {
		$("#ff")[0].submit();
	});

	// 重置事件
	$("#resetBtn").click(function() {
		$("#ff")[0].reset();
	});
});