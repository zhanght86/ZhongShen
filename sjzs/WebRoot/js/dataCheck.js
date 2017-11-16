/**
 * 验证文本框的内容长度是否在规定字符个数之间<br/>
 * 如果不指定下限则默认为5<br/>
 * 如果不指定上限则默认为30<br/>
 * @param labelId	文本框标签的id
 * @param lowerLimit	字符个数的下限
 * @param upperLimit	字符个数的上限
 * @return	返回TRUE 验证成功，返回FALSE 验证失败
 */		
function textCheck( labelId,lowerLimit,upperLimit ) {
	var textContent = document.getElementById( labelId ).value.replace(/(^\s*)|(\s*$)/g,"");
	var lower = lowerLimit || 5;
	var upper = upperLimit || 30;
	return textContent.length < lower || textContent.length > upper ? false :true;
}

/**
 * 验证FCKeditor的内容长度是否符合指定的规则<br/>
 * 如果不指定下限，则默认下限为0<br/>
 * 如果不指定上限，则默认上限为1500<br/>
 * @param instanceName  FCKeditor实例的名称
 * @param lowerLimit	字数限制的下限
 * @param upperLimit	字数限制的上限
 * @return	返回TRUE FCKeditor的内容符合规则 返回FALSE FCKeditor的内容不符合规则
 */
function checkFCKContent( instanceName, lowerLimit, upperLimit){
	//获取FCKeditor中的内容包含HTML标记，并清除收尾空格
//alert(FCKeditorAPI.GetInstance( instanceName ));
	if ( 'inline' == document.getElementById(instanceName).style.display) {//如果当前编辑器不是FCKeditor
		return true;
	} else {
		var myEditor = FCKeditorAPI.GetInstance( instanceName );
		var contentText = myEditor.GetXHTML(true).replace(/(^\s*)|(\s*$)/g,"");
		var lower = lowerLimit || 0;	//如果不指定下限，则默认下限为0
		var upper = upperLimit || 1500;//如果不指定上限，则默认上限为1500
		if ( 0 == lower ) {//不指定上限时，是否符合上限
			return contentText.length < upper ? true : false;
		}
		return contentText.length < lower || contentText.length > upper ? false : true;
	}
}

/**
 * 调用指定的数据验证函数，根据验证函数返回的结果（true或false）执行相应的操作
 * @param result	验证函数返回的验证结果
 * @param info		验证结果为失败时（false）显示的提示信息
 * @param promptLabelId	指定装载提示信息的标签的id，如果不指定则调用警告框显示提示信息
 * @return 	返回验证函数的验证结果
 */
function promptMsg( result, info, promptLabelId ) {
	var promptLabelObjectId = promptLabelId || 'no';	//如果不指定显示标签则将promptLabelObjectId的值置为'no'
	if ( false == result ) {//验证结果失败时
		if ( "no" == promptLabelObjectId ) {//警告框显示提示信息
			alert(info);
			return false;
		} else {//将提示信息放入指定的标签
			document.getElementById( promptLabelObjectId ).innerHTML = "<font color=\"red\"><b>" + info + "</b></font>";
			return false;
		}
	} 
	return true;
}

/**
 * 视频文件上传表单验证内容是否为空和文件格式是否正确
 * @param fileId	上传标签id
 * @param allowed	上传标签的内容是否允许为空
 * @return
 */
