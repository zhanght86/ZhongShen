/**
 * 用户从url上获取？后面的数据
 * 现在用action传过来的，在地址栏里没有显示，所以获取不到了，
 * 要用到后台代码
 * *
function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) 
		return unescape(r[2]); 
	return null;
}
*/
function loadS(type){			
	//var type =(getQueryString("type"));
	$("#showType").attr("value",type);
	var target = "td.td_"
	if(type != null && type != ""){
		target += type;
	}else{
		target += "all";
	}
	$(target).attr("style","background:url('/sjzs/images/fore/hover_back.png') repeat-x");		
}
function showInfoS(obj,showinfo){
	//alert("showInfo!show"+showinfo+".action?type="+$("#type").val());
	var hre=$(obj).attr("href");
	if(hre != null && hre != ""){
		$(obj).click();		
	}else{
		$(obj).attr("href","showInfo!show"+showinfo+".action?type="+$("#type").val());		
	}
}
function changebg(obj,cl){
	var bgcolor;
	if(cl=="0"){
		bgcolor="#ffffff";
	}else{
		bgcolor="#e5f4ee";
	}
	obj.style.backgroundColor=bgcolor;
}

function wain(){
	alert("用户没有登陆，请登录！");
	return false;
}

