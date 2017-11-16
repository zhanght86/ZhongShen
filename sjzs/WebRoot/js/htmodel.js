/**
 * 实现添加自定义变量层的移动
 */
var Obj = ''
document.onmouseup = MUp
document.onmousemove = MMove

function MDown(Object) {
	Obj = Object.id
	document.all(Obj).setCapture()
	pX = event.x - document.all(Obj).style.pixelLeft;
	pY = event.y - document.all(Obj).style.pixelTop;
}

function MMove() {
	if (Obj != '') {
		document.all(Obj).style.left = event.x - pX;
		document.all(Obj).style.top = event.y - pY;
	}
}

function MUp() {
	if (Obj != '') {
		document.all(Obj).releaseCapture();
		Obj = '';
	}
}

/**
 * 
 */
var selectedText = ""; //选中的文本内容

function replaySelectedText(variableValue) {
	var Editor = FCKeditorAPI.GetInstance('matText');
	//Editor.InsertHtml("<span>$" + variableValue + "$</span>");
	if (window.getSelection) { //firefox
		//Editor.EditorWindow.getSelection() = "$" + variableValue + "$";
		Editor.InsertHtml("$" + variableValue + "$");
	} else { //IE
		if(Editor.EditorDocument.selection.createRange().text!=""){
		      Editor.EditorDocument.selection.createRange().text = "$"
					+ variableValue + "$";
		}else{
			 // Insert the desired HTML.
			if(Editor.EditMode == FCK_EDITMODE_WYSIWYG){
				Editor.InsertHtml("$" + variableValue + "$");
			}
		      
		}
		
	}
}


/**
 * 引用定义好的变量
 * @return
 */
function CiteVariable() {
	var citeValues = document.getElementsByName("insertValue");
	var replaceValue = "";
	for ( var i = 0; i < citeValues.length; i++) {
		if (citeValues[i].checked == true) {
			replaceValue =citeValues[i].value;
		}
	}
	if(replaceValue==""){
		alert("您还没有选择相应的模板变量！");
	}else{
		replaySelectedText(replaceValue);
	}
}
/**
 * 用户自定义变量生成word模板
 * 创建人:余鹏飞
 * 创建时间:2011-5-27 15:57
 * @return
 */
function CustomVariable() {
	var text=document.getElementById("variable").value;
	var custVariable = document.getElementById("varis").innerHTML;
	if(custVariable.length==0){
		document.getElementById("varis").innerHTML=text;
		return;
	}
	document.getElementById("varis").innerHTML=custVariable+"<BR/>"+text;
	replaySelectedText(text);
}


/**
 *删除时、修改时让用户只选中一个项目的复选框
 */
function getSingleBox(boxValue){
      var leng = document.getElementsByName("insertValue").length;
      for(var i=0;i<leng;i++){
      var checkBoxes = document.getElementsByName("insertValue")[i];
       if(checkBoxes.checked==true){
          if(checkBoxes.value!=boxValue){
          		checkBoxes.checked=false;
          }
       }
   }
}
