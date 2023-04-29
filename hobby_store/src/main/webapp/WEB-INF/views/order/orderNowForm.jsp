<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/order.js"></script>

<div class="form-wrap">
	<form method="post" action="nowOrder.do" id="orderNow_register">
		<!-- 클래스 -->
		<c:set var="course_num" value='<%=request.getParameter("course_num") %>'/>
		<c:set var="course_name" value='<%=request.getParameter("course_name") %>'/>
		<c:set var="course_price" value='<%=request.getParameter("course_price") %>'/>
		<c:set var="course_quan" value='<%=request.getParameter("course_quan") %>'/>
		<c:set var="course_onoff" value='<%=request.getParameter("course_onoff") %>'/>
		<c:set var="course_onoff" value='<%=request.getParameter("course_onoff") %>'/>
		<c:set var="course_date" value='<%=request.getParameter("course_date") %>'/>
		<c:set var="course_time" value='<%=request.getParameter("course_time") %>'/>
		
		<input type="hidden" name="course_num" value="<c:out value="${course_num}"/>" id="course_num">
		<input type="hidden" name="course_name" value="<c:out value="${course_name}"/>" id="course_name">
		<input type="hidden" name="course_price" value="<c:out value="${course_price}"/>" id="course_price">
		<input type="hidden" name="course_quan" value="<c:out value="${course_quan}"/>" id="course_quan">
		<input type="hidden" name="course_onoff" value="<c:out value="${course_onoff}"/>" id="course_onoff">
		<input type="hidden" name="course_date" value="<c:out value="${course_date}"/>" id="course_date">
		<input type="hidden" name="course_time" value="<c:out value="${course_time}"/>" id="course_time">
		<input type="hidden" name="course_total" value="${courseTotal}" id="course_total">
		
		<!-- 상품 -->
		<c:set var="items_num" value='<%=request.getParameter("items_num") %>'/>
		<c:set var="items_name" value='<%=request.getParameter("items_name") %>'/>
		<c:set var="items_price" value='<%=request.getParameter("items_price") %>'/>
		<c:set var="items_quan" value='<%=request.getParameter("items_quan") %>'/>
		
		<input type="hidden" name="items_num" value="<c:out value="${items_num}"/>" id="items_num">
		<input type="hidden" name="items_name" value="<c:out value="${items_name}"/>" id="items_name">
		<input type="hidden" name="items_quan" value="<c:out value="${items_quan}"/>" id="items_quan">
		<input type="hidden" name="items_total" value="${itemsTotal}" id="items_total">
		
		<!-- 장소대여 -->
		<c:set var="space_num" value='<%=request.getParameter("space_num") %>'/>
		<c:set var="space_name" value='<%=request.getParameter("space_name") %>'/>
		<c:set var="space_price" value='<%=request.getParameter("space_price") %>'/>
		<c:set var="space_np" value='<%=request.getParameter("space_np") %>'/>
		<c:set var="order_quantity" value='<%=request.getParameter("order_quantity") %>'/>
	
		<input type="hidden" name="space_num" value="<c:out value="${space_num}"/>" id="space_num">
		<input type="hidden" name="space_name" value="<c:out value="${space_name}"/>" id="space_name">
		<input type="hidden" name="space_price" value="<c:out value="${space_price}"/>" id="space_price">			
		<input type="hidden" name="space_np" value="<c:out value="${space_np}"/>" id="space_quantity">
		<input type="hidden" name="order_quantity" value="<c:out value="${order_quantity}"/>" id="order_quantity">
		<input type="hidden" name="space_total" value="${spaceTotal}" id="space_total">
		
		<!-- 바로 구매하기 -->
		<c:if
			test="${course_name != null || items_name != null || space_name != null}">
			<h5 class="form-title">바로 구매하기</h5>
			<table class="table table-borderless">
				<colgroup>
					<col width="15%" />
					<col width="45%" />
					<col width="20%" />
					<col width="20%" />
				</colgroup>
				<tr class="table-tr-title">
					<th colspan="2"
						style="text-align: left; border-bottom: 1px solid #ccc;">구매품목</th>
					<th style="border-bottom: 1px solid #ccc;">가격</th>
					<th style="border-bottom: 1px solid #ccc;">수량</th>
				</tr>
				<tr class="table-tr-content">
					<td><img
						src="/course/imageView.do?course_num=${course_num}&item_type=1"
						onerror="this.style.display='none'"> <img
						src="/items/imageView.do?items_num=${items_num}&items_type=1"
						onerror="this.style.display='none'"> <img
						src="/space/imageView.do?space_num=${space_num}&space_type=1"
						onerror="this.style.display='none'">
					<td>${course_name}${items_name}${space_name}</td>
					<td><span>₩<fmt:formatNumber
								value="${course_price}${items_price}${space_price}" />
					</span></td>
					<td>${course_quan}${items_quan}${order_quantity}</td>
				</tr>
				<tr>
					<td colspan="4"><span class="allTotal"><br> 총 주문 금액 :
							₩${courseTotal}${itemsTotal}${spaceTotal} </span></td>
				</tr>
			</table>
		</c:if>
		
		<div class="order-wrap">
			<input type="submit" value="결제하기" id="order_button">
			<input type="button" value="주문취소"
			       onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>

		<c:if test="${items_name != null}">
			<hr
				style="width: 50%; margin: 0.5rem 0 2rem 25%; border: 2px solid #bbb;">
			<ul class="info-form">
				<li>
					<h5 class="form-title" style="text-align: center;">배송지 정보</h5>
					<p>* 필수항목을 모두 입력해주세요</p>
				</li>
				<li><label for="receive_name" class="form-label">* 이름</label> <input
					type="text" name="receive_name" class="order_form"></li>
				<li><label for="receive_post" class="form-label">* 우편번호</label>
					<input type="text" name="receive_post" id="receive_post" readonly
					style="width: 12rem" /> <input type="button" class="receive-post"
					onclick="execDaumPostcode()" value="우편번호 검색" /></li>
				<li><label for="receive_address1" class="form-label">*
						주소</label> <input type="text" name="receive_address1"
					id="receive_address1" readonly class="order_form"></li>

				<li><label for="address2" class="form-label">* 상세주소</label> <input
					type="text" name="receive_address2" id="receive_address2"
					class="order_form"></li>
				<li><label for="receive_phone" class="form-label">*
						휴대전화</label> <input type="text" name="receive_phone" class="order_form"></li>
				<li><label for="notice" class="form-label">* 배송 메시지</label> <input
					type="text" name="notice" id="form-notice" class="order_form"
					Placeholder=" 1~20자 내로 입력해주세요"></li>
			</ul>
			<div class="order-wrap">
				<input type="submit" value="결제하기" id="order_btn"> <input
					type="button" value="주문취소"
					onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
			</div>
		</c:if>
	</form> 


	<!-- 우편번호 검색 시작 -->
	<!-- iOS에서는 position:fixed 버그가 있음, 적용하는 사이트에 맞게 position:absolute 등을 이용하여 top,left값 조정 필요 -->
	<div id="layer"
		style="display: none; position: fixed; overflow: hidden; z-index: 998; -webkit-overflow-scrolling: touch;">
		<img src="//t1.daumcdn.net/postcode/resource/images/close.png"
			id="btnCloseLayer"
			style="cursor: pointer; position: absolute; right: -3px; top: -3px; z-index: 1"
			onclick="closeDaumPostcode()" alt="닫기 버튼">
	</div>
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