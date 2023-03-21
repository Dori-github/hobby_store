$(function(){
	//유효성 검사
	$('#order_btn').prop('disabled',true);//결제하기 버튼 비활성화
			
	$('input.order_form').on('click', function(){
		//input 포커스 해제 시
		$(this).blur(function(){
			$(this).mousedown(function(){
			$(this).parent().find('.valid').remove();
		})
		if(!($(this).val())){
			$(this).parent().find('.valid').remove();
			
			if($(this).attr('name')=='receive_name'){
			$(this).after('<p class="valid">받는 분을 입력해주세요</p>');}
			else if($(this).attr('name')=='receive_post'){
			$(this).after('<p class="valid">우편번호를 입력해주세요</p>');}
			else if($(this).attr('name')=='receive_address2'){
			$(this).after('<p class="valid">상세주소를 입력해주세요</p>');}
			else if($(this).attr('name')=='receive_phone'){
			$(this).after('<p class="valid">전화번호를 입력해주세요</p>');}
			}
		else if($(this).attr('name')=='notice' && $(this).val().length > 20){
			$(this).parent().find('.valid').remove();
			$(this).after('<p class="valid">20자 내로 입력해주세요</p>');}
		})
	})
	
	//결제하기 버튼 활성화
	$(this).change(function(){
		$('input.order_form').each(function(){
			if($(this).val() == ''){
				$('#order_btn').prop('disabled',true);
			} 
			else if($(this).attr("name")=='notice' 
			&& $(this).val().length <= 20){
				$('#order_btn').prop('disabled',false);
			}
			else{$('#order_btn').prop('disabled',true);}
	    });
	});
});