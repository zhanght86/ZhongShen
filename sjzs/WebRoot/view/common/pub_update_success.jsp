<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
	<title>更新记录成功</title>
	<script type="text/javascript">
	var locationURI = "<%=request.getParameter("myUri")%>"
	function redirectWindow(){
		window.location.href="<%=basePath%>" + locationURI;
	}
	
	function clock(){
		i = i -1;
		if(document.getElementById("info")){
			document.getElementById("info").innerHTML = "本窗口将在"+i+"秒后自动进入列表页面";
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
<body bgcolor="#FFFFFF">
<center><br/>
	<p>操作成功！</p>
	<div id="info">本窗口将在3秒后自动进入列表页面</div>
	<input type="button" value="进入列表页面" onclick="redirectWindow();">
</center>
</body>
</html>