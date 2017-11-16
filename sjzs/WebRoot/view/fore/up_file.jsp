<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/view/common/common.jsp"%>
<%@ page import="com.hnzskj.common.Constant" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.0 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>文档上传</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script language="javascript" src="js/fore/wdzs.js"></script>
	
	<link rel="stylesheet" href="css/validate.css" type="text/css"></link>
	<script type="text/javascript" src="js/validate/jquery.js"></script>
	<script type="text/javascript" src="js/validate/jquery.validate.js"></script>
	<script type="text/javascript" src="js/validate/messages_cn.js"></script>
	<script type="text/javascript" src="js/validate/customValidate.js"></script>
	<script type="text/javascript" src="js/fckeditor/fckeditor.js"></script>
	<script type="text/javascript" src="js/validate/messages_cn.js"></script>
	<script type="text/javascript" src="js/validate/customValidate.js"></script>
	<script language="javascript" src="js/calendar/calendar.js" charset="UTF-8"></script>
	<script language="javascript" src='js/showdialog.js'></script>
 	<script type='text/javascript' src='dwr/engine.js'></script>
   	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src='js/dataCheck.js'></script>
	<script type='text/javascript' src='dwr/interface/dwrUtil.js'></script>
	<script type='text/javascript' src='dwr/interface/auditLaw.js'></script>
	<script type='text/javascript' src='dwr/engine.js'></script>
	<link rel="stylesheet" type="text/css" href="css/fore/up_file.css">
	<script type="text/javascript">
		function closeWin(){
			window.parent.closeWin('addMenuWin');
		}	
	  
		//打开文件选择窗口
		function selectFile(type) {
			var oldAttId = $("#attachId"+type).val();
			if(oldAttId!=null&&""!=oldAttId){	
				alert("只能上传一个文档");
				return false;				
			}else{
				var width = "920";
				var url = "<%=basePath%>wdzs!selectFile.action";
				var returnVal = window.showModalDialog(url, null, "center=yes;dialogWidth="+width+"px;dialogHeight=600px; edge:Raised; directories:no; localtion:no; menubar:no; status=yes; toolbar=yes;scroll:yes;help=no");
				
				if (returnVal != undefined && returnVal != null) {//如果用户选择了文件
					listFile(returnVal,type);
				}
			}
		}
	
		//将上传的文件列表列出
		function listFile(fileName,type) {
			//alert(fileName);
			var oldAttId = $("#attachId"+type).val();
			if(oldAttId!=null&&""!=oldAttId){
				deleteFile(oldAttId,type);
			}
			var name = fileName[0][0];
			var id=fileName[0][1];
			var params = 'javascript:deleteFile(\'' + id + '\')';
			var html_str = "<li id='"+id+"'>"+name +"<img src=\"images/del.gif\" onclick=\"" 
					+ params + "\" style=\"margin-left:20px;cursor:pointer;\"/><input type=\"hidden\" value=\""
					+ id + "\" /></li>";
	       	$("#fileList"+type).append(html_str);
	       	$("#attachId"+type).val(id);
		}
	
		//根据guid删除其所指定的文件
		function deleteFile(attId,type) {
			dwrUtil.delAttachJS(attId, function(data) {
					if (true == data) {
						$("#" + attId).remove();
						document.getElementById("attachId"+type).value = "";
					}
				});
		}	
	
		//删除法规信息
		function deleteAttach(curchaId) {
			if(confirm("您确定要删除么？")) {
				document.deleteForm.submit();   //获取所有的法规信息
			}
		}
		 $(function(){
				$("#uploadForm").validate({
					rules:{
						"upload.caption":{
							required:true
						}
					},
					messages:{
						"upload.caption":{
							required:"必填"
						}
					},
					errorElement:"em",
					success:function(label) {
						label.text(" ").addClass("success")
					}
				});
			});	
		//保存之前检查
		function checkInfo(type){
			var attachId = $("#attachId"+type).val();
			if(attachId == ""){
				alert("请上传文档");
				return false;
			}else{
				$("#"+type).submit();
			}
			return false;
		}
	</script>
  </head>
  
  <body onload="inits()">
    <div class="up_all">
    	<div class="up_main">
    	
    		<table cellpadding="0" cellspacing="0">
    			<tr class="up_tab_title">
    				<td>
    					<h3><span>上传文档  >> 选择上传文档所属分类</span></h3>
    				</td>
    			</tr>
    			<tr class="up_tab_main">
    				<td align="center" style="vertical-align: top;">    					
	    					<table id="menu" style="height:120px;" cellpadding="0" cellspacing="0">
	    						<tr class="tab_main_menu" style="height:90px;">	    							
	    							<td style="text-align: right; width:80px;">分类&nbsp;</td>
	    							<td id="sortList" style="width:360px;text-align: left;">
	    								<select id="rootMenu" size="3"  onchange="addMenu(this)" name="upload.parentId">
	    									
	    								</select>
	    							</td>
	    						</tr>
	    						<tr style="height:30px;">
	    							<td colspan="2" align="center" style="line-height:40px;">
	    								<input type="button" value="下一步" onclick="makeSort()" >
	    							</td>
	    						</tr>	
	    					</table>
	    					<form id="<%=Constant.DXYJ%>" style="display: none;" action="wdzs!uploadFile<%=Constant.DXYJ %>.action" method="post">
		    					<input type="hidden" name="uploadDxyj.clientId" value="${sessionScope.employee.emplId }" />
		    					<input type="hidden" name="uploadDxyj.clientName" value="${sessionScope.employee.emplName }" />
		    					<input type="hidden" name="type" value="" />
		    					<input type="hidden" name="parentId" value="" />
		    					<table id="cont" cellpadding="0" cellspacing="0">
		    						<tr class="sortName">
		    							<td class="left">分类:&nbsp;</td> <td id="sort<%=Constant.DXYJ %>"></td>
		    						</tr>
		    						<tr class="tab_main_title">
		    							<td class="left">标题&nbsp;</td>
		    							<td class="right">
		    								<input style="margin-left:10px;" type="text" name="uploadDxyj.caption" >
		    							</td>
		    						</tr>
		    						<tr style="height:25px;">
		    							<td class="left">
		    								创建时间
		    							</td>
		    							<td align="left">
		    								<input style="margin-left:10px;" id="date<%=Constant.DXYJ%>" maxLength="50" name="uploadDxyj.writeDate" readonly="readonly" value="" style="width:100px;" onClick="setday(this,document.all.date<%=Constant.DXYJ%>)" />
											<img style="CURSOR: hand" onClick="setday(this,document.all.date<%=Constant.DXYJ%>)" src="js/calendar/form/calendar.gif" align="middle" border="0" width="30" height="30"/>
		    							</td>
		    						</tr>
		    						<tr style="height:25px;">
		    							<Td class="left">
		    								发文编号
		    							</td>
		    							<td align="left">
		    								<input style="margin-right:50px;margin-left:10px;" type="text" name="uploadDxyj.lawNo"/>
		    								条:<input style="margin-right:20px;margin-left:10px;" size="5" type="text" name="uploadDxyj.tiao"/>
		    								款:<input style="margin-left:10px;" size="5" type="text" name="uploadDxyj.kuan"/>
		    							</td>
		    						</tr>	
		    						<tr class="tab_main_cont">
		    							<td class="left">简介&nbsp;</td>
		    							<td class="right">
		    								<textarea style="margin-left:10px;" rows="3" cols="40" name="uploadDxyj.content"></textarea>
		    							</td>
		    						</tr>   							    						  										
		    						<tr class="up_file_tr" align="left" style="height:25px;">
		    							<td colspan="2">
		    								<input style="margin-left:50px;margin-right:50px;" type="button" value="上传文档" class="selectUnit" onclick="javascript:selectFile('<%=Constant.DXYJ %>')"/>
		    								<input style="margin-left:5px;" type="radio" checked="checked" value="0" name="uploadDxyj.isOpen">不公开
		    								<input style="margin-left:5px;" type="radio" value="1" name="uploadDxyj.isOpen">公开
		    								<span style="margin-left:50px;">积分&nbsp;</span>
		    								<select style="margin-left:10px;" name="uploadDxyj.integral">
		    									<option selected="selected" value="0">0</option>
		    									<option value="1">1</option>
		    									<option value="2">2</option>
		    									<option value="5">5</option>
		    									<option value="10">10</option>
		    								</select>
		    							</td>
		    						</tr>
		    						<tr class="contentTd" style="height:40px;">
										<td class="left">
											文档标题&nbsp;</td>
										<td align="left" style="padding-left:10px;">
											<ul id="fileList<%=Constant.DXYJ %>" style="margin-left: 5px;">										
											</ul>
											<input type="hidden" name="uploadDxyj.attachId" id ="attachId<%=Constant.DXYJ %>" value=""/>
										</td>
									</tr>
		    						<tr class="up_file_but" >
		    							<td colspan="2">
		    								<input type="submit" onclick="return checkInfo('<%=Constant.DXYJ %>')" value="保存文档">
		    							</td>
		    						</tr>
		    					</table>
	    					</form>
	    					<form id="<%=Constant.FFAL %>"  style="display: none;" action="wdzs!uploadFile<%=Constant.FFAL %>.action" method="post">
		    					<input type="hidden" name="uploadFfal.clientId" value="${sessionScope.employee.emplId }" />
		    					<input type="hidden" name="uploadFfal.clientName" value="${sessionScope.employee.emplName }" />
		    					<input type="hidden" name="type" value="" />
		    					<input type="hidden" name="parentId" value="" />
		    					<table id="cont" cellpadding="0" cellspacing="0">
		    						<tr class="sortName">
		    							<td class="left">分类:&nbsp;</td><td id="sort<%=Constant.FFAL %>"></td>
		    						</tr>
		    						<tr class="tab_main_title">
		    							<td class="left">标题&nbsp;</td>
		    							<td class="right">
		    								<input style="margin-left:10px;" type="text" name="uploadFfal.caption" >
		    							</td>
		    						</tr>
		    						<tr style="height:25px;">
		    							<td class="left">撰写人:&nbsp;</td>
		    							<td align="left">
		    								<input type="text" name="uploadFfal.author" style="margin-left:10px;margin-right:50px;" />
		    								撰写单位:&nbsp;
		    								<input type="text" name="uploadFfal.department" style="margin-left:10px;"/>
		    							</td>
		    						</tr>
		    						<tr style="height:25px;">
		    							<td class="left">
		    								撰写日期:&nbsp;
		    							</td>
		    							<Td align="left">
		    								<input style="margin-left:10px;" id="date<%=Constant.FFAL %>" maxLength="50" name="uploadFfal.ffalDateTime" readonly="readonly" value="" style="width:100px;" onClick="setday(this,document.all.date<%=Constant.FFAL %>)" />
											<img style="CURSOR: hand;margin-right:50px;" onClick="setday(this,document.all.date<%=Constant.FFAL %>)" src="js/calendar/form/calendar.gif" align="middle" border="0" width="30" height="30"/>
		    								获奖情况:&nbsp;
		    								<input style="margin-left:10px;" type="text" name="uploadFfal.awards" />
		    								
		    							</Td>
		    						</tr>
		    						<tr class="tab_main_cont">
		    							<td class="left">简介&nbsp;</td>
		    							<td class="right">
		    								<textarea style="margin-left:10px;" rows="3" cols="40" name="uploadFfal.content"></textarea>
		    							</td>
		    						</tr>
		    										
		    						<tr class="up_file_tr" style="height:30px;">
		    							<td colspan="2">
		    								<input style="margin-left:50px;margin-right:50px;" type="button" value="上传文档" class="selectUnit" onclick="javascript:selectFile('<%=Constant.FFAL %>')"/>
		    								<input style="margin-left:5px;" type="radio" checked="checked" value="0" name="uploadFfal.isOpen">不公开
		    								<input style="margin-left:5px;" type="radio" value="1" name="uploadFfal.isOpen">公开
		    								<span style="margin-left:50px;">积分&nbsp;</span>
		    								<select style="margin-left:10px;" name="uploadFfal.integral">
		    									<option selected="selected" value="0">0</option>
		    									<option value="1">1</option>
		    									<option value="2">2</option>
		    									<option value="5">5</option>
		    									<option value="10">10</option>
		    								</select>
		    							</td>
		    						</tr>
		    						<tr class="contentTd" style="height:40px;">
										<td class="left">
											附件列表&nbsp;</td>
										<td align="left" style="padding-left:10px;">
											<ul id="fileList<%=Constant.FFAL %>" style="margin-left: 5px;">										
											</ul>
											<input type="hidden" name="uploadFfal.attachId" id ="attachId<%=Constant.FFAL %>" value=""/>
										</td>
									</tr>
		    						<tr class="up_file_but">
		    							<td colspan="2">
		    								<input type="submit" onclick="return checkInfo('<%=Constant.FFAL %>')" value="保存">
		    							</td>
		    						</tr>
		    					</table>
	    					</form>
	    					<form id="<%=Constant.SJFG %>" style="display: none;" action="wdzs!uploadFile<%=Constant.SJFG %>.action" method="post">
		    					<input type="hidden" name="uploadSjfg.clientId" value="${sessionScope.employee.emplId }" />
		    					<input type="hidden" name="uploadSjfg.clientName" value="${sessionScope.employee.emplName }" />
		    					<input type="hidden" name="type" value="" />
		    					<input type="hidden" name="parentId" value="" />
		    					<table id="cont" cellpadding="0" cellspacing="0">
		    						<tr class="sortName">
		    							<td class="left">分类&nbsp;</td><td id="sort<%=Constant.SJFG %>"></td>
		    						</tr>
		    						<tr class="tab_main_title">
		    							<td class="left">标题&nbsp;</td>
		    							<td class="right">
		    								<input style="margin-left:10px;" type="text" name="uploadSjfg.caption" >
		    							</td>
		    						</tr>
		    						<tr>
		    							<td class="left">
		    								归属行业&nbsp;
		    							</td>
		    							<Td align="left">
		    								<input type="text" name="uploadSjfg.lawGrade" style="margin-left:10px;margin-right:30px;" />
		    								发文单位&nbsp;
		    								<input type="text" name="uploadSjfg.lawOrg" style="margin-left:10px;"/>
		    							</Td>
		    						</tr>
		    						<tr>
		    							<td class="left">
		    								发文编号&nbsp;
		    							</td>
		    							<Td align="left">
		    								<input type="text" size="9" name="uploadSjfg.lawNumber" style="margin-left:10px;margin-right:30px;">
		    								发文日期&nbsp;
		    								<input style="margin-left:10px;" id="date<%=Constant.SJFG %>" maxLength="50" name="uploadSjfg.lawDate" readonly="readonly" value="" style="width:100px;" onClick="setday(this,document.all.date<%=Constant.SJFG %>)" />
											<img style="CURSOR: hand;margin-right:50px;" onClick="setday(this,document.all.date<%=Constant.SJFG %>)" src="js/calendar/form/calendar.gif" align="middle" border="0" width="30" height="30"/>
		    								
		    							</Td>
		    						</tr>
		    						<tr class="tab_main_cont">
		    							<td class="left">简介&nbsp;</td>
		    							<td class="right">
		    								<textarea style="margin-left:10px;" rows="3" cols="40" name="uploadSjfg.content"></textarea>
		    							</td>
		    						</tr>
		    										
		    						<tr class="up_file_tr" style="height:30px;">
		    							<td colspan="2">
		    								<input style="margin-left:50px;margin-right:50px;" type="button" value="上传文档" class="selectUnit" onclick="javascript:selectFile('<%=Constant.SJFG %>')"/>
		    								<input style="margin-left:5px;" type="radio" checked="checked" value="0" name="uploadSjfg.isOpen">不公开
		    								<input style="margin-left:5px;" type="radio" value="1" name="uploadSjfg.isOpen">公开
		    								<span style="margin-left:50px;">积分&nbsp;</span>
		    								<select style="margin-left:10px;" name="uploadSjfg.integral">
		    									<option selected="selected" value="0">0</option>
		    									<option value="1">1</option>
		    									<option value="2">2</option>
		    									<option value="5">5</option>
		    									<option value="10">10</option>
		    								</select>
		    							</td>
		    						</tr>
		    						<tr class="contentTd" style="height:40px;">
										<td class="left">
											附件列表&nbsp;</td>
										<td align="left" style="padding-left:10px;">
											<ul id="fileList<%=Constant.SJFG %>" style="margin-left: 5px;">										
											</ul>
											<input type="hidden" name="uploadSjfg.attachId" id ="attachId<%=Constant.SJFG %>" value=""/>
										</td>
									</tr>
		    						<tr class="up_file_but">
		    							<td colspan="2">
		    								<input type="submit" onclick="return checkInfo('<%=Constant.SJFG %>')" value="保存">
		    							</td>
		    						</tr>
		    					</table>
	    					</form>
	    					<form id="<%=Constant.SJSS %>" style="display: none;" action="wdzs!uploadFile<%=Constant.SJSS %>.action" method="post">
		    					<input type="hidden" name="uploadSsfn.clientId" value="${sessionScope.employee.emplId }" />
		    					<input type="hidden" name="uploadSsfn.clientName" value="${sessionScope.employee.emplName }" />
		    					<input type="hidden" name="type" value="" />
		    					<input type="hidden" name="parentId" value="" />
		    					<table id="cont"cellpadding="0" cellspacing="0">
		    						<tr class="sortName">
		    							<td class="left">分类&nbsp;</td><td id="sort<%=Constant.SJSS %>"></td>
		    						</tr>
		    						<tr class="tab_main_title">
		    							<td class="left">标题&nbsp;</td>
		    							<td class="right">
		    								<input style="margin-left:10px;" type="text" name="uploadSsfn.caption" >
		    							</td>
		    						</tr>
		    						<tr>
		    							<td class="left">
		    								所属行业
		    							</td>
		    							<td align="left">
		    								<input name="uploadSsfn.industry" />
		    								发文日期
		    								<input style="margin-left:10px;" id="date<%=Constant.SJSS %>" maxLength="50" name="uploadSsfn.writedate" readonly="readonly" value="" style="width:100px;" onClick="setday(this,document.all.date<%=Constant.SJSS %>)" />
											<img style="CURSOR: hand;margin-right:50px;" onClick="setday(this,document.all.date<%=Constant.SJSS %>)" src="js/calendar/form/calendar.gif" align="middle" border="0" width="30" height="30"/>
		    							</td>
		    						</tr>
		    						<tr>
		    							<td class="left">
		    								关键字
		    							</td>
		    							<Td align="left">
		    								<input style="margin-left: 10px;" name="uploadSsfn.keyword" />
		    							</Td>
		    						</tr>
		    						<tr class="tab_main_cont">
		    							<td class="left">简介&nbsp;</td>
		    							<td class="right">
		    								<textarea style="margin-left:10px;" rows="3" cols="40" name="uploadSsfn.content"></textarea>
		    							</td>
		    						</tr>
		    										
		    						<tr class="up_file_tr" style="height:30px;">
		    							<td colspan="2">
		    								<input style="margin-left:50px;margin-right:50px;" type="button" value="上传文档" class="selectUnit" onclick="javascript:selectFile('<%=Constant.SJSS %>')"/>
		    								<input style="margin-left:5px;" type="radio" checked="checked" value="0" name="uploadSsfn.isOpen">不公开
		    								<input style="margin-left:5px;" type="radio" value="1" name="uploadSsfn.isOpen">公开
		    								<span style="margin-left:50px;">积分&nbsp;</span>
		    								<select style="margin-left:10px;" name="uploadSsfn.integral">
		    									<option selected="selected" value="0">0</option>
		    									<option value="1">1</option>
		    									<option value="2">2</option>
		    									<option value="5">5</option>
		    									<option value="10">10</option>
		    								</select>
		    							</td>
		    						</tr>
		    						<tr class="contentTd" style="height:40px;">
										<td class="left">
											附件列表&nbsp;</td>
										<td align="left" style="padding-left:10px;">
											<ul id="fileList<%=Constant.SJSS %>" style="margin-left: 5px;">										
											</ul>
											<input type="hidden" name="uploadSsfn.attachId" id ="attachId<%=Constant.SJSS %>" value=""/>
										</td>
									</tr>
		    						<tr class="up_file_but">
		    							<td colspan="2">
		    								<input type="submit" onclick="return checkInfo('<%=Constant.SJSS %>')" value="保存">
		    							</td>
		    						</tr>
		    					</table>
	    					</form>
    				</td>
    			</tr>
    		</table>    		
    	</div>
    </div>
  </body>
</html>
