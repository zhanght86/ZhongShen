<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

	<link href="js/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
	<link href="js/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
	<link href="js/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
	<link href="js/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
	<!--[if IE]>
	<link href="themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
	<![endif]-->
	
	<script src="js/dwz/speedup.js" type="text/javascript"></script>
	<script src="js/dwz/jquery-1.7.2.js" type="text/javascript"></script>
	<script src="js/dwz/jquery.cookie.js" type="text/javascript"></script>
	<script src="js/dwz/jquery.validate.js" type="text/javascript"></script>
	<script src="js/dwz/jquery.bgiframe.js" type="text/javascript"></script>
	<script src="xheditor/xheditor-1.1.14-zh-cn.min.js" type="text/javascript"></script>
	<script src="uploadify/scripts/swfobject.js" type="text/javascript"></script>
	<script src="uploadify/scripts/jquery.uploadify.v2.1.0.js" type="text/javascript"></script>
	
	<!-- svg图表  supports Firefox 3.0+, Safari 3.0+, Chrome 5.0+, Opera 9.5+ and Internet Explorer 6.0+ -->
	<script type="text/javascript" src="chart/raphael.js"></script>
	<script type="text/javascript" src="chart/g.raphael.js"></script>
	<script type="text/javascript" src="chart/g.bar.js"></script>
	<script type="text/javascript" src="chart/g.line.js"></script>
	<script type="text/javascript" src="chart/g.pie.js"></script>
	<script type="text/javascript" src="chart/g.dot.js"></script>
	
	<script src="js/dwz/dwz.core.js" type="text/javascript"></script>
	<script src="js/dwz/dwz.util.date.js" type="text/javascript"></script>
	<script src="js/dwz/dwz.validate.method.js" type="text/javascript"></script>
	<script src="js/dwz/dwz.regional.zh.js" type="text/javascript"></script>
	<script src="js/dwz/dwz.barDrag.js" type="text/javascript"></script>
	<script src="js/dwz/dwz.drag.js" type="text/javascript"></script>
	<script src="js/dwz/dwz.tree.js" type="text/javascript"></script>
	<script src="js/dwz/dwz.accordion.js" type="text/javascript"></script>
	<script src="js/dwz/dwz.ui.js" type="text/javascript"></script>
	<script src="js/dwz/dwz.theme.js" type="text/javascript"></script>
	<script src="js/dwz/dwz.switchEnv.js" type="text/javascript"></script>
	<script src="js/dwz/dwz.alertMsg.js" type="text/javascript"></script>
	<script src="js/dwz/dwz.contextmenu.js" type="text/javascript"></script>
	<script src="js/dwz/dwz.navTab.js" type="text/javascript"></script>
	<script src="js/dwz/dwz.tab.js" type="text/javascript"></script>
	<script src="js/dwz/dwz.resize.js" type="text/javascript"></script>
	<script src="js/dwz/dwz.dialog.js" type="text/javascript"></script>
	<script src="js/dwz/dwz.dialogDrag.js" type="text/javascript"></script>
	<script src="js/dwz/dwz.sortDrag.js" type="text/javascript"></script>
	<script src="js/dwz/dwz.cssTable.js" type="text/javascript"></script>
	<script src="js/dwz/dwz.stable.js" type="text/javascript"></script>
	<script src="js/dwz/dwz.taskBar.js" type="text/javascript"></script>
	<script src="js/dwz/dwz.ajax.js" type="text/javascript"></script>
	<script src="js/dwz/dwz.pagination.js" type="text/javascript"></script>
	<script src="js/dwz/dwz.database.js" type="text/javascript"></script>
	<script src="js/dwz/dwz.datepicker.js" type="text/javascript"></script>
	<script src="js/dwz/dwz.effects.js" type="text/javascript"></script>
	<script src="js/dwz/dwz.panel.js" type="text/javascript"></script>
	<script src="js/dwz/dwz.checkbox.js" type="text/javascript"></script>
	<script src="js/dwz/dwz.history.js" type="text/javascript"></script>
	<script src="js/dwz/dwz.combox.js" type="text/javascript"></script>
	<script src="js/dwz/dwz.print.js" type="text/javascript"></script>
<style type="text/css">
	#splitBar{
	display:none;
	}
</style>
<script type="text/javascript">
jQuery(document).ready(function(){
  jQuery(".list tr:odd").addClass("color1");
  jQuery(".list tr:even").addClass("color2");
  jQuery(".list tr").hover(function(){
    jQuery(this).addClass("color3")
  },function(){
    jQuery(this).removeClass("color3")
  });
jQuery(".list tr:has(:checked)").addClass("ed");
//判断行是否被选中(返回布尔类型)添加样式类   // has(:checked)")   返回一个bool值  true/false

jQuery('.list tr').click(function(){
	 jQuery('.list tr').removeClass("ed");
	 jQuery(this).addClass("ed");
}
);
});


</script>

<style type="text/css">
.list {border:1px solid #333;text-align:center;}
.list th {background-color:#F0EFF0; color:#000000;}
.color1 {background-color:#F8F8F8; color:#333;}
.color2 {background-color:#ededed; color:#333;}
.color3 {background-color:#e4f5ff; color:#000000;}
.ed {background:#7cc5e5;color:#000000;}
</style>

