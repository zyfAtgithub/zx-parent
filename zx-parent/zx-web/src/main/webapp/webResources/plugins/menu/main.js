$(function(){
	loadMenu();
});

function loadMenu(){
	$('.sidebar-menu').html('');
	$.ajax({
		   url: "menu.json",//json文件位置
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
					if ('1' == level) {
						$('.layui-body .right-topnav .layui-breadcrumb').html('<a href="">'+ title +'</a>');
					}
					else {
						$('.layui-body .right-topnav .layui-breadcrumb').append($('<a href="">'+ title +'</a>'));
					}
					layer.msg(dataUrl + "&&" + title + "&&" + level);
					if (dataUrl && "#" != dataUrl) {
						$(".right-content").html('<iframe src="'+dataUrl+'"></iframe>');
					}
				});
				
		   }
		});
}
