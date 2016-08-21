//找到处于回收站，收藏，活动的笔记
function findSpecialNote(status,part) {
	$("#pc_part_3").hide();
	$("#pc_part_1 .contacts-list li a").removeClass();
	$(".col-xs-3").hide();
	$("#pc_part_"+part).show();
	$.ajax({
		url : "note/findSpecialNote.do",
		type : "post",
		data : {"status_id":status},
		dataType : "json",
		success : function(result) {
			var obj = result.obj;
			$("#pc_part_"+part+ " .contacts-list li").remove();
			var li=null;
			for (var i = 0; i < obj.length; i++) {
				var title=obj[i].cn_note_title;
				if(status=="2"){
					li = $('<li class="disable"><a ><i class="fa fa-file-text-o" '
							+ 'title="online" rel="tooltip-bottom"></i><span> '
							+ title
							+ '</span><button type="button" '
							+ 'class="btn btn-default btn-xs btn_position btn_delete"><i class="fa fa-times"> '
							+ '</i></button><button type="button" class="btn btn-default btn-xs btn_position_2 '
							+ 'btn_replay"><i class="fa fa-reply">'
							+ '</i></button></a></li>');
//				}else if(status=="3"){
//						li = $('<li class="idle"><a ><i class="fa fa-file-text-o" '
//								+ 'title="online" rel="tooltip-bottom"></i><span> '
//								+ title
//								+ '</span><button type="button" class="btn btn-default btn-xs '
//								+ 'btn_position btn_delete"><i class="fa fa-times">'
//								+ '</i></button></a></li>');
//				}else if(status=="4"){
//						li = $('<li class="offline"><a ><i class="fa fa-file-text-o" '
//								+ 'title="online" rel="tooltip-bottom"></i><span>'
//								+ title + '</span></a></li>');
				}
				li.data("noteId",obj[i].cn_note_id);
				$("#pc_part_"+part+ " .contacts-list").append(li);
			}
		},
		error : function() {
			alert("未知错误！");
		}
	});
}
//取消删除
function deleteNote(){
	$("#can").load("alert/alert_delete_rollback.html");
	var noteli=$(this).parent().parent();
	var noteId=noteli.data("noteId");
	$("#can").on("click","#sure_delete_rollback",function(){
		$.ajax({
			url:"note/deleteNote.do",
			type:"post",
			data:{"noteId":noteId},
			dataType:"json",
			success:function(result){
				var status=result.status;
				var msg=result.msg;
				alert(msg);
				if(status=='0'){
					noteli.remove();
				}
			},
			error:function(){
				alert("未知错误！");
			}
		});
		
	});
}
function replayNote(){
	console.log(2)
	var noteli=$(this).parent().parent();
	var books=$("#book-body li");
	$("#can").load("alert/alert_replay.html",function(){
		$("#replaySelect option").remove();
		var l= $("<option value='none'>- 请选择笔记本 -</option>");
		$("#replaySelect").append(l);
		for(i=0;i<books.length;i++){
			var book= $(books[i]);
			var bookName=book.text();
			var bookId=book.data("bookId");
			var bookli=$('<option value="'+bookId+'">- '+bookName+ '-</option>');
			$("#replaySelect").append(bookli);
		}
		
	});
	$("#can").on("click","#sure_replay",function(){
		var book=$("#replaySelect option:selected");
		var bookId=book.val();
		var noteId=noteli.data("noteId");
		if(bookId=='none'){
			return;
		}
		$.ajax({
			url:"note/replayNote.do",
			type:"post",
			data:{"bookId":bookId,"noteId":noteId},
			dataType:"json",
			success:function(result){
				var status=result.status;
				var msg=result.msg;
				alert(msg)
				if(status=='0'){
					noteli.remove();
				}
			},
			error:function(){
				alert("未知错误！");
			}
		});
	})	
}
function cancelLike(){
	$("#can").load("alert/alert_delete_like.html");
	var noteli=$(this).parent().parent();
	var noteId=noteli.data("noteId");
	console.log(noteId)
	$("#can").on("click","#sure_delete_like",function(){
		$.ajax({
			url:"note/changeStatus.do",
			type:"post",
			data:{"noteId":noteId,"status":"1"},
			dataType:"json",
			success:function(result){
				var status=result.status;
				var msg=result.msg;
				alert(msg);
				if(status=='0'){
					noteli.remove();
				}
			},
			error:function(){
				alert("未知错误1");
			}
		});
	});
}