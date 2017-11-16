<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>机构部门管理主页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
	<body>
		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			<!---mjl改动单元格width比例  --->
				<td  bgcolor="#F7F7F7">
					<iframe src="system/role!selectOrgs.action" name="lefttree" width="100%"
						height="100%" scrolling="auto" frameborder="0" id="lefttree"></iframe>
				</td>
				
				<td  bgcolor="#F7F7F7">
					<iframe src='view/back/role/selectOrg/sellist.jsp' name="emplW" width="100%"
						height="100%" scrolling="auto" frameborder="0" id="mx1"></iframe>
				</td>
			</tr>
		</table>
	</body>
</html>
