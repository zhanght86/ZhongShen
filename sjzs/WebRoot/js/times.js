
/**
 * 方法描述：计算两个时间相差的天数，格式必须为yyyy-MM-dd，如果起始时间大于结束时间，则返回的天数为负数
 * 创建人：高容翔
 * 创建时间：2011-6-25
 * @param time1 起始的时间
 * @param time2 结束的时间
 * @return 两个时间之间相差的天数
 * @version   1.0
 */
function dealtime(time1,time2){
	//把时间按"_"切成数组
    var ss1=time1.split("-");
    var ss2=time2.split("-");
	//转为毫秒数
    var btime=new Date(ss1[0],ss1[1]-1,ss1[2]);
   	var etime=new Date(ss2[0],ss2[1]-1,ss2[2]);
    //计算相差天数
    time=(etime.getTime()-btime.getTime())/(1000*24*60*60);
    return time ;
}

/**
 * 方法描述：验证复选框是否为空
 * 创建人：高容翔
 * 创建时间：2011-6-30
 * @param name 复选框的name属性值
 * @param errorInfo 出错的提示信息
 * @param show 显示的位置
 * @return true或者false分别代表验证是否通过
 * @version  1.0
 */
function testCheckBox( name , errorInfo ,show ){
	var x = document.getElementsByName(name);
		var count = 0;
		for( var i=0;i<x.length;i++ ){
			if( x[i].checked == true )
				count = count + 1 ;
		}
		if( count == 0 ){
			document.getElementById(show).innerHTML="<font color=\"red\"><b>"+errorInfo+"</b></font>";
			return false;
		}
		return true;
}

/**
 * 方法描述：验证下拉菜单存在值
 * 创建人：高容翔
 * 创建时间：2011-7-8
 * @param name 下拉菜单的编号
 * @param errorInfo 出错的提示信息
 * @param show 显示的位置
 * @return 
 * @version  1.0
 */
function testSelect( name , errorInfo , show ){
	var id = document.getElementById(name);
	if( id.value == 0 ){
		document.getElementById(show).innerHTML="<font color=\"red\"><b>"+errorInfo+"</b></font>";
		return false;
	}
	return true;
}