<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/view/common/common.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>"/>
    <title>更新数据包列表页面</title>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	
    <link rel="stylesheet" type="text/css" href='css/style.css' />
	<script src="js/treeView/scripts/jquery.min.js" type="text/javascript"></script>
	<script type='text/javascript' src='js/treeView/scripts/jquery.contextmenu.r2-min.js'></script>
	<script src="js/treeView/scripts/jquery.treeview.js" type="text/javascript"></script>
	<link rel="stylesheet" href="js/treeView/css/jquery.treeview.css" />
	<link rel="stylesheet" type="text/css" href="js/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="js/themes/icon.css"/>
	<script type="text/javascript" src="js/jquery.easyui.min.1.2.2.js"></script>
	<script type="text/javascr  ipt" src='js/locale/easyui-lang-zh_CN.js'></script>
	<script type="text/javascript">

    function deleteFile(path){
    	if(confirm("您确定要删除么？")) {
    		var goURI = "system/attach!deleteFile.action?type=${type}";
    		document.getElementById("path1").value = path;
			document.deleteForm.action = goURI;
			document.deleteForm.submit();   
		}
     }

		
		//下载
		function downloadAttach(path) {
			var goURI = "system/attach!downFile.action?type=${type}";
			document.getElementById("path2").value = path;
			document.downloadForm.action = goURI;
			document.downloadForm.submit();  
		}

		
</script>
 <jsp:include page="/jsPage.jsp"></jsp:include>
  </head>
 <body style="overflow: scroll;">
<div id="navTab" class="tabsPage">
 <div class="pageHeader">
   <form id="deleteForm" name="deleteForm" action = "" method="post" style="display: none;">
     <input type="hidden"  value="" id="path1" name="path"/>
   </form>
   <form method="post" action="" id="downloadForm" name="downloadForm" style="display: none;">
    <input type="hidden"  value="" id="path2"  name="path"/>
   </form>
</div>
 

	<div id="w_list_print">
	<table class="list" width="100%" targetType="navTab" asc="asc" desc="desc" layoutH="118">
		<thead>
			<tr>	
				<th>序号</th>
		    	<th>数据包名称</th><%--	
		    	<th>路径</th>	
				--%><th>操作</th>	
			</tr>
		</thead>
		<tbody id = 'playList'>
			<s:iterator id="entry" value="attachList" status="sta">
				<tr rel="${sta.count }">
			  			<td>${sta.count+(page.curPage-1)*page.maxResult}</td>
			  			<td>
				    	${entry.attachName}
						</td>
						<%--<td>${entry.absolutePath }</td>--%>
						<td>
							 <a href="javascript:deleteFile('${entry.attachName }')" class="txt12">删除</a>
  							<a href="javascript:downloadAttach('${entry.attachName }')" class="txt12">下载</a>
						</td>
					</tr>
		  		</tr>
		  	</s:iterator>
		</tbody>
	</table>
	</div></div>
</div>
</body>
</html>
