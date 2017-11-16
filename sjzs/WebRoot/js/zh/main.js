var timeoutId = 0;var refreshPage;var closePage;function closewindow(){if ($('#tabs').tabs('exists',refreshPage)) 
{var refpage = document.getElementById(refreshPage);if (null != refpage) 
{refpage.contentWindow.document.forms["queryForm"].submit();}
$('#tabs').tabs('select',refreshPage);}$('#tabs').tabs('close',closePage);}
function changeWidth(){$.get("login!updateIndexPage.action?nocache=" + new Date().getTime(),function(data){$("#mainContext").html(data);},"html");setTimeout(changeWidth, 5 * 60 * 1000);}
function destorySession() {$.get('login!loginout.action');}$("#statdForm").click(function(){$.ajax({url:'state!AddState.action',type:'POST',data:{"state.stateValue":$("#stateValue").val()},beforeSend:function()
{if(confirm("你确定要登记吗")){return true;}return false;},error:function(){alert("load error");},success:function(html){
if(html=="success"){msgShow("提示","登记成功","info");$("#message").fadeOut(3000);}}});});
function chufa(){$("#statdForm").attr("disabled","disabled")}function showJob(instance_id){
var url=document.getElementById("url"+instance_id).value;url=url+"&flow.instance_no="+instance_id+"&type=task"+"&time="+
new Date().getTime();document.getElementById(instance_id).style.display="none";
self.parent.addTab('待办事项',url,'icon-add');}function showTaskInfo(val){if(""!=val)
{var url ="task!showTask.action?task.taskId="+val;self.parent.addTab('查看任务',url,'icon-add');}}