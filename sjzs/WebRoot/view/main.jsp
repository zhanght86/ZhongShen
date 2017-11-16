<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/view/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>"/>
    <title>河南中审科技有限公司审计助手后台管理系统</title>
	<link href="css/style.css" type="text/css" rel="stylesheet"/>
    <link href="css/default.css" rel="stylesheet" type="text/css" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="js/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="js/themes/icon.css" />
    <script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="js/jquery.easyui.min.1.2.2.js"></script>
	<script type="text/javascript" src='js/outlook2.js'></script>
	<script type="text/javascript" src='js/locale/easyui-lang-zh_CN.js'></script>
    <script src="js/AC_RunActiveContent.js" type="text/javascript"></script>

    <script type="text/javascript">
    baseURL = "<%=basePath%>";
	//用于记录生成的iTimerID 
	var timeoutId = 0;
	//刷新的标签页
	var refreshPage;
	//关闭标签页
	var closePage;
	//关闭和刷新指定的标签页
	function closewindow(){
		//如果刷新的页面存在
		if ($('#tabs').tabs('exists',refreshPage)) {
			//刷新的标签页
			var refpage = document.getElementById(refreshPage);
			if (null != refpage) {
				//刷新当前的iframe
				//refpage.contentWindow.location.reload(true);
				refpage.contentWindow.document.forms["queryForm"].submit();
			}
			//选中要刷新的页面
			$('#tabs').tabs('select',refreshPage);
		}
		//关闭标签的页面
		$('#tabs').tabs('close',closePage);
	}
	var _menus = ${menuStr};
    	function changeWidth() {
			$.get("system/login!updateIndexPage.action?nocache=" + new Date().getTime(),function(data){
					$("#mainContext").html(data);
				},"html");
		}

		//当用户关闭浏览器时，销毁当前请求的session
		function destorySession() {
			$.get('system/login!loginout.action');
		}

    </script>

</head>
<body class="easyui-layout" style="overflow-y: hidden"  scroll="no" onunload="destorySession();">
	<noscript>
	<div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
	    <img src="images/noscript.gif" alt='抱歉，请开启脚本支持！' />
	</div>
	</noscript>
	<!-- 顶部信息模块 -->
    <div region="north" split="true" border="false" style="overflow: hidden; height: 57px;
        background: url(images/c_topbg.jpg) repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
        <span style="float:right; padding-right:20px; padding-top:20px;" class="head">
        <img src="images/tadmin.gif" width="16" height="16" align="absmiddle" />欢迎您：${sessionScope.loginEmpl.emplName }&nbsp; 
        <!-- 
        <img src="images/email.gif" width="16" height="16" align="absmiddle" />
        <a href="javascript:addTab('收件箱','message!getMessContentList.action')" id="editpass">有<span id="messcount">0</span>未读消息</a>&nbsp; &nbsp;
         -->
        <img src="images/lock.gif" width="16" height="16" align="absmiddle" />
        <a href="system/login!loginout.action" id="loginOut">安全退出</a></span>
        <span style="padding-left:3px;font-size: 16px;color:#000; ">
  <br/>   
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <font style="font-size: 30px;font-weight: bolder;"> 欢迎使用审计助手管理系统   </font>
        <%--
        <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" width="500" height="50">
	        <param name="movie" value="images/logo.swf" />
	        <param name="quality" value="high" />
	        <param name="wmode" value="transparent" />
	        <embed src="images/logo.swf" width="500" height="50" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" wmode="transparent"></embed>
      	</object>
      --%></span>
    </div>
    <div region="south" split="true" style="height: 29px; background:url(images/bottombg.jpg) repeat-x; ">
        <div class="footer">版权所有：河南中审科技有限公司</div>
    </div>
    <div region="west" split="true" title="导航菜单" style="width:175px;" id="west">
		<div id="nav" class="easyui-accordion" fit="true" border="false">
			<!--  导航内容模块 -->
		</div>
    </div>
    <div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
        <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
			<div title="欢迎使用" id="mainContext" style="padding:20px;overflow:hidden; color:red; background:url('<%=basePath %>images/welcomeMain.png') no-repeat;" >
			</div>
		</div>
    </div>
    <!-- 在标签页的标签题上单击右键时出现的操作菜单 -->
	<div id="mm" class="easyui-menu" style="width:150px;z-index: 100" >
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
	</div>
</body>

</html>