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
		<title>选择审批组</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" type="text/css" href='js/tree/dtree/dtree.css' />
		<link href="style/common.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src='js/tree/dtree/dtree.js'></script>
		<script language="javascript" src='js/showdialog.js'></script>
	</head>
	<body bgcolor="#FFFFFF" leftmargin="0" topmargin="5" marginwidth="0"
		marginheight="0">
		<script type="text/javascript">
		 a = new dTree('a');
		 a.config.folderLinks=true;
		 a.add("0","-1","审批组","","","");
		<c:forEach items="${list}" var="entry">
    	  	a.add("${entry.spzcode}","0","${entry.spzname}","javascript:getDataC('${entry.spzid}','${entry.spzname}');","","");
	    </c:forEach>
	   
	    document.write(a);
		</script>
	</body>
</html>
