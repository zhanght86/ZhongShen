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
	<script language="javascript" src="js/fore/mainPage.js"></script>
	<link rel="stylesheet" type="text/css" href="css/fore/mainPage.css">	
  </head>
  
  <body onload="chushi('${type}')">
    <div class="main_top">
    	<jsp:include page="mainPage_top.jsp"></jsp:include>
    </div>
    <div class="main_cont">
    	<div class="main_bann">
    		<img alt="审计之家" src="<%=basePath %>images/fore/banner.jpg">
    	</div>
    	<div class="main_each">
    	 <c:forEach var="contentMap" varStatus="status" items="${contentMapList}" > 
			<c:choose>
				<c:when test="${contentMap.type == \"sjfg\"}">
					<div class="each_cont_sjfg">
						<div class="cont_title">
							<img class="title_logo" alt="审计法规" src="<%=basePath %>images/fore/title/title01.png"/>
							<b>审计法规</b>
							<a href="showInfo!showSjfg.action" target="_blank"><img border="0" class="title_more" alt="更多" src="<%=basePath %>images/fore/more.png"></a>
						</div>
						<div class="cont_biref">
		    				<img class="biref_logo" alt="审计法规" src="<%=basePath %>images/fore/biref/biref01.png">
		    				<span class="biref_cont">
		    					<a href="search!testAddIndex.action">创建索引</a>
		    					提供了丰富的审计法律、法规资料，审计人员再也不用翻阅厚厚的书籍，能够快速查找所需了解的法律、法规信息。
		    				</span>
		    			</div>
				</c:when>
				<c:when test="${contentMap.type == \"dxyj\"}">
					<div class="each_cont_dxyj">
						<div class="cont_title">
							<img class="title_logo" alt="审计法规" src="<%=basePath %>images/fore/title/title02.png"/>
							<b>定性依据</b>
							<a href="showInfo!showDxyj.action" target="_blank"><img border="0" class="title_more" alt="更多" src="<%=basePath %>images/fore/more.png"></a>
						</div>
						<div class="cont_biref">
		    				<img class="biref_logo" alt="审计法规" src="<%=basePath %>images/fore/biref/biref02.png">
		    				<span class="biref_cont">
		    					审计定性是对经审计证据证实的事项进行分析，对其中存在问题的性质对照相应的法规予以定性，并提出相应的处理意见和建议。
		    				</span>
		    			</div>
				</c:when>
				<c:when test="${contentMap.type == \"ffal\"}">
					<div class="each_cont_ffal">
						<div class="cont_title">
							<img class="title_logo" alt="审计法规" src="<%=basePath %>images/fore/title/title03.png"/>
							<b>方法案例</b>
							<a href="showInfo!showFfal.action" target="_blank"><img border="0" class="title_more" alt="更多" src="<%=basePath %>images/fore/more.png"></a>
						</div>
						<div class="cont_biref">
		    				<img class="biref_logo" alt="审计法规" src="<%=basePath %>images/fore/biref/biref03.png">
		    				<span class="biref_cont">
		    					提炼总结出了历年来优秀的审计方法、案例，为审计人员在审计过程中提供学习和参考，直接复用和升华出符合项目需要的审计方法、案例。
		    				</span>
		    			</div>
				</c:when>
				<c:when test="${contentMap.type == \"sjdh\"}">
					<div class="each_cont_sjdh">
						<div class="cont_title">
							<img class="title_logo" alt="审计法规" src="<%=basePath %>images/fore/title/title04.png"/>
							<b>审计导航</b>
							<a href="showInfo!showSjdh.action" target="_blank"><img border="0" class="title_more" alt="更多" src="<%=basePath %>images/fore/more.png"></a>
						</div>
						<div class="cont_biref">
		    				<img class="biref_logo" alt="审计法规" src="<%=basePath %>images/fore/biref/biref04.png">
		    				<span class="biref_cont">
		    					以导航的形式描述不同的审计项目执行的全过程，使审计人员能够以导航为参考，了解审计工作的整个过程，为具体审计项目的执行提供导航、帮助，从整体上提高审计效率、降低审计风险。
		    				</span>
		    			</div>
				</c:when>				
				<c:when test="${contentMap.type == \"ssfa\"}">
					<div class="each_cont_ssfa">
						<div class="cont_title">
							<img class="title_logo" alt="审计法规" src="<%=basePath %>images/fore/title/title05.png"/>
							<b>实施方案</b>
							<a href="showInfo!showSsfa.action" target="_blank"><img border="0" class="title_more" alt="更多" src="<%=basePath %>images/fore/more.png"></a>
						</div>
						<div class="cont_biref">
		    				<img class="biref_logo" alt="审计法规" src="<%=basePath %>images/fore/biref/biref05.png">
		    				<span class="biref_cont">
		    					审计实施方案的目的在于抓住主要问题和环节，有层次、有步骤、有秩序、有计划地开展审计工作。
		    				</span>
		    			</div>
				</c:when>
				<c:when test="${contentMap.type == \"sjsx\"}">
					<div class="each_cont_sjsx">
					<div style="display: hidden;padding:0;margin:0;"></div>
						<img class="biref_logo" alt="审计法规" src="<%=basePath %>images/fore/biref/biref06.png">
						<div class="cont_title">
							<b>审计事项</b>
							<a href="showInfo!showSjsx.action" target="_blank" class="title_more">[更多]</a>
						</div>
						<div class="cont_biref">		    				
		    				<span class="biref_cont">
		    					审计事项是指需要审核其真实性合法性效益性的每一个审计的具体内容。审计事项支持在审计机关编制审计工作方案、
		    					审计组编制审计实施方案、 描述审计项目实施内容、汇总审计成果、编写计算机审计方法、
		    					总结AO应用实例等方面起到标识的作用。
		    				</span>
		    			</div>
		    			<br/>
				</c:when>
				<c:otherwise>
						${contentMap.type}
				</c:otherwise>
				</c:choose>
				
  			<div class="cont_list">
  			<br/>
  				<ul>
  				<c:set var="nums" scope="page" value="1"></c:set>
  				<c:forEach var="content" items="${contentMap.contentList}">
  					<c:choose>
  						<c:when test="">
  							<c:if test="${nums==1}">
    							<li>
	    							<table cellpadding="0" cellspacing="0" style="margin:0;padding:0;color:black;">
	    							<tr>
	    								<td class="a">
	    									<div>事项名称</div>
	    								</td>
	    								<td class="b">
	    									<div>事项说明</div>
	    								</td>
	    								<td class="c">
	    									<div>事项分类</div>
	    								</td>
	    								<td class="d">
	    									<div>日期</div>
	    								</td>
	    							</tr>
	    							</table>	
	    						</li>
    						</c:if>
    						<c:set var="nums" scope="page" value="2"></c:set>	
  						</c:when>  						
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
    					<c:when test="${contentMap.type == \"ssfa\"}">
    						<c:set var="href" scope="page" value="search!showContentEI.action?id=${content.id }"></c:set> 	
    					</c:when>
    					<%--<c:when test="${contentMap.type == \"sjsx\"}">
    							<c:set var="href" scope="page" value="search!showContentSX.action?id=${content.id }"></c:set>
    					</c:when>
    				--%></c:choose>    				
    				<c:choose>    				
    					<c:when test="${contentMap.type == \"sjsx\"}">    										
    						<c:if test="${nums==1}">
    							<li style="background-color: #e5f4ee;">
	    							<table cellpadding="0" cellspacing="0" style="margin:0;padding:0;color:black;">
	    							<tr>
	    								<td class="a">
	    									<div>事项名称</div>
	    								</td>
	    								<td class="b">
	    									<div>事项说明</div>
	    								</td>
	    								<td class="c">
	    									<div>事项分类</div>
	    								</td>
	    								<td class="d">
	    									<div>日期</div>
	    								</td>
	    							</tr>
	    							</table>	
	    						</li>
    						</c:if>
    						<c:set var="nums" scope="page" value="2"></c:set>
   								<li>
	    							<table cellpadding="0" cellspacing="0" style="margin:0;padding:0;">
	    							<tr>
	    								<td class="a">
	    									<div>${content.title }</div>
	    								</td>
	    								<td class="b">
	    									<div>${content.obj.dicMemo }</div>
	    								</td>
	    								<td class="c">
	    									<div>${content.obj.industry }</div>
	    								</td>
	    								<td class="d">
	    									<div>
	    									${content.obj.updateDate }</div>
	    								</td>
	    							</tr>
	    							</table>	
	    						</li>						
    					</c:when>
    					<c:otherwise>
    						<c:choose>
		    					<c:when test="${href eq \"#\"}">
		    						<li><a href="${href }" onclick="wain()" title="${content.title }" >${content.title } </a></li>
		    					</c:when>
		    					<c:otherwise>
		    						<li><a href="${href }" target="_blank" title="${content.title }">${content.title } </a></li>
		    					</c:otherwise>
		    				</c:choose>
		    			</c:otherwise>
    				</c:choose>
   				</c:forEach>
   				</ul>
  			</div>
  			</div>
 			</c:forEach> 
    	</div>
    </div>
    <div style="clear:both;width:100%;height:10px;"></div>
    <div class="boot">
    	<jsp:include page="boot.jsp"></jsp:include>
    </div>
  </body>
</html>
