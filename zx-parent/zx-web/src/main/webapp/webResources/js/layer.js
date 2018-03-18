$(function(){
//	$.fn.zTree.init($("#treeDemo"), $.treeconfig.setting, $.treeconfig.zNodes);
//	$('#admin-side').sidebarMenu();
//	layer.msg('aa');
});

//侧边菜单栏
$.fn.sidebarMenu = function(menulist) { 
 	var _menulist = [
 		{
 			id:1,
 			layuiIcon:'&#xe68e;',
 			url:'#',
 			title:'欢迎',
 			active:false,
 		},
 		{
 			id:2,
 			layuiIcon:'&#xe631',
 			url:'#',
 			title:'系统管理',
 			active:true,
 			children:[{
 				id:21,
 	 			layuiIcon:'&#xe612;',
 	 			url:'#',
 	 			title:'用户管理',
 	 			active:false,
 			}]
 		},
 	];

 	$this = this;
 	$this.html('');
 	$(_menulist).each(function(index, menu){
 		console.log(menu);
 		var $liItem =  $('<li class="layui-nav-item "></li>');
 		var $aItem = $('<a href="javascript:void();"><i class="layui-icon">'+ 
 				menu.layuiIcon +'</i>'+ menu.title +'</a>');
 		$liItem.append($aItem);
 		
 		//有子菜单
 		if (menu.children) {
 			console.log('has child..');
 			var $moreItem = '<span class="layui-nav-more"></span>';
 			$aItem.append($moreItem);
 			var $dlItem = $('<dl class="layui-nav-child"></dl>');
 			$.each(menu.children, function(index, subMenu){
 				var $ddItem = '<dd><a href=""><i class="layui-icon">'+ subMenu.layuiIcon +'</i>'
 				+ subMenu.title + '</a>';
 				$dlItem.append($ddItem);
 			});
 			console.log($dlItem);
 			$liItem.append($dlItem);
 		}
 		
 		$this.append($liItem);
// 		if (menu.active) {
// 			$liItem.addClass('layui-this');
// 		}
 		
 		$aItem.bind('click', function(){
 			console.log('dropdown...');
 		});
 		
 	});
 	console.log('sidebarMenu init done.');
};    

//树形插件的配置信息
$.treeconfig= {
	setting: {},
	zNodes:[
		{ name:"父节点1 - 展开", open:true,
			children: [
				{ name:"父节点11 - 折叠",
					children: [
						{ name:"叶子节点111"},
						{ name:"叶子节点112"},
						{ name:"叶子节点113"},
						{ name:"叶子节点114"}
					]},
				{ name:"父节点12 - 折叠",
					children: [
						{ name:"叶子节点121"},
						{ name:"叶子节点122"},
						{ name:"叶子节点123"},
						{ name:"叶子节点123"},
						{ name:"叶子节点123"},
						{ name:"叶子节点123"},
						{ name:"叶子节点123"},
						{ name:"叶子节点123"},
						{ name:"叶子节点123"},
						{ name:"叶子节点123"},
						{ name:"叶子节点123"},
						{ name:"叶子节点123"},
						{ name:"叶子节点123"},
						{ name:"叶子节点123"},
						{ name:"叶子节点123"},
						{ name:"叶子节点123"},
						{ name:"叶子节点123"},
						{ name:"叶子节点123"},
						{ name:"叶子节点123"},
						{ name:"叶子节点123"},
						{ name:"叶子节点123"},
						{ name:"叶子节点123"},
						{ name:"叶子节点123"},
						{ name:"叶子节点123"},
						{ name:"叶子节点123"},
						{ name:"叶子节点123"},
						{ name:"叶子节点123"},
						{ name:"叶子节点123"},
						{ name:"叶子节点123"},
						{ name:"叶子节点123"},
						{ name:"叶子节点123"},
						{ name:"叶子节点123"},
						{ name:"叶子节点123"},
						{ name:"叶子节点123"},
						{ name:"叶子节点123"},
						{ name:"叶子节点123"},
						{ name:"叶子节点123"},
						{ name:"叶子节点123"},
						{ name:"叶子节点123"},
						{ name:"叶子节点123"},
						{ name:"叶子节点123"},
						{ name:"叶子节点123"},
						{ name:"叶子节点123"},
						{ name:"叶子节点124"}
					]},
				{ name:"父节点13 - 没有子节点", isParent:true}
			]},
		{ name:"父节点2 - 折叠",
			children: [
				{ name:"父节点21 - 展开", open:true,
					children: [
						{ name:"叶子节点211"},
						{ name:"叶子节点212"},
						{ name:"叶子节点213"},
						{ name:"叶子节点214"}
					]},
				{ name:"父节点22 - 折叠",
					children: [
						{ name:"叶子节点221"},
						{ name:"叶子节点222"},
						{ name:"叶子节点223"},
						{ name:"叶子节点224"}
					]},
				{ name:"父节点23 - 折叠",
					children: [
						{ name:"叶子节点231"},
						{ name:"叶子节点232"},
						{ name:"叶子节点233"},
						{ name:"叶子节点234"}
					]}
			]},
		{ name:"父节点3 - 没有子节点", isParent:true}

	]
};
