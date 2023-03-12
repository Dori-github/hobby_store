<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 비밀번호 변경 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/mypage.js"></script>
<div id="content">
	<form:form action="changePassword.do" 
	       id="change_form" modelAttribute="memberVO">
	<h2>비밀번호변경</h2>
		<form:errors element="div" 
		                      cssClass="error-color"/>
		                      
		<table class="myPage-info passwd-change">
			<tr>
				<td>현재 비밀번호</td>
				<td>
					<form:password path="now_pw"/>
					<form:errors path="now_pw"
				              cssClass="error-color"/>
				</td>
			</tr>
			<tr>
				<td>새 비밀번호</td>
				<td>
					<form:password path="mem_pw"/>
					<form:errors path="mem_pw"
				              cssClass="error-color"/>
				</td>
			</tr>
			<tr>
				<td>비밀번호 확인</td>
				<td>
					<input type="password" id="confirm_pw"/>
					<span id="message_id"></span>
				</td>
			</tr>
		</table>                    
		<!--                
		<ul>
			<li>
				<label for="now_pw"></label>
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
		-->
		<div class="align-center">
			<form:button class="mod-btn" style="margin-right:10px;">전송</form:button>
			<input type="button" value="My페이지" class="mod-btn"
 			     onclick="location.href='myPage.do'">
		</div>                            
	</form:form>
</div>
<!-- 비밀번호 변경 끝 -->