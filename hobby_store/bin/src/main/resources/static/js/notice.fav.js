$(function(){
	//좋아요 읽기
	//좋아요 선택 여부와 선택한 총개수 표시
	function selectFav(noti_num){
		$.ajax({
			url:'getFav.do',
			type:'post',
			data:{noti_num:noti_num},
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
	$('#output_fav').click(function(){
		$.ajax({
			url:'writeFav.do',
			type:'post',
			data:{noti_num:$('#output_fav').attr('data-num')},
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
		let output;
		if(param.status == 'yesFav'){
			output = '../images/fav02.gif';
		}else{
			output = '../images/fav01.gif';
		}
		
		//문서 객체에 추가
		$('#output_fav').attr('src',output);
		$('#output_fcount').text(param.count);
	}
	
	//초기 데이터 표시
	selectFav($('#output_fav').attr('data-num'));
});




