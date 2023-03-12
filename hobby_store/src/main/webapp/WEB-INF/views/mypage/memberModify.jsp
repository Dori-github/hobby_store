<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 회원정보 변경 시작 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

  <style>
    body {
      min-height: 100vh;
    }

    .input-form {
      max-width: 680px;

      margin-top: 80px;
      padding: 32px;

      background: #fff;
      -webkit-border-radius: 10px;
      -moz-border-radius: 10px;
      border-radius: 10px;
      
      -webkit-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
      -moz-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
      box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
      
    }
  </style>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/mypage.js"></script>
<div id="content">
<!--<form:form action="update.do" id="modify_form"
	                       modelAttribute="memberVO">
		<form:errors element="div" cssClass="error-color"/>                          
		<ul>
			<li>
				<img src="${pageContext.request.contextPath}/member/photoView.do" width="200" 
				           height="200" class="my-photo">
			</li>
			<li>
				<div class="align-center">
					<input type="button" value="수정" id="photo_btn">
				</div>
				<div id="photo_choice" style="display:none;">
					<input type="file" id="upload" 
					         accept="image/gif,image/png,image/jpeg">
					<input type="button" value="전송" id="photo_submit">
					<input type="button" value="취소" id="photo_reset">         
				</div>
			</li>
			<li>
				<div class="row">
					<div class="col-md-6 mb-3">
						<label for="mem_name">닉네임</label>
					</div>
					<div class="col-auto">
						<input type="text" id="mem_name" class="form-control">
						<form:errors path="mem_name" cssClass="error-color"/>
					</div>
				</div>
			</li>
			<li>
				<div class="row">
					<div class="col-auto">
						<label for="password2">Password</label>
					</div>
					<div class="col-auto">
						<input type="password" id="password2" class="form-control" disabled>
					</div>
				</div>
			</li>
			<li>
				<div class="row">
					<div class="col-auto">
						<label for="mem_nickname">닉네임</label>
					</div>
					<div class="col-auto">
						<input type="text" id="mem_nickname" class="form-control">
						<form:errors path="mem_nickname" cssClass="error-color"/>
					</div>
				</div>
			</li>
			<li>
				<label for="mem_cell">전화번호</label>
				<form:input path="mem_cell"/>
				<form:errors path="mem_cell" cssClass="error-color"/>
			</li>
			<li>
				<label for="mem_email">이메일</label>
				<form:input path="mem_email"/>
				<form:errors path="mem_email" cssClass="error-color"/>
			</li>
			<li>
				<label for="mem_zipcode">우편번호</label>
				<form:input path="mem_zipcode"/>
				<input type="button" 
				    onclick="execDaumPostcode()" value="우편번호찾기">
				<form:errors path="mem_zipcode" cssClass="error-color"/>
			</li>
			<li>
				<label for="mem_address1">주소</label>
				<form:input path="mem_address1"/>
				<form:errors path="mem_address1" cssClass="error-color"/>
			</li>
			<li>
				<label for="mem_address2">상세주소</label>
				<form:input path="mem_address2"/>
				<form:errors path="mem_address2" cssClass="error-color"/>
			</li>
		</ul>
		<div class="align-center">
			<form:button>수정</form:button>
			<input type="button" value="My페이지"
			   onclick="location.href='myPage.do'">
		</div>
	</form:form>-->
	
 <div class="container">
    <div class="input-form-backgroud row">
      <div class="input-form col-md-12 mx-auto">
        <h4 class="mb-4 align-center">회원정보 수정</h4>
        <div class="align-center">
        <img src="${pageContext.request.contextPath}/member/photoView.do" width="200" 
				           height="200" class="my-photo">
		</div>
		<div class="align-center">
		<input type="button" value="수정" id="photo_btn" class="btn btn-primary mypage-btn btn-sm" style="background-color:#FF4E02; color:white; border:none;">
		</div>
		<div id="photo_choice" style="display:none;">
		<div class="input-group">
			<input type="file" id="upload" class="form-control"
			         accept="image/gif,image/png,image/jpeg">
			<input type="button" value="전송" id="photo_submit">
			<input type="button" value="취소" id="photo_reset"> 
		</div>        
		</div>
        <form:form  action="update.do" id="modify_form" modelAttribute="memberVO" class="validation-form">
          <div class="row">
            <div class="col-md-6 mb-3">
              <label for="mem_name">이름</label>
              <form:input path="mem_name" class="form-control" id="mem_name"/>
              <div class="invalid-feedback">
                이름을 입력해주세요.
              </div>
            </div>
            <div class="col-md-6 mb-3">
              <label for="mem_nickname">닉네임</label>
               <form:input path="mem_nickname" class="form-control" id="mem_name" placeholder=""/>
              <div class="invalid-feedback">
                별명을 입력해주세요.
              </div>
            </div>
          </div>
          
          <div class="mb-3">
            <label for="mem_pw">비밀번호</label>
            <input type="password" name="mem_pw" class="form-control" id="mem_pw" value="0000" disabled />
            <input type="button" value="비밀번호 수정" onclick="location.href='changePassword.do'"  class="btn btn-primary mypage-btn btn-sm" style="background-color:#FF4E02; color:white; border:none;">
          </div>
          
          <div class="mb-3">
            <label for="mem_cell">전화번호</label>
            <form:input path="mem_cell" class="form-control" id="mem_cell" placeholder="000-0000-0000" />
            <div class="invalid-feedback">
              전화번호를 입력해주세요.
            </div>
          </div>

          <div class="mb-3">
            <label for="mem_email">이메일</label>
            <form:input path="mem_email" class="form-control" id="mem_email" placeholder="you@example.com" />
            <div class="invalid-feedback">
              이메일을 입력해주세요.
            </div>
          </div>

		  <div class="mb-3">
            <label for="mem_zipcode">우편번호</label>
            <form:input path="mem_zipcode" class="form-control" id="mem_zipcode"/>
            <input type="button" class="btn btn-primary mypage-btn btn-sm" style="background-color:#FF4E02; color:white; border:none;"
				    onclick="execDaumPostcode()" value="우편번호찾기">
            <div class="invalid-feedback">
              우편번호를 입력해주세요.
            </div>
          </div>

          <div class="mb-3">
            <label for="mem_address1">주소</label>
            <form:input path="mem_address1" class="form-control" id="mem_address1" placeholder="서울특별시 강남구"/>
            <div class="invalid-feedback">
              주소를 입력해주세요.
            </div>
          </div>

          <div class="mb-3">
            <label for="mem_address2">상세주소</label>
            <form:input path="mem_address2" class="form-control" id="mem_address2" placeholder="상세주소를 입력해주세요."/>
          	<div class="invalid-feedback">
              상세주소를 입력해주세요.
            </div>
          </div>

       <!-- <div class="row">
            <div class="col-md-8 mb-3">
              <label for="root">선호지역</label>
              <select class="custom-select d-block w-100" id="root">
                <option value=""></option>
              </select>
              <div class="invalid-feedback">
                선호 지역을 선택해주세요.
              </div>
            </div>
            <div class="col-md-4 mb-3">
              <label for="code">관심사</label>
              <input type="text" class="form-control" id="code" placeholder="" required>
              <div class="invalid-feedback">
                관심사를 입력해주세요.
              </div>
            </div>
          </div>-->
          <hr class="mb-4">
          <div class="mb-4 align-center">
          <form:button class="btn btn-primary mypage-btn" type="submit" style="background-color:#FF4E02; color:white; border:none;">수정 완료</form:button>
          <input class="btn btn-primary mypage-btn" type="button" value="목록으로" onclick="location.href='myPage.do'" style="background-color:#FF4E02; color:white; border:none;">
          </div>
        </form:form>
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
                document.getElementById('mem_zipcode').value = data.zonecode;
                //(수정) + extraAddr를 추가해서 address1에 참고항목이 보여지도록 수정
                document.getElementById("mem_address1").value = addr + extraAddr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("mem_address2").focus();

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
<!-- 회원정보 변경 끝 -->