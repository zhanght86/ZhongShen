<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/view/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>审核方法案例</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script language="javascript" src="js/calendar/calendar.js" charset="UTF-8"></script>
		<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
		<%--
		<link rel="stylesheet" href="css/right.css" type="text/css"></link>
		<link rel="stylesheet" href="css/validate.css" type="text/css"></link>
		<script type="text/javascript" src="js/validate/jquery.js"></script>
		<script type="text/javascript" src="js/validate/jquery.validate.js"></script>
		<script type="text/javascript" src="js/validate/messages_cn.js"></script>
		<script type="text/javascript" src="js/validate/customValidate.js"></script>
		<script type="text/javascript" src="js/fckeditor/fckeditor.js"></script>
		<script type="text/javascript" src="js/validate/messages_cn.js"></script>
		<script type="text/javascript" src="js/validate/customValidate.js"></script>
		<script language="javascript" src="js/calendar/calendar.js" charset="UTF-8"></script>
		<script language="javascript" src='js/showdialog.js'></script>
		<script type='text/javascript' src='dwr/interface/auditLaw.js'></script>
  		<script type='text/javascript' src='dwr/engine.js'></script>
    	<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src='js/dataCheck.js'></script>
		<script type='text/javascript' src='dwr/interface/attachSJZS.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
			<script type="text/javascript" src="fckeditor/fckeditor.js"></script>
		<script type="text/javascript">


		  $(function(){
				$("#addForm").validate({
					rules:{
						"ffal.title":{
							required:true
						},
						"ffal.attachId":{
							required:true
						},
						"ffal.content":{
							required:true
						}
					},
					messages:{
						"ffal.title":{
							required:"必填"
						},
						"ffal.attachId":{
							required:"必填"
						} ,
						"ffal.content":{
							required:"必填"
						}
					},
					errorElement:"em",
					success:function(label) {
						label.text(" ").addClass("success")
					}
				});
			});		
