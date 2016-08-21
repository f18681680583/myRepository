<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>我的笔记</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="x-pjax-version" content="v173">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="ico/favico-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="ico/favico-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="ico/favico-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="ico/favico-57-precomposed.png">
<link rel="shortcut icon" href="ico/favico.png">
<link rel="shortcut icon" href="ico/favico.ico">
<link rel="stylesheet" href="styles/icon.css" />
<link rel="stylesheet" href="styles/main.css" />
<!-- Google-Code代码高亮CSS -->
<link rel="stylesheet" href="styles/prettify.css" />
<!-- Ueditor编辑器CSS -->
<link href="styles/umeditor.min.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="scripts/jquery.min.js"></script>

<!-- Bootstrap框架JS -->
<script src="scripts/bootstrap.min.js"></script>
<script src="scripts/js-prototype.js"></script>
<script src="scripts/theme-setup.js"></script>
<!-- Google-Code代码高亮JS -->
<script class="re-execute" src="scripts/run_prettify.js"></script>
<!-- Ueditor编辑器JS -->
<script type="text/javascript" charset="utf-8"
	src="scripts/ue/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="scripts/ue/umeditor.min.js"></script>
<script type="text/javascript" src="scripts/ue/lang/zh-cn.js"></script>
<script type="text/javascript" src="scripts/cookie_util.js"></script>
<script type="text/javascript" charset="utf-8" src="scripts/edit.js"></script>
<script type="text/javascript" charset="utf-8" src="scripts/alert.js"></script>
<script type="text/javascript" charset="utf-8" src="scripts/note.js"></script>
<script type="text/javascript" charset="utf-8" src="scripts/specialNote.js"></script>
<script type="text/javascript" charset="utf-8" src="scripts/share.js"></script>
<script type="text/javascript" charset="utf-8" src="scripts/changePhoto.js"></script>
<script type="text/javascript" charset="utf-8" src="scripts/information.js"></script>
<script type="text/javascript" charset="utf-8" src="scripts/ajax.js"></script>
<script type="text/javascript">
	$(function() {
		//$("#modalBasic").hide();
		findNoteBook();
		$("#add_notebook").click(addNoteBook);
		$("#can").on("click",".cancle,.close",cancle);
		$("#can").on("click","#sure_addbook",createNoteBook);
		$("#can").on("click","#sure_addnote",createNote);
		$("#add_note").click(addNote);
		$("#book-body").on("click","li",findNote);
		$("#book-body").on("dblclick","li a",renameNoteBook);
		$("#book-body").on("click",".btn_delete",deleteBook);
		//$("#note-body").on("click","li",findNoteText);
		//$("#note-body").on("mouseover mouseout",".btn_slide_down",showNoteMenu);
		$("#note-body").on("click",".btn_slide_down",showNoteMenu);
		$("#note-body").on("click",".note_menu .btn_move,.btn_share,.btn_delete",noteAction)
		$("div").click(hidenotemenu);
		$("#note-body").on("click",".note_menu ",function(){
			return false;
		});
		$("#save_note").click(saveNote);
		$("#pc_part_3").hide();
		$("#rollback_button").click(function(){
			findSpecialNote(2,4);
		});
		$("#like_button").click(function(){
			//findSpecialNote(4,7);
		});
		$("#action_button").click(function(){
			//findSpecialNote(5,8);
		});
		$("#pc_part_4 .contacts-list").on("click",".btn_delete",deleteNote);
		$("#pc_part_4 .contacts-list").on("click",".btn_replay",replayNote);
		$("#pc_part_7 .contacts-list").on("click",".btn_delete",cancelLike);
		$(".note-part .contacts-list").on("click","li",findNoteText);
		$("#search_note").keydown(searchNote);
		$("#pc_part_6 .contacts-list").on("click","li",findShareText);
		$("#pc_part_6 .contacts-list").on("click",".btn_like",likeNote);
	//	$("#pc_part_4 .contacts-list").unbind("click");
		$("#more_note").click(search);
	});
