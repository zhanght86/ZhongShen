<%@ page language="java" contentType="text/html; charset=UTF-8" errorPage="error.jsp" isErrorPage="true"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
	<title>无权限提示页面</title>
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
	<table width="100%" border="0" align="center" cellpadding="0"
		cellspacing="0" class="table_1">
		<tr>
			<td>
				&nbsp;
			</td>
		</tr>
	</table>
	<br>
	<br>
	<br>
	<table width="50%" border="0" align="center" cellpadding="0"
		cellspacing="0" class="table_2">
		<tr>
			<td align="center">
				<img src="images/warn.gif" onclick="changeDisplay('infodiv')">
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
										<span><font color="red" style="font-weight: bold;">您无权限执行此操作！</font></span>
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
	<div style="text-align: center;display: none;" id="infodiv">
		禁止访问的URI：${URI }
	</div>
</body>
</html>