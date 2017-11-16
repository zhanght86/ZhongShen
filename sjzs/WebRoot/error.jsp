<%@ page language="java" contentType="text/html; charset=UTF-8" errorPage="error.jsp" isErrorPage="true"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
	<title>系统出错页面</title>
	<base href="<%=basePath%>" />
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript">
		//切换HTML标签的显示隐藏状态
		function changeDisplay(objId) {
			$("#" + objId).toggle();
		}
	</script>
</head>
<body bgcolor="#FFFFFF">
	<br>
	<br>
	<br>
	<table width="50%" border="0" align="center" cellpadding="0"
		cellspacing="0" class="table_2">
		<tr>
			<td align="center">
				<img src="images/warn.gif" alt="点击我查看出错信息！" onclick="changeDisplay('errorInfo')">
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" border="0" align="center" cellpadding="3"
					cellspacing="0">
					<tr>
						<td>
							<table width="100%" border="0" align="center" cellpadding="3"
								cellspacing="0">
								<tr>
									<td>
										<% 
											if (null != exception.getMessage()) {
												out.print(exception.getMessage());
											} else {
												out.print("服务器出现错误，请重试如果此错误一直存在，请联系管理员！");
											}										
										%><img src="images/xt_12.gif">
										<a href="" onclick="window.history.back();return false;">返回</a>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<div style="display: none;" id="errorInfo">
     <table border="0" cellpadding="0" cellspacing="0">
     	<tr>
     		<td>错误原因：</td>
     		<td><%=exception%></td>
     	</tr>
     	<tr>
     		<td valign="top">错误内容：</td>
     		<td>
				<%
				     StackTraceElement[] trace = exception.getStackTrace();
				     for (int i=0; i < trace.length; i++){
				         out.println("\tat " + trace[i] + "<br/>");
				     }
			     %>
			</td>
     	</tr>
     </table>
	</div>	
</body>
</html>