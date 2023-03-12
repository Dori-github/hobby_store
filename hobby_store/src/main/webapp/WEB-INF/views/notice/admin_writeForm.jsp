<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 중앙 컨텐츠 시작 -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath}/css/notice.css" rel="stylesheet">
<style>
.ck-editor__editable_inline{
	min-height:250px;
}
</style>
<script src="${pageContext.request.contextPath}/js/ckeditor.js"></script>
<script src="${pageContext.request.contextPath}/js/uploadAdapter.js"></script>
<div class="page-main" id="notice_register">
	<h2>공지사항등록</h2>
	<form:form action="admin_write.do" 
	                   id="register_form" 
	                   modelAttribute="noticeVO"
	                   enctype="multipart/form-data">
		<form:errors element="div" cssClass="error-color"/>
	<table>
		<tr>
			<td>필독</td>
			<td  class="radio">
				<label>
					<form:radiobutton path="status"
				       value="1" id="status1"
				              checked="checked"/>일반	
				</label>
				<label>
					<form:radiobutton path="status"
					       value="2" id="status2"/>필독 
				</label>
			</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>
				<form:input path="noti_title" class="input"/>
				<form:errors path="noti_title" 
				                  cssClass="error-color"/>
			</td>
		</tr>
		<tr>
			<td>썸네일</td>
			<td>
				<input type="file" name="upload1" id="upload1" 
				     accept="image/gif,image/png,image/jpeg">
				<%--(주의) upload1은 자바빈(vo)에 필드가 없기 때문에 명시하면 오류발생 --%>      
				<form:errors path="photo1" cssClass="error-color"/>    
			</td>
		</tr>
		<tr>
			<td>공지사항 사진</td>
			<td>
				<input type="file" name="upload2" id="upload2" 
				     accept="image/gif,image/png,image/jpeg">
				<%--(주의) upload2은 자바빈(vo)에 필드가 없기 때문에 명시하면 오류발생 --%>      
				<form:errors path="photo2" cssClass="error-color"/>     
			</td>
		</tr>
		<tr>
			<td>본문</td>
			<td>
				<form:textarea path="noti_content"/>
				<form:errors path="noti_content"
				                  cssClass="error-color"/>
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
			</td>
		</tr>
		<tr>
			<td>기간종료</td>
			<td>
				<input type="date" name="noti_end" id="noti_end">
			</td>
		</tr>
	</table>
		<!-- 
		<ul>
			<li>
				<label>필독</label>
				<form:radiobutton path="status"
				       value="1" id="status1"
				              checked="checked"/>일반
				<form:radiobutton path="status"
				       value="2" id="status2"/>필독 
			</li>
			<li>
				<label for="title">제목</label>
				<form:input path="noti_title"/>
				<form:errors path="noti_title" 
				                  cssClass="error-color"/>
			</li>
			<li>
				<label for="upload1">썸네일</label>
				<input type="file" name="upload1" id="upload1" 
				     accept="image/gif,image/png,image/jpeg">
				<%--(주의) upload1은 자바빈(vo)에 필드가 없기 때문에 명시하면 오류발생 --%>      
				<form:errors path="photo1" cssClass="error-color"/>     
			</li>
			<li>
				<label for="upload2">공지사항 사진</label>
				<input type="file" name="upload2" id="upload2" 
				     accept="image/gif,image/png,image/jpeg">
				<%--(주의) upload2은 자바빈(vo)에 필드가 없기 때문에 명시하면 오류발생 --%>      
				<form:errors path="photo2" cssClass="error-color"/>     
			</li>
			<li>
				<label for="noti_content">본문</label>
				<form:textarea path="noti_content"/>
				<form:errors path="noti_content"
				                  cssClass="error-color"/>
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
			<li>
				<label for="noti_end">기간종료</label>
				<input type="date" name="noti_end" id="noti_end">
			</li>
		</ul>
		-->
	
		<div class="align-center" style="margin:50px;">
			<form:button class="form-btn">전송</form:button>
			<input type="button" value="목록" class="form-btn"
			             onclick="location.href='admin_list.do'">
		</div>	                   
	</form:form>
</div>
<!-- 중앙 컨텐츠 끝 -->