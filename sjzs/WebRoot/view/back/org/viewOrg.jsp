<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/view/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	    <base href="<%=basePath%>" target="_self"/>
		<title>查看机构组织图</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" type="text/css" href='js/tree/dtree/dtree.css' />
		<link rel="stylesheet" type="text/css" href='css/style.css' />
		<script language="javascript" src='js/tree/dtree/dtree.js'></script>
		<script language="javascript" src='js/showdialog.js'></script>
	</head>
	<body bgcolor="#FFFFFF">
	<table width="99%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC">
	    <tr class="title_style">
	      <td colspan="8" style="text-align:left">
	      	<img src="images/group1.png" width="20" height="20" align="absmiddle" />机构组织图
	      </td>
	    </tr>
    </table>
		<script type="text/javascript">
		 a = new dTree('a');
		 a.config.folderLinks=true;
		 a.add("00000000","-1","组织机构","javascript:javascript:getDataC('00000000','组织机构');","","");
		<c:forEach items="${page.list}" var="entry">
  	  		a.add("${entry.orgId}","${entry.orgParent}","${entry.orgName}","javascript:void(0);","","");
	    </c:forEach>
	    document.write(a);
		</script>
	</body>
</html>
