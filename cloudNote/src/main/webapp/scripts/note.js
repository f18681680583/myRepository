//显示笔记菜单的函数
function showNoteMenu(){
//	var length=$("#note-body .note_menu .hover").length;
//	$("#note-body .note_menu").removeClass("note_menu_show");
	$("#note-body a").removeClass("checked");
	$("#note-body div").hide();
	var $menu=$(this).parent().next();
	$menu.slideDown(500);
	$menu.hover(function(event){
		var li=$(this).parent();
		a=li.find("a")
		if(event.type=="mouseenter"){
			a.addClass("checked");
			$menu.addClass("hover");
		}
	});
	return false;
}
//	var times;
//	var notemenu2=$(this).parent().parent();
//	var notemenu=notemenu2.find(".note_menu");
//	//如果鼠标在按钮上
//	if(event.type=="mouseover"){
//		notemenu.addClass("note_menu_show");
////		notemenu.addClass("hover");
//	}
//	//笔记菜单的悬停事件
//	notemenu.hover(function(event){
//		var li=$(this).parent();
//		a=li.find("a")
//		if(event.type=="mouseenter"){
//			clearTimeout(times);
//			a.addClass("checked");
//			notemenu.addClass("hover");
//			var move=notemenu.find(".btn_move");
//			var share=notemenu.find(".btn_share");
//			var del=notemenu.find(".btn_delete");
//			move.click(noteMove);
//			share.click(noteShare);
//			del.click(noteDel);
//		}
//		if(event.type=="mouseleave"){
//			a.removeClass("checked");
//			notemenu.removeClass("hover");
//			notemenu.removeClass("note_menu_show");
//		}
//	});
//	//如果鼠标移出按钮button
//	if(event.type=="mouseout"){			
//		s=setTimeout(function(){
//			notemenu.removeClass();
//			notemenu.addClass("note_menu");
//		},3000);
//	}	
function hidenotemenu(){
	$("#note-body .note_menu").hide();
}
function noteAction(){
	if($(this).hasClass("btn_move")){
		noteMove();
		return;
	}
	if($(this).hasClass("btn_share")){
		noteShare();
		return;
	}
	if($(this).hasClass("btn_delete")){
		noteDel();
		return;
	}
}
//得到笔记的Id
function getNoteId(){
//	var noteli=$(btn).parent().parent().parent().parent();
	var noteli=$("#note-body a.checked").parent();
	var noteId=noteli.data("noteId");
	return noteId;
}
//移动笔记
function noteMove(){
	var noteId=getNoteId();
	var book=$("#book-body li .checked").parent();
	var bookId=book.data("bookId");
	//显示移动笔记的选择窗口
	$("#can").load("alert/alert_move.html",function(){
		$.ajax({
			url:"book/findNoteBook.do",
			type:"post",
			data:{"cn_user_id":getCookie("cn_user_id")},
			dataType:"json",
			success:function(result){
				var list=result.obj;
				$("#moveSelect").empty();
				var bookli=$('<option value="none">- 请选择 -</option>');
				$("#moveSelect").append(bookli);
				for(var i=0;i<list.length;i++){
					if(list[i].cn_notebook_id==bookId){
						continue;
					}
					addNoteBookOption(list[i].cn_notebook_name, list[i].cn_notebook_id);
				}
			}
		});
	});
	//添加点击事件
	$("#can").on("click","#sure_move",function(){ 
		
		var selectId=$("#moveSelect option:selected").val();
		if(selectId=='none'){
			return;
		}
			$.ajax({
				url:"note/moveNote.do",
				type:"post",
				data:{"bookId":selectId,"noteId":noteId},
				dataType:"json",
				success:function(result){
					var status=result.status;
					var msg=result.msg;
					alert(msg);
					if(status=='0'){
						var noteli=$("#note-body a.checked").parent();
						noteli.remove();
					}
				}
			});
		
		
	});
}
//添加笔记本到下拉框
function addNoteBookOption(name,id){
	var bookli=$('<option value="'+id+'">- '+name+ '-</option>');
	$("#moveSelect").append(bookli);
}
//添加笔记到分享
function noteShare(){
	var notelia=$("#note-body a.checked");
	var tab=notelia.find("i:visible");
	//console.log($(tab[0]).attr("style"))
	var noteId=getNoteId();
	if(tab.length==3){
		$("#can").load("alert/alert_delete_share.html");
		//取消分享
		$("#can").on("click","#sure_delete_share",function(){
			console.log(noteId);
			$.ajax({
				url:"share/deleteShare.do",
				type:"post",
				data:{"noteId":noteId},
				dataType:"json",
				success:function(result){
					var status=result.status;
					var msg=result.msg;
					alert(msg);
					if(status=='0'){
						notelia.find(".tab").hide();
						notelia.parent().data("share","");
					}
				}
			})
		});
		return ;
	}
	$("#can").load("alert/alert_share.html");
	//分享
	$("#can").on("click","#sure_share",function(){
//		changeNoteStatus(noteId,3);
		$.ajax({
			url:"share/shareNote.do",
			type:"post",
			data:{"noteId":noteId},
			dataType:"json",
			success:function(result){
				var status=result.status;
				var msg=result.msg;
				alert(msg)
				var li=$("#note-body a.checked").parent();
				li.data("share","true");
				var tab=li.find(".tab");
				tab.show();
			}
		});
	});
}
//改变笔记的状态id
function changeNoteStatus(id,num){
	$.ajax({
		url:"note/changeStatus.do",
		type:"post",
		data:{"noteId":id,"status":num},
		dataType:"json",
		success:function(result){
			var status=result.status;
			var msg=result.msg;
			alert(msg);
			if(status=='0'){
				var noteli=$(".contacts-list a.checked").parent();
				noteli.remove();
			}
		}
	});
}
function noteDel(){
	var noteId=getNoteId(this);
	var noteli=$("#note-body a.checked").parent();
	var share=noteli.data("share");
	if(share=="true"){
		alert("处于分享状态，不能被删除！");
		return;
	}
//	console.log(getNoteId(this));
	$("#can").load("alert/alert_delete_note.html");
	$("#can").on("click","#sure_delete_note",function(){
		changeNoteStatus(noteId,2);
	});
}