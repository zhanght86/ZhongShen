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
		<title>审计助手</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" href="css/right.css" type="text/css"></link>
		<script language="javascript" src="js/calendar/calendar.js" charset="UTF-8"></script>
		<script language="javascript" src='js/showdialog.js'></script>
		<script type="text/javascript">
		<!--
		window.onload=function(){
			//window.moveTo(screen.width/2,screen.height/2);
			window.resizeTo(600,600);
		 var locator = new ActiveXObject ("WbemScripting.SWbemLocator");
		 var computer = locator.ConnectServer(".");
		 var properties = computer.ExecQuery("SELECT * FROM Win32_USBHub");
		 var e = new Enumerator (properties);
		 var arrayUsbSN=new Array();//存放序列号
		 var intCount=0;
		 
		 for (;!e.atEnd();e.moveNext ())
		{
			 var p = e.item ();
			 var strSN;
			 var uSerialNum;
			var sn=p.DeviceID
		if(sn.indexOf("VID")>0)
		 {
		 strSN=GetUsbSN(sn);
		 uSerialNum=strSN.substring(8);
		 arrayUsbSN[intCount]=strSN;
		 intCount=intCount+1;
		 document.getElementById("userKey").value=uSerialNum;
		// alert("你的U盘系列号为："+ uSerialNum);
		 }   
		}
		
		}


		function GetUsbSN(DIVPIVSN) 
	{ 
	var UsbSN; 
	var strSN=DIVPIVSN ; 
	var arraySN=new Array(); 
	if(strSN.indexOf("&")>0) 
	{ 
	arraySN=strSN.split("\\"); 
	if(arraySN.length>2) 
	{ 
	UsbSN=GetDIVandPIV(arraySN[1])+arraySN[2]; 
	} 
	else 
	{ 
	UsbSN=""; 
	} 
	} 
	else 
	{ 
	UsbSN=""; 
	} 
	return UsbSN; 
	} 




	function GetDIVandPIV(DIVPIV) 
	{ 
	var strDIVandPIV; 
	var strDIVPIV=DIVPIV; 
	var arrayDIVPIV=new Array(); 
	if(strDIVPIV.indexOf("&")>0) 
	{ 
	arrayDIVPIV=strDIVPIV.split("&"); 
	strDIVandPIV=GetDIVORPIV(arrayDIVPIV[0])+GetDIVORPIV(arrayDIVPIV[1]); 
	} 
	else 
	{ 
	strDIVandPIV=""; 
	} 
	return strDIVandPIV; 
	} 


	function GetDIVORPIV(DIV) 
	{ 
	var str; 
	var arrayDIV=new Array(); 
	if(DIV.indexOf("_")>0) 
	{ 
	arrayDIV=DIV.split("_"); 
	str=arrayDIV[1]; 
	} 
	else 
	{ 
	str=""; 
	} 
	return str; 
	}

	  //-->
        </script>
  </head>
  
  <body>
		<form method="post" action="userkey!saveUserKey.action" id="addForm" name="fm">
		 <input type="hidden" name="userKey" id="userKey" value=""/>
			 <input type="hidden" name="userKeyId" value="${userKeyId }"/> 
		  <table class="table" id="list" style="font-size: 12px;margin-top: 10px"   height="100%" width="100%" align="center">
		   <tr class="contentTd"><th colspan="2">U盘绑定</th></tr>
			<tr class="contentTd">
				<td  align="right"><b>U盘制作日期</b></td>
				<td  ><label>
				  <input id="date1" maxLength="50" name="productionDate" readonly="readonly" value="" style="width:160px;"/>
				</label><img style="CURSOR: hand" onClick="setday(this,document.all.date1)"
						src="js/calendar/form/calendar.gif" align="middle" border="0" width="30" height="30"/> </td>
			</tr>
			<tr class="contentTd">
				<td  align="right" ><b>U盘出售日期</b></td>
				<td  ><label>
				  <input id="date" maxLength="50" name="saleDate" readonly="readonly" value="" style="width:160px;"/>
				</label><img style="CURSOR: hand" onClick="setday(this,document.all.date)"
						src="js/calendar/form/calendar.gif" align="middle" border="0" width="30" height="30"/></td>
				
				
			</tr>
			<tfoot>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value=" " id="savebtn" class="savebtn" onclick="save();">
				</td>
				</tr>
				<tr>
				<td colspan="2"><font color="red"> 注意：U盘的出售日期即为U盘的使用开始日期，如果出售日期为空，则U盘中的所有功能都不可用！</font></td>
			</tr>
			</tfoot>
		</table>
	</form>
  </body>
</html>
