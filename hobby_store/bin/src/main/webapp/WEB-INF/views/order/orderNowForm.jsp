<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    

<!DOCTYPE html>
<form method="post" action="nowOrder.do" id="orderNow_register">
		<h5>바로주문하기</h5>	
		<c:set var="course_name" value='<%=request.getParameter("course_name") %>'/>
		<c:set var="items_name" value='<%=request.getParameter("items_name") %>'/>
		<c:set var="items_price" value='<%=request.getParameter("items_price") %>'/>
		<c:set var="items_quantity" value='<%=request.getParameter("items_quantity") %>'/>
		<c:set var="space_num" value='<%=request.getParameter("space_num") %>'/>
		<c:set var="space_name" value='<%=request.getParameter("space_name") %>'/>
		<c:set var="space_price" value='<%=request.getParameter("space_price") %>'/>
		<c:set var="space_np" value='<%=request.getParameter("space_np") %>'/>
		<c:set var="space_quan" value='<%=request.getParameter("space_quan") %>'/>
		<c:out value="c${course_name}"/>
		<c:out value="i${items_name}"/>
		<c:out value="i${items_price}"/>
		<c:out value="i${items_quantity}"/>
		<c:out value="i${space_num}"/>
		<c:out value="i${space_name}"/>
		<c:out value="i${space_price}"/>
		<c:out value="i${space_quan}"/>
		<input type="hidden" name="space_num" value="<c:out value="${space_num}"/>" id="space_num">
		<input type="hidden" name="space_name" value="<c:out value="${space_name}"/>" id="space_name">
		<input type="hidden" name="space_price" value="<c:out value="${space_price}"/>" id="space_price">			
		<input type="hidden" name="space_np" value="<c:out value="${space_np}"/>" id="space_quantity">
		<input type="hidden" name="space_quan" value="<c:out value="${space_quan}"/>" id="space_quan">
			
		<!-- course_name, course_onoff 넣고난 후 확인하기
		course.js에 jsp 125~126 확인 --> 
		<%-- <c:if test="${course_name == null || items_num == null || space_name == null)}">
		<h2>ss</h2>
		
		<script>
		$(".zaz").hide();
		</script>
		</c:if> --%>
		
		<p>spaceTotal<br>
		${spaceTotal}<br>
		</p>
		<!-- 최대 예약 인원 != 예약 인원
		현재 spaceTotal = space_price * space_np
		space_np 대신 공간대여 상세페이지의 구매수량 값이 들어가야 함 -->
			
		<c:if test="${courseCount > 0}">
		<h5 class="zaz">Course Cart [ ${courseCount} items ]</h5>	
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
			<td></td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="2"></td>
			<td>${courseTotal}</td>
		</tr>
		</table>
		
	</c:if>
		

		
		<c:if test="${space_name != null}">
		<table class="table table-borderless">
		<colgroup>
			<col style="width: 1rem">
			<col style="width: 25rem">
			<col style="width: 10rem">
			<col style="width: 10rem">
			<col style="width: 15rem">
		</colgroup>
		<tr>
			<th colspan="3" style="text-align:left;border-bottom: 1px solid #ccc;">Item</th>
			<th style="border-bottom: 1px solid #ccc;">Price</th>
			<th style="border-bottom: 1px solid #ccc;">Quantity</th>
		</tr>
		<tr style="border-bottom: 1px solid #ccc;">
			
		<td style="text-align:left;">
			<img src="${pageContext.request.contextPath}/images/${cart.items_photo1}"></td>
			<td colspan="2" style="text-align:left;">
			${course_name}
			${items_name}
			${space_name}
			</td>
			<td>
			${items_price}
			${space_price}
			</td>
			<td>		
			${items_quantity}	
			${space_np}
			${space_quan}
			</td>
		</tr>
			<tr>
			<td colspan="4"></td>
			<td>${spaceTotal}</td>
		</tr>
		
		</table>
		 </c:if> 
		
		<c:if test="${items_name != null}">
		<ul>			
			<li>
				<h4>배송지 정보</h4>       
			</li>
			<li>
				<label for="receive_name">이름</label>
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
				<label for="receive_phone">휴대전화</label>
				<input type="text" name="receive_phone"
				       id="receive_phone" maxlength="15">       
			</li>
			<li>
				<label for="notice">배송 메시지</label>
				<input type="text" name="notice"
				       id="notice" maxlength="300">       
			</li>
<!-- 			<li>
				<label>결제수단</label>
				<input type="radio" name="payment" value="1"
				    class="payment" id="payment1">통장입금
				<input type="radio" name="payment" value="2"
				    class="payment" id="payment2">카드결제           
			</li> -->
		</ul>
		</c:if>
		
		<div class="align-center">
			<input type="submit" value="결제하기" id="general_btn">
			<input type="button" value="홈으로"
			       onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>
	</form> 