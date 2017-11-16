$(document).ready(function() {
	//左边初始化
	$("#left_cont_title").text("当前是第一步");
	leftHei();
	$("#down_pig").mouseover(function(){
		alert("---");
		$(this).attr("style","background-position: -170px -2px;");
	});
	$("#down_pig").mouseout(function(){
		$(this).attr("style","background-position: -114px -2px;");
	});
});

function mouserE(f,ov){
	if(f=="down"){
		if(ov=="over"){
			$("#"+f+"_pig").css("background-position"," -170px -2px;");
		}else if(ov=="out"){
			$("#"+f+"_pig").css("background-position"," -114px -2px;");
		}
	}else if(f == "up"){
		if(ov=="over"){
			$("#"+f+"_pig").css("background-position"," -5px -2px;");
		}else if(ov=="out"){
			$("#"+f+"_pig").css("background-position"," -60px -2px;");
		}
	}
	
}


function inits(begId){
	$("td.mode"+begId+">div").attr({style:"color: red;background:url('view/xml/modeB2.png') no-repeat;"});
	var modes = new Array();
	$("td[class^=mode]").each(function(){
			var modeClass = $(this).attr("class");
			var tag = false;
			for(var i=0;i<modes.length;i=i+1){
				if(modes[i]==modeClass){
					tag = true;
					break;
				}
			}
			if(!tag){
				modes.push(modeClass);
			}
	});
	for(var i=0;i<modes.length;i=i+1){
		var cla=modes[i];
		var a=$("td."+cla);
		if(a.length>2){
			var j = Math.floor(a.length/2);
			var k=0;
			$("td."+cla).each(function(){
				if(k != j){
					var m = $(this).attr("id");
					$("#"+m).html("<span style='margin-left:50px;margin-right:50px;'>&nbsp;</span>");
					//$("#"+m+">div").html("");
					//$("#"+m+">div").attr("style","background:none;");
					//$("#"+m+">div").attr("title","");
				}
				k=k+1;
			});	
		}
	};
	
}

/**
 * 左右切换的单击事件，从后传过来数据；
 */
function clickAjax(n,bear) {
	var modeId=$("#" + $(n).attr("id") + ">#"+bear+"_modeId").val();
	if(bear=="right"){
		$("td.mode"+modeId+">div").attr({style:"color: red;background:url('view/xml/modeB2.png') no-repeat;"});
	}
	$.ajax( {
		url : 'servlet/switchShowXml?bear='+bear+'&modeId=' + modeId,
		type : 'post',
		// date:{'modeId':$("#"+$(n).attr("id")+">#left_mode").val()},
		dataType : 'xml',
		error : function(xml) {
			alert(xml);
			alert('出错了e~!!');
		},
		success : function(xml) {
			succ(xml,bear);
		}
	});
}

/** 获取数据成功后执行的方法
 *对传过来的xml文件进行解析，获取数据 
 *  
 **/
function succ(xml,bear) {
	$(xml).find("show").each(function(i) {
		var mess = $(this).children("mess"); // 取信息对象			
			var left = $(this).children("left");
			var left_id = $(left).children("id").text();
			var left_title = $(left).children("title").text();
			lefCheng(left_id,left_title);
			var mess_title = $(mess).children("title").text();
			var mess_cont = $(mess).children("cont").text();			
			var center = $(this).children("center");
			var center_key = $(center).children("key").text();
			var flow = $(center).children("flow");
			var flow_ffId = $(flow).children("fangFaId").text();
			var flow_ffUrl = $(flow).children("fangFaUrl").text();
			var flow_ffName = $(flow).children("fangFaName").text();
			var flow_atId = $(flow).children("attachId").text();
			var flow_atUrl = $(flow).children("attachUrl").text();
			var flow_atName = $(flow).children("attachName").text();
			centerCheng(mess_title,mess_cont,flow_ffId,flow_ffUrl,flow_ffName,flow_atId,flow_atUrl,flow_atName);
			var arr1 = new Array();
			var arr2  = new Array();
			var right = $(this).find("right").each(function(i){
				arr1[i] = $(this).children("id").text();
				arr2[i] = $(this).children("title").text();
			});
			rightCheng(arr1,arr2,bear);
		});
}

/**
 *显示左侧数据
 *
 */
function lefCheng(modId,title){
	$("div.left_cont").fadeToggle(100);
	$("#left_cont_title").fadeToggle(100);
	if(modId == "null"){		
		$("div.left_cont").removeAttr("onclick","");
		$("#left_cont_title").text("当前是第一步");
	}else{
		$("#left_modeId").val(modId);
		$("div.left_cont").attr("onclick","clickAjax(this,'left')");
		$("#left_cont_title").text(title);
	}
	leftHei();
	$("#left_cont_title").fadeToggle(100);
	$("div.left_cont").fadeToggle(100);
}

