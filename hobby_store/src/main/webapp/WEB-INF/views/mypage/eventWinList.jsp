<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 이벤트 신청자 조회 시작 -->
<script type="text/javascript">
$(function(){
	$('.modify-btn').on('click',function(){
		$(this).parent().find('.select-win').css('display','inline');
		$(this).parent().find('.modify-reset').css('display','inline');
		$(this).parent().find('.modify-submit').css('display','inline');
		$(this).parent().find('.modify-win').css('display','none');
		$(this).hide();
	});
	
	$('.modify-reset').on('click',function(){
		$(this).parent().find('.select-win').css('display','none');
		$(this).parent().find('.modify-btn').css('display','inline');
		$(this).parent().find('.modify-win').css('display','inline');
		$(this).parent().find('.modify-submit').css('display','none');
		$(this).hide();
	});
	
	$('.modify-submit').on('click',function(){
		if(!$('.select-win > option:selected').val()){
			alert('당첨여부를 선택해주세요');
			return false;
		}
	});
});
</script>
<div id="content">
	<form action="eventWinList.do" id="search_form" method="get">
		<ul class="search">
			<li>
				<select name="keyfield" id="keyfield">
					<option value="1" <c:if test="${param.keyfield == 1}">selected</c:if>>등록 날짜순</option>
					<option value="2" <c:if test="${param.keyfield == 2}">selected</c:if>>마감 날짜순</option>					
					<option value="3" <c:if test="${param.keyfield == 3}">selected</c:if>>당첨 날짜순</option>
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
	<table class="table table-group-divider align-center">
		<tr>
			<th>신청자</th>
			<th>제목</th>
			<th>이벤트 마감일</th>
			<th>이벤트 당첨일</th>
			<th>당첨 여부</th>
		</tr>
		<c:forEach var="event" items="${list}">
		<tr>
			<td>${event.mem_num}</td>
			<td>
			<a href="${pageContext.request.contextPath}/event/detail.do?event_num=${event.event_num}">
			<img src="${pageContext.request.contextPath}/event/imageView.do?event_num=${event.event_num}" width="60" height="60">
			${event.event_title}
			</a>
			</td>
			<td>${event.event_end}</td>
			<td>${event.event_rdate}</td>
			<td>
			<form action="lec_modifyWin.do" method="post">
			<input type="hidden" name="event_num" value="${event.event_num}"/>
			<input type="hidden" name="mem_num" value="${event.mem_num}"/>
			<select name="event_a_win" name="event_a_win" class="select-win" style="display:none;">
				<option value="0" <c:if test="${event.event_a_win==0}">selected</c:if>>미당첨</option>
				<option value="1" <c:if test="${event.event_a_win==1}">selected</c:if>>당첨</option>
			</select>
			<input type="submit" value="수정" class="modify-submit" style="display:none;">			
			</form>
			<input type="button" value="취소" class="modify-reset" style="display:none;">	
			<div class="modify-win">
				<c:if test="${event.event_a_win==0}">미당첨</c:if>
				<c:if test="${event.event_a_win==1}">당첨</c:if>
			</div>
			<input type="button" value="당첨자 수정" class="modify-btn">
			</td>
		</tr>
		</c:forEach>
	</table>
	<div class="align-center">${page}</div>
	</c:if>
	<input type="button" value="목록" onclick="location.href='eventWinList.do'" class="order-list-btn">
</div>
<!-- 이벤트 신청자 조회 끝 -->