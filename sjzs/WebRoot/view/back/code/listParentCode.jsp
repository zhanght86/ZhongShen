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
    <title>字典设置</title>
	<link href="css/style.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="js/dataCheck.js"></script>
	<script type="text/javascript">
		function goNextPage( currentPage ) {
			document.getElementById("reqPage").value = currentPage;
			document.forms.namedItem("queryForm").submit();	
		}
		
		function toUpdate(jmcode) {
			var selectVal = getCheckBox(jmcode,1);
			if ("" != selectVal) {
				var url ="rcCode!toUpdateParentInfo.action?rcCode.rc_Id="+selectVal;
				self.parent.addTab('修改字典',url,'icon-add');
			} else {
				alert("有且只有一个复选框必须被选中");
			}
		}
		
		function toSonRcCode(jmcode) {
			var selectVal = getCheckBox(jmcode,1);
			if ("" != selectVal) {
				var url ="rcCode!toUpdateSonInfo.action?rcCode.rc_Id="+selectVal;
				self.parent.addTab('字典设置',url,'icon-add');
			} else {
				alert("有且只有一个复选框必须被选中");
			}
		}
		
		function toDelete(jmcode) {
			var selectVal = getCheckBox(jmcode,1);
			if ("" != selectVal) {
				var url ="rcCode!deleteInfo.action?rcCode.rc_Id="+selectVal;
				if(confirm("是否删除!")){
					window.location.href=url;
				}
			} else {
				alert("有且只有一个复选框必须被选中");
			}
		}

	</script>
  </head>
<body>
<form action="rcCode!findAllParentRcCode.action" method="post" name="queryForm" style="display: inline;">
	<input type="hidden" value="<s:property value="page.curPage"/>" name="page.curPage" id="reqPage"/>
	
</form>
<div align="center" style="margin-top:10px;">
  <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="118" align="left"><img src="images/ny_t1.jpg" width="118" height="37" /></td>
      <td align="right" background="images/ny_t2.jpg">
     	<img src="images/ny_tj.jpg" width="19" height="19" align="absmiddle" />
		<a href="javascript:void(0)" title="新增字典" onclick="self.parent.addTab('新增字典','rcCode!toAddParentReCode.action','icon-add')" class="txt12">新增字典</a>
		<img src="images/ny_tj.jpg" width="19" height="19" align="absmiddle" />
		<a href="javascript:void(0)" title="修改字典" onclick="toUpdate('rcIds')" class="txt12">修改字典</a>
		<img src="images/ny_tj.jpg" width="19" height="19" align="absmiddle" />
		<a href="javascript:void(0)" title="字典设置" onclick="toSonRcCode('rcIds')" class="txt12">字典设置</a>
		<img src="images/ny_tj.jpg" width="19" height="19" align="absmiddle" />
		<a href="javascript:void(0)" title="删除字典" onclick="toDelete('rcIds')" class="txt12">删除字典</a>
      </td>
      <td width="6" align="right"><img src="images/ny_t3.jpg" width="6" height="37" /></td>
    </tr>
  </table>
  <table width="99%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td align="left" class="non_topborder">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC">
        <tr class="title_style">
        	<td width="5%">&nbsp;</td>
        	<td width="40%">字典内码</td>
  			<td width="40%">字典名称</td>
  			<td width="15%" colspan="3">排序</td>
        </tr>
    	<s:iterator var ="entry" value="page.list" >
    	<tr>
    		<td align="center"><input name="rcIds" type="checkbox" value="${entry.rc_Id }"/></td>
    		<td align="center">${entry.rc_no }</td>
    		<td align="center">${entry.rc_name }</td>
    		<td align="center">${entry.rc_order }</td>
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
	  	
	  	<c:if test="${empty page.list}">
	  		<tr>
	  			<td colspan="10" align="center"><b>没有找到您所需要的角色信息!</b></td>
	  		</tr>
	  	</c:if>
      </table>
      </td>
    </tr>
  </table>
</div>
</body>
</html>
