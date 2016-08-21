<!DOCTYPE html>
<html>
<head>
<title>修改密码</title>
<meta charset="utf-8">
<link rel="stylesheet" href="styles/login.css"/>
<script type="text/javascript" src="scripts/BaseValues.js">
</script>
<script type="text/javascript" src="scripts/jquery.min.js">
</script>
<script type="text/javascript" src="scripts/login/login.js">
</script>
<script type="text/javascript" src="scripts/login/login_callback.js">
</script>
<script type="text/javascript" src="scripts/cookie_util.js">
</script>
<script type="text/javascript" src="scripts/base64.js">
</script>
<script type="text/javascript">
 $(function(){
	 $("#back").click(function(){
		 history.back();
	 });
	 $("#changePassword").click(function(){
		 var last_password=$("#last_password").val().trim();
		 var new_password=$("#new_password").val().trim();
		 var final_password=$("#final_password").val().trim();
		 var verifycode=$("#verifycode").val().trim();	 	
		 var flog=true;
		 if (last_password == "") {
			flog = false;
			$("#warning_1 span").html("输入不能为空");
			$("#warning_1").show();
		 }
		 if(last_password ==new_password){
			 floh=false;
			 $("#warning_2 span").html("密码不能和原来的一致");
			 $("#warning_2").show();
		 }
		 if (new_password == "" || new_password.length<6) {
			flog = false;
			$("#warning_2 span").html("输入长度太短");
			$("#warning_2").show();
		 }
		 if (final_password == "") {
			flog = false;
			$("#warning_3 span").html("输入不能为空");
			$("#warning_3").show();
			
		 }
		 if (verifycode== "") {
			flog = false;
			$("#warning_4 span").html("输入不能为空");
			$("#warning_4").show()
		}
		 if(new_password!=final_password){
			 flog=false;
			 $("#warning_3 span").html("两次密码输入不一致！");
			 $("#warning_3").show();
		 }
		 var userId=getCookie("cn_user_id");
		 if(flog){
			 $.ajax({
				 url:"user/changeInfo.do",
				 type:"post",
				 data:{"userId":userId,"password":new_password,"oldPassword":last_password},
				 dataType:"json",
				 success:function(result){
					 var status=result.status;
					 var msg=result.msg;
					 if(status=='0'){
						 alert(msg);
						 window.location.href="edit.jsp";
					 }
					 if(status=='1'){
						 alert(msg);
						 window.location.href="log_in.html";
					 }
					 if(status=='2'){
						 $("#warning_1 span").html("密码错误！");
						 $("#warning_1").show();
					 }
					 if(status=='3'){
						 alert(msg);
					 }
					 if(status=='4'){
						 alert(msg);
					 }
				 },
				 error:function(){
					 alert("未知错误！");
				 }
			 });
		 }
	});
});
</script>
</head>
	<body>
		<div class="global">
			<div class="sig sig_in" tabindex='4' id='zc'>
				<dl>
					<dt>
						<div class='header'>
							<h3>修改密码</h3>
						</div>
					</dt>
					<dt></dt>
					<dt>
						<div class='letter'>
							原密码:&nbsp;<input type="password" name="" id="last_password" tabindex='1'/>
							<div class='warning' id='warning_1'><span>原始密码错误</span></div>
						</div>
					</dt>
					<dt>
						<div class='letter'>
							新密码:&nbsp;<input type="password" name="" id="new_password" tabindex='2'/>
							<div class='warning' id='warning_2'><span>新密码长度过短</span></div>
						</div>
					</dt>
					<dt>
						<div class='password'>
							确认新密码:&nbsp;<input type="password" name="" id="final_password" tabindex='3'/>
							<div class='warning' id='warning_3'><span>密码输入不一致</span></div>
						</div>
					</dt>
					<dt>
						<div class='letter'>
							验证码:&nbsp;<input type="text" name="" id="verifycode" class="verifycode"/>
							<input type="image" alt="验证码" src="user/verifycode.vc" id="imgcode" 
							onclick="this.setAttribute('src','user/verifycode.vc?x='+Math.random())"></input>
							<div class="warning" id="warning_4"><span></span></div>
						</div>
					</dt>
					<dt>
						<div>
							<input type="button" name="" id="changePassword" value='&nbsp;确&nbsp;定&nbsp;' tabindex='4'/>
							<input type="button" name="" id="back" value='&nbsp;关&nbsp;闭&nbsp;' tabindex='5'/>
							<script type="text/javascript">
							
							function get(e){
								return document.getElementById(e);
							}
							get('back').onclick=function(){
								get('zc').className='sig sig_out';
								//window.history.back();
								window.opener=null;
								window.open('','_self');
								window.close();
							}
							window.onload=function(){
							/*
								get('last_password').onblur=function(){
									var lpassword=get('last_password').value;
									if(!validOldPwd(lpassword)){
										get('warning_1').style.display='block';
										flog = false;
									}else{
										get('warning_1').style.display='none';
										flog = true;
									}
								}
								
								get('final_password').onblur=function(){
									
									var npassword=get('new_password').value;
									var fpassword=get('final_password').value;
									if(npassword!=fpassword){
										get('warning_3').style.display='block';
										flog = false;
									}else{
										flog = true;
									}
								}
								*/
//								get('new_password').onblur=function(){
//									var npassword=get('new_password').value.length;
//									if(npassword<6&&npassword>0){
//										get('warning_2').style.display='block';
//										flog = false;
//									}else{
//										flog = true;
//									}
//								}
								get('new_password').onfocus=function(){
									get('warning_2').style.display='none';
								}
								get('final_password').onfocus=function(){
									get('warning_3').style.display='none';
								}
								get('last_password').onfocus=function(){
									get('warning_1').style.display='none';
								}
								get('verifycode').onfocus=function(){
									get('warning_4').style.display='none';
								}
							}
//						$("#changePassword").click(function(){
		//							if(flog){
			//							changepwd(changepwdSuccess,changepwdError);
				//					}
					//				
						//		})	
						//	}
							</script>
						</div>
					</dt>
				</dl>
			</div>
		</div>
	</body>
</html>