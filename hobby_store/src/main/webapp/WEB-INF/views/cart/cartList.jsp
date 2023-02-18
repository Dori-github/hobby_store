<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>장바구니 목록</h3>
	<c:if test="${count == 0}">
		<div>표시할 게시물이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
		<h4>장바구니 목록 List</h4>
		
	</c:if>