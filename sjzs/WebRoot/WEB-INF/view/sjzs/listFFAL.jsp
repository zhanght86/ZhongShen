<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/view/common/common.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>"/>
    <title>方法案例列表页面</title>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	
	<link rel="stylesheet" type="text/css" href="js/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="js/themes/icon.css"/>
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
		document.forms.namedItem("searchF").submit();
	}

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
	
	function reloadPage(){
		document.searchF.submit();
	}
	function deleteFFAL(jmcode) {
			var checkboxs = document.getElementsByName("ffalId"); //(jmcode,2);
			var values = '';
			for(var i = 0; i<checkboxs.length ;i++){
				if(checkboxs[i].checked==true){
					values +=checkboxs[i].value+","
				}
			}
			if ("" != values) {
			if(confirm("您确定要删除么？")) {
				var goURI = "system/ffal!deleteFFALs.action?ffal.id=" + values+"&ffal.sort=${ffal.sort}";;
				document.deleteForm.action = goURI;
				document.deleteForm.submit();   //获取所有的法规信息
			}
			}else{
				alert('请选择！');
			 }
		}


		//添加
		
		function addFFAL() {
			var reqURL = "<%=basePath%>system/ffal!goToAddPage.action?ffal.sort=${ffal.sort}&nocache=" + new Date().getTime();
			self.parent.addFFAL(reqURL,'700','550')	
		}


		function update(jmcode) {
			var selectVal = getCheckBox(jmcode,1);
			if ("" != selectVal) {
				var reqURL = "<%=basePath%>system/ffal!goToUpdatePage.action?ffal.id="+selectVal+"&ffal.sort=${ffal.sort}&nocache=" + new Date().getTime();
				self.parent.updateFFAL(reqURL,'700','550')	
			} 
			else {
				alert("有且只有一个复选框可以被选中");
				}
			}
