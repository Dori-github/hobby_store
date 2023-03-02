package kr.spring.items.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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


import kr.spring.items.service.ItemsService;
import kr.spring.items.vo.ItemsVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;
  
@Controller
public class ItemsController {
	@Autowired
	private ItemsService itemsService;
	
	@ModelAttribute //컨트롤러 접근 후 VO 초기화 
	public ItemsVO initCommend() {
		return new ItemsVO();
	}
	
	private static final Logger logger = LoggerFactory.getLogger(ItemsController.class);
	
	//1. 상품 등록//
	//1-1 등록 폼 호출//
	@GetMapping("/items/itemsRegister.do")
	public ModelAndView form() {
		//Model = interface(객체를 인자로 사용) ModelAndView = class(내부에 객체 생성)
		ModelAndView mav = new ModelAndView();
			
			List<ItemsVO> items_cate = null; 
	
			items_cate = itemsService.selectCate1();
			logger.debug("<<items_cate>> : " + items_cate);

			mav.setViewName("itemsRegister");
			mav.addObject("items_cate", items_cate);

		
		return mav;
	}
	
	//1-2 상세 카테고리 및 아이템 등록 폼 
	@ResponseBody
	@GetMapping("/items/itemsRegister2.do")
	public Map<String,Object> form(@RequestParam int cate_num) {
		//ModelAndView mav = new ModelAndView();
		List<ItemsVO> items_child = null;
		
		items_child = itemsService.selectCate2(cate_num);
		logger.debug("<<items_child>> :" + items_child);
		
		Map<String,Object> mapJson = new HashMap<String,Object>();

		mapJson.put("items_child", items_child);
		
		logger.debug("<<Map_items_child>> : " + mapJson);

		return mapJson;

	}
	//1-3 아이템 등록 데이터 처리 
	@PostMapping("/items/itemsRegister2.do")
	public ModelAndView submit(ItemsVO vo, BindingResult result, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		logger.debug("<<상품 등록 :>>"+ vo);	
		
		//이미지 유효성 체크 (photo1만 not null)
		if(vo.getItems_photo1().length==0) {
			//upload1은 자바빈(vo)에 필드가 없기때문에 명시
			//할 수 없음
			result.rejectValue("photo1", "required");
		}
		//이미지 용량 체크 
		if(vo.getItems_photo1().length > 5*1024*1024) {//5MB
			result.rejectValue("photo1", 
					"limitUploadSize",new Object[] {"5MB"},null);
		}
		if(vo.getItems_photo2().length > 5*1024*1024) {
			result.rejectValue("photo2", 
					"limitUploadSize",new Object[] {"5MB"},null);
		}
		if(vo.getItems_photo3().length > 5*1024*1024) {
			result.rejectValue("photo3", 
					"limitUploadSize",new Object[] {"5MB"},null);
		}
		//유효성 쳌
				if(result.hasErrors()) {
					return form();
				}
		//회원번호 조회 후 회원번호 -> VO에 저장 
		vo.setMem_num(((MemberVO)session.getAttribute("user")).getMem_num());
				
		//상품 등록 
		itemsService.insertItems(vo);
		
		mav.setViewName("itemsList");
		mav.addObject("itemsVO", vo);
		return mav;
	}
	
	//2 상품 목록 
	@RequestMapping("/items/itemsList.do")
	public ModelAndView process(@RequestParam(value="pageNum",defaultValue="1")int currentPage, String keyfield, String keyword) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("keyfield", keyfield);	
			map.put("keyword", keyword);
			map.put("status", 1);
			//keyword : 검색 키워드 , keyfield :검색 키필드 
			
			//상품 개수, 검색된 상품 개수 
			int count = itemsService.selectItemsCount(map);
			logger.debug("<<상품 목록>> :  "+count);
			
			PagingUtil page = new PagingUtil(keyfield, keyword, currentPage, count, 20, 10,"itemsList.do");
			
			List<ItemsVO> list = null;
			
			if(count > 0) {
				map.put("start", page.getStartRow());
				map.put("end", page.getEndRow());
				
				list = itemsService.selectItemsList(map);
				logger.debug("<<상품 리스트 >> : "+list);
			}
			//model 객체에 데이터를 담아 view에 전달
			ModelAndView mav = new ModelAndView();
			mav.setViewName("itemsList");
			mav.addObject("count",count);
			mav.addObject("list",list);
			mav.addObject("page",page.getPage());
		return mav;
	}
	//이미지 출력
	@RequestMapping("/items/imageView.do")
	public ModelAndView viewImage(
			@RequestParam int items_num,
			@RequestParam int items_type) {
		
		ItemsVO itemsVO = 
				itemsService.selectItems(items_num);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		
		if(items_type==1) {
			mav.addObject("imageFile", itemsVO.getItems_photo1());
			mav.addObject("filename", itemsVO.getItems_photo_name1());
		}else if(items_type==2) {
			mav.addObject("imageFile", itemsVO.getItems_photo2());
			mav.addObject("filename", itemsVO.getItems_photo_name2());
		}else if(items_type==3) {
			mav.addObject("imageFile", itemsVO.getItems_photo3());
			mav.addObject("filename", itemsVO.getItems_photo_name3());
		}
		
		return mav;
	}
	

}
