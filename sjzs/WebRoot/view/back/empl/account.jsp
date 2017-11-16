<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.hnzskj.common.Constant"%>
<%@ include file="/view/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>添加员工信息</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/dataCheck.js"></script>
	<script language="javascript" src="js/calendar/calendar.js" charset="utf-8"></script>
	<script language="javascript" src='js/showdialog.js'></script>
	<script type='text/javascript' src='dwr/interface/emplService.js'></script>
  	<script type='text/javascript' src='dwr/engine.js'></script>
	<script type="text/javascript">
			
		//清除提示信息
		function clearInfo(spanId) {
			document.getElementById(spanId).innerHTML = "<font color=\"red\">*必填</font>";
		}
		
		function checkUsername(uid, txtId) {
			var t_name = document.getElementById(txtId).value;
			if (!textCheck(txtId,1,200)) {
				alert("账号不可为空!");
				return false;
			}
			emplService.isExsit(uid,t_name,function(data){
						if (!data) {//如果当前用户不存在
							alert("当前用户名可以使用！");
							document.getElementById("addInfo").disabled = false;
						} else {
							alert("当前用户名已经存在，请使用其它用户名");
						}
					});
		}
	</script>
  </head>
<body>
	<div align="center">
	<form action="system/empl!addAcount.action" name="form1" method="post" >
	<input type="hidden" value="${empl.uid }" name="empl.uid">
	<%--<input type="hidden" value="${empl.ussjg }" name="empl.ussjg">--%>
	<input type="hidden" value="分配账号" name="closePage"/>
	<input type="hidden" value="员工管理" name="refreshPage"/>
	  <table width="99%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC">
	    <tr class="title_style">
	      <td colspan="8" style="text-align:left">
	      	<img src="images/group1.png" width="20" height="20" align="absmiddle" /> 为员工分配账号
	      </td>
	    </tr>
	    <tr class="row_bg">
	      <td colspan="8">
	      	<table width="99%" border="0" cellspacing="0" cellpadding="0" class="nonborder2">
		        <tr bgcolor="#F7FCFF">
		          <td align="right">员工账号：</td>
		          <td align="left">
		          	<s:textfield name="empl.username" theme="simple" id="tusername" class="input_width" ></s:textfield>
					<input class="button1" type="button" value="检测账号是否可用" onclick="checkUsername(${empl.uid },'tusername');">
					<span id="info1"><font color="red">*必填</font></span>
		          </td>
		        </tr>
		        <tr>
		         	<td colspan="2" align="center">
						<input type="submit" value=" 提  交 " id="addInfo" class="button1" disabled="disabled"/>
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











