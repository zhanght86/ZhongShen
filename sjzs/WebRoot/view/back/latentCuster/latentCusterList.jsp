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
    <title>潜在客户级别信息维护</title>
	<script language="javascript" src="js/public.js" charset="utf-8"></script>
	<link href="css/style.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="js/jquery-1.4.4.min.js"></script>	
	<script type="text/javascript">
		//分页查询
		function goNextPage( currentPage ) {
			document.getElementById("reqPage").value = currentPage;
			document.forms.namedItem("queryForm").submit();	
		}

		//回调函数当删除成功后执行此操作
		function delCallBack() {
			document.forms.namedItem("queryForm").submit();
		}
		
		//切换HTML标签的显示隐藏状态
		function changeDisplay(objId) {
			$("#" + objId).toggle();
		}
		//checkboxname待选择复选框的name
		//counts要选择的复选框的数目，1表示只选择一个，-1表示只选择一个
		//如果counts为1时，选择了多个返回false,counts为-1时，没有选择返回false
		function getCheckBox(checkboxname, counts) {
			var selectCount = 0;
			var selectValue = "";
			//选取input中type为checkbox,name为checkboxname的表单
			var myselector = "input[type='checkbox'][name='"+checkboxname+"']";
			var checkboxs = $(myselector).each(function(){
					if($(this).attr("checked")==true){
						selectCount = selectCount + 1;
						selectValue = $(this).attr("value");
					}					
				});
			if (1 == counts) {
				if (selectCount == counts) {
					return selectValue;
				}
				return "";
			} else if (-1 == counts) {
				if (selectCount >= 1) {
					return true;
				}
				return false;
			}
			return false;						
		} 
		
		//点击更新按钮执行的操作
		function doUpdate(lcId) {
			var selectVal = getCheckBox(lcId,1);
			if ("" != selectVal) {
				var sp = selectVal.indexOf(',');
				if(sp>-1){
					alert("有且只有一个复选框必须被选中");
					return;
				}
				var goURI = "system/latentCuster!goUpdatePage.action?latentCuster.lcId="+selectVal;
				self.parent.addTab('详细信息',goURI,'icon-add')
			} else {
				alert("有且只有一个复选框必须被选中");
			}
		}
		
		//点击删除按钮执行的操作
		function doDelete(lcId){
			var selectVal = getCheckBox(lcId,1);
			if ("" != selectVal) {
				var sp = selectVal.indexOf(',');
				if(sp>-1){
					alert("有且只有一个复选框必须被选中");
					return;
				}
				if (confirm("您确定要删除所选的信息？")) {
					var url = "system/latentCuster!delLatentCuster.action?latentCuster.lcId="+selectVal;
					$("form[name='searchF']").attr("action",url);
		    		$("form[name='searchF']").submit();
				}
			} else {
				alert("至少有一个复选框必须被选中！");
			}
		}
			
	</script>
  </head>
<body>
<form action="system/latentCuster!searchLatentCuster.action" method="post" name="queryForm" style="display: none;">
	<input type="hidden" value="<s:property value="page.curPage"/>" name="page.curPage" id="reqPage"/>
	<input type="hidden" name="latentCuster.lcText" value="${latentCuster.lcText }"/>
</form>
<div align="center" style="margin-top:10px;">
  <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="118" align="left"><img src="images/ny_t1.jpg" width="118" height="37" /></td>
      <td align="left" background="images/ny_t2.jpg" class="txt_bold">
      <form action="system/latentCuster!searchLatentCuster.action" method="post" id="searchF" name="searchF">
		<input type="hidden" value="字典信息维护" name="refreshPage"/>
		<input type="hidden" value="listPage" name="type"/>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="50%" height="30" valign="top">
          <label>字典内容:</label><input type="text" name="latentCuster.lcText" value="${latentCuster.lcText }"/>
            <input type="image" src="images/ny_searchbtn.jpg"/>
          </td>
          <td width="49%" valign="middle" align="right">&nbsp;
          	<c:if test="${fu:check('system/latentCuster!goAddPage.action')}">
          		<img src="images/ny_tj.jpg" width="19" height="19" align="absmiddle" />
				<a href="javascript:void(0)" title="新增字典" onclick="self.parent.addTab('新增字典','system/latentCuster!goAddPage.action','icon-add')" class="txt12">新增</a>
			</c:if>
          	&nbsp; &nbsp;
          	<c:if test="${fu:check('system/latentCuster!goUpdatePage.action')}">
          		<img src="images/edit.png" width="19" height="19" align="absmiddle" />
          		<a href="javascript:void(0)" title="编辑字典" onclick="javascript:doUpdate('lcId');" class="txt12">编辑</a>
          	</c:if>          
          	&nbsp; &nbsp;
          	<c:if test="${fu:check('system/latentCuster!delLatentCuster.action')}">
          		<img src="images/ny_sc.jpg" width="19" height="19" align="absmiddle" /> 
          		<a href="javascript:void(0)" title="删除字典" onclick="javascript:doDelete('lcId')" class="txt12">删除</a>
          	</c:if>
          	</td>
        </tr>
      </table>
      </form>
      </td>
      <td width="6" align="right"><img src="images/ny_t3.jpg" width="6" height="37" /></td>
    </tr>
  </table>
  <table width="99%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td align="left" class="non_topborder">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC">
        <tr class="title_style">
  			<td>选择</td>
  			<td>字典内容</td>
  			<td>排序号</td>
  			<td>录入时间</td>
        </tr>
        <c:if test="${!empty page.list}">
		  	<s:iterator id="entry" value="page.list" status="sta">
		  		<s:if test="#sta.even">
					<tr class="row_bg">
				</s:if>
				<s:else>
					<tr>
				</s:else>
		  			<td align="center" align="center">
		  				<input type="checkbox"  id="lcId" name="lcId" value="${entry.lcId }"/>
		  			</td>
		  			<td>		  			
		  				<a href="javascript:void(0);" onclick="self.parent.addTab('详细信息','system/latentCuster!goUpdatePage.action?latentCuster.lcId=${entry.lcId}','icon-add')">${entry.lcText }</a>					  			
		  			</td>
		  			<td align="center">
			  			<a href="javascript:void(0);" onclick="self.parent.addTab('详细信息','system/latentCuster!goUpdatePage.action?latentCuster.lcId=${entry.lcId}','icon-add')">${entry.lcOrder }</a>
		  			</td>
		  			<td align="center">
		  				<a href="javascript:void(0);" onclick="self.parent.addTab('详细信息','system/latentCuster!goUpdatePage.action?latentCuster.lcId=${entry.lcId}','icon-add')">${fn:substring(entry.lcDateTime,0,19) }</a>
		  			</td>
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
	              <td width="1%" align="right">&nbsp;</td>
	            </tr>
	          </table>
	          </td>
	          </tr>
	  	</c:if>
	  	<c:if test="${empty page.list}">
	  		<tr>
	  			<td colspan="10" align="center"><b>没有找到您所需要的记录!</b></td>
	  		</tr>
	  	</c:if>
      </table>
      </td>
    </tr>
  </table>
</div>
</body>
</html>