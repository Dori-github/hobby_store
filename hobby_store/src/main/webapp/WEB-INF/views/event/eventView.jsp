<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 이벤트 상세 시작 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/event.css">
<div class="event-main">
	<c:if test="${event.event_attr == 0}">
		<div class="align-center">
			종료된 이벤트입니다!
			<input type="button" value="목록으로" onclick="location.href='list.do'">
		</div>
	</c:if>
	<c:if test="${event.event_attr == 1}">
	<div class="event-detail">
		<div class="event-top">
		<span class="event-title"><strong>${event.event_title}</strong></span>
		<span class="event-subtitle">
			기간 : ${event.event_end} 조회수 : ${event.event_hit}
		</span>
		</div>
		<div class="event-image">
			<img src="imageView.do?event_num=${event.event_num}" width="600">
		</div>
		<div id="event_detail">
			${event.event_detail}
		</div>
	</div>
	<div class="end-btn">
	<c:if test="${user.mem_auth>2}">
			<input id="modify_btn" type="button" value="이벤트 수정" onclick="location.href='update.do?event_num=${event.event_num}'">
			<input id="delete_btn" type="button" value="이벤트 삭제" id="event_delete">
			<script type="text/javascript">
			let delete_btn = document.getElementById('delete_btn');
			delete_btn.onclick=function(){
				let choice = confirm('삭제하시겠습니까?');
				if(choice){
					location.replace('delete.do?event_num=${event.event_num}');
				}
			};
		</script> 
	</c:if>
	</div>
	</c:if>
</div>
<!-- 이벤트 상세 끝 -->