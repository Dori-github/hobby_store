<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="${pageContext.request.contextPath}/css/space.css" rel="stylesheet">
<!-- 중앙 컨텐츠 시작 -->
<script src="${pageContext.request.contextPath}/js/space.js"></script>
<script src="${pageContext.request.contextPath}/js/space.fav.js"></script>
<script src="${pageContext.request.contextPath}/js/space.reply.js"></script>
<div id="space_detail" class="space-info">
	<!-- 왼쪽 대표 이미지 -->
	<div class="left-img">
		<!-- 이미지 캐러셀  왜 넘어가질 못하니? 클릭 안됨-->
		<div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
		  <div class="carousel-indicators" style="display: none;">
		    <button type="button" id="img1" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
		    <button type="button" id="img2" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
		    <button type="button" id="img3" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
		  </div>
		  <div class="carousel-inner">
	     	<div class="carousel-item active">
		      <img src="/space/imageView.do?space_num=${space.space_num}&space_type=1" class="d-block w-100">
		    </div>
	        <c:if test="${!empty course.course_photo_name1}">
		    <div class="carousel-item">
		      <img src="/space/imageView.do?space_num=${space.space_num}&space_type=2" class="d-block w-100">
		    </div>
		    </c:if>
		    <c:if test="${!empty course.course_photo_name2}">
		    <div class="carousel-item">
		      <img src="/space/imageView.do?space_num=${space.space_num}&space_type=3" class="d-block w-100">
		    </div>
		    </c:if>

		    
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
		
		<ul>
			<li>
				<label for="img1"><img src="/space/imageView.do?space_num=${space.space_num}&space_type=1" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></label>
			</li>
			<c:if test="${!empty space.space_photo_name1}">
			<li>
				<label for="img2"><img src="/space/imageView.do?space_num=${space.space_num}&space_type=2"></label>
			</li>
			</c:if>
			<c:if test="${!empty space.space_photo_name2}">
			<li>
				<label for="img3"><img src="/space/imageView.do?space_num=${space.space_num}&space_type=3"></label>
			</li>
			</c:if>

		</ul>
	</div>
	<!-- 오른쪽 클래스 설명 -->
	<div class="right-class">
		<p>
			${space.mem_nickname} 
			<span class="heart" id="red_heart_detail" data-num="${space.space_num}"><i class="fa-regular fa-heart" style="color:red;"></i> 찜하기</span>
		</p>

		<p>
			<h3><b>${space.space_name}</b></h3>
		</p>
		
		<div>
			<c:if test="${!empty space.space_zipcode}">
			<p>
				<span class="gray"><i class="fa-solid fa-location-dot"></i> &nbsp;${space.space_address1} ${space.space_address2}</span>
			</p>
			</c:if>
			<p>
				<i class="fa-regular fa-star" style="color:orange;"></i> 4.5 <span class="gray">(후기 N)</span>	
			</p>
		</div>
		<%-- 결제정보 전송 폼(공간 번호,가격,요일,시간) --%>
		<form id="space_cart" action="/order/list.do" method="post">
			<input type="hidden" name="space_num" value="${space.space_num}" id="space_num">
			<input type="hidden" name="space_price" value="${space.space_price}" id="space_price">
			<div class="reservation">
				<%-- 공간대여 예약 --%>
				<p>날짜선택<input type="date" name></p>
				<p>시간선택 &nbsp;<input type="radio"></p>
			
				<p>공간수 &nbsp; / ${space.space_np} &nbsp &nbsp 최대인원수 &nbsp; / ${space.space_limit}</p>
				<span>구매수량 <input></span> 
				<span class="price">가격 : <fmt:formatNumber>${space.space_price}</fmt:formatNumber>원</span>
				<hr size="2" noshade width="100%" style="color:gray;">
				<button type="submit" class="buy" style="width:100%;">공간 예약하기</button>
			</div>
		</form>

	</div>
</div>

