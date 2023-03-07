$(function(){
/*	$(".qq").click(
		function(){
		alert("ddd");
		}
	);*/
	
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
			});
	/* 수량 증가 */
	$(".quan_inc").click(
			function() {
				var quantity = $(this).parent().find('.quantity').val();
				quantity = Number(quantity) + Number(1);
				var item_quan = $(this).parent().find('.item_quan').val();
				var cart_num = $(this).parent().find('.cart_num').val();
				if (quantity > item_quan) {
					alert('주문 가능한 수량을 초과했습니다');
					return;
				}
	
				var data = {
					quantity : quantity,
					cart_num : cart_num
				};
				
				let cart_quantity = '<input type="number" class="quantity" value="${quan.quantity}">';
				
				cart_quantity += '<span>${data.quantity}</span>';
				cart_quantity += '<span>zzz</span>';
			
			//문서 객체에 추가
			$('#quan').append(cart_quantity);
	
			
				$.ajax({
					url : "/updateCart",
					type : "post",
					data : data,
					success : function(data) {
						console.log("수량+1\n" + "cart_num:" + cart_num
								+ " quantity:" + quantity);
					
						$('#quan').empty();
						
						
						let str = JSON.stringify(data); // <> parse()
						alert(str); 
						
						
						//댓글 목록 작업
						$(data.list)
								.each(
										function(index, item) {
											console.log(index + " " + item.quantity);
											console.log(index + " " + item);
										
											let cart_quantity = '<input type="number" class="quantity" value="${quan.quantity}">';
												cart_quantity += '<span>zzz</span>';
											
											//문서 객체에 추가
											$('#quan').append(cart_quantity);
					
					
										});
					
					 
				
				
				
				
				
				},
				error : function() {
					alert("수량 변경 실패");
				}
			});
		});


});








/*
$(function() {
	function getItemCart() {
		$
				.ajax({
					url : '/getItemQuan',
					type : 'post',
					data : {
						mem_num : 100
					},
					dataType : 'json',
					success : function(param) {
						count = param.count;
						rowCount = param.rowCount;

						//처음 호출시 해당 ID의 div의 내부 내용물 제거
						$('#output').empty(); 

						//댓글 목록 작업
						$(param.list)
								.each(
										function(index, item) {
											let cart_quantitiy = '<div class="item">';
											cart_quantitiy += '<ul class="detail-info">';
											cart_quantitiy += '<div>';
											cart_quantitiy += '<input type="number" class="quantity" value="${quan.quantity}">';
											cart_quantitiy += '</div>';
											cart_quantitiy += '</ul>';
											cart_quantitiy += '<div class="sub-item">';
											cart_quantitiy += '<p>'
													+ item.re_content
															.replace(
																	/\r\n/g,
																	'<br>')
													+ '</p>';
											cart_quantitiy += '<hr size="1" noshade>'
											cart_quantitiy += '</div>';
											cart_quantitiy += '</div>';

											//문서 객체에 추가
											$('#cart_quantitiy').append(
													cart_quantitiy);
										});
					},
					errror : function() {
						alert('네트워크 오류 발생');
					}
				});
	}
	;
});
*/