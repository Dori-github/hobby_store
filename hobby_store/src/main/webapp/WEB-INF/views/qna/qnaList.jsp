<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 중앙 컨텐츠 시작 -->
<script type="text/javascript">
	let result = '${result}';
	if(!location.hash && result == 'success'){
		alert('문의 작성 완료');
		history.replaceState('','','#rs');
	}
	
	$(function(){
		//검색 유효성 체크
		$('#search_form').submit(function(){
			if($('#keyword').val().trim()==''){
				alert('검색어를 입력하세요!');
				$('#keyword').val('').focus();
				return false;
			}
		});
	});	
</script>

<div class="page-main">
	<h2>문의사항 목록</h2>
	<form action="list.do" id="search_form" method="get">
		<ul class="search">
			<li><select name="keyfield" id="keyfield">
					<option value="1"
						<c:if test="${param.keyfield == 1}">selected</c:if>>제목</option>
					<option value="2"
						<c:if test="${param.keyfield == 2}">selected</c:if>>내용</option>
					<option value="3"
						<c:if test="${param.keyfield == 3}">selected</c:if>>제목+내용</option>
			</select></li>
			<li><input type="search" name="keyword" id="keyword"
				value="${param.keyword}"></li>
			<li>
				<input type="submit" value="검색"> 
				<input type="button" value="목록" onclick="location.href='list.do'">
			</li>
		</ul>
	</form>
	<c:if test="${!empty user}">
		<div class="align-right">
			<input type="button" value="문의사항 작성"
				onclick="location.href='write.do'">
		</div>
	</c:if>
	<c:if test="${count == 0}">
		<div class="result-display">문의사항이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
		<table class="striped-table">
			<tr>
				<th></th> <!-- 강의,상품,공간대여 null값인거는 가리고  -->
				<th>번호</th>
				<th>제목</th>
				<th>내용</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			<c:forEach var="qna" items="${list}">
				<tr>
					<td>
						<c:if test="${empty qna.course_num && qna.space_num}">${qna.items_num}</c:if>
						<c:if test="${empty qna.items_num && qna.space_num}">${qna.course_num}</c:if>
						<c:if test="${empty qna.course_num && qna.items_num}">${qna.space_num}</c:if>
					</td>
					<td>${qna.qna_num}</td>
					<td>
						<a href="detail.do?qna_num=${qna.qna_num}">${qna.qna_title}</a>
					</td>
					<td>
						<a href="detail.do?qna_num=${qna.qna_content}">${qna.qna_content}</a>
					</td>
					<td>
						<c:if test="${empty qna.mem_nickname}">${qna.mem_id}</c:if> 
					    <c:if test="${!empty qna.mem_nickname}">${qna.mem_nickname}</c:if>
					</td>
					<td>${qna.qna_date}</td>
					<td>${qna.qna_hit}</td>
				</tr>
			</c:forEach>
			<hr size="1" width="100%">
		</table>
		<hr size="1" width="100%">
		<div class="align-center">${page}</div>
		<hr size="1" width="100%">
	</c:if>
</div>
<!-- 중앙 컨텐츠 끝 -->