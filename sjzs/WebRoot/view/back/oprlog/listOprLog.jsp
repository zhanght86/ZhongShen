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
    <title>日志列表</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
    <link href="js/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
	<link href="js/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
	<link href="js/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
	<link href="js/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
	<script language="javascript" src="js/public.js" charset="utf-8"></script>	
	
	
	<script type="text/javascript" src="js/validate/jquery.js"></script>
<script type="text/javascript">
jQuery(document).ready(function(){
  jQuery(".list tr:odd").addClass("color1");
  jQuery(".list tr:even").addClass("color2");
  jQuery(".list tr").hover(function(){
    jQuery(this).addClass("color3")
  },function(){
    jQuery(this).removeClass("color3")
  });
jQuery(".list tr:has(:checked)").addClass("ed");
//判断行是否被选中(返回布尔类型)添加样式类   // has(:checked)")   返回一个bool值  true/false

jQuery('.list tr').click(function(){
	 jQuery('.list tr').removeClass("ed");
	 jQuery(this).addClass("ed");
}
);
});


</script>

<style type="text/css">
.list {border:1px solid #333;text-align:center;}
.list th {background-color:#F0EFF0; color:#000000;}
.color1 {background-color:#F8F8F8; color:#333;}
.color2 {background-color:#ededed; color:#333;}
.color3 {background-color:#e4f5ff; color:#000000;}
.ed {background:#7cc5e5;color:#000000;}
</style>
	<script type="text/javascript">
		//分页查询
		function goNextPage( currentPage ) {
			document.getElementById("reqPage").value = currentPage;
			document.forms.namedItem("queryForm").submit();	
		}

		//回调函数当删除成功后执行此操作
		function delCallBack() {
			document.forms.namedItem("queryForm").submit();
		}
		
		//切换HTML标签的显示隐藏状态
		function changeDisplay(objId) {
			$("#" + objId).toggle();
		}
	</script>
  </head>
<body>
<form action="system/oprLog!searchLog.action" method="post" style="display: none;">
	<label>操作人:</label><input type="text" name="operationLog.operator" value="${operationLog.operator }"/>
	<label>操作时间:</label><input type="text" name="operationLog.oprtime" value="<s:date name="operationLog.oprtime" format="yyyy-MM-dd"/>"/>
	<label>操作内容:</label><input type="text" name="operationLog.operation" value="${operationLog.operation}"/>
	<input type="submit" value=" 搜 索 " class="button1" onclick="changeDisplay('subtab');"/>
</form>	
						
<form action="system/oprLog!searchLog.action" method="post" name="queryForm" style="display: none;">
	<input type="hidden" value="<s:property value="page.curPage"/>" name="page.curPage" id="reqPage"/>
	<input type="hidden" name="operationLog.operator" value="${operationLog.operator }"/>
	<input type="hidden" name="operationLog.oprtime" value="<s:date name="operationLog.oprtime" format="yyyy-MM-dd"/>"/>
	<input type="hidden" name="operationLog.operation" value="${operationLog.operation }"/>
</form>


<div class="pageHeader">
	<form rel="pagerForm" action="system/oprLog!searchLog.action" method="post" id="searchF">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>操作人：</label>
            <input type="text" name="operationLog.operator" id="textfield"/>
			</li>
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
				<!-- <li><a class="button" href="demo_page6.html" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li> -->
			</ul>
		</div>
	</div>
	</form>
</div>

<div class="pageContent">
	<div id="w_list_print">
	<table class="list" width="100%" targetType="navTab" asc="asc" desc="desc" layoutH="118">
		<thead>
			<tr>				
				<th>序号</th>
	  			<th>操作人</th>
	  			<th>登陆IP</th>
	  			<th>操作时间</th>
	  			<th>操作内容</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator id="entry" value="page.list" status="sta">
				<tr target="sid_user" rel="${sta.count }">
			  		<td>${sta.count+(page.curPage-1)*page.maxResult}</td>
		  			<td class="txt03467B">
		  				${fn:substring(entry.operator,0,fn:indexOf(entry.operator,"__")) }
		  			</td>
		  			<td><s:property value="#entry.loginIp"/></td>
		  			<td class="green_txt"><s:date name="#entry.oprtime" format="yyyy-MM-dd HH:mm:ss"/></td>
		  			<td>
		  				<a title="查看日志详细信息" href="javascript:self.parent.addTab('日志详细信息','system/oprLog!show.action?operationLog.oid=${entry.oid }')" class="txt12">
		  					${fn:substring(entry.operation,0,fn:indexOf(entry.operation,"<br/>")) }
		  				</a>
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