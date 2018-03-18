layui.use('form', function() {
	var form = layui.form;
	form.on('submit(saveRole)', function(data) {
		
		var treeObj = $.fn.zTree.getZTreeObj("treeMenu");
		var nodes = treeObj.getCheckedNodes(true);
		if (nodes && nodes.length > 0) {
			var permids = '';
			$.each(nodes, function(idx, node) {
				permids += node.id + ',';
			});
			permids = permids.substring(0, permids.length - 1);
			data.field.permids = permids;
		}
		$.ajax({
			url : "save",
			type : "POST",
			dataType : "json",
			data : data.field,
			success : function(resultRet) {
						if (resultRet.resultCode == '200') {
							if (data.field.id) {
								parent.document.getElementById('editRoleRes').value = "200";
							} else {
								parent.document.getElementById('addRoleRes').value = "200";
							}
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
	return false;
	});
});


function closeWin() {
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}