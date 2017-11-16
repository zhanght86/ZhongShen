<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hnzskj.common.Constant"%>
<%@ include file="/view/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.0 Transitional//EN">


<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>个人主页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!-- link rel="stylesheet" type="text/css" href="css/fore/homepage.css" -->
	<link rel="stylesheet" type="text/css" href="css/fore/profile.css">
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript">
		function updateInfo(){
			$("#updateInfoForm").submit();
		}
		function updatePass(){
			$("#updatePassForm").submit();
		}
		function goIndex(){
			$("#indexForm").submit();
		}
		function showMore(){
			$("#more").slideToggle();
		}
		function hidMess(){
			$("#baseTitle").attr("style","font-width:bold;");
			$("#more").attr("style","display:none;");
			$("#base").attr("style","display:black");
			$("#menu_base").attr("style","color:black;");
			$("#mainCon").slideDown(1000);
			$("#updateMess").fadeOut(2500);
		}
		function swit(types){
			if(types=="1"){
				$("#more").slideUp(1500);
				$("#base").slideDown(1500);
				$("#menu_base").attr("style","color:black;");
				$("#menu_more").attr("style","color:#666666;");
			}else if(types == "0"){
				$("#more").slideDown(1500);
				$("#base").slideUp(1500);
				$("#menu_base").attr("style","color:#666666;");
				$("#menu_more").attr("style","color:black");
			}
		}
	</script>
  </head>
  
  <body onload="hidMess()"> 
    <div class="homepage" >
    <form action="reglogin!updateInfo.action" id="updateInfoForm" style="display: none;"></form>
    <form action="reglogin!updatePass.action" id="updatePassForm" style="display:none;"></form>    
    <form action="reglogin!index.action" id="indexForm" style="display: none;"></form>
    	<div class="top_top">
    		<div class="title" >
    			<img alt="审计之家" src="/sjzs/images/fore/logobit.png" style="display: inline;float: left;border: 0;height:40px;">
    			<a href="<%=basePath%>" style="margin-right:35px;">审计之家首页</a>
    		</div>
    	</div>
    	<div class="main" id="mainCon" style="display:none;">
    		<div class="title">
    			<h1>查看资料</h1>
    			<div class="update" >
	    			<a href="javascript:void(0)" onclick="updateInfo()">编辑个人资料</a>
    			</div>
    		</div>
    		<div class="left">
    			<div class="title">
    				<h3>个人资料</h3>
    			</div>
    			<div class="menu_">
    				<div class="base">
	    				<h3 onclick="swit('1')" id="menu_base">基本资料</h3>
	    			</div>
	    			<hr />
	    			<div class="more">
	    				<h3 onclick="swit('0')" id="menu_more">详细资料</h3>
	    			</div>
	    			<hr />
    			</div>
    		</div>
    		<div class="right">
    			<div class="info" id="base">
    				<h2 id="baseTitle">基本资料</h2>
    				<hr />
    				<div class="content">
    					<table>
	    					<tr>
				    			<td class="left">用户名：</td>
				    			<td class="right">&nbsp;${sessionScope.employee.emplAccount }</td>
				    		</tr>
				    		<tr>
				    			<td class="left">性别：</td>
				    			<td class="right">&nbsp;
				    				<c:if test="${!empty sessionScope.employee.emplSex }">
					    				<c:choose>
					    					<c:when test="${sessionScope.employee.emplSex == -1}">
					    						保密
					    					</c:when>
					    					<c:when test="${sessionScope.employee.emplSex == 1}">
					    						男
					    					</c:when>
					    					<c:when test="${sessionScope.employee.emplSex == 0}">
					    						女
					    					</c:when>
					    				</c:choose>				    					
				    				</c:if>			    				
				    			</td>
				    		</tr>
				    		<tr>
				    			<td class="left">年龄：</td>
				    			<td class="right">&nbsp;${sessionScope.employee.emplAge }</td>
				    		</tr>
				    		<tr>
				    			<td class="left">注册日期：</td>
				    			<td class="right">&nbsp;${sessionScope.employee.timeOrder }</td>
				    		</tr> 
	    				</table>
    				</div>
    			</div>
    			<div class="info" id="more">
    				<h2 id="moreTitle">详细资料</h2>	
    				<hr />
    				<div class="content">
    					<table>
		    				<tr>
				    			<td class="left">身份证号：</td>
				    			<td class="right">&nbsp;${sessionScope.employee.emplIdCard }</td>
				    		</tr>
				    		<tr>
				    			<td class="left">住址：</td>
				    			<td class="right">&nbsp;${sessionScope.employee.emplAddress }</td>
				    		</tr>
				    		<tr>
				    			<td class="left">手机号：</td>
				    			<td class="right">&nbsp;${sessionScope.employee.emplMobile }</td>
				    		</tr>
				    		<tr>
				    			<td class="left">办公电话：</td>
				    			<td class="right">&nbsp;${sessionScope.employee.emplOfficeTel }</td>
				    		</tr>
				    		<tr>
				    			<td class="left">家庭电话：</td>
				    			<td class="right">&nbsp;${sessionScope.employee.emplHomeTel }</td>
				    		</tr>
				    		<tr>
				    			<td class="left">E-mail：</td>
				    			<td class="right">&nbsp;${sessionScope.employee.emplEmail }</td>
				    		</tr>
				    		<tr>
				    			<td class="left">QQ：</td>
				    			<td class="right">&nbsp;${sessionScope.employee.emplqq }</td>
				    		</tr>
				    	</table>
    				</div>
    			</div>
    			<div id="updateMess" style="width: 100%;height:30px;margin-top:10px;margin-left:220px;color: red;">
	    			${requestScope.updateMess }
	    		</div> 
    		</div>    		   		
    	</div>    		
    </div>
    <div class="all_boot">
		<div class="boot">
			<jsp:include page="boot.jsp"></jsp:include>
		</div>
	</div>
  </body>
</html>
