$(function() {
	//강사님 이게 뭘까요..
	let pageSize = 4;//화면에 보여줄 레코드 수
	let pageBlock = 3;//페이지 표시 단위
	let currentPage;//현재 보고 있는 화면
	let totalItem;//총 레코드 수


	//후기 목록
	function selectList(pageNum) {
		currentPage = pageNum;
		//로딩 이미지 노출
		$('#loading').show();
	
		$.ajax({
			url: 'itemsReplyList.do',
			type: 'post',
			data: { pageNum: pageNum, items_num: $('#items_num').val() },
			dataType: 'json',
			success: function(param) {
	
				var count = param.itemsReply;
				//로딩 이미지 감추기
				$('#loading').hide();

			
				//별점 평균
				$('#starAvg').text(param.itemsStar);
				//후기 개수
				$('#reply').text(param.itemsReply);
				$('#reply_1').text('후기 ' + param.itemsReply + ' 개');
				$('#reply_2').text(param.itemsReply + ' 건의 후기 중');

				$('#star_per').text(param.star5_per + '% 의 고객이 5점을 주었어요 !');

				$('#starAvg2').append(param.itemsStar);



				//후기 목록 작업
				$(param.list).each(function(index, item) {
					let output = '<div class="item">';
					output += '<ul class="detail-info">';
					output += '<li>';
					output += '<img src="../member/viewProfile.do?mem_num=' + item.mem_num + '" width="40" height="40" class="my-photo">';
					output += '</li>';
					output += '<li>';
					if (item.mem_nickname) {
						output += item.mem_nickname + '<br>';
					} else {
						output += item.mem_id + '<br>';
					}
					if (item.reply_mdate) {
						output += '<span class="modify-date">최근 수정일 : ' + item.reply_mdate + '</span>';
					} else {
						output += '<span class="modify-date">등록일 : ' + item.reply_date + '</span>';
					}
					output += '</li>';
					output += '</ul>';
					output += '<div class="sub-item">';
					output += '<p>' +item.reply_content.replace(/\r\n/g, '<br>') + '</p>';
					output += '<span id = "red-heart" class = "red-heart" data-num="' + item.reply_num + '">';
					output += '<i class = "fa-regular fa-heart">';
					output += '<span class = "heart-count" id = "heart-count">' + item.favcount + '</span></span>';
					if (item.reply_photo_name1 != null) {
						output += '<img src="replyImageView.do?reply_num=' + item.reply_num + '&reply_type=1" width="100" height="100" class="phto1">';
					}
					if (item.reply_photo_name2 != null) {
						output += '<img src="replyImageView.do?reply_num=' + item.reply_num + '&reply_type=2" width="100" height="100" class="phto2">';
					}
					if (item.reply_photo_name3 != null) {
						output += '<img src="replyImageView.do?reply_num=' + item.reply_num + '&reply_type=3" width="100" height="100" class="phto3">';
					}
					output += '</div>';
					if (param.user_num == item.mem_num) {
						output += ' <input type="button" data-num="' + item.reply_num + '" value="수정" class="modify-btn">';
						output += ' <input type="button" data-num="' + item.reply_num + '" value="삭제" class="delete-btn">';
					}
					output += '<hr size="1" noshade>'
					output += '</div>';
					output += '</div>';
					
				
					//문서 객체에 추가
					$('#output').append(output);



				});
				//페이지 처리 
				totalItem = param.itemsReply;
				if (totalItem == 0) {
					return;
				}

				var totalPage = Math.ceil(totalItem / pageSize);

				if (currentPage == undefined || currentPage == '') {
					currentPage = 1;
				}
				//현재 페이지가 전체 페이지 수보다 크면 전체 페이지수로 설정
				if (currentPage > totalPage) {
					currentPage = totalPage;
				}

				//시작 페이지와 마지막 페이지 값을 구하기
				var startPage =
					Math.floor((currentPage - 1) / pageBlock) * pageBlock + 1;
				var endPage = startPage + pageBlock - 1;

				//마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정
				if (endPage > totalPage) {
					endPage = totalPage;
				}

				var add = '';
				if (startPage > pageBlock) {
					add += '<li data-page=' + (startPage - 1) + '>[이전]</li>';
				}

				for (var i = startPage; i <= endPage; i++) {
					add += '<li data-page=' + i + '>' + i + '</li>';
				}
				if (endPage < totalPage) {
					add += '<li data-page=' + (startPage + pageBlock) + '>[다음]</li>';;
				}
				//ul 태그에 생성한 li를 추가
				$('.paging-btn').append(add);


			},
			errror: function() {
				//로딩 이미지 감추기
				$('#loading').hide();
				alert('네트워크 오류 발생');
			}
		});
	}

	//후기 작성
	$('#reply-form').submit(function(event) {
		event.preventDefault();

		if ($('#reply_content ').val().trim() == '') {
			alert('리뷰 작성해');
			$('리뷰 작성 내용').val('').focus();
			return false;
		}
		//후기 작성에 필요한 데이터들을 form_data에 담아 한번에 보냄
		//let form_data = $(this).serialize();
		$.ajax({
			url: 'itemsReply.do',
			type: 'post',
			data: new FormData($('#reply-form')[0]),
			dataType: 'json',
			processData: false,
			contentType: false,
			success: function(param) {
				if (param.result == 'logout') {
					alert('리뷰는 로그인 후 작성할 수 있습니다.');
				}
				else if (param.result == 'success') {
					//폼 초기화
					initForm();
					//리뷰 목록 갱신
					selectList(1);
				}
			}, error: function() {
				alert('네트워크 오류 발생');
			}

		});
		//댓글 작성 폼 초기화
		function initForm() {
			$('textarea').val('');
			$('#reply-form .letter-count').text('300/300');
		}
		//textarea 글자수 체크
		$(document).on('keyup', 'textarea', function() {
			//입력 글자 수 구하기
			let inputLength = $(this).val().length;

			if (inputLength > 300) {
				$(this).val($(this).val().substring(0, 300));
			}
			else {//300자 이하인 경우
				let remain = 300 - inputLength;
				remain += '/300';
				if ($(this).attr('id') == 'reply_content') {
					$('#reply-form .letter-count').text(remain);
				}
				else {
					$('#mreply-form .letter-count').text(remain);;
				}
			}
		});

	});
	selectList(1);

	//댓글 수정
	//댓글 수정 버튼 클릭시 수정폼 노출
	$(document).on('click', '.modify-btn', function() {
		//댓글 글번호 
		let re_num = $(this).attr('data-num');
		//댓글 내용
		let content = $(this).parents('.item').find('p').html().replace(/<br>/g, '\r\n');



		//댓글수정 폼 UI
		let modifyUI = '<form id="mre_form">';
		modifyUI += '<input type="hidden" name="re_num" id="mre_num" value="' + re_num + '">';
		modifyUI += '<textarea rows="3" cols="50" name="re_content" id="mre_content" class="col-auto form-control">' + content + '</textarea>';
		modifyUI += '<div id="mre_first"><span class="letter-count">300/300</span></div>';
		for (let i = 1; i < 3; i++) {
			modifyUI += '<li>';
			modifyUI += '<img src="replyImageView.do?reply_num=' + re_num + '&reply_type=' + i + '" class="phto' + i + ' width="100" height="100"">';
		}
		modifyUI += '<div id="mre_second" class="align-right">';
		modifyUI += '<input type="submit" value="수정">';
		modifyUI += ' <input type="button" value="취소" class="re-reset">';
		modifyUI += '</div>';
		modifyUI += '<hr size="1" noshade width="96%">';
		modifyUI += '</form>';

		//이전에 이미 수정하는 댓글이 있을 경우 수정버튼을 클릭하면
		//숨김 sub-item을 환원시키고 수정 폼을 초기화함
		initModifyForm();
		//지금 클릭해서 수정하고자 하는 데이터는 감추기
		//수정버튼을 감싸고 있는 div
		$(this).parents('.sub-item').hide();

		//수정폼을 수정하고자 하는 데이터가 있는 div에 노출
		$(this).parents('.item').append(modifyUI);

		//입력한 글자수 셋팅
		let inputLength = $('#mre_content').val().length;
		let remain = 300 - inputLength;
		remain += '/300';

		//문서 객체에 반영
		$('#mre_first .letter-count').text(remain);
	});

	//수정폼에서 취소 버튼 클릭시 수정폼 초기화
	$(document).on('click', '.re-reset', function() {
		initModifyForm();
	});
	//댓글 수정 폼 초기화
	function initModifyForm() {
		$('.sub-item').show();
		$('#mre_form').remove();
	}
	//댓글 수정 처리
	$(document).on('submit', '#mre_form', function(event) {
		//기본 이벤트 제거
		event.preventDefault();

		if ($('#mre_content').val().trim() == '') {
			alert('내용을 입력하세요!');
			$('#mre_content').val('').focus();
			return false;
		}

		//폼에 입력한 데이터 반환


		//서버와 통신
		$.ajax({
			url: 'itemsUpdateReply.do',
			type: 'post',
			data: new FormData($('#reply_form')[0]),
			dataType: 'json',
			processData: false,
			contentType: false,
			success: function(param) {
				if (param.result == 'logout') {
					alert('로그인해야 수정할 수 있습니다.');
				} else if (param.result == 'success') {
					//수정 데이터 표시
					$('#mre_form').parent().find('p')
						.html($('#mre_content').val()
							.replace(/</g, '&lt;')
							.replace(/>/g, '&gt;')
							.replace(/\r\n/g, '<br>')
							.replace(/\r/g, '<br>')
							.replace(/\n/g, '<br>'));
					//최근 수정일 표시
					$('#mre_form').parent().find('.modify-date')
						.text('최근 수정일 : 5초미만');
					//수정 폼 초기화
					initModifyForm();
				} else if (param.result == 'wrongAccess') {
					alert('타인의 글은 수정할 수 없습니다.');
				} else {
					alert('댓글 수정시 오류 발생');
				}
			},
			error: function() {
				alert('네트워크 오류 발생');
			}
		});

	});


	//좋아요 읽기
	//좋아요 선택 여부와 선택한 총개수 표시
	function selectFav(reply_num) {
		$.ajax({
			url: 'replyGetFav.do',
			type: 'post',
			data: { reply_num: reply_num },
			dataType: 'json',
			success: function(param) {
				displayFav(param);
				console.log(param);
			},
			error: function() {
				alert('네트워크 오류');
			}
		});
	}





	//좋아요 등록 		
	$(document).on('click', '.red-heart', function() {
		let heart = $(this);
		$.ajax({
			url: 'replyWriteFav.do',
			type: 'post',
			data: { reply_num: $(this).attr('data-num') },
			dataType: 'json',
			success: function(param) {
				console.log(param)
				if (param.result == 'logout') {
					alert('로그인 후 좋아요를 누를 수 있습니다.');
				}
				else if (param.result == 'success') {
					if (param.status == 'yesFav') {
						heart.find('.fa-heart').css('font-weight', 'bold');
						heart.parent().find('.heart-count').text(param.count);

					} else {
						heart.find('.fa-heart').css('font-weight', 'normal');
						heart.parent().find('.heart-count').text(param.count);

					}
				}
			},
			error: function() {
				alert('왜 안되냐고');
			}
		});
	});
	//좋아요 표시, 좋아요 개수 표시 공통 함수
	function displayFav(param) {
		if (param.status == 'yesFav') {
			$('.fa-heart').css('font-weight', 'bold');
		} else {
			$('.fa-heart').css('font-weight', 'normal');
		}
		$('#heart-count').text(param.count);
	}

	selectFav($('.red-heart').attr('data-num'));

});