<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 중앙 컨텐츠 시작 -->
<script src="${pageContext.request.contextPath}/js/videoAdapter.js"></script>
<script src="${pageContext.request.contextPath}/js/qna.reply.js"></script>
<div class="page-main">
	<h2>${board.title}</h2>
	<ul class="detail-info">
		<li>
			<c:if test="${empty qna.mem_nickname}">${qna.id}</c:if>
			<c:if test="${!empty qna.mem_nickname}">${qna.mem_nickname}</c:if>
			<br>
			<c:if test="${!empty qna.qna_mdate}">
			최근 수정일 : ${board.modify_date}
			</c:if>
			<c:if test="${empty qna.qna_mdate}">
			작성일 : ${board.qna_date}
			</c:if>
			조회 : ${qna.qna_hit}
		</li>
	</ul>
	<hr size="1" width="100%">
	<p>
		${qna.qna_content}
	</p>
	<hr size="1" width="100%">
	<div class="align-right">
		<c:if test="${!empty user && user.mem_num == qna.mem_num}">
		<input type="button" value="수정" 
		  onclick="location.href='update.do?qna_num=${qna.qna_num}'">
		<input type="button" value="삭제" id="delete_btn"> 
		<script type="text/javascript">
			let delete_btn = document.getElementById('delete_btn');
			delete_btn.onclick=function(){
				let choice = confirm('삭제하시겠습니까?');
				if(choice){
					location.replace('delete.do?qna_num=${qna.qna_num}');
				}
			};
		</script> 
		</c:if> 
		<input type="button" value="목록"
		           onclick="location.href='list.do'">
	</div>
	<!-- 댓글 UI 시작 -->
	<div id="reply_div">
		<span class="re-title">Q&A 답변하기</span>
		<form id="re_form">
			<input type="hidden" name="qna_num"
			    value="${qna.qna_num}" id="qna_num">
			<textarea rows="3" cols="50" 
			  name="re_content" id="re_content"
			  class="rep-content"
			  <c:if test="${empty user}">disabled="disabled"</c:if>>
			  <c:if test="${empty user}">로그인해야 작성할 수 있습니다.</c:if>
			</textarea>    
		
			<c:if test="${!empty user}">
			<div id="re_first">
				<span class="letter-count">300/300</span>
			</div>
			<div id="re_second">
				<input type="submit" value="전송">
			</div>
			</c:if>
		</form>
	</div>
	<!-- 댓글 목록 출력 -->
	<div id="output"></div>
	<div class="paging-button" style="display:none;">
		<input type="button" value="더보기">
	</div>
	<div id="loading" style="display:none;">
		<img src="${pageContext.request.contextPath}/images/loading.gif" width="50" 
		                                height="50">
	</div>
	<!-- 댓글 UI 끝 -->
</div>