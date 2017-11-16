<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/view/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<script type="text/javascript" src="js/fckeditor/fckeditor.js"></script>
		<base href="<%=basePath%>" />
		<title>查看定性依据</title>
		<link rel="stylesheet" type="text/css" href='css/style.css' />
		<link rel="stylesheet" href="css/right.css" type="text/css"></link>
		<link rel="stylesheet" type="text/css"href="js/themes/default/easyui.css" />
		<link rel="stylesheet" type="text/css" href="js/themes/icon.css" />
	<link rel="stylesheet" href="css/validate.css" type="text/css"></link>
	<script type="text/javascript" src="js/validate/jquery.js"></script>
	<script type="text/javascript" src="js/validate/jquery.validate.js"></script>
	<script type="text/javascript" src="js/validate/messages_cn.js"></script>
		<script type="text/javascript" src="js/validate/customValidate.js"></script>
		<script type="text/javascript" src="fckeditor/fckeditor.js"></script>
		<script type="text/javascript" src="js/validate/customValidate.js"></script>
		<script language="javascript" src="js/calendar/calendar.js" charset="UTF-8"></script>
		<script language="javascript" src='js/showdialog.js'></script>
		<script language="javascript" src='js/dataCheck.js'></script>
		<script type='text/javascript' src='dwr/interface/auditLaw.js'></script>
  		<script type='text/javascript' src='dwr/engine.js'></script>
    	<script type='text/javascript' src='dwr/util.js'></script>
		<style type='text/css'>
* {
	font-size: 12px;
}

.current {
	background: blue;
	color: white;
}
.contentTd{
	width:30px;
}
</style>	
<jsp:include page="/jsPage.jsp"></jsp:include>	
	</head>

	<body style="margin-top: 10px;">
	<div id="navTab" class="tabsPage">
		<div id="main" align="center">
			<c:choose>
				<c:when test="${law != null}">
					<form method="post" action="sjzs!deleteAuditLaw.action" id="deleteForm" name="deleteForm" style="display: none;">
						<input type="hidden" name="law.id" value="${law.id}"/>
						 <input type="hidden" name="law.parentID" value="${law.parentID}"/>
					</form>
				
					<table height="300" class="table" id="show"
						style="font-size: 12px;">
						<thead>
							<tr>
								<th colspan="2"  style="height:40px;font-size: 25px">查看定性依据</th>
							</tr>
						</thead>
						<tbody>
							<tr class="contentTd" onmouseover="this.className='onmouseover'"
								onmouseout="this.className='contentTd'">
								<td class="conTd"
									style="font-weight: bold; text-align: right; padding-right: 10px; border-left: 0; width: 15%">
									<b>法规名称:</b>
								</td>
								<td style="padding-left: 10px; text-align: left">
									${law.caption}
								</td>
							</tr>
							<tr class="contentTd" onmouseover="this.className='onmouseover'"
								onmouseout="this.className='contentTd'">
								<td 
									style="font-weight: bold; text-align: right; padding-right: 10px; border-left: 0">
									<b>发文单位:</b>
								</td>
								<td style="padding-left: 10px; text-align: left">
									${law.department}
								</td>
							</tr>
							<tr class="contentTd" onmouseover="this.className='onmouseover'"
								onmouseout="this.className='contentTd'">
								<td
									style="font-weight: bold; text-align: right; padding-right: 10px; border-left: 0">
									<b>发文编号:</b>
								</td>
								<td style="padding-left: 10px; text-align: left">
									${law.lawNo}
								</td>
							</tr>

							<tr class="contentTd" onmouseover="this.className='onmouseover'"
								onmouseout="this.className='contentTd'">
								<td
									style="font-weight: bold; text-align: right; padding-right: 10px; border-left: 0">
									<b>条:</b>
								</td>
								<td style="padding-left: 10px; text-align: left">
									${law.tiao}
								</td>
							</tr>

							<tr class="contentTd" onmouseover="this.className='onmouseover'"
								onmouseout="this.className='contentTd'">
								<td
									style="font-weight: bold; text-align: right; padding-right: 10px; width: % 15;">
									款:
								</td>
								<td style="padding-left: 10px; text-align: left">
								
									${law.kuan}
								</td>
							</tr>

							<tr class="contentTd" onmouseover="this.className='onmouseover'"
								onmouseout="this.className='contentTd'">
								<td align="right" valign="top" style="padding-top: 10px;">
									<b>法规内容:</b>
								</td>
								<td style="padding: 5px; text-align: left">
									<%--<textarea rows="10" cols="80" style="width: 100%" readonly="readonly" name="law.lawContent" id="matText">${law.lawContent }</textarea>--%>
									<div  style="width:100%;height:450px;overflow-y:scroll ">
									${law.lawContent }
									</div>
								</td>
							</tr>
						</tbody>
					</table>			
					
				</c:when>
				<c:otherwise>
					<div align="center" style="margin-top: 10px;" >
						<table width="100%" border="0" cellspacing="0" cellpadding="0" id="show">							
							<tr>
								<td>
									没有相关法规依据！请添加！
								</td>
							</tr>
						</table>
					</div>
		
				</c:otherwise>
			</c:choose>			
		</div>
		</div>
	</body>	
</html>
