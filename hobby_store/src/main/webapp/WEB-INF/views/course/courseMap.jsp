<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="${pageContext.request.contextPath}/css/course.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/course.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=fefd68452eb1631196f5d667fac06edf&libraries=services"></script>

<!-- 중앙 컨텐츠 시작 -->
<div id="course_main">
	<!-- 사이즈바 시작 -->
	<div id="sidebar">
		<form id="map_form">
			<ul>
				<li class="my-town town" data-ad="${address}">
					<i class="fa-solid fa-person-shelter"></i> 우리 동네 공방 찾기
				</li>
				<li id="sel">
					<select class="loc" id="sido" name="sido"></select>
					<select class="loc" id="gugun" name="gugun"></select>
				</li>
				<li style="position:relative;">
					<input type="text" name="keyword" id="keyword" maxlength="30" placeholder="키워드 (ex 쿠키,캔들)">
					<button type="submit" id="map_search"><i class="fa-solid fa-magnifying-glass"></i></button>
				</li>
				<li id="here">현재 위치로 이동하기</li>
				<li><input type="button" class="form-btn" id="list_back" value="> 목록으로 돌아가기" onclick="location.href='${pageContext.request.contextPath}/course/courseList.do?cate=전체'"></li>
			</ul>
		</form>
	</div>
	<!-- 사이즈바 끝 -->
	
	<!-- 오른쪽 컨텐츠 시작 -->
	<div id="content" class="course-map">
		<div id="map"></div>
		<div id="menu_wrap" class="bg_white" >
	        <ul id="placesList"></ul>
	        <div id="pagination"></div>
	    </div>
		<div class="to-my-town">우리 동네로</div>
	</div>
	<!-- 오른쪽 컨텐츠 끝 -->
</div>


<script>

//===================지역 select,option 목록=====================//
		var sido = ["시/도 선택","서울","인천","대전","광주","대구","울산","부산","경기","강원","충북","충남","전북","전남","경북","경남","제주"];
		var gugun = new Array();
		gugun[1] = ["강남구","강동구","강북구","강서구","관악구","광진구","구로구","금천구","노원구","도봉구","동대문구","동작구","마포구","서대문구","서초구","성동구","성북구","송파구","양천구","영등포구","용산구","은평구","종로구","중구","중랑구"];
		gugun[2] = ["계양구","남구","남동구","동구","부평구","서구","연수구","중구","강화군","옹진군"];
		gugun[3] = ["대덕구","동구","서구","유성구","중구"];
		gugun[4] = ["광산구","남구","동구","북구","서구"];
		gugun[5] = ["남구","달서구","동구","북구","서구","수성구","중구","달성군"];
		gugun[6] = ["남구","동구","북구","중구","울주군"];
		gugun[7] = ["강서구","금정구","남구","동구","동래구","부산진구","북구","사상구","사하구","서구","수영구","연제구","영도구","중구","해운대구","기장군"];
		gugun[8] = ["고양시","과천시","광명시","광주시","구리시","군포시","김포시","남양주시","동두천시","부천시","성남시","수원시","시흥시","안산시","안성시","안양시","양주시","오산시","용인시","의왕시","의정부시","이천시","파주시","평택시","포천시","하남시","화성시","가평군","양평군","여주군","연천군"];
		gugun[9] = ["강릉시","동해시","삼척시","속초시","원주시","춘천시","태백시","고성군","양구군","양양군","영월군","인제군","정선군","철원군","평창군","홍천군","화천군","횡성군"];
		gugun[10] = ["제천시","청주시","충주시","괴산군","단양군","보은군","영동군","옥천군","음성군","증평군","진천군","청원군"];
		gugun[11] = ["계룡시","공주시","논산시","보령시","서산시","아산시","천안시","금산군","당진군","부여군","서천군","연기군","예산군","청양군","태안군","홍성군"];
		gugun[12] = ["군산시","김제시","남원시","익산시","전주시","정읍시","고창군","무주군","부안군","순창군","완주군","임실군","장수군","진안군"];
		gugun[13] = ["광양시","나주시","목포시","순천시","여수시","강진군","고흥군","곡성군","구례군","담양군","무안군","보성군","신안군","영광군","영암군","완도군","장성군","장흥군","진도군","함평군","해남군","화순군"];
		gugun[14] = ["경산시","경주시","구미시","김천시","문경시","상주시","안동시","영주시","영천시","포항시","고령군","군위군","봉화군","성주군","영덕군","영양군","예천군","울릉군","울진군","의성군","청도군","청송군","칠곡군"];
		gugun[15] = ["거제시","김해시","마산시","밀양시","사천시","양산시","진주시","진해시","창원시","통영시","거창군","고성군","남해군","산청군","의령군","창녕군","하동군","함안군","함양군","합천군"];
		gugun[16] = ["서귀포시","제주시","남제주군","북제주군"];
		
		
		//시,도 option 표시
		$.each(sido,function(){
			$('#sido').append("<option value='"+this+"'>"+this+"</option>");
		});
		$('#gugun').append("<option>구/군 선택</option>");
		
		//시,도 선택시 구,군 option표시
		$(document).on('change','#sido',function(){
			var idx = $('#sido option').index($('#sido option:selected'));
			//구,군 option 초기화
			$('#gugun').empty();
			
			if(idx==0){
				 $('#gugun').append("<option>구/군 선택</option>");
			}else{
				$.each(gugun[idx],function(){
			    	$('#gugun').append("<option value='"+this+"'>"+this+"</option>");
			    });
			}
		});
		



