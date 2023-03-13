<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.timepicker.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link href="${pageContext.request.contextPath}/css/course.css" rel="stylesheet"> 
<style>
.ck-editor__editable_inline{
	min-height:250px;
}
</style>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.timepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ckeditor.js"></script>
<script src="${pageContext.request.contextPath}/js/course.js"></script>
<script type="text/javascript">
	$(function(){
		//카테고리 상세
		let cate_parent = new Array();
		let cate_name = new Array();

		<c:forEach var="cate" items="${course_cate}">
		cate_parent.push("${cate.cate_parent}");
		cate_name.push("${cate.cate_name}");
		</c:forEach>
		 
		let list = $('#course_form .list-cate li');

		list.on('click',function(){
			$('#course_form .list-cate2').empty();
			let output='';
			for(let i=0;i<cate_parent.length;i++){
				if(cate_parent[i]==$('#course_form #cate_parent').val()){
					output += '<li>'+cate_name[i]+'</li>';
				}
			}
			$('#course_form .list-cate2').append(output);
		});
		
		
		//정기클래스 시작요일
		$.datepicker.setDefaults({
	        dateFormat: 'yy-mm-dd',
	        prevText: '이전 달',
	        nextText: '다음 달',
	        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
	        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
	        dayNames: ['일', '월', '화', '수', '목', '금', '토'],
	        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
	        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
	        showMonthAfterYear: true,
	        yearSuffix: '년'
	    });

	    $("#course_startDate").datepicker();
		
	});		
