//下载
function downloadAttach() {
	if(document.downloadForm.attId!=""){
		checkInte()
	}else{
		alert("没有要下载的文件！");
	}
}

//判断用户的积分,以及扣除积分.
function checkInte(){
	var integral = 1;
	var upClientId = $("#upClientId").val()
	if(! upClientId == undefined){
		if(upClientId == $("#emplId").val()){
			alert("这是您上传的文档，下载不需要积分！");
			document.downloadForm.submit();
		}else{
			integral = $("#integral").val();
			downFile(integral,upClientId);
		}
	}else{
		downFile(integral,upClientId);
	}	
}

function downFile(integral,upClientId){
	var r = confirm("下载所需积分："+integral+"\r 确定下载吗？");
	if(r){
		$.ajax({		   
		    url: "servlet/clientDownloadServlet",
		    data:{'integral':integral,'upClientId':upClientId},
		    type: "post",
		    async:false,
		    dataType:'text',
			error : function(data) {
				alert(data);
				return false;
			},
			success : function(data) {
				var text=data;
				if(text==0){
					document.downloadForm.submit(); 
				}else{
					alert(text);
					return false;
				}				
			}
		});
	}
}
		