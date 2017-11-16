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
		<title>添加实施方案页面</title>
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
		<script language="javascript" src="js/calendar/calendar.js"
			charset="UTF-8"></script>
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
				"shiShiFangAn.name":{
					required:true
				},
				"shiShiFangAn.attachId":{
					required:true
				}, 
				"shiShiFangAn.keyWord":{
					required:true
				},
				"shiShiFangAn.writeDate":{
					required:true
				},
				"shiShiFangAn.industry":{
					required:true
				}
			},
			messages:{
				"shiShiFangAn.name":{
					required:"必填"
				},
				"shiShiFangAn.attachId":{
					required:"必填"
				}, 
				"shiShiFangAn.keyWord":{
					required:"必填"
				} , 
				"shiShiFangAn.writeDate":{
					required:"必填"
				}, 
				"shiShiFangAn.industry":{
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
		<form method="post" action="system/ssfa!addSSFA.action" id="addForm"
			name="addJsdwForm">
			<input type="hidden" name="shiShiFangAn.sort"
				value="${shiShiFangAn.sort }" />
			<table class="table" id="list"
				style="font-size: 12px; margin-top: 10px" height="100%" width="100%"
				align="center">
				<tr class="contentTd">
					<td style="font-weight: bold; text-align: right;  width: 150px">
						<b>方案名称</b>
					</td>
					<td style="width: 170px">
						<label>
							<input type="text" name="shiShiFangAn.name" maxlength="50" >
						</label>
					</td>
					<td style="font-weight: bold; text-align: right;  width: 150px">
						<b>所属行业</b>
					</td>
					<td style="width: 170px">
						<label>
							<input type="text" name="shiShiFangAn.industry"  maxlength="50">
						</label>
					</td>
				</tr>
				<tr class="contentTd">
					<td style="font-weight: bold; text-align: right;  width: 150px">
						<b>关键字</b>
					</td>
					<td style="width: 170px">
						<label>
							<input type="text" name="shiShiFangAn.keyWord" maxlength="50" >
						</label>
					</td>
					<td style="font-weight: bold; text-align: right;  width: 150px">
						<b>撰写人</b>
					</td>
					<td style="width: 170px">
						<label>
							<input type="text" name="shiShiFangAn.author" maxlength="50">
						</label>
					</td>
				</tr>
				<tr class="contentTd">
					<td style="font-weight: bold; text-align: right; width: 150px">
						<b>撰写日期</b>
					</td>
					<td colspan="1" style="width: 170px">
						<label>
							<input id="date" maxLength="50"  name="shiShiFangAn.writeDate" readonly="readonly" value=""
								style="width:160px;" />
						</label>
					</td>
					<td colspan="2">
						<img style="CURSOR: hand" onClick="setday(this,document.all.date)"
							src="js/calendar/form/calendar.gif" align="middle" border="0"
							width="30" height="30" />
					</td>

				</tr>
				<tr class="contentTd">
					<td
						style="font-weight: bold; text-align: right; width: 50%"
						colspan="2">
						方案内容:
					</td>
					<td
						style="font-weight: bold; text-align: left;"
						colspan="2">
						<input type="button" value="&nbsp;" class="selectUnit"
							onclick="javascript:selectFile()" />
					</td>
				</tr>
				<tr class="contentTd">
					<td
						style="font-weight: bold; text-align: right; width: 25%"
						colspan="2">
						附件列表:
					</td>
					<td align="left" style="padding-left: 10px;" colspan="2">
						<ul id="fileList" style="margin-left: 5px;"></ul>
						<input type="hidden" name="shiShiFangAn.attachId" id="attachId"
							value="" />
					</td>
				</tr>
				<tfoot>
					<tr>
						<td colspan="4" align="center">
							<input type="submit" value=" " id="savebtn" class="savebtn">
							<input type="button" value=" " id="closebtn" class="closebtn"
								onclick="closeWin();">
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</body>
	<script type="text/javascript">
	function closeWin(){
		window.parent.closeWin('addMenuWin');
		var attachId = 	document.getElementById("attachId").value;
		if(attachId != "" && attachId !="undefined"){
			deleteFile(attachId);
		}
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
</script>

	</script>
</html>
