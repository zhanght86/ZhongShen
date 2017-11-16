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
		a:link {text-decoration: none ; color: black;}  
		a:active {text-decoration: none;}                                      
		a:visited {text-decoration: none;color:blue;}                          
		a:hover {text-decoration: underline ; color: #FFFFD5;}
		*{font-size:12px;}
		.current{ background:blue;color:white;}
	</style>
	<script type='text/javascript'>
	var treeNodes = [${sjzs_json_str}];
		function createtree(d) {
	        var list = '';
	        var url = '';
	        var ahref = '';
	        $.each(d, function (i, n) {
	            var cls = 'folder';
	          //  if (n.children.length == 0)
	          //      cls = 'file';	
                if(n.menuType == 1 ){//1-审计法规 
             	   url = 'system/sjfg!searchMessage.action?law.lawSort=' + n.id + "&menuType=1";
				}
				if(n.menuType == 2){ // 2-定性依据 
					url = 'system/dxyj!searchMessage.action?law.parentID=' + n.id+ "&menuType=2";
				}
                if(n.menuType == 3){//方法案例
                	 url = 'system/ffal!searchMessage.action?ffal.sort=' + n.id + "&menuType=3";
				}
                if(n.menuType == 4){//审计导航
                	url = 'system/sjdh!serachSjdhMethod.action?sjdhMethod.typeNo=' + n.id;
				}
                if(n.menuType == 5){//审计事项
                	url = 'system/dataDic!searchMessage.action?dataDicDto.dicParentId=' + n.id;
				}
                if(n.menuType == 6){//审计实施方案
                	url = 'system/ssfa!searchMessage.action?shiShiFangAn.sort=' + n.id+ "&menuType=6";
				}
				if(n.id =='11111111' || n.id=='22222222'||n.id=='33333333'||n.id=='44444444'||n.id=='55555555'||n.id=='66666666'){
					ahref =	'<A class="" target="menuList">' + n.text + '</A>';
					list += '<li><span class="' + cls + '" ref="' + n.id +' " leaf="'+n.leaf+ '"  pid="'+n.parentid+'" id="'+n.id+'" menuType="'+n.menuType+'" >'+ahref+'</span>';
				}else{
		            ahref =	'<A href="'+url+'" target="menuList">' + n.text + '</A>';
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
					'addMenu': function(t) {
						var id = $(t).attr('id');
						var pid = $(t).attr('pid');
						var txt = $(t).children('a').text();
						var type = $(t).attr('menuType');
						typeTemp = type;
						addMenu(id,type);
					 },
					 'edit': function(t) {
						var id = $(t).attr('id');
						var pid = $(t).attr('pid');
						var txt = $(t).children('a').text();
						var type = $(t).attr('menuType');
						updateMenuInfo(pid, id,type);
						typeTemp = type;
					 },
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
						 },
					 'delete': function(t) {
						var id = $(t).attr('id');var pid = $(t).attr('pid');var txt = $(t).children('a').text();delMenu(id);}}});
	
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
		      <ul>
		        <li id="addcom"><img src="js/treeView/images/add.png" />添加子菜单</li>
		        <li id="addMenu"><img src="js/treeView/images/add.png" />添加菜单</li>
		        <li id="edit"><img src="js/treeView/images/edit.gif"/>修改</li>
		        <li id=""><img src="js/treeView/images/delete.png"/>删除</li>
		         <li id="daoru"><img src="js/treeView/images/edit.gif"/>导入</li>
		      </ul>
		    </div>  		
			 <c:if test="${fu:check('system/sjzs!goAddPage.action')}">
				<div class="contextMenu" id="myMenu2" style="visibility: hidden">
			      <ul>
			       	<c:if test="${fu:check('system/sjzs!goAddPage.action')}">
			      	  <li id="addMenu"><img src="js/treeView/images/add.png" />添加</li>
			        </c:if>
			        <c:if test="${fu:check('system/sjzs!goUpdatePage.action')}">
			      	  <li id="edit"><img src="js/treeView/images/edit.gif"/>修改</li>
			        </c:if>
			        <c:if test="${fu:check('system/sjzs!deleteMenu.action')}">
			      	  <li id="delete"><img src="js/treeView/images/delete.png"/>删除</li>
			        </c:if>
			         <c:if test="${fu:check('system/sjzs!goDaoRuPage.action')}">
			      	  <li id="daoru"><img src="js/treeView/images/edit.gif"/>导入</li>
			        </c:if>
			      </ul>
			    </div> 
		    </c:if> 			
  		</div>
  		<div id="myright" style="float: left;height: 100%;margin-left: 4px;border: 1px solid #98d2f3;width: 80% ">
  			<iframe id="menuList" name="menuList" src="<%=basePath %>welcome.jsp" frameborder="0" width="100%" height="100%"></iframe>
  		</div>
  	</div>
  	<!-- 浮动窗口  添加/修改部门-->
	<div id="addMenuWin" iconCls="icon-save" title="添加" style="width: 0;height: 0"></div> 
	<div id="updMenuWin" iconCls="icon-edit" title="修改" style="width: 0;height: 0"></div> 
	<div id="daoruMenuWin" iconCls="icon-edit" title="导入" style="width: 0;height: 0"></div> 
  </body>

</html>
<script type='text/javascript'>
		//添加
		function addMenu(menuId,menuType) {
			var reqURL = "<%=basePath%>system/sjzs!goAddPage.action?sjzhMenuTree.menuParent=" + menuId + "&sjzhMenuTree.menuType="+menuType+"&nocache=" + new Date().getTime();
			$('#addMenuWin').window({   
				width:700,   
				height:350,   
				modal:true,
				collapsible:false,
				minimizable:false, 
				maximizable:false,
				closable:true
			});
			var iframe_str = "<iframe height=\"100%\" width=\"100%\" scrolling=\"auto\" frameborder=\"0\" src=\"" + reqURL + "\"></iframe>";
			$('#addMenuWin').html(iframe_str);
		}
		
		
		function add(reqURL) {
		
			$('#addMenuWin').window({   
				width:700,   
				height:350,   
				modal:true,
				collapsible:false,
				minimizable:false, 
				maximizable:false,
				closable:true
			});
			var iframe_str = "<iframe height=\"100%\" width=\"100%\" scrolling=\"auto\" frameborder=\"0\" src=\"" + reqURL + "\"></iframe>";
			$('#addMenuWin').html(iframe_str);
		}
		
		
		
		
		
		
		function addFFAL(reqURL,width,height) {
		
			$('#addMenuWin').window({   
				width:width,   
				height:height,   
				modal:true,
				collapsible:false,
				minimizable:false, 
				maximizable:false,
				closable:true
			});
			var iframe_str = "<iframe height=\"100%\" width=\"100%\" scrolling=\"auto\" frameborder=\"0\" src=\"" + reqURL + "\"></iframe>";
			$('#addMenuWin').html(iframe_str);
		}
		
		function updateFFAL(reqURL,width,height) {
		
			$('#updMenuWin').window({   
				width:width,   
				height:height,   
				modal:true,
				collapsible:false,
				minimizable:false, 
				maximizable:false,
				closable:true
			});
			var iframe_str = "<iframe height=\"100%\" width=\"100%\" scrolling=\"auto\" frameborder=\"0\" src=\"" + reqURL + "\"></iframe>";
			$('#updMenuWin').html(iframe_str);
		}
		
		
		
		function adddxyj(reqURL,width,height) {
		
			$('#addMenuWin').window({   
				width:width,   
				height:height,   
				modal:true,
				collapsible:false,
				minimizable:false, 
				maximizable:false,
				closable:true
			});
			var iframe_str = "<iframe height=\"100%\" width=\"100%\" scrolling=\"no\" frameborder=\"0\" src=\"" + reqURL + "\"></iframe>";
			$('#addMenuWin').html(iframe_str);
		}
		
		function upddxyj(reqURL,width,height) {
		
			$('#updMenuWin').window({   
				width:width,   
				height:height,   
				modal:true,
				collapsible:false,
				minimizable:false, 
				maximizable:false,
				closable:true
			});
			var iframe_str = "<iframe height=\"100%\" width=\"100%\" scrolling=\"no\" frameborder=\"0\" src=\"" + reqURL + "\"></iframe>";
			$('#updMenuWin').html(iframe_str);
		}
		

		
		
		
		function addSjdh(reqURL,width,height) {
			$('#addMenuWin').window({   
				width:width,   
				height:height,   
				modal:true,
				minimizable:false, 
				maximizable:false,
				closable:true
			});
			var iframe_str = "<iframe height=\"100%\" width=\"100%\" scrolling=\"no\" frameborder=\"0\" src=\"" + reqURL + "\"></iframe>";
			$('#addMenuWin').html(iframe_str);
		}
		function updSjdh(reqURL,width,height) {
			
			$('#updMenuWin').window({   
				width:width,   
				height:height,   
				modal:true,
				collapsible:false,
				minimizable:false, 
				maximizable:false,
				closable:true
			});
			var iframe_str = "<iframe height=\"100%\" width=\"100%\" scrolling=\"no\" frameborder=\"0\" src=\"" + reqURL + "\"></iframe>";
			$('#updMenuWin').html(iframe_str);
		}
		

		
		
		//修改
		function updateMenuInfo(parentId, menuId,type) {
		
			if(menuId =='11111111' ||menuId == '22222222' ||menuId == '33333333' ||menuId == '44444444'){
				alert('根菜单不允许修改！');
			}else{
				var reqURL = "<%=basePath%>system/sjzs!goUpdatePage.action?parentCode=" + parentId + "&sjzhMenuTree.menuId=" + menuId + "&nocache=" + new Date().getTime();
				$('#updMenuWin').window({   
					width:730,   
					height:350,   
					modal:true,
					collapsible:false,
					minimizable:false, 
					maximizable:false,
					closable:true
				});
				var iframe_str = "<iframe height=\"100%\" width=\"100%\" scrolling=\"auto\" frameborder=\"0\" src=\"" + reqURL + "\"></iframe>";
				$('#updMenuWin').html(iframe_str);
			}
		}

		//导入
		function daoruInfo(parentId, menuId,type) {
		
			if(menuId =='11111111' ||menuId == '22222222' ||menuId == '33333333' ||menuId == '44444444'||menuId == '55555555'||menuId == '66666666'){
				alert('根菜单不允许导入！');
			}else{
				var reqURL = "<%=basePath%>system/sjzs!goDaoRuPage.action?parentCode=" + parentId + "&sjzhMenuTree.menuId=" + menuId + "&nocache=" + new Date().getTime()+"&type="+type;
				$('#daoruMenuWin').window({   
					width:630,   
					height:260,   
					modal:true,
					collapsible:false,
					minimizable:false, 
					maximizable:false,
					closable:true
				});
				var iframe_str = "<iframe height=\"100%\" width=\"100%\" scrolling=\"auto\" frameborder=\"0\" src=\"" + reqURL + "\"></iframe>";
				$('#daoruMenuWin').html(iframe_str);
			}
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
		
		//删除
		function delMenu(jmcode) {
			if(jmcode =='11111111' ||jmcode == '22222222' ||jmcode == '33333333' ||jmcode == '44444444'  ||jmcode == '55555555'  ||jmcode == '66666666'){
				alert('根菜单不允许删除！');
			}else{
			$.messager.confirm('确认','你认要删除当前菜单?',function(r){
				if (r){
						window.location.href = "<%=basePath%>system/sjzs!deleteMenu.action?menuCodes=" + jmcode;}
				});
			}
		}

</script>