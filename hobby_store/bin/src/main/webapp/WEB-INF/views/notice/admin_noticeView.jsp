<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 중앙 컨텐츠 시작 -->
<script src="${pageContext.request.contextPath}/js/videoAdapter.js"></script>
<script src="${pageContext.request.contextPath}/js/board.fav.js"></script>
<script src="${pageContext.request.contextPath}/js/board.reply.js"></script>
<div class="page-main">
	<h1 class="align-center">공지사항</h1>
	<br>
	<hr size="1" width="100%">
	<br>
	<h2 class="align-center">${notice.noti_title}</h2>
	<ul class="detail-info">
		<!-- 
		<li>
			<div class="noti-image">
			<img src="imageView.do?noti_num=${notice.noti_num}&noti_type=2" width="400" height="400" >
			</div>
		</li>
		 -->
		<li>
			<div class="align-right">
			<c:if test="${empty notice.mem_nickname}">작성자 : ${notice.mem_id}</c:if>
			<c:if test="${!empty notice.mem_nickname}">작성자 : ${notice.mem_nickname}</c:if>
			<br>
			<c:if test="${!empty notice.noti_mdate}">
			최근 수정일 : ${notice.noti_mdate}
			</c:if>
			<c:if test="${empty notice.noti_mdate}">
			작성일 : ${notice.noti_date}
			</c:if>
			조회수 : ${notice.noti_hit}
			</div>
		</li>
	</ul>
	<hr size="1" width="100%">
	<div class="align-center">
		<img src="imageView.do?noti_num=${notice.noti_num}&noti_type=2" class="detail-img">
	</div>	
	<p>
		${notice.noti_content}
	</p>
	<div class="align-right">
		<input type="button" value="공지사항수정" onclick="location.href='admin_modify.do'"> 
		<input type="button" value="목록" onclick="location.href='admin_list.do'">
	</div>
	
		<%-- 좋아요 시작 --%>
		
		<%-- 좋아요 끝 --%>
</div>
<!-- 중앙 컨텐츠 끝 -->