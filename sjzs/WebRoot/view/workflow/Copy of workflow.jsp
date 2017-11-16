<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String pagePath = request.getRequestURL() + "";
%>
<html>
	<head>
		<base href="<%=basePath%>">
<title>null</title>
<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" type="text/css" href='css/style.css' />
	<script src="js/treeView/scripts/jquery.min.js" type="text/javascript"></script>
	<script type='text/javascript' src='js/treeView/scripts/jquery.contextmenu.r2-min.js'></script>
	<script src="js/treeView/scripts/jquery.treeview.js" type="text/javascript"></script>
	<link rel="stylesheet" href="js/treeView/css/jquery.treeview.css" />
	<link rel="stylesheet" type="text/css" href="js/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="js/themes/icon.css"/>
	<script type="text/javascript" src="js/jquery.easyui.min.1.2.2.js"></script>
	<script type="text/javascript" src='js/locale/easyui-lang-zh_CN.js'></script>
<script language='JavaScript' src="<%=basePath %>view/workflow/wz_jsgraphics.js" ></script>
<script language="javascript">
 var tool=""//1表示dot,2表示line
 var dotNum=0;//节点变量
 var nowObj;//当前节点
 var basex=0;//基础x位置
 var basey=0;//基础y位置
 var dotList="";//节点列表
 var mapWidth=0;//底图的宽度
 var mapHeight=0;//底图的高度
 var color="#000000";//线的颜色
 var lineNum=0;//连线变量
 var lineList="";//连线列表
 var lineDotList="";//连线节点列表
 var lineLikeList="";//连线连接的节点的列表
 //设置图片拖动效果
 var orgMouseX;
 var orgMouseY;
 var orgObjX;
 var orgObjY;
 //画线变量
 var lineStart=0;//开始画线的标志
 var linex=0;//连线开始横坐标
 var linry=0;//连线开始纵坐标
 var nowLineDotList="";//当前连线的节点信息
 var nowlineLikeList="";//当前连线对应节点属性
 var nowLine;//当前的连线对象
 var linejp;//连线对象
 var lineType="1";//连线类型
 var dotType="1";//节点类型
 var st=0;
 function f_move(obj){
   obj.border=1;
 }

 function f_out(obj){
   if(tool=="1"&&obj.title=="环节"){
     obj.border=1;
   }else if(tool=="2"&&obj.title=="连线"){
     obj.border=1;
   }else{
    obj.border=0;
   }
 }

 function f_dot(obj,temptype){
   document.getElementById("dot").border=0;
   document.getElementById("line").border=0;
   obj.border=1;
   tool="1";
   dotType=temptype;
 }

 function f_line(obj,temptype){
   document.getElementById("dot").border=0;
   document.getElementById("line").border=0;
   obj.border=1;
   tool="2";
   lineType=temptype;
 }
 /**
  * 点击节点
  */
 function getoffset(e)
 {
 var t=e.offsetTop;
 var l=e.offsetLeft;
 while(e=e.offsetParent)
 {
  t+=e.offsetTop;
  l+=e.offsetLeft;
 }
 var rec = new Array(1);
 rec[0]=t;
 rec[1]=l;
 return rec
}
/**
 * 不高亮显示所有节点
 */
