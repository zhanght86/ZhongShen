<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/view/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>"/>
    <title>添加功能页面</title>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="<%=basePath%>css/style.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="js/dataCheck.js"></script>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
		function returnSonValue() {
	    //String tempInfo="#tempno=template_no#tempname=template_name#tempinfo=description_info#temptime=create_time#tempcode=order_code";
		var tempNo = document.forms[0].elements["template.template_no"].value;
		var tempName = document.forms[0].elements["template.template_name"].value;
		var descInfo = document.forms[0].elements["template.description_info"].value;
		var tempdate = document.forms[0].elements["template.create_time"].value;
		var tempcode = document.forms[0].elements["template.order_code"].value;
		var flowmain ="*tempno="+tempNo+"*tempname="+tempName+"*tempinfo="+ descInfo+"*temptime="+ tempdate+"*tempcode="+tempcode;
		window.returnValue = flowmain;
		window.close();
	}
	</script>
  </head>
  <body>
	<div align="center">
	<form action="" name="form1" method="post" onsubmit="return submitCheck();">
	  <table width="99%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC">
	    <tr class="title_style">
	      <td colspan="8" style="text-align:left">
	      	<img src="images/group1.png" width="20" height="20" /> 功能基本信息
	      </td>
	    </tr>
	    <tr class="row_bg">
	      <td colspan="8">
	      	<table width="99%" border="0" cellspacing="0" cellpadding="0" class="nonborder2">
		        <tr bgcolor="#F7FCFF">
		          <td align="right">模版编号：</td>
		          <td align="left">
		          	<input type="text" name="template.template_no" value="${template.template_no}" readonly=readonly />
					<span id="info1"><font color="red">*必填</font></span>
		          </td>
		        </tr>
		        <tr bgcolor="#F7FCFF">
		          <td align="right">模板名称：</td>
		          <td align="left">
		          	<input type="text" name="template.template_name" value="${template.template_name}" />
					<span id="info1"><font color="red">*必填</font></span>
		          </td>
		        </tr>
		        <tr bgcolor="#F7FCFF">
		          <td align="right">模版初始化路径：</td>
		          <td align="left">
		          	<textarea rows="4" cols="30" name="template.description_info">${template.description_info}</textarea>
					<span id="info1"><font color="red">*必填</font></span>
		          </td>
		        </tr>
		        <tr bgcolor="#F7FCFF">
		          <td align="right">模版类型编号	</td>
		          <td align="left">
		          	<input type="text" name="template.order_code" value="${template.order_code}" />
		          </td>
		        </tr>
		        <tr bgcolor="#F7FCFF">
		          <td align="center" colspan="2">
		                <input type="button" value="提交" onclick="returnSonValue()"/>
						<input type="button" value="关闭" onclick="javascript:window.close()"/>
				  </td>
		        </tr>
	      </table>
	     </td>
	    </tr>
	  </table>
	        <input type="hidden" name="template.create_time" value="${template.create_time}" />
			<s:hidden name="template.flowmain"></s:hidden>
	  </form>
	</div>
</body>
</html>


