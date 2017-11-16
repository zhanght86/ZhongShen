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
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="js/ztree/css/demo.css" type="text/css"/>
		<link rel="stylesheet" href="js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
		<script type="text/javascript" src="js/ztree/js/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="js/ztree/js/jquery.ztree.core-3.1.js"></script>
		<script type="text/javascript" src="js/ztree/js/jquery.ztree.excheck-3.1.js"></script>
		<script type="text/javascript" src="js/ztree/js/jquery.ztree.exedit-3.1.js"></script>
		<script type="text/javascript">
		<!--
		var setting = {
			check: {
				enable: true,
				//chkboxType: { "Y" : "", "N" : "" }
				chkboxType: { "Y" : "s", "N" : "ps" }
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};
 
		var zNodes =[
			{ id:'00000000', pId:'0', name:"组织机构树", open:true,nocheck:true},
			<c:forEach items="${page.list}" var="entry" varStatus="sta">
				<c:choose>
					<c:when test="${sta.last}">
						{ id:'${entry.orgId}', pId:'${entry.orgParent}', name:"${entry.orgName}", open:true ${ entry.count > 0 ? ",checked:true" : ""}}
					</c:when>
					<c:otherwise>
					{ id:'${entry.orgId}', pId:'${entry.orgParent}', name:"${entry.orgName}", open:true ${ entry.count > 0 ? ",checked:true" : ""}},
					</c:otherwise>
				</c:choose>
			</c:forEach>
		];
		
		var code;
		/*
		function setCheck() {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			py = $("#py").attr("checked")? "p":"",
			sy = $("#sy").attr("checked")? "s":"",
			pn = $("#pn").attr("checked")? "p":"",
			sn = $("#sn").attr("checked")? "s":"",
			type = { "Y":py + sy, "N":pn + sn};
			zTree.setting.check.chkboxType = type;
			showCode('setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "' + type.N + '" };');
		}
		function showCode(str) {
			if (!code) code = $("#code");
			code.empty();
			code.append("<li>"+str+"</li>");
		}
		*/
		var zTreeObj;
		$(document).ready(function(){
			zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
			//setCheck();
			//$("#py").bind("change", setCheck);
			//$("#sy").bind("change", setCheck);
			//$("#pn").bind("change", setCheck);
			//$("#sn").bind("change", setCheck);
		});

		//获得选中和复选框
		function getSelectNode() {
			var checkedNodes = zTreeObj.getCheckedNodes(true);
			var _jmcodes = ""; 
			for (var i in checkedNodes) {
				_jmcodes = _jmcodes + "," + checkedNodes[i].id;
			}
			_jmcodes = _jmcodes.substr(1);
		//	$("#ussjg").val(_jmcodes);
			$("#saveForm").submit();
			return true;
		}
		//-->
	</script>
		
	</head>
	<body bgcolor="green">
		<div style="text-align: right;">
			<form action="system/org!saveDistribute.action" style="display:inline;" id="saveForm">
				<input type="hidden" name="empl.emplId" value="${empl.emplId }"/>
				<%--<input type="hidden" name="empl.orgId" value="" id="ussjg" size="100"/>--%>
				<img src="images/ico01_03.gif" onclick="javascript:getSelectNode()" style="margin-top: 5px;margin-right: 10px;"/>
			</form>
		</div>
		<div class="zTreeDemoBackground left" style="border: 0px;width: 100%;height: 100%;margin-top: 0;padding-top: 0">
			<ul id="treeDemo" class="ztree" style="border: 0px;width: 96%;height: 100%;margin-top: 0;padding-top: 0"></ul>
		</div>
		<div style="text-align: right;">
			<img src="images/ico01_03.gif" onclick="javascript:getSelectNode()" style="margin-top: 5px;margin-right: 10px;"/>
		</div>
	</body>
</html>
