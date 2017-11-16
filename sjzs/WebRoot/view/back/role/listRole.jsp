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
    <title>角色列表</title>
     <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<link href="css/style.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	 <jsp:include page="/jsPage.jsp"></jsp:include>
	<script type="text/javascript">
		function goNextPage( currentPage ) {
			document.getElementById("reqPage").value = currentPage;
			document.forms.namedItem("queryForm").submit();	
		}

		//回调函数当删除成功后执行此操作
		function delCallBack() {
			document.forms.namedItem("queryForm").submit();
		}
	</script>
  </head>
<body>
<form action="system/role!searchRole.action" method="post" name="queryForm" style="display: inline;">
	<input type="hidden" value="<s:property value="role.roleName"/>" name="role.roleName"/>
	<input type="hidden" value="<s:property value="role.roleId"/>" name="role.roleId"/>
	<input type="hidden" value="<s:property value="page.curPage"/>" name="page.curPage" id="reqPage"/>
</form>
<div class="pageHeader">
	<form rel="pagerForm" action="system/role!searchRole.action" method="post" id="searchF">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>角色名称：</label>
				<input type="text" name="role.roleName" id="textfield"/>
			</li>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
		</ul>
	</div>
	</form>
</div>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li>
				<c:if test="${fu:check('system/role!goAddPage.action')}">
					<a class="add" href="javascript:void(0)" onclick="self.parent.addTab('新增角色','system/role!goAddPage.action','icon-add')" ><span>添加角色</span></a>
				</c:if>
			</li>
			<li><%--<a class="icon" href="javascript:history.go(-1);"><span>返回</span></a>--%></li>
		</ul>
	</div>

	<div id="w_list_print">
	<table class="list" width="100%" targetType="navTab" asc="asc" desc="desc" layoutH="118">
		<thead>
			<tr>				
				<th>序号</th>
	  			<th>角色名称</th>
	  			<th colspan="3">相关操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator id="entry" value="page.list" status="sta">
				<tr target="sid_user" rel="${sta.count }">
			  		<td>${sta.count+(page.curPage-1)*page.maxResult}</td>
			  			<td class="txt03467B"><s:property value="#entry.roleName"/></td>
			  			<td class="green_txt">
			  				<c:if test="${fu:check('system/role!goAuthorize.action')}">
			  					<a href="javascript:void(0)" title="角色授权" 
			  					onclick="self.parent.addTab('角色授权','system/role!goAuthorize.action?role.roleId=${entry.roleId }&role.roleType=${entry.roleType}','icon-add')" class="txt12">
			  						角色授权
			  					</a>
			  				</c:if>
			  				<c:if test="${!fu:check('system/role!goAuthorize.action')}">
			  					<font color="#A1A1A1">角色授权</font>
			  				</c:if>
			  			</td>
			  			<td>
			  				<c:if test="${fu:check('system/role!goUpdatePage.action')}">
			  					<a href="javascript:void(0)" title="编辑角色" 
			  					onclick="self.parent.addTab('编辑角色','system/role!goUpdatePage.action?role.roleId=${entry.roleId }','icon-add')" class="txt12">
			  						编辑角色
			  					</a>
			  				</c:if>
			  				<c:if test="${!fu:check('system/role!goUpdatePage.action')}">
			  					<font color="#A1A1A1">修改</font>
			  				</c:if>
			  			</td>
			  			<td>
			  				<c:if test="${fu:check('system/role!deleteRole.action')}">
			  					<a href="system/role!deleteRole.action?role.roleId=${entry.roleId }" class="txt12"
			  					 onclick="if(!confirm('您确认要删除角色信息么？')){return false;}">	
			  					删除
			  					</a>
			  				</c:if>
			  				<c:if test="${!fu:check('system/role!deleteRole.action')}">
			  					<font color="#A1A1A1">删除</font>
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
