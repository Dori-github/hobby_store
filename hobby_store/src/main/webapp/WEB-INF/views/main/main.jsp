<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/main.js"></script>
<!-- 메인 시작 -->
	<div class="course-img clear-flex">
		<div class="img1">
			<img src="${pageContext.request.contextPath}/image_bundle/공예.jpeg">
			<a href="/course/courseList.do?cate=공예"><span style="left:150px;">공예 <i class="fa-solid fa-chevron-right"></i></span></a>
		</div>
		<div class="img2">
			<img src="${pageContext.request.contextPath}/image_bundle/쿠킹.jpeg">
			<a href="/course/courseList.do?cate=쿠킹"><span style="left:550px;">쿠킹 <i class="fa-solid fa-chevron-right"></i></span></a>
		</div>
		<div class="img3">
			<img src="${pageContext.request.contextPath}/image_bundle/드로잉.jpeg">
			<a href="/course/courseList.do?cate=드로잉"><span style="left:950px;">드로잉 <i class="fa-solid fa-chevron-right"></i></span></a>
		</div>
		<div class="img4">
			<img src="${pageContext.request.contextPath}/image_bundle/운동.jpeg">
			<a href="/course/courseList.do?cate=운동"><span style="left:1350px;">운동 <i class="fa-solid fa-chevron-right"></i></span></a>
		</div>
		<div class="img5">
			<img src="${pageContext.request.contextPath}/image_bundle/뷰티.jpeg">
			<a href="/course/courseList.do?cate=뷰티"><span style="left:1750px;">뷰티 <i class="fa-solid fa-chevron-right"></i></span></a>
		</div>
	</div>
	
	<div style="width:80%;margin:0 auto;">
		<div class="course-search">
			<div class="search-bar1">
				<input type="search" name="keyword" id="search_bar" value="${param.keyword}" placeholder="주변에 다양한 클래스를 찾아보세요!" autocomplete="off">
				<button type="submit" class="search-btn" id="search_icon1"><i class="fa-solid fa-magnifying-glass"></i></button>
			</div>
			<div class="cate">
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
		</div>
		<!-- 클래스 목록 가져오기 -->
		<div class="course-list">
			<h1>실시간 <span style="color:#FF4E02;font-weight:bold;font-size:40pt;">BEST</span> 클래스</h1>
			<div class="row row-cols-1 row-cols-sm-2 row-cols-md-4 g-4">
				<c:forEach var="course" items="${list2}">
				<div class="col">
					<div class="card h-100" style="position:relative;">
					
						<c:forEach var="fav" items="${favCheck}">
					 	<span class="red-heart" data-num="${course.course_num}"><i class="fa-regular fa-heart" <c:if test="${fav.fmem_num==user.mem_num&&fav.course_num==course.course_num}">
					 		style="font-weight:bold;"</c:if>></i></span>
					 	</c:forEach>
					 	
						<a href="courseDetail.do?course_num=${course.course_num}" style="display:block;">
							<div class="card-img-top">
							  <img src="/course/imageView.do?course_num=${course.course_num}&item_type=1" width="100%" height="100%">
							</div>
							<div class="card-body">
							  	<div class="color-gray">
							  		<span>${course.mem_nickname}</span>
							  		<span class="card-hit"><i class="fa-solid fa-eye"></i> ${course.course_hit} <i class="fa-solid fa-heart"></i> <span class="countFav">${course.fav}</span></span>
							  	</div>
							  	<h5 class="card-title"><b>${course.course_name}</b></h5>
							  	<span><i class="fa-regular fa-star"></i> ${course.staravg} (후기 ${course.replycount})</span>
							  	<p class="card-text">
							  		<span>${course.course_address1}</span>
							  		<br><b><fmt:formatNumber>${course.course_price}</fmt:formatNumber>원</b><span style="color:gray;"> / 1인</span>
							  	</p>
							</div>
						</a>
					</div>
				</div>
				</c:forEach>
			</div>
		</div>
		<div class="course-list">
			<h1>신규 클래스</h1>
			<div class="row row-cols-1 row-cols-sm-2 row-cols-md-4 g-4">
				<c:forEach var="course" items="${list}">
				<div class="col">
					<div class="card h-100" style="position:relative;">
					
						<c:forEach var="fav" items="${favCheck}">
					 	<span class="red-heart" data-num="${course.course_num}"><i class="fa-regular fa-heart" <c:if test="${fav.fmem_num==user.mem_num&&fav.course_num==course.course_num}">
					 		style="font-weight:bold;"</c:if>></i></span>
					 	</c:forEach>
					 	
						<a href="courseDetail.do?course_num=${course.course_num}" style="display:block;">
							<div class="card-img-top">
							  <img src="/course/imageView.do?course_num=${course.course_num}&item_type=1" width="100%" height="100%">
							</div>
							<div class="card-body">
							  	<div class="color-gray">
							  		<span>${course.mem_nickname}</span>
							  		<span class="card-hit"><i class="fa-solid fa-eye"></i> ${course.course_hit} <i class="fa-solid fa-heart"></i> <span class="countFav">${course.fav}</span></span>
							  	</div>
							  	<h5 class="card-title"><b>${course.course_name}</b></h5>
							  	<span><i class="fa-regular fa-star"></i> ${course.staravg} (후기 ${course.replycount})</span>
							  	<p class="card-text">
							  		<span>${course.course_address1}</span>
							  		<br><b><fmt:formatNumber>${course.course_price}</fmt:formatNumber>원</b><span style="color:gray;"> / 1인</span>
							  	</p>
							</div>
						</a>
					</div>
				</div>
				</c:forEach>
			</div>
		</div>
		<div class="course-list">
			<h1>만원의 행복</h1>
			<div class="row row-cols-1 row-cols-sm-2 row-cols-md-4 g-4">
				<c:forEach var="course" items="${list3}">
				<div class="col">
					<div class="card h-100" style="position:relative;">
					
						<c:forEach var="fav" items="${favCheck}">
					 	<span class="red-heart" data-num="${course.course_num}"><i class="fa-regular fa-heart" <c:if test="${fav.fmem_num==user.mem_num&&fav.course_num==course.course_num}">
					 		style="font-weight:bold;"</c:if>></i></span>
					 	</c:forEach>
					 	
						<a href="courseDetail.do?course_num=${course.course_num}" style="display:block;">
							<div class="card-img-top">
							  <img src="/course/imageView.do?course_num=${course.course_num}&item_type=1" width="100%" height="100%">
							</div>
							<div class="card-body">
							  	<div class="color-gray">
							  		<span>${course.mem_nickname}</span>
							  		<span class="card-hit"><i class="fa-solid fa-eye"></i> ${course.course_hit} <i class="fa-solid fa-heart"></i> <span class="countFav">${course.fav}</span></span>
							  	</div>
							  	<h5 class="card-title"><b>${course.course_name}</b></h5>
							  	<span><i class="fa-regular fa-star"></i> ${course.staravg} (후기 ${course.replycount})</span>
							  	<p class="card-text">
							  		<span>${course.course_address1}</span>
							  		<br><b><fmt:formatNumber>${course.course_price}</fmt:formatNumber>원</b><span style="color:gray;"> / 1인</span>
							  	</p>
							</div>
						</a>
					</div>
				</div>
				</c:forEach>
			</div>
		</div>
	</div>
	
	<div style="width:100%;">
		<div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="true">
		  <div class="carousel-indicators">
		    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
		    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
		    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
		  </div>
		  <div class="carousel-inner">
		    <div class="carousel-item active">
		      <img src="${pageContext.request.contextPath}/image_bundle/캐러셀1.JPG" class="d-block w-100">
		    </div>
		    <div class="carousel-item">
		      <img src="${pageContext.request.contextPath}/image_bundle/쿠폰.jpg" class="d-block w-100">
		    </div>
		    <div class="carousel-item">
		      <img src="${pageContext.request.contextPath}/image_bundle/포인트.jpg" class="d-block w-100">
		    </div>
		  </div>
		  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
		    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
		    <span class="visually-hidden">Previous</span>
		  </button>
		  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
		    <span class="carousel-control-next-icon" aria-hidden="true"></span>
		    <span class="visually-hidden">Next</span>
		  </button>
		</div>
	</div>
	
	<!-- 사이트 설명 -->
	<div style="width:80%;margin:0 auto;">
		<div>
			<div class="online">
				<img src="${pageContext.request.contextPath}/image_bundle/offline.jpeg" style="margin:0 100px 0 200px;width:600px;">
				<div style="margin:70px;margin-bottom:auto;">
					<h1><span style="color:#FF4E02;font-weight:bold;">오프라인 클래스,</span> <br>함께 즐겨요!</h1>
					<p>전국에 있는 다양한<br>취미/여가/체험/원데이 클래스를<br>예약하고 참여하세요!!!</p>
				</div>
			</div>
		</div>
		<div>
			<div class="online">
				<h2 style="margin-top:auto;margin-bottom:30px;margin-left:auto;margin-right:50px;">편하게 <span style="color:#FF4E02;font-weight:bold;">온라인</span>으로도</h2>
				<img src="${pageContext.request.contextPath}/image_bundle/online.jpeg" style="margin-right:250px;margin-top:-200px;width:400px;height:600px;">
			</div>
		</div>
		<div>
			<div class="online1">
				<img src="${pageContext.request.contextPath}/image_bundle/items.jpeg">
				<h1 style="margin:30px 0 10px 0;">공방의 다양한 <span style="color:#FF4E02;">상품</span>, 지금 구매하세요</h1><br>
				<p>다양한 이벤트를 통해 할인된 가격을 만나보세요!<br>공방에서 제작한 모든 상품을 온라인으로 구매할 수 있습니다</p>
			</div>
		</div>
		<div>
			<div class="online" style="clear:both;">
				<img src="${pageContext.request.contextPath}/image_bundle/space.jpeg" style="margin-left:500px;">
				<div style="margin-top:auto;margin-left:30px;">
					<h3><span style="color:#FF4E02;line-height:2em;font-weight:bold;">1인 스튜디오에서 대형 체육관까지,</span><br>상상가능한 공간을 한눈에!</h3>
					<p>내 주변에 대여가능한 공간을 찾아보세요</p>
				</div>
			</div>
		</div>
		<!--
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
	  -->

</div>
<!-- 메인 끝 -->