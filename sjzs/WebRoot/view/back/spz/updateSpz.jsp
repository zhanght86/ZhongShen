<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/view/common/common.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>"/>
    <title>添加页面</title>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script type="text/javascript" src="dwr/interface/updateSpzname.js"></script>
	<script type="text/javascript" src="dwr/engine.js"></script>
	<script type="text/javascript" src="dwr/util.js"></script>
	<link href="style/common.css" rel="stylesheet" type="text/css" />
	<link href="css/style.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript">
  function check(){
	  var spzname=document.getElementById("spzname").value;
	  var oldName=document.getElementById("oldName").value;
	  if(spzname==""){
		  alert("名称不能为空，请输入!");
		  document.getElementById("spzname").focus();
		  return false;
		  }
	  if(oldName!=spzname){
	  updateSpzname.updateSpzname(spzname,{
			callback:function(array){
				if(array=="spzname"){
					alert("您所添加的名称已存在，请您重新填写");
					document.getElementById('spzname').focus();
					return false;
				}else{
					
					document.forms[0].submit();
				}
			}
			
		});
	  }else{
		  document.forms[0].submit();
		  }
		return false;
	  }
</script>
  </head>
<body>
	<div align="center">
	<form action="spz!updateSpz.action" method="post">
	
	    <input type="hidden" value="修改审批组 " name="closePage"/>
	  	<input type="hidden" value="审批组管理 " name="refreshPage"/>
	
	<input type="hidden" value="${spz.spzid}" name="spz.spzid"/>
    <input type="hidden" value="${spz.spzname}" id="oldName"/>
	  <table width="99%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC">
	    <tr class="title_style">
	      <td colspan="8" style="text-align:left">
	      	<img src="images/group1.png" width="20" height="20" align="absmiddle" /> 审批组信息
	      </td>
	    </tr>
	    <tr class="row_bg">
	      <td colspan="8">
	      	<table width="99%" border="0" cellspacing="0" cellpadding="0" class="nonborder2">
		        <tr bgcolor="#F7FCFF">
		          <td width="11%" align="right"> 审批组编号：</td>
		          <td width="39%" align="left">
		          	<input type="text" value="${spz.spzcode}" name="spz.spzcode" readonly="readonly"/>
		          	<span id="info1"><font color="red">*</font></span>
		          </td>
		          <td width="14%" align="right">审批组名称：</td>
		          <td width="34%" align="left">
		          <input type="text" value="${spz.spzname}" name="spz.spzname" id="spzname"/>
		          <span id="info1"><font color="red">*</font></span>
		          </td>
		        </tr>
	      </table>
	     </td>
	    </tr>
	  </table>
	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
	    <tr>
	      <td><input type="submit" value="修改" onclick="return check();"/></td>
	    </tr>
	  </table>
	  </form>
	</div>
</body>
</html>

