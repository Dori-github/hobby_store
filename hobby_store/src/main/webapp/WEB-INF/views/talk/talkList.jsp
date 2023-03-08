<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<title>채팅 목록</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/talk.css" />


<!-- 채팅목록 시작 -->
<div class="container">
	<h4 class="modal-title">채팅 목록</h4>
	<form action="talkList.do" id="search_form" method="get">

		<div class="text-center">
			<input type="search" name="keyword" id="keyword"
				class="btn-sm search" value="${param.keyword}">
			<button type="submit" class="btn btn-secondary btn-sm">찾기</button>
			<button type="button" class="btn btn-secondary btn-sm" onclick="location.href='talkList.do'">목록</button>
		</div>
	</form>

	<div class="text-right" style="margin: 10px;">
		<button type="button" class="btn"
			onclick="location.href='talkRoomWrite.do'"
			style="background-color: #FF4E02; color: white;">채팅방 생성</button>
	</div>
	<c:if test="${empty list}">
		<div class="result-display">표시할 채팅방이 없습니다.</div>
	</c:if>
	<c:if test="${!empty list}">
	<table class="table">
		<c:forEach var="talk" items="${list}">
		<tbody>
			<tr>
				<td><a href="talkDetail.do?talkroom_num=${talk.talkroom_num}">${talk.talkroom_name}(${talk.room_cnt})<br>
					<small>${fn:substring(talk.talkVO.message,0,45)}</small></a></td>
				<td>
				<c:if test="${!empty talk.talkVO.chat_date}">${talk.talkVO.chat_date}</c:if>
				<c:if test="${empty talk.talkVO.chat_date}">${talk.talkroom_date}</c:if>
				</td>
			</tr>
		</tbody>
		</c:forEach>
	</table>
	</c:if>
</div>

