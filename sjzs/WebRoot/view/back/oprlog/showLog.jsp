<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/view/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>"/>
    <title>查看日志详细信息</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
  </head>
<body>
<div align="center">
	<table width="98%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC">
	    <tr class="title_style">
	      <td>
	      	<img src="images/group1.png" width="20" height="20" align="absmiddle" />日志详细信息
	      </td>
	    </tr>
		<tr>
			<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC">
					<tr>
						<td>
							操作人
						</td>
						<td align="left">
							${operationLog.operator }
						</td>
					</tr>
					<tr>
						<td>
							操作时间
						</td>
						<td align="left">
							${operationLog.oprtime }
						</td>
					</tr>
					<tr>
						<td>
							登陆IP
						</td>
						<td align="left">
							${operationLog.loginIp}
						</td>
					</tr>
					<tr>
						<td valign="top">
							操作内容
						</td>
						<td align="left">
							${operationLog.operation }
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="button" onclick="closeLabel();" class="button1" value=" 关闭  "/>
						</td>
					</tr>
				</table>
				<script type="text/javascript">
					function closeLabel() {
						self.parent.$('#tabs').tabs('select','系统日志');
						self.parent.$('#tabs').tabs('close','日志详细信息');
					}
				</script>
			</td>
		</tr>
	</table>
</div>
</body>
</html>