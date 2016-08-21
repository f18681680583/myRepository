<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="modal fade in" id="modalBasic_5" tabindex="-1" role="dialog"
	aria-labelledby="modalBasicLabel" aria-hidden="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
				<h4 class="modal-title" id="modalBasicLabel_5">
					<a href="javascript:info();">个人信息</a> 
					<a href="javascript:changeInfo();">修改信息</a>
				</h4>
			</div>
			<div>
				<div id="p"><img alt="头像"
				src='<c:out value="${user.cn_user_photo}"></c:out>'
				id="image_photo" />
				</div>
				<ul class="info" id="info">
					<li>&nbsp;账&nbsp;&nbsp;&nbsp;号&nbsp;:&nbsp;<c:out value="${user.cn_user_name }"></c:out></li>
					<li>&nbsp;昵&nbsp;&nbsp;&nbsp;称&nbsp;:&nbsp;<c:out value="${user.cn_user_nick}"></c:out></li>
					<li>&nbsp;性&nbsp;&nbsp;&nbsp;别&nbsp;:&nbsp;<c:out value="${user.cn_user_sex }"></c:out></li>
					<li>&nbsp;生&nbsp;&nbsp;&nbsp;日&nbsp;:&nbsp;<c:out value="${user.cn_user_borth }"></c:out></li>
					<li>&nbsp;籍&nbsp;&nbsp;&nbsp;贯&nbsp;:&nbsp;<c:out value="${user.cn_user_province }"></c:out>&nbsp;省&nbsp;<c:out value="${user.cn_user_city }"></c:out>&nbsp;市</li>
					<li>&nbsp;签&nbsp;&nbsp;&nbsp;名&nbsp;:&nbsp;<c:out value="${user.cn_user_sign}"></c:out> </li>
				</ul>
				<ul  class="info" id="info2" style="display:none">
					<li class="letter">&nbsp;账&nbsp;&nbsp;&nbsp;号&nbsp;:&nbsp;<c:out value="${user.cn_user_name }"></c:out></li>
					<li>&nbsp;昵&nbsp;&nbsp;&nbsp;称&nbsp;:&nbsp;<input type="text" id="nick" value="<c:out value="${user.cn_user_nick }"></c:out>"></input></li>
					<li>&nbsp;性&nbsp;&nbsp;&nbsp;别&nbsp;:&nbsp; 
						<input type="radio" name="sex" 
							<c:if test="${user.cn_user_sex=='男' }">
								<c:out value="checked"></c:out>
							</c:if> value="<c:out value="男"></c:out>">男
						</input>&nbsp;&nbsp;&nbsp; 
						<input type="radio" name="sex" 
							<c:if test="${user.cn_user_sex=='女' }">
								<c:out value="checked"></c:out>
							</c:if> value="<c:out value="女"></c:out>">女
						</input>&nbsp;&nbsp;&nbsp; 
						<input type="radio" name="sex" 
							<c:if test="${user.cn_user_sex=='未知' }">
								<c:out value="checked"></c:out>
							</c:if> value="<c:out value="未知"></c:out>">未知
						</input>
					
					</li>
					<li>&nbsp;生&nbsp;&nbsp;&nbsp;日&nbsp;:&nbsp;
						<input type="date" id="borth" value="<c:out value='${user.cn_user_borth}'></c:out>" style="height:26px"></input>
					</li>
					<li>&nbsp;籍&nbsp;&nbsp;&nbsp;贯&nbsp;:&nbsp;
						<select id="province">
							<option value="none">---请选择---</option>
						</select>&nbsp;&nbsp;&nbsp;
						<select id="city">
							<option value="none">---请选择---</option>
						</select>&nbsp;&nbsp;&nbsp;
					</li>
					<li><p>&nbsp;签&nbsp;&nbsp;&nbsp;名&nbsp;:&nbsp;</p>
						<textarea cols='50' rows='3' >${user.cn_user_sign }</textarea>
					</li>
					<h4 class="modal-title" id="sure_change">
						<a href="javascript:changeInfo();">确认修改</a>
					</h4>
				</ul>
			</div>
		</div>
	</div>
</div>
<script src="scripts/information.js"></script>
<style>
	#sure_change{
		margin-left:200px
	}
	.info>li{
		padding:10px
	}
	#p{float:right; margin:10px 50px;}
</style>