function checkUpVideo( fileId, allowed){
	var videoPath = document.getElementById( fileId ).value.replace(/(^\s*)|(\s*$)/g,"");
	var isAllowed = allowed || false;

    if ( "" == videoPath ){//为空提示
    	if ( false == isAllowed ) {
    		//请选择要上传的视频文件
    		//alert("132");
    		//alert('\u8BF7\u9009\u62E9\u8981\u4E0A\u4F20\u7684\u89C6\u9891\u6587\u4EF6\!');
    		return false;
    	} else {
    		return true;
    	}
    } 
    
    //获得文件后缀名
	var videoExt = videoPath.substr(videoPath.lastIndexOf("."));
	videoExt = videoExt.toLowerCase();//转为小写
	if ('.mp3' != videoExt && ".rmvb" != videoExt 
			&& ".rm" != videoExt && ".wmv" != videoExt ){//文件格式错误
		//文件格式错误
		alert('\u4E0A\u4F20\u6587\u4EF6\u683C\u5F0F\u9519\u8BEF\uFF1A\u89C6\u9891\u8BF7\u9009\u62E9.rm,.rmvb,.wmv\u683C\u5F0F\u7684\u6587\u4EF6\uFF0C\u97F3\u9891\u8BF7\u9009\u62E9.mp3\u683C\u5F0F\u7684\u6587\u4EF6\uFF01');
		return false;
	}
	return true;
}

/**
 * 验证上传图片格式是否正确
 * @param fieldObj 文件表单
 * @param allowed 是否允许为空
 * @param info 提示信息
 * @param spanId 显示提示信息的Id
 * @return
 */
function checkUpImg( fieldObj, allowed, spanId ){
	var imgPath = fieldObj.value.replace(/(^\s*)|(\s*$)/g,"");
	var isAllowed = allowed || false;
    if ( "" == imgPath ){//为空提示
		return true;
    } 
    //获得文件后缀名
	var imgExt = imgPath.substr(imgPath.lastIndexOf("."));
	imgExt = imgExt.toLowerCase();//转为小写
	
	if ( imgExt != '.jpeg' && imgExt != '.jpg' && imgExt != '.bmp'  
			&& imgExt != '.png' && imgExt != '.gif'){//文件格式错误
		alert("图片格式错误！");
		return false;
	}
	return true;
}

//用于记数
var count =  0;
/**
 * 动态添加上传文件表单
 * @param imgFile
 * @return
 */
function addUpForm(imgFile) {
	if ( count > 30) {
		alert("不可再继续添加，已达到最大值！");
		return;
	}
	var tdObj = document.getElementById("imgFile");
	var fileForm = document.createElement("input");
	var spanText = document.createElement("span");
	var fileText = document.createElement("input");
	var btn = document.createElement("input");
	var brLabel = document.createElement("br");

	fileForm.type = "file";
	fileForm.name = "imgUp";
	fileForm.value = "";
	/*fileForm.onchange = function (fileForm) {
		var imgPath = fileForm.value.replace(/(^\s*)|(\s*$)/g,"");
	    if ( "" == imgPath ){//为空提示
			return true;
	    } 
	    //获得文件后缀名
		var imgExt = imgPath.substr(imgPath.lastIndexOf("."));
		imgExt = imgExt.toLowerCase();//转为小写
		
		if ( imgExt != '.jpeg' && imgExt != '.jpg' && imgExt != '.bmp'  
				&& imgExt != '.png' && imgExt != '.gif'){//文件格式错误
			alert("图片格式错误！");
			fileForm.value = "";
			return false;
		}
	}*/
	spanText.innerHTML = "图片描述:";
	
	fileText.type = "text";
	fileText.name = "imgUpText";
	fileText.value = " ";
	
	btn.type = "button";
	btn.value = "移除";
	btn.onclick = function () {
		tdObj.removeChild( brLabel );
		tdObj.removeChild( btn );
		tdObj.removeChild( fileText );
		tdObj.removeChild(spanText);		
		tdObj.removeChild( fileForm );
		count = count - 1;
	}
	tdObj.appendChild( fileForm );
	tdObj.appendChild(spanText);
	tdObj.appendChild( fileText );
	tdObj.appendChild( btn );
	tdObj.appendChild( brLabel );
	count = count + 1;
}
/**
 * 将URI进行编码解决中文乱码
 * @param urlObj
 * @return
 */
function enc_URI(urlObj) {
	urlObj.href= encodeURI(urlObj.href);
	return true;
}


