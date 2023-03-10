<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/cart.js"></script>

<div style="padding: 7rem 7rem;">
	<!-- 클래스 장바구니 -->
	<c:if test="${courseCount < 1 && itemCount < 1}">
	<h4>장바구니가 비어있습니다</h4>
	</c:if>
	<form id="cart_order" action="${pageContext.request.contextPath}/order/orderForm.do"
	                                  method="post">
	
	<c:if test="${courseCount > 0}">
	<h5>Course Cart [ ${courseCount} items ]</h5>	
		<table class="table table-borderless">
		<colgroup>
			<col style="width: 1rem">
			<col style="width: 45rem">
			<col style="width: 15rem">
		</colgroup>
		<tr>
			<th colspan="2" style="text-align:left;border-bottom: 1px solid #ccc;">Course</th>
			<th style="border-bottom: 1px solid #ccc;">Price</th>
		</tr>
		<c:forEach var="cart" items="${courseList}">
		<c:if test="${courseCount == 0}">
		<div>장바구니가 비어있습니다</div>
		</c:if>
		<tr style="border-bottom: 1px solid #ccc">
			<td style="text-align:left;">
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
		</table>
	</c:if>
	<br>

	<!-- 상품 장바구니 -->	
	<c:if test="${itemCount > 0}">	
	<h5>Item Cart List [ ${itemCount} items ]</h5>
		<table class="table table-borderless">
		<colgroup>
			<col style="width: 1rem">
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
		<c:forEach var="cart" items="${itemList}" varStatus="statusQuan">
  		<c:if test="${itemCount == 0}">
		<div>장바구니가 비어있습니다</div>
		</c:if>
  		<tr style="border-bottom: 1px solid #ccc;">
			
		<td style="text-align:left;">
			<img src="${pageContext.request.contextPath}/images/${cart.items_photo1}"></td>
			<td style="text-align:left;">${cart.items_name}
			<br>
				<c:if test="${cart.cate_parent!=0}">
				${cart.cate_parent}/</c:if>
				${cart.cate_name}
			</td>
			<td>${cart.items_price}
			<td>
			<input class="item_quan" type="text" value="재고:${cart.items_quantity}"/>
			
			<c:forEach var="quan" items="${itemQuan}" begin="${statusQuan.index}" end="${statusQuan.index}">
			
			<input class="cart_num" type="text" value="${quan.cart_num}"/>
			<input class="quan_dec" type="button" value="-"/>			
			
			<div id="quan"></div>
			
			<input type="number" name="quantity" class="quantity" value="${quan.quantity}">
			<input class="quan_inc" type="button" value="+"/>
			
			</td>
			<!-- <div class="items_total"> -->
			<td>${quan.items_total}</td>
			
			</c:forEach>
		</tr>
		</c:forEach>
		
			<tr>
			<td colspan="4"></td>
			<!-- <div class="itemTotal"> -->
			<td>
			<span class="itemTotal" data-itemTotal="${itemTotal}">${itemTotal}원</span>
			</td>
		</tr>
		</table>
	</c:if>
	<c:if test="${courseCount > 0 || itemCount > 0}">
	<input type="submit" id="order_btn" value="구매하기">
	</c:if>		
		</form>
		
</div>
