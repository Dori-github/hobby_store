<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<link  rel="stylesheet" href="${pageContext.request.contextPath}/css/space.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css">
<!-- 상품목록 시작 -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.js"></script>

<script type="text/javascript">
	$(function(){
		$('#search_form').on('submit',function(){
			if($('#keyword').val().trim()==''){
				alert('검색어를 입력하세요');
				$('#keyword').val('').focus();
				return false;
			}
		});
	});
</script>
<div id="space_main"><!-- main이었음 -->
	<!-- 사이드바 시작 -->
<div id="sidebar">
<ul>
  

		<li><a class="cate" href="${pageContext.request.contextPath}/space/list.do"> 전체 </a></li>
		 <li><a class="cate" href="${pageContext.request.contextPath}/space/list.do?cate=1">촬영 스튜디오</a>
		 <li><a class="cate" href="${pageContext.request.contextPath}/space/list.do?cate=2" ><li>댄스공간</a></li>
		 <li><a class="cate" href="${pageContext.request.contextPath}/space/list.do?cate=3" ><li>체육시설</a></li>
		 <li><a class="cate" href="${pageContext.request.contextPath}/space/list.do?cate=4" ><li>악기 연습실</a></li>
		 <li><a class="cate" href="${pageContext.request.contextPath}/space/list.do?cate=5" ><li>공연장</a></li>
		 <li><a class="cate" href="${pageContext.request.contextPath}/space/list.do?cate=6" ><li>그외</a></li>
	


</ul>
</div>
	<!-- 사이즈바 끝 -->


<div class="content">

<!-- 검색폼 시작 -->
<div id="space_search">
			<form action="/space/list.do" method="get" class="navbar-expand search-form d-flex" id="search_form">
				<select class="form-select" name="keyfield">
					<option value="1" <c:if test="${param.keyfield==1}">selected</c:if>>전체</option>
					<option value="2" <c:if test="${param.keyfield==2}">selected</c:if>>제목</option>
					<option value="3" <c:if test="${param.keyfield==3}">selected</c:if>>작성자</option>
				</select> 
				<input type="search" name="keyword" class="search-bar" id="search_bar" value="${param.keyword}" placeholder="원하는 대여 공간을 찾아보세요!">
				<button type="submit" class="search-btn" id="search_icon"><i class="fa-solid fa-magnifying-glass"></i></button>
	
			</form>
</div><br>
		<div id="select" ><!-- class원래 없었음 -->
			<span id="total">총 ${count}개</span>
			<!-- form-select select -->
			<select class="form-select select" id="location" name="location" style="width:100px;">
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
			<!-- class="form-select select"밑에 원래 -->
			<select class="form-select select" id="order" name="order" style="width:100px;">
				<option value="1" <c:if test="${param.order == 1}">selected</c:if>>최신순</option>
				<option value="2" <c:if test="${param.order == 2}">selected</c:if>>리뷰순</option>
				<option value="3" <c:if test="${param.order == 3}">selected</c:if>>좋아요순</option>
				<option value="4" <c:if test="${param.order == 4}">selected</c:if>>가격낮은순</option>
			</select>
		</div>
		<hr size="3" noshade width="100%" style="color:gray;margin:.8em 0;">
<!-- 검색폼 끝 -->
    <c:if test="${count == 0}">
    <table class="table table-group-divider align-center">
	<tr>
				<td>표시할 게시물이 없습니다</td>
			</tr>
		</table>
	</c:if>

	<c:if test="${count > 0}">
	<div class="row row-cols-1 row-cols-sm-2 row-cols-md-4 g-4">
			<c:forEach var="space" items="${list}">
			<div class="col">
			<a href="spaceDetail.do?space_num=${space.space_num}" style="display:block;">
					<div class="card h-100" style="position:relative;">
						<div class="card-img-top">
						  <img src="imageView.do?space_num=${space.space_num}&space_type=1" width="100%" height="100%">
						</div>
						<div class="card-body">
						 	<span id="red-heart"><i class="fa-regular fa-heart"></i></span>
						  	<div class="color-gray">
						  		<span>${space.mem_nickname}</span>
						  		<span class="card-hit"><i class="fa-solid fa-eye"></i> ${space.space_hit} <i class="fa-solid fa-heart"></i> ${space.fav}</span>
						  	</div>
						  	<h5 class="card-title"><b>${space.space_name}</b></h5>
						  	<span><i class="fa-regular fa-star"></i> ${space.star} (후기 ${space.reply})</span>
						  	<p class="card-text">
						  		<span>${space.space_address1}</span>
						  		<br><b><fmt:formatNumber>${space.space_price}</fmt:formatNumber>원</b><span style="color:gray;"> / 1인</span>
						  	</p>
						</div>
					</div>
			</a>
			</div>
       </c:forEach>
      </div>

 



		<!-- ---------------- -->
	<div class="align-center float-clear">${page}</div>
	</c:if>
</div>
</div>

	<!-- 공간 목록 (관리자용) 끝 -->








