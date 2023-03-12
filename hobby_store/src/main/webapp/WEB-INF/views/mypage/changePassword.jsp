<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 비밀번호 변경 시작 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

  <style>
    body {
      min-height: 100vh;
    }

    .input-form {
      max-width: 400px;

      margin-top: 80px;
      padding: 32px;

      background: #fff;
      -webkit-border-radius: 10px;
      -moz-border-radius: 10px;
      border-radius: 10px;
      
      -webkit-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
      -moz-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
      box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
      
    }
  </style>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/mypage.js"></script>
<div id="content">
	<h2>비밀번호변경</h2>
	<!--<form:form action="changePassword.do" 
	       id="change_form" modelAttribute="memberVO">
		<form:errors element="div" 
		                      cssClass="error-color"/>
		<ul>
			<li>
				<label for="now_pw">현재 비밀번호</label>
				<form:password path="now_pw"/>
				<form:errors path="now_pw"
				              cssClass="error-color"/>
			</li>
			<li>
				<label for="mem_pw">새비밀번호</label>
				<form:password path="mem_pw"/>
				<form:errors path="mem_pw"
				              cssClass="error-color"/>
			</li>
			<li>
				<label for="confirm_pw">비밀번호 확인</label>
				<input type="password" id="confirm_pw"/>
				<span id="message_id"></span>
			</li>
		</ul> 
		<div class="align-center">
			<form:button>전송</form:button>
			<input type="button" value="My페이지"
			     onclick="location.href='myPage.do'">
		</div>                            
	</form:form>-->
	<div class="container">
    <div class="input-form-backgroud row">
      <div class="input-form col-md-12 mx-auto">
        <h4 class="mb-4 align-center">회원정보 수정</h4>
        <div class="align-left">
        <form:form  action="changePassword.do" id="change_form" modelAttribute="memberVO" class="validation-form">
         
          <div class="mb-3">
            <label for="now_pw">현재 비밀번호</label>
            <form:input path="now_pw" type="password" class="form-control" id="now_pw"/>
            <form:errors path="now_pw" cssClass="error-color"/>
          </div>
          
          <div class="mb-3">
            <label for="mem_pw">새 비밀번호</label>
            <form:input path="mem_pw" class="form-control" id="mem_pw"/>
            <div class="invalid-feedback">
              새 비밀번호 입력해주세요.
            </div>
          </div>

          <div class="mb-3">
            <label for="confirm_pw">비밀번호 확인</label>
            <input type="password" class="form-control" id="confirm_pw"/>
            <span id="message_id"></span>
            <div class="invalid-feedback">
              비밀번호 확인을 입력해주세요.
            </div>
          </div>

          <hr class="mb-4">
          <div class="mb-4 align-center">
          <button class="btn btn-primary mypage-btn" type="submit" style="background-color:#FF4E02; color:white; border:none;">비밀번호 수정</button>
          <input class="btn btn-primary mypage-btn" type="button" value="목록으로" onclick="location.href='myPage.do'" style="background-color:#FF4E02; color:white; border:none;">
          </div>
        </form:form>
        
      </div>
    </div>
    <footer class="my-3 text-center text-small">
      <p class="mb-1">&copy; 2021 YD</p>
    </footer>
  </div>
</div>
</div>
<!-- 비밀번호 변경 끝 -->