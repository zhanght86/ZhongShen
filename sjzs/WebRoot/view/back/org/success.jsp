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
	<title>添加记录成功</title>
	<script type="text/javascript">
		function clock(){
			window.parent.closeWin("${param.type}"); 
		}
		clock();
	</script>
</head>
<body bgcolor="#FFFFFF">
<center><br/>
	<p>操作成功！</p>	
</center>
</body>
</html>