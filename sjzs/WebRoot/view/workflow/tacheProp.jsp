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
		<title>添加功能页面</title>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<script type="text/javascript" src="js/dataCheck.js"></script>
		<link href="css/style.css" rel="stylesheet" type="text/css" />		
		<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
		<script type='text/javascript' src='dwr/interface/attachSJZS.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<link rel="stylesheet" href="css/right.css" type="text/css"></link>
		<script src="js/treeView/scripts/jquery.min.js" type="text/javascript"></script>
		<script type='text/javascript' src='js/treeView/scripts/jquery.contextmenu.r2-min.js'></script>
		<script src="js/treeView/scripts/jquery.treeview.js" type="text/javascript"></script>
		<link rel="stylesheet" href="js/treeView/css/jquery.treeview.css" />
		<link rel="stylesheet" type="text/css" href="js/themes/default/easyui.css"/>
		<link rel="stylesheet" type="text/css" href="js/themes/icon.css"/>
		<script type="text/javascript" src="js/jquery.easyui.min.1.2.2.js"></script>
		<script type="text/javascript" src='js/locale/easyui-lang-zh_CN.js'></script>
		
		<script type="text/javascript">
  function returnSonValue(){
    var tache_id=window.document.forms[0].elements["tache.tache_id"].value;
     
    var tache_type=window.document.forms[0].elements["tache.tache_type"].value;
   
    var tache_name=window.document.forms[0].elements["tache.tache_name"].value;
    var tache_desc=window.document.forms[0].elements["tache.tache_DESCRIPTION"].value;
    var emp_name=window.document.forms[0].elements["tache.emp_names"].value;
    var emp_ids=window.document.forms[0].elements["tache.guid"].value;
    var dottype=window.document.forms[0].elements["tache.dotType"].value;
    var days=window.document.forms[0].elements["tache.days"].value;
    var hours=window.document.forms[0].elements["tache.hours"].value;
    var minutes=window.document.forms[0].elements["tache.minutes"].value;
    var tag=window.document.forms[0].elements["tache.memo"].value;
    var is_back=window.document.forms[0].elements["tache.is_back"].value;
    var model=window.document.forms[0].elements["tache.nameModel"].value;
    var roleId=window.document.forms[0].elements["tache.roles_id"].value;
    var roleName=window.document.forms[0].elements["tache.roles_name"].value;
    var attach=window.document.forms[0].elements["tache.attach"].value;
     
    var main ="*tache_id="+tache_id+"*tache_type="+tache_type+"*tache_name="+tache_name+"*tache_desc="+tache_desc+"*emp_name="+emp_name+
    "*emp_ids="+emp_ids+"*dottype="+dottype+"*days="+days+"*hours="+hours+"*minutes="+minutes+"*tag="+tag+
    "*is_back="+is_back+"*model="+model+"*roles_id="+roleId+"*roles_name="+roleName+"*attach="+attach;
    window.returnValue = main;
	window.close();
  }


	//打开文件选择窗口
	function selectFile() {			
		var width = "920";
		var url = "<%=basePath%>attach!selectFile.action?attach.attachId=${type}&type=1&nocache=" + new Date().getTime();
		var returnVal = window.showModalDialog(url, null, "center=yes;dialogWidth="+width+"px;dialogHeight=600px; edge:Raised; directories:no; localtion:no; menubar:no; status=yes; toolbar=yes;scroll:yes;help=no");
		
		if (returnVal != undefined && returnVal != null) {//如果用户选择了文件
			for (var i = 0; i < returnVal.length; i++) {
				if (returnVal[i][2] == 1) {//如果是上传的附件
					document.getElementById("attach").value=returnVal[i][1];
					listFile(returnVal[i][0],returnVal[i][1]);
					
				} 
			}
		}
	}


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
		var html_str = "<li id=\"" + attId + "\">" + fileName + "<img src=\"images/del.gif\" onclick=\"" 
				+ params + "\" style=\"margin-left:20px;cursor:pointer;\"/><input type=\"hidden\" value=\""
				+ attId + "\" name=\"attIds\" /></li>";
       	$("#fileList").append(html_str);
       	document.execCommand('Refresh') ;
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

	function addSjInfo(){
		 var url="<%=basePath%>system/workflow!getSjList.action";
    	 var _sfeatures ="dialogWidth=800px;dialogHeight=500px;top=0;left=0;toolbar=no;location=no;titlebar=no;menubar=no";
    	 var info=window.showModalDialog(url,"",_sfeatures);
    	 if(info!=null){
    	 	document.getElementById("sjInfo").value=info;
    	 	info =info.replace("0000000000000000000","&");
    	 	var strHtml='<A href="javascript:showPage(\''+info+'\')">查询</A>';
    	 	$('#urlHtml').html(strHtml);
    	 }
	}

	//添加
	function showPage(reqURL) {
		$('#addMenuWin').window({   
			width:700,   
			height:350,   
			modal:true,
			collapsible:false,
			minimizable:false, 
			maximizable:false,
			closable:true
		});
		var iframe_str = "<iframe height=\"100%\" width=\"100%\" scrolling=\"no\" frameborder=\"0\" src=\"" + reqURL + "\"></iframe>";
		$('#addMenuWin').html(iframe_str);
	}
