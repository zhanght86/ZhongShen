<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/view/common/common.jsp" %>
<%@ taglib prefix="wf" uri="/auite-tags"%>
<%@ taglib prefix="o" uri="/opinion-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<base href="<%=basePath%>">
		<title>添加功能页面</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="<%=basePath%>css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="js/dataCheck.js"></script>
		<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>js/json.js"></script>
<script type="text/javascript">
	var cGetRow=-99999;
	var idx =0;
	var startidx=0;
	var num =0;
	var trow='';
	var jsonObj;
  function returnSonValue(){
	var role_id =document.getElementById("line.lineLink_role").value;
	goSubmit();
	var prop =document.getElementById("line.lineLink_props").value;
    window.returnValue = "*lineLink_role="+role_id+"*lineLink_props="+prop;
	window.close();
  }
  
  
  function getPerson(id,name){  	
 			 var emplIds =document.getElementById(id).value;
			var url="notice!toGetEmpls.action?time="+new Date().getTime()+"&emplIds="+emplIds;
				var returnVal=window.showModalDialog(url,"选择人员","dialogWidth:700px;");
				if(returnVal!=null){
					//此窗口的返回值为一个二维数组arr
					//arr[0]是选择的用户的id,arr[1]是选择的用户的用户名
					//如果没有做任何选择哪么arr[0],arr[1]的长度为0
					var str3 = ArrayToString(returnVal[0]);
					var str4 = ArrayToString(returnVal[1]);
					window.document.forms[0].elements[id].value=str3;
					window.document.forms[0].elements[name].value=str4;
				}else{
					return;
				}
				
		}
		
  function ArrayToString(arr) {
			var str = "";
			for (var i = 0; i < arr.length; i++) {
				str +=  arr[i]+"_";
			}
			str = str.substring(0,str.length-1) ;
			return str;
		}
		
		
		


