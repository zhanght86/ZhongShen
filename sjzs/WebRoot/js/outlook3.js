$(function(){
	//InitLeftMenu();
	tabClose();
	tabCloseEven();

/*	$('#tabs').tabs({
        onSelect: function (title) {
            var currTab = $('#tabs').tabs('getTab', title);
            var iframe = $(currTab.panel('options').content);

			var src = iframe.attr('src');
			if(src)
				$('#tabs').tabs('update', { tab: currTab, options: { content: createFrame(src,title)} });
        }
    });*/
	

	 
	$('#tabs').tabs({
        onSelect: function (title) {
            if ('欢迎使用' == title) {            	
            	//changeWidth();
            }
        }
    });
   
})
/*
//初始化左侧
function InitLeftMenu() {
	$("#nav").accordion({animate:false});

    $.each(_menus.menus, function(i, n) {
		var menulist ='';
		menulist +='<ul>';
        $.each(n.menus, function(j, o) {
			menulist += '<li><div><a ref="'+o.menuid+'" href="javascript:void(0)" rel="' + o.url + '" ><span class="icon '+o.icon+'" >&nbsp;</span><span class="nav">' + o.menuname + '</span></a></div></li> ';
        })
		menulist += '</ul>';

        $('#nav').accordion('add', {
            title: n.menuname,
            content: menulist,
            iconCls: 'icon ' + n.icon
        });

    });
    */
    /*
     * 先前用于实现菜单栏初始化时全部折叠的功能
     */
/*	$('#nav').accordion('add', {
        title: "&nbsp;",
        content: "<ul id='lastmenu' style='display:none'><li><div style='background-color:#E1ECFF '><a href='null'>LastMenu</a></div></li></ul>",
        iconCls: 'icon '
    });*/

	/*
	 * 修改最后一个空白栏目的样式，使其和不存在的样式一样
	 */
/*	$("#lastmenu").parent().prev("div").children().first().next().removeClass('panel-icon').removeClass('icon');
	$("#lastmenu").parent().css("background","");
	$("#lastmenu").parent().parent().css("border-bottom","0px");
	$("#lastmenu").parent().parent().css("height","0px");
	$("#lastmenu").parent().prev("div").removeClass("panel-header").removeClass("accordion-header");*/
	//panel-header accordion-header accordion-header-selected
	/*
    $('.easyui-accordion li a').click(function(){
		var tabTitle = $(this).children('.nav').text();

		var url = $(this).attr("rel");
		var menuid = $(this).attr("ref");
		var icon = 'icon ';//getIcon(menuid,icon);

		addTab(tabTitle,url,icon);
		
		$('.easyui-accordion li div').removeClass("selected");
		$(this).parent().addClass("selected");
	}).hover(function(){
		$(this).parent().addClass("hover");
	},function(){
		$(this).parent().removeClass("hover");
	});

	//选中第一个
	var panels = $('#nav').accordion('panels');
	if(panels.length > 0){
		var t = panels[panels.length-1].panel('options').title;
	    $('#nav').accordion('select', t);
	    panels[panels.length-1].panel('collapse');
	}else{
		window.alert("你好，目前你未被授予任何权限，如果需要使用该系统处理业务，请联系管理员 ！");
	}
}
*/
/*
//获取左侧导航的图标
function getIcon(menuid){
	var icon = 'icon ';
	$.each(_menus.menus, function(i, n) {
		 $.each(n.menus, function(j, o) {
		 	if(o.menuid==menuid){
				icon += o.icon;
			}
		 })
	})
	return icon;
}
*/
var baseURL = "";
function addTab(subtitle,url,icon){
	if(!$('#tabs').tabs('exists',subtitle)){
		$('#tabs').tabs('add',{
			title:subtitle,
			content:createFrame(url,subtitle),
			closable:true,
			icon:null//icon
		});
	}else{
		//如果标签页已经存在,则重新设置原来标签页的URL，并选择标签页
		var reqPage = document.getElementById(subtitle);
		if (-1 != url.indexOf("?")) {
			url = url + "&nocache=" + new Date().getTime();
		} else {
			url = url + "?nocache=" + new Date().getTime();
		}
		reqPage.contentWindow.location.href = baseURL + url;
		$('#tabs').tabs('select',subtitle);
	}
	tabClose();
}

/**
 * 为此函数增加了一个参数，用来设置此iframe的id，此值和标签页的标题相同
 * 目的是为完成刷新标签时获得此标签页对应的iframe
 * @param url	iframe的src
 * @param subtitle iframe的subtitle
 * @return
 */
function createFrame(url,subtitle)
{
	var s = '<iframe scrolling="auto" frameborder="0"  src="waiting.jsp" id="'+subtitle+'" name="'+subtitle+'" style="width:100%;height:100%;"></iframe>';
	//在iframe加载完成之后，再刷新一次iframe，用以解决在ie6中标签创建完成之后标签页内容空白
	s += '<script type="text/javascript">reloadIframe("'+subtitle+'","'+url+'");</script>';
	return s;
}

function reloadIframe(iframeId,url) {
	if (-1 != url.indexOf("?")) {
		url = url + "&nocache=" + new Date().getTime();
	} else {
		url = url + "?nocache=" + new Date().getTime();
	}
	$('iframe[id="'+iframeId+'"]').attr("src",url)
}

function tabClose()
{
	/*双击关闭TAB选项卡*/
	$(".tabs-inner").dblclick(function(){
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close',subtitle);
	})
	/*为选项卡绑定右键*/
	$(".tabs-inner").bind('contextmenu',function(e){
		$('#mm').menu('show', {
			left: e.pageX,
			top: e.pageY
		});

		var subtitle =$(this).children(".tabs-closable").text();

		$('#mm').data("currtab",subtitle);
		$('#tabs').tabs('select',subtitle);
		return false;
	});
}
//绑定右键菜单事件
function tabCloseEven()
{
	//刷新
	$('#mm-tabupdate').click(function(){
		var currTab = $('#tabs').tabs('getSelected');
		var currTitle = currTab.panel('options').title;
		if ("欢迎使用" != currTitle) {
			//var url = $(currTab.panel('options').content).attr('src');
			var url = $("iframe[name='"+currTitle+"']").attr("src");
			$('#tabs').tabs('update',{
				tab:currTab,
				options:{
					content:createFrame(url,currTitle)
				}
			})
		}
	})
	//关闭当前
	$('#mm-tabclose').click(function(){
		var currtab_title = $('#mm').data("currtab");
		$('#tabs').tabs('close',currtab_title);
	})
	//全部关闭
	$('#mm-tabcloseall').click(function(){
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			if ("欢迎使用" != t) {
				$('#tabs').tabs('close',t);
			}
		});
	});
	//关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function(){
		$('#mm-tabcloseright').click();
		$('#mm-tabcloseleft').click();
	});
	//关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function(){
		var nextall = $('.tabs-selected').nextAll();
		if(nextall.length==0){
			//msgShow('系统提示','后边没有啦~~','error');
			//alert('后边没有啦~~');
			return false;
		}
		nextall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});
	//关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		if(prevall.length==0){
			alert('到头了，前边没有啦~~');
			return false;
		}
		prevall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			if ("欢迎使用" != t) {
				$('#tabs').tabs('close',t);
			}
		});
		return false;
	});

	//退出
/*	$("#mm-exit").click(function(){
		$('#mm').menu('hide');
	})*/
}

//弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
function msgShow(title, msgString, msgType) {
	$.messager.alert(title, msgString, msgType);
}
