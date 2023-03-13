<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>

<div style="padding: 7rem 7rem;">
	<!-- 클래스 장바구니 -->
	<c:if test="${courseCount < 1 && itemCount < 1}">
	<h4>장바구니가 비어있습니다</h4>
	</c:if>
	<form id="cart_order"
		action="${pageContext.request.contextPath}/order/orderForm.do"
		method="post">
		
			
		<c:if test="${courseCount > 0}">
			<h5>Course Cart [ ${courseCount} items ]</h5>
			<table class="table table-borderless">
				<colgroup>
					<col style="width: 1rem">
					<col style="width: 1rem">
					<col style="width: 45rem">
					<col style="width: 15rem">
				</colgroup>
				<tr>
					<th style="border-bottom: 1px solid #ccc;">
					<input type="checkbox" checked="checked" id="chk_all">
					</th>
					<th colspan="2"
						style="text-align: left; border-bottom: 1px solid #ccc;">Course</th>
					<th style="border-bottom: 1px solid #ccc;">Price</th>
				</tr>
				<c:forEach var="cart" items="${courseList}">
					<c:if test="${courseCount == 0}">
						<div>장바구니가 비어있습니다</div>
					</c:if>
					<tr style="border-bottom: 1px solid #ccc">
						<td>
						<input type="checkbox" name="course_numbers" checked="checked"
							class="choice-btn" value="${cart.cart_num}">
						</td>
						<td style="text-align: left;"><img
							src="/course/imageView.do?course_num=${cart.course_num}&item_type=1" ></td>
						<td style="text-align: left;">${cart.course_name}<br> <c:if
								test="${cart.cate_parent!=0}">
				${cart.cate_parent}/</c:if> ${cart.cate_name} <br>
							${cart.course_onoff}
						</td>
						<td>
						<span class="course-price" data-coursePrice="${cart.course_price}">
						₩<fmt:formatNumber value="${cart.course_price}"/></span>
						</td>
					</tr>
				<tr>
					<td colspan="2"></td>
					<td><span class="courseTotal"
						data-courseTotal="${courseTotal}" style="visibility:hidden;"> ₩<fmt:formatNumber
								value="${courseTotal}" />
					</span></td>
				</tr>
				
				</c:forEach>
			</table>
		</c:if>
		<br>

		<!-- 상품 장바구니 -->
		<c:if test="${itemCount > 0}">
			<h5>Item Cart List [ ${itemCount} items ]</h5>
			<table class="table table-borderless">
				<colgroup>
					<col style="width: 1rem">
					<col style="width: 1rem">
					<col style="width: 25rem">
					<col style="width: 10rem">
					<col style="width: 10rem">
					<col style="width: 15rem">
				</colgroup>
				<tr>
					<th style="border-bottom: 1px solid #ccc;"></th>
					<th colspan="2"
						style="text-align: left; border-bottom: 1px solid #ccc;">Item</th>
					<th style="border-bottom: 1px solid #ccc;">Price</th>
					<th style="border-bottom: 1px solid #ccc;">Quantity</th>
					<th style="border-bottom: 1px solid #ccc;">TotalPrice</th>
				</tr>
				<c:forEach var="cart" items="${itemList}" varStatus="statusQuan">
					<c:forEach var="quan" items="${itemQuan}"
							begin="${statusQuan.index}" end="${statusQuan.index}">
					<c:if test="${itemCount == 0}">
						<div>장바구니가 비어있습니다</div>
					</c:if>
					<tr style="border-bottom: 1px solid #ccc;">
						<td>
						<input type="checkbox" name="item_numbers" checked="checked"
							class="choice-btn" value="${cart.cart_num}">
						</td>
						<td style="text-align: left;"><img
							src="/items/imageView.do?items_num=${cart.items_num}&items_type=1"></td>
						<td style="text-align: left;">${cart.items_name}<br> <c:if
								test="${cart.cate_parent!=0}">
				${cart.cate_parent}/</c:if> ${cart.cate_name}
						</td>
						<td>
						<span class="item-price" data-price="${cart.items_price}">
						₩<fmt:formatNumber value="${cart.items_price}"/></span>
						<td><input class="item-quan" type="text" style="visibility:hidden;"
							value="재고:${cart.items_quantity}" />
						

								
								<input class="quan_dec" type="button" value="-" />
								<input type="number" id="quan" name="quantity" class="quantity"
									value="${quan.quantity}" data-quantity="${quan.quantity}">
								<input class="quan_inc" type="button" value="+" />
								<input class="cart_num" type="text" style="visibility:hidden;" value="${quan.cart_num}" />
								
								</td>
						<td><span class="itemSum"
							data-itemSum="${quan.items_total}"> ₩<fmt:formatNumber
									value="${quan.items_total}"/>
						</span></td>
				</c:forEach>
				</tr>
				</c:forEach>
				<tr>
					<td colspan="5"></td>
					<!-- <div class="itemTotal"> -->
					<td><span class="itemTotal" data-itemTotal="${itemTotal}">
							₩<fmt:formatNumber value="${itemTotal}" />
					</span></td>
				</tr>
			</table>
		</c:if>
		<c:if test="${courseCount > 0 || itemCount > 0}">
			<c:set var="allTotal" value="${courseTotal+itemTotal}" />
			<span class="allTotal" data-allTotal="<c:out value="${allTotal}"/>">
			총 주문 금액 :	₩<fmt:formatNumber value="${allTotal}" />
			</span><br><br>
			<input type="submit" id="order_btn" value="구매하기">
		</c:if>
	</form>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/cart.js"></script>
