<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.hnzskj.common.Constant" %>
<%@ include file="/view/common/common.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("com", Constant.COMPANY);
request.setAttribute("department", Constant.DEPARTMENT);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>"/>
    <title>部门列表</title>
	<link href="css/style.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script language="javascript" src='js/showdialog.js'></script>
	<script language="javascript" src='js/zh/org.js'></script>
	<script type="text/javascript">

	</script>
  </head>
  <body>
  <input type="hidden" value="" id="ssjgcode"/>
	<form action="system/org!searchOrg.action" method="post" name="queryForm" id="queryForm" style="display: inline;">
		<input type="hidden" value="<s:property value="org.orgId"/>" name="org.orgId"/>
		<input type="hidden" value="<s:property value="org.orgName"/>" name="org.orgName"/>
		<input type="hidden" value="<s:property value="org.orgParent"/>" name="org.orgParent"/>
		<input type="hidden" value="<s:property value="page.curPage"/>" name="page.curPage" id="reqPage"/>
	</form>
	<div align="center" style="margin-top:10px;">
	  <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
	    <tr>
	      <td width="118" align="left"><img src="images/ny_t1.jpg" width="118" height="37" /></td>
	      <td align="left" background="images/ny_t2.jpg" class="txt_bold">
	      <form action="system/org!searchOrg.action" method="post" id="searchF">
	      <input type="hidden" value="<s:property value="org.orgId"/>" name="org.orgId"/>
	      <table width="100%" border="0" cellspacing="0" cellpadding="0">
	        <tr>
	          <td width="1%">&nbsp;</td>
	          <td height="30" valign="top"> 机构名称：
	            <input type="text" name="org.orgName" id="textfield"/>
	            <input type="image" src="images/ny_searchbtn.jpg"/>
	          </td>
	          <td valign="middle" align="right">
	          	<c:if test="${fu:check('system/org!goAddPage.action')}">
					<!-- 
					<a href="javascript:void(0)" title="新增部门" onclick="self.parent.addTab('新增部门','system/org!goAddPage.action?org.jmssjg=${org.orgId}','icon-add')" class="txt12">新增</a>
					 -->
					<img src="images/ny_tj.jpg" width="19" height="19" align="absmiddle" />
					<a href="javascript:void(0)" title="新增部门" onclick="self.parent.addOrg('${org.orgId }',<%=Constant.DEPARTMENT%>);" class="txt12">新增部门</a>
				</c:if>
	          	&nbsp; &nbsp;
          		<img src="images/ny_tj.jpg" width="19" height="19" align="absmiddle" />
		  		<a href="javascript:self.parent.viewOrg();" class="txt12" >组织机构图</a>
			  	&nbsp;&nbsp;
			  	<c:if test="${fu:check('system/org!goUpdatePage.action')}">
		          	<img src="images/edit.png" width="19" height="19" align="absmiddle" />
		          	<a href="javascript:void(0)" title="编辑" onclick="updateOrg('orgCodes')" class="txt12">编辑</a>
		        </c:if>
	          	&nbsp; &nbsp;
	          	<c:if test="${fu:check('system/org!deleteOrg.action')}">
		          	<img src="images/ny_sc.jpg" width="19" height="19" align="absmiddle" />
		          	<a href="javascript:deleteOrg('orgCodes')" class="txt12">删除</a>
	          	</c:if>
	          	<!-- 
	          	&nbsp; &nbsp;
	          	<img src="images/xt_12.gif"/>
	          	<a href="system/org!searchOrg.action?org.orgId=${parentCode }" class="txt12">上一级</a>
	          	 -->
	          	</td>
	        </tr>
	      </table>
	      </form>
	      </td>
	      <td width="6" align="right"><img src="images/ny_t3.jpg" width="6" height="37" /></td>
	    </tr>
	  </table>
	  <table width="99%" border="0" cellspacing="0" cellpadding="0">
	    <tr>
	      <td align="left" class="non_topborder">
	      <form action="system/org!deleteOrg.action" method="post" name="deleteForm">
	      <input type="hidden" name="parentCode" value="${parentCode }"/>
	      <input type="hidden" name="org.orgId" value="${org.orgId }"/>
	      <input type="hidden" name="delType" value="0"/>
	      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC">
	        <tr class="title_style">
	  			<td></td>
	  			<td>序号</td>
	  			<td>部门名称</td>
	  			<td>所属部门</td>
	        </tr>
	        <c:if test="${!empty page.list}">
			  	<s:iterator id="entry" value="page.list" status="sta">
			  	<s:if test="#sta.even">
					<tr class="row_bg">
				</s:if>
				<s:else>
					<tr>
				</s:else>
			  			<td align="center"><input type="checkbox" value="${entry.orgId }" name="orgCodes"/></td>
			  			<td align="center">${sta.count+(page.curPage-1)*page.maxResult}</td>
			  			<td class="txt03467B">
			  			<!--
			  				<a href="system/org!searchOrg.action?org.orgId=${entry.orgId }" title="查看子部门">
			  				</a>
			  			-->
				  				<s:property value="#entry.orgName"/>
			  			</td>
	  					<td>
	  						${fu:getOrgNameByCode(entry.orgParent) }
	  					</td>
			  		</tr>
			  	</s:iterator>
				  <tr class="row_bg">
		          <td height="29" colspan="9" background="images/ny_titlebg1.jpg">
		          <table width="100%" border="0" cellspacing="0" cellpadding="0" >
		            <tr class="nonborder">
		              <td align="right">
		              		<a style="color: #1E5494;">总记录数：${page.totalRecords} 条</a>&nbsp;&nbsp;
							<c:choose>
								<c:when test="${ 1 == page.curPage}">
									<a>首页</a>
									<a>上一页</a>
								</c:when>
								<c:otherwise>
									<a href="" onclick="goNextPage(1);return false;">首页</a>
									<a href="" onclick="goNextPage(${page.curPage - 1 });return false;">上一页</a>
								</c:otherwise>
							</c:choose>
							<c:forEach begin="${page.beginIndex}" end="${page.endIndex}" var="pageCount" step="1">
								<c:choose>
									<c:when test="${ pageCount == page.curPage}">
										<a href="" onclick="goNextPage(${pageCount });return false;" style="color:red;">${pageCount }</a>
									</c:when>
									<c:otherwise>
										<a href="" onclick="goNextPage(${pageCount });return false;">${pageCount }</a>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:choose>
								<c:when test="${ page.curPage == page.totalPage}">
									<a>下一页</a>
									<a>末页</a>
								</c:when>
								<c:otherwise>
									<a href="" onclick="goNextPage(${page.curPage + 1});return false;">下一页</a>
									<a href="" onclick="goNextPage(${page.totalPage });return false;">末页</a>
								</c:otherwise>
							</c:choose>
		              </td>
		              <td width="1%" align="right">&nbsp;</td>
		            </tr>
		          </table>
		          </td>
		          </tr>
		  	</c:if>
	      </table>
	      </form>
	      </td>
	    </tr>
	  </table>
	</div>
</body>
</html>