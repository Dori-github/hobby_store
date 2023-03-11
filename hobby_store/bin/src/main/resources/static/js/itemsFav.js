$(function() {
	
	//좋아요 읽기
	//좋아요 선택 여부와 선택한 총개수 표시
	function selectFav(items_num){
		$.ajax({
			url:'itemsGetFav.do',
			type:'post',
			data:{items_num:items_num},
			dataType:'json',
			success:function(param){
				displayFav(param);
				console.log(param);
			},
			error:function(){
				alert('네트워크 오류');
			}
		});
	}


	
	
	
	//좋아요 등록 		
	$('.heart').click(function(){
		$.ajax({
			url:'itemsWriteFav.do',
			type:'post',
			data:{items_num:$('.heart').attr('data-num')},
			dataType:'json',
			success:function(param){
				console.log(param)
				if(param.result == 'logout') {
					alert('로그인 후 좋아요를 누를 수 있습니다.');
				}
				else if(param.result == 'success') {
					displayFav(param);
				}
				else {
					alert('등록 오류 발생 ');
				}
			},
			error:function(){
				alert('왜 안되냐고');
			}
		});
	});

	//좋아요 표시, 좋아요 개수 표시 공통 함수
   function displayFav(param){
      if(param.status == 'yesFav'){
         $('.fa-heart').css('font-weight','bold');
      }else{
         $('.fa-heart').css('font-weight','normal');
      }
		
   }	



	function displayFav(param){
      if(param.status == 'yesFav'){
         $('.fa-heart').css('font-weight','bold');
      }else{
         $('.fa-heart').css('font-weight','normal');
      }
		
   }	

	
	

	//초기 데이터 표시 
	selectFav($('.heart').attr('data-num'));

});