function goNextPage( currentPage ) {
	document.getElementById("reqPage").value = currentPage;document.forms.namedItem("queryForm").submit();
	}
function delCallBack() 
{document.forms.namedItem("queryForm").submit();}function selectSF(emplUid,emplssjg){getDataDoC2("<%=basePath%>system/role!selectRole.action?time="+new Date().getTime
(),400,"emplrcodes");var roleValues = document.getElementById("emplrcodes").value;if ( "" != roleValues ) {document.getElementById("emplid").value = 
emplUid;document.getElementById("empljgcode").value = emplssjg;document.getElementById("roleFrom").submit();}}function getCheckBox(checkboxname, counts) {var selectCount = 0;var 
selectValue = "";var myselector = "input[type='checkbox'][name='"+checkboxname+"']";var checkboxs = $(myselector).each(function(){if($(this).attr("checked")==true){selectCount = 
selectCount + 1;selectValue = $(this).attr("value");}
});if (1 == counts) {if (selectCount == counts) {return selectValue;}return "";} else if (-1 == counts) {if (selectCount >= 1) {return true;}return false;}return false;} 
function updateEmpl(jmcode) {var selectVal = getCheckBox(jmcode,1);if ("" != selectVal) {var goURI = "system/org!goUpdatePage.action?parentCode=${parentCode}&org.jmcode=" + 
selectValself.parent.addTab('编辑部门',goURI,'icon-add')} else {alert("有且只有一个复选框可以被选中");}			}
function deleteEmpl(jmcode){
var selectResult = getCheckBox(jmcode,-1);if (selectResult) {if (confirm("您确定要删除所选的员工？")) {$("form[name='deleteForm']").submit();}} else
 {alert("至少有一个复选框必须被选中！");}} function checkMobile(ObjectId)
 {var $val = $("input[id='"+ObjectId+"']").val();if ($val=="") {alert("当前员工没有手机号信息，无法进行短信设置，请先给当前员工添加手机号信息，再进行设置！");return false;} 
return true;} 