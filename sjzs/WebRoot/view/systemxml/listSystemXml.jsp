<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%request.setCharacterEncoding("utf-8"); %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>系统配置信息</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script type='text/javascript' src='/htglxt/dwr/interface/systemXmlServiceJS.js'></script>
  		<script type='text/javascript' src='/htglxt/dwr/engine.js'></script>
  		<script type='text/javascript' src='/htglxt/dwr/util.js'></script>
		<script language="JavaScript">
	 	
	 	//添加内容，selectName为节点的名称
		function addConfirm(selectName) {
			var name = window.prompt("请输入要添加的内容","");
			var nodeName = selectName;
			if((name != "") && (name != null)) {
				name=encodeURI(name);
				location.href = "systemXmlAction!add.action?nodeName=" + nodeName + "&name=" + name+"&flag=1";
			} else {
				return false;
			}
		}
		//删除内容         selectName 为节点名称 
		function delConfirm(selectName) {
			var selObj = document.getElementById(selectName);
			var nodeName = selectName;
			var name;
			var code;
			for(var i = 0; i < selObj.length; i ++) {
				if(selObj.options[i].selected == true) {
					code = selObj.options[i].value;
					name = selObj.options[i].text;
				}
			}
			if(name == null) {
				alert("请选择要删除的内容");
				return false;
			}
			else if(window.confirm("确实要删除:" + name)) {
				location.href = "systemXmlAction!del.action?nodeName=" + nodeName + "&code=" + code+"&flag=1";
			} else {
				return false;
			}
		}
		//修改验证函数	
		function updateConfirm(selectName) {
			var selObj = document.getElementById(selectName);
			var nodeName = selectName;
			var oldName
			var code;
			
			for(var i = 0; i < selObj.length; i ++ ) {
				if(selObj.options[i].selected == true) {
					oldName = selObj.options[i].text;
					code = selObj.options[i].value;
				}
			}
			if((oldName != null) && (oldName != "")) {
				var newName = window.prompt("请输入要修改的内容：",oldName);
				newName = encodeURI(newName);
				if((newName != null) && (newName != "")) {
					window.location.href="systemXmlAction!update.action?nodeName=" + nodeName + "&code=" + code + "&newName=" + newName+"&flag=1";
				} else {
					return false;
				}
			} else {
				alert("请选择要修改的内容");
				return false;
			}
			
		}
		//修改数据库配置
		function updateAll(selectName,reCode) {
			var selObj = document.getElementById(reCode);
			//var nodeName = selectName;
			var oldName=selObj.value;
			var newName = window.prompt("请输入要修改的内容：",oldName);
			if((oldName != null) && (oldName != "")) {
				if((newName != null) && (newName != "")){
					newName = encodeURI(newName);
				window.location.href="systemXmlAction!update.action?nodeName=" + selectName +"&code =" + reCode+"&newName="+newName+"&flag=1" ;
				}else{
					return false;
				}
			} else {
				alert("请选择要修改的内容");
				return false;
			}
			
		}
		//验证输入的级别是否重复
		function checkSame(ary){	
			var s = ary.split(",");
			 for(var i=0;i<s.length;i++) {
				if(ary.replace(s[i],"").indexOf(s[i])>-1) {
					return false;
				}
			}
				return true;
		}
		//验证当前输入的级别在系统中是否存在。
		function validate(id){
			var name=document.getElementById("name"+id)
			var value=name.value.replace(/^\s+|\s+$/g,"");

			//判断是否重复
			var length=${codeTempSize};
			var str="";
			for(var i =0;i<length;i++){
					var grade=document.getElementById("grade"+(i+1)).value+",";
					str+=grade;				
				}
			str=str.substring(0,str.lastIndexOf(","));
			
			if(value==""){
				alert("名称不能为空");
				name.focus();
				name.select();
				return;
			}			
			var grade=document.getElementById("grade"+id);
			var gradeValue=grade.value;
			systemXmlServiceJS.validateGrade(gradeValue,function(flag){
				if(!flag){
						alert("您输入的级别在系统组织机构中不存在，请重新输入");
						grade.focus();
						grade.select();
						return;
					}{
						if(!checkSame(str)){
							alert("级别不能重复。请重新输入");
							grade.focus();
							grade.select();
							return;
						}
						updateCodeTemp(id);
					}
				});
		}

		//新增加项目编码。
		function addCodeTemp(){
			var width=600;
			var height=400;
			var url="systemXmlAction!goAddPage.action";
			var returnValue=window.showModalDialog(url, null, "dialogWidth=" + width + "px;dialogHeight=" + height + "px;resizable:yes;");			
			if(null!=returnValue&&undefined!=returnValue){
				if(returnValue){
					this.location.href="systemXmlAction!listSystemXml.action";
				}
			}
		}
		//删除模板项目编码配置
		function deleteCodeTemp(selectNode,id){
			var name=document.getElementById("name"+id).value;
			var grade=document.getElementById("grade"+id).value;
			if(window.confirm("您确定要删除:" + name)) {
				//grade = encodeURI(encodeURI(grade,"utf-8"),"utf-8");
				this.location.href = "systemXmlAction!delByCodeTemp.action?nodeName=" + selectNode + "&flag=1&code=" + grade;
			}
		}

		//修改项目编码生成规则
		function updateCodeTemp(id){
			if(confirm("您确定要保存修改吗?")){
				var name=document.getElementById("name"+id).value;
				var code=document.getElementById("code"+id).value;
				var grade=document.getElementById("grade"+id).value;
				var chose=document.getElementById("chose"+id);
				if(chose.checked==true){
					chose=1;
				}else{
					chose=0;
				}
				name = encodeURI(encodeURI(name,"utf-8"),"utf-8");
				code = encodeURI(encodeURI(code,"utf-8"),"utf-8");
				this.location.href = "systemXmlAction!updateByCodeTemp.action?nodeName=codeTemp" + "&flag=1&code=" + grade+"&codeTemp.name="+name+"&codeTemp.chose="+chose+"&codeTemp.code="+code;	
			}
		}

		function updateChange(xmlTagName, atrrName) {
			var a = document.getElementsByName(atrrName);
			for(var i = 0;i<a.length;i++){
				if(a[i].checked==true){
					systemXmlServiceJS.updateScope(xmlTagName, a[i].value);
				}
			}
		}
		
		//修改客户查看范围
		function updateKu(){
			var a = document.getElementsByName("fanwei");
			for(var i = 0;i<a.length;i++){
				if(a[i].checked==true){
					systemXmlServiceJS.updateScope('kuckfw',a[i].value);
				}
			}
		}
		//修改供应商查看范围
		function updateGys(){
			var b = document.getElementsByName("gongyingshangfw");
			for(var i = 0;i<b.length;i++){
				if(b[i].checked==true){
					systemXmlServiceJS.updateScope('gysckfw',b[i].value);
				}
			}
		}
		//修改项目查看范围
		function updateXm(){
			var c = document.getElementsByName("xiangmufw");
			for(var i = 0;i<c.length;i++){
				if(c[i].checked==true){
					systemXmlServiceJS.updateScope('xmckfw',c[i].value);
				}
			}
		}
	</script>
	</head>

	<body>
	<table width="99%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td align="left" class="non_topborder">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC">
      	<tr class="title_style">
      	<td align="left">配置信息</td>
      	<td colspan="5" >&nbsp;</td>
      	</tr>
        <tr class="row_bg">
				
				<!-- 
				<td align="right">
					对象状态:
				</td>
				<td>
					<select size="1" id="dxzt" style="width:150px">
						<s:iterator value="#request.dxzt" id="dxzt">
							<option value="<s:property value="#dxzt.code"/>">
								<s:property value="#dxzt.name" />
							</option>
						</s:iterator>
					</select>
					<input type="button" value="添加" onclick="return addConfirm('dxzt')">
					<input type="button" value="删除" onclick="delConfirm('dxzt')">
					<input type="button" value="修改"
						onclick="return updateConfirm('dxzt');" />
				</td>
				 -->
			</tr>

			<tr>
			<td align="right">
					结算方式:
				</td>
				<td>
					<select size="1" id="jsfs" style="width:150px">
						<s:iterator value="#request.jsfs" id="jsfs">
							<option value="<s:property value="#jsfs.code"/>">
								<s:property value="#jsfs.name" />
							</option>
						</s:iterator>
					</select>
					<input type="button" value="添加" onclick="return addConfirm('jsfs')">
					<input type="button" value="删除" onclick="delConfirm('jsfs')">
					<input type="button" value="修改"
						onclick="return updateConfirm('jsfs');" />
				</td>
				<td align="right">
					项目附件类型:
				</td>
				<td>
					<select size="1" id="xmfjlb" style="width:150px">
						<s:iterator value="#request.xmfjlb" id="xmfjlb">
							<option value="<s:property value="#xmfjlb.code"/>">
								<s:property value="#xmfjlb.name" />
							</option>
						</s:iterator>
					</select>
					<input type="button" value="添加"
						onclick="return addConfirm('xmfjlb')">
					<input type="button" value="删除" onclick="delConfirm('xmfjlb')">
					<input type="button" value="修改"
						onclick="return updateConfirm('xmfjlb');" />
				</td>
				
				<!-- <td align="right">
					流程类别:
				</td>
				<td>
					<select size="1" id="lclb" style="width:150px">
						<s:iterator value="#request.lclb" id="lclb">
							<option value="<s:property value="#lclb.code"/>">
								<s:property value="#lclb.name" />
							</option>
						</s:iterator>
					</select>
					<input type="button" value="添加" onclick="return addConfirm('lclb')">
					<input type="button" value="删除" onclick="delConfirm('lclb')">
					<input type="button" value="修改"
						onclick="return updateConfirm('lclb');" />
				</td>
				 -->
			</tr>
			
			
			<tr>
				<td align="right">
					流程审批:
				</td>
				<td>
					<select size="1" id="lcsp" style="width:150px">
						<s:iterator value="#request.lcsp" id="lcsp">
							<option value="<s:property value="#lcsp.code"/>">
								<s:property value="#lcsp.name" />
							</option>
						</s:iterator>
					</select>
					<input type="button" value="添加" onclick="return addConfirm('lcsp')">
					<input type="button" value="删除" onclick="delConfirm('lcsp')">
					<input type="button" value="修改"
						onclick="return updateConfirm('lcsp');" />
				</td>
				<td align="right">
					模板类别:
				</td>
				<td>
					<select size="1" id="mblb" style="width:150px">
						<s:iterator value="#request.mblb" id="mblb">
							<option value="<s:property value="#mblb.code"/>">
								<s:property value="#mblb.name" />
							</option>
						</s:iterator>
					</select>
					<input type="button" value="添加" onclick="return addConfirm('mblb')">
					<input type="button" value="删除" onclick="delConfirm('mblb')">
					<input type="button" value="修改"
						onclick="return updateConfirm('mblb');" />
				</td>
			<!-- 
				<td align="right">
					流程状态:
				</td>
				<td>
					<select size="1" id="lczt" style="width:150px">
						<s:iterator value="#request.lczt" id="lczt">
							<option value="<s:property value="#lczt.code"/>">
								<s:property value="#lczt.name" />
							</option>
						</s:iterator>
					</select>
					<input type="button" value="添加" onclick="return addConfirm('lczt')">
					<input type="button" value="删除" onclick="delConfirm('lczt')">
					<input type="button" value="修改"
						onclick="return updateConfirm('lczt');" />
				</td>
				 -->
				 
			</tr>
			
			
			<tr>
				<!-- <td align="right">
					临时流状态:
				</td>
				<td>
					<select size="1" id="lslzt" style="width:150px">
						<s:iterator value="#request.lslzt" id="lslzt">
							<option value="<s:property value="#lslzt.code"/>">
								<s:property value="#lslzt.name" />
							</option>
						</s:iterator>
					</select>
					<input type="button" value="添加"
						onclick="return addConfirm('lslzt')">
					<input type="button" value="删除" onclick="delConfirm('lslzt')">
					<input type="button" value="修改"
						onclick="return updateConfirm('lslzt');" />
				</td>
			 -->
				
			</tr>
			
			<tr>
			<!-- 
				<td align="right">
					钱币种类:
				</td>
				<td>
					<select size="1" id="qbzl" style="width:150px">
						<s:iterator value="#request.qbzl" id="qbzl">
							<option value="<s:property value="#qbzl.code"/>">
								<s:property value="#qbzl.name" />
							</option>
						</s:iterator>
					</select>
					<input type="button" value="添加" onclick="return addConfirm('qbzl')">
					<input type="button" value="删除" onclick="delConfirm('qbzl')">
					<input type="button" value="修改"
						onclick="return updateConfirm('qbzl');" />
				</td>
			 -->
			 <td align="right">
					合同状态:
				</td>
				<td>
					
					<s:if test="#request.base.sfxzlc==1">
						<select size="1" id="htzt" style="width:150px">
						<s:iterator value="#request.htzt" id="htzt">
							<option value="<s:property value="#htzt.code"/>">
								<s:property value="#htzt.name" />
							</option>
						</s:iterator>
						</select>
						<input type="button" value="添加" onclick="return addConfirm('htzt')">
						<input type="button" value="删除" onclick="delConfirm('htzt')">
						<input type="button" value="修改" onclick="return updateConfirm('htzt');" />
					</s:if>
					<s:else>
						<select size="1" id="spzhtzt" style="width:150px">
						<s:iterator value="#request.htzt" id="htzt">
							<option value="<s:property value="#htzt.code"/>">
								<s:property value="#htzt.name" />
							</option>
						</s:iterator>
						</select>
						<input type="button" value="添加" onclick="return addConfirm('spzhtzt')">
						<input type="button" value="删除" onclick="delConfirm('spzhtzt')">
						<input type="button" value="修改" onclick="return updateConfirm('spzhtzt');" />
					</s:else>
				</td>
				<td align="right">
					客户类别:
				</td>
				<td>
					<select size="1" id="kulb" style="width:150px">
						<s:iterator value="#request.kulb" id="kulb">
							<option value="<s:property value="#kulb.code"/>" >
								<s:property value="#kulb.name" />
							</option>
							
						</s:iterator>
					</select>
					<input type="button" value="添加" onclick="return addConfirm('kulb')">
					<input type="button" value="删除" onclick="delConfirm('kulb')">
					<input type="button" value="修改"
						onclick="return updateConfirm('kulb');" />
				</td>
				
				
			</tr>
			
			
			<tr>
				<td align="right">
					合同类别:
				</td>
				<td>
					<select size="1" id="htlb" style="width:150px">
						<s:iterator value="#request.htlb" id="htlb">
							<option value="<s:property value="#htlb.code"/>">
								<s:property value="#htlb.name" />
							</option>
						</s:iterator>
					</select>
					<input type="button" value="添加" onclick="return addConfirm('htlb')">
					<input type="button" value="删除" onclick="delConfirm('htlb')">
					<input type="button" value="修改"
						onclick="return updateConfirm('htlb');" />
				</td>
					
					<td align="right">
								客户信用等级:
					</td>
					<td>
						<select size="1" id="khxydj" style="width:150px">
							<s:iterator value="#request.khxydj" var="khxydj">
								<option value="<s:property value="#khxydj.code"/>">
									<s:property value="#khxydj.name" />
								</option>
							</s:iterator>
						</select>
						<input type="button" value="添加" onclick="return addConfirm('khxydj')">
						<input type="button" value="删除" onclick="delConfirm('khxydj')">
						<input type="button" value="修改"
						onclick="return updateConfirm('khxydj');" />
					</td>
				
			</tr>
			<tr>
					
			</tr>
			<tr>
					<td align="right">
						合同前缀:
					</td>
					<td>
						<select size="1" id="htbh" style="width:150px">
							<s:iterator value="#request.base" id="htbh">
								<option value="<s:property value="#htbh.htbh"/>">
									<s:property value="#htbh.htbh" />
								</option>
							</s:iterator>
						</select>
						<input type="button" value="修改"
							onclick="return updateAll('ggbm','htbh');" />
					</td>
				<td align="right">
								供应商类别:
					</td>
					<td>
						<select size="1" id="gyslb" style="width:150px">
							<s:iterator value="#request.gyslb" var="gyslb">
								<option value="<s:property value="#gyslb.code"/>">
									<s:property value="#gyslb.name" />
								</option>
							</s:iterator>
						</select>
						<input type="button" value="添加" onclick="return addConfirm('gyslb')">
						<input type="button" value="删除" onclick="delConfirm('gyslb')">
						<input type="button" value="修改"
						onclick="return updateConfirm('gyslb');" />
					</td>
			</tr>
			
			<tr>
				<td align="right">
						上传路径:
					</td>
					<td>
						<select size="1" id="scwj" style="width:150px">
							<s:iterator value="#request.base" id="scwj">
								<option value="<s:property value="#scwj.scwj"/>">
									<s:property value="#scwj.scwj" />
								</option>
							</s:iterator>
						</select>
						<input type="button" value="修改"
								onclick="return updateAll('ggbm','scwj');" />
					</td>
					<td align="right">
								供应商信用等级:
					</td>
					<td>
						<select size="1" id="gysxydj" style="width:150px">
							<s:iterator value="#request.gysxydj" var="gysxydj">
								<option value="<s:property value="#gysxydj.code"/>">
									<s:property value="#gysxydj.name" />
								</option>
							</s:iterator>
						</select>
						<input type="button" value="添加" onclick="return addConfirm('gysxydj')">
						<input type="button" value="删除" onclick="delConfirm('gysxydj')">
						<input type="button" value="修改"
						onclick="return updateConfirm('gysxydj');" />
					</td>
				
			</tr>
			<tr>
				<td align="right">
						加密pin:
					</td>
					<td>
						<select size="1" id="userpin" style="width:150px">
							<s:iterator value="#request.et" id="userpin">
								<option value="<s:property value="#userpin.userpin"/>">
									<s:property value="#userpin.userpin" />
								</option>
							</s:iterator>
						</select>
						<input type="button" value="修改"
								onclick="return updateAll('ET99','userpin');">
					</td>
					<td align="right">
						是否启用短信功能:
					</td>
					<td>
						<select size="1" id="sms" style="width:150px">
							<s:iterator value="#request.base" id="sms">
								<option value="<s:property value="#sms.sms"/>">
									<s:property value="#sms.sms" />
								</option>
							</s:iterator>
						</select>
						<input type="button" value="修改"
							onclick="return updateAll('ggbm','sms');" />
					</td>
				<!-- 
				<td align="right">
					合同文本类别:
				</td>
				<td>
					<select size="1" id="htwblb" style="width:150px">
						<s:iterator value="#request.htwblb" id="htwblb">
							<option value="<s:property value="#htwblb.code"/>">
								<s:property value="#htwblb.name" />
							</option>
						</s:iterator>
					</select>
					<input type="button" value="添加"
						onclick="return addConfirm('htwblb')">
					<input type="button" value="删除" onclick="delConfirm('htwblb')">
					<input type="button" value="修改"
						onclick="return updateConfirm('htwblb');" />
				</td>
				 -->
			</tr>
			<tr>
				<td align="right">
					加密pid:
				</td>
				<td>
					<select size="1" id="pid" style="width:150px">
						<s:iterator value="#request.et" id="pid">
							<option value="<s:property value="#pid.pid"/>">
								<s:property value="#pid.pid" />
							</option>
						</s:iterator>
					</select>
					<input type="button" value="修改"
						onclick="return updateAll('ET99','pid');">
				</td>
				<td align="right">
						数据库:
					</td>
					<td>
						<select size="1" id="name" style="width:150px">
							<s:iterator value="#request.base" id="name">
								<option value="<s:property value="#name.name"/>">
									<s:property value="#name.name" />
								</option>
							</s:iterator>
						</select>
						<input type="button" value="修改"
								onclick="return updateAll('ggbm','name');" />
					</td>	
			</tr>
			<tr>
			<td align="right">
						是否选择流程:
					</td>
					<td>
						<select size="1" id="sfxzlc" style="width:150px">
							<s:iterator value="#request.base" id="sfxzlc">
								<option value="<s:property value="#sfxzlc.sfxzlc"/>">
									<s:property value="#sfxzlc.sfxzlc" />
								</option>
							</s:iterator>
						</select>
						<input type="button" value="修改"
							onclick="return updateAll('ggbm','sfxzlc');" />
					</td>
					<td align="right">
						编码位数:
					</td>
					<td>
						<select size="1" id="xmbh" style="width:150px">
							<s:iterator value="#request.base" id="xmbh">
								<option value="<s:property value="#xmbh.xmbh"/>">
									<s:property value="#xmbh.xmbh" />
								</option>
							</s:iterator>
						</select>
						<input type="button" value="修改"
							onclick="return updateAll('ggbm','xmbh');" />
					</td>
			</tr>
			<tr>
			<td align="right">
					客户查看范围:
				</td>
				<td>
					<s:if test="#request.kuckfw.code==1">
						个人<input onclick="updateChange('fanwei','kuckfw')" type="radio" checked="checked" name="fanwei" value="1" />
					</s:if>
					<s:else>
						个人<input  onclick="updateChange('fanwei','kuckfw')" type="radio"  name="fanwei" value="1" />
					</s:else>
					
					<s:if test="#request.kuckfw.code==2">
						所在部门<input  onclick="updateChange('fanwei','kuckfw')" type="radio" checked="checked" name="fanwei" value="2" />
					</s:if>
					<s:else>
						所在部门<input  onclick="updateChange('fanwei','kuckfw')" type="radio"  name="fanwei" value="2" />
					</s:else>
					
					<s:if test="#request.kuckfw.code==3">
						所在子公司<input  onclick="updateChange('fanwei','kuckfw')" type="radio" checked="checked" name="fanwei" value="3" />
					</s:if>
					<s:else>
						所在子公司<input  onclick="updateChange('fanwei','kuckfw')" type="radio"  name="fanwei" value="3" />
					</s:else>
					
					<s:if test="#request.kuckfw.code==4">
						所有<input  onclick="updateChange('fanwei','kuckfw')" type="radio" checked="checked" name="fanwei" value="4" />
					</s:if>
					<s:else>
						所有<input onclick="updateChange('fanwei','kuckfw')" type="radio"  name="fanwei" value="4" />
					</s:else>
				</td>
				<td align="right">
					供应商查看范围:
					</td>
					<td>
						<s:if test="#request.gysckfw.code==1">
							个人<input onclick="updateChange('gongyingshangfw','gysckfw')" type="radio" checked="checked" name="gongyingshangfw" value="1" />
						</s:if>
						<s:else>
							个人<input  onclick="updateChange('gongyingshangfw','gysckfw')"type="radio"  name="gongyingshangfw" value="1" />
						</s:else>
					<s:if test="#request.gysckfw.code==2">
						所在部门<input  onclick="updateChange('gongyingshangfw','gysckfw')" type="radio" checked="checked" name="gongyingshangfw" value="2" />
					</s:if>
					<s:else>
						所在部门<input  onclick="updateChange('gongyingshangfw','gysckfw')" type="radio"  name="gongyingshangfw" value="2" />
					</s:else>
					
					<s:if test="#request.gysckfw.code==3">
						所在子公司<input  onclick="updateChange('gongyingshangfw','gysckfw')" type="radio" checked="checked" name="gongyingshangfw" value="3" />
					</s:if>
					<s:else>
						所在子公司<input  onclick="updateChange('gongyingshangfw','gysckfw')" type="radio"  name="gongyingshangfw" value="3" />
					</s:else>
					
					<s:if test="#request.gysckfw.code==4">
						所有<input  onclick="updateChange('gongyingshangfw','gysckfw')" type="radio" checked="checked" name="gongyingshangfw" value="4" />
					</s:if>
					<s:else>
						所有<input onclick="updateChange('gongyingshangfw','gysckfw')" type="radio"  name="gongyingshangfw" value="4" />
					</s:else>
					</td>
			</tr>	
			<tr>
				<td align="right">
					项目查看范围:
				</td>
				<td>
					<s:if test="#request.xmckfw.code==1">
						个人<input onclick="updateChange('xiangmufw','xmckfw')" type="radio" checked="checked" name="xiangmufw" value="1" />
					</s:if>
					<s:else>
						个人<input  onclick="updateChange('xiangmufw','xmckfw')" type="radio"  name="xiangmufw" value="1" />
					</s:else>
					
					<s:if test="#request.xmckfw.code==2">
						所在部门<input  onclick="updateChange('xiangmufw','xmckfw')" type="radio" checked="checked" name="xiangmufw" value="2" />
					</s:if>
					<s:else>
						所在部门<input  onclick="updateChange('xiangmufw','xmckfw')" type="radio"  name="xiangmufw" value="2" />
					</s:else>
					
					<s:if test="#request.xmckfw.code==3">
						所在子公司<input  onclick="updateChange('xiangmufw','xmckfw')" type="radio" checked="checked" name="xiangmufw" value="3" />
					</s:if>
					<s:else>
						所在子公司<input  onclick="updateChange('xiangmufw','xmckfw')" type="radio"  name="xiangmufw" value="3" />
					</s:else>
					
					<s:if test="#request.xmckfw.code==4">
						所有<input  onclick="updateChange('xiangmufw','xmckfw')" type="radio" checked="checked" name="xiangmufw" value="4" />
					</s:if>
					<s:else>
						所有<input onclick="updateChange('xiangmufw','xmckfw')" type="radio"  name="xiangmufw" value="4" />
					</s:else>
				</td>
				<td align="right">
					年度查询查看范围：
				</td>
				<td>
					<s:if test="#request.contractQuery.scope==0">
						本级公司<input type="radio" onclick="updateChange('contractQuery','ndcx')" checked="checked" name="contractQuery" value="0"/>
						本级公司及下级公司<input type="radio" onclick="updateChange('contractQuery','ndcx')" name="contractQuery" value="1"/>
					</s:if>
					<s:if test="#request.contractQuery.scope==1">
						本级公司<input type="radio" onclick="updateChange('contractQuery','ndcx')" name="contractQuery" value="0" />
						本级公司及下级公司<input type="radio" onclick="updateChange('contractQuery','ndcx')" checked="checked" name="contractQuery" value="1" />
					</s:if>
				</td>
			</tr>	
		</table>
		</td>
	</tr>
	<tr>
		<td  align="left" class="non_topborder">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC">
			   	<tr class="title_style">
				   	<td align="left" colspan="5" style="text-align:left;padding-left:30px;">编码生成模板</td>	  
			   	</tr>
			   	 <tr class="row_bg">
				    	<td width ="30px">
							启用				    	
				    	</td>
				    	<td width="200px;">
							名称				    	
				    	</td>
				    	<td width="200px;">
							编码前缀				    	
				    	</td>	
				    	<td width="200px;">
							级别				    	
				    	</td>
				    	<td>
							操作				    	
				    	</td>	
				    </tr>
			   	<c:if test="${!empty codeTemp}">
			   	<c:forEach items="${codeTemp}" var="codeTemp" varStatus="i">
				    <tr class="row_bg">
				    	<td>
				    		<c:if test="${codeTemp.chose == '1'}">
								<input type="checkBox" id="chose${i.index+1 }" checked="checked" value="1">				    	
				    		</c:if>
				    		<c:if test="${codeTemp.chose == '0'}">
								<input type="checkBox" id="chose${i.index+1 }" value="1">				    	
				    		</c:if>
				    	</td>
				    	<td>
							<input type="text" id="name${i.index+1 }" value="${codeTemp.name }"/>				    	
				    	</td>
				    	<td>
							<input type="text" id="code${i.index+1 }" value="${codeTemp.code }" onKeyUp="value=value.replace(/[_]/g,'')"/>			    	
				    	</td>	
				    	<td>
							<input type="text" id="grade${i.index+1 }" value="${codeTemp.grade }" onKeyUp="value=value.replace(/[^\d]/g,'')"/>				    	
				    	</td>
				    	<td>
				    		<input type="button" id="addbtn${i.index+1 }" value="添加" onclick="javascript:addCodeTemp();"/>
				    		<input type="button" id="btn${i.index+1}" value="保存" onclick = "javascript:validate(${i.index+1});"/>	
							<input type="button" id="delbtn${i.index+1} }" value="删除" onclick="deleteCodeTemp('codeTemp',${i.index+1});"/>		
				    	</td>	
				    </tr>
			    </c:forEach>
			    </c:if>
			     <c:if test="${empty codeTemp}">
			    	<tr>
			    		<td colspan="5" align="center">
			    			<input type="button" id="addbtn${i.index+1 }" value="添加" onclick="javascript:addCodeTemp();"/>
			    		</td>
			    	</tr>
			    </c:if>
			</table>
		</td>
	</tr>
</table>
<div>
			<div>
				年度查询查看范围：				
				<s:if test="#request.contractQuery.code==0">
					本级公司<input type="radio" checked="checked" name="contractQuery" value="0" onclick=""/>
				</s:if>
				<s:if test="#request.contractQuery.code==1">
					本级公司及下级公司<input type="radio" checked="checked" name="contractQuery" value="1" onclick=""/>
				</s:if>
			</div>
		</div>

	</body>
</html>




