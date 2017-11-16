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
		<base target="_self"/>
		<title>定性依据列表页面</title>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<style type="text/css">
			#tooltip{
				position: absolute;
				z-index: 2;	
				background: #efd;
				border: 1px solid #ccc;
				padding: 3px;
			}	
		</style>
		<link rel="stylesheet" type="text/css" href='css/style.css' />
		<link rel="stylesheet" href="css/right.css" type="text/css"></link>
		<link rel="stylesheet" type="text/css" href="js/themes/default/easyui.css" />
		<link rel="stylesheet" type="text/css" href="js/themes/icon.css" />
		<link rel="stylesheet" href="css/validate.css" type="text/css"></link>
		<script type="text/javascript" src="js/validate/jquery.js"></script>
		<script type="text/javascript" src="js/validate/jquery.validate.js"></script>
		<script type="text/javascript" src="js/validate/messages_cn.js"></script>
		<script type="text/javascript" src="js/validate/customValidate.js"></script>


		<script type="text/javascript" src="js/validate/customValidate.js"></script>
		<script language="javascript" src="js/calendar/calendar.js" charset="UTF-8"></script>
		<script language="javascript" src='js/showdialog.js'></script>
		<script language="javascript" src='js/dataCheck.js'></script>
		<!-- 
			<script type="text/javascript" src="js/fckeditor/fckeditor.js"></script>
		<script type="text/javascript" src="fckeditor/fckeditor.js"></script>	
			 -->
		<script type='text/javascript' src='dwr/interface/auditLaw.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type="text/javascript">

	function goNextPage( currentPage ) {
		document.getElementById("reqPage").value = currentPage;
		//if('${newFlag}'=='all'){
		//	$("#searchForm").attr("action","law!searchLaws.action");
		//}else
		//{
		//	$("#searchForm").attr("action","dxyj!searchLaws.action");
		//}
		//document.forms.namedItem("searchForm").submit();
		document.forms.namedItem("searchF").submit();
	}

	function getSjId(tid,tname){
		var turl ="system/dxyj!showsearchMessage.action?law.id="+tid+"&law.parentID=${law.parentID}";;
		//window.parent.closeWinForm(url);
		var rs = {id:tid,name:tname,url:turl};
		parent.window.returnValue= rs; 
	    window.close(); 
	}

	$(function(){
		function dymShowText(column,nameLen,width){
			$("tbody td:nth-child("+column+")").mousemove(function(event){
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
			
			$("tbody td:nth-child("+column+")").each(function(){
				var str = $(this).children("a").text();
				if($(this).text().length>nameLen){
					var temp = str.substr(str,nameLen) + "......";
					$(this).children("a").html(temp);
				}
			});
		};
		
		if(${empty flag}||${"" eq flag}){
			dymShowText(2,10,150);
			dymShowText(3,8,150);
			dymShowText(4,8,150);
		}else{
			dymShowText(3,10,150);
			dymShowText(4,8,150);
			dymShowText(5,8,150);
		}
		
	});
</script>
		<jsp:include page="/jsPage.jsp"></jsp:include>
	</head>
	<body >

  <form action="system/dxyj!showsearchMessage.action" name="searchF">
 	<input type="hidden" name="flag" value="0"/>
 	<input type="hidden" name="law.parentID" value="${law.parentID}"/>
 	<input type="hidden" name="page.curPage" value="${page.curPage }" id="reqPage"/>
 </form>

		<div id="navTab" class="tabsPage">
			<div id="main" align="center">			
			
				<div id="w_list_print">
					<table cellpadding="0" cellspacing="0" border="1" width="100%"
						class="list" targetType="navTab"
						style="font-size: 12px; margin-top: 5px;">
						<thead>
							
							<tr align="center">
							   <c:if test="${flag==0}">
								<th width="4%">
								</th>
								</c:if>
								<th width="8%">
									序号
								</th>
								  <th width="25%">
									<strong>法规文件名称</strong>
									</th>
									<th width="20%">
										<b>发文单位</b>
									</th>
									<th width="17%">
										<b>发文编号</b>
									</th>
									<th width="15%">
										<b>条</b>
									</th>
									<th width="15%">
										<b>款</b>
									</th>
							</tr>
						</thead>
						<tbody id = 'playList'>
							<c:if test="${!empty page.list}">
								<s:iterator id="entry" value="page.list" status="sta">
									<tr align="center" class="contentTd"
										onmouseover="this.className='onmouseover'"
										onmouseout="this.className='contentTd'" rel="${sta.count }">
										   <c:if test="${flag==0}">
										<td>
										<input type="radio" name="dxyjId" value="${entry.id }" onclick="getSjId('${entry.id}','${entry.caption }')"/>
										</td>
										</c:if>
										<td>
											${sta.count+(page.curPage-1)*page.maxResult}
										</td>
										<td>
											
											<a href="system/dxyj!getLawById.action?law.id =${entry.id }" target="_self">${entry.caption}</a>
											<input type="hidden" value="${entry.caption}" />
										</td>
										<td>
											<a style="text-decoration:none;">${entry.department}</a>
											<input type="hidden" value="${entry.department}" />
										</td>
										<td>
											<a style="text-decoration:none;">${entry.lawNo}</a>
											<input type="hidden" value="${entry.lawNo}" />
										</td>
										<td>
											<a style="text-decoration:none;">${entry.tiao}</a>
											<input type="hidden" value="${entry.tiao}" />
										</td>
										<td>
											${entry.kuan}
										</td>
									</tr>

								</s:iterator>

							</c:if>
							<c:if test="${empty page.list}">
								<tr class="contentTd" onmouseover="this.className='onmouseover'"
									onmouseout="this.className='contentTd'">
									<td colspan="7" align="center">
										对不起，没有您要查找的数据
									</td>
								</tr>
							</c:if>
 							 <c:if test="${flag==0}">
							<tr>
								<td colspan="7" height="27" align="right" class="bottomTd"><jsp:include
										page="/page.jsp"></jsp:include></td>
							</tr>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
			
		</div>
	</body>	
</html>
