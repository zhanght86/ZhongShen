<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/view/common/common.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>" target="_self"/>
    <title>审批组列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="css/style.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="js/public.js" charset="utf-8"></script>
	
	<script type="text/javascript" src="dwr/interface/updateSpz.js"></script>
	<script type="text/javascript" src="dwr/engine.js"></script>
	<script type="text/javascript" src="dwr/util.js"></script>
	
	<script type="text/javascript">
		function goNextPage(currentPage) {
			document.getElementById("reqPage").value = currentPage;
			document.forms.namedItem("queryForm").submit();	
		}

		function updateS(){
	    	  var spzid = new Array();
	    	  var index = 0;
	          var leng = document.getElementsByName("spzids").length;
	          
	           for(var i=0;i<leng;i++){
	               if(document.getElementsByName("spzids")[i].checked==true){
	            	   spzid[index] = document.getElementsByName("spzids")[i].value;
	                   index +=1;
	                }
	              }
	           if(spzid.length==0){
	  		          alert("请勾选要修改的审批组");
	  		          return false;
	  		          }else if(spzid.length==1){
	  		        	updateSpz.updateSpz(spzid[0],{
	  						callback:function(array){
	  							if(array=="jdspz"){
	  								alert("该组已被使用,不能进行该操作");
	  								return false;
	  							}else{
	  								
	  								window.self.parent.addTab("修改审批组 ","spz!findSpz.action?spzid="+spzid[0],"icon-add");
	  								return false;
	  							}
	  						}
	  						
	  					});

	  			         return false;
	  			          }else{
	      			          alert("编辑时只能选择一个");
	      			          return false;
	      			          }
	      }
		
		function deleteSpzs(){
		  	  var spzid = new Array();
		  	  var index = 0;
		        var leng = document.getElementsByName("spzids").length;
		        
		         for(var i=0;i<leng;i++){
		             if(document.getElementsByName("spzids")[i].checked==true){
		            	 spzid[index] = document.getElementsByName("spzids")[i].value;
		                 index +=1;
		              }
		            }
		         if(spzid.length==0){
				          alert("请勾选要删除的组");
				          return false;
				          }else{
				        	  var ids = "";
		                     for(var j=0;j<spzid.length;j++){
		                         ids = ids+spzid[j]+",";
		                         }
	                         ids = ids.substring(0,ids.length-1)
		                     updateSpz.updateSpz(ids,callBackA);
							return false;
					          }
		         
		    }

		    function callBackA(array){
		    	if(array=="success"){
		    		if(confirm("确认删除吗?")){
		    			document.forms.namedItem("delSpzs").submit();
		   		  }else{
		   			return false;
		   		   }
		         }else{
		        	 alert("包含已被占用的组");
		         	   return false;
			       }
			       return true;
		    }	

		function toAdd(){
			document.forms.namedItem("toAddSpz").submit();
			}
	</script>
  </head>
