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
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/fore/mainPage.css">
	<script language="javascript" src="js/fore/mainPage.js"></script>
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
  </head>
  <%--
  <body onload="chushi('${parameters.showType[0] }')">
   --%>
   <body onload="chushi('${type }')">
    <div class="main_top">
    	<jsp:include page="mainPage_top.jsp"></jsp:include>
    </div>
   
    <div class="main_cont">
		<div class="cont_list" style="width:100%;height:620px;">
			<iframe id="mainList" frameborder="0" width="100%" height="100%" scrolling="no" src="" style="overflow: auto;"></iframe>
			<script type="text/javascript">
				$(function(){
					var type = "${type}";
					if("sjfg"==type){
						$("#mainList").attr("src","showInfo!showSjfg.action?type=sjfg");
					}else if("dxyj"==type){
						$("#mainList").attr("src","showInfo!showDxyj.action?type=dxyj");
					}else if("ffal"==type){
						$("#mainList").attr("src","showInfo!showFfal.action?type=ffal");
					}else if("sjdh"==type){
						$("#mainList").attr("src","showInfo!showSjdh.action?type=sjdh");
					}else if("sjsx"==type){
						$("#mainList").attr("src","showInfo!showSjsx.action?type=sjsx");
					}else if("ssfa"==type){
						$("#mainList").attr("src","showInfo!showSsfa.action?type=ssfa");
					}
				});
			</script>
		<%--
			<h3 >
   				<c:choose>
   					<c:when test="${contentMap.type == \"sjfg\"}">
   						审计法规
   					</c:when>
   					<c:when test="${contentMap.type == \"dxyj\"}">
   						定性依据
   					</c:when>
   					<c:when test="${contentMap.type == \"ffal\"}">
   						方法案例
   					</c:when>
   					<c:when test="${contentMap.type == \"sjdh\"}">
   						审计导航
   					</c:when>
   					<c:when test="${contentMap.type == \"sjsx\"}">
   						审计事项
   					</c:when>
   					<c:when test="${contentMap.type == \"ssfa\"}">
   						实施方案
   					</c:when>
   					<c:otherwise>
   						${contentMap.type}
   					</c:otherwise>
   				</c:choose>
   			</h3>
   			<div class="cont_each_list"><c:set var="nums" value="1"></c:set>
   				<!-- border: 1px solid #98d2f3; -->
   				<div id="left" style="float: left; width: 19%; height:100%;white-space:normal;overflow-x:hidden;overflow-y:scroll;">
   					<ul id="product_tree" class="filetree"></ul>
   				</div>
   				<div id="myright" style="float: left;margin-left: 4px;width: 80%;height:100%;">
   					<iframe id="menuList" name="menuList" src="<%=basePath %>${url}" frameborder="0" width="100%" height="100%"></iframe>
   				<!--
   				<c:forEach var="content" items="${contentMap.contentList}">
   					<c:choose>
   						<c:when test="${contentMap.type == \"sjsx\"}">
   							<c:if test="${nums==1}">
   							<div class="list_cont_sjsx" style="background-color: #dedede;color: black;" >
   								<div class="sp_title">事项名称</div>
   								<div class="sp_content">事项说明</div>
   								<div class="sp_clas">事项分类</div>
   								<div class="sp_date">日期</div></div>
   							</c:if>
   							<div class="list_cont_sjsx" onmousemove="changebg(this,'1')"	onmouseout="changebg(this,'0')"	>
    						<c:set var="nums" scope="page" value="2"></c:set>
   						</c:when>
   						<c:otherwise>
   							<div class="list_cont" onmousemove="changebg(this,'1')"	onmouseout="changebg(this,'0')"	>
   						</c:otherwise>
   					</c:choose>
    					<c:choose>
    						<c:when test="${empty employee}">
    							<c:set var="href" scope="page" value="#"></c:set>
    						</c:when>
	    					<c:when test="${contentMap.type == \"sjfg\"}">			    						
	    						<c:set var="href" scope="page" value="search!showContentFG.action?id=${content.id }"></c:set>
	    					</c:when>
	    					<c:when test="${contentMap.type == \"dxyj\"}">
	    						<c:set var="href" scope="page" value="search!showContentYJ.action?id=${content.id }"></c:set>
	    					</c:when>
	    					<c:when test="${contentMap.type == \"ffal\"}">
	    						<c:set var="href" scope="page" value="search!showContentAL.action?id=${content.id }"></c:set>
	    					</c:when>
	    					<c:when test="${contentMap.type == \"sjdh\"}">
	    						<c:set var="href" scope="page" value="search!showContentDH.action?id=${content.id }"></c:set>
	    					</c:when>
	    					<c:when test="${contentMap.type == \"sjsx\"}">
	    							<c:set var="href" scope="page" value="search!showContentSX.action?id=${content.id }"></c:set>
	    					</c:when>
	    					<c:when test="${contentMap.type == \"ssfa\"}">
	    						<c:set var="href" scope="page" value="search!showContentEI.action?id=${content.id }"></c:set>
	    					</c:when>
	    				</c:choose>	    			
	    				<div class="sp_title">
	    					<c:choose>
	    						<c:when test="${contentMap.type == \"sjsx\"}">
	    							<p title="${content.title }" style="white-space: nowrap;">${content.title }</p>
	    						</c:when>
	    						<c:otherwise>
	    							<c:choose>
				    					<c:when test="${href eq \"#\"}">
				    						<a href="${href }" onclick="return wain()" title="${content.title }">${content.title }</a>
				    					</c:when>
				    					<c:otherwise>
				    						<a href="${href }" target="_blank" title="${content.title }">${content.title }.</a>
				    					</c:otherwise>
				    				</c:choose>	
	    						</c:otherwise>
	    					</c:choose>		
	    				</div>	    					
    					<div class="sp_content" title="${fu:htmltoText(content.content)}">${fu:htmltoText(content.content)}</div>
    					<div class="sp_clas" title="${content.obj.industry}">${content.obj.industry } </div>
    					<div class="sp_date" title="${content.date }">${content.date }</div>
   				</c:forEach>
   				 -->
   				</div>
   			</div>
   			  --%>
		</div> 
    </div>
   
    <div style="clear:both;width:100%;height:20px;"></div>
    <div class="boot">
    	<jsp:include page="boot.jsp"></jsp:include>
    </div>
  </body>
</html>
