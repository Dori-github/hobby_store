$('#mail-Check-Btn').click(function() {
		const email = $('#mem_email').val(); // 이메일 주소값 얻어오기!
		console.log('완성된 이메일 : ' + mem_email); // 이메일 오는지 확인
		const checkInput = $('.mail-check-input') // 인증번호 입력하는곳 
		
		$.ajax({
			type : 'get',
			url : '<c:url value ="/user/mailCheck?email="/>'+email, // GET방식이라 Url 뒤에 email을 붙힘
			success : function (data) {
				console.log("data : " +  data);
				checkInput.attr('disabled',false);
				code =data;
				alert('인증번호가 전송되었습니다.')
			}			
		}); // end ajax
	}); // end send email