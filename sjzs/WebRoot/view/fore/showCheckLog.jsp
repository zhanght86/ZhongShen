<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/view/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>查看审核记录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		body,table{
			font-size: 14px;
		}
	</style>
  </head>
  
  <body>
	  <div>
	  	<table>
	  		<thead>
	  			<tr>
	  				<td colspan="4" style="text-align: center;">查看审核记录</td>
	  			</tr>
	  			<tr>
	  				<td width="7%">序号</td>
		  			<td width="20%">标题</td>
		  			<td width="15%">审核人员</td>
		  			<td width="15%">审核状态</td>
		  			<td width="43%">审核意见</td>
		  		</tr>
	  		</thead>
	  		<tbody>
	  			<s:iterator id="entry" value="checkLogPage.list" status="sta">
			  		<tr>
			  			<td><s:property value="#sta.index + 1"/></td>
			  			<td>${entry.infoName }</td>
			  			<td>${entry.checkUserName }</td>
			  			<td>
			  				<s:if test="checkResult == 0">待审核</s:if>
							<s:elseif test="checkResult == 1">审核中</s:elseif>
							<s:elseif test="checkResult == 2">审核通过</s:elseif>
							<s:elseif test="checkResult == 3">已打回</s:elseif>
			  			</td>
			  			<td>${entry.checkLog }</td>
			  		</tr>
		  		</s:iterator>
	  		</tbody>
	  	</table>
	  	<%--
	  	<s:iterator id="entry" value="checkLogPage.list" status="sta">
	  		<p>| ${entry.checkUserName } | ${entry.clientId } | ${entry.clientName } | ${entry.infoId } | ${entry.attachId } | ${entry.updateDate } | ${entry.checkLog } | ${entry.checkRresult } </p>
		</s:iterator>
	  	  <s:debug></s:debug>
	  	   --%>
	  </div>
  </body>
</html>
