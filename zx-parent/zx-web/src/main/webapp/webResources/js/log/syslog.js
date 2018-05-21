layui.use([ 'form', 'table', 'laydate' ], function() {
    var table = layui.table, form = layui.form, laydate = layui.laydate, $ = layui.jquery;
    var tbIns = table.render({
        id : 'dataTableSysLog',
        elem : '#sysLogTb',
        url : 'list',
        height : 'full-120',
        size : 'sm',
        cols : [ [ {
            checkbox : true,
            fixed : 'left'
        }, {
            field : 'ipAddress',
            sort : true,
            width : 120,
            title : '交换机IP'
        }, {
            field : 'generateTime',
            width : 180,
            title : '日志生成时间',
            templet : '#generateTimeTpl'
        }, {
            field : 'logLevel',
            width : 80,
            title : '日志级别',
            templet : '#logLevelTpl'
        }
        , {
            field : 'msg',
            width : 600,
            title : '日志'
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

    form.on('submit(querySysLog)', function(data) {
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

    //排序
    table.on('sort(sysLogTbFilter)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        console.log(obj.field); //当前排序的字段名
        console.log(obj.type); //当前排序类型：desc（降序）、asc（升序）、null（空对象，默认排序）
        console.log(this); //当前排序的 th 对象

        //尽管我们的 table 自带排序功能，但并没有请求服务端。
        //有些时候，你可能需要根据当前排序的字段，重新向服务端发送请求，从而实现服务端排序，如：
        // table.reload('idTest', {
        //     initSort: obj //记录初始排序，如果不设的话，将无法标记表头的排序状态。 layui 2.1.1 新增参数
        //     ,where: { //请求参数（注意：这里面的参数可任意定义，并非下面固定的格式）
        //         field: obj.field //排序字段
        //         ,order: obj.type //排序方式
        //     }
        // });
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