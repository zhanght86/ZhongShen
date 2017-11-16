<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/view/common/common.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>" />
		<title>审计导航审计方法列表</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<style type="text/css">
			#tooltip {
				position: absolute;
				z-index: 2;
				background: #efd;
				border: 1px solid #ccc;
				padding: 3px;
			}
		</style>
		<link rel="stylesheet" href="css/fore/mainList.css">
		<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
		<script type="text/javascript">
			function goNextPage(currentPage) {
				document.getElementById("reqPage").value = currentPage;
				document.forms.namedItem("searchF").submit();
			}
		
			function search() {
				$('#reqPage').val(0);
				var newName = $('#textfield').val();
				var oldName = "${sjdhMethod.name}";
				if (newName == oldName) {
					$('#textfield').val("");
				}
			}
			function reloadPage() {
				document.searchF.submit();
			}

			function wain(){
				alert("用户没有登陆，请登录！");
				return false;
			}
		</script>
	</head>
	<body>

		<div class="pageHeader">
			<form rel="pagerForm" action="showInfo!showSjdh.action" method="post"
				id="searchF" name="searchF">
				<input type="hidden" value="<s:property value="pageSjdh.curPage"/>" name="pageSjdh.curPage" id="reqPage" />
				<input type="hidden" name="sjdh.typeNo" value="${sjdh.typeNo }" />
			</form>
		</div>
		<div class="pageContent">

			<div id="w_list_print">
				<table class="list" width="100%" targetType="navTab" asc="asc"
					desc="desc" layoutH="118">
					<thead>
						<%--
						<tr>
							<th width="7%">
								序号
							</th>
							<th width="50%">
								审计方法名称
							</th>
							<th width="40%">
								审计方法简介
							</th>
						</tr>
						 --%>
					</thead>
					<tbody>
						<s:iterator id="entry" value="pageSjdh.list" status="sta">
							<tr target="sid_user" rel="${sta.count }">
								<%--
								<td>
									${sta.count+(pageSjdh.curPage-1)*pageSjdh.maxResult}
								</td>
								 --%>
								<td width="40%">
									<s:if test="#session.employee == null">
										<a href="" onclick="return wain();" target="_black">${entry.name}</a>
									</s:if>
									<s:else>
										<a href="sjdhxml!initFlow.action?id=${entry.id}" target="_black">${entry.name}</a>
									</s:else>
									<input type="hidden" value="${entry.name}" />
								</td>
								<td width="60%">
									<a style="text-decoration: none;">${entry.context}</a>
									<input type="hidden" value="${entry.context}" />
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>

			<div class="panelBar" <c:if test="${type eq 'init'}">style="display:none;"</c:if>>
					<div class="pages" style="text-align: center; width: 95%">
						<table style="width: 100%">
							<tr>
								<td style="text-align: right;">
									<a style="color: #1E5494;">总记录数：${pageSjdh.totalRecords} 条</a>&nbsp;&nbsp;
									<c:choose>
										<c:when test="${ 1 == pageSjdh.curPage}">
											<a>首页</a>
											<a>上一页</a>
										</c:when>
										<c:otherwise>
											<a href="" onClick="goNextPage(1);return false;">首页</a>
											<a href=""
												onClick="goNextPage(${pageSjdh.curPage - 1 });return false;">上一页</a>
										</c:otherwise>
									</c:choose>
									<c:forEach begin="${pageSjdh.beginIndex}" end="${pageSjdh.endIndex}"
										var="pageCount" step="1">
										<c:choose>
											<c:when test="${ pageCount == pageSjdh.curPage}">
												<a href="" onClick="goNextPage(${pageCount });return false;"
													style="color: red;">${pageCount }</a>
											</c:when>
											<c:otherwise>
												<a href="" onClick="goNextPage(${pageCount });return false;">${pageCount}</a>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									<c:choose>
										<c:when test="${ pageSjdh.curPage >= pageSjdh.totalPage}">
											<a>下一页</a>
											<a>末页</a>
										</c:when>
										<c:otherwise>
											<a href=""
												onClick="goNextPage(${pageSjdh.curPage + 1});return false;">下一页</a>
											<a href=""
												onClick="goNextPage(${pageSjdh.totalPage });return false;">末页</a>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</table>
					</div>
				</div>
		</div>
		</div>

		<div id="addMenuWin" iconCls="icon-save" title="查看"
			style="width: 0; height: 0"></div>

	</body>
	<%-- 
	<script type="text/javascript">
		$(function() {
			function dymShowText(column, nameLen, width) {
				$("tbody td:nth-child(" + column + ")").mousemove(
					function(event) {
						$("#tooltip").remove();
						var dicMemo = $(this).children("input").val();
						if (dicMemo != null && dicMemo != "") {
							$('__tag_135$10_' + dicMemo + '__tag_135$39_').css(
									"width", width).appendTo("body");
							var tPosX = (event.pageX - 5) + 'px';
							var tPosY = (event.pageY + 20) + 'px';
							$("#tooltip").css("top", tPosY).css("left", tPosX);
						}
					}).mouseout(function() {
				$("#tooltip").remove();
			});

			$("tbody td:nth-child(" + column + ")").each(function() {
				var str = $(this).children("a").text();
				if ($(this).text().length > nameLen) {
					var temp = str.substr(str, nameLen) + "......";
					$(this).children("a").html(temp);
				}
			});
		}
		;
		//dymShowText(2, 50, 300);
		//dymShowText(3, 25, 300);
		});
	</script>
	--%>
</html>
