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
    <title>审计导航审计方法列表</title>
     <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	
		<style type="text/css">
		#tooltip{
			position: absolute;
			z-index: 2;	
			background: #efd;
			border: 1px solid #ccc;
			padding: 3px;
		}	
		</style>

	<jsp:include page="/jsPage.jsp"></jsp:include>
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<script src="js/dataCheck.js" type="text/javascript"></script>
<script type='text/javascript' src='js/treeView/scripts/jquery.contextmenu.r2-min.js'></script>
<script src="js/treeView/scripts/jquery.treeview.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/treeView/css/jquery.treeview.css" />
<link rel="stylesheet" type="text/css" href="js/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="js/themes/icon.css"/>
<script type="text/javascript" src="js/jquery.easyui.min.1.2.2.js"></script>
<script type="text/javascript" src='js/locale/easyui-lang-zh_CN.js'></script>
	<script type="text/javascript">
		function goNextPage( currentPage ) {
			document.getElementById("reqPage").value = currentPage;
			document.forms.namedItem("searchF").submit();	
		}

		//回调函数当删除成功后执行此操作
		function delCallBack() {
			document.forms.namedItem("queryForm").submit();
		}

		function addInfo(){
			   var url="system/sjdh!toAddInfo.action?sjdhMethod.typeNo=${sjdhMethod.typeNo}";
			   add(url);
			}
		
		function add(reqURL) {
			var iframe_str = "<iframe height=\"100%\" width=\"100%\" scrolling=\"no\" frameborder=\"0\" src=\"" + reqURL + "\"></iframe>";
			self.parent.addSjdh(reqURL,'700','350')	
		}
		
		function updateLaw(jmcode) {
			var selectVal = getCheckBox(jmcode,1);
			if ("" != selectVal) {
				var reqURL = "<%=basePath%>system/sjdh!toUpdateInfo.action?sjdhMethod.id="+selectVal+"&nocache=" + new Date().getTime();
				self.parent.updSjdh(reqURL,'700','350')	
			} 
			else {alert("有且只有一个复选框可以被选中");}}

		function deleteLaw(jmcode) {
				var checkboxs = document.getElementsByName("sjdhId"); //(jmcode,2);
				var values = '';
				for(var i = 0; i<checkboxs.length ;i++){
					if(checkboxs[i].checked==true){
						//values +="'"+checkboxs[i].value+"',"
						values += checkboxs[i].value+","
					}
				}

				if ("" != values) {
				if(confirm("您确定要删除么？")) {
					var goURI = "<%=basePath%>system/sjdh!delete.action?sjdhMethod.id=" + values+"&sjdhMethod.typeNo=${sjdhMethod.typeNo}";
					location.href = goURI;
					
				}
				}else{
					alert('请选择！');
				 }
			}

		 $(document).ready(function() {
			  $("#selectAll").click(function(){
			  if($(this).attr("checked") == true){ //check all
			  $("input[name='sjdhId']").each(function(){
			   $(this).attr("checked",true);
			  });
			  }else{
			  $("input[name='sjdhId']").each(function(){
			   $(this).attr("checked",false);
			  });
			  }
			  });
			  });

			function search(){
				$('#reqPage').val(0);
				var newName = $('#textfield').val();
				var oldName = "${sjdhMethod.name}";
				if(newName == oldName){
					$('#textfield').val("");
				}
			}
			function reloadPage(){
				document.searchF.submit();
			}
	</script>
  </head>
<body>
<form action="system/sjdh!serachSjdhMethod.action" method="post" name="queryForm" style="display: inline;">
	<input type="hidden" value="<s:property value="sjdhMethod.typeNo"/>" name="sjdhMethod.typeNo"/>
	<input type="hidden" value="<s:property value="page.curPage"/>" name="page.curPage" id="reqPage"/>
</form>

