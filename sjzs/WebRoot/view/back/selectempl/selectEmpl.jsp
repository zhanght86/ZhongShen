<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>"/>
    <title>员工列表</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script language="javascript" src="js/public.js" charset="utf-8"></script>
	<script language="javascript" src='js/showdialog.js'></script>
	<script type="text/javascript">
		function goNextPage( currentPage ) {
			document.getElementById("reqPage").value = currentPage;
			document.forms.namedItem("queryForm").submit();	
		}
		
    	//此方法可解决在IE5，6，7下动态创建input表单时，无法给name属性赋值的情况
		function createElement(mywindow,type, name) {      
			var element = null;				  
			try {      
				//先是在使用IE的情况下创建元素，如果失败则使用标准方法   
				element = mywindow.document.createElement('<'+type+' name="'+name+'">');      
			} catch (e) {      
				//在使用非IE的情况下会出现异常       
			}      
			if (!element) {//如果没有成功创建元素      
				element = mywindow.document.createElement(type);      
				element.name = name;      
			}
			return element;
		}

		//在用户选择列表中增加一行单元格，包含选择的用户的uid,姓名，以及删除当前行的按钮
		function addEmpl(emplId, emplName) {
			if (isExist(emplId)) {//如果当前用户已经被选择
				alert("当前用户已存在于用户选择列表中");
				return;
			}
			
			var mxwin = window.parent.emplW;

			var tb_obj = mxwin.document.getElementById("empllist");
			
			var tr_obj = mxwin.document.createElement("tr");
			var td_obj = mxwin.document.createElement("td");
			var td_obj1 = mxwin.document.createElement("td");
			var hiid_obj = createElement(mxwin,"input","empids");
			var hiname_obj = createElement(mxwin,"input","empnames");
			var btn_obj = createElement(mxwin,"input","remove");
			hiid_obj.type = "hidden";
			hiid_obj.value = emplId;

			hiname_obj.type = "text";
			hiname_obj.value = emplName;
			hiname_obj.style.border = "0px";
			hiname_obj.style.backgroundColor = "#FFFFFF";
			hiname_obj.readOnly = "true";
			hiname_obj.size = "4";
			
			btn_obj.id="button-submis";
			btn_obj.type = "button";
			btn_obj.value = "删除";
			btn_obj.onclick = function () {
				tb_obj.removeChild(tr_obj);
			}

			td_obj.appendChild(hiid_obj);
			td_obj.appendChild(hiname_obj);
			td_obj1.appendChild(btn_obj);
			
			tr_obj.appendChild(td_obj);
			tr_obj.appendChild(td_obj1);
				
			tb_obj.appendChild(tr_obj);
		}

		//检测用户列表中是否已存在当前用户
		function isExist(emplId) {
			var mxwin = window.parent.emplW;

			var t_obj = mxwin.document.getElementsByName("empids");
			for ( var i = 0; i < t_obj.length; i++ ) {
				if (emplId == t_obj[i].value) {
					return true;
				}
			}
			return false;
		}
	</script>
  </head>
  <body bgcolor="#FFFFFF">
  <form name="queryForm" action="system/empl!selectEmpl.action" method="post">
  	<input type="hidden" value="${page.curPage }" id="reqPage" name="page.curPage"/>
  	<%--<input type="hidden" value="${empl.ussjg}" name="empl.ussjg"/>--%>
  </form>
	<div align="center" style="margin-top:10px;">
	  <table width="99%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC">
	    <tr class="title_style">
	      <td colspan="8" style="text-align:left">
	      	<img src="images/group1.png" width="20" height="20" align="absmiddle" /> 请点击需要选择的用户的姓名
	      </td>
	    </tr>
	    <tr>
	      <td align="left" class="non_topborder">
	      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC">
		    <tr class="title_style">
	  			<td>序号</td>
	  			<td>员工姓名</td>
	  			<td>职位</td>
	  			<td>手机号</td>
	        </tr>
		  	<c:if test="${!empty page.list}">
			  	<s:iterator id="entry" value="page.list" status="sta">
				  		<s:if test="#sta.even">
							<tr class="row_bg">
						</s:if>
						<s:else>
							<tr>
						</s:else>
				  			<td align="center">${sta.count+(page.curPage-1)*page.maxResult}</td>
				  			<td align="center"><a href="javascript:addEmpl('${entry.uid}','${entry.uname}')"><s:property value="#entry.uname"/></a></td>
		  					<td align="center"><s:property value="#entry.uposition"/></td>
		  					<td align="center"><s:property value="#entry.ucellphone"/></td>			
				  		</tr>
			  	</s:iterator>
				<tr class="row_bg">
		          <td height="29" colspan="9" background="images/ny_titlebg1.jpg">
		          <table width="100%" border="0" cellspacing="0" cellpadding="0" >
		            <tr class="nonborder">
		               <td align="right">
		              		<a style="color: #1E5494;">总记录数：${page.totalRecords} 条</a>&nbsp;&nbsp;
							<c:choose>
								<c:when test="${ 1 == page.curPage}">
									<a>首页</a>
									<a>上一页</a>
								</c:when>
								<c:otherwise>
									<a href="" onclick="goNextPage(1);return false;">首页</a>
									<a href="" onclick="goNextPage(${page.curPage - 1 });return false;">上一页</a>
								</c:otherwise>
							</c:choose>
							<c:forEach begin="${page.beginIndex}" end="${page.endIndex}" var="pageCount" step="1">
								<c:choose>
									<c:when test="${ pageCount == page.curPage}">
										<a href="" onclick="goNextPage(${pageCount });return false;" style="color:red;">${pageCount }</a>
									</c:when>
									<c:otherwise>
										<a href="" onclick="goNextPage(${pageCount });return false;">${pageCount }</a>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:choose>
								<c:when test="${ page.curPage == page.totalPage}">
									<a>下一页</a>
									<a>末页</a>
								</c:when>
								<c:otherwise>
									<a href="" onclick="goNextPage(${page.curPage + 1});return false;">下一页</a>
									<a href="" onclick="goNextPage(${page.totalPage });return false;">末页</a>
								</c:otherwise>
							</c:choose>
		              </td>
		              <td width="1%"></td>
		            </tr>
		          </table>
		          </td>
		          </tr>
		  	</c:if>
	      </table>
	      </td>
	    </tr>
	  </table>
	</div>
</body>
</html>
