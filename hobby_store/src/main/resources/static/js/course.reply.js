$(function(){
	//페이지와 관련된 정보를 가지는 변수
    let pageSize = 5;//화면에 보여줄 레코드 수
    let pageBlock = 3;//페이지 표시 단위
    let currentPage;//현재 보고 있는 화면
    let totalItem;//총 레코드 수 

	//알림창
	function alertIcon(icon,title){
		Swal.fire({
            icon: icon,
            title:title,
            showCancelButton: false,
            confirmButtonText: "확인",
            confirmButtonColor: "#FF4E02"
        });
	}
	function alertImg(title){
		Swal.fire({
			imageUrl:'/image_bundle/icon.png',
			imageWidth:200,
			imageHeight:130,
            title:title,
            showCancelButton: false,
            confirmButtonText: "확인",
            confirmButtonColor: "#FF4E02"
        });
	}


	//후기 목록
	function selectList(pageNum){
		currentPage = pageNum;
		
		//로딩 이미지 노출
		$('#loading').show();
		
		$.ajax({
			url:'listReply.do',
			type:'post',
			data:{pageNum:pageNum,course_num:$('#course_num').val(),order:$('#order').val()},
			dataType:'json',
			success:function(param){
				//로딩 이미지 감추기
				$('#loading').hide();
				
				//후기 개수 표시
	            let count = param.count;
	            $('.reply b').text(count);
	            $('.reply-avg .count').text(count);
	            $('.reply-count').text(count);
	            $('.reply-badge').text(count);
	            
	            //별점 5점 %
	            let star5_per = param.star5_per;
	            $('.reply-avg .star5_per').text(star5_per);
	            
	            //별점 평균
	            let star_avg = param.star_avg;
				if(count==0){
		            $('.star-avg').text('0.0');
				}else{
		            $('.star-avg').text(star_avg);
				}
	            
	            //호출시 해당 ID의 div의 내부 내용물 제거
	            $('#output').empty();
	            $('.paging-btn').empty();
				
				if(count==0){//후기가 없는 경우
					let output = '<div style="width:70%;margin:0 auto;">';
					output += '<div style="text-align:center;color:gray;">작성된 후기가 없습니다.</div>';
					output += '<hr size="2" noshade style="color:gray;">';
					output += '</div>';
					
					//문서 객체에 추가
					$('#output').append(output);
					
				}else{
					//후기 목록 작업
					$(param.list).each(function(index,item){
						let output = '<div class="wid"><span class="r-list-star">';
						for(let i=1;i<=item.star_auth;i++){
							output += '★';
						}
						output += '</span>';
						
						output += '<span class="r-list-fav" data-num="'+item.reply_num+'" data-cnum="'+item.course_num+'">';
						
						if(item.fav_num != 0) {
						output += '<i class="fa-regular fa-thumbs-up" style ="color :#FF4E02;transform:scale(1.2);margin-right:10px;"></i>';
						}
						if (item.fav_num == 0) {
						output += '<i class="fa-regular fa-thumbs-up style ="color :#000;"></i>';
						}					
						output += '<span class="favcount">'+item.fav_cnt+'</span>';
						output += '</span>';
						output += '</div>';
						
						output += '<div class="item">';
						output += '<ul class="detail-info">';
						output += '<li>';
						output += '<img src="../member/viewProfile.do?mem_num='+item.mem_num+'" width="50" height="50" class="my-photo">';
						output += '</li>';
						output += '<li>';
						if(item.mem_nickname){
							output += item.mem_nickname + '<br>';
						}else{
							output += item.mem_id + '<br>';
						}
						if(item.reply_mdate){
							output += '<span class="modify-date">' + item.reply_mdate + '</span>';
						}else{
							output += '<span class="modify-date">' + item.reply_date + '</span>';
						}
						output += '</li></ul>';
						output += '<div class="sub-item">';
						output += '<p>' + item.reply_content.replace(/\r\n/g,'<br>') + '</p>';
						if(item.reply_photo_name1!=null || item.reply_photo_name2!=null || item.reply_photo_name3!=null){
							output += '<div class="look-photo">사진 보기</div>';
						}
						output += '<p class="list-photos">';
						if(item.reply_photo_name1!=null){
							output += '<img src="replyImageView.do?reply_num='+item.reply_num+'&reply_type=1" data-photoName="'+item.reply_photo_name1+'" width="100" height="100" class="photo1 modal-p">';
						}
						if(item.reply_photo_name2!=null){
							output += '<img src="replyImageView.do?reply_num='+item.reply_num+'&reply_type=2" data-photoName="'+item.reply_photo_name2+'" width="100" height="100" class="photo2 modal-p">';
						}
						if(item.reply_photo_name3!=null){
							output += '<img src="replyImageView.do?reply_num='+item.reply_num+'&reply_type=3" data-photoName="'+item.reply_photo_name3+'" width="100" height="100" class="photo3 modal-p">';
						}
						output += '</p>';
						if(param.user_num == item.mem_num){
							output += '<div class="reply-btn">';
							output += ' <input type="button" data-num="'+item.reply_num+'" value="수정" class="modify-btn">';
							output += ' <input type="button" data-num="'+item.reply_num+'" value="삭제" class="delete-btn">';
							output += '</div>';
						}
						output += '</div>';//sub-item
						output += '</div>';//item
						output += '<hr size="1" noshade style="width:70%;margin:16px auto;color:gray;">';
						
						//모달창
						output += '<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">';
						output += '<div class="modal-dialog modal-dialog-centered">';
						output += '<div class="modal-content">';
						output += '<div class="modal-header">';
						output += '<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>';
						output += '</div>';
						output += '<div class="modal-body">';
						output += '<img src="" class="modal-img">';
						output += '</div>';
						output += '</div>';
						output += '</div>';
						output += '</div>';
						
						//문서 객체에 추가
						$('#output').append(output);
					});	
						
					//후기 페이징 처리	
					totalItem = param.count;
	                if(totalItem == 0){
	                   return;
	                }
	                
	                var totalPage = Math.ceil(totalItem/pageSize);
	                
	                if(currentPage == undefined || currentPage == ''){
	                   currentPage = 1;
	                }
	                //현재 페이지가 전체 페이지 수보다 크면 전체 페이지수로 설정
	                if(currentPage > totalPage){
	                   currentPage = totalPage;
	                }
	                
	                //시작 페이지와 마지막 페이지 값을 구하기
	                var startPage = Math.floor((currentPage-1)/pageBlock)*pageBlock + 1;
	                var endPage = startPage + pageBlock - 1;
	                
	                //마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정
	                if(endPage > totalPage){
	                   endPage = totalPage;
	                }
	               
	                var add='';
	                if(startPage>pageBlock){
	                   add += '<li data-page='+(startPage-1)+'><i class="fa-solid fa-chevron-left"></i></li>';
	                }
	                
	                for(var i=startPage;i<=endPage;i++){
						if(currentPage==i){
		                   add += '<li data-page='+i+' style="color:#FF4E02;"><b>'+i+'</b></li>';
						}else{
		                    add += '<li data-page='+i+'>'+i+'</li>';
						}
	                }
	                if(endPage < totalPage){
	                   add += '<li data-page='+(startPage+pageBlock)+'><i class="fa-solid fa-chevron-right"></i></li>';;
	                }
	                //ul 태그에 생성한 li를 추가
	                $('.paging-btn').append(add);
				}
				
			},
			error:function(){
				//로딩 이미지 감추기
				$('#loading').hide();
				alertIcon('error','네트워크 오류 발생');
			}
		});
		
	}
	
	
	
	//=========================================후기 등록================================================//
	
	//이미지 미리 보기
	function preview(course_photo,num){
		if(!course_photo){//취소한 경우
			$('.image .course-photo'+num).hide();	
			$('.image .l'+num).show();
			$('.image .d'+num).hide();
		}
		if(course_photo.size > 1024*1024){
			alert(Math.round(course_photo/1024) + 'kbytes(1024kbytes까지만 업로드 가능)');
			$('.image #upload'+num).val('');
			return;
		}
		
		let reader = new FileReader();
		reader.readAsDataURL(course_photo);
		
		reader.onload=function(){
			$('.image .course-photo'+num).show().attr('src',reader.result);	
			$('.image .l'+num).hide();
			$('.image .d'+num).show();
		};
	}
	
	//대표이미지
	$('.image #upload1').change(function(){
		course_photo = this.files[0];
		preview(course_photo,1);
	});//end of change
	
	//추가이미지1
	$('.image #upload2').change(function(){
		course_photo = this.files[0];
		preview(course_photo,2);
	});//end of change
	
	//추가이미지2
	$('.image #upload3').change(function(){
		course_photo = this.files[0];
		preview(course_photo,3);
	});//end of change
	
	
	//후기 목록 사진 모달
	document.addEventListener('click',function(e){
		if(e.target.classList.contains('modal-p')){
			let src = e.target.getAttribute('src');
			document.querySelector('.modal-img').src = src;
			const myModalAlternative = new bootstrap.Modal('#exampleModal');
			myModalAlternative.show();
		}
	});
	
	
	//후기 목록 사진보기 클릭
	$(document).on('click','.look-photo',function(){
		$(this).next().show();
		$(this).hide();
	});
	
	
	//후기 등록
	$('#reply_form').submit(function(event){
		//기본 이벤트 제거
		event.preventDefault();
		//별점 입력 필수
		if(!$('.reply_star input[type=radio]').is(':checked')){
			alertIcon('warning','별점을 입력하세요!');
			
			return false;
		}
		
		//내용 입력 필수
		if($('#reply_content').val().trim()==''){
			alertIcon('warning','내용을 입력하세요!');
			
			$('#reply_content').val('').focus();
			return false;
		}
		
		$.ajax({
			url:'writeReply.do',
			type:'post',
			data: new FormData($('#reply_form')[0]),
			dataType:'json',
			processData: false,
		    contentType: false,
            enctype:'multipart/form-data',
			success:function(param){
				if(param.result == 'logout'){
					alertIcon('warning','로그인해야 작성할 수 있습니다.');
				}else if(param.result == 'success'){
					//폼 초기화
					initForm();
					//등록된 데이터가 표시될 수 있도록 
					//목록 갱신
					selectList(1);
				}
			},
			error:function(){
				alertIcon('error','네트워크 오류 발생');
			}
		});
		
		
	});

	//후기 등록 폼 초기화
	function initForm(){
		$('textarea').val('');
		$('#reply_form .letter-count').text('300/300');
		
		$('.reply_star input[name=star_auth]').prop("checked",false);
		$('#upload1').val('');
		$('#upload2').val('');
		$('#upload3').val('');
		$('.image img').hide();
		$('.image .fa-circle-xmark').hide();
		$('.image .label1').show();
	}

	//textarea에 내용 입력시 글자수 체크
	$(document).on('keyup','textarea',function(){
		//입력한 글자수 구하기
		let inputLength = $(this).val().length;
		
		if(inputLength>300){//300자를 넘어선 경우
			$(this).val($(this).val().substring(0,300));
		}else{//300자 이하인 경우
			//남은 글자수 구하기
			let remain = 300 - inputLength;
			remain += ' / 300';
			if($(this).attr('id') == 'reply_content'){
				//등록 폼 글자수
				$('#reply_form .letter-count').text(remain);
			}else{
				//수정 폼 글자수
				$('#mreply_form .letter-count').text(remain);
			}
		}
		
	});
	
	
	
						
	
	//====================================후기 수정===========================================//
	//후기 수정 버튼 클릭시 수정폼 노출
	$(document).on('click','.modify-btn',function(){
		//댓글 글번호 
		let reply_num = $(this).attr('data-num');
		//댓글 내용
		let content = $(this).parents('.item').find('p').html().replace(/<br>/g,'\r\n');
		
		let photos_src={
			photo1:$(this).parents('.item').find('.photo1').attr('src'),
			photo2:$(this).parents('.item').find('.photo2').attr('src'),
			photo3:$(this).parents('.item').find('.photo3').attr('src')
		}
		let photos_name={
			photoName1:$(this).parents('.item').find('.photo1').attr('data-photoName'),
			photoName2:$(this).parents('.item').find('.photo2').attr('data-photoName'),
			photoName3:$(this).parents('.item').find('.photo3').attr('data-photoName')
		}
		
		//댓글수정 폼 UI
		let modifyUI = '<form id="mreply_form">';
		modifyUI += '<input type="hidden" name="reply_num" id="mreply_num" value="'+reply_num+'">';
		modifyUI += '<span class="letter-count mletter-count">300 / 300</span>';
		modifyUI += '<textarea rows="3" cols="50" name="reply_content" id="mreply_content" class="reply-content">'+content+'</textarea>';
		modifyUI += '<div class="m-image" style="position:relative;margin-top:10px;">';
		
			if(photos_src["photo1"]!=null){
				modifyUI += '<img src="'+photos_src["photo1"]+'" class="course-photo1" style="display:inline-block;">';
				modifyUI += '<label for="m_upload1" class="label1 l1" style="display:none;">';
				modifyUI += '<i class="fa-solid fa-circle-plus"></i><br>';
				modifyUI += '</label>';
				modifyUI += '<i class="fa-solid fa-circle-xmark d1" data-num="'+reply_num+'" data-type="1" data-photoName="'+photos_name["photoName1"]+'" style="display:inline-block;"></i>';
				modifyUI += '<input type="file" name="upload1" id="m_upload1" style="display:none;" accept="image/jpeg,image/png,image/gif">';
			}else{
				modifyUI += '<img class="course-photo1">';
				modifyUI += '<label for="m_upload1" class="label1 l1">';
				modifyUI += '<i class="fa-solid fa-circle-plus"></i><br>';
				modifyUI += '</label>';
				modifyUI += '<i class="fa-solid fa-circle-xmark d1"></i>';
				modifyUI += '<input type="file" name="upload1" id="m_upload1" style="display:none;" accept="image/jpeg,image/png,image/gif">';
			}
			if(photos_src["photo2"]!=null){
				modifyUI += '<img src="'+photos_src["photo2"]+'" class="course-photo2" style="display:inline-block;">';
				modifyUI += '<label for="m_upload2" class="label1 l2" style="display:none;">';
				modifyUI += '<i class="fa-solid fa-circle-plus"></i><br>';
				modifyUI += '</label>';
				modifyUI += '<i class="fa-solid fa-circle-xmark d2" data-num="'+reply_num+'" data-type="2" data-photoName="'+photos_name["photoName2"]+'" style="display:inline-block;"></i>';
				modifyUI += '<input type="file" name="upload2" id="m_upload2" style="display:none;" accept="image/jpeg,image/png,image/gif">';
			}else{
				modifyUI += '<img class="course-photo2">';
				modifyUI += '<label for="m_upload2" class="label1 l2">';
				modifyUI += '<i class="fa-solid fa-circle-plus"></i><br>';
				modifyUI += '</label>';
				modifyUI += '<i class="fa-solid fa-circle-xmark d2"></i>';
				modifyUI += '<input type="file" name="upload2" id="m_upload2" style="display:none;" accept="image/jpeg,image/png,image/gif">';
			}
			if(photos_src["photo3"]!=null){
				modifyUI += '<img src="'+photos_src["photo3"]+'" class="course-photo3" style="display:inline-block;">';
				modifyUI += '<label for="m_upload3" class="label1 l3" style="display:none;">';
				modifyUI += '<i class="fa-solid fa-circle-plus"></i><br>';
				modifyUI += '</label>';
				modifyUI += '<i class="fa-solid fa-circle-xmark d3" data-num="'+reply_num+'" data-type="3" data-photoName="'+photos_name["photoName3"]+'" style="display:inline-block;"></i>';
				modifyUI += '<input type="file" name="upload3" id="m_upload3" style="display:none;" accept="image/jpeg,image/png,image/gif">';
			}else{
				modifyUI += '<img class="course-photo3">';
				modifyUI += '<label for="m_upload3" class="label1 l3">';
				modifyUI += '<i class="fa-solid fa-circle-plus"></i><br>';
				modifyUI += '</label>';
				modifyUI += '<i class="fa-solid fa-circle-xmark d3"></i>';
				modifyUI += '<input type="file" name="upload3" id="m_upload3" style="display:none;" accept="image/jpeg,image/png,image/gif">';
			}
			modifyUI += '</li>';
			
		modifyUI += '<div style="position:absolute;top:0;right:0;">';
		modifyUI += ' <input type="button" value="취소" class="reply-reset">';
		modifyUI += '<input type="submit" value="수정" class="submit-btn">';
		modifyUI += '</div>';
		
		modifyUI += '</div>';
		modifyUI += '</form>';
		
		
		//이전에 이미 수정하는 댓글이 있을 경우 수정버튼을 클릭하면 숨김
		//sub-item을 환원시키고 수정 폼을 초기화함
		initModifyForm();
		//지금 클릭해서 수정하고자 하는 데이터는 감추기
		//수정버튼을 감싸고 있는 div
		$(this).parents('.sub-item').hide();
		$(this).parents().find('.r-list-fav').hide();
		
		//수정폼을 수정하고자 하는 데이터가 있는 div에 노출
		$(this).parents('.item').append(modifyUI);
		
		if(photos_src["photo1"]!=null){
			$(this).parents('.item').find('label').css('top','-60px');
		}
		
		//입력한 글자수 셋팅
		let inputLength = $('#mreply_content').val().length;
		let remain = 300 - inputLength;
		remain += ' / 300';
		
		//문서 객체에 반영
		$('#mreply_form .letter-count').text(remain);		
	});
	
	//댓글 수정 폼 초기화
	function initModifyForm(){
		$('#mreply_form').remove();
		$('.sub-item').show();
		$('.r-list-fav').show();
	}
	
	
	//선택한 이미지 미리보기
	function mPreview(course_photo,num){
		if(!course_photo){//취소한 경우
			$('.m-image .course-photo'+num).hide();	
			$('.m-image .l'+num).show();
			$('.m-image .d'+num).hide();
		}
		if(course_photo.size > 1024*1024){
			alert(Math.round(course_photo/1024) + 'kbytes(1024kbytes까지만 업로드 가능)');
			$('.m-image #m_upload'+num).val('');
			return;
		}
		
		let reader = new FileReader();
		reader.readAsDataURL(course_photo);
		
		reader.onload=function(){
			$('.m-image .course-photo'+num).show().attr('src',reader.result);	
			$('.m-image .l'+num).hide();
			$('.m-image .d'+num).show();
			$('.m-image label').css('top','-60px');
		};
	}
		
	//대표이미지
	$(document).on('change','#m_upload1',function(){
		//선택한 이미지 이름 저장
		let course_photo = this.files[0];
		mPreview(course_photo,1);
	});//end of change
	
	$(document).on('change','#m_upload2',function(){
		let course_photo = this.files[0];
		mPreview(course_photo,2);
	});//end of change
	
	$(document).on('change','#m_upload3',function(){
		let course_photo = this.files[0];
		mPreview(course_photo,3);
	});//end of change
	
	
	//수정폼에서 사진 삭제
	$(document).on('click','.fa-circle-xmark',function(){
		let xmark = $(this);
		Swal.fire({
            imageUrl:'/image_bundle/icon.png',
			imageWidth:200,
			imageHeight:130,
            title:'삭제하시겠습니까?',
            showCancelButton: true,
			cancelButtonText: "취소",
            cancelButtonColor: "lightgray",
            confirmButtonText: "확인",
            confirmButtonColor: "#FF4E02"
        }).then((result) => {
			  if (result.isConfirmed) {
				if(!xmark.attr('data-photoName')){//미리보기만 삭제
					xmark.prev().show();
					xmark.prev().prev().removeAttr('src');
					xmark.prev().prev().hide();
					xmark.next().val('');
					xmark.hide();
				}else{//데이터+미리보기 삭제
					$.ajax({
					url:'deleteReplyPhoto.do',
					data:{reply_num:xmark.attr('data-num'),photo_type:xmark.attr('data-type')},
					type:'post',
					dataType:'json',
					success:function(param){
						if(param.result == 'logout'){
							alert('warning','로그인 후 사용하세요');
						}else if(param.result == 'success'){
							xmark.prev().show();
							xmark.prev().prev().removeAttr('src');
							xmark.prev().prev().hide();
							xmark.hide();
							
							if(!xmark.parent().find('img').attr('src')){
								xmark.parent().find('label').css('top','0');
							}else{
								xmark.parent().find('label').css('top','-60px');
							}
						}else{
							alert('error','파일 삭제 오류 발생');
						}
					},
					error:function(){
						alert('error','네트워크 오류 발생');
					}
				});
				}
				
			}
		})
	});
	
	
	//================================================================
	//수정폼에서 취소 버튼 클릭시 수정폼 초기화
	$(document).on('click','.reply-reset',function(){
		initModifyForm();
		selectList(1);
	});
	
	
	//댓글 수정 처리
	$(document).on('submit','#mreply_form',function(event){
		//기본 이벤트 제거
		event.preventDefault();
		
		if($('#mreply_content').val().trim()==''){
			alertIcon('warning','내용을 입력하세요!');
			
			$('#mreply_content').val('').focus();
			return false;
		}
		
		//폼에 입력한 데이터 반환
		/*let form_data = $(this).serialize();*/
		
		//서버와 통신
		$.ajax({
			url:'updateReply.do',
			type:'post',
			data:new FormData($('#mreply_form')[0]),
			dataType:'json',
			processData: false,
		    contentType: false,
            enctype:'multipart/form-data',
			success:function(param){
				if(param.result=='logout'){
					alertIcon('warning','로그인 후 수정할 수 있습니다');
					
				}else if(param.result == 'success'){
					//수정 데이터 표시
					$('#mreply_form').parent().find('p').first().html($('#mreply_content').val()
		                   .replace(/</g,'&lt;')
                           .replace(/>/g,'&gt;')
                           .replace(/\r\n/g,'<br>')
                           .replace(/\r/g,'<br>')
                           .replace(/\n/g,'<br>'));
					//수정 이미지 표시
					$('#mreply_form').parent().find('.list-photos').empty();
					let src1 = $('#mreply_form .course-photo1').attr('src');
					let src2 = $('#mreply_form .course-photo2').attr('src');
					let src3 = $('#mreply_form .course-photo3').attr('src');
					let output = '';
					
					if(src1!=null){
						output += '<img src="'+src1+'" width="100" height="100" class="photo1 modal-p">';
					}
					if(src2!=null){
						output += '<img src="'+src2+'" width="100" height="100" class="photo2 modal-p">';
					}
					if(src3!=null){
						output += '<img src="'+src3+'" width="100" height="100" class="photo3 modal-p">';
					}
					$('#mreply_form').parent().find('.list-photos').append(output);
					
					//최근 수정일 표시
					$('#mreply_form').parent().find('.modify-date').text('최근 수정일 : 5초미만');
					
					//수정 폼 초기화
					initModifyForm();
				}else if(param.result == 'wrongAccess'){
					alertIcon('warning','타인의 후기을 수정할 수 없습니다');
				}else{
					alertIcon('error','후기수정 시 오류 발생!');
				}
			},
			error:function(){
				alertIcon('error','네트워크 오류 발생!');
			}
		});
		
	});
	

	
	//후기 삭제
	$(document).on('click','.delete-btn',function(){
		//댓글 번호
		let reply_num = $(this).attr('data-num');
		
		//서버와 통신
		$.ajax({
			url:'deleteReply.do',
			type:'post',
			data:{reply_num:reply_num},
			dataType:'json',
			success:function(param){
				if(param.result == 'logout'){
						alertIcon('warning','로그인 후 삭제할 수 있습니다');
				}else if(param.result == 'success'){
					alertImg('후기 삭제 완료!');
					
					selectList(1);
				}else if(param.result == 'wrongAccess'){
					alertIcon('warning','타인의 후기을 삭제할 수 없습니다');
				}else{
					alertIcon('error','후기삭제 시 오류 발생!');
				}
			},
			error:function(){
				alertIcon('error','네트워크 오류 발생!');
			}
		});
	});
	
	
	//후기 정렬
	$('#order').on('change',function(){
		selectList(1);
	});
	
	//페이지 버튼 이벤트 연결
	$(document).on('click','.paging-btn li',function(){
	   //페이지 번호를 읽어들임
	   currentPage = $(this).attr('data-page');

	   //목록 호출
	   selectList(currentPage);
	});
	
	
	//초기 데이터(목록) 호출
	selectList(1);
	
	
	
	
	
	
	
	
	
	//남은 인원수(수강인원수-예약인원수)
	let rest;
	
	//========================날짜========================//
	//클래스 요일 가져오기
	$('#datePicker').datepicker({
		dateFormat:'yy-mm-dd', //날짜 표시 형식
        showMonthAfterYear:true, //년도 먼저 나오고, 뒤에 월 표시
        yearSuffix: "년", //달력의 년도 부분 뒤에 붙는 텍스트
        monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'], //달력의 월 부분 텍스트
        monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'], //달력의 월 부분 Tooltip 텍스트
        dayNamesMin: ['일','월','화','수','목','금','토'], //달력의 요일 부분 텍스트
        dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'], //달력의 요일 부분 Tooltip 텍스트
        minDate: "0D", //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
        maxDate: "+1M", //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)
		//특정요일만 선택
		beforeShowDay: function(date){
			
			let day = date.getDay();
			let disabledDays = [];
			
			$('.day li').each(function(){
				disabledDays.push($(this).text());
			});
					
			//월,일을 2자리로 변경(1->01)
			let m = date.getMonth()+1;
			m = m < 10 ? 0+String(m) : String(m);
			let d = date.getDate();
			d = d < 10 ? 0+String(d) : String(d);
			let y = date.getFullYear();
			
			for (i = 0; i < disabledDays.length; i++) {
		        if($.inArray(y + '-' +(m) + '-' + d,disabledDays) != -1) {
		            return [false];
		        }
		    }
				//alert(disabledDays);
			return [!(day!=$('#datePicker').attr('data-mon')
					&&day!=$('#datePicker').attr('data-tues')
					&&day!=$('#datePicker').attr('data-wed')
					&&day!=$('#datePicker').attr('data-thur')
					&&day!=$('#datePicker').attr('data-fri')
					&&day!=$('#datePicker').attr('data-sat')
					&&day!=$('#datePicker').attr('data-sun'))]; 
			
		}  
	});
	
	
	//매진임박 표시,매진일 경우 비활성화
	function getReserved(timeVal){
		$.ajax({
			url:'getReserved.do',
			type:'post',
			data:{course_num:$('#course_num').val(),c_date:$('#datePicker').val(),c_time:timeVal},
			dataType:'json',
			success:function(param){
				//남은 인원수(수강인원수-예약인원수)
				rest = $('#course_limit').val()-param.reserved;
				
				//현재 예약한 인원수 표시
				$('.course-limit b').text(param.reserved);
				
				//현재 남은 인원수가 1~2명
				if(0 < rest && rest <3){
					//매진임박 표시
					$('.sold-out').show();
				}else{
					$('.sold-out').hide();
				}
			},
			error:function(){
				alertIcon('error','네트워크 오류 발생!');
			}
		});
	}
	
	//날짜를 바꿀 경우
	$(document).on('change','#datePicker',function(){
		//현재인원,예약인원,시간 입력창 활성화
		$('.reserved').show();
		$('#course_quan').attr('disabled',false);
		$('.time').attr('disabled',false);
		
		 
		let week = new Array('일', '월', '화', '수', '목', '금', '토');
		//선택한 날짜의 요일 구하기(0,1,2,3,4,5,6)
		let day = new Date($(this).val()).getDay();
		//숫자(1)를 문자(월)로 바꾸기
		day = week[day];
		
		let output = '';
		
		$.ajax({
			url:'getCourseTime.do',
			type:'post',
			data:{course_num:$('#course_num').val(),course_reg_date:day,c_date:$(this).val()},
			dataType:'json',
			success:function(param){
				//select 태그 안에 시간 표시
				$(param.course_reg_times).each(function(index,item){
					//매진된 시간이 있을 경우
					if(param.soldout_times.length!=0){
						$(param.soldout_times).each(function(index2,item2){
							if(item==item2){//값이 같으면 매진 -> 시간 비활성화
								output += '<option value="'+item+'" disabled>'+item+'</option>';
								return false;
							}
							if(index2+1==$(param.soldout_times).length){
								output += '<option value="'+item+'">'+item+'</option>';
							}
						});
					}else{
						//매진된 시간이 없을 경우
						output += '<option value="'+item+'">'+item+'</option>';		
					}
					
				});
				$('.time').html(output);
			
				
				//현재 예약 인원수 표시
				getReserved($('.time option:selected').text());
				
				$('.time').css('cursor','pointer');
				$('#course_quan').css('cursor','pointer');
				
			},
			error:function(){
				alertIcon('error','네트워크 오류 발생!');
			}
		});
	});


	//========================시간========================//
	//시간 선택시 해당 예약인원수 호출
	$(document).on('change','.time',function(){
		getReserved($('.time option:selected').val());
	});
	
	
	
	//========================예약인원========================//
	//초기가격
	let price = parseInt($('.price').text().replace(/,/g,''));
	
	//최소값 체크(1이상)
	function checkMin(){
		if($('#course_quan').val()<1){
			alertIcon('warning','1 이상 입력하세요!');
			$('#course_quan').val(1);
		}
		//가격 갱신(예약인원*초기가격)
		$('.price').text((parseInt($('#course_quan').val())*price).toLocaleString()+"원");
	}
	
	//최댓값 체크(수업인원수까지)
	function checkMax(){
		//원데이 클래스
		if($('#course_oneweek').val()=='one'){
			if($('#course_quan').val()>rest){
				alertIcon('warning','최대 '+ rest +'까지 입력하세요!');
				$('#course_quan').val(rest);
			}
		}
		
		//정기클래스
		if($('#course_oneweek').val()=='week'){
			//						수강인원수 - 현재 예약한 사람수
			rest = $('#course_limit').val() - $('.course-limit b').text();
			
			if($('#course_quan').val()>rest){
				alertIcon('warning','최대 '+ rest +'까지 입력하세요!');
				$('#course_quan').val(rest);
			}
		}
		
		
		//가격 갱신(예약인원*초기가격)
		$('.price').text((parseInt($('#course_quan').val())*price).toLocaleString()+"원");
	}
	
	//예약인원 입력창 비활성화
	function inactive(e){
		if($('#datePicker').val()==null || $('.time').val()==null){//날짜,시간을 선택하지 않은 경우
			alertIcon('warning','날짜를 먼저 선택하세요!');
			//기본이벤트 제거
			e.preventDefault()
		}
	}
	
	//-버튼 클릭시 최소값 체크
	$('#minus').on('click',function(){
		if($('#course_oneweek').val()=='one'){
			inactive();
		}
		
		let quan = parseInt($('#course_quan').val());
		$('#course_quan').val(--quan);
		
		checkMin();
	});
	
	
	//+버튼 클릭시 최댓값 체크
	$('#plus').on('click',function(){
		if($('#course_oneweek').val()=='one'){
			inactive();
		}
		
		let quan = parseInt($('#course_quan').val());
		$('#course_quan').val(++quan);
		
		checkMax();
	});

	//입력창에 직접 입력시 최소값,최댓값 체크
	$('#course_quan').on('keyup',function(){
		//지웠을 떈 작동 x
		if($(this).val()!=''){
			checkMin();
			checkMax();
		}
	});
	
	
	//----------------------예약하기----------------------//
	$('#course_cart').submit(function(){
		//날짜 유효성 체크
		if($('#datePicker').val()==''){
			alertIcon('warning','날짜를 선택하세요!');
			return false;
		}
		
		//예약인원 유효성 체크
		if($('#course_quan').val()==''){
			alertIcon('warning','예약인원을 선택하세요!');
			return false;
		}

	});
	
	
	//----------------------스크롤 시 소개,후기바/kakao map 고정----------------------//
	let origin = $('.scroll').offset().top;
	$(window).scroll(function(){
		//현재위치
		let cur = $(document).scrollTop();
		//특정 엘리먼트의 위치값
		let loc = $('.scroll').offset().top;
		//왼쪽 소개 bottom 위치값
		let bottom = $('.left-intro').offset().top+$('.left-intro').outerHeight();

		//소개,후기 바 고정x
		if(!$('.scroll').hasClass("fixed")){
			if(cur > loc - 170){
				$('.scroll').addClass("fixed");
				$('.c-content').css('margin-top','340px');
				
				//kakao map 고정
				$('.right-map').addClass("fixedMap");
			}
			
		//소개,후기 바 고정ㅇ
		}else{
			if(cur < origin - 170){
				$('.scroll').removeClass("fixed");
				$('.c-content').css('margin-top','100px');
				
				//kakao map 고정 해제
				$('.right-map').removeClass("fixedMap");
			}

			if(cur > bottom-850){
				$('.right-map').removeClass("fixedMap");
				$('#map').css('margin-top',$('.left-intro').height()-600);
			}else if(cur < bottom-850 && cur > origin-170){
				$('.right-map').addClass("fixedMap");
				$('#map').css('margin-top','0');
			}
		}
		
		/*
		let map =  $('#map').offset().top;
		//kakao map 고정
		if(!$('#map').hasClass("fixedMap")){
			if(cur > map - 290){
				alert(0);
				
			}
		}else{
			if(cur < map - 210){
				$('#map').removeClass("fixedMap");
			}
		}
		*/
				
	});
	
	
	
	//-----------------소개,후기 클릭시 위치로 이동-----------------//
	//소개
	$('.intro').on('click',function(){
		window.scrollTo({ top: 900, behavior: "smooth" });
	});
	//후기
	$('.rev').on('click',function(){
		window.scrollTo({ top: 3800, behavior: "smooth" });
	});
	
	
	
	
	
});




