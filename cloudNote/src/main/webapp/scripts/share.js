var page=1;
var searchtitle="";
function searchNote(event){
	if(event.keyCode==13){
		searchtitle = $("#search_note").val().trim();
		if(searchtitle==""){
			return;
		}
		$(".col-xs-3").hide();
		$(".col-sm-7").hide();
		$("#pc_part_6").show();
		$("#pc_part_6 .contacts-list").empty();
		page=1;
		search();
	}
}
function search(){
	if(searchtitle==""){
		return;
	}
	if(page<1){
		alert("无更多记录");
		return;
	}
	console.log(11111);
	$.ajax({
		url:"share/searchNote.do",
		type:"post",
		data:{"title":searchtitle,"page":page},
		dataType:"json",
		success:function(result){
			var status=result.status;
			if(status=='0'){
				var list=result.obj;
				if(list.length==0){
					page=-1;
				}
				for(i=0;i<list.length;i++){
					var li = $('<li class="idle"><a ><i class="fa fa-file-text-o" title="online" '
							+ 'rel="tooltip-bottom"></i> '
							+ list[i].cn_share_title
							+ '<button type="button" '
							+ 'class="btn btn-default btn-xs btn_position btn_like">'
							+ '<i class="fa fa-star">'
							+ '</i></button></a></li>');
					li.data("shareId",list[i].cn_share_id);
					$("#pc_part_6 .contacts-list").append(li);
				}
				page+=1;
			}			
		}
	});
}
 function findShareText(){
	 var shareId=$(this).data("shareId");
	 $.ajax({
		 url:"share/findShareBody.do",
		 type:"post",
		 data:{"shareId":shareId},
		 dataType:"json",
		 success:function(result){
			 var status=result.status;
			 var share=result.obj;
			 if(status=='0'){
				$("#pc_part_5").show(); 
				$("#noput_note_title").text(share.cn_share_title);
				$("#pc_part_5 .clear_margin p").html(share.cn_share_body);
			 }
		 }
	 });
 }
 function likeNote(){
	 $("#can").load("alert/alert_like.html");
	 var shareId=$(this).parent().parent().data("shareId");
	 $("#can").on("click","#sure_like",function(){
		 var userId=getCookie("cn_user_id");
		 $.ajax({
			 
		 });
	 })
	 return false;
 }