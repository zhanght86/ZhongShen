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
    <title>详细信息</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/dataCheck.js"></script>
	<script language="javascript" src='js/showdialog.js'></script>
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript">
	//提交前进行数据验证
	function submitCheck() {
			if(confirm("确定要修改吗？")){
				var result = false;
				if(promptMsg(textCheck('lcText',1,50),'内容不能为空','info1')){
					result = true;
				}
				return result;
			}else{
				return false;
			}
	}
			
	//清除提示信息
	function clearInfo(spanId) {
		document.getElementById(spanId).innerHTML = "<font color=\"red\">*必填</font>";
	}

	//删除项目信息
	function doDelete(){
		if(confirm("确定要删除吗?")){
			$("#form1").attr("action","system/latentCuster!delLatentCuster.action");
			return true;
		}
		return false;
	}
	</script>
  </head>
  <body>
	<div align="center">
	<form action="system/latentCuster!updLatentCuster.action" name="form1" id="form1" method="post">
	<input type="hidden" value="详细信息" name="closePage"/>
	<input type="hidden" value="潜在客户级别维护" name="refreshPage"/>
	<input type="hidden" value="${latentCuster.lcId }" name="latentCuster.lcId"/>
	  <table width="99%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC">
	    <tr class="title_style">
	      <td colspan="8" style="text-align:left">
	      	<img src="images/group1.png" width="20" height="20" align="absmiddle" />字典基本信息
	      </td>
	    </tr>
	    <tr class="row_bg">
	      <td colspan="8">
	      	<table width="99%" border="0" cellspacing="0" cellpadding="0" class="nonborder2">
		        <tr bgcolor="#F7FCFF">
		          <td width="11%" align="right">字典内容：</td>
		          <td width="39%" align="left">
		          	<input type="text" id="lcText" name="latentCuster.lcText" value="${latentCuster.lcText }" onfocus="clearInfo('info1')" class="input_width"/>
					<span id="info1"><font color="red">*必填</font></span>	
		          </td>
		          <td width="14%" align="right">排序号：</td>
		          <td width="34%" align="left">
					<input type="text" id="lcOrder" name="latentCuster.lcOrder" value="${latentCuster.lcOrder }"  class="input_width" onKeyUp="value=value.replace(/[^\d]/g,'')"/>
		          </td>
		        </tr>
		        <tr>
		          <td align="right" >录入时间：</td>
		          <td align="left">
		    			<input type="text" name="latentCuster.lcDateTime" value="${fn:substring(latentCuster.lcDateTime,1,19) }" id="lcDateTime" readonly="readonly"/>
	    			&nbsp;
		          </td>
		        </tr>
		        <tr>
		          	<td colspan="4" align="center" >
						<input name="submit" type="submit" value="&nbsp;修&nbsp;改&nbsp;" onclick="return submitCheck();"/>&nbsp;&nbsp;&nbsp;&nbsp;
		          		<input name="submit" type="button" value="&nbsp;新&nbsp;曾&nbsp;" onclick="self.parent.addTab('新增字典','system/latentCuster!goAddPage.action','icon-add')"/>&nbsp;&nbsp;&nbsp;&nbsp;
						<input name="submit" type="submit" value="&nbsp;删&nbsp;除&nbsp;" onclick="javascript:return doDelete();"/>
						
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



