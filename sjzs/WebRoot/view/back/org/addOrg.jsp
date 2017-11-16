<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.hnzskj.common.Constant" %>
<%@ include file="/view/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("com", Constant.COMPANY);
request.setAttribute("department", Constant.DEPARTMENT);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>" target="_self"/>
    <title>
    	<c:if test="${org.orgType == department}">
          	 添加部门信息：
        </c:if>
       	<c:if test="${org.orgType == com}">
        	 添加公司信息：
       	</c:if>
    </title>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="css/style.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="js/dataCheck.js"></script>
	<script language="javascript" src='js/showdialog.js'></script>
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script language="javascript" src='js/zh/org.js'></script>
  </head>
 <body>
	<div align="center">
	<form action="system/org!addOrganization.action" name="form1" method="post" onsubmit="return submitCheck();" enctype="multipart/form-data"　target="_self">
		<input type="hidden" value="新增部门" name="closePage"/>
	  	<input type="hidden" value="部门管理" name="refreshPage"/>
	  	<input type="hidden" value="${org.orgType }" name="org.orgType"/>
	  <table width="99%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC">
	    <tr class="title_style">
	      <td colspan="8" style="text-align:left">
	      	<img src="images/group1.png" width="20" height="20" align="absmiddle" /> 部门基本信息
	      </td>
	    </tr>
	    <tr class="row_bg">
	      <td colspan="8">
	      	<table width="99%" border="0" cellspacing="0" cellpadding="0" class="nonborder2">
		        <tr bgcolor="#F7FCFF">
		          <td width="11%" align="right" bgcolor="#FFFFFF">部门名称：</td>
		          <td width="39%" align="left" bgcolor="#FFFFFF">
		          	<input id="orgName" type="text" name="org.orgName" onfocus="clearInfo('info1')" class="input_width"/>
					<span id="info1"><font color="red">*必填</font></span>
		          </td>
		          <td align="right" bgcolor="#FFFFFF">
		          	<c:if test="${org.orgType == department}">
			          	上级部门：
		          	</c:if>
		          	<c:if test="${org.orgType == com}">
			          	上级公司：
		          	</c:if>
		          </td>
		          <td align="left" bgcolor="#FFFFFF">
			        <input type="text" value="${fu:getOrgNameByCode(org.orgParent) }" readonly="readonly"/>
	    			<input type="hidden" value="${org.orgParent}" name="org.orgParent" id="ssjgcode"/>
	    			<span id="info3"><font color="red">*必填</font></span>
		          </td>
		        </tr>
		        <tr>
		        	<td align="right" >
		        		部门排序：
		        	</td>
		        	<td align="left" colspan="3">
		        		<input type="text" value="" name="org.orgOrder" />
		        	</td>
		        </tr>
		        <%-- 
		        <tr>
	        		<td align="right"  bgcolor="#FFFFFF">
						部门签章：
	        		</td>
	        		<td align="left" colspan="3" bgcolor="#FFFFFF">
	        			<input type="file" name="signet" id="orgSignet" onChange="preImg(this)"/><span style="color:red">* 请上传*.gif,*.png格式的文件</span>
	        		</td>
		        </tr>
		         <tr bgcolor="#F7FCFF">
		          <td align="right" valign="top">图片预览：</td>
		          <td align="left" colspan="3"> 
		          		<div id="newPreview"></div>
		          		<img id="showimg" style="display: none;border: 1px #45A1B6 solid;" src="" />
						<img id="showImage" style=" display:none;">
		          </td>
		        </tr>	
		         --%>	
		         <tr>
	        		<td colspan="4" bgcolor="#FFFFFF">
						<input name="submit" type="submit" value="提  交" id="addInfo"/>
	        		</td>
		        </tr>
	      </table>
	     </td>
	    </tr>
	  </table>
		</form>
	</div>
</body>
</html>