<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/view/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>添加添加定性依据页面</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" href="css/right.css" type="text/css"></link>
		<link rel="stylesheet" href="css/validate.css" type="text/css"></link>
		<script type="text/javascript" src="js/validate/jquery.js"></script>
		<script type="text/javascript" src="js/validate/jquery.validate.js"></script>
		<script type="text/javascript" src="js/validate/messages_cn.js"></script>
		<script type="text/javascript" src="js/validate/customValidate.js"></script>
		<script type="text/javascript" src="fckeditor/fckeditor.js"></script>
		<script type="text/javascript" src="js/validate/messages_cn.js"></script>
		<script type="text/javascript" src="js/validate/customValidate.js"></script>
		<script language="javascript" src="js/calendar/calendar.js" charset="UTF-8"></script>
		<script language="javascript" src='js/showdialog.js'></script>
		<script type='text/javascript' src='dwr/interface/auditLaw.js'></script>
  		<script type='text/javascript' src='dwr/engine.js'></script>
    	<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src='js/dataCheck.js'></script>
		<script type='text/javascript' src='dwr/interface/attachSJZS.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type="text/javascript">		
		
  $(function(){
		$("#updateLawForm").validate({
			rules:{
				"law.caption":{
					required:true
				},
				"law.lawNo":{
					required:true
				},
				"law.writeDate":{
					required:true
				} 
			},
			messages:{
				"law.caption":{
					required:"必填"
				},
				"law.lawNo":{
					required:"必填"
				},
				"law.writeDate":{
					required:"必填"
				} 
			},
			errorElement:"em",
			success:function(label) {
				label.text(" ").addClass("success")
				//$("all").window('close');
			}
		});
	});	

	
//初始化table大小
	function init(){
		document.getElementById("add").style.width=document.body.offsetWidth-30;
	}

	function isdis(t){
        return $(t).attr('unable')=="true";
	}
	//打开导入word页面
	function importWord() {
		var openURL = "<%=basePath%>view/common/importWord.jsp?time="+new Date().getTime();
		var reValue = window.showModalDialog(openURL,"","dialogHeight=200px;");
		if ( 'no' != reValue && undefined != reValue ) {
			//var law = document.geElementById("matText");
			auditLaw.getConByHtml(reValue,function(data){
				var myEditor = FCKeditorAPI.GetInstance( "matText" );
				myEditor.InsertHtml(data);
				//$("#matText").val(data);
			});
		}
	} 
		
	
  </script>
 <jsp:include page="/jsPage.jsp"></jsp:include>
 <style type="text/css">
 	#list .comm{
 		width:90px;
 	}
 	#list .spec{
 		width:60px;
 	}
 	#list .fir{
 		width:100px;
 	}
 </style>
  </head>
  
  <body id="all" name="law" onload="javascript:init();">
  	<div id="navTab" class="tabsPage">
  	<div align="center" style="width:100%;height:100%;background-color: white;" id = "add" >
  	<div class="w_list_print">
		<form method="post" action="system/dxyj!doUpdateDXYJ.action" id="updateLawForm" class="pageFormContent" >
		 <input type="hidden" name="law.parentID" value="${law.parentID}"/>
		 <input type="hidden" name="law.id" value="${law.id}"/>
		 <input type="hidden" name="law.nodeType" value="1"/>
				  <table  id="list" height="100%" width="100%" style="font-size: 12px;"><%--
					 <thead >
				   <tr >
				    <th colspan="6" height="35px" style="font-size:20px;" align="center"> <b>添加定性依据</b></th>
				   </tr>
				 </thead>
				 --%><tbody>
					<tr class="contentTd"  >
						<td align="right" class="fir" ><b>法规名称:&nbsp;</b></td>
						<td class="comm"><input type="text" name="law.caption" value="${law.caption }" ></td>			
						<td  align="right" colspan="2" class="comm"><b>发文单位:&nbsp;</b></td>
						<td colspan="2" class="comm"><input type="text" name="law.department" value="${law.department }">
						</td>
					</tr>
					<tr class="contentTd">
						<td  align="right" class="fir"><b>发文编号:&nbsp;</b></td>
						<td><input type="text" name="law.lawNo" value="${law.lawNo }"></td>
					
						<td  align="right" class="spec" >条:&nbsp; 
						</td>
						<td class="spec" ><input type="text" name="law.tiao" size="7" value="${law.tiao }"  onkeyup="value=value.replace(/[^\d]/g,'')" 
		          	 onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>			
						<td  align="right" width="80px" >款:&nbsp;
						</td>
						<td  width="100px"><input align="left" type="text" name="law.kuan" size="7" value="${law.kuan }" onkeyup="value=value.replace(/[^\d]/g,'')" 
		          	 onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
					</tr>
					<tr>
						<td class="fir" align="right">创建日期：</td>
						<td colspan="5">
							<input id="date" maxLength="50" name="law.writeDate" value="${law.writeDate }" readonly="readonly" value="" style="width:160px;" onClick="setday(this,document.all.date)" />
						<!-- 	<input type="text" name="law.writeDate" class="date" readonly="readonly" /> -->
						</td>
					</tr>
					<tr class="contentTd">
						<td align="right"  class="fir">
							<b>法规内容:&nbsp;</b>				
						</td>
						<td colspan="5" >
						<input align="center" type="button" value="选择word文件导入" style="white-space: " onclick="importWord();" class="button-submis10"/>
						<div class="unit">
							<textarea class="editor" name="law.lawContent"  id="matText" rows="7" cols="60" tools="mfull">${law.lawContent } </textarea>
							<script type="text/javascript">
						    	var my_FCKeditor = new FCKeditor("matText");
						    	my_FCKeditor.BasePath = "fckeditor/";
						    	my_FCKeditor.Width= "100%";
						    	my_FCKeditor.Height= "250";
						    	my_FCKeditor.ToolbarSet= "Myself";
						    	my_FCKeditor.ReplaceTextarea();
							</script>
						
						</div>						
						</td>
					</tr>
					</tbody>
					<tfoot>
					<tr>
						<td colspan="6" align="center">
							<input type="submit" value=" " id="savebtn" class="savebtn">
							<input type="button" value=" " id="closebtn" class="closebtn" onclick="closeWin();">
							<%--<dl class="nowrap" >
								<dd style="margin-left:300px;width:100px;"><div class="buttonActive"><div class="buttonContent"><button type="submit" >修改</button></div></div></dd>
							</dl>				
						--%></td>
					</tr>
					</tfoot>
				</table>
			</form>
			</div>
		</div>
	 </div>
	</body>
	
</html>
