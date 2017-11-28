<%@ page contentType="text/html; charset=UTF-8"  %>
<%@include file="common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@include file="common/include.jsp" %> 
	<style type="text/css">
		body{
		}
	</style>
<body>
	<div class="layui-container">
		<!-- <div class="layui-row">
		    <div class="layui-col-md3 layui-col-md-offset4" style="text-align: center;">
				<div class="layui-anim layui-anim-scaleSpring">
					<i class="layui-icon" style="font-size: 35px; font-weight:bold;color: #48399B;">&#xe715;</i>
					<i style="font-size: 35px; font-weight:bold;color: #48399B;">欢迎使用！</i>   
				</div>
		    </div>
		</div> -->
		<div class="layui-row">
		    <div class="layui-carousel layui-anim layui-anim-scaleSpring" id="test10">
			  <div carousel-item>
			    <div><img src="${ctx }/webResources/images/carousel/3.png"></div>
			    <div><img src="${ctx }/webResources/images/carousel/4.png"></div>
			    <div><img src="${ctx }/webResources/images/carousel/5.png"></div>
			    <div><img src="${ctx }/webResources/images/carousel/6.png"></div>
			    <div><img src="${ctx }/webResources/images/carousel/7.png"></div>
			  </div>
			</div>
		</div>
	</div>
	
	<script src="${ctx }/webResources/plugins/layui/layui.all.js" type="text/javascript"></script>
	<script>
	layui.use(['carousel'], function(){
	  var carousel = layui.carousel;
	  //图片轮播
	  carousel.render({
	    elem: '#test10',
	    width: '778px',
	    height: '440px',
	    interval: 3000
	  });
	});
	</script>
</body>
</html>
