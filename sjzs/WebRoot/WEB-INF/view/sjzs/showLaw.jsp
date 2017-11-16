<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/view/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<script type="text/javascript" src="js/fckeditor/fckeditor.js"></script>
		<base href="<%=basePath%>" />
		<title>查看审计法规</title>
		<link rel="stylesheet" type="text/css" href='css/style.css' />
		<link rel="stylesheet" href="css/right.css" type="text/css"></link>
		<link rel="stylesheet" type="text/css"href="js/themes/default/easyui.css" />
		<link rel="stylesheet" type="text/css" href="js/themes/icon.css" />
	<link rel="stylesheet" href="css/validate.css" type="text/css"></link>
	<script type="text/javascript" src="js/validate/jquery.js"></script>
	<script type="text/javascript" src="js/validate/jquery.validate.js"></script>
	<script type="text/javascript" src="js/validate/messages_cn.js"></script>
	<script type="text/javascript" src="js/validate/customValidate.js"></script>
	<script type="text/javascript" src="js/fckeditor/fckeditor.js"></script>
	<script type="text/javascript" src="js/validate/customValidate.js"></script>
	<script language="javascript" src="js/calendar/calendar.js" charset="UTF-8"></script>
	<script language="javascript" src='js/showdialog.js'></script>
	<script language="javascript" src='js/dataCheck.js'></script>
	<script type="text/javascript" src="fckeditor/fckeditor.js"></script>		
	<script type='text/javascript' src='dwr/interface/auditLaw.js'></script>
  	<script type='text/javascript' src='dwr/engine.js'></script>
    <script type='text/javascript' src='dwr/util.js'></script>
    

<script src="sjzs/js/dwz.regional.zh.js" type="text/javascript"></script>
	<style type='text/css'>
* {
	font-size: 12px;
}

