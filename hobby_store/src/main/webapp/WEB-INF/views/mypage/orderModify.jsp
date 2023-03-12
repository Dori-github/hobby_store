<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   
<!-- 주문정보수정 시작 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<style>
  body {
    min-height: 100vh;
  }
</style>
<script type="text/javascript">
$(function(){
	$('#order_modify').submit(function(){
		if($('#receive_name').val().trim()==''){
			alert('받는 사람을 입력하세요!');
			$('#receive_name').val('').focus();
			return false;
		}
		if($('#zipcode').val().trim()==''){
			alert('우편번호를 입력하세요');
			$('#zipcode').val('').focus();
			return false;
		}
		if($('#address1').val().trim()==''){
			alert('주소를 입력하세요');
			$('#address1').val('').focus();
			return false;
		}
		if($('#address2').val().trim()==''){
			alert('상세주소를 입력하세요');
			$('#address2').val('').focus();
			return false;
		}
		if($('#receive_phone').val().trim()==''){
			alert('전화번호를 입력하세요');
			$('#receive_phone').val('').focus();
			return false;
		}
	});
});
</script>
<div id="content">
	<h2>구매내역</h2>
	<table class="table table-group-divider align-center">
		<tr>
			<th>상품명</th>
			<th>수량</th>
			<th>가격</th>
			<th>합계</th>
		</tr>
		<c:forEach var="detail" items="${detailList}">
		<tr>
			<td>${detail.detail_name}</td>
			<td class="align-center"><fmt:formatNumber value="${detail.quantity}"/></td>
			<td class="align-center"><fmt:formatNumber value="${detail.price}"/>원</td>
			<td class="align-center"><fmt:formatNumber value="${detail.price*detail.quantity}"/>원</td>
		</tr>                
		</c:forEach>
		<tr>
			<td colspan="3" class="align-right"><b>총구매금액</b></td>
			<td class="align-center"><fmt:formatNumber value="${order.order_price}"/>원</td>
		</tr>
	</table>
	
