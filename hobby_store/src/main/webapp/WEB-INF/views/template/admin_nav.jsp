<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Admin 메뉴 시작 -->
<div class="side-bar">
	<ul>
		<li>
			<input type="button" class="menu-btn"
			 value="회원관리" 
			 onclick="location.href='${pageContext.request.contextPath}/member/admin_list.do'">
		</li>
	</ul>
</div>
<!-- Admin 메뉴 끝 -->