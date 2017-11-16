<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<script type="text/javascript" src="dwr/interface/addSpz.js"></script>
	<script type="text/javascript" src="dwr/engine.js"></script>
	<script type="text/javascript" src="dwr/util.js"></script>
	<link href="style/common.css" rel="stylesheet" type="text/css" />
	<link href="css/style.css" type="text/css" rel="stylesheet"/>
	<script language="javascript">
		function check(){ 
			var spzname=document.getElementById('spzname').value;
        	var spzcode=document.getElementById('spzcode').value;
        	if (spzcode=="") { 
				alert("审批组编号不能为空，请您输入!");
				document.getElementById('spzcode').focus();
				return false;
			}
			if (spzname=="") { 
				alert("审批组名称不能为空，请您输入!");
				document.getElementById('spzname').focus();
				return false;
			}
			
			var reg = /^\d+$/;
			if(!reg.test(spzcode)){
				alert("审批组编号请填入数字！");
				document.getElementById('spzcode').focus();
				return false;
			}
			var reg1 = /^[\u4e00-\u9fa5]+$/;
			if(!reg1.test(spzname)){
				alert("审批组名称请填入汉字！");
				document.getElementById('spzname').focus();
				return false;
			}
			addSpz.addSpz(spzcode,spzname,{
 					callback:function(array){
 						if(array=="spzcode"){
 							alert("您所添加的编号已存在，请您重新填写");
 							document.getElementById('spzcode').focus();
 							return false;
 						}else{
 							if(array=="spzname"){
 								alert("您所添加的名称已存在，请您重新填写");
 								document.getElementById('spzname').focus();
 								return false;
 							}
 							document.forms[0].submit();
 						}
 					}
 					
 				});
			return false;
		
		}
		</script>
  </head>
<body>
	<div align="center">
	<form name="form1" action="spz!addSpz.action" method="post">
	<input type="hidden" value="新增审批组 " name="closePage"/>
	  	<input type="hidden" value="审批组管理 " name="refreshPage"/>
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
		          	<input name="spz.spzcode" type="text" id="spzcode" value=""/>
		          </td>
		          <td width="14%" align="right">审批组名称：</td>
		          <td width="34%" align="left">
		          <input type="text" name="spz.spzname" id="spzname" maxlength="100" onKeyUp="value=value.replace(/[^\a-\z\A-\Z0-9\u4E00-\u9FA5]/g,'')"/>
		          </td>
		        </tr>
	      </table>
	     </td>
	    </tr>
	  </table>
	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
	    <tr>
	      <td><input type="submit" class="singleboarder" value="添加" onclick="return check();"/></td>
	    </tr>
	  </table>
	  </form>
	</div>
</body>
</html>

