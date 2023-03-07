<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!-- MyPage 메뉴 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/member.js"></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<div class="side-bar">
   <label for="expand-menu"><div>메뉴</div></label>
    <input type="checkbox" id="expand-menu" name="expand-menu">
    <ul>
        <li><a href="${pageContext.request.contextPath}/member/myPage.do" class="item"><div>나의 프로필</div></a></li>
        <li><a href="#" class="item"><div>나의 활동내역</div></a></li>
        <li><a href="#" class="item"><div>배송목록</div></a></li>
        <li><a href="#" class="item"><div>나의 구매내역</div></a></li>
        <li><a href="#" class="item"><div>예약목록</div></a></li>
        <li><a href="#" class="item"><div>회원탈퇴</div></a></li>
        <li><a href="${pageContext.request.contextPath}/talk/talkList.do" class="item"><div>채팅</div></a></li>
    </ul>
</div>
<!-- MyPage 메뉴 끝 -->



