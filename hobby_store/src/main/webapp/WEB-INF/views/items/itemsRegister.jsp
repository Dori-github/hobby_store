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
								<td width="20%">상품표시 여부 </td>
								<td width="80%">
								<form:radiobutton path="status" value="1" />미표시
								<form:radiobutton path="status" value="2" />표시
								</td>
							<tr>
								<td width="20%">상품명</td>
								<td width="80%">
								<form:input path="items_name" />
								<form:errors path="items_name" cssClass="error-color" />
								</td>
							</tr>
							<tr>
								<td width="20%">상품가격</td>
								<td width="80%">
								<form:input path="items_price" type="number"/>
								<form:errors path="items_price" cssClass="error-color" />
								</td>
							</tr>
							<tr>
								<td width="20%">상품수량</td>
								<td width="80%">
								<form:input path="items_quantity" type="number" />
								<form:errors path="items_quantity" cssClass="error-color" />
								</td>
							</tr>
							<tr>
								<td width="20%">상품사진1</td>
								<td width="80%">
								<input type="file" name="upload1" id="upload1" accept="image/gif,image/png,image/jpeg">
								<%--(주의) upload1은 자바빈(vo)에 필드가 없기 때문에 명시하면 오류발생 --%>      
								<form:errors path="items_photo1" cssClass="error-color"/>     
								</td>
							</tr>
							<tr>
							<td width="20%">상품사진1 이름</td>
								<td width="80%">
								<form:input path="items_photo_name1"/>
								<form:errors path="items_photo_name1" cssClass="error-color" />
							</tr>
							<tr>
								<td width="20%">상품사진2</td>
								<td width="80%">
								<input type="file" name="upload2" id="upload2" accept="image/gif,image/png,image/jpeg">
								<%--(주의) upload1은 자바빈(vo)에 필드가 없기 때문에 명시하면 오류발생 --%>      
								<form:errors path="items_photo2" cssClass="error-color"/>     
								</td>
							</tr>
							<tr>
								<td width="20%">상품사진3</td>
								<td width="80%">
								<input type="file" name="upload3" id="upload3" accept="image/gif,image/png,image/jpeg">
								<%--(주의) upload1은 자바빈(vo)에 필드가 없기 때문에 명시하면 오류발생 --%>      
								<form:errors path="items_photo3" cssClass="error-color"/>     
								</td>
							</tr>
							<tr>
							<td width="20%">상세 설명</td>	
							<td width="80%">
								<form:textarea path="items_content"/>
								<form:errors path="items_content" cssClass="error-color"/>
							<script>
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
							</script>                   
							</td>
							</tr>	
						</tbody>
					</table>
					<input type = "submit" value = "전송">
					<input type = "button" value = "목록" onclick = "location.href ='itemsList.do'">
				</form:form>

			</div>
		</div>
	</div>
</div>
<!-- 중앙 컨텐츠 끝 -->