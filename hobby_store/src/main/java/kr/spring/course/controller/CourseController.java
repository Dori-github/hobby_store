package kr.spring.course.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.GeocoderStatus;
import com.google.code.geocoder.model.LatLng;

import kr.spring.course.service.CourseService;
import kr.spring.course.vo.CourseFavVO;
import kr.spring.course.vo.CourseReplyFavVO;
import kr.spring.course.vo.CourseReplyVO;
import kr.spring.course.vo.CourseTimeVO;
import kr.spring.course.vo.CourseVO;
import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.order.service.OrderService;
import kr.spring.order.vo.OrderDetailVO;
import kr.spring.util.PagingUtil;

@Controller
public class CourseController {
	private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

	//private int rowCount=12; 
	
	@Autowired
	private CourseService courseService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private MemberService memberService;
	
	//자바빈(VO) 초기화
	@ModelAttribute
	public CourseVO initCommand() {
		return new CourseVO();
	}

	
	//=============글쓰기==============//
	//등록 폼 호출
	@GetMapping("/course/courseWrite.do")
	public ModelAndView form() {
		
		List<CourseVO> course_cate = null;
		course_cate = courseService.selectCate();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("courseWrite");
		mav.addObject("course_cate",course_cate);
		
		return mav;
	}
	
	//등록 폼에서 전송된 데이터 처리
	@PostMapping("/course/courseWrite.do")
	public ModelAndView submit(@Valid CourseVO courseVO,BindingResult result,
							HttpServletRequest request,HttpSession session) {
		logger.debug("<<등록courseVO>> : " + courseVO);
		
		//클래스 이미지 유효성 체크
		//MultipartFile -> byte[]로 변환할 경우 파일을
		//업로드하지 않으면 byte[]은 생성되고 length는 0
		if(courseVO.getCourse_photo1().length==0) {
			result.rejectValue("course_photo1", "required");
		}
		//이미지용량체크
		if(courseVO.getCourse_photo1().length > 5*1024*1024) {//5MB
			result.reject("limitUploadSize",new Object[] {"5MB"},null);
		}
		if(courseVO.getCourse_photo2().length > 5*1024*1024) {//5MB
			result.reject("limitUploadSize",new Object[] {"5MB"},null);
		}
		if(courseVO.getCourse_photo3().length > 5*1024*1024) {//5MB
			result.reject("limitUploadSize",new Object[] {"5MB"},null);
		}
		
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			logger.debug("<<강의 등록 오류 >> :" + result.getFieldErrors());
			return form();
		}
		
		//session에 저장된 회원번호를 VO에 저장 
		MemberVO user = (MemberVO)session.getAttribute("user");
		courseVO.setMem_num(user.getMem_num());
		
		//카테고리
		//대분류 카테고리 번호
		int cate_parent = courseVO.getCate_parent();
		//상세 카테고리 번호
		int cate_num = courseService.selectCate_num(courseVO.getCate_name());
		
		courseVO.setCate_nums(cate_parent+"" + "," + cate_num+"");
		logger.debug("<<cate_nums>> :" + cate_parent + "," + cate_num);
		
		//클래스 데이터 등록
		courseService.insertCourse(courseVO);
		
		//View에 표시할 메시지
		ModelAndView mav = new ModelAndView();
		mav.setViewName("common/resultView");
		mav.addObject("message","클래스 등록이 완료되었습니다.");
		mav.addObject("url",request.getContextPath()+"/course/courseList.do?cate=전체");
		
