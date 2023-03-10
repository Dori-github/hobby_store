$(function(){
	//좋아요 읽기
	//좋아요 선택 여부 
	function selectFav(course_num){
		$.ajax({
			url:'getFav.do',
			type:'post',
			data:{course_num:course_num},
			dataType:'json',
			success:function(param){
				displayFav(param);
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
	
	//좋아요 표시 공통 함수
	function displayFav(param){
		if(param.status == 'yesFav'){
			$('.right-class .fa-heart').css('font-weight','bold');
		}else{
			$('.right-class .fa-heart').css('font-weight','normal');
		}
	}
	
	
	
	
	//좋아요 등록 - 상세페이지
	$('.right-class .heart').click(function(){
		$.ajax({
			url:'writeFav.do',
			type:'post',
			data:{course_num:$('.right-class .heart').attr('data-num')},
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
					displayFav(param);
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
	selectFav($('.right-class .heart').attr('data-num'));
});