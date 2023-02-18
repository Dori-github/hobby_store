<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 상단 시작 -->
<!-- 네비게이션바 시작 -->
<nav class="navbar-expand-lg size">
	<div id="navbar_top">
		<a id="navbar_brand" class="navbar-brand" href="main.do">취미상점</a>
		<!-- 통합검색창 시작 -->
		<form id="search">
			<input class="search-bar" type="search" placeholder="통합검색어를 입력하세요">
			<button type="submit" class="search-btn"><i class="fa-solid fa-magnifying-glass"></i></button>
		</form>
		<!-- 통합검색창 끝 -->
		<!-- 로그인/회원가입 버튼 시작 -->
		<div id="navbar_btn">
			<span class="btn-border"><a href="${pageContext.request.contextPath}/member/login.do">로그인</a></span>
			<span class="btn-border" id="btn_color"><a href="${pageContext.request.contextPath}/member/registerUser.do">회원가입</a></span>
		</div>
		<!-- 로그인/회원가입 버튼 끝 -->
		<!-- 토글버튼 시작 -->
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<!-- 토글버튼 끝 -->
	</div>
	
	<!-- 메뉴바 시작 -->
	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul>
			<li><a href="${pageContext.request.contextPath}/">등록</a>
				<ul class="dropdown">
					<li><a href="#">클래스</a></li>
					<li><a href="#">스토어</a></li>
					<li><a href="#">장소대여</a></li>
				</ul>
			</li>
			<li><a href="${pageContext.request.contextPath}/course/list.do">클래스</a></li>
			<li><a href="${pageContext.request.contextPath}/">스토어</a></li>
			<li><a href="${pageContext.request.contextPath}/">장소대여</a></li>
			<li><a href="${pageContext.request.contextPath}/">커뮤니티</a>
				<ul class="dropdown" id="dropdown">
					<li><a href="#">공지사항</a></li>
					<li><a href="#">자유게시판</a></li>
					<li><a href="#">Q & A</a></li>
				</ul>
			</li>
			<li><a href="${pageContext.request.contextPath}/">이벤트</a></li>
		</ul>
	</div>
	<!-- 마이페이지 시작 -->
	<div id="mypage">
		<img src="">
		<a href="${pageContext.request.contextPath}/member/myPage.do"> MY PAGE</a>
		<span class="lightgray">|</span>
		<a href="${pageContext.request.contextPath}/"><i class="fa-solid fa-cart-shopping"></i></a>
		
	</div>
	<!-- 마이페이지 끝 -->
	<!-- 메뉴바 끝 -->
</nav>
<!-- 네비게이션바 끝 -->
<!-- 상단 끝 -->
