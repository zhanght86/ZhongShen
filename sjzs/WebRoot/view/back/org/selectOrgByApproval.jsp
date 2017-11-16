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
		<title>选择部门</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" type="text/css" href='js/tree/dtree/dtree.css' />
		<link rel="stylesheet" type="text/css" href='css/style.css' />
		<script language="javascript" src='js/tree/dtree/dtree.js'></script>
		<script language="javascript" src='js/showdialog.js'></script>
		<style type="text/css">
			body{
				font-size:12px;
				font-family:Arial;
				background:url(../images/main_rbg.jpg) no-repeat;
				padding-top:10px;
				background-color: #FFFFFF;
				}
		</style>
		
		<script type="text/javascript">
			
		</script>
	</head>
	<body styl="text-algin:left">
	<table width="99%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC">
	    <tr class="title_style">
	      <td style="text-align:left" width="100%">
	      	<img src="images/group1.png"  height="20" align="absmiddle" />根据部门和合同类别选择适用的合同
	      </td>
	    </tr>
	    
	  <!-- <c:if test="${empty page.list}">
	    	<tr>
		    	<td align="center">
		    		暂时没有数据。
		    	</td>
		    </tr>
	    </c:if>
	    <c:if test="${!empty page.list}">
	    	<c:forEach items="${page.list}" var = "org" varStatus="state">
	    		<tr>
			    	<td>
			    		<input type="checkBox" name="org" value="${org.jmcode }"/>
			    		${org.jmname }
			    	</td>
			    </tr>
	    	</c:forEach>	    	
	    </c:if>
	    -->
   
    </table>
	 
			<script type="text/javascript">
			 a = new dTree('a');
			 a.config.folderLinks=true;
			 a.add("00000000","-1","组织机构","javascript:javascript:getDataC('00000000','组织机构');","","");
			<c:forEach items="${page.list}" var="entry">
		   	  	a.add("${entry.jmcode}","${entry.jmssjg}","${entry.jmname}","javascript:showHtlb('${entry.jmcode},${entry.jmname}');","","");
		    </c:forEach>
		    document.write(a);
			</script>	
			 <!-- 
			<script type="text/javascript">
			
			
			 a = new dTree('a');
			 a.config.folderLinks=true;
			 a.add("00000000","-1","组织机构","javascript:javascript:void(0);","","");
			<c:forEach items="${page.list}" var="entry" varStatus="i">


			<c:if test="${!empty listhtlb}">
				var hblb="";				
				<c:forEach items="${listhtlb}" var = "htlb">
					hblb+="<input type=\"checkbox\" name=\"${entry.jmcode}\" value=\"${htlb.code}\" disabled=\"disabled\" title=\"${htlb.name}\">${htlb.name}&nbsp;&nbsp;"						
				</c:forEach>						
			</c:if>
		   	  	a.add("${entry.jmcode}","${entry.jmssjg}","<input type=\"checkbox\" name=\"${entry.jmcode}\" id=\"${entry.jmcode}\" value=\"${entry.jmcode}\" title=\"${entry.jmname}\" onclick=\"javascript:orgCheck('${entry.jmcode}');\">&nbsp;&nbsp;${entry.jmname}&nbsp;&nbsp;"+hblb+"&nbsp;<input type=\"button\" id=\"btn${i.index+1}\" value=\"确定\" onclick=\"javascript:sureChose('${entry.jmcode}','btn${i.index+1}');\"/>","javascript:void(0);","","");
		    </c:forEach>
		    document.write(a);
			</script>	
			 -->	
			<table>
				<tr>
					<td valign="top">
						适用合同：
					</td>
					<td>
						<textarea rows="5" cols="80" id="returnName" readonly="readonly"></textarea>
						<input type=hidden  value="" id="returnValue" size="100"/>
					</td>
				</tr>
			</table>	
			<center>
				<input type="button" value="保存" onclick="javascript:setValue();"/>				
			</center>								
				
	</body>	
	
</html>
