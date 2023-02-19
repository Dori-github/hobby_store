package kr.spring.cart.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.cart.controller.CartController;
import kr.spring.cart.service.CartService;
import kr.spring.cart.vo.CourseCartVO;
import kr.spring.cart.vo.ItemCartVO;

@Controller
public class CartController {//메서드 생성, 데이터 처리
	@Autowired
	private CartService cartService;
	
	private static final Logger logger =
			LoggerFactory.getLogger(
					CartController.class);
	
	//=====장바구니 목록=====//
	@RequestMapping("/cart/cartList.do")
	public ModelAndView getList() {	
		
			//글의 총 개수
			int courseCount = cartService.getCartCount();
			int itemCount = cartService.getItemCount();

			//목록 호출
			List<CourseCartVO> courseList = null;
			if(courseCount > 0) {
				courseList = cartService.getCourseCart(100);
			}

			List<ItemCartVO> itemList = null;
			if(itemCount > 0) {
				itemList = cartService.getItemList(100);
			}
			
			//회원번호(mem_num)별 총 구입액	
			int courseTotal = cartService.courseTotal(100);
			int itemTotal = cartService.itemTotal(100);
			
			ModelAndView mav = new ModelAndView();
			//뷰 이름 설정(tiles-definition name)
			mav.setViewName("cartList");
			//데이터 저장
			mav.addObject("courseCount", courseCount);
			mav.addObject("courseList", courseList);
			mav.addObject("courseTotal", courseTotal);

			mav.addObject("itemCount", itemCount);
			mav.addObject("itemList", itemList);
			mav.addObject("itemTotal", itemTotal);
			
			return mav;
	}		
}
