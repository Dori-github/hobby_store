<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="${pageContext.request.contextPath}/css/items.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css">
<!-- 중앙 컨텐츠 시작 -->
<script
	src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/items.js"></script>




<div id="items_main">
	<!-- 사이드바 시작 -->
	<div id="sidebar" data-param="${param.cate}">
		<ul>
			<li><a
				class="cate <c:if test="${param.cate=='전체'}">active active-color0</c:if>">전체</a>
			</li>

			<c:forEach begin="0" end="2" var="parent">
				<li><a
					class="cate <c:if test="${param.cate==items_cate[parent].cate_name}">active active-color0</c:if>">${items_cate[parent].cate_name}</a>
					<ul class="d_cate">
						<c:forEach var="cate" items="${items_cate}">
							<c:if test="${cate.cate_parent==parent+1}">
								<li><a
									<c:if test="${param.cate==cate.cate_name}">class="active-color"</c:if>>${cate.cate_name}</a></li>
							</c:if>
						</c:forEach>
					</ul></li>
			</c:forEach>
		</ul>
	</div>

	<!-- 사이드바 끝 -->

	<!-- 오른쪽 컨텐츠 시작 -->
	<div id="content">
		<!-- 검색 시작 -->
		<div id="onoff"></div>
		<div id="items_search">
			<form
				action="/items/itemsList.do?check=${param.check}&cate=${param.cate}&packaging=${packaging}"
				method="post" class="navbar-expand search-form d-flex"
				id="search_form">
				<select class="form-select" name="keyfield">
					<option value="1" <c:if test="${param.keyfield==1}">selected</c:if>>전체</option>
					<option value="2" <c:if test="${param.keyfield==2}">selected</c:if>>제목</option>
					<option value="3" <c:if test="${param.keyfield==3}">selected</c:if>>내용</option>
					<option value="4" <c:if test="${param.keyfield==4}">selected</c:if>>작성자</option>
				</select> <input type="search" name="keyword" class="search-bar"
					id="search_bar" value="${param.keyword}" placeholder="골라봐"
					autocomplete="off">
				<button type="submit" class="search-btn" id="search_icon">
					<i class="fa-solid fa-magnifying-glass"></i>
				</button>
			</form>
		</div>
		<div id="select">
			<span id="total">총 ${count}개</span> <select
				class="form-select select" id="packaging" name="packaging">
				<option value="1"
					<c:if test="${param.packaging == 1}">selected</c:if>>포장 주문
					여부</option>
				<option value="2"
					<c:if test="${param.packaging == 2}">selected</c:if>>포장 주문
					가능</option>
				<option value="3"
					<c:if test="${param.packaging == 3}">selected</c:if>>포장 주문
					불가능</option>
			</select> <select class="form-select select" id="check" name="check">
				<option value="1" <c:if test="${param.check == 1}">selected</c:if>>최신순</option>
				<option value="2" <c:if test="${param.check == 2}">selected</c:if>>리뷰순</option>
				<option value="3" <c:if test="${param.check == 3}">selected</c:if>>좋아요순</option>
				<option value="4" <c:if test="${param.check == 4}">selected</c:if>>높은
					가격 순</option>
				<option value="5" <c:if test="${param.check == 5}">selected</c:if>>낮은
					가격 순</option>
			</select>
		</div>
		<hr size="3" noshade width="100%" style="color: gray; margin: .8em 0;">

		<!-- 클래스 목록 시작 -->
		<c:if test="${count==0}">
			<table class="table table-group-divider align-center">
				<tr>
					<td>표시할 게시물이 없습니다</td>
				</tr>
			</table>
		</c:if>
		<c:if test="${count>0}">
			<div class="row row-cols-1 row-cols-sm-2 row-cols-md-4 g-4">
				<c:forEach var="items" items="${list}">
					<div class="col">
						<div class="card h-100" style="position: relative;">

							<span id="red-heart" class="red-heart"
								data-num="${items.items_num}"> <c:if
									test="${items.fav_num != 0}">
									<i class="heart fa-regular fa-heart" style="font-weight: bold;"></i>
								</c:if> <c:if test="${items.fav_num == 0}">
									<i class="heart fa-regular fa-heart"></i>
								</c:if>

							</span> <a href="itemsDetail.do?items_num=${items.items_num}"
								style="display: block;">

								<div class="card-img-top">
									<img
										src="/items/imageView.do?items_num=${items.items_num}&items_type=1"
										width="100%" height="100%">
								</div>
								<div class="card-body">

									<div class="color-gray">
										<span>${items.mem_nickname}</span> <span class="card-hit"><i
											class="fa-solid fa-eye"></i> ${items.items_hit} <i
											class="fa-solid fa-heart"></i> <span class="countFav">${items.favcount}</span></span>
									</div>
									<h5 class="card-title">
										<b>${items.items_name}</b>
									</h5>
									<span><i class="fa-regular fa-star"></i>
										${items.starcount} (후기 ${items.replycount})</span>
									<p class="card-text">
										<span> </span> <br>
										<b><fmt:formatNumber>${items.items_price}</fmt:formatNumber>원</b><span
											style="color: gray;"></span>
									</p>
								</div>
							</a>
						</div>
					</div>
				</c:forEach>
			</div>
			<!-- 클래스 목록 끝 -->

			<!-- 페이지처리 시작 -->
			<div class="align-center list-page">${page}</div>
			<!-- 페이지처리 끝 -->
		</c:if>
	</div>
	<!-- 오른쪽 컨텐츠 끝 -->
</div>
<!-- 중앙 컨텐츠 끝 -->