		return mav;
	}
	
	
	
	//=============클래스 목록===============//
	@RequestMapping("/course/courseList.do")
	public ModelAndView process(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
								@RequestParam(value="order",defaultValue="1") String order,
								String cate,
								@RequestParam(value="onoff",defaultValue="0") String onoff,
								@RequestParam(value="oneweek",defaultValue="0") String oneweek,
								@RequestParam(value="location",defaultValue="전체") String location,
								String keyfield,String keyword,HttpSession session) {
		
		//카테고리 고유번호 저장
		int cate_num;
		if(cate.equals("전체")) {
			cate_num = 0;
		}else {
			cate_num = courseService.selectCate_num(cate);
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		//클래스 표시 여부
		map.put("status", 1);
		map.put("onoff", onoff);
		map.put("oneweek", oneweek);
		map.put("cate", cate);
		map.put("cate_num", cate_num);
		map.put("location", location);
		map.put("order", order);
		
		//글의 총개수 또는 검색된 글의 개수
		int count = courseService.selectCourseCount(map);
		
		logger.debug("<<클래스 목록 개수>> : " + count);
		
		//페이지 처리
		PagingUtil page = new PagingUtil(keyfield,keyword,currentPage, count, 12, 3, 
				"courseList.do","&cate="+cate+"&onoff="+onoff+"&oneweek="+oneweek+"&location="+location+"&order="+order);

		List<CourseVO> list = null;
		if(count>0) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());

			list = courseService.selectCourseList(map);
			
			logger.debug("<<리스트>> : " + list);
		}
		
		//카테고리 목록
		List<CourseVO> course_cate = null;
		course_cate = courseService.selectCate();
		
		//로그인한 회원정보
		MemberVO user = (MemberVO)session.getAttribute("user");
				
		//로그인한 회원이 누른 하트 체크
		List<CourseVO> favCheck = courseService.selectFavCheck();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("courseList");
		mav.addObject("course_cate",course_cate);
		mav.addObject("count",count);
		mav.addObject("list",list);
		mav.addObject("page",page.getPage());
		mav.addObject("user",user);
		mav.addObject("favCheck",favCheck);
		
		return mav;
	}
	
	
	
	//==========클래스 kakao map 검색============//
	@RequestMapping("/course/courseMap.do")
	public ModelAndView courseMap(HttpSession session) {
		
		//session에 저장된 회원번호 가져오기 
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		MemberVO member = memberService.selectMember(user.getMem_num());
		String address = member.getMem_address1();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("courseMap");
		mav.addObject("address",address);
		
		return mav;
	}
		
	//==========클래스 kakao map 주소 가져오기============//
	@RequestMapping("/course/getAddress.do")
	@ResponseBody
	public Map<String,Object> getAddress(String sido,String gugun,String keyword) {
		
		logger.debug("<<sido,gugun,keyword>> :" + sido + "," + gugun + "," + keyword);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sido", sido);
		map.put("gugun", gugun);
		map.put("keyword",keyword);
		List<CourseVO> list = courseService.selectCourseAddress(map);
		logger.debug("<<====================리스트>> : " + list);
		
		Map<String,Object> mapJson = new HashMap<String,Object>();
		mapJson.put("list", list);
		
		return mapJson;
	}	
		
	
	
	
	
	//이미지 출력
	@RequestMapping("/course/imageView.do")
	public ModelAndView viewImage(@RequestParam int course_num,@RequestParam int item_type) {
		
		CourseVO courseVO = courseService.selectCourse(course_num);
		
		logger.debug("<<이미지courseVO>> :" + courseVO);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		
		if(item_type==1) {
			mav.addObject("imageFile", courseVO.getCourse_photo1());
			mav.addObject("filename", courseVO.getCourse_photo_name1());
		}else if(item_type==2) {
			mav.addObject("imageFile", courseVO.getCourse_photo2());
			mav.addObject("filename", courseVO.getCourse_photo_name2());
		}else if(item_type==3) {
			mav.addObject("imageFile", courseVO.getCourse_photo3());
			mav.addObject("filename", courseVO.getCourse_photo_name3());
		}
		
		return mav;
	}
	
	

	

	
	
	
	//클래스 요일,시간 List에 저장하는 함수
	public List<CourseTimeVO> getCourseTime(int course_num) {
		List<CourseTimeVO> course_time = courseService.selectCourseTime(course_num);
		for(int i=0;i<course_time.size();i++) {
			//콤마 기준으로 쪼개서 배열에 저장
			String[] array_times = course_time.get(i).getCourse_reg_time().split(",");
			//배열에 담긴 데이터를 리스트에 저장
			List<String> course_reg_times = Arrays.asList(array_times);
			course_time.get(i).setCourse_reg_times(course_reg_times);
		}
		return course_time;
	}
	
	
	//==========클래스 글상세============//
	@RequestMapping("/course/courseDetail.do")
	public String detail(@RequestParam int course_num,Model model) {
		logger.debug("<<클래스상세>> : " + course_num);
		
		//조회수 증가
		courseService.updateHit(course_num);
		
		//클래스 정보 저장
		CourseVO course = courseService.selectCourse(course_num);
		logger.debug("<<==========================course>> : " + course);
		model.addAttribute("course", course);
		
		//클래스 요일,시간 저장
		List<CourseTimeVO> course_time = getCourseTime(course_num);
		model.addAttribute("course_time",course_time);
		
		//매진된 날짜 리스트
		List<OrderDetailVO> soldOut = orderService.selectSoldOutDates(course_num);
		
		List<String> days = new ArrayList<String>();
		
		for(OrderDetailVO item : soldOut) {
			for(CourseTimeVO item2 : course_time) {
				if(item.getCourse_reg_date().equals(item2.getCourse_reg_date())){//같은 요일에 해당하는 시간 수 구하기
					int time_cnt = item2.getCourse_reg_times().size();
					//               수강인원*시간수
					if(item.getRes_sum()==course.getCourse_limit()*time_cnt){//해당 날짜 매진
						
						days.add(item.getC_date());
						
					}
				}	
			}
		}
		model.addAttribute("days",days);
		
		//정기클래스 총 예약인원수
		int reserved = orderService.selectReservedNum2(course_num);
		model.addAttribute("reserved",reserved);
		
		return "courseView";
	}
	
	
	
	
	
	
	//===============상세 클래스시간 호출=============//
	@RequestMapping("/course/getCourseTime.do")
	@ResponseBody
	public Map<String,Object> getTime(@RequestParam int course_num,String course_reg_date,String c_date) {
		Map<String,Object> mapJson = new HashMap<String,Object>();
		
		logger.debug("<<============course_reg_date============>> : "+course_num+","+course_reg_date+","+c_date);
		
		//선택한 요일에 해당하는 시간 배열에 저장
		CourseTimeVO time = courseService.selectCourseTimes(course_num,course_reg_date);
		//콤마 기준으로 쪼개서 배열에 저장
		String[] array_times = time.getCourse_reg_time().split(",");
		//배열에 담긴 데이터를 리스트에 저장
		List<String> course_reg_times = Arrays.asList(array_times);
		
		
		//매진된 시간
		List<String> soldout_times = orderService.selectSoldOutTimes(course_num,c_date);
		
		
		mapJson.put("course_reg_times", course_reg_times);
		mapJson.put("soldout_times", soldout_times);
		logger.debug("<<course_reg_times>> :" + course_reg_times);
		logger.debug("<<soldout_times>> :" + soldout_times);
		return mapJson;
	}
	
		
	//===============현재 예약한 인원수체크=============//
	@RequestMapping("/course/getReserved.do")
	@ResponseBody
	public Map<String,Object> getReservedNum(@RequestParam int course_num,String c_date,String c_time) {
		Map<String,Object> mapJson = new HashMap<String,Object>();
		
		logger.debug("<<============c_date,c_time============>> : "+c_date+","+c_time);
		//현재 예약한 인원수
		int reserved = orderService.selectReservedNum(course_num,c_date,c_time);
		
		mapJson.put("reserved",reserved);
		return mapJson;
	}
	
	
	//=========클래스 글 수정===========//
	//수정 폼 호출
	@GetMapping("/course/update.do")
	public String formUpdate(@RequestParam int course_num,Model model) {
		CourseVO courseVO = courseService.selectCourse(course_num);
		logger.debug("<<수정courseVO>> : " + courseVO);
		
		//클래스 요일,시간 저장
		List<CourseTimeVO> course_time = getCourseTime(course_num);
		logger.debug("<<수정course_time>> : " + course_time);
		
		List<CourseVO> course_cate = null;
		course_cate = courseService.selectCate();
		
		model.addAttribute("courseVO",courseVO);
		model.addAttribute("course_time",course_time);
		model.addAttribute("course_cate",course_cate);
		
		return "courseModify";
	}
	//수정 폼에서 전송된 데이터 처리
	@PostMapping("/course/courseUpdate.do")
	public String submitUpdate(@Valid CourseVO courseVO,
			                   BindingResult result,
			                   HttpServletRequest request,
			                   Model model) {
		
		logger.debug("<<글수정>> : " + courseVO);
		logger.debug("<<업로드 파일 용량1>> : "+courseVO.getCourse_photo1().length);
		logger.debug("<<업로드 파일 용량2>> : "+courseVO.getCourse_photo2().length);
		logger.debug("<<업로드 파일 용량3>> : "+courseVO.getCourse_photo3().length);

		if(courseVO.getCourse_photo1().length > 5*1024*1024) {//5MB
			result.reject("limitUploadSize",new Object[]{"5MB"},null);
		}
		if(courseVO.getCourse_photo2().length > 5*1024*1024) {//5MB
			result.reject("limitUploadSize",new Object[]{"5MB"},null);
		}
		if(courseVO.getCourse_photo3().length > 5*1024*1024) {//5MB
			result.reject("limitUploadSize",new Object[]{"5MB"},null);
		}
		
		//유효성 체크 결과 오류가 있으면 폼을 호출
		if(result.hasErrors()) {
			//title 또는 content가 입력되지 않아서 유효성
			//체크에 걸리면 파일 정보를 잃어버리기 때문에
			//폼을 호출할 때 파일 정보를 다시 셋팅
			/*
			 * CourseVO vo = courseService.selectCourse(courseVO.getCourse_num());
			 * courseVO.setCourse_photo_name1(vo.getCourse_photo_name1());
			 * courseVO.setCourse_photo_name2(vo.getCourse_photo_name2());
			 * courseVO.setCourse_photo_name3(vo.getCourse_photo_name3());
			 */
			return formUpdate(courseVO.getCourse_num(),model);
		}
		
		//카테고리
		//대분류 카테고리 번호
		int cate_parent = courseVO.getCate_parent();
		//상세 카테고리 번호
		int cate_num = courseService.selectCate_num(courseVO.getCate_name());
		
		courseVO.setCate_nums(cate_parent+"" + "," + cate_num+"");
		logger.debug("<<cate_nums>> :" + cate_parent + "," + cate_num);
		
		//글수정
		courseService.updateCourse(courseVO);
		
		//View에 표시할 메시지
		model.addAttribute("message", "글수정을 완료했습니다");
		model.addAttribute("url", request.getContextPath()+"/course/courseDetail.do?course_num="+courseVO.getCourse_num());
		
		return "common/resultView";
	}
	
	
	
	//클래스 사진 삭제
	@RequestMapping("/course/deletePhoto.do")
	@ResponseBody
	public Map<String,String> deletePhoto(int course_num,int photo_type,HttpSession session){
		Map<String,String> mapJson = new HashMap<String,String>();
		
		logger.debug("<<수정폼 course_num>> : " + course_num);
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user==null) {
			mapJson.put("result", "logout");
		}else {
			if(photo_type==2) {
				courseService.deletePhoto2(course_num);
			}else if(photo_type==3){
				courseService.deletePhoto3(course_num);
			}
			mapJson.put("result", "success");
		}
		return mapJson;
	}

	
	//===========클래스 글삭제============//
	@RequestMapping("/course/delete.do")
	public String deleteCourse(@RequestParam int course_num,Model model,HttpServletRequest request) {
		
		//몽땅 삭제 	
		courseService.deleteCourseWithAll(course_num);
		
		//View에 표시할 메시지
		model.addAttribute("message", "글삭제를 완료했습니다");
		model.addAttribute("url", request.getContextPath()+"/course/courseList.do?cate=전체");
		
		return "common/resultView";
	}

	
	
	
	//===============좋아요================//
	//좋아요 읽기
	@RequestMapping("/course/getFav.do")
	@ResponseBody
	public Map<String,Object> getFav(CourseFavVO fav, HttpSession session){
		logger.debug("<<클래스 좋아요>> : " + fav);
		
		Map<String,Object> mapJson = new HashMap<String,Object>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user==null) {
			mapJson.put("status", "noFav");
		}else {
			//로그인된 아이디 셋팅
			fav.setFmem_num(user.getMem_num());
			
			CourseFavVO courseFav = courseService.selectFav(fav);
			if(courseFav!=null) {
				mapJson.put("status", "yesFav");
			}else {
				mapJson.put("status", "noFav");
			}
		}
		mapJson.put("count", courseService.selectFavCount(fav.getCourse_num()));
		
		return mapJson;
	}
	
	//클래스 좋아요 등록
	@RequestMapping("/course/writeFav.do")
	@ResponseBody
	public Map<String,Object> writeFav(CourseFavVO fav,HttpSession session){
		Map<String,Object> mapJson = new HashMap<String,Object>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user==null) {
			mapJson.put("result", "logout");
		}else {
			//로그인된 회원번호 셋팅
			fav.setFmem_num(user.getMem_num());
			
			logger.debug("<<부모글 좋아요 등록>> : " + fav);
			
			CourseFavVO courseFav = courseService.selectFav(fav);
			if(courseFav!=null) {
				//좋아요가 이미 등록되어있으면 삭제
				courseService.deleteFav(courseFav.getFav_num());
				
				mapJson.put("result", "success");
				mapJson.put("status", "noFav");
			}else {
				//좋아요 미등록이면 등록
				courseService.insertFav(fav);
				
				mapJson.put("result", "success");
				mapJson.put("status", "yesFav");
			}
			mapJson.put("count", courseService.selectFavCount(fav.getCourse_num()));
		}
		return mapJson;
	}
	
	
	
	//===============후기================//
	//후기등록
	@RequestMapping("/course/writeReply.do")
	@ResponseBody
	public Map<String,String> writeReply(CourseReplyVO vo,HttpSession session,HttpServletRequest request){
		
		logger.debug("<<댓글 등록>> : " + vo);
		
		Map<String,String> mapJson = new HashMap<String,String>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user==null) {
			//로그인 안 됨
			mapJson.put("result","logout");
		}else {
			//회원번호 등록
			vo.setMem_num(user.getMem_num());
			//댓글 등록
			courseService.insertReply(vo);
			mapJson.put("result", "success");
		}
		return mapJson;
	}
	
	
	//후기목록
	@RequestMapping("/course/listReply.do")
	@ResponseBody
	public Map<String,Object> getList(@RequestParam(value="pageNum",defaultValue="1") int currentPage,@RequestParam(value="order",defaultValue="1") int order,
										@RequestParam int course_num,HttpSession session){
		
		logger.debug("<<currentPage>> : " + currentPage);
		logger.debug("<<course_num>> : " + course_num);
		logger.debug("<<order>> : " + order);
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		logger.debug("<<---------user>> : " + user);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("course_num", course_num);
		map.put("order", order);
		//로그인 한 경우
		if(user!=null) { 
			map.put("mem_num",user.getMem_num());
		}

		
		//후기 개수
		int count = courseService.selectReplyCount(map);
		//별점 평균
		Float star_avg = courseService.selectStar(course_num);
		logger.debug("<<star_avg>> : " + star_avg);
		//별점 5점 %
		int star5 = courseService.select5star(course_num);
		int starall = courseService.selectallstar(course_num);
		float star5_per = Math.round((float)star5/starall*100);
		
		//페이지 처리
		PagingUtil page = new PagingUtil(currentPage,count,5,3,null);
		map.put("start", page.getStartRow());
		map.put("end", page.getEndRow());
		
		//목록 데이터 읽기
		List<CourseReplyVO> list = null;
		if(count > 0) {
			list = courseService.selectListReply(map);
		}else {
			list = Collections.emptyList();
		}
		
		
		Map<String,Object> mapJson = new HashMap<String,Object>();
		mapJson.put("list", list);
		mapJson.put("count", count);
		mapJson.put("star_avg", star_avg);
		mapJson.put("star5_per", star5_per);
		
		//===== 로그인 한 회원정보 셋팅 =====//
		if(user!=null) {
			mapJson.put("user_num", user.getMem_num());
		}	
		
		return mapJson;
	}

	
	
	//후기 이미지 출력
	@RequestMapping("/course/replyImageView.do")
	public ModelAndView viewReplyImage(@RequestParam int reply_num,@RequestParam int reply_type) {
		
		CourseReplyVO vo = courseService.selectReply(reply_num);
		
		logger.debug("<<==============================courseReplyVO>> :" + vo);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		
		if(reply_type==1 && vo.getReply_photo_name1()!=null) {
				mav.addObject("imageFile", vo.getReply_photo1());
				mav.addObject("filename", vo.getReply_photo_name1());
		}
		if(reply_type==2) {
			if(vo.getReply_photo_name2()!=null) {
				mav.addObject("imageFile", vo.getReply_photo2());
				mav.addObject("filename", vo.getReply_photo_name2());
			}
		}
		if(reply_type==3) {
			if(vo.getReply_photo_name3()!=null) {
				mav.addObject("imageFile", vo.getReply_photo3());
				mav.addObject("filename", vo.getReply_photo_name3());
			}
		}
		return mav;
	}
	
	
	
	
	//==========후기수정==========//
	@RequestMapping("/course/updateReply.do")
	@ResponseBody
	public Map<String,String> modifyReply(CourseReplyVO courseReplyVO,HttpSession session,HttpServletRequest request){
		
		logger.debug("<<후기수정>> : " + courseReplyVO);
		
		Map<String,String> mapJson = new HashMap<String,String>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		CourseReplyVO db_reply = courseService.selectReply(courseReplyVO.getReply_num());
		if(user==null) {//로그인이 안 되어있는 경우
			mapJson.put("result", "logout");
		}else if(user!=null && user.getMem_num()==db_reply.getMem_num()) {//로그인 회원번호와 작성자 회원번호 일치
			//댓글 수정
			courseService.updateReply(courseReplyVO);
			mapJson.put("result", "success");			
		}else {
			//로그인 회원번호와 작성자 회원번호 불일치
			mapJson.put("result", "wrongAccess");
		}
		
		return mapJson;
	}
	
	//후기 사진 삭제
	@RequestMapping("/course/deleteReplyPhoto.do")
	@ResponseBody
	public Map<String,String> processFile(int reply_num,int photo_type,HttpSession session){
		Map<String,String> mapJson = new HashMap<String,String>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user==null) {
			mapJson.put("result", "logout");
		}else {
			if(photo_type==1) {
				courseService.deleteReplyPhoto1(reply_num);
			}else if(photo_type==2) {
				courseService.deleteReplyPhoto2(reply_num);
			}else if(photo_type==3){
				courseService.deleteReplyPhoto3(reply_num);
			}
			mapJson.put("result", "success");
		}
		return mapJson;
	}
	
	
	
	//======후기 삭제========//
	@RequestMapping("/course/deleteReply.do")
	@ResponseBody
	public Map<String,String> deleteReply(@RequestParam int reply_num,HttpSession session){
		logger.debug("<<댓글 삭제>> : " + reply_num);
		
		Map<String,String> mapJson = new HashMap<String,String>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		CourseReplyVO db_reply = courseService.selectReply(reply_num);
		if(user==null) {
			//로그인이 되어있지 않음
			mapJson.put("result", "logout");
		}else if(user!=null && user.getMem_num()==db_reply.getMem_num()) {
			//로그인한 회원번호와 작성자 회원번호 일치
			courseService.deleteReplyWithAll(reply_num);
			mapJson.put("result", "success");
		}else {
			//로그인한 회원번호와 작성자 회원번호 불일치
			mapJson.put("result", "wrongAccess");
		}
		return mapJson;
	}
	
	
	
	//===============좋아요================//
	//후기 좋아요 등록
	@RequestMapping("/course/writeReplyFav.do")
	@ResponseBody
	public Map<String,Object> writeReplyFav(CourseReplyFavVO fav,HttpSession session){
		Map<String,Object> mapJson = new HashMap<String,Object>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user==null) {
			mapJson.put("result", "logout");
		}else {
			//로그인된 회원번호 셋팅
			fav.setFmem_num(user.getMem_num());
			
			logger.debug("<<부모글 좋아요 등록>> : " + fav);
			
			CourseReplyFavVO courseReplyFav = courseService.selectReplyFav(fav);

			if(courseReplyFav!=null) {
				//좋아요가 이미 등록되어있으면 삭제
				courseService.deleteReplyFav(courseReplyFav.getFav_num());
				
				mapJson.put("result", "success");
				mapJson.put("status", "noFav");
			}else {
				//좋아요 미등록이면 등록
				courseService.insertReplyFav(fav);
			
				
				mapJson.put("result", "success");
				mapJson.put("status", "yesFav");
			}
			mapJson.put("count", courseService.selectReplyFavCount(fav.getReply_num()));
		}
		return mapJson;
	}
	
}
