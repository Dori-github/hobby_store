<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>장바구니 목록</h3>
	<c:if test="${count == 0}">
		<div>표시할 게시물이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
		<h4>Course Cart List</h4>
		<tr>
			<th>No.</th>
			<th>Quantity</th>
			<th>Mem_num</th>
			<th>Course_num</th>
		</tr>
		<c:forEach var="cart" items="${list}">
		<tr>
			<td>${cart.cart_num}</td>
			<td>${cart.quantity}</td>
			<td>${cart.mem_num}</td>
			<td>${cart.course_num}</td>
		</tr>
		</c:forEach>
	</c:if>