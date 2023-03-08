<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!-- 중앙 컨텐츠 시작 -->
<div class="page-main">
	<div id="content">
	<h2>회원목록</h2>
	<c:if test="${count==0}">
		<table class="table table-group-divider align-center">
			<tr>
				<td>
				표시할 회원정보가 없습니다.
				</td>
			</tr>
		</table>
	</c:if>
	
	<c:if test="${count > 0}">
	<table class="table table-group-divider align-center">
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>전화번호</th>
			<th>이메일</th>
			<th>가입일</th>
			<th>권한</th>
		</tr>
		<c:forEach var="member" items="${list}">
		<tr>
			<td>${member.mem_id}</td>
			<td>${member.mem_name}</td>
			<td>${member.mem_cell}</td>
			<td>${member.mem_email}</td>
			<td>${member.mem_date}</td>
			<td>
				<c:if test="${member.mem_auth==0}">탈퇴</c:if>
				<c:if test="${member.mem_auth==1}">정지</c:if>
				<c:if test="${member.mem_auth==2}">일반</c:if>
				<c:if test="${member.mem_auth==9}">관리</c:if>
			</td>
		</tr>
		</c:forEach>
	</table>
	<div class="align-center">${page}</div>
	</c:if>
</div>
</div>
<!-- 중앙 컨텐츠 끝 -->



