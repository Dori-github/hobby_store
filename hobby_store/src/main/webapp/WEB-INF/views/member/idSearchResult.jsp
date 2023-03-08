<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/css/member.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.min.js"></script>


<title>아이디 찾기 결과</title>

<section class="h-100 gradient-form" style="background-color: #fff;">
	<div class="container py-5 h-100">
		<div
			class="row d-flex justify-content-center align-items-center h-100">
			<div class="col-xl-5">
				<div class="card-body p-md-5 mx-md-4">

					<div class="text-center mb-3">
						<h2 style="padding-bottom: 20px; font-weight: 800;">아이디 찾기</h2>
					</div>
					
					

					<c:if test="${empty mem_id}">
					
					<div class="findid">
					정보가 일치하지 않습니다.
					</div>
					
					
					<div class="row mb-4">
						<div class="col-md-14 d-flex justify-content-center">

							<button type="button" class="btn mb-4" onClick="history.go(-1); return false ; "
								style="width: 400%;background-color: #FF4E02;color: white;">돌아가기</button>

						</div>
						
						
						
						</div>
					
					
					</c:if>

					<c:if test="${!empty mem_id}">

						<div class="table">

							<div align="center" style="padding-bottom: 10px;">회원님의 아이디는</div>



							<div align="center">
								<div align="center"
									style="font-weight: bold; font-size: 24px; color: #FF4E02; width: 60%; height: 60%;">
									${mem_id}</div>
							</div>



							<div>
								<div align="center" style="padding-top: 10px;">입니다.</div>
							</div>
						</div>

						<div class="row mb-4">
							<div class="col-md-14 d-flex justify-content-center"
								style="padding-top: 10px;">

								<button type="button" class="btn mb-4"
									onclick="location.href='${pageContext.request.contextPath}/member/login.do';"
									style="width: 400%; background-color: #FF4E02; color: white;">로그인</button>

							</div>

							<div class="row mb-4">
								<div class="col-md-4 d-flex justify-content-center" style="padding: 0px;">
									<a id="find"
										href="${pageContext.request.contextPath}/member/pwSearch.do">비밀번호 찾기</a>
								</div>
	
								<div class="col-md-4 d-flex justify-content-center"
									style="border-right: 1px solid #ddd; border-left: 1px solid; padding: 0px;">
									<!-- Simple link -->
									<a id="find" class="find"
										href="${pageContext.request.contextPath}/main/main.do">홈으로</a>
								</div>
								
								<div class="col-md-4 d-flex justify-content-center"
									style=" border-left: 1px solid; padding: 0px;">
									<!-- Simple link -->
									<a id="find"
										href="${pageContext.request.contextPath}/member/registerUser.do">회원가입</a>
								</div>
								
							</div>
						</div>
					</c:if>



				</div>



				</div>
			</div>
</div>
		
		


</section>