<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.hnzskj.common.Constant" %>
<%@ include file="/view/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>查看详细信息---定性依据</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">	
	<script src="js/treeView/scripts/jquery.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="fckeditor/fckeditor.js"></script>
	<link rel="stylesheet" type="text/css" href="css/fore/newContent.css">
	
	<script type="text/javascript" language="javascript">
		function checkType( type ){
			var sAction = "search!searchAll.action";
			var sForm = document.getElementById('searchForm');
			sForm.action = sAction;
			//$("#contentForm").attr("action",sAction);
			$("#type").attr("value",type);
			var g="#"+type;
			var style="color:black;font-size:14;text-decoration:none;"
			$("a").attr("style","");
			$(g).attr("style",style);
			$("#reqPage").value=0;
			$("#searchForm").submit();
		}
		function inits(){
			var type="${sessionScope.type}"+"";
			var typeId="";
			var sAction="";
			if(type!="" && type !=null){
				typeId = $("#type").attr("value",type);
			}else{
				typeId = $("#type").attr("value","All");
				type="All";
			}
			sAction = "search!searchAll.action";
			$("#searchForm").attr("action",sAction);//action=sAction;
			var g="#"+type;
			var style="color:black;font-size:14;text-decoration:none;"
			$(g).attr("style",style);				
		}		
		document.oncontextmenu=function(e){return false;}
		</script>		
  </head>
  <body onload="inits()" >
  	<jsp:include page="cont_top.jsp"></jsp:include>
 <div id="content" onselectstart="return false" >
 	<div class="menu">
 		<a href="<%=basePath%>">审计之家</a> > 定性依据
 	</div>
			<c:choose>
				<c:when test="${! empty dxyjLaw}">
					<div class="top">
					<!-- 定性依据名称 -->
						<b class="ic-txt"></b><span>${dxyjLaw.caption}</span>
					</div>	
					<div class="content_left">						
						<div class="bottom" >
						<!-- 定性依据内容 -->
							<div style="width:100%;height:710px;margin-top:-10px; overflow-y: auto;">
								<textarea class="editor" name="law.lawContent" readonly="readonly" id="matText" rows="9" cols="70" tools="mfull">${dxyjLaw.lawContent }</textarea>
							  	<script type="text/javascript">
							    	var my_FCKeditor = new FCKeditor("matText");
							    	my_FCKeditor.BasePath = "fckeditor/";
							    	my_FCKeditor.Width= "100%";
							    	my_FCKeditor.Height= "99%";
							    	my_FCKeditor.ToolbarSet= "NoTool";
							    	my_FCKeditor.ReplaceTextarea();	

							    	function FCKeditor_OnComplete( editor ){
								    	editor.EditorDocument.body.contentEditable = false;
								    	editor.EditMode=FCK_EDITMODE_SOURCE;
								    	editor.ToolbarSet.RefreshModeState();
								    	editor.EditMode=FCK_EDITMODE_WYSIWYG;
								    	editor.ToolbarSet.RefreshModeState();
								    } 
								    
							    </script>
							</div>
						</div>
					</div>
					<div class="content_right" >
						<div class="top">
							<b>定性依据信息▼</b>
							<p>发文单位:&nbsp;${dxyjLaw.department}</p>
							<p>发文编号:&nbsp;${dxyjLaw.lawNo}</p>
							<p>条:&nbsp;${dxyjLaw.tiao}&nbsp;&nbsp;款:&nbsp;${dxyjLaw.kuan}</p>
						</div>
						<div class="center" style="background:none;"></div>
						<div class="bottom" style="overflow: hidden;height:552px;">							
						</div>
					</div>						
				</c:when>
				<c:otherwise>
					<div align="center" style="margin-top: 10px;" >
						<table width="100%" border="0" cellspacing="0" cellpadding="0" id="show">							
							<tr>
								<td>
									没有相关法规依据！
								</td>
							</tr>
						</table>
					</div>
		
				</c:otherwise>
			</c:choose>	
			<div class="all_boot">
				<div class="boot">
					<jsp:include page="boot.jsp"></jsp:include>
				</div>
			</div>		
		</div>
  </body>
</html>
