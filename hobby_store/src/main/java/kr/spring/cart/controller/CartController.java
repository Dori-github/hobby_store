package kr.spring.cart.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.cart.controller.CartController;
import kr.spring.cart.service.CartService;
import kr.spring.cart.vo.CourseCartVO;
import kr.spring.cart.vo.ItemCartVO;
import kr.spring.course.vo.CourseVO;
import kr.spring.items.vo.ItemsVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.order.vo.OrderVO;
import kr.spring.points.vo.PointsVO;

@Controller
public class CartController {//메서드 생성, 데이터 처리
	@Autowired
	private CartService cartService;
	
	private static final Logger logger =
			LoggerFactory.getLogger(
					CartController.class);
	
	//=====장바구니 목록=====//
	@RequestMapping("/cart/cartList.do")
	public ModelAndView getList(HttpSession session) {	
		
			//글의 총 개수
			int courseCount = cartService.getCartCount();
			int itemCount = cartService.getItemCount();
			logger.debug("클래스수" + courseCount);
			logger.debug("상품수" + itemCount);
			
			
			MemberVO user = 
					 (MemberVO)session.getAttribute("user");
				
			//목록 호출(장바구니 비었을 때 처리 추가)
			List<CourseCartVO> courseList = null;
			if(courseCount > 0) {
				courseList = cartService.getCourseCart(user.getMem_num());
			}

			List<ItemCartVO> itemList = null;
			if(itemCount > 0) {
				itemList = cartService.getItemCart(user.getMem_num());
			}
			
			//회원번호(mem_num)별 총 구입액	
			Integer courseTotal = cartService.courseTotal(user.getMem_num());
			Integer itemTotal = cartService.itemTotal(user.getMem_num());
//			Integer allTotal = courseTotal + itemTotal;
			
			//
			List<ItemCartVO> itemQuan = null;
			itemQuan = cartService.getItemQuan(user.getMem_num());
			
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
			
//			mav.addObject("allTotal", allTotal);
			mav.addObject("itemQuan", itemQuan);
			
			return mav;
	}
	
	//상품 수량, 구입액
	@RequestMapping(value = "/getItemQuan", method = RequestMethod.POST)
	@ResponseBody
	public void getItemQuan(int num, HttpSession session) throws Exception{
		logger.debug("zzzz" + num);
		
		MemberVO user = 
				 (MemberVO)session.getAttribute("user");
		
		List<ItemCartVO> getItemQuan = null;
		getItemQuan = cartService.getItemQuan(user.getMem_num());
		logger.debug("aaaaa" + num);
		
		ModelAndView mav = new ModelAndView();
		//뷰 이름 설정(tiles-definition name)
		mav.setViewName("cartList");
		//데이터 저장
		mav.addObject("getItemQuan", getItemQuan);
		
	}
	
	//장바구니에 클래스 추가
	public void insertCourseCart(CourseVO courseVO) {
		cartService.insertCourseCart(courseVO);
		logger.debug("장바구니에 클래스 추가" + courseVO);
	}
	
	//장바구니에 상품 추가
	public void insertItemCart(ItemsVO itemVO) {
		cartService.insertItemCart(itemVO);
		logger.debug("장바구니에 상품 추가" + itemVO);
	}
	

	//상품 장바구니 수정(개별 상품 수량 변경)
	@ResponseBody
	@RequestMapping(value = "/updateCart", method = RequestMethod.POST)
	public void updateCart(int quantity, int cart_num) throws Exception {
	 
		 cartService.updateCart(quantity, cart_num);
		 logger.debug("cart : " + quantity + "," + cart_num);
	}
}
