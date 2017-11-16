<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.0 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>U盘首页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
		body,html{
			padding:0;
			margin:0;
		}
		.u_all{
			width:100%;
			height:650px;
			margin:0 auto;
			background: url("image/back.png")  repeat-x;
		}
		.u_all .u_main{
			width:800px;
			height:650px;
			margin:0 auto;
			background: url("image/back_01.png") no-repeat;
		}
		.u_main img{
			border: none;
		}
		.u_main .main_left .span{
			width: 100%;
			height:65px;
		}
		.u_main .main_left{
			margin-left:50px;
			width: 275px;
			float: left;
		}
		.u_main .main_left a{
			float: left;
			margin-top:40px;
		}
		.main_left .sjsx{
			margin-left:120px;
		}
		.main_left .ssfa{
			margin-left:65px;
		}
		.main_left .sjfg{
			margin-left:40px;
		}
		.main_left .dxyj{
			margin-left:30px;
		}
		.main_left .ffal{
			margin-left:65px;
		}
		.main_left .sjdh{
			margin-left:120px;
		}
		.main_right{
			width:400px;
			text-align:left;
			float: left;
		}
		.main_right .span{
			width:100%;
			height:240px;
		}
	</style>
	<script type="text/javascript">
		function onloadPage(){
			document.form1.submit();
		}
	</script>
  </head>
  
  <body>
    <div class="u_all">    	
    	<div class="u_main">
    		<div class="main_left">
    		<div class="span"><!-- 支撑作用 --></div>
    			<a href="javascript:self.parent.parent.addTab('审计事项','system/sjzs!showSJSX.action')" class="sjsx" hideFocus="true"><img src="image/sjsx.png" alt="审计事项"/></a>
    			<a href="javascript:self.parent.parent.addTab('实施方案','system/sjzs!showSSFA.action')" class="ssfa" hideFocus="true"><img src="image/ssfa.png" alt="实施方案"/></a>
    			<a href="javascript:self.parent.parent.addTab('审计法规','system/sjzs!showSJFG.action')" class="sjfg" hideFocus="true"><img src="image/sjfg.png" alt="审计法规"/></a>
    			<a href="javascript:self.parent.parent.addTab('定性依据','system/sjzs!showDXYJ.action')" class="dxyj" hideFocus="true"><img src="image/dxyj.png" alt="定性依据"/></a>
    			<a href="javascript:self.parent.parent.addTab('方法案例','system/sjzs!showFFAL.action')" class="ffal" hideFocus="true"><img src="image/ffal.png" alt="方法案例"/></a>
    			<a href="javascript:self.parent.parent.addTab('审计导航','system/sjzs!showSJDH.action')" class="sjdh" hideFocus="true"><img src="image/sjdh.png" alt="审计导航"/></a>
    		</div>
    		<div class="main_right">
    			<div class="span"><!-- 支撑作用 --></div>
    				<img alt="审计之家" src="image/sjzs.png">
    			<div style="display: none;">
    				 <form name=form1 action="system/login.action" method="post" onsubmit="return form1_onsubmit()" style="display:none;">
		                <table width="100%" border="0" cellspacing="0" cellpadding="0">
		                    <tr>
		                      <td width="23%" height="40" align="right" class="txt_14bold">用户名：</td>
		                      <td width="77%" align="left">
		                      	<input name="empl.emplAccount" type="text" class="login_input" id="textfield" value="hnzsAdmin"/>
		                      </td>
		                    </tr>
		                    <tr>
		                      <td width="23%" height="40" align="right" class="txt_14bold">密    码：</td>
		                      <td align="left">
		                      	<input name="empl.emplPassword" type="password" class="login_input" id="textfield2" value="hnzskj"/>
		                      </td>
		                    </tr>
		                    <tr>
		                      <td height="15" colspan="2" align="center">&nbsp;</td>
		                    </tr>
		                    <tr>
		                      <td height="15" colspan="2" align="left">
		                      	<input name="button" type="submit" class="login_submit" id="button"  value="" style="cursor:pointer"/>
		                      </td>
		                    </tr>
		                </table>
		              </form>		
    			</div>
    		</div>
    	</div>
    </div>
  </body>
</html>
