$(function(){
	//좋아요 읽기
	//좋아요 선택 여부
/*
	function selectReplyFav(reply_num){
		$.ajax({
			url:'getReplyFav.do',
			type:'post', 
			data:{reply_num:reply_num},
			dataType:'json',
			success:function(param){
				if(param.status == 'yesFav'){
					$(this).find('.fa-thumbs-up').css('color','#FF4E02');
					$(this).find('.fa-thumbs-up').css('transform','scale(1.2)');
				}else{
					$(this).find('.fa-thumbs-up').css('color','#000');
					$(this).find('.fa-thumbs-up').css('transform','none');
				}
			},
			error:function(){
				Swal.fire({
			        icon: 'error',
			        title:'네트워크 오류',
			        showCancelButton: false,
			        confirmButtonText: "확인",
			        confirmButtonColor: "#FF4E02"
			        });
			}
		});
	}
	
	*/
	
	
	//좋아요 등록 - 상세페이지
	$(document).on('click','.r-list-fav',function(){
		let replyFav = $(this);
		$.ajax({
			url:'writeReplyFav.do',
			type:'post',
			data:{reply_num:$(this).attr('data-num'),course_num:$(this).attr('data-cnum')},
			dataType:'json',
			success:function(param){
				if(param.result == 'logout'){
					Swal.fire({
				        icon: 'warning',
				        title:'로그인 후 좋아요를 눌러주세요',
				        showCancelButton: false,
				        confirmButtonText: "확인",
				        confirmButtonColor: "#FF4E02"
				        });
				}else if(param.result == 'success'){
					if(param.status == 'yesFav'){
						replyFav.find('.fa-thumbs-up').css('color','#FF4E02');
						replyFav.find('.fa-thumbs-up').css('transform','scale(1.2)');
					}else{
						replyFav.find('.fa-thumbs-up').css('color','#000');
						replyFav.find('.fa-thumbs-up').css('transform','none');
					}
					replyFav.find('.favcount').text(param.count);
				}else{
					Swal.fire({
				        icon: 'error',
				        title:'등록시 오류 발생',
				        showCancelButton: false,
				        confirmButtonText: "확인",
				        confirmButtonColor: "#FF4E02"
				        });
				}
			},
			error:function(){
				Swal.fire({
			        icon: 'error',
			        title:'네트워크 오류',
			        showCancelButton: false,
			        confirmButtonText: "확인",
			        confirmButtonColor: "#FF4E02"
			        });
			}
		});
	});
	

	//초기 좋아요 표시-상세페이지
	//selectReplyFav($('.r-list-fav').attr('data-num'));
});