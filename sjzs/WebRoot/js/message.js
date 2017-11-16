
/**
 * 群发内部消息时对具体部门下的复选框进行全选
 * @param name 所有复选框的名称
 * @param titleValue 复选框title属性的值
 * @return void
 */
function checkAll(titleValue){    
     var el = document.getElementsByTagName('input');   
     var len = el.length;  
     for(var i=0; i<len; i++){      
	    if((el[i].type=="checkbox") && (el[i].name=='accepters')&&(el[i].title==titleValue)){           
		  el[i].checked = true; 
		  }    
	 } 
 } 

/**
 * 群发内部消息时对具体部门下的复选框进行全不选
 * @param name  所有复选框的名称
 * @param titleValue 复选框title属性的值
 * @return void
 */
  function clearAll(titleValue){     
    var el = document.getElementsByTagName('input');   
    var len = el.length;    
	 for(var i=0; i<len; i++){     
	     if((el[i].type=="checkbox") && (el[i].name=='accepters')&&(el[i].title==titleValue)){    
		          el[i].checked = false;      
		 }    
	} 
} 