<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Admin 메뉴 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/member.js"></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<div class="side-bar">
   <label for="expand-menu"><div>메뉴</div></label>
    <input type="checkbox" id="expand-menu" name="expand-menu">
    <ul class="main-menu">
        <li><a href="${pageContext.request.contextPath}/member/admin_list.do" class="item"><div>회원관리</div></a></li>
	</ul>
</div>
<!-- Admin 메뉴 끝 -->