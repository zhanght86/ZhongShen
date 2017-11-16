<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ include file="/view/common/common.jsp" %>
	<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath %>">
		<base target="self"> 
		<title>suc</title>
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	    <script type="text/javascript" src="js/jquery.easyui.pack.js"></script>
	</head>
	<body>
	<c:choose>
	<c:when test="${imageFileName != null}">
		<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0" width="100%" height="100%">
			<param name="movie" 	value="<%=basePath %>files/sjzs/swf/<s:property value="imageFileName"/>.swf" />
			<param name="quality" value="high">
			<embed src="<%=basePath %>files/sjzs/swf/<s:property value="imageFileName"/>.swf" width="90%" height="90%" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash"></embed>
			</embed>
		</object>
		</c:when>
	</c:choose>
	<c:choose>
	<c:when test="${imageFileName == null}">
	<script type="text/javascript">
	$(function() {
		if(${closePage == null}){
			alert("没有附件");
			history.go(-1);
			//window.parent.refreshListPage();
		}else{
			self.parent.msgShow("没有附件","没有附件","info");
			window.parent.closePage = "${closePage}";
			window.parent.closewindow();
		}
	});

	function reloadPage(){
		history.go(-1);
	}
	</script>
	</c:when>
</c:choose>

	
	</body>
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
</html>

