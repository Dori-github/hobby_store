<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/space/jquery.timepicker.css">
<link href="${pageContext.request.contextPath}/css/space.css" rel="stylesheet"> 
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"><!-- 새로넣은거 -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<style>
.ck-editor__editable_inline {
   min-height: 250px;
}
</style>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ckeditor.js"></script>
<script src="${pageContext.request.contextPath}/js/uploadAdapter.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.timepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/js/space.js"></script>

<!--  중앙 컨텐츠 시작 -->
<div id=space_form>
    <div class="title">공간수정</div>
            <form:form action="admin_modify.do" modelAttribute="spaceVO" name="admin_modify"
               id="admin_modify" enctype="multipart/form-data">
             <form:hidden path="space_num"/>               
		      <form:errors element="div" cssClass="error-color"/>
	                  <table class="reg-form">
               
                  <tbody>
                  
    
                        <td><b>카테고리</b></td>
                        <div class="list-box">
			            <ul class="list-cate">
                        <td><select class="form-select-lg" aria-label="Default select example" name="cate_num" id="cate_parent">
                              <option value="">카테고리</option>
                              <option value="1">촬영 스튜디오</option>
                              <option value="2">댄스공간</option>
                              <option value="3">체육시설</option>
                              <option value="4">악기 연습실</option>
                              <option value="5">공연장</option>
                              <option value="6">그외</option>
                       </select>
                        </td>
    
                    	
                     </ul>
                     </div>
                     <tr>
			<td>공간명</td>
			<td><form:input path="space_name"/></td>
			<form:errors element="div" path="space_name" cssClass="error-color"/>
		      </tr>
                    
                     <tr>
			<td>우편번호</td >
			<td id="zipcode">
				<form:input path="space_zipcode"/>
				<input type="button" onclick="execDaumPostcode()" value="우편번호찾기">
				<form:errors element="div" path="space_zipcode" cssClass="error-color"/>
			</td>
		</tr>
                         <tr>
                          <td width="20%"><b>상세주소</b></td>
                            <td width="80%">
                            <form:input path="space_address1"/>
                             <form:errors path="space_address1" cssClass="error-color"/>
                          </td>
                          </tr>
                           <tr>
                          <td width="20%"><b>상세주소</b></td>
                            <td width="80%">
                            <form:input path="space_address2"/>
                             <form:errors path="space_address2" cssClass="error-color"/>
                          </td>
                          </tr>

                    
             <tr id="price">
			<td>가격</td>
			<!-- 자바빈에 데이터가 없으면 int값은 자동으로 0값을 가짐 -->
			<td>
			<form:hidden path="space_price"/>
			<input id="space_vprice"/>
			 원</td>
			<form:errors element="div" path="space_price" cssClass="error-color"/>
		</tr>
                     <tr class="limit" style="display:none;">
			<tr>
                        <td>인원수</td>
                        <td>
                        <form:input path="space_np" type="number"/>
                         <form:errors path="space_np" cssClass="error-color"/>
                        </td>
                     </tr>
                     <tr>
                        <td>공간수</td>
                        <td>
                        <form:input path="space_limit" type="number"/>
            <form:errors path="space_limit" 
                              cssClass="error-color"/>
                        </td>
                     </tr>
                     <tr class="image2">
                        <td width="20%">상품사진</td>
                        <td width="80%">
                         <div class="upload-space">
               <img src="imageView.do?space_num=${spaceVO.space_num}&space_type=1" width="70" height="70" id="photo" class="space-space_photo">
               <input type="file" name="upload" id="upload" 
                      accept="image/gif,image/png,image/jpeg">
                <%--(주의) upload1은 자바빈(vo)에 필드가 없기 때문에 명시하면 오류발생 --%>      
                <form:errors path="space_photo" cssClass="error-color"/>     
            </div>

                        </td>
                     </tr>
                     <tr class="image2">
                        <td width="20%">상품사진1</td>
                        <td width="80%">
                        <div class="upload-space">
               <img src="imageView.do?space_num=${spaceVO.space_num}&space_type=2" width="70" height="70" id="photo1" class="space-space_photo1">
               <input type="file" name="upload1" id="upload1" 
                      accept="image/gif,image/png,image/jpeg">
                <%--(주의) upload1은 자바빈(vo)에 필드가 없기 때문에 명시하면 오류발생 --%>      
                <form:errors path="space_photo1" cssClass="error-color"/>     
            </div>
  
                        </td>
                     </tr>

                     <tr class="image2">
                        <td width="20%">상품사진2</td>
                        <td width="80%">
                        <div class="upload-space">
               <img src="imageView.do?space_num=${spaceVO.space_num}&space_type=3" width="70" height="70" id="photo2" class="space-space_photo2">
               <input type="file" name="upload2" id="upload2" 
                      accept="image/gif,image/png,image/jpeg">
                <%--(주의) upload1은 자바빈(vo)에 필드가 없기 때문에 명시하면 오류발생 --%>      
                <form:errors path="space_photo2" cssClass="error-color"/>     
            </div>  
                        </td>
                     </tr>

                     <tr>
                     <td width="20%">공간설명</td>   
                     <td width="80%">
                        <form:textarea path="space_content"/>
            <form:errors path="space_content"
                              cssClass="error-color"/>
                     <script>
                      function MyCustomUploadAdapterPlugin(editor) {
                            editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
                                return new UploadAdapter(loader);
                            }
                        }
                      
                      ClassicEditor
                           .create( document.querySelector( '#space_content' ),{
                              extraPlugins: [MyCustomUploadAdapterPlugin]
                           })
                           .then( editor => {
                           window.editor = editor;
                        } )
                           .catch( error => {
                               console.error( error );
                           } );
                     </script>                   
                     </td>
                     </tr>   
               </table>
	<div class="align-center margin-top">
		<form:button class="form-btn">등록</form:button>
		<input type="button" value="홈으로" class="form-btn" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
	</div>
            </form:form>   

