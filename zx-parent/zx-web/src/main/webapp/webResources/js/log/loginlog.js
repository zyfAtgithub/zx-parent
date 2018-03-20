layui.use([ 'form', 'table', 'laydate' ], function() {
    var table = layui.table, form = layui.form, laydate = layui.laydate, $ = layui.jquery;
    var tbIns = table.render({
        id : 'dataTableLoginLog',
        elem : '#loginLogTb',
        url : 'list',
        height : 'full-120',
        size : 'sm',
        cols : [ [ {
            checkbox : true,
            fixed : 'left'
        }, {
            field : 'loginuser',
            sort : true,
            width : 120,
            title : '登录用户'
        }, {
            field : 'ip',
            width : 150,
            title : '登录IP'
        }, {
            field : 'logintime',
            width : 180,
            title : '登录时间',
            templet : '#logintimeTpl'
        }, {
            field : 'logouttime',
            width : 180,
            title : '登出时间',
            templet : '#logouttimeTpl'
        }
        , {
            field : 'logindevice',
            width : 180,
            title : '登录设备'
        }, {
            field : 'loginResult',
            align : 'center',
            width : 150,
            title : '登录结果'
        }] ],
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

    //日期时间范围
    laydate.render({
        elem: '#logintime'
        ,type: 'datetime'
        ,range: true
        ,done: function(value, date, endDate){
            console.log(value); //得到日期生成的值，如：2017-08-18
            console.log(date); //得到日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
            console.log(endDate); //得结束的日期时间对象，开启范围选择（range: true）才会返回。对象成员同上。
        }
    });
    laydate.render({
        elem: '#logouttime'
        ,type: 'datetime'
        ,range: true
        ,done: function(value, date, endDate){
            console.log(value); //得到日期生成的值，如：2017-08-18
            console.log(date); //得到日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
            console.log(endDate); //得结束的日期时间对象，开启范围选择（range: true）才会返回。对象成员同上。
        }
    });
    $('#btn-batch-del').click(function() {
        var checkStatus = table.checkStatus('dataTableLoginLog');
        var delUsers = checkStatus.data;
        if (!delUsers.length) {
            layer.alert('请选择要删除的登录日志！');
            return;
        }

        layer.confirm('确定要删除吗？', {
            icon : 3,
            btn : [ '是', '否' ]
            // 按钮
        }, function() {
            var ids = "";
            $.each(delLogs, function(index, item) {
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

    form.on('submit(queryLoginLog)', function(data) {
        tbIns.reload({
            url : 'list',
            where : data.field
        });
        return false;
    });

    $('#btn-refresh').click(function() {
        tbIns.reload({
            url : 'list'
        });
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