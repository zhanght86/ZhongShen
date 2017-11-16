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
    
    <title>我的上传</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/dwz/jquery-1.7.2.min.js"></script>
	<link rel="stylesheet" type="text/css" href="js/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="js/themes/icon.css"/>
	<script type="text/javascript" src="js/jquery.easyui.min.1.2.2.js"></script>
	<script type="text/javascript" src='js/locale/easyui-lang-zh_CN.js'></script>
	<script language="javascript" src="js/fore/mainPage.js"></script>
	<script language="javascript" src="js/fore/wdzs.js"></script>
	<link rel="stylesheet" type="text/css" href="css/fore/wdsc.css" />
	<style type="text/css">
		a:link {text-decoration: none ; color: black;}  
		a:active {text-decoration: none;;}                                      
		a:visited {text-decoration: none;color:blue;}                          
		a:hover {text-decoration: underline ; color: red;}
	</style>
	<script type="text/javascript">		
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
		//翻页
		function goNextPage( currentPage ) {
			document.getElementById("reqPage").value = currentPage;
			document.forms.namedItem("listUploadF").submit();
		}
		function change( data){
			window.parent.change(data);
		}
		function showcont(data){
			window.parent.location.href="wdzs!showCont.action?showStr="+data;
		}

		function lookUpCheckLog(uploadId){
			var url = "wdzs!showCheckLog.action?uploadId=" + uploadId + "&nocache=" + new Date().getTime();
			window.showModalDialog(url,null,"dialogWidth=600px;dialogHeight=350px;top=0;left=0;toolbar=no;location=no;titlebar=no;menubar=no");
		}
	</script>
  </head>
  
  <body>
   	<div class="tag_main">
		<div class="wdzs_left">
			<div onclick="change('zsGrzs')" style="background-color: #d9d6c3;">个人中心</div>
			<div onclick="change('zsWdsc')" style="background-color: #7cb19b;">我的上传</div>
		</div>
		<div class="wdsc_cont">
			<div class="wdsc_but">
				<input type="button" value="上传" onclick="upFile('wdzs!upFile.action',800,600)" />&nbsp;&nbsp;&nbsp;
				<input type="button" value="删除" onclick="del()" />
				<form action="wdzs!deleteClientUp.action" id="deleteForm" name="deleteForm">
					<input type="hidden" name="dropStr" id="dropStr" value="">
				</form>
			</div>
			<div class="wdsc_main">
			<form action="" method="post" id="listUploadF" name="listUploadF">
			<input type="hidden" value="<s:property value="pageUpload.curPage"/>" name="pageUpload.curPage" id="reqPage" />
				<table cellpadding="0" cellspacing="0">
					<tr class="tab_tit" style="height:30px;background-color: #f9fbfa;">
						<td width="30px" style="border-left:none;"><span><input onclick="negation()" type="checkbox" /></span></td>
						<td width="300px"><span>名称</span></td>
						<td width="80px"><span>类型</span></td>
						<td width="95px"><span>创建时间</span></td>
						<td width="85px" ><span>状态</span></td>
						<td width="70px" ><span>审核日志</span></td>
					</tr>
					<%--
					
					<tr class="tab_cont" 
						style="height:35px;background-color: #ffffff"
						onmousemove="changebg(this,'1')" 
									onmouseout="changebg(this,'0')"	>
						<td ><span><input type="checkbox" /></span></td>
						<td ><span>名称</span></td>
						<td ><span>类型</span></td>
						<td ><span>上传时间</span></td>
						<td ><span>状态</span></td>
					</tr>
					
					--%>
					<s:iterator id="upload" value="pageUpload.list" status="sta">
						<tr class="tab_cont"  style="height:35px;background-color: #ffffff;" onmousemove="changebg(this,'1')"  onmouseout="changebg(this,'0')"	>
							<td ><span><input name="uploadL" type="checkbox" id="${upload.id}_${upload.type}_${upload.checkFlag}"  /></span></td>
							<td ><span><a href="javascript:void(0)" onclick="showcont('${upload.id}_${upload.type}')" >${upload.caption }</a></span></td>
							<td ><span>
								<c:if test="${upload.type eq \"FG\" }">审计法规</c:if>
								<c:if test="${upload.type eq \"YJ\" }">定性依据</c:if>
								<c:if test="${upload.type eq \"EI\" }">实施方案</c:if>
								<c:if test="${upload.type eq \"AL\" }">审计方法</c:if>
							</span></td>
							<td ><div style="width:70px;white-space: nowrap;overflow: hidden;">${upload.uploadDate }</div></td>
							<td ><span>
								<c:if test="${upload.checkFlag eq \"0\" }">待审核</c:if>
								<c:if test="${upload.checkFlag eq \"1\" }">审核中</c:if>
								<c:if test="${upload.checkFlag eq \"2\" }">审核通过</c:if>
								<c:if test="${upload.checkFlag eq \"3\" }">打回</c:if>
							</span></td>
							<td align="center" >
								<c:if test="${!(upload.checkFlag eq \"0\")}">
									<%--
									<input type="button" value="查看" onclick="upFile('wdzs!showCheckLog.action?uploadId=${upload.id}',800,600)" /> 
									 --%>
									 <input type="button" value="查看" onclick="lookUpCheckLog('${upload.id}')" /> 
								</c:if>
							</td>		
						</tr>
					</s:iterator>   											 											   											
				</table>
				<div style="width:600px;text-align: left;margin-bottom: 0px;margin-top:20px;">
				 <div class="pages" style="text-align: center; width: 95%">
						<table style="width: 100%">
							<tr>
								<td style="text-align: right;">
									<a style="color: #1E5494;">总记录数：${pageUpload.totalRecords} 条</a>&nbsp;&nbsp;
									<c:choose>
										<c:when test="${ 1 == pageUpload.curPage}">
											<a>首页</a>
											<a>上一页</a>
										</c:when>
										<c:otherwise>
											<a href="" onClick="goNextPage(1);return false;">首页</a>
											<a href=""
												onClick="goNextPage(${pageUpload.curPage - 1 });return false;">上一页</a>
										</c:otherwise>
									</c:choose>
									<c:forEach begin="${pageUpload.beginIndex}" end="${pageUpload.endIndex}"
										var="pageCount" step="1">
										<c:choose>
											<c:when test="${ pageCount == pageUpload.curPage}">
												<a href="" onClick="goNextPage(${pageCount });return false;"
													style="color: red;">${pageCount }</a>
											</c:when>
											<c:otherwise>
												<a href="" onClick="goNextPage(${pageCount });return false;">${pageCount}</a>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									<c:choose>
										<c:when test="${ pageUpload.curPage >= pageUpload.totalPage}">
											<a>下一页</a>
											<a>末页</a>
										</c:when>
										<c:otherwise>
											<a href=""
												onClick="goNextPage(${pageUpload.curPage + 1});return false;">下一页</a>
											<a href=""
												onClick="goNextPage(${pageUpload.totalPage });return false;">末页</a>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</table>
					</div>
			        </div>
				</form>
			</div>
		</div>
	</div>
	 <div id="upfile" iconCls="icon-save" title="上传文档" style="width: 0;height: 0"></div>
  </body>
</html>
