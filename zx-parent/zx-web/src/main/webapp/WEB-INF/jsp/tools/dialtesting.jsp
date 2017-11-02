<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../common/taglib.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@include file="../common/include.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx }/webResources/css/form.css">

</head>
<body>
	<div class="main-content">
		<!-- 查询面板 -->
		<div class="easyui-panel query-panel" data-options="fit:true">
				<marquee  data-options="align:left,loop:-1,direction:'left',vspace:0,behavior:'scroll'"
				 height="36" width="300px" behavior="scroll" hspace="10" vspace="0" scrollamount="10" 
				 scrolldelay="400" onMouseOut="this.start()" onMouseOver="this.stop()">
					<a href="javascript:void()">拨测：呼叫中心 统一通信 融合通信专业资讯网系统测试功能。</a>
				</marquee>
                <form id="ff" method="post">  
                    <table>  
                        <tr>  
                            <th>拨测开始时间</th>  
                            <td>
                            	<input class="easyui-datetimebox" name="dialStartTime"
    								data-options="showSeconds:true" style="width:150px">
							</td>  
                            <th>拨测结束时间</th>  
                            <td>
                            	<input class="easyui-datetimebox" name="dialEndTime"
    								data-options="showSeconds:true" style="width:150px">
							</td>  
                            <th>拨测URL</th>  
                            <td>
                            	<input class="easyui-textbox" style="width:150px"/>  
                            </td>  
                        </tr>  
                        <tr> 
                        	<td colspan="6" class="td-btn">
                        		<a href="javascript:void(0)" class="easyui-linkbutton">查询</a>
                        		<a href="javascript:void(0)" class="easyui-linkbutton">重置</a>
                        	</td> 
                        </tr>  
                    </table>  
                </form>  
		</div>
		
		<!-- 列表 -->
		<div id="datagridDial">
		</div>
	</div>
	<script type="text/javascript">

		$(function() {
			$('#datagridDial').datagrid({
				title:'拨测历史记录',
			    height: 340,
			    url: '${ctx }/webResources/json/dial.json',
			    method: 'GET',
			    //queryParams: { 'id': id },
			    //idField: '产品ID',
			    striped: true,
			    fitColumns: true,
			    singleSelect: false,
			    rownumbers: true,
			    pagination: true,
			    nowrap: false,
			    pageSize: 10,
			    pageList: [10, 20, 50, 100, 150, 200],
			    showFooter: true,
			    columns: [[
			        { field: 'dialStartTime', title: '拨测开始时间', width: 150, align: 'left' },
			        { field: 'dialEndTime', title: '拨测结束时间', width: 150, align: 'left' },
			        { field: 'dialUrl', title: '拨测URL', width: 180, align: 'center' },
			        { field: 'dialCount', title: '拨测数', width: 100, align: 'center' },
			        { field: 'dialSuccCount', title: '拨测成功数', width: 100, align: 'center' },
			        { field: 'dialSuccRate', title: '成功率（%）', width: 80, align: 'center' },
			    ]],
			    onBeforeLoad: function (param) {
			    },
			    onLoadSuccess: function (data) {
			        
			    },
			    onLoadError: function () {
			        
			    },
			    onClickCell: function (rowIndex, field, value) {
			        
			    }
			});
		
			//分页
			
			
			 //提交事件  
            $("#submitBtn").click(function(){  
                $("#ff")[0].submit();  
            });  
              
            //重置事件  
            $("#resetBtn").click(function(){  
                $("#ff")[0].reset();  
            });  
		});
	</script>
</body>
</html>
