<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hnzskj.persist.bean.attach.Attach"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.IOException"%>
<%@ include file="/view/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>"/>
    <title>方法案例页面</title>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type='text/javascript' src='dwr/interface/attachSJZS.js'></script>
	<script type='text/javascript' src='dwr/engine.js'></script>
	<link rel="stylesheet" href="css/right.css" type="text/css"></link>
	<link rel="stylesheet" href='css/style.css' type="text/css"/>
	<script type="text/javascript">
	//打开文件选择窗口
	function selectFile() {			
		var width = "920";
		var url = "<%=basePath%>attach!selectFile.action?attach.journalId=${attach.journalId}&type=1&nocache=" + new Date().getTime();
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
		var params = 'javascript:deleteFile(\'' + attId + '\')';
		var html_str = "<li id=\"" + attId + "\">" + fileName + "<img src=\"images/del.gif\" onclick=\"" 
				+ params + "\" style=\"margin-left:20px;cursor:pointer;\"/><input type=\"hidden\" value=\""
				+ attId + "\" name=\"attIds\" /></li>";
       	$("#fileList").append(html_str);
       //	document.execCommand('Refresh') ;
	}

	//根据guid删除其所指定的文件
	function deleteFile(attId) {
		attachSJZS.delAttach(attId, function(data) {
				if (true == data) {
					$("#" + attId).remove();
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
  </head>
 <body onload="javascript:init();">
<div id="main" align="center" >
<form action="" method="post" id="searchForm" name="searchForm">
 	<input type="hidden" name="attach.journalId" value="${attach.journalId}"/>
 </form>
		<table cellpadding="0" cellspacing="0" border="1"  id="list" class="table" style="font-size: 12px;margin-top:5px;">
			<thead>
			<tr align="center">
				<td><strong>方法案例名称</strong></td>
				<td><strong>撰写人</strong></td>
				<td><strong>撰写单位</strong></td>
				<td><strong>撰写日期</strong></td>
				<td><strong>获奖情况</strong></td>
				<td><strong>操作</strong></td>
			</tr>
			</thead>
			<tbody>
			<c:if test="${!empty attach.attachId}">
					<form method="post" action="attach!deleteAttachById.action" id="deleteForm" name="deleteForm" style="display: none;">
					<input type="hidden" name="attach.attachId" value="${attach.attachId}"/>
					<input type="hidden" name="attach.journalId" value="${attach.journalId}"/>
					</form>
					<form method="post" action="attach/download/DownLoadServletSJZS" id="downloadForm" name="downloadForm" style="display: none;">
					<input type="hidden" name="attId" value="${attach.attachId}"/>
					<input type="hidden" name="journalId" value="${attach.journalId}"/>
					</form>
					<tr align="center" class="contentTd" onmouseover="this.className='onmouseover'" onmouseout="this.className='contentTd'">
					<td>
						<a href="attach!showAtttachInfo.action?attach.journalId =${attach.journalId }"  target ="attchInfo" >${attach.attachName}</a>
					</td>
					<td>${attach.attachType}</td>
					<td>	<img src="images/ny_sc.jpg" width="19" height="19"  />
		          	<a href="javascript:deleteAttach()" class="txt12">删除</a>
		          	&nbsp;&nbsp;&nbsp;&nbsp;
		          	<img src="images/xt_12.gif" width="19" height="19"  />
		          	<a href="javascript:downloadAttach()" class="txt12">下载</a>
		          	</td>
						
					</tr>
			</c:if>
			<c:if test="${empty attach.attachId}">
			<tr class="contentTd" onmouseover="this.className='onmouseover'" onmouseout="this.className='contentTd'">					
					<td colspan="5" align="center">对不起，没有您要查找的数据</td>	
			</tr>
				<tr  class="contentTd">
					<td style="font-weight:bold; text-align:right; padding-right:10px;">选择附件:</td>
					<td style="font-weight:bold; text-align:left; padding-left:10px; " colspan="2">
					<input type="button" value="&nbsp;" class="selectUnit" onclick="javascript:selectFile()"/>
					</td>
				</tr>
				<tr class="contentTd">
					<td style="font-weight:bold; text-align:right; padding-right:20px;">
						附件列表:</td>
					<td align="left" style="padding-left:10px;" colspan="2">
						<ul id="fileList" style="margin-left: 5px;"></ul>
					</td>
				</tr>
					<form method="post" action="attach!deleteAttachById.action" id="deleteForm1" name="deleteForm1" style="display: none;">
					<input type="hidden" name="attach.attachId" value=""/>
					</form>
			</c:if>
			</tbody>
	</table>
	</div>
	<div id="myright" style="float: left;margin-left: 10px;border:0;width: 100% ;height: 800px;">
  		<iframe id="attchInfo" name="attchInfo" src="" frameborder="0" width="100%" height="100%"></iframe>
    </div>
</body>
</html>