function showAllNoLight(){
   var dots=dotList.split(",");
   for(i=3;i<dots.length;i++){
     var temp=dots[i];
     if(temp!=""){
       var tempdotType=document.getElementById("dots"+temp).dottype;
       if(!tempdotType) tempdotType="1";
       if(tempdotType=="1"){
         document.getElementById("imgs"+temp).background="<%=basePath%>images/workflow/dot.gif";
       }
//       else if(tempdotType=="2"){
//         document.getElementById("imgs"+temp).src="<%=basePath%>images/workflow/both_start.gif";
//       }else if(tempdotType=="3"){
//         document.getElementById("imgs"+temp).src="<%=basePath%>images/workflow/both_end.gif";
//       }
       else if(tempdotType=="4"){
         document.getElementById("imgs"+temp).src="<%=basePath%>images/workflow/select_start.gif";
       }else if(tempdotType=="5"){
         document.getElementById("imgs"+temp).src="<%=basePath%>images/workflow/select_end.gif";
       }
     }
   }
   document.getElementById("imgs1").background="<%=basePath%>images/workflow/start.gif";
   document.getElementById("imgs2").background="<%=basePath%>images/workflow/end.gif";
}
 /**
  * 向下
  */
 function f_down(num){
   var obj=document.getElementById("imgs"+num);
   showAllNoLight();
   if(obj.id=="imgs1"){
     obj.background="<%=basePath%>images/workflow/start_light.gif";
   }else if(obj.id=="imgs2"){
     obj.background="<%=basePath%>images/workflow/end_light.gif";
   }else{
     var tempdotType=document.getElementById("dots"+num).dottype;

     if(!tempdotType) tempdotType="1";
     if(tempdotType=="1"){
      obj.background="<%=basePath%>images/workflow/dot_light.gif";
    }
//    else if(tempdotType=="2"){
//     obj.src="<%=basePath%>images/workflow/both_start_light.gif";
//    }else if(tempdotType=="3"){
//     obj.src="<%=basePath%>images/workflow/both_end_light.gif";
//    }
    else if(tempdotType=="4"){
      obj.src="<%=basePath%>images/workflow/select_start_light.gif";
    }else if(tempdotType=="5"){
      obj.src="<%=basePath%>images/workflow/select_end_light.gif";
    }
   }
   //设置移动属性
   orgMouseX=event.clientX;
   orgMouseY=event.clientY;

   orgObjX=parseInt(obj.parentElement.style.left);
   orgObjY=parseInt(obj.parentElement.style.top);
   oldX=orgObjX;
   oldY=orgObjY;
   nowObj=obj;
   //最后设置线路信息
   if(tool=="2"){
     if(lineStart==0){
//       linex=parseInt(orgObjX+obj.parentElement.offsetWidth/2);
//       liney=parseInt(orgObjY+obj.parentElement.offsetHeight/2);
       linex=parseInt(orgObjX+obj.offsetWidth/2);
       liney=parseInt(orgObjY+obj.offsetHeight/2);
       f_drawLine();//创建图层
       lineStart=1;//已经开始画线
       nowLineDotList="line"+lineNum+";"+linex+","+liney;
       nowlineLikeList=lineNum+","+nowObj.id;
//////////////////////////////////////////////////
     ////  alert("nowlineLikeList"+nowlineLikeList);
    ////   alert("nowLineDotList"+nowLineDotList);
     }else if(lineStart==1){
//       templinex=parseInt(orgObjX+obj.parentElement.offsetWidth/2);
//       templiney=parseInt(orgObjY+obj.parentElement.offsetHeight/2);
       templinex=parseInt(orgObjX+obj.offsetWidth/2);
       templiney=parseInt(orgObjY+obj.offsetHeight/2);
       nowLineDotList=nowLineDotList+";"+templinex+","+templiney;
       nowlineLikeList+=","+nowObj.id;
       if(f_checkLine()){
       if(nowLine){
         if(lineType=="2"){
           nowLine.str="缺省边";
         }else if(lineType=="3"){
           nowLine.str="返回边";
         }else if(lineType=="4"){
           nowLine.str="跳转边";
         }
         f_drawLastLine();
       }
       lineStart=0;//已经开始画线
       linex=0;
       liney=0;
       nowLine=null;
       linejp=null;
//////////////////////////////////////////////////////////////////////////
///////获取连线的位置信息和环节的位置 
       lineDotList+="_"+nowLineDotList;
      
       lineLikeList+=";"+nowlineLikeList;
      
       nowLineDotList="";
       nowlineLikeList="";
      }else{
       nowLineDotList="";
       nowlineLikeList="";
       nowLine=null;
       linejp=null;
       lineStart=0;//已经开始画线
       linex=0;
       liney=0;
       //删除连线信息
       var newLineList="";//新的列表
       var templines=lineList.split(",");
       for(i=0;i<templines.length-1;i++){
       var temp=templines[i];
       if(temp!=""){
           newLineList+=","+temp;
         }
        }
       lineList=newLineList;
      }
     }
   }
   if(nowLine){
     (nowLine,2);//先把原来的线弄平
   }
 }

 /**
  * 判断连线是否正确
  */
 function f_checkLine(){
   //判断不允许存在循环节点
   var temps=nowlineLikeList.split(",");
   if(temps[1]==temps[2]){
     alert("添加的线路存在循环！");
     nowLine.outerHTML="";
     return false;
   }
   //判断并行汇总和并行分支环节上不能添加跳转边和返回边和默认边
    if(nowLine.linetype!="1"){//不为空
      tempNum=temps[1].substring(4,temps[1].length);
      var tempdottype=document.getElementById("dots"+tempNum).dottype;
      if(!tempdottype) tempdottype="1";
      if(tempdottype=="5"){//如果存在并行分组和并行汇总
        if(nowLine.linetype=="2"){
          alert("前驱环节上不能添加默认边！");
        }else if(nowLine.linetype=="3"){
          alert("前驱环节上不能添加返回边！");
        }else if(nowLine.linetype=="4"){
          alert("前驱环节上不能添加跳转边！");
        }
        nowLine.outerHTML="";
        return false;
      }else if(tempdottype=="4"){//如果存在并行分组和并行汇总
        if(nowLine.linetype=="3"){
          alert("前驱环节上不能添加返回边！");
          nowLine.outerHTML="";
          return false;
        }else if(nowLine.linetype=="4"){
          alert("前驱环节上不能添加跳转边！");
          nowLine.outerHTML="";
          return false;
        }
      }
      tempNum=temps[2].substring(4,temps[2].length);
      tempdottype=document.getElementById("dots"+tempNum).dottype;
      if(!tempdottype) tempdottype="1";
      if(tempdottype=="4"||tempdottype=="5"){//如果存在并行分组和并行汇总
        if(nowLine.linetype=="3"){
          alert("后继环节上不能添加返回边！");
          nowLine.outerHTML="";
          return false;
        }else if(nowLine.linetype=="4"){
          alert("后继环节上不能添加跳转边！");
          nowLine.outerHTML="";
          return false;
        }
      }
    }
   //判断原来环节里面是否已经存在当前连线
   var templines=lineLikeList.split(";");
   for(i=0;i<templines.length;i++){
    var temp=templines[i];
      if(temp!=""){
          var tempthis=temp.split(",");
          if(tempthis[1]==temps[1]&&tempthis[2]==temps[2]){
             alert("该线路已经存在！");
             nowLine.outerHTML="";
             return false;
         }
         if(temps[1]=="imgs1"&&tempthis[1]==temps[1]){
           alert("从开始环节只能引出一条输出边！");
           nowLine.outerHTML="";
           return false;
         }
         //判断只能加入一条默认边,加入
         if(lineType=="2"){//如果当前边是默认边
           tempNum=temps[1].substring(4,temps[1].length);
           var tempdottype=document.getElementById("dots"+tempNum).dottype;
           if(!tempdottype) tempdottype="1";
           if(tempdottype=="1"){
             alert("前驱环节上不能添加默认边！");
             nowLine.outerHTML="";
             return false;
           }
           if(tempdottype=="4"){
              if(tempthis[1]==temps[1]&&document.getElementById("line"+tempthis[0]).linetype=="2"){
                alert("选择分支上已经有一条默认边！");
                nowLine.outerHTML="";
                return false;
              }
           }
         }
         if(lineType=="1"){//判断无条件输出边
            if(tempthis[1]==temps[1]&&document.getElementById("line"+tempthis[0]).linetype=="1"){
               tempNum=temps[1].substring(4,temps[1].length);
               var tempdottype=document.getElementById("dots"+tempNum).dottype;
               if(!tempdottype) tempdottype="1";
               if(tempdottype=="1"||tempdottype=="5"){
                 alert("前驱环节上只能存在一条无条件输出边！");
                 nowLine.outerHTML="";
                 return false;
               }
            }
            if(tempthis[2]==temps[2]&&document.getElementById("line"+tempthis[0]).linetype=="1"){
               tempNum=temps[2].substring(4,temps[2].length);
               var tempdottype=document.getElementById("dots"+tempNum).dottype;
               if(!tempdottype) tempdottype="1";
               if(tempdottype=="1"||tempdottype=="4"){
                 alert("后继环节上只能存在一条无条件或有条件输入边！");
                 nowLine.outerHTML="";
                 return false;
               }
            }
         }
      }
   }
   if(temps[2]=="imgs1"){
     alert("不能把箭头指向开始环节！");
     nowLine.outerHTML="";
     return false;
   }
   if(temps[1]=="imgs1"&&temps[2]=="imgs2"){
     alert("不能直接连接开始环节和结束环节！");
     nowLine.outerHTML="";
     return false;
   }
   return true;
 }

 function f_checkIn(types){//判断是否在范围内
     var spans=document.getElementsByTagName("span");
     var tempx=0;
     var tempy=0;
     var tempx1=0;
     var tempy1=0;
     var thisx=event.clientX-basex;
     var thisy=event.clientY-basey
     var isIn="0";//是否包含
     var inObj;//被选中的项目
     for(i=0;i<spans.length;i++){
       tempx=parseInt(spans[i].style.left);
       tempy=parseInt(spans[i].style.top);
       tempx1=tempx+parseInt(spans[i].style.width)+6;
       tempy1=tempy+parseInt(spans[i].style.height)+6;
       if(tempx<=thisx&&thisy>=tempy&&tempx1>=thisx&&thisy<=tempy1){
          isIn="1";
          inObj=spans[i];
       }
     }
     if(isIn=="1"){
        var parents=inObj.parentElement;
        f_lineClick(parents,4);
        
       if(types=="1"){
         var isthisLine="0";
         var tempLinetype=parents.linetype;
         if(!tempLinetype) tempLinetype="1";
          var templines=lineLikeList.split(";");
          for(i=0;i<templines.length;i++){
            var temp=templines[i];
            if(temp!=""){
              var tempthis=temp.split(",");
              if("line"+tempthis[0]==parents.id){//如果找到了当前连线
                 tempNum=tempthis[1].substring(4,tempthis[1].length);
                 var memoB=document.getElementById("imgs"+tempthis[1].substring(4,tempthis[1].length)).setmemo;
                 var memoE=document.getElementById("imgs"+tempthis[2].substring(4,tempthis[2].length)).setmemo;
                if(tempthis[1].substring(4,tempthis[1].length)==1){
                	memoB="*tache_name=开始*";
                }
                if(tempthis[2].substring(4,tempthis[1].length)==2){
                	memoE="*tache_name=结束*";
                }
                 var memo=document.getElementById(parents.id).setmemo;
                 var t = memoB+','+memoE+','+memo;   //开始节点，结束节点，连接线属性
				 t=encodeURI(t=encodeURI(t));   
                 var url="system/workflow!toSetLineInfo.action?linePropties="+t
		    	 var _sfeatures ="dialogWidth=800px;dialogHeight=500px;top=0;left=0;toolbar=no;location=no;titlebar=no;menubar=no";
		    	 var info=window.showModalDialog(url,"",_sfeatures);
		    	 if(info!=null){
		    	 	document.getElementById(parents.id).setmemo=info;
		    	 }
		    	 
		    	 
                 var tempdottype=document.getElementById("dots"+tempNum).dottype;
                 if(!tempdottype) tempdottype="1";
                 if(tempdottype=="4"){//如果开始环节是逻辑分支
                   isthisLine="1";
                 }
                 
			       
              }
            }
          }
         if(tempLinetype=="1"&&isthisLine=="1"){//如果是普通边，并且开始环节是选择分支逻辑节点,那么弹出属性
           f_showLineInfo(parents);
         }
       }
      
       
       showAllNoLight();
       nowObj=null;
     }
 }

 function f_showLineInfo(obj){
    
 }
 //开始画节点
 function f_drawDot(){
    dotNum=dotNum+1;
    var x=event.clientX;
    var y=event.clientY;
    x=x-basex-5;
    y=y-basey-5;
    var setImg="";
    var bgImg="";
    var thisWidth="";
    var thisHeight="";
    var dotHtml="";
    if(dotType=="1"){
      setImg="dot.gif";
      bgImg="dotbg.gif";
      thisWidth="80";
      thisHeight="50";
      dotHtml="<div id=\"dots"+dotNum+"\" setmemo='' style=\"position:absolute;left:"+x+";top:"+y+";z-index:"+(500+dotNum)+" \" ondrag='f_doDrag(this);' ondragend='f_dragEnd(this)'  ondragstart='f_dragStart(this)'><table  id=\"imgs"+dotNum+"\" background=\"<%=basePath%>images/workflow/"+setImg+"\" style=\"cursor:hand;\" width="+thisWidth+" height="+thisHeight+" border=0><tr><td align=\"center\" vlign=\"center\" id=\"td"+dotNum+"\"></td></tr></table><img onmousedown=\"f_down("+dotNum+")\" ondblclick=\"f_showProp("+dotNum+")\" style=\"position:absolute;left:0;top:0;\" src=\"<%=basePath%>images/workflow/dotbg.gif\" border=0></div>";
    }
//    else if(dotType=="2"){
//      setImg="both_start.gif";
//      dotHtml="<div id=\"dots"+dotNum+"\" setmemo='' style=\"position:absolute;left:"+x+";top:"+y+";z-index:"+(500+dotNum)+" \" ondrag='f_doDrag(this);' ondragend='f_dragEnd(this)'  ondragstart='f_dragStart(this)'><img src=\"<%=basePath%>images/workflow/"+setImg+"\" id=\"imgs"+dotNum+"\"  onmousedown=\"f_down("+dotNum+")\" ondblclick=\"f_showProp("+dotNum+")\"><br><span id=\"td"+dotNum+"\" align=\"center\"></span></div>";
//    }else if(dotType=="3"){
//      setImg="both_end.gif";
//      dotHtml="<div id=\"dots"+dotNum+"\" setmemo='' style=\"position:absolute;left:"+x+";top:"+y+";z-index:"+(500+dotNum)+" \" ondrag='f_doDrag(this);' ondragend='f_dragEnd(this)'  ondragstart='f_dragStart(this)'><img src=\"<%=basePath%>images/workflow/"+setImg+"\" id=\"imgs"+dotNum+"\"  onmousedown=\"f_down("+dotNum+")\" ondblclick=\"f_showProp("+dotNum+")\"><br><span id=\"td"+dotNum+"\" align=\"center\"></span></div>";
//    }
    else if(dotType=="4"){
      setImg="select_start.gif";
      dotHtml="<div id=\"dots"+dotNum+"\" setmemo='' style=\"position:absolute;left:"+x+";top:"+y+";z-index:"+(500+dotNum)+" \" ondrag='f_doDrag(this);' ondragend='f_dragEnd(this)'  ondragstart='f_dragStart(this)'><img src=\"<%=basePath%>images/workflow/"+setImg+"\" id=\"imgs"+dotNum+"\"  onmousedown=\"f_down("+dotNum+")\" ondblclick=\"f_showProp("+dotNum+")\"><br><span id=\"td"+dotNum+"\" align=\"center\"></span></div>";
    }else if(dotType=="5"){
      setImg="select_end.gif";
      dotHtml="<div id=\"dots"+dotNum+"\" setmemo='' style=\"position:absolute;left:"+x+";top:"+y+";z-index:"+(500+dotNum)+" \" ondrag='f_doDrag(this);' ondragend='f_dragEnd(this)'  ondragstart='f_dragStart(this)'><img src=\"<%=basePath%>images/workflow/"+setImg+"\" id=\"imgs"+dotNum+"\"  onmousedown=\"f_down("+dotNum+")\" ondblclick=\"f_showProp("+dotNum+")\"><br><span id=\"td"+dotNum+"\" align=\"center\"></span></div>";
    }
    document.getElementById("div_map").innerHTML+=dotHtml;
    document.getElementById("dots"+dotNum).dottype=dotType;//设置属性
//    if(dotType=="2"){
//      document.getElementById("td"+dotNum).innerHTML="并行分支";
//    }else if(dotType=="3"){
//      document.getElementById("td"+dotNum).innerHTML="并行汇聚";
//    }else
    if(dotType=="4"){
      document.getElementById("td"+dotNum).innerHTML="逻辑分支";
    }else if(dotType=="5"){
      document.getElementById("td"+dotNum).innerHTML="逻辑汇聚";
    }
    dotList+=","+dotNum;
 }
 /**
  * 设置拖动
  */
 function f_doDrag(obj){
   var x=event.clientX;
   var y=event.clientY;
   obj.style.left=x-(orgMouseX-orgObjX);
   obj.style.top=y-(orgMouseY-orgObjY);
 }
 /**
  * 获得当前节点的前驱节点的列表
  */
  var tempDotNum="";
