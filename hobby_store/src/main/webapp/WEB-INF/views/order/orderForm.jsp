<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    

<!DOCTYPE html>
<h4>주문폼</h4>
<form method="post" action="order.do" id="order_register">
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
		<c:forEach var="cart" items="${courseCart}">
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
			<div id="quan"></div>
			<input type="number" class="quantity" value="${quan.quantity}">
			</td>
			
			<!-- <div class="items_total"> -->
			<td>${quan.items_total}</td>
			
			</c:forEach>
		</tr>
		</c:forEach>
		
			<tr>
			<td colspan="4"></td>
			<!-- <div class="itemTotal"> -->
			<td>${itemTotal}</td>
		</tr>
		
		</table>
		</c:if>
		<ul>
			<li>
				<label for="receive_name">받는 사람</label>
				<input type="text" name="receive_name"
				       id="receive_name" maxlength="10">       
			</li>
			<li>
				<label for="zipcode">우편번호</label>
				<input type="text" name="receive_post"
				       id="zipcode" maxlength="5">
				<input type="button" 
				           onclick="execDaumPostcode()"
				           value="우편번호 찾기"> 
				                        
			</li>
			<li>
				<label for="address1">주소</label>
				<input type="text" name="receive_address1"
				       id="address1" maxlength="30">       
			</li>
			<li>
				<label for="address2">상세주소</label>
				<input type="text" name="receive_address2"
				       id="address2" maxlength="30">       
			</li>
			<li>
				<label for="receive_phone">전화번호</label>
				<input type="text" name="receive_phone"
				       id="receive_phone" maxlength="15">       
			</li>
			<li>
				<label for="notice">남기실 말씀</label>
				<input type="text" name="notice"
				       id="notice" maxlength="300">       
			</li>
			<li>
				<label>결제수단</label>
				<input type="radio" name="payment" value="1"
				    class="payment" id="payment1">통장입금
				<input type="radio" name="payment" value="2"
				    class="payment" id="payment2">카드결제           
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="주문상품 결제하기" id="general_btn">
			<input type="button" value="홈으로"
			       onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>
	</form> 