<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>操作成功页面</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
		function closewindow(){
			window.returnValue = true;
			window.close();
		}
		
		function clock(){
			i = i -1;
			if(document.getElementById("info")){
				document.getElementById("info").innerHTML = "本窗口将在"+i+"秒后自动关闭";
			}
			if(i > 0)
				setTimeout("clock();",1000);
			else
				closewindow();
		}
	
		var i = 4;
		window.onload = clock;
	</script>
  </head>
  <body bgcolor="#FFFFFF" onbeforeunload="closewindow();">
	<center><br/>
		<p>操作成功！</p>
		<div id="info">本窗口将在3秒后自动关闭</div>
		<input type="button" value="关闭窗口" onclick="closewindow();">
	</center>
  </body>
</html>
