/**
 * 打开新窗口
 * @param address 链接地址
 * @param windowName 窗口的名称
 * @param windowWidth 窗口的宽度
 * @param windowHeight 窗口的高度
 * @param isStatus 窗口是否有滚动条，1：有滚动条；0：没有滚动条
 * @return
 */
function openWin(address ,windowName,windowWidth,windowHeight,isStatus){
	sb = isStatus == "1" ? "1" : "0";
	var windowLeft = (screen.width - windowWidth)/2;
	var windowTop = (screen.height - windowHeight)/2;
	sFeatures = "left="+ windowLeft +",top="+ windowTop +",height="+ windowHeight +",width="+ windowWidth
			+ ",center=1,scrollbars=" + sb + ",status=0,directories=0,channelmode=0";
	openwin = window.open(address , windowName , sFeatures );
	if (!openwin.opener)
		openwin.opener = self;
	openwin.focus();
	return openwin;
}

/**
 * 打开删除窗口
 * @param url 链接地址
 * @param confirmString confirm的提示信息
 * @return
 */
function openDeleteDialog(url,confirmString){
	var c = confirmString;
	if(c == null || c == ''){
		c = "你确认要删除记录吗？";
	} else if ('no' == confirmString) {
		return window.showModalDialog(url,"window123","dialogHeight:234px;dialogWidth:271px;resizable:no;help:yes;status:no;scroll:no");
	}
	if(confirm(c)){
		return window.showModalDialog(url,"window123","dialogHeight:234px;dialogWidth:271px;resizable:no;help:yes;status:no;scroll:no");
	}
	return false;
}

/**
 * 删除记录
 * @param url 链接地址
 * @param info confirm的提示信息
 * @param callbackfn 操作成功后的回调函数
 * @return
 */
function del(url,info,callbackfn){
	if(openDeleteDialog(url,info)){
		callbackfn();
	}
}

/*
 * 替换字符串
 */
function replaceStr( str ) {
    var re="/( )/gi";
    str = str.replace(re,"");
    re="/\</gi";
    str = str.replace(re,"&lt;");

    return str;
}

/**
 * 去掉字符串左边空格
 * @param str 去掉空格的字符串
 * @return
 */
function LTrim(str) {
    var whitespace = new String(" \t\n\r");
    var s = new String(str);
    if (whitespace.indexOf(s.charAt(0)) != -1) {
        var j=0, i = s.length;
        while (j < i && whitespace.indexOf(s.charAt(j)) != -1) {
           j++;
        }
        s = s.substring(j, i);
    }
    return s;
}

/**
 * 去掉字符串右边空格
 * @param str 去掉空格的字符串
 * @return
 */
function RTrim(str) {
    var whitespace = new String(" \t\n\r");
    var s = new String(str);
    if (whitespace.indexOf(s.charAt(s.length-1)) != -1) {
        var i = s.length - 1;
        while (i >= 0 && whitespace.indexOf(s.charAt(i)) != -1) {
            i--;
        }
        s = s.substring(0, i+1);
    }
    return s;
}

/**
 * 去掉字符串的首尾空格
 * @param str 去掉空格的字符串
 * @return
 */
function Trim(str) {
    return RTrim(LTrim(str));
}

/**
 * 按比例缩小图片
 * @param ImgD 图片表单对象
 * @param iwidth 图片的宽度
 * @param iheight 图片的高度
 */
function DrawImage(ImgD,iwidth,iheight){
	var flag=false;
	var image=new Image();
	image.src=ImgD.src;
	if(image.width>0 && image.height>0){
		flag=true;
		if(image.width/image.height>= iwidth/iheight){
			if(image.width>iwidth){ 
				ImgD.width=iwidth;
				ImgD.height=(image.height*iwidth)/image.width;
			}else{
				ImgD.width=image.width; 
				ImgD.height=image.height;
			}
		}else{
			if(image.height>iheight){ 
				ImgD.height=iheight;
				ImgD.width=(image.width*iheight)/image.height; 
			}else{
				ImgD.width=image.width; 
				ImgD.height=image.height;
			}
		}
	}
	ImgD.style.visibility = "visible";
} 

/*
 *
 */
function objectEval(text) {
    text = text.replace(/\n/g, " ");
    text = text.replace(/\r/g, " ");
    if (text.match(/^\s*\{.*\}\s*$/)) {
        text = "[" + text + "]";
    }
    return eval(text)[0];
}

/*
 * 添加页面载入后触发的事件
 */
function addLoadEvent(func) {
	var oldonload = window.onload;
	if (typeof(window.onload) != "function") {
		window.onload = func;
	} else {
		window.onload = function() {
			oldonload();
			func();
		}
	}
}
