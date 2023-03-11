$(function(){
	//jsp안에 자바스크립트보다 먼저 작동
	//=================클래스 메인 사이드바==================//
	let cate = $('#sidebar .cate');
	let d_cate = $('#sidebar .d_cate a');
	
	//카테고리 대분류 클릭시 세부카테고리 드롭다운
	cate.on('click',function(){  
		let item = $(this);//대분류
		
		if(item.text()=='전체'){
			location.href='courseList.do?onoff='+$('input[name=onoff]:checked').val()+'&oneweek='+$('#oneweek').val();
		}else{
			location.href='courseList.do?cate='+item.text()+'&onoff='+$('input[name=onoff]:checked').val()+'&oneweek='+$('#oneweek').val();
		}
	});
	cate.each(function(){
		if($(this).hasClass('active')){
			$(this).parent().find('.d_cate').stop().slideDown();
		}
	});
	
	//세부카테고리 클릭시 드롭다운 고정
	d_cate.on('click',function(){
		let item = $(this);//대분류
		location.href='courseList.do?cate='+item.text()+'&onoff='+$('input[name=onoff]:checked').val()+'&oneweek='+$('#oneweek').val();
	});
	d_cate.each(function(){
		if($(this).hasClass('active-color')){
			$(this).parents('.d_cate').stop().slideDown();
		}
	});
		
	//==============클래스 목록==================//
	//
//$('input[name=onoff]').on('change',function(){
	//	location.href='courseList.do?onoff='+$('input[name=onoff]:checked').val()+'&oneweek='+$(this).val();
	//});
	
	
	//원데이,정기 선택
	$('#select #oneweek').on('change',function(){
		location.href='courseList.do?onoff='+$('input[name=onoff]:checked').val()+'&oneweek='+$(this).val();
	});
	
	//지역 선택
	$('#select #location').on('change',function(){
		location.href='courseList.do?onoff='+$('input[name=onoff]:checked').val()+'&oneweek='+$('#oneweek').val()+
									'&cate='+$('#sidebar').data('param')+'&location='+$(this).val();

	});
	
	//최신순 선택
	$('#select #order').on('change',function(){
		location.href='courseList.do?onoff='+$('input[name=onoff]:checked').val()+'&oneweek='+$('#oneweek').val()+
									'&cate='+$('#sidebar').data('param')+'&location'+$('#location').val()+'&order='+$(this).val();

	});
	
	//좋아요 클릭 
	$(document).on('click','.red-heart',function(){
		let heart = $(this);
		$.ajax({
			url:'writeFav.do',
			type:'post',
			data:{course_num:$(this).attr('data-num')},
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

	
	
	
	
	//==============클래스 등록폼==================//
	//등록폼에서 유효성 체크시 폼을 다시 호출할 때 또는 수정폼 호출시 기본값 셋팅
	$('input[name=course_onoff]').on('click',function(){
		if($('#course_onoff2').is(':checked')){//오프라인 선택
			$('#course_form .oneweek').show();
			$('#course_form .limit').show();
			$('#course_form .zipcode').show();
			$('#course_form .address1').show();
			$('#course_form .address2').show();
		 }
		
		if($('#course_onoff1').is(':checked')){//온라인 선택
			$('#course_form .oneweek').hide();
			$('#course_form input[name=course_oneweek]').prop("checked", false);
			$('#course_form .limit').hide();
			$('#course_form .zipcode').hide();
			$('#course_form .address1').hide();
			$('#course_form .address2').hide();
			$('#course_form .monthCount').hide();
			$('#course_form #course_mc').show();
			$('#course_form #datetime').hide();
		 }
	});
	
	
	$('input[name=course_oneweek]').click(function(){

		if($('#course_form #course_oneweek1').is(':checked')){//원데이 선택
			$('#course_form .monthCount').hide();
			$('#course_form #datetime').show();
		}
		if($('#course_form #course_oneweek2').is(':checked')){//정기 선택
			$('#course_form .startDate').show();
			$('#course_form .monthCount').show();
			$('#course_form #course_mc').hide();
			$('#course_form #datetime').hide();
		}
	});
	
	
	
	
	
	
	//===============카테고리 분류===================//
	//카테고리 분류
	let formCate = $('.btn-select');
	let whole = $('.btn-select span');
	let formList = $('.list-box');
	let list = $('.list-cate li');
	
	formCate.on('click',function(){
		if($(this).hasClass('active')){
			formList.hide();
			$(this).removeClass('active');
			$('.btn-select .fa-chevron-up').hide();
			$('.btn-select .fa-chevron-down').show();//v
		}else{
			formList.show();
			$(this).addClass('active');
			$('.btn-select .fa-chevron-down').hide();
			$('.btn-select .fa-chevron-up').show();//^
		}
	});
	
	$('html').on('click',function(e){
		if(!$(e.target).hasClass('btn-select') && !$(e.target).hasClass('whole') && !$(e.target).hasClass('icon') ){
			formList.hide();
			formCate.removeClass('active');
			$('.btn-select .fa-chevron-up').hide();
			$('.btn-select .fa-chevron-down').show();//v
		}
		
	});
	
	list.on('click',function(){
		let value = $(this).html();
		whole.html(value);
		
		let num = $(this).attr('data-value');
		$('#course_form #cate_parent').val(num);
		
		//상세카테고리 노출
		$('#d_cate').show();
		
		//카테고리 상세 hover 스타일 활성화
		$('#course_form .btn-select2').on('mouseover',function(){
			$('#course_form .btn-select2').css('outline','3px solid gray');
		});
		$('#course_form .btn-select2').on('mouseout',function(){
			$('#course_form .btn-select2').css('outline','1px solid lightgray');
		});
		
		//기타(6) 클릭시 카테고리 상세 노출X
		if(num==6){
			$('#d_cate').hide();		
		}
	});
	
	
	//===============카테고리 상세===================//
	let formCate2 = $('#course_form .btn-select2');
	let whole2 = $('#course_form .btn-select2 span');
	let formList2 = $('#course_form .list-box2');
	
	//전체버튼 클릭
	formCate2.on('click',function(){
		
		if($(this).hasClass('active')){
			formList2.hide();
			$(this).removeClass('active');
			$('#course_form .btn-select2 .fa-chevron-up').hide();
			$('#course_form .btn-select2 .fa-chevron-down').show();//v
		}else{
			formList2.show();
			$(this).addClass('active');
			$('#course_form .btn-select2 .fa-chevron-down').hide();
			$('#course_form .btn-select2 .fa-chevron-up').show();//^
		}
	});
	
    
	
	$('html').on('click',function(e){
		if(!$(e.target).hasClass('btn-select2') && !$(e.target).hasClass('whole2') && !$(e.target).hasClass('icon2') ){
			formList2.hide();
			formCate2.removeClass('active');
			$('#course_form .btn-select2 .fa-chevron-up').hide();
			$('#course_form .btn-select2 .fa-chevron-down').show();//v
		}
		
	});

	
	$(document).on('click','#course_form .list-cate2 li',function(){//미래의 태그는 무조건 documentㅠㅠㅠ
		let value = $(this).html();
		whole2.html(value);
		
		$('#course_form #cate_name').val(value);
		
	});
		
		
		
	
    //===============요일,시간==================//
	//요일 클릭시
	$(document).on('click','#course_form #datetime input[type=checkbox]',function(){
	
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
		
		$(document).on('click','#course_form #datetime span',function(){
				let $input = $(this).parent().find('.time-choice');
			    let tnum = $input.attr('name').charAt($input.attr('name').indexOf(']')-1)
			
				let addTime=''; 
				addTime += '<input type="text" name="courseTimeVO['+tnum+'].course_reg_times" class="time-choice" placeholder="시간 선택">';
			
			
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
	$('#course_form #course_vprice').on('keyup',function(){
		if(isNaN($(this).val().replace(/,/g,''))){
			alert('숫자만 입력 가능');
			$('#course_form #course_price').val(0);
			$(this).val('');
			return;
		}
		
		$(this).val(Number($(this).val().replace(/,/g,'')).toLocaleString());
		$('#course_form #course_price').val(Number($(this).val().replace(/,/g,'')));
		
	});
	
	
	
	//===============인원수===================//
	$('#minus').on('click',function(){
		let course_num = parseInt($('#course_limit').val());
		result = course_num - 1;
		$('#course_limit').val(result);
	});
	$('#plus').on('click',function(){
		let course_num = parseInt($('#course_limit').val());
		result = course_num + 1;
		$('#course_limit').val(result);
	});
	
	
	
	//===============이미지===================//
	
	//이미지 미리 보기
	let course_photo;//선택한 이미지 저장
	//대표이미지
	$('.image #upload1').change(function(){
		course_photo = this.files[0];
		if(!course_photo){//취소한 경우
			$('.course-photo1').hide();	
			$('.l1').show();
			$('.d1').hide();
		}
		if(course_photo.size > 1024*1024){
			alert(Math.round(course_photo/1024) + 'kbytes(1024kbytes까지만 업로드 가능)');
			$(this).val('');
			return;
		}
		
		let reader = new FileReader();
		reader.readAsDataURL(course_photo);
		
		reader.onload=function(){
			$('.course-photo1').show().attr('src',reader.result);	
			$('.l1').hide();
			$('.d1').show();
		};
	});//end of change
	
	//추가이미지1
	$('.image #upload2').change(function(){
		course_photo = this.files[0];
		
		if(!course_photo){//취소한 경우
			$('.course-photo2').hide();	
			$('.l2').show();
			$('.d2').hide();
		}
		
		if(course_photo.size > 1024*1024){
			alert(Math.round(course_photo/1024) + 'kbytes(1024kbytes까지만 업로드 가능)');
			$(this).val('');
			return;
		}
		
		let reader = new FileReader();
		reader.readAsDataURL(course_photo);
		
		reader.onload=function(){
			$('.course-photo2').show().attr('src',reader.result);	
			$('.l2').hide();
			$('.d2').show();
		};
	});//end of change
	
	//추가이미지2
	$('.image #upload3').change(function(){
		course_photo = this.files[0];
		
		if(!course_photo){//취소한 경우
			$('.course-photo3').hide();	
			$('.l3').show();
			$('.d3').hide();
		}
		
		if(course_photo.size > 1024*1024){
			alert(Math.round(course_photo/1024) + 'kbytes(1024kbytes까지만 업로드 가능)');
			$(this).val('');
			return;
		}
		
		let reader = new FileReader();
		reader.readAsDataURL(course_photo);
		
		reader.onload=function(){
			$('.course-photo3').show().attr('src',reader.result);	
			$('.l3').hide();
			$('.d3').show();
		};
	});//end of change
	
	
	//취소 버튼 처리
	$(document).on('click','.image .d1',function(){
		$('.course-photo1').hide();
		$('.image #upload1').val('');	
		$('.l1').show();
		$('.d1').hide();
	});
	$(document).on('click','.image .d2',function(){
		$('.course-photo2').hide();
		$('.image #upload2').val('');	
		$('.l2').show();
		$('.d2').hide();
	});
	$(document).on('click','.image .d3',function(){
		$('.course-photo3').hide();
		$('.image #upload3').val('');	
		$('.l3').show();
		$('.d3').hide();
	});
	
	
	$('#form-select2').change(function(){
		location.href='list.do?sort='+$(this).val();
	});
	
	
	//등록폼 submit
	$('#course_form').submit(function(){
		//개월,횟수 유효성 체크
		if($('#course_onoff2').is(':checked')){
			if($('#course_month').val().trim()==''){
				let month = '<div class="error-color">개월수를 입력하세요</div>';
				$('input[name=course_month]').parent().after(month);
				$('#course_month').val().focus();
				return false;
			}
			if($('#course_count').val().trim()==''){
				let count = '<div class="error-color">횟수를 입력하세요</div>';
				$('input[name=course_count]').parent().after(count);
				$('#course_count').val().focus();
				return false;
			}
			
		}
		if($('#course_month').val().trim()==''){
			$('#course_month').val(0);
		}
		if($('#course_count').val().trim()==''){
			$('#course_count').val(0);
		}
	});	
	
	//===============클래스 상세페이지================//
	
	

	
	
	
});