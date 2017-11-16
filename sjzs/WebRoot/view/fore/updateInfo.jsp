<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.hnzskj.common.Constant"%>
<%@ include file="/view/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.0 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>个人信息--编辑个人资料</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="js/treeView/scripts/jquery.min.js" type="text/javascript"></script>
	<script type='text/javascript' src='js/treeView/scripts/jquery.contextmenu.r2-min.js'></script>
	<script src="js/treeView/scripts/jquery.treeview.js" type="text/javascript"></script>
	<link rel="stylesheet" href="js/treeView/css/jquery.treeview.css" />
	<link rel="stylesheet" type="text/css" href="js/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="js/themes/icon.css"/>
	<script type="text/javascript" src="js/jquery.easyui.min.1.2.2.js"></script>
	<script type="text/javascript" src='js/locale/easyui-lang-zh_CN.js'></script>
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

	<link rel="stylesheet" type="text/css" href="css/fore/profile.css">
	<script type="text/javascript">	
		
		//验证码切换
		function cutImage(){
			var src="reglogin!image.action?noche="+(new Date());
				$("#image").attr("src",src);

		}
		function checkUpdateInfo(){
			var age = ($("input[name='employee.emplAge']").val());
			var idCard=$("input[name='employee.emplIdCard']").val();
			var mobile = $("input[name='employee.emplMobile']").val();
			var email = $("input[name='employee.emplEmail']").val();
			if(age !=null && age !=""){
				if(age <18 || age >100){
					alert("年龄输入不合法！（18-100）");
					$("#menu_base").click();
					return false;
				}
			}
			if(idCard != null && idCard !=""){
				var reg =/(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
				if(reg.test(idCard)===false){
					alert("身份证输入不合法！");
					$("#menu_more").click();
					return false;
				}
			}
			if(mobile != null && mobile != ""){
				var reg=/(^\d{11}$)/;
				if(reg.test(mobile)==false){
					alert("手机号输入不合法！");
					$("#menu_more").click();
					return false;
				}
			}
			if(email != null && email != ""){
				var reg=/^[_a-z0-9]+@([_a-z0-9]+\.)+[a-z0-9]{2,3}$/;
				if(reg.test(email)==false){
					alert("邮箱输入不合法！");
					$("#menu_more").click();
					return false;
				}
			}
				return true;
		}
		function subYzm() {
			var mess = "#mess";
			var yanzh = "#yanzh";
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
					}								
				}
			});	
		}
		function hidN(){
			$("#baseTitle").attr("style","font-width:bold;");
			$("#more").attr("style","display:none;");
			$("#base").attr("style","display:block");
			$("#main_content").slideDown(1000);
			$("#menu_base").attr("style","color:black;");
			var type=(${parameters.type[0]});
			if(type=="3"){
				$("#menu_pass").click();
			}
		}
		function swit(types){
			if(types=="1"){
				$("#update_pass").slideUp(1500);
				$("#update_info").slideDown(1500);
				$("#more").slideUp(1500);
				$("#base").slideDown(1500);
				$("#menu_base").attr("style","color:black;");
				$("#menu_more").attr("style","color:#666666;");
				$("#menu_pass").attr("style","color:#666666;");
			}else if(types == "0"){
				$("#update_pass").slideUp(1500);
				$("#update_info").slideDown(1500);
				$("#more").slideDown(1500);
				$("#base").slideUp(1500);
				$("#menu_base").attr("style","color:#666666;");
				$("#menu_more").attr("style","color:black");
				$("#menu_pass").attr("style","color:#666666;");
			}else if(types == "2"){
				$("#menu_base").attr("style","color:#666666;");
				$("#menu_more").attr("style","color:#666666;");
				$("#menu_pass").attr("style","color:black;");
				$("#update_info").slideUp(1500);
				$("#update_pass").slideDown(1500);
			}
		}
		function subForm(){
			if(checkUpdateInfo()){
				$("#updateInfoForm").submit();
			}			
		}
		function subUpPass(){
			$("#updatePassForm").submit();
		}
		//检测修改密码用
		$(function() {
			$("#updatePassForm").validate( {
				rules : {				
					"oldPass" : {
						required : true
					},
					"newPass" : {
						required : true
					},
					"reNewPass" : {
						required : true
					},
					"yanzh":{
						required:true
					}
				},
				messages : {
					"oldPass" : {
						required : "*必填"
					},
					"newPass" : {
						required : "*必填"
					},
					"reNewPass" : {
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
	</script>
  </head>
  
  <body onload="hidN()">
    <div class="homepage">
    	<div class="top_top">
    		<div class="title">
    			<img alt="审计之家" src="/sjzs/images/fore/logobit.png" style="display: inline;float: left;border: 0;height:40px;">
    			<a href="<%=basePath%>" style="margin-right:35px;">审计之家首页</a>
    		</div>
    	</div>
    	<div class="main" id="main_content" style="display:none;">
    		<div class="title">
    			<h1>编辑个人资料</h1>
    		</div>
    		<div class="left">
    			<div class="title">
    				<h3>编辑资料</h3>
    			</div>
    			<div class="menu_">
	    			<div class="base">
	    				<h3 onclick="swit('1')" id="menu_base">基本资料</h3>
	    			</div>
	    			<hr />
	    			<div class="more">
	    				<h3 onclick="swit('0')" id="menu_more">详细资料</h3>
	    			</div>
	    			<hr />
	    			<div class="pass">
	    				<h3 onclick="swit('2')" id="menu_pass">修改密码</h3>
	    			</div>
	    			<hr />
    			</div>
    		</div>
    		<div class="right" id="update_info">
    		<form action="reglogin!infoUpdate.action" id="updateInfoForm" method="post">
    			<div class="info" id="base">
    				<h2 id="baseTitle">基本资料</h2>
    				<hr />
    				<div class="content">
    					<table>
	    					<tr>
				    			<td class="left">用户名：</td>
				    			<td class="right">&nbsp;${sessionScope.employee.emplAccount }
				    				<input type="hidden" name="employee.emplAccount" value="${sessionScope.employee.emplAccount }" >
				    			</td>
				    		</tr>
				    		<tr>
				    			<td class="left">性别：</td>
				    			<td class="right">&nbsp;
				    				<select name="employee.emplSex">
				    					<option value="-1">保密</option>
				    					<option value="<%=Constant.MAN %>">男</option>
				    					<option value="<%=Constant.WOMAN %>">女</option>
				    				</select>
				    				<script type="text/javascript">
				    					$("[value='${sessionScope.employee.emplSex}']").attr('selected','selected');
				    					
				    				</script>
				    				</td>
				    		</tr>
				    		<tr>
				    			<td class="left">年龄：</td>
				    			<td class="right">&nbsp;
				    				<input type="text" name="employee.emplAge" value="${sessionScope.employee.emplAge }" onkeyup="value=value.replace(/[^\d]/g,'')" 
						          	 onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/> 
				    				</td>
				    		</tr>
				    		<tr>
				    			<td class="left">注册日期：</td>
				    			<td class="right">&nbsp;${sessionScope.employee.timeOrder }
				    				<input type="hidden" name="employee.timeOrder" value="${sessionScope.employee.timeOrder }"> 
				    			</td>
				    		</tr> 
	    				</table>
    				</div>
    			</div>
    			<div class="info" id="more">
    				<h2 id="moreTitle">详细资料</h2>	
    				<hr />
    				<div class="content">
    					<table>
	    					<tr>
				    			<td class="left">身份证号：</td>
				    			<td class="right">&nbsp;
				    				<input  type="text" name="employee.emplIdCard" value="${sessionScope.employee.emplIdCard }" >
				    			</td>
				    		</tr>
				    		<tr>
				    			<td class="left">住址：</td>
				    			<td class="right">&nbsp;
				    				<input type="text" name="employee.emplAddress" value="${sessionScope.employee.emplAddress }">
				    			</td>
				    		</tr>
				    		<tr>
				    			<td class="left">手机号：</td>
				    			<td class="right">&nbsp;
				    				<input type="text" name="employee.emplMobile" value="${sessionScope.employee.emplMobile }" onkeyup="value=value.replace(/[^\d]/g,'')" 
						          	 onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				    			</td>
				    		</tr>
				    		<tr>
				    			<td class="left">办公电话：</td>
				    			<td class="right">&nbsp;
				    				<input type="text" name="employee.emplOfficeTel" value="${sessionScope.employee.emplOfficeTel }"onkeyup="value=value.replace(/[^\d]/g,'')" 
						          	 onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				    			</td>
				    		</tr>
				    		<tr>
				    			<td class="left">家庭电话：</td>
				    			<td class="right">&nbsp;
				    				<input type="text" name="employee.emplHomeTel" value="${sessionScope.employee.emplHomeTel }"onkeyup="value=value.replace(/[^\d]/g,'')" 
						          	 onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				    			</td>
				    		</tr>
				    		<tr>
				    			<td class="left">E-mail：</td>
				    			<td class="right">&nbsp;
				    				<input type="text" value="${sessionScope.employee.emplEmail }" name="employee.emplEmail" >		          	
				    			</td>
				    		</tr>
				    		<tr>
				    			<td class="left">QQ：</td>
				    			<td class="right">&nbsp;
				    				<input type="text" name="employee.emplqq" value="${sessionScope.employee.emplqq }" onkeyup="value=value.replace(/[^\d]/g,'')" 
						          	 onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				    			</td>
				    		</tr>
				    		<tr>
				    			<td colspan="2">&nbsp;</td>
				    		</tr>
			    		</table>
    				</div>
    			</div>
    			<div class="upInfoBut">
    				<input type="button" onclick="subForm()" value="保存资料" />
    			</div>
    			</form>
    		</div>
    		<div class="right" style="display:none;" id="update_pass">
    			<div class="info">
    				<h2 id="baseTitle">修改密码</h2>
    				<hr />
    				<div class="content">
    					<form action="reglogin!passUpdate.action" id="updatePassForm" method="post">
    					<table >
				    		<tr>
				    			<td class="left">姓名：</td>
				    			<td class="right">&nbsp;${sessionScope.employee.emplName }
				    			</td>
				    		</tr>    		
				    		<tr>
				    			<td class="left">用户名：</td>
				    			<td class="right">&nbsp;${sessionScope.employee.emplAccount }
				    			</td>
				    		</tr>
				    		<tr>
				    			<td class="left">原始密码：</td>
				    			<td class="right">&nbsp;
				    				<input type="password" name="oldPass">
				    			</td>
				    		</tr>
				    		<tr>
				    			<td class="left">新密码：</td>
				    			<td class="right">&nbsp;
				    				<input type="password" name="newPass"></td>
				    		</tr>
				    		<tr>
				    			<td class="left">重复新密码：</td>
				    			<td class="right">&nbsp;
				    				<input  type="password" name="reNewPass"></td>
				    		</tr>
				    		<tr>
								<td class="left">
									验证码：
								</td>
								<td class="right">
									<input type="text" name="yanzh" id="yanzh" maxlength="4" size="5" width="50px" style="margin-left:10px;display:inline;"  onblur="subYzm()">
									<img alt="验证码" src="reglogin!image.action" width="55px" style="margin-left:10px;display:inline;" id="image" onclick="cutImage()" />
								</td>
							</tr>	
				    		<tr>
				    			<td colspan="2" style="width:375px;">
				    				<div class="upInfoBut" style="margin-left:auto;margin-right:auto;">
					    				<input type="button" onclick="subUpPass()" value="修改密码" />
					    			</div>   				
				    			</td>
				    		</tr>
				    		<tr>
				    			<td id="mess" colspan="2" style="color:red" align="center"  style="width:375px;">
				    				&nbsp;${requestScope.errorMess }
				    			 </td>
				    		</tr>
				    	</table>
				    	</form>
    				</div>
    			</div>
    		</div>  
    	</div>
    </div>
    <div class="all_boot">
		<div class="boot">
			<jsp:include page="boot.jsp"></jsp:include>
		</div>
	</div>
  </body>
</html>
