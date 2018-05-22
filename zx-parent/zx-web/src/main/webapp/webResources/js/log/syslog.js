layui.use([ 'form', 'table', 'laydate' ], function() {
    var table = layui.table, form = layui.form, laydate = layui.laydate, $ = layui.jquery;
    var queryCondition = {};
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
            sort : true,
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
        queryCondition = data.field;
        tbIns.reload({
            url : 'list',
            where : data.field
        });
        return false;
    });

    $('#btn-refresh').click(function() {
        tbIns.reload({
            url : 'list',
            where : queryCondition
        });
    });

    //排序
    table.on('sort(sysLogTbFilter)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        if (!queryCondition) {
            queryCondition = {};
        }

        if (obj.field && obj.type) {
            queryCondition.orderBy = camel2UnderLine(obj.field) + ' ' + obj.type;
        } else {
            delete queryCondition.orderBy;
        }
        console.log(queryCondition);
        //根据当前排序的字段，重新向服务端发送请求，从而实现服务端排序
        tbIns.reload({
            initSort: obj,
            url : 'list',
            where : queryCondition
        });
    });
});