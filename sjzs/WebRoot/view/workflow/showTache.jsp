<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/view/common/common.jsp" %>
<%@ taglib prefix="wf" uri="/auite-tags"%>
<%@ taglib prefix="o" uri="/opinion-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String pagePath = request.getRequestURL() + "";
%>
<html>
	<head>
		<base href="<%=basePath%>">
		<base target="_self">
		<title>添加功能页面</title>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="<%=basePath%>css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="js/dataCheck.js"></script>
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
		<script type='text/javascript' src='dwr/interface/attachSJZS.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<link rel="stylesheet" href="css/right.css" type="text/css"></link>
		<script type="text/javascript" src="js/jquery.easyui.min.1.2.2.js"></script>
		<script type="text/javascript" src='js/locale/easyui-lang-zh_CN.js'></script>
		
		

		<script type="text/javascript">

window.name="currWin";

	$(function(){ 
		 //获取附件列表
		attachSJZS.findAttachs('${tache.attach}', function (data){
			if (null != data && data.length > 0) {
				for (var i = 0; i < data.length; i++) {
					listFile(data[i].attachName,data[i].attachId);
				}
			}
		});
	 });
	
	//将上传的文件列表列出
	function listFile(fileName,attId) {
		var params = 'javascript:deleteFile(\'' + attId + '\')';
		var html_str = "<li id=\"" + attId + "\"><a href=\"attach!showAtttachInfo.action?attach.attachId ="+attId+ "\"  target =\"_self\">" + fileName + "</a><input type=\"hidden\" value=\""
				+ attId + "\" name=\"attIds\" /></li>";
       	$("#fileList").append(html_str);
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

	//添加
	function showPage(reqURL) {

		self.location = reqURL;
		/**$('#addMenuWin').window({   
			width:700,   
			height:350,   
			modal:true,
			collapsible:false,
			minimizable:false, 
			maximizable:false,
			closable:true
		});
		var iframe_str = "<iframe height=\"100%\" width=\"100%\" scrolling=\"no\" frameborder=\"0\" src=\"" + reqURL + "\"></iframe>";
		$('#addMenuWin').html(iframe_str);*/
	}
	
</script>
	</head>
	<body style="overflow: scroll;">
		<div align="center">
			<form action="shenpi!commitSave.action" method="post"
				accept-charset="utf-8">
				<table width="99%" border="0" cellspacing="0" cellpadding="0"
					class="border_A6C4DC">
					<tr class="title_style">
						<td colspan="8" style="text-align: left">
							<img src="images/group1.png" width="20" height="20"
								align="absmiddle" />
								审计节点信息
						</td>
					</tr>

			
					<tr class="row_bg" height="250px">
						<td colspan="8">
							<table width="99%" border="0" cellspacing="0"  height="100%" cellpadding="0"
								class="nonborder2">
								<tr bgcolor="#F7FCFF">
									<td height="35" align="right">
										环节名称:
									</td>
									<td>
										<input type="text" name="tache.tache_name" readonly="readonly" value="${tache.tache_name }" />
										<input type="hidden" name="tache.tache_id" value="${tache.tache_id}" />
									</td>
								</tr>
								
								<tr bgcolor="#F7FCFF">
									<td height="35" align="right">
										审计描述：
									</td>
									<td>
										<textarea style="width:95%;height:100px" readonly="readonly" name="tache.minutes">${tache.minutes }</textarea>
									</td>
								</tr>
								<c:if test="${tache.hours !='null'}">
											
								<tr class="contentTd">
								  <td colspan="1" align="right">引用信息:</td>
								  <td><input type="button" value = "查看" onclick="showPage('${fn:replace(tache.hours, '0000000000000000000','&') }')"></td>
								</tr>
								</c:if>
								<tr class="contentTd">
									<td style="font-weight:bold; text-align:right; padding-right:20px;">
										附件列表:</td>
									<td align="left" style="padding-left:10px;" colspan="2">
										<ul id="fileList" style="margin-left: 5px;"></ul>
										<input type="hidden" value="${tache.attach }" id="attach" name="tache.attach"/>
										
									</td>
								</tr>
							
							</table>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="addMenuWin" iconCls="icon-save" title="查看" style="width: 0;height: 0"></div> 
	</body>
</html>