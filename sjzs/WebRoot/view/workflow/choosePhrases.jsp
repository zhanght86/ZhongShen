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
    <title>常用语选择</title>
	<link rel="stylesheet" href="<%=basePath%>css/button.css" type="text/css"></link>
	<link rel="stylesheet" href="<%=basePath%>css/style.css" type="text/css"></link>

<script language="javascript">

 function choosePhrases(val){
 	var phrasesVal =val ;
 	window.returnValue =val;
 	window.close();
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
					  <!-- <input type="button" name="Submit"style="border:0px;padding:0px;margin:0px"> -->
					  &nbsp;
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
          <td width="10%">选择</td>
          <td>常用语</td>
          </tr>   
           <s:iterator var ="entry" value="list" status="idx">
            <tr>
         	 <td width="3%"><input type=radio id=bo${idx.index } name=rowIdx onclick="choosePhrases(this.value)"  value="${entry.phrases_info}" /></td>
         	 <td><input type="text" id="phrases_info${idx.index }" value="${entry.phrases_info}" style="border:solid 1px white;width:100%"/><input type="hidden" name="emplId" value="${employee.emplId}"/></td>
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