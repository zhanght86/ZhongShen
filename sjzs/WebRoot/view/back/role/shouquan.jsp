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
    <title>角色列表</title>
	<link rel="stylesheet" type="text/css" href='js/tree/dhtmltree/css/dhtmlXTree.css"/>'>
	<style type="text/css">
	<!--
	body {background-color: #F6F6F6;}	
	.STYLE1 {line-height: 25px;font-size: 14px;color: #FFFFFF;}
	.title_style td{
		height:29px;
		background:url(images/ny_titlebg1.jpg) repeat-x;
		text-align:center;
		color:#03467B;
		font-size:12px;
		font-weight:bold;
	}
	
	-->
	</style>
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script src="js/tree/dhtmltree/js/dhtmlXCommon.js"></script>
	<script src="js/tree/dhtmltree/js/dhtmlXTree.js"></script>
	<script type="text/javascript">
		function doSubmit() {
			var selectcodes = tree.getAllCheckedBranches();
			//window.location.href="<%=basePath%>system/role!authorize.action?role.roleId=${role.roleId}&rpcodes=" + mids.value;
			var rpcode = document.getElementById("pcodes");
			rpcode.value = selectcodes;
			document.forms[0].submit();
		}

		//设置节点的父节点及其父节点被选中
		function setParentChecked(id) {
			var tree_parentId = tree.getParentId(id);
			if (0 != tree_parentId) {
				var statuId = tree.isItemChecked(tree_parentId);
				if (0 == statuId) {
					tree.setCheck(tree_parentId,1);
				}
				setParentChecked(tree_parentId);
			}
		}

		//当改变节点状态到未选中时，如果其父节点没有其它子节点处于选中状态，则设置父节点处于未选中状态
		function setParentunChecked(id) {
			var tree_parentId = tree.getParentId(id);
			var nodeChildIds = tree.getSubItems(tree_parentId);
			var childIdsArr = nodeChildIds.split(",");
			var isexist = false;
			//当前节点的兄弟节点是否有处于选中状态的节点
			for(var i = 0; i < childIdsArr.length; i++) {
				if (tree.isItemChecked(childIdsArr[i])) {
					isexist = true;
					break;
				}				
			}
			if (!isexist) {
				//如果当前节点已经是顶层节点,则停止递归调用
				if ( 0 != tree_parentId) {
					tree.setCheck(tree_parentId,0);
					setParentunChecked(tree_parentId);
				}
			}
		}

		//处理节点的选中与取消选中事件
		function myClickHandler(id,state) {
			var nodeParentId = tree.getParentId(id);
			var type;
			$.get("system/power!getPowerType.action?powerId="+id+"&nocache=" + new Date().getTime(),function(data){
				type=data;
				if (state) {//如果是选中节点
				   if(type==0){
						if (0 != nodeParentId) {//如果当前节点有父节点，则将其父节点选中
							setParentChecked(id);//则将所有的父节点设置为选中状态
						}
						var nodeChildIds = tree.getSubItems(id);
						if ("" != nodeChildIds) {//如果当前节点有子节点
							tree.setSubChecked(id,1);//将当前节点及其子节点选中				
						}
				   }else if(type==1){
				   	    return;
				   }
				} else {//如果是取消选中
					//var nodeParentId = tree.getParentId(id);
					if(type==0){
						//如果当前节点的父节点是根节点，则将其所有的子节点置为未选中状态
						if (0 == nodeParentId) {
							tree.setSubChecked(id,0);
						} else {
							//将其所有子节点置为未选中状态
							tree.setSubChecked(id,0);
							//将当前节点的父节点置为未选中
							setParentunChecked(id);
						}
						}else{
							return;
						}
				}			
			},"text");
		}
	</script>	
  </head>
	<body>
	<div align="center" >
		<form action="system/role!authorize.action" method="post">
			<input type="hidden" name="role.roleId" value="${role.roleId}">
			<input type="hidden" name="rpcodes" value="" id="pcodes">
			<input type="hidden" value="角色授权" name="closePage"/>
			<input type="hidden" value="角色管理" name="refreshPage"/>
		</form>
	  <table width="99%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC" style="border: 2px solid #93D3DF">
	    <tr class="title_style">
	      <td colspan="8" style="text-align:left">
	      	<img src="images/group1.png" width="20" height="20" align="absmiddle" />为角色授权
	      </td>
	    </tr>
	    <tr class="row_bg">
	      <td colspan="8">
	      	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="nonborder2">
		        <tr>
		          <td bgcolor="#CCCCCC" style="padding-top: 5px;">
						<table width="40%" border="0" cellspacing="0"
							cellpadding="0" bgcolor="#CCCCCC">
							<tr background="#CCCCCC">
								<td>
									&nbsp;
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
									<input onClick="doSubmit();" type="image" src="images/ico01_03.gif">
								</td>
							</tr>
						</table>		          	
		          </td>
		        </tr>
		       <tr>
		       	<td>
					<div id="treeboxbox_tree"></div>
					<script>
						//创建树的对象
						tree=new dhtmlXTreeObject("treeboxbox_tree","100%","100%",0);
						//设置树节点图片路径
						tree.setImagePath("<%=basePath%>js/tree/dhtmltree/imgs/");
						//设置生成树有复选框
						tree.enableCheckBoxes(1);
						//设置节点的选中取消处理函数
						tree.setOnCheckHandler(myClickHandler);
						//从XML字符串加载树
						tree.loadXML("system/createXML.action?type=role&role.roleId=${role.roleId}&role.roleType=${role.roleType}&tos=" + new Date().getTime());
					</script>
					<input type="hidden" id="mids" name="mids" value="">						       		
		       	</td>
		       </tr>
		       <tr>
		          <td bgcolor="#CCCCCC" style="padding-top: 5px;">
						<table width="40%" border="0" cellspacing="0"
							cellpadding="0" bgcolor="#CCCCCC">
							<tr background="#CCCCCC">
								<td>
									&nbsp;
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
									<input onClick="doSubmit();" type="image" src="images/ico01_03.gif">
								</td>
							</tr>
						</table>		          	
		          </td>
		        </tr>		        
	      </table>
	     </td>
	    </tr>
	  </table>
	</div>	
	</body>
</html>

