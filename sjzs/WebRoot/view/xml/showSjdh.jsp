<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hnzskj.common.xml.ShowXml"%>
<%@page import="com.hnzskj.common.xml.XmlDTO"%>
<%@page import="com.hnzskj.common.xml.ModeDTO"%>
<%@page import="com.hnzskj.common.xml.PathMap"%>
<%@ include file="/view/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%! int v=1; %>
<%
	ShowXml show = (ShowXml)(request.getSession().getAttribute("showxml"));
	XmlDTO xmlDTO = show.getXmlDTO();
	String beginId = xmlDTO.beginModeDTO(true).getId()+"";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.0 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>查看审计导航</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>view/xml/showSjdh.css">
	<script type="text/javascript" src="js/dwz/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>view/xml/showSjdh.js"></script>
	<style type="text/css">
		.sjdh_whole table td{
			width:200px;
		}
	</style>
  </head>
  
  <body onload="inits('<%=beginId %>')">
    <div class="sjdh_main">
    	<div class="sjdh_show">
    		<div class="show_left">    		
   				<div id="left_cont" class="left_cont" style="background-color:gray ;">
   					<input type="hidden" id="left_modeId" value="" />
   					<b id="left_cont_title" ></b>
   				</div>
    		</div>
    		<div class="show_center">    		
    			<div class="center_cont" style="background-color: white;">    			
    					<table style="position:absolute;border: none;width:400px;height:70px;top:20px;left:50px;" cellpadding="0" cellspacing="0">
	   						<tr><td align="left" style="width:100px;font-weight: bold;">节点标题：</td> <td style="width:300px;" align="left" id="cent_title"> ${showXml.message[0] }</td></tr>
	   						<tr><td align="left" style="font-weight: bold;">节点说明：</td> <td align="left" id="cent_cont"> ${showXml.message[1] }</td></tr>
		   				</table>    					
    					<table style="position: absolute; width:400px;height:70px;top:150px;left:50px;">
    					<s:iterator value="showXml.center"> 
    					<input type="hidden" name="modeId" id="cent_modeId" value="${key }">
    					<tr>
    						<td style="width:150px;text-align: left;font-weight: bold;">引入的审计方法：</td><td style="width:250px;text-align:left;" id="cent_ff"><a href="${value.fangFaUrl }">${value.fangFaName}</a></td>
    					</tr>
    					<tr>
    						<td style="font-weight: bold;">节点的附件：</td><td id="cent_att"><a href="${value.attachUrl }">${value.attachName }</a></td>
    					</tr>
    					<%-- 
					   		ModeId --> ${key}<br>
					   		Id ------> ${value.id }<br> 
							sjdhId --> ${value.sjdhId }<br>
							xmlId ---> ${value.xmlId }<br>
							modeId --> ${value.modeId }<br>
							attachId-> ${value.attachId }<br>
							fangFaId-> ${value.fangFaId }<br>
							deleteflag-> ${value.deleteflag }<br>
							updatedate-> ${value.updatedate }<br>
							fangFaUrl--> ${value.fangFaUrl }<br>
							attachUrl--> ${value.attachUrl }<br>
							fangFaName-> ${value.fangFaName }<br>
							attachName-> ${value.attachName }<br> 
						--%>
						</s:iterator>  
    					</table>
    			</div>
    		</div>
    		<div class="show_right">
    			
	    			<c:choose>
	    				<c:when test="${empty showXml.right}">
	    					<div id="right_cont" class="right_cont" >
	    					结束了
	    				</c:when>
		    			<c:otherwise>
		    			<div id="right_cont" class="right_cont" onclick="clickAjax(this,'right')" >
		    				<c:forEach var="lef" items="${showXml.right}" >
		   						<input type="hidden" id="right_modeId" value="${lef[0] }"/>
		   						${lef[1] }
		   					</c:forEach > 
		    			</c:otherwise>
	    			</c:choose>    				
	   			</div>
    		</div>
    	</div>    	
    </div>
    <div class="sjdh_whole" style="position: absolute; top:500px;height:500px;width:100%; z-index:20;font-weight: bold;font-size: 12px;">
   		<%--   			
   			localOut = out;
   			outMess(xmlDTO,beginId);
   		--%>
   		<iframe src="view/xml/tree.jsp" width="100%"height="500px" scrolling="yes">
   			
   		</iframe>		
   	</div>
    <div style="position: absolute; z-index: 200;width:100%;">
	 <%--!JspWriter localOut;
	 	public void outMess(XmlDTO xmlDTO,String modeId ) throws java.io.IOException
		{  	
	 		
	 		ModeDTO mode =xmlDTO.getModeById(modeId);
	 		localOut.print("<table align='center'  cellpadding='0' cellspacing='0' width='100%'><tr>");
	 		List<String> values = xmlDTO.getPathMap().getValueByKey(modeId);
	 		
 			if(values ==null || values.size() == 0){
 				localOut.print("<td border='1' id='td"+(v++)+"' style='width:100px' class='mode"+mode.getId()+"' border='0'> <div title='"+mode.getTitle()+"'><p>");
 				localOut.print(mode.getTitle());
	 			localOut.print("</p></div></td>");
 			}else{
 				localOut.print("<td id='td"+(v++)+"'style='width:100px' class='mode"+mode.getId()+"' border='0'rowspan='"+values.size()+"'> <div title='"+mode.getTitle()+"'><p>");
 				localOut.print(mode.getTitle());
 				localOut.print("</p></div> </td>");
 				localOut.print("<td><table cellpadding='0' cellspacing='0' border='1'>");
 				for(String s:values){
 					localOut.print("<tr><td>");
 					outMess(xmlDTO,s);
 					localOut.print("</td></tr>");
 				}
 				localOut.print("</table></td>");
 			}
	 		localOut.print("</tr></table>");
		}   
	 --%>  
    <s:debug></s:debug>
    </div>
  </body>
</html>
     

