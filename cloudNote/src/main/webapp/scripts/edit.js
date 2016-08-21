//找到该用户所有的笔记本
function findNoteBook(){
	$.ajax({
		url:"book/findNoteBook.do",
		type:"post",
		data:{"cn_user_id":getCookie("cn_user_id")},
		dataType:"json",
		success:function(result){
			var list=result.obj;
			for(var i=0;i<list.length;i++){
				createbookli(list[i].cn_notebook_name, list[i].cn_notebook_id);
			}
		}
	});
}
//删除笔记本
function deleteBook(){
	var book=$(this).parent().parent();
	var bookId=book.data("bookId");
	$.ajax({
		url:"book/deleteBook.do",
		type:"post",
		data:{"bookId":bookId},
		dataType:"json",
		success:function (result){
			var status=result.status;
			var msg=result.msg;
		//	console.log(bookId)
			alert(msg);
			if(status=='0'){
				book.remove();
			}
		},
	});
	return false;
}
//添加笔记本的窗口
function addNoteBook(){
	var userId=getCookie("cn_user_id");
	if(userId==""||userId==null){
		rebackLogin();
		return;
	}
	$("#can").load("alert/alert_notebook.html");
	$(".opacity_bg").show();
}
//创建笔记本
function createNoteBook(){
	$(".col-sm-8 span").html("");
	var bookname=$("#input_notebook").val().trim();
	var userId=getCookie("cn_user_id");
	if(bookname==""){
		$(".col-sm-8 span").html("笔记本名字不能为空");
		return;
	}
	$.ajax({
		url:"book/createNoteBook.do",
		type:"post",
		data:{"userId":userId,"bookname":bookname},
		dataType:"json",
		success:function(result){
			var status=result.status;
			var book=result.obj;
			var bookId=book.cn_notebook_id;
			var name=book.cn_notebook_name;
			if(status=='0'){
				createbookli(name,bookId);
			}
			alert(result.msg);
		}
	});
}
//创建新的笔记
function createNote(){
	var book=$("#book-body a.checked").parent();
	var userId=getCookie("cn_user_id");
	var bookId=book.data("bookId");
	console.log(bookId)
	var notename=$("#input_note").val().trim();
	if(notename==''){
		$(".col-sm-8 span").html("笔记名字不能为空");
		return;
	}
	$.ajax({
		url:"note/createNote.do",
		type:"post",
		data:{"bookId":bookId,"notename":notename,"userId":userId},
		dataType:"json",
		success:function(result){
			var status=result.status;
			var noteId=result.obj;
			var msg=result.msg;
			alert(msg);
			if(status=='0'){
				createnoteli(notename,noteId,"none");
			}
		}
	});
}
//添加笔记本的li
function createbookli(name,bookId){
	var obj=$('<li class="online">'+
			'<a  class="">'+
			'<i class="fa fa-book" title="online" rel="tooltip-bottom">'+
			'</i><span>'+name+'</span><button type="button" class="btn btn-default btn-xs btn_position btn_delete"><i class="fa fa-times"> </i></button></a></li>');
	obj.data("bookId",bookId);
	$("#book-body").append(obj);
}
//添加笔记的li
function createnoteli(notename,noteId,status){
	var li=$('<li class="online">'
			+ '<a  class="">'
			+ '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'
			+ '<span>'+notename+'</span>'
			+ '<i class="fa fa-sitemap tab" style="display:'+status+'"></i><button type="button" class="btn btn-default btn-xs btn_position btn_slide_down">'
			+ '<i class="fa fa-chevron-down"></i></button></a>'
			+ '<div class="note_menu" tabindex="-1">'
			+ '<dl>'
			+ '<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>'
			+ '<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>'
			+ '<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>'
			+ '</dl>'
			+ '</div>'
			+ '</li>');
	li.data("noteId",noteId);
	if(status=='inline'){
		li.data("share","true");
	}
	$("#note-body").append(li);
}
function addNote(){
	var userId=getCookie("cn_user_id");
	if(userId==""||userId==null){
		rebackLogin();
		return;
	}
	var book=$("#book-body a.checked");
	if(book.length==0){
		alert("请选择笔记本！");
		return ;
	}
	$("#can").load("alert/alert_note.html");
	$(".opacity_bg").show();
}
function cancle(){
	$("#can").empty();
	$(".opacity_bg").hide();
}
//找到该笔记本所有的笔记
function findNote() {
	$(".col-xs-3").hide();
	$(".col-sm-7").hide();
	$("#pc_part_2").show();
	$("#book-body a").removeClass();
	$(this).find("a").addClass("checked");
	var bookId = $(this).data("bookId");
	$.ajax({
		url : "note/findNote.do",
		type : "post",
		data : {
			"cn_book_id" : bookId
		},
		dataType : "json",
		success : function(result) {
			$("#pc_part_3").hide();
			var list = result.obj;
			$("#note-body li").remove();
			for (var i = 0; i <list.length; i++) {
				var status="none";
				if(list[i].cn_note_status_id=="3"){	
					status="inline";
				}
				createnoteli(list[i].cn_note_title,list[i].cn_note_id,status);
			}
		}
	});
}
//找到该笔记的内容
function findNoteText(){
//	console.log(event.target);
	$("#save-button").show();
	$(".note-part li a").removeClass();
	$(this).find("a").addClass("checked");
	var noteId=$(this).data("noteId");
//	var len=$(this).next("a").find("i:visiable").length;
//	console.log(len)
//	console.log()
	$.ajax({
		url:"note/findNoteText.do",
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",
		success:function(result){
			if(result.obj==null){
				return;
			}
			$("#pc_part_3").show();
//			$("#pc_part_3").data("noteId",noteId);
			var text=result.obj.cn_note_body;
			var title=result.obj.cn_note_title;
			$("#input_note_title").val(title);
			um.setContent(text);
		}
	});
	return false;
}
//保存笔记
function saveNote(){
	var title=$("#input_note_title").val().trim();
	if(title==""){
		alert("标题不能为空！");
		return;
	}
	var share=$("#note-body a.checked").parent().data("share");
	if(share=='true'){
		alert("笔记处于分享状态，不可被更改！，取消分享后可更改");
		return;
	}
	var text=um.getContent();
	var $li=$(".note-part a.checked").parent();
	var noteId=$li.data("noteId");
	$.ajax({
		url:"note/saveNote.do",
		type:"post",
		data:{"title":title,"text":text,"noteId":noteId},
		dataType:"json",
		success:function(result){
			if(result.status=='0'){
				alert(result.msg);
				var s=$(".contacts-list a.checked span");
				s.html(title);
			}else{
				alert(result.msg);
			}
		}
	});
}
//修改笔记本名称
function renameNoteBook(){
	$("#can").load("alert/alert_rename.html");
	var li=$(this).parent();
	var bookId=li.data("bookId");
	$("#can").on("click","#sure_rename",function(){
		$(".col-sm-8 span").html("");
		var name=$("#input_notebook_rename").val();
		if(name==''){
			$(".col-sm-8 span").html("笔记本名称不能为空");
			return;
		}
		$.ajax({
			url:"book/rename.do",
			type:"post",
			data:{"bookId":bookId,"bookname":name},
			dataType:"json",
			success:function(result){
				var status=result.status;
				var msg=result.msg;
				alert(msg);
				if(status=='0'){
					li.find("span").html(name);
				}
			}
		});
	});	
}
function exit(){
	delCookie("cn_user_id");
	delCookie("cn_user_nick");
	delCookie("cn_user_photo");
	window.location.href="log_in.html";
}