// function f_getFatherDot(num){
//   var tempLineLinks=lineLikeList.split(";");//计算当前节点的前驱节点
//   var allnum="";
//   for(i=0;i<tempLineLinks.length;i++){
//     var tempLinks=tempLineLinks[i];
//     if(tempLinks!=""){//如果对象不为空
//       var temps=tempLinks.split(",");
//       alert("tempLinks="+tempLinks+" temps[2]="+temps[2]+"  num="+num);
//       if(temps[2]=="imgs"+num&&temps[1]!="imgs1"){
//         alert("tempDotNum="+tempDotNum);
//         alert("temps="+temps[1]);
//         if(tempDotNum.lastIndexOf(temps[1])==-1){
//         allnum+=","+temps[1];//开始节点
//         tempDotNum+=","+temps[1];
//         tempNum=temps[1].substring(4,temps[1].length);
//         allnum+=","+f_getFatherDot(tempNum);
//
//         }
//       }
//     }
//   }
//   return allnum;
// }
//
function f_getallLine(){
   var tempLineLinks=lineLikeList.split(";");//计算当前节点的前驱节点
   var tempthis="";
   for(i=0;i<tempLineLinks.length;i++){
     var tempLinks=tempLineLinks[i];
     if(tempLinks!=""){//如果对象不为空
       var temps=tempLinks.split(",");
       var tempLinetype=document.getElementById("line"+temps[0]).linetype;
       if(!tempLinetype) tempLinetype="1";
       if(tempLinetype=="1"){
         tempthis+=";"+tempLinks;
       }
     }
   }
   return tempthis;
 }

 /**
  * 获得父节点名
  */
 function f_getFatherDotName(){
   var allName="";
   var tempDots=dotList.split(",");
   for(i=0;i<tempDots.length;i++){
     if(tempDots[i]!=""){
       var name=document.getElementById("td"+tempDots[i]).innerHTML;
       var tempdottype=document.getElementById("dots"+tempDots[i]).dottype;
       if(!tempdottype) tempdottype="1";
       if(tempdottype=="1"){
         if(name=="") name=" ";
         allName+=";"+tempDots[i]+","+name;
       }
     }
   }
   return allName;
 }
 
 
	function getModelValue(tempInfo, mes) {
		var props = tempInfo.split("*");
		var code = "";
		for ( var i = 1; i < props.length; i++) {
			var prop = props[i];
			var key = prop.substring(0, prop.indexOf("="));
			var value = prop.substring(prop.indexOf("=") + 1);
			if (mes==key) {
				code = value;
			}
		}
		return code;
	}
 /**
  * 显示环节属性
  */
 function f_showProp(num){//把单元格属性构造成大字段,便于字段的扩展

    var tempmemo=document.getElementById("imgs"+num).setmemo;
    if(!tempmemo) tempmemo="";
    if(tempmemo!="") tempmemo=tempmemo;
    tempDotNum="";
    var dottype=document.getElementById("dots"+num).dottype;
    if(!dottype) dottype="1";
    if(dottype=="1"){
    var tempallLine=f_getallLine();
    allName=f_getFatherDotName();
    allName=encodeURI(allName=encodeURI(allName));      //
    var memo=encodeURI(tempmemo=encodeURI(tempmemo)); 
    //var url="workflow!toAddTacheInfo.action?lineLikeList="+tempallLine+"&allName="+allName+"&num="+num+"&tachememo="+memo+"&time="+new Date().getTime();
    var url="<%=basePath %>system/workflow!toTachePro.action?lineLikeList="+tempallLine+"&allName="+allName+"&num="+num+"&tachememo="+memo+"&type=${type}&time="+new Date().getTime();
    var menu ="dialogWidth=800px;dialogHeight=500px;top=0;left=0;toolbar=no;location=no;titlebar=no;menubar=no";
    var info=window.showModalDialog(url,null,menu);   
    if(info!=null){
      var tache_name=getModelValue(info,"tache_name");
      document.getElementById("imgs"+num).setmemo=info;
      document.getElementById("td"+num).innerHTML=tache_name;
    }
   }
 }
 
 
 /**
  * 当环节移动时环节上的连线移动的情况
  */
 function f_drageLine(obj){
   var tempLines="";//相关节点
   //与节点关的连线信息
   var ids=obj.id;
   ids="imgs"+ids.substring(4,ids.length);
   var templikes=lineLikeList.split(";");
   for(i=0;i<templikes.length;i++){
     var temp=templikes[i];
     if(temp!=""){
       var tempItems=temp.split(",");
       if(tempItems.length>=3){
         if(tempItems[1]==ids){
           tempLines+=";"+tempItems[0]+",1";
         }else if(tempItems[2]==ids){
           tempLines+=";"+tempItems[0]+",2";
         }
       }
     }
   }
   var newCenterX=parseInt(parseInt(obj.style.left)+document.getElementById(ids).offsetWidth/2);
   var newCenterY=parseInt(parseInt(obj.style.top)+document.getElementById(ids).offsetHeight/2);
   var tempLineList=tempLines.split(";");
   for(i=0;i<tempLineList.length;i++){
     if(tempLineList[i]!=""){
       var tempthis=tempLineList[i].split(",");
       f_moveOneLine(tempthis[0],tempthis[1],newCenterX,newCenterY);
     }
   }
 }
 /**
  * 完成拖动
  */
 function f_dragEnd(obj){
   f_drageLine(obj);
 }
 /**
  * 开始拖动
  */
 function f_dragStart(obj){
   var tempLines="";//相关节点
   //与节点关的连线信息
   var ids=obj.id;
   ids="imgs"+ids.substring(4,ids.length);
   var templikes=lineLikeList.split(";");
   for(i=0;i<templikes.length;i++){
     var temp=templikes[i];
     if(temp!=""){
       var tempItems=temp.split(",");
       if(tempItems.length>=3){
         if(tempItems[1]==ids){
           document.getElementById("line"+tempItems[0]).innerHTML="";
         }else if(tempItems[2]==ids){
           document.getElementById("line"+tempItems[0]).innerHTML="";
         }
       }
     }
   }
 }
 /**
  * 移动一条线路
  */
 function f_moveOneLine(tempLine,lineNo,newCenterX,newCenterY){
   var tempLineDots=lineDotList.split("_");
   var newlineDotList="";//临时对象
   for(s=0;s<tempLineDots.length;s++){
     if(tempLineDots[s]!=""){
      var temps=tempLineDots[s].split(";");
      if(temps[0]=="line"+tempLine){//如果是当前线路
       nowLineDotList=temps[0];
       if(lineNo==1){//如果移动的是开始节点
         nowLineDotList+=";"+newCenterX+","+newCenterY;
         for(m=2;m<temps.length;m++){
           nowLineDotList+=";"+temps[m];
         }
       }else{//如果移动的是后来的节点
         for(m=1;m<temps.length-1;m++){
           nowLineDotList+=";"+temps[m];
         }
         nowLineDotList+=";"+newCenterX+","+newCenterY;
       }
       //完成设置nowLineDotList
       //设置nowLine
       nowLine=document.getElementById("line"+tempLine);
       linejp=new jsGraphics("line"+tempLine);
       linejp.setStroke(2);
       f_drawLastLine();//画变化后的线
       newlineDotList+="_"+nowLineDotList;
      }else{
        newlineDotList+="_"+tempLineDots[s];
      }
     }
   }
   lineDotList=newlineDotList;
 }

 //开始画连线
 function f_drawLine(){
    lineNum=lineNum+1;
    var lineHtml="<div id=\"line"+lineNum+"\"  style=\"position:absolute;left:0;top:0;width:"+mapWidth+";height:"+mapHeight+";z-index:"+lineNum+"\" ></div>";
    document.getElementById("div_map").innerHTML+=lineHtml;
    lineList+=","+lineNum;
    nowLine=document.getElementById("line"+lineNum);//当前连线的图层属性
    nowLine.linetype=lineType;
    linejp=new jsGraphics("line"+lineNum);
 }
 /**
  * 高亮显示线路
  */
 function f_highLight(types){
   //设置连线加亮
   if(lineStart==0){//如果是正在画线，
   
   var isIn="1";
   var dots=dotList.split(",");
   var thisx=event.clientX-basex;
   var thisy=event.clientY-basey
   for(i=0;i<dots.length;i++){
     var temp=dots[i];
     if(temp!=""){
       var obj=document.getElementById("imgs"+temp);
       if(obj){
         var tempx=parseInt(obj.parentElement.style.left);
         var tempy=parseInt(obj.parentElement.style.top);
         var tempx1=tempx+parseInt(obj.parentElement.offsetWidth);
         var tempy1=tempy+parseInt(obj.parentElement.offsetHeight);
         if(tempx<=thisx&&thisy>=tempy&&tempx1>=thisx&&thisy<=tempy1){
          isIn="0";
		  st=0;
         }
       }
     }
   }
   if(isIn=="1"){
     if(nowLine){
     
       f_lineClick(nowLine,2);//先把原来的线弄平
     }

     f_checkIn(types);
   }
  }
 }

 /**
  * 设置连线属性
  */
 function f_dblClickLine(){
   f_highLight("1")
 }
 /**
  * 画折线
  */
 function f_drawOutLine(){
   if(lineStart==1){//如果是正在画线，
      linex=event.clientX-basex;
      liney=event.clientY-basey;
      nowLineDotList+=";"+(linex)+","+(liney);
   }
 }
 //开始画图
 function f_draw(){
   if(tool=="1"){//节点
     f_drawDot();
    }
    f_highLight("0");//高亮显示线路
    f_drawOutLine();//画折线
 }
 //画流程图
