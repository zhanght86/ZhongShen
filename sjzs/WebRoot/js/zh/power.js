function goNextPage( currentPage ) {document.getElementById("reqPage").value = currentPage;document.forms.namedItem("queryForm").submit();	}
function delCallBack() {document.forms.namedItem("queryForm").submit();}
function getCheckBox(checkboxname, counts) {var selectCount = 0;var selectValue = "";var myselector = "input[type='checkbox'][name='"+checkboxname+"']";var checkboxs = $(myselector).each(function(){if($(this).attr("checked")==true){selectCount = selectCount + 1;
selectValue = $(this).attr("value");}});if (1 == counts) {if (selectCount == counts) {return selectValue;}return "";} else if (-1 == counts) {if (selectCount >= 1) {return true;}return false;}return false;}
function submitCheck(){var result = true;result = promptMsg(textCheck('pname',1,200), '功能名称不可为空','info1') && result;if ( result ) {document.getElementById("addInfo").disabled = true;}return result;}
function clearInfo(spanId) {document.getElementById(spanId).innerHTML = "<font color=\"red\">*必填</font>";}
function selectPower(){getDataDoC2(baseURL + "system/power!selectPower.action?time="+new Date().getTime(),400,"parentCode","parentName");}
function opeImgDia(){var myVal = window.showModalDialog(baseURL + "view/common/selectImg.jsp","center=yes;dialogWidth=450px;dialogHeight=300px; edge:Raised; directories:no; localtion:no; menubar:no; status=yes; toolbar=yes;scroll:yes;resizable=yes;help=no");
if (undefined != myVal ){myVal = myVal.replace(baseURL,"");document.getElementById("pimg").value = myVal;document.getElementById("showImg").src = myVal;}}