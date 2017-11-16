<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showSJFG.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>

	<c:if test="${fu:check(\"auditLaw!toAddAuditLaw.action\")}">
		<a unable="false" id="addLaw" href="javascript:;" onclick="if(!isdis(this))addLaw()">新增法规</a>
	</c:if>.<a></a>
	<c:if test="${fu:check(\"auditLaw!goUpdatePage.action\")}">
		<a  unable="false" id="updLaw" href="javascript:void(0);"  onclick="if(!isdis(this))updateLaw()">编辑法规</a>
	</c:if>
	<c:if test="${fu:check(\"auditLaw!deleteAuditLaw.action\")}">
		<a unable="false" id="delLaw" href="javascript:void(0);" onclick="if(!isdis(this))deleteAuditLaw()" >删除法规</a>
	</c:if>



  </body>
</html>
