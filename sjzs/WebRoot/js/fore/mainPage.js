function loadS(types){	
	$("#showType").attr("value",types);
	var target = "td.td_";
	if(types != null && types != ""){
		target += types;
	}else{
		target += "sy";
	}
	$(target).attr("style","background:#f4f4f4");
	$(target+">a").attr("style","color:black;");
}
function showInfoS(obj,showinfo){
	//alert("showInfo!show"+showinfo+".action?type="+$("#type").val());
	var hre=$(obj).attr("href");
	if(hre != null && hre != ""){
		$(obj).click();		
	}else{
		$(obj).attr("href","showInfo!show"+showinfo+".action?searchType="+$("#searchType").val());		
	}
}

function showClick(obj,type){
	var hre=$(obj).attr("href");
	if(hre != null && hre != ""){
		$(obj).click();		
	}else{
		$(obj).attr("href","showInfo!showMain.action?type="+type+"&searchType="+$("#searchType").val());		
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

function clearS(evenSou){
	var vale=$.trim($(evenSou).val());
	if(vale=="用户登陆后可以搜索"){
		$(evenSou).val("");
	}
}

function Dwain(){
	alert("用户没有登陆，请登录！");
	showDiv('showbox1');
	return false;
}