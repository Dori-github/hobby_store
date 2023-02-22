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
<div id="item_form" class="page-main">
	<h2>상품등록</h2>
	<form:form action="itemsRegister.do" 
	                   id="register_form" 
	                   modelAttribute="itemsVO"
	                   enctype="multipart/form-data">
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<label>상품표시여부</label>
				<form:radiobutton path="status"
				       value="1" id="status1"/>미표시
				<form:radiobutton path="status"
				       value="2" id="status2"
				              checked="checked"/>표시
			</li>
			<li>
				<label for="name">상품명</label>
				<form:input path="name"/>
				<form:errors path="name" 
				                  cssClass="error-color"/>
			</li>
			<li>
				<label for="price">가격</label>
				<form:input path="price" type="number"/>
				<form:errors path="price" 
				                  cssClass="error-color"/>
			</li>
			<li>
				<label for="quantity">수량</label>
				<form:input path="quantity" type="number"/>
				<form:errors path="quantity" 
				                  cssClass="error-color"/>
			</li>
			<li>
				<label for="upload1">상품사진1</label>
				<input type="file" name="upload1" id="upload1" 
				     accept="image/gif,image/png,image/jpeg">
				<%--(주의) upload1은 자바빈(vo)에 필드가 없기 때문에 명시하면 오류발생 --%>      
				<form:errors path="photo1" cssClass="error-color"/>     
			</li>
			<li>
				<label for="upload2">상품사진2</label>
				<input type="file" name="upload2" id="upload2" 
				     accept="image/gif,image/png,image/jpeg">
				<%--(주의) upload2은 자바빈(vo)에 필드가 없기 때문에 명시하면 오류발생 --%>      
				<form:errors path="photo2" cssClass="error-color"/>     
			</li>
			
			<li><b>상품설명</b></li>
			<li>
				<form:textarea path="detail"/>
				<form:errors path="detail"
				                  cssClass="error-color"/>
				<script>
				 function MyCustomUploadAdapterPlugin(editor) {
					    editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
					        return new UploadAdapter(loader);
					    }
					}
				 
				 ClassicEditor
		            .create( document.querySelector( '#detail' ),{
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
			             onclick="location.href='my_list.do'">
		</div>	                   
	</form:form>
</div>
<!-- 중앙 컨텐츠 끝 -->