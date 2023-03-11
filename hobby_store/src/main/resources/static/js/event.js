$(function(){
	//=====Event 클래스/상품등록 목록 검색======//
	//버튼 클릭 시 text창 보여짐
	$('#course_btn').click(function(){
		$('#course_choice').show();
		$('#items_choice').hide();
		$('#items_select').val('');
	});
	$('#items_btn').click(function(){
		$('#items_choice').show();
		$('#course_choice').hide();
		$('#course_select').val('');
	});
	
	//클래스/상품 검색
	/*$('#search_list').keyup(function(){
		if($('#search_list').val().trim()==''){
			$('#search_area').empty();			
			return;
		}
		
		$.ajax({
			url:'eventSearchAjax.do',
			type:'post',
			data:{name:$('#search_list').val()},
			dataType:'json',
			success:function(param){
				if(param.result=='logout'){
					$('#search_list').attr('disabled',true);
					$('#search_list').val('로그인 후 검색 가능합니다.');
				}else if(param.result=='success'){
					if($('#class_btn').is(':checked')){//클래스 버튼 선택
						$(param.course).each(function(index,item){
							let output = '';
							output += '<li data-num="' + item.course_num + '">';
							output += item.course_name;
							output += '</li>';
							$('#search_area').append(output);
						});
					}else if($('#item_btn').is(':checked')){//클래스 버튼 선택
						$(param.items).each(function(index,item){
							let output = '';
							output += '<li data-num="' + item.items_num + '">';
							output += item.items_name;
							output += '</li>';
							$('#search_area').append(output);
					});
				}
				}
			},
			error:function(){
				alert('목록 검색 시 오류 발생');
			}
		});	
	});*/
	
	//reset 버튼 클릭 시 text창 숨김
	$('#reset_btn').click(function(){
		$('#course_choice').hide();
		$('#items_choice').hide();
		$('#course_select').val('');
		$('#items_select').val('');
	});
	
	$('#event_delete').click(function(){
		let choice = confirm('이벤트를 삭제하시겠습니까?');
		
		if(choice){
			location.href='delete.do';
		}
	});
	
	
	$('#rdate-O').click(function(){
		$('#rdate').show();
		$('#rdate').datepicker('option','disabled',false);
	});
	
	$('#rdate-X').click(function(){
		$('#rdate').hide();
		$('#rdate').datepicker('option','disabled',true);
		$('#rdate').val('0000-00-00');
	});
	
	//이미지 변경
	$('#file_modify').click(function(){
		$('.file-detail').hide();
		$('#file_modifyForm').show();
	});
	
	$('#photo_reset').click(function(){
		$('.file-detail').show();
		$('#file_modifyForm').hide();
		$('#upload').val('');
	});
});