function f_mapUp(){
   if(event.button == 2 || event.button == 3){//取消选择
     document.getElementById("dot").border=0;
     document.getElementById("line").border=0;
     tool="";
   }
}

document.oncontextmenu = nocontextmenu;
function nocontextmenu()
{
    if(lineStart==1){//如果是正在画线，
      //删除连线信息
       var newLineList="";//新的列表
       var templines=lineList.split(",");
       for(i=0;i<templines.length-1;i++){
       var temp=templines[i];
       if(temp!=""){
           newLineList+=","+temp;
         }
        }
      lineList=newLineList;
      nowLine.outerHTML="";
      nowLineDotList="";
      lineStart=0;
    }
  event.cancelBubble = true
  event.returnValue = false;
  return false;
}

function f_getBase(){
   var offset=getoffset(document.getElementById("div_map"));
   basex=offset[1];
   basey=offset[0];
   mapWidth=document.getElementById("div_map").offsetWidth;
   mapHeight=document.getElementById("div_map").offsetHeight;
   //添加开始节点和结束节点
   dotNum=dotNum+1;
   x=basex+20;
   y=basey+100;
   var dotHtml="<span id=\"flowName\" style=\"LINE-HEIGHT:40px;font-size:30pt;FONT-FAMILY: 楷体_GBK;position:absolute;left:"+((mapWidth)/2-100)+";top:5;\"></span>";
   document.getElementById("div_map").innerHTML+=dotHtml;
   dotHtml="<div id=\"dots"+dotNum+"\" style=\"position:absolute;left:"+x+";top:"+y+";z-index:"+(500+dotNum)+" \" ondrag='f_doDrag(this);' ondragend='f_dragEnd(this)' ondragstart='f_dragStart(this)'><table  id=\"imgs"+dotNum+"\" background=\"<%=basePath%>images/workflow/start.gif\" style=\"cursor:hand;\" width=47 height=47 border=0><tr><td align=\"center\" vlign=\"center\" id=\"td"+dotNum+"\">开始</td></tr></table><img onmousedown=\"f_down("+dotNum+")\" style=\"position:absolute;left:0;top:0;\" src=\"<%=basePath%>images/workflow/startbg.gif\" border=0></div>";
   document.getElementById("div_map").innerHTML+=dotHtml;
   dotList+=","+dotNum;
   //添加结束节点
   dotNum=dotNum+1;
   x=basex+600;
   y=basey-10;
   dotHtml="<div id=\"dots"+dotNum+"\" style=\"position:absolute;left:"+x+";top:"+y+";z-index:"+(500+dotNum)+" \" ondrag='f_doDrag(this);' ondragend='f_dragEnd(this)' ondragstart='f_dragStart(this)'><table  id=\"imgs"+dotNum+"\" background=\"<%=basePath%>images/workflow/end.gif\" style=\"cursor:hand;\" width=47 height=47 border=0><tr><td align=\"center\" vlign=\"center\" id=\"td"+dotNum+"\">结束</td></tr></table><img onmousedown=\"f_down("+dotNum+")\" style=\"position:absolute;left:0;top:0;\" src=\"<%=basePath%>images/workflow/startbg.gif\" border=0></div>";
   document.getElementById("div_map").innerHTML+=dotHtml;
   dotList+=","+dotNum;
   //初始化流程
   initAll();
   document.getElementById("flowName").innerHTML="<b>"+document.forms[0].elements["flow.flowName"].value+"</b>";
}
/**
 * 删除当前节点
 */