</script>
	</head>
	
	<body>
		<div align="center">
			<form action="shenpi!commitSave.action" method="post"
				accept-charset="utf-8">
				<table width="99%" border="0" cellspacing="0" cellpadding="0"
					class="border_A6C4DC">
					<tr class="title_style">
						<td colspan="8" style="text-align: left">
							<img src="images/group1.png" width="20" height="20"
								align="absmiddle" />
								添加审计方案节点
						</td>
					</tr>

			
					<tr class="row_bg" height="300px">
						<td colspan="8">
							<table width="99%" border="0" cellspacing="0" cellpadding="0"
								class="nonborder2">
								<tr bgcolor="#F7FCFF">
									<td height="35" align="right">
										环节名称:
									</td>
									<td>
										<input type="text" name="tache.tache_name" value="${tache.tache_name }" />
										<input type="hidden" name="tache.tache_id" value="${tache.tache_id}" />
									</td>
								</tr>
								
								<tr>
									<td  align="right">
										引入审计方法：
									</td>
									<td>
										<div id="urlHtml" style="width:100px">
											<c:if test="${tache.hours !='null'}">
												<a href="javascript:showPage('${fn:replace(tache.hours, '0000000000000000000','&') }')">查询</a>
											</c:if>
										</div>
										<input type="hidden" name="tache.hours" id="sjInfo" value="${tache.hours }" style="width: 50px"/>
										<input type="button" value="请选择" class="selectUnit" onclick="addSjInfo()">
									</td>
								</tr>
								
								<tr bgcolor="#F7FCFF">
									<td height="35" align="right">
										审计描述：
									</td>
									<td>
										<textarea style="width:95%;height:100px" name="tache.minutes">${tache.minutes }</textarea>
									</td>
								</tr>
								<tr  bgcolor="#F7FCFF">
									<td style="font-weight:bold; text-align:right; padding-right:10px;">选择附件:</td>
									<td style="font-weight:bold; text-align:left; padding-left:10px; " colspan="2">
									<input type="button" value="&nbsp;" class="selectUnit" onclick="javascript:selectFile()"/>
									</td>
								</tr>
								<tr bgcolor="#F7FCFF">
									<td style="font-weight:bold; text-align:right; padding-right:20px;">
										附件列表:</td>
									<td align="left" style="padding-left:10px;" colspan="2">
										<ul id="fileList" style="margin-left: 5px;"></ul>
										<input type="hidden" value="${tache.attach }" id="attach" name="tache.attach"/>
									</td>
								</tr>
							<tbody style="display:none">
								<tr bgcolor="#F7FCFF">
									<td height="56" align="right" >
										处理人员模式
									</td>
									<td>
										<select name="tache.nameModel" onChange="changeEmpModel()">
											<option value="0">
												请选择模式
											</option>
											<option value="1">
												选择人员
											</option>
											<option value="2">
												上一环节选择
											</option>
											<option value="3">
												默认当前用户
											</option>
											<option value="4">
												 设置角色
											</option>
										</select>
									</td>
								</tr>
								<tr bgcolor="#F7FCFF" id="chooseperson" style=" display:none">
									<td align="right">
										处理人员
									</td>
									<td>
										<input type="text" name="tache.emp_names"
											value="${tache.emp_names }" />
										<input type="button" value="选择人员" onClick="getPerson('tache.guid','tache.emp_names')">
										<input type=hidden name="tache.guid" value="${tache.guid }" />
									</td>
								</tr>
								
								<tr bgcolor="#F7FCFF" id="chooseRole" style=" display:none">
									<td align="right">
										角色名称
									</td>
									<td>
										<input type="text" name="tache.roles_name" value="${tache.roles_name }" />
										<input type="button" value="请选择角色" onClick="getRoles()"/>
										<input type=hidden name="tache.roles_id" value="${tache.roles_id }" />
									</td>
								</tr>
								
								<tr bgcolor="#F7FCFF">
									<td height="42" align="right">
										环节属性
									</td>
									<td>
										<input type="text" name="tache.memo" value="${tache.memo }" />
									</td>
								</tr>
								<tr bgcolor="#F7FCFF">
									<td height="40" align="right">
										处理类型
									</td>
									<td>
											<select name="tache.is_back">
											 <option value="1" selected=selected>一个人处理</option>
											 <option value="2">所有同时处理</option>
											</select>
									</td>
								</tr>
								<tr bgcolor="#F7FCFF" style="dispaly:none">
									<td  align="right">
										时限天
									</td>
									<td>
										<input type="text" name="tache.days" value="${tache.days }"
											style="width: 50px" />
										时
										

									</td>
								</tr>
								<tr>
									<td height="72" align="right">
										环节转发
									</td>
									<td>
										<textarea cols="60" rows="5" name="desc_name" readonly=readonly>${fu:getEmplNamesByEmplIds(tache.tache_DESCRIPTION) }</textarea>
									    <input type="button" value="选择人员" onClick="getPerson('tache.tache_DESCRIPTION','desc_name')">
									    <input type=hidden name="tache.tache_DESCRIPTION" value="${tache.tache_DESCRIPTION }" />
									</td>
								</tr>
						</tbody>
								<tr bgcolor="#F7FCFF">
									<td height="45" colspan="3" align="center">
										<input type="button" onClick="returnSonValue()" value="提交">
										<input type="hidden" name="tache.tache_type" value="${tache.tache_type }" />
										<input type="hidden" name="tache.dotType" value="${tache.dotType }" />
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</form>
		</div>
		
		<div id="addMenuWin" iconCls="icon-save" title="关联---" style="width: 0;height: 0"></div> 
	</body>
</html>