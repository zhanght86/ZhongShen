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
          	 添加字典信息
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
	<script type="text/javascript">
		//提交前进行数据验证
		function submitCheck() {
			var result = false;
			if(promptMsg(textCheck('lcText',1,50),'内容不能为空','info1')){
				result = true;
			}
			return result;
		}
				
		//清除提示信息
		function clearInfo(spanId) {
			document.getElementById(spanId).innerHTML = "<font color=\"red\">*必填</font>";
		}
	</script>
  </head>
 <body>
	<div align="center">
	<form action="system/latentCuster!addLatentCuster.action" name="form1" method="post"　target="_self">
		<input type="hidden" value="新增字典" name="closePage"/>
	  	<input type="hidden" value="潜在客户级别维护" name="refreshPage"/>
	  <table width="99%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC">
	    <tr class="title_style">
	      <td colspan="8" style="text-align:left">
	      	<img src="images/group1.png" width="20" height="20" align="absmiddle" /> 字典基本信息
	      </td>
	    </tr>
	    <tr class="row_bg">
	      <td colspan="8">
	      	<table width="99%" border="0" cellspacing="0" cellpadding="0" class="nonborder2">
		        <tr bgcolor="#F7FCFF">
		          <td width="11%" align="right" bgcolor="#FFFFFF">字典内容：</td>
		          <td width="39%" align="left" bgcolor="#FFFFFF">
		          	<input  type="text" id="lcText" name="latentCuster.lcText" onfocus="clearInfo('info1')" class="input_width"/>
					<span id="info1"><font color="red">*必填</font></span>
		          </td>
		          <td align="right" bgcolor="#FFFFFF">
		          	排序号：
		          </td>
		          <td align="left" bgcolor="#FFFFFF">
			        <input type="text" value="" name=latentCuster.lcOrder id="proName"  class="input_width" onKeyUp="value=value.replace(/[^\d]/g,'')"/>
		          </td>
		        </tr>
		         <tr>
	        		<td colspan="4" bgcolor="#FFFFFF">
						<input name="submit" type="submit" value="提  交" id="addInfo" onclick="return submitCheck();"/>
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