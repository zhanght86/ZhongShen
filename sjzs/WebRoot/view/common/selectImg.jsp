<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>选择功能菜单图片</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/tree/dtree/img.js"></script>
	<style type="text/css">
		body{background-color: white;}
		ul{
			display: block;
			float: left;
			list-style: none;
			width: 400px;
		}
		li{
			display: block;
			float: left;
			list-style: none;
			width: 40px;
			height: 40px;
			text-align:center;
			vertical-align:middle;
			border: 1px solid;
			border-color: #6794d1;
		}
	</style>
  </head>
  <body>
	<div align="center" style="margin-top: 20px;">
		<script type="text/javascript">
			imgTable();
		</script>
	</div>
  </body>
</html>