<div class="pageHeader">
 	<form rel="pagerForm" action="system/sjdh!serachSjdhMethod.action" method="post" id="searchF" name="searchF">
 	<input type="hidden" name="sjdhMethod.typeNo" value="${sjdhMethod.typeNo }" />
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>审计方法名称：</label>
				<input type="text" name="sjdhMethod.name" id="textfield" value="${sjdhMethod.name}"/>
			</li>
				<%--
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
				 --%>
			 <li><input type="image" src="images/ny_searchbtn.jpg" onclick="search()"/></li>
		</ul>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
		<c:if test="${fu:check('system/sjdh!toAddInfo.action')}">
			<li>
				<a class="add" href="javascript:void(0);" onclick="javascript:addInfo()" ><span>添加</span></a>
			</li>
		</c:if>
		<c:if test="${fu:check('system/sjdh!toUpdateInfo.action')}">
			<li>
				<a class="edit" href="javascript:void(0)" onclick="updateLaw('sjdhId')" warn="请选择"><span>修改</span></a>
			</li>
		</c:if>
		<c:if test="${fu:check('system/sjdh!delete.action')}">
			<li>
				<a class="delete" href="javascript:deleteLaw()"  title="确定要删除吗？" warn="请选择"><span>删除</span></a>
			</li>
		</c:if>
		</ul>
	</div>
	
	<div id="w_list_print">
	<table class="list" width="100%" targetType="navTab" asc="asc" desc="desc" layoutH="118">
		<thead>
			<tr>
			    <c:if test="${fu:check('system/sjdh!toAddInfo.action')}">
				<th width="3%"><input type="checkbox" id='selectAll' class="checkboxCtrl"></th>		
				</c:if>		
				<th width="7%">序号</th>
	  			<th width="50%">审计方法名称</th>
	  			<c:if test="${fu:check('system/workflow!getTempInfo.action')== true}">
	  			 <th width="40%">流程图管理</th>
	  			</c:if>
	  			<c:if test="${fu:check('system/workflow!getTempInfo.action')==false}">
	  			 <th width="40%"> 审计方法简介</th>
	  			 </c:if>
			</tr>
		</thead>
		<tbody>
			<s:iterator id="entry" value="page.list" status="sta">
				<tr target="sid_user" rel="${sta.count }">
				    <c:if test="${fu:check('system/sjdh!toAddInfo.action')}">
		  			<td class="txt03467B"><input type="checkbox" name="sjdhId" value="${entry.id }" /></td>
		  			</c:if>
		  			<td>${sta.count+(page.curPage-1)*page.maxResult}</td>
		  			<td>
		  				<%--<a style="text-decoration:none;">${entry.name}</a>--%>
		  				<a style="text-decoration:none;" class="add" href="javascript:void(0);" onclick="self.parent.parent.addTab('流程查看','system/workflow!initFlowView.action?workFlowDTO.id=${entry.template_no}&type=${entry.id }','icon-add')" >${entry.name}</a>
						<input type="hidden" value="${entry.name}" />
		  			</td>
		  			<td>
		  			<!--  workflow!toFlowPage.action? -->
		  			<c:if test="${fu:check('system/workflow!getTempInfo.action')==true}">
		  			<a class="add" href="javascript:void(0);" onclick="self.parent.parent.addTab('创建审计导航图','system/workflow!getTempInfo.action?workFlowDTO.id=${entry.template_no }&type=${entry.id}','icon-add')" >创建</a>
		  			</c:if>
		  			<c:if test="${fu:check('system/workflow!getTempInfo.action')==false}">
		  		    <a style="text-decoration:none;">${entry.context}</a>
						<input type="hidden" value="${entry.context}" />
						</c:if>
		  			</td>
		  			
		  		</tr>
		  	</s:iterator>
		</tbody>
	</table>
	</div>
	
				<div class="panelBar">
					<div class="pages" style="text-align: center;width:95%">
						<table style="width: 100%">
						 <tr>
						  <td>	<span><font color="red">点击"审计方法"可查看详细信息！</font> </span></td>
						  <td  style="text-align: right;">
						 <jsp:include page="/page.jsp"></jsp:include>
						  </td>
						 </tr>
						</table>
			</div>
	</div>
	</div>
</div>

<div id="addMenuWin" iconCls="icon-save" title="查看" style="width: 0;height: 0"></div> 

</body>

<script type="text/javascript">
	$(function(){
				function dymShowText(column,nameLen,width){
					$("tbody td:nth-child("+column+")").mousemove(function(event){
						$("#tooltip").remove();
						var dicMemo = $(this).children("input").val();
						if(dicMemo != null && dicMemo !=""){
							$('<div id="tooltip">'+dicMemo+'</div>').css("width",width).appendTo("body");
								var tPosX = (event.pageX - 5) + 'px';
								var tPosY = (event.pageY + 20) + 'px';
								$("#tooltip").css("top",tPosY).css("left",tPosX);
						  }
					})
					.mouseout(function(){
						$("#tooltip").remove();
					});
					
					$("tbody td:nth-child("+column+")").each(function(){
						var str = $(this).children("a").text();
						if($(this).text().length>nameLen){
							var temp = str.substr(str,nameLen) + "......";
							$(this).children("a").html(temp);
						}
					});
				};
				dymShowText(2,50,300);
				dymShowText(3,25,300);
			});
</script>
</html>
