<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.hnzskj.common.Constant" %>
<%@ include file="/view/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("com", Constant.COMPANY);
request.setAttribute("department", Constant.DEPARTMENT);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>" target="_self"/>
    <title>
          	 添加菜单：
    </title>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="css/style.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="js/dataCheck.js"></script>
	<script language="javascript" src='js/showdialog.js'></script>
	<script type="text/javascript" src="js/zh/org.js"></script>
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	
	<script type="text/javascript">
	//为有readonly属性 的元素取消退格键
		$(function(){$("input").each(function(){var readonly = $(this).attr("readonly");if(readonly){$(this).keydown(function(){var keydown = event.keyCode;if(keydown){return false;}});}});});
		//提交前进行数据验证
		function submitMenuCheck() {
			var result = true;
			result = promptMsg(textCheck('menuName',1,200), '菜单名称不可为空','info1') && result;
			return result;
		}
	</script>
	
  </head>
 <body>
	<div align="center">
	<form action="system/sjzs!addMenu.action" name="form1" method="post" onsubmit="return submitMenuCheck();"　target="_self">
		<input type="hidden" value="新增菜单" name="closePage"/>
	  	<input type="hidden" value="审计助手管理" name="refreshPage"/>
	  	<input type="hidden" value="${sjzhMenuTree.menuType }" name="sjzhMenuTree.menuType "/>
	  <table width="99%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC">
	    <tr class="title_style">
	      <td colspan="8" style="text-align:left">
	      	<img src="images/group1.png" width="20" height="20" align="absmiddle" />菜单基本信息
	      </td>
	    </tr>
	    <tr class="row_bg">
	      <td colspan="8">
	      	<table width="99%" border="0" cellspacing="0" cellpadding="0" class="nonborder2">
		        <tr bgcolor="#F7FCFF">
		          <td width="11%" align="right" bgcolor="#FFFFFF">菜单名称：</td>
		          <td width="39%" align="left" bgcolor="#FFFFFF">
		          	<input id="menuName" type="text" name="sjzhMenuTree.menuName" onfocus="clearInfo('info1')" class="input_width"/>
					<span id="info1"><font color="red">*必填</font></span>
		          </td>
		          <td align="right" bgcolor="#FFFFFF">
			         	上级菜单：
		          </td>
		          <td align="left" bgcolor="#FFFFFF">
			        <input type="text" value="${fu:getMenuNameByCode(sjzhMenuTree.menuParent)}" readonly="readonly"/>
	    			<input type="hidden" value="${sjzhMenuTree.menuParent }" name="sjzhMenuTree.menuParent" id="ssjgcode"/>
	    			<input type="hidden" name="sjzhMenuTree.menuType" value='0'/>
	    			<span id="info3"><font color="red">*必填</font></span>
		          </td>
		        </tr>
		        <tr>
		        	<td align="right" >
		        		菜单排序：
		        	</td>
		        	<td align="left" colspan="3">
		        		<input type="text" value="" name="sjzhMenuTree.menuOrder" onkeyup="value=value.replace(/[^\d]/g,'')" 
		          	 onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
		        	</td>
		        </tr>
		         <tr>
	        		<td colspan="4" bgcolor="#FFFFFF">
						<input name="submit" type="submit" value="提  交" id="addInfo"/>
	        		</td>
		        </tr>
	      </table>
	     </td>
	    </tr>
	  </table>
		</form>
	</div>
</body>
</html>