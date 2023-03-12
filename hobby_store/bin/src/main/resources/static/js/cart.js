$(function(){
	$(document).ready(function(){

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
						console.log("수량-1\n" + "cart_num:" + cart_num
								+ " quantity:" + quantity);
					},
					error : function() {
						alert("수량 변경 실패");
					}
				});
			$(this).parent().find("#quan").val(quantity);
			location.reload();
		});
		
		/* 수량 증가 */
		$(".quan_inc").click(
			function() {
				var quantity = $(this).parent().find('.quantity').val();
				quantity = Number(quantity) + Number(1);
				var item_quan = $(this).parent().find('.item_quan').val();
				var cart_num = $(this).parent().find('.cart_num').val();
				$(this).closest('tr').find(".item-price").css("color","blue");
				
				var item_price = $(this).closest('tr').find(".item-price").attr("data-price");
				
				if (quantity > item_quan) {
					alert('주문 가능한 수량을 초과했습니다');
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
						console.log("수량+1\n" + "cart_num:" + cart_num
								+ " quantity:" + quantity);
					},
					error : function() {
						alert("수량 변경 실패");
					}
				});
			$(this).parent().find("#quan").val(quantity);
			location.reload();
		});
		
		//장바구니 클래스-상품 선택/해제
		/*
		$.ajax({
			url : "/updateCart",
			type : "get",
			data : {
				quantity : "quantity"
			},
			dataType:"text" //json으로도 해봄
		,
		error: function(error){
			console.log("noooo");
		},
		success: function(response, status, request){
					alert("success")
					alert("response : "+response);
					alert("status : "+status);
					alert("request : "+request);
					}
	});
		
	function out(resp){
		var result = "";
		result +='<h2>ssssooooddddd</h2>'
		$.each(JSON.parse(resp),function(index,item){
			result +='<h4>'+item["cartVO"]+'</h4>'
		})
		$('#ss').append(result);
	}*/
		
		
		
		let course_cart_sum = $('.courseTotal').attr('data-courseTotal');
	 	let item_cart_sum = $('.itemTotal').attr('data-itemTotal');
	 	let item_sum = $('.itemSum').attr('data-itemSum');
		let cart_sum = $('.allTotal').attr('data-allTotal');
	console.log(course_cart_sum);
	console.log(item_cart_sum);
	console.log(course_cart_sum + item_cart_sum);
	console.log(cart_sum);
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
			
			console.log("courseTotal전체선택:" 
			+ $('.courseTotal').attr('data-courseTotal') + " / " + course_cart_sum);
			console.log("itemTotal전체선택:" 
			+ $('.itemTotal').attr('data-itemTotal') + " / " + item_cart_sum);
			console.log("allTotal전체선택:" 
			+ $('.allTotal').attr('data-allTotal') + " / " + cart_sum);
	
			
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
			
			console.log("2courseTotal전체선택:" 
			+ $('.courseTotal').attr('data-courseTotal') + " / " + course_cart_sum);
			console.log("2itemTotal전체선택:" 
			+ $('.itemTotal').attr('data-itemTotal') + " / " + item_cart_sum);
			console.log("2allTotal전체선택:" 
			+ $('.allTotal').attr('data-allTotal') + " / " + cart_sum);
			
			}
			let ctotal = Number($('.courseTotal').attr('data-courseTotal'));
			$('.courseTotal').text(ctotal.toLocaleString()+'원');
			let itotal = Number($('.itemTotal').attr('data-itemTotal'));
			$('.itemTotal').text(itotal.toLocaleString()+'원');
			let total = Number($('.allTotal').attr('data-allTotal'));
			$('.allTotal').text(total.toLocaleString()+'원');
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

		console.log("클래스 해제 전 : " + $('.allTotal').attr('data-allTotal'));
		$('.allTotal').attr('data-allTotal',
			   Number($('.allTotal').attr('data-allTotal')) - 
               Number($(this).closest('tr')
                      .find('.course-price')
                      .attr('data-coursePrice'))


				);
		console.log("클래스 해제 후 : " + $('.allTotal').attr('data-allTotal'));
				
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
				console.log("상품 해제");
			/*$(this).closest('tr')
                      .find('.itemSum').css("color","green");*/

			}
		}
		
		//총구매금액처리. 하나라도 선택된 것이 있으면 true
		if($('.choice-btn').is(':checked')){
			//구매 가능
			$('#order_btn').prop('disabled',false);
			//총구매금액을 선택과 미선택에 따라 다시 산출
			console.log("총 금액:" + $('.allTotal').attr('data-allTotal'));
			
			if($(this).is(':checked')){//선택					
			/*console.log("nnn"+Number($('.courseTotal').attr('data-courseTotal')));
			console.log("bbb"+Number($(this).parents('tr')
                      .find('.course-price')
                      .attr('data-coursePrice')));*/


			if($(this).attr("name") == 'course_numbers'){
			$('.courseTotal').attr('data-courseTotal',
			   Number($('.courseTotal').attr('data-courseTotal')));
			console.log("?ZZCZCZCZ" + $(this).attr("name") == 'course_numbers');
			
			console.log("ccqq:"+$('.allTotal').attr('data-allTotal'));
			}
			
			/*if($(this).attr("name") == 'item_numbers'){
			$('.itemTotal').attr('data-itemTotal',
			   Number($('.itemTotal').attr('data-itemTotal')));
			console.log("iiqq:"+$('.allTotal').attr('data-allTotal'));
			}*/
			
			}else{//미선택
			console.log("nnn"+Number($('.courseTotal').attr('data-courseTotal')));
			console.log("bbb"+Number($(this).parents('tr')
                      .find('.course-price')
                      .attr('data-coursePrice')));
			
			if($(this).attr("name") == 'course_numbers'){
			/*$('.courseTotal').attr('data-courseTotal',
			   Number($('.courseTotal').attr('data-courseTotal')) - 
               Number($(this).parents('tr')
                      .find('.course-price')
                      .attr('data-coursePrice')));*/

			$('.allTotal').attr('data-allTotal',
			   Number($('.allTotal').attr('data-allTotal')) + 
               Number($(this).parents('table')
                      .find('.courseTotal')
                      .attr('data-courseTotal')))
			
			}

			if($(this).attr("name") == 'item_numbers'){
			/*$('.itemTotal').attr('data-itemTotal',
			   Number($('.itemTotal').attr('data-itemTotal')) - 
               Number($(this).parents('tr')
                      .find('.itemSum')
                      .attr('data-itemSum')));*/
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
		
		$('.allTotal').css("color","red");
		$('.allTotal').text(
			Number($('.allTotal').attr('data-allTotal'))
			                      .toLocaleString()+'원');
		/*$('.allTotal').attr('data-allTotal',
			   Number($('.allTotal').attr('data-allTotal')) + 
               Number($(this).parents('table')
                      .find('.courseTotal')
                      .attr('data-courseTotal')) +
               Number($(this).parents('table')
                      .find('.itemTotal')
                      .attr('data-itemTotal'))	
);*/

	console.log("qq:"+$('.allTotal').attr('data-allTotal'));
	console.log("ㅎ:" + (Number($('.allTotal').attr('data-allTotal'))) + 
               Number($(this).parents('table')
                      .find('.courseTotal')
                      .attr('data-courseTotal')) +
               Number($(this).parents('table')
                      .find('.itemTotal')
                      .attr('data-itemTotal')));
	
		
	}
	else{
	//선택된게 아무것도 없음
	console.log("전체 해제");
	
	
$('.courseTotal').text(
			Number($('.courseTotal').attr('data-courseTotal'))
			                      .toLocaleString()+'원');
$('.itemTotal').text(
			Number($('.itemTotal').attr('data-itemTotal'))
			                      .toLocaleString()+'원');
$('.allTotal').text(
			Number($('.allTotal').attr('data-allTotal'))
			                      .toLocaleString()+'fff원');
console.log("금ㅇㄹㅇㄹ:" + $('.allTotal').attr('data-allTotal'));
	}
});
		
	});
});

