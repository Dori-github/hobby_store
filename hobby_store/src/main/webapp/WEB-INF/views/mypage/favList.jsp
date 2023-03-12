<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- 좋아요 목록 시작 -->
<div id="content">
<div id="fav_size">
	<h2 style="margin-bottom:50px;" class="align-center">좋아요 조회</h2>
	<div class="btn-group top-btn">
			<input type="radio" class="btn-check" name="btnradio" id="btnradio1"
			onclick="location.href='fav.do?cate_num=1'" <c:if test="${cate_num==1}">checked</c:if>>
			<label class="btn" for="btnradio1">클래스</label>
			
			<input type="radio" class="btn-check" name="btnradio" id="btnradio2"
			onclick="location.href='fav.do?cate_num=2'" <c:if test="${cate_num==2}">checked</c:if>>
			<label class="btn" for="btnradio2">상품</label>
			
			<input type="radio" class="btn-check" name="btnradio" id="btnradio3"
			onclick="location.href='fav.do?cate_num=3'" <c:if test="${cate_num==3}">checked</c:if>>
			<label class="btn" for="btnradio3">공간대여</label>
		</div>
	<c:if test="${empty list}">
		<table class="table table-group-divider align-center">
			<tr>
				<td>표시할 상품이 없습니다</td>
			</tr>
		</table>
	</c:if>
	<c:if test="${!empty list}">
		<div class="row row-cols-1 row-cols-sm-2 row-cols-md-4 g-4">
		<c:if test="${cate_num==1}">
			<c:forEach var="course" items="${list}">
			<div class="col">
				<div class="card h-100" style="position:relative;">
				
					<c:forEach var="fav" items="${favCheck}">
				 	<span class="red-heart" data-num="${course.course_num}"><i class="fa-regular fa-heart" <c:if test="${course.mem_num==user.mem_num&&list.course_num==course.course_num}">
				 		style="font-weight:bold;"</c:if>></i></span>
				 	</c:forEach>
				 	
					<a href="${pageContext.request.contextPath}/course/courseDetail.do?course_num=${course.course_num}" style="display:block;">
						<div class="card-img-top">
						  <img src="${pageContext.request.contextPath}/course/imageView.do?course_num=${course.course_num}&item_type=1" width="100%" height="100%">
						</div>
						<div class="card-body">
						  	<h5 class="card-title"><b>${course.course_name}</b></h5>
						  	<p class="card-text">
						  		<span>${course.course_address1}</span>
						  		<br><b><fmt:formatNumber>${course.course_price}</fmt:formatNumber>원</b><span style="color:gray;"> / 1인</span>
						  	</p>
						</div>
					</a>
				</div>
			</div>
		</c:forEach>
		</c:if>
		<c:if test="${cate_num==2 && !empty list}">
			<c:forEach var="items" items="${list}">
			<div class="col">
				<div class="card h-100" style="position:relative;">
				
					<c:forEach var="fav" items="${favCheck}">
				 	<span class="red-heart" data-num="${items.items_num}"><i class="fa-regular fa-heart" <c:if test="${items.mem_num==user.mem_num&&list.items_num==items.items_num}">
				 		style="font-weight:bold;"</c:if>></i></span>
				 	</c:forEach>
				 	
					<a href="${pageContext.request.contextPath}/items/itemsDetail.do?items_num=${items.items_num}" style="display:block;">
						<div class="card-img-top">
						  <img src="${pageContext.request.contextPath}/items/imageView.do?items_num=${items.items_num}&items_type=1" width="100%" height="100%">
						</div>
						<div class="card-body">
						  	<h5 class="card-title"><b>${items.items_name}</b></h5>
						  	<p class="card-text">
						  		<span>${items.items_address1}</span>
						  		<br><b><fmt:formatNumber>${items.items_price}</fmt:formatNumber>원</b><span style="color:gray;"> / 1인</span>
						  	</p>
						</div>
					</a>
				</div>
			</div>
		</c:forEach>
		</c:if>
		<c:if test="${cate_num==3 && !empty list}">
			<c:forEach var="space" items="${list}">
			<div class="col">
				<div class="card h-100" style="position:relative;">
				
					<c:forEach var="fav" items="${favCheck}">
				 	<span class="red-heart" data-num="${space.space_num}"><i class="fa-regular fa-heart" <c:if test="${space.mem_num==user.mem_num&&list.space_num==items.space_num}">
				 		style="font-weight:bold;"</c:if>></i></span>
				 	</c:forEach>
				 	
					<a href="${pageContext.request.contextPath}/space/spaceDetail.do?space_num=${space.space_num}" style="display:block;">
						<div class="card-img-top">
						  <img src="/space/imageView.do?space_num=${space.space_num}&space_type=1" width="100%" height="100%">
						</div>
						<div class="card-body">
						  	<h5 class="card-title"><b>${space.space_name}</b></h5>
						  	<p class="card-text">
						  		<span>${space.space_address1}</span>
						  		<br><b><fmt:formatNumber>${space.space_price}</fmt:formatNumber>원</b><span style="color:gray;"> / 1인</span>
						  	</p>
						</div>
					</a>
				</div>
			</div>
		</c:forEach>
		</c:if>
		<!-- 페이지처리 시작 -->
		<div class="align-center">${page}</div>
		<!-- 페이지처리 끝 -->
		</c:if>
</div>
</div>
<!-- 좋아요 목록 끝 -->