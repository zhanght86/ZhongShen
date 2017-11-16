<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>日期选择对话框</title>
	<link href="css/default.css" rel="stylesheet" type="text/css" />
	<script language="javascript" src="js/calendar/calendar.js" charset="utf-8"></script>
	<script type="text/javascript">
		function returnDateVal() {
			var dateVal = document.getElementById("policyDate").value;
			if ( dateVal.length == 0 || dateVal.value == "") {
				alert("请选择法规的废止日期！");
				return;
			}
			parent.window.returnValue = dateVal;
			window.close();
		}
	</script>
  </head>
  <body bgcolor="#FFFFFF" style="text-align: center;">  
	<table width="400" border="1" align="center" cellpadding="0" cellspacing="0" class="table">
		<tr>
			<td>请选择您需要的日期：</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="policy.policyDate" id="policyDate" value=""
					maxlength="100" readonly="readonly">
				<img style="CURSOR: hand"
					onClick="setday(this,document.all.policyDate)"
					src="js/calendar/form/calendar.gif" align="middle" border="0"
					width="26" height="26">
				<input type="button" value="确定" onclick="returnDateVal();"/>
			</td>
		</tr>
	</table>
  </body>
</html>
