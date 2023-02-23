package kr.spring.course.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import kr.spring.course.service.CourseService;
import kr.spring.course.vo.CourseVO;

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
	
	//클래스 목록
	@RequestMapping("/course/courseList.do")
	public ModelAndView process(@RequestParam(value="pageNum",defaultValue="1") 
								int currentPage,String keyfield,String keyword) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		//글의 총개수 또는 검색된 글의 개수
		//int count = courseService.;
		
		//페이지 처리
		//PagingUtil page = new PagingUtil(keyfield,keyword,currentPage, count, 12, 3, "courseList.do");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("courseList");
		//mav.addObject("count",count);
		//mav.addObject("list",list);
		//mav.addObject("page",page.getPage());
		
		return mav;
	}
	
	
	//글쓰기
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
	
	
	//게시판 글상세
	
	
	
	
	//게시판 글 수정
	//수정 폼 호출
	
	//수정 폼에서 전송된 데이터 처리
	
	
	
	
	//파일삭제
	
	
	//게시판 글삭제
	
	
	
	//이미지 출력
	
	
	
	//===============좋아요================//
	//부모글 좋아요 읽기
	
	
	//좋아요 등록
	
	
	
	//===============후기================//
	//후기등록
	
	
	
	
	//후기목록
	
	
	
	
	
}
