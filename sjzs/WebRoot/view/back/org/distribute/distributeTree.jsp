<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/view/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	    <base href="<%=basePath%>"/>
		<title>选择部门</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" type="text/css" href='js/tree/dtree/dtree.css' />
		<link rel="stylesheet" type="text/css" href='css/style.css' />
		<script language="javascript" src='js/tree/dtree/dtree.js'></script>
		<script language="javascript" src='js/showdialog.js'></script>
		<script type="text/javascript" src="js/ztree/js/jquery-1.4.4.min.js"></script>
		<script type="text/javascript">
			$(function() {
				$("a").click(function() {
					self.parent.frames['orgselect'].location.href="<%=basePath%>blank.jsp"
				});
			});
		</script>
	</head>
	<body bgcolor="#FFFFFF">
	<!-- 
	<table width="99%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC">
	    <tr class="title_style">
	      <td colspan="8" style="text-align:left">
	      	<img src="images/group1.png" width="20" height="20" align="absmiddle" />点击部门名称进行选择
	      </td>
	    </tr>
    </table>
     -->
		<script type="text/javascript">
		 a = new dTree('a');
		 a.config.target = "emplList"; //链接显示目标窗口
		 a.config.folderLinks=true;
		 a.add("00000000-0000-0000-0000-000000000000","-1","组织机构","javascript:javascript:getDataC('00000000','组织机构',0);","","");
		<c:forEach items="${page.list}" var="entry">
	   	  	a.add("${entry.orgId}","${entry.orgParent}","${entry.orgName}","system/org!getEmplByOrg.action?empl.orgId=${entry.orgId}","","");
	    </c:forEach>
	    document.write(a);
		</script>
	</body>
</html>
