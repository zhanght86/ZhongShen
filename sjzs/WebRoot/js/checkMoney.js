/**
 * 判断输入的金额是否是正确。并且可以默认添加千位分隔符。
 * @param input 表单
 * @param e 输入的值
 * @return
 */
function currencyFormat(input, e) {
	var sep = 0;
	var key = '';
	var i = j = 0;
	var len = len2 = 0;
	var aux = aux2 = '';
	var strCheck = '0123456789';
	var re = /(\d{1,3})(?=(\d{3})+(?:$|\.))/g;
	var whichCode = (window.Event) ? e.which : e.keyCode;// 获取当前用户输入的键盘
	// alert("键盘输入值="+whichCode);
	if (whichCode == 13)
		return true; // 如果用户输入正确返回
	key = String.fromCharCode(whichCode); // 获得用户输入的键盘值
	len = input.value.length; // 获得当前文本框字符串长度
	// alert("len="+len);
	if (len == 0 && strCheck.indexOf(key) != -1) {
		return true; // 如果文本框当前为空，输入数字，返回当前数字
	}
	if (len <= 2) {
		if (strCheck.indexOf(key) != -1 || whichCode == 46) {// 判断输入金额是否为"0123456789."
			if (whichCode == 46) {// 判断输入是否是"."
				if (input.value.indexOf('.') != -1) {// 判断文本框字符串是否已经存在"."
					return false;
				} else {
					return true;
				}
			} else {// 如果不是"."输入数字字符生效
				return true;
			}
		} else { // 不是，输入键盘无效
			return false;
		}
	}
	if (len > 2) {// 当前文本框字符串长度大于3时
		aux = input.value;
		//晁飞添加开始  控制输入长度，支持输入17位数字
		if(len >= 22){
			aux = aux.replace(/\,/g, "");
			return false;
		}
		//晁飞添加开始
		if (aux.indexOf('.') != -1) {// 判断是否带小数的金额
			var strInput = aux.split('.');
			if (strInput[0].length > 3) {
				if (strInput[0].indexOf(',') != -1) {
					strInput[0] = strInput[0].replace(/\,/g, "");
				}
				strInput[0] = strInput[0].replace(re, "$1,");
			}
			//限制小数位数
			if (strInput[1].length < 4) {
				if (strCheck.indexOf(key) != -1) {
					strInput[1] += key;
				} else {
					return false;
				}
			}
			input.value = strInput[0] + "." + strInput[1];
		} else {
			if (strCheck.indexOf(key) != -1 || whichCode == 46) {
				if (aux.indexOf(',') != -1) {
					aux = aux.replace(/\,/g, "");
				}
				aux += key;
				aux = aux.replace(re, "$1,");
				input.value = aux;
			} else {
				return false;
			}
		}
		return false;
	}
}

/**
 * 把页面输入的金额转型赋值给id
 * @param value
 * @param id
 * @return 
 */
