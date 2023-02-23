$(function(){
	let checkNickname = 0;
	
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
	
});



