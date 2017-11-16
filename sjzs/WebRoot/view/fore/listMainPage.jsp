<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/view/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.0 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>审计之家</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link rel="stylesheet" href="css/fore/mainList.css">
	<link rel="stylesheet" href="js/treeView/css/jquery.treeview.css"/>
	<link rel="stylesheet" href="js/treeView/css/jquery.treeview.css" />
	
	<script type="text/javascript" src="js/treeView/scripts/jquery.js"></script>
	<script type="text/javascript" src="js/treeView/scripts/jquery.treeview.js"></script>
	
	<style type="text/css">
    	a:link {text-decoration: none ; color: black;}  
		a:active {text-decoration: none;}                                      
		a:visited {text-decoration: none;color:blue;}                          
		a:hover {text-decoration: underline ; color: #FFFFD5;} 
	</style>
	<script type="text/javascript">
		var treeNodes = [${sjzs_json_str}];
		function createtree(d) {
	        var list = '';
	        var url = '';
	        var ahref = '';
	        $.each(d, function (i, n) {
	            var cls = 'folder';
	            if(n.menuType == 1 ){//1-审计法规 
	         	    url = 'showInfo!showSjfg.action?sjfg.lawSort=' + n.id;
				}
				if(n.menuType == 2){ // 2-定性依据 
					url = 'showInfo!showDxyj.action?dxyj.parentID=' + n.id ;
				}
	            if(n.menuType == 3){//方法案例
	            	 url = 'showInfo!showFfal.action?ffal.sort=' + n.id;
				}
	            if(n.menuType == 4){//审计导航
	            	url = 'showInfo!showSjdh.action?sjdh.typeNo=' + n.id;
				}
	            if(n.menuType == 5){//审计事项
	            	url = 'showInfo!showSjsx.action?sjsx.dicParentId=' + n.id;
				}
	            if(n.menuType == 6){//审计实施方案
	            	url = 'showInfo!showSsfa.action?ssfa.sort=' + n.id;
				}
				if(n.id =='11111111' || n.id=='22222222'||n.id=='33333333'||n.id=='44444444'||n.id=='55555555'||n.id=='66666666'){
					ahref =	'<A  target="menuList">' + n.text + '</A>';
					list += '<li><span class="' + cls + '" ref="' + n.id + '"  pid="'+n.parentid+'" id="'+n.id+'" menuType="'+n.menuType+'" >'+ahref+'</span>';
					
				}else{
		            ahref =	'<A href="'+url+'" target="menuList">' + n.text + '</A>';
		            list += '<li class="closed"><span class="' + cls + '" ref="' + n.id + '"  pid="'+n.parentid+'" id="'+n.id+'" menuType="'+n.menuType+'" >'+ahref+'</span>';
				}
	            if (n.children.length > 0) {
	                list += "<ul>";
	                list += createtree(n.children);
	                list += "</ul>";
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
			$("#product_tree").treeview({
				animated: "fast",
				//collapsed: true,
				unique: true,
				control: "#mm1"
			});
		}
		$(function(){
			initTree();
			document.onselectstart=function(){return false;}
		});
		
	</script>
  </head>

   <body>
		<div style="width:100%;height:100%;">
			<div>
			<h3 >
   				<c:choose>
   					<c:when test='${type eq "sjfg"}'>
   						<c:set var="url" value="showInfo!showSjfg.action?type=init"/>
   						审计法规
   					</c:when>
   					<c:when test='${type eq "dxyj"}'>
   						<c:set var="url" value="showInfo!showDxyj.action?type=init"/>
   						定性依据
   					</c:when>
   					<c:when test='${type eq "ffal"}'>
   						<c:set var="url" value="showInfo!showFfal.action?type=init"/>
   						审计方法
   					</c:when>
   					<c:when test='${type eq "sjdh"}'>
   						<c:set var="url" value="showInfo!showSjdh.action?type=init"/>
   						审计导航
   					</c:when>
   					<c:when test='${type eq "sjsx"}'>
   						<c:set var="url" value="showInfo!showSjsx.action?type=init"/>
   						审计事项
   					</c:when>
   					<c:when test='${type eq "ssfa"}'>
   						<c:set var="url" value="showInfo!showSsfa.action?type=init"/>
   						实施方案
   					</c:when>
   				</c:choose>
   			</h3>
   			</div>
   			<div style="overflow-y:auto;">
   				<!-- border: 1px solid #98d2f3; -->
   				<div id="left" style="float:left;width:25%;height:100%;overflow-x:hidden;overflow-y:auto;">
   					<ul id="product_tree" class="filetree"></ul>
   				</div>
   				<div id="myright" style="float:left;margin-left:10px;width:70%;height:550px;overflow-x:hidden;overflow-y:hidden;">
   					<iframe id="menuList" name="menuList" src="${url }" frameborder="0" width="100%" height="100%"></iframe>
   				</div>
   			</div>
		</div> 
    <div style="clear:both;width:100%;height:20px;"></div>
  </body>
</html>
