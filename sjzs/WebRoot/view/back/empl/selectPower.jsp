<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/view/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>选择功能页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/default.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href='js/tree/dhtmltree/css/dhtmlXTree.css"/>'>
	<style type="text/css">
	<!--
	body {background-color: #F6F6F6;}	
	.STYLE1 {line-height: 25px;font-size: 14px;color: #FFFFFF;}
	-->
	</style>
	<script src="js/tree/dhtmltree/js/dhtmlXCommon.js"></script>
	<script src="js/tree/dhtmltree/js/dhtmlXTree.js"></script>
	<script type="text/javascript">
		function doSubmit() {
			var pcodes = document.getElementById("powersCode");
			pcodes.value =  tree.getAllCheckedBranches();
			document.forms[0].submit();
		}
	</script>
  </head>
	<body>
	<form action="system/empl!addPower.action" method="post">
	<input type="hidden" name="empl.uid" value="${empl.emplId}">
	<input type="hidden" name="empl.powersCode" value="" id="powersCode">
	<input type="hidden" value="分配功能" name="closePage"/>
	<input type="hidden" value="员工管理" name="refreshPage"/>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td width="100%" valign="top" bgcolor="#93D3DF">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0" class="default_table">
						<tr>
							<td valign="top">
								<table width="100%" border="0" cellpadding="0" cellspacing="0"
									class="default_table2">
									<tr>
										<td>
											<table width="100%" border="0" cellspacing="1"
												cellpadding="0">
												<tr>
													<td bgcolor="94b4e0" class="default_title">
														<table width="98%" height="25" border="0" align="center"
															cellpadding="0" cellspacing="0">
															<tr>
																<td width="20%" class="STYLE1">
																	为岗位分配菜单
																</td>
																<td width="80%">&nbsp;
																	
																</td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td class="default_td"></td>
												</tr>
												<tr>
													<td>
														<table width="100%" border="0" cellspacing="2"
															cellpadding="0">
															<tr>
																<td>
																	<table width="100%" border="0" cellpadding="0"
																		cellspacing="0" bgcolor="#CCCCCC">
																		<tr>
																			<td>
																				<table width="100%" border="0" align="center"
																					cellpadding="0" cellspacing="1">
																					<tr background="images/bgtitle.gif">
																						<td height="25" class="default_text">
																							<div style="font-size: 12px">
																								<table width="40%" border="0" cellspacing="0"
																									cellpadding="0">
																									<tr>
																										<td>&nbsp;
																											
																										</td>
																										<td>
																											<a href="javascript:void(tree.openAllItems(0))"><img
																													src="images/outin_03.gif" width="53" height="19" border="0">
																											</a>
																										</td>
																										<td>
																											<a href="javascript:void(tree.closeAllItems(0))"><img
																													src="images/outin_05.gif" width="54" height="19" border="0">
																											</a>
																										</td>
																										<td>
																											<input
																												onclick="doSubmit();"
																												type="image"
																												src="images/ico01_03.gif">
																										</td>
																										<td>
																											&nbsp;
																											<!-- 
																											<a href="javascript:history.back();"><img
																													src="images/ico01_05.gif" width="53" height="19" border="0">
																											</a>
																											 -->
																										</td>
																									</tr>
																								</table>
																							</div>
																						</td>
																					</tr>
																					<tr>
																						<td height="380" valign="top" bgcolor="#F6F6F6"
																							class="default_text">
																							<div align="left">
																								<table width="80%" border="0" cellspacing="0"
																									cellpadding="0">
																									<tr>
																										<td>&nbsp;
																											
																										</td>
																									</tr>
																								</table>
																								<table width="80%" border="0" align="center"
																									cellpadding="0" cellspacing="0">
																									<tr>
																										<td>

																											<div id="treeboxbox_tree"></div>
																											<script>
																												tree=new dhtmlXTreeObject("treeboxbox_tree","100%","100%",0);
																												tree.setImagePath("<%=basePath%>js/tree/dhtmltree/imgs/");
																												//enable checkboxes
																												tree.enableCheckBoxes(true);
																												tree.enableThreeStateCheckboxes(true);
																												tree.loadXML("system/createXML.action?type=empl&empl.emplId=${empl.emplId}&tos=" + new Date().getTime());
																											</script>
																											<input type="hidden" id="mids" name="mids"
																												value="">

																										</td>
																									</tr>
																								</table>
																								<table width="80%" border="0" align="center"
																									cellpadding="0" cellspacing="0">
																									<tr>
																										<td>&nbsp;
																											
																										</td>
																									</tr>
																								</table>
																							</div>
																						</td>
																					</tr>
																				</table>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		</form>
	</body>
</html>