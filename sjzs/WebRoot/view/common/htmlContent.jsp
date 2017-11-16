<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>htmlContent page</title>
  </head>  
  <body bgcolor="#FFFFFF">
  <span rows="10" cols="10" name="htmlContent" id="htmlContent" style="visibility: hidden;">${content }</span>
	<script type="text/javascript">	
		window.parent.document.getElementById("impsuc").innerHTML = document.getElementById("htmlContent").innerHTML;
		window.parent.closeHTMLDialog();
	</script>
  </body>
</html>
