$(function(){
	loadMenu();
});

function loadMenu(){
	$('.sidebar-menu').html('');
	$.ajax({
		   url: "menu",//json文件位置
		   type: "GET",//请求方式为get
		   dataType: "json", //返回数据格式为json
		   success: function(menulist) {//请求成功完成后要执行的方法 
				$(menulist).each(function(index, menu){
					if (!menu.children) {
						var $menuItem = $('<li><a href="javascript:;" data-url="'+ menu.url +'" data-title="'+ menu.title +'" data-level="1"><i class="'+ menu.iconCls +'"></i>'
								+ '<span>'+ menu.title +'</span></a></li>');
						$(".sidebar-menu").append($menuItem);
					}
					else {
						var $treeViewElem = $('<li class="treeview">'
								+'<a href="'+ menu.url +'" data-url="#" data-title="'+ menu.title +'" data-level="1"><i class="'+ menu.iconCls +'"></i>'
								+ '<span>'+ menu.title +'</span><span class="layui-badge layui-bg-cyan pull-right">'+ menu.children.length +'</span></a></li>');
						var $treeViewmenuElem = $('<ul class="treeview-menu"></ul>')
						$.each(menu.children, function(index, subMenu){
							var $subMenuItem = $('<li><a href="javascript:;" data-url="'+ subMenu.url +'"  data-title="'+ subMenu.title +'" data-level="2"><i class="'+ subMenu.iconCls +'"></i>'
									+ '<span>'+ subMenu.title +'</span></a></li>');
							$treeViewmenuElem.append($subMenuItem);
						});
						$treeViewElem.append($treeViewmenuElem);
						$(".sidebar-menu").append($treeViewElem);
					}
				});
				$.sidebarMenu($('.sidebar-menu'));
				
				$('.sidebar-menu li>a').click(function(){
					var dataUrl = $(this).attr('data-url');
					var title = $(this).attr('data-title');
					var level = $(this).attr('data-level');
					var $breadcrumb = $('.layui-body .right-topnav .layui-breadcrumb');
					if ('1' == level) {
						$breadcrumb.html('<a href="">'+ title +'</a>');
					}
					else {
						var $parentMenu = $('.layui-body .right-topnav .layui-breadcrumb a:first');
						if ($parentMenu.children('.layui-box').length == 0) {
							$parentMenu.append($('<span class="layui-box">&gt;</span>'));
						}
						$breadcrumb.html('');
						$breadcrumb.append($parentMenu);
						$breadcrumb.append($('<a><cite>'+ title +'</cite></a>'));
					}
					layer.msg(dataUrl + "&&" + title + "&&" + level);
					if (dataUrl && "#" != dataUrl) {
						$(".right-content").html('<iframe src="'+dataUrl+'"></iframe>');
					}
				});
		   },
			error:function(e) {
				layer.open({
					title:"请求出错！",
					content:e.responseText
				});
			}
		});
}

function startTime() {
    var today=new Date()
	var years=today.getFullYear();
	var months=today.getMonth();
	var d=today.getDate()
	var h=today.getHours()
	var m=today.getMinutes()
	var s=today.getSeconds()
	// add a zero in front of numbers<10
	months=months+1
	months=checkTime(months)
	d=checkTime(d)
	m=checkTime(m)
	s=checkTime(s)
	var weekday=new Array(7)
	weekday[0]="星期日"
	weekday[1]="星期一"
	weekday[2]="星期二"
	weekday[3]="星期三"
	weekday[4]="星期四"
	weekday[5]="星期五"
	weekday[6]="星期六"
	var w=weekday[today.getDay()]
	document.getElementById('showNowTime').innerHTML=years+"年"+months+"月"+d+"日 "+"<br>"+w+" "+h+":"+m+":"+s;
	t=setTimeout('startTime()',500)
}
function checkTime(i) {
    if (i < 10) {
        i = "0" + i
    }
    return i
}

/**
 * 修改密码
 * @param curUser
 * @returns
 */
function modifyPassword(curUser) {
	layer.open({
		type:2,
		title:'修改密码',
		area: ['410px', '270px'],
		content:"sys/user/toModifypasswordView?loginname=" + curUser,
		end: function(){
			if ("200" == $("#pwdmodifyRes").val()) {
				$("#pwdmodifyRes").val('');
				layer.msg('密码修改成功！',{icon:1});
			}
		}
	});
}
