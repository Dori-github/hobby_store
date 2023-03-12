<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 작성 이벤트 조회 시작 -->
<script type="text/javascript">
$(function(){
	$('.modify-btn').on('click',function(){
		$(this).parent().find('.select-attr').css('display','inline');
		$(this).parent().find('.modify-reset').css('display','inline');
		$(this).parent().find('.modify-submit').css('display','inline');
		$(this).parent().find('.modify-attr').css('display','none');
		$(this).hide();
	});
	
	$('.modify-reset').on('click',function(){
		$(this).parent().find('.select-attr').css('display','none');
		$(this).parent().find('.modify-btn').css('display','inline');
		$(this).parent().find('.modify-attr').css('display','inline');
		$(this).parent().find('.modify-submit').css('display','none');
		$(this).hide();
	});
	
	$('.modify-submit').on('click',function(){
		if(!$('.select-attr > option:selected').val()){
			alert('마감여부를 선택해주세요');
			return false;
		}
	});
});
</script>
<div id="content">
	<form action="lec_event.do" id="search_form" method="get">
		<ul class="search">
			<li>
				<select name="keyfield">
					<option value="1" <c:if test="${param.keyfield == 1}">selected</c:if>>등록 날짜순</option>
					<option value="2" <c:if test="${param.keyfield == 2}">selected</c:if>>마감 날짜순</option>					
					<option value="3" <c:if test="${param.keyfield == 3}">selected</c:if>>당첨 날짜순</option>
				</select>
			</li>
			<li>
				<input type="hidden" name="keyword" id="keyword" value="${param.keyword}">
			</li>
			<li>
				<input type="submit" value="찾기">
				<input type="button" value="목록" onclick="location.href='lec_event.do'">
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
			<th>이벤트 등록일</th>
			<th>이벤트 마감일</th>
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
			<form action="lec_modifyAttr.do" method="post">
			<input type="hidden" name="event_num" value="${event.event_num}"/>
			<select name="event_attr" name="event_attr" class="select-attr" style="display:none;">
				<option value="0" <c:if test="${event.event_attr==0}">selected</c:if>>마감</option>
				<option value="1" <c:if test="${event.event_attr==1}">selected</c:if>>진행중</option>
			</select>
			<input type="submit" value="확인" class="modify-submit" style="display:none;">			
			</form>
			<input type="button" value="취소" class="modify-reset" style="display:none;">	
			<c:if test="${event.event_end > today}">
			<div class="modify-attr">
				<c:if test="${event.event_attr==0}">마감</c:if>
				<c:if test="${event.event_attr==1}">진행중</c:if>
			</div>
			<input type="button" value="수정" class="modify-btn">
			</c:if>
			<c:if test="${event.event_end <= today}">마감</c:if>
			</td>
			<td>${event.event_date}</td>
			<td>${event.event_end}</td>
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
</div>
<!-- 작성 이벤트 조회 끝 -->