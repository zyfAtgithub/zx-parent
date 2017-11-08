<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@include file="common/include.jsp"%>
<link rel="stylesheet" type="text/css" href="webResources/css/index.css">
<script type="text/javascript" src='webResources/js/index.js'> </script>
<title>文件管理系统</title>

<style type="text/css">
	.north {
		overflow: hidden;
		height: 40px;
       	background: url(webResources/images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
       	line-height: 40px;
       	color: #fff; 
       	font-family: Verdana, 微软雅黑,黑体;
	}
	.north a {
	    color: White;
	    text-decoration: underline;
	}
	.panel-body {
	    overflow: hidden;
	    border-top-width: 0;
	    padding: 0;
	}
</style>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',split:true,border:false" class="north">
		<span style="float:right; padding-right:20px;" class="head">
       	<shiro:principal/>
       	<a href="#" id="editpass">修改密码</a>
		<a href="javascript:void(0)" id="loginOut">安全退出</a></span>
       	<span style="padding-left:10px; font-size: 16px; ">
       	<img src="webResources/images/favicon.ico" style="width:20px;height:20px;margin-right: 5px"/>文件管理系统</span>
	</div>
	<div data-options="region:'west',split:true,title:'导航菜单'" style="width:180px; ">
		<div id="nav" class="easyui-accordion" data-options="fit:true,border:false">
		</div>
	</div>
	<div id="mainPanle" data-options="region:'center'" style="background: #eee; overflow:hidden;">
    	<div id="tabs" class="easyui-tabs"  data-options="fit:true,border:true" >
		</div>
	</div>
	
	<!--修改密码窗口-->
    <!-- <div id="w" class="easyui-window" title="修改密码" collapsible="false" minimizable="false"
        maximizable="false" icon="icon-save"  style="display:none; width: 300px; height: 150px; padding: 5px;
        background: #fafafa;">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
                <table cellpadding=3>
                    <tr>
                        <td>新密码：</td>
                        <td><input id="txtNewPass" type="password" class="txt01" /></td>
                    </tr>
                    <tr>
                        <td>确认密码：</td>
                        <td><input id="txtRePass" type="password" class="txt01" /></td>
                    </tr>
                </table>
            </div>
            <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
                <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" >
                    确定</a> <a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a>
            </div>
        </div>
    </div>

	<div id="mm" class="easyui-menu" style="width:150px;display:none">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div> -->
</body>
<script type="text/javascript">
	 var _menus = {"menus":[
						{"menuid":"1","icon":"icon-welcome","menuname":"首页",
							"menus":[
								{"menuid":"11","menuname":"欢迎使用","icon":"icon-welcome","url":"welcome"},
							]
						},
						{"menuid":"2","icon":"icon-sys","menuname":"系统管理",
							"menus":[
								{"menuid":"21","menuname":"用户管理","icon":"icon-users","url":"tools/dialtesting"},
								{"menuid":"22","menuname":"权限管理","icon":"icon-role","url":"tools/dialtesting_add"},
								]
						},
						{"menuid":"3","icon":"icon-cmd","menuname":"实用工具",
							"menus":[
								{"menuid":"31","menuname":"拨测工具","icon":"icon-dial","url":"tools/dialtesting"},
								{"menuid":"32","menuname":"新增拨测任务","icon":"icon-dial","url":"tools/dialtesting_add"},
								]
						}
				]};
        //设置登录窗口
        function openPwd() {
            $('#w').window({
                title: '修改密码',
                width: 300,
                modal: true,
                shadow: true,
                closed: true,
                height: 160,
                resizable:false
            });
        }
        //关闭登录窗口
        function closePwd() {
            $('#w').window('close');
        }

        

        //修改密码
        function serverLogin() {
            var $newpass = $('#txtNewPass');
            var $rePass = $('#txtRePass');

            if ($newpass.val() == '') {
                msgShow('系统提示', '请输入密码！', 'warning');
                return false;
            }
            if ($rePass.val() == '') {
                msgShow('系统提示', '请在一次输入密码！', 'warning');
                return false;
            }

            if ($newpass.val() != $rePass.val()) {
                msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
                return false;
            }

            $.post('/ajax/editpassword.ashx?newpass=' + $newpass.val(), function(msg) {
                msgShow('系统提示', '恭喜，密码修改成功！<br>您的新密码为：' + msg, 'info');
                $newpass.val('');
                $rePass.val('');
                close();
            })
            
        }

        $(function() {

            openPwd();

            $('#editpass').click(function() {
                $('#w').window('open');
            });

            $('#btnEp').click(function() {
                serverLogin();
            })

			$('#btnCancel').click(function(){closePwd();})

            $('#loginOut').click(function() {
                $.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {
                    if (r) {
                        location.href = 'logout';
                    }
                });
            })
        });
    </script>
</html>