<!-- ============================= kakao map ========================== -->
		var container = document.getElementById('map');
		var options = {
			center: new kakao.maps.LatLng(37.56667, 126.97806),
			level: 5 // 지도의 확대 레벨
		};

		// 지도를 생성합니다
		var map = new kakao.maps.Map(container, options); 
		
		// 마커이미지의 주소입니다 
		var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png',    
	    imageSize = new kakao.maps.Size(30, 40), // 마커이미지의 크기입니다
	    imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

	    //현재주소의 마커
	    var add_marker= [];
	    //현재위치의 마커
	    var here_marker = [];
		// 마커를 담을 배열입니다
	    var markers = [];
	    
		// 주소-좌표 변환 객체를 생성합니다
		var geocoder = new kakao.maps.services.Geocoder();
	    
		// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
		var infowindow = new kakao.maps.InfoWindow({zIndex:1});
	    
		//현재 주소의 LatLng 저장
		var add_LatLng = '';
	    
		// 장소 검색 객체를 생성합니다
		var ps = new kakao.maps.services.Places();  
	    
	   
		
		
		
		//주소 목록 가져오는 함수
		function getAddress(sido,gugun,keyword){
			
			var arr = {};
			
			//검색목록 초기화
			delSearch();
			
			$.ajax({
				url:'getAddress.do',
				type:'post',
				async: false,
				data:{sido:sido,gugun:gugun,keyword:keyword},
				dataType:'json',
				success:function(param){
					
					arr = param.list;
				},
				error:function(){
					alert('네트워크 오류 발생!');
				}
			});
			
			return arr;
		}
		
		
		

		// 키워드 검색을 요청하는 함수입니다
		function searchPlaces(sido,gugun,keyword,title) {
			
			
		   	//주소와 공방이름 가져오기
		   	var arr = getAddress(sido,gugun.substring(0,3),keyword);
		   	
		   	if(arr.length!=0){
		   		
		   		//loc_data 배열 초기화
			   	loc_data.splice(0, loc_data.length);
			   	
			   	$(arr).each(function(index,item){

			   		var kw = item.course_address1 + " " + item.mem_store;
			   		
				    // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
				    ps.keywordSearch(kw,placesSearchCB); 
			   	});
			
			//DB에 검색 결과가 없을 경우
		   	}else{

			   		var address = sido + " " + gugun;
			   		
			   		geocoder.addressSearch(address, function(result, status) {
		    		    // 정상적으로 검색이 완료됐으면 
		    		     if (status === kakao.maps.services.Status.OK) {
		    		    	 
		    		    	// 지도 중심을 이동
	    		        	var moveLatLon = new kakao.maps.LatLng(result[0].y, result[0].x);
	    		            map.panTo(moveLatLon);	
		    		    	 
		    		   	 }
			   		});
		   			   		
		   		
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
		}

		
		
		
		
		var loc_data = [];
		
		// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
		function placesSearchCB(data, status, pagination) {
		    if (status === kakao.maps.services.Status.OK) {
		    	 
		    	 for ( var i=0; i<data.length; i++ ) {
		    		 loc_data.push(data[i]);
		    	 }
		    	 
	    		// 정상적으로 검색이 완료됐으면
	 	        // 검색 목록과 마커를 표출합니다
	 	        displayPlaces(loc_data);


	 	        // 페이지 번호를 표출합니다
	 	        displayPagination(pagination);

		    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {

		        return;

		    } else if (status === kakao.maps.services.Status.ERROR) {

		        alert('검색 결과 중 오류가 발생했습니다.');
		        return;

		    }
		}
		
		// 검색 결과 목록과 마커를 표출하는 함수입니다
		function displayPlaces(places) {
		    var listEl = document.getElementById('placesList'),
		    menuEl = document.getElementById('menu_wrap'),
		    fragment = document.createDocumentFragment(), 
		    bounds = new kakao.maps.LatLngBounds(), 
		    listStr = '';
			
		    $('#menu_wrap').show();
		    
		 	// 검색 결과 목록에 추가된 항목들을 제거합니다
		    removeAllChildNods(listEl);
			
		    // 지도에 표시되고 있는 마커를 제거합니다
		    removeMarker(); 
		    

		    for ( var i=0; i<places.length; i++ ) {
				
		        // 마커를 생성하고 지도에 표시합니다
		        var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
		            marker = addMarker(placePosition, i), 
		            itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

		        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
		        // LatLngBounds 객체에 좌표를 추가합니다
		        bounds.extend(placePosition);
				
		        // 마커와 검색결과 항목에 mouseover 했을때
		        // 해당 장소에 인포윈도우에 장소명을 표시합니다
		        // mouseout 했을 때는 인포윈도우를 닫습니다
		        (function(marker, title) {
		            kakao.maps.event.addListener(marker, 'mouseover', function() {
		                displayInfowindow(marker, title);
		            });

		            kakao.maps.event.addListener(marker, 'mouseout', function() {
		                infowindow.close();
		            });

		            itemEl.onmouseover =  function () {
		                displayInfowindow(marker, title);
		            };

		            itemEl.onmouseout =  function () {
		                infowindow.close();
		            };
		        })(marker, places[i].place_name);

		        fragment.appendChild(itemEl);
		    }

		    
		    // 검색결과 항목들을 검색결과 목록 Element에 추가합니다
		    listEl.appendChild(fragment);
		    menuEl.scrollTop = 0;

		    //현재위치와 우리동네 클릭할 경우, 현재위치 또는 현재주소 좌표추가
		    if($('#sido').val()=='시/도 선택' && $('#keyword').val().trim()==''){
		    	bounds.extend(add_LatLng);
		    }
		    
		    // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
		    map.setBounds(bounds);
		}

		
		
		// 검색결과 항목을 Element로 반환하는 함수입니다
		function getListItem(index, places) {

		    var el = document.createElement('li'),
		    itemStr = '<span class="markerbg marker_' + (index+1) + '"></span>' +
		                '<div class="info">' +
		                '   <h5>' + places.place_name + '</h5>';

		    if (places.road_address_name) {
		        itemStr += '    <span>' + places.road_address_name + '</span>' +
		                    '   <span class="jibun gray">' +  places.address_name  + '</span>';
		    } else {
		        itemStr += '    <span>' +  places.address_name  + '</span>'; 
		    }
		                 
		      itemStr += '  <span class="tel">' + places.phone  + '</span>' +
		                '</div>';           

		    el.innerHTML = itemStr;
		    el.className = 'item';

		    return el;
		}

		// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
		function addMarker(position, idx, title) {
		    var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
		        imageSize = new kakao.maps.Size(36, 37),  // 마커 이미지의 크기
		        imgOptions =  {
		            spriteSize : new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
		            spriteOrigin : new kakao.maps.Point(0, (idx*46)+10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
		            offset: new kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
		        },
		        markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
		            marker = new kakao.maps.Marker({
		            position: position, // 마커의 위치
		            image: markerImage 
		        });

		    marker.setMap(map); // 지도 위에 마커를 표출합니다
		    markers.push(marker);  // 배열에 생성된 마커를 추가합니다

		    return marker;
		}

		// 지도 위에 표시되고 있는 마커를 모두 제거합니다
		function removeMarker() {
		    for ( var i = 0; i < markers.length; i++ ) {
		        markers[i].setMap(null);
		    }   
		    markers = [];
		}

		// 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
		function displayPagination(pagination) {
		    var paginationEl = document.getElementById('pagination'),
		        fragment = document.createDocumentFragment(),
		        i; 

		    // 기존에 추가된 페이지번호를 삭제합니다
		    while (paginationEl.hasChildNodes()) {
		        paginationEl.removeChild (paginationEl.lastChild);
		    }

		    for (i=1; i<=pagination.last; i++) {
		        var el = document.createElement('a');
		        el.href = "#";
		        el.innerHTML = i;

		        if (i===pagination.current) {
		            el.className = 'on';
		        } else {
		            el.onclick = (function(i) {
		                return function() {
		                    pagination.gotoPage(i);
		                }
		            })(i);
		        }

		        fragment.appendChild(el);
		    }
		    paginationEl.appendChild(fragment);
		}

		// 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
		// 인포윈도우에 장소명을 표시합니다
		function displayInfowindow(marker, title) {
		    var content = '<div style="padding:5px;z-index:1;">' + title + '</div>';

		    infowindow.setContent(content);
		    infowindow.open(map, marker);
		}

		 // 검색결과 목록의 자식 Element를 제거하는 함수입니다
		function removeAllChildNods(el) {   
		    while (el.hasChildNodes()) {
		        el.removeChild (el.lastChild);
		    }
		}
		
		
		
		//검색목록 초기화하는 함수
		function delSearch(){
			var listEl = document.getElementById('placesList');
			// 검색 결과 목록에 추가된 항목들을 제거합니다
		    removeAllChildNods(listEl);
			
			$('#menu_wrap').hide();

		    // 지도에 표시되고 있는 마커를 제거합니다
		    removeMarker(); 
		    
		}
		
        
		
		
		
		
		
		
	    
		
		
		
		
		
		function delMarker(){
			
			//현재위치 마커 삭제
			if(here_marker.length!=0){
		   		here_marker.setMap(null);
		   		here_marker = [];
			}
			//현재주소 마커 삭제
			if(add_marker.length!=0){
		   		add_marker.setMap(null);
		   		add_marker = [];
			}
		}
		
		
		//시,도 변경시 해당 주소 목록 가져오기
		$('#sido').on('change',function(){
			
			//현재위치 또는 현재주소 마커 삭제
			delMarker();
			
			//시,도에 해당하는 구,군 배열의 첫번째 값 가져오기
			$.each(sido,function(index,value){
				if(value==$('#sido').val()){
					
					searchPlaces($('#sido').val(),gugun[index][0],null,'검색 결과가 없습니다');
				}
			});
			
		});
	    
		//구,군 변경시 해당 주소 목록 가져오기
		$('#gugun').on('change',function(){
			
			searchPlaces($('#sido').val(),$('#gugun').val(),null,'검색 결과가 없습니다');
		});
		
		
		
		
		//submit 클릭시
		$('#map_form').on('submit',function(){
			//현재위치 또는 현재주소 마커 삭제
			delMarker();
			
			var keyword = $('#keyword').val();
			
		    if (!keyword.replace(/^\s+|\s+$/g, '')) {
		        alert('키워드를 입력해주세요!');
		        return false;
		    }
		    
		    searchPlaces($('#sido').val(),$('#gugun').val(),keyword,'검색 결과가 없습니다');
		    
		    return false;
		});
		 
		 
		
		
		
		
		
		 
		 //====================현재 위치로 이동하기==================//
		 //좌표를 주소로 변환하는 함수
		 function searchAddrFromCoords(coords, callback) {
		    // 좌표로 행정동 주소 정보를 요청합니다
		    geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);         
		 }
		 
		 
		 // 주소정보를 표출하는 함수입니다
		 function displayAddrInfo(result, status) {
		     if (status === kakao.maps.services.Status.OK) {

		         for(var i = 0; i < result.length; i++) {
		                 var addr = result[i].address.address_name;
		                 
		                 //현재 위치 근처 공방 표시
		                 searchPlaces(addr.substring(0,2),addr.substring(3),null,'근처에 공방이 없습니다');
		                 
		         }
		     }    
		 }
		 
		 
		 //현재위치,현재주소 클릭시 검색 초기화 
		 function initSearch(){
				//검색목록 초기화
				delSearch();
				//지역검색 초기화
			    $('#sido option:first-child').prop('selected','selected');
			    $('#gugun').empty();
			    $('#gugun').append('<option>구/군 선택</option>');
			 	//키워드 초기화
				$('#keyword').val('');
		 }
		 
		 
		 
		 //현재 위치 클릭시, 현재 위치로 이동하는 함수
		 function success({ coords, timestamp }) {
			
			initSearch();
			
			if(add_marker.length!=0){
				add_marker.setMap(null);
				add_marker = [];
			}
			
			var latitude = coords.latitude;// 위도
			var longitude = coords.longitude;// 경도
			
           
            // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
			var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
			    markerPosition = new kakao.maps.LatLng(latitude, longitude); // 마커가 표시될 위치입니다
			//현재 위치에 마커 표시
    	    here_marker = new kakao.maps.Marker({
    	        position: markerPosition, 
    	        image:markerImage 
    	    });
			    
			    
    	    kakao.maps.event.addListener(here_marker, 'mouseover', function() {
                displayInfowindow(here_marker, '현재 위치');
            });
            kakao.maps.event.addListener(here_marker, 'mouseout', function() {
                infowindow.close();
            });
            
    	    here_marker.setMap(map); 
    	    
    	    //좌표를 주소로 변환
    	    searchAddrFromCoords(markerPosition, displayAddrInfo);
    		
		}
		 
	
		 
		 
		$('#here').on('click',function(){
			
			// watchPosition()는 한번 실행하면 직접 종료하기 전까지 위치를 계속 알려준다.
        	// getCurrentPosition()는 함수를 호출할 때마다 현재 위치를 알려준다.
            navigator.geolocation.getCurrentPosition(success);
		});
		
		
		
		
		
		
	    
		//====================우리 동네로 이동하기==================//
		//DB에 저장된 현재 주소로 이동하는 함수
	    function getUserLocation() {
            if (!navigator.geolocation) {
                throw "위치 정보가 지원되지 않습니다.";
            }else{
	         	
            	initSearch();
            	
	            var address = $('.my-town').attr('data-ad');	
				
	            geocoder.addressSearch(address, function(result, status) {
	            	
	    		    // 정상적으로 검색이 완료됐으면 
	    		     if (status === kakao.maps.services.Status.OK) {
		    		    	
	    		            //변수에 현재 주소의 LatLng 저장
	    		    	 	add_LatLng = new kakao.maps.LatLng(result[0].y, result[0].x);
	    		    	 	
	    		            //현재 주소에 마커 표시
	    		    	    add_marker = new kakao.maps.Marker({
	    		    	        position: add_LatLng, 
	    		    	        image:new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption) 
	    		    	    });
	    		    	    
	    		    	    kakao.maps.event.addListener(add_marker, 'mouseover', function() {
	    		                displayInfowindow(add_marker, '내 주소');
	    		            });
	    		            kakao.maps.event.addListener(add_marker, 'mouseout', function() {
	    		                infowindow.close();
	    		            });
	    		            add_marker.setMap(map); 
	    		            
	    		            //현재 주소 근처 공방 표시
	    		            searchPlaces(address.substring(0,2),address.substring(3),null,'근처에 공방이 없습니다');
	            	
		    		    	
	    		     }
	    		});
	            
            }
        }
		
		
		//'우리 동네로' 버튼 클릭시, 회원 주소로 이동
	   	$('.to-my-town').on('click',function(){
	   		
	   		//현재위치 마커 삭제
	   		if(here_marker.length!=0){
	   			here_marker.setMap(null);
	   		}
	   		
	   		getUserLocation();
	   	});
		
		
		//DB에 저장된 현재 주소로 이동
        getUserLocation();
		
		
		
</script>

<!-- 중앙 컨텐츠 끝 -->