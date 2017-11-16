<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    <title>添加字典</title>
	<link rel="stylesheet" href="css/right.css" type="text/css"></link>
		<link rel="stylesheet" href="css/validate.css" type="text/css"></link>
		<script type="text/javascript" src="js/validate/jquery.js"></script>
		<script type="text/javascript" src="js/validate/jquery.validate.js"></script>
		<script type="text/javascript" src="js/validate/messages_cn.js"></script>
		<script type="text/javascript" src="js/validate/customValidate.js"></script>
		<script type="text/javascript" src="js/fckeditor/fckeditor.js"></script>
		<script type="text/javascript" src="js/validate/messages_cn.js"></script>
		<script type="text/javascript" src="js/validate/customValidate.js"></script>
		<script language="javascript" src="js/calendar/calendar.js" charset="UTF-8"></script>
		<script language="javascript" src='js/showdialog.js'></script>
		<script type='text/javascript' src='dwr/interface/auditLaw.js'></script>
  		<script type='text/javascript' src='dwr/engine.js'></script>
    	<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src='js/dataCheck.js'></script>
		<script type='text/javascript' src='dwr/interface/attachSJZS.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
			<script type="text/javascript" src="fckeditor/fckeditor.js"></script>
	<script type="text/javascript">
		
		$(function(){
			$("#addForm").validate({
				rules:{
					"dataDicDto.dicName":{
						required:true
					},
					"dataDicDto.industry":{
						required:true
					},
					"dataDicDto.dicOrder":{
						digits:true
					}
				},
				messages:{
					"dataDicDto.dicName":{
						required:"请输入审计事项名称"
					},
					"dataDicDto.industry":{
						required:"请输入业务分类"
					},
					"dataDicDto.dicOrder":{
						digits:"请输入一个整数"
					}
				},
				errorElement:"em",
				success:function(label) {
					label.text(" ").addClass("success");
				}
			});
		});	

		
		//初始化table大小
		function init(){
			document.getElementById("add").style.width=document.body.offsetWidth-40;
		}

  </script>
  </head>
  <body onload="javascript:init();" style="margin-top:10px;">
	<div id="main" align="center">
	<form action="system/dataDic!addDataDic.action" method="post" id="addForm" id="addForm">
	<input type="hidden"  name="dataDicDto.dicParentId" value="${dataDicDto.dicParentId }"/>
		<table cellpadding="0" cellspacing="0" border="1"  id="add" class="table2">
			<caption>审计事项添加</caption>
			<tr class="contentTd">
				<td style="font-weight:bold; text-align:right; padding-right:10px;width: 25%">
					<em>*</em>审计事项名称：
				</td>
				<td style=" font-weight:bold; text-align:left; padding-left:10px;">
					<input type="text" value="" name="dataDicDto.dicName" maxlength="50"/>
				</td>
			</tr>
				<tr class="contentTd">
				<td style="font-weight:bold; text-align:right; padding-right:10px;" valign="top">
					业务分类
				</td>
				<td style=" font-weight:bold; text-align:left; padding-left:10px;">
					<input name="dataDicDto.industry" readonly="readonly" value="${dataDicDto.industry }"></input>
				</td>
			</tr>
			<tr class="contentTd">
				<td style="font-weight:bold; text-align:right; padding-right:10px;">
					排序：
				</td>
				<td style="text-align:left; padding-left:10px;">
					<input type="text" value="" name="dataDicDto.dicOrder" onkeyup="value=value.replace(/[^\d]/g,'')" 
		          	 onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>
				</td>
			</tr>
			<tr class="contentTd">
				<td style="font-weight:bold; text-align:right; padding-right:10px;" valign="top">
					说明：
				</td>
				<td style=" font-weight:bold; text-align:left; padding-left:10px;">
					<textarea name="dataDicDto.dicMemo" style="width: 95%;height: 150px;"></textarea>
				</td>
			</tr>
			<tr class="bottomTd" height="27" align="center">
				<td colspan="2">
					<input type="submit" value=" " id="savebtn" class="savebtn" >
					<%--<input type="button" onclick="javascript:history.go(-1);" value="&nbsp;" class="backbtn"/>--%>
				</td>
			</tr>
		</table>
	</form>
	</div>
  </body>
</html>
