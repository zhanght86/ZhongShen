function getDataDoC2(urls,width,get1,get2,get3,get4,get5,get6,get7,get8,get9,get10,get11,get12) {
	if (width=="" || width==undefined) {
		width=600;
	}
	if (showModalDialog) {
		var sRtn;
		sRtn = window.showModalDialog(urls,null,"center=yes;dialogWidth="+width+"px;dialogHeight=400px; edge:Raised; directories:no; localtion:no; menubar:no; status=yes; toolbar=yes;scroll:yes;help=no");
		if (sRtn!=undefined){
			if(get1!="" && get1!=undefined){
				document.getElementById(get1).value=sRtn[0];
			}
			if(get2!="" && get2!=undefined){
				document.getElementById(get2).value=sRtn[1];
			}
			if(get3!="" && get3!=undefined){
				document.getElementById(get3).value=sRtn[2];
			}
			if(get4!="" && get4!=undefined){
				document.getElementById(get4).value=sRtn[3];
			}
			if(get5!="" && get5!=undefined){
				document.getElementById(get5).value=sRtn[4];
			}
			if(get6!="" && get6!=undefined){
				document.getElementById(get6).value=sRtn[5];
			}
			if(get7!="" && get7!=undefined){
				document.getElementById(get7).value=sRtn[6];
			}
			if(get8!="" && get8!=undefined){
				document.getElementById(get8).value=sRtn[7];
			}
			if(get9!="" && get9!=undefined){
				document.getElementById(get9).value=sRtn[8];
			}
			if(get10!="" && get10!=undefined){
				document.getElementById(get10).value=sRtn[9];
			}
			if(get11!="" && get11!=undefined){
				document.getElementById(get11).value=sRtn[10];
			}
			if(get12!="" && get12!=undefined){
				document.getElementById(get12).value=sRtn[11];
			}
		}
   } else { 
   	alert("请使用Internet Explorer 4.0以后的版本");
   }
}

function getDataC(rvalue1,rvalue2,rvalue3,rvalue4,rvalue5,rvalue6,rvalue7,rvalue8,rvalue9,rvalue10,rvalue11,rvalue12) {
  var sDate = new Array(rvalue1, rvalue2,rvalue3,rvalue4,rvalue5,rvalue6,rvalue7,rvalue8,rvalue9,rvalue10,rvalue11,rvalue12);
  window.returnValue = sDate; 
  window.close();
}