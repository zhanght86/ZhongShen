<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>"/>
    <title>上传文件界面</title>
	<script type="text/javascript" src="js/uploadify/swfobject.js"></script>
  	<script type="text/javascript" src="js/uploadify/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="js/uploadify/jquery.uploadify.v2.1.4.js"></script>
	<script type='text/javascript' src='dwr/interface/dwrUtil.js'></script>
  	<script type='text/javascript' src='dwr/engine.js'></script>
	<link rel="stylesheet" href="css/right.css" type="text/css"></link>
	<link rel="stylesheet" type="text/css" href="css/uploadify.css">
	
	<script type="text/javascript">
		var files = new Array();
			
		$(function() {
			var ext='*.zip';
			var desc='请选择*.zip格式的文件';
	        $("#uploadify").uploadify({
	            'uploader'       : 'images/uploadify.swf',
	            'script'         : 'attachment/UploadServletSJZS',
	            'cancelImg'      : 'images/cancel.png',
	            'folder'         : 'uploads',
	            'queueID'        : 'fileQueue',
	            'auto'           : true,
	            'multi'          : true,
	            'method'		 : 'get',
	            'simUploadLimit' : 1,
	            "queueSizeLimit" : 1,//允许选择文件的数量
	            'fileExt'        : ext,
	            'fileDesc'       : desc,  //允许上传的文件类型  ；必须先设置fileExt 属性再设置fileDesc
	            'scriptData'	 : {'type':'${type}'},
	            'onComplete': function(event, ID, fileObj, response, data) {
                	var upfile = $.parseJSON(response);
                	if(upfile.file == "haved"){
                    	alert("文件已经存在！");
                    	}else{
                    		if (!isSelected(upfile.attid)) {
			                	window.parent.addAttachment(upfile.file, upfile.attid);
			                	listFile(upfile.file, upfile.attid);
			                	files.push(new Array(fileName, upfile.attid, 1));
                    		}
                     }
	             }, 
	            'buttonImg'     : "images/browse.gif"
	        });
	    });
	
	    //将上传的文件列表列出
		function listFile(fileName,attId,path) {
			var params = 'javascript:deleteFile(\'' + attId +'\',\''+ fileName +'\')';
			var html_str = '<li id=\"' + attId + '\">' + fileName + '<img src="images/del.gif" onclick=\"' + params + '\" style=\"margin-left:20px;cursor:pointer;\"/></li>';
	       	$("#fileList").append(html_str);
	     	if(attId!="" && attId!=null){
	       		$("#myup").hide();
	       	}
		}
	 
		//根据guid删除其所指定的文件
		function deleteFile(attId,fileName) {
			dwrUtil.delAttachUpdate(fileName, function(data) {
					if (true == data) {
						$("#" + attId).remove();
						window.parent.parent.removeFile(attId);
						$("#myup").show();
					}
				});
		}

		//判断文件是否已经被选择
		function isSelected(fileId) {
			for (var i = 0; i < files.length; i++) {
				if ( files[i][1] == fileId ) {
					return true;
				}
			}
			return false;
		}
	</script>
  </head>
  <body>
		<table cellpadding="0" cellspacing="0" border="1"  width="1000" class="table">
			<caption>
				本地文件上传
			</caption>
			<tr class="contentTd"  id="myup">
				<td style="font-weight:bold; text-align:right; padding-right:10px;width:20%">附件:</td>
				<td style="font-weight:bold; text-align:left; padding-left:10px; " valign="middle">
					<div id="fileQueue"></div>
					<input type="file" name="uploadify" id="uploadify" />
				</td>
			</tr>
			<tr class="contentTd">
				<td style="font-weight:bold; text-align:right; padding-right:10px;width:20%">附件列表:</td>
				<td style="font-weight:bold; text-align:left; padding-left:5px; " valign="middle">					
					<ul id="fileList"></ul>
				</td>
			</tr>
			 <tfoot>
				 <tr  class="contentTd">
					<td style="font-weight:bold; text-align:left; padding-left:10px;text-align: center; " colspan="2" align="center">
						<input type="button" value="&nbsp;" onclick="javascript:window.parent.close();" class="savebtn"/>
					</td>
				</tr>
			</tfoot>
		</table>
  </body>
</html>
