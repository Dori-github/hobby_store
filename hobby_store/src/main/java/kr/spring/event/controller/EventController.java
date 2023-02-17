package kr.spring.event.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.event.service.EventService;
import kr.spring.event.vo.EventVO;

@Controller
public class EventController {
	private static final Logger logger = LoggerFactory.getLogger(EventController.class);
	
	@Autowired
	private EventService eventService;
	
	//자바빈 초기화
	@ModelAttribute 
	public EventVO initCommand(){
		return new EventVO();
	}
	
	@RequestMapping("/event/eventList.do")
	public ModelAndView process(@RequestParam(value="pageNum", defaultValue="1") int currentPage, String keyfield, String keyword) {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("eventList");
		
		return mav;
	}
}
