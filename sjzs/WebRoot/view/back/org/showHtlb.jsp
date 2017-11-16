<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/view/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	    <base href="<%=basePath%>" target="_self"/>
		<title>选择合同类别</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" type="text/css" href='js/tree/dtree/dtree.css' />
		<link rel="stylesheet" type="text/css" href='css/style.css' />
		<script language="javascript" src='js/tree/dtree/dtree.js'></script>
		<script language="javascript" src='js/showdialog.js'></script>
		<script LANGUAGE=javascript>
			//选择类别
			function setValue(){
				var orgs=window.dialogArguments.split(",");
				var orgcode=orgs[0];
				var orgName=orgs[1];
				var returnCode=orgcode+",";
				var returnValue=orgName+",";
				var codes=document.getElementsByName("code");
				for(var i =0;i<codes.length;i++){
					if(codes[i].checked==true){
						returnCode+=codes[i].value+",";
						returnValue+=codes[i].title+",";
					}
				}
				returnCode=returnCode.substring(0,returnCode.lastIndexOf(","));
				returnValue=returnValue.substring(0,returnValue.lastIndexOf(","));
				var returnParam=new Array(returnCode,returnValue);
				if(returnCode.indexOf(",")>-1){
					window.returnValue=returnParam;
				}else{
					window.returnValue=null;
				}
				window.close();
			}
		</script>
	</head>
	<body bgcolor="#FFFFFF">
	<table width="99%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC">
	    <tr class="title_style">
	      <td colspan="2"  style="text-align:left">
	      	<img src="images/group1.png" width="20" height="20" align="absmiddle" />选择合同类别
	      </td>
	    </tr>
	    <tr>
	    	<td align="center" width="30px;">
	    		选择
	    	</td>
	    	<td>
	    		合同类别名称
	    	</td>
	    </tr>
	    <c:if test="${!empty listhtlb}">
	    	<c:forEach items="${listhtlb}" var="ht" varStatus="i">
	    		<tr>
			    	<td align="center">
			    		<input type="checkbox" value="${ht.code }" id="code${i.index+1 }" name="code" title="${ht.name }"/>
			    	</td>
			    	<td>
			    		${ht.name }
			    	</td>
			    </tr>
	    	</c:forEach>
	    </c:if>
	    <tr>
	    	<td colspan="2" align="center">
	    		<input type="button" value="确定" onclick="javascript:setValue();"/>
	    	</td>
	    </tr>
    </table>
		
	</body>
</html>
