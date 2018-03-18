layui.use('form', function() {
	var form = layui.form;
	form.on('submit(saveUser)', function(data) {
		$.ajax({
			url : "save",
			type : "POST",
			dataType : "json",
			data : data.field,
			success : function(resultRet) {
						console.log(resultRet);
						if (resultRet.resultCode == '200') {
							if (data.field.id) {
								parent.document.getElementById('editUserRes').value = "200";
							} else {
								parent.document.getElementById('addUserRes').value = "200";
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

function initPassword() {
	var data = {};
	data.id = $("input[name='id']").val();
	data.loginname = $("input[name='loginname']").val();
	$.ajax({
		url : "initPassword",
		type : "POST",
		dataType : "json",
		data : data,
		success : function(resultRet) {
			if (resultRet.resultCode == '200') {
				layer.alert('密码初始化成功！');
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
}

function closeWin() {
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}