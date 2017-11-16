<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.hnzskj.common.Constant"%>
<%@ include file="/view/common/common.jsp"%>
<%@ page import="com.hnzskj.common.Page" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>"/>
    <title>添加员工信息</title>
	
	
	<jsp:include page="/jsPage.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			$("#addForm").validate({
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
					"empl.emplqq":"qq",
					"orgName":{
						required:true
					}
				},
				messages:{
					"empl.emplName":{
						required:"请输入员工姓名"
					},
					"empl.emplAccount":{
						required:"请输入用户名"
					},
					"orgName":{
						required:"请选择所属部门"
					}
				},
				errorElement:"em",
				success:function(label) {
					label.text(" ").addClass("success");
				}
			});
		});	
	//	function selectOrg(){getDataDoC2("<%=basePath%>system/org!selectOrg.action?time="+new Date().getTime(),400,"orgId","orgName");var jgcode = $("#empl.ussjg").val();}
		function selectSF(){getDataDoC2("<%=basePath%>system/role!selectRole.action?time="+new Date().getTime(),400,"rcodes","rnames");}
		//验证用户账号是否重复
		function subName() {
			var param="#tuname";
			var mess="#mess";			
			$.ajax( {
				type : "post", 
				url: "<%=basePath%>servlet/checkEmplNameServlet",
				data : { //发送给数据库的数据				
					emplName:$(param).val(),
					type:0
				},
				//请求成功后的回调函数有两个参数
				success : function(data, textStatus) {
					$(mess).html(data);
					if(data != ""){
						$(param).val("");
					}		
				}
			});	
		}
	</script>
	
  </head>
<body>
<div class="pageContent">
	<form name="form1" action="system/empl!addEmpl.action" method="post" id="addForm">
	<input type="hidden" value="新增用户" name="closePage"/>
	<input type="hidden" value="用户管理" name="refreshPage"/>
	<input type="hidden" value="1" name="empl.emplStatus"/>
	<input type="hidden" value="0" name="empl.powerT"/>
	  <table width="99%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC">
	    <tr class="title_style">
	      <td colspan="8" style="text-align:left">
	      	<div class="formBar">
				 <img src="images/group1.png" width="20" height="20" align="absmiddle" />用户基本信息	
			</div>
	      </td>
	    </tr>
	    <tr class="row_bg">
	      <td colspan="8">
	      	<table width="99%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC" >
		        <tr>
		          <td width="11%" align="right">真实姓名：</td>
		          <td width="39%" align="left">
						<input name="empl.emplName" type="text" value="" id="uname" maxlength="20" />
						<span id="info1"><font color="red">*必填</font></span>
				  </td>
		          <td width="14%" align="right">性别：</td>
		          <td width="34%" align="left">
		          	<input type="radio" value="<%=Constant.MAN %>" name="empl.emplSex" id="usex0" checked="checked"/>男
                    <input type="radio" value="<%=Constant.WOMAN %>" name="empl.emplSex" id="usex1"/>女 
                  </td>
		        </tr>
		        <tr>
		          <td align="right">账号：</td>
		          <td align="left">
		          	<input name="empl.emplAccount" type="text" value=""  id="tuname"  maxlength="20" onblur="subName()"/>
		          	<span id="info4"><font color="red">*必填</font></span>
		          </td>
		          <td align="right">年龄：</td>
		          <td align="left">
						<input name="empl.emplAge" type="text" value="" id="uage" onKeyUp="value=value.replace(/[^\d]/g,'')"/>
				  </td>
		        </tr>
		     
		        <tr>
		          <td align="right">QQ：</td>
		          <td align="left">
					<input name="empl.emplqq" type="text" value="" onKeyUp="value=value.replace(/[^\d]/g,'')"/>		          </td>
		          <td align="right">职位：</td>
		          <td align="left">
						<input name="empl.emplPosition" type="text" value="" id="uposition" />
				  </td>
		        </tr>
		        <tr>
		        <td align="right">Email：</td>
		          <td align="left">
					<input name="empl.emplEmail" type="text"  value="" id="email" />
				  </td>
		          
		          <td align="right" >办公电话：</td>
		          <td align="left">
					<input name="empl.emplOfficeTel" type="text" value="" id="utelephone" />		          </td>
		        </tr>
		        <tr>
		          <td align="right">住址：</td>
		          <td align="left">
					<input name="empl.emplAddress" type="text" value=""  />		          </td>
		          <td align="right">手机号：</td>
		          <td align="left">
					<input name="empl.emplMobile" type="text" value="" id="ucellphone" onKeyUp="value=value.replace(/[^\d]/g,'')"/>
					<span id="infotell"></span>	
				</td>
		        </tr>
		       	
		       	
		       	<tr>
		        <td align="right">身份证号：</td>
		          <td align="left">
		          	<input name="empl.emplIdCard" type="text" value="" id="usfzh" onKeyUp="value=value.replace(/[^\d]/g,'')"/>
		          </td>
		          <td align="right">&nbsp;</td>
		          <td align="left">&nbsp;</td>
		        </tr>
		       	
		       	
		        <tr>
		          <td  colspan="4">
		          	<div class="formBar" align="center">
						<ul style="float:none;margin-left:400px; ">
							<li style="width:100px;"><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
							<li  style="width:100px;">
								<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
							</li>
						</ul>
					</div>
		          </td>
		        </tr>
	      </table>
	     </td>
	    </tr>
	    <tr>
	    	<td colspan="8" align="center">
	    		<div id="mess" style="width:200px;height:30px;line-height:30px;color:red;margin-top:10px;text-align: left;">
	    			${requestScope.errorMess }
	    		</div>
	    	</td>
	    </tr>
	  </table>
	  </form>
	</div>
</body>
</html>

