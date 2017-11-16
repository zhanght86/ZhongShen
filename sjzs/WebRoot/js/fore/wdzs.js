/**
 *弹框
 */
function upFile(reqURL,width,height) {
	
		$('#upfile').window({   
			width:width,   
			height:height,   
			modal:true,
			collapsible:false,
			minimizable:false, 
			maximizable:false,
			closable:true
		});
		var iframe_str = "<iframe height=\"100%\" width=\"100%\" scrolling=\"no\" frameborder=\"0\" src=\"" + reqURL + "\"></iframe>";
		$('#upfile').html(iframe_str);
	}
	

	//关闭浮动窗口
	function closeWin() {
		$('#upfile').window('close');
		window.location.reload(true);
	}



/**
 * 反选
 */
function negation(){
	$("input[type='checkbox']").each(function(){
		if($(this).attr("checked")=="checked"){
			$(this).removeAttr("checked");
		}else{
			$(this).attr("checked","checked");
		}
	});
}

function del(){
	var i=0;
	$("input[name='uploadL']").each(function(){
		if($(this).attr("checked")=="checked"){
			i = i+1;
		}
	});
	if(i==0){
		alert("请选择要删除的记录!");
		return false;
	}else{
		if(confirm("确定要删除所选择的记录吗?")){		
			var selStr = $("#dropStr").val();
			$("input[name='uploadL']").each(function(){
				if($(this).attr("checked")=="checked"){
					$("#dropStr").val(selStr+","+$(this).attr("id"));
					selStr = $("#dropStr").val();
				}
			});
			$("#deleteForm").submit();
		}
	}
}
/**
 * 初始化 
 */
function inits(){
	$.ajax({		   
		    url: "servlet/menuServlet",
		    type: "post",
			error : function(json) {
				alert(json);
				alert('出错了e~!!');
			},
			success : function(json) {
				succ(json,"rootMenu");
			}
		});
}
/** 获取数据成功后执行的方法
 *对传过来的xml文件进行解析，获取数据 
 *  
 **/
function succ(json , selectId) {
	var menus = eval(json);
	for( var i=0;i<menus.length;i=i+1){
		$("#"+selectId).append("<option value=\""+menus[i].menuId+"\" >"+menus[i].menuName+"</option>");
	}
	if(menus.length == 1){
		addMenu(menus[0].menuId);
	}
}

function addMenu(eventO){
	$(eventO).nextAll().remove();
	$.ajax({		   
	    url: "servlet/menuServlet",
	    type: "post",
	    data:{'menuParent':$(eventO).val()},
		error : function(json) {
			alert(json);
			alert('出错了e~!!');
		},
		success : function(json) {
			if(json != ""){
				var tit = $(eventO).find("option:selected").text();
				$(eventO).after("<select  size='5' onchange=\"addMenu(this)\"  id='"+$(eventO).val()+"p'><optgroup id='"+$(eventO).val()+"' label='"+tit+"'></optgroup></select>");
				succ(json,$(eventO).val());
			}			
		}
	});
}

function makeSort(){
	var sels = $("#sortList>select");
	var type=$(sels[0]).val();
	var typeN = $(sels[0]).find("option:selected").text();
	var parentId = $(sels[sels.length-1]).val();
	var parentN = $(sels[sels.length-1]).find("option:selected").text();
	if( type==null || parentId==null){
		alert("请选择分类");
	}else{
		if(type=="11111111"){
			$("input[name='type']").val("FG");
		}else if(type=="22222222"){
			$("input[name='type']").val("YJ");
		}else if(type=="33333333"){
			$("input[name='type']").val("AL");
		}else if(type=="66666666"){
			$("input[name='type']").val("EI");
		}
		$("input[name='parentId']").val(parentId);
		$("#menu").hide();
		var selFormId = $("input[name='type']").val();
		$("#sort"+selFormId).append(typeN+" >> "+parentN);
		$("#"+selFormId).show();
		$("h3>span").after(" >> 上传文档,填写信息");
	}
}

