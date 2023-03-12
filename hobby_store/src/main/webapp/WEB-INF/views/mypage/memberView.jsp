<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="${pageContext.request.contextPath}/css/mypage.css" rel="stylesheet">
<!-- 사이드바 시작 -->
<div id="mypage_main">
<!-- 사이드바 끝 -->
<!-- 회원정보 시작 -->
	<div id="content" style="margin-left:300px;">
		<img src="${pageContext.request.contextPath}/member/photoView.do" width="200" height="200" class="my-photo">
		<h3><span><c:if test="${!empty member.mem_nickname}">${member.mem_nickname}</c:if></span>
			<span><c:if test="${empty member.mem_nickname}">${member.mem_nickname}</c:if></span>
			 님 환영합니다!
		</h3>
		 <input type="button" value="회원정보수정" class="modify-btn"
		       onclick="location.href='update.do'">
		
		<table class="myPage-info">
			<tr>
				<td>이름</td>
				<td>${member.mem_name}</td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td>${member.mem_cell}</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td>${member.mem_email}</td>
			</tr>
			<tr>
				<td>우편번호</td>
				<td>${member.mem_zipcode}</td>
			</tr>
			<tr>
				<td>주소</td>
				<td>${member.mem_address1} ${member.mem_address2}</td>
			</tr>
			<tr>
				<td>가입날짜</td>
				<td>${member.mem_date}</td>
			</tr>
			<c:if test="${!empty member.mem_mdate}">
			<tr>
				<td>정보수정일</td>
				<td>${member.mem_mdate}</td>
			</tr>
			</c:if>
		</table>
	</div>
</div>
<!-- 회원정보 끝 -->