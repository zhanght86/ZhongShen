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
    <title>河南中审科技有限公司办公自动化系统</title>
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
		var _menus = {"menus":[
	{"menuid":"C1DAA696-A371-482B-9DB6-1FAD3A542408","icon":"icon-sys","menuname":"审计助手管理",
		"menus":[
   		{"menuid":"1","menuname":"审计法规","icon":"icon-nav","url":"sjzs!showSJFG.action"},
		{"menuid":"2","menuname":"定型依据","icon":"icon-nav","url":"sjzs!showDXYJ.action"},
		{"menuid":"3","menuname":"方法案例","icon":"icon-nav","url":"sjzs!showFFAL.action"},
		{"menuid":"4","menuname":"审计导航","icon":"icon-nav","url":"sjzs!showSJDH.action"},
		{"menuid":"5","menuname":"导出功能","icon":"icon-nav","url":"sjzs!showExport.action"}
	]
}
]};


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
    <div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
        <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
			<div title="欢迎使用" id="mainContext" style="padding:20px;overflow:hidden; color:red; " >
				<!-- 页面主体部分 -->
			    <jsp:include page="/WEB-INF/view/sjzs/welcome.jsp"></jsp:include>
			  
			</div>
		</div>
    </div>
</body>

</html>