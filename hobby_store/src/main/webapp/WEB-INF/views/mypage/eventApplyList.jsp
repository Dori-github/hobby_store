<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 신청 이벤트 조회 시작 -->
<div id="content">
	<form action="event.do" id="search_form" method="get">
		<ul class="search">
			<li>
				<select name="keyfield" id="keyfield">
					<option value="1" <c:if test="${param.keyfield == 1}">selected</c:if>>신청 날짜순</option>
					<option value="2" <c:if test="${param.keyfield == 2}">selected</c:if>>당첨 일자순</option>
				</select>
			</li>
			<li>
				<input type="hidden" name="keyword" id="keyword" value="${param.keyword}">
			</li>
			<li>
				<input type="submit" value="찾기" class="order-search-btn">
			</li>
		</ul>
	</form>
	<c:if test="${count==0}">
		<table class="table table-group-divider align-center">
			<tr>
				<td>
				표시할 이벤트 정보가 없습니다.
				</td>
			</tr>
		</table>
	</c:if>
	
	<c:if test="${count > 0}">
		<jsp:useBean id="now" class="java.util.Date"/>
		<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today"/>
	<table class="table table-group-divider align-center">
		<tr>
			<th>제목</th>
			<th>진행 상태</th>
			<th>이벤트 신청일</th>
			<th>당첨결과일</th>
			<th>당첨 여부</th>
		</tr>
		<c:forEach var="event" items="${list}">
		<tr>
			<td>
			<a href="${pageContext.request.contextPath}/event/detail.do?event_num=${event.event_num}">
			<img src="${pageContext.request.contextPath}/event/imageView.do?event_num=${event.event_num}" width="60" height="60">
			${event.event_title}
			</a>
			</td>
			<td>
			<c:if test="${event.event_end > today}">마감</c:if>
			<c:if test="${event.event_end <= today}">
			<c:if test="${event.event_attr == 0}">마감</c:if>
			<c:if test="${event.event_attr == 1}">진행중</c:if>
			</c:if>
			</td>
			<td>${event.event_a_date}</td>
			<td>${event.event_rdate}</td>
			<td>
			<c:if test="${event.event_rdate < today}">결과 발표 전</c:if>
			<c:if test="${event.event_rdate >= today}">
			<c:if test="${event.event_a_win==0}">미당첨</c:if>
			<c:if test="${event.event_a_win==1}">당첨</c:if>
			</c:if>
			</td>
		</tr>
		</c:forEach>
	</table>
	<div class="align-center">${page}</div>
	</c:if>
	<input type="button" value="목록" onclick="location.href='event.do'" class="order-list-btn">
</div>
<!-- 신청 이벤트 조회 끝 -->