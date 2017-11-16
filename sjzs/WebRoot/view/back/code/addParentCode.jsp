<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
    <title>父字典添加</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="css/validate.css" type="text/css"></link>
	<script type="text/javascript" src="js/dataCheck.js"></script>
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="js/validate/jquery.validate.js"></script>
  	<script type="text/javascript" src="js/validate/messages_cn.js"></script>
	<script type='text/javascript' src='dwr/interface/role.js'></script>
  	<script language="javascript" src='js/showdialog.js'></script>
  	<script type='text/javascript' src='dwr/engine.js'></script>
	<script type="text/javascript">
		function checkCode(val){
			if(val != null){
				$.get("rcCode!checkRcCode.action?selectNameId="+val+"&nocache=" + new Date().getTime(),function(data){
					if(data !=0 ){
						var obj =$("#rc_no");
						alter("字典编码已经存在！");
						obj.foucs();
						return ;
					}
				},"text");
			}
		}

		$(function(){
			$("#form1").validate({
				rules:{
					"rcCode.rc_no":{
						required:true
					},
					"rcCode.rc_name":{
						required:true
					}
				},
				messages:{
					"rcCode.rc_no":{
						required:"必填"
					},
					"rcCode.rc_name":{
						required:"必填"
					}
				},
				errorElement:"em",
				success:function(label) {
					label.text(" ").addClass("success")
				}
			})
		});
	</script>
  </head>
<body>
	<div align="center">
	<form action="rcCode!doAddParentInfo.action" id="form1" name="form1" method="post" >
	  	<input type="hidden" value="新增字典" name="closePage"/>
		<input type="hidden" value="数据字典" name="refreshPage"/>
	  <table width="99%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC">
	    <tr class="title_style">
	      <td colspan="8" style="text-align:left">
	      	<img src="images/group1.png" width="20" height="20" align="absmiddle" /> 添加父级字典
	      </td>
	    </tr>
	    <tr class="row_bg">
	      <td colspan="8">
	      	<table width="99%" border="0" cellspacing="0" cellpadding="0" class="nonborder2">
	      		<tr bgcolor="#F7FCFF">
		          <td width="11%" align="right">字典编码：</td>
		          <td width="39%" align="left">
					<input type="text" name="rcCode.rc_no" id="rc_no" onchange="checkCode(this.value)" class="input_width"/>
		          </td>
		        </tr>
		        <tr bgcolor="#F7FCFF">
		          <td width="11%" align="right">字典名称：</td>
		          <td width="39%" align="left">
					<input id="rname" type="text" name="rcCode.rc_name"	class="input_width"/>
		          </td>
		        </tr>
				
				 <tr bgcolor="#F7FCFF">
		        	<td align="right">字典类型：</td>
		        	<td align="left">
		        	<input type="radio" name="rcCode.rc_type" value="0" checked=checked/>手动输入
		        	<input type="radio" name="rcCode.rc_type" value="1" />sql查询
		        	</td>
		        </tr>
				
		        <tr bgcolor="#F7FCFF">
		        	<td align="right">字典排序：</td>
		        	<td align="left">
		        	<input type="text" name="rcCode.rc_order"  class="input_width" />
		        		
		        	</td>
		        </tr>

		        <tr bgcolor="#F7FCFF">
		          <td align="right" valign="top">字典说明：</td>
		          <td align="left">
					<textarea rows="5" cols="30" id="rcCode.rc_desc" name="rcCode.rc_desc"></textarea>
		          </td>
		        </tr>
		        
		        <tr bgcolor="#F7FCFF">
	        		<td colspan="4" bgcolor="#FFFFFF">
						<input type="submit" id="addInfo" value="提  交"/>
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


