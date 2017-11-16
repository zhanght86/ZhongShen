<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.hnzskj.common.Constant"%>
<%@ page import="com.hnzskj.persist.bean.sjzs.SjzhMenuTree"%>
<%@ include file="/view/common/common.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("com", Constant.COMPANY);
	request.setAttribute("department", Constant.DEPARTMENT);
	SjzhMenuTree tree = (SjzhMenuTree) request
			.getAttribute("sjzhMenuTree");
	int menutype = tree.getMenuType();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>" 　target="_self" />
		<title>导入信息：</title>
		<link rel="stylesheet" href="css/right.css" type="text/css"></link>

		<script type="text/javascript" src="js/dataCheck.js"></script>
		<script language="javascript" src='js/showdialog.js'></script>
		<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="js/jquery.divbox.js"></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/interface/dwrUtil.js'></script>
		<link href="css/style.css" type="text/css" rel="stylesheet" />
		<style>
		#divSCA {
            position: absolute;
            width: 180px;
            height: 100px;
            display: none;
        }
       
	</style>
		<script type="text/javascript">
	
	//为有readonly属性 的元素取消退格键
		$(function(){$("input").each(function(){var readonly = $(this).attr("readonly");if(readonly){$(this).keydown(function(){var keydown = event.keyCode;if(keydown){return false;}});}});});
		//提交前进行数据验证
		function submitMenuCheck() {
			var result = true;
			result = promptMsg(textCheck('menuName',1,200), '菜单名称不可为空','info1') && result;
			return result;
		}



	

		
	</script>
	
	</head>
	<body>
		<%
			if (menutype == 2 || menutype == 5) {
		%>
		<form method="post" action="stencil/DownLoadServletStencil" id="downloadForm" name="downloadForm" style="display: none;">
					<input type="hidden" name="attId" id ='attId' value=""/>
					<%--<input type="button" value = "下载" onclick="downloadAttach('<s:property value="attach.attachId"/>')">--%>
	</form>
		<div id="divSCA"><center>
		
            <img src="images/c.gif"/>
            <BR>
	    <font>正在提交,请稍后...</font>
	    </center>
	    </div>
		<div align="center">
			<form action="system/sjzs!daoRuInfo.action" name="form1"
				method="post" target="_self" id="from1"
				enctype="multipart/form-data">
				<input type="hidden" value="${sjzhMenuTree.menuType}"
					name="sjzhMenuTree.menuType" />
				<input type="hidden" value="${sjzhMenuTree.menuId}"
					name="sjzhMenuTree.menuId" />
				<input type="hidden" name="sjzhMenuTree.menuName"
					value="${sjzhMenuTree.menuName}" />

				<table width="99%" border="0" cellspacing="0" cellpadding="0"
					class="border_A6C4DC">
					<tr class="title_style">
						<td colspan="8" style="text-align: left">
							<img src="images/group1.png" width="20" height="20"
								align="absmiddle" />
							导入信息
						</td>
					</tr>
					<tr class="row_bg">
						<td colspan="8">
							<table width="99%" border="0" cellspacing="0" cellpadding="0"
								class="nonborder2">


								<td align="right" bgcolor="#FFFFFF">
									上传execl文件：
								</td>
								<td align="left" bgcolor="#FFFFFF">
									<input type="file" name="filedata" id="wordFile" />


									<span id="info3"><font color="red">*必填</font> </span>

								</td>
								<td align="right" bgcolor="#FFFFFF">
									&nbsp;
								</td>
								<td align="left" bgcolor="#FFFFFF">
									&nbsp;
								</td>
								</tr>
                                
								<tr>
									<td colspan="2" bgcolor="#FFFFFF">
										<input type="submit" value="&nbsp;&nbsp;导&nbsp;&nbsp;&nbsp;&nbsp;入 &nbsp;&nbsp;"
											onclick="return upcheck();" id="daoru" />
									</td>
									
									
								</tr>
								<tr><td align="right" bgcolor="#FFFFFF">
									&nbsp;
								</td>
								<td align="left" bgcolor="#FFFFFF">
									&nbsp;
								</td><td align="right" bgcolor="#FFFFFF">
									&nbsp;
								</td>
								<td align="left" bgcolor="#FFFFFF">
									&nbsp;
								</td></tr>
                                <tr><td align="right" bgcolor="#FFFFFF">
									&nbsp;
								</td>
								<td align="left" bgcolor="#FFFFFF">
									&nbsp;
								</td><td align="right" bgcolor="#FFFFFF">
									&nbsp;
								</td>
								<td align="left" bgcolor="#FFFFFF">
									&nbsp;
								</td></tr>
								<tr>
									
									<td colspan="2" bgcolor="#FFFFFF"></td>
									<td colspan="2" bgcolor="#FFFFFF">
									
									<%if( menutype == 5){%>
									   <img src="images/xt_12.gif" width="19" height="19"  />
									   <a href="javascript:downloadAttach('1')" class="txt12"><font color="red"> 模板下载</font><br></a>
									   <%}else{ %>
									   <img src="images/xt_12.gif" width="19" height="19"  />
									   <a href="javascript:downloadAttach('2')" class="txt12"><font color="red"> 模板下载</font><br></a>
									   <%} %>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</form>




		</div>
		<%
			} else {
				if (menutype == 1 || menutype == 3 || menutype == 6) {
		%>
		<div id="divSCA"><center>
		
            <img src="images/c.gif"/>
            <BR>
	    <font>正在提交,请稍后...</font>
	    </center>
	    </div>
		<div align="center">
			<form action="system/sjzs!daoRuInfoAttach.action" name="form1"
				method="post" target="_self" id="from1">
				<input type="hidden" value="${sjzhMenuTree.menuType}"
					name="sjzhMenuTree.menuType" />
				<input type="hidden" value="${sjzhMenuTree.menuId}"
					name="sjzhMenuTree.menuId" />
				<input type="hidden" name="sjzhMenuTree.menuName"
					value="${sjzhMenuTree.menuName}" />

				<table width="99%" border="0" cellspacing="0" cellpadding="0"
					class="border_A6C4DC">
					<tr class="title_style">
						<td colspan="8" style="text-align: left">
							<img src="images/group1.png" width="20" height="20"
								align="absmiddle" />
							导入信息
						</td>
					</tr>
					<tr class="row_bg">
						<td colspan="8">
							<table width="99%" border="0" cellspacing="0" cellpadding="0"
								class="nonborder2">


								<td align="right" bgcolor="#FFFFFF">
									上传压缩包文件：
								</td>
								<td align="left" bgcolor="#FFFFFF">

									<input type="button" value=" &nbsp;" class="selectUnit"
										onclick="javascript:selectFile()" />

									<span id="info3"><font color="red">*必填</font> </span>


								</td>
								<td align="right" bgcolor="#FFFFFF">
									&nbsp;
								</td>
								<td align="left" bgcolor="#FFFFFF">
									&nbsp;
								</td>
								</tr>
								<tr>
									<td colspan="4" bgcolor="#FFFFFF">
										<ul id="fileList" style="margin-left: 5px;"></ul>
										<input  type="hidden" id="ids" name="attIds" value="1"/>
									</td>
								</tr>
								<tr>
									<td colspan="4" bgcolor="#FFFFFF">
										<input type="submit" value="&nbsp;导&nbsp;入 &nbsp;"
											onclick="return upcheckzip();" id="daoru" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>

			</form>

		</div>



		<%
			} else {
		%>
		<script type="text/javascript">
		alert("此节点不能导入");
		window.close();
		parent.window.close();
		</script>
		<%
			}
			}
		%>
	</body>

	<script type="text/javascript">

	//打开文件选择窗口
	function selectFile() {	
		if((document.getElementsByName("attIds").length)>1){
          alert("只能导入一个压缩包");

			}else{
		var width = "920";
		var url = "<%=basePath%>attach!selectFileAttach.action?type=3&nocache=" + new Date().getTime();
		var returnVal = window.showModalDialog(url, null, "center=yes;dialogWidth="+width+"px;dialogHeight=600px; edge:Raised; directories:no; localtion:no; menubar:no; status=yes; toolbar=yes;scroll:yes;help=no");
		
		if (returnVal != undefined && returnVal != null) {//如果用户选择了文件
			for (var i = 0; i < returnVal.length; i++) {
				if (returnVal[i][2] == 1) {//如果是上传的附件
					listFile(returnVal[i][0],returnVal[i][1]);
				} 
			}
		}
	}}
			//上传word前验证用户是否选择了上传文件
			function upcheck() {
			var  fileValue=	document.getElementById("wordFile").value;
				if ('' == fileValue  ) {
					alert("请选择导入的Execl文件！");
					return false;
				} else{ if ( fileValue.substring(fileValue.length-4,fileValue.length) == '.xls'||fileValue.substring(fileValue.length-5,fileValue.length) == '.xlsx') {

					
				}else{
					alert("上传文件的格式错误,请选择xls或xlsx格式的文件!");
					return false;
				}
				}
				openDiv();
				document.getElementById("daoru").submit;
				document.getElementById("daoru").value="正在提交,请稍后！";
				
			}

			function openDiv() {
				$("#divSCA").OpenDiv();
				$("#load_div").stop(true, true).fadeIn(300);
			}

			//上传word前验证用户是否选择了上传文件
			function upcheckzip() {
				
				if((document.getElementsByName("attIds").length)>1){
					document.getElementById("daoru").value="正在提交,请稍后！";
					//alert($("#daoruMenuWin"));
					//$("#daoruMenuWin").parentNode.attr("disabled","disabled");
					openDiv();
					document.getElementById("daoru").submit;
				}else{
					alert("请导入压缩包");
					return false;
				
				}
			}
			function closeHTMLDialog() {
				parent.window.returnValue = document.getElementById("impsuc").innerHTML;
				window.close();
			}


			//将上传的文件列表列出
			function listFile(fileName,attId) {
		 
				var oldAttId = $("#attachId").val();
				if(oldAttId!=null&&""!=oldAttId){
					deleteFile(oldAttId);
				}
				
				var params = 'javascript:deleteFilebyname(\'' + attId + '\',\'' + fileName + '\')';
				var html_str = "<li id=\"" + attId + "\">" + fileName + "<img src=\"images/del.gif\" onclick=\"" 
						+ params + "\" style=\"margin-left:20px;cursor:pointer;\"/><input type=\"hidden\" value=\""
						+attId+"_"+ fileName  + "\" name=\"attIds\" id=\"" +"s"+ attId + "\"/></li>";
		       	$("#fileList").append(html_str);
		       	$("#attachId").val(attId);
		       //	document.getElementById("ids").value = ;
		      //	document.execCommand('Refresh') ;
			}
		 
			//根据guid删除其所指定的文件
			function deleteFile(attId) {
				
				attachSJZS.delAttachJS(fileName, function(data) {
						if (true == data) {
							$("#" + attId).remove();
							document.getElementById("attachId").value = "";
						}
					});
			}


			//根据guid删除其所指定的文件
			function deleteFilebyname(attId,fileName) {
				dwrUtil.deleteByname(attId+"_"+fileName, function(data) {
						if (true == data) {
							$("#" + attId).remove();
							$("#s" + attId).remove();
						}
					});
			}


			//下载
			function downloadAttach(curchaId) {
				document.getElementById("attId").value = curchaId;
				if(document.downloadForm.attId!=""){
						document.downloadForm.submit();   //获取所有的法规信息
				}else{
					alert("没有要下载的文件！");
				}
			}
					
		</script>


</html>
