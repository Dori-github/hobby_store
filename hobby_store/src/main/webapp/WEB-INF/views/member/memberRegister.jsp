<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/css/member.css"
	rel="stylesheet">	  
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Plugin -->
<script src="${pageContext.request.contextPath}/js/rolling.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/confirmId.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/confirmNickname.js"></script>  
<!-- 텍스트 좌우 방향 롤링 -->
<script>
$(function(){
    id=rollingLeft('.box1', 'li', '1000', '2000');
    $('.box1').hover(function(){
      clearInterval(id);
    }, function(){
      id= rollingLeft('.box1', 'li', '1000', '2000');
    })
  })
</script>


<!-- 중앙 컨텐츠 시작  -->

<title>회원가입</title>

	<section class="h-100 gradient-form" style="background-color: #fff;">
		<div class="container py-5 h-100">
			<div class="row d-flex justify-content-center align-items-center h-100">
				 <div class="col-xl-5">
						<div class="row">
						</div>

						<div class="card-body p-md-5 mx-md-4">   
							<div class="text-center mb-3">
								<div class="view-box">
									<h2 style="font-weight: 800;">회원가입</h2>
								 	<ul class="box1">
										<li>성장이 목마를때, 취미상점</li>
										<li>나의 온라인 사수, 취미상점</li>
										<li>취미상점에서 가치를 높이세요</li>
									</ul>
							</div>
							
                            </div>
                            
						</div>
	
				<div class="hr-sect">간편 회원가입</div>
							
							<br>
							
							<div class="text-center mb-3">
								<img src="${pageContext.request.contextPath}/images/kakao_talk.svg" style="height: 50px;"> 
								<img src="${pageContext.request.contextPath}/images/naver.svg" style="height: 50px;">
							</div>
                            
                            <br>
                       
                            <!-- 유효성 체크 -->
							<form:form action="registerUser.do" id="register_form"
								modelAttribute="memberVO">
								
								<h5 style="font-weight: 800;">필수항목</h5>
								
								<br>
								

								<label for="mem_id">아이디</label>
								<div class="input-group mb-3">
									<form:input path="mem_id" type="text" id="mem_id"
										class="form-control" style="border: none;background: #F2F2F2;"
										placeholder="4~12 영문,숫자만 허용" autocomplete="off" />
									<div class="input-group-append">
										<input type="button" class="btn btn-secondary" id="confirmId" value="중복확인">
						
									</div>
								</div>
								
								<span id="message_id"></span>
								<form:errors element="div" path="mem_id" cssClass="error-color" />
								
								<br>
		                        
		                    
								<label for="mem_name">이름</label>
								<div class="input-group mb-3">
									<form:input path="mem_name" type="text" id="mem_name"
										class="form-control" style="border: none;background: #F2F2F2;" />
									
								</div>
								
								<form:errors element="div" path="mem_name" cssClass="error-color" />
								
								<br>

								<label for="mem_nickname">닉네임</label>
								<div class="input-group mb-3">
									<form:input path="mem_nickname" type="text" id="mem_nickname"
										class="form-control" style="border: none;background: #F2F2F2;" />
									<div class="input-group-append">
										<input type="button" class="btn btn-secondary" id="confirmNickname" value="중복확인">
									</div>
								</div>
							     
							     <span id="message_nickname"></span>

								<label for="mem_pw">비밀번호</label>
								<div class="input-group mb-3">
									<form:input path="mem_pw" type="password" id="mem_pw"
										class="form-control" style="border: none;background: #F2F2F2;" />
									
								</div>
								
								
								<form:errors element="div" path="mem_pw" cssClass="error-color" />
								
								<br>

								<label for="mem_cell">전화번호</label>
								<div class="input-group mb-3">
									<form:input path="mem_cell" type="tel" id="mem_cell"
										class="form-control" style="border: none;background: #F2F2F2;" />
									
								</div>
								
								<form:errors element="div" path="mem_cell" cssClass="error-color" />
								
								<br>

								<label for="mem_email">이메일</label>
								<div class="input-group mb-3">
									<form:input path="mem_email" type="email" id="mem_email"
										class="form-control" style="border: none;background: #F2F2F2;" />
									
								</div>
								
								<form:errors element="div" path="mem_email" cssClass="error-color" />
								
								<br>

								<!-- 수정해야 할 부분 + -->
								<div class="input-group mb-3">
									<input type="text" id="otp" class="form-control"
										style="border: none; background: #F2F2F2;"
										placeholder="인증번호 6자리를 입력하세요">
									<div class="input-group-append">
										<button class="btn btn-secondary" type="button">본인인증</button>
									</div>
								</div>
								<!-- 수정해야 할 부분 +  -->
								
								<br>

								<label for="mem_zipcode">우편번호</label>
								<div class="input-group mb-3">
									<form:input path="mem_zipcode" type="text" id="mem_zipcode"
										class="form-control" style="border: none;background: #F2F2F2;" />
									<div class="input-group-append">
										<form:button class="btn btn-secondary" type="button"
											onclick="execDaumPostcode()">우편번호 찾기</form:button>
									</div>
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
																	.getElementById('mem_zipcode').value = data.zonecode;
															//(수정) + extraAddr를 추가해서 address1에 참고항목이 보여지도록 수정
															document
																	.getElementById("mem_address1").value = addr
																	+ extraAddr;
															// 커서를 상세주소 필드로 이동한다.
															document
																	.getElementById(
																			"mem_address2")
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
									
								</div>
								
								<form:errors element="div" path="mem_zipcode" cssClass="error-color" />
								
								<br>

								<label for="mem_address1">주소</label>
								<div class="input-group mb-3">
									<form:input path="mem_address1" type="text" id="mem_address1"
										class="form-control" style="border: none;background: #F2F2F2;" />
									
								</div>
								
								
								
								<form:errors element="div" path="mem_cell" cssClass="error-color" />
								
								<br>

								<label for="mem_address2">상세주소</label>
								<div class="input-group mb-3">
									<form:input path="mem_address2" type="text" id="mem_address2"
										class="form-control" style="border: none;background: #F2F2F2;" />
									
								</div>
								
								<form:errors element="div" path="mem_address2" cssClass="error-color" />
								
								<br>
								<br>

								
								<div><h5 style="font-weight: 800;">당신에 대해 알고싶어요!</h5></div>

								<br>
								

								<div class="input-group mb-3">
									<div class="input-group-prepend" style="padding-right: 20px;">
										<label for="region">선호지역을 선택해주세요 : </label>
									</div>
									<select class="form-select form-select-sm"
										aria-label=".form-select-sm example" name="country_num">
										<c:forEach var="member" items="${countryList}">
										<option value="${member.country_num}">${member.country_detail}</option>
										</c:forEach>
									</select>
								</div>

								<div class="input-group mb-3">
									<div class="input-group-prepend" style="padding-right: 20px;">
										<label for="region">주요 관심사를 선택해주세요 : </label>
									</div>
									<select class="form-select form-select-sm"
										aria-label=".form-select-sm example" name="like_num">
										<c:forEach var="member" items="${likeList}">
										<option value="${member.like_num}">${member.like_detail}</option>
										</c:forEach>
									</select>
								</div>
								
								
								<br>

								<!-- 2 column grid layout -->
								<div class="row mb-4">
									<div class="col-md-14 d-flex justify-content-center">

										<form:button type="submit" class="btn mb-4" style="width: 400%;background-color: #FF4E02;color: white;">가입하기</form:button>

									</div>
													
								</div>
</form:form>
							

						</div>
					</div>
				</div>
	</section>


<!-- 중앙 컨텐츠 끝 -->

