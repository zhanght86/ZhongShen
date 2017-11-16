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
    <title>查看短信详细信息</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
  </head>
<body>
<div align="center">
	<table width="98%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC">
	    <tr class="title_style" align="left">
	      <td align="left">
	      	<img src="images/group1.png" width="20" height="20" align="absmiddle" />短信详细信息
	      </td>
	    </tr>
		<tr>
			<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC">
					<tr>
						<td>
							接收人
						</td>
						<td align="left">
							${fu:getEmplNameById(sms.uid) }
						</td>
					</tr>
					<tr>
						<td>
							手机号
						</td>
						<td align="left">
							${sms.ucellphone }
						</td>
					</tr>
					<tr>
						<td>
							发送时间
						</td>
						<td align="left">
							${sms.ssendTime }
						</td>
					</tr>
					<tr>
						<td>
							发送状态
						</td>
						<td align="left">
							<s:if test="sms.success==true">
								发送成功
							</s:if>
							<s:else>
								发送失败
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
							短信类别
						</td>
						<td align="left">
							${fu:getTypeNameByCode(sms.stype)}
						</td>
					</tr>
					<tr>
						<td valign="top">
							短信内容
						</td>
						<td align="left">
							${sms.scontent }
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
						self.parent.$('#tabs').tabs('select','短信日志');
						self.parent.$('#tabs').tabs('close','短信详细信息');
					}
				</script>
			</td>
		</tr>
	</table>
</div>
</body>
</html>