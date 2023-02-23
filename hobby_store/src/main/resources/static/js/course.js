$(function(){
	//jsp안에 자바스크립트보다 먼저 작동
	//클래스 메인 사이드바
	let cate = $('#sidebar .cate');
	let d_cate = $('#sidebar .d_cate a');
	
	cate.on('click',function(){ 
		let item = $(this);//대분류
		
		cate.parent().find('.d_cate').stop().slideUp();
		cate.not(item).removeClass('active');
		cate.removeClass('active-color0');
		cate.removeClass('active-black');
		
		if(item.hasClass('active')){
			item.parent().find('.d_cate').stop().slideUp();
			item.removeClass('active');
			d_cate.removeClass('active-color');
		}else{
			item.parent().find('.d_cate').stop().slideDown();
			item.addClass('active');
		}
			item.addClass('active-color0');
	});
	
	d_cate.on('click',function(){
		let item = $(this);//대분류
		
		if(d_cate.hasClass('active-color')){
			d_cate.removeClass('active-color');
		}
		cate.addClass('active-black');
		item.addClass('active-color');
	});
	
	
	//클래스 등록폼
	
	//카테고리 분류
	let formCate = $('#course_form .btn-select');
	let whole = $('#course_form .btn-select span');
	let formList = $('#course_form .list-box');
	let list = $('#course_form .list-cate li');
	
	formCate.on('click',function(){
		
		if($(this).hasClass('active')){
			formList.hide();
			$(this).removeClass('active');
			$('#course_form .btn-select .fa-chevron-up').hide();
			$('#course_form .btn-select .fa-chevron-down').show();//v
		}else{
			formList.show();
			$(this).addClass('active');
			$('#course_form .btn-select .fa-chevron-down').hide();
			$('#course_form .btn-select .fa-chevron-up').show();//^
		}
	});
	
	$('html').on('click',function(e){
		if(!$(e.target).hasClass('btn-select') && !$(e.target).hasClass('whole') && !$(e.target).hasClass('icon') ){
			formList.hide();
			formCate.removeClass('active');
			$('#course_form .btn-select .fa-chevron-up').hide();
			$('#course_form .btn-select .fa-chevron-down').show();//v
		}
		
	});
	
	list.on('click',function(){
		let value = $(this).html();
		whole.html(value);
		
		let num = $(this).val();
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
	
	
	
	
	
	//카테고리 상세
	let formCate2 = $('#course_form .btn-select2');
	let whole2 = $('#course_form .btn-select2 span');
	let formList2 = $('#course_form .list-box2');
	let list2 = $('#course_form .list-cate2 li');
	
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
		
		let name = $(this).val();
		//alert(name);
		$('#course_form #cate_name').val(name);
		
	});
		
		
	
	
	
    //요일,시간
    
    
	
	
	
	
	
	
	
	
	
	//콤마 표시
	$('#course_form #course_vprice').on('keyup',function(){
		if(isNaN($(this).val().replace(/,/g,''))){
			alert('숫자만 입력 가능');
			return;
		}
		if($(this).val()==0){
			$(this).val(' ').trim();
			$('#course_form #course_price').val(0);
		}
		$('#course_form #course_price').val(Number($(this).val().replace(/,/g,'')));
		$(this).val(Number($(this).val().replace(/,/g,'')).toLocaleString());
	});
	
	//인원수 증가,감소
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
	
	//이미지 + 버튼
	$('#course_form #image i').on('click',function(){
		$('#course_form #course_photo3').show();
		$(this).hide();
	});
	
	
	
	
});