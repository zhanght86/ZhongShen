<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/view/common/common.jsp"%>
<%@ page import="com.hnzskj.common.Constant" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>"/>
    <title>修改功能信息</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
		var baseURL = "<%=basePath%>";
	</script>
	<script type="text/javascript" src="js/dataCheck.js"></script>
	<script language="javascript" src='js/showdialog.js'></script>
	<script type="text/javascript" src="js/zh/power.js"></script>
  </head>
  <body>
	<div align="center">
	<form action="system/power!updatePower.action" name="form1" method="post" onSubmit="return submitCheck();">
	<s:hidden name="power.powerId" theme="simple"></s:hidden>
	<input type="hidden" value="编辑功能" name="closePage"/>
	<input type="hidden" value="功能管理" name="refreshPage"/>
	  <table width="99%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC">
	    <tr class="title_style">
	      <td colspan="8" style="text-align:left">
	      	<img src="images/group1.png" width="20" height="20" align="absmiddle" />功能基本信息
	      </td>
	    </tr>
	    <tr class="row_bg">
	      <td colspan="8">
	      	<table width="99%" border="0" cellspacing="0" cellpadding="0" class="nonborder2">
		        <tr bgcolor="#F7FCFF">
		          <td width="11%" align="right">功能名称：</td>
		          <td width="39%" align="left">
					<s:textfield id="pname" name="power.powerName" onfocus="clearInfo('info1')" theme="simple" class="input_width"></s:textfield>
					<span id="info1"><font color="red">*必填</font></span>	
		          </td>
		          <td width="14%" align="right">功能url：</td>
		          <td width="34%" align="left">
					<s:textfield id="url" name="power.powerUrl" onfocus="clearInfo('info2')" theme="simple" class="input_width"></s:textfield>
					<span id="info2"><font color="red">*必填</font></span>
		          </td>
		        </tr>
		        <tr>
		          
		          <td align="right" bgcolor="#FFFFFF">所属模块：</td>
		          <td align="left" bgcolor="#FFFFFF">
		          	<input type="hidden" name="power.powerParent" value="${power.powerParent }" id="parentCode"/>
	    			<input type="text" value="${power.parentNode.powerName }" readonly="readonly" id="parentName"/>
	    			<input type="button" value="选择父模块" onclick="selectPower();"/>
		          </td>
		          <td align="right" bgcolor="#FFFFFF">功能类型：</td>
		          <td align="left" bgcolor="#FFFFFF">
		          	<c:if test="${true == power.powerModule}">
		    			<input type="radio" name="power.powerModule" value="true" id="pisModule" checked="checked"/>功能模块
		    			<input type="radio" name="power.powerModule" value="false" id="pisModule"/>操作功能
	    			</c:if>
	    			<c:if test="${false == power.powerModule}">
		    			<input type="radio" name="power.powerModule" value="true" id="pisModule"/>功能模块
		    			<input type="radio" name="power.powerModule" value="false" id="pisModule" checked="checked"/>操作功能
	    			</c:if>&nbsp;
		          </td>
		        </tr>
		        <tr>
		          <td align="right" >功能级别：</td>
		          <td align="left" >&nbsp;
		          	<c:if test="${true == power.powerSuperPower}">
		    			<input type="radio" name="power.powerSuperPower" value="true" id="pisSuperPower" checked="checked"/>超级功能
		    			<input type="radio" name="power.powerSuperPower" value="false" id="pisSuperPower"/>普通功能
	    			</c:if>
	    			<c:if test="${false == power.powerSuperPower}">
		    			<input type="radio" name="power.powerSuperPower" value="true" id="pisSuperPower"/>超级功能
		    			<input type="radio" name="power.powerSuperPower" value="false" id="pisSuperPower" checked="checked"/>普通功能
	    			</c:if>&nbsp;
		          	<!--<s:hidden name="power.powerImg" theme="simple" id="pimg"></s:hidden> -->
		          </td>
		          <td align="right" >功能排序：</td>
		          <td align="left" >
		          	<input type="text" value="${power.powerOrder }" name="power.powerOrder" id="powerOrder"/>
		          </td>
		        </tr>
		        <tr>
		        	<td align="right">权限类型：</td>
		        	<td align="left" colspan="3">
			        	<input type="radio" name="power.powerType" value="<%=Constant.POTTOE %>" ${0 == power.powerType ?  "checked='checked'" : ""}/>业务权限
			        	<input type="radio" name="power.powerType" value="<%=Constant.FLTTOE %>" ${1 == power.powerType ?  "checked='checked'" : ""}/>流程权限
		            </td>
		        </tr>
		        <tr>
		          	<td colspan="4" align="center" bgcolor="#FFFFFF">
						<input name="submit" type="submit" id="addInfo" value="提  交" />
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



