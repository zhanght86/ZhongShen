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
    <base href="<%=basePath%>"　target="_self"/>
    <title>
    	<c:if test="${org.orgType == department}">
          	修改部门信息：
        </c:if>
       	<c:if test="${org.orgType == com}">
        	 修改公司信息：
       	</c:if>	
    </title>
	<script type="text/javascript" src="js/dataCheck.js"></script>
	<script language="javascript" src='js/showdialog.js'></script>
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="js/zh/org.js"></script>
	<link href="css/style.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript">
	//为有readonly属性 的元素取消退格键
		$(function(){$("input").each(function(){var readonly = $(this).attr("readonly");if(readonly){$(this).keydown(function(){var keydown = event.keyCode;if(keydown){return false;}});}});});
		//提交前进行数据验证
		function submitOrgCheck() {
			var result = true;result = promptMsg(textCheck('orgName',1,200), '部门名称不可为空','info1') && result;var ssjgcode = document.getElementById("ssjgcode").value;var orgId = document.getElementById("orgId").value;
			//验证选择的所属部门是否跟自身一样
			if(ssjgcode==orgId){document.getElementById("info3").innerHTML="<font color=\"red\"><b>所属部门不能为当前部门</b></font>";result=false;}
			if ( result ) {//如果验证成功，使提交按钮不可用，防止重复提交
				if(checkUpImg(document.getElementById("orgSignet"))){document.getElementById("addInfo").disabled = true;}else{result = false;}document.getElementById("addInfo").disabled = true;}return result;}
	</script>
  </head>
 <body>
	<div align="center">
	<form action="system/org!updateOrg.action" name="form1" method="post" onsubmit="return submitOrgCheck();" enctype="multipart/form-data" target="_self">
	  <input type="hidden" value="${org.orgId }" name="org.orgId" id="orgId"/>
	  <input type="hidden" value="编辑部门" name="closePage"/>
	  <input type="hidden" value="部门管理" name="refreshPage"/>
	  <table width="99%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC">
	    <tr class="title_style">
	      <td colspan="8" style="text-align:left">
	      	<img src="images/group1.png" width="20" height="20" align="absmiddle" /> 部门基本信息
	      </td>
	    </tr>
	    <tr class="row_bg">
	      <td colspan="8">
	      	<table width="99%" border="0" cellspacing="0" cellpadding="0" class="nonborder2">
		        <tr bgcolor="#F7FCFF">
		          <td width="11%" align="right" bgcolor="#FFFFFF">部门名称：</td>
		          <td width="39%" align="left" bgcolor="#FFFFFF">
					<s:textfield id="orgName" name="org.orgName" onfocus="clearInfo('info1')" theme="simple" cssClass="input_width"></s:textfield>
  					<span id="info1"><font color="red">*必填</font></span>
		          </td>		         
		          <td align="right" bgcolor="#FFFFFF">
		          	<c:if test="${org.orgType == department}">
			          	上级部门：
		          	</c:if>
		          	<c:if test="${org.orgType == com}">
			          	上级公司：
		          	</c:if>
		          </td>
		          <td align="left" bgcolor="#FFFFFF">
		          	<input type="text" value="${org.orgParentName }" id="ssjgname" readonly="readonly"/>
	    			<input type="button" onclick="selectOrg();" value="选择部门"/>
	    			<input type="hidden" value="${org.orgParent }" name="org.orgParent" id="ssjgcode"/>
	    			<span id="info3"><font color="red">*必填</font></span>
		          </td>
		          <td align="right" bgcolor="#FFFFFF">&nbsp;</td>
		          <td align="left" bgcolor="#FFFFFF">&nbsp;</td>
		        </tr>
		        <tr>
		        	<td align="right" >
		        		部门排序：
		        	</td>
		        	<td align="left" colspan="3">
		        		<input type="text" name="org.orgOrder" value="${org.orgOrder}" />
		        	</td>
		        </tr>
		        <!-- 
		        <tr bgcolor="#FFFFFF">
	        		<td align="right" >
						部门签章：
	        		</td>
	        		<td align="left" colspan="3">
	        			<input type="file" name="signet" id="orgSignet" onChange="preImg(this)"/><span style="color:red">* 请上传*.gif,*.png格式的文件</span>
	        		</td>
		        </tr>
		         <tr bgcolor="#F7FCFF">
		          <td align="right" valign="top">图片预览：</td>
		          <td align="left" colspan="3"> 
		          		<div id="newPreview"></div>
		          		<img id="showimg" style="display: none;border: 1px #45A1B6 solid;" src="" />
						<img id="showImage" style=" display:none;" >
		          </td>
		        </tr>
		         -->		
		        <tr>
	        		<td colspan="4" bgcolor="#FFFFFF">
						<input name="submit" type="submit" value="&nbsp;提&nbsp;交 &nbsp;" id="addInfo" onsubmit="javascript:return check();"/>
	        		</td>
		        </tr>		        
	      </table>
	     </td>
	    </tr>	    
	  </table>
		</form>
	</div>	
	<c:if test="${null != org.signetFileName && '' != org.signetFileName}">
		<div style="margin-top:10px;margin-left:10px;">		
		当前签章:<img src="ShowOrgSignetServlet?id=${org.orgId }" alt="" title="${org.parentName }" align="top"/>
		<a href="system/org!delSignet.action?org.orgId=${org.orgId }" onClick="if(confirm('您确认要删除个人签名么？')) {return true;} return false;">删除</a>
		</div>
	</c:if>
</body>
</html>
