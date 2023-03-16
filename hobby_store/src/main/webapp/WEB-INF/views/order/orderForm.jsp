<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    

<!DOCTYPE html>
<form method="post" action="order.do" id="order_register">
		<h5>구매하기</h5>	
		<c:set var="course_name" value='<%=request.getParameter("course_name") %>'/>
		<c:set var="items_num" value='<%=request.getParameter("items_num") %>'/>
		<c:set var="space_name" value='<%=request.getParameter("space_name") %>'/>
		<c:out value="g${course_name}g"/>
		<c:out value="aa${items_num}"/>
		
		<c:set var="c_chkNum" value='<%=request.getParameter("c_chk") %>'/>
		<c:set var="i_chkNum" value='<%=request.getParameter("i_chk") %>'/>
		<c:out value="ㅎㅎㅎ${c_chkNum}"/>
		<c:out value="ㅋㅋㅋ${i_chkNum}"/>
		fgfgf
		<c:forEach var="chk" items="${c_chkArr}" >
		<h4>zzzz</h4>
		<h4>${chk}</h4>
		<h4>zzzz</h4>
		</c:forEach>
		
		<%--  <c:out value="bb${space_name}"/>
		 --%>
		<!-- course_name, course_onoff 넣고난 후 확인하기
		course.js에 jsp 125~126 확인 --> 
		<%-- <c:if test="${course_name == null || items_num == null || space_name == null)}">
		<h2>ss</h2>
		
		<script>
		$(".zaz").hide();
		</script>
		</c:if> --%>
		<%-- <input type="number" name="course_name" value="${course.course_name}" id="course_name">
		<input type="number" name="course_onoff" value="${course.course_onoff}" id="course_onoff">
		 --%>	
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
		<c:forEach var="cart" items="${courseCart}" >
		<tr style="border-bottom: 1px solid #ccc">
			<td style="text-align:left;">
			<img src="/course/imageView.do?course_num=${cart.course_num}&item_type=1"></td>
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
		<c:forEach var="cart" items="${itemCart}" varStatus="statusQuan">

  		<tr style="border-bottom: 1px solid #ccc;">
			
		<td style="text-align:left;">
			<img src="/items/imageView.do?items_num=${cart.items_num}&items_type=1"></td>
			<td style="text-align:left;">${cart.items_name}
			<br>
				<c:if test="${cart.cate_parent!=0}">
				${cart.cate_parent}/</c:if>
				${cart.cate_name}
			</td>
			<td>${cart.items_price}
			<td>			
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
			<td>${allTotal}</td>
		</tr>
		
		</table>
		</c:if>
		<h5><%=request.getParameter("course_price") %></h5>
		<h5>----</h5>
		<h5>${course_price} zzo</h5>
		<h5>----</h5>
		<%String password = request.getParameter("course_price");
		%>
		<c:if test="${itemCount > 0}">
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