<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/view/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>审计之家---用户登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="css/validate.css" type="text/css"></link>
		<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="js/validate/jquery.js"></script>
		<script type="text/javascript" src="js/validate/jquery.validate.js"></script>
		<script type="text/javascript" src="js/validate/messages_cn.js"></script>
		<script type="text/javascript" src="js/validate/customValidate.js"></script>
		<script type="text/javascript" src="fckeditor/fckeditor.js"></script>
		<script type="text/javascript" src="js/validate/messages_cn.js"></script>
		<script type="text/javascript" src="js/validate/customValidate.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"
			charset="UTF-8"></script>
	
	<link rel="stylesheet" type="text/css" href="css/fore/style.css" ></link>
	
	<script type="text/javascript">
	$(function() {
		$("#loginEmplForm").validate( {
			rules : {				
				"employee.emplAccount" : {
					required : true
				},
				"employee.emplPassword" : {
					required : true
				},
				"yanzh":{
					required:true
				}
			},
			messages : {
				"employee.emplAccount" : {
					required : "*必填"
				},
				"employee.emplPassword" : {
					required : "*必填"
				},
				"yanzh":{
					required:"*必填"
				}
			},
			errorElement : "em",
			success : function(label) {
				label.text(" ").addClass("success")
				//$("all").window('close');
		}
		});
	});

	//验证码切换
	function cutImage(){
		$("#image").attr("src","reglogin!image.action?noche="+(new Date()));
	}

	function subName() {
		$.ajax( {
			type : "post", //请求方式
			//url : "reglogin!checkEmplName.action", 
			//发送请求地址
			url: "<%=basePath%>servlet/checkEmplNameServlet",
			data : { //发送给数据库的数据				
				emplName:$("#emplAccount").val(),
				type:"1"
			},
			//请求成功后的回调函数有两个参数
			success : function(data, textStatus) {
				$("#mess").html(data);
				if(data != ""){
					$("#emplAccount").val("");
				}		
			}
		});		
	}
	
	function subPass() {
		var param1=$("#emplAccount").val();
		var param2="#emplPassword";
		var mess="#mess";
		$.ajax( {
			type : "post", 
			url: "<%=basePath%>servlet/checkEmplPassServlet",
			data : { //发送给数据库的数据				
				param1: param1,
				param2: $(param2).val(),
				type: "1"
			},
			//请求成功后的回调函数有两个参数
			success : function(data, textStatus) {
				$(mess).html(data);
				if(data != ""){
					$("#pass").val("");
					$(param2).val("");
				}	
			}
		});		
	}
	function subYzm() {
		$.ajax( {
			type : "post", //请求方式
			//发送请求地址
			url: "<%=basePath%>servlet/checkYzmServlet",
			data : { //发送给数据库的数据				
				yanzh:$("#yanzh").val()
			},
			//请求成功后的回调函数有两个参数
			success : function(data, textStatus) {
				$("#mess").html(data);
				if(data != ""){
					$("#yanzh").val("");
					cutImage();
				}								
			}
		});		
	}
	function checkLogin(){
		$.ajax( {
			type : "post", //请求方式
			//发送请求地址
			url: "<%=basePath%>servlet/checkEmplLoginServlet",
			data : { //发送给数据库的数据	
				emplName:$("#emplAccount").val(),
				emplPass:$("#emplPassword").val(),		
				yanzh:$("#yanzh").val()
			},
			//请求成功后的回调函数有两个参数
			success : function(data, textStatus) {
				$("#mess").html(data);
				if(data != ""){
					$("#emplPassword").val("");
					$("#yanzh").val("");
					cutImage();
				}else{
					$("#loginEmplForm").submit();
					//location.reload("<%=basePath%>");
				}	
			}
		});
	}
	
	</script>	
  </head>
  
  <body>
   <div class="empl_login" style="margin-top:200px;">
   	 <form id="loginEmplForm" action="reglogin!loginEmpl.action" method="post">
	    	<table cellpadding="0" cellspacing="0" border="0">
	    		<tr>
	    			<th colspan="2" align="center" style="font-size:20px;">用户登录</th>
	    		</tr>
	    		<tr>
	    			<td class="left">用户名</td>
	    			<td class="right"><input type="text" name="employee.emplAccount" id="emplAccount" onblur="subName()"> </td> 
	    		</tr>
	    		<tr>
	    			<td class="left">密&nbsp;&nbsp;&nbsp;码</td>
	    			<td class="right"><input type="password" name="employee.emplPassword" id="emplPassword" onblur="subPass()"> </td> 
	    		</tr>
	    		<tr>
	    			<td class="left">验证码</td>
	    			<td class="yanzhen">
	    				<input type="text" name="yanzh" id="yanzh" maxlength="4" size="5" width="50px" style="margin-left:10px;float:left;"  onblur="subYzm()">
	    				 <img alt="验证码" src="reglogin!image.action" width="55px" style="float:left;margin-left:10px;" id="image" onclick="cutImage()" />
	    				<!-- <input type="image" src="reglogin!image.action" style="height:25px;width:60px; margin-left:5px;float:left;"> -->
	    			</td> 
	    		</tr>
	    		<tr>
	    			<td colspan="2" class="buttons">
	    				<input type="button" value="登录" width="100px" onclick="checkLogin()"> 					    				 
	    			</td> 
	    		</tr>
	    		<tr>
	    			<td colspan="2" height="30px">&nbsp;
	    				<div id="mess">
	    					${requestScope.errorMess }
	    				</div>
	    			</td>
	    		</tr>
	    	</table>
    	</form>
    </div>
  </body>
</html>
