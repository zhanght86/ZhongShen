<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.hnzskj.persist.bean.system.Employee"%>
<%@ page import="com.hnzskj.common.Constant" %>
<%@ include file="/view/common/common.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
  
  request.setAttribute("fg",Constant.SJFG);//审计法规
  request.setAttribute("dh",Constant.SJDH);//审计导航
  request.setAttribute("yj",Constant.DXYJ);//审计依据
  request.setAttribute("al",Constant.FFAL);//方法案例
  request.setAttribute("sx",Constant.SJSX);//审计事项
  request.setAttribute("fa",Constant.SJSS);//实施方案
  

%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>审计之家</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script src="js/treeView/scripts/jquery.min.js" type="text/javascript"></script>
		<script type='text/javascript' src='js/treeView/scripts/jquery.contextmenu.r2-min.js'></script>
		<script src="js/treeView/scripts/jquery.treeview.js" type="text/javascript"></script>
		<link rel="stylesheet" href="js/treeView/css/jquery.treeview.css" />
		<link rel="stylesheet" type="text/css" href="js/themes/default/easyui.css"/>
		<link rel="stylesheet" type="text/css" href="js/themes/icon.css"/>
		<script type="text/javascript" src="js/jquery.easyui.min.1.2.2.js"></script>
		<script type="text/javascript" src='js/locale/easyui-lang-zh_CN.js'></script>
		<link rel="stylesheet" href="css/validate.css" type="text/css"></link>
		
		<script type="text/javascript" language="javascript">
			function checkType( searchType ){
				var sAction = "search!searchAll.action";
				var sForm = document.getElementById('searchForm');
				sForm.action = sAction;
				$("#searchType").attr("value",searchType);
				var g="#"+searchType;
				var style="color:black;font-size:14;text-decoration:none;"
				$("a").attr("style","");
				$(g).attr("style",style);
				$("#reqPage").value=0;
				$("#searchForm").submit();
			}
			function inits(){
				var searchType="${searchType}"+"";
				var typeId="";
				var sAction="";
				if(searchType!="" && searchType !=null){
					typeId = $("#searchType").attr("value",searchType);
				}else{
					typeId = $("#searchType").attr("value","All");
					searchType="All";
				}
				sAction = "search!searchAll.action";
				$("#searchForm").attr("action",sAction);//action=sAction;
				var g="#"+searchType;
				var style="color:black;font-size:14;text-decoration:none;"
				$(g).attr("style",style);
				$("#searchText").focus();
			}
			
				
			//翻页
			function goNextPage( currentPage ) {
				document.getElementById("reqPage").value = currentPage;
				document.forms.namedItem("searchForm").submit();
			}			

			function KeyDown(types) { 
			 if(event.keyCode==13){
				if(types=="1"){
					 $("#loginSub").click();
				}else if(types=="0"){
					//$("#regSub").click();
				}			 
			 }
			}
							
		</script>		
		<s:if test="page == null" >
			<link rel="stylesheet" type="text/css" href="css/fore/style.css">
		</s:if>
		<s:else>
			<link rel="stylesheet" type="text/css" href="css/fore/searchList.css">
		</s:else>
	</head>

	<body onload="inits()" >	
		<div id="all" >
		<form  id="searchForm" name="searchForm" method="post">
				<div id="top">
					<div class="pageContent">
						<c:if test="${!empty employee}">
							<a href="reglogin!showHomePage.action"  target="_blank">${employee.emplName}</a>
							<a href="reglogin!exit.action">退出</a>
						</c:if>
					</div>					
				</div>
				<div id="main">
					<div class="title">
						<a hideFocus="true" href="<%=basePath%>">
							<img border="0" alt="审计之家" src="images/fore/logobitT.png">
						</a>
					</div>
					<div class="serc">						
						<input type="hidden" value="<s:property value="page.curPage"/>"
						name="page.curPage" id="reqPage" />
						<input type="hidden" id="searchType" name="searchType" value="${searchType }">
						<table align="right">
							<tr id="menu">
								<td>
									<a href="javascript:void(0)" onclick="checkType('<%=Constant.ALL %>')" class="first" id="<%=Constant.ALL %>">全&nbsp;部</a>
								</td>
								<td>
									<a href="javascript:void(0)" onclick="checkType('<%=Constant.SJFG %>')" id="<%=Constant.SJFG %>">审计法规</a>
								</td>
								<td>
									<a href="javascript:void(0)" onclick="checkType('<%=Constant.DXYJ %>')" id="<%=Constant.DXYJ %>">定性依据</a>
								</td>
								<td>
									<a href="javascript:void(0)" onclick="checkType('<%=Constant.FFAL %>')" id="<%=Constant.FFAL %>">方法案例</a>
								</td>
								<td>
									<a href="javascript:void(0)" onclick="checkType('<%=Constant.SJDH %>')" id="<%=Constant.SJDH %>">审计导航</a>
								</td>
								<td>
									<a href="javascript:void(0)" onclick="checkType('<%=Constant.SJSX %>')" id="<%=Constant.SJSX %>">审计事项</a>
								</td>
								<td>
									<a href="javascript:void(0)" onclick="checkType('<%=Constant.SJSS %>')" id="<%=Constant.SJSS %>">实施方案</a>
								</td>
								<td>
									&nbsp;
								</td>
							</tr>
							<tr>
								<td colspan="7" align="center">
									<%--
										onKeyUp="value=value.replace(/[^a-zA-Z0-9\u4E00-\u9FA5]/g,'')" onMouseOver="value=value.replace(/[^a-zA-Z0-9\u4E00-\u9FA5]/g,'')"
										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^a-zA-Z0-9\u4E00-\u9FA5]/g,''))"
									--%>
									<input type="text" size="55" class="search_text" name="key" id="searchText"  value="${key eq '*:*' ?'':key }">
								</td>
								<td align="left">
									<a href="" onclick="javascript:void(0)" class="search_but"><input type="submit" value="搜索一下" style="font-size:18px;" /></a>
								</td>
							</tr>
							<tr>
								<td colspan="8" style="color: red; "><span style="margin-left:100px;"> 
									<c:if test="${empty employee}">
										${sessionScope.noUser }
									</c:if>
								</span> </td>
							</tr>
						</table>						
					</div>
				</div>
				<div class="searchList" style="height: 600px;">
					<c:choose>
						<c:when test="${page.list[0]!=null}">
						<div style="height:580px;width:750px;margin-left: 10px; ">
							<s:iterator id="entry" value="page.list" status="sta">
							  <div class="list_top">	
								<div class="list_type">
									【       
										<c:if test="${entry.type eq yj}">定性依据</c:if>
										<c:if test="${entry.type eq fg}">审计法规</c:if>
										<c:if test="${entry.type eq al}">方法案例</c:if>
										<c:if test="${entry.type eq dh}">审计导航</c:if>
										<c:if test="${entry.type eq sx}">审计事项</c:if>
										<c:if test="${entry.type eq fa}">实施方案</c:if>
									】
								</div>
								<div class="list_title">
								<c:choose>
									<c:when test="${entry.type eq sx}">
										<a onclick="javascript:void(0);" target="_blank">${entry.title }</a>
									</c:when>
									<c:otherwise>
										<a href="search!showContent${entry.type}.action?id=${entry.id}" target="_blank">${entry.title }</a>	
									</c:otherwise>
								</c:choose>							
								</div>
								<div class="list_date">
									${entry.date }
								</div>								
							  </div>							  
							  <div class="list_content">
									${entry.attach }
							   </div>																		
							</s:iterator>
							</div>
							<div style="width:600px;text-align: left;margin-bottom: 0px;margin-top:20px;">
							  <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
					            <tr class="nonborder">
					               <td align="center" width="100%"><jsp:include page="/page.jsp"></jsp:include> </td>
					              <td width="1%" align="right">&nbsp;</td>
					            </tr>
					          </table>
					         </div>
						</c:when>
						<c:otherwise>
							<table>
								<tr><td align="center">
								<div style="margin-top:10px;font-size:15px;color:red;margin-left:40px;">
									对不起，没有您要查询的结果！
								</div>
								</td></tr>
							</table>
						</c:otherwise>
						</c:choose>				
				</div>
				</form>
			</div>			
		<div class="all_boot">
			<div class="boot">
				<jsp:include page="boot.jsp"></jsp:include>
			</div>
		</div>
	</body>
</html>
