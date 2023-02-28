package kr.spring.order.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import kr.spring.cart.service.CartService;
import kr.spring.cart.vo.CourseCartVO;
import kr.spring.cart.vo.ItemCartVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.order.vo.OrderVO;

@Controller
public class OrderController {
	private static final Logger logger = 
			LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private CartService cartService;
	
	//=====주문하기=====//
	//주문등록 폼 호출
	@PostMapping("/order/orderForm.do")
	public String form(OrderVO orderVO,
			HttpSession session,Model model,
			HttpServletRequest request) {

		
	logger.debug("<<주문 mem_num>> : " + orderVO.getMem_num());
		
	//글의 총 개수
	int courseCount = cartService.getCartCount();
	int itemCount = cartService.getItemCount();

	MemberVO user = 
			(MemberVO)session.getAttribute("user");
		
	//장바구니 상품 정보 호출
	List<CourseCartVO> courseCart = 
			cartService.getCourseCart(user.getMem_num());
	List<ItemCartVO> itemCart = 
			cartService.getItemCart(user.getMem_num());

	model.addAttribute("courseCount", courseCount);
	model.addAttribute("courseCart", courseCart);
	model.addAttribute("itemCount", itemCount);
	model.addAttribute("itemCart", itemCart);
	/* model.addAttribute("all_total", all_total); */	

	return "orderForm";
}
}
