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
		
		
		$("#order_btn").click(
			function() {
				var c_chk = new Array();
				var i_chk = new Array();
				
				$('input[class=choice-btn][name=course_numbers]:checked').each(function(){
					console.log("><<cc<>> : " + $(this).attr('data-c-cart-num'));
					c_chk.push($(this).attr('data-c-cart-num'));
				})
				
				$('input[class=choice-btn][name=item_numbers]:checked').each(function(){
					console.log("><<ii<>> : " + $(this).attr('data-i-cart-num'));
					i_chk.push($(this).attr('data-i-cart-num'));
				})
				
				console.log("><<g<>> : " + c_chk);
				console.log("><<h<>> : " + i_chk);
				
				$('#c_chk').val(c_chk);
				$('#i_chk').val(i_chk);
				});
		
		/*let item_cart_sum = 0;
		*/
		let course_cart_sum = $('.courseTotal').attr('data-courseTotal');
	 	let item_cart_sum = $('.itemTotal').attr('data-itemTotal');
	 	/*let item_sum = $('.itemSum').attr('data-itemSum');*/
		/*item_cart_sum = item_cart_sum + Number(item_sum);
		*/let cart_sum = $('.allTotal').attr('data-allTotal');
	console.log(course_cart_sum);
	console.log(item_cart_sum);
	//console.log(item_sum);
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
				console.log("??price?"+price);
				return '₩'+price.toLocaleString();
			});
			$('.itemSum').text(function(){
				let price = Number($(this).attr('data-itemSum'));
				console.log("??price?"+price);
				return '₩'+price.toLocaleString();
			});
				
			console.log("??item-price?"+Number($(this).attr('data-price')));
			console.log("??itemSum?"+Number($(this).attr('data-itemSum')));
			$('.courseTotal').attr('data-courseTotal', course_cart_sum);			
			$('.itemTotal').attr('data-itemTotal', item_cart_sum);			
			$('.allTotal').attr('data-allTotal', cart_sum);
			//$('.itemSum').attr('data-itemSum', item_sum);
			
			console.log("courseTotal전체선택:" 
			+ $('.courseTotal').attr('data-courseTotal') + " / " + course_cart_sum);
			console.log("itemTotal전체선택:" 
			+ $('.itemTotal').attr('data-itemTotal') + " / " + item_cart_sum);
			/*console.log("itemSum전체선택:" 
			+ $('.itemSum').attr('data-itemSum') + " / " + item_sum);*/
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
		console.log("아ㅏㅏㅇ" + $('input[class=choice-btn]:checked').length);
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
	console.log("sdsdsdsdsd");
			};
			
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
			
			
	console.log("ggsdsdsdsd");
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
		console.log("클래스 해제: " + Number($('.allTotal').attr('data-allTotal')) -Number($(this).closest('tr')
                      .find('.course-price')
                      .attr('data-coursePrice')));
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

			console.log("aaaaaaa" + Number($('.itemTotal').attr('data-itemTotal')));

			$('.allTotal').attr('data-allTotal',
			   Number($('.allTotal').attr('data-allTotal')) - 
               Number($(this).closest('tr')
                      .find('.itemSum')
                      .attr('data-itemSum'))
				);
				console.log("상품 해제");
				
				console.log("yy클래스 해제: " + Number($(this).closest('tr')
                      .find('.itemSum')
                      .attr('data-itemSum')));
		console.log("yy클래스 해제 후 : " + $('.allTotal').attr('data-allTotal'));
				
				
			/*$(this).closest('tr')
                      .find('.itemSum').css("color","green");*/

			}
		}
		
		//총구매금액처리. 하나라도 선택된 것이 있으면 true
		if($('.choice-btn').is(':checked')){
			//구매 가능
			$('#order_btn').prop('disabled',false);
			//총구매금액을 선택과 미선택에 따라 다시 산출
			console.log("dd총 금액:" + $('.allTotal').attr('data-allTotal'));
			
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
                      .attr('data-coursePrice')));
*/
			console.log("kkk"+Number($('.courseTotal').attr('data-courseTotal')));
			console.log("ii"+Number($('.allTotal').attr('data-allTotal')));
			console.log("kkk"+Number($(this).parents('tr')
			                      .find('.course-price').attr('data-coursePrice')));
			
			/*$('.allTotal').attr('data-allTotal',
			   Number($('.allTotal').attr('data-allTotal')) + 
               Number($(this).parents('table')
                      .find('.courseTotal')
                      .attr('data-courseTotal')))*/
			
			}

			if($(this).attr("name") == 'item_numbers'){
			/*$('.itemTotal').attr('data-itemTotal',
			   Number($('.itemTotal').attr('data-itemTotal')) - 
               Number($(this).parents('tr')
                      .find('.itemSum')
                      .attr('data-itemSum')));*/
			}
			console.log("jjjjhj:");
			
			}
			//총구매금액 표시
		if($(this).attr("name") == 'course_numbers'){
			$('.courseTotal').text('₩'+
			Number($('.courseTotal').attr('data-courseTotal'))
			                      .toLocaleString());
		}
		if($(this).attr("name") == 'item_numbers'){
			
			console.log("ssrsrjhj:");
		$('.itemTotal').text('₩'+
			Number($('.itemTotal').attr('data-itemTotal'))
			                      .toLocaleString());
		}

		$('.allTotal').css("color","red");
		console.log("hhh" + Number($('.allTotal').attr('data-allTotal')));
		$('.allTotal').text('총 주문 금액 : ₩'+
			Number($('.allTotal').attr('data-allTotal'))
			                      .toLocaleString());
	
		
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
	}
});
		
	});
});

