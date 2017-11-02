<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@include file="common/include.jsp"%>
<link rel="stylesheet" type="text/css" href="webResources/css/default.css">
	<script type="text/javascript" src='webResources/js/outlook2.js'> </script>
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
	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">
		<div class="footer">copyright © 2017-2018 zyf</div>
	</div>
	<div id="mainPanle" data-options="region:'center'" style="background: #eee; overflow-y:hidden">
    	<div id="tabs" class="easyui-tabs"  data-options="fit:true,border:false" >
		</div>
	</div>
</body>
<script type="text/javascript">
	 var _menus = {"menus":[
						{"menuid":"1","icon":"icon-sys","menuname":"控件使用",
							"menus":[
									{"menuid":"12","menuname":"疯狂秀才","icon":"icon-add","url":"http://www.mycodes.net"},
									{"menuid":"13","menuname":"用户管理","icon":"icon-users","url":"demo2.html"},
									{"menuid":"14","menuname":"角色管理","icon":"icon-role","url":"demo2.html"},
									{"menuid":"15","menuname":"权限设置","icon":"icon-set","url":"demo.html"},
									{"menuid":"16","menuname":"系统日志","icon":"icon-log","url":"demo1.html"}
								]
						},{"menuid":"8","icon":"icon-sys","menuname":"员工管理",
							"menus":[{"menuid":"21","menuname":"员工列表","icon":"icon-nav","url":"demo.html"},
									{"menuid":"22","menuname":"视频监控","icon":"icon-nav","url":"demo1.html"}
								]
						},{"menuid":"56","icon":"icon-sys","menuname":"部门管理",
							"menus":[{"menuid":"31","menuname":"添加部门","icon":"icon-nav","url":"demo1.html"},
									{"menuid":"32","menuname":"部门列表","icon":"icon-nav","url":"demo2.html"}
								]
						},{"menuid":"28","icon":"icon-sys","menuname":"财务管理",
							"menus":[{"menuid":"41","menuname":"收支分类","icon":"icon-nav","url":"demo.html"},
									{"menuid":"42","menuname":"报表统计","icon":"icon-nav","url":"demo1.html"},
									{"menuid":"43","menuname":"添加支出","icon":"icon-nav","url":"demo2.html"}
								]
						},{"menuid":"39","icon":"icon-sys","menuname":"商城管理",
							"menus":[{"menuid":"51","menuname":"商品分类","icon":"icon-nav","url":"demo.html"},
									{"menuid":"52","menuname":"商品列表","icon":"icon-nav","url":"demo1.html"},
									{"menuid":"53","menuname":"商品订单","icon":"icon-nav","url":"demo2.html"}
								]
						},{"menuid":"39","icon":"icon-sys","menuname":"商城管理",
							"menus":[{"menuid":"51","menuname":"商品分类","icon":"icon-nav","url":"demo.html"},
								{"menuid":"52","menuname":"商品列表","icon":"icon-nav","url":"demo1.html"},
								{"menuid":"53","menuname":"商品订单","icon":"icon-nav","url":"demo2.html"}
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