function onLoadInfo(){
	
	//初始化连接线数据
	var lineProps='${linePropties}';
	var lineObjs =lineProps.split(",");
	var temp="";
	for(var i =0;i<lineObjs.length;i++){
		var objs =lineObjs[i].split("*");
		if(objs == null)  continue;
		for(var j =0;j<objs.length;j++){
			if(objs[j].indexOf("tache_name")!=-1){
				temp+=objs[j].replace("tache_name=","")+",";
			}
			
			if(objs[j].indexOf("lineLink_role")!=-1){
				temp+=objs[j].replace("lineLink_role=","")+",";
			}
			
			if(objs[j].indexOf("lineLink_props")!=-1){
				temp+=objs[j].replace("lineLink_props=","")+",";
			}
		}
	}
	
	if(temp.length!=0) temp =temp.substring(0,temp.length-1);
	
	var temps =temp.split(",");
	document.getElementById("beforeTacheId").value=temps[0];
	document.getElementById("endTacheId").value=temps[1];
	if(temps.length>2){
		document.getElementById("line.lineLink_role").value=temps[2];
		document.getElementById("line.lineLink_props").value=temps[3];
		
		$.get("system/role!getRoleName.action?roleIds="+temps[2]+"&nocache=" + new Date().getTime(),function(data){
			document.getElementById("roleName").value=data;
		},"text");
		
	}
		createJsonJob();  //转换json
}


  function getRoles(){
  	var url ="<%=basePath%>system/role!chooseRole.action?urlType=checkbox";
  	var returnVal=window.showModalDialog(url,"选择角色","dialogWidth:500px;");
  	if(returnVal!=null){
  		window.document.forms[0].elements["line.lineLink_role"].value=returnVal.split(",")[0];
  	
   		window.document.forms[0].elements["roleName"].value=returnVal.split(",")[1];
  	}
  }
  
  function changeEmpModel(){
	if(val ==1){
		document.getElementById("chooseRole").style.display="block";
		document.getElementById("sysId").style.display="block";
	}
  }
  
  function createJsonJob(){
	var jsonVal=document.getElementById("line.lineLink_props").value;
	if(jsonVal=="null") jsonVal="[]";
	if(jsonVal==null){ 
		jsonVal="[]";
	}else{
		if(jsonVal != "[]"){
			jsonObj=eval('('+"[]"+')');
			var jobjs =jsonVal.split("FENGEFU");
			for(var i =0; i < jobjs.length-1; i++){
				
				var jobs = jobjs[i].split("ZSKJYXGSZSKJYXGS");
				//alert(jobs);
				var p_name=jobs[0];
				var logic=jobs[1].replace("[","").replace("]","");
				var p_value=jobs[2];
				var oper="";
				var p_name_t="";
				var p_value_t="";
				var logic_t="";
				

				if(jobs.length>4){
					oper=jobs[3].replace("[","").replace("]","");
					p_name_t=jobs[4];
					logic_t=jobs[5].replace("[","").replace("]","");
					p_value_t=jobs[6];
				}
				var j="{\"prop_name\":null,\"logic\":null,\"prop_value\":null,\"oper\":null,\"prop_name_two\":null,\"logic_two\":null,\"prop_value_two\":null,\"classType\":0}";
				var jobj=eval('('+j+')');
				
				if(p_name=="") p_name=null;
				if(p_value=="") p_value=null;
				if(logic=="") logic=null;
				if(oper=="") oper=null;
				if(p_name_t=="") p_name_t=null;
				if(logic_t=="") logic_t=null;
				if(p_value_t=="") p_value_t=null;
				jobj.prop_name=p_name;
				jobj.prop_value=p_value;
				jobj.logic=logic;
				jobj.oper=oper;
				jobj.prop_name_two=p_name_t;
				jobj.logic_two=logic_t;
				
				jobj.prop_value_two=p_value_t;
				
				jsonObj.push(jobj);
			}
			var last=JSON.stringify(jsonObj); //将JSON对象转化为JSON字符
			//alert(last);
			initRow()
			return ;
		}
	}

	jsonObj=eval('('+jsonVal+')');
	var last=JSON.stringify(jsonObj); //将JSON对象转化为JSON字符
	
}

	function fnBanBackSpace() {
 	  if(event.keyCode ==188){
	  	event.returnValue=false;
   	  }
	}
	//0:默认 1:添加    2：修改  3：删除 	
	//{"prop_name":"","logic":"","prop_value":"","prop_name_two":"","oper":null,"logic_tow":"","prop_value_two":"","classType":null}
	function AddRow()
	{
		var tab1 =document.getElementById("tab1");
		//添加一行
		var newTr = tab1.insertRow();
		//添加列
		var newTd0 = newTr.insertCell();
		var newTd1 = newTr.insertCell();
		var newTd2 = newTr.insertCell();
		var newTd3 = newTr.insertCell();
		var newTd4 = newTr.insertCell();
		var newTd5 = newTr.insertCell();
		var newTd6 = newTr.insertCell();
		var newTd7 = newTr.insertCell();

		
		newTd0.innerHTML ='<input type=radio id=box'+idx+' name=rowIdx  onclick=\'GetRow(this.id)\'/ value='+idx+' />';
		newTd1.innerHTML= '<input type=text id=prop_name'+idx+' name=\'prop_name\' onkeydown=fnBanBackSpace() onpropertychange="addObj(this.value,this.name,this.id,'+idx+')" style="width:100px" />';
		newTd2.innerHTML= '<select id=logic'+idx+' name="logic" onchange="addObj(this.value,this.name,this.id,'+idx+')"><option value="">请选择</option><option value="[>]" >大于</option><option value="[>=]">大于等于</option><option value="[==]">等于</option><option value="[<]">小于</option><option value="[<=]">小于等于</option></select>';
		newTd3.innerHTML= '<input type=text id=prop_value'+idx+' name=prop_value value=\'\' onkeydown=fnBanBackSpace() onpropertychange="addObj(this.value,this.name,this.id,'+idx+')" style="width:100px" />';
		newTd4.innerHTML = '<select id=oper'+idx+' name="oper" onchange="addObj(this.value,this.name,this.id,'+idx+')"><option value="">请选择</option><option value="[||]" >或者</option><option value="[BINGQIE]">并且</option></select>';
		newTd5.innerHTML= '<input type=text id=prop_name_two'+idx+' name=\'prop_name_two\' value=\'\' onkeydown=fnBanBackSpace() onpropertychange="addObj(this.value,this.name,this.id,'+idx+')"  style="width:100px"/>';
		newTd6.innerHTML= '<select id=logic_two'+idx+' name="logic_two" onchange="addObj(this.value,this.name,this.id,'+idx+')"><option value="">请选择</option><option value="[>]">大于</option><option value="[>=]">大于等于</option><option value="[==]">等于</option><option value="[<]">小于</option><option value="[<=]">小于等于</option></select>';
		newTd7.innerHTML= '<input type=text id=prop_value_two'+idx+' name=prop_value_two value=\'\' onkeydown=fnBanBackSpace() onpropertychange="addObj(this.value,this.name,this.id,'+idx+')"  style="width:200px" />';
		
		
		jsonObj.push({"prop_name":null,"logic":null,"prop_value":null,"oper":null,"prop_name_two":null,"logic_two":null,"prop_value_two":null,"classType":1});
		var last=JSON.stringify(jsonObj); //将JSON对象转化为JSON字符
		//alert(last);
		idx++;
	}
	
	
	function initRow()
	{
		var tab1 =document.getElementById("tab1");
		for(var i =0;i<jsonObj.length;i++){
			var ob=jsonObj[i];
			//添加一行
			var newTr = tab1.insertRow();
			//添加列
			var newTd0 = newTr.insertCell();
			var newTd1 = newTr.insertCell();
			var newTd2 = newTr.insertCell();
			var newTd3 = newTr.insertCell();
			var newTd4 = newTr.insertCell();
			var newTd5 = newTr.insertCell();
			var newTd6 = newTr.insertCell();
			var newTd7 = newTr.insertCell();
	
			newTd0.innerHTML ='<input type=radio id=box'+idx+' name=rowIdx  onclick=\'GetRow(this.id)\'/ value='+idx+' />';
			newTd1.innerHTML= '<input type=text id=prop_name'+idx+' value="'+ob.prop_name +'" name=\'prop_name\' onkeydown=fnBanBackSpace() onpropertychange="addObj(this.value,this.name,this.id,'+idx+')" style="width:100px" />';
			newTd2.innerHTML= '<select id=logic'+idx+' name="logic" value="'+ob.logic +'" onpropertychange="addObj(this.value,this.name,this.id,'+idx+')"><option value="">请选择</option><option value="[>]" >大于</option><option value="[>=]">大于等于</option><option value="[==]">等于</option><option value="[<]">小于</option><option value="[<=]">小于等于</option></select>';
			newTd3.innerHTML= '<input type=text id=prop_value'+idx+' value="'+ob.prop_value +'" name=prop_value value=\'\' onkeydown=fnBanBackSpace() onpropertychange="addObj(this.value,this.name,this.id,'+idx+')" style="width:100px" />';
			newTd4.innerHTML = '<select id=oper'+idx+' name="oper" value="'+ob.oper +'" onchange="addObj(this.value,this.name,this.id,'+idx+')"><option value="">请选择</option><option value="[||]" >或者</option><option value="[BINGQIE]">并且</option></select>';
			newTd5.innerHTML= '<input type=text id=prop_name_two'+idx+' value="'+ob.prop_name_two +'" name=\'prop_name_two\' value=\'\' onkeydown=fnBanBackSpace() onpropertychange="addObj(this.value,this.name,this.id,'+idx+')"  style="width:100px"/>';
			newTd6.innerHTML= '<select id=logic_two'+idx+' name="logic_two" value="'+ob.logic_two +'" onchange="addObj(this.value,this.name,this.id,'+idx+')"><option value="">请选择</option><option value="[>]">大于</option><option value="[>=]">大于等于</option><option value="[==]">等于</option><option value="[<]">小于</option><option value="[<=]">小于等于</option></select>';
			newTd7.innerHTML= '<input type=text id=prop_value_two'+idx+' value="'+ob.prop_value_two +'" name=prop_value_two value=\'\' onkeydown=fnBanBackSpace() onpropertychange="addObj(this.value,this.name,this.id,'+idx+')"  style="width:200px" />';
			initSelect(idx,ob.logic,ob.oper,ob.logic_two);
			idx++;
		}
	}
	
	
	function initSelect(id,lgc,opr,lgct){
		//初始化下拉框被选中
			var logic =document.getElementById("logic"+id);
			for(var i =0;i<logic.options.length;i++){
				if("["+lgc+"]"==logic.options[i].value){
					logic.options[i].selected=true;
					break;
				}
			}
			var oper =document.getElementById("oper"+id);
			for(var i =0;i<oper.options.length;i++){
				if("["+opr+"]"==oper.options[i].value){
					oper.options[i].selected=true;
					break;
				}
			}
			
			var logic_t =document.getElementById("logic_two"+id);
			for(var i =0;i<logic_t.options.length;i++){
				if("["+lgct+"]"==logic_t.options[i].value){
					logic_t.options[i].selected=true;
					break;
				}
			}
	}
	function updateObj(val,name,id,rows){
 		var last=JSON.stringify(jsonObj); //将JSON对象转化为JSON字符
		setVal(val,name,id,rows,2); 
			//alert(last);
	}
	
	//添加
	function addObj(val,name,id,rows){
	 		var last=JSON.stringify(jsonObj); //将JSON对象转化为JSON字符
	 		
			setVal(val,name,id,rows,1); 
			//alert(last);
	}
	//删除
	function deleteObj(rows){
	 		 //将JSON对象转化为JSON字符
	 		var last=JSON.stringify(jsonObj); //将JSON对象转化为JSON字符
			for(var i =0;i<jsonObj.length;i++){
				if(trow==i){
					//jsonObj.classType=3;
					//var type =document.getElementById("items_id"+trow).value
					//if(type==""){
						delete jsonObj[i];
					//}
				}
			}
	}
	
	function setVal(val,name,id,rows,classTypeNum){
		//alert(val+name+id+rows+classTypeNum);
 		var last=JSON.stringify(jsonObj); //将JSON对象转化为JSON字符
 		for(var i =0;i<jsonObj.length;i++){
			if(i==rows){
				var obj=jsonObj[i];
				if(name=="prop_name"){
					obj.prop_name=val;
					continue;
				}
				
				if(name=="logic"){
					obj.logic=val;
					continue;
				}

				if(name=="prop_value"){
					obj.prop_value=val;
					continue;
				}
				if(name=="oper"){
					obj.oper=val;
					continue;
				}
				
				if(name=="prop_name_two"){
					obj.prop_name_two=val;
					continue;
				}
				
				if(name=="logic_two"){
					obj.logic_two=val;
					continue;
				}
				
				if(name=="prop_value_two"){
					obj.prop_value_two=val;
					continue;
				}
				
			}
		}
	
}


