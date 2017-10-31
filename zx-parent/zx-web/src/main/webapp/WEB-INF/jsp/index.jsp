<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@include file="common/include.jsp"%>
<title>文件管理系统</title>

<style type="text/css">
 article, aside, figure, footer, header, hgroup, 
  menu, nav, section {  display:  block;  } 
  .west{ 
    width: 200px; 
    padding: 10px; 
  } 
  .north{ 
    height: 100px; 
  } 
</style>
</head>
<body class="easyui-layout">
<div  data-options="region:'north'"  class ="north"  title ="____′↘夏悸  http://easyui.btboys.com" > 
     <h1>最简单的左右结构实现，及tab的右键菜单实现，右键查看源码 </h1> 
   </div> 
   <div  data-options="region:'center'" > 
     <div  class ="easyui-tabs"  fit ="true"  border ="false"  id ="tabs" > 
       <div  title ="首页" data-options="closable:true,
       	tools:[{iconCls:'icon-mini-refresh',handler:function(){alert('refresh')}}]">
       		这里是首页。。。
		</div> 
       <div  title ="用户管理" data-options="closable:true,
       	tools:[{iconCls:'icon-mini-refresh',handler:function(){alert('refresh')}}]">
       		这里是用户管理
		</div> 
     </div> 
   </div> 
   <div  region ="west"  class ="west"  title ="menu" > 
     <ul  id ="tree" ></ul> 
   </div> 
   
   <div  id ="tabsMenu"  class ="easyui-menu"  style ="width:120px;" >   
     <div  name ="close" >关闭 </div>   
     <div  name ="Other" >关闭其他 </div>   
     <div  name ="All" >关闭所有 </div> 
   </div> 
</body>

<script type="text/javascript">
</script>
</html>
