<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!-- 중앙 컨텐츠 시작 -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
.ck-editor__editable_inline{
	min-height:250px;
}
</style>
<script src="${pageContext.request.contextPath}/js/ckeditor.js"></script>
<script src="${pageContext.request.contextPath}/js/uploadAdapter.js"></script>
<div class="page-main">
	<h2>QnA 작성</h2>
	<form:form action="write.do" id="register_form" 
	                   modelAttribute="qnaVO"
	                   enctype="multipart/form-data">
	     <form:hidden path="items_num"/>
	    <form:hidden path="course_num"/>   
	    <form:hidden path="space_num"/>             
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<label for="qna_title">제목</label>
				<form:input path="qna_title"/>
				<form:errors path="qna_title" 
				                  cssClass="error-color"/>
			</li>
			<li>
				<label for="qna_content">내용</label>
				<form:textarea path="qna_content"/>
				<form:errors path="qna_content"
				                  cssClass="error-color"/>
				<script>
				 function MyCustomUploadAdapterPlugin(editor) {
					    editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
					        return new UploadAdapter(loader);
					    }
					}
				 
				 ClassicEditor
		            .create( document.querySelector( '#qna_content' ),{
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
		</ul>
		<div class="align-center">
			<form:button>전송</form:button>
			<input type="button" value="목록" 
			             onclick="location.href='list.do'">
		</div>	                   
	</form:form>
</div>
<!-- 중앙 컨텐츠 끝 -->