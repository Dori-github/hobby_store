package kr.spring.main.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.course.service.CourseService;
import kr.spring.course.vo.CourseVO;

@Controller
public class MainController {
	@Autowired
	private CourseService courseService;
	
	@RequestMapping("/")
	public String main() {
		return "redirect:/main/main.do";
	}
	
	@RequestMapping("/main/main.do")
	public String main(Model model) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start", 1);
		map.put("end", 4);
		List<CourseVO> list = courseService.selectCourseMainList(map);
		
		
		Map<String,Object> map2 = new HashMap<String,Object>();
		map2.put("start", 1);
		map2.put("end", 4);
		List<CourseVO> list2 = courseService.selectCourseMainList2(map2);
		
		Map<String,Object> map3 = new HashMap<String,Object>();
		map3.put("start", 1);
		map3.put("end", 4);
		List<CourseVO> list3 = courseService.selectCourseMainList3(map3);
		
		model.addAttribute("list",list);
		model.addAttribute("list2",list2);
		model.addAttribute("list3",list3);

		return "main";//타일스 설정값
	}
}





