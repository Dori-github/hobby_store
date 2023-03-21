<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<!DOCTYPE html>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/order.js"></script>
<form:form method="post" action="order.do" id="order_form" modelAttribute="orderVO">
		<h5>구매하기</h5>	
		<c:set var="course_name" value='<%=request.getParameter("course_name") %>'/>
		<c:set var="items_num" value='<%=request.getParameter("items_num") %>'/>
		<c:set var="space_name" value='<%=request.getParameter("space_name") %>'/>
		<c:out value="g${course_name}g"/>
		<c:out value="aa${items_num}"/>
		
		<c:set var="c_chkNum" value='<%=request.getParameter("c_chk") %>'/>
		<c:set var="i_chkNum" value='<%=request.getParameter("i_chk") %>'/>
		<c:out value="${c_chkNum}"/>
		<c:out value="${i_chkNum}"/>
		fgfgf
		<c:forEach var="chk" items="${i_chkArr}" >
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
			<img style="height:500px; width:500px;" src="/course/imageView.do?course_num=${cart.course_num}&item_type=1"></td>
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
		<c:forEach var="cart" items="${itemCart}" varStatus="status">

  		<tr style="border-bottom: 1px solid #ccc;">
			
		<td style="text-align:left;">
			<img style="height:500px; width:500px;" src="/items/imageView.do?items_num=${cart.items_num}&items_type=1"></td>
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
			<td>${itemTotal}</td>
			
			</c:forEach>
		</tr>
		</c:forEach>
		
			<tr>
			<td colspan="4"></td>
			<td>${allTotal}</td>
		</tr>
		
		</table>
		</c:if>
		
		<c:if test="${itemCount > 0}">
		<ul>			
			<li>
				<h4>배송지 정보</h4>       
			</li>
			<li>
				<label for="receive_name">이름</label>
				<form:input type="text" path="receive_name" name="receive_name"	class="order_form" id="receive_name"/>                         
			</li>
			<li>
				<label for="receive_post">우편번호</label>
				<form:input type="text" path="receive_post" name="receive_post" class="order_form" readonly="true"/>
				<form:input type="button" path="receive_post" style="height:20px;" 
				onclick="execDaumPostcode()" value="우편번호찾기"/>
			</li>
			<li>
				<label for="receive_address1">주소</label>
				<form:input type="text" path="receive_address1" name="receive_address1" class="order_form" readonly="true"/>       
			</li>
			<li>
				<label for="receive_address2">상세주소</label>
				<form:input type="text" path="receive_address2" name="receive_address2" class="order_form"/>       
				<form:errors element="div" cssClass="error-color" />                       
			</li>
			<li>
				<label for="receive_phone">휴대전화</label>
				<form:input type="text" path="receive_phone" name="receive_phone" class="order_form"/>       
				<form:errors element="div" cssClass="error-color" />                       
			</li>
			<li>
				<label for="notice">배송 메시지</label>
				<form:input type="text" path="notice" name="notice" class="order_form" />  
				<form:errors element="div" cssClass="error-color" />
                                             
			</li>
		</ul>
		</c:if>
		<input type="text" id="c_chkNum" name="c_chkNum[]" value="${c_chkNum}">
		<input type="text" id="i_chkNum" name="i_chkNum[]" value="${i_chkNum}">
			
		<div class="align-center">
			<input type="button" value="test" id="test_btn">
			<form:button value="결제하기" id="order_btn"/>
			<input type="button" value="홈으로"
			       onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>
	</form:form> 
	
