<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/view/common/common.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>" />
		<title>定性依据列表页面</title>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<link rel="stylesheet" type="text/css"
			href="js/themes/default/easyui.css" />
		<link rel="stylesheet" type="text/css" href="js/themes/icon.css" />
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
		<script src="js/dataCheck.js" type="text/javascript"></script>
		<script language="javascript" src="js/public.js" charset="utf-8"></script>
		<script src="js/treeView/scripts/jquery.min.js" type="text/javascript"></script>
		<script type="text/javascript" src="js/jquery.easyui.min.1.2.2.js"></script>
		<script type="text/javascript" src='js/locale/easyui-lang-zh_CN.js'></script>
		<script type="text/javascript">
		var  menuType = "${param.menuType}";
	function goNextPage( currentPage ) {
		document.getElementById("reqPage").value = currentPage;
		if('${newFlag}'=='all'){
			$("#searchForm").attr("action","system/dxyj!searchMessage.action");
		}else
		{
			$("#searchForm").attr("action","system/dxyj!searchMessage.action");
		}
		document.forms.namedItem("searchForm").submit();	
	}

	function getCheckBox(checkboxname, counts) {
		var selectCount = 0;
		var selectValue = "";
		//选取input中type为checkbox,name为checkboxname的表单
		var myselector = "input[type='checkbox'][name='"+checkboxname+"']";
		var checkboxs = $(myselector).each(function(){
			//alert($(this).attr("checked")=="checked");
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
	
	//修改
	function updateLaw(jmcode) {
		var selectVal = getCheckBox(jmcode,1);
		if ("" != selectVal) {
			var reqURL = "<%=basePath%>system/dxyj!goToUpdatePage.action?law.Id="+selectVal+"&nocache=" + new Date().getTime();
			self.parent.upddxyj(reqURL,'700','480')	
		}else {
			alert("有且只有一个复选框可以被选中");
		}
	}
	
	//打开指定的链接地址
	function goFunction(goURL) {
			top.document.getElementById('main').src=goURL;
	}

	function reloadPage(){
		document.searchForm.submit();
	}
	
	//新增法规
	function addLaw(){
	     // document.getElementById('add').style.display = "";
	     var reqURL = "<%=basePath%>system/dxyj!goToAddPage.action?law.parentID=${law.parentID}&nocache=" + new Date().getTime();
		 self.parent.adddxyj(reqURL,'700','480');	
	}
	//删除法规
	function deleteLaw() {
			var checkboxs = document.getElementsByName("lawId"); //(jmcode,2);
			var values = '';
			for(var i = 0; i<checkboxs.length ;i++){
				if(checkboxs[i].checked==true){
					values +=checkboxs[i].value+","
				}
			}
			if ("" != values) {
			if(confirm("您确定要删除么？")) {
				var goURI = "system/dxyj!deleteLaws.action?law.id=" + values+"&law.parentID=${law.parentID}";
				document.deleteForm.action = goURI;
				document.deleteForm.submit();   //获取所有的法规信息
			}
			}else{
				alert('请选择！');
			}
			return false; 
		}
	//迁移
	function move(jmcode) {
		var checkboxs = document.getElementsByName("lawId"); //(jmcode,2);
		var values = '';
		for(var i = 0; i<checkboxs.length ;i++){
			if(checkboxs[i].checked==true){
				values +=checkboxs[i].value+","
			}
		}
		values =values.substring(0,values.length-1);
		if ("" != values) {
			var reqURL="<%=basePath%>system/sjzs!moveTree.action?menuType="+menuType+"&attIds="+values+"&nocache="+new Date().getTime();
			//var reqURL = "<%=basePath%>system/ffal!goToMovePage.action?attIds="+values+"&nocache=" + new Date().getTime();
			self.parent.updateFFAL(reqURL,'270','500');
		
		}
}
	//查找
	function search(){
		location.href="system/dxyj!searchPage.action?newFlag=all";
	}
	
	
	 $(document).ready(function() {
		  $("#selectAll").click(function(){
		  if($(this).attr("checked") == true){ //check all
		  $("input[name='lawId']").each(function(){
		   $(this).attr("checked",true);
		  });
		  }else{
		  $("input[name='lawId']").each(function(){
		   $(this).attr("checked",false);
		  });
		  }
		  });
		  });


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

			dymShowText(3,20,200);
			dymShowText(4,15,170);
			dymShowText(5,15,170);
		});

		function search(){
			$('#reqPage').val(0);
			var newName = $('#textfield').val();
			var oldName = "${law.caption}";
			if(newName == oldName){
				$('#textfield').val("");
			}
		}
</script>
	</head>
	<body>

		<div id="navTab" class="tabsPage">
			<div class="pageHeader">
				<form id="deleteForm" name="deleteForm" action="" method="post"
					style="display: none;">
					<input type="hidden" value="${param.menuType}" name="menuType"                                 0/></form>
				<form action="system/dxyj!searchMessage.action" method="post"
					id="searchForm" name="searchForm">
					<input type="hidden" value="<s:property value="page.curPage"/>"
						name="page.curPage" id="reqPage" />
					<%--
					<input type="hidden" name="law.lawNo" value="${law.lawNo }" />
					<input type="hidden" name="law.id" value="${law.id}" />
					 --%>
					<input type="hidden" name="law.parentID" value="${law.parentID}" />
					<input type="hidden" value="${param.menuType}" name="menuType" id="menuType" />
					<!-- ${law.parentID} -->
					<div class="searchBar">
						<ul class="searchContent">
							<li>
								<label>
									定性依据：
								</label>
								<input type="text" name="law.caption" id="textfield" value="${law.caption}"/>
							</li>
							<li>
								<input type="image" src="images/ny_searchbtn.jpg" onclick="search()"/>
							</li>
						</ul>

					</div>
				</form>
			</div>
			<div class="pageContent">
				<div class="panelBar" style="clear: left;">
					<ul class="toolBar">
						<li>
							<c:if test="${fu:check('system/dxyj!goToAddPage.action')}">
								<a class="add" href="javascript:void(0);" onclick="addLaw()"><span>添加</span>
								</a>
							</c:if>
						</li>
						<li>
							<c:if test="${fu:check('system/dxyj!deleteLaws.action')}">
								<a class="delete" href="javascript:void(0);"
									onclick="deleteLaw()" title="确定要删除吗？" warn="请选择"><span>删除</span>
								</a>
							</c:if>
						</li>
						<li>
							<c:if test="${fu:check('system/dxyj!goToUpdatePage.action')}">
								<a class="edit" href="javascript:void(0);"
									onclick="updateLaw('lawId')" warn="请选择一个用户"><span>修改</span>
								</a>
							</c:if>
						</li>
					</ul>
				</div>
				<div id="w_list_print">
					<table class="list" width="100%" targetType="navTab" asc="asc"
						desc="desc" layoutH="118">
						<thead>
							<tr align="center">
							<c:if test="${fu:check('system/dxyj!goToAddPage.action')}">
								<th width="3%">
									<input type="checkbox" id='selectAll' class="checkboxCtrl" />
								</th>
								</c:if>
								<th width="7%">
									序号
								</th>
								<th width="28%">
									<strong>法规文件名称</strong>
								</th>
								<th width="22%">
									<b>发文单位</b>
								</th>
								<th width="16%">
									<b>发文编号</b>
								</th>
								<th width="12%">
									<b>条</b>
								</th>
								<th width="12%">
									<b>款</b>
								</th>
							</tr>
						</thead>
						<tbody id='playList'>
							<s:iterator id="entry" value="page.list" status="sta">
									<tr rel="${sta.count }">
									<c:if test="${fu:check('system/dxyj!goToAddPage.action')}">
									<td>
										<input type="checkbox" name="lawId" value="${entry.id }" />
									</td>
									</c:if>
									<td>
										${sta.count+(page.curPage-1)*page.maxResult}
									</td>
									<td>
										<%--<a href="dxyj!getLawById.action?law.id =${entry.id }">${entry.caption}</a>--%>
										<a href="javascript:void(0);" onclick="self.parent.parent.addTab('查看定性依据','system/dxyj!getLawById.action?law.id =${entry.id }')">${entry.caption}</a>
										<input type="hidden" value="${entry.caption}" />
									</td>
									<td>
										<a style="text-decoration:none;">${entry.department}</a>
										<input type="hidden" value="${entry.department}" />
									</td>
									<td>
										<a style="text-decoration:none;">${entry.lawNo}</a>
										<input type="hidden" value="${entry.lawNo}" />
									</td>
									<td>
										${entry.tiao}
									</td>
									<td>
										${entry.kuan}
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
						  <td>	<span><font color="red">点击"法规文件名"可查看详细信息！</font> </span></td>
						  <td  style="text-align: right;">
						  <a style="color: #1E5494;">总记录数：${page.totalRecords} 条</a>&nbsp;&nbsp;
	<c:choose>
		<c:when test="${ 1 == page.curPage}">
			<a>首页</a>
			<a>上一页</a>
		</c:when>
		<c:otherwise>
			<a href="" onClick="goNextPage(1);return false;">首页</a>
			<a href="" onClick="goNextPage(${page.curPage - 1 });return false;">上一页</a>
		</c:otherwise>
	</c:choose>
	<c:forEach begin="${page.beginIndex}" end="${page.endIndex}"
		var="pageCount" step="1">
		<c:choose>
			<c:when test="${ pageCount == page.curPage}">
				<a href="" onClick="goNextPage(${pageCount });return false;"
					style="color: red;">${pageCount }</a>
			</c:when>
			<c:otherwise>
				<a href="" onClick="goNextPage(${pageCount });return false;">${pageCount}</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:choose>
		<c:when test="${ page.curPage >= page.totalPage}">
			<a>下一页</a>
			<a>末页</a>
		</c:when>
		<c:otherwise>
			<a href="" onClick="goNextPage(${page.curPage + 1});return false;">下一页</a>
			<a href="" onClick="goNextPage(${page.totalPage });return false;">末页</a>
		</c:otherwise>
	</c:choose>
						  </td>
						 </tr>
						</table>
					</div><%--

					<div class="pagination">

						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr class="nonborder">
								<td align="right"><jsp:include page="/page.jsp"></jsp:include>
								</td>
								<td width="1%" align="right">
									&nbsp;
								</td>
							</tr>
						</table>
					</div>
				--%></div>
				</div>

			</div>
		</div>
	</div>
	</body>
</html>
