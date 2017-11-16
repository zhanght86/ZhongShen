<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/view/common/common.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String listName =request.getAttribute("list").toString();
//out.print(listName);
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
		.uu{
			font: bolder;
			color: red;
		}
	</style>
	
	
</head>
<body bgcolor="#ECE9D8">
<center><br/>
	<p><s:actionerror theme="simple"/></p>
	<div id="info"></div>
	<form action="system/sjzs!diaozhuan.action">
	<table>
	<tr align="center">
	
	<td colspan="2">有部分数据未导入成功</td></tr>
	<%
	String listfile =listName.substring(0,listName.length()-1);
	String fileName[] = listfile.split(",");
	
	for(int i = 0;i<fileName.length;i++){
	%>
	<tr><td>未导入成功的数据信息：<td calss="uu"><font color="red">
	<%= fileName[i] %></font>
	</td></tr>
	<%	
	}
	%>
	
	</table>
	<input type="submit" value=" 确    定  " onclick="go()">
	</form>
</center>
</body>
</html>