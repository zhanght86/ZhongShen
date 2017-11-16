<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/view/common/common.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>" />
		<title>审计法规列表页面</title>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
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
			function goNextPage( currentPage ) {
				document.getElementById("reqPage").value = currentPage;
				document.forms.namedItem("searchF").submit();
			}

			function reloadPage(){
				document.searchF.submit();
			}

			function wain(){
				alert("用户没有登陆，请登录！");
				return false;
			}
			
		<%--
		$(function(){
			function dymShowText(column,nameLen,width){
				$("tbody[id=playList] td:nth-child("+column+")").mousemove(function(event){
					$("#tooltip").remove();
					var dicMemo = $(this).children("input").val();
					if(dicMemo != null && dicMemo !=""){
						$('<div id="tooltip">'+dicMemo+'</div>').css("width",width).appendTo("body");
							var tPosX = (event.pageX - 5) + 'px';
							var tPosY = (event.pageY + 20) + 'px';
							$("#tooltip").css("top",tPosY).css("left",tPosX);
					  }
				})
				.mouseout(function(){
					$("#tooltip").remove();
				});
				
				$("tbody[id=playList] td:nth-child("+column+")").each(function(){
					var str = $(this).children("a").text();
					if($(this).text().length>nameLen){
						var temp = str.substr(str,nameLen) + "......";
						$(this).children("a").html(temp);
					}
				});
			};

			dymShowText(2,20,200);
			dymShowText(3,10,180);
			dymShowText(4,12,180);
		});

		function search(){
			$('#reqPage').val(0);
			var newName = $('#textfield').val();
			var oldName = "${law.lawName}";
			if(newName == oldName){
				$('#textfield').val("");
			}
		}	
		 --%>
		</script>
	</head>
	<body>
		<div id="navTab" class="tabsPage">
			<div class="pageHeader">
				<form rel="pagerForm" action="showInfo!showSjfg.action" method="post" id="searchF" name="searchF">
					<input type="hidden" value="<s:property value="pageSjfg.curPage"/>" name="pageSjfg.curPage" id="reqPage" />
					<input type="hidden" name="sjfg.lawSort" value="${sjfg.lawSort}" />
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
								<th width="30%">
									法规名称
								</th>
								
								<th width="18%">
									发文单位
								</th>
								<th width="18%">
									发文编号
								</th>
								 
								<th width="12%">
									发文日期
								</th>
							</tr>
							--%>
						</thead>
						<tbody id='playList'>
						<ui>
							<s:iterator id="entry" value="pageSjfg.list" status="sta">
								<tr rel="${sta.count }">
										<%--
										<td>
											${sta.count+(page.curPage-1)*page.maxResult}
										</td>
										 --%>
										<td width="85%">
											<s:if test="#session.employee == null">
											<li title="${entry.lawName}">	<a href="" onclick="return wain();" target="_black">${entry.lawName}</a></li>
											</s:if>
											<s:else>
												<li title="${entry.lawName}"><a href="search!showContentFG.action?id=${entry.lawId }" target="_black">${entry.lawName}</a></li>
											</s:else>
											<input type="hidden" value="${entry.lawName}" />
										</td>
										<%--
										<td>
											<a style="text-decoration: none;">${entry.lawOrg}</a>
											<input type="hidden" value="${entry.lawOrg}" />
										</td>
										<td>
											<a style="text-decoration: none;">${entry.lawNumber}</a>
											<input type="hidden" value="${entry.lawNumber}" />
										</td>
										 --%>
										<td width="15%">
											${entry.lawDate}
										</td>
								</tr>
							</s:iterator>
							</ui>
						</tbody>
					</table>
				</div>

				<div class="panelBar"  <c:if test="${type eq 'init'}">style="display:none;"</c:if>>
					<div class="pages" style="text-align: center; width: 95%">
						<table style="width: 100%">
							<tr>
								<td style="text-align: right;">
									<a style="color: #1E5494;">总记录数：${pageSjfg.totalRecords} 条</a>&nbsp;&nbsp;
									<c:choose>
										<c:when test="${ 1 == pageSjfg.curPage}">
											<a>首页</a>
											<a>上一页</a>
										</c:when>
										<c:otherwise>
											<a href="" onClick="goNextPage(1);return false;">首页</a>
											<a href=""
												onClick="goNextPage(${pageSjfg.curPage - 1 });return false;">上一页</a>
										</c:otherwise>
									</c:choose>
									<c:forEach begin="${pageSjfg.beginIndex}" end="${pageSjfg.endIndex}"
										var="pageCount" step="1">
										<c:choose>
											<c:when test="${ pageCount == pageSjfg.curPage}">
												<a href="" onClick="goNextPage(${pageCount });return false;"
													style="color: red;">${pageCount }</a>
											</c:when>
											<c:otherwise>
												<a href="" onClick="goNextPage(${pageCount });return false;">${pageCount}</a>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									<c:choose>
										<c:when test="${ pageSjfg.curPage >= pageSjfg.totalPage}">
											<a>下一页</a>
											<a>末页</a>
										</c:when>
										<c:otherwise>
											<a href=""
												onClick="goNextPage(${pageSjfg.curPage + 1});return false;">下一页</a>
											<a href=""
												onClick="goNextPage(${pageSjfg.totalPage });return false;">末页</a>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
