<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/view/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>"/>
    <title>短信发送列表</title>
	<script language="javascript" src="js/public.js" charset="utf-8"></script>
	<link href="css/style.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="js/jquery-1.4.4.min.js"></script>	
	<script language="javascript" src="js/calendar/calendar.js" charset="utf-8"></script>
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
<form action="system/sms!searchSMS.action" method="post" style="display: none;">
	<label>发送时间:</label><input type="text" name="sms.ssendTime" value="<s:date name="sMS.ssendTime" format="yyyy-MM-dd"/>"/>
	<label>短信内容:</label><input type="text" name="sms.scontent" value="${operationLog.operation}"/>
	<input type="submit" value=" 搜 索 " class="button1" onclick="changeDisplay('subtab');"/>
</form>							
<form action="system/sms!searchSMS.action" method="post" name="queryForm" style="display: none;">
	<input type="hidden" value="<s:property value="page.curPage"/>" name="page.curPage" id="reqPage"/>
	<input type="hidden" name="sms.uid" value="${sMS.uid }"/>
	<input type="hidden" name="sms.ssendTime" value="<s:date name="sms.ssendTime" format="yyyy-MM-dd"/>"/>
	<input type="hidden" name="sms.scontent" value="${sMS.scontent }"/>
</form>
<div align="center" style="margin-top:10px;">
  <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="118" align="left"><img src="images/ny_t1.jpg" width="118" height="37" /></td>
      <td align="left" background="images/ny_t2.jpg" class="txt_bold">
      <form action="system/sms!searchSMS.action" method="post" id="searchF">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="50%" height="30" valign="top" style="font-size: 12px;">短信发送时间：
            <input type="text" name="sms.ssendTime" id="ssendTime" readonly="readonly"/>
            <img style="CURSOR: hand"
				onClick="setday(this,document.all.ssendTime);"
				src="js/calendar/form/calendar.gif" align="middle" border="0"
				width="26" height="26"/>
            <input type="image" src="images/ny_searchbtn.jpg"/>
          </td>
          <td width="49%" valign="middle">&nbsp;
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
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC">
        <tr class="title_style">
  			<td>序号</td>
  			<td>接收人</td>
  			<td>手机号</td>
  			<td>发送时间</td>
  			<td>发送状态</td>
  			<td>短信类别</td>
  			<td>短信内容</td>
        </tr>
        <c:if test="${!empty page.list}">
		  	<s:iterator id="entry" value="page.list" status="sta">
		  		<s:if test="#sta.even">
					<tr class="row_bg">
				</s:if>
				<s:else>
					<tr>
				</s:else>
		  			<td>${sta.count+(page.curPage-1)*page.maxResult}</td>
		  			<td class="txt03467B" align="center">
		  				${fu:getEmplNameById(entry.uid) }
		  			</td>
		  			<td class="txt03467B" align="center">
		  				${entry.ucellphone }
		  			</td>
		  			<td align="center"><s:date name="#entry.ssendTime" format="yyyy-MM-dd HH:mm:ss"/></td>
		  			<td align="center">
		  				<s:if test="#entry.success==true">
		  					<a class="green_txt">发送成功</a>
		  				</s:if>
		  				<s:else>
		  					<a class="red_txt">发送失败</a>
		  				</s:else>
		  			</td>
		  			<td align="center">
		  				${fu:getTypeNameByCode(entry.stype) }
		  			</td>
		  			<td style="padding-left: 10px;">
		  				<a title="查看短信详细内容" href="javascript:self.parent.addTab('短信详细信息','system/sms!showSMS.action?sms.sid=${entry.sid }')" class="txt12">
		  					${fn:substring(entry.scontent,0,30) }.....
		  				</a>
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
	  	<c:if test="${empty page.list}">
	  		<tr>
	  			<td colspan="10" align="center"><b>没有找到您所需要的记录!</b></td>
	  		</tr>
	  	</c:if>
      </table>
      </td>
    </tr>
  </table>
</div>
</body>
</html>