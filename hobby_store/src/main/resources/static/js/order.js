$(function(){
	//유효성 검사
	$('#order_btn').prop('disabled',true);//결제하기 버튼 비활성화
	$('#order_btn').css('border',"1px solid #ddd");
	$('#order_btn').css('color',"#aaa");
/*	$('.order-wrap > #order_btn').css("background-color","#fff");	
	$('.order-wrap > #order_btn').css("color","#aaaa");	
	$('.order-wrap > #order_btn').css("border","1px solid #aaa");*/	
	$('input.order_form').on('click', function(){
		//input 포커스 해제 시
		$(this).blur(function(){
			$(this).mousedown(function(){
			$(this).css("border","1px solid #aaa;");
			$(this).parent().find('.valid').remove();
		})
		if(!($(this).val())){
			$('.info-form input[type="text"]').css("border","1px solid #aaa;");
			$(this).parent().find('.valid').remove();
			
			if($(this).attr('name')=='receive_name'){	
			$(this).css("border","1px solid #ff3939");
			$(this).after('<p class="valid">받는 분을 입력해주세요</p>');}
			else if($(this).attr('name')=='receive_post'){
			$(this).css("border","1px solid #ff3939");
			$(this).after('<p class="valid">우편번호를 입력해주세요</p>');}
			else if($(this).attr('name')=='receive_address2'){
			$(this).css("border","1px solid #ff3939");
			$(this).after('<p class="valid">상세주소를 입력해주세요</p>');}
			else if($(this).attr('name')=='receive_phone'){
			$(this).css("border","1px solid #ff3939");
			$(this).after('<p class="valid">전화번호를 입력해주세요</p>');}
			else if($(this).attr('name')=='notice'){
			$(this).css("border","1px solid #ff3939");
			$(this).after('<p class="valid">배송 메시지를 입력해주세요</p>');}
			}
		else if($(this).attr('name')=='notice'
					&& $(this).val().length > 20){
			$(this).parent().find('.valid').remove();
			$(this).css("border","1px solid #ff3939");
			$(this).after('<p class="valid">1~20자 내로 입력해주세요</p>');}
		else{$(this).css("border","1px solid #aaa");
			}
		})
	})
	
	//결제하기 버튼 활성화
	$(this).change(function(){
		$('input[type="text"].order_form').each(function(){
			if($(this).attr("name")=='notice' 
			&& $(this).val().length > 20){
				$('#order_btn').prop('disabled',true);
				$('#order_btn').css('border',"1px solid #ddd");
				$('#order_btn').css('color',"#aaa");
			}
			else if ($(this).val() == ''){
				$('#order_btn').prop('disabled',true);
				$('#order_btn').css('border',"1px solid #ddd");
				$('#order_btn').css('color',"#aaa");
			}
			else if($('#receive_post').val()!=''){
				$('#order_btn').prop('disabled',false);
				$('#order_btn').css('border',"1px solid #aaa");
				$('#order_btn').css('color',"#666");
			}
	    });
	});
});