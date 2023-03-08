$(function(){
	//좋아요 읽기
	//좋아요 선택 여부와 선택한 총개수 표시
	function selectFav(space_num){
		
		$.ajax({
			url:'getFav.do',
			type:'post',
			data:{space_num:space_num},
			dataType:'json',
			success:function(param){
				displayFav(param);
			},
			error:function(){
				alert('네트워크 오류');
			}
		});
	}
		//좋아요 등록
	$('#red_heart_detail').click(function(){
		$.ajax({
			url:'writeFav.do',
			type:'post',
			data:{space_num:$('#red_heart_detail').attr('data-num')},
			dataType:'json',
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 좋아요를 눌러주세요!');
				}else if(param.result == 'success'){
					displayFav(param);
				}else{
					alert('등록시 오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	});

	//좋아요 표시, 좋아요 개수 표시 공통 함수
	function displayFav(param){
	
		if(param.status == 'yesFav'){
			            $('.fa-heart').css('font-weight', 'bold');
			}else if(param.status=='noFav'){
						$('.fa-heart').css('font-weight', 'normal');
					
					}else{
					Swal.fire({
				        icon: 'error',
				        title:'등록시 오류 발생',
				        showCancelButton: false,
				        confirmButtonText: "확인",
				        confirmButtonColor: "#FF4E02"
					 });				        
					}
            //문서 객체에 추가
     
            $('.fav_cnt').text(param.count);
           }




		
	

	/*
	//초기 데이터 표시
	selectFav($('#heart').attr('data-num'));*/



	//좋아요 클릭 
	$(document).on('click','.red-heart',function(){
		let heart = $(this);
		$.ajax({
			url:'writeFav.do',
			type:'post',
			data:{space_num:$(this).attr('data-num')},
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
						heart.find('.fa-heart').css('font-weight', 'bold');
					}else{
						heart.find('.fa-heart').css('font-weight', 'normal');
					}
					heart.parent().find('.fav_cnt').text(param.count);
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
	
	if($('#space_detail').length>0){
		//초기 데이터 표시
		selectFav($('#red_heart_detail').attr('data-num'));
	}
});
	
	