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
    <base href="<%=basePath%>">
    <title>更新用户信息</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="css/validate.css" type="text/css"></link>
	<script type="text/javascript" src="js/dataCheck.js"></script>
	<script language="javascript" src="js/calendar/calendar.js" charset="utf-8"></script>
	<script language="javascript" src='js/showdialog.js'></script>
	<script type='text/javascript' src='dwr/interface/emplService.js'></script>
  	<script type='text/javascript' src='dwr/engine.js'></script>
	<script type="text/javascript" src="js/validate/jquery.js"></script>
	<script type="text/javascript" src="js/validate/jquery.validate.js"></script>
	<script type="text/javascript" src="js/validate/messages_cn.js"></script>
	<script type="text/javascript" src="js/validate/customValidate.js"></script>
	<script type="text/javascript">
		
		$(function(){
			$("input").each(function(){
				var readonly = $(this).attr("readonly");
				if(readonly){
					$(this).keydown(function(){
						var keydown = event.keyCode;
						if(8==keydown){
							return false;
						}
					});
				}
			});
			
			$("#updateForm").validate({
				rules:{
					"empl.emplName":{
						required:true
					},
					"empl.emplAccount":{
						required:true
					},
					"empl.emplOfficeTel":"phone",
					"empl.emplHomeTel":"phone",
					"empl.emplMobile":"mobile",
					"empl.emplEmail":"email",
					"empl.emplIdCard":"idCard",
					"empl.emplqq":"qq"
				},
				messages:{
					"empl.emplName":{
						required:"请输入用户姓名"
					},
					"empl.emplAccount":{
						required:"请输入账号"
					}
				},
				errorElement:"em",
				success:function(label) {
					label.text(" ").addClass("success");
				}
			});
		});		

		//提交前进行数据验证
		function submitCheck() {
			var result = true;
			var t_name = document.getElementById("tuname");
			emplService.isExsit('${empl.emplId }',t_name.value,function(data){
						if (!data) {//如果当前用户不存在
							document.getElementById("addInfo").disabled = true;
							document.forms["form1"].submit();
						} else {
							alert("当前用户名已经存在，请使用其它用户名");
						}
					});
			return result;
		}
				
		//部门选择
		//function selectorg(){
		//	getDataDoC2("<%=basePath%>system/org!selectOrg.action?time="+new Date().getTime(),400,"emplssjg","emplssjgname");
		//}
		//角色选择
		function selectSF(){
			getDataDoC2("<%=basePath%>system/role!selectRole.action?time="+new Date().getTime(),400,"rcodes","rnames");
		}

		
	</script>
  </head>
