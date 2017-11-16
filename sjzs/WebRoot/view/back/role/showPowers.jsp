<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/view/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>权限列表</title>
	<link rel="stylesheet" type="text/css" href='js/tree/dhtmltree/css/dhtmlXTree.css"/>'>
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script src="js/tree/dhtmltree/js/dhtmlXCommon.js"></script>
	<script src="js/tree/dhtmltree/js/dhtmlXTree.js"></script>
	<style type="text/css">
	body{background-color: #F6F6F6;}
	</style>
	<script type="text/javascript">
		$(function() {
			var reqURI = "system/createXML.action?type=onerole&role.roleId=${role.roleId}&tos=" + new Date().getTime();
			$.get(reqURI,"",function (mydata) {
				var xmlpro = $(mydata).find("item");//在返回的xml文档中查找item节点
				if (xmlpro.length < 1) {//如果没有找到节点
					$("#treeboxbox_tree").html("<font color='red'>暂无权限信息</font>");
				}
			},"xml");
		});
	</script>
  </head>
	<body bgcolor="#F6F6F6">
	<div style="background-color: #F6F6F6;">
		<div>																															
			<a href="javascript:void(tree.openAllItems(0))">
				<img src="images/outin_03.gif" width="53" height="19" border="0">
			</a>
			<a href="javascript:void(tree.closeAllItems(0))">
				<img src="images/outin_05.gif" width="54" height="19" border="0">
			</a>
		</div>
		<div id="treeboxbox_tree"></div>
		<script>
		tree=new dhtmlXTreeObject("treeboxbox_tree","100%","100%",0);
		tree.setImagePath("<%=basePath%>js/tree/dhtmltree/imgs/");
		tree.loadXML("system/createXML.action?type=onerole&role.roleId=${role.roleId}&tos=" + new Date().getTime());
		</script>
	</div>
	</body>
</html>

