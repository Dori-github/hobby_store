<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 이벤트 목록 시작 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/event.css">
<div class="event-main">
<div id="event_search">
	<form id="search_form" action="list.do" method="get">
	<h2>이벤트</h2>
		<ul>
			<li>
				<select class="select-form" name="keyfield">
					<option value="1" <c:if test="${param.keyfield==1}">selected</c:if>>제목</option>
					<option value="2" <c:if test="${param.keyfield==2}">selected</c:if>>내용</option>
					<option value="2" <c:if test="${param.keyfield==2}">selected</c:if>>제목+내용</option>
				</select>
			</li>
			<li>
				<input type="search" value="${param.keyword}" id="search_bar" name="keyword" >
				<button type="submit" class="search-btn" id="search_icon"><i class="fa-solid fa-magnifying-glass"></i></button>
			</li>
		</ul>
	</form>
</div>
<div class="select-list">
	<select>
		<option>최신순</option>
		<option>마감순</option>
	</select>
</div>
	<div class="event-space">
	<c:if test="${count > 0}">
		<ul class="event-list">
			<c:forEach var="event" items="${list}">
			<jsp:useBean id="now" class="java.util.Date"/>
			<fmt:formatDate value="${now}" pattern="yyyyMMdd" var="today"/>
			<fmt:formatDate value="${event.event_end}" pattern="yyyyMMdd" var="endDate"/>
				<li>
				<a href="detail.do?event_num=${event.event_num}" <c:if test="${endDate < today or event.event_attr == 0}">class="disabled" style="cursor:default;"</c:if>>
					<div class="event-image">
					<img src="imageView.do?event_num=${event.event_num}" width="300" height="200"></div>
					<div class="event-content"><br>
					<b><strong>${event.event_title}</strong></b>
					<c:if test="${endDate >= today and event.event_attr != 0}"><span class="badge bg-primary limit">진행중</span></c:if>
					<c:if test="${endDate < today or event.event_attr == 0}"><span class="badge bg-danger limit">마감</span></c:if>
					<br>
					<span>${event.event_content}</span><br>
					<span>기간 : ${event.event_date} ~ ${event.event_end}</span>
					<span>조회 : ${event.event_hit}</span>
					<hr size="1" style="background-color:black;">
					</div>
				</a>
				</li>
			</c:forEach>
		</ul>
		<div class="page-num">${page}</div>
		</c:if>
		</div>
	<div class="move-btn float-clear">
	<c:if test="${user.mem_auth>2}">
		<input class="form-btn" type="button" value="이벤트 등록" onclick="location.href='write.do?mem_num=${user.mem_num}'">
	</c:if>
		<input class="form-btn" type="button" value="이벤트 목록" onclick="location.href='list.do'">
	</div>
</div>
<!-- 이벤트 목록 끝 -->