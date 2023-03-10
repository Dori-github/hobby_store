<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <meta charset="UTF-8">
    <link href="${pageContext.request.contextPath}/css/member.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <!-- 중앙 컨텐츠 시작  -->
    <title>로그인</title>
        <section class="h-100 gradient-form" style="background-color: #fff;">
        <div class="container py-5 h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-xl-5">
                        <div class="card-body p-md-5 mx-md-4">
                       
                            <div class="text-center mb-3">
                                <h2 style="padding-bottom: 20px;font-weight: 800;">취미상점</h2>
                            </div>
                            <!-- Pills content -->
                           
                            <div class="tab-content">
                                <div class="tab-pane fade show active" id="pills-login" role="tabpanel"
                                    aria-labelledby="tab-login">
                                    
                                    <!-- 유효성 체크 -->
                                    <form:form action="login.do" id="login_form" modelAttribute="memberVO">
                                    <!-- 아이디,비밀번호 불일치 에러처리 -->
                                    <form:errors element="div" cssClass="error-color"/> 
                                    
                                        <!-- id input -->
                                        <div class="form-outline mb-4">
                                            <form:input path="mem_id" type="text" id="mem_id" class="form-control"
                                                style="border: none;background: #eee;" placeholder="아이디" />
                                        </div>
                                        
                                        <form:errors element="div" path="mem_id" cssClass="error-color" />

                                        <!-- Password input -->
                                        <div class="form-outline mb-4">
                                            <form:input path="mem_pw" type="password" id="mem_pw" class="form-control"
                                                style="border: none;background: #eee;" placeholder="비밀번호" />
                                        </div>
                                        
                                        

                                       <form:button type="submit" class="btn mb-4" style="width: 100%;background-color: #FF4E02;color: white;">로그인</form:button>
                                        
                                       <div class="form-check d-flex justify-content-start mb-4">
                                            <input class="form-check-input" type="checkbox"  name="auto"
                                                id="auto" />
                                            <label class="form-check-label" for="auto" style="margin-left: 5px;"> 로그인 상태 유지
                                            </label>
                                        </div>


                                        <!-- 2 column grid layout -->
                                        <div class="row mb-4">
                                            <div class="col-md-4 d-flex justify-content-center">
                                                <a id="find" href="${pageContext.request.contextPath}/member/idSearch.do">아이디 찾기</a>
                                            </div>
                                            <div class="col-md-4 d-flex justify-content-center" style="border-right: 1px solid #ddd;border-left: 1px solid;padding: 0px;">
                                                <!-- Simple link -->
                                                <a  id="find" href="${pageContext.request.contextPath}/member/pwSearch.do">비밀번호 찾기</a>
                                            </div>

                                            <div class="col-md-4 d-flex justify-content-center" style="border-left: 1px solid;padding: 0px;">
                                                <!-- Simple link -->
                                                <a id="find" class="find" href="${pageContext.request.contextPath}/member/registerUser.do">회원가입</a>
                                            </div>
                                        </div>
                                    </form:form>
                                </div>
                            </div>
                       
                        </div>
                    </div>
                </div>
            </div>
       
    </section>
    <!-- 중앙 컨텐츠 끝 -->