// 用于获取列表的数值
function goSubmit(){
	var json=null;
	var last=JSON.stringify(jsonObj); //将JSON对象转化为JSON字符
	var rJson="";  //因为json中可以有元素为 null  在后台java处理的时候会报错。所以要把null的元素删除。建立一个变量来代替
	var r=0;	//表示表格中行
	for(var i =0;i<jsonObj.length;i++){
		var obj =jsonObj[i]
		if(obj==null) continue;
			if(obj.prop_name!=null){
				rJson+= obj.prop_name+"ZSKJYXGSZSKJYXGS";
			}
			
			if(obj.logic!=null){
				rJson+=obj.logic+"ZSKJYXGSZSKJYXGS";
			}
			if(obj.prop_value!=null){
				rJson+=obj.prop_value+"ZSKJYXGSZSKJYXGS";
			}
			
			if(obj.oper!=null){
				rJson+=obj.oper+"ZSKJYXGSZSKJYXGS";
			}
			
			if(obj.prop_name_two!=null){
				rJson+=obj.prop_name_two+"ZSKJYXGSZSKJYXGS";
			}
			
			if(obj.logic_two!=null){
				rJson+=obj.logic_two+"ZSKJYXGSZSKJYXGS";
			}
			
			if(obj.prop_value_two!=null){
				rJson+=obj.prop_value_two+"ZSKJYXGSZSKJYXGS";
			}
			
			r++;
			rJson+="FENGEFU";
			
	}
	document.getElementById("line.lineLink_props").value=rJson;
	
	//$("#addForm").submit();

}

	function DelRow(iIndex)
	{
		//删除一行
		if(iIndex ==-99999){
			alert("选中删除行");
			return ;
		} 
			if(window.confirm("是否删除")){
				deleteObj(iIndex);
				tab1.deleteRow(iIndex);
				cGetRow=-99999;
		}
	}

	function GetRow(id)
	{
		var objs = document.getElementById(id);
		trow=objs.value;
		if(objs.checked ==true){
			cGetRow=window.event.srcElement.parentElement.parentElement.rowIndex;
		}else{
			alert("请选择一行！");
			cGetRow=-99999;
		}
	}
