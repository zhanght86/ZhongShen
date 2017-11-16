<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.hnzskj.common.Constant" %>
<%@ include file="/view/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>查看详细信息---审计导航</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">	
	<script src="js/treeView/scripts/jquery.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="fckeditor/fckeditor.js"></script>
	<link rel="stylesheet" type="text/css" href="css/fore/newContent.css">
	
	<script type="text/javascript" language="javascript">
		function checkType( type ){
			var sAction = "search!searchAll.action";
			var sForm = document.getElementById('searchForm');
			sForm.action = sAction;
			//$("#contentForm").attr("action",sAction);
			$("#type").attr("value",type);
			var g="#"+type;
			var style="color:black;font-size:14;text-decoration:none;"
			$("a").attr("style","");
			$(g).attr("style",style);
			$("#reqPage").value=0;
			$("#searchForm").submit();
		}
		function inits(){
			var type="${sessionScope.type}"+"";
			var typeId="";
			var sAction="";
			if(type!="" && type !=null){
				typeId = $("#type").attr("value",type);
			}else{
				typeId = $("#type").attr("value","All");
				type="All";
			}
			sAction = "search!searchAll.action";
			$("#searchForm").attr("action",sAction);//action=sAction;
			var g="#"+type;
			var style="color:black;font-size:14;text-decoration:none;"
			$(g).attr("style",style);				
		}		
		document.oncontextmenu=function(e){return false;}
		</script>		
  </head>
  <body onload="inits()" >
  		<jsp:include page="cont_top.jsp"></jsp:include>
 <div id="content" onselectstart="return false" >
 	<div class="menu">
 		<a href="<%=basePath%>">审计之家</a> > 审计导航
 	</div>
	<c:choose>
		<c:when test="${! empty sjdhMethod}">	
			<div class="content_left" style="width: 1052px;">
				<div class="top" style="width: 1050px;">
					<b class="ic-tu"></b><span>${sjdhMethod.name}</span>
				</div>
				<div class="bottom"  style="width: 1050px;">
					<iframe style="width: 100%;height:100%;margin-top:-25px;"
							src="search!showDHT.action?id=${sjdhMethod.template_no }">
						
					</iframe>
				</div>
			</div>											
		</c:when>
		<c:otherwise>
			<div align="center" style="margin-top: 10px;" >
				<table width="100%" border="0" cellspacing="0" cellpadding="0" id="show">							
					<tr>
						<td>
							没有相关法规依据！
						</td>
					</tr>
				</table>
			</div>

		</c:otherwise>
	</c:choose>	
		<div class="all_boot">
			<div class="boot">
				<jsp:include page="boot.jsp"></jsp:include>
			</div>
		</div>		
	</div>
  </body>
</html>
