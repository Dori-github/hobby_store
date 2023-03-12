$(function(){
	$(document).ready(function(){
		$(".order_btn").on('click',function(){
			var delCourse = new Array();
			var delItem = new Array();
			delCourse.push(0);
			delItem.push(0);
			
			$("input[class='choice-btn']").each(function(){
				if( $(this).is(":checked") == false ){

				if($(this).closest('tr').find(".cart-del")
				.attr('data-course-del') != undefined){
					delCourse.push($(this).closest('tr').find('.cart-del')
					.attr('data-course-del'));
					}

				if($(this).closest('tr').find(".cart-del")
				.attr('data-item-del') != undefined){
					delItem.push($(this).closest('tr').find(".cart-del")
					.attr('data-item-del'));
					}
				}
			});

	$.ajax({
		url:'deleteCheck.do',
		type:'get',
		data: {
			delCourse : delCourse,
			delItem : delItem
		},
		dataType:'json',
		success:function(param){
			console.log(param);
			if(param.result == 'logout'){
				alert('로그인 후 사용하세요!');
			}else if(param.result == 'success'){
				/*orderRocation();*/
					$(location).attr('href', '/order/orderForm.do');
	
			}else{
				alert('상품 삭제 중 오류가 발생했습니다.');
			}
		},
		error:function(){
			alert('네트워크 오류가 발생했습니다.');
		}
	});	
});



	$('.cart-del').on('click',function(){
		$.ajax({
			url:'deleteCart.do',
			type:'post',
			data:{
				c_cart_num:$(this).attr('data-course-del'),
				i_cart_num:$(this).attr('data-item-del')				
			},
			dataType:'json',
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 사용하세요!');
				}else if(param.result == 'success'){
					alert('선택한 상품을 삭제했습니다.');
					location.href='cartList.do';
				}else{
					alert('장바구니 상품 삭제 오류');
				}
			},
			error:function(){
				alert('네트워크 오류');
			}
		});
	});


		/* 수량 감소 */
		$(".quan_dec").click(
			function() {
				var quantity = $(this).parent().find('.quantity').val();
				quantity = Number(quantity) - Number(1);
				var cart_num = $(this).parent().find('.cart_num').val();

				if (quantity < 1) {
					alert('상품을 1개 이상 넣어주세요');
				return;
				}
				
				var data = {
					quantity : quantity,
					cart_num : cart_num
				};
				
				$.ajax({
					url : "/updateCart",
					type : "post",
					data : data,
					success : function(result) {
						location.reload();
					},
					error : function() {
						alert("수량 변경에 실패했습니다.");
					}
				});
		});
		
		/* 수량 증가 */
		$(".quan_inc").click(
			function() {
				var quantity = $(this).parent().find('.quantity').val();
				quantity = Number(quantity) + Number(1);
				var max = $(this).parent().find('.quantity').attr('max');
				var cart_num = $(this).parent().find('.cart_num').val();

				if (quantity > max) {
					alert('주문 가능한 수량을 초과합니다');
					return;
				}
	
				var data = {
					quantity : quantity,
					cart_num : cart_num
				};
			
				$.ajax({
					url : "/updateCart",
					type : "post",
					data : data,
					success : function(data) {
						location.reload();
					},
					error : function() {
						alert("수량 변경에 실패했습니다.");
					}
				});
		});
		
		
		/* 장바구니 개별/전체 선택 */
		let course_cart_sum = $('.courseTotal').attr('data-courseTotal');
	 	let item_cart_sum = $('.itemTotal').attr('data-itemTotal');
	 	let item_sum = $('.itemSum').attr('data-itemSum');
		let cart_sum = $('.allTotal').attr('data-allTotal');
	
		//전체 선택/해제
		$('#chk_all').on('click',function(){
		if($('#chk_all').is(':checked')){//전체 선택
			$('.choice-btn').prop('checked',true);
			$('#order_btn').prop('disabled',false);//구매 가능
			$('.item-setting').remove();
			$('.quantity').show();
			$('.course-price').text(function(){
				let price = Number($(this).attr('data-coursePrice'));
				return '₩'+price.toLocaleString();
			});
			$('.item-price').text(function(){
				let price = Number($(this).attr('data-price'));
				return '₩'+price.toLocaleString();
			});
			$('.itemSum').text(function(){
				let price = Number($(this).attr('data-itemSum'));
				return '₩'+price.toLocaleString();
			});
			$('.courseTotal').attr('data-courseTotal', course_cart_sum);			
			$('.itemTotal').attr('data-itemTotal', item_cart_sum);			
			$('.allTotal').attr('data-allTotal', cart_sum);
			
		}else{//전체 해제
			$('.item-setting').remove();
			$('.choice-btn').prop('checked',false);
			$('#order_btn').prop('disabled',true);//구매 불가
			$('.quantity').before(
			  '<input type="number" class="item-setting" value="0"/>');
			$('.quantity').hide();
			$('.course-price').text('₩0');
			$('.item-price').text('₩0');
			$('.itemSum').text('₩0');
			$('.courseTotal').text('₩0');
			$('.courseTotal').attr('data-courseTotal',0);
			$('.itemTotal').text('₩0');
			$('.itemTotal').attr('data-itemTotal',0);
			$('.allTotal').text('₩0');
			$('.allTotal').attr('data-allTotal',0);
			}
			let ctotal = Number($('.courseTotal').attr('data-courseTotal'));
			$('.courseTotal').text('₩' + ctotal.toLocaleString());
			let itotal = Number($('.itemTotal').attr('data-itemTotal'));
			$('.itemTotal').text('₩' + itotal.toLocaleString());
			let total = Number($('.allTotal').attr('data-allTotal'));
			$('.allTotal').text('₩' + total.toLocaleString());
		});	
		
	
		//개별 선택/해제
		$('.choice-btn').on('click',function(){
		console.log($('input[class=choice-btn]:checked').length);
		console.log($('.choice-btn').length);
		if($('input[class=choice-btn]:checked').length
		                   == $('.choice-btn').length){
			//전체 체크박스 선택
			$('#chk_all').prop('checked',true);
		}else{
			//부분 체크박스 선택
			$('#chk_all').prop('checked',false);
		}
		
		if($(this).is(':checked')){
			//체크하면 수량, 가격, 상품별 합계 표시
			$(this).parents('tr').find('.item-setting').remove();
			$(this).parents('tr').find('.quantity').show();
			
			if($(this).attr("name") == 'course_numbers'){
			$(this).parents('tr').find('.course-price')
			       .text('₩'+Number($(this).parents('tr')
                                       .find('.course-price')
                                       .attr('data-coursePrice')).toLocaleString());
			
			$('.courseTotal').attr('data-courseTotal',
			   Number($('.courseTotal').attr('data-courseTotal')) + 
	           Number($(this).parents('tr')
						.find('.course-price')
						.attr('data-coursePrice')));
	
			$('.allTotal').attr('data-allTotal',
			   Number($('.allTotal').attr('data-allTotal')) + 
               Number($(this).closest('tr')
                      .find('.course-price')
                      .attr('data-coursePrice'))
				);
		
			}
			
			if($(this).attr("name") == 'item_numbers'){
			$(this).parents('tr').find('.item-price')
			       .text('₩'+Number($(this).parents('tr')
                                       .find('.item-price')
                                       .attr('data-price')).toLocaleString());
			$(this).parents('tr').find('.itemSum')
			       .text('₩'+Number($(this).parents('tr')
                                       .find('.itemSum')
                                       .attr('data-itemSum')).toLocaleString());
			$('.itemTotal').attr('data-itemTotal',
				   Number($('.itemTotal').attr('data-itemTotal')) + 
		           Number($(this).parents('tr')
							.find('.itemSum')
							.attr('data-itemSum')));
			
			$('.allTotal').attr('data-allTotal',
				   Number($('.allTotal').attr('data-allTotal')) + 
	               Number($(this).closest('tr')
	                      .find('.itemSum')
	                      .attr('data-itemSum'))
					);									
		}
		
		}else{
			//체크를 해제하면 수량, 가격, 상품별 합계가 모두 0으로 처리
			$(this).parents('tr').find('.quantity')
			       .before('<input type="number" class="item-setting" value="0"/>');
            $(this).parents('tr').find('.quantity').hide();
            
			if($(this).attr("name") == 'course_numbers'){		
			$(this).parents('tr').find('.course-price').text('₩0');
           	
		$('.courseTotal').attr('data-courseTotal',
			   Number($('.courseTotal').attr('data-courseTotal')) - 
               Number($(this).parents('tr')
                      .find('.course-price')
                      .attr('data-coursePrice')));

		$('.allTotal').attr('data-allTotal',
			   Number($('.allTotal').attr('data-allTotal')) - 
               Number($(this).closest('tr')
                      .find('.course-price')
                      .attr('data-coursePrice'))


				);		
}

			if($(this).attr("name") == 'item_numbers'){
			$(this).parents('tr').find('.item-price').text('₩0');
            $(this).parents('tr').find('.itemSum').text('₩0');

			$('.itemTotal').attr('data-itemTotal',
			   Number($('.itemTotal').attr('data-itemTotal')) - 
               Number($(this).parents('tr')
                      .find('.itemSum')
                      .attr('data-itemSum')));

			$('.allTotal').attr('data-allTotal',
			   Number($('.allTotal').attr('data-allTotal')) - 
               Number($(this).closest('tr')
                      .find('.itemSum')
                      .attr('data-itemSum'))
				);
			}
		}
		
		//총구매금액처리. 하나라도 선택된 것이 있으면 true
		if($('.choice-btn').is(':checked')){
			//구매 가능
			$('#order_btn').prop('disabled',false);
			//총구매금액을 선택과 미선택에 따라 다시 산출
			if($(this).is(':checked')){//선택					
			
			if($(this).attr("name") == 'course_numbers'){
			$('.courseTotal').attr('data-courseTotal',
			   Number($('.courseTotal').attr('data-courseTotal')));
			console.log("?ZZCZCZCZ" + $(this).attr("name") == 'course_numbers');
			
			console.log("ccqq:"+$('.allTotal').attr('data-allTotal'));
			}
			
			}else{//미선택
			console.log("nnn"+Number($('.courseTotal').attr('data-courseTotal')));
			console.log("bbb"+Number($(this).parents('tr')
                      .find('.course-price')
                      .attr('data-coursePrice')));
			
			if($(this).attr("name") == 'course_numbers'){
			
			$('.allTotal').attr('data-allTotal',
			   Number($('.allTotal').attr('data-allTotal')) + 
               Number($(this).parents('table')
                      .find('.courseTotal')
                      .attr('data-courseTotal')))
			}			
			}
			//총구매금액 표시
		if($(this).attr("name") == 'course_numbers'){
			$('.courseTotal').text('₩'+
			Number($('.courseTotal').attr('data-courseTotal'))
			                      .toLocaleString());
		}
		if($(this).attr("name") == 'item_numbers'){
			
		$('.itemTotal').text('₩'+
			Number($('.itemTotal').attr('data-itemTotal'))
			                      .toLocaleString());
		}
		$('.allTotal').text('₩' + 
			Number($('.allTotal').attr('data-allTotal'))
			                      .toLocaleString());

		$('.allTotal').attr('data-allTotal', 
		Number($('.allTotal').attr('data-allTotal')));	
		}
		else{//아무것도 선택 안됨
	
		$('.courseTotal').text('₩' + 
					Number($('.courseTotal').attr('data-courseTotal'))
					                      .toLocaleString());
		$('.itemTotal').text('₩' + 
					Number($('.itemTotal').attr('data-itemTotal'))
					                      .toLocaleString());
		$('.allTotal').text('₩' + 
					Number($('.allTotal').attr('data-allTotal'))
					                      .toLocaleString());
		}
	});		
});
	
function orderRocation(){
	$(location).attr('href', '${pageContext.request.contextPath}/order/orderForm.do');
	}
});
