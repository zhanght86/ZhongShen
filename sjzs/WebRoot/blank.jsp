<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/view/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>" target="_self"/>
    <title>空白页面</title>
	<link href="css/style.css" type="text/css" rel="stylesheet"/>
  </head>
 <body>
 	<c:if test="${param.info == 'savesuc'}">
	 	<script type="text/javascript">
	 		self.top.msgShow("操作成功","授权成功！","info");
	 	</script>
 	</c:if>
</body>
</html>