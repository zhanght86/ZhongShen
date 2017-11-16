<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.hnzskj.persist.bean.system.Role" %>
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
	<link rel="stylesheet" type="text/css" href="js/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="js/themes/icon.css" />
    <script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="js/jquery.easyui.min.1.2.2.js"></script>
	<script type="text/javascript">
		function returnDateVal() {
			parent.window.returnValue = getcheckbox("ids");
			window.close();
		}

		//获得所有别选择复选框的value值		
		function getcheckbox(checkboxname){
			var valueArr = new Array();
			var checkboxboxs = document.getElementsByName(checkboxname);
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
			var checkboxboxs = document.getElementsByName(checkboxname);
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

		function getRolePowers(selectRoleCode) {
			var goURI = "system/system/role!showRolePowers.action?role.roleId=" + selectRoleCode;
			$("iframe[id='powerTree']").attr("src",goURI);
			$("#showPowers").show();
			$('#showPowers').dialog({
					title:'当前角色拥有的权限',
					resizable:true,
					maximizable:true
					});
		}
	</script>
  </head>
  <body>
  	<div align="center">
  	<form action="system/empl!addRole.action" method="post">
  	<input type="hidden" value="${empl.emplId }" name="empl.emplId">
  	<input type="hidden" value="${empl.orgId}" name="empl.orgId">
  	<input type="hidden" value="分配角色" name="closePage"/>
	<input type="hidden" value="员工管理" name="refreshPage"/>
	  <table width="99%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC" style="margin-left: 0px;">
	    <tr class="title_style">
	      <td colspan="8" style="text-align:left">
	      	<img src="images/group1.png" width="20" height="20" align="absmiddle" />请选择员工的角色
	      	<font color="red">(点击角色名称可查看其权限信息)</font>
	      </td>
	    </tr>
	    <tr class="row_bg">
	      <td colspan="8">
	      <!-- 列出系统所拥有的全部角色信息，并将当前用户拥有的角色勾选显示，“rcodes”用户已经拥有的角色的代码 -->
	      <!-- 每行显示三个角色信息，不足三个的显示为空白的单元格 -->
	      	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC">
			<%
				List<Role> roles = (List<Role>) request.getAttribute("roleList");
				String rcodes = (String) request.getAttribute("rcodes");
				for (int i = 0; i < roles.size();) {
					if (i % 2 == 0) {
						out.print("<tr>");
					} else {
						out.print("<tr class=\"row_bg\">");
					}
					for (int j = 0; j < 3; j++) {
						if (i < roles.size()) {
							out.print("<td>");
							if (rcodes.contains(roles.get(i).getRoleId())) {
			%>
								<input type="checkbox" checked="checked" value="<%=roles.get(i).getRoleId() %>" 
								name="empl.rolesCode" id='<%=roles.get(i).getRoleName() %>' />
								<label for="ids"  title="查看角色权限信息" style="cursor: pointer;" 
									onclick="getRolePowers('<%=roles.get(i).getRoleId() %>')">
									<%=roles.get(i).getRoleName() %>
								</label>
			<%
							} else {
			%>
								<input type="checkbox" value="<%=roles.get(i).getRoleId() %>" name="empl.rolesCode" id='<%=roles.get(i).getRoleName() %>'/>
								<label for="ids" title="查看角色权限信息" style="cursor: pointer;" 
									onclick="getRolePowers('<%=roles.get(i).getRoleId() %>')">
									<%=roles.get(i).getRoleName() %>
								</label>
			<%								
							}
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
  				<td align="center">
  					<span style="width: 40%;text-align: left;float: left">
  						<a href="javascript:changeStatus('0','empl.rolesCode');" style="margin-left: 10px;">全选</a>
		  				<a href="javascript:changeStatus('1','empl.rolesCode');" style="margin-left: 20px;">反选</a>
		  			</span>
		  		</td>
		  		<td colspan="2">
  					<input type="submit" value="确定授权" onclick="returnDateVal();"/>
  				</td>
  			</tr>
	      	</table>
	     </td>
	    </tr>
	  </table>
	</form>
	</div>
	<div id="showPowers" style="width: 400;height: 400px;display: none;">
		<iframe id="powerTree" width="100%" height="100%" frameborder="0"></iframe>
	</div>
  </body>
</html>