</script>
<!-- 중앙 컨텐츠 시작 -->
<form:form action="/course/courseWrite.do" id="course_form" class="course-mform" modelAttribute="courseVO" enctype="multipart/form-data">
	<form:hidden path="course_num"/>
    <div id="course_mc" style="display:none;">
    	<input type="hidden" id="course_month" name="course_month" value="0">
		<input type="hidden" id="course_count" name="course_count" value="0"/>
    </div>
	<div class="title">클래스 등록</div>
	<table class="reg-form">
		<tr>
			<td>온라인 / 오프라인</td>
			<td class="radio">
				<label>
					<form:radiobutton path="course_onoff" value="on" />온라인 <!-- 기본값 checked="checked" -->
				</label>
				<label>
					<form:radiobutton path="course_onoff" value="off"/>오프라인
				</label>
				<form:errors element="div" path="course_onoff" cssClass="error-color"/>
			</td>
		</tr>
		<tr class="radio oneweek" style="display:none;">
			<td>원데이 / 정기</td>
			<td>
				<label for="course_oneweek1"> 
					<form:radiobutton  path="course_oneweek" value="one"/>원데이
				</label>
				<label for="course_oneweek2">
					<form:radiobutton  path="course_oneweek" value="week"/>정기
				</label>
			</td>
		</tr>
		<tr>
			<td>카테고리 분류</td>
			<td>
				<form:input path="cate_parent" type="number" hidden="hidden"/>
				
				<c:forEach var="cate" items="${course_cate}">
					<c:if test="${fn:substring(courseVO.cate_nums,0,1) == cate.cate_num}">
						<div class="btn-select"><span class="whole">${cate.cate_name}</span>
							<i class="fa-solid fa-chevron-down icon" style="float: right;font-size:15px;"></i>
							<i class="fa-solid fa-chevron-up icon" style="float: right;font-size:15px;display:none;"></i>
						</div>
					</c:if>
				</c:forEach>
				<!-- 				
				<div class="btn-select"><span class="whole">전체</span>
					<i class="fa-solid fa-chevron-down icon" style="float: right;padding-bottom:5px;font-size:15px;"></i>
					<i class="fa-solid fa-chevron-up icon" style="float: right;font-size:15px;display:none;"></i>
				</div>
				 -->
				
				<div class="list-box">
			        <ul class="list-cate">
			            <li data-value="1">공예</li>
			            <li data-value="2">쿠킹</li>
			            <li data-value="3">디자인</li>
			            <li data-value="4">운동</li>
			            <li data-value="5">뷰티</li>
			            <li data-value="6">기타</li>
			        </ul>
		        </div>
				<form:errors element="div" path="cate_parent" cssClass="error-color"/>
			</td>
		</tr>
		<tr id="d_cate">
			<td>카테고리 상세</td>
			<td>
				<form:input path="cate_name" type="text" hidden="hidden"/>
				<c:forEach var="cate" items="${course_cate}">
					<c:if test="${courseVO.cate_nums.substring(2)==cate.cate_num}">
						<div class="btn-select2"><span class="whole2">${cate.cate_name}</span>
							<i class="fa-solid fa-chevron-down icon2" style="float: right;font-size:15px;"></i>
							<i class="fa-solid fa-chevron-up icon2" style="float: right;font-size:15px;display:none;"></i>
						</div>
					</c:if>
				</c:forEach>
				<div class="list-box2">
			        <ul class="list-cate2"></ul>
		        </div>
				<form:errors element="div" path="cate_name" cssClass="error-color"/>
			</td>
		</tr>
		<tr>
			<td>클래스명</td>
			<td><form:input path="course_name"/></td>
			<form:errors element="div" path="course_name" cssClass="error-color"/>
		</tr>
		<tr id="datetime" style="display:none;">
			<td>클래스 요일/시간</td>
			<td>
				<i class="fa-regular fa-calendar-check" style="margin-top:7px;margin-bottom:10px;font-size:15pt;"></i> 요일 선택
				<table>
					<tr>
						<th>
							<input type="checkbox" name="courseTimeVO[0].course_reg_date" id="mon" value="월"/>
							<label for="mon"><i class="fa-solid fa-check"></i> 월</label>
						</th>
						<td>
							<input type="text" name="courseTimeVO[0].course_reg_times" class="time-choice time1" placeholder="시간 선택" autocomplete="off">
							<span>추가</span>
						</td>					
					</tr>
					<tr>
						<th>
							<input type="checkbox" name="courseTimeVO[1].course_reg_date" data-num="2" id="tues" value="화"/>
							<label for="tues"><i class="fa-solid fa-check"></i> 화</label>
						</th>
						<td>
							<input type="text" name="courseTimeVO[1].course_reg_times" class="time-choice time2" placeholder="시간 선택">
							<span>추가</span>
						</td>					
					</tr>
					<tr>
						<th>
							<input type="checkbox" name="courseTimeVO[2].course_reg_date" id="wed" value="수"/>
							<label for="wed"><i class="fa-solid fa-check"></i> 수</label>
						</th>
						<td>
							<input type="text" name="courseTimeVO[2].course_reg_times" class="time-choice time3"  placeholder="시간 선택">
							<span>추가</span>
						</td>					
					</tr>
					<tr>
						<th>
							<input type="checkbox" name="courseTimeVO[3].course_reg_date" id="thur" value="목"/>
							<label for="thur"><i class="fa-solid fa-check"></i> 목</label>
						</th>
						<td>
							<input type="text" name="courseTimeVO[3].course_reg_times" class="time-choice time4" placeholder="시간 선택">
							<span>추가</span>
						</td>					
					</tr>
					<tr>
						<th>
							<input type="checkbox" name="courseTimeVO[4].course_reg_date" id="fri" value="금"/>
							<label for="fri"><i class="fa-solid fa-check"></i> 금</label>
						</th>
						<td>
							<input type="text" name="courseTimeVO[4].course_reg_times" class="time-choice time5" placeholder="시간 선택">
							<span>추가</span>
						</td>					
					</tr>
					<tr>
						<th>
							<input type="checkbox" name="courseTimeVO[5].course_reg_date" id="sat" value="토"/>
							<label for="sat"><i class="fa-solid fa-check"></i> 토</label>
						</th>
						<td>
							<input type="text" name="courseTimeVO[5].course_reg_times" class="time-choice time6" placeholder="시간 선택">
							<span>추가</span>
						</td>					
					</tr>
					<tr>
						<th>
							<input type="checkbox" name="courseTimeVO[6].course_reg_date" id="sun" value="일"/>
							<label for="sun"><i class="fa-solid fa-check"></i> 일</label>
						</th>
						<td>
							<input type="text" name="courseTimeVO[6].course_reg_times" class="time-choice time7 t1" placeholder="시간 선택">
							<span>추가</span>
						</td>					
					</tr>
				</table>
			</td>
			<form:errors element="div" path="course_reg_date" cssClass="error-color"/>
			<form:errors element="div" path="course_reg_time" cssClass="error-color"/>
		</tr>
		
		<tr class="startDate" style="display:none;">
			<td>시작날짜</td>
			<td>
				<form:input type="date" path="course_startdate"/>
				<form:errors element="div" path="course_startdate" cssClass="error-color"/>
			</td>
		</tr>
		
		<tr class="monthCount" style="display:none;">
			<td>기간 / 횟수</td>
			<td>
				<div>
				<input type="number" id="course_month" name="course_month" value="${fn:replace(courseVO.course_month,'0','')}"> 개월 
				<input type="number" id="course_count" name="course_count" value="${fn:replace(courseVO.course_count,'0','')}"/> 회</div>
				<form:errors element="div" path="course_month" cssClass="error-color"/>
				<form:errors element="div" path="course_count" cssClass="error-color"/>
			</td>
		</tr>
		
		<tr class="limit" style="display:none;">
			<td>수강인원수</td>
			<td>
				<ul>
					<li id="minus"><i class="fa-solid fa-minus" style="line-height:30px;"></i></li>
					<li><form:input type="number" path="course_limit" value="1"/></li>
					<li id="plus"><i class="fa-solid fa-plus" style="line-height:30px;"></i></li>
				</ul>
				<form:errors element="div" path="course_limit" cssClass="error-color"/>
			</td>
		</tr>
		<tr id="price">
			<td>가격</td>
			<!-- 자바빈에 데이터가 없으면 int값은 자동으로 0값을 가짐 -->
			<td>
			<form:hidden path="course_price"/>
			<input id="course_vprice" value="${courseVO.course_price}"/>
			 원</td>
			<form:errors element="div" path="course_price" cssClass="error-color"/>
		</tr>
		<tr class="zipcode" style="display:none;">
			<td>우편번호</td >
			<td id="zipcode">
				<form:input path="course_zipcode"/>
				<input type="button" onclick="execDaumPostcode()" value="우편번호찾기">
				<form:errors element="div" path="course_zipcode" cssClass="error-color"/>
			</td>
		</tr>
		<tr class="address1" style="display:none;">
			<td>주소</td>
			<td>
				<form:input path="course_address1"/>
				<form:errors element="div" path="course_address1" cssClass="error-color"/>
			</td>
		</tr>
		<tr class="address2" style="display:none;">
			<td>상세주소</td>
			<td>
				<form:input path="course_address2"/>
				<form:errors element="div" path="course_address2" cssClass="error-color"/>
			</td>
		</tr>
		<tr>
			<td>대표이미지</td>
			<td class="image">
				<ul>
					<li>
						<img class="course-photo1" <c:if test="${!empty courseVO.course_photo_name1}">style="display:inline-block;"  src="/course/imageView.do?course_num=${courseVO.course_num}&item_type=1"</c:if>>
						<label for="upload1" class="label1 l1" <c:if test="${!empty courseVO.course_photo_name1}">style="display:none;"</c:if>>
							<i class="fa-solid fa-circle-plus"></i><br>
							<span>이미지를 추가하세요</span><br>
						</label>
						<label for="upload1" class="label2 c1">파일 선택</label>
						<i class="fa-solid fa-circle-xmark d1" <c:if test="${!empty courseVO.course_photo_name1}">style="display:inline-block;"</c:if>></i>
						<input type="file" name="upload1" id="upload1" style="display:none;" accept="image/jpeg,image/png,image/gif">
						
						<form:errors element="div" path="course_photo1" cssClass="error-color"/>
					</li>
				</ul>
			</td>
		</tr>
		<tr>
			<td>추가이미지</td>
			<td class="image">
				<ul>
					<li>
						<img class="course-photo2" <c:if test="${!empty courseVO.course_photo_name2}"> style="display:inline-block;" src="/course/imageView.do?course_num=${courseVO.course_num}&item_type=2"</c:if>>
						<label for="upload2" class="label1 l2" <c:if test="${!empty courseVO.course_photo_name2}">style="display:none;"</c:if>>
							<i class="fa-solid fa-circle-plus"></i><br>
							<span>이미지를 추가하세요</span><br>
						</label>
						<label for="upload2" class="label2 c2">파일 선택</label>
						<i class="fa-solid fa-circle-xmark d2" <c:if test="${!empty courseVO.course_photo_name2}">style="display:inline-block;"</c:if>></i>
						<input type="file" name="upload2" id="upload2" style="display:none;" accept="image/jpeg,image/png,image/gif">
					</li>
					<li>
						<img class="course-photo3" <c:if test="${!empty courseVO.course_photo_name3}"> style="display:inline-block;" src="/course/imageView.do?course_num=${courseVO.course_num}&item_type=3"</c:if>>
						<label for="upload3" class="label1 l3" <c:if test="${!empty courseVO.course_photo_name3}">style="display:none;"</c:if>>
							<i class="fa-solid fa-circle-plus"></i><br>
							<span>이미지를 추가하세요</span><br>
						</label>
						<label for="upload3" class="label2 c3">파일 선택</label>
						<i class="fa-solid fa-circle-xmark d3" <c:if test="${!empty courseVO.course_photo_name3}">style="display:inline-block;"</c:if>></i>
						<input type="file" name="upload3" id="upload3" style="display:none;" accept="image/jpeg,image/png,image/gif">
					</li>
				</ul>
			</td>
		</tr>
		<tr>
			<td>상세설명</td>
			<td><form:textarea path="course_content"/></td>
			<form:errors element="div" path="course_content" cssClass="error-color"/>
			<script>
				 function MyCustomUploadAdapterPlugin(editor) {
					    editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
					        return new UploadAdapter(loader);
					    }
					}
				 
				 ClassicEditor
		            .create( document.querySelector( '#course_content' ),{
		            	extraPlugins: [MyCustomUploadAdapterPlugin]
		            })
		            .then( editor => {
						window.editor = editor;
					} )
		            .catch( error => {
		                console.error( error );
		            } );
			</script> 
		</tr>
	</table>
	<div class="align-center margin-top">
		<form:button class="form-btn">수정</form:button>
		<input type="button" value="목록" class="form-btn" onclick="location.href='courseDetail.do?course_num=${courseVO.course_num}'">
	</div>
</form:form>

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
                document.getElementById('course_zipcode').value = data.zonecode;
                //(수정) + extraAddr를 추가해서 address1에 참고항목이 보여지도록 수정
                document.getElementById("course_address1").value = addr + extraAddr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("course_address2").focus();

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