<div class="container">
    <div class="input-form-backgroud row">
      <div class="input-form col-md-12 mx-auto">
        <h4 class="mb-4 align-left">배송정보 수정</h4>
        <div class="align-left">
        <form:form  action="orderModify.do" id="change_form" modelAttribute="orderVO" class="validation-form">
	    <input type="hidden" name="order_num" value="${orderVO.order_num}">
	    <input type="hidden" name="order_status" value="${orderVO.order_status}">
	    <input type="hidden" name="refund_status" value="${orderVO.refund_status}">                                             
		    <c:if test="${orderVO.order_status < 1 && orderVO.refund_status==null}">
			<!-- <li>
				<label for="receive_name">받는 사람</label>
				<input type="text" name="receive_name" value="${order.receive_name}"
				       id="receive_name" maxlength="10">       
			</li>
			<li>
				<label for="zipcode">우편번호</label>
				<input type="text" name="receive_post" value="${order.receive_post}"
				       id="zipcode" maxlength="5">
				<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"> 
				                        
			</li>
			<li>
				<label for="address1">주소</label>
				<input type="text" name="receive_address1" value="${order.receive_address1}"
				       id="address1" maxlength="30">       
			</li>
			<li>
				<label for="address2">상세주소</label>
				<input type="text" name="receive_address2" value="${order.receive_address2}"
				       id="address2" maxlength="30">       
			</li>
			<li>
				<label for="receive_phone">전화번호</label>
				<input type="text" name="receive_phone" value="${order.receive_phone}"
				       id="receive_phone" maxlength="15">       
			</li>
			<li>
				<label for="notice">배송메시지</label>
				<input type="text" name="notice" value="${order.notice}" 
					id="notice" maxlength="300">       
			</li> -->
			
			<div class="mb-3">
            <label for="receive_name">수취인</label>
            <form:input path="receive_name" class="form-control" id="receive_name" />
            <div class="invalid-feedback">
              수취인을 입력해주세요.
            </div>
          </div>

		  <div class="mb-3">
            <label for="receive_post">우편번호</label>
            <form:input path="receive_post" class="form-control" id="receive_post"/>
            <input type="button" class="btn btn-primary mypage-btn btn-sm" style="background-color:#FF4E02; color:white; border:none;"
				    onclick="execDaumPostcode()" value="우편번호찾기">
            <div class="invalid-feedback">
              우편번호를 입력해주세요.
            </div>
          </div>

          <div class="mb-3">
            <label for="receive_address1">주소</label>
            <form:input path="receive_address1" class="form-control" id="receive_address1" placeholder="ex)서울특별시 강남구"/>
            <div class="invalid-feedback">
              주소를 입력해주세요.
            </div>
          </div>

          <div class="mb-3">
            <label for="receive_address2">상세주소</label>
            <form:input path="receive_address2" class="form-control" id="receive_address2" placeholder="ex)1000동 1000호"/>
          	<div class="invalid-feedback">
              상세주소를 입력해주세요.
            </div>
          </div>
			
		  <div class="mb-3">
            <label for="receive_phone">전화번호</label>
            <form:input path="receive_phone" class="form-control" id="receive_phone" placeholder="000-0000-0000" />
            <div class="invalid-feedback">
              전화번호를 입력해주세요.
            </div>
          </div>
          
          <div class="mb-3">
            <label for="notice">배송 메모</label>
            <form:input path="notice" class="form-control" id="notice" />
          </div>
          
			</c:if>
			<c:if test="${orderVO.order_status >= 2 || orderVO.refund_status!=null}">
			<div class="card" style="width:43rem; cursor:default">
			<div class="card-body">
				<h4 class="cart-title">배송정보</h4>
				<p class="card-text">받는 사람 : ${orderVO.receive_name}</p>
				<p class="card-text">우편번호 : ${orderVO.receive_post}</p>
				<p class="card-text">주소 : ${orderVO.receive_address1}</p>
				<p class="card-text">상세주소 : ${orderVO.receive_address2}</p>
				<p class="card-text">전화번호 : ${orderVO.receive_phone}</p>
				<p class="card-text">남기실 말씀 : ${orderVO.notice}</p>
			</div>
			</div>
			<!-- <li>
				<label>받는 사람</label>
				${order.receive_name}
			</li>
			<li>
				<label>우편번호</label>
				${order.receive_post}
			</li>
			<li>
				<label>주소</label>
				${order.receive_address1}
			</li>
			<li>
				<label>상세주소</label>
				${order.receive_address2}
			</li>
			<li>
				<label>전화번호</label>
				${order.receive_phone}
			</li>
			<li>
				<label>남기실 말씀</label>
				${order.notice}
			</li> -->
			</c:if>
			<c:if test="${orderVO.order_status == 1}">
			<h4>예약완료</h4>
			</c:if>
				<label>배송상태 :</label>
				<c:if test="${orderVO.refund_status==null}">
				<c:if test="${orderVO.order_status==0}">구매완료</c:if>
				<c:if test="${orderVO.order_status==1}">예약완료</c:if>
				<c:if test="${orderVO.order_status==2}">배송준비중</c:if>
				<c:if test="${orderVO.order_status==3}">배송중</c:if>
				<c:if test="${orderVO.order_status==4}">배송완료</c:if>
				<c:if test="${orderVO.order_status==5}">주문취소</c:if>
				</c:if>
				<c:if test="${orderVO.refund_status==0}">환불요청중</c:if>
				<c:if test="${orderVO.refund_status==1}">환불완료</c:if>
		<div class="align-center">
		<c:if test="${orderVO.order_status<2 && orderVO.refund_status==null}">
			<form:button class="btn btn-primary btn-sm mypage-btn" style="background-color:#FF4E02; color:white; border:none;">배송지 수정</form:button>
			<input type="button" value="주문취소" id="order_cancel">
			<script>
				let order_cancel = document.getElementById('order_cancel');
				order_cancel.onclick=function(){
					let choice = confirm('주문을 취소하겠습니까?');
					if(choice){
						location.replace('orderCancel.do?order_num=${order.order_num}');
					}
				};
			</script>
		</c:if>
			<input class="btn btn-primary btn-sm mypage-btn" style="background-color:#FF4E02; color:white; border:none;" type="button" value="목록" onclick="location.href='order.do'">
			<input class="btn btn-primary btn-sm mypage-btn" style="background-color:#FF4E02; color:white; border:none;" type="button" value="MyPage" onclick="location.href='myPage.do'">       
		</div>
	</form:form>
        
      </div>
    </div>
    <footer class="my-3 text-center text-small">
      <p class="mb-1">&copy; 2021 YD</p>
    </footer>
  </div>
