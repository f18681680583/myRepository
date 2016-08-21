function information(){
	if(getCookie("cn_user_id")==null){
		alert("请登录");
		window.location.href="log_in.html";
		return;
	}
	$("#can").load("alert/alert_info.jsp");
}
function havefun(){
	$("#can").load("2048/game.html");
}
function changeInfo(){
	$("#info").hide();
	$("#info2").show();
	findPro("province",1)
	$("#province").change(function(){
		var idname=$(this).val();
		var id=idname.split(":")[0];
		findPro("city",id);
	});
	$("#info2").on("click","#sure_change a",function(){
		var nick=$("#nick").val();
		var sex=$("input[name='sex']:checked").val();
		var borth=$("#borth").val();
		var pro=$("#province").val().split(":")[1];
		var city=$("#city").val().split(":")[1];
		var sign=$("#info2 textarea").val();
		console.log(nick+":"+sex+":"+borth+":"+pro+":"+city+":"+sign);
		var userId=getCookie("cn_user_id");
		$.ajax({
			url:"info/changeInfo.do",
			type:"post",
			data:{"nick":nick,"sex":sex,"borth":borth,"pro":pro,"city":city,"sign":sign,"cn_user_id":userId},
			dataType:"json",
			success:function(result){
				var msg=result.msg;
				alert(msg);
			},
		});
		
	});
}
function findPro(province,id){
	$.ajax({
		url:"info/findProvince.do",
		type:"post",
		data:{"parent_id":id},
		dataType:"json",
		success:function(result){
			var pros=result.obj;
			$("#"+province).empty();
			var pro=$("<option value='none'>---请选择---</option>");
			$("#"+province).append(pro);
			for(i=0;i<pros.length;i++){
				var pro=$("<option value="+pros[i].region_id+':'+pros[i].region_name+">"+pros[i].region_name+"</option>");
				$("#"+province).append(pro);
			}
		},
	});
}
function info(){
	$("#info2").hide();
	$("#info").show();
}