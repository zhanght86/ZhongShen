<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/view/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.0 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>个人中心</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/dwz/jquery-1.7.2.min.js"></script>
	<script language="javascript" src="js/fore/mainPage.js"></script>
	<link rel="stylesheet" type="text/css" href="css/fore/grzs.css" />
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

		function change( data){
			window.parent.change(data);
		}
	</script>
  </head>
  
  <body>
   	<div class="tag_main">
		<div class="wdzs_left">
			<div onclick="change('zsGrzs')" style="background-color: #7cb19b;">个人中心</div>
			<div onclick="change('zsWdsc')" style="background-color: #d9d6c3;">我的上传</div>
		</div>
		<div class="grzx_cont" >
			<div class="grzx_left">
				<div class="grzx_per_info">
					<b>${empl.emplName } </b>
					<b>等级:${client.rank } </b>
					<b>头衔:${client.title } </b>
					<img alt="张三" src="/sjzs/view/fore/tou.jpg" width="100%" />
					<span style="width:100%;text-align: center;">
						积分：${client.integral }
					</span>
				</div>
			</div>
			<div class="grzx_right">
				<div class="grzx_info">
					<table cellpadding="0" cellspacing="0">
						<tr>
							<td class="info_title"><b>基本信息</b></td>
							<td class="info_abc" colspan="7">&nbsp;</td>
						</tr>
						<tr style="height: 15px;"><td colspan="8"></td></tr>
						<tr class="info_main">
							<td colspan="2">
								<b>等级</b>
								<span>${client.rank }</span>
							</td>
							<td colspan="2">
								<b>积分</b>
								<span>${client.integral }</span>
							</td>
							<td colspan="2">
								<b>文档下载数量</b>
								<span>${client.downNum }</span>
							</td>
							<td style="border-right:#d1e4de 1px solid;" colspan="2">
								<b>文档上传数量</b>
								<span>${client.uploadNum}</span>
							</td>
						</tr>
					</table>
				</div>
				<div class="grzx_main">
					<table cellpadding="0" cellspacing="0">
						<tr class="main_title"> 
							<td colspan="4">
								<span>我的文档</span>
							</td>
						</tr>
						<tr class="main_cont_title" style="background-color: #f6fbfa;">
							<td width="250px"><span>文档名</span></td>
							<td width="80px"><span>所属类别</span></td>
							<td width="70px"><span>状态</span></td>
							<td width="80px"><span>上传时间 </span></td>
						</tr><%--
						<!-- 上面是标题，下面的是内容 -->
						<tr class="main_cont"
							onmousemove="changebg(this,'1')" 
										onmouseout="changebg(this,'0')"	>
							<td><span>文档标题</span></td>
							<td><span>所属类别</span></td>
							<td><span>状态</span></td>
							<td><span>上传时间 </span></td>
						</tr>
						--%>
						<c:forEach var="upload"  items="${pageUpload.list}">
						<tr class="main_cont"
							onmousemove="changebg(this,'1')" 
										onmouseout="changebg(this,'0')">
							<td ><span>${upload.caption }</span></td>
							<td ><span>
								<c:if test="${upload.type eq \"FG\" }">审计法规</c:if>
								<c:if test="${upload.type eq \"YJ\" }">定性依据</c:if>
								<c:if test="${upload.type eq \"EI\" }">实施方案</c:if>
								<c:if test="${upload.type eq \"AL\" }">审计方法</c:if>
							</span></td>
							<td ><span>
								<c:if test="${upload.checkFlag eq \"0\" }">待审核</c:if>
								<c:if test="${upload.checkFlag eq \"1\" }">审核中</c:if>
								<c:if test="${upload.checkFlag eq \"2\" }">审核通过</c:if>
								<c:if test="${upload.checkFlag eq \"3\" }">打回</c:if>
							</span></td>
							<td ><div style="width:70px;white-space: nowrap;overflow: hidden;">${upload.uploadDate }</div></td>
						</tr>
					</c:forEach> 																						
					</table>
				</div>
			</div>
		</div>
	</div>
  </body>
</html>
