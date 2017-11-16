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
		<title>审核列表</title>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<link rel="stylesheet" type="text/css"
			href="js/themes/default/easyui.css" />
		<link rel="stylesheet" type="text/css" href="js/themes/icon.css" />
		<jsp:include page="/jsPage.jsp"></jsp:include>
		<style type="text/css">
			#tooltip {
				position: absolute;
				z-index: 2;
				background: #efd;
				border: 1px solid #ccc;
				padding: 3px;
			}
			
			.mysearch ul li {
				list-style-type: none;
				float: left;
				margin-left: 20px;
			}
		</style>
		<script src="js/dataCheck.js" type="text/javascript"></script>
		<script language="javascript" src="js/public.js" charset="utf-8"></script>
		<script src="js/treeView/scripts/jquery.min.js" type="text/javascript"></script>
		<script type="text/javascript" src="js/jquery.easyui.min.1.2.2.js"></script>
		<script type="text/javascript" src='js/locale/easyui-lang-zh_CN.js'></script>
		<script type="text/javascript">
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

		function update(jmcode) {
			var selectVal = getCheckBox(jmcode,1);
			if ("" != selectVal) {
				var reqURL = "<%=basePath%>system/ssfa!goToUpdatePage.action?shiShiFangAn.id="+selectVal+"&shiShiFangAn.sort=${shiShiFangAn.sort}&nocache=" + new Date().getTime();
				self.parent.updateFFAL(reqURL,'700','350')	
			} 
			else {
				alert("有且只有一个复选框可以被选中");
				}
			}

		 $(document).ready(function() {
			  $("#selectAll").click(function(){
			  if($(this).attr("checked") == true){ //check all
			  $("input[name='clientUploadId']").each(function(){
			   $(this).attr("checked",true);
			  });
			  }else{
			  $("input[name='clientUploadId']").each(function(){
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

				dymShowText(3,10,150);
				dymShowText(4,15,200);
			});
			
			function search(){
				$('#reqPage').val(0);
				var newName = $('#textfield').val();
				var oldName = "";
				if(newName == oldName){
					$('#textfield').val("");
				}
			}
			
			//批量审核功能 
			function checkBatch(flag){
				var checkboxs = document.getElementsByName("clientUploadId"); 
				var values = '';
				for(var i = 0; i<checkboxs.length ;i++){
					if(checkboxs[i].checked==true){
						values +=checkboxs[i].value+";"
					}
				}
				if ("" != values) {
					if(flag==2){
						if(confirm("您确定要全部审核通过么？")) {
							var goURI = "system/wdsh!checkBatch.action";
							$("#batchData").val(values);
							$("#result").val(2);
							document.batchCheck.action = goURI;
							document.batchCheck.submit();
						}
					}
					if(flag==3){
						if(confirm("您确定要全部打回么？")) {
							var goURI = "system/wdsh!checkBatch.action";
							$("#batchData").val(values);
							$("#result").val(3);
							document.batchCheck.action = goURI;
							document.batchCheck.submit();
						}
					}
				}else{
					alert('请选择！');
				}
			}

			//审核前判断当前记录是否正在被他人审核
			function isChecking(uploadId,type){
				$.ajax({
					type:"GET",
					url:"system/wdsh!isChecking.action",
					data:"uploadId="+uploadId+"&type="+type,
					success:function(data){
						if(data){
							self.parent.addTab('文档审核','system/wdsh!toCheck.action?uploadId='+uploadId+'&type='+type+'','icon-add')
						}else{
							alert("当前信息正在被审核 ！");
							//刷新当前页面
						}
					}
				});
			}
		</script>
	</head>
	<body>

		<div id="navTab" class="tabsPage">
			<div class="pageHeader">
				<form id="deleteForm" name="batchCheck" action="" method="post"
					style="display: none;">
					<input type="hidden" id="batchData" name="batchData" value="" />
					<input type="hidden" id="result" name="result" value="" />
				</form>
				<form rel="pagerForm" action="system/wdsh!list.action" method="post"
					id="searchF" name="searchF">
					<input type="hidden" value="${page.curPage }" name="page.curPage"
						id="reqPage" />
					<%--
					<div class="searchBar">
						<ul class="searchContent">
					 --%>
					<div class="mysearch">
						<ul>
							<li>
								<label>
									文档名称：
								</label>
								<input type="text" name="clientUpload.caption" id="textfield"
									value="${clientUpload.caption }" />
							</li>
							<li>
								<label>
									审核状态：
								</label>
								<select name="clientUpload.checkFlag">
									<option value="0"
										<s:if test="clientUpload.checkFlag == 0">selected="selected"</s:if>>
										未审核
									</option>
									<option value="1"
										<s:if test="clientUpload.checkFlag == 1">selected="selected"</s:if>>
										审核中
									</option>
									<option value="2"
										<s:if test="clientUpload.checkFlag == 2">selected="selected"</s:if>>
										已通过
									</option>
									<option value="3"
										<s:if test="clientUpload.checkFlag == 3">selected="selected"</s:if>>
										已打回
									</option>
								</select>
							</li>
							<li>
								<label>
									文档分类：
								</label>
								<select name="clientUpload.type">
									<option value="ALL"></option>
									<option value="FG"
										<s:if test="clientUpload.type == 'FG'">selected="selected"</s:if>>
										审计法规
									</option>
									<option value="YJ"
										<s:if test="clientUpload.type == 'YJ'">selected="selected"</s:if>>
										定性依据
									</option>
									<option value="AL"
										<s:if test="clientUpload.type == 'AL'">selected="selected"</s:if>>
										审计方法
									</option>
									<option value="EI"
										<s:if test="clientUpload.type == 'EI'">selected="selected"</s:if>>
										实施方案
									</option>
								</select>
							</li>
							<li>
								<label>
									审核人：
								</label>
								<select name="clientUpload.curCheckUserId">
									<option value="ALL">
										系统所有
									</option>
									<option value="${loginEmpl.emplId }"
										<s:if test="clientUpload.curCheckUserId == #session.loginEmpl.emplId">selected="selected"</s:if>>
										我的审核
									</option>
								</select>
							</li>
							<li>
								<input type="image" src="images/ny_searchbtn.jpg"
									onclick="search()" />
							</li>
						</ul>
					</div>
				</form>
			</div>

			<div class="pageContent">
				<div class="panelBar">
					<ul class="toolBar">
						<li>
							<c:if test="${fu:check('system/wdsh!check.action')}">
								<a class="add" href="javascript:void(0)" onclick="checkBatch(2)"><span>审核通过</span>
								</a>
							</c:if>
						</li>
						<li>
							<c:if test="${fu:check('system/wdsh!check.action')}">
								<a class="add" href="javascript:void(0)" onclick="checkBatch(3)"><span>打回</span>
								</a>
							</c:if>
						</li>
						<li class="line">
							line
						</li>
					</ul>
				</div>

				<div id="w_list_print" style="overflow: atuo;">
					<table class="list" width="100%" targetType="navTab" asc="asc"
						desc="desc" layoutH="118">
						<thead>
							<tr>
								<c:if test="${fu:check('system/wdsh!check.action')}">
									<th width="3%">
										<input type="checkbox" id='selectAll' class="checkboxCtrl">
									</th>
								</c:if>
								<th width="5%">
									序号
								</th>
								<th width="15%">
									文档名称
								</th>
								<th width="18%">
									内容简介
								</th>
								<th width="8%">
									文档类型
								</th>
								<th width="10%">
									上传用户
								</th>
								<th width="10%">
									上传时间
								</th>
								<th width="8%">
									审核状态
								</th>
								<c:if test="${clientUpload.checkFlag != 0}">
									<th width="8%">
										审核人员
									</th>
								</c:if>
								<c:if test="${fu:check('system/wdsh!checkAll.action')}">
									<th width="8%">
										操作
									</th>
								</c:if>
								<c:if test="${!fu:check('system/wdsh!checkAll.action') && fu:check('system/wdsh!check.action')}">
									<s:if test="clientUpload.checkFlag != 2  && clientUpload.checkFlag != 3">
										<th width="8%">
											操作
										</th>
									</s:if>
								</c:if>
							</tr>
						</thead>
						<tbody id='playList'>
							<s:iterator id="entry" value="page.list" status="sta">
								<tr rel="">
									<c:if test="${fu:check('system/wdsh!check.action')}">
										<td align="center">
											<input type="checkbox" name="clientUploadId"
												value="${entry.id },${entry.type}" />
										</td>
									</c:if>
									<td>
										${sta.count+(page.curPage-1)*page.maxResult}
									</td>
									<td>
										<a href="javascript:void(0)"
											onclick="self.parent.addTab('详细信息','system/wdsh!showAttachInfo.action?attachId =${entry.attachId }&type=0','icon-add')">${entry.caption
											}</a>
										<input type="hidden" value="${entry.caption }" />
									</td>
									<td>
										<a style="text-decoration: none;">${entry.content }</a>
										<input type="hidden" value="${entry.content }" />
									</td>
									<td>
										<s:if test="type == 'FG'">审计法规</s:if>
										<s:elseif test="type == 'YJ'">定性依据</s:elseif>
										<s:elseif test="type == 'EI'">实施方案</s:elseif>
										<s:elseif test="type == 'AL'">审计方法</s:elseif>
									</td>
									<td>
										${entry.clientName }
									</td>
									<td>
										<%--<fmt:parseDate value="${entry.uploadDate}" var="date"/>
										<fmt:formatDate value="${date}" pattern="yyyy-MM-dd"/>--%>
										<s:date name="uploadDate" nice="false" format="yyyy-MM-dd"/>
									</td>
									<td>
										<s:if test="checkFlag == 0">待审核</s:if>
										<s:elseif test="checkFlag == 1">审核中</s:elseif>
										<s:elseif test="checkFlag == 2">审核通过</s:elseif>
										<s:elseif test="checkFlag == 3">已打回</s:elseif>
									</td>
									<c:if test="${clientUpload.checkFlag != 0}">
										<td>
											${entry.curCheckUserName }
										</td>
									</c:if>
									<c:if test="${fu:check('system/wdsh!checkAll.action')}">
										<td>
											<a href="javascript:void(0)"
												onclick="isChecking('${entry.id }','${entry.type}')">审核</a>
										</td>
									</c:if>
									<c:if test="${!fu:check('system/wdsh!checkAll.action') && fu:check('system/wdsh!check.action')}">
										<s:if test="clientUpload.checkFlag != 2 && clientUpload.checkFlag != 3">
											<td>
												<s:if test="clientUpload.checkFlag == 1">
													<s:if test="#session.loginEmpl.emplId == curCheckUserId">
														<%--
													<a href="javascript:void(0)" onclick="self.parent.addTab('文档审核','system/wdsh!toCheck.action?uploadId=${entry.id }&type=${entry.type}','icon-add')">审核</a>
													 --%>
														<a href="javascript:void(0)"
															onclick="isChecking('${entry.id }','${entry.type}')">审核</a>
													</s:if>
													<s:else>
														<a href="javascript:void(0)" style="color: red;">审核</a>
													</s:else>
												</s:if>
												<s:elseif test="clientUpload.checkFlag == 3">
													<s:if test="#session.loginEmpl.emplId == curCheckUserId">
														<%--
													<a href="javascript:void(0)" onclick="self.parent.addTab('文档审核','system/wdsh!toCheck.action?uploadId=${entry.id }&type=${entry.type}','icon-add')">审核</a>
												--%>
														<a href="javascript:void(0)"
															onclick="isChecking('${entry.id }','${entry.type}')">审核</a>
													</s:if>
													<s:else>
														<a href="javascript:void(0)" style="color: red;">审核</a>
													</s:else>
												</s:elseif>
												<s:else>
													<%--
													<a href="javascript:void(0)" onclick="self.parent.addTab('文档审核','system/wdsh!toCheck.action?uploadId=${entry.id }&type=${entry.type}','icon-add')">审核</a>
												--%>
													<a href="javascript:void(0)"
														onclick="isChecking('${entry.id }','${entry.type}')">审核</a>
												</s:else>
											</td>
										</s:if>
									</c:if>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>

				<div class="panelBar">
					<div class="pages" style="text-align: center; width: 95%">
						<table style="width: 100%">
							<tr>
								<td style="text-align: right;">
									<a style="color: #1E5494;">总记录数：${page.totalRecords} 条</a>&nbsp;&nbsp;
									<c:choose>
										<c:when test="${ 1 == page.curPage}">
											<a>首页</a>
											<a>上一页</a>
										</c:when>
										<c:otherwise>
											<a href="" onClick="goNextPage(1);return false;">首页</a>
											<a href=""
												onClick="goNextPage(${page.curPage - 1 });return false;">上一页</a>
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
											<a href=""
												onClick="goNextPage(${page.curPage + 1});return false;">下一页</a>
											<a href=""
												onClick="goNextPage(${page.totalPage });return false;">末页</a>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>

	</body>
</html>
