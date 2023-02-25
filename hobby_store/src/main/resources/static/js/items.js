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

});