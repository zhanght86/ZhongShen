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
    <title>功能列表</title>

    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
    <jsp:include page="/jsPage.jsp"></jsp:include>
    	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
    	
	<script src="js/dataCheck.js" type="text/javascript"></script>
	<script language="javascript" src="js/public.js" charset="utf-8"></script>
	<script type="text/javascript" src="js/zh/power.js"></script>
	
	<script type="text/javascript">
	function goNextPage( currentPage ) {
		document.getElementById("reqPage").value = currentPage;document.forms.namedItem("queryForm").submit();
	}

	function getCheckBox(checkboxname, counts) {
		var selectCount = 0;
		var selectValue = "";
		//选取input中type为checkbox,name为checkboxname的表单
		var myselector = "input[type='checkbox'][name='"+checkboxname+"']";
		var checkboxs = $(myselector).each(function(){
				if($(this).attr("checked")==true){
					selectCount = selectCount + 1;
					selectValue = $(this).attr("value");
				}					
			});
		if (1 == counts) {
			if (selectCount == counts) {
				return selectValue;
			}
			return "";
		} else if (-1 == counts) {
			if (selectCount >= 1) {
				return true;
			}
			return false;
		}
		return false;						
	} 

	function deletePower(jmcode){
		var selectResult = getCheckBox(jmcode,-1);
		if (selectResult) {
			if (confirm("您确定要删除所选的功能？")) {
				$("form[name='deleteForm']").submit();
				}
			} else {
				alert("至少有一个复选框必须被选中！");
				}
		}

	
	function updatePower(jmcode) {
		var selectVal = getCheckBox(jmcode,1);
		if ("" != selectVal) {
			var goURI = "system/power!goUpdatePage.action?power.powerId=" + selectVal;
			self.parent.addTab('编辑功能',goURI,'icon-add');
		} 
		else {alert("有且只有一个复选框可以被选中");}}
	</script>
  </head>
  <body>
  <div id="navTab" class="tabsPage">
	<form action="system/power!searchPower.action" method="post" name="queryForm" style="display: inline;">
		<input type="hidden" value="<s:property value="power.powerId"/>" name="power.powerId"/>
		<input type="hidden" value="<s:property value="power.powerName"/>" name="power.powerName"/>
		<input type="hidden" value="<s:property value="page.curPage"/>" name="page.curPage" id="reqPage"/>
	</form>
	
<div class="pageHeader">
	<form rel="pagerForm" action="system/power!searchPower.action" method="post" id="searchF">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>功能名称：</label>
				 <input type="text" name="power.powerName" id="textfield"/>
			</li>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
		</ul>
		<%--<div class="subBar">
			<ul>
				<!-- <li><a class="button" href="demo_page6.html" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li> -->
			</ul>
		</div>
	--%></div>
	</form>
</div>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li>
				<c:if test="${fu:check('system/power!goAddPage.action')}">
					<a class="add" href="javascript:void(0)" onclick="self.parent.addTab('新增功能','system/power!goAddPage.action?power.powerParent=${power.powerId }','icon-add')" ><span>添加</span></a>
				</c:if>
			</li>
			<li>
				<c:if test="${fu:check('system/power!deletePower.action')}">
					<a class="delete" href="javascript:deletePower('powerCodes')" title="确定要删除吗？" warn="请选择"><span>删除</span></a>
				</c:if>
			</li>
			<li>
				<c:if test="${fu:check('system/power!goUpdatePage.action')}">
					<a class="edit" href="javascript:void(0)" onclick="updatePower('powerCodes')" warn="请选择"><span>修改</span></a>
				</c:if>
			</li>
			<li>
			<!-- 	<a class="back" href="javascript:void(0)" onclick="window.history.back();return false;" warn="返回" >   -->
					<a class="back"  href="system/power!searchPower.action?power.PowerId=${parentCode }" class="txt12" warn="返回">
                      <img src="images/xt_12.gif" alt="" />返回
				</a>
			</li>			
		</ul>
	</div>

	<div id="w_list_print">
	<form action="system/power!deletePower.action" name="deleteForm" method="post">
	  <input type="hidden" name="parentCode" value="${power.powerId }"/>
	  
	<table class="list" width="100%" targetType="navTab" asc="asc" desc="desc" layoutH="118">
		<thead>
			<tr>				
				<th>选择</th>
	  			<th>序号</th>
	  			<th>功能名称</th>
	  			<th>功能url</th>
	  			<th>功能类型</th>  			
	  			<th>功能级别</th>  			
	  			<th>功能排序</th>  	
			</tr>
		</thead>
		<tbody>
			<s:iterator id="entry" value="page.list" status="sta">
				<tr target="sid_user" rel="${sta.count }">
		  			<td align="center"><input type="checkbox" name="powerCodes" value="${entry.powerId }"/></td>
			  			<td>${sta.count+(page.curPage-1)*page.maxResult}</td>
			  			<td class="txt03467B">
			  				<a href="system/power!searchPower.action?power.powerId=${entry.powerId }" title="查看子模块">
				  				<s:property value="#entry.powerName"/>
			  				</a>
			  			</td>
			  			<td>
			  				${fn:substring(entry.powerUrl,0,100) }
			  				<span style="display: none;"><s:property value="#entry.powerUrl"/></span>
			  			</td>
	  					<td class="green_txt">
	  						<c:if test="${true == entry.powerModule}">
	  							功能模块
	  						</c:if>
	  						<c:if test="${false == entry.powerModule}">
	  							操作功能
	  						</c:if>
	  					</td>				
	  					<td class="green_txt">
	  						<c:if test="${true == entry.powerSuperPower}">
	  							超级功能
	  						</c:if>
	  						<c:if test="${false == entry.powerSuperPower}">
	  							普通功能
	  						</c:if>
	  					</td>	
	  					<td class="green_txt">
	  						${entry.powerOrder}
	  					</td>	
		  		</tr>
		  	</s:iterator>
		</tbody>
	</table>
	</form>
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
</div>
</body>
</html>