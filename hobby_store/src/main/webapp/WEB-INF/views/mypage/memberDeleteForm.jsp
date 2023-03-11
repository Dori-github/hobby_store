<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 회원탈퇴 시작 -->
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
	<form:form action="delete.do" id="delete_form" modelAttribute="memberVO">
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
	</form:form>
</div>
<!-- 회원탈퇴 끝 -->