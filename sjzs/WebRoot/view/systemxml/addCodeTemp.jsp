<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <base   target="_self"   >
    <title>添加编码模板</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type='text/javascript' src='/htglxt/dwr/interface/systemXmlServiceJS.js'></script>
  	<script type='text/javascript' src='/htglxt/dwr/engine.js'></script>
  	<script type='text/javascript' src='/htglxt/dwr/util.js'></script>

	<script type="text/javascript">
	//验证当前输入的级别在系统中是否存在。
		function validate(){
			var name=document.getElementById("name")
			var grade=document.getElementById("grade");
			var code=document.getElementById("code");
			var chose=document.getElementById("chose").value;
			
			var value=name.value.replace(/^\s+|\s+$/g,"");
			if(value==""){
				alert("名称不能为空");
				name.focus();
				name.select();
				return;
			}			
			var codeValue=code.value.replace(/^\s+|\s+$/g,"");
			if(codeValue==""){
				alert("编码前缀不能为空");
				code.focus();
				code.select();
				return;
			}	
			var grades="${codeTemp.grade}";
			var gradeValue=grade.value.replace(/^\s+|\s+$/g,"");

			if(gradeValue==""){
				alert("级别不能为空");
				grade.focus();
				grade.select();
				return;
			}
			if(grades.indexOf(gradeValue)>-1){
				alert("您输入的级别在系统配置文件中已经存在，请重新输入");
				grade.focus();
				grade.select();
				return;
			}
			document.addForm.submit();
		}

		//验证当前系统是否存在次级别部门
		function validateGrade(){
			var grade=document.getElementById("grade");
			var gradeValue=grade.value.replace(/^\s+|\s+$/g,"");
			if(gradeValue!=""){
				systemXmlServiceJS.validateGrade(gradeValue,function(flag){
					if(!flag){
							alert("您输入的级别在系统组织机构中不存在，请重新输入");
							grade.focus();
							grade.select();
							return false;
						}
					});
			}
			return true;
		}

		//验证配置文件当前配置文件是否存在当前级别前缀。
		function validateSameGrade(){
			var grade=document.getElementById("grade");
			var gradeValue=grade.value;
			systemXmlServiceJS.validateGrade(gradeValue,function(flag){
				if(!flag){
						alert("您输入的级别在系统配置文件中已经存在，请重新输入");
						grade.focus();
						grade.select();
						return false;
					}
				});
			return true;
		}
		window.onload=function(){
				if("${msg}"!=""){
					window.returnValue=true;
					window.close();
				}
			}
	</script>
  </head>
  
  <body target="_self">
  <form action="systemXmlAction!addCodeTemp.action" method="post" name="addForm">
  	<input type="hidden" value="codeTemp" name="nodeName"/>
 	<table width="99%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td  align="left" class="non_topborder">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC">
			   	<tr class="title_style">
				   	<td align="left" colspan="5" style="text-align:left;padding-left:30px;">添加编码模板</td>	  
			   	</tr>
			   	 <tr class="row_bg">
				   	 	<td>
				   	 		名称:
				   	 	</td>
				   	 	<td>
						 	<input type="text" name="codeTemp.name" id="name"/>  	
				   	 	</td>
				 </tr>
				  <tr class="row_bg">
				   	 	<td>
				   	 		编码前缀:
				   	 	</td>
				   	 	<td>
						 	<input type="text" name="codeTemp.code" id="code"/>  	
				   	 	</td>
				 	</tr>
				 	  <tr class="row_bg">
				   	 	<td>
				   	 		级别:
				   	 	</td>
				   	 	<td>
						 	<input type="text" name="codeTemp.grade" id="grade" onblur="javascript:validateGrade();" onKeyUp="value=value.replace(/[^\d]/g,'')"/>  	
				   	 	</td>
				 	</tr>
				 	  <tr class="row_bg">
				   	 	<td>
				   	 		是否启用:
				   	 	</td>
				   	 	<td>
						 	<select name="codeTemp.chose" id="chose">
						 		<option value="1">启用</option>
						 		<option value="0">未启用</option>
						 	</select>	
				   	 	</td>
				 	</tr>
			   		<tr>
			   			<td colspan="2" align="center">
			   				<input type="button" value="保存" onclick="javascript:validate();"/>
			   				<input type="reset" value="重置"/>
			   			</td>
			   		</tr>
			</table>
		</td>
	</tr>
</table>
</form>
  </body>
</html>
