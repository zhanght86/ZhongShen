<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/view/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<base href="<%=basePath %>">
		<title>修改用户密码</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<link href="css/style.css" type=text/css rel=stylesheet />
		<style type="text/css">
			ul{text-align: center;
			}
			ul li{
				color: red;
				font:bold;
				font-size: 18px;
				list-style: none;
			}
		</style>
		<script language="javascript" src="js/jquery-1.4.4.min.js"></script>
		<script language="javascript">	
		function checkChangePassword() {
			if ($.trim($("#OldPassword").val()) == "") {
				alert("输入旧用户密码！");
				$("#OldPassword").focus();
				return false;
			}
			if ($.trim($("#Password").val()) == "") {
				alert("输入新用户密码！");
				$("#Password").focus();
				return false;
			}
			if ($.trim($("#Password1").val()) == "") {
				alert("输入新确认密码！");
				$("#Password1").focus();
				return false;
			}
			if (document.form1.Password.value != document.form1.Password1.value) {
				alert("新用户密码和新确认密码不一致，请重新填写新密码！");
				$("#Password").val("");
				$("#Password1").val("");
				$("#OldPassword").focus();
				return false;
			}
			return true;
		}
	</script>
	</head>
 <body>
	<div align="center">
	<form name="form1" action="system/empl!changePassword.action" method="post" onSubmit="return checkChangePassword();">
	  <input type="hidden" name="userid" value="${sessionScope.loginEmpl.emplId}" />
	  <table width="99%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC">
	    <tr class="title_style">
	      <td colspan="8" style="text-align:left">
	      	<img src="images/group1.png" width="20" height="20" align="absmiddle" /> 修改用户密码
	      </td>
	    </tr>
	    <tr class="row_bg">
	      <td colspan="8">
	      	<table width="99%" border="0" cellspacing="0" cellpadding="0" class="nonborder2">
		        <tr bgcolor="#F7FCFF">
		          <td align="right">&nbsp;旧&nbsp;密&nbsp;码：&nbsp;</td>
		          <td align="left">
		          	<input id="OldPassword" name="empl.emplPassword" type="Password" value="" size="50" class="input_width">
					<span id="info1"><font color="red">*必填</font></span>
		          </td>
		        </tr>
		        <tr>
		          <td align="right" bgcolor="#FFFFFF">&nbsp;新&nbsp;密&nbsp;码：&nbsp;</td>
		          <td align="left" bgcolor="#FFFFFF">
		          	<input id="Password" name="empl.emplPassword" type="Password" value="" size="50" class="input_width"/>
    				<span id="info3"><font color="red">*必填</font></span>	
		          </td>
		        </tr>
		        <tr>
		          <td align="right">&nbsp;确&nbsp;认&nbsp;密&nbsp;码：&nbsp;</td>
		          <td align="left">
		          	<input id="Password1" type="Password" value="" size="50" class="input_width"/>
    				<span id="info3"><font color="red">*必填</font></span>	
		          </td>
		        </tr>
		        <tr>
	        		<td colspan="4" bgcolor="#FFFFFF">
						<input name="submit" type="submit" value="修 改 密 码" id="addInfo"/>
	        		</td>
		        </tr>
	      </table>
	     </td>
	    </tr>
	  </table>
	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
	    <tr>
	      <td height="10"></td>
	    </tr>
	  </table>
		</form>
		<s:actionmessage theme="simple"/>
	</div>
</body>
</html>