function f_deleteDot(){
  if(nowObj){
   if(nowObj.id=="imgs1"){
     alert("不能删除开始节点");
     return false;
   }
   if(nowObj.id=="imgs2"){
     alert("不能删除结束节点");
     return false;
   }
   
   //清理节点列表
   var dots=dotList.split(",");
   var newList="";//新的列表
   for(i=0;i<dots.length;i++){
     var temp=dots[i];
     if(temp!=""){
       if(nowObj!=document.getElementById("imgs"+temp)){
         newList+=","+temp;
       }
     }
   }
   dotList=newList;
   var deleteLine="";
   //删除与节点关的连线信息
   var templikes=lineLikeList.split(";");
   for(i=0;i<templikes.length;i++){
     var temp=templikes[i];
     if(temp!=""){
       var tempItems=temp.split(",");
       if(tempItems.length>=3){
         if(tempItems[1]==nowObj.id||tempItems[2]==nowObj.id){
           deleteLine+=","+tempItems[0];
         }
       }
     }
   }
   if(deleteLine!=""){
     var delLines=deleteLine.split(",");
     for(m=0;m<delLines.length;m++){
       var temp=delLines[m];
       if(temp!=""){//不为空
          nowLine=document.getElementById("line"+temp);
          f_deleteLine();
       }
     }
   }
   //完成连线删除
   nowObj.parentElement.outerHTML="";//把整个对象删除了;
   nowObj=null;
  }else if(nowLine){//如果当前连线不为空
    //删除连线信息
    f_deleteLine();
  }
}
/**
 * 删除连线
 */
function f_deleteLine(){
    var newLineList="";//新的列表
    var templines=lineList.split(",");
    for(i=0;i<templines.length;i++){
     var temp=templines[i];
     if(temp!=""){
       if(nowLine!=document.getElementById("line"+temp)){
         newLineList+=","+temp;
       }
     }
   }
   lineList=newLineList;
   //设置连线节点信息
   var newlineDotList="";
   var tempdots=lineDotList.split("_");
  
   for(i=0;i<tempdots.length;i++){
     var temp=tempdots[i];
     if(temp!=""){
       var tempItems=temp.split(";");
       if(tempItems.length>0){
         if(nowLine!=document.getElementById(tempItems[0]))
         newlineDotList+="_"+temp;
       }
     }
   }
   lineDotList=newlineDotList;

   //删除连线节点信息
   var newlineLikeList="";
   var templikes=lineLikeList.split(";");
   for(i=0;i<templikes.length;i++){
     var temp=templikes[i];
     if(temp!=""){
       var tempItems=temp.split(",");
       if(tempItems.length>0){
         if(nowLine!=document.getElementById("line"+tempItems[0]))
          newlineLikeList+=";"+temp;
       }
     }
   }

   lineLikeList=newlineLikeList;
   nowLine.outerHTML="";
   nowLine=null;
}
//节点上移动
function f_objMove(type){
    if(nowObj){
      var top=parseInt(nowObj.parentElement.style.top);
      var left=parseInt(nowObj.parentElement.style.left);
      if(type==1){//向上
       if(top>0){
        nowObj.parentElement.style.top=top-1;
       }
      }else if(type==2){//向下
        if(top<mapHeight-nowObj.offsetHeight){
          nowObj.parentElement.style.top=top+1;
        }
      }else if(type==3){//向左
        if(left>0){
          nowObj.parentElement.style.left=left-1;
        }
      }else if(type==4){//向右s
        if(left<mapWidth-nowObj.offsetWidth){
          nowObj.parentElement.style.left=left+1;
        }
      }
      f_drageLine(nowObj.parentElement);
    }
}

function f_keyDown(){
  //alert(event.keyCode);
  switch(event.keyCode){
    case 46:f_deleteDot();break;//删除节点
    case 38:f_objMove(1);break;//向上移动
    case 40:f_objMove(2);break;//向下移动
    case 37:f_objMove(3);break;//向左移动
    case 39:f_objMove(4);break;//向右移动
  }
}
document.onkeydown=f_keyDown;
//画最后的连线
function f_drawLastLine(){
   var startx=-1;
   var starty=-1;
   var endx=-1;
   var endy=-1;
   var laststartx=0;
   var laststarty=0;
   var lastendx=0;
   var lastendy=0;
    nowLine.innerHTML="";
    linejp.setColor("#ff0000");
    var temps=nowLineDotList.split(";");
    if(temps.length>=2){
       for(j=1;j<temps.length;j++){
           var tempdots=temps[j].split(",");
            if(tempdots.length==2){
               if(startx==-1){
                 startx=tempdots[0];
                 starty=tempdots[1];
               }else{
                 endx=tempdots[0];
                 endy=tempdots[1];
                 linejp.drawLine(parseInt(startx),parseInt(starty),parseInt(endx),parseInt(endy));
                 laststartx=parseInt(startx);
                 laststarty=parseInt(starty);
                 lastendx=parseInt(endx);
                 lastendy=parseInt(endy);
                 startx=tempdots[0];
                 starty=tempdots[1];
                 endx=-1;
                 endy=-1;
               }
             }
      }
    }
    f_drawroundArrowhead(laststartx,lastendx,laststarty,lastendy,nowLine);
    linejp.paint();
}
/**
 * 当鼠标移动的时候开始画线
 */
function f_moveLine(){
   var startx=-1;
   var starty=-1;
   var endx=-1;
   var endy=-1;
   var laststartx=0;
   var laststarty=0;
   var lastendx=0;
   var lastendy=0;
  if(tool=="2"&&nowLine&&lineStart==1){
    nowLine.innerHTML="";
    linejp.setColor("#ff0000");
    var temps=nowLineDotList.split(";");
    if(temps.length>=2){
       for(j=1;j<temps.length;j++){
           var tempdots=temps[j].split(",");
            if(tempdots.length==2){
               if(startx==-1){
                 startx=tempdots[0];
                 starty=tempdots[1];
               }else{
                 endx=tempdots[0];
                 endy=tempdots[1];
                 linejp.drawLine(parseInt(startx),parseInt(starty),parseInt(endx),parseInt(endy));
                 laststartx=parseInt(startx);
                 laststarty=parseInt(starty);
                 lastendx=parseInt(endx);
                 lastendy=parseInt(endy);
                 startx=tempdots[0];
                 starty=tempdots[1];
                 endx=-1;
                 endy=-1;
               }
             }
      }
    }
    linejp.drawLine(linex,liney,event.clientX-basex,event.clientY-basey);
    linejp.paint();
  }
}
/**
 * 点击连线
 */
