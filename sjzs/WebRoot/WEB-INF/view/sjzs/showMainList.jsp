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
    <title>审计助手管理</title>
    	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
    <link rel="stylesheet" type="text/css" href='css/style.css' />
	<script src="js/treeView/scripts/jquery.js" type="text/javascript"></script>
	<script type='text/javascript' src='js/treeView/scripts/jquery.contextmenu.r2-min.js'></script>
	<script src="js/treeView/scripts/jquery.treeview.js" type="text/javascript"></script>
	<%-- 
	<script src="js/treeView/scripts/jquery.treeview.async2.js" type="text/javascript"></script>
	--%>
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
		
		
		var treeNodes = ${son_str};
		function createtree(d) {
	        var list = '';
	        var url = '';
	        var ahref = '';
	        $.each(d, function (i, n) {
	        	 var cls = 'folder';
		            if (n.children.length == 0)
		                cls = 'file';	
	                if(n.menuType == 1 ){//1-审计法规 
	             	   url = 'system/sjfg!showsearchMessage.action?flag=0&law.lawSort=' + n.id;
					}
					if(n.menuType == 2){ // 2-定性依据 
						url = 'system/dxyj!showsearchMessage.action?flag=0&law.parentID=' + n.id ;
					}
	                if(n.menuType == 3){//方法案例
	                	 url = 'system/ffal!showsearchMessage.action?flag=0&ffal.sort=' + n.id;
					}
	                if(n.menuType == 5){//审计事项
	                	 url = 'system/dataDic!showsearchMessage.action?flag=0&dataDicDto.dicParentId=' + n.id;
					}
	                if(n.menuType == 6){//审计实施方案
	                	url = 'system/ssfa!showsearchMessage.action?flag=0&shiShiFangAn.sort=' + n.id;
					}
	           
				if(n.id =='11111111' || n.id=='22222222'||n.id=='33333333'||n.id=='44444444' || n.id == '0'|| n.id=='55555555'|| n.id=='66666666'){
					ahref =	'<A  target="menuList">' + n.text + '</A>';
					list += '<li><span class="' + cls + '" ref="' + n.id + '"  pid="'+n.parentid+'" url="'+url+'" id="'+n.id+'" menuType="'+n.menuType+'" >'+ahref+'</span>';
				}else{
			        ahref =	'<A href="'+url+'" target="menuList">' + n.text + '</A>';
		            list += '<li><span class="' + cls + '" ref="' + n.id + '"  pid="'+n.parentid+'" url="'+url+'" id="'+n.id+'" menuType="'+n.menuType+'" >'+ahref+'</span>';
				}
	            if (n.children.length > 0) {
	                list += "<ul>";
	                list += createtree(n.children);
	                list += "</ul>"
	            }
	            list += "</li>";
	        });
	        return list;
	    }

		function closeWinForm(url){
			window.returnValue=url;
			window.close();
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
			/**
			$('#product_tree span').contextMenu('myMenu2', {
				bindings: {
					'addMenu': function(t) {
						var id = $(t).attr('id');
						var pid = $(t).attr('pid');
						var txt = $(t).children('a').text();
						var type = $(t).attr('menuType');
						addMenu(id,type);
					 },
					 'edit': function(t) {
						var id = $(t).attr('id');
						var pid = $(t).attr('pid');
						var txt = $(t).children('a').text();
						var type = $(t).attr('menuType');
						updateMenuInfo(pid, id,type);
					 },
					 'delete': function(t) {
						var id = $(t).attr('id');var pid = $(t).attr('pid');var txt = $(t).children('a').text();delMenu(id);}}});
	*/
			$('#product_tree').treeview({
			//	url:"system/workflow!getSjListAnysc.action",
				animated: "fast",
				collapsed: true,
				unique: true,
				control: "#mm1"});}
		$(function(){
			initTree();
			document.onselectstart=function(){return false;}
		})
		function removeBackground(){$(".treeview UL").css("backgroud-color","transparent");}
		function refreshListPage(){
			window.frames['menuList'].reloadPage();
			//closeWin('addMenuWin');
		}
		
		//关闭浮动窗口
		function closeWin(win_name) {
			$('#' + win_name).window('close');
		}
		function viewMenu() {
		var reqURL = "<%=basePath%>sjzs!viewMenu.action?time="+new Date().getTime();
		$('#viewMenu').window({width:350,height:500, modal:true,collapsible:false,minimizable:false, maximizable:true,closable:true});
			var iframe_str = "<iframe height=\"100%\" width=\"100%\" scrolling=\"auto\" frameborder=\"0\" src=\"" + reqURL + "\"></iframe>";
			$('#viewMenu').html(iframe_str);
		}
	</script>
  </head>
  <body bgcolor="#FFFFFF">
  	<!-- 
  	<div style="width: 100%;"><img src="images/refresh.gif" onclick="javascript:window.location.reload(true)"/><a href="javascript:window.location.reload(true)">刷新<a/></div>
  	 -->
  	<div id="outDIV" style="min-width:800px;width: 99%;margin-left:3px; height: 660px;">
  		<div id="left" style="float: left; width: 19%;height:100%;overflow:scroll;border: 1px solid #98d2f3;">
			<ul id="product_tree" class="filetree"></ul>
			<div class="contextMenu" id="myMenu1" style="visibility: hidden">
		      
		    </div>  		
			<div class="contextMenu" id="myMenu2" style="visibility: hidden">
		      
		    </div>  		
  		</div>
  		<div id="myright" style="float: left;height: 100%;margin-left: 4px;border: 1px solid #98d2f3;width: 80% ">
  			<iframe id="menuList" name="menuList" src="" frameborder="0" width="100%" height="100%"></iframe>
  		</div>
  	</div>
  </body>
</html>