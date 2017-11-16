<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/view/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>" />
    
    <title>审计之家</title>
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<script language="javascript" src="js/fore/mainPage.js"></script>
	<script language="javascript" src="js/fore/wdzs.js"></script>
	
	
	
	
	<!-- 弹框 -->
	<%--<script src="js/treeView/scripts/jquery.js" type="text/javascript"></script>
	--%>	
    <link rel="stylesheet" type="text/css" href="js/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="js/themes/icon.css"/>
	<link rel="stylesheet" type="text/css" href="css/fore/mainPage.css" />
	<script type="text/javascript">
		function loadzhu(){
			$("td.grzx").attr("style","background-color:#7cb19b;");
			$("td.wdsc").attr("style","background-color:#d9d6c3;");
			$("div.wdsc_cont").attr("style","display:none");
			$("div.grzx_cont").attr("style","display:block");
		}
		function mouseMove(types){
			if(types=="1"){
				$("td.grzx").attr("style","background-color:#7cb19b;");
				$("td.wdsc").attr("style","background-color:#d9d6c3;");	
				$("div.wdsc_cont").attr("style","display:none");
				$("div.grzx_cont").attr("style","display:block");
						
			}else if(types == "0"){
				$("td.grzx").attr("style","background-color:#d9d6c3;");
				$("td.wdsc").attr("style","background-color:#7cb19b;");
				$("div.grzx_cont").attr("style","display:none");
				$("div.wdsc_cont").attr("style","display:block");
			}
		}		

		function tr_hover(obj){
			alert(obj.tags);
			$("tr.tab_cont_hover").attr("class","tab_cont");
			obj.attr("class","tab_cont_hover");
		}

		//翻页
		function goNextPage( currentPage ) {
			document.getElementById("reqPage").value = currentPage;
			document.forms.namedItem("listUploadF").submit();
		}
		function change(data){
			var src= "wdzs!"+data+".action";
			$("#wdzs_cont").attr("src",src);
		}
	</script>
  </head> 
  <body onload="chushi('${type}');loadzhu();">
    <div class="main_top">
    	<jsp:include page="mainPage_top.jsp"></jsp:include>
    </div>
	<script type="text/javascript" src="js/jquery.easyui.min.1.2.2.js"></script>
	<script type="text/javascript" src='js/locale/easyui-lang-zh_CN.js'></script>
	
    <div class="main_cont">
    	<div class="cont_cont" style="border-top:3px solid #7cb19b;">
   				<iframe id="wdzs_cont" src="wdzs!zsGrzs.action" frameborder="0" scrolling="no" style="width:100%;height:630px;border: none"></iframe>
   		</div>
    </div>
    <div style="clear:both;width:100%;height:20px;"></div>
    <div class="boot">
    	<jsp:include page="boot.jsp"></jsp:include>
    </div>
    <div id="upfile" iconCls="icon-save" title="上传文档" style="width: 0;height: 0"></div>
  </body>
</html>
