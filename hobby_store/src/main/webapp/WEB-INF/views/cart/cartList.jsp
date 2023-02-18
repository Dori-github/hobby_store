<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>장바구니 목록</h3>
	<h4>Course Cart List</h4>
	<c:if test="${courseCount == 0}">
		<div>장바구니에 담은 클래스가 없습니다</div>
	</c:if>
	<c:if test="${courseCount > 0}">
		<table style="text-align:center;">
		<colgroup>
			<col style="width: 80px">
			<col style="width: 150px">
			<col style="width: 80px">
		</colgroup>
		<tr>
			<th colspan="2" style="border-bottom: 2px solid black;">Course</th>
			<th style="border-bottom: 2px solid black;">Price</th>
		</tr>
		<c:forEach var="cart" items="${courseList}">
		<tr>
			<td rowspan="3">${cart.course_photo1}</td>
			<td>${cart.course_name}</td>
			<td rowspan="3">${cart.course_price}</td>
		</tr>
		<tr>
			<td><c:if test="${cart.cate_parent!=0}">
			${cart.cate_parent}/</c:if>
			${cart.cate_name}</td>
		</tr>
		<tr style="border-bottom: 1px solid #ccc;">
			<td>${cart.course_onoff}</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="2">${courseCount}개 품목</td>
			<td>${courseTotal}</td>
		</tr>
		<tr>
			<td colspan="3" style="text-align:right;">
			<input type="button" value="구매하기">
			</td>
		</tr>
		</table>
	</c:if>
	
	<h4>Item Cart List</h4>
	<c:if test="${itemCount == 0}">
		<div>장바구니에 담은 상품이 없습니다</div>
	</c:if>
	<c:if test="${itemCount > 0}">
		<table style="text-align:center;">
		<colgroup>
			<col style="width: 80px">
			<col style="width: 150px">
			<col style="width: 80px">
		</colgroup>
		<tr>
			<th colspan="2" style="border-bottom: 2px solid black;">Item</th>
			<th style="border-bottom: 2px solid black;">Price</th>
			<th style="border-bottom: 2px solid black;">Quantity</th>
			<th style="border-bottom: 2px solid black;">TotalPrice</th>
		</tr>
		<c:forEach var="cart" items="${itemList}">
		<tr>
			<td rowspan="2">${cart.items_photo1}</td>
			<td>${cart.items_name}</td>
			<td rowspan="2">${cart.items_price}</td>
			<td rowspan="2">${cart.quantity}</td>
			<td rowspan="2">${cart.items_total}</td>
		</tr>
		<tr>
			<td><c:if test="${cart.cate_parent!=0}">
			${cart.cate_parent}/</c:if>
			${cart.cate_name}</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="2">${itemCount}개 품목</td>
			<td>${itemTotal}</td>
		</tr>
		<tr>
			<td colspan="3" style="text-align:right;">
			<input type="button" value="구매하기">
			</td>
		</tr>
		</table>
	</c:if>