<div class="space-d-info">
	<ul class="title">
		<li class="active">소개</li>
		<li>후기 <span class="badge"> 총 ${count}개</span></li>
		<li>문의 <span class="badge">4</span></li>
	</ul>
	<hr size="2" noshade width="100%" style="color:gray;margin:0;">
	<!-- 소개 시작 -->
	<div class="c-content">
		${space.space_content}
		<div class="map">
		위치<br>
		지도표시
		</div>
	</div>
	<!-- 소개 끝 -->
	
	<!-- 후기 시작 -->
	<div class="reply">
		<h5>후기 <b>N</b> 개</h5>
		<hr size="2" noshade width="100%" style="color:gray;">
		<ul class="reply-avg">
			<li><i class="fa-regular fa-star" style="color:orange;"></i> 4.5 </li>
			<li>N 건의 후기 중<br>N % 의 고객이 5점을 주었어요!</li>
		</ul>
	</div>
	<hr size="2" noshade width="100%" style="color:gray;">
	
	<form id="reply_form">
		<c:if test="${!empty user}">
		<div class="star-choice">별점 
		<c:forEach begin="1" end="5"><i class="fa-regular fa-star"></i></c:forEach>
		<span class="letter-count">300 / 300</span>
		</div>
		</c:if>
		<input type="hidden" name="space_num" value="${space.space_num}" id="space_num">
		<textarea rows="3" cols="50" name="reply_content" id="reply_content" class="reply-content" 
				<c:if test="${empty user}">disabled="disabled"</c:if>
				><c:if test="${empty user}">로그인해야 작성할 수 있습니다.</c:if></textarea>
		<c:if test="${!empty user}">
		<div class="reply-photo">
			<ul class="image">
				<li>
					<img class="space-photo1">
					<label for="upload1" class="label1 l1">
						<i class="fa-solid fa-circle-plus"></i><br>
					</label>
					<i class="fa-solid fa-circle-xmark d1"></i>
					<input type="file" name="upload1" id="upload1" style="display:none;" accept="image/jpeg,image/png,image/gif">
				</li>
				<li>
					<img class="space-photo2">
					<label for="upload2" class="label1 l2">
						<i class="fa-solid fa-circle-plus"></i><br>
					</label>
					<i class="fa-solid fa-circle-xmark d2"></i>
					<input type="file" name="upload2" id="upload2" style="display:none;" accept="image/jpeg,image/png,image/gif">
				</li>
				<li>
					<img class="space-photo3">
					<label for="upload3" class="label1 l3">
						<i class="fa-solid fa-circle-plus"></i><br>
					</label>
					<i class="fa-solid fa-circle-xmark d3"></i>
					<input type="file" name="upload3" id="upload3" style="display:none;" accept="image/jpeg,image/png,image/gif">
				</li>
			</ul>
			<div class="submit-btn">
				<label for="submit" style="width:40px;height:40px;"><i class="fa-solid fa-paper-plane"></i></label>
				<input type="submit" id="submit" style="display:none;">
			</div>
		</div>
		</c:if>
	</form>
	
	<%-- 정렬 --%>
	<div class="reply-search">
		<div class="btn-select"><span class="whole">최신순</span>
			<i class="fa-solid fa-chevron-down icon" style="float: right;padding-bottom:5px;font-size:15px;"></i>
			<i class="fa-solid fa-chevron-up icon" style="float: right;font-size:15px;display:none;"></i>
			<%-- 최신순 정렬 --%>
			<div class="list-box">
		        <ul class="list-cate">
		            <li data-value="1">최신순</li>
		            <li data-value="2">별점순</li>
		            <li data-value="3">추천순</li>
		        </ul>
	        </div>
	    </div>
		<hr size="2" noshade style="color:gray;">
	</div>
	
	<!-- 후기 목록 출력 시작 -->
	<div id="output"></div>
	<!-- 페이징 처리 시작 -->
	<ul class="paging-btn"></ul>
	<!-- 페이징 처리 끝 -->
	<div id="loading" style="display:none;">
		<img src="${pageContext.request.contextPath}/images/loading2.gif" width="40" height="40">
	</div>
	<!-- 후기 목록 출력 끝 -->
	<!-- 후기 끝 -->
	
	<!-- 문의 시작 -->
	<div class="qna">
		<h5>문의</h5>
		<hr size="2" noshade width="100%" style="color:gray;">
	</div>
	<!-- 문의 끝 -->
</div>

<!-- 중앙 컨텐츠 끝 -->