
function addReg(reqURL) {
	$('#regDiv').window({   
		width:700,   
		height:600,   
		modal:true,
		collapsible:false,
		minimizable:false, 
		maximizable:false,
		closable:true
	});
	var iframe_str = "<iframe height=\"100%\" width=\"100%\" scrolling=\"no\" frameborder=\"0\" src=\"" + reqURL + "\"></iframe>";
	$('#regDiv').html(iframe_str);
}

//关闭浮动窗口
function closeWinS(win_name) {
	$('#' + win_name).window('close');
	window.location.reload(true);
}

/**
 * 用户登陆，注册等用 * 
 */
//弹窗
function showDiv(types){			
	//var box =300;				
	var th= $(window).scrollTop()+$(window).height()/1.6-300;				
	var h =document.body.clientHeight;				
	var rw =$(window).width()/2-180;
	//var 				
	$("."+types).animate({top:th,opacity:'show',width:400,height:300,right:rw},500);				
	$("body").prepend("<div class='mask'></div>");				
	$(".mask").css({opacity:"0.5"}).css("height",h);			
	document.getElementById('fade').style.display='block';
	$("."+types).focus();
	return false;				
}
//登录用***
$(function() {
	$("#loginEmplForm").validate( {
		rules : {				
			"employee.emplAccount" : {
				required : true
			},
			"employee.emplPassword" : {
				required : true
			},
			"yanzh":{
				required:true
			}
		},
		messages : {
			"employee.emplAccount" : {
				required : "*必填"
			},
			"employee.emplPassword" : {
				required : "*必填"
			},
			"yanzh":{
				required:"*必填"
			}
		},
		errorElement : "em",
		success : function(label) {
			label.text(" ").addClass("success")
			//$("all").window('close');
	}
	});
});
//注册用
$(function() {
	$("#regEmplForm").validate( {
		rules : {
			"employee.emplName" : {
				required : true
			},
			"employee.emplAccount" : {
				required : true
			},
			"employee.emplPassword" : {
				required : true
			},
			"rePassword" : {
				required : true
			}
		},
		messages : {
			"employee.emplName" : {
				required : "*必填"
			},
			"employee.emplAccount" : {
				required : "*必填"
			},
			"employee.emplPassword" : {
				required : "*必填"
			},
			"rePassword" : {
				required : "*必填"
			}
		},
		errorElement : "em",
		success : function(label) {
			label.text(" ").addClass("success")
	}
	});
});

//验证码切换
function cutImage(type){
	var src="reglogin!image.action?noche="+(new Date());
	if(type == "1"){
		$("#image").attr("src",src);
	}else{
		$("#reg_image").attr("src",src);
	}			
	return false;
}

function subName(type) {
	var param="#emplAccount";
	var mess="#mess";
	if(type=="0"){
		param="#reg_emplAccount";
		mess="#reg_mess";
	}
	$.ajax( {
		type : "post", 
		url: "/sjzs/servlet/checkEmplNameServlet",
		data : { //发送给数据库的数据				
			emplName:$(param).val(),
			type:type
		},
		//请求成功后的回调函数有两个参数
		success : function(data, textStatus) {
			$(mess).html(data);
			if(data != ""){
				$(param).val("");
			}		
		}
	});	
}
function subPass(type) {
	var param1=$("#emplAccount").val();
	var param2="#emplPassword";
	var mess="#mess";
	if(type == "0"){
		param1=$("#pass").val();
		param2="#repass";
		mess="#reg_mess";
	}
	$.ajax( {
		type : "post", 
		url: "/sjzs/servlet/checkEmplPassServlet",
		data : { //发送给数据库的数据				
			param1: param1,
			param2: $(param2).val(),
			type: type
		},
		//请求成功后的回调函数有两个参数
		success : function(data, textStatus) {
			$(mess).html(data);
			if(data != ""){
				$("#pass").val("");
				$(param2).val("");
			}	
		}
	});		
}
function subYzm(type) {
	var mess = "#mess";
	var yanzh = "#yanzh";
	if(type == "0"){
		mess="#reg_mess";
		yanzh = "#reg_yanzh";	
	}
	$.ajax( {
		type : "post", //请求方式
		//发送请求地址
		url: "/sjzs/servlet/checkYzmServlet",
		data : { //发送给数据库的数据				
			yanzh:$(yanzh).val()
		},
		//请求成功后的回调函数有两个参数
		success : function(data, textStatus) {
			$(mess).html(data);
			if(data != ""){
				$(yanzh).val("");
				cutImage(type);
			}								
		}
	});		
}
function checkLogin(){
	$.ajax( {
		type : "post", //请求方式
		//发送请求地址
		url: "/sjzs/servlet/checkEmplLoginServlet",
		data : { //发送给数据库的数据	
			emplName:$("#emplAccount").val(),
			emplPass:$("#emplPassword").val(),		
			yanzh:$("#yanzh").val()
		},
		//请求成功后的回调函数有两个参数
		success : function(data, textStatus) {
			$("#mess").html(data);
			if(data != ""){
				$("#emplPassword").val("");
				$("#yanzh").val("");
				cutImage('1');
			}else{
				closeT();
				$("#loginEmplForm").submit();
			}	
		},
		error :function(){
			return false;
		}
	});
}
function checkReg(){
	$.ajax( {
		type : "post", //请求方式
		//发送请求地址
		url: "/sjzs/servlet/checkEmplRegServlet",
		data : { //发送给数据库的数据	
			emplName:$("#reg_emplAccount").val(),
			pass:$("#pass").val(),
			repass:$("#repass").val(),		
			yanzh:$("#reg_yanzh").val()
		},
		//请求成功后的回调函数有两个参数
		success : function(data, textStatus) {
			$("#reg_mess").html(data);
			if(data != ""){
				$("#pass").val("");
				$("#repass").val("");
				$("#yanzh").val("");
				cutImage('0');
			}else{
				closeT();
				$("#regEmplForm").submit();
			}
			return false;	
		},
		error :function(){
			return false;
		}
	});				
}

function closeT(types){
	$("."+types+" .close").parents("."+types).animate({top:0,opacity: 'hide',width:0,height:0,right:0},500);				
	location.href="/sjzs/";
	$(".mask").fadeOut("fast");					
	document.getElementById('fade').style.display='none';
}