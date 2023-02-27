package kr.spring.course.controller;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.course.service.CourseService;
import kr.spring.course.vo.CourseVO;
import kr.spring.util.PagingUtil;

@Controller
public class CourseController {
	private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

	//private int rowCount=12; 
	
	@Autowired
	private CourseService courseService;
	
	//자바빈(VO) 초기화
	@ModelAttribute
	public CourseVO initCommand() {
		return new CourseVO();
	}
	
	//=============클래스 목록===============//
	@RequestMapping("/course/courseList.do")
	public ModelAndView process(@RequestParam(value="pageNum",defaultValue="1") 
								int currentPage,String keyfield,String keyword) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		//글의 총개수 또는 검색된 글의 개수
		int count = courseService.selectCourseCount(map);
		
		logger.debug("<<상품 목록 개수>> : " + count);
		
		//카테고리 목록
		List<CourseVO> course_cate = null;
		course_cate = courseService.selectCate();
		
		//페이지 처리
		PagingUtil page = new PagingUtil(keyfield,keyword,currentPage, count, 12, 3, "courseList.do");
		
		List<CourseVO> list = null;
		if(count>0) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
		}
		list = courseService.selectCourseList(map);
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("courseList");
		mav.addObject("course_cate",course_cate);
		mav.addObject("count",count);
		mav.addObject("list",list);
		mav.addObject("page",page.getPage());
		
		return mav;
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
		logger.debug("<<courseVO>> : " + courseVO);
		
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
			return form();
		}
		
		//카테고리
		//상세 카테고리 번호
		int cate_num = courseService.selectCate_num(courseVO);
		//대분류 카테고리 번호
		int cate_parent = courseVO.getCate_parent();
		
		courseVO.setCate_nums(cate_parent + "," + cate_num);
		
		
		//요일,날짜 
		
		
		
		
		
		//클래스 데이터 등록
		courseService.insertCourse(courseVO);
		
		//View에 표시할 메시지
		ModelAndView mav = new ModelAndView();
		mav.setViewName("common/resultView");
		mav.addObject("message","클래스 등록이 완료되었습니다.");
		mav.addObject("url",request.getContextPath()+"/course/courseList.do");
		
		return mav;
	}
	
	//==========게시판 글상세============//
	
	
	
	
	
	
	
	
	//=========게시판 글 수정===========//
	//수정 폼 호출
	
	//수정 폼에서 전송된 데이터 처리
	
	
	
	
	//파일삭제
	
	
	//===========게시판 글삭제============//
	
	
	
	//이미지 출력
	
	
	
	//===============좋아요================//
	//부모글 좋아요 읽기
	
	
	//좋아요 등록
	
	
	
	//===============후기================//
	//후기등록
	
	
	
	
	//후기목록
	
	
	
	
	
}
