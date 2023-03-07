<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<link  rel="stylesheet" href="${pageContext.request.contextPath}/css/space.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css">

<script src="${pageContext.request.contextPath}/js/space.js"></script>
<script src="${pageContext.request.contextPath}/js/space.fav.js"></script>
<!-- 상품목록 시작 -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.js"></script>

<div class="page-main">
<div class="card mb-3">
  <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
  <div class="left">
  <div class="carousel-inner">
   <div class="img">
    <div class="carousel-item active">
      <img src="imageView.do?space_num=${space.space_num}&space_type=1" >
    </div>
    <div class="carousel-item">
      <img src="imageView.do?space_num=${space.space_num}&space_type=2">
    </div>
    <div class="carousel-item">
      <img src="imageView.do?space_num=${space.space_num}&space_type=3">
    </div>
    <div class="carousel-item">
      <img src="imageView.do?space_num=${space.space_num}&space_type=4">
    </div>
    
  </div>
  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
  
</div>

     <div class="image3">
  
		<span><img src="imageView.do?space_num=${space.space_num}&space_type=2" class="img-fluid rounded-start"></span>
        <span><img src="imageView.do?space_num=${space.space_num}&space_type=3" class="img-fluid rounded-start"></span>
        <span><img src="imageView.do?space_num=${space.space_num}&space_type=4" class="img-fluid rounded-start"></span>
 
    </div>
 </div>
   <div class="space-detail">
		<form id="space_cart" method="post">
		
			<input type="hidden" name="space_num"
			    value="${space.space_num}" id="space_num">
			<input type="hidden" name="space_price"
			    value="${space.space_price}" id="space_price">
			<input type="hidden" name="space_np"
			    value="${space.space_np}" id="space_np">    
			<input type="hidden" name="space_limit"
			    value="${space.space_limit}" id="space_limit"> 
  
        <h5 class="card-title">${space.space_name}</h5>
        <p class="card-text">${space.space_address1}</p>
        <p class="card-text">${space.space_date}</p>
        <hr class='hr-solid'/>
        <p class="card-text">날짜선택</p>
        <p class="card-text">인원 : <b><fmt:formatNumber> ${space.space_np} </fmt:formatNumber></b></p>
        <p class="card-text">공간 재고 : <b><fmt:formatNumber> ${space.space_limit} </fmt:formatNumber></b></p>
        
         <span id="red-heart-detail" data-num="${space.space_num}">
              <i class="heart fa-regular fa-heart"></i>
         </span>
        <span class="fav_cnt">${space.fav_cnt}</span>
		
        <c:if test="${space.space_limit > 0}">
				<li>
					<label for="order_space_limit">구매수량</label>
					<input type="number" name="order_space_limit"
					   min="1" max="${space.space_limit}" autocomplete="off"
					   id="order_space_limit" class="space_limit-width">
				</li>
			
					<li>
					<span id="space_total_txt">총주문 금액 : 0원</span>
				</li>
				
				<li>
					<input type="submit" value="예약하기">
				</li>
				</c:if>
				<c:if test="${space.space_limit <= 0}">
				<li class="align-center">
					<span class="sold-out">품절</span>
				</li>
				</c:if>
				
	
		</form>
		<hr size="1" noshade="noshade" width="100%">
        <p class="card-text"> 
        <b>${space.space_hit}</b>
        <br><b><fmt:formatNumber>${space.space_price}</fmt:formatNumber>원</b></p>
        <p class="card-text"><small class="text-muted">${space.mem_nickname}</small></p>
      </div>
    </div>
	</div>
	
    <!-- ----------------content--------------------- -->
    <nav id="navbar-example2" class="navbar bg-light px-3 mb-3">
 
  <ul class="nav nav-pills">
    <li class="nav-item">
      <a class="nav-link" href="#scrollspyHeading1">상세설명</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#scrollspyHeading2">후기</a>
    </li>
     <li class="nav-item">
      <a class="nav-link" href="#scrollspyHeading2">문의</a>
    </li>
      </ul>
</nav>
<div class="tab-content pt-2" id="myTabContent">
	          <div class="tab-pane fade show active" id="상세설명" role="tabpanel" aria-labelledby="home-tab">
	            <div class="card-write p-4" >
	              ${space.space_content}
	            </div>
	          </div>
	        </div>


</div><!-- page-main end -->
 
