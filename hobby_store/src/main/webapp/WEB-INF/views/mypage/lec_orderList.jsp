<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 강사 주문조회 시작 -->
<div id="content">
<form action="lec_order.do" id="search_form" method="get">
		<ul class="search">
			<li>
				<select name="keyfield" id="keyfield">
					<option value="0" <c:if test="${param.keyfield == 0}">selected</c:if>>구매완료</option>
					<option value="1" <c:if test="${param.keyfield == 1}">selected</c:if>>예약완료</option>
					<option value="2" <c:if test="${param.keyfield == 2}">selected</c:if>>배송준비중</option>
					<option value="3" <c:if test="${param.keyfield == 3}">selected</c:if>>배송중</option>
					<option value="4" <c:if test="${param.keyfield == 4}">selected</c:if>>배송완료</option>
					<option value="5" <c:if test="${param.keyfield == 5}">selected</c:if>>환불요청중</option>
					<option value="6" <c:if test="${param.keyfield == 6}">selected</c:if>>환불완료</option>
				</select>
			</li>
			<li>
				<input type="hidden" name="keyword" id="keyword" value="${param.keyword}">
			</li>
			<li>
				<input type="submit" value="찾기" class="order-search-btn">
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
			<td>
			<a href="lec_modify.do?order_num=${order.order_num}">
				${order.order_name}
			</a>
			</td>
			<td class="align-center">
				<fmt:formatNumber value="${order.order_price}"/>
			</td>
			<td class="align-center">${order.order_date}</td>
			<td class="align-center">
			<c:if test="${order.refund_status==null}">
				<c:if test="${order.order_status==0}">구매완료</c:if>
				<c:if test="${order.order_status==1}">예약완료</c:if>
				<c:if test="${order.order_status==2}">배송준비중</c:if>
				<c:if test="${order.order_status==3}">배송중</c:if>
				<c:if test="${order.order_status==4}">배송완료</c:if>
			</c:if>
			<!-- refund_status 값 변경은 가능하지만 포인트 내역 삭제는 안 된 상태 -->
			<c:if test="${order.order_status!=null}">
				<c:if test="${order.refund_status==0}">환불요청중</c:if>
				<c:if test="${order.refund_status==1}">환불완료</c:if>
			</c:if>
				<!-- <c:if test="${order.order_status > 2}">
				<input type="button" value="환불요청" onclick="location.href='user_order.do'">
				</c:if> -->
			</td>
		</tr>
		</c:forEach>
	</table>
	<div class="align-center">${page}</div>
	</c:if>
	<input type="button" value="목록" onclick="location.href='lec_order.do'" class="order-list-btn">
</div>
<!-- 강사 주문조회 끝 -->