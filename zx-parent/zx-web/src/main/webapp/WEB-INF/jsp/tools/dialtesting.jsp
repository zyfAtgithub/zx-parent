<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../common/taglib.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@include file="../common/include.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx }/webResources/css/form.css">
<link rel="stylesheet" type="text/css" href="${ctx }/webResources/plugins/layui/css/layui.css">
</head>
<body>
	<div class="main-content">
		<!-- 查询面板 -->
		<div class="query-panel" data-options="fit:true">
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
                        	<td colspan="6" class="self-btn-group">
	                        	<a id="submitBtn" class="layui-btn layui-btn-radius layui-btn-small">
								  <i class="layui-icon">&#xe615;</i> 查询
								</a>
	                        	<a id="resetBtn" class="layui-btn layui-btn-radius layui-btn-primary layui-btn-small">
								  <i class="layui-icon">&#xe603;</i> 重置
								</a>
                        	</td> 
                        </tr>  
                    </table>  
                </form>  
		</div>
		
		<!-- 列表 -->
		<div id="datagridDial" style="width:100%">
		</div>
	</div>

	<script type="text/javascript" src="${ctx }/webResources/plugins/layui/layui.all.js"></script>
	<script type="text/javascript" src="${ctx }/webResources/js/tools/dialtesting.js"></script>
	<script type="text/javascript">
	</script>
</body>
</html>
