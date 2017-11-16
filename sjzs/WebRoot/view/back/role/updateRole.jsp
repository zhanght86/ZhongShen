<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.hnzskj.common.Constant" %>
<%@ include file="/view/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>修改角色信息</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/dataCheck.js"></script>
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type='text/javascript' src='dwr/interface/role.js'></script>
	<script language="javascript" src='js/showdialog.js'></script>
  	<script type='text/javascript' src='dwr/engine.js'></script>
	<script type="text/javascript">
		//清除提示信息
		function clearInfo(spanId) {
			document.getElementById(spanId).innerHTML = "<font color=\"red\">*必填</font>";
		}

		function checkRole() {
			var r_name = $("#rname").val();
			var roleId = '${role.roleId}';
			var result = textCheck('rname',1,200);
			if (!result) {
				alert("请输入角色名称!");
				return;
			}
			role.isExsit(roleId,r_name,function (data) {
					if (!data) {
						document.forms["updateForm"].submit();
					} else {
						alert("该角色名称已经被使用！");
					}
				});
		}
		//部门选择
		function selectDepart(){
		
			getDataDoC2("<%=basePath%>system/role!selectMains.action?time="+new Date().getTime(),800,"orgssjg","orgssjgname");
		}
		
		function selectType(type){
			var types = <%=Constant.RTYPEY%>;
			if(type==types){
				document.getElementById("txtrtype").style.display="block";
			}
			else{
				document.getElementById("txtrtype").style.display="none";
			}
		}
	</script>
  </head>
<body>
	<div align="center">
	<form action="system/role!updateRole.action" name="updateForm" id="updateForm" method="post" >
		<input type="hidden" value="编辑角色" name="closePage"/>
		<input type="hidden" value="角色管理" name="refreshPage"/>
		<input type="hidden" value="${role.roleId }" name="role.roleId">
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
					<s:textfield id="rname" name="role.roleName" onfocus="clearInfo('info1')" theme="simple"></s:textfield>
		  			<span id="info1"><font color="red">*必填</font></span>
		          </td>
		        </tr>
		        
		         <tr bgcolor="#F7FCFF">
		        	<td align="right">角色类型：</td>
		        	<td align="left">
		        	<input type="radio" name="role.roleType" value="<%=Constant.RTYPEN %>" ${0 == role.roleType ?  "checked='checked'" : ""} />业务角色
		        	<input type="radio" name="role.roleType" value="<%=Constant.RTYPEY %>" ${1 == role.roleType ?  "checked='checked'" : ""} />审批角色
		        		
		        	</td>
		        </tr>
		         
		        <tr bgcolor="#F7FCFF">
		          <td align="right" valign="top">角色说明：</td>
		          <td align="left">
					<s:textarea name="role.roleRemark" id="rremark" rows="5" cols="30" theme="simple"></s:textarea>
		          </td>
		        </tr>
		        <tr>
	        		<td colspan="4" align="center" bgcolor="#FFFFFF">
						<input name="button" type="button" value="提  交" id="addInfo" onclick="checkRole();"/>
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
