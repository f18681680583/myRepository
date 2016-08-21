$(document).ajaxError(function(event, XMLHttpRequest, ajaxOptions, thrownError){
//	console.log(XMLHttpRequest.getAllResponseHeaders())
	alert("服务器异常！");
})
$(document).ajaxComplete(function(event, XMLHttpRequest){
	if(XMLHttpRequest.readyState){
		
	}
});
//$(document).ajaxSend(function(){
//	console.log(1)
//	alert("ajaxsgatr");
//	var userId=getCookie("cn_user_id");
//	if(userId==""||userId==null){
//		rebackLogin();
//		return;
//	}
//});
//回到登录页面
function rebackLogin(){
		var i=5;
		alert("请登录,"+i+"秒后自动跳转到登录页面！");
		var interval=setInterval(function(){
			i--;
			if(i<0){
				clearInterval(interval);
//				window.location.href="log_in.html";
				return;
			}
			$("#can #error_info").html("请登录,"+i+"秒后自动跳转到登录页面！");
		},1000);
		$("#can").on("click",".close,.cancle",function(){
			clearInterval(interval);
//			window.location.href="log_in.html";
		});
}