<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/view/common/common.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>" />
		<title>河南中审科技有限公司－审计助手管理系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<script src="js/AC_RunActiveContent.js" type="text/javascript"></script>
		<link href="css/login.css" type="text/css" rel="stylesheet"/>
		<style type="text/css">
		
body {margin: 0;padding: 0;line-height: 1.5em;font-family: Georgia, "Times New Roman", Times, serif;font-size: 12px;color: #33322e;background: #39443D url(images/templatemo_body_bg.jpg) repeat-x;
/*	background: #47443c url(images/templatemo_body_bg_2.jpg) repeat-x;	*/
}
		
		*{
		margin:0;
		padding:0;
		}
		body{
		background:url(images/back022.png) repeat-x ;
		}
		</style>	
		<script language="javascript">
			function indexPage() {
				var istop = '${param.top}';
				if ('1' == istop) {
					alert("您尚未登陆或登陆超时请重新登陆！");
					window.top.location = "<%=basePath%>system/index.action";
				} else if ('2' == istop) {
					alert("您的账号已经在其它地方登陆，您已经被退出系统！");
					window.top.location = "<%=basePath%>system/index.action";
				} else if ('3' == istop) {
					alert("您的账号已经在其它地方登陆，您已经被退出系统！");
					window.top.location = "<%=basePath%>system/index.action";
				}
			}
       		indexPage();

       </script>
	</head>
    <body onload="javascript:document.getElementById('textfield').focus()" >
	<table width="100%" border="0" cellspacing="0" cellpadding="0" style="vertical-align: middle;">
	  <tr>
	    <td height="730" align="center" valign="middle">
	    <table width="1000" height="700" border="0" cellspacing="0" cellpadding="0">
	    
	      <tr>
	        <td valign="top" style="background:url(images/back03.png) no-repeat;"><table width="100%" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            <td width="2%" >&nbsp;</td>
	            <td width="62%" height="340px">&nbsp;</td>
	            <td width="34%">&nbsp;</td>
	            <td width="2%">&nbsp;</td>
	          </tr>
	          <tr>
	            <td width="2%" height="260">&nbsp;</td>
	            <td align="center" valign="middle">
	  <font style="font-size: 30px;font-style: italic;">&nbsp;   </font>
	            	<script type="text/javascript">
						//AC_FL_RunContent( 'codebase','http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,28,0','width','584','height','242','src','images/logoinadv','quality','high','pluginspage','http://www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash','wmode','transparent','movie','images/logoinadv' ); //end AC code
					</script>
					<noscript><%--
						<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,28,0" width="584" height="242">
	              			<param name="movie" value="images/logoinadv.swf" />
	              			<param name="quality" value="high" />
	              			<param name="wmode" value="transparent" />
	              			<embed src="images/logoinadv.swf" width="584" height="242" quality="high" pluginspage="http://www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash" type="application/x-shockwave-flash" wmode="transparent"></embed>
	            		</object>
					--%></noscript>
				</td>
	            <td>
		            <table width="100%" border="0" cellspacing="0" cellpadding="0">
		              <tr>
		                <td width="7%" height="72">&nbsp;</td>
		                <td width="81%">&nbsp;</td>
		                <td width="12%">&nbsp;</td>
		              </tr>
		              <tr>
		                <td height="159">&nbsp;</td>
		                <td valign="top">
		                <form name=form1 action="system/login.action" method="post" onsubmit="return form1_onsubmit()">
		                <table width="100%" border="0" cellspacing="0" cellpadding="0">
		                    <tr>
		                      <td width="23%" height="50" align="right" class="txt_14bold">用户名：</td>
		                      <td width="77%" align="left">
		                      	<input name="empl.emplAccount" type="text" class="login_input" id="textfield" value="hnzsAdmin"/>
		                      </td>
		                    </tr>
		                    <tr>
		                      <td width="23%" height="50" align="right" class="txt_14bold">密    码：</td>
		                      <td align="left">
		                      	<input name="empl.emplPassword" type="password" class="login_input" id="textfield2" value="hnzskj"/>
		                      </td>
		                    </tr>
		                    <tr>
		                      <td height="20" colspan="2" align="center">&nbsp;</td>
		                    </tr>
		                    <tr>
		                      <td height="30" colspan="2" align="left">
		                      	<input name="button" type="submit" class="login_submit" id="button"  value="" style="cursor:pointer"/>
		                      </td>
		                    </tr>
		                </table>
		                </form>
		                </td>
		                <td>&nbsp;</td>
		              </tr>
		              <tr>
		                <td>&nbsp;</td>
		                <td>&nbsp;</td>
		                <td>&nbsp;</td>
		              </tr>
		            </table>
	            </td>
	            <td>&nbsp;</td>
	          </tr>
	          <tr>
	            <td width="2%">&nbsp;</td>
	            <td>&nbsp;</td>
	            <td>&nbsp;</td>
	            <td>&nbsp;</td>
	          </tr>
	        </table></td>
	      </tr>
	    </table>
	      <table width="100%" border="0" cellspacing="0" cellpadding="0">
	        <tr>
	          <td height="40" align="center" style="font-size:12px; color:#333;">版权所有：河南中审科技有限公司 2013-2014 </td>
	        </tr>
	      </table></td>
	  </tr>
	</table>
	<script language="javascript">
		<!--
		function form1_onsubmit() {
			errmsg = "";
			if (document.getElementById("textfield").value=="") {
				errmsg += "请输入用户名！\n";
			}
			if (document.getElementById("textfield2").value=="") {
				errmsg += "请输入密码！\n";
			}
			if (errmsg!="")	{
				alert(errmsg);
				return false;
			}
		}

		//-->
	</script><%--
	<object id=WebOffice1 style="LEFT: 0px; TOP: 0px; WIDTH: 0px; HEIGHT: 0px;"
			classid="clsid:E77E049B-23FC-4DB8-B756-60529A35FAD5" codebase='plugins/weboffice_v6.0.5.0.cab#Version=6,0,5,0'>
			<param name="_ExtentX" value="6350">
			<param name="_ExtentY" value="6350">
			<param name="BorderColor" value="-2147483632">
			<param name="BackColor" value="-2147483643">
			<param name="ForeColor" value="-2147483640">
			<param name="TitlebarColor" value="-2147483635">
			<param name="TitlebarTextColor" value="-2147483634">
			<param name="BorderStyle" value="1">
			<param name="Titlebar" value="1">
			<param name="Toolbars" value="1">
			<param name="Menubar" value="1">
		</object>
--%></body>
</html>
