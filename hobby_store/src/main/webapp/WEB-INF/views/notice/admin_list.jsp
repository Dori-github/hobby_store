<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="${pageContext.request.contextPath}/css/notice.css" rel="stylesheet">

<!-- 공지사항목록(관리자용) 시작 -->
<script type="text/javascript">
	$(function(){
		$('#search_form').on('submit',function(){
			if($('#keyword').val().trim()==''){
				alert('검색어를 입력하세요');
				$('#keyword').val('').focus();
				return false;
			}
		});
	});
</script>
<div class="page-main">
	<h2>공지사항목록</h2>
	<form id="search_form" action="admin_list.do" method="get">
		<ul class="search">
			<li><select name="keyfield" class="form-select">
					<option value="1" <c:if test="${param.keyfield==1}">selected</c:if>>제목</option>
					<option value="2" <c:if test="${param.keyfield==2}">selected</c:if>>내용</option>
					<option value="3" <c:if test="${param.keyfield==3}">selected</c:if>>제목+내용</option>
			</select></li>
			<li><input type="search" size="16" name="keyword" id="keyword"
				value="${param.keyword}"></li>
			<li><input type="submit" value="검색"></li>
		</ul>
	</form>
	<div class="align-right">
		<input type="button" value="공지사항등록" onclick="location.href='admin_write.do'"> 
		<input type="button" value="목록" onclick="location.href='admin_list.do'">
	</div>
	<c:if test="${count == 0}">
		<div class="result-display">공지가 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
		<table class="striped-table">
			<tr>
				<th>번호</th>
				<th>필독</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>마감일</th>
				<th>조회수</th>
				<th>좋아요수</th>
			</tr>
			<c:forEach var="notice" items="${list}">
				<tr>
					<td>${notice.noti_num}</td>
					<td>
						<c:if test="${notice.status==1}">일반</c:if> 
					    <c:if test="${notice.status==2}">필독</c:if>
					</td>
					<td><img src="imageView.do?noti_num=${notice.noti_num}&noti_type=1" width="80" height="80"></td>
					<td>
						<a href="detail.do?noti_num=${notice.noti_num}">${notice.noti_title}</a>
					</td>
					<td>
						<a href="detail.do?noti_num=${notice.noti_content}">${notice.noti_content}</a>
					</td>
					<td>
						<c:if test="${empty notice.mem_nickname}">${notice.mem_id}</c:if> 
					    <c:if test="${!empty notice.mem_nickname}">${notice.mem_nickname}</c:if>
					</td>
					<td>${notice.noti_date}</td>
					<td>${notice.noti_end}</td>
					<td>${notice.noti_hit}</td>
					<!-- 공지사항 좋아요 테이블이면 $context ~ 이용해야하는지 ??  -->
					<td>
						<!-- 좋아요 개수 넣어야 -->
					</td>
				</tr>
			</c:forEach>
			<hr size="1" width="100%">
		</table>
		<hr size="1" width="100%">
		<div class="align-center">${page}</div>
		<hr size="1" width="100%">
	</c:if>
</div>
<!-- 공지사항목록(관리자용) 끝 -->
