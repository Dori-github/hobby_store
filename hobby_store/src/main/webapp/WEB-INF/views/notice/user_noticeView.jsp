<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
		<img src="imageView.do?noti_num=${notice.noti_num}&noti_type=2"
			class="detail-img">
	</div>
	<p>${notice.noti_content}</p>

	<%-- 좋아요 시작 --%>
	<div>
		<img id="output_fav" data-num="${notice.noti_num}"
			src="${pageContext.request.contextPath}/images/fav01.gif" width="40">
		<span id="output_fcount"></span>
	</div>
	<%-- 좋아요 끝 --%>

	<hr size="1" width="100%">
	<div class="align-right">
		<c:if test="${!empty user && user.mem_auth == 9}">
			<input type="button" value="공지수정"
				onclick="location.href='admin_modify.do?noti_num=${notice.noti_num}'">
			<input type="button" value="공지삭제" id="delete_btn">
			<script type="text/javascript">
				let delete_btn = document.getElementById('delete_btn');
				delete_btn.onclick = function() {
					let choice = confirm('삭제하시겠습니까?');
					if (choice) {
						location
								.replace('delete.do?noti_num=${notice.noti_num}');
					}
				};
			</script>
			<input type="button" value="목록"
				onclick="location.href='admin_list.do'">
		</c:if>
		<c:if test="${empty user || user.mem_auth < 9}">
			<input type="button" value="목록"
				onclick="location.href='noticeList.do'">
		</c:if>
	</div>
</div>
<!-- 중앙 컨텐츠 끝 -->