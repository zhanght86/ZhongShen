<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/view/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>"/>
    <title>设置列表</title>
	<link href="css/style.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="js/json.js"></script>
	<script type="text/javascript">
		var cGetRow=-99999;
		var idx =0;
		var startidx=0;
		var num =0;
		var trow='';
		var jsonObj;
		//0:默认 1:添加    2：修改  3：删除 	
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
			//设置列内容和属性
			newTd0.innerHTML ='<input type=radio id=box'+idx+' name=rowIdx  onclick=\'GetRow(this.id)\'/ value='+idx+' />';
			newTd1.innerHTML= '<input type=text id=rc_key name=\'rc_key\' value=\'\' onkeydown=fnBanBackSpace() onpropertychange="addObj(this.value,this.name,this.id,'+idx+')" style=\'border:solid white 1px;width:100%\'/>';
			newTd2.innerHTML= '<input type=text id=rc_value name=rc_value value=\'\' onkeydown=fnBanBackSpace() onpropertychange="addObj(this.value,this.name,this.id,'+idx+')" style=\'border:solid white 1px;width:100%\' />';
			newTd3.innerHTML= '<input type=text id=rc_order name=rc_order value=\'\' onkeydown=fnBanBackSpace() onpropertychange="addObj(this.value,this.name,this.id,'+idx+')" style=\'border:solid white 1px;width:100%\' />';
			newTd4.innerHTML = '<input type=text id=rc_desc name=rc_desc value=\'\' onkeydown=fnBanBackSpace() onpropertychange="addObj(this.value,this.name,this.id,'+idx+')"  style=\'border:solid white 1px;width:100%\' />';
			jsonObj.push({"rc_Id":"","rc_desc":"","rc_parent":'${rcCode.rc_no}',"rc_key":"","rc_name":"","rc_order":"","rc_value":"","classType":"0"});
			var last=JSON.stringify(jsonObj); //将JSON对象转化为JSON字符
			//alert(last);
			idx++;
		}
		
		
		function init(key,val,order,desc){
			var tab1 =document.getElementById("tab1");
			
			//添加一行
			var newTr = tab1.insertRow();
			//添加列
			var newTd0 = newTr.insertCell();
			var newTd1 = newTr.insertCell();
			var newTd2 = newTr.insertCell();
			var newTd3 = newTr.insertCell();
			var newTd4 = newTr.insertCell();
			//设置列内容和属性
			newTd0.innerHTML= '<input type=radio id=box'+idx+' name=rowIdx  onclick=\'GetRow(this.id)\'/ value='+idx+' />';
			newTd1.innerHTML= '<input type=text id=rc_key name=\'rc_key\' onkeydown=fnBanBackSpace() onpropertychange="updateObj(this.value,this.name,this.id,'+idx+')" style=\'border:solid white 1px;width:100%\' value='+key+'  />';
			newTd2.innerHTML= '<input type=text id=rc_value name=rc_value onkeydown=fnBanBackSpace() onpropertychange="updateObj(this.value,this.name,this.id,'+idx+')" style=\'border:solid white 1px;width:100%\' value="'+val+ '"/>';
			newTd3.innerHTML= '<input type=text id=rc_order name=rc_order onkeydown=fnBanBackSpace() onpropertychange="updateObj(this.value,this.name,this.id,'+idx+')" style=\'border:solid white 1px;width:100%\' value='+order+' />';
			newTd4.innerHTML= '<input type=text id=rc_desc name=rc_desc onkeydown=fnBanBackSpace() onpropertychange="updateObj(this.value,this.name,this.id,'+idx+')"  style=\'border:solid white 1px;width:100%\' value="'+desc+'" />';
			idx++;
			
		}
		
		//修改
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
					jsonObj[i].classType=3;
					//delete jsonObj[i];
				}
			}
			
		}

		//为Json对象赋值
		function setVal(val,name,id,rows,classTypeNum){
			//alert(val+name+id+rows+classTypeNum);
	 		var last=JSON.stringify(jsonObj); //将JSON对象转化为JSON字符
	 		for(var i =0;i<jsonObj.length;i++){
				if(i==rows){
					var obj=jsonObj[i];
					
					if(obj==null) continue;
					obj.classType=classTypeNum;
					if(name=="rc_key"){
						obj.rc_key=val;
						continue;
					}
					
					if(name=="rc_value"){
						obj.rc_value=val;
						continue;
					}
	
					if(name=="rc_order"){
						obj.rc_order=val;
						continue;
					}
					
					if(name=="rc_desc"){
						obj.rc_desc=val;
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
				if(obj.rc_key==null){
					alert("第"+(r+1)+"行的键不能为空");
					return;
				}
				
				if(obj.rc_value==null){
					alert("第"+(r+1)+"行的值不能为空");
					return;
				}
				
				if(obj.rc_order==null){
					alert("第"+(r+1)+"行的排序不能为空");
					return;
				}
				r++;
				rJson+=JSON.stringify(obj)+",";
				
		}
		
		if(rJson.length!=0)
			rJson=rJson.substring(0,rJson.length-1);
		rJson='['+rJson+']';
		document.forms[0].elements["rcCode.codeTemp"].value=rJson;
		$("#addForm").submit();
	
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
					deleteObj(iIndex);
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
	
		function ShowRow(){
			alert(cGetRow);
		}
	function createJsonJob(){
		var jsonVal=document.forms[0].elements["rcCode.codeTemp"].value;
		
		if(jsonVal==null)  jsonVal="[]";
		if(jsonVal=="")  jsonVal="[]";
		jsonObj=eval('('+jsonVal+')');
	}
		
	
	function fnBanBackSpace() {
 	  if(event.keyCode ==188){
	  	event.returnValue=false;
   	  }
	}
	</script>
  </head>
<body onload="createJsonJob()">

<div align="center">
<form name ="addForm" id="addForm" action ="rcCode!doUpdateSonInfo.action" method="post">
		<input type="hidden" value="${rcCode.rc_Id }" name="rcCode.rc_Id"/>
		<input type="hidden" name="rcCode.codeTemp" value='${rcCode.codeTemp }' />
  <table width="99%" border="0" cellspacing="0" cellpadding="0" style="border:1px solid #A6C4DC">
    <tr class="title_style">
      <td colspan="8" style="text-align:left;border:1px solid #A6C4DC;"><img src="images/group1.png" width="20" height="20" align="absmiddle" />数据字典设置</td>
    </tr>
    <tr class="row_bg">
      <td colspan="8"  align="center">
	  
	  <table width="70%" border="0" cellspacing="0" cellpadding="0" class="border_empty_fontsize">
		<tr>
			<td colspan="4" align="center">
				<table id="tab1" width="100%" border="0" cellspacing="0" cellpadding="0"  style="margin-top:10px; margin-bottom:10px" class="border_A6C4DC">
   			 <tr class="title_style">
			  <td width="5%">&nbsp;</td>
    		  <td width="30%">键</td>
			  <td width="30%">值</td>
			  <td width="10%">序列</td>
			  <td width="25%">描述</td>
   			 </tr>
  			
  			</table>
			</td>
		</tr>
		
		<s:iterator var ="entry" value="rcCodeSonList">
		  <script language="javascript">
		  		init('${entry.rc_key }','${entry.rc_value }','${entry.rc_order }','${entry.rc_desc }');
		  	</script>
		</s:iterator>
					
						
        <c:if test="${rcCode.rc_type == 0}">
			<tr>
	          <td width="100%" colspan="4" align="right" >
	          		 <input type="button" name="addbutton" value="添加" onclick="AddRow()" />
					 <input type="button" name="delButton" value="删除" onclick="javascript :DelRow(cGetRow);"/>
			  </td>
			</tr>
		</c:if>
		
		 <c:if test="${rcCode.rc_type == 1}">
		 	<tr>
	          <td width="100%" colspan="4" align="left" > 
				数据库查询映射的实体对象：<br/><input type="text" name="rcCode.rc_querySql" value="${rcCode.rc_querySql }" class="input_width"/>      		 
			  </td>
			</tr>
			<tr>
	          <td width="100%" colspan="4" align="left" >
	          	数据库查询的sql语句:<br/>
				<textarea rows="5" cols="77" name="rcCode.rc_querySql">${rcCode.rc_querySql }</textarea>          		 
			  </td>
			</tr>
		</c:if>
			
		<tr>
          <td colspan="6" align="center" ><input type="button" onclick="goSubmit()" value=" 提  交 " /> 
          </td>
        </tr>
      </table></td>
    </tr>
  </table>
  
  </form>
</div>
</body>
</html>
