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
	 var basePath = "<%=basePath%>";
	 var type = "${type}";//对应的审计导航ID
	 var id = "${workFlowDTO.id}";
     var strsjzse = "${dataStr}";
     var flag  = '0';
	//根据id打开审计方法或附件详细   0审计方法  1附件 
	 function showPage(flag) {
		var searchBackUrl = "";
		if(flag==0){
			searchBackUrl = $("#sjInfoUrl").val();
		}else if(flag==1){
			searchBackUrl = $("#attachUrl").val();
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
		var iframe_str = "<iframe height=\"100%\" width=\"100%\" scrolling=\"auto\" frameborder=\"0\" src=\"" + searchBackUrl + "\"></iframe>";
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
		var url = "<%=basePath%>attach!selectFile.action?attach.attachId=${type}&type=1&single=yes&nocache=" + new Date().getTime();
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

	//根据guid删除其所指定的文件
	function deleteFile(attId) {
		attachSJZS.delAttachJS(attId, function(data) {
			if (true == data) {
				$("#attachNames").html("");
				$("#delAttImg").html("");
			}
		});
	}

	function addDelImg(){
		alert("enter");
		var attId = $("#attachNames").html();
		if(attId!=null&&attId!=" "&&attId!=""){
			$("#delAttImg").html('<img src="images/del.gif" onclick="javascript:deleteFile('+attId+')"');
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
		<img alt="合并" title="合并" src="flow/images/1.png" onclick="graphUtils.toMerge()" class="buttonStyle" />
		<img alt="拆分" title="拆分" src="flow/images/2.png" onclick="graphUtils.toSplit()" class="buttonStyle"/>
		<img alt="置顶"	title="置顶" src="flow/images/4.png" onclick="graphUtils.toTop()" class="buttonStyle"/>
		<img alt="置底"	title="置底" src="flow/images/3.png" onclick="graphUtils.toBottom()" class="buttonStyle"/>
		<img alt="预览"	title="预览" src="flow/images/zoom.png" onclick="graphUtils.printView()" class="buttonStyle"/>
		<%--<img alt="打印" title="打印" src="flow/images/printer.png"  class="buttonStyle"/>--%>
		<img alt="撤销"	title="撤销" src="flow/images/back.png" onclick="graphUtils.undo();" class="buttonStyle"/>
		<img alt="重做"	title="重做" src="flow/images/next.png" onclick="graphUtils.redo();" class="buttonStyle"/>
		<img alt="保存"	title="保存" src="flow/images/save.png" onclick="graphUtils.saveXml();" class="buttonStyle"/>
		<img alt="加载"	title="加载" src="flow/images/download_page.png" onclick="graphUtils.loadXml();" class="buttonStyle"/>
		<img alt="清空"	title="清空" src="flow/images/trash.png" onclick="graphUtils.clearHtml();" class="buttonStyle"/>
		<img alt="复制"	title="复制" src="flow/images/copy.png" onclick="graphUtils.copyNode();" class="buttonStyle"/>
		<img alt="删除"	title="删除" src="flow/images/delete.png" onclick="graphUtils.removeNode();" class="buttonStyle"/>
		<img alt="左对齐"	title="左对齐" src="flow/images/toLeft.png" onclick="graphUtils.alignLeft();" class="buttonStyle"/>
		<img alt="垂直居中"	title="垂直居中" src="flow/images/toMiddleWidth.png" onclick="graphUtils.verticalCenter();" class="buttonStyle"/>
		<img alt="右对齐"	title="右对齐" src="flow/images/toRight.png" onclick="graphUtils.alignRight();" class="buttonStyle"/>
		<img alt="顶对齐"	title="顶对齐" src="flow/images/toTop.png" onclick="graphUtils.alignTop();" class="buttonStyle"/>
		<img alt="水平居中"	title="水平居中" src="flow/images/toMiddleHeight.png" onclick="graphUtils.horizontalCenter();" class="buttonStyle"/>
		<img alt="底对齐"	title="底对齐" src="flow/images/toBottom.png" onclick="graphUtils.bottomAlignment();" class="buttonStyle"/>
		
	</div>
	
	<div id="leftContent" region="west" split="true" title="图元区" class="leftContent">
		
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
				<!--  
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
				-->
			</div>
			
	</div>
	
	<div region="center" title="绘图区" id="contextBody" class="mapContext" >
			
			<!-- Line右键菜单 -->
			<div id="lineRightMenu" class="modeRight">
				<div class="modeRightTop"></div>
				<div class="modeRightDel" onmousemove="this.style.backgroundColor='#c5e7f6'" onclick="graphUtils.removeNode();" onmouseout="this.style.backgroundColor=''"><span class="menuSpan">删 除</span></div>
				<div class="modeRightPro" onmousemove="this.style.backgroundColor='#c5e7f6'" onclick="graphUtils.showLinePro();" onmouseout="this.style.backgroundColor=''"><span class="menuSpan">属 性</span></div>
				<div class="modeRightButtom"></div>
			</div>
					
			<!-- Mode右键菜单 -->
			<div id="rightMenu" class="modeRight">
				<div class="modeRightTop"></div>
				<div class="modeRightCopy" onmousemove="this.style.backgroundColor='#c5e7f6'" onclick="graphUtils.copyNode();" onmouseout="this.style.backgroundColor=''"><span class="menuSpan">复 制</span></div>
				<div class="modeRightDel" onmousemove="this.style.backgroundColor='#c5e7f6'" onclick="graphUtils.removeNode();" onmouseout="this.style.backgroundColor=''"><span class="menuSpan">删 除</span></div>
				<div class="modeRightPro" onmousemove="this.style.backgroundColor='#c5e7f6'" onclick="graphUtils.showModePro();" onmouseout="this.style.backgroundColor=''"><span class="menuSpan">属 性</span></div>
				<div class="modeRightButtom"></div>
			</div>
			
			<!-- PathBody右键菜单 -->
			<div id="pathBodyRightMenu" class="modeRight">
				<div class="modeRightTop"></div>
				<div class="modeRightPixel" id="isPixel" onclick="global.baseTool.changStyle(this);" onmousemove="this.style.backgroundColor='#c5e7f6'" onmouseout="this.style.backgroundColor=''"><span class="menuSpan">Pixel</span></div>
				<div class="modeRightLeft" onclick="graphUtils.alignLeft();" onmousemove="this.style.backgroundColor='#c5e7f6'" onmouseout="this.style.backgroundColor=''"><span class="menuSpan">左对齐</span></div>
				<div class="modeRightCenter" onclick="graphUtils.verticalCenter();" onmousemove="this.style.backgroundColor='#c5e7f6'" onmouseout="this.style.backgroundColor=''"><span class="menuSpan">垂直居中</span></div>
				<div class="modeRightRight" onclick="graphUtils.alignRight();" onmousemove="this.style.backgroundColor='#c5e7f6'" onmouseout="this.style.backgroundColor=''"><span class="menuSpan">右对齐</span></div>
				<div class="modeRightHead" onclick="graphUtils.alignTop();" onmousemove="this.style.backgroundColor='#c5e7f6'" onmouseout="this.style.backgroundColor=''"><span class="menuSpan">顶部对齐</span></div>
				<div class="modeRightMiddle" onclick="graphUtils.horizontalCenter();" onmousemove="this.style.backgroundColor='#c5e7f6'" onmouseout="this.style.backgroundColor=''"><span class="menuSpan">水平居中</span></div>
				<div class="modeRightFoot" onclick="graphUtils.bottomAlignment();" onmousemove="this.style.backgroundColor='#c5e7f6'" onmouseout="this.style.backgroundColor=''"><span class="menuSpan">底端对齐</span></div>
				<div class="modeRightButtom"></div>
			</div>
			
		 	<div id="topCross"></div>
      <div id="leftCross"></div>
	
	</div>

	<div region="east" split="true" title="历史操作" class="history">
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
									<td style="text-align:center;height:25px;width:80px;white-space: nowrap;">
									<p style="width:80px;white-space: nowrap;">
										节点标题</p>
									</td>									
									<td colspan="7"style="width:200px;">
										<input type="text" id="inputTitle" class="inputComm" style="width:90%" />
									</td>
							</tr>
							

								<tr>
									<td style="width:80px;text-align:center;">
										<p style="width:80px;white-space: nowrap;">节点说明</p>
									</td>									
									<td colspan="7">
										<textarea rows="3" id="modeContent" class="contextArea" ></textarea>
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
									<td>选择引入审计方法:</td>
									<td>
										<input type="hidden" name="" id="sjInfoUrl" value=""/>
										<input type="hidden" name="" id="sjInfoId" value=""/>
										<input type="button" value="" class="selectBut" onclick="addSjInfo()">
									</td>
								</tr>
								<tr>
										
										
									<td>引入审计方法:&nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td><a href="javascript:showPage(0);" id="sjInfoName"></a>
									</td>
								</tr>
								<tr>
									<td>选择附件:</td>
									<td>
										<input type="button" value="&nbsp;" class="selectBut" onclick="javascript:selectFile()"/>
									</td>
								</tr>
								<tr>
									<td>附件:</td>
									<td>
										<a href="javascript:showPage(1);" id="attachNames"></a>
										<div id="delAttImg"></div>
										<input type="hidden" value="" id="attachIds" name="" onchange="javascript:addDelImg();"/>
										<input type="hidden" value="" id="attachUrl" name=""/>
									</td>
								</tr>

						</table>
							
				</div>
				
			 <h3 id="h2" class="hclass" onClick="hOnMouseOver(this, '2')">
			 	&nbsp;<img src="flow/images/img2.png" style="vertical-align:middle;" />&nbsp;&nbsp;节点属性
			 	</h3>
					<div id="tab2" class="htab">
						<table cellpadding="0" cellspacing="0">
							<tr>
									<td style="width:70px;height:25px;text-align:center;">
										宽
									</td>									
									<td>
										<input type="text" id="inputWidth" class="inputComm" style="width:50px;"/>
									</td>
									<td style="width:70px;text-align:center;">
										高
									</td>									
									<td>
										<input type="text" id="inputHeight" class="inputComm" style="width:50px;" />
									</td>
									
							</tr>
							<tr>
									<td style="text-align:center;">
										上边距
									</td>									
									<td>
										<input type="text" id="inputTop" class="inputComm" style="width:50px;" />
									</td>
									<td style="text-align:center;">
										左边距
									</td>									
									<td>
										<input type="text" id="inputLeft" class="inputComm" style="width:50px;" ond/>
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
	
	<div id="showDetailWin" iconCls="icon-show" title="详细信息" style="width: 0;height: 0"></div> 
	
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
				
				document.onclick = countModule;
				document.onkeydown = KeyDown;
				
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
</html>