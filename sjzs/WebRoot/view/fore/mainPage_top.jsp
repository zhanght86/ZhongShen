<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.hnzskj.common.Constant" %>
<%@ include file="/view/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="js/validate/jquery.js"></script>
	<script type="text/javascript" src="js/validate/jquery.validate.js"></script>
	<script type="text/javascript" src="js/validate/messages_cn.js"></script>
	<script type="text/javascript" src="js/validate/customValidate.js"></script>
	<script type="text/javascript" src="js/validate/messages_cn.js"></script>
	<script type="text/javascript" src="js/validate/customValidate.js"></script>
	<script language="javascript" src="js/calendar/calendar.js"	charset="UTF-8"></script>
<script language="javascript" src="js/fore/tanchuang.js"></script>
<link rel="stylesheet" type="text/css" href="css/fore/tanchuang.css"/>
<script type="text/javascript">
	function checkType( searchType ){
		var sAction = "search!searchAll.action";
		var sForm = document.getElementById('searchForm');
		sForm.action = sAction;
		$("#searchType").attr("value",searchType);
		var g="#"+searchType;
		var style="color:black;font-size:18;text-decoration:none;"
		if($("#mainList")!=null){
			var listSrc = $("#mainList").attr("src");
			$("mainList").attr("src",listSrc+"&searchType="+searchType);
		}			
		$("a.a").each(function(){$(this).attr("style","color:blue;text-decoration:underline;");});
		$(g).attr("style",style);
		$("#reqPage").value=0;
	}
	
	function chushi(showType){
		var searchType="${searchType}"+"";
		var typeId="";
		var sAction="";
		if(searchType!="" && searchType !=null){
			typeId = $("#searchType").attr("value",searchType);
		}else{
			typeId = $("#searchType").attr("value","All");
			searchType="All";
		}
		sAction = "search!searchAll.action";
		$("#searchForm").attr("action",sAction);//action=sAction;
		var g="#"+searchType;
		var style="color:black;font-size:18;text-decoration:none;"
		$(g).attr("style",style);
		$("#searchText").focus();
		loadS(showType);
	}
</script>

<div class="top_user">
	<c:if test="${!empty employee}">
		<a href="reglogin!showHomePage.action"  target="_blank">${employee.emplName}</a>
		<a href="" onclick="showInfoS(this,'Wdzs')">个人中心</a>&nbsp;
		<a href="reglogin!exit.action">退出</a>
	</c:if>
	<c:if test="${empty employee}">
		<a href="javascript:void(0)"  onclick="showDiv('showbox1')">登录</a>
		<a href="javascript:void(0)"  onclick="showDiv('showbox2')">注册</a>
	</c:if>&nbsp;&nbsp;&nbsp;&nbsp;
</div>
<div class="top_search">
	<form  id="searchForm" name="searchForm" method="post" >
	<input type="hidden" id="searchType" name="searchType" value="<%=Constant.ALL %>">
	<input type="hidden" id="showType" name="showType" value="sy"/>

	<div class="s_logo">
		<img alt="审计之家" src="<%=basePath%>images/fore/logobitT.png">
	</div>
	<div class="s_search">
		<div class="s_each">
			<a class="a" href="javascript:void(0)" onclick="checkType('<%=Constant.ALL %>')" id="<%=Constant.ALL %>">全&nbsp;部</a>&nbsp;
			<a class="a" href="javascript:void(0)" onclick="checkType('<%=Constant.SJFG %>')" id="<%=Constant.SJFG %>">审计法规</a>
			<a class="a" href="javascript:void(0)" onclick="checkType('<%=Constant.DXYJ %>')" id="<%=Constant.DXYJ %>">定性依据</a>
			<a class="a" href="javascript:void(0)" onclick="checkType('<%=Constant.FFAL %>')" id="<%=Constant.FFAL %>">方法案例</a>
			<a class="a" href="javascript:void(0)" onclick="checkType('<%=Constant.SJDH %>')" id="<%=Constant.SJDH %>">审计导航</a>
			<a class="a" href="javascript:void(0)" onclick="checkType('<%=Constant.SJSX %>')" id="<%=Constant.SJSX %>">审计事项</a>
			<a class="a" href="javascript:void(0)" onclick="checkType('<%=Constant.SJSS %>')" id="<%=Constant.SJSS %>">实施方案</a>
		</div>
		<div class="s_text">
			<c:choose>
				<c:when test="${empty employee}">
					<input type="text" name="key" maxlength="100" id="searchText" value="用户登陆后可以搜索 " onclick="clearS(this)">
				</c:when>
				<c:otherwise>
					<input type="text" name="key" maxlength="100" id="searchText" >
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<div class="s_but">
		<c:choose>
			<c:when test="${empty employee}">
				<input type="submit" value="搜索一下" onclick="return Dwain()">
			</c:when>
			<c:otherwise>
				<input type="submit" value="搜索一下">
			</c:otherwise>
		</c:choose>
	</div>
	</form>
