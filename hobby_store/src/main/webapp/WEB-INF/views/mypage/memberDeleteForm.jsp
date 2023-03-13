<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 회원탈퇴 시작 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

  <style>
    body {
      min-height: 100vh;
    }
	
	.input-form {
      max-width: 350px;
     }
  </style>
<script type="text/javascript">
$(function(){
	$('#delete_form').submit(function(){
		let check = confirm('정말 삭제하시겠습니까?');
		return check;
	});
});
</script>
<div id="content">
	<h2>회원탈퇴</h2>
	<!--<form:form action="delete.do" id="delete_form" modelAttribute="memberVO">
		<form:errors element="div" cssClass="error-color"/> 
		<ul>
			<li>
				<label for="mem_id">아이디</label>
				<form:input path="mem_id"/>
				<form:errors path="mem_id" cssClass="error-color"/>
			</li>
			<li>
				<label for="mem_pw">비밀번호</label>
				<form:password path="mem_pw"/>
				<form:errors path="mem_pw" cssClass="error-color"/>
			</li>
		</ul>  
		<div class="align-center">
			<form:button>전송</form:button>
			<input type="button" value="MY페이지" onclick="location.href='myPage.do'">
		</div>                   
	</form:form>-->
	
	<div class="container">
    <div class="input-form-backgroud row">
      <div class="input-form col-md-12 mx-auto">
        <h4 class="mb-4 align-center">회원탈퇴</h4>
        <div class="align-left">
        <form:form action="delete.do" id="delete_form" modelAttribute="memberVO" class="validation-form">
         <form:errors element="div" cssClass="error-color"/> 
          <div class="mb-3">
            <label for="mem_id">아이디</label>
            <form:input path="mem_id" type="password" class="form-control" id="mem_id" placeholder="아이디"
            style="border: none;background: #eee;"/>
            <form:errors path="mem_id" cssClass="error-color"/>
            <div class="invalid-feedback">
              아이디를 입력해주세요.
            </div>
          </div>
          
          <div class="mb-3">
            <label for="mem_pw">비밀번호</label>
            <form:input path="mem_pw" class="form-control" id="mem_pw" placeholder="비밀번호"
            style="border: none;background: #eee;"/>
            <form:errors path="mem_id" cssClass="error-color"/>
            <div class="invalid-feedback">
              비밀번호를 입력해주세요.
            </div>
          </div>

          <hr class="mb-4">
          <div class="mb-4 align-center">
          <button class="btn btn-primary mypage-btn" type="submit" style="background-color:#FF4E02; color:white; border:none;">회원 탈퇴</button>
          <input class="btn btn-primary mypage-btn" type="button" value="my페이지" onclick="location.href='myPage.do'" style="background-color:#FF4E02; color:white; border:none;">
          </div>
        </form:form>
        
      </div>
    </div>
  </div>
</div>
</div>
<script>
    window.addEventListener('load', () => {
      const forms = document.getElementsByClassName('validation-form');

      Array.prototype.filter.call(forms, (form) => {
        form.addEventListener('submit', function (event) {
          if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
          }

          form.classList.add('was-validated');
        }, false);
      });
    }, false);
  </script>
<!-- 회원탈퇴 끝 -->