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
		<form action="/course/getAddress.do">
			<ul>
				<li class="my-town town" data-ad="${address}">
					<i class="fa-solid fa-person-shelter"></i> 우리 동네 공방 찾기
				</li>
				<li id="sel">
					<select class="loc" id="sido" name="sido"></select>
					<select class="loc" id="gugun" name="gugun"></select>
				</li>
				<li style="position:relative;">
					<input type="text" name="keyword" id="keyword" maxlength="14">
					<button type="submit" id="map_search"><i class="fa-solid fa-magnifying-glass"></i></button>
				</li>
				<li><input type="button" class="form-btn" id="list_back" value="> 목록으로 돌아가기" onclick="location.href='${pageContext.request.contextPath}/course/courseList.do?cate=전체'"></li>
			</ul>
		</form>
	</div>
	<!-- 사이즈바 끝 -->
	
	<!-- 오른쪽 컨텐츠 시작 -->
	<div id="content" class="course-map">
		<div id="map"></div>
		<div class="here">현재 위치로</div>
	</div>
	<!-- 오른쪽 컨텐츠 끝 -->
</div>


<!-- ============================= kakao map ========================== -->
<script>
		var container = document.getElementById('map');
		var options = {
			center: new kakao.maps.LatLng(33.450701, 126.570667),
			level: 6 // 지도의 확대 레벨
		};

		var map = new kakao.maps.Map(container, options); // 지도를 생성합니다
		
		
		
		var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png', // 마커이미지의 주소입니다    
	    imageSize = new kakao.maps.Size(30, 40), // 마커이미지의 크기입니다
	    imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
	      
		
	    //DB에 저장된 현재 주소로 이동하는 함수
	    function getUserLocation() {
            if (!navigator.geolocation) {
                throw "위치 정보가 지원되지 않습니다.";
            }else{
            	
         // watchPosition()는 사용자의 현재 위치 정보를 요청합니다.
        	// getCurrentPosition()과 동일하지만, 사용자의 위치가 변경될 때마다 호출되는 점이 다릅니다.
            navigator.geolocation.watchPosition(success);
            }
        }
	    
		//DB에 저장된 현재 주소로 이동
        getUserLocation();
	    
	    
	    
	    
	    
	    
	    
		//현재 위치 클릭시, 현재 위치로 이동
		function success({ coords, timestamp }) {
			var latitude = coords.latitude;// 위도
			var longitude = coords.longitude;// 경도

			// 지도 중심을 이동 시킵니다
        	var moveLatLon = new kakao.maps.LatLng(latitude, longitude);
            map.setCenter(moveLatLon);
           

            // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
			var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
			    markerPosition = new kakao.maps.LatLng(latitude, longitude); // 마커가 표시될 위치입니다

			//현재 위치에 마커 표시
    	    var marker = new kakao.maps.Marker({
    	        position: markerPosition, 
    	        title:'현재 위치',
    	        image:markerImage 
    	    });
    	    marker.setMap(map); 
			
    	    
    	    
        }

        
		
		
		
		
        //====================클래스 전체 마커 표시==================//
        var MARKER_WIDTH = 33, // 기본, 클릭 마커의 너비
		    MARKER_HEIGHT = 36, // 기본, 클릭 마커의 높이
		    OFFSET_X = 12, // 기본, 클릭 마커의 기준 X좌표
		    OFFSET_Y = MARKER_HEIGHT, // 기본, 클릭 마커의 기준 Y좌표
		    OVER_MARKER_WIDTH = 40, // 오버 마커의 너비
		    OVER_MARKER_HEIGHT = 42, // 오버 마커의 높이
		    OVER_OFFSET_X = 13, // 오버 마커의 기준 X좌표
		    OVER_OFFSET_Y = OVER_MARKER_HEIGHT, // 오버 마커의 기준 Y좌표
		    SPRITE_MARKER_URL = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markers_sprites2.png', // 스프라이트 마커 이미지 URL
		    SPRITE_WIDTH = 126, // 스프라이트 이미지 너비
		    SPRITE_HEIGHT = 146, // 스프라이트 이미지 높이
		    SPRITE_GAP = 10; // 스프라이트 이미지에서 마커간 간격
		
		var markerSize = new kakao.maps.Size(MARKER_WIDTH, MARKER_HEIGHT), // 기본, 클릭 마커의 크기
		    markerOffset = new kakao.maps.Point(OFFSET_X, OFFSET_Y), // 기본, 클릭 마커의 기준좌표
		    overMarkerSize = new kakao.maps.Size(OVER_MARKER_WIDTH, OVER_MARKER_HEIGHT), // 오버 마커의 크기
		    overMarkerOffset = new kakao.maps.Point(OVER_OFFSET_X, OVER_OFFSET_Y), // 오버 마커의 기준 좌표
		    spriteImageSize = new kakao.maps.Size(SPRITE_WIDTH, SPRITE_HEIGHT); // 스프라이트 이미지의 크기
		
		var selectedMarker = null; // 클릭한 마커를 담을 변수
		    
        // 주소-좌표 변환 객체를 생성합니다
		var geocoder = new kakao.maps.services.Geocoder();
        
        $.ajax({
        	url:'getAddress.do',
			type:'post',
			dataType:'json',
			success:function(param){
				var ad = param.address;
				
				for(let idx in ad){
		        	// 주소로 좌표를 검색합니다
		    		geocoder.addressSearch(ad[idx], function(result, status) {
		    		    // 정상적으로 검색이 완료됐으면 
		    		     if (status === kakao.maps.services.Status.OK) {
								
								var gapX = (MARKER_WIDTH + SPRITE_GAP), // 스프라이트 이미지에서 마커로 사용할 이미지 X좌표 간격 값
							        originY = (MARKER_HEIGHT + SPRITE_GAP) * idx, // 스프라이트 이미지에서 기본, 클릭 마커로 사용할 Y좌표 값
							        overOriginY = (OVER_MARKER_HEIGHT + SPRITE_GAP) * idx, // 스프라이트 이미지에서 오버 마커로 사용할 Y좌표 값
							        normalOrigin = new kakao.maps.Point(0, originY), // 스프라이트 이미지에서 기본 마커로 사용할 영역의 좌상단 좌표
							        clickOrigin = new kakao.maps.Point(gapX, originY), // 스프라이트 이미지에서 마우스오버 마커로 사용할 영역의 좌상단 좌표
							        overOrigin = new kakao.maps.Point(gapX * 2, overOriginY); // 스프라이트 이미지에서 클릭 마커로 사용할 영역의 좌상단 좌표
							        
								//위도,경도 주소
			    		        var pos = new kakao.maps.LatLng(result[0].y, result[0].x);
							        
			    		        // 마커를 생성하고 지도위에 표시합니다
							    addMarker(pos, normalOrigin, overOrigin, clickOrigin);   
		    		console.log("============"+pos+","+normalOrigin+","+originY+","+clickOrigin);
		    		     }
		    		});
				}
				
			},
			error:function(){
				alert('네트워크 오류 발생!');
			}
        }); //ajax end
        
        
        // 마커를 생성하고 지도 위에 표시하고, 마커에 mouseover, mouseout, click 이벤트를 등록하는 함수입니다
        function addMarker(position, normalOrigin, overOrigin, clickOrigin) {
            // 기본 마커이미지, 오버 마커이미지, 클릭 마커이미지를 생성합니다
            var normalImage = createMarkerImage(markerSize, markerOffset, normalOrigin),
                overImage = createMarkerImage(overMarkerSize, overMarkerOffset, overOrigin),
                clickImage = createMarkerImage(markerSize, markerOffset, clickOrigin);
           
            // 마커를 생성하고 이미지는 기본 마커 이미지를 사용합니다
            var marker = new kakao.maps.Marker({
                map: map,
                position: position,
                image: normalImage
            });
            

            
            // 마커 객체에 마커아이디와 마커의 기본 이미지를 추가합니다
            marker.normalImage = normalImage;
            
            
            // 마커에 mouseover 이벤트를 등록합니다
            kakao.maps.event.addListener(marker, 'mouseover', function() {

                // 클릭된 마커가 없고, mouseover된 마커가 클릭된 마커가 아니면
                // 마커의 이미지를 오버 이미지로 변경합니다
                if (!selectedMarker || selectedMarker !== marker) {
                    marker.setImage(overImage);
                }
            });

            // 마커에 mouseout 이벤트를 등록합니다
            kakao.maps.event.addListener(marker, 'mouseout', function() {

                // 클릭된 마커가 없고, mouseout된 마커가 클릭된 마커가 아니면
                // 마커의 이미지를 기본 이미지로 변경합니다
                if (!selectedMarker || selectedMarker !== marker) {
                    marker.setImage(normalImage);
                }
            });

            // 마커에 click 이벤트를 등록합니다
            kakao.maps.event.addListener(marker, 'click', function() {

                // 클릭된 마커가 없고, click 마커가 클릭된 마커가 아니면
                // 마커의 이미지를 클릭 이미지로 변경합니다
                if (!selectedMarker || selectedMarker !== marker) {

                    // 클릭된 마커 객체가 null이 아니면
                    // 클릭된 마커의 이미지를 기본 이미지로 변경하고
                    !!selectedMarker && selectedMarker.setImage(selectedMarker.normalImage);

                    // 현재 클릭된 마커의 이미지는 클릭 이미지로 변경합니다
                    marker.setImage(clickImage);
                }

                // 클릭된 마커를 현재 클릭된 마커 객체로 설정합니다
                selectedMarker = marker;
            });
        }
        
        
        // MarkerImage 객체를 생성하여 반환하는 함수입니다
        function createMarkerImage(markerSize, offset, spriteOrigin) {
            var markerImage = new kakao.maps.MarkerImage(
                SPRITE_MARKER_URL, // 스프라이트 마커 이미지 URL
                markerSize, // 마커의 크기
                {
                    offset: offset, // 마커 이미지에서의 기준 좌표
                    spriteOrigin: spriteOrigin, // 스트라이프 이미지 중 사용할 영역의 좌상단 좌표
                    spriteSize: spriteImageSize // 스프라이트 이미지의 크기
                }
            );
            
            return markerImage;
        }
       
        
        
        
        
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
	    	
	    
</script>

<!-- 중앙 컨텐츠 끝 -->