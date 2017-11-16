<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/view/common/common.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>项目附件选择页面</title>
		<link rel="stylesheet" href="css/right.css" type="text/css"></link>
		<link rel="stylesheet" href="js/resources/css/all.css" type="text/css"></link>
		<script type="text/javascript" src="js/base.js"></script>
		<script type="text/javascript" src="js/all.js"></script>
		<script type="text/javascript">
			var src = "wdzs!upSingleFilePage.action";
			function createPanel(){	  
				var panel = new Ext.TabPanel({
						width:910,
						height:590,
						resizeTabs : Boolean,
		            	pageX:500,
		            	
		           		pageY:200, 
		            	items:[
							new Ext.Panel({title:"从本地上传",height:30,html:'<iframe frameborder="0" scrolling="auto" width="100%" height="100%" src=\"'+src+'\"></iframe>'})
		                ]
		      	});         
		     	panel.render("panel");
				panel.activate(0);	 
			}
			window.onload = createPanel;

			//存放选择的文件
			var files = new Array();

			function addAttachment(fileName, fileId) {
				if (!isSelected(fileId)) {
					files.push(new Array(fileName, fileId, 1));
					window.returnValue = files;
				}
			}
			
			function addFiles(fileName, fileId) {
				if (!isSelected(fileId)) {
					files.push(new Array(fileName, fileId, 2));
					window.returnValue = files;
				}
			}
			
			//判断文件是否已经被选择
			function isSelected(fileId) {
				for (var i = 0; i < files.length; i++) {
					if ( files[i][1] == fileId ) {
						return true;
					}
				}
				return false;
			}

			//移除已经选择的文件
			function removeFile(fileId) {
				for (var i = files.length - 1; i >= 0; i--) {
					if ( files[i][1] == fileId ) {
						files.splice(i, 1);
					}
				}
				window.returnValue = files;
			}
			//获得已经选择的文件
			function getSelected() {
				return files;
			}
		</script>
	</head>
<body>
	<div id="main" align="center">
		<div id="panel"></div>
	</div>
</body>
</html>