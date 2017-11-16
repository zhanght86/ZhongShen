<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>部门权限授于主页面</title>
  </head>
  	<frameset cols="20%,35%,25%" framespacing="0" border="0" id="colsFrame"
			bordercolor="#EFEFEF">
		<frame src='system/org!distributeTree.action' name="treepage"
			id="treepage" marginheight="0" marginwidth="0"
			frameborder="0" scrolling="auto" >
		<frame src='blank.jsp' id="emplList" name="emplList"
			scrolling="auto" marginheight="0" marginwidth="0" frameborder="0">
		<frame src='blank.jsp' name="orgselect" id="orgselect"
			scrolling="auto" marginheight="0" marginwidth="0" frameborder="0">
 	</frameset>
	<noframes>
  	<body>
  		
  	</body>
  	</noframes>
</html>
