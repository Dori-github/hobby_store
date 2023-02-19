$(function(){
	let cate = $('#sidebar .cate');
	let d_cate = $('#sidebar .d_cate a');
	
	cate.on('click',function(){ 
		let item = $(this);//대분류
		
		
		cate.parent().find('.d_cate').stop().slideUp();
		cate.removeClass('active-color0');
		cate.removeClass('active-black');
		
		if(item.hasClass('active')){
			item.find('.d_cate').slideUp();
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
});