
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>채팅</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/message.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/talk.css" />


<!-- 채팅하기 시작 -->
<div id="talkDetail" class="container">

	<button id="talkDetail" type="button" class="btn" data-toggle="modal"
		data-target="#myModal">모달창</button>


	<div class="modal fade" id="myModal">
		<div class="modal-dialog">
			<div class="modal-content" style="padding: 20px;">
				<div class="row" 
					style="margin-top: 1rem; margin-bottom: 1rem; padding-bottom: 1rem; border-bottom: 1px solid rgba(0, 0, 0, .1);">
					<div class="col-9" >
						<div class="modal-title">${talkRoomVO.talkroom_name}</div>
					</div>
					<div class="col-3" style="font-size: 26px; text-align: right;">
					<!-- 목록 -->
						<i class="fa fa-bars" style="cursor:pointer;"
							onclick="location.href='talkList.do'"></i> 
					<!-- 채팅방 나가기 -->
							<i class="fa fa-sign-out-alt" style="cursor:pointer;" id="delete_talkroom"></i>
					</div>
				</div>

				<p>
					채팅 멤버 :
					<c:forEach var="talkVO" items="${list}" varStatus="status">
						<c:if test="${status.index > 0}">,</c:if>
						${talkVO.mem_nickname}
						<c:if test="${status.last}">(${status.count}명)</c:if>
					</c:forEach>
				</p>


				
				<div id="chatting_message" style="min-height: 500px;"></div>
				<!--  
					<p class="text-center" style="background-color: #F2F2F2;">날짜</p>
					<div class="myChat">
						<div class="chatBox">안녕1</div>
					</div>
					<div class="myChat">
						<div class="dateBox">14:11:41</div>
					</div>
					<div class="notMyChat">
						<div class="notMyChatBox">안녕2</div>
					</div>
					<div class="notMyChat">
						<div class="leftDateBox">14:11:41</div>
					</div>-->
				
				

			
				<form method="post" id="detail_form">
					<input type="hidden" name="talkroom_num" id="talkroom_num"
						value="${talkRoomVO.talkroom_num}"> 
					<input type="hidden" name="mem_num" id="mem_num" value="${user.mem_num}"> 
					<!-- 내용 -->
				<div class="chat">
					<input type="text" class="msg-input" name="message" placeholder="메세지를 입력해 주세요" id="message">
					<!-- 전송 -->
					<button type="submit" class="btn"
						style="background-color: #FF4E02; color: white; width: 19%; line-height: 1.5;">전송</button>
				</div>
			</form>
			</div>
		</div>

	</div>
</div>