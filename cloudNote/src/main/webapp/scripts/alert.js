//重写JS原生alert函数
window.alert=function(e){
		$('#can').load('alert/alert_error.html',function(){
			$('#error_info').text(' '+e);
			$('.opacity_bg').show();
		});
	}