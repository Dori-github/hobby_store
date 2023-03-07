$(function(){
	//후기 작성
	$('#reply-form').submit(function(event) {
		event.preventDefault();
		
		if($('#reply_content ').val().trim()=='') {
			alert('리뷰 작성해');
			$('리뷰 작성 내용')	.val('').focus();
			return false;	
		}
		//후기 작성에 필요한 데이터들을 form_data에 담아 한번에 보냄
		//let form_data = $(this).serialize();
		$.ajax({
			url:'itemsReply.do',
			type:'post',
			data: new FormData($('#reply-form')[0]),
			dataType:'json',
			processData: false,
			contentType: false,
			success:function(param) {
				if(param.result == 'logout') {
					alert('리뷰는 로그인 후 작성할 수 있습니다.');
				}
				else if(param.result == 'success') {
					//폼 초기화
					initForm();
					//리뷰 목록 갱신
					selectList(1);
				}
			}, error:function(){
				alert('네트워크 오류 발생');
			}
			
		});
		//댓글 작성 폼 초기화
		function initForm() {
			$('textarea').val('');
			$('#reply_limit .letter-count').text('300/300');
		}
		//textarea 글자수 체크
		$(document).on('keyup', 'textarea', function(){
			//입력 글자 수 구하기
			let inputLength = $(this).val().length;
			
			if(inputLength > 300) {
				$(this).val($(this).val().substring(0,300));
			}
			else{//300자 이하인 경우
				let remain = 300 - inputLength;
				remain += '/300';
				if($(this).attr('id') == 'reply_content') {
					$('#reply_limit .letter-count').text(remain);
				}
				else {
					$('#reply_limit .letter-count').text(remain);;
				}
			}
		});

	});
	
	
	
});