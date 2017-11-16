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
	<link href="js/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
	<link href="js/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
	<link href="js/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
	<link href="js/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
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
			var result = textCheck('rname',1,200);
			if (!result) {
				alert("请输入角色名称!");
				return;
			}
			role.isExsit(0,r_name,function (data) {
					if (!data) {
						document.forms["form1"].submit();
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
				document.getElementById("messageRade").style.display="block";
				document.getElementById("orgssjgname").value="";
			}
			else{
				document.getElementById("txtrtype").style.display="none";
				document.getElementById("messageRade").style.display="none";
			}
		}
	</script>
  </head>
<body>
	<div class="pageContent">
	<form action="system/role!addRole.action" name="form1" method="post" >
	  	<input type="hidden" value="新增角色" name="closePage"/>
		<input type="hidden" value="角色管理" name="refreshPage"/>
	  <table width="99%" border="0" cellspacing="0" cellpadding="0" class="list">
	    <tr>
	      <td colspan="8" style="text-align:left">
	      	<div class="formBar">
	      		<img src="images/group1.png" width="20" height="20" align="absmiddle" /> 角色基本信息
	      	</div>
	      </td>
	    </tr>
	    <tr>
	      <td colspan="8">
	      	<table width="99%" border="0" cellspacing="0" cellpadding="0" class="nonborder2">
		        <tr>
		          <td width="11%" align="right">角色名称：</td>
		          <td width="39%" align="left">
					<input id="rname" type="text" name="role.roleName"	onfocus="clearInfo('info1')" class="input_width"/>
		  			<span id="info1"><font color="red">*必填</font></span>
		          </td>
		        </tr>

		        <tr>
		        	<td align="right">角色类型：</td>
		        	<td align="left">
		        	<input type="radio" name="role.roleType" value="<%=Constant.RTYPEN %>"  checked="checked" />业务角色
		        	<input type="radio" name="role.roleType" value=<%=Constant.RTYPEY %> />审批角色
		        		
		        	</td>
		        </tr>

		        <tr>
		          <td align="right" valign="top">角色说明：</td>
		          <td align="left">
					<textarea rows="5" cols="30" id="rremark" name="role.roleRemark"></textarea>
		          </td>
		        </tr>
		        
	      </table>
	     </td>
	    </tr>
	     <tr>
	        		<td colspan="8">
					<div class="formBar">
						<input type="button" id="addInfo" value="提  交" onclick="checkRole();"/>						
					</div>
	        		</td>
		        </tr>
	  </table>
	</form>
	</div>
</body>
</html>


