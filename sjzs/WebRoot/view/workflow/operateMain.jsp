<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>">
		<title>跳转列表</title>
		<link rel="stylesheet" href="css/right.css" type="text/css"></link>
		<link rel="stylesheet" href="js/resources/css/all.css" type="text/css"></link>
		<script type="text/javascript" src="js/base.js"></script>
		<script type="text/javascript" src="js/all.js"></script>
		<script type="text/javascript">
		var win;
		function onlineFile(upid, uptype) {			
			createDIV();			
					
			//window的宽度和高度
			var win_width = document.getElementsByTagName('body')[0].clientWidth-10 ;	
			var win_height = self.parent.document.body.offsetHeight;
			var reqURL = "<%=basePath%>attachment!showAttach.action?attachment.fileId="+upid+"&attachment.fileType=" + uptype + "&time=" + new Date().getTime()+"&type=ok";
	
			if (!win) {
				win = new Ext.Window({
					applyTo : 'hello-win',
					title : '文档在线查看',
					layout : 'fit',
					width : win_width,
					height : win_height,
					closeAction : 'close',
					plain : false,
					items : [{
						title : '文档在线查看',
						header : false,
						html : '<iframe style="border-top-width: 0px; border-left-width: 0px; border-bottom-width: 0px; width: ' + win_width + 'px; height: '+win_height+'px; border-right-width: 0px" src="' + reqURL + '" frameborder="0" width="100%" scrolling="yes" height="100%">',
						border : false
					}]
				});
			}
			win.show();
			//将window的位置设置为窗口的左上角
			win.setPosition(0,0)
		}

			//将window设置为closeAction : 'close'时会将<div id="hello-win" class="x-hidden"></div>从DOM树中移除
			//所以再次显示window时需要再创建一个<div id="hello-win" class="x-hidden"></div>
			function createDIV() {
				var _div = document.getElementById('hello-win');
				if (null == _div) {
					document.getElementById('outer').innerHTML = '<div id="hello-win" class="x-hidden"></div>';
				}
			}
			//关闭附件查看页面
			function closeShowDiv(){
					win.hide();
			}

			function reqUrl(){
				var allName='${allName}';
				var tachememo='${tachememo}';
				allName=encodeURI(allName=encodeURI(allName));
				tachememo=encodeURI(tachememo=encodeURI(tachememo)); 
				var  url ="<%=basePath%>system/workflow!toAddTacheInfo.action?lineLikeList=${lineLikeList }&allName="+allName+"&num=${num }&tachememo="+tachememo+"&type=${type}";
					document.getElementById("main").innerHTML="<iframe frameborder=\"0\" scrolling=\"auto\" width=\"100%\" height=\"100%\" src="+url+"></iframe>"
				}
		</script>
	</head>
<body onload="reqUrl()">
	<div id="main" align="center">
		
	</div>
	 <div id="outer">
	<div id="hello-win" class="x-hidden"></div>
</div>
</body>
</html>