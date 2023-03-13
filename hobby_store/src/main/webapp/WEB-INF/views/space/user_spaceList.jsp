<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<link  rel="stylesheet" href="${pageContext.request.contextPath}/css/space.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css">
<!-- 상품목록 시작 -->
<script src="${pageContext.request.contextPath}/js/space.fav.js"></script>
<script src="${pageContext.request.contextPath}/js/space.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.js"></script>

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
<div id="space_main"><!-- main이었음 -->
	<!-- 사이드바 시작 -->
<div id="sidebar" data-param="${param.cate}" >


		 <li><a class="cate" href="${pageContext.request.contextPath}/space/list.do"> 전체 </a></li>
		 <li><a class="cate" href="${pageContext.request.contextPath}/space/list.do?cate_num=1">촬영 스튜디오</a>
		 <li><a class="cate" href="${pageContext.request.contextPath}/space/list.do?cate_num=2" >댄스공간</a></li>
		 <li><a class="cate" href="${pageContext.request.contextPath}/space/list.do?cate_num=3" >체육시설</a></li>
		 <li><a class="cate" href="${pageContext.request.contextPath}/space/list.do?cate_num=4" >악기 연습실</a></li>
		 <li><a class="cate" href="${pageContext.request.contextPath}/space/list.do?cate_num=5" >공연장</a></li>
		 <li><a class="cate" href="${pageContext.request.contextPath}/space/list.do?cate_num=6" >그외</a></li>
	


</div>
	<!-- 사이즈바 끝 -->



<div id="content">

<!-- 검색폼 시작 -->
<div id="space_search">                           <!-- get이엇음 -->
			<form action="/space/list.do" method="get" class="navbar-expand search-form d-flex" id="search_form">
				<select class="form-select" name="keyfield">
					<option value="1" <c:if test="${param.keyfield==1}">selected</c:if>>전체</option>
					<option value="2" <c:if test="${param.keyfield==2}">selected</c:if>>제목</option>
					<option value="3" <c:if test="${param.keyfield==3}">selected</c:if>>내용</option>
				</select> 
				<input type="search" name="keyword" class="search-bar" id="search_bar" value="${param.keyword}" placeholder="원하는 대여 공간을 찾아보세요!" autocomplete="off">
				<button type="submit" class="search-btn" id="search_icon"><i class="fa-solid fa-magnifying-glass"></i></button>
	
			</form>
</div><br>
		<div id="select" ><!-- class원래 없었음 -->
			<span id="total">총 ${count}개</span>
			<!-- form-select select -->
			
			<!-- class="form-select select"밑에 원래 -->
			<select class="form-select select" id="order" name="order" style="width:100px;">
				<option value="1" <c:if test="${param.order == 1}">selected</c:if>>최신순</option>
				<option value="2" <c:if test="${param.order == 2}">selected</c:if>>리뷰순</option>
				<option value="3" <c:if test="${param.order == 3}">selected</c:if>>좋아요순</option>
				<option value="4" <c:if test="${param.order == 4}">selected</c:if>>가격낮은순</option>
			</select>
		</div>
		<hr size="3" noshade width="100%" style="color:gray;margin:.8em 0;">
<!-- 검색폼 끝 -->
    <c:if test="${count == 0}">
    <table class="table table-group-divider align-center">
	<tr>
				<td>표시할 게시물이 없습니다</td>
			</tr>
		</table>
	</c:if>
	<c:if test="${count > 0}">
   <div class="row row-cols-1 row-cols-sm-2 row-cols-md-4 g-4">
         <c:forEach var="space" items="${list}">
         <div class="col">
         
               <div class="card h-100" style="position:relative;">
                  <div class="card-img-top">
                  <a href="detail.do?space_num=${space.space_num}" style="display:block;">
                    <img src="imageView.do?space_num=${space.space_num}&space_type=1" width="100%" height="200">
                  </a>  
                  </div>
                  <div class="card-body">
                       <div class="color-gray">
                           <span class="red-heart" data-num="${space.space_num}">
                           <c:if test="${space.fav_num != 0}">
                           <i class="heart fa-regular fa-heart" style="font-weight:bold;"></i>
                           </c:if>
                           <c:if test="${space.fav_num == 0}">
                           <i class="heart fa-regular fa-heart"></i>
                           </c:if>
                           </span>
                          <span>${member.mem_nickname}</span>
                          <span class="card-hit"><i class="fa-solid fa-eye"></i> ${space.space_hit} <i class="fa-solid fa-heart"></i> <span class="fav_cnt">${space.fav_cnt}</span></span>
                       </div>
                       <a href="detail.do?space_num=${space.space_num}" style="display:block;">
                       <h5 class="card-title"><b>${space.space_name}</b></h5>
                       <span><i class="fa-regular fa-star"></i> ${space.star_avg} (후기 ${space.replycount})</span>
                       <p class="card-text">
                          <span>${space.space_address1}</span>
                          <br><b><fmt:formatNumber>${space.space_price}</fmt:formatNumber>원</b><span style="color:gray;"><b><fmt:formatNumber>${space.space_np} </fmt:formatNumber>인</b></span>
                       </p>
                       </a>
                  </div>
               </div>
         </div>
       </c:forEach>
	

	
      </div>


 



		<!-- ---------------- -->
	<div class="align-center float-clear">${page}</div>
	</c:if>
</div>
</div>
	<!-- 공간 목록 (관리자용) 끝 -->








