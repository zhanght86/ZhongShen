﻿<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/view/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	    <base href="<%=basePath%>" target="_self"/>
		<title>选择部门</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" type="text/css" href='js/tree/dtree/dtree.css' />
		<link rel="stylesheet" type="text/css" href='css/style.css' />
		<script language="javascript" src='js/tree/dtree/dtree.js'></script>
		<script language="javascript" src='js/showdialog.js'></script>
		<script type="text/javascript">
		//此方法可解决在IE5，6，7下动态创建input表单时，无法给name属性赋值的情况
		function createElement(mywindow,type, name) {      
			var element = null;				  
			try {      
				//先是在使用IE的情况下创建元素，如果失败则使用标准方法   
				element = mywindow.document.createElement('<'+type+' name="'+name+'">');      
			} catch (e) {      
				//在使用非IE的情况下会出现异常       
			}      
			if (!element) {//如果没有成功创建元素      
				element = mywindow.document.createElement(type);      
				element.name = name;      
			}
			return element;
		}
		
			//在用户选择列表中增加一行单元格，包含选择的用户的uid,姓名，以及删除当前行的按钮
		function addEmpl(emplId, emplName) {
			if (isExist(emplId)) {//如果当前用户已经被选择
				alert("当前用户已存在于用户选择列表中");
				return;
			}
			
			var mxwin = window.parent.emplW;

			var tb_obj = mxwin.document.getElementById("empllist");
			
			var tr_obj = mxwin.document.createElement("tr");
			
			var td_obj = mxwin.document.createElement("td");
			
			var td_obj1 = mxwin.document.createElement("td");
			
			
			var hiid_obj = createElement(mxwin,"input","empids");
			
			var hiname_obj = createElement(mxwin,"input","empnames");
			var btn_obj = createElement(mxwin,"input","remove");
			hiid_obj.type = "hidden";
			hiid_obj.value = emplId;

			hiname_obj.type = "text";
			hiname_obj.value = emplName;
			hiname_obj.style.border = "0px";
			hiname_obj.style.backgroundColor = "#FFFFFF";
			hiname_obj.readOnly = "true";
			hiname_obj.size = "4";
			
			btn_obj.id="button-submis";
			btn_obj.type = "button";
			btn_obj.value = "删除";
			btn_obj.onclick = function () {
				tb_obj.removeChild(tr_obj);
			}

			td_obj.appendChild(hiid_obj);
			td_obj.appendChild(hiname_obj);
			td_obj1.appendChild(btn_obj);
			
			tr_obj.appendChild(td_obj);
			tr_obj.appendChild(td_obj1);
				
			tb_obj.appendChild(tr_obj);
		}
			//检测用户列表中是否已存在当前用户
		function isExist(emplId) {
			var mxwin = window.parent.emplW;

			var t_obj = mxwin.document.getElementsByName("empids");
			for ( var i = 0; i < t_obj.length; i++ ) {
				if (emplId == t_obj[i].value) {
					return true;
				}
			}
			return false;
		}
		</script>
	</head>
	<body bgcolor="#FFFFFF">
	<table width="99%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC">
	    <tr class="title_style">
	      <td colspan="8" style="text-align:left">
	      	<img src="images/group1.png" width="20" height="20" align="absmiddle" />点击部门名称进行选择
	      </td>
	    </tr>
    </table>
		<script type="text/javascript">
		 a = new dTree('a');
		 a.config.folderLinks=true;
		 a.add("00000000","-1","组织机构","javascript:javascript:getDataC('00000000','组织机构');","","");
		<c:forEach items="${pages.list}" var="entry">
	   	  	a.add("${entry.jmcode}","${entry.jmssjg}","${entry.jmname}","javascript:addEmpl('${entry.jmcode}','${entry.jmname}');","","");
	    </c:forEach>
	    document.write(a);
		</script>
	</body>
</html>
