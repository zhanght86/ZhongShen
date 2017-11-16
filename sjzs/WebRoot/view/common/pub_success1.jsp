<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	<title>操作成功1</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link href="css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="js/jquery.easyui.pack.js"></script>
	<script type="text/javascript">

		//计时器
		function clock(){
			i = i -1;
			if(document.getElementById("info")){
				document.getElementById("info").innerHTML = "本窗口将在"+i+"秒后自动关闭";
			}
			if(i > 0)
				setTimeout("clock();",1000);
			else {
				window.parent.closewindow();
			}
		}
		
		var i = 4;
		$(function() {
			self.parent.msgShow("操作成功","操作成功！","info");
			window.parent.refreshPage = "${refreshPage}";
			window.parent.closePage = "${closePage}";
			window.parent.closewindow();
		});
	</script>
</head>
<body>
<!-- 
<center><br/>
	<p>操作成功！</p>
	<div id="info">本窗口将在3秒后自动关闭</div>
	<input type="button" value="关闭窗口" onclick="window.parent.closewindow();">
</center>
 -->
 	<p>操作成功！</p>
</body>
</html>