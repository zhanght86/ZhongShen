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
    <base href="<%=basePath%>"/>
    <title>添加审计导航信息</title>
	
	
	<jsp:include page="/jsPage.jsp"></jsp:include>
		<script type="text/javascript" src="js/dataCheck.js"></script>
	<link rel="stylesheet" href="css/validate.css" type="text/css"></link>
	<script type="text/javascript" src="js/validate/jquery.js"></script>
	<script type="text/javascript" src="js/validate/jquery.validate.js"></script>
	<script type="text/javascript" src="js/validate/messages_cn.js"></script>
	<script type="text/javascript">
	 $(function(){
			$("#addLawForm").validate({
				rules:{
					"sjdhMethod.name":{
						required:true
					},
					"sjdhMethod.orderNum":{
						required:true
					} 
				},
				messages:{
					"law.lawName":{
						required:"必填"
					},
					"law.attachId":{
						required:"必填"
					} 
				},
				errorElement:"em",
				success:function(label) {
					label.text(" ").addClass("success")
				}
			});
		});	

	//为有readonly属性 的元素取消退格键
		$(function(){$("input").each(function(){var readonly = $(this).attr("readonly");if(readonly){$(this).keydown(function(){var keydown = event.keyCode;if(keydown){return false;}});}});});
		//提交前进行数据验证
		function submitMenuCheck() {
			var result = true;
			result = promptMsg(textCheck('methodName',1,200), '*必填','info1') && result;
			result = promptMsg(textCheck('methodOrder',1,200), '*必填','info2') && result;
			result = promptMsg(textCheck('methodTime',1,200), '*必填','info3') && result;
			return result;
		}
	</script>
	
  </head>
<body>
<div class="pageContent">
	<form name="form1" action="system/sjdh!doUpdateInfo.action" method="post" id="addForm" onsubmit="return submitMenuCheck();">
	<input name="sjdhMethod.id" type="hidden" value="${sjdhMethod.id }"/>
	  <table width="99%" border="0" cellspacing="0" cellpadding="0" class="list">
	    <tr class="title_style">
	      <td colspan="8" style="text-align:left">
	      	<div class="formBar">
				 <img src="images/group1.png" width="20" height="20" align="absmiddle" />审计导航方法基本信息	
			</div>
	      </td>
	    </tr>
	    <tr class="row_bg">
	      <td colspan="8">
	      	<table width="99%" border="0" cellspacing="0" cellpadding="0" class="">
		        <tr>
		          <td width="11%" align="right">方法名称：</td>
		          <td width="39%" align="left">
						<input name="sjdhMethod.name" type="text" value="${sjdhMethod.name }" id="methodName"/>
						<span id="info1"><font color="red">*必填</font></span>
				  </td>
		          <td width="14%" align="right">排序：</td>
		          <td width="34%" align="left">
		          <input name="sjdhMethod.orderNum" type="text" value="${sjdhMethod.orderNum }" id="methodOrder" onkeyup="value=value.replace(/[^\d]/g,'')" 
		          	 onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>
		          <span id="info2"><font color="red">*必填</font></span>
                  </td>
		        </tr>
		               	
		       	 <tr>
		          <td width="11%" align="right">创建时间：</td>
		          <td width="39%" align="left" >
						<input name="sjdhMethod.createTime" type="text" value="${sjdhMethod.createTime }" readonly="readonly" id="methodTime"/>
						<span id="info3"><font color="red">*必填</font></span>
				  </td>
		          <td width="14%" align="right">&nbsp;</td>
		          <td width="34%" align="left">
		          	&nbsp;
                  </td>
		        </tr>
		        <tr>
		          <td width="11%" align="right">方法描述：</td>
		          <td width="39%" align="left" >
						<textarea name="sjdhMethod.context" style="widht:50%;height:50px">${sjdhMethod.context }</textarea>
				  </td>
		          <td width="14%" align="right">&nbsp;</td>
		          <td width="34%" align="left">
		          	&nbsp;
                  </td>
		        </tr>
		        <tr>
		          <td align="center" colspan="4">
		          	<div class="formBar">
						<ul>
							<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
							<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
							<li>
								<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
							</li>
						</ul>
					</div>
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

