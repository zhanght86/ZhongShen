	//用户选择
		function selectEmpl(emplname,emplid){
			var url="system/empl!orgTreeEmple.action?time="+new Date().getTime();
			var returnVal=window.showModalDialog(url,"选择人员","dialogWidth:900px;");
			if(returnVal!=null){
				//此窗口的返回值为一个二维数组arr,arr[0]是选择的用户的id,arr[1]是选择的用户的用户名,如果没有做任何选择哪么arr[0],arr[1]的长度为0
				var str3 = ArrayToString(returnVal[0]);
				var str4 = ArrayToString(returnVal[1]);
				document.getElementById(emplname).value =  str4 ;
				document.getElementById(emplid).value =  str3 ;
			}else{
				return;
			}
		}
		
		//将人员数组转换为字符串
		function ArrayToString(arr) {
			var str = "";
			for (var i = 0; i < arr.length; i++) {
				str += "," + arr[i];
			}
			str = str.substring(1,str.length) ;
			return str;
		}