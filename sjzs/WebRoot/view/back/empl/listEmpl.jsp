<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/view/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>"/>
    <title>员工列表</title>	
    	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="/jsPage.jsp"></jsp:include>
	<script type="text/javascript">
	function goNextPage( currentPage ) {
		document.getElementById("reqPage").value = currentPage;document.forms.namedItem("queryForm").submit();
	}
	</script>
	  
  </head>
<body>
<form action="system/empl!searchEmpl.action" method="post" name="queryForm" style="display: inline;">
	<input type="hidden" value="${empl.orgId}" name="empl.orgId"/>
	<input type="hidden" value="${empl.emplName}" name="empl.emplName"/>
	<input type="hidden" value="${empl.emplPosition}" name="empl.emplPosition"/>
	<input type="hidden" value="<s:property value="page.curPage"/>" name="page.curPage" id="reqPage"/>
</form>

<div class="pageHeader">
 	<form rel="pagerForm" action="system/empl!searchEmpl.action" method="post" id="searchF">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>员工姓名：</label>
				<input type="text" name="empl.emplName" id="textfield"/>
			</li>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
		</ul>
	</div>
	</form>
</div>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="javascript:void(0)" onclick="self.parent.addTab('新增用户','system/empl!goAddPage.action?empl.orgId=${empl.orgId}','icon-add')" ><span>添加</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>

	<div id="w_list_print">
	<table class="list" width="98%" targetType="navTab" asc="asc" desc="desc" layoutH="116">
		<thead>
			<tr>				
				<th>序号</th>
	  			<th>用户姓名</th>
	  			<th>账号</th>
	  			<%--<th>所属部门</th>--%>
	  			<th>职位</th>
	  			<th>手机号</th>
	  			<th>办公电话</th>
	  			<th>Email</th>
	  			<th>QQ</th>
	  			<th>状态</th>
	  			<th colspan="5">相关操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator id="entry" value="page.list" status="sta">
				<tr target="sid_user" rel="${sta.count }">
		  			<td>${sta.count+(page.curPage-1)*page.maxResult}</td>
		  			<td class="txt03467B"><s:property value="#entry.emplName"/></td>
		  			<td class="txt03467B">${entry.emplAccount }</td>
		  			<%--<td class="txt03467B">${entry.orgName }</td>--%>
  					<td><s:property value="#entry.emplPostion"/></td>
  					<td><s:property value="#entry.emplMobile"/><input type="hidden" value="${entry.emplMobile }" id="cell${sta.count }"/></td>			
  					<td><s:property value="#entry.emplOfficeTel"/></td>
  					<td><s:property value="#entry.emplEmail"/></td>
  					<td class="green_txt"><s:property value="#entry.emplqq"/></td>
  					<td>&nbsp;
  						<c:if test="${'0' == entry.emplStatus}">未启用</c:if>
  						<c:if test="${'1' == entry.emplStatus}">启用</c:if>
  						<c:if test="${'2' == entry.emplStatus}"><font color="#A1A1A1">注销</font></c:if>
  						<c:if test="${'3' == entry.emplStatus}"><font color="#A1A1A1">停用</font></c:if>
  					</td>
		  			<td>
		  				<c:if test="${fu:check('system/empl!goAddRole.action')}">
		  					<a href="javascript:void(0)" title="分配角色" 
		  					onclick="self.parent.addTab('分配角色','system/empl!goAddRole.action?empl.emplId=${entry.emplId }&empl.orgId=${empl.orgId}','icon-add')" class="txt12">
		  						分配角色
		  					</a>
		  				</c:if>
		  				<c:if test="${!fu:check('system/empl!goAddRole.action')}">
		  					<font color="#A1A1A1">分配角色</font>
		  				</c:if>
		  			</td>
		  			<td>
		  				<c:if test="${fu:check('system/empl!resetPassword.action')}">
		  					<a href="system/empl!resetPassword.action?empl.emplId=${entry.emplId }"
		  						 class="txt12" onclick="if (!confirm('您确定要重置此员工的密码吗？')) {return false;}">
		  						 重置密码
		  					</a>
		  				</c:if>
		  				<c:if test="${!fu:check('system/empl!resetPassword.action')}">
		  					<font color="#A1A1A1">重置密码</font>
		  				</c:if>
		  			</td>
		  			<td>
		  				<c:if test="${fu:check('system/empl!goUpdatePage.action')}">
		  					<a href="javascript:void(0)" title="编辑用户" 
		  					onclick="self.parent.addTab('编辑用户','system/empl!goUpdatePage.action?empl.emplId=${entry.emplId }','icon-add')" class="txt12">
		  						修改
		  					</a>
		  				</c:if>
		  				<c:if test="${!fu:check('system/empl!goUpdatePage.action')}">
		  					<font color="#A1A1A1">修改</font>
		  				</c:if>
		  			</td>
		  			<td>
		  				<c:if test="${'2' == entry.emplStatus}">
			  				<c:if test="${fu:check('system/empl!delEmpl.action')}">
  								<a href="system/empl!delEmpl.action?empl.emplId=${entry.emplId }&empl.orgId=${entry.orgId}" 
  								class="txt12" onclick="if (!confirm('您确认要删除当前员工信息么？')) {return false}">删除/</a>
		  					</c:if>
		  					<c:if test="${!fu:check('system/empl!delEmpl.action')}">
		  						<font color="#A1A1A1">删除</font>
			  				</c:if>
  						</c:if> 
  						<c:if test="${'1' == entry.emplStatus}">
			  				<c:if test="${fu:check('system/empl!leaveOff.action')}">
	  							<a href="system/empl!leaveOff.action?empl.emplId=${entry.emplId }&empl.orgId=${entry.orgId}" class="txt12">注销</a>/
	  							<a href="system/empl!suspend.action?empl.emplId=${entry.emplId }&empl.orgId=${entry.orgId}" class="txt12">停用</a>
			  				</c:if>
			  				<c:if test="${!fu:check('system/empl!leaveOff.action')}">
	  							<font color="#A1A1A1">注销/停用</font>
			  				</c:if>
  						</c:if>
  						<c:if test="${'2' == entry.emplStatus || '3' == entry.emplStatus}">
	  						<c:if test="${fu:check('system/empl!enabledAccount.action')}">
  								<a href="system/empl!enabledAccount.action?empl.emplId=${entry.emplId }&empl.orgId=${entry.orgId}" class="txt12">启用</a>
			  				</c:if>
			  				<c:if test="${!fu:check('system/empl!suspend.action')}">
		  						<font color="#A1A1A1">启用</font>
			  				</c:if>
  						</c:if>  											
		  			</td>
		  		</tr>
		  	</s:iterator>
		</tbody>
	</table>
	</div>
	
	<div class="panelBar" ><%--
		<div class="pages">
			<span>显示</span>
			<select name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
				<option value="200">200</option>
			</select>
			<span>条，共${page.totalRecords}条</span>
		</div>
		
		--%><div class="pagination">
		
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
		</div>
	</div>
</div>
</body>
</html>