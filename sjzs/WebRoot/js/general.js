//执行删除操作
function execMethod(name,url){
	var selectVal =getCheckBox(name,1);
	if("" !=selectVal){
		if(confirm("是否删除数据")){
			window.location.href=url;
		}
	}else{
		alert("只能选择一个复选框");
	}	
}