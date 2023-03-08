<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 메인 시작 -->
<div>
	<div class="course-img">
		<div class="img1">
			<img src="${pageContext.request.contextPath}/image_bundle/공예.jpeg">
			<span>공예 <i class="fa-solid fa-chevron-right"></i></span>
		</div>
		<div class="img2">
			<img src="${pageContext.request.contextPath}/image_bundle/쿠킹.jpeg">
			<span>쿠킹 <i class="fa-solid fa-chevron-right"></i></span>
		</div>
		<div class="img3">
			<img src="${pageContext.request.contextPath}/image_bundle/드로잉.jpeg">
			<span>드로잉 <i class="fa-solid fa-chevron-right"></i></span>
		</div>
		<div class="img4">
			<img src="${pageContext.request.contextPath}/image_bundle/운동.jpeg">
			<span>운동 <i class="fa-solid fa-chevron-right"></i></span>
		</div>
		<div class="img5">
			<img src="${pageContext.request.contextPath}/image_bundle/뷰티.jpeg">
			<span>뷰티 <i class="fa-solid fa-chevron-right"></i></span>
		</div>
	</div>
	
	<div class="course-search">
		<div class="search-bar">
			<input type="search" name="keyword" class="search-bar" id="search_bar" value="${param.keyword}" placeholder="주변에 다양한 클래스를 찾아보세요!" autocomplete="off">
			<button type="submit" class="search-btn" id="search_icon"><i class="fa-solid fa-magnifying-glass"></i></button>
		</div>
		<select class="form-select select" id="location" name="location">
				<option hidden="hidden">지역</option>
				<option value="1" <c:if test="${param.location == 1}">selected</c:if>>전체</option>
				<option value="2" <c:if test="${param.location == 2}">selected</c:if>>서울</option>
				<option value="3" <c:if test="${param.location == 3}">selected</c:if>>경기</option>
				<option value="4" <c:if test="${param.location == 4}">selected</c:if>>인천</option>
				<option value="5" <c:if test="${param.location == 5}">selected</c:if>>강원</option>
				<option value="6" <c:if test="${param.location == 6}">selected</c:if>>충북</option>
				<option value="7" <c:if test="${param.location == 7}">selected</c:if>>세종</option>
				<option value="8" <c:if test="${param.location == 8}">selected</c:if>>충남</option>
				<option value="9" <c:if test="${param.location == 9}">selected</c:if>>대전</option>
				<option value="10" <c:if test="${param.location == 10}">selected</c:if>>경북</option>
				<option value="11" <c:if test="${param.location == 11}">selected</c:if>>대구</option>
				<option value="12" <c:if test="${param.location == 12}">selected</c:if>>울산</option>
				<option value="13" <c:if test="${param.location == 13}">selected</c:if>>부산</option>
				<option value="14" <c:if test="${param.location == 14}">selected</c:if>>경남</option>
				<option value="15" <c:if test="${param.location == 15}">selected</c:if>>전북</option>
				<option value="16" <c:if test="${param.location == 16}">selected</c:if>>전남</option>
				<option value="17" <c:if test="${param.location == 17}">selected</c:if>>광주</option>
				<option value="18" <c:if test="${param.location == 18}">selected</c:if>>제주</option>
		</select>
		<select class="form-select select" id="cate" name="cate">
				<option value="1" <c:if test="${param.location == 1}">selected</c:if>>공예</option>
				<option value="2" <c:if test="${param.location == 2}">selected</c:if>>쿠킹</option>
				<option value="3" <c:if test="${param.location == 3}">selected</c:if>>드로잉</option>
				<option value="4" <c:if test="${param.location == 4}">selected</c:if>>운동</option>
				<option value="5" <c:if test="${param.location == 5}">selected</c:if>>뷰티</option>
		</select>
	</div>
	<!-- 클래스 목록 가져오기 -->
	<div class="course-list">
		<h5>선호지역 BEST 클래스</h5>
		<div>캐러셀</div>
	</div>
	<div class="course-list">
		<h5>실시간 BEST 클래스</h5>
		<div>캐러셀</div>
	</div>
	<div class="course-list">
		<h5>신규 클래스</h5>
		<div>캐러셀</div>
	</div>
	<div class="course-list">
		<h5>만원의 행복</h5>
		<div>캐러셀</div>
	</div>
	
	<div>이벤트 캐러셀</div>
	
	<!-- 사이트 설명 -->
	<div>
		<div class="online">
			<img src="${pageContext.request.contextPath}/image_bundle/online.jpeg">
			<h5>오프라인 클래스, <br>함께 즐겨요!</h5>
			<p>전국에 있는 다양한<br>취미/여가/체험/원데이 클래스를<br>예약하고 참여하세요</p>
		</div>
	</div>
	<div>
		<div class="online">
			<h5>편하게 온라인으로도</h5>
			<img src="${pageContext.request.contextPath}/image_bundle/offline.jpeg">
		</div>
	</div>
	<div>
		<div class="online">
			<img src="${pageContext.request.contextPath}/image_bundle/items.jpeg">
			<h5>공방의 다양한 상품, 지금 구매하세요</h5>
			<p>다양한 이벤트를 통해 할인된 가격을 만나보세요!<br>공방에서 제작한 모든 상품을 온라인으로 구매할 수 있습니다</p>
		</div>
	</div>
	<div>
		<div class="online">
			<img src="${pageContext.request.contextPath}/image_bundle/space.jpeg">
			<h5>1인 스튜디오에서 대형 체육관까지,<br>상상가능한 공간을 한눈에!</h5>
			<p>내 주변에 대여가능한 공간을 찾아보세요</p>
		</div>
	</div>
	
	<!-- 이용자 수 -->
	<div>
		<p><span>N</span>명이 <br>취미상점을 이용하고 있습니다</p>
	</div>
	<ul>
		<li>
			<i class="fa-regular fa-user"></i><br>
			N<br>
			강사
		</li>
		<li>
			<i class="fa-solid fa-pencil"></i><br>
			N<br>
			클래스
		</li>
		<li>
			<i class="fa-regular fa-credit-card"></i><br>
			N<br>
			클래스 구매수
		</li>
	</ul>
	
	

</div>
<!-- 메인 끝 -->