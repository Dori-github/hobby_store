<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="${pageContext.request.contextPath}/css/items.css"
	rel="stylesheet">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<!-- 중앙 컨텐츠 시작 -->
<script src="${pageContext.request.contextPath}/js/itemsFav.js"></script>
<script src="${pageContext.request.contextPath}/js/itemsReply.js"></script>


<div class="course-info">
	<!-- 왼쪽 대표 이미지 -->
	<div class="left-img">
		<!-- 이미지 캐러셀 -->
		<div id="carouselExampleIndicators" class="carousel slide"
			data-bs-ride="carousel">
			<div class="carousel-indicators" style="display: none;">
				<button type="button" id="img1"
					data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0"
					class="active" aria-current="true" aria-label="Slide 1"></button>
				<button type="button" id="img2"
					data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"
					aria-label="Slide 2"></button>
				<button type="button" id="img3"
					data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2"
					aria-label="Slide 3"></button>
			</div>
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img src="imageView.do?items_num=${items.items_num}&items_type=1"
						class="d-block w-100">
				</div>
				<c:if test="${!empty items.items_photo_name2}">
					<div class="carousel-item">
						<img src="imageView.do?items_num=${items.items_num}&items_type=2"
							class="d-block w-100">
					</div>
				</c:if>
				<c:if test="${!empty items.items_photo_name3}">
					<div class="carousel-item">
						<img src="imageView.do?items_num=${items.items_num}&items_type=3"
							class="d-block w-100">
					</div>
				</c:if>
			</div>
			<button class="carousel-control-prev" type="button"
				data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Previous</span>
			</button>
			<button class="carousel-control-next" type="button"
				data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Next</span>
			</button>
		</div>

		<ul>
			<li><label for="img1"><img
					src="imageView.do?items_num=${items.items_num}&items_type=1"
					data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0"
					class="active" aria-current="true" aria-label="Slide 1"></label></li>
			<c:if test="${!empty items.items_photo_name2}">
				<li><label for="img2"><img
						src="imageView.do?items_num=${items.items_num}&items_type=2"></label>
				</li>
			</c:if>
			<c:if test="${!empty items.items_photo_name3}">
				<li><label for="img3"><img
						src="imageView.do?items_num=${items.items_num}&items_type=3"></label>
				</li>
			</c:if>
		</ul>
	</div>
	<!-- 오른쪽 클래스 설명 -->
	<div class="right-class">
		<p>
			${items.mem_nickname} <span class="heart"
				data-num="${items.items_num}"><i class="fa-regular fa-heart"
				style="color: red;"></i> 찜하기 </span>

		</p>
		<p></p>
		<p>
		<h3>
			<b>${items.items_name}</b>
		</h3>
		</p>
		<div>
			<c:if test="${!empty items.items_zipcode}">
				<p>
					<span class="gray"><i class="fa-solid fa-location-dot"></i>
						&nbsp;${items.items_address1} ${items.items_address2}</span>
				</p>
			</c:if>
			<p>
				<i class="fa-regular fa-star" style="color: orange;"></i> <span id="starAvg"> </span>
			</p>
		</div>
		<form id="itemsOn_cart" method="post">
			<div class="reservation">
				<c:if test="${items.items_quantity lt 5}">
					<p class="soldout">품절 임박</p>
				</c:if>
				<p>재고</p>
				<span>구매수량<input type="number" value="1" name="items_quan"></span>
				<span>가격</span> <span class="price"><fmt:formatNumber>${items.items_price}</fmt:formatNumber>원</span>
				<hr size="2" noshade width="100%" style="color: gray;">

				<input type="hidden" name="items_num" value="${items.items_num}"
					id="items_num"> <input type="hidden" name="items_price"
					value="${items.items_price}" id="items_price"> <input
					type="hidden" name="items_name" value="${items.items_name}"
					id="items_name"> <input type="hidden" name="items_quantity"
					value="${items.items_quantity}" id="items_quantity">
				<div class="reservation"></div>
				<div style="display: flex; justify-content: space-between;">
					<input type="submit" class="buy" value="장바구니"
						formaction="/cart/insert.do"><i
						class="fa-solid fa-cart-plus"></i> <input type="submit"
						class="buy" value="구매하기" formaction="/order/orderNowForm.do">
				</div>
			</div>
		</form>
	</div>
