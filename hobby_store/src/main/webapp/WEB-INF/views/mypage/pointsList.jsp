<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    

<!DOCTYPE html>
		<h5>포인트 내역</h5>	
		<c:if test="${pointsCount > 0}">
		<p>보유 포인트</p>
		<p>~보유포인트 값~</p>
		<hr size="1" noshade="noshade" width="100%">
        <p>전체 적립 사용 (탭 구현)</p>
		<table class="table table-borderless" style="text-align:center;">
		<colgroup>
			<col style="width: 15rem">
			<col style="width: 15rem">
			<col style="width: 25rem">
			<col style="width: 25rem">
		</colgroup>
		<tr>
			<th style="border-bottom: 1px solid #ccc;">구분</th>
			<th style="border-bottom: 1px solid #ccc;">포인트</th>
			<th style="border-bottom: 1px solid #ccc;">적립일자<br>(소멸일자)</th>
			<th style="border-bottom: 1px solid #ccc;">사용일자</th>
		</tr>
		<c:forEach var="pt" items="${pointsList}">
		<tr style="border-bottom: 1px solid #ccc">
		${pointsCount}
			<c:if test="${pt.points_type==0}">
			<td>적립</td>
			<td>${pt.saved_points}</td>		
			<td>${pt.saved_date}
				<c:if test="${pt.expired_date!=null}">
				<br>(${pt.expired_date})
				</c:if>
			</td>
			</c:if>
			<c:if test="${pt.points_type==1}">
			<td>사용</td>
			<td></td>
			<td>${pt.used_points}</td>
			</c:if>
			<c:if test="${pt.points_type==2}">소멸</c:if>

		</tr>
		</c:forEach>
		<tr>
			<td colspan="2"></td>
			<td>${courseTotal}</td>
		</tr>
		</table>
		
	</c:if>
	<c:if test="${pointsCount == 0}">
	포인트 내역이 존재하지 않습니다
	</c:if>