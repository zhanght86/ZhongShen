<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/view/common/common.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>" target="_self"/>
    <title>员工列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="css/style.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src='js/showdialog.js'></script>
<script type="text/javascript">
		function goNextPage(currentPage) {
			document.getElementById("reqPage").value = currentPage;
			document.forms.namedItem("queryForm").submit();	
		}
		//用户选择
		function selectEmpl(){
			getDataDoC2("<%=basePath%>system/empl!selectMain.action?time="+new Date().getTime(),935,"emplid2","emplname");
		}
		function check(){
			var emplid2 = document.getElementById("emplid2").value;
			var emplname = document.getElementById("emplname").value;
			if(emplid2==""){
				alert("请先选择员工!");
				document.getElementById('emplid2').focus();
				return false;
				}
			if(emplname==""){
				alert("请先选择员工!");
				document.getElementById('emplname').focus();
				return false;
				}
			document.getElementById("emplid1").value=emplid2;
			document.getElementById("emplname1").value=emplname;
			document.forms.namedItem("addForm").submit();
			return false;
				
			}
		function deleteEmpls(){
	       	  var gyid = new Array();
	       	  var index = 0;
	           var leng = document.getElementsByName("uids").length;
	             
	              for(var i=0;i<leng;i++){
	                  if(document.getElementsByName("uids")[i].checked==true){
	                	  gyid[index] = document.getElementsByName("uids")[i].value;
	                      index +=1;
	                   }
	                 }
	              if(gyid.length==0){
	     		          alert("请勾选要删除的员工");
	     		          return false;
	     		          }else{
	                          if(confirm("确认删除吗?")){
	                          document.forms.namedItem("delEmpls").submit();
	                          }
	     					 return false;
	     			          }
	              
	         }
	</script>
  </head>
<body>
          <form action="spz!addEmpl.action" method="post" name="addForm">
          <input type="hidden" value="<s:property value="spzid"/>" name="spzid"/>
          <input type="hidden" id="emplid1" name="emplid"/>
          <input type="hidden" id="emplname1"/>
          </form>
      <form action="spz!deleteEmpl.action" method="post" name="dropForm">
	  <input type="hidden" name="spzid" value="<s:property value="spzid"/>"/>
	  <input type="hidden" name="uid" id="uid"/>
	  </form>
  
  <form action="spz!searchEmplBySpz.action" method="post" name="queryForm">
  			<input type="hidden" value="<s:property value="spzid"/>" name="spzid"/>
  			<input type="hidden" name="emplPage.curPage" id="reqPage"/>
  	</form>
<div align="center" style="margin-top:10px;">
  <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="118" align="left"><img src="images/ny_t1.jpg" width="118" height="37" /></td>
      <td align="left" background="images/ny_t2.jpg" class="txt_bold">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="49%" valign="middle">
          
          	姓名：<input type="text" id="emplname" size="10" readonly="readonly"/>
          	<input type="hidden" id="emplid2"/>
          	 <c:if test="${fu:check('spz!addEmpl.action')}">
			 <input type="button" value="选择员工" onclick="selectEmpl();"/>
	  	     <input type="submit" value="添加员工" onclick="return check();" id="adde"/>
			 </c:if>
			 <%--<c:if test="${!fu:check('spz!addEmpl.action')}">
			 <input type="button" value="选择员工"/>
	  	     <input type="button" value="添加员工"/>
			 </c:if>
          	--%><c:if test="${fu:check('spz!deleteEmpls.action')}">
          	<img src="images/ny_sc.jpg" width="19" height="19" align="absmiddle" />
			 <a style="text-decoration: none" href="%" onclick="return deleteEmpls();" class="txt12">删除</a>
			 </c:if>
			  <%--<c:if test="${!fu:check('spz!deleteEmpls.action')}">
			 <font color="#A1A1A1">删除</font>
			 </c:if>
          	--%></td>
        </tr>
      </table>
      </td>
      <td width="6" align="right"><img src="images/ny_t3.jpg" width="6" height="37" /></td>
    </tr>
  </table>
  <table width="99%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td align="left" class="non_topborder">
      <form action="spz!deleteEmpls.action" method="post" name="delEmpls">
      <input type="hidden" value="${requestScope.spzid}" name="spzid"/>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC">
        <tr class="title_style">
  		    <td align="center">选择</td>
		    <td align="center">员工Id</td>
		    <td align="center">姓名</td>
		    <td align="center">性别</td>
		    <td align="center">职位</td>
		    <td align="center">Email</td>
		    <td align="center">手机号</td>
          </tr>
       	<c:if test="${!empty emplPage.list}">
	  	<s:iterator id="entity" value="emplPage.list" status="sta">
			<tr class="row_bg">
	  			<td align="center"><input type="checkbox" name="uids" value="<s:property value="#entity.uid"/>"/></td>
	  			<td align="center"><s:property value="#entity.uid"/></td><td align="center"><s:property value="#entity.uname"/></td>
	  			<c:if test="${entity.usex=='1'}">
			    <td align="center">男</td><td align="center"><s:property value="#entity.uposition"/></td>
			    </c:if>
			    <c:if test="${entity.usex=='0'}">
			    <td align="center">女</td><td align="center"><s:property value="#entity.uposition"/></td>
			    </c:if>
			    <td align="center"><s:property value="#entity.uemail"/></td><td align="center"><s:property value="#entity.ucellphone"/></td>
			</tr>				
	  	</s:iterator>
	  	</c:if>
	  	<c:if test="${empty emplPage.list}">
	  		<tr>
	  			<td colspan="12" align="center"><b>没有找到您所需要的记录!</b></td>
	  		</tr>
	  	</c:if>
        <tr class="row_bg"><td height="29" colspan="9" background="images/ny_titlebg1.jpg">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" >
        <tr class="nonborder">
        <td width="18%" align="right">&nbsp;
			总记录数：${emplPage.totalRecords} 条
			
			<c:choose>
				<c:when test="${ 1 == emplPage.curPage}">
					<a>首页</a>
					<a>上一页</a>
				</c:when>
				<c:otherwise>
					<a href="" onclick="goNextPage(1);return false;" style="text-decoration: none">首页</a>
					<a href="" onclick="goNextPage(${emplPage.curPage - 1 });return false;" style="text-decoration: none">上一页</a>
				</c:otherwise>
			</c:choose>
			<c:forEach begin="${emplPage.beginIndex}" end="${emplPage.endIndex}" var="pageCount" step="1">
				<c:choose>
					<c:when test="${ pageCount == emplPage.curPage}">
						<a href="" onclick="goNextPage(${pageCount });return false;" style="color:red; text-decoration: none" >${pageCount }</a>
					</c:when>
					<c:otherwise>
						<a href="" onclick="goNextPage(${pageCount });return false;" style="text-decoration: none">${pageCount }</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${ emplPage.curPage == emplPage.totalPage}">
					<a>下一页</a>
					<a>末页</a>
				</c:when>
				<c:otherwise>
					<a href="" onclick="goNextPage(${emplPage.curPage + 1});return false;" style="text-decoration: none">下一页</a>
					<a href="" onclick="goNextPage(${emplPage.totalPage });return false;" style="text-decoration: none">末页</a>
				</c:otherwise>
			</c:choose>
			</td>
			</tr>
			</table>
	</td></tr>
      </table>
      </form>
      </td>
    </tr>
  </table>
</div>
</body>
</html>