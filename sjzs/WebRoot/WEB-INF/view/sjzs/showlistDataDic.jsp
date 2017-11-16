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
    <title>审计事项</title>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/> 
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<style type="text/css">
		#tooltip{
			position: absolute;
			z-index: 2;	
			background: #efd;
			border: 1px solid #ccc;
			padding: 3px;
		}	
	</style>
	<link rel="stylesheet" type="text/css" href="js/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="js/themes/icon.css"/>
    <jsp:include page="/jsPage.jsp"></jsp:include>
    <script src="js/dataCheck.js" type="text/javascript"></script>
	<script language="javascript" src="js/public.js" charset="utf-8"></script>
	<script src="js/treeView/scripts/jquery.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.1.2.2.js"></script>
	<script type="text/javascript" src='js/locale/easyui-lang-zh_CN.js'></script>
	<script type="text/javascript">
	function goNextPage( currentPage ) {
		document.getElementById("reqPage").value = currentPage;
		document.forms.namedItem("searchF").submit();
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

		function getSjId(tid,tname){
			var turl ="system/dataDic!showsearchMessage.action?dataDicDto.dicId="+tid+"&dataDicDto.dicParentId=${dataDicDto.dicParentId}";
		//	var turl ="system/dataDic!showsearchMessage.action?dataDicDto.dicId="+tid;
			//window.parent.closeWinForm(url);
			var rs = {id:tid,name:tname,url:turl};
			parent.window.returnValue= rs; 
		    window.close(); 
		}	

		$(function(){
			function dymShowText(column,nameLen,width){
				$("tbody td:nth-child("+column+")").mousemove(function(event){
					$("#tooltip").remove();
					var dicMemo = $(this).children("input").val();
					if(dicMemo != null && dicMemo !=""){
						$('<div id="tooltip">'+dicMemo+'</div>').css("width",width).appendTo("body");
							var tPosX = (event.pageX - 5) + 'px';
							var tPosY = (event.pageY + 20) + 'px';
							$("#tooltip").css("top",tPosY).css("left",tPosX);
					}
				})
				.mouseout(function(){
					$("#tooltip").remove();
				});
				
				$("tbody td:nth-child("+column+")").each(function(){
					var str = $(this).children("span").text();
					if($(this).text().length>nameLen){
						var temp = str.substr(str,nameLen) + "......";
						$(this).children("span").html(temp);
					}
				});
			};

			if(${empty flag}||${"" eq flag}){
				dymShowText(2,8,200);
				dymShowText(3,12,200);
				dymShowText(4,15,240);
			}else{
				dymShowText(3,8,180);
				dymShowText(4,12,180);
				dymShowText(5,15,220);
			}
			
		});	
</script>
  </head>
 <body>
  <form action="system/dataDic!showsearchMessage.action" name="searchF">
 	<input type="hidden" name="flag" value="0"/>
 	<input type="hidden" name="dataDicDto.dicParentId" value="${dataDicDto.dicParentId}"/>
 	<input type="hidden" name="page.curPage" value="${page.curPage }" id="reqPage"/>
 </form>
<div id="navTab" class="tabsPage">
 
<div class="pageContent">
	
	<div id="w_list_print">
	<table class="list" width="100%" targetType="navTab" asc="asc" desc="desc" layoutH="118">
		<thead>
			<tr>	
			   <c:if test="${flag==0}">
			    <th width="4%"></th>	
			    </c:if>
		    	<th width="9%">序号</th>	
				<th width="20%">审计事项名称</th>	
				<th width="27%">业务分类</th>			
				<th width="40%">说明</th>	
			</tr>
		</thead>
		<form method="post" action="attach/download/DownLoadServletSJZS" id="downloadForm" name="downloadForm" style="display: none;">
					<input type="hidden" name="attId" id ='attId' value=""/>
					<%--<input type="button" value = "下载" onclick="downloadAttach('<s:property value="attach.attachId"/>')">--%>
	</form>
		<tbody id = 'playList'>
			<s:iterator id="entry" value="page.list" status="sta">
				<tr rel="${sta.count }">
				   <c:if test="${flag==0}">
		  			<td align="center">
		  			<input type="radio" name="ffalId" value="${entry.dicId }" onclick="getSjId('${entry.dicId}','${entry.dicName}')"/>
		  			</td>
		  			</c:if>
			  			<td>${sta.count+(page.curPage-1)*page.maxResult}</td>
			  			
			  			<td>
							<span>${entry.dicName}</span>
							<input type="hidden" value="${entry.dicName}" />
						</td>
						<td>
							<span>${entry.industry}</span>
							<input type="hidden" value="${entry.industry}" />
						</td>
						<td>
							<span>${entry.dicMemo}</span>
							<input type="hidden" value="${entry.dicMemo}" />
						</td>
						
					</tr>
		  		</tr>
		  	</s:iterator>
		</tbody>
	</table>
	</div>
	  <c:if test="${flag==0}">
	<div class="panelBar" >
		<div class="pagination">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" >
	            <tr class="nonborder">
	               <td align="right"><jsp:include page="/page.jsp"></jsp:include> </td>
	              <td width="1%" align="right">&nbsp;</td>
	            </tr>
	         </table>
		</div>
	</div>
	</c:if>
</div>
</div>
</body>
</html>
