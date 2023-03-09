$(function(){
	let message_socket;//웹소켓 식별자
	//채팅방 채팅 멤버 저장 배열
	//채팅방 생성자도 연결되어 있어야한다.(로그인한 사람의 id를 연결해서 배열에 전달=나도,채팅방에 참여한 사람도 포함)
	let member_list = [$('#user').attr('data-id')];
	
	//웹소켓 연결
	function alarm_connect(){
	}
	alarm_connect();
	
	//=======채팅회원검색======//
	//검색창이 비워져 있다고 알려줌 
	$('#member_search').keyup(function(){
		if($('#member_search').val().trim()==''){
			$('#search_area').empty();
			return;
		}
		
		  
		$.ajax({
			url:'memberSearchAjax.do',
			type:'post',
			data:{mem_nickname:$('#member_search').val()},
			dataType:'json',
			success:function(param){
				if(param.result == 'logout'){ //로그아웃 인 경우 
					$('#member_search').attr('disabled',true); //검색창을 비활성화 시키고 
					$('#member_search').val('로그인해야 회원검색이 가능합니다.'); //input태그에 표시 
				}else if(param.result == 'success'){
					$('#search_area').empty(); //기존의 데이터는 지우고
					$(param.member).each(function(index,item){ //새로운 데이터를 가져온다(list형태로 온걸 표시)
						if(!member_list.includes(item.mem_nickname)){ //data-id에 들어가있는 데이터는 검색하지 못하도록함
							//채팅회원배열에 포함되어있지 않는 id만 표시
							//<li> 태그에 input 태그 넣어서 표시
							let output = '';
							output += '<li data-num="'+item.mem_num+'">';
							output += item.mem_nickname;
							output += '</li>';
							$('#search_area').append(output);
						}
					});
				}else{
					alert('채팅회원검색시 오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
		
		
	});
	
	//검색된 회원 선택하기
	$(document).on('click','#search_area li',function(){
		let mem_nickname = $(this).text();//선택한 id
		let mem_num = $(this).attr('data-num');//선택한 회원번호
		//선택한 id를 배열에 저장
		member_list.push(mem_nickname);
		//선택한 id를 화면에 표시
		let choice_id = '<div class="member-span" data-id="'+mem_nickname+'">';
		//실제로 전송할 목록 
		choice_id += '<input type="hidden" name="members" value="'+mem_num+'">';
		choice_id += mem_nickname + '<button type="button" class="close">&times;</button></div>';
		$('#talk_member').append(choice_id);
		//초기화
		$('#member_search').val('');
		$('#search_area').empty();
		
		if($('#name_checked').is(':checked')){
			//이름 자동 생성
			makeTalkroom_name();
		}
	});
	
	//채팅방 이름 생성
	function makeTalkroom_name(){
		let name = '';
		//배열에서 저장된 아이디를 뽑아내서 채팅방을 만듬 (자동으로 만들 경우)
		$.each(member_list,function(index,item){
			//너무 길면 안되기 때문에 제한을 둠 
			//아이디를 명시한 다음, 명시 
			if(index>0) name += ',';
			name += item;
		});
		if(name.length>55){
			name = name.substring(0,55) + '...';
		}
		$('#talkroom_name').val(name);
		$('#name_span').text(name);
	}
	
	//선택한 ID 삭제
	$(document).on('click','.member-span',function(){
		let mem_nickname = $(this).attr('data-id');
		//배열에서 ID 삭제
		member_list.splice(member_list.indexOf(mem_nickname),1);
		//화면상에서 span 태그 제거
		$(this).remove();
		
		if($('#name_checked').is(':checked')){
			//이름 자동 생성
			makeTalkroom_name();
		}
		if($('#talk_member span').length == 0){
			$('#name_span').text('');
			$('#talkroom_name').val('');
		}
		
		
	});
	
	//채팅방 이름 생성 제어
	$('#name_checked').click(function(){
		if($('#name_checked').is(':checked')){ //체크가 되어있으면 true가 됨
			//채팅방 이름 자동 생성
			$('#talkroom_name').attr('type','hidden');
			if(member_list.length>1){ //만든 사람(1)일때 채팅방이 들어가지 않음 
				makeTalkroom_name(); //만듬
			}
		}else{
			//채팅방 이름 수동 생성
			$('#talkroom_name').attr('type','text');
			$('#name_span').text('');//채팅방 이름 표시 텍스트 초기화
		}
	});
	//채팅방 생성 전송
	$('#talk_form').submit(function(){
		if(member_list.length<=1){
			//이미 배열에 현재 로그인한 유저가 기본 등록되어 
			//있어서 로그인한 유저 포함 최소 2명이 되어야
			//채팅 가능
			Swal.fire({
                    icon: 'warning',
                    title:'채팅에 참여할 회원을 <br> 검색하세요!',
                    showCancelButton: false,
                    confirmButtonText: "확인",
                    confirmButtonColor: "#FF4E02"
                });
			$('#member_search').focus();
			return false;
		}
	});
	
	
	
});




