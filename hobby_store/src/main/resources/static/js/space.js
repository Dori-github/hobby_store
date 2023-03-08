$(function(){
			//jsp안에 자바스크립트보다 먼저 작동
	//=================클래스 메인 사이드바==================//
	let cate = $('#sidebar .cate');

	
	//세부카테고리 클릭시 드롭다운 고정

	cate.each(function(){
		if($(this).hasClass('active-color')){
			$(this).parents('.cate').stop().slideDown();
		}
	});

//==============클래스 목록==================//

	
	//지역 선택
	$('#select #location').on('change',function(){
		location.href='spaceList.do?cate='+$('#sidebar').data('param')+'&location='+$(this).val();

	});
	
	//최신순 선택
	$('#select #order').on('change',function(){
		location.href='spaceList.do?cate='+$('#sidebar').data('param')+'&location'+$('#location').val()+'&order='+$(this).val();

	});



//===============요일,시간==================//
	//요일 클릭시
	$(document).on('click','#space_form #datetime input[type=checkbox]',function(){
	
		//요일 클릭시 시간선택 input 노출
		if($(this).is(':checked')){
			
			if($(this).val()=='월'){
				$('#datetime .time1').show();
			}else if($(this).val()=='화'){
				$('#datetime .time2').show();
			}else if($(this).val()=='수'){
				$('#datetime .time3').show();
			}else if($(this).val()=='목'){
				$('#datetime .time4').show();
			}else if($(this).val()=='금'){
				$('#datetime .time5').show();
			}else if($(this).val()=='토'){
				$('#datetime .time6').show();
			}else{
				$('#datetime .time7').show();
			}
			
			$(this).parent().next().find('.d1').show();
			$(this).parent().next().find('span').show();
		}else{//요일 체크 안된 경우
			$(this).parent().next().find('.time-choice').not(':first').remove();
			$(this).parent().next().find('.time-choice').val('');
			$(this).parent().next().find('.time-choice').hide();
			$(this).parent().next().find('.d1').remove();
			$(this).parent().next().find('span').hide();
		}
			});
		//시간 클릭시 input 추가
		
		$(document).on('click','#space_form #datetime span',function(){
				let $input = $(this).parent().find('.time-choice');
			    let tnum = $input.attr('name').charAt($input.attr('name').indexOf(']')-1)
			
				let addTime=''; 
				addTime += '<input type="text" name="spaceTimeVO['+tnum+'].space_reg_times" class="time-choice" placeholder="시간 선택">';
			
			
			addTime += '<i class="fa-solid fa-circle-xmark d1"></i>';
			$(this).prev().after(addTime);
			$(this).prev().show();
			$(this).prev().prev().show();
		});
		
		//x버튼 클릭시 시간 삭제
		$(document).on('click','#datetime .d1',function(){
			$(this).prev().remove();
			$(this).remove();
		});
		
		
		$(document).on('click','#datetime .time-choice',function(){
			//jquery timepicker 사용
			$(this).timepicker({
			    timeFormat: 'H:i A',
			    interval: 30,
			    minTime: '09:00am',
			    maxTime: '24:00pm',
			    startTime: '09:00',
			});
			
		});

	
	//===============가격===================//
	//콤마 표시
	$('#space_form #space_price').on('keyup',function(){
		if(isNaN($(this).val().replace(/,/g,''))){
			alert('숫자만 입력 가능');
			$('#space_form #space_price').val(0);
			$(this).val('');
			return;
		}
		
		$(this).val(Number($(this).val().replace(/,/g,'')).toLocaleString());
		$('#space_form #space_price').val(Number($(this).val().replace(/,/g,'')));
		
	});
	
	//===============공간수===================//
	$('#minus').on('click',function(){
		let space_num = parseInt($('#space_np').val());
		result = space_num - 1;
		$('#space_np').val(result);
	});
	$('#plus').on('click',function(){
		let space_num = parseInt($('#space_np').val());
		result = space_num + 1;
		$('#space_np').val(result);
	});
		//===============인원수제한===================//
	$('#minus').on('click',function(){
		let space_num = parseInt($('#space_limit').val());
		result = space_num - 1;
		$('#space_limit').val(result);
	});
	$('#plus').on('click',function(){
		let space_num = parseInt($('#space_limit').val());
		result = space_num + 1;
		$('#space_limit').val(result);
	});
	
	
	
	//===============이미지===================//
	
	//이미지 미리 보기
	let space_photo;//선택한 이미지 저장
	//대표이미지
	$('.image #upload1').change(function(){
		space_photo = this.files[0];
		if(!space_photo){//취소한 경우
			$('.space-photo1').hide();	
			$('.l1').show();
			$('.d1').hide();
		}
		if(space_photo.size > 1024*1024){
			alert(Math.round(space_photo/1024) + 'kbytes(1024kbytes까지만 업로드 가능)');
			$(this).val('');
			return;
		}
		
		let reader = new FileReader();
		reader.readAsDataURL(space_photo);
		
		reader.onload=function(){
			$('.space-photo1').show().attr('src',reader.result);	
			$('.l1').hide();
			$('.d1').show();
		};
	});//end of change
	
	//추가이미지1
	$('.image #upload2').change(function(){
		space_photo = this.files[0];
		
		if(!space_photo){//취소한 경우
			$('.space-photo2').hide();	
			$('.l2').show();
			$('.d2').hide();
		}
		
		if(space_photo.size > 1024*1024){
			alert(Math.round(space_photo/1024) + 'kbytes(1024kbytes까지만 업로드 가능)');
			$(this).val('');
			return;
		}
		
		let reader = new FileReader();
		reader.readAsDataURL(space_photo);
		
		reader.onload=function(){
			$('.space-photo2').show().attr('src',reader.result);	
			$('.l2').hide();
			$('.d2').show();
		};
	});//end of change
	
	//추가이미지2
	$('.image #upload3').change(function(){
		space_photo = this.files[0];
		
		if(!space_photo){//취소한 경우
			$('.space-photo3').hide();	
			$('.l3').show();
			$('.d3').hide();
		}
		
		if(space_photo.size > 1024*1024){
			alert(Math.round(space_photo/1024) + 'kbytes(1024kbytes까지만 업로드 가능)');
			$(this).val('');
			return;
		}
		
		let reader = new FileReader();
		reader.readAsDataURL(space_photo);
		
		reader.onload=function(){
			$('.space-photo3').show().attr('src',reader.result);	
			$('.l3').hide();
			$('.d3').show();
		};
	});//end of change
	
	
	//취소 버튼 처리
   $(document).on('click','.image .d1',function(){
      $('.space-photo1').hide();
      $('.image #upload1').val('');   
      $('.l1').show();
      $('.d1').hide();
   });
   $(document).on('click','.image .d2',function(){
      $('.space-photo2').hide();
      $('.image #upload2').val('');   
      $('.l2').show();
      $('.d2').hide();
   });
   $(document).on('click','.image .d3',function(){
      $('.space-photo3').hide();
      $('.image #upload3').val('');   
      $('.l3').show();
      $('.d3').hide();
   });
	
	
});