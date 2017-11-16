/*
 * Async Treeview 0.1 - Lazy-loading extension for Treeview
 * 
 * http://bassistance.de/jquery-plugins/jquery-plugin-treeview/
 *
 * Copyright (c) 2007 Jörn Zaefferer
 *
 * Dual licensed under the MIT and GPL licenses:
 *   http://www.opensource.org/licenses/mit-license.php
 *   http://www.gnu.org/licenses/gpl.html
 *
 * Revision: $Id$
 *
 */

;(function($) {

function load(settings, root, child, container) {
	function createNode(parent) {
		var url="";
		 if(this.menuType == 1 ){//1-审计法规 
       	   url = 'system/sjfg!showsearchMessage.action?flag=0&law.lawSort=' + this.id;
			}
			if(this.menuType == 2){ // 2-定性依据 
				url = 'system/dxyj!showsearchMessage.action?flag=0&law.parentID=' + this.id ;
			}
          if(this.menuType == 3){//方法案例
          	 url = 'system/ffal!showsearchMessage.action?flag=0&ffal.sort=' + this.id;
			}
          if(this.menuType == 5){//审计事项
          	 url = 'system/dataDic!showsearchMessage.action?flag=0&dataDicDto.dicParentId=' + this.id;
			}
          if(this.menuType == 6){//审计实施方案
          	url = 'system/ssfa!showsearchMessage.action?flag=0&shiShiFangAn.sort=' + this.id;
			}
          var current = ""; 
          if(this.id =='11111111' || this.id=='22222222'||this.id=='33333333'||this.id=='44444444'||this.id=='55555555'||this.id=='66666666'){
			current = $("<li/>").attr("id", this.id || "").html("<span class='folder' ref='"+this.id+"'  pid='"+this.parentid+"'  id='"+this.id+"'  menuType='"+this.menuType+"'>" + this.text + "</span>").appendTo(parent);
		}else{
           current = $("<li/>").attr("id", this.id || "").html("<span class='folder' ref='"+this.id+"'  pid='"+this.parentid+"'  id='"+this.id+"'  menuType='"+this.menuType+"'><a href='"+url+"'target='menuList'>" + this.text + "</a></span>").appendTo(parent);
		}
//          if(this.id =='11111111' || this.id=='22222222'||this.id=='33333333'||this.id=='44444444' || this.id == '0'|| this.id=='55555555'|| this.id=='66666666'){
//        	  var current = $("<li/>").attr("id", this.id || "").html("<span class='folder' ref='"+this.id+"'  pid='"+this.parentid+"'  id='"+this.id+"'  menuType='"+this.menuType+"'></span>").appendTo(parent);	
//			}else{
//				 var current = $("<li/>").attr("id", this.id || "").html("<span class='folder' ref='"+this.id+"'  pid='"+this.parentid+"'  id='"+this.id+"'  menuType='"+this.menuType+"'><a href='"+url+"'target='menuList'>" + this.text + "</a></span>").appendTo(parent);
//			}
        //  alert("<span class='folder' ref='"+this.id+"'  pid='"+this.parentid+"'  id='"+this.id+"'  menuType='"+this.menuType+"'><a href='"+url+"'target='menuList'>" + this.text + "</a></span>");
        //  var current = $("<li/>").attr("id", this.id || "").html("<span class='folder'><a href='"+url+"'target='menuList'>" + this.text + "</a></span>").appendTo(parent);
		//  var current = $("<li/>").attr("id", this.id || "").html("<span class='folder' ref='"+this.id+"'  pid='"+this.parentid+"'  id='"+this.id+"'  menuType='"+this.menuType+"'><a href='"+url+"'target='menuList'>" + this.text + "</a></span>").appendTo(parent);
		  if (this.classes) {
			current.children("span").addClass(this.classes);
		}
		if (this.expanded) {
			current.addClass("open");
		}
		if (this.hasChildren || this.children && this.children.length) {
			var branch = $("<ul/>").appendTo(current);
			if (this.hasChildren) {
				current.addClass("hasChildren");
				createNode.call({
					classes: "placeholder",
					text: "&nbsp;",
					children:[]
				}, branch);
			}
			if (this.children && this.children.length) {
				$.each(this.children, createNode, [branch])
			}
		}
		
		
	}
	$.ajax($.extend(true, {
		url: settings.url,
		dataType: "json",
		data: {
			root: root
		},
		success: function(response) {
			child.empty();
			$.each(response, createNode, [child]);
	        $(container).treeview({add: child});
	    }
	}, settings.ajax));
	/*
	$.getJSON(settings.url, {root: root}, function(response) {
		function createNode(parent) {
			var current = $("<li/>").attr("id", this.id || "").html("<span>" + this.text + "</span>").appendTo(parent);
			if (this.classes) {
				current.children("span").addClass(this.classes);
			}
			if (this.expanded) {
				current.addClass("open");
			}
			if (this.hasChildren || this.children && this.children.length) {
				var branch = $("<ul/>").appendTo(current);
				if (this.hasChildren) {
					current.addClass("hasChildren");
					createNode.call({
						classes: "placeholder",
						text: "&nbsp;",
						children:[]
					}, branch);
				}
				if (this.children && this.children.length) {
					$.each(this.children, createNode, [branch])
				}
			}
		}
		child.empty();
		$.each(response, createNode, [child]);
        $(container).treeview({add: child});
    });
    */
}

var proxied = $.fn.treeview;
$.fn.treeview = function(settings) {
	if (!settings.url) {
		return proxied.apply(this, arguments);
	}
	var container = this;
	if (!container.children().size())
		load(settings, "source", this, container);
	var userToggle = settings.toggle;
	return proxied.call(this, $.extend({}, settings, {
		collapsed: true,
		toggle: function() {
			var $this = $(this);
			if ($this.hasClass("hasChildren")) {
				var childList = $this.removeClass("hasChildren").find("ul");
				load(settings, this.id, childList, container);
			}
			if (userToggle) {
				userToggle.apply(this, arguments);
			}
		}
	}));
};

})(jQuery);