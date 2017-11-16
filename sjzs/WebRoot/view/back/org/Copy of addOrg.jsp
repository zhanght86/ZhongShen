<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/view/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>"/>
    <title>添加部门信息</title>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="css/style.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="js/dataCheck.js"></script>
	<script language="javascript" src='js/showdialog.js'></script>
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript">
		//验证签证格式是否正确
		function checkUpImg(fieldObj){
			var imgPath = fieldObj.value.replace(/(^\s*)|(\s*$)/g,"");
		    if ( "" != imgPath ){//为空提示
		    	 //获得文件后缀名
				var imgExt = imgPath.substr(imgPath.lastIndexOf("."));
				imgExt = imgExt.toLowerCase();//转为小写
				if ( imgExt != '.png' && imgExt != '.gif'){//文件格式错误
					alert("图片格式错误,请上传.png,.gif格式的图片！");
					return false;
				}
				return true;
		    }else{ 
		    	return true;
		    }
		   
		}
		//提交前进行数据验证
		function submitCheck() {
			var result = true;
			result = promptMsg(textCheck('jmname',1,200), '部门名称不可为空','info1') && result;
			result = promptMsg(textCheck('ssjgcode',1,200), '所属部门不可为空','info3') && result;
			
			if ( result ) {//如果验证成功，使提交按钮不可用，防止重复提交
				if(checkUpImg(document.getElementById("orgSignet"))){
					document.getElementById("addInfo").disabled = true;
				}else{
					result=false;
				}
			}			
			return result;
		}
				
		//清除提示信息
		function clearInfo(spanId) {
			document.getElementById(spanId).innerHTML = "<font color=\"red\">*必填</font>";
		}
		//部门选择
		function selectOrg(){
			getDataDoC2("<%=basePath%>system/org!selectOrg.action?time="+new Date().getTime(),400,"ssjgcode","ssjgname");
		}
		//用户选择
		function selectPerson(){
			getDataDoC2("<%=basePath%>system/empl!selectMain.action?time="+new Date().getTime(),935,"emplId","emplName");
			var selectId = $("#emplId").val();
			if (-1 != selectId.indexOf(",")) {
				alert("只可选择一个负责人");
				$("#emplId").val("");
				$("#emplName").val("");
			}			
		}
		//图片预览
		function preImg(imgFile){
			if(!checkUpImg(document.getElementById("orgSignet"))){
				return;
			  }
			$("#newPreview").empty();
			var newPreview=document.getElementById("newPreview");
		 	//ie6或ie8
	         if($.browser.version == 8.0||$.browser.version==6.0){
		         var imgDiv=document.createElement("div");

		         document.body.appendChild(imgDiv);
		         imgDiv.style.width="118px";
		         imgDiv.style.height="127px";
		         imgDiv.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
		         imgDiv.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src=imgFile.value;
				 imgDiv.style.position="relative";
		         newPreview.appendChild(imgDiv);

	         }
	         //如果是其他浏览器
	         else{
	        	 
	        	 var img = document.createElement("img");
	             img.setAttribute("src", "");
	             img.setAttribute("id", "preview");
	             newPreview.appendChild(img);
	             $("#preview").attr("src",imgFile.value).show();			
		     }
		}   
	</script>
  </head>
 <body>
	<div align="center">
	<form action="system/org!addOrganization.action" name="form1" method="post" onsubmit="return submitCheck();" enctype="multipart/form-data">
		<input type="hidden" value="新增部门" name="closePage"/>
	  	<input type="hidden" value="部门管理" name="refreshPage"/>
	  <table width="99%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC">
	    <tr class="title_style">
	      <td colspan="8" style="text-align:left">
	      	<img src="images/group1.png" width="20" height="20" align="absmiddle" /> 部门基本信息
	      </td>
	    </tr>
	    <tr class="row_bg">
	      <td colspan="8">
	      	<table width="99%" border="0" cellspacing="0" cellpadding="0" class="nonborder2">
		        <tr bgcolor="#F7FCFF">
		          <td width="11%" align="right" bgcolor="#FFFFFF">部门名称：</td>
		          <td width="39%" align="left" bgcolor="#FFFFFF">
		          	<input id="jmname" type="text" name="org.jmname" onfocus="clearInfo('info1')" class="input_width"/>
					<span id="info1"><font color="red">*必填</font></span>
		          </td>
		          <td align="right" bgcolor="#FFFFFF">所属部门：</td>
		          <td align="left" bgcolor="#FFFFFF">
			        <input type="text" value="${org.parentName }" id="ssjgname" readonly="readonly" class="input_width"/>
	    			<input type="button" onclick="selectOrg()" value="选择部门"/>
	    			<input type="hidden" value="jmcode_0001" name="org.jmssjg" id="ssjgcode"/>
	    			<span id="info3"><font color="red">*必填</font></span>
		          </td>
		        </tr>
		        <tr>
	        		<td align="right" >
						部门签章：
	        		</td>
	        		<td align="left">
	        			<input type="file" name="signet" id="orgSignet" onChange="preImg(this)"/><span style="color:red">* 请上传*.gif,*.png格式的文件</span>
	        		</td>
		        </tr>
		         <tr bgcolor="#F7FCFF">
		          <td align="right" valign="top" bgcolor="#FFFFFF">图片预览：</td>
		          <td align="left" bgcolor="#FFFFFF" colspan="3"> 
		          		<div id="newPreview"></div>
		          		<img id="showimg" style="display: none;border: 1px #45A1B6 solid;" src="" />
						<img id="showImage" style=" display:none;">
		          </td>
		        </tr>		
		          <tr>
	        		<td colspan="4">
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