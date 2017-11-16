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
    <title>添加功能页面</title>
	<jsp:include page="/jsPage.jsp"></jsp:include>
	<script type="text/javascript" src="js/dataCheck.js"></script>
	<script type="text/javascript">
		//提交前进行数据验证
		function submitCheck() {
			var result = true;
			result = promptMsg(textCheck('pname',1,200), '功能名称不可为空','info1') && result;
			if ( result ) {//如果验证成功，使提交按钮不可用，防止重复提交
				document.getElementById("addInfo").disabled = true;
			}
			return result;
		}
				
		//清除提示信息
		function clearInfo(spanId) {
			document.getElementById(spanId).innerHTML = "<font color=\"red\">*必填</font>";
		}

		//打开图片选择对话框
		function opeImgDia(){
			var myVal = window.showModalDialog("<%=basePath%>/view/common/selectImg.jsp","center=yes;dialogWidth=300px;dialogHeight=300px;localtion:no; menubar:no; status=yes; toolbar=yes;scroll:yes;resizable=yes;help=no");
			if (undefined != myVal ) {
				myVal = myVal.replace("<%=basePath%>","");
				document.getElementById("pimg").value = myVal;
				document.getElementById("showImg").src = myVal;
			}
		}
	</script>
  </head>
  <body>
	<div class="pageContent">
	<form action="system/power!addPower.action" name="form1" method="post" onsubmit="return submitCheck();">
	<input type="hidden" value="${power.powerParent }" name="power.powerParent"/>
	<input type="hidden" value="新增功能" name="closePage"/>
	<input type="hidden" value="功能管理" name="refreshPage"/>
	  <table class="nonborder" width="99%" border="0" cellspacing="0" cellpadding="0" >
	    <tr >
	      <td colspan="8" style="text-align:left">
	      	<div class="formBar">
	      	<img src="images/group1.png" width="20" height="20" align="absmiddle" /> 功能基本信息
	      	</div>
	      </td>
	    </tr>
	    <tr>
	      <td colspan="8">
	      	<table width="99%" border="0" cellspacing="0" cellpadding="0" class="nonborder2">
		        <tr>
		          <td width="11%" align="right">功能名称：</td>
		          <td width="39%" align="left">
		          	<input id="pname" type="text" name="power.powerName" onfocus="clearInfo('info1')" class="input_width"/>
					<span id="info1"><font color="red">*必填</font></span>
		          </td>
		          <td width="14%" align="right">功能url：</td>
		          <td width="34%" align="left">
		          	<input type="text" value="#" name="power.powerUrl" id="url" class="input_width"/>
					<span id="info2"><font color="red">*必填</font></span>	
		          </td>
		        </tr>
		        <tr>
		          <td align="right">功能类型：</td>
		          <td align="left">
					<input type="radio" name="power.powerModule" value="false" id="pisModule" checked="checked"/>操作功能
		          	<input type="radio" name="power.powerModule" value="true" id="pisModule"/>功能模块
		          </td>
		          <td align="right">功能级别：</td>
		          <td align="left">
					<input type="radio" name="power.powerSuperPower" value="false" id="pisSuperPower" checked="checked"/>普通功能
		          	<input type="radio" name="power.powerSuperPower" value="true" id="pisSuperPower" />超级功能
		          	<input type="hidden" value="js/tree/dtree/tree/book.gif" name="power.powerImg" id="pimg" class="input_width" />
		          <!-- 
	    			<img src="js/tree/dtree/tree/book.gif" id="showImg"/>
	    			<input type="button" value="选择图片" onclick="opeImgDia();"/>
	    			<span id="info2"><font color="red">*必填</font></span>
	    		  -->	
		          </td>
		        </tr>
		        <tr>
		          <td width="11%" align="right">功能排序：</td>
		          <td width="39%" align="left">
		          	<input id="powerOrder" type="text" name="power.powerOrder" onfocus="clearInfo('info1')" class="input_width" 
		          	 onkeyup="value=value.replace(/[^\d]/g,'')" 
		          	 onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>
					<span id="info1"><font color="red">*必填</font></span>
		          </td>
		          <td width="14%" align="right">权限类型</td>
		          <td width="34%" align="left">
		            <input type="radio" name="power.powerType" value="<%=Constant.POTTOE %>" checked="checked" />业务权限
		        	<input type="radio" name="power.powerType" value=<%=Constant.FLTTOE %> />审批权限</td>
		        </tr>
		        <tr style="height:50px;">
		        	<td>&nbsp;</td>
		        </tr>
		        <tr>
		          <td align="center" colspan="4" >
						<div class="button" align="center" style="margin-left:400px;"><div class="buttonContent" align="center"><button type="submit" id="addInfo">保存</button></div></div>
						<div class="button" align="center" style="margin-left:80px;"><div class="buttonContent" align="center"><button type="button" class="close">取消</button></div></div>
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


