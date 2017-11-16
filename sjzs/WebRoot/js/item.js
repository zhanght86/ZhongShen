// JavaScript Document

/**
 * 批量删除项目信息时 复选框进行全选
 * projects 所有复选框的名称
 * @return void
 */
function checkAll(checkName){    
     var el = document.getElementsByName(checkName);   
     var len = el.length;  
     for(var i=0; i<len; i++){      
	    if((el[i].type=="checkbox") && (el[i].name==checkName)){           
		  el[i].checked = true; 
		  }    
	 } 
 } 

/**
 * 批量删除项目信息时 复选框进行全不选
 * projects 所有复选框的名称
 * @return void
 */
  function clearAll(checkName){     
    var el = document.getElementsByTagName(checkName);   
    var len = el.length;    
	 for(var i=0; i<len; i++){     
	     if((el[i].type=="checkbox") && (el[i].name==checkName)){    
		          el[i].checked = false;      
		 }    
	} 
} 
  
     /**
	  *删除时、修改时让用户只选中一个项目的复选框
	  */
	function getSingleBox(boxValue){
	       var leng = document.getElementsByName("projectIds").length;
	       for(var i=0;i<leng;i++){
	       var checkBoxes = document.getElementsByName("projectIds")[i];
            if(checkBoxes.checked==true){
               if(checkBoxes.value!=boxValue){
               		checkBoxes.checked=false;
               }
            }
        }
	}
	/**
	  *修改时让用户只选中一个复选框
	  */
	function getSingleValue(boxValue,boxName){
		
	       var leng = document.getElementsByName(boxName).length;
	       for(var i=0;i<leng;i++){
	       var checkBoxes = document.getElementsByName(boxName)[i];
           if(checkBoxes.checked==true){
              if(checkBoxes.value!=boxValue){
              		checkBoxes.checked=false;
              }
           }
       }
	}
	
  /**
   * 打开一个子窗口
   * @param url 子窗口的地址（*.action）
   * @param data 传值到Action
   * @param name 
   */
  function openPostWindow(url, data, name){
		var ccode = document.getElementById('projectNum').value; 
		var tempForm = document.createElement("form");  
 		tempForm.id="tempForm1";  
 		tempForm.method="post";  
 		tempForm.action=url;  
 		tempForm.target=name;  
 		var hideInput = document.createElement("input");  
 		hideInput.type="hidden";  
 		hideInput.name= data;
 		hideInput.value= ccode;
 		tempForm.appendChild(hideInput);   
 		tempForm.attachEvent("onsubmit",function(){ openWindow(name); });
 		document.body.appendChild(tempForm);  
 		tempForm.fireEvent("onsubmit");
 		tempForm.submit();
 		document.body.removeChild(tempForm);
	}
  function openWindow(name){  
	  window.open('about:blank',name,'');   
	}
  
  	 /**
	  *
	  *验证用户输入的各个信息不能为空
	  */
	function checkProject(){
		var proName = document.getElementById("projectName").value; //项目名称
		var proYssk = document.getElementById("projectYssk").value; //预算收款
		var proYsfk = document.getElementById("projectYsfk").value; //预算付款
		var proStart = document.getElementById("projectStartTime").value; //计划开始时间
		var proEnd= document.getElementById("projectEndTime").value; //计划结束时间
		var emplname= document.getElementById("emplname").value; //人员名称
		if(proName==""||proName.length==0){
			alert("项目名称不能为空");
			return false;
		}
		if(proYssk==""||proYssk.length==0){
			alert("预算收款不能为空");
			return false;
		}
		if(proYsfk==""||proYsfk.length==0){
			alert("预算付款不能为空");
			return false;
		}
		if(proStart==""||proStart.length==0){
			alert("计划开始时间不能为空");
			return false;
		}
		if(proEnd==""||proEnd.length==0){
			alert("计划结束时间不能为空");
			return false;
		}
		if(emplname==""||emplname.length==0){
			alert("请选择员工");
			return false;
		}
		return true;
	}
	
	 /**
 	  *如果输入信息正确，把提交按钮变成不可用，避免用户重复提交信息
 	  */
   function checkForm(){
     if(checkProject()){
       	document.getElementById("prosub").disabled = true;
       	document.forms.namedItem("projectForm").submit();
     }
 }
   
   /**
    *获取父窗体的项目编号
    */
  function getProCode(){
  		 var proCode = window.parent.document.all("projectNum").value;
  		 document.getElementById("procode").value = proCode;
  }
  