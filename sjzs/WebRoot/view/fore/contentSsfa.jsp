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
    
    <title>查看详细信息---实施方案</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="js/treeView/scripts/jquery.min.js" type="text/javascript"></script>
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
 		<a href="<%=basePath%>">审计之家</a> > 实施方案
 	</div>
			<c:choose>
				<c:when test="${ssfaMethod != null}">
					<div class="top">
					<!-- 方法案例名称 -->
						<b class="ic-doc"></b><span>${ssfaMethod.name}</span>
						<c:if test="${empty showStr }">	
							<input type="button" value="下载文档" onclick="downloadAttach('${ssfaMethod.attachId}')">
							<form method="post" action="attach/download/DownLoadServletSJZS" id="downloadForm" name="downloadForm" style="display: none;">
								<input type="hidden" name="attId" id ='attId' value="${ssfaMethod.attachId}"/>
							</form>
						</c:if>
					</div>	
					<div class="content_left">
						<div class="bottom">
						<!-- 方法案例内容 -->
							<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0" width="100%" height="100%">
								<param name="movie" 	value="<%=basePath %>files/sjzs/swf/<s:property value="imageFileName"/>.swf" />
								<param name="quality" value="high">
								<embed src="<%=basePath %>files/sjzs/swf/<s:property value="imageFileName"/>.swf" width="100%" height="100%" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash"></embed>
							</object>							
							<script type="text/javascript">
							
							var basePath = "<%=basePath%>";
						 	function onloadSuc(){
								$.ajax({
									url:basePath + "attach!deleteTempFiles.action",
									type: "POST",
									success: function(data){
										if(data){
										}
									}
								});
							}
							</script>
						</div>
					</div>
					<div class="content_right">
						<div class="top">
							<b>实施方案信息▼</b>
							<p>所属行业:&nbsp;${ssfaMethod.industry}</p>
							<p>撰写日期:&nbsp;${ssfaMethod.writeDate}</p>
							
						</div>
						<div class="center">
							&nbsp;&nbsp;&nbsp;实施方案关键字
						</div>
						<div class="bottom">
							${fu:htmltoText(ssfaMethod.keyWord)}
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