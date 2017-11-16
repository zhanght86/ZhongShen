<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hnzskj.persist.bean.system.Role"%>
<%@ include file="/view/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>选择角色页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript">
		function returnDateVal() {
			var myreturnVal = getcheckbox("ids");
			parent.window.returnValue = myreturnVal;
			window.close();
		}

		//获得所有别选择复选框的value值		
		function getcheckbox(checkboxname){
			var valueArr = new Array();
			var checkboxboxs = document.getElementsByName(checkboxname);//document.all.item(checkboxname);
			var checkboxValue = "";
			var checkboxAlt = "";
			if (checkboxboxs!=null) {
				if (checkboxboxs.length==null) {
					if (checkboxboxs.checked) {
						return checkboxboxs.value;
					}
				}
				for (i=0; i<checkboxboxs.length; i++) {
					if (checkboxboxs[i].type=="checkbox" && checkboxboxs[i].checked) {
						if (checkboxValue=='') {
							checkboxValue += checkboxboxs[i].value;
							checkboxAlt += checkboxboxs[i].id;
						} else {
							checkboxValue += ","+ checkboxboxs[i].value;
							checkboxAlt += "," + checkboxboxs[i].id;
						}
					}
				}
			}
			valueArr[0] = checkboxValue;
			valueArr[1] = checkboxAlt;
			return valueArr;
		}

		//改变复选框的状态
		//status 0 表示全选 ,1表示反选
		//checkboxname 要执行此操作的复选框的名称
		function changeStatus(status,checkboxname) {
			var checkboxboxs = document.getElementsByName(checkboxname); //document.all.item(checkboxname);
			if (checkboxboxs!=null) {
				if ( '0' == status ) {//如果是全选
					for (i=0; i<checkboxboxs.length; i++) {
						if (checkboxboxs[i].type=="checkbox" ) {
							checkboxboxs[i].checked = true;
						}
					}
				} else if ( '1' == status ) {//如果是反选
					for (i=0; i<checkboxboxs.length; i++) {
						if (checkboxboxs[i].type=="checkbox") {
							if (checkboxboxs[i].checked) {
								checkboxboxs[i].checked = false;
							} else{
								checkboxboxs[i].checked = true;
							}
						}
					}

				}
			}
		}
	</script>
  </head>
  <body bgcolor="#FFFFFF" style="text-align: center;">
  	  <table width="99%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC">
	    <tr class="title_style">
	      <td colspan="8" style="text-align:left">
	      	<img src="images/group1.png" width="20" height="20" align="absmiddle" />请选择角色
	      </td>
	    </tr>
	    <tr class="row_bg">
	      <td colspan="8">
  	<table width="97%" border="1" align="center" cellpadding="0" cellspacing="0" class="table">
  		<tbody>
  			<tr align="left">
  				<td align="left" colspan="3">请选择审批角色</td>
  			</tr>
  			<tr>
  				<%
				List<Role> roles = (List<Role>) request.getAttribute("roleList");
			//	String rcodes = (String) request.getAttribute("rcodes");
				for (int i = 0; i < roles.size();) {
					if (i % 2 == 0) {
						out.print("<tr>");
					} else {
						out.print("<tr class=\"row_bg\">");
					}
					for (int j = 0; j < 3; j++) {
						if (i < roles.size()) {
							out.print("<td>");
						
			%>

								<input type="checkbox" value="<%=roles.get(i).getRoleId() %>" name="ids" id='<%=roles.get(i).getRoleName() %>'/>
								<label for="ids" title="查看角色权限信息" style="cursor: pointer;" >
									<%=roles.get(i).getRoleName() %>
								</label>
			<%								
				
							out.print("</td>");
						} else {
							out.print("<td>&nbsp;</td>");
						}
						i++;
					}
					out.print("</tr>");
				}
			%>
			<tr>
  				<td colspan="3">
  					<span style="width: 40%;text-align: left;">
  						<a href="" onClick="changeStatus('0','ids');return false;" style="margin-left: 10px;">全选</a>
		  				<a href="" onClick="changeStatus('1','ids');return false;" style="margin-left: 20px;">反选</a>
		  			</span>
  					<input type="button" value="确定" onClick="returnDateVal();" style="margin-left: 20px;"/>
  				</td>
  			</tr>
  		</tbody>
  	</table>    
  	</td>
  	</tr>
  	</table>
  </body>
</html>