</script>
</head>
<body class="animated fadeIn">
	<header class="header">
	<div class="header-brand">
		<a data-pjax=".content-body" href="edit.jsp"> <img
			class="brand-logo" src="images/dummy/8986f28e.stilearn-logo.png"
			alt="Stilearn Admin Sample Logo">
		</a>
	</div>
	<div class="header-profile">
		<div class="profile-nav">
			<span class="profile-username"><c:out
					value="${user.cn_user_nick }"></c:out></span> <a
				class="dropdown-toggle" data-toggle="dropdown"> <span
				class="fa fa-angle-down"></span>
			</a>
			<ul class="dropdown-menu animated flipInX pull-right" role="menu">
				<li><a id="information" href="javascript:information();"><i class="fa fa-user"></i>个人信息</a></li>
				<li class="divider"></li>
				<li><a id="information" href="javascript:havefun();"><i class="fa fa-user"></i>轻松一下</a></li>
				<li class="divider"></li>
				<li><a href="Change_password.jsp"><i class="fa fa-user"></i>
						修改密码</a></li>
				<li class="divider"></li>
				<li><a id="logout" href="javascript:exit();"><i class="fa fa-sign-out"></i>
						退出登录</a></li>
			</ul>
		</div>
	</div>
	<div class="photo">
		<div class="photo_nav">
			<a href="javascript:changePhoto();"><img alt="头像"
				src='<c:out value="${user.cn_user_photo}"></c:out>'
				id="image_photo" /></a>
		</div>
	</div>
	<form class="form-inline" onsubmit="return false;">
		<button type="button" class="btn btn-default btn-expand-search">
			<i class="fa fa-search"></i>
		</button>
		<div class="toggle-search">
			<input type="text" class="form-control" placeholder="搜索笔记"
				id='search_note'>
			<button type="button" class="btn btn-default btn-collapse-search">
				<i class="fa fa-times"></i>
			</button>
		</div>
	</form>
	<ul class="hidden-xs header-menu pull-right">
		<li><a href="activity.html" target='_blank' title="笔记活动">活动</a></li>
	</ul>
	</header>
	<div class="row" style='padding: 0;' id='center'>
		<!-- alert_background-->
		<div class="opacity_bg" style='display: none'></div>
		<!-- alert_notebook -->
		<div id="can"></div>
		<div class="col-xs-2"  style='padding: 0;' id='pc_part_1'>
			<!-- side-right -->
			<div class="pc_top_first">
				<h3>全部笔记本</h3>
				<button type="button" class="btn btn-default btn-xs btn_plus"
					id='add_notebook'>
					<i class="fa fa-plus"></i>
				</button>
			</div>
			<aside class="side-right" id='first_side_right'>
			<div class="module" data-toggle="niceScroll">
				<div class="chat-contact">
					<div class="contact-body">
						<ul class="contacts-list" id="book-body">
						</ul>
					</div>
				</div>
			</div>
			</aside>
			<div class="row clear_margin">
				<div class="col-xs-4 click" id='rollback_button' title='回收站'>
					<i class='fa fa-trash-o'
						style='font-size: 20px; line-height: 31px;'></i>
				</div>
				<div class="col-xs-4 click" id='like_button' title='收藏笔记本'>
					<i class='fa fa-star' style='font-size: 20px; line-height: 31px;'></i>
				</div>
				<div class="col-xs-4 click" id='action_button' title='参加活动笔记'>
					<i class='fa fa-users' style='font-size: 20px; line-height: 30px;'></i>
				</div>
			</div>
		</div>
		<!-- 全部笔记本 -->
		<!-- 全部笔记 -->
		<div class="col-xs-3 note-part" style='padding: 0;' id='pc_part_2'>
			<div class="pc_top_second" id='notebookId'>
				<h3>全部笔记</h3>
				<button type="button" class="btn btn-default btn-xs btn_plus"
					id='add_note'>
					<i class="fa fa-plus"></i>
				</button>
			</div>
			<aside class="side-right" id='second_side_right'>
			<div class="module" data-toggle="niceScroll">
				<div class="chat-contact">
					<div class="contact-body">
						<ul class="contacts-list" id="note-body">
						</ul>
					</div>
				</div>
			</div>
			</aside>
		</div>
		<!-- 全部笔记 -->
		<!-- 回收站笔记 -->
		<div class="col-xs-3"  style='padding: 0; display: none;'
			id='pc_part_4'>
			<div class="pc_top_second">
				<h3>回收站笔记</h3>
			</div>
			<aside class="side-right" id='four_side_right'>
			<div class="module" data-toggle="niceScroll">
				<div class="chat-contact">
					<div class="contact-body">
						<ul class="contacts-list">
						</ul>
					</div>
				</div>
			</div>
			</aside>
		</div>
		<!-- 回收站笔记 -->
		<!-- 搜索笔记列表 -->
		<div class="col-xs-3" style='padding: 0; display: none;'
			id='pc_part_6'>
			<div class="pc_top_second">
				<h3>搜索结果</h3>
			</div>
			<aside class="side-right" id='sixth_side_right'>
			<div class="module" data-toggle="niceScroll">
				<div class="chat-contact">
					<div class="contact-body">
						<ul class="contacts-list">
						</ul>
					</div>
				</div>
				<div id='more_note'>更多...</div>
			</div>
			</aside>
		</div>
		<!-- 搜索笔记列表 -->
		<!-- 收藏笔记列表 -->
		<div class="col-xs-3 note-part" style='padding: 0; display: none;'
			id='pc_part_7'>
			<div class="pc_top_second">
				<h3>已收藏笔记</h3>
			</div>
			<aside class="side-right" id='seventh_side_right'>
			<div class="module" data-toggle="niceScroll">
				<div class="chat-contact">
					<div class="contact-body">
						<ul class="contacts-list">
						</ul>
					</div>
				</div>
			</div>
			</aside>
		</div>
		<!-- 收藏笔记列表 -->
		<!-- 参加活动的笔记列表 -->
		<div class="col-xs-3 note-part" style='padding: 0; display: none;'
			id='pc_part_8'>
			<div class="pc_top_second">
				<h3>参加活动的笔记</h3>
			</div>
			<aside class="side-right" id='eighth_side_right'>
			<div class="module" data-toggle="niceScroll">
				<div class="chat-contact">
					<div class="contact-body">
						<ul class="contacts-list">
							<!--li class="offline"><a ><i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> 样式用例（点击无效）</a></li-->
						</ul>
					</div>
				</div>
			</div>
			</aside>
		</div>
		<!-- 参加活动的笔记列表 -->
		<!-- 编辑笔记 -->
		<div class="col-sm-7" id='pc_part_3'>
			<!-- side-right -->
			<div class="pc_top_third">
				<div class="row">
					<div class="col-xs-9">
						<h3>编辑笔记</h3>
					</div>
					<div class="col-xs-3" id="save-button">
						<button type="button" class="btn btn-block btn-sm btn-primary"
							id='save_note'>保存笔记</button>
					</div>
				</div>
			</div>
			<aside class="side-right" id='third_side_right'>
			<div class="module" data-toggle="niceScroll">
				<div class="chat-contact">
					<div class="contact-body clear_margin">
						<!--- 笔记标题 --->
						<div class="row">
							<div class="col-xs-8">
								<input type="text" class="form-control" id="input_note_title"
									placeholder='笔记标题...'/>
							</div>
						</div>
						<!--- 笔记标题 --->
						<div class="row">
							<div class="col-sm-12">
								<!--- 输入框 --->
								<script type="text/plain" id="myEditor"
									style="width:100%;height:400px;">
										</script>
								<!--- 输入框 --->
							</div>
						</div>
					</div>
				</div>
			</div>
			</aside>
		</div>
		<!-- 编辑笔记 -->
		<!-- 预览笔记 -->
		<div class="col-sm-7" id='pc_part_5' style='display: none;'>
			<div class="pc_top_third">
				<div class="row">
					<div class="col-xs-9">
						<h3>预览笔记</h3>
					</div>
				</div>
			</div>
			<aside class="side-right" id='fifth_side_right'>
			<div class="module" data-toggle="niceScroll">
				<div class="chat-contact">
					<div class="contact-body clear_margin">
						<h4 id="noput_note_title"></h4>
						<p></p>
					</div>
				</div>
			</div>
			</aside>
		</div>
		<!-- 预览笔记 -->
	</div>
	<footer>
	<p>&copy; 2014 Stilearning</p>
	<div
		style='position: absolute; top: 5PX; height: 30px; right: 20px; line-height: 26px; border: 1px solid #0E7D76; display: none; background: #fff'>
		<strong style='color: #0E7D76; margin: 0 10px;'></strong>
	</div>
	</footer>
	<script type="text/javascript">
		//加载DOM之后处理页面高度
		function get_dom(e) {
			return document.getElementById(e);
		}
		function set_height() {
			var pc_height = window.innerHeight;
			pc_height = pc_height - 132;
			get_dom('first_side_right').style.height = (pc_height - 31) + 'px';
			get_dom('second_side_right').style.height = pc_height + 'px';
			get_dom('four_side_right').style.height = pc_height + 'px';
			get_dom('sixth_side_right').style.height = pc_height + 'px';
			get_dom('seventh_side_right').style.height = pc_height + 'px';
			get_dom('eighth_side_right').style.height = pc_height + 'px';
			get_dom('third_side_right').style.height = (pc_height - 15) + 'px';
			get_dom('fifth_side_right').style.height = (pc_height - 15) + 'px';
		}
		function myEditorWidth() {
			var dom = get_dom('third_side_right');
			var style = dom.currentStyle || window.getComputedStyle(dom, null);
			get_dom('myEditor').style.width = style.width;
		}
		set_height();
		myEditorWidth();
		//改变窗口大小时调整页面尺寸
		window.onresize = function() {
			set_height();
			var width = $('#third_side_right').width() - 35;
			$('.edui-container,.edui-editor-body').width(width);
			$('#myEditor').width(width - 20);
		};
	</script>
  

	<script type="text/javascript">
		//实例化Ueditor编辑器
		var um = UM.getEditor('myEditor');
	</script>
	

</body>
</html>