<body>
		<form action="spz!toAddSpz.action" method="post" name="toAddSpz">
		</form>
		<form action="spz!findAllSpzByPage.action" method="post"
			name="queryForm">
			<input type="hidden" value="<s:property value="spz.spzname"/>"
				name="spz.spzname" />
			<input type="hidden" value="<s:property value="spz.spzcode"/>"
				name="spz.spzcode" />
			<input type="hidden" name="page.curPage" value="${page.curPage}" id="reqPage" />
		</form>
		<div align="center" style="margin-top:10px;">
  <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="118" align="left"><img src="images/ny_t1.jpg" width="118" height="37" /></td>
      <td align="left" background="images/ny_t2.jpg" class="txt_bold">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="49%" valign="middle" align="right">
          	<c:if test="${fu:check('spz!toAddSpz.action')}">
	          	<img src="images/ny_tj.jpg" width="19" height="19" align="absmiddle" />
				<a style="text-decoration: none" href="javascript:void(0)" title="新增审批组 " onclick="self.parent.addTab('新增审批组 ','spz!toAddSpz.action','icon-add')" class="txt12">新增</a>
	          	&nbsp; &nbsp;
			</c:if>
          	<c:if test="${fu:check('spz!findSpz.action')}">
	          	<img src="images/edit.png" width="19" height="19" align="absmiddle" />
          		<a style="text-decoration: none" href="javascript:void(0)" title="编辑" onclick="return updateS();" class="txt12">编辑</a>
          		&nbsp; &nbsp;
          	</c:if>
          	<c:if test="${fu:check('spz!deleteSpzs.action')}">
	          	<img src="images/ny_sc.jpg" width="19" height="19" align="absmiddle" />
			 	<a href="javascript:void(0)" onclick="return deleteSpzs();" class="txt12">删除</a>
			 </c:if>
          	</td>
        </tr>
      </table>
      </td>
      <td width="6" align="right"><img src="images/ny_t3.jpg" width="6" height="37" /></td>
    </tr>
  </table>
  <table width="99%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td align="left" class="non_topborder">
      <form action="spz!deleteSpzs.action" method="post" name="delSpzs">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC">
        <tr class="title_style">
  		    <td align="center">选择</td>
		    <td align="center">审批组编号</td>
            <td align="center">审批组名称</td>
            <td align="center">操作</td>
          </tr>
       	<c:if test="${!empty page.list}">
	  	<s:iterator id="entity" value="page.list" status="sta">
	  		<s:if test="#sta.even">
				<tr class="row_bg">
			</s:if>
			<s:else>
				<tr>
			</s:else>
			<td align="center"><input type="checkbox" value="<s:property value="#entity.spzid"/>" name="spzids"/></td>
			<td align="center"><s:property value="#entity.spzcode"/></td>
            <td align="center"><s:property value="#entity.spzname"/></td>
	         <td align="center">
	         <c:if test="${fu:check('spz!searchEmplBySpz.action')}">
			 	<a style="text-decoration: none" href="javascript:void(0)" title="查看审批组" 
			 		onclick="self.parent.addTab('查看审批组','spz!searchEmplBySpz.action?spzid=<s:property value="#entity.spzid"/>','icon-add')" class="txt12">
			 		组员管理
			 	</a>
			 </c:if>
			 <c:if test="${!fu:check('spz!searchEmplBySpz.action')}">
			 	<font color="#A1A1A1">组员管理</font>
			 </c:if>
			 </td>
		 </tr>				
	  	</s:iterator>
	  	</c:if>
	  	<c:if test="${empty page.list}">
	  		<tr>
	  			<td colspan="12" align="center"><b>没有找到您所需要的记录!</b></td>
	  		</tr>
	  	</c:if>
        <tr class="row_bg"><td height="29" colspan="9" background="images/ny_titlebg1.jpg">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" >
        <tr class="nonborder">
        <td width="18%" align="right">&nbsp;
			总记录数：${page.totalRecords} 条
			
			<c:choose>
				<c:when test="${ 1 == page.curPage}">
					<a>首页</a>
					<a>上一页</a>
				</c:when>
				<c:otherwise>
					<a href="" onclick="goNextPage(1);return false;" style="text-decoration: none">首页</a>
					<a href="" onclick="goNextPage(${page.curPage - 1 });return false;" style="text-decoration: none">上一页</a>
				</c:otherwise>
			</c:choose>
			<c:forEach begin="${page.beginIndex}" end="${page.endIndex}" var="pageCount" step="1">
				<c:choose>
					<c:when test="${ pageCount == page.curPage}">
						<a href="" onclick="goNextPage(${pageCount });return false;" style="color:red; text-decoration: none" >${pageCount }</a>
					</c:when>
					<c:otherwise>
						<a href="" onclick="goNextPage(${pageCount });return false;" style="text-decoration: none">${pageCount }</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${ page.curPage == page.totalPage}">
					<a>下一页</a>
					<a>末页</a>
				</c:when>
				<c:otherwise>
					<a href="" onclick="goNextPage(${page.curPage + 1});return false;" style="text-decoration: none">下一页</a>
					<a href="" onclick="goNextPage(${page.totalPage });return false;" style="text-decoration: none">末页</a>
				</c:otherwise>
			</c:choose>
			</td>
			</tr>
			</table>
	</td></tr>
      </table>
      </form>
      </td>
    </tr>
  </table>
</div>
</body>
</html>