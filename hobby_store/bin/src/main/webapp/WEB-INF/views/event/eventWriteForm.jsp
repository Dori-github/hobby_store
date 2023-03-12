<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 이벤트 등록 폼 시작 -->
<script src="${pageContext.request.contextPath}/js/event.js"></script>
<script src="${pageContext.request.contextPath}/js/ckeditor.js"></script>
<script src="${pageContext.request.contextPath}/js/uploadAdapter.js"></script>
<link rel="stylesheet" href="../css/jquery-ui.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/event.css">
<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('.datepicker').datepicker({
			showMonthAfterYear:true, //default 월 연 -> 연 월로 변경
			dateFormat:'yy-mm-dd',
			changeMonth:true,
			dayNamesMin:['일','월','화','수','목','금','토'],
			monthNamesShort:['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
			changeYear:true,
			minDate:0
		});
	});
</script>
<div class="event_regis_main">
	<form:form id="event_form" action="write.do" modelAttribute="eventVO" enctype="multipart/form-data">
		<form:errors element="div" cssClass="error-color"/>
		<form:hidden path="mem_num"/>
		<h2 class="title">이벤트 등록</h2>
		<table class="regis-form">
			<tr>
				<td>
					<label for="event_title">이벤트 제목</label>
				</td>
				<td>
					<form:input path="event_title"/>
					<form:errors path="event_title" cssClass="error-color"/>
				</td>
			</tr>
			<tr>
				<td>
				<label for="event_attr">이벤트 상태</label>
				</td>
				<td>
				<form:radiobutton path="event_attr" value="0" id="attr1"/>마감
				<form:radiobutton path="event_attr" value="1" id="attr2" checked="checked"/>진행중
				<form:errors path="event_attr" cssClass="error-color"/>
				</td>
			</tr>
			<tr>
				<td>
				<label for="event_content">이벤트 부제목</label>
				</td>
				<td>
				<form:input path="event_content"/>
				<form:errors path="event_content" cssClass="error-color"/>
				<td>
			</tr>
			<tr>
				<td>
				<label for="upload">이미지 추가</label>
				</td>
				<td>
				<input type="file" name="upload" id="upload" accept="image/gif,image/png,image/jpeg">
				<form:errors path="event_photo" cssClass="error-color"/>
				</td>
			</tr>
			<tr>
				<td>
				<label for="search_item">클래스/상품 등록</label>
				</td>
				<td>
				<input type="radio" name="search-btn" id="course_btn">클래스
				<input type="radio" name="search-btn" id="items_btn">상품
				<input type="radio" name="search-btn" id="reset_btn">없음
				<div id="course_choice" style="display:none;">
					<span id="course_name">클래스명</span>
					<select id="course_select" name="course_num" <c:if test="${empty course}">disabled</c:if>>
					<option value="" style="display:none;"/>
					<c:forEach var="course" items="${course}">
						<option value="${course.course_num}">${course.course_name}</option>
					</c:forEach>
					</select>
				</div>
				<div id="items_choice" style="display:none;">
					<span id="items_name">상품명</span>
					<select id="items_select" name="course_num" <c:if test="${empty items}">disabled</c:if>>
					<option value="" style="display:none;"/>
					<c:forEach var="items" items="${items}">
						<option value="${items.items_num}">${items.items_name}</option>
					</c:forEach>
					</select>
				</div>
				</td>
			</tr>
			<tr>
				<td>
				<label for="event_end">이벤트 마감일</label><!-- 달력 api는 일단 생각해보기 -->
				</td>
				<td>
				<form:input path="event_end" class="datepicker"/>
				</td>
			</tr>
			<tr>
				<td>
				<label for="event_rdate">이벤트 당첨 날짜</label><!-- 달력 api는 일단 생각해보기 -->
				</td>
				<td>
				<input type="radio" name="rdate-btn" id="rdate-O">등록
				<input type="radio" name="rdate-btn" id="rdate-X">미등록
				<form:input path="event_rdate" class="datepicker" id="rdate"/>
				</td>
			</tr>
			<tr>
				<td>
					상품설명
				</td>
				<td>
				<form:textarea path="event_detail"/>
				<form:errors path="event_detail"
				                  cssClass="error-color"/>
				<script>
				 function MyCustomUploadAdapterPlugin(editor) {
					    editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
					        return new UploadAdapter(loader);
					    }
					}
				 
				 ClassicEditor
		            .create( document.querySelector( '#event_detail' ),{
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
		<div class="end-btn">
			<form:button class="form-btn">등록</form:button>
			<input type="button" value="취소" onclick="location.href='list.do'" class="form-btn">
		</div>
	</form:form>
</div>
<!-- 이벤트 등록 폼 끝 -->