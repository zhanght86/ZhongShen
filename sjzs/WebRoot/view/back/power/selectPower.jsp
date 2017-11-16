<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/view/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	    <base href="<%=basePath%>" target="_self">
		<title>选择父菜单</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" type="text/css" href='js/tree/dtree/dtree.css' />
		<script language="javascript" src='js/tree/dtree/dtree.js'></script>
		<script language="javascript" src='js/showdialog.js'></script>
	</head>
	<body bgcolor="#FFFFFF">
		<script type="text/javascript">
		 a = new dTree('a');
		 a.config.folderLinks=true;
		 a.add("00000000","-1","系统功能","javascript:return false;","","");
		 <c:forEach items="${page.list}" var="entry">
		    a.add("${entry.powerId}","${entry.powerParent}","${entry.powerName}","javascript:getDataC('${entry.powerId}','${entry.powerName}');","","");
	     </c:forEach>
	     document.write(a);
		</script>
	</body>
</html>
