<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'selSpz.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/dataCheck.js"></script>
	<script language="javascript" src="js/calendar/calendar.js" charset="utf-8"></script>
	<script language="javascript" src='js/showdialog.js'></script>
<script type="text/javascript">
		//部门选择
		function selectorg(){
			getDataDoC2("<%=basePath%>system/spz!findAll.action?time="+new Date().getTime(),400,"spzid","spzname");
		}
	</script>
  </head>
  
  <body>
    <input type="text" id="spzname"/>
  	<input type="hidden" id="spzid"/>
  	<input type="button" value="选择审批组" onclick="selectorg();"/>
  </body>
</html>