//初始化table大小
	function init(){
		document.getElementById("add").style.width=document.body.offsetWidth-40;
	}

	function isdis(t){
        return $(t).attr('unable')=="true";
	}
  </script>
   --%>
   <script type="text/javascript">
	
		function check(flag){
			$("#checkFlag").val(flag);
			document.checkForm.submit();
		}
	
		//删除附件功能
		function delAttach(uploadId,type){
			if(confirm("您确定要删除么？")){
				$.ajax({
					type:"GET",
					url:"system/wdsh!delAttach.action",
					data:"uploadId="+uploadId+"&type="+type,
					success:function(data){
						if(data){
							$("#attachId").val("");
							$("#showAttach").hide();
							$("#info").html("没有附件信息");
						}else{
							alert("附件删除失败，请联系管理员！");
						}
					}
				});
			}
		}
		
	 </script>
  <style type="text/css">
  	body,table {
		font-size: 12px;
	}
  </style>
  </head>
  
  <body>
		<form method="post" action="system/wdsh!check.action" id="checkForm" name="checkForm">
		  <input type="hidden" name="type" value="${ffal.type }"/>
		  <input type="hidden" name="ffal.type" value="${ffal.type }"/>
		  <input type="hidden" name="ffal.id" value="${ffal.id }"/>
		  <input type="hidden" name="ffal.parentId" value="${ffal.parentId }"/>
		  <input type="hidden" name="ffal.attachId" value="${ffal.attachId }" id="attachId"/>
		  <input type="hidden" name="ffal.clientId" value="${ffal.clientId }"/>
		  <input type="hidden" name="ffal.curCheckUserId" value="${ffal.curCheckUserId }"/>
		  <input type="hidden" name="ffal.curCheckUserName" value="${ffal.curCheckUserName }"/>
		  <input type="hidden" name="ffal.checkFlag" value="${ffal.checkFlag }" id="checkFlag"/>
		  <div id="content">
		  <table>
			<tr>
				<td><b>方法名称</b></td>
				<td><input type="text" name="ffal.caption" value="${ffal.caption}"></td>
				<td><b>撰写单位</b></td>
				<td><input type="text" name="ffal.department" value="${ffal.department}"></td>
				<td><b>撰写日期</b></td>
				<td>
			      <fmt:parseDate value="${ffal.ffalDateTime}" var="date1"/>
				  <input type="text" id="date" maxLength="50" name="ffal.ffalDateTime" readonly="readonly" value="<fmt:formatDate value='${date1}' pattern='yyyy-MM-dd' />" style="width:130px;"/>
     			  <img id="selectDay" style="CURSOR: hand" onClick="setday(this,document.all.date)"src="js/calendar/form/calendar.gif" align="middle" border="0" width="30" height="30"/>
				</td>
				<td><b>撰写人</b></td>
				<td><input type="text" name="ffal.author" value="${ffal.author}"></td>
			</tr>
			<tr>
				<td><b>积分</b></td>
				<td><input type="text" name="ffal.integral" value="${ffal.integral}"></td>
				<td><b>上传用户</b></td>
				<td><input type="text" name="ffal.clientName" value="${ffal.clientName}"></td>
				<td><b>审核状态</b></td>
				<td>
					<span>
						<s:if test="ffal.checkFlag == 0">待审核</s:if>
						<s:elseif test="ffal.checkFlag == 1">审核中</s:elseif>
						<s:elseif test="ffal.checkFlag == 2">审核通过</s:elseif>
						<s:elseif test="ffal.checkFlag == 3">已打回</s:elseif>
					</span>
				</td>
				<td><b>是否公开</b></td>
				<td>
					<input type="radio" name="ffal.isOpen" value="1" <s:if test="ffal.isOpen == 1">checked="checked"</s:if> />公开
					<input type="radio" name="ffal.isOpen" value="0"<s:if test="ffal.isOpen == 0">checked="checked"</s:if> />不公开
				</td>
			</tr>
			<tr>
				<td><b>上传时间</b></td>
				<td>
					<fmt:parseDate value="${ffal.uploadDate}" var="date"/>
					<fmt:formatDate value="${date}" pattern="yyyy-MM-dd"/>
				</td>
				<td><b>内容简介</b></td>
				<td colspan="2">
				  <textarea rows="2" cols="40"  name="ffal.content" id="matText">${ffal.content}</textarea>
				</td>
			</tr>
			<%--
			<tfoot>
			</tfoot>
			 --%>
		</table>
		<script type="text/javascript">
		  	$(function(){
				$("#content input[type='text']").attr("readonly","readonly").css("border","none");
				$("#content input[type='radio']").attr("disabled","disabled");
				$("#content textarea").attr("readonly","readonly");
				$('#selectDay').hide();
		  	});
		</script>
	</div>
	<div id="attach">
		<s:if test="ffal.attachId == null || '' == ffal.attachId">
			<span id="info">没有附件信息</span>
		</s:if>
		<s:else>
			<span id="info">
			文档信息<img style="cursor: pointer;" alt="" src="images/del.gif" onclick="javascript:delAttach('${ffal.id}','${ffal.type }');">
			</span>
   			<iframe id="showAttach" src="<%=basePath %>system/wdsh!showAttachInfo.action?attachId=${ffal.attachId}" frameborder="0" width="100%" height="100%"></iframe>
   		</s:else>
   	</div>
   	<div>
   		<table>
   			<tr>
   				<td>审核意见</td>
   				<td>
   					<textarea rows="2" cols="120" name="checkAdvice"></textarea>
   				</td>
   			</tr>
   			<tr>
   				<td colspan="2" style="height:40px;line-height: 40px;text-align: center;">
   					<input type="button" value="通过" id="" onclick="check(2)">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="打回" id="" onclick="check(3)">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="放弃" id="" onclick="check(0)">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="修改" id="update">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   				</td>
   			</tr>
		</table>
   	</div>
   	</form>
  </body>
  <script type="text/javascript">
  	$(function(){
  		$("#update").toggle(
  			  function(){
  				$("#content input[type='text']").removeAttr("readonly").css("border","1px solid");
				$("#content input[type='radio']").removeAttr("disabled");
				$("#content textarea").removeAttr("readonly");
				$('#selectDay').show();
  			    $(this).attr("value","保存");
  			  },
  			  function(){
  				$("#content input[type='text']").attr("readonly","readonly").css("border","none");
				$("#content input[type='radio']").attr("disabled","disabled");
				$("#content textarea").attr("readonly","readonly");
				$('#selectDay').hide();
  			    $(this).attr("value","修改");
  			  }
  		); 
  	});
  </script>
</html>