.current {
	background: blue;
	color: white;
}
</style>
		<script type="text/javascript">

		$(function(){
			$("#addLawForm").validate({
				rules:{
					"auditLaw.caption":{
						required:true
					},
					"auditLaw.lawContent":{
						required:true
					} 
				},
				messages:{
					"auditLaw.caption":{
						required:"必填"
					},
					"auditLaw.lawContent":{
						required:"必填"
					} 
				},
				errorElement:"em",
				success:function(label) {
					label.text(" ").addClass("success")
				}
			});
		});	

		$(function(){
			$("#updLawForm").validate({
				rules:{
					"auditLaw.caption":{
						required:true
					},
					"auditLaw.lawContent":{
						required:true
					} 
				},
				messages:{
					"auditLaw.caption":{
						required:"必填"
					},
					"auditLaw.lawContent":{
						required:"必填"
					} 
				},
				errorElement:"em",
				success:function(label) {
					label.text(" ").addClass("success")
				}
			});
		});	
	//初始化table大小
	function init(){
		document.getElementById("show").style.width=document.body.offsetWidth-40;
	}

	//修改节点信息
	function updateLaw() {
		document.getElementById('update').style.display = "";
		document.getElementById('show').style.display = "none";
	}
	
	//新增法规
	function addLaw(){
	      document.getElementById('add').style.display = "";
	}

	//删除法规信息
	function deleteLaw(curchaId) {
		if(confirm("您确定要删除么？")) {
			document.deleteForm.submit();   //获取所有的法规信息
		}
	}

	function upDateBack(){
		document.getElementById('update').style.display = "none";
		document.getElementById('show').style.display = "";
	}
	
  </script>
	</head>

	<body onload="javascript:init();" style="margin-top: 10px;">
		<div id="main" align="center">
			<c:choose>
				<c:when test="${auditLaw.id != null}">
					<form method="post" action="sjzs!deleteAuditLaw.action" id="deleteForm" name="deleteForm" style="display: none;">
						<input type="hidden" name="auditLaw.id" value="${auditLaw.id}"/>
						 <input type="hidden" name="sjzhMenuTree.menuId" value="${auditLaw.parentID}"/>
					</form>
				
					<table height="181" class="table" id="show"
						style="font-size: 12px;">
						<thead>
						<tr>
						<th colspan="6">
						<table width="100%">
						<tr>
						  <td width="80%">审计法规细信息</td>
						    <td><img src="images/edit.png" width="19" height="19"  />
		          	<a href="javascript:void(0)" title="编辑" onclick="updateLaw()" class="txt12">编辑</a>
	          		&nbsp; &nbsp;
		          	<img src="images/ny_sc.jpg" width="19" height="19"  />
		          	<a href="javascript:deleteLaw('${auditLaw.id}')" class="txt12">删除</a>
	             		&nbsp; &nbsp;</td>
						</tr>
						</table>
						  </th>
						</tr>
						</thead>
						<tbody>
							<tr class="sid_user" onmouseover="this.className='onmouseover'"
								onmouseout="this.className='contentTd'">
								<td
									style="font-weight: bold; text-align: right; padding-right: 10px; border-left: 0; width: 15%">
									<b>法规名称:</b>
								</td>
								<td style="padding-left: 10px; text-align: left">
									${auditLaw.caption}
								</td>
								<td
									style="font-weight: bold; text-align: right; padding-right: 10px; border-left: 0">
									<b>发文单位:</b>
								</td>
								<td style="padding-left: 10px; text-align: left">
									${auditLaw.department}
								</td>
								<td
									style="font-weight: bold; text-align: right; padding-right: 10px; border-left: 0">
									<b>发文编号:</b>
								</td>
								<td style="padding-left: 10px; text-align: left">
									${auditLaw.lawNo}
								</td>
							</tr>

							<tr class="contentTd" onmouseover="this.className='onmouseover'"
								onmouseout="this.className='contentTd'">
								<td
									style="font-weight: bold; text-align: right; padding-right: 10px; border-left: 0">
									<b>条:</b>
								</td>
								<td style="padding-left: 10px; text-align: left">
									${auditLaw.tiao}
								</td>
							</tr>

							<tr class="contentTd" onmouseover="this.className='onmouseover'"
								onmouseout="this.className='contentTd'">
								<td
									style="font-weight: bold; text-align: right; padding-right: 10px; width: % 15;">
									款:
								</td>
								<td style="padding-left: 10px; text-align: left">
								
									${auditLaw.kuan}
								</td>
							</tr>

							<tr class="contentTd" onmouseover="this.className='onmouseover'"
								onmouseout="this.className='contentTd'">
								<td align="right" valign="top" style="padding-top: 10px;">
									<b>法规内容:</b>
								</td>
								<td style="padding: 5px; text-align: left" colspan="5">
									<textarea rows="10" cols="80" style="width: 100%" readonly="readonly" name="auditLaw.lawContent" id="matText">${auditLaw.lawContent }</textarea>
								${auditLaw.lawContent }
								</td>
							</tr>
						</tbody>
					</table>
			<div align="center" style="margin-top: 10px;display: none;" id = "update">
		<form method="post" action="sjzs!doUpdAuditLaw.action" id="updLawForm" name="updLawForm">
		<input type="hidden" name="auditLaw.parentID" value="${sjzhMenuTree.menuId}"/>
		<input type="hidden" name="auditLaw.id" value="${auditLaw.id}"/>
		<input type="hidden" name="auditLaw.type" value="0"/>
		  <table class="table" id="update" width="100%" style="font-size: 12px;margin-top: 10px;text-align: left;">
		 <thead>
		   <tr>
		    <th colspan="6" align="center">编辑法规内容</th>
		   </tr>
		 </thead>
			<tr class="contentTd">
				<td  align="right"><b>法规名称</b></td>
				<td><label>
				  <input type="text" name="auditLaw.caption" value="${auditLaw.caption}">
				</label></td>
				<td  align="right"><b>发文单位</b></td>
				<td><label>
				  <input type="text" name="auditLaw.department" value="${auditLaw.department}">
				</label></td>
				<td  align="right"><b>发文编号</b></td>
				<td><label>
				  <input type="text" name="auditLaw.lawNo" value="${auditLaw.lawNo }">
				</label></td>
			</tr>
			<tr class="contentTd">
				<td  align="right"><b>条</b></td>
				<td><label>
				  <input type="text" name="auditLaw.tiao" value="${auditLaw.tiao }">
				</label></td>
			</tr>
			<tr class="contentTd">
				<td  align="right"><b>款</b></td>
				<td><label>
				  <input type="text" name="auditLaw.kuan" value="${auditLaw.kuan }">
				</label></td>
			</tr>
			<tr class="contentTd">
				<td align="right" >
					<b>法规内容</b>
				</td>
				<td colspan="5">
					<textarea cols="50" rows="20" name="auditLaw.lawContent" id="matText">${auditLaw.lawContent }</textarea>
				    <script type="text/javascript">
				    	var my_FCKeditor = new FCKeditor("matText");
				    	my_FCKeditor.BasePath = "fckeditor/";
				    	my_FCKeditor.Width= "100%";
				    	my_FCKeditor.Height= "400";
				    	my_FCKeditor.ToolbarSet= "Myself";
				    	my_FCKeditor.ReplaceTextarea();
					</script>
				</td>
			</tr>
			<tfoot>
			<tr>
				<td colspan="6" align="center">
					<input type="submit" value=" " class="savebtn">
					&nbsp;&nbsp;&nbsp;
					<input type="button" onclick="upDateBack();" value="&nbsp;" class="backbtn">
				</td>
			</tr>
			</tfoot>
		</table>
	</form>
					</div>
					
				</c:when>
				<c:otherwise>
					<div align="center" style="margin-top: 10px;">
						<table width="100%" border="0" cellspacing="0" cellpadding="0" id="show">
							<tr>
								<td valign="middle" align="right">

									<img src="images/ny_tj.jpg" width="19" height="19" />
									<a href="javascript:void(0);" title="新增"
										onclick="addLaw()"
										class="txt12"> 新增 </a> &nbsp; &nbsp;
								</td>
							</tr>
							<tr>
								<td>
									<font color="red">没有相关法规依据！请添加！</font>
								</td>
							</tr>
						</table>
					</div>
		<div align="center" style="margin-top: 10px;display: none;" id = "add" >
		<form method="post" action="sjzs!doAddAuditLaw.action" id="addLawForm" >
		 <input type="hidden" name="auditLaw.parentID" value="${sjzhMenuTree.menuId}"/>
		 <input type="hidden" name="auditLaw.type" value="0"/>
		  <table class="table" id="add" width="100%" style="font-size: 12px;margin-top: 10px;text-align: left;">
			 <thead>
		   <tr>
		    <th colspan="6" align="center">添加审计法规</th>
		   </tr>
		 </thead>
			<tr class="contentTd">
				<td  align="right"><b>法规名称</b></td>
				<td><label>
				  <input type="text" name="auditLaw.caption">
				</label></td>
				<td  align="right"><b>发文单位</b></td>
				<td><label>
				  <input type="text" name="auditLaw.department">
				</label></td>
				<td  align="right"><b>发文编号</b></td>
				<td><label>
				  <input type="text" name="auditLaw.lawNo">
				</label></td>
			</tr>
			<tr class="contentTd">
				<td align="right" >
					<b>法规内容</b>
				</td>
				<td colspan="5">
					<textarea cols="50" rows="20" name="auditLaw.lawContent" id="matText"></textarea>
				    <script type="text/javascript">
				    	var my_FCKeditor = new FCKeditor("matText");
				    	my_FCKeditor.BasePath = "fckeditor/";
				    	my_FCKeditor.Width= "100%";
				    	my_FCKeditor.Height= "400";
				    	my_FCKeditor.ToolbarSet= "Myself";
				    	my_FCKeditor.ReplaceTextarea();
					</script>
				</td>
			</tr>
			<tfoot>
			<tr>
				<td colspan="6" align="center">
					<input type="submit" value=" " class="savebtn">
				</td>
			</tr>
			</tfoot>
		</table>
	</form>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	
	</body>
	
	<script type="text/javascript">


		//打开导入word对话框
	function importWord() {
		var openURL = "<%=basePath%>view/common/importWord.jsp?time="+new Date().getTime();
		var reValue = window.showModalDialog(openURL,"","dialogHeight=200px;");
		if ( 'no' != reValue && undefined != reValue ) {
			auditLaw.getConByHtml(reValue,function(data){
				$("#matText").val(data);
			});
		}
	} 
	</script>
</html>
