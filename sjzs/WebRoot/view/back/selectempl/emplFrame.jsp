<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>员工管理主页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
	<body leftmargin=0 topmargin=0 marginwidth=10 marginheight=0>
		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			<!---mjl改动单元格width比例  --->
				<td width="25%" height="450" valign="top" bgcolor="#F7F7F7">
					<iframe src="system/empl!orgTreeEmpl.action" name="lefttree" width="100%"
						height="100%" scrolling="auto" frameborder="0" id="lefttree"></iframe>
				</td>
				<td width="50%" valign="top" bgcolor="#F6F6F6">
					<%--<iframe src='system/empl!selectEmpl.action?empl.ussjg=00000000' name="mx" width="100%"
						height="100%" scrolling="auto" frameborder="0" id="mx"></iframe>--%>
				</td>
				<td width="25%" valign="top" bgcolor="#F6F6F6">
					<iframe src='view/back/selectempl/sellist.jsp' name="emplW" width="100%"
						height="100%" scrolling="auto" frameborder="0" id="mx1"></iframe>
				</td>
			</tr>
		</table>
	</body>
</html>
