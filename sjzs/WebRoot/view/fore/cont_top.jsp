<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.hnzskj.common.Constant" %>
<%@ include file="/view/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script src="js/fore/downClient.js" type="text/javascript"></script>
<form  id="searchForm" name="searchForm" method="post">	
 <div id="top">
	<div class="pageContent">
		<s:if test="#session.employee!=null">
			<input type="hidden" id="emplId" value="${sessionScope.employee.emplId}"/>
			<a href="reglogin!showHomePage.action"  target="_blank">${sessionScope.employee.emplName}</a>
			<a href="reglogin!exit.action">退出</a>
		</s:if>
		<s:else>
			<a href="javascript:void(0)" onclick="regEmpl()">登录</a>
			<a href="reglogin!reg.action">注册</a>
		</s:else>															
	</div>
</div>	
 	<div id="main">
	<div class="title">
		<a href="<%=basePath%>"  hideFocus="true">
		<img border="0" alt="审计之家" src="images/fore/logobitT.png">  
		</a>
	</div>
	<div class="serc">						
		<input type="hidden" value="<s:property value="page.curPage"/>"
		name="page.curPage" id="reqPage" />
		<input type="hidden" id="searchType" name="searchType" value="<%=Constant.ALL %>">
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
					<input type="text" size="55" class="search_text" name="key" value="${sessionScope.key }">
				
				</td>
				<td align="left">
					<a href="" onclick="javascript:void(0)" class="search_but"><input type="submit" value="搜索一下" style="font-size:18px;" /></a>
				</td>
			</tr>
			<tr>
				<td colspan="8" align="center" style="color: red;">${requestScope.noUser } </td>
			</tr>
		</table>						
	</div>
</div>
</form>

