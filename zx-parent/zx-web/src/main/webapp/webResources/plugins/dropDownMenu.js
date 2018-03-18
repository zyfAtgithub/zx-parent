(function() {
	
	//配置监视器构造函数
	var listener = function(ele, opt){
		this.$element = ele,
        this.defaults = {
            'color': 'red',
            'fontSize': '12px',
            'textDecoration': 'none'
        },
        this.options = $.extend({}, this.defaults, opt)
	};

	
	listener.prototype = {
		onClick: function (e, treeId, treeNode){
			var zTree = $.fn.zTree.getZTreeObj("menuTreeSel"),
			nodes = zTree.getSelectedNodes(),
			v = "";
			nodes.sort(function compare(a,b){return a.id-b.id;});
			for (var i=0, l=nodes.length; i<l; i++) {
				v += nodes[i].name + ",";
			}
			if (v.length > 0 ) v = v.substring(0, v.length-1);
			var cityObj = $("#parentSel");
			cityObj.attr("value", v);
		}
	};
	
	//下拉菜单列表插件
	$.fn.dropDownMenu = function(){
		console.log('nihao a ');
		
		var setting = {
				view: {
					dblClickExpand: false
				},
				data: {
					simpleData: {
						enable: true
					}
				},
				callback: {
					//onClick: onClick
				}
			};
	
			var zNodes =[
				{id:1, pId:0, name:"北京"},
				{id:2, pId:0, name:"天津"},
				{id:3, pId:0, name:"上海"},
				{id:6, pId:0, name:"重庆"},
				{id:4, pId:0, name:"河北省", open:true},
				{id:41, pId:4, name:"石家庄"},
				{id:42, pId:4, name:"保定"},
				{id:43, pId:4, name:"邯郸"},
				{id:44, pId:4, name:"承德"},
				{id:5, pId:0, name:"广东省", open:true},
				{id:51, pId:5, name:"广州"},
				{id:52, pId:5, name:"深圳"},
				{id:53, pId:5, name:"东莞"},
				{id:54, pId:5, name:"佛山"},
				{id:6, pId:0, name:"福建省", open:true},
				{id:61, pId:6, name:"福州"},
				{id:62, pId:6, name:"厦门"},
				{id:63, pId:6, name:"泉州"},
				{id:64, pId:6, name:"三明"}
			 ];
		
		
	}
})();