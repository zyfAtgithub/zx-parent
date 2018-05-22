<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../../common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@include file="../../common/include.jsp"%>
<title>交换机Syslog</title>
</head>
<body>
	<div class="container">
		<div class="layui-row query-panel">
			<form class="layui-form layui-form-pane" action="" >
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">ip地址</label>
			    		<div class="layui-input-inline">
			    			<input type="text" name="ipAddress" value="${ipAddress}" autocomplete="off" placeholder="请输入IP" class="layui-input">
			    		</div>
			    	</div>
		    		<div class="layui-inline">
						<button class="layui-btn layui-btn-small" lay-submit lay-filter="querySysLog">查询</button>
						<button class="layui-btn layui-btn-primary layui-btn-small" type="reset">重置</button>
		    		</div>
		    	</div>
				<input type="hidden" name="orderBy" value="id desc" />
			</form>
		</div>
		<div class="layui-row">
			<div class="batch-operation">
				<div class="layui-btn-group">
					<shiro:hasPermission name="log:loginlog:batchdel">
						<button class="layui-btn layui-btn-danger layui-btn-small" id="btn-batch-del">
							<i class="layui-icon">&#xe640;</i>
						</button>
					</shiro:hasPermission>
				  <button class="layui-btn layui-btn-normal layui-btn-small" id="btn-refresh">
				    <i class="layui-icon">&#x1002;</i>
				  </button>
				</div>
			</div>
			<div class="table-container">
				<table id="sysLogTb" lay-filter="sysLogTbFilter"></table>
			</div>
		</div>
		<input type="hidden" name="logintimeBegin"/>
		<input type="hidden" name="logintimeEnd"/>
		<input type="hidden" name="logouttimeBegin"/>
		<input type="hidden" name="logouttimeEnd"/>
	</div>

	<script src="${ctx }/webResources/plugins/layui/layui.all.js" type="text/javascript"></script>
	<!-- 日期格式化 -->
	<script type="text/html" id="createtimeTpl">
		{{ formatDatebox(d.createTime) }}
	</script>
	<script type="text/html" id="generateTimeTpl">
		{{ formatDatebox(d.generateTime) }}
	</script>

	<!-- 日志级别-->
	<script type="text/html" id="logLevelTpl">
		{{#
			var fn =function(logLevel){
				var logDes = '';
				switch(logLevel) {
					case '0':
					logDes = 'emerg';
					break;
					case '1':
					logDes = 'alert';
					break;
					case '2':
					logDes = 'crit';
					break;
					case '3':
					logDes = 'err';
					break;
					case '4':
					logDes = 'err';
					break;
					case '5':
					logDes = 'notice';
					break;
					case '6':
					logDes = 'info';
					break;
					case '7':
					logDes = 'debug';
					break;
				}
				return logDes;
			}
		}}

		{{ fn(d.logLevel)}}
	</script>

	<script src="${ctx }/webResources/js/log/syslog.js" type="text/javascript"></script>
</body>
</html>