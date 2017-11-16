<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/view/common/common.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns:v>
<head>
	 <base href="<%=basePath%>"/>
     <title>审计导航流程图</title>
     <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">   
	<link rel="stylesheet" type="text/css" href="flow/themes/default/easyui.css">
	<link href="flow/css/flowPath.css" type="text/css" rel="stylesheet" />
	<style type="text/css">
		.selectBut{ 
          width:93px;    
          height:27px;    
          background:url("images/buttons/select.gif") no-repeat center;      
          font-size:0;            
          line-height:0;    
          border: 0px;
          cursor:hand;
	}
	a{
		font-size:10pt;
		color:black;
		text-decoration: none;
	}
	a:hover {  text-decoration: underline; color: red; }
	
	
	</style>
		
	<script type="text/javascript">
	//alert("--------------");
	  var strsjzse = "${dataStr}";//
  	//	alert(strsjzse);
  		var flag  = '1';
  		//alert(strsjzs1+"##########");
  		
	
	 var basePath = "<%=basePath%>";
	 var type = "${type}";//对应的审计导航ID
	 var id = "${workFlowDTO.id}";

	//根据id打开审计方法或附件详细   0审计方法  1附件 
	 function showPage(flag) {
		var searchBackUrl = "";
		if(flag==0){
			searchBackUrl = $("#sjInfoUrl").val();
			$('#showDetailWin').attr("title","法规详细");
		}else if(flag==1){
			searchBackUrl = $("#attachUrl").val();
			$('#showDetailWin').attr("title","附件详细");
		}else{
			searchBackUrl = "return false;";
		}
		$('#showDetailWin').window({   
			width:700,
			height:400,
			modal:true,
			collapsible:false,
			minimizable:false,
			maximizable:false,
			closable:true
		});
		var iframe_str = "<iframe height=\"100%\" width=\"100%\" scrolling=\"no\" frameborder=\"0\" src=\"" + searchBackUrl + "\"></iframe>";
		$('#showDetailWin').html(iframe_str);
	 }

	//选择审计方法
	 function addSjInfo(){
		 var url="<%=basePath%>system/workflow!getSjList.action";
    	 var _sfeatures ="dialogWidth=800px;dialogHeight=500px;top=0;left=0;toolbar=no;location=no;titlebar=no;menubar=no";
    	 var info=window.showModalDialog(url,"",_sfeatures);
    	 if(info!=null){
     	 	$("#sjInfoId").val(info.id);
     	 	$("#sjInfoName").html(info.name);
     	 	$("#sjInfoUrl").val(info.url);
    	 }
	}

	//打开文件选择窗口
	function selectFile() {			
		var width = "800";
		var url = "<%=basePath%>attach!selectFile.action?attach.attachId=${type}&type=1&nocache=" + new Date().getTime();
		var returnVal = window.showModalDialog(url, null, "center=yes;dialogWidth="+width+"px;dialogHeight=500px; edge:Raised; directories:no; localtion:no; menubar:no; status=yes; toolbar=yes;scroll:yes;help=no");
		if (returnVal != undefined && returnVal != null) {//如果用户选择了文件
			var id = returnVal.id;
			var name = returnVal.name;
			var url = "<%=basePath%>attach!showAtttachInfo.action?attach.attachId ="+id+"&type=0";
			$("#attachIds").val(id);
			$("#attachNames").html(name);
			$("#attachUrl").val(url);
		}
	}
	</script>
	<STYLE>
		v\:*{behavior:url(#default#VML);}
	</STYLE>

</head>

<body class="easyui-layout bodySelectNone" id="body" onselectstart="return false" onload=" initXml();">
	
	<div id="title" region="north" split="true" border="false" title="工具栏" class="titleTool">
		<div id="message" class="message"></div>	
		<img alt="加载"	title="加载" src="flow/images/download_page.png" onclick="graphUtils.loadXml();" class="buttonStyle"/>
	</div>
	
	<div id="leftContent"  split="true" title="图元区" class="leftContent" style="display: none">
		
		<div class="easyui-accordion overflowHidden" fit="true" border="false">
			
				<div title="基本图形" style="overflow:hidden;background-color:#c7dbfc;">
					
					<div id="baseLine2" style="position:absolute;left:10px;top:30px !important;top:10px">
		 				<div class="title">&nbsp;</div>
						<img id="backGroundImg" src="flow/images/line3.png" class="nodeStyle"/>
					</div>
			
					<div id="baseLine3" style="position:absolute;left:50px;top:30px !important;top:10px">
						<div class="title">&nbsp;</div>
						<img id="backGroundImg" src="flow/images/line2.png" class="nodeStyle"/>
					</div>
			
					<div id="baseLine1" style="position:absolute;left:90px;top:30px !important;top:10px">
						<div class="title">&nbsp;</div>
						<img id="backGroundImg" src="flow/images/line1.png" class="nodeStyle"/>
					</div>
			
					<div id="baseMode4" divType="mode" style="position:absolute;left:10px;top:70px !important;top:50px">
						<div class="title">&nbsp;</div>
						<img id="backGroundImg" src="flow/images/baseMode2.png" class="nodeStyle"/>
					</div>
			
					<div id="baseMode5" divType="mode" style="position:absolute;left:50px;top:70px !important;top:50px">
						<div class="title">&nbsp;</div>
						<img id="backGroundImg" src="flow/images/baseMode3.png" class="nodeStyle"/>
					</div>
			
					<div id="baseMode6" divType="mode" style="position:absolute;left:90px;top:70px !important;top:50px">
						<div class="title">&nbsp;</div>
						<img id="backGroundImg" src="flow/images/baseMode4.png" class="nodeStyle"/>
					</div>
					
					<div id="baseMode3" divType="mode" style="position:absolute;left:50px;top:106px !important;top:86px">
						<div class="title">&nbsp;</div>
						<img id="backGroundImg" src="flow/images/baseMode1.png" class="nodeStyle"/>
					</div>
			
					<div id="baseMode7" divType="mode" style="position:absolute;left:10px;top:106px !important;top:86px">
						<div class="title">&nbsp;</div>
						<img id="backGroundImg" src="flow/images/baseMode5.png" class="nodeStyle"/>
					</div>
			
					<div id="baseMode8" divType="mode" style="position:absolute;left:90px;top:106px !important;top:86px">
						<div class="title">&nbsp;</div>
						<img id="backGroundImg" src="flow/images/baseMode6.png" class="nodeStyle"/>
					</div>
			
					<div id="baseMode9" divType="mode" style="position:absolute;left:10px;top:140px !important;top:120px">
						<div class="title">&nbsp;</div>
						<img id="backGroundImg" src="flow/images/baseMode7.png" class="nodeStyle"/>
					</div>
			
					<div id="baseMode10" divType="mode" style="position:absolute;left:50px;top:140px !important;top:120px">
						<div class="title">&nbsp;</div>
						<img id="backGroundImg" src="flow/images/baseMode8.png" class="nodeStyle"/>
					</div>
			
					<div id="baseMode11" divType="mode" style="position:absolute;left:90px;top:140px !important;top:120px">
						<div class="title">&nbsp;</div>
						<img id="backGroundImg" src="flow/images/baseMode9.png" class="nodeStyle"/>
					</div>
			
					<div id="baseMode12" divType="mode" style="position:absolute;left:10px;top:175px !important;top:155px">
						<div class="title">&nbsp;</div>
						<img id="backGroundImg" src="flow/images/baseMode10.png" class="nodeStyle"/>
					</div>
			
					<div id="baseMode13" divType="mode" style="position:absolute;left:50px;top:175px !important;top:155px">
						<div class="title">&nbsp;</div>
						<img id="backGroundImg" src="flow/images/baseMode11.png" class="nodeStyle"/>
					</div>
			
					<div id="baseMode14" divType="mode" style="position:absolute;left:90px;top:175px !important;top:155px">
						<div class="title">&nbsp;</div>
						<img id="backGroundImg" src="flow/images/baseMode12.png" class="nodeStyle"/>
					</div>
			
					<div id="baseMode15" divType="mode" style="position:absolute;left:10px;top:210px !important;top:190px">
						<div class="title">&nbsp;</div>
						<img id="backGroundImg" src="flow/images/baseMode13.png" class="nodeStyle"/>
					</div>
			
					<div id="baseMode16" divType="mode" style="position:absolute;left:90px;top:210px !important;top:190px">
						<div class="title">&nbsp;</div>
						<img id="backGroundImg" src="flow/images/baseMode14.png" class="nodeStyle"/>
					</div>
			
					<div id="baseMode17" divType="mode" style="position:absolute;left:50px;top:210px !important;top:190px">
						<div class="title">&nbsp;</div>
						<img id="backGroundImg" src="flow/images/baseMode15.png" class="nodeStyle"/>
					</div>
			
				</div>
				
				<div title="流程图形" class="flowImag">
					
					<div id="baseMode36" divType="mode" style="float:left;margin-top:10px;margin-left:5px;margin-right:10px;">
						<div class="title">&nbsp;</div>
						<img id="backGroundImg" src="flow/images/liucheng1.png" class="nodeStyle"/>
					</div>
					
					<div id="baseMode37" divType="mode" style="float:left;margin-top:10px;margin-right:10px;">
						<div class="title">&nbsp;</div>
						<img id="backGroundImg" src="flow/images/liucheng2.png" class="nodeStyle"/>
					</div>
					
					<div id="baseMode38" divType="mode" style="float:left;margin-top:10px;">
						<div class="title">&nbsp;</div>
						<img id="backGroundImg" src="flow/images/liucheng3.png" class="nodeStyle"/>
					</div>
					
					
					<div id="baseMode39" divType="mode" style="float:left;margin-top:10px;margin-left:5px;margin-right:10px;">
						<div class="title">&nbsp;</div>
						<img id="backGroundImg" src="flow/images/liucheng4.png" class="nodeStyle"/>
					</div>
					
					<div id="baseMode40" divType="mode" style="float:left;margin-top:10px;margin-right:10px;">
						<div class="title">&nbsp;</div>
						<img id="backGroundImg" src="flow/images/liucheng5.png" class="nodeStyle"/>
					</div>
					
					<div id="baseMode41" divType="mode" style="float:left;margin-top:10px">
						<div class="title">&nbsp;</div>
						<img id="backGroundImg" src="flow/images/liucheng6.png" class="nodeStyle"/>
					</div>
					
				</div>
				
				<div title="财经图形" class="flowImag">
					
					<div id="baseMode18" divType="mode" style="float:left;margin-top:10px;margin-left:5px;margin-right:10px;">
						<div class="title">&nbsp;</div>
						<img id="backGroundImg" src="flow/images/jinrong7.png" class="nodeStyle"/>
					</div>
					
					<div id="baseMode19" divType="mode" style="float:left;margin-top:10px;margin-right:10px;">
						<div class="title">&nbsp;</div>
						<img id="backGroundImg" src="flow/images/jinrong8.png" class="nodeStyle"/>
					</div>
					
					<div id="baseMode20" divType="mode" style="float:left;margin-top:10px;">
						<div class="title">&nbsp;</div>
						<img id="backGroundImg" src="flow/images/jinrong9.png" class="nodeStyle"/>
					</div>
					
					<div id="baseMode21" divType="mode" style="float:left;margin-top:10px;margin-left:5px;margin-right:10px;">
						<div class="title">&nbsp;</div>
						<img id="backGroundImg" src="flow/images/jinrong10.png" class="nodeStyle"/>
					</div>
					
					<div id="baseMode22" divType="mode" style="float:left;margin-top:10px;margin-right:10px;">
						<div class="title">&nbsp;</div>
						<img id="backGroundImg" src="flow/images/jinrong5.png" class="nodeStyle"/>
					</div>
					
					<div id="baseMode23" divType="mode" style="float:left;margin-top:10px;">
						<div class="title">&nbsp;</div>
						<img id="backGroundImg" src="flow/images/jinrong6.png" class="nodeStyle"/>
					</div>
					
				</div>
				<div title="数据图形" class="flowImag">
					
					<div id="baseMode24" divType="mode" style="float:left;margin-top:10px;margin-left:5px;margin-right:10px;">
						<div class="title">&nbsp;</div>
						<img id="backGroundImg" src="flow/images/shuju1.png" class="nodeStyle"/>
					</div>
					
					<div id="baseMode25" divType="mode" style="float:left;margin-top:10px;margin-right:10px;">
						<div class="title">&nbsp;</div>
						<img id="backGroundImg" src="flow/images/shuju2.png" class="nodeStyle"/>
					</div>
					
					<div id="baseMode26" divType="mode" style="float:left;margin-top:10px;">
						<div class="title">&nbsp;</div>
						<img id="backGroundImg" src="flow/images/shuju3.png" class="nodeStyle"/>
					</div>
					
					
					<div id="baseMode27" divType="mode" style="float:left;margin-top:10px;margin-left:5px;margin-right:10px;">
						<div class="title">&nbsp;</div>
						<img id="backGroundImg" src="flow/images/shuju4.png" class="nodeStyle"/>
					</div>
					
					<div id="baseMode28" divType="mode" style="float:left;margin-top:10px;margin-right:10px;">
						<div class="title">&nbsp;</div>
						<img id="backGroundImg" src="flow/images/shuju5.png" class="nodeStyle"/>
					</div>
					
					<div id="baseMode29" divType="mode" style="float:left;margin-top:10px">
						<div class="title">&nbsp;</div>
						<img id="backGroundImg" src="flow/images/shuju6.png" class="nodeStyle"/>
					</div>

					
				</div>
				
				<div title="电信图形" class="flowImag">
					
					<div id="baseMode30" divType="mode" style="float:left;margin-top:10px;margin-left:5px;margin-right:10px;">
						<div class="title">&nbsp;</div>
						<img id="backGroundImg" src="flow/images/dianxin1.png" class="nodeStyle"/>
					</div>
					
					<div id="baseMode31" divType="mode" style="float:left;margin-top:10px;margin-right:10px;">
						<div class="title">&nbsp;</div>
						<img id="backGroundImg" src="flow/images/dianxin2.png" class="nodeStyle"/>
					</div>
					
					<div id="baseMode32" divType="mode" style="float:left;margin-top:10px;">
						<div class="title">&nbsp;</div>
						<img id="backGroundImg" src="flow/images/dianxin3.png" class="nodeStyle"/>
					</div>
					
					<div id="baseMode33" divType="mode" style="float:left;margin-top:10px;margin-left:5px;margin-right:10px;">
						<div class="title">&nbsp;</div>
						<img id="backGroundImg" src="flow/images/dianxin4.png" class="nodeStyle"/>
					</div>
					
					<div id="baseMode34" divType="mode" style="float:left;margin-top:10px;margin-right:10px;">
						<div class="title">&nbsp;</div>
						<img id="backGroundImg" src="flow/images/dianxin5.png" class="nodeStyle"/>
					</div>
					
					<div id="baseMode35" divType="mode" style="float:left;margin-top:10px">
						<div class="title">&nbsp;</div>
						<img id="backGroundImg" src="flow/images/dianxin6.png" class="nodeStyle"/>
					</div>
					
				</div>
				
			</div>
			
	</div>
		<div class="modeRightPixel" id="isPixel" ></div>
	<div region="center" title="浏览区" id="contextBody" class="mapContext" >
		
			<!-- Line右键菜单 -->
			<!-- 
			<div id="lineRightMenu" class="modeRight">
				
				<div class="modeRightTop"></div>
				<div class="modeRightDel" onmousemove="this.style.backgroundColor='#c5e7f6'" onclick="graphUtils.removeNode();" onmouseout="this.style.backgroundColor=''"><span class="menuSpan">删 除</span></div>
				<div class="modeRightPro" onmousemove="this.style.backgroundColor='#c5e7f6'" onclick="graphUtils.showLinePro();" onmouseout="this.style.backgroundColor=''"><span class="menuSpan">属 性</span></div>
				<div class="modeRightButtom"></div>
				
			</div>
			 -->		
			<!-- Mode右键菜单 -->
			<div id="rightMenu" class="modeRight">
				<div class="modeRightTop"></div>
				<!--  
				<div class="modeRightCopy" onmousemove="this.style.backgroundColor='#c5e7f6'" onclick="graphUtils.copyNode();" onmouseout="this.style.backgroundColor=''"><span class="menuSpan">复 制</span></div>
				<div class="modeRightDel" onmousemove="this.style.backgroundColor='#c5e7f6'" onclick="graphUtils.removeNode();" onmouseout="this.style.backgroundColor=''"><span class="menuSpan">删 除</span></div>
				<div class="modeRightPro" onmousemove="this.style.backgroundColor='#c5e7f6'" onclick="graphUtils.showModePro();" onmouseout="this.style.backgroundColor=''"><span class="menuSpan">属 性</span></div>
				<div class="modeRightButtom"></div>
				
				-->
			</div>
			
			<!-- PathBody右键菜单 -->
			<div id="pathBodyRightMenu" class="modeRight">
				<div class="modeRightTop"></div>
				<!--  
				<div class="modeRightPixel" id="isPixel" onclick="global.baseTool.changStyle(this);" onmousemove="this.style.backgroundColor='#c5e7f6'" onmouseout="this.style.backgroundColor=''"><span class="menuSpan">Pixel</span></div>
				<div class="modeRightLeft" onclick="graphUtils.alignLeft();" onmousemove="this.style.backgroundColor='#c5e7f6'" onmouseout="this.style.backgroundColor=''"><span class="menuSpan">左对齐</span></div>
				<div class="modeRightCenter" onclick="graphUtils.verticalCenter();" onmousemove="this.style.backgroundColor='#c5e7f6'" onmouseout="this.style.backgroundColor=''"><span class="menuSpan">垂直居中</span></div>
				<div class="modeRightRight" onclick="graphUtils.alignRight();" onmousemove="this.style.backgroundColor='#c5e7f6'" onmouseout="this.style.backgroundColor=''"><span class="menuSpan">右对齐</span></div>
				<div class="modeRightHead" onclick="graphUtils.alignTop();" onmousemove="this.style.backgroundColor='#c5e7f6'" onmouseout="this.style.backgroundColor=''"><span class="menuSpan">顶部对齐</span></div>
				<div class="modeRightMiddle" onclick="graphUtils.horizontalCenter();" onmousemove="this.style.backgroundColor='#c5e7f6'" onmouseout="this.style.backgroundColor=''"><span class="menuSpan">水平居中</span></div>
				<div class="modeRightFoot" onclick="graphUtils.bottomAlignment();" onmousemove="this.style.backgroundColor='#c5e7f6'" onmouseout="this.style.backgroundColor=''"><span class="menuSpan">底端对齐</span></div>
				<div class="modeRightButtom"></div>
				-->
				
				
			</div>
			
		 	<div id="topCross"></div>
      <div id="leftCross"></div>
	
	</div>

	<div  split="true" title="历史操作" class="history" style="display: none">
		<div id="historyMessage" class="historyMessage"></div>
	</div>
	
	<div region="south"  split="true" title="辅助区" class="auxiliaryArea" >
		<!-- 小地图 -->	
		<div id="smallMap"></div> 
		
		<div id="mainControl">
			
			<div id="tab" class="control">
	
				<h3 id="h1" class="hclass" onClick="hOnMouseOver(this, '1')">
					&nbsp;<img src="flow/images/img1.png" style="vertical-align:middle;" />&nbsp;&nbsp;节点名称
				</h3>
					<div id="tab1" class="htabup">
						
						<table cellpadding="0" cellspacing="0">
							<tr>
									<td style="text-align:center;height:25px;">
										节点标题
									</td>									
									<td colspan="7">
										<input type="text" id="inputTitle" class="inputComm" style="width:300px;"  readonly="readonly"/>
									</td>
							</tr>
							

								<tr>
									<td style="width:70px;text-align:center;">
										处理说明
									</td>									
									<td colspan="7">
										<textarea rows="3" id="modeContent" class="contextArea"  readonly="readonly"></textarea>
									</td>
								</tr>
								
						</table>	

					</div>
					
			<h3 id="h3" class="hclass" onClick="hOnMouseOver(this, '3')">
				&nbsp;<img src="flow/images/img3.png" style="vertical-align:middle;" />&nbsp;&nbsp;节点详细</h3>
					<div id="tab3" class="htab">
						<table cellpadding="0" cellspacing="0">
								<!-- 背景图隐藏 ，不需要-->
								<tr style="display: none;">
									<td>
										背景图
									</td>
									<td>
										<input type="text" id="inputImgSrc" class="inputComm" style="width:500px;" />
									</td>
								</tr>
								<tr>
									<td>引入审计方法  :  </td>
									<td>
										<input type="hidden" name="" id="sjInfoUrl" value=""/>
										<input type="hidden" name="" id="sjInfoId" value=""/>
										
										<a href="javascript:showPage(0);" id="sjInfoName"></a>
									</td>
								</tr>
								
								<tr></tr>
								<tr>
									<td>&nbsp;&nbsp;&nbsp;附&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;件  : </td>
									<td>
										<a href="javascript:showPage(1);" id="attachNames"></a>
										<input type="hidden" value="" id="attachIds" name=""/>
										<input type="hidden" value="" id="attachUrl" name=""/>
									</td>
								</tr>

						</table>
							
				</div>
				
			 <h3 id="h2" class="hclass" onClick="hOnMouseOver(this, '2')" style="display: none;">
			 	&nbsp;<img src="flow/images/img2.png" style="vertical-align:middle;" />&nbsp;&nbsp;模型属性
			 	</h3>
					<div id="tab2" class="htab" style="display: none;">
						<table cellpadding="0" cellspacing="0">
							<tr>
									<td style="width:70px;height:25px;text-align:center;">
										宽
									</td>									
									<td>
										<input type="text" id="inputWidth" class="inputComm" style="width:50px;" readonly="readonly"/>
									</td>
									<td style="width:70px;text-align:center;">
										高
									</td>									
									<td>
										<input type="text" id="inputHeight" class="inputComm" style="width:50px;" readonly="readonly" />
									</td>
									
							</tr>
							<tr>
									<td style="text-align:center;">
										上边距
									</td>									
									<td>
										<input type="text" id="inputTop" class="inputComm" style="width:50px;"  readonly="readonly"/>
									</td>
									<td style="text-align:center;">
										左边距
									</td>									
									<td>
										<input type="text" id="inputLeft" class="inputComm" style="width:50px;" readonly="readonly"/>
									</td>
							</tr>
								
						</table>	
					</div>
					<%--
					<h3 id="h3" class="hclass" onClick="hOnMouseOver(this, '3')">
					&nbsp;<img src="flow/images/img3.png" style="vertical-align:middle;" />&nbsp;&nbsp;模型背景</h3>
					<div id="tab3" class="htab">
							
						<table cellpadding="0" cellspacing="0">
							
								<tr>
									
									<td style="width:70px;text-align:center;">
										背景图
									</td>
									
									<td colspan="6">
										<input type="text" id="inputImgSrc" class="inputComm" style="width:500px;" />
									</td>
									
								</tr>

						</table>
							
					</div>
					 --%>
					

			</div>
			
		</div>
		<div id="show" class="show">
			
			<div id="nullCount" class="showItem"></div>
			<div id="lineCount" class="showItem"></div>
			<div id="modeCount" class="showItem"></div>
			<div id="contextCount" class="showItem"></div>
			
		</div>
	
	</div>
	
	<!-- 移动时的图象 -->
	<div id="moveBaseMode" class="moveBaseMode">
		<img id="moveBaseModeImg"  src="flow/images/Favourite.png" class="nodeStyle"/>
	</div>
	
	<div id="prop" style="visibility: hidden;">
				Dialog Content.  
	</div>
	
	<div id="showDetailWin" iconCls="icon-show" title="" style="width: 0;height: 0"></div> 
	
	</body>
	
	<script src="flow/js/jquery-1.6.min.js" type="text/javascript"></script>
	<script src="flow/js/jquery.easyui.min.js" type="text/javascript"></script>
	<script src="flow/js/strawberry.min.js" type="text/javascript" language="javascript"></script>
	
	<script>
		var mainControl = $id("mainControl");
		mainControl.style.width = (document.body.offsetWidth - 412) + "px";
		var global = com.xjwgraph.Global;
  		var bgImg = "url(flow/images/bg.gif)";
  		var backColor = "#e0ecff";
  	
  		
  		function hOnMouseOver(tagObj, index) {
				
				var h1 = $id("h1");
				var h2 = $id("h2");
				var h3 = $id("h3");
				
				var tab1 = $id("tab1");
				var tab2 = $id("tab2");
				var tab3 = $id("tab3");
				
				var setHClass = function (obj, colorValue, indexValue) {
				
					obj.style.background = colorValue;
					obj.style.zIndex = indexValue;
					
				}
				
				var setTagClass = function (obj, className) {
					
					obj.setAttribute("class", className);
					obj.setAttribute("className", className);

				}
				
				setHClass(h1, bgImg, "1");
				setHClass(h2, bgImg, "1");
				setHClass(h3, bgImg, "1");
				
				setTagClass(tab1, "htab");
				setTagClass(tab2, "htab");
				setTagClass(tab3, "htab");
				
				setHClass(tagObj, backColor,"3");
				setTagClass($id("tab" + index), "htabup");
				
			}
  		
			jQuery(document).ready(function () {
				
				$id("h1").style.background = "#e0ecff";
				$id("h1").style.zIndex = "3";
				graphUtils = com.xjwgraph.Utils.create({
					
					contextBody : 'contextBody',
					width : 1600,
					height : 1000,
					smallMap : 'smallMap',
					mainControl : 'mainControl',
					historyMessage : 'historyMessage',
					prop : 'prop'
				
				});
				
				graphUtils.nodeDrag($id("baseLine1"), true, 1);
				graphUtils.nodeDrag($id("baseLine2"), true, 2);
				graphUtils.nodeDrag($id("baseLine3"), true, 3);
				
				var modes = jQuery("[divType='mode']");
				var modeLength = modes.length;
				
				for (var i = 0; i < modeLength; i++) {
					graphUtils.nodeDrag(modes[i]);
				}
				
				document.body.onclick = function () {
					
					if (!stopEvent) {
						global.modeTool.clear();
					} 
					
				}
				
				function countModule() {
				
					stopEvent = false;
					
					var lineCount = global.lineMap.size();
					var modeCount = global.modeMap.size();
					var contextCount = global.baseTool.contextMap.size();
					
					$id("lineCount").innerHTML = "线总数:" + lineCount;
					$id("modeCount").innerHTML = "模型数:" + modeCount;
				
					$id("contextCount").innerHTML = "区域数:" + contextCount;
					
				}
				
				countModule();
				
			//	document.onclick = countModule;
				//document.onkeydown = KeyDown;
				
			});

			function initXml(){
				var strXml = "${workFlowDTO.textXml}";
				if(strXml !=null && strXml!= "undefined" && strXml!= ""){
					strXml = strXml.replaceAll("&amp;", "&");
					strXml = strXml.replaceAll("&apos;", "'");
					strXml = strXml.replaceAll("&quot;", '"');
					strXml = strXml.replaceAll("&gt;", ">");
					strXml = strXml.replaceAll("&lt;", "<");
					Utils.loadTextXml(strXml);
				}
			}
			
	</script>
	


	<script type="text/javascript">
/**
	$(window).keydown(function(event){
		alert(event.keyCode);
  switch(event.keyCode) {
  
   default :
   alert("你好！只能浏览内容！");
   return  false;
   break;
   }
  
}); 
*/

	$(document).keydown(function(event){
		
     switch(event.keyCode) {
  
	   default :
	 //  alert("只能浏览内容！");
	   return  false;
	   break;
   	}
  
}); 
	
	</script>
	
</html>