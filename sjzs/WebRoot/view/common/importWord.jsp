<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>将Word文件导入到编辑器中</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script type="text/javascript">
			//上传word前验证用户是否选择了上传文件
			function upcheck() {
				var fileValue = document.getElementById("wordFile").value;
				if ('' == fileValue  ) {
					alert("请选择导入的Word文件！");
					return false;
				} else if ( fileValue.substring(fileValue.length-4,fileValue.length) != '.doc') {
					alert("上传文件的格式错误,请选择doc格式的文件!");
					return false;
				}
		   	//	document.getElementById("wordFile").disabled="disabled";
		  //   alert(document.getElementById("wordForm").value);
		 
				document.getElementById("wordForm").submit;
				//style="display: none;"
				document.getElementById("daoru").value="正在提交,请稍后！";
				 
				document.getElementById("wordFile").style.display = "none";
			
		        document.getElementById("wordFile1").style.display="";		
				//alert(document.getElementById("wordFile").disabled);
				document.getElementById("fieldsetId").display = "none";
				var statusSpan = document.getElementById("upstatus");
				statusSpan.style.display = "inline";
			}

			function closeHTMLDialog() {
				parent.window.returnValue = document.getElementById("impsuc").innerHTML;
				window.close();
			}
		</script>
	</head>
	<body bgcolor="#FFFFFF">
		<br />
		<fieldset id="fieldsetId">
			<legend>
				请选择导入的word文件
			</legend>
			<form id="wordForm" action="<%=basePath%>system/uploadWord.action"
				method="post" enctype="multipart/form-data" target="wordUp">
				<input type="file" name="filedata" id="wordFile" />
				<input type="file" name="filedata" id="wordFile1"
					disabled="disabled" style="display: none;" />
				<input type="submit" value="导入" onclick="return upcheck();" id="daoru" />
			</form>
		</fieldset>
		<span style="display: none;" id="impsuc">导出成功！</span>
		<span style="display: none;" id="upstatus">请稍后，正在导入文件......</span>
		<!-- 表单提交到IFRAME避免页面刷新 -->
		<div
			style="position: absolute; bottom: 0; left0: 50; height: 50; z-index: -1">
			<iframe name="wordUp" style="visibility: hidden;"></iframe>
		</div>
		<!-- 用于解决上传文件中有表格,页面底部出现残缺表格的现象 -->
		<div
			style="position: absolute; bottom: 0; left0: 50; height: 50; z-index: 200">
			<span id="wordUp1" style="visibility: hidden;"></span>
		</div>
	</body>
</html>
