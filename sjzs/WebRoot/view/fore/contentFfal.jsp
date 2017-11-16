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
    
    <title>查看详细信息---方法案例</title>
    
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
 		<a href="<%=basePath%>">审计之家</a> > 方法案例
 	</div>
			<c:choose>
				<c:when test="${fangFaAnLi != null}">
					<div class="top">
					<!-- 方法案例名称 -->
						<b class="ic-doc"></b><span>${fu:htmltoText(fangFaAnLi.title)}</span>
						
						<c:if test="${empty showStr }">
							<input type="button" value="下载文档" onclick="downloadAttach('${fangFaAnLi.attachId}')">
							<form method="post" action="attach/download/DownLoadServletSJZS" id="downloadForm" name="downloadForm" style="display: none;">
								<input type="hidden" name="attId" id ='attId' value="${fangFaAnLi.attachId}"/>
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
							<b>方法案例信息▼</b>
							<p>撰写人:&nbsp;${fangFaAnLi.author}</p>
							<p>撰写单位:&nbsp;${fangFaAnLi.department}</p>
							<p>获奖情况:&nbsp;${fangFaAnLi.awards}</p>
							<c:if test="${! fangFaAnli.uploadFlag eq \"0\"}">
								<input type="hidden" id="upClientId" value="${clientUpload.clientId}"/>
								<p>上传人:&nbsp;${clientUpload.clientName}</p>
								<p>上传时间:&nbsp;${clientUpload.uploadDate}</p>
								<input type="hidden" id="integral" value="${clientUpload.integral}"/>
								<p>下载所需积分:&nbsp;${clientUpload.integral}</p>
							</c:if>
						</div>
						<div class="center">
							&nbsp;&nbsp;&nbsp;方法案例简介
						</div>
						<div class="bottom" >
							${fu:htmltoText(fangFaAnLi.content)}
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