function currencyFormat1(input,e){
    var sep = 0;
	var key = '';
	var i = j = 0;
	var len = len2 = 0;
	var aux = aux2 = '';
	var strCheck = '0123456789';
	var re=/(\d{1,3})(?=(\d{3})+(?:$|\.))/g;
	var whichCode = (window.Event) ? e.which : e.keyCode;//获取当前用户输入的键盘
	//alert("键盘输入值="+whichCode);
	if (whichCode == 13) return true; // 如果用户输入正确返回
	key = String.fromCharCode(whichCode); // 获得用户输入的键盘值
	len = input.value.length; //获得当前文本框字符串长度
	//alert("len="+len);
	if (len == 0 && strCheck.indexOf(key) != -1) {
		return true; //如果文本框当前为空，输入数字，返回当前数字
	}
	aux = input.value;
	//晁飞添加开始
	var realVal=getMoney(aux);
	var reg = /^\d+$/;
	if(!reg.test(realVal)){
		input.value="";
		return false;
	}
	if(whichCode==8||whichCode==46){
		input.value="";
	}
	//晁飞添加结束
	if(len <= 2 ){
		if (strCheck.indexOf(key) != -1 || whichCode == 46){//判断输入金额是否为"0123456789."
		if (whichCode == 46){//判断输入是否是"."
		if(input.value.indexOf('.') != -1){//判断文本框字符串是否已经存在"."
		return false;
	}else{
		return true;
	}
	}else{//如果不是"."输入数字字符生效
		return true;
	}
	}else{ //不是，输入键盘无效
		return false;
	}
	}
	if(len > 2){//当前文本框字符串长度大于3时
	
	// alert(aux);
	if (aux.indexOf('.') != -1){//判断是否带小数的金额
		 var strInput = aux.split('.');
		 if (strInput[0].length > 3){
		 if(strInput[0].indexOf(',') != -1){
		  strInput[0] = strInput[0].replace(/\,/g,"");
	   }
	strInput[0] = strInput[0].replace(re,"$1,");
		 }
		 if (strInput[1].length <100){
			 //小数点后也分
				if (strCheck.indexOf(key) != -1){
					strInput[1] += key;
					 if (strInput[1].length > 3){
						 if(strInput[1].indexOf(',') != -1){
						  strInput[1] = strInput[1].replace(/\,/g,"");
					   }
						 strInput[1] = strInput[1].replace(re,"$1,");
				 }
			}else{
				return false;
			}
		 }
		 input.value = strInput[0]+"."+strInput[1];
	}else{
	   if (strCheck.indexOf(key) != -1 || whichCode == 46){
		  if(aux.indexOf(',') != -1){
		  aux = aux.replace(/\,/g,"");
		  }
		  aux +=key;
		  aux = aux.replace(re,"$1,");
				input.value = aux;


			
			 }else{
				return false;
			 }
	}
	return false;
	}
	
}   


/**
 * 重新格式化金额。以千位分隔符分割
 * @param id
 * @return
 */
function setNewValue(id){
	   var value = document.getElementById(id).value;
	   value=value.split('').reverse().join('').replace(/(\d{3})/g, '$1,').split('').reverse().join('');
	   if(value.indexOf(",")==0){
 	   //去掉以”,“开头的字母
 	   value=value.substring(1,value.length);
 	}else{
			if(value.indexOf(".,")!=-1){
				//替换”.,“为”.“
				value=value.replace(/\.\,/g,".");
			}
    	}
	   document.getElementById(id).value=value;
}

/**
 * 把千位分隔符字符窜转化成数字
 * @param htje
 * @return
 */
function getMoney(htje){
	if(htje!="."){
		if(htje.indexOf(".")>-1){
			if(htje.indexOf(".")==0){
				htje="0"+htje;
				if(htje.indexOf(",")>-1){
					htje=htje.replace(/\,/g,"");
				}
			}else{
				
				if(htje.indexOf(".")==htje.length-1){
					htje=htje.substring(0, htje.length-1);								
					if(htje.indexOf(",")>-1){
						htje=htje.replace(/\,/g,"");
					}
				}else{
					if(htje.indexOf(",")>-1){
						htje=htje.replace(/\,/g,"");
					}
				}
			}
		}else{
			if(htje.indexOf(",")>-1){
				htje=htje.replace(/\,/g,"");
			}
		}
	}else{
		htje=0;
	}	
	
	return Number(htje);
	
}

/**
 * 重新格式化金额。以千位分隔符分割
 * @param id
 * @return
 */
function setNewValueByList(value){	  
	   value=value.split('').reverse().join('').replace(/(\d{3})/g, '$1,').split('').reverse().join('');
	   if(value.indexOf(",")==0){
	 	   //去掉以”,“开头的字母
	 	   value=value.substring(1,value.length);
	 	  if(value.indexOf(".,")!=-1){
				//替换”.,“为”.“
				value=value.replace(/\.\,/g,".");
			}
	 	  if(value.indexOf(".,")==value.length-1){
	 		 value=value.substring(0,value.length-1);
	 	  }
	   }else{		   
			if(value.indexOf(".,")!=-1){
				//替换”.,“为”.“
				value=value.replace(/\.\,/g,".");
			}
			 if(value.indexOf(".,")==value.length-1){
		 		 value=value.substring(0,value.length-1);
		 	  }
    	}
	   return value;
}