<!-- 우편번호 검색 시작 -->
						<!-- iOS에서는 position:fixed 버그가 있음, 적용하는 사이트에 맞게 position:absolute 등을 이용하여 top,left값 조정 필요 -->
						<div id="layer"
							style="display: none; position: fixed; overflow: hidden; z-index: 998; -webkit-overflow-scrolling: touch;">
							<img src="//t1.daumcdn.net/postcode/resource/images/close.png"
								id="btnCloseLayer"
								style="cursor: pointer; position: absolute; right: -3px; top: -3px; z-index: 1"
								onclick="closeDaumPostcode()" alt="닫기 버튼">
						</div>

						<script
							src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
						<script>

										// 우편번호 찾기 화면을 넣을 element
										var element_layer = document
												.getElementById('layer');

										function closeDaumPostcode() {
											// iframe을 넣은 element를 안보이게 한다.
											element_layer.style.display = 'none';
										}

										function execDaumPostcode() {
											new daum.Postcode(
													{
														oncomplete : function(
																data) {
															// 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

															// 각 주소의 노출 규칙에 따라 주소를 조합한다.
															// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
															var addr = ''; // 주소 변수
															var extraAddr = ''; // 참고항목 변수

															//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
															if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
																addr = data.roadAddress;
															} else { // 사용자가 지번 주소를 선택했을 경우(J)
																addr = data.jibunAddress;
															}

															// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
															if (data.userSelectedType === 'R') {
																// 법정동명이 있을 경우 추가한다. (법정리는 제외)
																// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
																if (data.bname !== ''
																		&& /[동|로|가]$/g
																				.test(data.bname)) {
																	extraAddr += data.bname;
																}
																// 건물명이 있고, 공동주택일 경우 추가한다.
																if (data.buildingName !== ''
																		&& data.apartment === 'Y') {
																	extraAddr += (extraAddr !== '' ? ', '
																			+ data.buildingName
																			: data.buildingName);
																}
																// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
																if (extraAddr !== '') {
																	extraAddr = ' ('
																			+ extraAddr
																			+ ')';
																}
																//(주의)address1에 참고항목이 보여지도록 수정
																// 조합된 참고항목을 해당 필드에 넣는다.
																//(수정) document.getElementById("address2").value = extraAddr;

															}
															//(수정) else {
															//(수정)    document.getElementById("address2").value = '';
															//(수정) }

															// 우편번호와 주소 정보를 해당 필드에 넣는다.
															document
																	.getElementById('receive_post').value = data.zonecode;
															//(수정) + extraAddr를 추가해서 address1에 참고항목이 보여지도록 수정
															document
																	.getElementById("receive_address1").value = addr
																	+ extraAddr;
															// 커서를 상세주소 필드로 이동한다.
															document
																	.getElementById(
																			"receive_address2")
																	.focus();

															// iframe을 넣은 element를 안보이게 한다.
															// (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
															element_layer.style.display = 'none';
														},
														width : '100%',
														height : '100%',
														maxSuggestItems : 5
													}).embed(element_layer);

											// iframe을 넣은 element를 보이게 한다.
											element_layer.style.display = 'block';

											// iframe을 넣은 element의 위치를 화면의 가운데로 이동시킨다.
											initLayerPosition();
										}

										// 브라우저의 크기 변경에 따라 레이어를 가운데로 이동시키고자 하실때에는
										// resize이벤트나, orientationchange이벤트를 이용하여 값이 변경될때마다 아래 함수를 실행 시켜 주시거나,
										// 직접 element_layer의 top,left값을 수정해 주시면 됩니다.
										function initLayerPosition() {
											var width = 300; //우편번호서비스가 들어갈 element의 width
											var height = 400; //우편번호서비스가 들어갈 element의 height
											var borderWidth = 5; //샘플에서 사용하는 border의 두께

											// 위에서 선언한 값들을 실제 element에 넣는다.
											element_layer.style.width = width
													+ 'px';
											element_layer.style.height = height
													+ 'px';
											element_layer.style.border = borderWidth
													+ 'px solid';
											// 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
											element_layer.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width) / 2 - borderWidth)
													+ 'px';
											element_layer.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height) / 2 - borderWidth)
													+ 'px';
										}
									</script>

						<!-- 우편번호 검색 끝 -->