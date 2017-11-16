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
    <title>movetree</title>
    <link rel="stylesheet" type="text/css" href='css/style.css' />
	<script src="js/treeView/scripts/jquery.js" type="text/javascript"></script>
	<script type='text/javascript' src='js/treeView/scripts/jquery.contextmenu.r2-min.js'></script>
	<script src="js/treeView/scripts/jquery.treeview.js" type="text/javascript"></script>
	<%--
	<script src="js/treeView/scripts/jquery.treeview.async.js" type="text/javascript"></script>
	 --%>
	<link rel="stylesheet" href="js/treeView/css/jquery.treeview.css" />
		<link rel="stylesheet" href="js/treeView/css/tree/screen.css" />
		<link rel="stylesheet" href="js/treeView/css/tree/tree.css" />
	<link rel="stylesheet" type="text/css" href="js/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="js/themes/icon.css"/>
	
	<script type="text/javascript" src="js/jquery.easyui.min.1.2.2.js"></script>
	<script type="text/javascript" src='js/locale/easyui-lang-zh_CN.js'></script>
	<style type='text/css'>
		*{font-size:12px;}
		.current{ background:blue;color:white;}
	</style>
	<script type='text/javascript'>
	var treeNodes = [${sjzs_json_str}];
	var  ids = "${attIds}";
	var  menuType = "${menuType}";
		function createtree(d) {
	        var list = '';
	        var url = '';
	        var ahref = '';
	        $.each(d, function (i, n) {
	            var cls = 'folder';
	          //  if (n.children.length == 0)
	          //      cls = 'file';	
                if(n.menuType == 1 ){//1-审计法规 
             	   url = 'system/sjfg!searchMessage.action?law.lawSort=' + n.id;
				}
				if(n.menuType == 2){ // 2-定性依据 
					url = 'system/dxyj!searchMessage.action?law.parentID=' + n.id ;
				}
                if(n.menuType == 3){//方法案例
                	 url = 'system/ffal!searchMessage.action?ffal.sort=' + n.id;
				}
                if(n.menuType == 4){//审计导航
                	url = 'system/sjdh!serachSjdhMethod.action?sjdhMethod.typeNo=' + n.id;
				}
                if(n.menuType == 5){//审计事项
                	url = 'system/dataDic!searchMessage.action?dataDicDto.dicParentId=' + n.id;
				}
                if(n.menuType == 6){//审计实施方案
                	url = 'system/ssfa!searchMessage.action?shiShiFangAn.sort=' + n.id;
				}
				if(n.id =='11111111' || n.id=='22222222'||n.id=='33333333'||n.id=='44444444'||n.id=='55555555'||n.id=='66666666'){
					ahref =	'<A>' + n.text + '</A>';
					list += '<li><span class="' + cls + '" ref="' + n.id +' " leaf="'+n.leaf+ '"  pid="'+n.parentid+'" id="'+n.id+'" menuType="'+n.menuType+'" >'+n.text+'</span>';
				}else{
		            ahref =	'<A href="javascript:void(0)" onclick="gomove(\''+n.leaf+'\',\''+n.id+'\')">' + n.text + '</A>';
		            list += '<li class="closed"><span class="' + cls + '" ref="' + n.id +' " leaf="'+n.leaf+ '"  pid="'+n.parentid+'" id="'+n.id+'" menuType="'+n.menuType+'" >'+ahref+'</span>';
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
					 'daoru': function(t) {
						 var leaf = $(t).attr('leaf');
						 if(leaf==1){
							var id = $(t).attr('id');
							var pid = $(t).attr('pid');
							var txt = $(t).children('a').text();
							var type = $(t).attr('menuType');
							daoruInfo(pid, id,type);}
						 else{
                          alert("请选择叶子节点");
							 }
						 }
				
							}
			}
			);
	
			$('#product_tree').treeview({
			//	url: "system/sjzs!AsyncTree.action",
				animated: "fast",
				collapsed: false,
				unique: true,
				control: "#mm1"});}
		$(function(){
			initTree();
			document.onselectstart=function(){return false;}
		})
		function removeBackground(){$(".treeview UL").css("backgroud-color","transparent");}
	
		
		function viewMenu() {
		var reqURL = "<%=basePath%>system/sjzs!viewMenu.action?time="+new Date().getTime();
		$('#viewMenu').window({width:350,height:500, modal:true,collapsible:false,minimizable:false, maximizable:true,closable:true});
			var iframe_str = "<iframe height=\"100%\" width=\"100%\" scrolling=\"auto\" frameborder=\"0\" src=\"" + reqURL + "\"></iframe>";
			$('#viewMenu').html(iframe_str);
		}

		function gomove(data,parentid){
			if('1'==data){
				//ids 要迁移的记录ids
				//id 为迁移后的n.parentid 
				if ("" != ids) {
				if(confirm("您确定要迁移么？")) {
					var goURI = "system/sjzs!moveNotesInfo.action?menuType="+menuType+"&attIds=" + ids+"&parentid="+parentid;
					document.deleteForm.action = goURI;
					document.deleteForm.submit();   //获取所有的法规信息
				}
				}else{
					alert('请选择！');
				 }
				}
		}
	</script>
  </head>
  <body bgcolor="#FFFFFF">
  <form id="deleteForm" name="deleteForm" action = "" method="post" style="display: none;"></form>
  	<!-- 
  	<div style="width: 100%;"><img src="images/refresh.gif" onclick="javascript:window.location.reload(true)"/><a href="javascript:window.location.reload(true)">刷新<a/></div>
  	 -->
  	<div id="outDIV" style="min-width:800px;width: 99%;margin-left:3px; height: 660px;">
  		
			<ul id="product_tree" class="filetree"></ul>
	
  		</div>
  		
 
  </body>

</html>
<script type='text/javascript'>
	
function closeWin(){
	window.parent.closeWin('updMenuWin');
}

		function refreshListPage(win_name){
			//alert(win_name);
			window.frames['menuList'].reloadPage();
			closeWin(win_name);
		}
		
		//关闭浮动窗口
		function closeWin(win_name) {
			$('#' + win_name).window('close');
			//window.location.reload(true);
		}
		//关闭浮动窗口
		function closeWin1(win_name) {
			$('#' + win_name).window('close');
			window.location.reload(true);
		}
		
		
</script>