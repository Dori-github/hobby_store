<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<div class="page-main">
	<h2>공지사항수정</h2>
	<form:form action="admin_modifyForm.do" id="update_form"
		modelAttribute="noticeVO" enctype="multipart/form-data">
		<form:hidden path="noti_num" />
		<form:errors element="div" cssClass="error-color" />
		<ul>
			<li>
				<label>필독</label> 
				<form:radiobutton path="status" value="1"
					id="status1" checked="checked" />일반 
				<form:radiobutton
					path="status" value="2" id="status2" />필독
			</li>
			<li>
				<label for="title">제목</label> 
				<form:input path="noti_title" />
				<form:errors path="noti_title" cssClass="error-color" />
			</li>
			<li>
				<label for="upload1">썸네일</label> 
				<div class="upload-space">
				<img src="imageView.do?noti_num=${noticeVO.noti_num}&noti_type=1" width="70" height="70" id="photo1" class="noti-photo">
				<input type="file" name="upload1" id="upload1" 
				          accept="image/gif,image/png,image/jpeg"  >
				<%--(주의) upload1은 자바빈(vo)에 필드가 없기 때문에 명시하면 오류발생 --%> 
				<form:errors path="photo1" cssClass="error-color" />
				</div> 
				<c:if test="${!empty noticeVO.photo_name1}">
				<div id="file_detail">
					(${noticeVO.photo_name1})파일이 등록되어 있습니다.
					<input type="button" value="사진 삭제" id="file_del">
				</div>
				
				<script type="text/javascript">
					$(function(){
						$('#file_del').click(function(){
							let choice = confirm('삭제하시겠습니까?');
							if(choice){
								$.ajax({
									url:'deleteFile.do',
									data:{noti_num:${noticeVO.noti_num}},
									type:'post',
									dataType:'json',
									success:function(param){
										if(param.result == 'logout'){
											alert('로그인 후 사용하세요');
										}else if(param.result == 'success'){
											$('#file_detail').hide();
										}else{
											alert('사진 삭제 오류 발생');
										}
									},
									error:function(){
										alert('네트워크 오류 발생');
									}
								});
							}
						});
					});
				</script>
				</c:if>
			</li>
			<li>
				<label for="upload2">공지사항 사진</label> 
					<div class="upload-space">
						<img src="imageView.do?noti_num=${noticeVO.noti_num}&noti_type=2" width="70" height="70" id="photo1" class="noti-photo">
						<input type="file" name="upload2" id="upload2" 
				          accept="image/gif,image/png,image/jpeg">
						<form:errors path="photo2" cssClass="error-color"/>
					</div>
				<c:if test="${!empty noticeVO.photo_name2 }">
				<div id="file_detail">
					(${noticeVO.photo_name2})파일이 등록되어 있습니다.
					<input type="button" value="사진 삭제" id="file_del">
				</div>
				<script type="text/javascript">
					$(function(){
						$('#file_del').click(function(){
							let choice = confirm('삭제하시겠습니까?');
							if(choice){
								$.ajax({
									url:'deleteFile.do',
									data:{noti_num:${noticeVO.noti_num}},
									type:'post',
									dataType:'json',
									success:function(param){
										if(param.result == 'logout'){
											alert('로그인 후 사용하세요');
										}else if(param.result == 'success'){
											$('#file_detail').hide();
										}else{
											alert('사진 삭제 오류 발생');
										}
									},
									error:function(){
										alert('네트워크 오류 발생');
									}
								});
							}
						});
					});
				</script>
				</c:if>
			</li>
			
			<li>
				<label for="noti_content">본문</label> 
				<form:textarea path="noti_content" /> 
				<form:errors path="noti_content" cssClass="error-color" /> 
				
				<script>
				 function MyCustomUploadAdapterPlugin(editor) {
					    editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
					        return new UploadAdapter(loader);
					    }
					}
				 
				 ClassicEditor
		            .create( document.querySelector( '#noti_content' ),{
		            	extraPlugins: [MyCustomUploadAdapterPlugin]
		            })
		            .then( editor => {
						window.editor = editor;
					} )
		            .catch( error => {
		                console.error( error );
		            } );
				</script>
			</li>
			<li><label for="noti_end">기간종료</label> <input type="date"
				name="noti_end" id="noti_end"></li>
		</ul>
		<div class="align-center">
			<form:button>수정</form:button>
			<input type="button" value="목록"
				onclick="location.href='noticeList.do'">
		</div>
	</form:form>
</div>
<!-- 중앙 컨텐츠 끝 -->