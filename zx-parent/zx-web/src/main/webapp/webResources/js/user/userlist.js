layui.use([ 'form', 'table' ], function() {
	var table = layui.table, form = layui.form, $ = layui.jquery;
	var tbIns = table.render({
		id : 'dataTableUser',
		elem : '#userTb',
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

	$('#btn-add').click(function() {
		// 多窗口模式，层叠置顶
		layer.open({
			type : 2, // iframe
			title : '新增用户',
			area : [ '480px', '320px' ],
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
				console.log($("#addUserRes").val());
				if ("200" == $("#addUserRes").val()) {
					$("#addUserRes").val('');
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

	$('#btn-batch-del').click(function() {
		var checkStatus = table.checkStatus('dataTableUser');
		var delUsers = checkStatus.data;
		if (!delUsers.length) {
			layer.alert('请选择要删除的用户！');
			return;
		}

		layer.confirm('确定要删除吗？', {
			icon : 3,
			btn : [ '是', '否' ]
		// 按钮
		}, function() {
			var ids = "";
			$.each(delUsers, function(index, item) {
				console.log(item.id);
				ids += item.id + "|";
			});
			$.ajax({
				url : "/zx-web/sys/user/del",
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
							tbIns.reload({});
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

	form.on('submit(queryUser)', function(data) {
		tbIns.reload({
			url : '/zx-web/sys/user/list',
			where : data.field
		});
		return false;
	});

	$('#btn-refresh').click(function() {
		tbIns.reload({
			url : '/zx-web/sys/user/list'
		});
	});

	// 监听工具条
	table.on('tool(userTbFilter)', function(obj) {
		var data = obj.data;
		if (obj.event === 'detail') {
			layer.open({
				type : 2,
				title : '查看用户',
				area : [ '440px', '280px' ],
				// shade: 0,
				// maxmin: true,
				content : 'toview?id=' + data.id,
				zIndex : layer.zIndex, // 重点1
				success : function(layero) {
					layer.setTop(layero); // 重点2
				},
				end : function() {
				}
			});
		} else if (obj.event === 'del') {
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
								tbIns.reload({});
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
		} else if (obj.event === 'edit') {
			layer.open({
				type : 2,
				title : '修改用户',
				area : [ '480px', '320px' ],
				// shade: 0,
				// maxmin: true,
				content : 'toedit?id=' + data.id,
				zIndex : layer.zIndex, // 重点1
				success : function(layero) {
					layer.setTop(layero); // 重点2
				},
				end : function() {
					if ("200" == $("#editUserRes").val()) {
						$("#editUserRes").val('');
						layer.msg('修改用户成功', {
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

Date.prototype.format = function (format) {  
    var o = {  
        "M+": this.getMonth() + 1, // month
        "d+": this.getDate(), // day
        "h+": this.getHours(), // hour
        "m+": this.getMinutes(), // minute
        "s+": this.getSeconds(), // second
        "q+": Math.floor((this.getMonth() + 3) / 3), // quarter
        "S": this.getMilliseconds()  // millisecond
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
  
    return dt.format("yyyy-MM-dd hh:mm:ss"); // 扩展的Date的format方法(上述插件实现)
}