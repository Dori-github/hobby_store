<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!-- 중앙 컨텐츠 시작 -->
<div class="page-main">
	<h2>회원목록</h2>
	<table class="striped-table">
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>전화번호</th>
			<th>이메일</th>
			<th>가입일</th>
			<th>권한</th>
		</tr>
		<c:forEach var="member" items="${memberList}">
		<tr>
			<td>
				<c:if test="${member.auth==0}">
					${member.id}
				</c:if>
				<c:if test="${member.auth>0}">
				<a href="${pageContext.request.contextPath}/member/admin_update.do?mem_num=${member.mem_num}">${member.id}</a>
				</c:if>
			</td>
			<td>${member.name}</td>
			<td>${member.phone}</td>
			<td>${member.email}</td>
			<td>${member.reg_date}</td>
			<td>
				<c:if test="${member.auth==0}">탈퇴</c:if>
				<c:if test="${member.auth==1}">정지</c:if>
				<c:if test="${member.auth==2}">일반</c:if>
				<c:if test="${member.auth==9}">관리</c:if>
			</td>
		</tr>
		</c:forEach>
	</table>
</div>
<!-- 중앙 컨텐츠 끝 -->



