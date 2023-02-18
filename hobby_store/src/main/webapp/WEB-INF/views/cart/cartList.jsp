<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>장바구니 목록</h3>
	<c:if test="${count == 0}">
		<div>장바구니에 담은 클래스가 없습니다</div>
	</c:if>
	<c:if test="${count > 0}">
		<h4>Course Cart List</h4>
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
		<c:forEach var="cart" items="${list}">
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
			<td colspan="2">${count}개 품목</td>
			<td>all_total</td>
		</tr>
		<tr>
			<td colspan="3" style="text-align:right;">
			<input type="button" value="구매하기">
			</td>
		</tr>
		</table>
	</c:if>