function checkIDCard(str) {
	var isIDCard1 = new Object();
	var isIDCard2 = new Object();

	// 身份证正则表达式(15位)
	isIDCard1 = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;

	// 身份证正则表达式(18位)

	isIDCard2 = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[A-Z])$/;

	if (isIDCard1.test(str) || isIDCard2.test(str)) {
		return true;
	}
	return false;
} 

// 判断字符串是否是手机号码
// 0是手机号码 1不是11位数字 2号码前缀不正确
/* 用于检验手机号的位数以及检验此手机中是否为中国移动的手机号*/
/* 由于存在携号转网的情况 允许3个运营商的全部号段（试点中）*/
// 中国移动号码段：134(0至8号段) 135 136 137 138 139 147 150 151 152 157 158 159 182 183 187 188
// 中国联通号码段：130 131 132 145 155 156 186
// 中国电信号码段：133 153 180 189
function isPhoneNumber(phone) {
	var rv = 0;

	var mbphnoM = /^(13[4-9])|^(147)|^(150)|^(151)|^(152)|^(157)|^(158)|^(159)|^(182)|^(183)|^(187)|^(188)/;
	var mbphnoU = /^(130)|^(131)|^(132)|^(145)|^(155)|^(156)|^(186)/;
	var mbphnoT = /^(133)|^(153)|^(180)|^(189)/;
      
	var num11 = /\d{11}/; // 11位数字;
      
	if (null != phone && "" != phone && num11.exec(phone)) {
		
		if (mbphnoM.exec(phone) || mbphnoU.exec(phone) || mbphnoT.exec(phone)) {
			rv = 0;
		} else {
			rv = 2;
		}
	} else {
		rv = 1;
	}     
	return rv;
}

//counts要选择的复选框的数目，1表示只选择一个，-1表示只选择一个
//如果counts为1时，选择了多个返回false,counts为-1时，没有选择返回false
function getCheckBox(checkboxname, counts) {
	var selectCount = 0;
	var selectValue = "";
	//选取input中type为checkbox,name为checkboxname的表单
	var myselector = "input[type='checkbox'][name='"+checkboxname+"']";
	var checkboxs = $(myselector).each(function(){
			if($(this).attr("checked")==true){
				selectCount = selectCount + 1;
				selectValue = $(this).attr("value");
			}					
		});
	if (1 == counts) {
		if (selectCount == counts) {
			return selectValue;
		}
		return "";
	} else if (-1 == counts) {
		if (selectCount >= 1) {
			return true;
		}
		return false;
	}
	return false;						
} 




//该方法用来处理   处理 self.parent.addTab  打开一个新的选项卡，造成  无法回退的问题。 该方法的 本质是，禁止掉jsp页面中出去几个标签 意外，整个页面的 回退键的功能
function fnBanBackSpace(e) {
    var ev = e || window.event;//获取event对象
    var obj = ev.target || ev.srcElement;//获取事件源
    var t = obj.type || obj.getAttribute('type');//获取事件源类型
    //获取作为判断条件的事件类型
    var vReadOnly = obj.getAttribute('readonly');
    var vEnabled = obj.getAttribute('enabled');
    //处理null值情况
    vReadOnly = (vReadOnly == null) ? false : vReadOnly;
    vEnabled = (vEnabled == null) ? true : vEnabled;
    //当敲Backspace键时，事件源类型为密码或单行、多行文本的，
    //并且readonly属性为true或enabled属性为false的，则退格键失效
    var flag1 = (ev.keyCode == 27 && ev.keyCode == 8 && (t == "password" || t=="text" 
        || t =="textarea") && (vReadOnly == true || vEnabled != true)) 
        ? true:false;
    //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效
    var flag2 = (ev.keyCode == 8 && t != "password" && t != "text" 
        && t != "textarea") ?true:false;
    //判断
    if (flag2)
    {
        return false;
    }
    if (flag1)
    {
        return false;
    }
}


   //禁止后退键 作用于Firefox、Opera
    document.onkeypress = fnBanBackSpace;
    //禁止后退键 作用于IE、Chrome  
    document.onkeydown = fnBanBackSpace;