function f_lineClick(obj,size){
   var startx=-1;
   var starty=-1;
   var endx=-1;
   var endy=-1;
   var father=obj;
   var laststartx=0;
   var laststarty=0;
   var lastendx=0;
   var lastendy=0;
   father.innerHTML="";
   linejp=new jsGraphics(father.id);
   var id =father.id;
   linejp.setColor("#ff0000");
   linejp.setStroke(size);
   var tempLists=lineDotList.split("_");
   for(i=0;i<tempLists.length;i++){
     var tempThisLine=tempLists[i];
     if(tempThisLine!=""){
       var temps=tempThisLine.split(";");
       if(temps.length>=3){
        if(temps[0]==father.id){
           for(j=1;j<temps.length;j++){
             var tempdots=temps[j].split(",");
             if(tempdots.length==2){
               //linejp.drawLine();
               if(startx==-1){
                 startx=tempdots[0];
                 starty=tempdots[1];
               }else{
                 endx=tempdots[0];
                 endy=tempdots[1];
                 linejp.drawLine(parseInt(startx),parseInt(starty),parseInt(endx),parseInt(endy));
                 laststartx=parseInt(startx);
                 laststarty=parseInt(starty);
                 lastendx=parseInt(endx);
                 lastendy=parseInt(endy);
                 startx=tempdots[0];
                 starty=tempdots[1];
              
                
               }
             }
          }
         }
       }
     }
   }
   f_drawroundArrowhead(laststartx,lastendx,laststarty,lastendy,father);
   linejp.paint();
   //设置当前对象
   nowLine=father;//设置当前对象
}

//画箭头，表示流程方向
function f_drawroundArrowhead(tempstartx,tempendx,tempstarty,tempendy,obj){
var endwith=70;
var endheight=42;
var xx=0,yy=0,xx1=0,yy1=0,xx2=0,yy2=0;
var myWidth=0;
var myHeight=0;
var tempstr=obj.str;
if(!tempstr) tempstr="";
var nowstoke=linejp.getStroke();
tempstartx=tempstartx+nowstoke/2;
tempstarty=tempstarty+nowstoke/2;
tempendx=tempendx+nowstoke/2;
tempendy=tempendy+nowstoke/2;

myHeight=Math.pow((Math.pow(endwith,2)+Math.pow(endheight,2)),1/2)/2;
myWidth=myHeight;
xx=tempendx-(tempendx-tempstartx)*myWidth/Math.pow((tempendx-tempstartx)*(tempendx-tempstartx)+(tempendy-tempstarty)*(tempendy-tempstarty),1/2);
yy=tempendy-(tempendy-tempstarty)*myHeight/(Math.pow((tempendx-tempstartx)*(tempendx-tempstartx)+(tempendy-tempstarty)*(tempendy-tempstarty),1/2));
xx1=xx-15*Math.cos(Math.atan((yy-tempstarty)/(xx-tempstartx))-radians(20));
yy1=yy-15*Math.sin(Math.atan((yy-tempstarty)/(xx-tempstartx))-radians(20));
xx2=xx-15*Math.sin(radians(70)-Math.atan(((yy-tempstarty))/((xx-tempstartx))));
yy2=yy-15*Math.cos(radians(70)-Math.atan(((yy-tempstarty))/((xx-tempstartx))));
if(tempstartx>tempendx) {
  xx1=xx+15*Math.cos(Math.atan((yy-tempstarty)/(xx-tempstartx))-radians(20));
  yy1=yy+15*Math.sin(Math.atan((yy-tempstarty)/(xx-tempstartx))-radians(20));
  xx2=xx+15*Math.sin(radians(70)-Math.atan(((yy-tempstarty))/((xx-tempstartx))));
  yy2=yy+15*Math.cos(radians(70)-Math.atan(((yy-tempstarty))/((xx-tempstartx))));
}
 var Xpoints =new Array(parseInt(xx2),parseInt(xx1),parseInt(xx));
 var Ypoints = new Array(parseInt(yy2),parseInt(yy1),parseInt(yy));
 linejp.fillPolygon(Xpoints,Ypoints); //填充多边形

 //添加注释

 if(tempstr!="")
  {
    linejp.setColor("#000000");
    linejp.drawString(tempstr,parseInt(((tempstartx+tempendx)/2)-1),parseInt(((tempstarty+tempendy)/2)-1));
    linejp.setColor("#ff0000");
  }
}

function radians(degrees){
  return (degrees)*Math.PI/180.0;
}
/**
 * 检验工作流程是否正确
 */
