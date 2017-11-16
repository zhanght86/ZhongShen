<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!--分页模块 -->
<div class="fanye" style="width: 97%;">
	<a style="color: #1E5494;">总记录数：${page.totalRecords} 条</a>&nbsp;&nbsp;
	<c:choose>
		<c:when test="${ 1 == page.curPage}">
			<a>首页</a>
			<a>上一页</a>
		</c:when>
		<c:otherwise>
			<a href="" onClick="goNextPage(1);return false;">首页</a>
			<a href="" onClick="goNextPage(${page.curPage - 1 });return false;">上一页</a>
		</c:otherwise>
	</c:choose>
	<c:forEach begin="${page.beginIndex}" end="${page.endIndex}"
		var="pageCount" step="1">
		<c:choose>
			<c:when test="${ pageCount == page.curPage}">
				<a href="" onClick="goNextPage(${pageCount });return false;"
					style="color: red;">${pageCount }</a>
			</c:when>
			<c:otherwise>
				<a href="" onClick="goNextPage(${pageCount });return false;">${pageCount}</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:choose>
		<c:when test="${ page.curPage >= page.totalPage}">
			<a>下一页</a>
			<a>末页</a>
		</c:when>
		<c:otherwise>
			<a href="" onClick="goNextPage(${page.curPage + 1});return false;">下一页</a>
			<a href="" onClick="goNextPage(${page.totalPage });return false;">末页</a>
		</c:otherwise>
	</c:choose>
</div>