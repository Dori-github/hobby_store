package kr.spring.items.controller;

import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.spring.items.service.ItemsService;
import kr.spring.items.vo.ItemsVO;

@Controller
public class ItemsController {
	@Autowired
	private ItemsService itemsService;
	
	private static final Logger logger = LoggerFactory.getLogger(ItemsController.class);
	
	//1. 상품 등록//
	//1-1 등록 폼 호출//
	@GetMapping("/items/register.do")
	public String form() {
		return "ItemsRegister";
	}
	//1-2 등록 데이터 처리//
	@PostMapping("/items/register.do")
	public String submit(@Valid ItemsVO itemsVO, BindingResult result, HttpSession session, HttpRequest request, RedirectAttributes redirect) {
		logger.debug("<<상품 등록 정보>> : " + itemsVO);

		//유효성 췍
		if(result.hasErrors()) {
			return form();
		}
		
		
		return form();
	}
	
	//2. 상품 목록 // 
	//2-1 마이페이지에서보는 상품 목록//
	@RequestMapping("/itmes/my_list.do")
	public ModelAndView process(@RequestParam(value = "pageNum", defaultValue = "1") int currentPage, String keyfield, String keyword) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		int count = itemsService.selectRowCount(map);
		
		logger.debug("<<count >>:" + count);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("myitemsList");
		
		return mav;
		
	}
	//2-2 스토어 페이지에서 보는 상품 목록//
	
	
}
