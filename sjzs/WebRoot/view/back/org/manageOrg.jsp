<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.hnzskj.common.Constant" %>
<%@ include file="/view/common/common.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>"/>
    <title>部门资料树管理</title>
    <link rel="stylesheet" type="text/css" href='css/style.css' />
	<script src="js/treeView/scripts/jquery.min.js" type="text/javascript"></script>
	<script type='text/javascript' src='js/treeView/scripts/jquery.contextmenu.r2-min.js'></script>
	<script src="js/treeView/scripts/jquery.treeview.js" type="text/javascript"></script>
	<link rel="stylesheet" href="js/treeView/css/jquery.treeview.css" />
	
	<link rel="stylesheet" type="text/css" href="js/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="js/themes/icon.css"/>
	<script type="text/javascript" src="js/jquery.easyui.min.1.2.2.js"></script>
	<script type="text/javascript" src='js/locale/easyui-lang-zh_CN.js'></script>
	<style type='text/css'>
		*{font-size:12px;}
		.current{ background:blue;color:white;}
	</style>
	<script type='text/javascript'>
		var treeNodes = [${org_json_str }];
		function createtree(d) {
	        var list = '';
	        $.each(d, function (i, n) {
	            var cls = 'folder';
	            if (n.children.length == 0)
	                cls = 'file';	
	            list += '<li><span class="' + cls + '" ref="' + n.id + '"  pid="'+n.parentid+'" id="'+n.id+'" orgType="'+n.orgType+'" ><A href="system/org!searchOrg.action?org.orgId=' + n.id + '&org.orgType='+n.orgType+'" target="orgList">' + n.text + '</A></span>';
	            if (n.children.length > 0) {
	                list += "<ul>";
	                list += createtree(n.children);
	                list += "</ul>"
	            }
	            list += "</li>";
	        });
	        return list;
	    }
		function initTree(){
			$('#product_tree').empty().append(createtree(treeNodes));
			$('body').data('ptree', treeNodes);
			$('#product_tree .file').click(function () {
				var id = $(this).attr("ref");
				var txt = $(this).text();
				$('#product_tree span>a').removeClass('current');
				$(this).children('a').addClass('current');
				$('form').data("treeid", {"id":id,"txt":txt});	
			});
			$('#product_tree span').contextMenu('myMenu2', {
				bindings: {
					'addorg': function(t) {
						var id = $(t).attr('id');
						var pid = $(t).attr('pid');
						var txt = $(t).children('a').text();
						addOrg(id,<%=Constant.DEPARTMENT%>);
					 },
					 'edit': function(t) {
						var id = $(t).attr('id');
						var pid = $(t).attr('pid');
						var txt = $(t).children('a').text();
						updateOrgInfo(pid, id);
					 },
					 'delete': function(t) {
						var id = $(t).attr('id');var pid = $(t).attr('pid');var txt = $(t).children('a').text();delOrg(id);}}});
	
			$('#product_tree').treeview({
				animated: "fast",
				collapsed: true,
				control: "#mm1"});}
		$(function(){
			initTree();
			document.onselectstart=function(){return false;}
		})
		function removeBackground(){$(".treeview UL").css("backgroud-color","transparent");}
		//添加部门
		function addOrg(orgId,orgType) {
			var reqURL = "<%=basePath%>system/org!goAddPage.action?org.orgParent=" + orgId + "&org.orgType="+orgType+"&nocache=" + new Date().getTime();
			$('#addOrgWin').window({   
				width:700,   
				height:350,   
				modal:true,
				collapsible:false,
				minimizable:false, 
				maximizable:false,
				closable:true
			});
			var iframe_str = "<iframe height=\"100%\" width=\"100%\" scrolling=\"no\" frameborder=\"0\" src=\"" + reqURL + "\"></iframe>";
			$('#addOrgWin').html(iframe_str);
		}

		//修改部门
		function updateOrgInfo(parentId, orgId) {
			var reqURL = "<%=basePath%>system/org!goUpdatePage.action?parentCode=" + parentId + "&org.orgId=" + orgId + "&nocache=" + new Date().getTime();
			$('#updOrgWin').window({   
				width:730,   
				height:350,   
				modal:true,
				collapsible:false,
				minimizable:false, 
				maximizable:false,
				closable:true
			});
			var iframe_str = "<iframe height=\"100%\" width=\"100%\" scrolling=\"no\" frameborder=\"0\" src=\"" + reqURL + "\"></iframe>";
			$('#updOrgWin').html(iframe_str);
		}

		//关闭浮动窗口
		function closeWin(win_name) {
			$('#' + win_name).window('close');
			window.location.reload(true);
		}

		//删除部门
		function delOrg(jmcode) {
			$.messager.confirm('确认','你认要删除当前部门?',function(r){if (r){window.location.href = "<%=basePath%>system/org!deleteOrg.action?orgCodes=" + jmcode;}});}
		function viewOrg() {
		var reqURL = "<%=basePath%>system/org!viewOrg.action?time="+new Date().getTime();
		$('#viewOrg').window({width:350,height:500, modal:true,collapsible:false,minimizable:false, maximizable:true,closable:true});
			var iframe_str = "<iframe height=\"100%\" width=\"100%\" scrolling=\"auto\" frameborder=\"0\" src=\"" + reqURL + "\"></iframe>";
			$('#viewOrg').html(iframe_str);
		}
	</script>
  </head>
  <body bgcolor="#FFFFFF">
  	<!-- 
  	<div style="width: 100%;"><img src="images/refresh.gif" onclick="javascript:window.location.reload(true)"/><a href="javascript:window.location.reload(true)">刷新<a/></div>
  	 -->
  	<div id="outDIV" style="width: 100%;height: 600px;">
  		<div id="left" style="float: left;width: 25%;height:100%;overflow:scroll;border: 1px solid #98d2f3;">
			<ul id="product_tree" class="filetree"></ul>
			<div class="contextMenu" id="myMenu1" style="visibility: hidden">
		      <ul>
		        <li id="addcom"><img src="js/treeView/images/add.png" />添加子公司</li>
		        <li id="addorg"><img src="js/treeView/images/add.png" />添加部门</li>
		        <li id="edit"><img src="js/treeView/images/edit.gif"/>修改</li>
		        <li id="delete"><img src="js/treeView/images/delete.png"/>删除</li>
		      </ul>
		    </div>  		
			<div class="contextMenu" id="myMenu2" style="visibility: hidden">
		      <ul>
		        <li id="addorg"><img src="js/treeView/images/add.png" />添加部门</li>
		        <li id="edit"><img src="js/treeView/images/edit.gif"/>修改</li>
		        <li id="delete"><img src="js/treeView/images/delete.png"/>删除</li>
		      </ul>
		    </div>  		
  		</div>
  		<div id="myright" style="float: left;height: 100%;margin-left: 4px;border: 1px solid #98d2f3;width: 71% ">
  			<iframe id="orgList" name="orgList" src="system/org!searchOrg.action?org.orgId=00000000" frameborder="0" width="100%" height="100%"></iframe>
  		</div>
  	</div>
  	<!-- 浮动窗口  添加/修改部门-->
	<div id="addOrgWin" iconCls="icon-save" title="添加部门---" style="width: 0;height: 0"></div> 
	<div id="updOrgWin" iconCls="icon-edit" title="修改部门--" style="width: 0;height: 0"></div> 
	<div id="viewOrg" iconCls="icon-edit" title="修改部门--" style="width: 0;height: 0"></div> 
  </body>
</html>