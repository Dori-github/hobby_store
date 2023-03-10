<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 관리자 회원목록 시작 -->
<script type="text/javascript">
$(function(){
	$('.modify-btn').on('click',function(){
		$(this).parent().find('.select-auth').css('display','inline');
		$(this).parent().find('.modify-reset').css('display','inline');
		$(this).parent().find('.modify-submit').css('display','inline');
		$(this).parent().find('.modify-auth').css('display','none');
		$(this).hide();
	});
	
	$('.modify-reset').on('click',function(){
		$(this).parent().find('.select-auth').css('display','none');
		$(this).parent().find('.modify-btn').css('display','inline');
		$(this).parent().find('.modify-auth').css('display','inline');
		$(this).parent().find('.modify-submit').css('display','none');
		$(this).hide();
	});
	
	$('.modify-submit').on('click',function(){
		if(!$('.select-auth > option:selected').val()){
			alert('권한을 선택해주세요');
			return false;
		}
		
		/*$.ajax({
			url:'admin_modify.do',
			type:'post',
			data:{mem_auth=$('.select-auth').val(),mem_num=${member.mem_num}},
			dataType:'json',
			success:function(param){
				
			},
			error:function(){
				location.href="common/notice";
			}
		});*/
	});
});
</script>
<div id="content">
	<h2>회원목록</h2>
	<form action="admin_list.do" id="search_form" method="get">
		<ul class="search">
			<li>
				<select name="keyfield">
					<option value="1" <c:if test="${param.keyfield == 1}">selected</c:if>>ID</option>
					<option value="2" <c:if test="${param.keyfield == 2}">selected</c:if>>이름</option>
					<option value="3" <c:if test="${param.keyfield == 3}">selected</c:if>>이메일</option>
					<option value="4" <c:if test="${param.keyfield == 4}">selected</c:if>>전체</option>
				</select>
			</li>
			<li>
				<input type="search" name="keyword"
				       id="keyword" value="${param.keyword}">
			</li>
			<li>
				<input type="submit" value="찾기">
				<input type="button" value="목록" onclick="location.href='admin_list.do'">
			</li>
		</ul>                                   
	</form>
	<c:if test="${count==0}">
		<table class="table table-group-divider align-center">
			<tr>
				<td>
				표시할 회원정보가 없습니다.
				</td>
			</tr>
		</table>
	</c:if>
	
	<c:if test="${count > 0}">
	<table class="table table-group-divider align-center">
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>전화번호</th>
			<th>이메일</th>
			<th>가입일</th>
			<th>권한</th>
		</tr>
		<c:forEach var="member" items="${list}">
		<tr>
			<td>${member.mem_id}</td>
			<td>${member.mem_name}</td>
			<td>${member.mem_cell}</td>
			<td>${member.mem_email}</td>
			<td>${member.mem_date}</td>
			<td>
			<form action="admin_modify.do" method="post">
			<input type="hidden" name="mem_num" value="${member.mem_num}"/>
			<select name="mem_auth" name="mem_auth" class="select-auth" style="display:none;">
				<option value="2" <c:if test="${member.mem_auth==2}">selected</c:if>>일반</option>
				<option value="3" <c:if test="${member.mem_auth==3}">selected</c:if>>강사</option>
				<option value="0" <c:if test="${member.mem_auth==1}">selected</c:if>>정지</option>
			</select>
			<input type="submit" value="확인" class="modify-submit" style="display:none;">			
			</form>
			<input type="button" value="취소" class="modify-reset" style="display:none;">	
			<div class="modify-auth">
				<c:if test="${member.mem_auth==0}">탈퇴</c:if>
				<c:if test="${member.mem_auth==1}">정지</c:if>
				<c:if test="${member.mem_auth==2}">일반</c:if>
				<c:if test="${member.mem_auth==3}">강사</c:if>
				<c:if test="${member.mem_auth==9}">관리</c:if>
			</div>
			<c:if test="${member.mem_auth<9}">
			<input type="button" value="권한 수정" class="modify-btn">
			</c:if>
			</td>
		</tr>
		</c:forEach>
	</table>
	<div class="align-center">${page}</div>
	</c:if>
</div>
<!-- 관리자 회원목록 끝 -->