/**
 * 对左侧数据的上下位置调整居中
 */
function leftHei(){
	var he= $("#left_cont_title").height();
	var to=($("div.left_cont").height()-he)/2;
	$("#left_cont_title").css("top",to+"px");
}

/**
 *把中间的节点信息显示出来
 *
 */
function centerCheng(title,cont,ffid,ffUrl,ffName,atId,atUrl,atName){
	$("div.center_cont").fadeToggle(100);
	$("div.center_cont>table").fadeToggle(100);
	$("#cent_title").text(title=="null"?"":title);
	$("#cent_cont").text(cont=="null"?"":cont);
	$("#cent_ff").html('<a href="'+ffUrl+'">'+ffName+'</a>');
	$("#cent_att").html('<a href="'+atUrl+'">'+atName+'</a>');
	$("div.center_cont>table").fadeToggle(100);
	$("div.center_cont").fadeToggle(100);
}


/**
 * 把传过来的数据显示到右侧，传过来是两个数组，一个方向，方向当时用于对下面节点高亮用，两个数组一个存储节点的Id，一个存储节点的标题
 */
function rightCheng(arr1,arr2,bear){	
	$("div.show_right").fadeToggle(100);
	var hei = (330-(arr1.length+1)*5)/(arr1.length);
	var all ="";
	for(var i=0;i<arr1.length;i=i+1){
		if(bear=="left"){
			$("td.mode"+arr1[i]+">div").attr("style","");
		}
		var t = i*hei+(i+1)*5;
		var style='';
		if(arr1.length>3){
			hei =(330-(4)*5)/(3)-5 ;
			t = i*hei+(i+1)*5;
			if(i >= 3 ){
				style='style="height:'+hei+'px;top:'+(t)+'px;display:none"';
			}else{
				style='style="height:'+hei+'px;top:'+(t)+'px;"';				
			}			
		}else{
			style='style="height:'+hei+'px;top:'+t+'px;"';
		}
		var div='<div id="right_cont'+i+'" onclick="clickAjax(this,\'right\')" class="right_cont" '+style+' >';
		var inp='<input type="hidden" id="right_modeId" value="'+arr1[i]+'"/>';
		all = all + (div+inp+arr2[i]+"</div>");
	}
	if(arr1.length>3){
		var divs = "<div id='down_pig' onclick='showH(\"down\")' onmouseover='mouserE(\"down\",\"over\")' onmouseout='mouserE(\"down\",\"out\")'></div>";
		all = all + divs;
	}
	if(all == ""){
		all="结束了！";
	}
	$("div.show_right").html(all);
	$("div.show_right").fadeToggle(100);
}

function showH(f){
	var all = new Array();
	all=$("div[id^=right_cont]");
	if(f=='down'){
		var a= 0;
		for(var i=0;i<all.length;i=i+1){
			a=i;
			if($(all[i]).css("display")=="block"){
				break;
			};
		}
		if(a == 0){
			var divs = "<div id='up_pig' onclick='showH(\"up\")' onmouseover='mouserE(\"up\",\"over\")' onmouseout='mouserE(\"up\",\"out\")'></div>";
			$("#right_cont0").before(divs);
		}
		
		for(var i=0;i<all.length;i=i+1){
			var height = $(all[i]).height();
			if(a == 0){
				height =height-9;
			}
			var style="height:"+height+"px;";
			// 100
			var t = (i-a-1)*height+(i-a)*5+25;
			if(i > a && i<=a+3){
				style=style + "top:"+t+"px;display:block;"
			}else{
				style=style + "display:none;"
			}
			$(all[i]).attr("style",style);
		}
		if(a+4 == all.length){
			$("#down_pig").attr("style","display:none;");
		}
		
	}else if(f=='up'){
		var a= 0;
		for(var i=0;i<all.length;i=i+1){
			a=i;
			if($(all[i]).css("display")=="block"){
				break;
			};
		}		
		for(var i=0;i<all.length;i=i+1){
			var height = $(all[i]).height();
			
			if(a == 1){
				height =height+9;
			}
			var style="height:"+height+"px;";
			// 100
			var t = (i-a+1)*height+(i-a+1)*5+25;
			if(a == 1){
				t=t-20;
			}
			if(i >= a-1 && i<=a+1){
				style=style + "top:"+t+"px;display:block;"
			}else{
				style=style + "display:none;"
			}
			$(all[i]).attr("style",style);
		}
		if(a+3==all.length){
			$("#down_pig").attr("style","display:block;");
		}
		if(a==1){
			$("#up_pig").remove();
		}
	}
}