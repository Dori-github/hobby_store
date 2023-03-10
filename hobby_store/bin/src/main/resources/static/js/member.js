$(function(){
	let checkId = 0;
	
	
	//--------------------------아이디 중복 체크 
	//아이디 중복 체크
	$('#confirmId').click(function(){
      if($('#mem_id').val().trim()==''){
         $('#message_id').css('color','red').text('아이디를 입력하세요');
         $('#mem_id').val('').focus();
         return;
      }
		
		//서버와 통신
		$.ajax({
			url:'confirmId.do',
			type:'post',
			data:{mem_id:$('#mem_id').val()},
			dataType:'json',
			success:function(param){
				if(param.result == 'idNotFound'){
					$('#message_id').css('color','#000')
					                .text('등록가능ID');
					checkId = 1;
				}else if(param.result == 'idDuplicated'){
					$('#message_id').css('color','red')
					                .text('중복된 ID');
                    checkId = 0; 
				}else if(param.result == 'notMatchPattern'){
					$('#message_id').css('color','red')
					                .text('영문,숫자 4자~12자 입력!');
				    checkId = 0;
				}else{
					checkId = 0;
					alert('ID중복체크 오류');
				}
			},
			error:function(){
				checkId = 0;
				alert('네트워크 오류 발생');
			}
		});
	});//end of click
	
	//아이디 중복 안내 메시지 초기화 및 아이디 중복 값 초기화
	$('#register_form #mem_id').keydown(function(){
		checkId = 0;
		$('#message_id').text('');
	});//end of keydown
	
	//submit 이벤트 발생시 아이디 중복 체크 여부 확인
	$('#register_form').submit(function(){
		if(checkId==0){
			$('#message_id').css('color','red')
			                .text('아이디 중복 체크 필수!');
			if($('#mem_id').val().trim()==''){
				$('#mem_id').val('').focus();
			}
			return false;
		}
	});//end of submit
	
	let checkNickname = 0;
	
	//--------------------------닉네임 중복 체크 
	//닉네임 중복 체크
	$('#confirmNickname').click(function(){
      if($('#mem_nickname').val().trim()==''){
         $('#message_nickname').css('color','red').text('닉네임을 입력하세요');
         $('#mem_nickname').val('').focus();
         return;
      }
		
		//서버와 통신
		$.ajax({
			url:'confirmNickname.do',
			type:'post',
			data:{mem_nickname:$('#mem_nickname').val()},
			dataType:'json',
			success:function(param){
				if(param.result == 'nicknameNotFound'){
					$('#message_nickname').css('color','#000')
					                .text('등록가능한 닉네임');
					checkNickname = 1;
				}else if(param.result == 'nicknameDuplicated'){
					$('#message_nickname').css('color','red')
					                .text('중복된 닉네임');
                    checkNickname = 0; 
				
				}else{
					checkNickname = 0;
					alert('닉네임 중복체크 오류');
				}
			},
			error:function(){
				checkNickname = 0;
				alert('네트워크 오류 발생');
			}
		});
	});//end of click
	
	//닉네임 중복 안내 메시지 초기화 및 닉네임 중복 값 초기화
	$('#register_form #mem_nickname').keydown(function(){
		checkNickname = 0;
		$('#message_nickname').text('');
	});//end of keydown
	
	//submit 이벤트 발생시 닉네임 중복 체크 여부 확인
	$('#register_form').submit(function(){
		if(checkNickname==0){
			$('#message_nickname').css('color','red')
			                .text('닉네임 중복 체크 필수!');
			if($('#mem_nickname').val().trim()==''){
				$('#mem_nickname').val('').focus();
			}
			return false;
		}
	});//end of submit
	

	
	//--------------------------이메일 인증
	var emailCheckCode = "";	// 이메일 인증번호 변수
	let checkEmailCode = 0;	// 이메일 인증 성공 여부
	
	//인증번호 전송클릭시 입력 이메일 전송 ajax 
	
	$("#email_send_button").click(function(){	// 인증번호전송 버튼 클릭시
		if($('#mem_email').val().trim()==''){
         $('#message_email').css('color','red').text('이메일을 입력하세요');
         $('#mem_email').val('').focus();
         return;
      }

      var email = $("#mem_email").val();					// 입력 이메일
	  var email_Check = $("#email_check");			// 인증번호 입력란

			
	
			// 인증번호 전송 클릭시 입력 이메일 전송 ajax 작성
			$.ajax({
				url:"mailCheck.do",
				type : "post",
				data:{mem_email:email},
				dataType:'json',
				success:function(data){
					email_Check.attr("disabled",false);		// 인증번호 입력란 활성화
					emailCheckCode = data;					// 변수에 인증번호 담기
					Swal.fire({
                    icon: 'success',
                    title:'인증번호 전송 완료',
                    showCancelButton: false,
                    confirmButtonText: "확인",
                    confirmButtonColor: "#FF4E02"
                });
				},
				error : function(){
					Swal.fire({
                    icon: 'error',
                    title:'네트워크 오류 발생',
                    showCancelButton: false,
                    confirmButtonText: "확인",
                    confirmButtonColor: "#FF4E02"
                });
				}
			});
				
		});	// end of send
		
		
		//-----------------------인증번호비교
		
	
		$("#email_check_button").click(function(){			// 인증확인 버튼 클릭시
		 	if($('#email_check').val().trim()==''){
         $('#message_emailCheck').css('color','red').text('인증번호를 입력하세요');
         $('#email_check').val('').focus();
         return;
      }
      
      
      //서버와 통신
		$.ajax({
			url:'mailCheckcode.do',
			type:'post',
			data:{userInputcode:$("#email_check").val()},
			dataType:'json',
			success:function(param){
				if(param.result == 'success'){
					$('#message_emailCheck').css('color','green')
					                .text('인증번호가 일치합니다');
		    	     checkEmailCode = 1;		
		    	     $("#email_check").attr("readonly",true);	// 인증번호 입력칸 비활성화
				
				}else{ //인증번호 불일치
		$('#message_emailCheck').css('color','red')
					                .text('인증번호가 일치하지 않습니다');
					 checkEmailCode = 0;                
				}
			},
			error:function(){
				 checkEmailCode = 0;
				alert('네트워크 오류 발생');
			}
		});
	});//end of click
	
	//인증번호 변수 초기화
	$('#register_form #email_check').keydown(function(){
		checkEmailCode = 0
		$('#message_emailCheck').text('');
	});//end of keydown

		
		// submit 이벤트 발생시 emailCertification가 false라면 체크 다시
		//submit 이벤트 발생시 인증번호 중복 체크 여부 확인
		$('#register_form').submit(function(){
			
			if( checkEmailCode == 0){
				$('#message_emailCheck').css('color','red')
					                .text('본인인증 필수!');
			if($('#mem_email').val().trim()==''){
				$('#mem_email').val('').focus();
			}
			
				return false;
			}
		});
		
	
		});
		
		
		



	

	
