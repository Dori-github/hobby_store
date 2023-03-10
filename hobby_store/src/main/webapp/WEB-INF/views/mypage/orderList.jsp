<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 주문조회 시작 -->
<div id="content">
	<div id="delivery_box">
		<div class="delivery-status"></div>
	</div>
<form action="admin_list.do" id="search_form" method="get">
		<ul class="search">
			<li>
				<select name="keyfield">
					<option value="1" <c:if test="${param.keyfield == 1}">selected</c:if>>주문번호</option>
					<option value="2" <c:if test="${param.keyfield == 2}">selected</c:if>>주문품목명</option>
				</select>
			</li>
			<li>
				<input type="search" name="keyword"
				       id="keyword" value="${param.keyword}">
			</li>
			<li>
				<input type="submit" value="찾기">
				<input type="button" value="목록" onclick="location.href='admin_list.do'">
			</li>
		</ul>                                   
</form>
	<c:if test="${count == 0}">
	<table class="table table-group-divider align-center">
		<tr>
			<td>
				표시할 게시물이 없습니다.
			</td>
		</tr>
	</table>
	</c:if>	
	<c:if test="${count > 0}">
	<table class="table table-group-divider align-center">
		<tr>
			<th>주문번호</th>
			<th>상품명</th>
			<th>총구매금액</th>
			<th>주문날짜</th>
			<th>배송상태</th>
		</tr>
		<c:forEach var="order" items="${list}">
		<tr>
			<td>${order.order_num}</td>
			<td>${order.order_name}</td>
			<td class="align-center">
				<fmt:formatNumber value="${order.order_price}"/>
			</td>
			<td class="align-center">${order.order_date}</td>
			<td class="align-center">
				<c:if test="${order.order_status==0}">구매완료</c:if>
				<c:if test="${order.order_status==1}">예약완료</c:if>
				<c:if test="${order.order_status==2}">배송준비중</c:if>
				<c:if test="${order.order_status==3}">배송중</c:if>
				<c:if test="${order.order_status==4}">배송완료</c:if>
			<!-- <c:if test="${order.refund_status==0}">환불요청중</c:if>
				<c:if test="${order.refund_status==1}">환불완료</c:if>
				 -->
				<c:if test="${order.order_status < 2}">
				<input type="button" value="주문취소" onclick="location.href='user_orderCancel.do'">
				</c:if>
				<c:if test="${order.order_status > 2}">
				<input type="button" value="환불요청" onclick="location.href='user_order.do'">
				</c:if>
			</td>
		</tr>
		</c:forEach>
	</table>
	<div class="align-center">${page}</div>
	</c:if>
</div>
<!-- 주문조회 끝 -->