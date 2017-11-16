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
		<title>添加方法案例页面</title>
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
  		<script type='text/javascript' src='dwr/engine.js'></script>
    	<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src='js/dataCheck.js'></script>
		<script type='text/javascript' src='dwr/interface/dwrUtil.js'></script>
		<script type='text/javascript' src='dwr/interface/auditLaw.js'></script>
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
				}, 
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
		document.getElementById("list").style.width=document.body.offsetWidth-40;
	}

	function isdis(t){
        return $(t).attr('unable')=="true";
	}

	
	
  </script>
  </head>
  
  <body onload="javascript:init();">
		<form method="post" action="system/ffal!doAdd.action" id="addForm" name="addJsdwForm">
		 <input type="hidden" name="ffal.sort" value="${ffal.sort }"/>
		  <table class="table" id="list" style="font-size: 12px;margin-top: 10px"   height="100%" width="100%" align="center">
			<tr class="contentTd">
				<td  align="right" width="170px;"><b>标题</b></td>
				<td><label>
				  <input type="text" name="ffal.title">
				</label></td>
				<td  align="right" width="170px;"><b>撰写人</b></td>
				<td><label>
				  <input type="text" name="ffal.author">
				</label></td>
			</tr>
			<tr class="contentTd">
				<td  align="right" width="170px;"><b>撰写单位</b></td>
				<td><label>
				  <input type="text" name="ffal.department">
				</label></td>
					<td  align="right" width="170px;"><b>获奖情况</b></td>
				<td><label>
				  <input type="text" name="ffal.awards">
				</label></td>
			</tr>
			<tr class="contentTd">
				<td  align="right" colspan="1"><b>撰写日期</b></td>
				<td colspan="1" ><label>
				  <input id="date" maxLength="50" name="ffal.ffalDateTime" readonly="readonly" value="" style="width:160px;"/>
				</label></td>
				<td colspan="2"><img style="CURSOR: hand" onClick="setday(this,document.all.date)"
						src="js/calendar/form/calendar.gif" align="middle" border="0" width="30" height="30"/> </td>
				
			</tr>
			<tr class="contentTd">
				<td  align="right"><b>内容简介</b></td>
				<td colspan="3">
				  <input type="button" value="选择word文件导入" onclick="importWord();" class="button-submis10"/><br/>
				  <textarea rows="5" cols="60"  name="ffal.content" id="matText"></textarea>
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
					<td style="font-weight:bold; text-align:right; padding-right:10px;" colspan="2">案例内容:</td>
					<td style="font-weight:bold; text-align:left; padding-left:10px; " colspan="2">
					<input type="button" value="&nbsp;" class="selectUnit" onclick="javascript:selectFile()"/>
					</td>
				</tr>
				<tr class="contentTd">
					<td style="font-weight:bold; text-align:right; padding-right:10px;" colspan="2">
						附件列表:</td>
					<td align="left" style="padding-left:10px;padding-bottom: 0px;" colspan="2">
						<ul id="fileList" style="margin-left: 5px;"></ul>
						<input type="hidden" name="ffal.attachId" id ="attachId" value=""/>
					</td>
				</tr>
			<tfoot>
			<tr>
				<td colspan="4" align="center">
					<input type="submit" value=" " id="savebtn" class="savebtn" >
					<input type="button" value=" " id="closebtn" class="closebtn" onclick="closeWin();">
				</td>
			</tr>
			</tfoot>
		</table>
	</form>
  </body>
  <script type="text/javascript">
	function closeWin(){
		window.parent.closeWin('addMenuWin');
	}

  
	//打开文件选择窗口
	function selectFile() {			
		var width = "920";
		var url = "<%=basePath%>system/attach!selectFile.action?attach.attachId=${attach.attachId}&nocache=" + new Date().getTime();
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
		dwrUtil.delAttachJS(attId, function(data) {
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
