<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/view/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>" target="_self">
    <title>选择部门/人员</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script language="javascript" src='js/jquery-1.4.4.min.js'></script>
	<link rel="stylesheet" type="text/css" href="js/tree/dtree/dtree.css" />
	<link rel="stylesheet" type="text/css" href="js/org.css" />
	<script language="javascript" src='js/tree/dtree/dtree.js'></script>
	<script language="javascript" src='js/json.js'></script>
	<script language="javascript" src='js/showdialog.js'></script>  
	<link rel="stylesheet" type="text/css" href='css/style.css' />
	<script type="text/javascript">
		function addCustomer(depname,depCode) {var $addCu = $("#selectcustomers").get(0);var option = createOption(depname,depCode);if(checkIsExixts(depCode)){alert("机构'"+depname+"'在已选择机构中已经存在，不能选择重复的机构！");return ;}else{$addCu.options.add(option);}}function checkIsExixts(id){var flag = false;var $selectedCustomers = $("#selectcustomers").get(0);for (var i = 0; i < $selectedCustomers.options.length; i++) {var curOption = $selectedCustomers.options[i];if(curOption.value==id){flag = true;break;}}return flag;}function addAllCustomer() {var $addCu = $("#selectcustomers").get(0);var $sel1 = $("#customers").get(0);for(var i= 0; i < $sel1.options.length;i++) {var option = createOption($sel1.options[i].text, $sel1.options[i].value);if(checkIsExixts( $sel1.options[i].value)){alert("用户'"+$sel1.options[i].text+"'在已选择用户中已经存在，不能选择重复的用户！");return ;}else{$addCu.options.add(option);}}}
		function delAllCustomer(){$("#selectcustomers").empty();}
		function removeOptionSelected (){var $addCu = $("#selectcustomers").get(0);for(var i= $addCu.options.length -1; i >= 0;i--) {if ($addCu.options[i].selected) {$addCu.remove(i);}}}function parseData(data){  var $addCu = $("#customers").get(0);$addCu.length = 0;for (var key in data){$addCu.options.add(new Option(data[key].uname,data[key].uid));}}
	    function createOption(optionText, optionValue) {var option = new Option(optionText,optionValue)return option;}function addValueToArray(arryObj, val) {var length = arryObj.length;arryObj[length] = val;}function selectOK() {var customerIds = new Array();var customerNames = new Array();var $selectedCustomers = $("#selectcustomers").get(0);var cookieStr = "";for (var i = 0; i < $selectedCustomers.options.length; i++) {var curOption = $selectedCustomers.options[i];addValueToArray(customerIds, curOption.value);addValueToArray(customerNames, curOption.text);}var returnVal = new Array(customerIds,customerNames);window.parent.returnValue = returnVal;window.close();}
		function toClose(){window.parent.returnValue = null;window.close();}

	</script>
  </head>
  <body bgcolor="#FFFFFF">
	<div style="width:100%;margin-top:10px;margin-left:20px;border:1px solid #E1F3FF;">
		<div class="orgTree">
			<div><b><img src="images/group1.png" width="20" height="20" align="absmiddle" />&nbsp;&nbsp;选择部门</b></div>
			<div id="orgContainer">
				<script type="text/javascript">
					 a = new dTree('a');
					 a.config.folderLinks=true;
					 a.add("00000000","-1","组织机构","javascript:javascript:getDataC('00000000','组织机构',0);","","");
					<c:forEach items="${page.list}" var="entry">
				    	  a.add("${entry.jmcode}","${entry.jmssjg}","${entry.jmname}","javascript:addCustomer('${entry.jmname}','${entry.jmcode}');","","");
					</c:forEach>    
				    document.write(a);
				</script>
				
			</div>
		</div>

		<div class="showSelect">
			<div><b><img src="images/group1.png" width="20" height="20" align="absmiddle" />&nbsp;&nbsp;已选择机构</b></div>
			<div id="selectContainer">
				<select id="selectcustomers" multiple="multiple">
					
				</select>
				<span style="text-align:center;width=100%">
					<input type="button" value="删　　除" onClick="removeOptionSelected()">
					<input type="button"  value="删除全部" onclick="delAllCustomer()"/>
				</span>
			</div>
		</div>
		<div class="clear"></div>
		<div class="selectok"><input type="button" value="确定选择" onclick="selectOK()"/> &nbsp; <input type="button" value="关&nbsp;&nbsp;&nbsp;&nbsp;闭" onclick="toClose();"/></div>
	</div>
  </body>
</html>
