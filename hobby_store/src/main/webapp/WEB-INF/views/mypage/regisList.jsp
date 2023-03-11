<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 등록 상품 조회 시작 -->
<div id="content">
	<h2>등록 상품 조회</h2>
	<div class="btn-group top-btn">
			<input type="radio" class="btn-check" name="btnradio" id="btnradio1"
			onclick="location.href='regisList.do?cate_num=1'" <c:if test="${cate_num==1}">checked</c:if>>
			<label class="btn btn-outline-primary" for="btnradio1">클래스</label>
			
			<input type="radio" class="btn-check" name="btnradio" id="btnradio2"
			onclick="location.href='regisList.do?cate_num=2'" <c:if test="${cate_num==2}">checked</c:if>>
			<label class="btn btn-outline-primary" for="btnradio2">상품</label>
			
			<input type="radio" class="btn-check" name="btnradio" id="btnradio3"
			onclick="location.href='regisList.do?cate_num=3'" <c:if test="${cate_num==3}">checked</c:if>>
			<label class="btn btn-outline-primary" for="btnradio3">공간대여</label>
		</div>
	<c:if test="${empty list}">
	<table class="table align-center">
		<tr>
			<td>
				등록된 상품이 없습니다.
			</td>
		</tr>
	</table>
	</c:if>
	
	<c:if test="${!empty list}">
	<table class="table table-group-divider align-center">
		<tr>
			<td>강의번호</td>
			<td>강의명</td>
			<td>등록일</td>
		</tr>
	<c:if test="${cate_num==1 && !empty list}">
	<c:forEach var="course" items="${list}">
		<tr>
			<td>${course.course_num}</td>
			<td>
			<img src="${pageContext.request.contextPath}/course/imageView.do?course_num=${course.course_num}&item_type=1" width="50" height="50">
			${course.course_name}
			<input type="button" value="수정" onclick="location.href='${pageContext.request.contextPath}/course/update.do?course_num=${course.course_num}'">
			<input type="button" value="삭제" onclick="location.href='${pageContext.request.contextPath}/course/delete.do?course_num=${course.course_num}'">
			</td>
			<td>${course.course_date}</td>
		</tr>
	</c:forEach>
	</c:if>
	<c:if test="${cate_num==2 && !empty list}">
	<c:forEach var="items" items="${list}">
		<tr>
			<td>${items.items_num}</td>
			<td>
			  <img src="${pageContext.request.contextPath}/items/imageView.do?items_num=${items.items_num}&items_type=1" width="40" height="40">
			${items.items_name}
			<input type="button" value="수정" onclick="location.href='${pageContext.request.contextPath}/items/modify.do?items_num=${items.items_num}'">
			<input type="button" value="삭제" onclick="location.href='${pageContext.request.contextPath}/items/delete.do?items_num=${items.items_num}'">
			</td>
			<td>${items.reg_date}</td>
		</tr>
	</c:forEach>
	</c:if>
	<c:if test="${cate_num==3 && !empty list}">
	<c:forEach var="space" items="${list}">
		<tr>
			<td>${space.space_num}</td>
			<td>
			<img src="/space/imageView.do?space_num=${space.space_num}&space_type=1" width="40" height="40">
			${space.space_name}
			<input type="button" value="수정" onclick="location.href='${pageContext.request.contextPath}/space/admin_modify.do?space_num=${space.space_num}'">
			<input type="button" value="삭제" onclick="location.href='${pageContext.request.contextPath}/space/delete.do?space_num=${space.space_num}'">
			</td>
			<td>${space.space_date}</td>
		</tr>
	</c:forEach>
	</c:if>
	</table>
	<div class="align-center">
		${page}
	</div>
	<div class="align-right">
	</div>
	</c:if>
	
</div>
<!-- 등록 상품 조회 끝 -->