function f_checkall(types){
  //如果环节没有连线信息
  var error="";
  var noName="0";
  //取得所有入口环节信息
   var inputDot=",";//入口节点
   var outputDot=",";//出口节点
   var templines=lineLikeList.split(";");
   if(lineLikeList.length==0){
     alert("流程需要至少存在一条路由线路！");
     return false;
   }
   for(i=0;i<templines.length;i++){
    var temp=templines[i];
      if(temp!=""){
          var tempthis=temp.split(",");
          if(inputDot.lastIndexOf(","+tempthis[1]+",")==-1){
            inputDot+=tempthis[1]+",";
          }
          if(outputDot.lastIndexOf(","+tempthis[2]+",")==-1){
            outputDot+=tempthis[2]+",";
          }
      }
   }
  //判断是不是所有节点都既有出口信息，又有入口信息
  var dots=dotList.split(",");
  for(i=3;i<dots.length;i++){
    if(document.getElementById("td"+dots[i]).innerHTML==""&&noName=="0"){
      noName="1";
    }
    if(inputDot.lastIndexOf(",imgs"+dots[i]+",")==-1){
      error+="“"+document.getElementById("td"+dots[i]).innerHTML+"”环节没有输出边\r\n";
    }
    if(outputDot.lastIndexOf(",imgs"+dots[i]+",")==-1){
      error+="“"+document.getElementById("td"+dots[i]).innerHTML+"”环节没有输入边\r\n";
    }
    var tempdottype=document.getElementById("dots"+dots[i]).dottype;
    if(!tempdottype) tempdottype="1";
    if(tempdottype=="4"){
        var tempdefault="0";
        var tempnodefine="0"
        var allLinecounts=0;
        for(j=0;j<templines.length;j++){
        var temp=templines[j];
        if(temp!=""){
          var tempthis=temp.split(",");
          if(tempthis[1]=="imgs"+dots[i]){
             allLinecounts=allLinecounts+1;
             var tempstr=document.getElementById("line"+tempthis[0]).str;
             if(!tempstr) tempstr="";
             var templinetype=document.getElementById("line"+tempthis[0]).linetype;
             if(!templinetype) templinetype="1";
             if(tempstr==""){
               tempnodefine="1";
             }
             if(templinetype=="2"){
               tempdefault="1";
             }
          }
        }
      }
      if(tempnodefine=="1"){
        error+="“"+document.getElementById("td"+dots[i]).innerHTML+"”至少有一条边没有定义条件\r\n";
      }
      if(tempdefault=="0"){
        error+="“"+document.getElementById("td"+dots[i]).innerHTML+"”需要至少一条默认边\r\n";
      }
      if(allLinecounts<=1){
        error+="“"+document.getElementById("td"+dots[i]).innerHTML+"”至少缺少一条输出边\r\n";
      }
    }
  }

  if(noName=="1"){
    alert("至少有一个环节没有定义“环节属性”！");
    return false;
  }else{
    if(error!=""){
      alert(error);
      return false;
    }
  }
  if(types=="0"){
    alert("流程正确！");
  }
  return true;
}
//完成画线
</script>
<script language="javascript">
 function f_save(){
    if(f_checkall(1)){
      document.forms[0].elements["flow.lineDotList"].value=lineDotList;
      document.forms[0].elements["flow.lineLikeList"].value=lineLikeList;
	   
      var dotProp="";//节点属性
      var tempdots=dotList.split(",");
      //获取到节点的属性
      for(i=0;i<tempdots.length;i++){
        var tempItem=tempdots[i];
        if(tempItem!=""){
          var left=parseInt(document.getElementById("dots"+tempItem).style.left);
          var top=parseInt(document.getElementById("dots"+tempItem).style.top);
          var memo=document.getElementById("imgs"+tempItem).setmemo;
          if(!memo) memo="";
          var str=document.getElementById("td"+tempItem).innerHTML;
          var tempdottype=document.getElementById("dots"+tempItem).dottype;
          if(!tempdottype) tempdottype="1";
          dotProp+=";"+tempItem+","+left+","+top+","+memo+","+str+","+tempdottype;
        }
      }
      var lineProp="";//获取连线属性
      var templines=lineList.split(",");
      for(i=0;i<templines.length;i++){
        var tempItem=templines[i];
        if(tempItem!=""){
          var memo=document.getElementById("line"+tempItem).setmemo;
          if(!memo) memo="";
          var tempstr=document.getElementById("line"+tempItem).str;
          if(!tempstr) tempstr="";
          var templinetype=document.getElementById("line"+tempItem).linetype;
          if(!templinetype) templinetype="1";
          lineProp+=";"+tempItem+","+memo+","+tempstr+","+templinetype;
        }
      }
      document.forms[0].elements["flow.dotProp"].value=dotProp;//设置节点属性
      document.forms[0].elements["flow.lineProp"].value=lineProp;//设置连线属性
      var tempmemo=document.forms[0].elements["flow.flowMain"].value;
      if(tempmemo==""){
        alert("请设置“流程属性”！");
        return false;
      }
      if(lineList==""){
        alert("流程需要设置至少一条流程路径！");
        return false;
      }
	  window.document.forms[0].submit();
    }
 }
 /**
  * 流程属性
  */
 function f_flowProp(){
   /*
     	:获取页面中的工作流属性，如果属性为空，那么，后台获取模版的最大编号，将其显示在新打开的页面中。如果属性不为空，那么将其信息解析，显示在新打开的页面中。
   */
    var tempInfo=document.forms[0].elements["flow.flowMain"].value;  //flowMain存放的是 工作流模版的信息，包括 模版编号，模版名称，模版要初始化的路径
	tempInfo=encodeURI(tempInfo=encodeURI(tempInfo));    
    if(!tempInfo) tempInfo="";
    if(tempInfo!="") tempInfo=tempInfo;
    var url="workflow!toAddTemplate.action?tempInfo="+tempInfo+"&time="+new Date().getTime();
    var _sfeatures = "dialogWidth=500px;dialogHeight=230px;top=0;left=0;toolbar=no;location=no;titlebar=no;menubar=no";







	 
    var info=window.showModalDialog(url,"",_sfeatures);
    if(info!=null){
    var name= getModelValue(info,"tempname");
    var temp_no =getModelValue(info,"tempno");
 	document.forms[0].elements["flow.flowName"].value=name;
    document.getElementById("flowName").innerHTML="<b>"+name+"</b>";
    document.forms[0].elements["flow.flowMain"].value=info;
    document.forms[0].elements["flow.flowNo"].value=temp_no;
    }
 }
 
 /**
  * 流程初始化
  */
  function initAll(){
     lineDotList=document.forms[0].elements["flow.lineDotList"].value;
     lineLikeList=document.forms[0].elements["flow.lineLikeList"].value;
     dotProp=document.forms[0].elements["flow.dotProp"].value;
     lineProp=document.forms[0].elements["flow.lineProp"].value;
     var tempdotProps=dotProp.split(";");
     var maxNum=dotNum;
     for(i=0;i<tempdotProps.length;i++){
       if(tempdotProps[i]!=""){//对象不为空
         var temps=tempdotProps[i].split(",");
	         if(temps[0]!='1'&&temps[0]!="2"){//不是原始节点，那么就添加节点
	           dotList+=","+temps[0];
	           dotType=temps[5];
	           var dotHtml="";
	           if(dotType=="1"){
	             setImg="dot.gif";
	             bgImg="dotbg.gif";
	             thisWidth="80";
	             thisHeight="50";
	             dotHtml="<div id=\"dots"+temps[0]+"\" setmemo='' style=\"position:absolute;left:"+temps[1]+";top:"+temps[2]+";z-index:"+(500+parseInt(temps[0]))+" \" ondrag='f_doDrag(this);' ondragend='f_dragEnd(this)'  ondragstart='f_dragStart(this)'><table  id=\"imgs"+temps[0]+"\" background=\"<%=basePath%>images/workflow/"+setImg+"\" style=\"cursor:hand;\" width="+thisWidth+" height="+thisHeight+" border=0><tr><td align=\"center\" vlign=\"center\" id=\"td"+temps[0]+"\"></td></tr></table><img onmousedown=\"f_down("+temps[0]+")\" ondblclick=\"f_showProp("+temps[0]+")\" style=\"position:absolute;left:0;top:0;\" src=\"<%=basePath%>images/workflow/dotbg.gif\" border=0></div>";
	          }else if(dotType=="4"){
	            setImg="select_start.gif";
	            dotHtml="<div id=\"dots"+temps[0]+"\" setmemo='' style=\"position:absolute;left:"+temps[1]+";top:"+temps[2]+";z-index:"+(500+parseInt(temps[0]))+" \" ondrag='f_doDrag(this);' ondragend='f_dragEnd(this)'  ondragstart='f_dragStart(this)'><img src=\"<%=basePath%>images/workflow/"+setImg+"\" id=\"imgs"+temps[0]+"\"  onmousedown=\"f_down("+temps[0]+")\" ondblclick=\"f_showProp("+temps[0]+")\"><br><span id=\"td"+temps[0]+"\" align=\"center\"></span></div>";
	        }else if(dotType=="5"){
	           setImg="select_end.gif";
	           dotHtml="<div id=\"dots"+temps[0]+"\" setmemo='' style=\"position:absolute;left:"+temps[1]+";top:"+temps[2]+";z-index:"+(500+parseInt(temps[0]))+" \" ondrag='f_doDrag(this);' ondragend='f_dragEnd(this)'  ondragstart='f_dragStart(this)'><img src=\"<%=basePath%>images/workflow/"+setImg+"\" id=\"imgs"+temps[0]+"\"  onmousedown=\"f_down("+temps[0]+")\" ondblclick=\"f_showProp("+temps[0]+")\"><br><span id=\"td"+temps[0]+"\" align=\"center\"></span></div>";
	        }
         //var dotHtml="<div id=\"dots"+temps[0]+"\" setmemo='' style=\"position:absolute;left:"+temps[1]+";top:"+temps[2]+";z-index:"+(500+parseInt(temps[0]))+" \" ondrag='f_doDrag(this);' ondragend='f_dragEnd(this)'  ondragstart='f_dragStart(this)'><table  id=\"imgs"+temps[0]+"\" background=\"<%=basePath%>images/workflow/dot.gif\" style=\"cursor:hand;\" width=80 height=50 border=0><tr><td align=\"center\" vlign=\"center\" id=\"td"+temps[0]+"\"></td></tr></table><img onmousedown=\"f_down("+temps[0]+")\" ondblclick=\"f_showProp("+temps[0]+")\" style=\"position:absolute;left:0;top:0;\" src=\"<%=basePath%>images/workflow/dotbg.gif\" border=0></div>";
           document.getElementById("div_map").innerHTML+=dotHtml;
           document.getElementById("td"+(parseInt(temps[0]))).innerHTML=temps[4];
           document.getElementById("dots"+(parseInt(temps[0]))).dottype=temps[5];
         }else{
           document.getElementById("dots"+(parseInt(temps[0]))).style.left=parseInt(temps[1]);
           document.getElementById("dots"+(parseInt(temps[0]))).style.top=parseInt(temps[2]);
         }
         if(maxNum<parseInt(temps[0])){
            maxNum=parseInt(temps[0]);
         }
         document.getElementById("imgs"+(parseInt(temps[0]))).setmemo=temps[3];
       }
     }
     dotNum=maxNum;
     dotType="";
     //初始化连线数据
     var templineProps=lineProp.split(";");
     var maxLine=lineNum;
     for(i=0;i<templineProps.length;i++){
       if(templineProps[i]!=""){
         var temps=templineProps[i].split(",");
         if(maxLine<parseInt(temps)){
           maxLine=parseInt(temps[0]);
         }
         var lineHtml="<div id=\"line"+temps[0]+"\"   style=\"position:absolute;left:0;top:0;width:"+mapWidth+";height:"+mapHeight+";z-index:"+temps[0]+"\"></div>";
         document.getElementById("div_map").innerHTML+=lineHtml;
         lineList+=","+temps[0];
         document.getElementById("line"+temps[0]).setmemo=temps[1];
         document.getElementById("line"+temps[0]).str=temps[2];
         document.getElementById("line"+temps[0]).linetype=temps[3];
         
         
         
       }
     }
     lineNum=maxLine;
     //画线
     var templineDotLists=lineDotList.split("_");
     for(i=0;i<templineDotLists.length;i++){
       if(templineDotLists[i]!=""){
          nowLineDotList=templineDotLists[i];
          var tempLines=nowLineDotList.split(";");
          nowLine=document.getElementById(tempLines[0]);
          linejp=new jsGraphics(tempLines[0]);
          linejp.setStroke(2);
          f_drawLastLine();//画变化后的线
       }
     }
     //设置流程标题
     document.getElementById("flowName").innerHTML="<b>&nbsp;"+document.forms[0].elements["flow.flowName"].value+"&nbsp;</b>";
  }
 /**
  * 打开流程
  */
