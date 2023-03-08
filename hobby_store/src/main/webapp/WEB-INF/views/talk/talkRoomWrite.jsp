<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>채팅방 생성</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/talk.css" />

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/message.js"></script>
<!-- 채팅방 생성 시작 -->
<div class="container">
	<h4 class="modal-title" style="margin-bottom: 30px;margin-top:20px;">채팅방 생성</h4>
	<form action="talkRoomWrite.do" method="post" id="talk_form">
		<!-- 방을 생성하는 사람을 hidden값으로 표시 -->
			<input type="hidden" name="members" id="user"
				data-id="${user.mem_nickname}" value="${user.mem_num}">

		<div class="chat-name">	
			<div class="row" style="margin-left: 30px; margin-right: 30px;">
			<div class="col-2">
				<label>채팅방 이름</label>
			</div>
			<div class="col-9">
				<input type="hidden" class="form-input" placeholder="이름을 입력해주세요" name="talkroom_name" id="talkroom_name">
				<span id="name_span"></span>
				
				<label class="form-check-label" style="padding-left: 30px;">
					<input class="form-check-input" type="checkbox" checked
					id="name_checked"> 자동생성
				</label>
			</div>
			</div>
			</div>
	
		<div class="row"
			style="margin-left: 30px; margin-right: 30px; padding-top: 10px;">
			<div class="col-2">
				<label for="name">회원 검색</label>
			</div>
			<div class="col-9">
				<input type="text" id="member_search" class="form-input"
					list="member_name" autocomplete="off" placeholder="닉네임을 입력해주세요">
				<ul id="search_area"></ul>
				<div id="talk_member"></div>
			</div>

		</div>

<div class="align-center" style="padding-top: 190px;">
	<input type="submit" class="btn btn-secondary btn-sm" value="전송">
	<input type="button" value="목록" class="btn btn-secondary btn-sm"
		onclick="location.href='talkList.do'">
</div>

</form>

</div>

