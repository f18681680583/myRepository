function checkLogin() {
	var name = $("#count").val().trim();
	var password = $("#password").val().trim();
	var code = $("#verifycode").val().trim();
	$("#count_span").html("");
	$("#password_span").html("");
	$("#verifycode_span").html("");
	var ok = true;
	if (name == "") {
		ok = false;
		$("#warning_name span").html("用户名不能为空");
		$("#warning_name").show();
		//	$("#count_span").html("用户名为空");
	}
	if (password == "") {
		ok = false;
		$("#warning_pass span").html("密码不能为空");
		$("#warning_pass").show();
		//$("#password_span").html("密码为空");
	}
	if (code == "") {
		ok = false;
		$("#warning_code span").html("验证码不能为空");
		$("#warning_code").show();
		//$("#verifycode_span").html("验证码为空");
	}
	if (ok == true) {
		$.ajax({
			url : "user/login.vc",
			type : "post",
			data : {
				"name" : name,
				"password" : password,
				"verifycode" : code
			},
			dataType : "json",
			success : function(data) {
				var status = data.status;
				var mg = data.msg;
				var codestatus = data.codestatus;
				if (codestatus == '1') {
					$("#warning_code span").html("验证码错误！");
					$("#warning_code").show();
				}
				$.ajax({
					url:"user/verifycode.vc"
				})
				if (status == '0' && codestatus == '0') {
					var user=data.obj;
					addCookie("cn_user_id",user.cn_user_id,1);
					addCookie("cn_user_nick",user.cn_user_nick, 1);
					addCookie("cn_user_photo",user.cn_user_photo,1);
					window.location.href= "edit.jsp";
				} else if (status == '1') {
					$("#warning_name span").html(mg);
					$("#warning_name").show();
				} else if (status == '2') {
					$("#warning_pass span").html(mg);
					$("#warning_pass").show();
				}
			}
		});
	}
}
function regist() {
	var username = $("#regist_username").val().trim();
	var nickname = $("#nickname").val().trim();
	var password = $("#regist_password").val().trim();
	var final_password=$("#final_password").val().trim();
	var code = $("#verifycode2").val().trim();
	var ok = true;
	if(final_password!=password){
		 ok=false;
		 $("#warning_4 span").html("两次输入密码不一致");
		 $("#warning_4").show();
	}
	if(final_password==""){
		 ok=false;
		 $("#warning_4 span").html("确认密码为空");
		 $("#warning_4").show();
		 //$("#regist_password_span2").html("确认密码为空");
	 }
	if (username =="") {
		ok = false;
		$("#warning_1 span").html("用户名为空");
		$("#warning_1").show();
//		$("#regist_username_span").html("用户名为空");
	}
	if (nickname == "") {
		ok = false;
		$("#warning_2 span").html("昵称为空");
		$("#warning_2").show();
//		$("#nickname_span").html("昵称为空");
	}
	if (password == "") {
		ok = false;
		$("#warning_3 span").html("密码为空");
		$("#warning_3").show();
//		$("#regist_password_span").html("密码为空");
	}
	if (code == "") {
		ok = false;
		$("#warning_5 span").html("验证码为空");
		$("#warning_5").show();
//		$("#regist_verifycode_span").html("验证码为空");
	}
	if(ok){
		$.ajax({
			url:"user/regist.vc",
			type:"post",
			data:{"cn_user_name":username,"cn_user_password":password,"cn_user_nick":nickname,"verifycode":code},
			dataType:"json",
			success:function(data){
				var status = data.status;
				var mg = data.msg;
				var codestatus = data.codestatus;
				if(status=='0'&& codestatus=='0'){
					alert(mg);
					$("#back").trigger("click");
				}
				if(status=='1'){
					$("#regist_username_span").html(mg);
				}
				if(codestatus=='1'){
					$("#regist_verifycode_span").html("验证码错误");
				}
			}
		});
	}
}
function get(e){
	return document.getElementById(e);
}
function load(){
	get('sig_in').onclick=function(){
		$("#regist_imgcode").trigger("click");
		get('dl').className='log log_out';
		get('zc').className='sig sig_in';
	}
	get('back').onclick=function(){
		$("#imgcode").trigger("click");
		get('zc').className='sig sig_out';
		get('dl').className='log log_in';
	}
	window.onload=function(){
		var t =setTimeout("get('zc').style.visibility='visible'",800);
		get("regist_username").onblur=function(){
			var regist_username=get("regist_username").value;
			if(regist_username==""){
				$("#regist_username_span").html("用户名为空");
			}else{
				$("#regist_username_span").html("");
			}
		}
		get("nickname").onblur=function(){
			var regist_username=get("nickname").value;
			if(regist_username==""){
				$("#nickname_span").html("昵称为空");
			}else{
				$("#nickname_span").html("");
			}
		}
		get('final_password').onblur=function(){
			var npassword=get('regist_password').value;
			var fpassword=get('final_password').value;
			if(npassword!=fpassword){
				$("#regist_password_span2").html("密码输入不一致");
			}else{
				$("#regist_password_span2").html("");
			}
		}
		get('regist_password').onblur=function(){
			var npassword=get('regist_password').value.length;
			if(npassword<6&&npassword>0){
				$("#regist_password_span").html("密码长度过短");
			}else{
				$("#regist_password_span").html("");
			}
		}
		$("input").focus(function(){
			$(this).next(".warning").hide();
		});
		$(".verifycode").focus(function(){
			$(this).next().next(".warning").hide();
		});
//		get('regist_password').onfocus=function(){
//			get('warning_2').style.display='none';
//		}
//		get('final_password').onfocus=function(){
//			get('warning_3').style.display='none';
//		}
	}	
}