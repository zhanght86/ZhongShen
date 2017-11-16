<%@ page language="java" contentType="text/html; charset=UTF-8" errorPage="error.jsp" isErrorPage="true"%>
<%@ include file="/view/common/common.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
	<title>系统提示页面</title>
	<base href="<%=basePath%>" />
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
	body{
	font-size:12px;
	font-family:Arial;
	background:url(images/main_rbg.jpg) repeat-x bottom;
	padding-top:10px;
	background-color: #FFFFFF;
	}
	</style>
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
			<td>
				<table width="100%"  border="0" cellspacing="0" cellpadding="0" bgcolor="#F7FCFF" class="border_A6C4DC">
					<tr>
						<td>
							<div style="color: red;font-weight: bolder;font-size: 20px;">
								<img src="images/warn.gif" align="middle" onclick="changeDisplay('errorInfo')"/>
								<s:property value="exception.message"/>
								<img src="images/xt_12.gif">
								<a href="javascript:window.history.back()">返回</a>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<div style="display: none;" id="errorInfo">
     <table border="0" cellpadding="0" cellspacing="0">
     	<tr>
     		<td valign="top">错误内容：</td>
     		<td>
				<s:property value="exceptionStack"/>
			</td>
     	</tr>
     </table>
	</div>	
</body>
</html>