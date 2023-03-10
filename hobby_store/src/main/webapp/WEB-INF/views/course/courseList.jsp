<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="${pageContext.request.contextPath}/css/course.css" rel="stylesheet">
<!-- 중앙 컨텐츠 시작 -->
<script src="${pageContext.request.contextPath}/js/course.js"></script>
<script type="text/javascript">
	$(function(){
		$('#search_form').on('submit',function(){
			if($('#search_bar').val().trim()==''){
				Swal.fire({
                    icon: 'warning',
                    title:'검색어를 입력하세요!',
                    showCancelButton: false,
                    confirmButtonText: "확인",
                    confirmButtonColor: "#FF4E02"
                });
				$('#search_bar').val('').focus();
				return false;
			}
		});
	});
</script>
<div id="course_main">
	<!-- 사이즈바 시작 -->
	<div id="sidebar" data-param="${param.cate}">
		<ul>
			<li>
				<a class="cate <c:if test="${param.cate=='전체'}">active active-color0</c:if>">전체</a>
			</li>
			
			<c:forEach begin="0" end="5" var="parent">
				<li>
					<a class="cate <c:if test="${param.cate==course_cate[parent].cate_name}">active active-color0</c:if>">${course_cate[parent].cate_name}</a>
					<ul class="d_cate">
						<c:forEach var="cate" items="${course_cate}">
							<c:if test="${cate.cate_parent==parent+1}">
							<li><a <c:if test="${param.cate==cate.cate_name}">class="active-color"</c:if>>${cate.cate_name}</a></li>
							</c:if>
						</c:forEach>
					</ul>
				</li>
			</c:forEach>
		</ul>
	</div>
	
	<!-- 사이즈바 끝 -->
	
	<!-- 오른쪽 컨텐츠 시작 -->
	<div id="content">
		<!-- 검색 시작 -->
		<div id="onoff">
			<label for="off" class="off <c:if test="${param.onoff==1}">click1</c:if>"><b>오프라인</b></label>
			<input type="radio" id="off" name="onoff" value="1" <c:if test="${param.onoff==1}">checked="checked"</c:if>
					onclick="location.href='courseList.do?onoff=1&oneweek=1&cate=전체'">
			<label for="on" class="on  <c:if test="${param.onoff==2}">click1</c:if>"><b>온라인</b></label>
			<input type="radio" id="on" name="onoff" value="2" <c:if test="${param.onoff==2}">checked="checked"</c:if>
					onclick="location.href='courseList.do?onoff=2&cate=전체'">
		</div>
		<div id="course_search">
			<form action="/course/courseList.do?onoff=${param.onoff}<c:if test="${param.onoff==1}">&oneweek=${param.oneweek}</c:if>&cate=${param.cate}" method="post" class="navbar-expand search-form d-flex" id="search_form">
				<select class="form-select" name="keyfield">
					<option value="1" <c:if test="${param.keyfield==1}">selected</c:if>>전체</option>
					<option value="2" <c:if test="${param.keyfield==2}">selected</c:if>>제목</option>
					<option value="3" <c:if test="${param.keyfield==3}">selected</c:if>>내용</option>
					<option value="4" <c:if test="${param.keyfield==4}">selected</c:if>>작성자</option>
				</select> 
				<input type="search" name="keyword" class="search-bar" id="search_bar" value="${param.keyword}" placeholder="주변에 다양한 클래스를 찾아보세요!" autocomplete="off">
				<button type="submit" class="search-btn" id="search_icon"><i class="fa-solid fa-magnifying-glass"></i></button>
			</form>
		</div>
		<div id="select">
			<!-- 원데이,정기와 지역은 오프라인 선택시에만 보임 -->
			<c:if test="${param.onoff==1}">
			<span id="total">총 ${count}개</span>
			<select class="form-select select" id="oneweek" name="oneweek">
				<option value="1" <c:if test="${param.oneweek == 1}">selected</c:if>>원데이</option>
				<option value="2" <c:if test="${param.oneweek == 2}">selected</c:if>>정기</option>
			</select>
			
			<select class="form-select select" id="location" name="location">
				<option value="전체" <c:if test="${param.location == '전체'}">selected</c:if>>지역 전체</option>
				<option value="서울" <c:if test="${param.location == '서울'}">selected</c:if>>서울</option>
				<option value="경기" <c:if test="${param.location == '경기'}">selected</c:if>>경기</option>
				<option value="인천" <c:if test="${param.location == '인천'}">selected</c:if>>인천</option>
				<option value="강원" <c:if test="${param.location =='강원'}">selected</c:if>>강원</option>
				<option value="충북" <c:if test="${param.location =='충북'}">selected</c:if>>충북</option>
				<option value="세종" <c:if test="${param.location == '세종'}">selected</c:if>>세종</option>
				<option value="충남" <c:if test="${param.location == '충남'}">selected</c:if>>충남</option>
				<option value="대전" <c:if test="${param.location == '대전'}">selected</c:if>>대전</option>
				<option value="경북" <c:if test="${param.location == '경북'}">selected</c:if>>경북</option>
				<option value="대구" <c:if test="${param.location == '대구'}">selected</c:if>>대구</option>
				<option value="울산" <c:if test="${param.location == '울산'}">selected</c:if>>울산</option>
				<option value="부산" <c:if test="${param.location == '부산'}">selected</c:if>>부산</option>
				<option value="경남" <c:if test="${param.location == '경남'}">selected</c:if>>경남</option>
				<option value="전북" <c:if test="${param.location == '전북'}">selected</c:if>>전북</option>
				<option value="전남" <c:if test="${param.location == '전남'}">selected</c:if>>전남</option>
				<option value="광주" <c:if test="${param.location == '광주'}">selected</c:if>>광주</option>
				<option value="제주" <c:if test="${param.location == '제주'}">selected</c:if>>제주</option>
			</select>
			</c:if>
			
			<select class="form-select select" id="order" name="order">
				<option value="1" <c:if test="${param.order == 1}">selected</c:if>>최신순</option>
				<option value="2" <c:if test="${param.order == 2}">selected</c:if>>리뷰순</option>
				<option value="3" <c:if test="${param.order == 3}">selected</c:if>>좋아요순</option>
				<option value="4" <c:if test="${param.order == 4}">selected</c:if>>가격낮은순</option>
			</select>
		</div>
		<hr size="3" noshade width="100%" style="color:gray;margin:.8em 0;">
		
		<!-- 클래스 목록 시작 -->
		<c:if test="${count==0}">
		<table class="table table-group-divider align-center">
			<tr>
				<td>표시할 게시물이 없습니다</td>
			</tr>
		</table>
		</c:if>
		<c:if test="${count>0}">
		<div class="row row-cols-1 row-cols-sm-2 row-cols-md-4 g-4">
			<c:forEach var="course" items="${list}">
			<div class="col">
				<div class="card h-100" style="position:relative;">
				
					<c:forEach var="fav" items="${favCheck}">
				 	<span class="red-heart" data-num="${course.course_num}"><i class="fa-regular fa-heart" <c:if test="${fav.fmem_num==user.mem_num&&fav.course_num==course.course_num}">
				 		style="font-weight:bold;"</c:if>></i></span>
				 	</c:forEach>
				 	
					<a href="courseDetail.do?course_num=${course.course_num}" style="display:block;">
						<div class="card-img-top">
						  <img src="/course/imageView.do?course_num=${course.course_num}&item_type=1" width="100%" height="100%">
						</div>
						<div class="card-body">
						  	<div class="color-gray">
						  		<span>${course.mem_nickname}</span>
						  		<span class="card-hit"><i class="fa-solid fa-eye"></i> ${course.course_hit} <i class="fa-solid fa-heart"></i> <span class="countFav">${course.fav}</span></span>
						  	</div>
						  	<h5 class="card-title"><b>${course.course_name}</b></h5>
						  	<span><i class="fa-regular fa-star"></i> ${course.staravg} (후기 ${course.replycount})</span>
						  	<p class="card-text">
						  		<span>${course.course_address1}</span>
						  		<br><b><fmt:formatNumber>${course.course_price}</fmt:formatNumber>원</b><span style="color:gray;"> / 1인</span>
						  	</p>
						</div>
					</a>
				</div>
			</div>
			</c:forEach>
		</div>
		<!-- 클래스 목록 끝 -->
		
		<!-- 페이지처리 시작 -->
		<div class="align-center list-page">${page}</div>
		<!-- 페이지처리 끝 -->
		</c:if>
	</div>
	<!-- 오른쪽 컨텐츠 끝 -->
</div>
<!-- 중앙 컨텐츠 끝 -->