var baseurl = new String(document.URL);
baseurl=baseurl.substring(baseurl.indexOf("//")+2,baseurl.length);
baseurl=baseurl.substring(baseurl.indexOf("/")+1,baseurl.length);
var basepath=baseurl.substring(0,baseurl.indexOf("/"));

 function f_open(){
   var isLine="1";
   if(lineList.length>0){
     if(confirm("已经存在流程图，是否需要先保存流程图")){
       alert("请点击保存按纽保存流程图！");
       isLine="0";
     }
   }
   if(isLine=="1"){
    
     var url="system/workflow!getTempList.action?type=${type}";
     //var str="dialogWidth=800px;dialogHeight=230px;top=0;left=0;toolbar=no;location=no;titlebar=no;menubar=no";
     var str="width=800,height=500,top=200,left=200,toolbar=no,location=no,titlebar=no,menubar=no";
     window.location.href=url;
     //window.open(url,'newwindow',str)
   }
 }
 /**
  * 删除当前流程
  */
 function f_delete(){
   if(confirm("您确定要删除当前流程吗？")){
     var tempflowMain=document.forms[0].elements["flow.flowMain"].value;
     if(tempflowMain!=""){ 
       window.location.href="system/workflow!delteTemplate.action?template.template_no="+document.forms[0].elements["flow.flowNo"].value;
     }else{
       f_clear();
     }
   }
 }
 /**
  * 清除当前所有数据
  */
 function f_clear(){
   document.getElementById("div_map").innerHTML="";
   tool=""//1表示dot,2表示line
   dotNum=0;//节点变量
   nowObj;//当前节点
   basex=0;//基础x位置
   basey=0;//基础y位置
   dotList="";//节点列表
   mapWidth=0;//底图的宽度
   mapHeight=0;//底图的高度
   lineNum=0;//连线变量
   lineList="";//连线列表
   lineDotList="";//连线节点列表
   lineLikeList="";//连线连接的节点的列表
   document.forms[0].elements["flow.dotProp"].value="";
   document.forms[0].elements["flow.lineProp"].value="";
   document.forms[0].elements["flow.lineDotList"].value="";
   document.forms[0].elements["flow.lineLikeList"].value="";
   document.forms[0].elements["flow.flowMain"].value="";
   document.forms[0].elements["flow.flowName"].value="";
   f_getBase();
 }




//添加
	function showPage(reqURL) {
		$('#addMenuWin').window({   
			width:700,   
			height:350,   
			modal:true,
			collapsible:false,
			minimizable:false, 
			maximizable:false,
			closable:true
		});
		var iframe_str = "<iframe height=\"100%\" width=\"100%\" scrolling=\"no\" frameborder=\"0\" src=\"" + reqURL + "\"></iframe>";
		$('#addMenuWin').html(iframe_str);
	}

 
</script>
</head>
<body bgcolor="#EDF3F4">
<%

%>
<form action="system/workflow!saveWorkFlow.action" method="POST" >

<table border="0" width="100%" height="60" cellpadding="0" cellspacing="0">
 <tr height="30"><td >
   <input type="button" name="aaaa"  value="流程属性" onclick="f_flowProp()"  class="btn"/>
   <input type="button" name="aaaa"  value="保存" onclick="f_save()"  class="btn"/>
   <input type="button" name="aaaa"  value="删除当前流程" onclick="f_delete()"  class="btn"/>
   <input type="button" name="aaaa"  value="检验" onclick="f_checkall(0)"  class="btn"/>
<!--    <input type="button" name="aaaa"  value="打开" onclick="f_open()"  class="btn"/> -->
  </td>
  </tr>
 <tr height="30"><td >
   <img src="<%=basePath%>images/workflow/act.gif" id="dot" onclick="f_dot(this,1)" title="标准环节" onmouseover="f_move(this)" onmouseout="f_out(this)">
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   <img src="<%=basePath%>images/workflow/line.gif" id="line" onclick="f_line(this,1)" title="连线" onmouseover="f_move(this)" onmouseout="f_out(this)">
   <img src="<%=basePath%>images/workflow/default.gif" id="line" onclick="f_line(this,2)" title="缺省边" onmouseover="f_move(this)" onmouseout="f_out(this)">
   <img src="<%=basePath%>images/workflow/back.gif" id="line" onclick="f_line(this,3)" title="返回边" onmouseover="f_move(this)" onmouseout="f_out(this)">
   <img src="<%=basePath%>images/workflow/jump.gif" id="line" onclick="f_line(this,4)" title="跳转边" onmouseover="f_move(this)" onmouseout="f_out(this)">
 </td>
 </tr>
 </table>
 <table border="0" width="100%" height="85%" cellpadding="0" cellspacing="0">
 <tr><td  background="<%=basePath%>images/workflow/back.jpg">
  <div id="div_map" style="width:100%;height:100%;OVERFLOW:hidden;BORDER: 1px solid #000000;z-index:2"   onmouseup="f_mapUp()" ondblclick="f_dblClickLine()" onclick="f_draw()" onmousemove="f_moveLine()"  onselectstart="return false">
 </div>
 </td>
 </tr>
 </table>
<input type="hidden" name="flow.dotProp" value="${flow.dotProp }"/><!--节点属性-->
<input type="hidden" name="flow.lineProp" value="${flow.lineProp }"/><!--连线属性-->
<input type="hidden" name="flow.lineDotList" value="${ flow.lineDotList}"/><!--连线点列表-->
<input type="hidden" name="flow.lineLikeList" value="${flow.lineLikeList }"/><!--连线节点列表-->
<input type="hidden" name="flow.flowMain" value="${flow.flowMain }"/><!--流程主要信息-->
<input type="hidden" name="flow.flowName" value="${flow.flowName }"/><!--流程名称-->
<input type="hidden" name="flow.flowNo" value="${ flow.flowNo}"/><!--流程代码-->
<input type="hidden" name="type" value="${type}"/><!--流程代码-->
 <script language="javascript">
   f_getBase();//设置基础的x和y坐标
 </script>
 <%
  String errors=(String)request.getAttribute("Error");
  if(errors==null) errors="";
%>
<input type="hidden" name="errors" value="<%=errors%>" >
<script language="javascript">
 var errorsvalue=document.all.errors.value;
 if(errorsvalue!=""){
    alert(errorsvalue);
 }
</script>
</form>

<div id="addMenuWin" iconCls="icon-save" title="添加菜单---" style="width: 0;height: 0"></div> 
</body>
</html>
