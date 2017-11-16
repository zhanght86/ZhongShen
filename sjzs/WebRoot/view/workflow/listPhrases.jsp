<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hnzskj.persist.bean.system.Employee"%>
<%@page import="com.hnzskj.common.Constant"%>
<%@ include file="/view/common/common.jsp"  %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>常用语定制</title>
	<link rel="stylesheet" href="css/button.css" type="text/css"></link>
	<link rel="stylesheet" href="<%=basePath%>css/style.css" type="text/css"></link>
	<link rel="stylesheet" href="css/validate.css" type="text/css"></link>
	<script type="text/javascript" src="fckeditor/fckeditor.js"></script>
	<script type="text/javascript" src="js/showdialog.js"></script>
	<script type="text/javascript" src="js/validate/jquery.js"></script>
	<script type="text/javascript" src="js/validate/jquery.validate.js"></script>
	<script type="text/javascript" src="js/validate/messages_cn.js"></script>
	<script language="javascript" src="js/calendar/calendar.js" charset="UTF-8"></script>
<script language="javascript">
var cGetRow=-99999;
var idx =0;
var startidx=0;;
var falg =true;
function AddRow()
{
var tab1 =document.getElementById("tab1");
//添加一行
var newTr = tab1.insertRow();
//添加两列
var newTd0 = newTr.insertCell();
var newTd1 = newTr.insertCell();

//设置列内容和属性
newTd0.innerHTML ='<input type=radio id=box'+idx+' name=rowIdx  onclick=\'GetRow(this.id)\'/ value=box'+tab1.rows.length+' />';
newTd1.innerHTML= '<input type=text id=phrases_info name=\'phrases_info\' value=\'\' style=\'border:solid white 1px;width:100%\'/>'+
										'<input type=hidden id=emplId name=\'emplId\' value=\'${employee.emplId}\' style=\'border:solid white 1px\'/>';
idx++;
}

// 用于获取列表的数值
function goSubmit(){
	var v ='';
	var tab1 =document.getElementById("tab1");
	var phrases= document.getElementsByName("phrases_info");
	var emplId= document.getElementsByName("emplId");
	var v='';
	for(i =0;i<phrases.length;i++){
		if(phrases[i].value=="") {
			alert("常用语不可以为空!");
			return ;
		}
		v += phrases[i].value+","+emplId[i].value+",_";
	}
	document.forms[0].elements["phrases.temp"].value=v;
	document.forms[0].submit();
}

function DelRow(iIndex)
{
	//删除一行
	if(iIndex ==-99999){
		alert("选中删除行");
		return ;
	} 
	tab1.deleteRow(iIndex);
	if(tab1.rows.length==1) falg =true;
	cGetRow=-99999;
}

function GetRow(id)
{
//获得行索引
//两个parentElement分别是TD和TR哟，rowIndex是TR的属性
//this.parentElement.parentElement.rowIndex
var objs = document.getElementById(id);
if(objs.checked ==true){
	cGetRow=window.event.srcElement.parentElement.parentElement.rowIndex;
}else{
	alert("请选择一行！");
	cGetRow=-99999;
}
}


function ShowRow()
{
alert(cGetRow);
}
	
</script>
</head>
<body>
<div align="center" style="margin-top:10px;">
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
	    <tr>
	      <td width="118" align="left"><img src="images/ny_t1.jpg" width="118" height="37" /></td>
	      <td align="left" background="images/ny_t2.jpg" class="txt_bold">
          	 
			  <table width="99%" border="0" cellspacing="0" cellpadding="0" >
				 <tr>
				  <td align="right">
					  <input type="button" name="Submit" value="添加" onClick="AddRow()">
					  <input type="button" name="Submit" value="撤销" onClick="javascript :DelRow(cGetRow);">
					  <input type="button" name="Submit" value="保存" onClick="javascript :goSubmit();">
				 </td>
			 	</tr>	
				</table>
				  
		  </td>
	      <td width="6" align="right"><img src="images/ny_t3.jpg" width="6" height="37" /></td>
	    </tr>
	  </table>

  <table width="99%" border="0"  cellspacing="0" cellpadding="0">
    <tr>
      <td align="left" class="non_topborder">
      <form action="phrasesAction!doAddPage.action" method="post">
      	<table width="100%" id="tab1" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC">
        <tr class="title_style">
          <td width="3%">选择</td>
          <td>常用语</td>
          </tr>   
           <s:iterator var ="entry" value="list" status="idx">
            <tr>
         	 <td width="3%"><input type=radio id=bo${idx.index } name=rowIdx  onclick='GetRow(this.id)' value=box /></td>
         	 <td><input type="text" name="phrases_info" value="${entry.phrases_info}" style="border:solid 1px white;width:100%"/><input type="hidden" name="emplId" value="${employee.emplId}"/></td>
          </tr>    
           </s:iterator>
      </table>
      <input type="hidden" name="phrases.temp"/>
      </form>
     </td>
    </tr>
  </table>
</div>
</body>
</html>