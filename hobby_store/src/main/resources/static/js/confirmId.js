$(function(){
	let checkId = 0;
	
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
	
});