</script>
	</head>
	<body onLoad="onLoadInfo()">
		<div align="center">
			<form action="" method="post"
				accept-charset="utf-8">
				<table width="99%" border="0" cellspacing="0" cellpadding="0"
					style="border:1px solid #A6C4DC">
					<tr class="title_style" >
						<td colspan="8" style="text-align: left;border-bottom:1px solid #A6C4DC">
							<img src="images/group1.png" width="20" height="20"
								align="absmiddle" />
							功能基本信息
						</td>
					</tr>


					<tr class="row_bg">
						<td colspan="8">
							<table width="99%" border="0" cellspacing="0" cellpadding="0" style="font-size:13px">
								<tr bgcolor="#F7FCFF">
									<td width="17%" height="35" align="right">
										开始环节									</td>
									<td width="83%">
										<input type="text" name="beforeTacheId" id ="beforeTacheId"
											value="" />
										
								  </td>
								</tr>
								<tr bgcolor="#F7FCFF">
									<td height="35" align="right">
										结束环节
									</td>
									<td>
										<input type="text" name="endTacheId" id="endTacheId" value="" />
									</td>
								</tr>
								
								
								<tr>
									<td align="right">
										设置连接线使用权限
									</td>
									<td>
									<textarea rows="4" cols="30" name="roleName" id="roleName">${fu:getRoleNamesByCode(line.lineLink_role) }</textarea>
										<!-- <input type="text" name="roleName" id="roleName" value="${fu:getRoleNamesByCode(line.lineLink_role) }" /> -->
										<input type="button" value="请选择角色" onClick="getRoles()"/>
										<input type=hidden name="line.lineLink_role" id="line.lineLink_role" value="${line.lineLink_role }" />
									</td>
								</tr>
								
								<tr>
									<td align="center" colspan="2">
										<h3>设置模板对象属性</h3>
										<input type="hidden" name="line.lineLink_props" id="line.lineLink_props" value="${line.lineLink_props }"/>
										
									</td>
								</tr>
								
								<tr>
									<td height="45" colspan="2" align="center">
										<table id="tab1" width="99%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC">
											<tr class="title_style">
												<td align="center" width="5%">
													
												</td>
												<td align="center" width="15%">
													字段名称
												</td>
												<td align="center" width="15%">
													逻辑
												</td>
												<td align="center" width="10%">
													值
												</td>
												<td align="center" width="15%">
													逻辑
												</td>
												<td align="center" width="15%">
													字段名称
												</td>
												<td align="center" width="10%">
													逻辑
												</td>
												<td align="center" width="15%">
													值
												</td>
											</tr>
										</table>

									</td>
								</tr>
								<tr>
									<td colspan="2" align="center">
										<input type="button" name="addbutton" value="添加" onClick="AddRow()">
				  						<input type="button" name="delButton" value="删除" onClick="javascript :DelRow(cGetRow);">
									</td>
								</tr>
								<tr bgcolor="#F7FCFF">
									<td height="45" colspan="2" align="center">
										<input type="button" onClick="returnSonValue()" value="提交">
								</tr>
							</table>

						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>


