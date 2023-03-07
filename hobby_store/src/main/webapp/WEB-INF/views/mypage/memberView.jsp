<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="${pageContext.request.contextPath}/css/mypage.css" rel="stylesheet">
<!-- 사이드바 시작 -->
<div id="mypage_main">
<!-- 사이드바 끝 -->
<!-- 회원정보 시작 -->
	<div id="content">
		<img src="${pageContext.request.contextPath}/mypage/photoView.do" width="200" height="200" class="my-photo">
		<h2><c:if test="${!empty member.nick_name}">${member.nick_name}</c:if>
			<c:if test="${empty member.nick_name}">${member.name}</c:if>
			 님 환영합니다!
		 <input type="button" value="회원정보수정"
		       onclick="location.href='update.do'"></h2>
		<ul>
			<li>이름 : ${member.name}</li>
			<li>별명 : ${member.nick_name}</li>
			<li>취미 : ${member.hobby}</li>
			<li>전화번호 : ${member.phone}</li>
			<li>이메일 : ${member.email}</li>
			<li>우편번호 : ${member.zipcode}</li>
			<li>주소 : ${member.address1} ${member.address2}</li>
			<li>가입날짜 : ${member.reg_date}</li>
			<c:if test="${!empty member.modify_date}">
			<li>정보 수정일 : ${member.modify_date}</li>
			</c:if>
		</ul>       
	</div>
</div>
<!-- 회원정보 끝 -->