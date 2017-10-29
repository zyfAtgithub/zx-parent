<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="common/header.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@include file="common/include.jsp"%>
<title>你好</title>

<style type="text/css">
	body{
		padding-top:60px;
		padding-bottom:60px;
	}
.side-nav-item {
  display: block;
  padding: 10px 15px 10px 15px;
  background-color: #FFFFFF;
  cursor: pointer;
  box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
  -webkit-box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
}

.item-title {
  background-color: #F5F5F5;
  border-top-left-radius: 3px;
  border-top-right-radius: 3px;
  border-bottom: 1px solid #DDDDDD;
}

.panel-heading {
  margin-top: 5px;
  padding: 0;
  border-radius: 3px;
  border: 1px solid transparent;
  border-color: #DDDDDD;
}

.item-body {
  padding: 10px 15px 5px 15px;
  border-bottom: 1px solid #DDDDDD;
}

.item-second {
  margin-top: 5px;
  cursor: pointer;
}

.item-second a {
  display: block;
  height: 100%;
  width: 100%;
}
</style>
</head>
<body>
	<nav class="navbar navbar-default navbar-fixed-top" style= role="navigation">  
	    <div class="container">  
	        <div class="navbar-header">  
	            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"  
	                data-target="#navbar" aria-expanded="false" aria-controls="navbar">  
	                <span class="sr-only"></span>  
	                <span class="icon-bar"></span>  
	                <span class="icon-bar"></span>  
	                <span class="icon-bar"></span>  
	            </button>  
	            <a class="navbar-brand" href="#">电影管理系统</a>  
	        </div>  
	        <div id="navbar" class="navbar-collapse collapse" aria-expanded="false">  
			    <ul class="nav navbar-nav navbar-right">
			    	 <li class="dropdown">
			    	 	<a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    		<shiro:principal/> <b class="caret"></b>
                   		</a>
                   		 <ul class="dropdown-menu">
		                    <li> 
		                    	<shiro:user>  
									<a href="${pageContext.request.contextPath}/logout">退出</a>  
								</shiro:user>
							</li>
		                </ul>
				  </li>
	            </ul>
	        </div> 
	    </div>  
	</nav>  
	

<div class="col-md-2 side-nav">
  <div class="panel-group" id="accordion">
    <div class="panel-heading panel">
      <a href="" class="side-nav-item item-title">
      	首页
      </a>
      <div class="item-body collapse">
      </div>
    </div>

    <div class="panel-heading panel">
      <a data-toggle="collapse" data-parent="#accordion" href="#item-cangku" id="headcangku" class="side-nav-item item-title">
      仓库管理
      </a>
      <div id="item-cangku" class="panel-collapse collapse in">
        <div class="item-body">
          <ul class="list-unstyled">
            <li class="item-second"><a href="">产品库存</a></li>
            <li class="item-second"><a href="">原料库存</a></li>
          </ul>
        </div>
      </div>
    </div>

    <div class="panel-heading panel">
      <a data-toggle="collapse" data-parent="#accordion" href="#item-caiwu" id="headcaiwu" class="side-nav-item item-title collapsed">
      财务管理
      </a>
      <div id="item-caiwu" class="panel-collapse collapse">
        <div class="item-body">
          222
        </div>
      </div>
    </div>
    
    <div class="panel-heading panel">
      <a data-toggle="collapse" data-parent="#accordion" href="#item-renshi" id="headrenshi" class="side-nav-item item-title collapsed">
      人事管理
      </a>
      <div id="item-renshi" class="panel-collapse collapse">
        <div class="item-body">
          333
        </div>
      </div>
    </div>
  </div>
</div>

</body>
</html>
