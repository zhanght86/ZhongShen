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
		<title>修改审计法规页面</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
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
		$("#addLawForm").validate({
			rules:{
				"law.lawName":{
					required:true
				},
				"law.attachId":{
					required:true
				} ,
				"law.lawContent":{
					required:true
				}
			},
			messages:{
				"law.lawName":{
					required:"必填"
				},
				"law.attachId":{
					required:"必填"
				} ,
				"law.lawContent":{
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
		document.getElementById("list").style.width=document.body.offsetWidth-40;
	}

	function isdis(t){
        return $(t).attr('unable')=="true";
	}
  </script>
  </head>
  
  <body onload="javascript:init();">
		<form method="post" action="system/sjfg!doUpdateLaw.action" id="addLawForm" name="addJsdwForm">
		 <input type="hidden" name="law.lawSort" value="${law.lawSort }"/>
		  <input type="hidden" name="law.lawId" value="${law.lawId }"/>
		  <table class="table" id="list" style="font-size: 12px;margin-top: 10px"  align="center">
			<tr class="contentTd">
				<td  align="right" width="200px;"><b>法规名称</b></td>
				<td><label>
				  <input type="text" name="law.lawName" value="${law.lawName}">
				</label></td>
				<td  align="right" width="200px;"><b>发文单位</b></td>
				<td width="200px;"><label>
				  <input type="text" name="law.lawOrg" value="${law.lawOrg}">
				</label></td>
			</tr>
			<tr class="contentTd">
				<td  align="right" width="200px;"><b>法规日期</b></td>
				<td width="200px;"><label>
				  <input id="date" maxLength="50" name="law.lawDate" readonly="readonly" value="${law.lawDate}"" style="width:130px;"/>
     					<img style="CURSOR: hand" onClick="setday(this,document.all.date)"
						src="js/calendar/form/calendar.gif" align="middle" border="0" width="30" height="30"/>
				</label></td>
				<td  align="right" width="200px;"><b>发文编号</b></td>
				<td width="200px;"><label>
				  <input type="text" name="law.lawNumber" value="${law.lawNumber}">
				</label></td>
			</tr>
			<tr class="contentTd">
				<td  align="right"><b>内容简介</b></td>
				<td colspan="3">
				  <input type="button" value="选择word文件导入" onclick="importWord();" class="button-submis10"/><br/>
				  <textarea rows="7" cols="60"  name="law.lawContent" id="matText">${law.lawContent}</textarea>
				      <script type="text/javascript">
								    	var my_FCKeditor = new FCKeditor("matText");
								    	my_FCKeditor.BasePath = "fckeditor/";
								    	my_FCKeditor.Width= "100%";
								    	my_FCKeditor.Height= "250";
								    	my_FCKeditor.ToolbarSet= "Myself";
								    	my_FCKeditor.ReplaceTextarea();
									</script>
				</td>
			</tr>
			<tr  class="contentTd">
					<td style="font-weight:bold; text-align:right; padding-right:10px;" colspan="2">法规内容:</td>
					<td style="font-weight:bold; text-align:left; padding-left:10px; " colspan="2">
					<input type="button" value="&nbsp;" class="selectUnit" onclick="javascript:selectFile()"/>
					</td>
				</tr>
				<tr class="contentTd">
					<td style="font-weight:bold; text-align:right; padding-right:20px;" colspan="2">
						附件列表:</td>
					<td align="left" style="padding-left:10px;" colspan="2">
						<ul id="fileList" style="margin-left: 5px;">
						<c:if test="${!empty law.attach.attachId}">
							<li id="${law.attachId }"> ${law.attach.attachName } 
							<img src="images/del.gif" onclick="javascript:deleteFile('${law.attachId }')" style="margin-left:20px;cursor:pointer;"/>
							</li>
						</c:if>
						</ul>
						<input type="hidden" name="law.attachId" id ="attachId" value="${law.attachId }"/>
					</td>
				</tr>
			<tfoot>
			<tr>
				<td colspan="4" align="center">
					<input type="submit" value=" " id="savebtn" class="savebtn">
					<input type="button" value=" " id="closebtn" class="closebtn" onclick="closeWin();">
				</td>
			</tr>
			</tfoot>
		</table>
	</form>
  </body>
  <script type="text/javascript">
  function closeWin(){
		window.parent.closeWin('updMenuWin');
	}

  
	//打开文件选择窗口
	function selectFile() {			
		var width = "920";
		var url = "<%=basePath%>attach!selectFile.action?attach.attachId=${attach.attachId}&type=1&nocache=" + new Date().getTime();
		var returnVal = window.showModalDialog(url, null, "center=yes;dialogWidth="+width+"px;dialogHeight=600px; edge:Raised; directories:no; localtion:no; menubar:no; status=yes; toolbar=yes;scroll:yes;help=no");
		
		if (returnVal != undefined && returnVal != null) {//如果用户选择了文件
			for (var i = 0; i < returnVal.length; i++) {
				if (returnVal[i][2] == 1) {//如果是上传的附件
					listFile(returnVal[i][0],returnVal[i][1]);
				} 
			}
		}
	}

	//将上传的文件列表列出
	function listFile(fileName,attId) {
		var oldAttId = $("#attachId").val();
		if(oldAttId!=null&&""!=oldAttId){
			deleteFile(oldAttId);
		}
		var params = 'javascript:deleteFile(\'' + attId + '\')';
		var html_str = "<li id=\"" + attId + "\">" + fileName + "<img src=\"images/del.gif\" onclick=\"" 
				+ params + "\" style=\"margin-left:20px;cursor:pointer;\"/><input type=\"hidden\" value=\""
				+ attId + "\" name=\"attIds\" /></li>";
       	$("#fileList").append(html_str);
       	$("#attachId").val(attId);
       	//document.getElementById("attachId").value = attId;
      //	document.execCommand('Refresh') ;
	}

	//根据guid删除其所指定的文件
	function deleteFile(attId) {
		attachSJZS.delAttach(attId, function(data) {
				if (true == data) {
					
					$("#" + attId).remove();
					document.getElementById("attachId").value = "";
				}
			});
	}
	//初始化table大小
	function init(){
		document.getElementById("list").style.width=document.body.offsetWidth-40;		
	}

	//删除法规信息
	function deleteAttach(curchaId) {
		if(confirm("您确定要删除么？")) {
			document.deleteForm.submit();   //获取所有的法规信息
		}
	}
//下载
	function downloadAttach(curchaId) {
		if(document.downloadForm.attId!=""){
				document.downloadForm.submit();   //获取所有的法规信息
		}else{
			alert("没有要下载的文件！");
		}
	}
	//打开导入word对话框
	function importWord() {
		var openURL = "<%=basePath%>view/common/importWord.jsp?time="+new Date().getTime();
		var reValue = window.showModalDialog(openURL,"","dialogHeight=200px;");
		if ( 'no' != reValue && undefined != reValue ) {
			auditLaw.getConByHtml(reValue,function(data){
				var myEditor = FCKeditorAPI.GetInstance( "matText" );
				myEditor.InsertHtml(data);
			});
		}
	} 

</script>
</html>
