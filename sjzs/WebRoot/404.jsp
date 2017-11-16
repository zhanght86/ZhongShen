<%@ page language="java" contentType="text/html; charset=UTF-8" isErrorPage="true"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
	<title>404页面</title>
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
				<img src="images/warn.gif" onclick="changeDisplay('errorInfo')" alt="点击我查看出错详细信息">
				您所访问的页面不存在！
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
										<!-- 
										<img src="images/xt_12.gif">
										<a href="view/back/menu/index.html">返回主页面</a>
										 -->
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
		<table>
			<tr>
				<td>错误码:</td>
				<td>
					<%=request.getAttribute("javax.servlet.error.status_code")%>
				</td>
			</tr>
			<tr>
				<td>错误信息:</td>
				<td>
					<%=request.getAttribute("javax.servlet.error.message")%>
				</td>
			</tr>
			<tr>
				<td>请求URI:</td>
				<td>
					<%=request.getAttribute("javax.servlet.error.request_uri") %> 
				</td>
			</tr>
		</table>
	</div>
</body>
</html>