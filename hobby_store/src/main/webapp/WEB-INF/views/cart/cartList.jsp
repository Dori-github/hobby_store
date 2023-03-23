<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/cart.js"></script>

<div class="form-wrap">
	<h5 class="form-title">장바구니</h5>
	<!-- 클래스 장바구니 -->
	<c:if test="${courseCount < 1 && itemCount < 1}">
	<h4>장바구니가 비어있습니다</h4>
	</c:if>
	<form id="cart_order" action="${pageContext.request.contextPath}/order/orderForm.do"
		method="post">
		<c:if test="${courseCount > 0}">
			<h6 class="form-name">${courseCount}개의 클래스</h6>
			<table class="table table-borderless">
				<colgroup>
					<col width="1%"/>
					<col width="20%"/>
					<col width="54%"/>
					<col width="15%"/>
				</colgroup>
				<tr class="table-tr-title">
					<th class="table-chk">
					<input type="checkbox" checked="checked" id="chk_all">
					</th>
					<th colspan="2">클래스</th>
					<th>가격</th>
				</tr>
				<c:forEach var="cart" items="${courseList}">
					<tr class="table-tr-content">
						<td>
						<input type="checkbox" name="course_numbers" checked="checked"
							class="choice-btn" data-c-cart-num="${cart.course_num}">
						</td>
						<td><img 
						src="/course/imageView.do?course_num=${cart.course_num}&item_type=1"></td>
						<td>${cart.course_name}</td>
						<td>
						<span class="course-price" data-coursePrice="${cart.course_price}">
						₩<fmt:formatNumber value="${cart.course_price}"/></span>
						</td>
					</tr>	
				</c:forEach>
			</table>
		</c:if>
		<br>

		<!-- 상품 장바구니 -->
		<c:if test="${itemCount > 0}">
			<h6 class="form-name">${itemCount}개의 상품</h6>
			<table class="table table-borderless">
				<colgroup>
					<col width="1%" />
					<col width="24%" />
					<col width="40%" />
					<col width="15%" />
					<col width="5%" />
					<col width="15%" />
				</colgroup>
				<tr class="table-tr-title">
					<th></th>
					<th colspan="2">상품</th>
					<th>가격</th>
					<th>수량</th>
					<th>합계</th>
				</tr>
				<c:forEach var="cart" items="${itemList}" varStatus="statusQuan">
					<c:forEach var="quan" items="${itemQuan}"
						begin="${statusQuan.index}" end="${statusQuan.index}">
						<tr class="table-tr-content">
							<td><input type="checkbox" name="item_numbers"
								checked="checked" class="choice-btn"
								data-i-cart-num="${cart.items_num}"></td>
							<td><img
								src="/items/imageView.do?items_num=${cart.items_num}&items_type=1"></td>
							<td>${cart.items_name}<br>[${cart.cate_name}]
							</td>
							<td><span class="item-price"
								data-price="${cart.items_price}"> ₩<fmt:formatNumber
										value="${cart.items_price}" /></span></td>
							<td><input class="item-quan" type="text"
								style="visibility: hidden;" value="재고:${cart.items_quantity}"/>
								<input class="quan_dec" type="button" value="-" />
								<input type="number" id="quan" name="quantity" readonly
								class="quantity" value="${quan.quantity}" data-quantity="${quan.quantity}">
								<input class="quan_inc" type="button" value="+" /> <input
								class="cart_num" type="text" style="visibility: hidden;"
								value="${quan.cart_num}" /></td>
							<td><span class="itemSum" data-itemSum="${quan.items_total}">
									₩<fmt:formatNumber value="${quan.items_total}" />
							</span></td>
					</c:forEach>
				</c:forEach>
			</table>
		</c:if>
		<c:if test="${courseCount > 0 || itemCount > 0}">
			<c:set var="allTotal" value="${courseTotal+itemTotal}" />
			<span class="allTotal" data-allTotal="<c:out value="${allTotal}"/>">
			총 주문 금액 :	₩<fmt:formatNumber value="${allTotal}" />
			</span><br><br>
			<input type="hidden" id="c_chk" name="c_chk">
			<input type="hidden" id="i_chk" name="i_chk">
			<input type="submit" id="order_btn" value="구매하기">
			</c:if>
	</form>

</div>