</div>

		

		

			

<div class="course-d-info">
	<ul class="title">
		<li class="active">소개</li>
		<li>후기 <span class="badge" id="reply"></span></li>
		<li>문의 <span class="badge" id="qna">4</span></li>
	</ul>
	<hr size="2" noshade width="100%" style="color: gray; margin: 0;">
	<div class="c-content">
		${items.items_content}
		<div class="map">
			위치<br> 지도표시
		</div>
	</div>
	<div class="reply">
		<h5 id ="reply_1">
			 <b>  </b> 
		</h5>
		<hr size="2" noshade width="100%" style="color: gray;">
		<ul class="reply-avg">
			<li id ="starAvg2"> <span><label for="" style="font-size :1em; color:rgba(250, 208, 0, 0.99);">★</label></span></li>
			<li><span id = "reply_2"></span><br><span id = "star_per"> 의 고객이 5점을 주었어요!</span> </li>
		</ul>


	<form class="mb-3" name="reply_form" id="reply_form" <c:if test="${empty user}">style="height:370px;"</c:if>>
	<c:if test="${!empty user}">
		<div class="reply_star">
			별점
			<fieldset>
				<span class="text-bold"> </span> <input type="radio"
					name="star_auth" value="5" id="rate1"> <label for="rate1">★</label>
				<input type="radio" name="star_auth" value="4" id="rate2"><label
					for="rate2">★</label> <input type="radio" name="star_auth"
					value="3" id="rate3"><label for="rate3">★</label> <input
					type="radio" name="star_auth" value="2" id="rate4"><label
					for="rate4">★</label> <input type="radio" name="star_auth"
					value="1" id="rate5"><label for="rate5">★</label>
			</fieldset>
		</div>
		</c:if>
			<input type="hidden" name="items_num" value="${items.items_num}" id="items_num">
		<textarea rows="3" cols="50" name="reply_content" id="reply_content" class="reply-content" 
				<c:if test="${empty user}">disabled="disabled"</c:if>
				><c:if test="${empty user}">로그인해야 작성할 수 있습니다.</c:if></textarea>
		<c:if test="${!empty user}">
		<div class="reply-photo">
			<ul class="image">
				<li>
					<img class="course-photo1">
					<label for="upload1" class="label1 l1">
						<i class="fa-solid fa-circle-plus"></i><br>
					</label>
					<i class="fa-solid fa-circle-xmark d1"></i>
					<input type="file" name="upload1" id="upload1" style="display:none;" accept="image/jpeg,image/png,image/gif">
				</li>
				<li>
					<img class="course-photo2">
					<label for="upload2" class="label1 l2">
						<i class="fa-solid fa-circle-plus"></i><br>
					</label>
					<i class="fa-solid fa-circle-xmark d2"></i>
					<input type="file" name="upload2" id="upload2" style="display:none;" accept="image/jpeg,image/png,image/gif">
				</li>
				<li>
					<img class="course-photo3">
					<label for="upload3" class="label1 l3">
						<i class="fa-solid fa-circle-plus"></i><br>
					</label>
					<i class="fa-solid fa-circle-xmark d3"></i>
					<input type="file" name="upload3" id="upload3" style="display:none;" accept="image/jpeg,image/png,image/gif">
				</li>
			</ul>
			<div class="reply-submit-btn">
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
	<!-- 후기 목록 출력 -->
	<div id="output"></div>

	<div class="paging-button" style="display: none;"></div>
	
	<div id="loading" style="display: none;">
		<img src="${pageContext.request.contextPath}/images/loading.gif"
			width="50" height="50">

	</div>



	<div class="qna">
		문의
		<hr size="2" noshade width="100%" style="color: gray;">
	</div>
</div>

<!-- 중앙 컨텐츠 끝 -->