<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 상단 시작 --> 
<!-- 네비게이션바 시작 -->
<nav class="navbar-expand-lg size">
	<div class="container-fluid">
		<div id="navbar_top">
			<!-- 토글버튼 시작 -->
			<button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#navbarOffcanvasLg" aria-controls="navbarOffcanvasLg">
		      <span class="navbar-toggler-icon"><i class="fa-solid fa-bars toggler-size"></i></span>
		    </button>
			<!-- 토글버튼 끝 -->
			<a id="navbar_brand" class="navbar-brand" href="/main/main.do"><b>취미상점</b></a>
			<!-- 통합검색창 시작 -->
			<form id="search" class="navbar-expand">
				<input class="search-bar" type="search" placeholder="통합검색어를 입력하세요">
				<button type="submit" class="search-btn"><i class="fa-solid fa-magnifying-glass"></i></button>
			</form>
			<!-- 통합검색창 끝 -->
			<!-- 로그인/회원가입 버튼 시작 -->
			<div id="navbar_btn">
				<c:if test="${empty user}">
				<span class="btn-border font-10"><a href="${pageContext.request.contextPath}/member/login.do">로그인</a></span>
				<span class="btn-border font-10" id="btn_color"><a href="${pageContext.request.contextPath}/member/registerUser.do">회원가입</a></span>
				</c:if>
				<c:if test="${!empty user}">
				<span class="btn-border font-10"><a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a></span>
				</c:if>
			</div>
			<!-- 로그인/회원가입 버튼 끝 -->
		</div>
		
		<!-- 메뉴바 시작 -->
		<div class="offcanvas offcanvas-start" tabindex="-1" id="navbarOffcanvasLg" aria-labelledby="navbarOffcanvasLgLabel">
	      	<div class="offcanvas-header">
		      <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
		    </div>
		    <div id="menu_bar">
				<ul class="navbar-nav me-auto my-2 my-lg-0">
					<!--<c:if test="${!empty user && user.auth == 3}"></c:if>-->
					<li id="register_btn"><a>등록</a>
						<ul class="dropdown">
							<li><a href="${pageContext.request.contextPath}/course/courseWrite.do">클래스</a></li>
							<li><a href="${pageContext.request.contextPath}/items/itemsRegister.do">스토어</a></li>
							<li><a href="#">장소대여</a></li>
						</ul>
					</li>
					
					<li><a href="${pageContext.request.contextPath}/course/courseList.do">클래스</a></li>
					<li><a href="${pageContext.request.contextPath}/items/itemsList.do">스토어</a></li>
					<li><a href="${pageContext.request.contextPath}/">장소대여</a></li>
					<li id="community_btn"><a href="${pageContext.request.contextPath}/">커뮤니티</a>
						<ul class="dropdown" id="dropdown">
							<li><a href="#">공지사항</a></li>
							<li><a href="#">자유게시판</a></li>
							<li><a href="#">Q & A</a></li>
						</ul>
					</li>
					<li><a href="${pageContext.request.contextPath}/">이벤트</a></li>
				</ul>
				<!-- 마이페이지 시작 -->
				<div id="mypage">
					<c:if test="${!empty user}">
					<img src="">
					</c:if>
					
					<!--<c:if test="${!empty user && 2 <= user.auth <= 3}">일반회원,강사 -->
					<!--</c:if>-->
					<a href="${pageContext.request.contextPath}/member/myPage.do"> MY PAGE</a>
					<span class="lightgray">|</span>
					<a href="${pageContext.request.contextPath}/cart/cartList.do"><i class="fa-solid fa-cart-shopping"></i></a>
					
					<c:if test="${!empty user && user.auth == 9}"><!-- 관리자 -->
					<a href="${pageContext.request.contextPath}/member/myPage.do"> 관리자 PAGE</a>
					</c:if>
					<c:if test="${!empty user}">
					<span class="btn-border font-10"><a href="${pageContext.request.contextPath}/">로그아웃</a></span>
					</c:if>
					<c:if test="${empty user}">
					<span class="btn-border font-10" id="btn_color"><a href="${pageContext.request.contextPath}/member/registerUser.do">회원가입</a></span>
					<span class="btn-border font-10"><a href="${pageContext.request.contextPath}/member/login.do">로그인</a></span>
					</c:if>
				</div>
				<!-- 마이페이지 끝 -->
			</div><!-- end of menu_bar -->
		</div>
		<!-- 메뉴바 끝 -->
	</div><!-- end of container-fluid -->
</nav>
<!-- 네비게이션바 끝 -->
<!-- 상단 끝 -->