</div>
</div>
<script>
    window.addEventListener('load', () => {
      const forms = document.getElementsByClassName('validation-form');

      Array.prototype.filter.call(forms, (form) => {
        form.addEventListener('submit', function (event) {
          if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
          }

          form.classList.add('was-validated');
        }, false);
      });
    }, false);
  </script>
<!-- 우편번호 검색 시작 -->
<!-- iOS에서는 position:fixed 버그가 있음, 적용하는 사이트에 맞게 position:absolute 등을 이용하여 top,left값 조정 필요 -->
<div id="layer" style="display:none;position:fixed;overflow:hidden;z-index:1;-webkit-overflow-scrolling:touch;">
<img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnCloseLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px;z-index:1" onclick="closeDaumPostcode()" alt="닫기 버튼">
</div>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    // 우편번호 찾기 화면을 넣을 element
    var element_layer = document.getElementById('layer');

    function closeDaumPostcode() {
        // iframe을 넣은 element를 안보이게 한다.
        element_layer.style.display = 'none';
    }

    function execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    //(주의)address1에 참고항목이 보여지도록 수정
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    //(수정) document.getElementById("address2").value = extraAddr;
                
                } 
                //(수정) else {
                //(수정)    document.getElementById("address2").value = '';
                //(수정) }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('receive_post').value = data.zonecode;
                //(수정) + extraAddr를 추가해서 address1에 참고항목이 보여지도록 수정
                document.getElementById("receive_address1").value = addr + extraAddr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("receive_address2").focus();

                // iframe을 넣은 element를 안보이게 한다.
                // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
                element_layer.style.display = 'none';
            },
            width : '100%',
            height : '100%',
            maxSuggestItems : 5
        }).embed(element_layer);

        // iframe을 넣은 element를 보이게 한다.
        element_layer.style.display = 'block';

        // iframe을 넣은 element의 위치를 화면의 가운데로 이동시킨다.
        initLayerPosition();
    }

    // 브라우저의 크기 변경에 따라 레이어를 가운데로 이동시키고자 하실때에는
    // resize이벤트나, orientationchange이벤트를 이용하여 값이 변경될때마다 아래 함수를 실행 시켜 주시거나,
    // 직접 element_layer의 top,left값을 수정해 주시면 됩니다.
    function initLayerPosition(){
        var width = 300; //우편번호서비스가 들어갈 element의 width
        var height = 400; //우편번호서비스가 들어갈 element의 height
        var borderWidth = 5; //샘플에서 사용하는 border의 두께

        // 위에서 선언한 값들을 실제 element에 넣는다.
        element_layer.style.width = width + 'px';
        element_layer.style.height = height + 'px';
        element_layer.style.border = borderWidth + 'px solid';
        // 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
        element_layer.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width)/2 - borderWidth) + 'px';
        element_layer.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height)/2 - borderWidth) + 'px';
    }
</script>
<!-- 우편번호 검색 끝 -->
<!-- 주문정보수정 끝 -->