</div>
<div class="top_menu">
	<div style="width: 100%;height:8px;"><!-- 没什么大用 --></div>
	<div class="menu_each">
	<table cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td class="td_Sy"><a onclick="showInfoS(this,'Sy')" >首&nbsp;页</a></td>
			<%--
			<td class="td_sjfg"><a onclick="showInfoS(this,'Sjfg')" >审计法规</a></td>
			<td class="td_dxyj"><a onclick="showInfoS(this,'Dxyj')" >定性依据</a></td>
			<td class="td_ffal"><a onclick="showInfoS(this,'Ffal')" >方法案例</a></td>
			<td class="td_sjdh"><a onclick="showInfoS(this,'Sjdh')" >审计导航</a></td>
			<td class="td_sjsx"><a onclick="showInfoS(this,'Sjsx')" >审计事项</a></td>
			<td class="td_ssfa"><a onclick="showInfoS(this,'Ssfa')" >实施方案</a></td>
			 --%>
			<td class="td_sjfg"><a onclick="showClick(this,'sjfg')">审计法规</a></td>
			<td class="td_dxyj"><a onclick="showClick(this,'dxyj')">定性依据</a></td>
			<td class="td_ffal"><a onclick="showClick(this,'ffal')">审计方法</a></td>
			<td class="td_sjdh"><a onclick="showClick(this,'sjdh')">审计导航</a></td>
			<td class="td_sjsx"><a onclick="showClick(this,'sjsx')">审计事项</a></td>
			<td class="td_ssfa"><a onclick="showClick(this,'ssfa')">实施方案</a></td>
			<c:if test="${!empty employee}">	
				<td class="td_Wdzs"><a onclick="showInfoS(this,'Wdzs')" >我的助手</a></td>
			</c:if>
		</tr>
	</table>
	</div>
</div>
<div class="showbox1">
	<div class="mini_title">
		<h2>
			用户登录
			<a class="close" href="#" style="float:right;" onclick="closeT('showbox1')" >关闭</a>
		</h2>
	</div>
	<div class="mainlist">
		<div class="empl_login">
	   	 <form id="loginEmplForm" action="reglogin!loginEmpl.action" method="post" >
		    	<table cellpadding="0" cellspacing="0" border="0">
		    		<tr>
		    			<td class="left">用户名:</td>
		    			<td class="right"><input type="text" name="employee.emplAccount" id="emplAccount" onblur="subName('1')"> </td> 
		    		</tr>
		    		<tr>
		    			<td class="left">密&nbsp;&nbsp;&nbsp;码:</td>
		    			<td class="right"><input type="password" name="employee.emplPassword" id="emplPassword" > </td> 
		    		</tr>
		    		<tr>
		    			<td class="left">验证码:</td>
		    			<td class="yanzhen">
		    				<input type="text" name="yanzh" id="yanzh" maxlength="4" size="5" width="50px" style="margin-left:10px;float:left;"  onblur="subYzm('1')">
		    				 <img alt="验证码" src="reglogin!image.action" width="55px" style="float:left;margin-left:10px;" id="image" onclick="return cutImage('1')" />
		    				<!-- <input type="image" src="reglogin!image.action" style="height:25px;width:60px; margin-left:5px;float:left;"> -->
		    			</td> 
		    		</tr>
		    		<tr>
		    			<td colspan="2" class="buttons">
		    				<input type="button" id="loginSub" value="登录" width="100px" onclick="checkLogin()"> 					    				 
		    			</td> 
		    		</tr>
		    		<tr>
		    			<td colspan="2" height="30px">&nbsp;
		    				<div id="mess" style="color:red;">
		    					${requestScope.errorMess }
		    				</div>
		    			</td>
		    		</tr>
		    	</table>
	    	</form>
	    </div>
	</div>
</div>
	<div class="showbox2">
		<div class="mini_title">
			<h2>
				用户注册
				<a class="close" href="#" style="float:right;">关闭</a>
			</h2>
		</div>
		<div class="mainlist">
			<div class="reg_empl">
				<form action="reglogin!regEmpl.action" id="regEmplForm" method="post" >
				 <table cellspacing="0" cellpadding="0" border="0">					
					<tr>
						<td class="left">
							登录账号
						</td>
						<td class="right">
							<input type="text" name="employee.emplAccount" value="${employee.emplAccount}"  maxlength="20" id="reg_emplAccount" onblur="subName('0')">
						</td>
						<td class="reg_info">
							登录系统用，请您牢记！
						</td>
					</tr>
					<tr>
						<td class="left">
							用户姓名
						</td>
						<td class="right">
							<input type="text" name="employee.emplName" id="emplName" value="${employee.emplName }" maxlength="20">
						</td>
						<td class="reg_info">
							您的真实姓名，不能修改！
						</td>
					</tr>
					
					<tr>
						<td class="left">
							设置密码
						</td>
						<td class="right">
							<input type="password" name="employee.emplPassword" id="pass">
						</td>
						<td class="reg_info">
							请输入密码！
						</td>
					</tr>
					<tr>
						<td class="left">
							重复密码
						</td>
						<td class="right">
							<input type="password" name="rePassword" id="repass" onblur="subPass('0')">
						</td>
						<td class="reg_info">
							请再次输入密码！
						</td>
					</tr>
					<tr>
						<td class="left">
							验证码
						</td>
						<td>
							<input type="text" id="reg_yanzh" maxlength="4" size="5"
								width="50px" style="margin-left: 5px;float:left;" onblur="subYzm('0')">
							<img alt="验证码" src="reglogin!image.action" width="55px" style="float:left;margin-left:10px;" id="reg_image" onclick="cutImage('0')" />
						</td>
						<td>
							<a href="" onclick="return cutImage('0')" style="color:#216fb7;">看不清？换一张</a>
						</td>
					</tr>
					<tr>
						<td colspan="3" class="button">
							<input type="button" id="regSub" value="注册" onclick="checkReg()" >							
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							&nbsp;
							<div id="reg_mess" style="color:red;">
								${requestScope.errorMess }
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>
	<!-- 遮挡 -->
<div id="fade" class="black_overlay"></div>