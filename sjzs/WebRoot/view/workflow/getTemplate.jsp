<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String pagePath=request.getRequestURL()+"";
%>
<html>
	<head>
		<base href="<%=basePath%>"/>
		
		<title>项目详细信息列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="<%=basePath%>css/style.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript">
		function getTemplate(no,name){
			var info =no+","+name;
			window.returnValue=info;
			window.close();
		}
	</script>
	</head>
	<body>
	<form action="">
		<div align="center" style="margin-top: 10px;">
				<table width="80%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td align="left" class="">
							<table width="100%" border="0" cellspacing="0" cellpadding="0"
								class="border_A6C4DC">
								<tr class="title_style">
									<td></td>
									<td align="center">
										模版名称
									</td>
									<td align="center">
										创建时间
									</td>
									<td align="center">
										排序类型
									</td>
								</tr>
								<s:iterator var="entry" value="tempPage.list" status="idx">
									<tr class="">
										<td align="center">
											<input type="radio"
												onclick="getTemplate('${entry.template_no}','${entry.template_name }')">
										</td>
										<td align="center">
											<s:property value="#entry.template_name" />
										</td>
										<td align="center">
											<s:property value="#entry.create_time" />
										</td>
										<td align="center">
											<s:property value="#entry.order_code" />
										</td>
									</tr>
								</s:iterator>

							</table>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</body>
</html>