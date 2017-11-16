<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.hnzskj.common.Constant" %>
<%@ include file="/view/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>"/>
    <title>添加角色信息</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/dataCheck.js"></script>
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type='text/javascript' src='dwr/interface/role.js'></script>
  	<script language="javascript" src='js/showdialog.js'></script>
  	<script type='text/javascript' src='dwr/engine.js'></script>
	<script type="text/javascript">

	</script>
  </head>
<body>
	<div align="center">
	<form action="system/role!addRole.action" name="form1" method="post" >
	  	<input type="hidden" value="新增角色" name="closePage"/>
		<input type="hidden" value="角色管理" name="refreshPage"/>
	  <table width="99%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC">
	    <tr class="title_style">
	      <td colspan="8" style="text-align:left">
	      	<img src="images/group1.png" width="20" height="20" align="absmiddle" /> 角色基本信息
	      </td>
	    </tr>
	    <tr class="row_bg">
	      <td colspan="8">
	      	<table width="99%" border="0" cellspacing="0" cellpadding="0" class="nonborder2">
		        <tr bgcolor="#F7FCFF">
		          <td width="11%" align="right">角色名称：</td>
		          <td width="39%" align="left">
					<input id="rname" type="text" name="role.roleName"	onfocus="clearInfo('info1')" class="input_width"/>
		  			<span id="info1"><font color="red">*必填</font></span>
		          </td>
		        </tr>

		        <tr bgcolor="#F7FCFF">
		        	<td align="right">角色类型：</td>
		        	<td align="left">
		        	<input type="radio" name="role.roleType" value="<%=Constant.RTYPEN %>"  checked="checked" />业务角色
		        	<input type="radio" name="role.roleType" value=<%=Constant.RTYPEY %> />审批角色
		        		
		        	</td>
		        </tr>

		        <tr bgcolor="#F7FCFF">
		          <td align="right" valign="top">角色说明：</td>
		          <td align="left">
					<textarea rows="5" cols="30" id="rremark" name="role.roleRemark"></textarea>
		          </td>
		        </tr>
		        
		        <tr bgcolor="#F7FCFF">
	        		<td colspan="4" bgcolor="#FFFFFF">
						<input type="button" id="addInfo" value="提  交" onclick="checkRole();"/>
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