<body>
	<div align="center">
	<form name="form1" action="system/empl!updateEmpl.action" method="post" id="updateForm">
	<input type="hidden" value="${empl.emplId }" name="empl.emplId">
	<input type="hidden" value="编辑用户" name="closePage"/>
	<input type="hidden" value="用户管理" name="refreshPage"/>
	  <table width="99%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC">
	    <tr class="title_style">
	      <td colspan="8" style="text-align:left">
	      	<img src="images/group1.png" width="20" height="20" align="absmiddle" />用户基本信息
	      </td>
	    </tr>
	    <tr class="row_bg">
	      <td colspan="8">
	      	<table width="99%" border="0" cellspacing="0" cellpadding="0" class="nonborder2">
		        <tr bgcolor="#F7FCFF">
		          <td width="11%" align="right">用户姓名：</td>
		          <td width="39%" align="left">
					<s:textfield name="empl.emplName" cssClass="input_width2" theme="simple" id="uname"></s:textfield>
					<span id="info1"><font color="red">*必填</font></span>
		          </td>
		          <td align="right">性别：</td>
		          <td align="left">
					<c:if test="${empl.emplSex == '1'}">
					 	<input type="radio" value="<%=Constant.MAN %>" name="empl.emplSex" id="usex0" checked="checked">男
					 	<input type="radio" value="<%=Constant.WOMAN %>" name="empl.emplSex" id="usex1">女
				 	</c:if>
				 	<c:if test="${empl.emplSex == '0'}">
				 		<input type="radio" value="<%=Constant.MAN %>" name="empl.emplSex" id="usex0">男
					 	<input type="radio" value="<%=Constant.WOMAN %>" name="empl.emplSex" id="usex1" checked="checked">女
				 	</c:if>
		          </td>
		        </tr>
		        <tr>
		          <td align="right"  bgcolor="#FFFFFF">账号：</td>
		          <td align="left"  bgcolor="#FFFFFF">
					<s:textfield name="empl.emplAccount" id="tuname" cssClass="input_width2" theme="simple"></s:textfield>
					<span id="info4"><font color="red">*必填</font></span>
		          </td>
		          <td align="right" bgcolor="#FFFFFF">年龄：</td>
		          <td align="left" bgcolor="#FFFFFF">
						<s:textfield theme="simple" name="empl.emplAge" id="uage" cssClass="input_width2" onkeyup="value=value.replace(/[^\d]/g,'')"></s:textfield>
		          </td>
		        </tr>
		        <tr>
		          <td align="right">Email：</td>
		          <td align="left">
					<s:textfield theme="simple" cssClass="input_width2" name="empl.emplEmail" id="uemail" ></s:textfield>
				  </td>
		        </tr>
		        <tr>
		          <td align="right" bgcolor="#FFFFFF">QQ：</td>
		          <td align="left" bgcolor="#FFFFFF">
					<input name="empl.emplqq" type="text" value="${empl.emplqq }" class="input_width2" onKeyUp="value=value.replace(/[^\d]/g,'')"/>
		          </td>
		          <td align="right" bgcolor="#FFFFFF">职位：</td>
		          <td align="left" bgcolor="#FFFFFF">
						<s:textfield theme="simple" cssClass="input_width2" name="empl.emplPosition" id="uposition"></s:textfield>
		          </td>
		        </tr>
		        <tr>
		          <td align="right">注册日期：</td>
		          <td align="left">
						<input type="text" class="input_width2" name="empl.emplRuZhi" id="urzrq" value="<s:date name="empl.emplRuZhi" format="yyyy-MM-dd"/>"
									maxlength="100" readonly="readonly">
						<img style="CURSOR: hand"
							onClick="setday(this,document.all.urzrq);"
							src="js/calendar/form/calendar.gif" align="middle" border="0"
							width="26" height="26">
		          </td>
		          <td align="right" >电话：</td>
		          <td align="left">
					<s:textfield theme="simple" cssClass="input_width2" name="empl.emplOfficeTel" id="utelephone" ></s:textfield>
		          </td>
		        </tr>
		        <tr>
		          <td align="right" bgcolor="#FFFFFF">住址：</td>
		          <td align="left" bgcolor="#FFFFFF">
					<s:textfield theme="simple" name="empl.emplAddress" size="60" cssClass="input_width2"></s:textfield>
		          </td>
		          <td align="right" bgcolor="#FFFFFF">手机号：</td>
		          <td align="left" bgcolor="#FFFFFF">
					<s:textfield theme="simple" name="empl.emplMobile" id="ucellphone" cssClass="input_width2" onkeyup="value=value.replace(/[^\d]/g,'')"></s:textfield>
					<span id="infotell"></span>	
		          </td>
		        </tr>
		        <tr>
		          <td align="right">员工状态：</td>
		          <td align="left">
					<select name="empl.emplStatus"  class="input_width2">
					  <!-- 如果此操作者具有修改权限，那么他可以任意修改员工的状态  mjl-->
						<c:if test="${fu:check('system/empl!goUpdatePage.action')}">
				    		<option value="<%=Constant.UNINIT %>" ${'0' == empl.emplStatus ? "selected=\"selected\"" : '' }>未启用</option>
				    		<option value="<%=Constant.NORMAL %>" ${'1' == empl.emplStatus ? "selected=\"selected\"" : ''}>启用</option>
				    		<option value="<%=Constant.DIMISSION %>" ${'2' == empl.emplStatus ? "selected=\"selected\"" : ''}>注销</option>
				    		<option value="<%=Constant.SUSPEND %>" ${'3' == empl.emplStatus ? "selected=\"selected\"" : ''}>停用</option>
		  				</c:if>
		  				<c:if test="${!fu:check('system/empl!goUpdatePage.action')}">
				    		<c:if test="${'0' == empl.emplStatus}">
					    		<option value="<%=Constant.UNINIT %>" ${'0' == empl.emplStatus ? "selected=\"selected\"" : '' }>未启用</option>
					    		<option value="<%=Constant.NORMAL %>" ${'1' == empl.emplStatus ? "selected=\"selected\"" : ''}>启用</option>
					    		<option value="<%=Constant.DIMISSION %>" ${'2' == empl.emplStatus ? "selected=\"selected\"" : ''}>注销</option>
				    		</c:if>
				    		<c:if test="${'0' != empl.emplStatus}">
					    		<option value="<%=Constant.NORMAL %>" ${'1' == empl.emplStatus ? "selected=\"selected\"" : ''}>启用</option>
					    		<option value="<%=Constant.DIMISSION %>" ${'2' == empl.emplStatus ? "selected=\"selected\"" : ''}>注销</option>
					    		<option value="<%=Constant.SUSPEND %>" ${'3' == empl.emplStatus ? "selected=\"selected\"" : ''}>停用</option>
				    		</c:if>
			    		</c:if>
			    	</select>
		          </td>
		          <td width="14%" align="right">身份证号：</td>
		          <td width="34%" align="left">
					<s:textfield theme="simple" cssClass="input_width2" name="empl.emplIdCard" id="usfzh" onkeyup="value=value.replace(/[^\d]/g,'')"></s:textfield>
		          </td>
		        </tr>
		        <tr>
		          <td align="center" colspan="4">
		          	<input  type="submit" value="修改" id="addInfo">
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