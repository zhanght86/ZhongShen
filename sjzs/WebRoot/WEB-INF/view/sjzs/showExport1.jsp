<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/view/common/common.jsp"  %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String flag = request.getParameter("flag");
if(flag!=null &&flag.trim().equals("ok")){
	%>
	<script type="text/javascript">
	alert('导出成功！');
</script>
	
	<%
	
}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>显示所有审计助手信息</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<script type="text/javascript" src="js/tree/dtree/dtree.js"></script>
	<link rel="stylesheet" type="text/css" href="js/tree/dtree/dtree.css">
	<link rel="stylesheet" href="css/right.css" type="text/css"></link>
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
  </head>
  <body style="background: url('images/bgimg.jpg') repeat;">
	 <form name="fm" action="system/sjzs!excuteExport.action" method="post" > 
  <table cellpadding="0" cellspacing="0" border="1" class="table" style="font-size: 12px;margin-top:5px;width: 100%;text-align: left;">
  <thead>
   <tr align="center">
   <th colspan="4">导出信息展示</th>
   </tr>
  </thead>
  <tbody>
    <tr>
      <td style="width: 25%;">
      <script type="text/javascript">
	 	d = new dTree('d');
	 	d.config.folderLinks=true;
	    d.add("11111111","-1",'审计法规','javascript:void(0);','审计法规',''); //创建一个树对象   
		<c:forEach items="${sjfgList}" var="sjzhMenuTree">
			d.add('${sjzhMenuTree.menuId}','${sjzhMenuTree.menuParent}','${sjzhMenuTree.menuName}',"javascript:void(0);");
		</c:forEach>
	   	document.write(d);
		d.closeAll();
	</script>
      </td>
      <td style="width: 25%;">
      <script type="text/javascript">
	 	d1 = new dTree('d1');
	 	d1.config.folderLinks=true;
	    d1.add("22222222","-1",'定性依据','javascript:void(0);','定性依据',''); //创建一个树对象   
		<c:forEach items="${dxyjList}" var="sjzhMenuTree">
			d1.add('${sjzhMenuTree.menuId}','${sjzhMenuTree.menuParent}','${sjzhMenuTree.menuName}',"javascript:void(0);");
		</c:forEach>
	   	document.write(d1);
		d1.closeAll();
	</script>
	</td>
      <td style="width: 25%;">
      <script type="text/javascript">
	 	d3 = new dTree('d3');
	 	d3.config.folderLinks=true;
	    d3.add("33333333","-1",'方法案例','javascript:void(0);','方法案例',''); //创建一个树对象   
		<c:forEach items="${ffalList}" var="sjzhMenuTree">
			d3.add('${sjzhMenuTree.menuId}','${sjzhMenuTree.menuParent}','${sjzhMenuTree.menuName}',"javascript:void(0);");
		</c:forEach>
	   	document.write(d3);
	   	d3.closeAll();
	</script>
	</td>
      <td style="width: 25%;">
       <script type="text/javascript">
	 	d2 = new dTree('d2');
	 	d2.config.folderLinks=true;
	    d2.add("44444444","-1",'审计导航','javascript:void(0);','审计导航',''); //创建一个树对象   
		<c:forEach items="${sjdhList}" var="sjzhMenuTree">
			d2.add('${sjzhMenuTree.menuId}','${sjzhMenuTree.menuParent}','${sjzhMenuTree.menuName}',"javascript:void(0);");
		</c:forEach>
	   	document.write(d2);
	    d2.closeAll();
	</script>
      </td>
    </tr>
  </tbody>
  </table>
  	<div id="ls_div_div">
  <table width="100%">
				<tr>
					<td colspan="4" align="center">
						<input type="button" value="导出" id='export'  onclick='loadDiv();exceuteExport();'>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						提示：导出功能会将上述信息一次性全部导出，请耐心等待！
					</td>
				</tr>
  </table>
		</div>
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

	</form>
  </body>
    <script type="text/javascript">

	function loadDiv() {
		$("#ls_div_div").stop(true, true).hide();
		$("#load_div").stop(true, true).fadeIn(300);
		//setTimeout('unLloadDiv()', 3000)
	}
	function unLloadDiv() {
		$("#ls_div_div").stop().fadeIn(100);
		$("#load_div").stop(true, true).fadeOut(300);
	}
	var basePath = "<%=basePath%>";
 	function exceuteExport(){
 	 	document.forms["fm"].submit();
 	 	document.getElementById("export").disabled="disabled";
 	 	
	//	$.ajax({
		//	url:basePath + "system/sjzs!excuteExport.action",
		
		//	type: "POST",
		//	success: function(data){
		//		if(data=="OK"){
		//			alert("导出数据成功！");
		//		}else{
		//			alert("导出数据失败！");
		//			}
		//	    unLloadDiv();
		//	}
		//});
	}
</script>
</html>
