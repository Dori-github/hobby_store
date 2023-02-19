<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cart.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
	<br>
	<h4>Course Cart [ ${courseCount} items ]</h4>
	<c:if test="${courseCount == 0}">
		<div>장바구니에 담은 클래스가 없습니다</div>
	</c:if>
	<c:if test="${courseCount > 0}">
		<table class="table table-borderless">
		<colgroup>
			<col style="width: 10rem">
			<col style="width: 45rem">
			<col style="width: 15rem">
		</colgroup>
		<tr>
			<th colspan="2" style="text-align:left;border-bottom: 1px solid #ccc;">Course</th>
			<th style="border-bottom: 1px solid #ccc;">Price</th>
		</tr>
		<c:forEach var="cart" items="${courseList}">
		<tr style="border-bottom: 1px solid #ccc">
			<td style="text-align:center;">
			<img src="${pageContext.request.contextPath}/images/${cart.course_photo1}"></td>
			<td style="text-align:left;">${cart.course_name}
			<br>
				<c:if test="${cart.cate_parent!=0}">
				${cart.cate_parent}/</c:if>
				${cart.cate_name}
			<br>
				${cart.course_onoff}
			</td>
			<td>${cart.course_price}</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="2"></td>
			<td>${courseTotal}</td>
		</tr>
		<tr>
			<td colspan="3" style="text-align:right;">
			<input type="button" value="구매하기">
			</td>
		</tr>
		</table>
	</c:if>
	
	<h4>Item Cart List [ ${itemCount} items ]</h4>
	<c:if test="${itemCount == 0}">
		<div>장바구니에 담은 상품이 없습니다</div>
	</c:if>
	<c:if test="${itemCount > 0}">
		<table class="table table-borderless">
		<colgroup>
			<col style="width: 10rem">
			<col style="width: 25rem">
			<col style="width: 10rem">
			<col style="width: 10rem">
			<col style="width: 15rem">
		</colgroup>
		<tr>
			<th colspan="2" style="text-align:left;border-bottom: 1px solid #ccc;">Item</th>
			<th style="border-bottom: 1px solid #ccc;">Price</th>
			<th style="border-bottom: 1px solid #ccc;">Quantity</th>
			<th style="border-bottom: 1px solid #ccc;">TotalPrice</th>
		</tr>
		<c:forEach var="cart" items="${itemList}">
		<tr style="border-bottom: 1px solid #ccc;">
			<td style="text-align:center;">
			<img src="${pageContext.request.contextPath}/images/${cart.items_photo1}"></td>
			<td style="text-align:left;">${cart.items_name}
			<br>
				<c:if test="${cart.cate_parent!=0}">
				${cart.cate_parent}/</c:if>
				${cart.cate_name}
			</td>
			<td>${cart.items_price}</td>
			<td>${cart.quantity}</td>
			<td>${cart.items_total}</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="4"></td>
			<td>${itemTotal}</td>
		</tr>
		<tr>
			<td colspan="5" style="text-align:right;">
			<input type="button" value="구매하기">
			</td>
		</tr>
		</table>
	</c:if>