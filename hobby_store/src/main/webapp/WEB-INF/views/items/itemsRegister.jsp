<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 중앙 컨텐츠 시작 -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<style>
.ck-editor__editable_inline {
	min-height: 250px;
}
</style>
<script src="${pageContext.request.contextPath}/js/ckeditor.js"></script>
<script src="${pageContext.request.contextPath}/js/uploadAdapter.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/js/items.js"></script>

<!--  중앙 컨텐츠 시작 -->
<div class="page-1">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<h1 class="align-center">상품 등록</h1>

				<form name="register_items" id="register_items"
					enctype="multipart/form-data" action="itemsRegister.do">
					<table class="table table-condensed table-bordered mt-4">
						<thead>
							<tr>
								<th colspan="2" class="align-center">
									<h3>Items Register</h3>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td width="20%"><b>카테고리</b></td>
								<td width="80%"><select name="cate_parent"
									id="category_parent">
										<option value="">카테고리</option>
										<c:forEach var="item" items="${items_cate}">
											<option value="${item.cate_num}">${item.cate_name}</option>

										</c:forEach>
								</select></td>
							</tr>
						</tbody>
					</table>
				</form>

				<form:form modelAttribute="itemsVO" name="register_items2"
					id="register_items2" enctype="multipart/form-data"
					action="itemsRegister2.do">
					<table class="table table-condensed table-bordered mt-4">
						<thead>
							<tr>
								<th colspan="2" class="align-center">
									<h3>Items Register2</h3>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td width="20%"><b>세부 카테고리</b></td>
								<td width="80%"><select name="cate_num" id="cate_child">
										<option id="child" value="">세부 카테고리</option>
								</select></td>
							</tr>
							<tr>
								<td width="20%">상품표시 여부</td>
								<td width="80%"><form:radiobutton path="status" value="1" />미표시
									<form:radiobutton path="status" value="2" />표시</td>
							</tr>
							<tr>
								<td width="20%">포장 가능 여부</td>
								<td width="80%">
									<form:radiobutton path="packaging" value="2" />가능 
									<form:radiobutton path="packaging" value="3" />불가능
								</td>
							</tr>
							<tr class="zipcode">
								<td>우편번호</td>
								<td id="zipcode"><form:input path="items_zipcode" /> <input
									type="button" onclick="execDaumPostcode()" value="우편번호찾기">
									<form:errors element="div" path="items_zipcode"
										cssClass="error-color" /></td>
							</tr>
							<tr class="address1">
								<td>주소</td>
								<td><form:input path="items_address1" /> <form:errors
										element="div" path="items_address1" cssClass="error-color" />
								</td>
							</tr>
							<tr class="address2">
								<td>상세주소</td>
								<td><form:input path="items_address2" /> <form:errors
										element="div" path="items_address2" cssClass="error-color" />
								</td>
							</tr>


							<tr>
								<td width="20%">상품명</td>
								<td width="80%"><form:input path="items_name" /> <form:errors
										path="items_name" cssClass="error-color" /></td>
							</tr>
							<tr>
								<td width="20%">상품가격</td>
								<td width="80%"><form:input path="items_price"
										type="number" /> <form:errors path="items_price"
										cssClass="error-color" /></td>
							</tr>
							<tr>
								<td width="20%">상품수량</td>
								<td width="80%"><form:input path="items_quantity"
										type="number" /> <form:errors path="items_quantity"
										cssClass="error-color" /></td>
							</tr>
							<tr>
									<td width="20%">상품사진1</td>
								<td width="80%">
								<label for = "upload1">
									<i class="fa-solid fa-circle-plus"></i><br>
								</label>
								<input type="file" name="upload1"
									id="upload1" style="display:none;" accept="image/gif,image/png,image/jpeg"> <%--(주의) upload1은 자바빈(vo)에 필드가 없기 때문에 명시하면 오류발생 --%>
									<form:errors path="items_photo1" cssClass="error-color" />
									<i class="fa-solid fa-circle-xmark d1"></i>
									<input type = "hidden" id = "delete_check1" name = "delete_check1" value =1 >
									<img id = "photo1" class = "photo1" src = "imageView.do?items_num=${itemsVO.items_num}&items_type=1" width = "50" alt ="선택한 사진">
									
									</td>
							</tr>
							<tr>
								<td width="20%">상품사진2</td>
								<td width="80%">
								<label for = "upload2">
									<i class="fa-solid fa-circle-plus"></i><br>
								</label>
								<input type="file" name="upload2"
									id="upload2" style="display:none;" accept="image/gif,image/png,image/jpeg"> <%--(주의) upload1은 자바빈(vo)에 필드가 없기 때문에 명시하면 오류발생 --%>
									<form:errors path="items_photo2" cssClass="error-color" /> 
									<i class="fa-solid fa-circle-xmark d2"></i>
									<input type = "hidden" id = "delete_check2" name = "delete_check2" value =1 >
									<img id = "photo2" class = "photo2" src = "imageView.do?items_num=${itemsVO.items_num}&items_type=2" width = "50" alt ="선택한 사진"></td>
							</tr>
							<tr>
								<td width="20%">상품사진3</td>
								<td width="80%">
								<label for = "upload3">
									<i class="fa-solid fa-circle-plus"></i><br>
								</label>
								<input type="file" name="upload3"
									id="upload3" style="display:none;" accept="image/gif,image/png,image/jpeg"> <%--(주의) upload1은 자바빈(vo)에 필드가 없기 때문에 명시하면 오류발생 --%>
									<form:errors path="items_photo3" cssClass="error-color" />
									<i class="fa-solid fa-circle-xmark d3"></i>
									<input type = "hidden" id = "delete_check3" name = "delete_check3" value =1 >
									<img id = "photo3" class = "photo3" src = "imageView.do?items_num=${itemsVO.items_num}&items_type=3" width = "50" alt ="선택한 사진"></td>
							</tr>
							<tr>
								<td width="20%">상세 설명</td>
								<td width="80%"><form:textarea path="items_content" /> <form:errors
										path="items_content" cssClass="error-color" /> <script>
								 function MyCustomUploadAdapterPlugin(editor) {
					    		 	editor.plugins.get('FileRepository').createUploadAdapter = (loader) =>{
					       			 return new UploadAdapter(loader);
					   			 	}
								}
								 ClassicEditor
						            .create( document.querySelector( '#items_content' ),{
						            	extraPlugins: [MyCustomUploadAdapterPlugin]
						            })
						            .then( editor => {
										window.editor = editor;
									} )
						            .catch( error => {
						                console.error( error );
						            } );
							</script></td>
							</tr>
						</tbody>
					</table>
					<input type="submit" value="전송">
					<input type="button" value="목록"
						onclick="location.href ='main/main.do'">
				</form:form>


			</div>
		</div>
	</div>
</div>
<!-- 우편번호 검색 시작 -->
<!-- iOS에서는 position:fixed 버그가 있음, 적용하는 사이트에 맞게 position:absolute 등을 이용하여 top,left값 조정 필요 -->
<div id="layer"
	style="display: none; position: fixed; overflow: hidden; z-index: 1; -webkit-overflow-scrolling: touch;">
	<img src="//t1.daumcdn.net/postcode/resource/images/close.png"
		id="btnCloseLayer"
		style="cursor: pointer; position: absolute; right: -3px; top: -3px; z-index: 1"
		onclick="closeDaumPostcode()" alt="닫기 버튼">
</div>

<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
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
                document.getElementById('items_zipcode').value = data.zonecode;
                //(수정) + extraAddr를 추가해서 address1에 참고항목이 보여지도록 수정
                document.getElementById("items_address1").value = addr + extraAddr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("items_address2").focus();

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

<!-- 중앙 컨텐츠 끝 -->