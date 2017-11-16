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
		<title>更新数据上传功能</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<script type="text/javascript" src="js/tree/dtree/dtree.js"></script>
		<link rel="stylesheet" type="text/css" href="js/tree/dtree/dtree.css">
		<link rel="stylesheet" href="css/right.css" type="text/css"></link>
		<script type="text/javascript" src="/js/jquery-1.4.4.min.js"></script>
		<script type='text/javascript' src='dwr/interface/dwrUtil.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		
	<script src="js/treeView/scripts/jquery.min.js" type="text/javascript"></script>
	<script type='text/javascript' src='js/treeView/scripts/jquery.contextmenu.r2-min.js'></script>
	<script src="js/treeView/scripts/jquery.treeview.js" type="text/javascript"></script>
	<link rel="stylesheet" href="js/treeView/css/jquery.treeview.css" />
	<link rel="stylesheet" type="text/css" href="js/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="js/themes/icon.css"/>
	<script type="text/javascript" src="js/jquery.easyui.min.1.2.2.js"></script>
	<script type="text/javascript" src='js/locale/easyui-lang-zh_CN.js'></script>
	</head>
	<body style="background: url('images/bgimg.jpg') repeat;">
		<form name="fm" action="system/sjzs!excuteExport.action" method="post">
			<table cellpadding="0" cellspacing="0" border="1" class="table"
				style="font-size: 12px; margin-top: 5px; width: 100%; text-align: left;">
				<thead>
					<tr align="center">
						<th colspan="4">
							审计助手更新数据上传
						</th>
					</tr>
				</thead>
				<tbody>

						<tr class="contentTd">
						<td
							style="font-weight: bold; text-align: right; padding-right: 10px;"
							colspan="2">
							上传类型：
						</td>
						<td
							style="font-weight: bold; text-align: left; padding-left: 10px;"
							colspan="2">
							<input type="radio" name="type" value="0" checked="checked">程序或数据结构
							<input type="radio" name="type" value="1">数据
							<input type="button" value="查看已上传更新包"   onclick="searchUpdateData();">
						</td>
					</tr>
					<tr class="contentTd">
						<td
							style="font-weight: bold; text-align: right; padding-right: 10px;"
							colspan="2">
							上传附件：&nbsp;
						</td>
						<td
							style="font-weight: bold; text-align: left; padding-left: 10px;"
							colspan="2">
							<input type="button" value="&nbsp;" id="selectBtn"  class="selectUnit" onclick= "javascript: selectFile(); "/>
						</td>
					</tr>
					<tr class="contentTd">
						<td
							style="font-weight: bold; text-align: right; padding-right: 20px;"
							colspan="2">
							附件列表:
						</td>
						<td align="left" style="padding-left: 10px;" colspan="2">
							<ul id="fileList" style="margin-left: 5px;"></ul>
							<input type="hidden"  id="attachId" value="" />
						</td>
					</tr>
					<tr>
					 <td colspan="4" align="center">
					  <input type="button" value="提交上传"  id="sub" onclick='onSubmit();'>
					  <input type="button" value="生成数据更新包" id='export' width="50px;" onclick='loadDiv();exceuteExport();' onmouseover='getUpdateTime();' onmouseout='moveUpdateTime();'>
					  <input type="hidden" value="<s:date name="updateDataLog.uploadDate" format="yyyy/MM/dd HH:mm:ss"/>"  id="updateTime" >
					  
					 </td>
					</tr>
					<tr>
						<td colspan="4" align="left" style="color: red;">
						说明：①、更新数据上传分两种类型；程序和数据结构：程序更新时需要有完整的目录结构，以压缩包的形式上传；数据更新：只需要把相应的sql打包上传。<br/>
						②、上传程序更新包名称格式为<font color="blue">V1.0_sjzs.zip(初始版本为V1.0_sjzs.zip)</font>,扩展名必须为zip。<br/>
						③，数据更新包为程序自动生成，可点击查看按钮，查看当前的所有数据更新包的版本！
						</td>
					</tr>
				</tbody>
			</table>
		</form>
			<div id="showUpdateData" iconCls="icon-save" title="已上传更新数据包查看" style="width: 0;height: 0"></div> 
			<div id="load_div" style="filter:progid:DXImageTransform.Microsoft.Alpha(opacity=80); position:relative; left:0px; 
			top:0px; width:100%;display: none;">
			<table bgcolor="#FBE4B5" style="border: 0px;" width="100%">
				<tr>
					<td></td>
					<td  style="border: 0px;" width="100%" align="center" valign="bottom"><img src="images/c.gif"/></td>
				</tr>
				<tr align="center">
					<td></td>	
					<td style="border: 0px;">正在处理数据，请稍等...</td>
					<td></td>
				</tr>
			</table>
	</div>
	</body>
	<script type="text/javascript">
	function getUpdateTime(){
	    var updateTime =$("#updateTime").val();
	    if(updateTime != "" && updateTime!=null && updateTime!="undefined"){
	    	$("#export").css("width","200px");
	    	$("#export").val("上次更新："+updateTime);
		}
	}

	function moveUpdateTime(){
		$("#export").val("生成数据更新包");
		$("#export").css("width","200px");
	}
	
	var type="";
	//打开文件选择窗口
	function selectFile() {			
		var width = "920";
		
		var obj = document.getElementsByName("type");
		for(i=0;i<obj.length;i++){
			if(obj[i].checked){
				type = obj[i].value;
			break;
			}
		}		
		if(type == "1"){
			alert("数据更新包不需要上传，请点击更新按钮自动生成！");
			}else{
				var url = "<%=basePath%>system/attach!selectUpdateDataFile.action?type="+type+"&nocache=" + new Date().getTime();
				var returnVal = window.showModalDialog(url, null, "center=yes;dialogWidth="+width+"px;dialogHeight=600px; edge:Raised; directories:no; localtion:no; menubar:no; status=yes; toolbar=yes;scroll:yes;help=no");
				
				if (returnVal != undefined && returnVal != null) {//如果用户选择了文件
					for (var i = 0; i < returnVal.length; i++) {
						if (returnVal[i][2] == 1) {//如果是上传的附件
							listFile(returnVal[i][0],returnVal[i][1]);
						} 
					}
				}
			}
	}
	var html_str;
	//将上传的文件列表列出
	function listFile(fileName,attId) {
		//var attachId = attId.split("&")[0];
		//var path = attId.split("&")[1];
		//var params ='javascript:deleteFile(\'' + attId +'\',\''+ path +'\',\''+ fileName+'\')';
		 html_str = "<li id=\"" + fileName  +"\">" + fileName + "</li>";
       	$("#fileList").append(html_str);
       	document.getElementById("attachId").value = fileName;
      //	document.execCommand('Refresh') ;
	}

	//根据guid删除其所指定的文件
	function deleteFile(attId,fileName) {
		dwrUtil.delAttachUpdate(fileName, function(data) {
				if (true == data) {
					$("#" + fileName).remove();
					document.getElementById("attachId").value = "";
				}
			});
	}

	function onSubmit() {
		var attId = document.getElementById("attachId").value;
		if(attId != "" && attId !="undefined" && attId !=null && attId !="NaN"){
		//	$("#" + attId).remove();
			$("ul").find("li").remove(); 
			alert("上传成功！");
			}else{
				alert("请选择上传文件！");
			}
	}


	function searchUpdateData() {
		var obj = document.getElementsByName("type");
		for(i=0;i<obj.length;i++){
			if(obj[i].checked){
				type = obj[i].value;
			break;
			}
		}
		
		var reqURL = "<%=basePath%>attach!getUpdateDatas.action?type="+type+"&nocache=" + new Date().getTime();
		$('#showUpdateData').window({   
			width:700,   
			height:350,   
			modal:true,
			collapsible:false,
			minimizable:false, 
			maximizable:false,
			closable:true
		});
		var iframe_str = "<iframe scrolling=\"yes\" height=\"100%\" width=\"100%\" frameborder=\"0\" src=\"" + reqURL + "\"></iframe>";
		$('#showUpdateData').html(iframe_str);
	}

	function loadDiv() {
		$("#ls_div_div").stop(true, true).hide();
		$("#load_div").stop(true, true).fadeIn(300);
		//setTimeout('unLloadDiv()', 3000)
	}
	function unLloadDiv() {
		$("#ls_div_div").stop().fadeIn(100);
		$("#load_div").stop(true, true).fadeOut(300);
		document.getElementById("export").disabled="";
		document.getElementById("sub").disabled="";
		document.getElementById("selectBtn").disabled="";
	}
 	function exceuteExport(){
 	 	//document.forms["fm"].submit();
 	 	document.getElementById("export").disabled="disabled";
 		document.getElementById("sub").disabled="disabled";
 		document.getElementById("selectBtn").disabled="disabled";
 		
		$.ajax({
			url:"<%=basePath%>system/sjzs!executeWork.action",
			type: "POST",
			success: function(data){
				var flag = data.split("#");
				if(flag[0]=="OK"){
					document.getElementById("updateTime").value=flag[1];
					alert("导出数据成功！");
				}else{
					alert("导出数据失败，请联系管理员！");
					}
			    unLloadDiv();
			}
		});
	}

</script>
</html>
