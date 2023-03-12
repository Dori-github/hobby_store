<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="${pageContext.request.contextPath}/css/mypage.css" rel="stylesheet">
<!-- 사이드바 시작 -->
<div id="mypage_main">
<!-- 사이드바 끝 -->
<!-- 회원정보 시작 -->
	<div id="content">
		<div class="container py-5 h-100">
		<div class=" justify-content-center align-items-center">
			<div class="col-xl-5 align-center">
		<img src="${pageContext.request.contextPath}/member/photoView.do" width="200" height="200" class="my-photo">
		<h2>
		<c:if test="${member.mem_auth==3}">[강사]</c:if>
			<c:if test="${!empty member.mem_nickname}">${member.mem_nickname}</c:if>
			<c:if test="${empty member.mem_nickname}">${member.mem_name}</c:if>
			 님 환영합니다!</h2>
		</div>
		<ul class="member-ul align-center">
			<li>ID : ${member.mem_id}</li>
			<li>이름 : ${member.mem_name}</li>
			<li>전화번호 : ${member.mem_cell}</li>
			<li>이메일 : ${member.mem_email}</li>
			<li>우편번호 : ${member.mem_zipcode}</li>
			<li>주소 : ${member.mem_address1} ${member.mem_address2}</li>
			<li>선호지역 : ${member.country_detail}</li>
			<li>관심사 : ${member.like_detail}</li>
			<li>가입날짜 : ${member.mem_date}</li>
			<c:if test="${!empty member.mem_mdate}">
			<li>정보 수정일 : ${member.mem_mdate}</li>
			</c:if>
		</ul>
		<input type="button" value="회원정보수정" onclick="location.href='update.do'" class="btn mb-4 mypage-btn">
		<input type="button" value="포인트 내역 조회" onclick="location.href='${pageContext.request.contextPath}/order/point.do'" class="btn mb-4 mypage-btn">   	
		</div>
	</div>
	
	

	</div>
</div>
<!-- 회원정보 끝 -->