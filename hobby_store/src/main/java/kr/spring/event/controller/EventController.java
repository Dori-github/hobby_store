package kr.spring.event.controller;

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

import kr.spring.course.vo.CourseVO;
import kr.spring.event.service.EventService;
import kr.spring.event.vo.EventApplyVO;
import kr.spring.event.vo.EventVO;
import kr.spring.items.vo.ItemsVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;

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
	
	//======이벤트 목록======//
	@RequestMapping("/event/list.do")
	public ModelAndView process(@RequestParam(value="pageNum", defaultValue="1") int currentPage, String keyfield, String keyword) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		int count = eventService.selectRowCount(map);
		
		PagingUtil page = new PagingUtil(keyfield,keyword,currentPage,count,9,10,"list.do");
		
		List<EventVO> list = null;
		if(count > 0) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			list = eventService.selectList(map);
		}

		logger.debug("<<이벤트 목록>> : " + count);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("eventList");
		mav.addObject("count",count);
		mav.addObject("list",list);
		mav.addObject("page",page.getPage());
		
		return mav;
	}
	
	//======이벤트 등록======//
	//이벤트 폼 이동
	   @GetMapping("/event/write.do")
	   public String form(HttpSession session, Model model) {
	      
	      MemberVO user = (MemberVO)session.getAttribute("user");
	      
	      int mem_num = user.getMem_num();
	      
	      List<CourseVO> course = eventService.selectSearchCourse(mem_num);
	      List<ItemsVO> items = eventService.selectSearchItems(mem_num);
	      
	      model.addAttribute("course",course);
	      model.addAttribute("items",items);
	      
	      return "eventWriteForm";
	   }
	
	//이벤트 전송된 데이터 저장
	@PostMapping("/event/write.do")
	public String write(@Valid EventVO eventVO,BindingResult result,HttpServletRequest request,Model model,HttpSession session) {
		if(eventVO.getEvent_photo().length==0) {
			result.rejectValue("event_photo", "required");
		}
		//이미지 용량 체크
		if(eventVO.getEvent_photo().length > 5*1024*1024) {
			result.rejectValue("photo", "limitUploadSize",new Object[] {"5MB"},null);
		}
		
		if(result.hasErrors()) {
			return form(session,model);
		}
		
		eventVO.setMem_num(((MemberVO)session.getAttribute("user")).getMem_num());
		
		logger.debug("<<이벤트 등록 VO>> : " + eventVO);
		
		eventService.insertEvent(eventVO);
		
		//View에 메시지 표시
		model.addAttribute("message","이벤트 등록이 완료되었습니다.");
		model.addAttribute("url",request.getContextPath()+"/event/list.do");
		
		return "common/resultView";
	}
	
	//======이벤트 상세======//
	@RequestMapping("/event/detail.do")
	public String detail(@RequestParam int event_num,Model model) {
		
		EventVO event = eventService.selectEvent(event_num);
		
		eventService.updateHit(event_num);
		
		model.addAttribute("event",event);
		
		return "eventView";
	}
	
	//======이벤트 수정======//
	@GetMapping("/event/update.do")
	public String formUpdate(@RequestParam int event_num,Model model) {
			
		EventVO eventVO = eventService.selectEvent(event_num);
		
		List<CourseVO> course = eventService.selectSearchCourse(eventVO.getMem_num());
	    List<ItemsVO> items = eventService.selectSearchItems(eventVO.getMem_num());
	      
	    model.addAttribute("course",course);
	    model.addAttribute("items",items);
			
		model.addAttribute("eventVO",eventVO);
			
		return "eventModify";
	}
	
	@PostMapping("/event/update.do")
	public String submitUpdate(@Valid EventVO eventVO, BindingResult result, HttpServletRequest request, Model model) {
		
		logger.debug("<<글수정>> : " + eventVO);
		logger.debug("<<업로드 파일 용량>> : " + eventVO.getEvent_photo().length);
		
		if(eventVO.getEvent_photo().length > 5*1024*1024) {//5MB
			result.reject("limitUploadSize", new Object[] {"5MB"},null);
		}
		
		//유효성 체크에 걸릴 시 파일명 다시 셋팅
		if(result.hasErrors()) {
			EventVO vo = eventService.selectEvent(eventVO.getEvent_num());
			eventVO.setEvent_photo_name(vo.getEvent_photo_name());
			return "eventModify";
		}
		
		//이벤트 수정
		eventService.updateEvent(eventVO);
		
		//View에 메시지 표시
		model.addAttribute("message","이벤트 수정이 완료되었습니다.");
		model.addAttribute("url",request.getContextPath()+"/event/list.do");
		
		return "common/resultView";
	}
	
	//=====이벤트 글삭제=======// 
	@RequestMapping("/event/delete.do")
	public String submitDelete(@RequestParam int event_num,Model model,HttpServletRequest request) {
		
			logger.debug("<<이벤트 글삭제>> : " + event_num);
			
			EventApplyVO db_apply = null;
			
			db_apply = eventService.selectEventApply(event_num);
			
			if(db_apply!=null) {
				//View에 메시지 표시
				model.addAttribute("message","이벤트 신청자가 존재해 삭제가 불가합니다.");
				model.addAttribute("url",request.getContextPath()+"/event/detail.do?event_num="+event_num);
				
				return "common/resultView";
			}
			
			//이벤트 삭제
			eventService.deleteEvent(event_num);
			
			//View에 메시지 표시
			model.addAttribute("message","이벤트 삭제가 완료되었습니다.");
			model.addAttribute("url",request.getContextPath()+"/event/list.do");
			
			return "common/resultView";
		}
	
	//======이벤트 신청======//
	@RequestMapping("/event/user_regis.do")
	public String userRegisForm(@RequestParam int event_num, HttpSession session,HttpServletRequest request, Model model) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		logger.debug("<<이벤트 신청>> : " + event_num);
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		map.put("event_num", event_num);
		map.put("mem_num", user.getMem_num());
		
		if(user!=null && user.getMem_auth() <= 3){
			
			EventApplyVO db_apply = eventService.selectEventApplyByMem_num(map);
			EventVO db_event = eventService.selectEvent(event_num);

			if(db_event.getMem_num()==user.getMem_num()) {
				model.addAttribute("message","본인이 등록한 이벤트는 신청이 불가합니다.");
				model.addAttribute("url",request.getContextPath()+"/event/detail.do?event_num="+event_num);
				return "common/resultView";
			}
			
			if(db_apply!=null) {
				model.addAttribute("message","이미 신청된 이벤트가 존재합니다.");
				model.addAttribute("url",request.getContextPath()+"/event/detail.do?event_num="+event_num);
				return "common/resultView";
			}
			
			EventApplyVO apply = new EventApplyVO();
			apply.setEvent_num(event_num);
			apply.setMem_num(user.getMem_num());
			eventService.insertEventApply(apply);
		}else {
			return "common/notice";
		}
		
		//View에 메시지 표시
		model.addAttribute("message","이벤트 신청이 완료되었습니다.");
		model.addAttribute("url",request.getContextPath()+"/event/list.do");
		
		return "common/resultView";
	}
	
	//======이벤트 삭제======//
	/*@PostMapping("/event/delete.do")
	public Map<String,String> submitDelete(@RequestParam int event_num, HttpSession session) {
		
		Map<String,String> mapAjax = new HashMap<String,String>();
		
		logger.debug("<<이벤트 삭제>> : " + event_num);
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		EventVO event = eventService.selectEvent(event_num);
		
		if(user==null) {
			mapAjax.put("result", "logout");
		}else if(user!=null && user.getMem_num()==event.getMem_num()) {
			mapAjax.put("result", "wrongAccess");
		}else {
			eventService.deleteEvent(event_num);
			mapAjax.put("result", "success");
		}
		return mapAjax;
	}*/
	
	//이벤트 클래스/상품 목록 검색
	/*@RequestMapping("/event/eventSearchAjax.do")
	@ResponseBody
	public Map<String,Object> selectSearch(@RequestParam String name, HttpSession session){
		Map<String,Object> mapAjax = new HashMap<String,Object>();
		
		MemberVO memberVO = (MemberVO)session.getAttribute("user");
		
		if(memberVO==null) {//로그인 X
			mapAjax.put("result", "logout");
		}else if(memberVO!=null && memberVO.getMem_auth()>2) {//로그인 O, 강사/관리자 O
				List<CourseVO> course = eventService.selectSearchCourse(name);
				List<ItemsVO> items = eventService.selectSearchItems(name);
				mapAjax.put("result", "success");
				mapAjax.put("course", course);
				mapAjax.put("items", items);
		}else {//로그인 O, 강사/관리자 X
			mapAjax.put("result", "wrongAccess");
		}
		
		return mapAjax;
	}*/
	
	//이미지 출력
	@RequestMapping("/event/imageView.do")
	public ModelAndView viewImage(@RequestParam int event_num) {
		
		EventVO event = eventService.selectEvent(event_num);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		
		mav.addObject("imageFile",event.getEvent_photo());
		mav.addObject("filename",event.getEvent_photo_name());
		
		return mav;
	}
	
}
 