//迁移
		function move(jmcode) {
			var checkboxs = document.getElementsByName("ffalId"); //(jmcode,2);
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


		//下载
		function downloadAttach(curchaId) {
			document.getElementById("attId").value = curchaId;
			if(document.downloadForm.attId!=""){
					document.downloadForm.submit();   //获取所有的法规信息
			}else{
				alert("没有要下载的文件！");
			}
		}



		 $(document).ready(function() {
			  $("#selectAll").click(function(){
			  if($(this).attr("checked") == true){ //check all
			  $("input[name='ffalId']").each(function(){
			   $(this).attr("checked",true);
			  });
			  }else{
			  $("input[name='ffalId']").each(function(){
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

				dymShowText(3,16,200);
				dymShowText(4,13,150);
			});
			
			function search(){
				$('#reqPage').val(0);
				var newName = $('#textfield').val();
				var oldName = "${ffal.title}";
				if(newName == oldName){
					$('#textfield').val("");
				}
			}		

		    function showUserUpload(){
				var temp = $("#uploadUser").val();
				if(temp == "1"){
					var temp = $("#textfield").val();
					$("#caption").val(temp);
					document.forms.namedItem("userUpload").submit();
					return false;
				}else{
					return true;
				}
		    }
</script>
  </head>
 <body>
<div id="navTab" class="tabsPage">
 <div class="pageHeader">
  <form id="userUpload" name="userUpload" action="system/clientUpload!list.action" method="post">
	<input type="hidden" value="${param.menuType}" name="menuType" id="menuType" />
	<input type="hidden" name="ffal.caption" id="caption" value="${ffal.title }"/>
	<input type="hidden" name="ffal.parentId" value="${ffal.sort}" />
 </form>
 <form id="deleteForm" name="deleteForm" action = "" method="post" style="display: none;">
  <input type="hidden" value="${param.menuType}" name="menuType" /></form>
  <form rel="pagerForm" action="system/ffal!searchByCondition.action" method="post" id="searchF" name = "searchF" onsubmit="return showUserUpload();">
   <input type="hidden" value="<s:property value="page.curPage"/>" name="page.curPage" id="reqPage"/>
   <input type="hidden" value="${param.menuType}" name="menuType" id="menuType" />
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>案例名称：</label>
				 <input type="text" name="ffal.title" id="textfield" value="${ffal.title}"/>
				 <input type="hidden" name="ffal.sort" value="${ffal.sort}"/>
			</li>
			<li>
				<label>
					上传人：
				</label>
				<select id="uploadUser">
					<option value="0" selected="selected">管理员</option>
					<option value="1">前台用户</option>
				</select>
			</li>
			<li><input type="image" src="images/ny_searchbtn.jpg" onclick="search()"/></li>
		</ul><%--
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
				<!-- <li><a class="button" href="demo_page6.html" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li> -->
			</ul>
		</div>
	--%></div>
	</form>
</div>
 
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li>
				<c:if test="${fu:check('system/ffal!goToAddPage.action')}">
				 <a class="add" href="javascript:void(0)" onclick="addFFAL();" ><span>添加</span></a>
				</c:if>
			</li>
			<li>
				<c:if test="${fu:check('system/ffal!deleteFFALs.action')}">
					<a class="delete" href="javascript:deleteFFAL()"  title="确定要删除吗？" warn="请选择"><span>删除</span></a>
				</c:if>
			</li>
			<li>
				<c:if test="${fu:check('system/ffal!goToUpdatePage.action')}">
					<a class="edit" href="javascript:void(0)" onclick="update('ffalId')" warn="请选择"><span>修改</span></a>
				</c:if>
			</li>
			<li class="line">line</li>
			<%--<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
			<li><a class="icon" href="javascript:history.go(-1);"><span>返回</span></a></li>
		--%></ul>
	</div>

	<div id="w_list_print">
	<table class="list" width="100%" targetType="navTab" asc="asc" desc="desc" layoutH="118">
		<thead>
			<tr><c:if test="${fu:check('system/ffal!goToAddPage.action')}">	
			    <th width="3%"><input type="checkbox" id='selectAll' class="checkboxCtrl"></th>	
			    </c:if>
		    	<th width="7%">序号</th>	
				<th width="25%">标题</th>	
				<th width="17%">撰写单位</th>
				<th width="12%">撰写人</th>			
				<th width="12%">撰写日期</th>	
				<th width="12%">获奖情况</th>	
				<c:if test="${fu:check('system/ffal!goToAddPage.action')}">
				<th width="12%">下载附件</th>	
				</c:if>
			</tr>
		</thead>
		<form method="post" action="attach/download/DownLoadServletSJZS" id="downloadForm" name="downloadForm" style="display: none;">
					<input type="hidden" name="attId" id ='attId' value=""/>
					<%--<input type="button" value = "下载" onclick="downloadAttach('<s:property value="attach.attachId"/>')">--%>
	</form>
		<tbody id = 'playList'>
			<s:iterator id="entry" value="page.list" status="sta">
				<tr rel="${sta.count }">
				<c:if test="${fu:check('system/ffal!goToAddPage.action')}">
		  			<td align="center">
		  			<input type="checkbox" name="ffalId" value="${entry.id }"/>
		  			</td>
		  			</c:if>
			  			<td>${sta.count+(page.curPage-1)*page.maxResult}</td>
			  			<td>
						<a href="javascript:void(0)" onclick="self.parent.parent.addTab('查看方法案例','attach!showAtttachInfo.action?attach.attachId =${entry.attachId }&type=0','icon-add')" >${entry.title}</a>
						<input type="hidden" value="${entry.title}" />
						</td>
						<td>
							<a style="text-decoration:none;">${entry.department}</a>
							<input type="hidden" value="${entry.department}" />
						</td>
						<td>
							${entry.author}
						</td>
						<td>
							${entry.ffalDateTime}
						</td>
						<td>
							${entry.awards}
						</td>
						<c:if test="${fu:check('system/ffal!goToAddPage.action')}">
						<td>
							 <img src="images/xt_12.gif" width="19" height="19"  />
	                   <a href="javascript:downloadAttach('${entry.attachId }')" class="txt12">下载</a>
						</td>
						</c:if>
					</tr>
		  		</tr>
		  	</s:iterator>
		</tbody>
	</table>
	</div>
	
<div class="panelBar">
					<div class="pages" style="text-align: center;width:95%">
						<table style="width: 100%">
						 <tr>
						  <td>	<span><font color="red">点击"标题"可查看详细信息！</font> </span></td>
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
</body>
</html>
