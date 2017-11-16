<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/view/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String serverPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<base href="<%=basePath %>">
		<title>上传签章</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<link href="css/style.css" type=text/css rel=stylesheet />
		<style type="text/css">
			ul{text-align: center;
			}
			ul li{
				color: red;
				font:bold;
				font-size: 18px;
				list-style: none;
			}

			#showImage{
			filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);
			
			}
		</style>
		<script language="javascript" src="js/jquery-1.4.4.min.js"></script>
		<script language="javascript" src="js/dataCheck.js"></script>
		<script language="javascript" type="text/javascript">	
		function checkUpImg( fieldObj){
			var imgPath = fieldObj.value.replace(/(^\s*)|(\s*$)/g,"");
		    if ( "" == imgPath ){//为空提示
	    		alert("请选择上传的图片！");
	    		return false;
		    } 
		    //获得文件后缀名
			var imgExt = imgPath.substr(imgPath.lastIndexOf("."));
			imgExt = imgExt.toLowerCase();//转为小写
			if ( imgExt != '.png' && imgExt != '.gif'){//文件格式错误
				alert("图片格式错误,请上传*.png,*.gif格式的图片！");
				return false;
			}

			return true;
		}
		var emplWidth = 120;
		var emplHeigth = 40;
		var orgWidth = 151;
		var orgHeigth = 151;
		//改变印章类型时
		function changeType(selectObj) {
			var signet_type = selectObj.value;
			if (0==signet_type) {
				$("#showimg").attr("width",emplWidth).attr("height",emplHeigth);
				$("#showInfo").html("<a>推荐尺寸120x40像素</a>");
			} else {
				$("#showimg").attr("width",orgWidth).attr("height",orgHeigth);
				$("#showInfo").html("<a>推荐尺寸151x151像素</a>");
			}
		}

		//图片预览
		function preImg(imgFile){			
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
		$(function(){
			$("#showimg").attr("width",emplWidth).attr("height",emplHeigth);
			//$("#preImg").css("width",emplWidth).css("height",emplHeigth);
		});
		
		function cancel(obj){
			if(confirm('您确认要删除个人签名么？')) {
				obj.href="system/empl!delSignet.action?sigtype=empl";
				return true;
			}
			 return false;
		}
	</script>
	</head>
 <body>

	<div align="center">
	<form name="form1" action="system/empl!uploadImage.action" method="post" onSubmit="return checkUpImg( document.getElementById('personal') )" enctype="multipart/form-data">
		<input type="hidden" name="refreshPage" value="欢迎使用" />
		<input type="hidden" name="closePage" value="电子签章" />
		<input type="hidden" name="signetType" value="0"/>
		<input type="hidden" name="empl.emplId" value="${sessionScope.loginEmpl.emplId }"/>
	  <table width="99%" border="0" cellspacing="0" cellpadding="0" class="border_A6C4DC">
	    <tr class="title_style">
	      <td colspan="8" style="text-align:left">
	      	<img src="images/group1.png" width="20" height="20" align="absmiddle" />上传电子签章
	      </td>
	    </tr>
	    <tr class="row_bg">
	      <td colspan="8">
	      	<table width="99%" border="0" cellspacing="0" cellpadding="0" class="nonborder2">
		        <%-- <tr bgcolor="#F7FCFF">
		          <td align="right">类别</td>
		          <td align="left">
		          	<select name="signetType" onChange="changeType(this)">
		          		<option value="0" selected="selected">个人</option>
		          		<option value="1">部门</option>
		          	</select>
		          	<span id="showInfo" style="color: red;"><a>推荐尺寸120x40像素</a></span>
		          </td>
		        </tr>--%>		        
		        <tr bgcolor="#F7FCFF">
		          <td align="right" bgcolor="#FFFFFF">电子签章：</td>
		          <td align="left" bgcolor="#FFFFFF">
		          	<input type="file" value="" name="signet" id="personal" onChange="preImg(this)"/>
		          	<div style="color:red; display:inline;" id="errorWarn">* 推荐尺寸120x40像素</div>
		          </td>
		        </tr>		        
		        <tr bgcolor="#F7FCFF">
		          <td align="right" valign="top">图片预览：</td>
		          <td align="left"> 
		          		<div id="newPreview"></div>
		          		<img id="showimg" style="display: none;border: 1px #45A1B6 solid;" src="" />
						<img id="showImage" style=" display:none;">
		          </td>
		        </tr>		        
		        <tr>
	        		<td colspan="4" bgcolor="#FFFFFF" align="center">
						<input name="submit" type="submit" value="&nbsp;保&nbsp;存&nbsp;" id="addInfo"/>
	        		</td>
		        </tr>
	      </table>
	     </td>
	    </tr>
	  </table>
	</form>
	<br/>
	<div style="text-align: left;margin-left:10px;margin-top:10px;">	
	<%-- 	<c:if test="${null!=sessionScope.loginEmpl.emplSignet && ''!=sessionScope.loginEmpl.emplSignet}">					 
			个人签名：<img src="ShowSignetServlet?id=${sessionScope.loginEmpl.emplId }" width="100" height="40" align="top" style="border: 1px #45A1B6 solid;vertical-align: middle;"/>
			<a href="system/empl!delSignet.action?sigtype=empl" onClick="if(confirm('您确认要删除个人签名么？')) {return true;} return false;">删除</a>
			<br/><br/>
		</c:if>
		<c:if test="${null!=sessionScope.loginEmpl.orgSignet && ''!=sessionScope.loginEmpl.orgSignet}">
			电子签章：<img src="<%=serverPath %>${sessionScope.loginEmpl.orgSignet }" width="200" height="200" align="top" style="border: 1px #45A1B6 solid;vertical-align: middle;"/>
			<a href="system/empl!delSignet.action?sigtype=org" onClick="if(confirm('您确认要删除部门签章么？')) {return true;} return false;">删除</a>
		</c:if>
		--%>
		<c:if test="${null!=empl.emplSignet && ''!=empl.emplSignet}">					 
			个人签名：<img src="ShowSignetServlet?id=${sessionScope.loginEmpl.emplId }" width="100" height="40" align="top" style="border: 1px #45A1B6 solid;vertical-align: middle;"/>
			<a href="system/empl!delSignet.action?sigtype=empl" onClick="if(confirm('您确认要删除个人签名么？')) {return true;} return false;">删除</a>
			<br/><br/>
		</c:if>
		
	</div>
	</div>
</body>
</html>