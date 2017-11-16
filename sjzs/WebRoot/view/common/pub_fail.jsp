<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/view/common/common.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>操作失败</title>
	<style type="text/css">
		ul li{
			font: bolder;
			color: red;
		}
	</style>
	<script type="text/javascript">
		function redirectWindow(){			
			window.history.back(-1);
		}
		
		function clock(){
			i = i -1;
			if(document.getElementById("info")){
				document.getElementById("info").innerHTML = "本窗口将在"+i+"秒后自动返回到上一页";
			}
			if(i > 0) {
				setTimeout("clock();",1000);
			} else {
				redirectWindow();
			}
		}
		
		var i = 4;
		clock();
	</script>
</head>
<body bgcolor="#ECE9D8">
<center><br/>
	<p><s:actionerror theme="simple"/></p>
	<div id="info">本窗口将在3秒后自动返回到上一页</div>
	<input type="button" value="返回上一页" onclick="redirectWindow();">
</center>
</body>
</html>