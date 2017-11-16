<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/view/common/common.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>审计之家---用户注册</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" href="css/validate.css" type="text/css"></link>
		<script type="text/javascript" src="js/validate/jquery.js"></script>
		<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="js/validate/jquery.validate.js"></script>
		<script type="text/javascript" src="js/validate/messages_cn.js"></script>
		<script type="text/javascript" src="js/validate/customValidate.js"></script>
		<script type="text/javascript" src="fckeditor/fckeditor.js"></script>
		<script type="text/javascript" src="js/validate/messages_cn.js"></script>
		<script type="text/javascript" src="js/validate/customValidate.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"
			charset="UTF-8"></script>
			

		<link rel="stylesheet" type="text/css" href="css/fore/reg_style.css">
		<script type="text/javascript">
	$(function() {
		$("#regEmplForm").validate( {
			rules : {
				"employee.emplName" : {
					required : true
				},
				"employee.emplAccount" : {
					required : true
				},
				"employee.emplPassword" : {
					required : true
				},
				"rePassword" : {
					required : true
				}
			},
			messages : {
				"employee.emplName" : {
					required : "*必填"
				},
				"employee.emplAccount" : {
					required : "*必填"
				},
				"employee.emplPassword" : {
					required : "*必填"
				},
				"rePassword" : {
					required : "*必填"
				}
			},
			errorElement : "em",
			success : function(label) {
				label.text(" ").addClass("success")
				//$("all").window('close');
		}
		});
	});

	function sub() {
		$.ajax( {
			type : "post", //请求方式
			//url : "reglogin!checkEmplName.action", 
			//发送请求地址
			url: "<%=basePath%>servlet/checkEmplNameServlet",
			data : { //发送给数据库的数据
				
				emplName:$("#emplAccount").val(),
				type:"0"
			},
			//请求成功后的回调函数有两个参数
			success : function(data, textStatus) {
				$("#reg_mess").html(data);
				if(data != ""){
					$("#emplAccount").val("");
				}		
			}
		});
	}
	function checkPass(){
		var pass=$("#pass").val();
		var repass = $("#repass").val();
		if(pass == ""){
			$("#reg_mess").html("请输入密码！");
			$("#repass").val("");
		}else if(repass == ""){
			$("#reg_mess").html("请输入确认密码！");
		}else if(pass != repass){
			$("#reg_mess").html("两次密码输入不一致！");
			$("#repass").val("");
		}else{
			$("#reg_mess").html("");
		}	
	}
	function subYzm(type) {
		mess="#reg_mess";
		yanzh = "#reg_yanzh";
		$.ajax( {
			type : "post", //请求方式
			//发送请求地址
			url: "<%=basePath%>servlet/checkYzmServlet",
			data : { //发送给数据库的数据				
				yanzh:$(yanzh).val()
			},
			//请求成功后的回调函数有两个参数
			success : function(data, textStatus) {
				$(mess).html(data);
				if(data != ""){
					$(yanzh).val("");
					cutImage(type);
				}								
			}
		});		
	}
	function checkReg(){
		$.ajax( {
			type : "post", //请求方式
			//发送请求地址
			url: "<%=basePath%>servlet/checkEmplRegServlet",
			async:false,
			data : { //发送给数据库的数据	
				emplName:$("#emplAccount").val(),
				pass:$("#pass").val(),
				repass:$("#repass").val(),		
				yanzh:$("#reg_yanzh").val()
			},
			//请求成功后的回调函数有两个参数
			success : function(data, textStatus) {
				$("#reg_mess").html(data);
				if(data != ""){
					$("#pass").val("");
					$("#repass").val("");
					$("#reg_yanzh").val("");
					cutImage('0');
				}else{
					//setTimeout($("#regEmplForm").submit(),1000);
					$("#regEmplForm").submit();
				}
			}
		});				
	}
	//验证码切换
	function cutImage(type){
		var src="reglogin!image.action?noche="+(new Date());
		$("#reg_image").attr("src",src);
	}
	//userKeyId
	function checkUserKey(){
		$.ajax( {
			type : "post", //请求方式
			//发送请求地址
			url: "<%=basePath%>servlet/checkUserKeyServlet",
			data : { //发送给数据库的数据	
				userKeyId:$("#userKey").val()				
			},
			//请求成功后的回调函数有两个参数
			success : function(data, textStatus) {
				$("#reg_mess").html(data);
				if(data != ""){
					setTimeout(function(){location.href="<%=basePath%>"}, 1000 );
				}
			}
		});				
	}
</script>


	</head>

	<body>
		<div class="main" >
			<div style="margin-bottom:20px;margin-left:20px;font-size: 20px;color:#9b95c9;">
				${requestScope.showMessage}
			</div>
			<form action="reglogin!regEmpl.action" id="regEmplForm" method="post" >
				<input type="hidden" name="employee.emplId" value="${requestScope.emplId }">
				<input type="hidden" name="employee.userKey" id="userKey" value="${requestScope.userKeyId }"/>
				<table cellspacing="0" cellpadding="0" border="1" >
					<tr>
						<th colspan="2" class="title">
							<font style="margin-top: 5px;"> 审计之家用户注册 </font>
						</th>
					</tr>
					<tr>
						<td class="a">
							登录账号:
						</td>
						<td class="b">
							<input type="text" name="employee.emplAccount"  id="emplAccount" onblur="sub();">
							<br>
							<div>
								*登录系统用，请您记住！
							</div>
						</td>
					</tr>
					<tr>
						<td class="a">
							用户姓名:
						</td>
						<td class="b">
							<input type="text" name="employee.emplName" id="emplName">
							<br>
							<div>
								*您的真实姓名，不能修改！
							</div>
						</td>
					</tr>
					
					<tr>
						<td class="a">
							设置密码:
						</td>
						<td class="b">
							<input type="password" name="employee.emplPassword" id="pass">
						</td>
					</tr>
					<tr>
						<td class="a">
							重复密码:
						</td>
						<td class="b">
							<input type="password" name="rePassword" id="repass" onblur="checkPass()">
						</td>
					</tr>
					<tr>
						<td class="a">
							验证码:
						</td>
						<td>
							<input type="text" name="yanzh" id="reg_yanzh" maxlength="4" size="5"
								width="50px" style="margin-left: 5px;float:left;" onfocus="checkUserKey()" onblur="subYzm('0')">
							<img alt="验证码" src="reglogin!image.action" width="55px" style="float:left;margin-left:10px;" id="reg_image" onclick="cutImage('0')" />
						</td>
					</tr>
					<tr>
						<td colspan="2" class="c">
							<input type="button" value="注册" onclick="checkReg()" >
							&nbsp;&nbsp;&nbsp;
							<input type="reset" value="重置">							
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
	</body>
</html>
