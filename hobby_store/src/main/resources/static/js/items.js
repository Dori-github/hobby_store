//서사의 시작 
$(function() {
	$('#category_parent').change(function() {
		var cate_num = $('#category_parent').val();
		//var cate_num = $('#category_parent option:selected').val();
		alert('selectd:' + cate_num);

		$.ajax({
			url: 'itemsRegister2.do',
			type: 'get',
			data: { cate_num: cate_num },
			dataType: 'json',
			success: function(param) {

				const like_Array = param;
				//alert(Array.isArray(like_Array)); //false(배열X)
				//console.log(like_Array); //유사배열 key : 숫자
				const real_Array = Array.from(like_Array['items_child']); // 유사배열 -> 배열 
				//console.log(real_Array);

				/*	
					const arrData = [];
					for(const key of Object.keys(like_Array)) {
						arrData.push(Array.from(like_Array[key]));
						console.debug(arrData);
					}
				*/
				var cate_num = []; //카테고리 번호
				var cate_name = []; //카테고리 이름 
				let output = '';
				for (var i = 0; i < real_Array.length; i++) {
					cate_num[i] = real_Array[i].cate_num;
					cate_name[i] = real_Array[i].cate_name;
					//alert("cate_num = "+cate_num[i]+" cate_name = "+ cate_name[i]);
				}
				$('#cate_child').empty();
				var selectedItem = $("<option selected>세부 카테고리</option>");
				$('#cate_child').append(selectedItem);

				for (let i = 0; i < real_Array.length; i++) {
					output += '<option value ="' + cate_num[i] + '">' + cate_name[i] + '</option>'; //select box 밑에 출력					
				}
				$('#cate_child').append(output);
			},
			error: function() {
				alert('아 왜 오류나는데');
			}
		});
		//ajax 통신 완료

	});

	$('#cate_child').change(function() {
		var cate_num = $('#cate_child').val();
		alert("선택한 소분류 카테고리 : " + cate_num);
	});
	

	//1. 사이드 바 시작 
	let cate = $('#sidebar .cate');
	let d_cate = $('#sidebar .d_cate a');
	

	//카테고리 대분류 클릭시 세부카테고리 드롭다운
	cate.on('click', function() {
		let item = $(this);//대분류
		//선택한 cate(text)
		location.href = 'itemsList.do?cate=' + item.text();
				
	});
	cate.each(function() {
		if ($(this).hasClass('active')) {
			$(this).parent().find('.d_cate').stop().slideDown();
		}
	});

	//세부카테고리 클릭시 드롭다운 고정
	d_cate.on('click', function() {
		let item = $(this);//소분류
		location.href = 'itemsList.do?cate=' + item.text();
		//cate.addClass('active-black');
	});
	d_cate.each(function() {
		if ($(this).hasClass('active-color')) {
			$(this).parents('.d_cate').stop().slideDown();
		}
	});
	//2. 검색 시작
	$('#search_form').on('submit',function(){
			if($('#keyword').val().trim()==''){
				alert('검색어를 입력하세요');
				$('#keyword').val('').focus();
				return false;
			}
		});
		
	//3-1 포장, 픽업 여부 
	$('#select #packaging').change(function(){
		location.href = 'itemsList.do?cate='+$('#sidebar').data('param')+'&packaging='+$('#select #packaging').val();
	});
	
	//3-2 최신,리뷰,좋아요순 정렬 시작
	$('#select #check').change(function() {
		location.href = 'itemsList.do?cate='+$('#sidebar').data('param')+'&packaging='+$('#select #packaging').val()+'&check='+$('#select #check').val();
		});
		
		
		
	//좋아요 클릭 
	$(document).on('click','.red-heart',function(){
		let heart = $(this);

		$.ajax({
			url:'itemsWriteFav.do',
			type:'post',
			data:{items_num:$(this).attr('data-num')},
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
						heart.parent().find('.countFav').text(param.count);
						
					}else{
						heart.find('.fa-heart').css('font-weight', 'normal');
						heart.parent().find('.countFav').text(param.count);
					
					}
					
						
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


});