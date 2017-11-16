<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>"/>
    <title>已经选择的用户</title>	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
    <meta http-equiv="description" content="this is my page"/>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>    
    <link href="css/style.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript">
    	//返回给父窗口所选择的用户id和用户姓名
    	function getEmpl() {
        	var myids = "";
        	var mynames = "";
        	//获得所有选择的用户id
        	var ids_obj = document.getElementsByName("empids");
        	
        	//获得所有选择的用户的姓名
			var names_obj = document.getElementsByName("empnames");
			
			for (i = 0; i < ids_obj.length; i++) {
				myids = myids + ids_obj[i].value + ",";
			}
			
			for (j = 0; j < names_obj.length; j++ ) {
				mynames = mynames + names_obj[j].value + ","
			}
			//去掉最后一个逗号
			myids = myids.substring(0,myids.length-1);
			mynames = mynames.substring(0,mynames.length-1);
			//设置返回的数组
			var svalue = new Array(myids, mynames);
			parent.window.returnValue = svalue;
			top.close();
        }
    </script>
  </head>  
  <body bgcolor="#FFFFFF">
   <div align="center" style="margin-top:10px;">
	  <table width="99%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC">
	  	<tbody id="empllist">
	    <tr class="title_style">
	      <td colspan="8" style="text-align:left">
	      	<img src="images/group1.png" width="20" height="20" align="absmiddle" />用户选择列表
	      </td>
	    </tr>
    	</tbody>
    </table>
    <div style="width: 100%;text-align: center;">
		<input type="button" value="确认选择" onclick="getEmpl();" class="button-submis"/>		
    </div>
    </div>
  </body>
</html>