</div>

      <!-- 우편번호 검색 시작 -->
                           <!-- iOS에서는 position:fixed 버그가 있음, 적용하는 사이트에 맞게 position:absolute 등을 이용하여 top,left값 조정 필요 -->
                           <div id="layer"
                              style="display: none; position: fixed; overflow: hidden; z-index: 998; -webkit-overflow-scrolling: touch;">
                              <img src="//t1.daumcdn.net/postcode/resource/images/close.png"
                                 id="btnCloseLayer"
                                 style="cursor: pointer; position: absolute; right: -3px; top: -3px; z-index: 1"
                                 onclick="closeDaumPostcode()" alt="닫기 버튼">
                           </div>

                           <script
                              src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
                           <script>

                              // 우편번호 찾기 화면을 넣을 element
                              var element_layer = document
                                    .getElementById('layer');

                              function closeDaumPostcode() {
                                 // iframe을 넣은 element를 안보이게 한다.
                                 element_layer.style.display = 'none';
                              }

                              function execDaumPostcode() {
                                 new daum.Postcode(
                                       {
                                          oncomplete : function(
                                                data) {
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
                                             if (data.userSelectedType === 'R') {
                                                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                                                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                                                if (data.bname !== ''
                                                      && /[동|로|가]$/g
                                                            .test(data.bname)) {
                                                   extraAddr += data.bname;
                                                }
                                                // 건물명이 있고, 공동주택일 경우 추가한다.
                                                if (data.buildingName !== ''
                                                      && data.apartment === 'Y') {
                                                   extraAddr += (extraAddr !== '' ? ', '
                                                         + data.buildingName
                                                         : data.buildingName);
                                                }
                                                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                                                if (extraAddr !== '') {
                                                   extraAddr = ' ('
                                                         + extraAddr
                                                         + ')';
                                                }
                                                //(주의)address1에 참고항목이 보여지도록 수정
                                                // 조합된 참고항목을 해당 필드에 넣는다.
                                                //(수정) document.getElementById("address2").value = extraAddr;

                                             }
                                             //(수정) else {
                                             //(수정)    document.getElementById("address2").value = '';
                                             //(수정) }

                                             // 우편번호와 주소 정보를 해당 필드에 넣는다.
                                             document
                                                   .getElementById('space_zipcode').value = data.zonecode;
                                             //(수정) + extraAddr를 추가해서 address1에 참고항목이 보여지도록 수정
                                             document
                                                   .getElementById("space_address1").value = addr
                                                   + extraAddr;
                                             // 커서를 상세주소 필드로 이동한다.
                                             document
                                                   .getElementById(
                                                         "space_address2")
                                                   .focus();

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
                              function initLayerPosition() {
                                 var width = 300; //우편번호서비스가 들어갈 element의 width
                                 var height = 400; //우편번호서비스가 들어갈 element의 height
                                 var borderWidth = 5; //샘플에서 사용하는 border의 두께

                                 // 위에서 선언한 값들을 실제 element에 넣는다.
                                 element_layer.style.width = width
                                       + 'px';
                                 element_layer.style.height = height
                                       + 'px';
                                 element_layer.style.border = borderWidth
                                       + 'px solid';
                                 // 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
                                 element_layer.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width) / 2 - borderWidth)
                                       + 'px';
                                 element_layer.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height) / 2 - borderWidth)
                                       + 'px';
                              }
                           </script>

                           <!-- 우편번호 검색 끝 -->