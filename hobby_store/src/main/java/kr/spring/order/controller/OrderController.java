package kr.spring.order.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import kr.spring.course.service.CourseService;
import kr.spring.course.vo.CourseVO;
import kr.spring.items.service.ItemsService;
import kr.spring.items.vo.ItemsVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.order.service.OrderService;
import kr.spring.order.vo.OrderDetailVO;
import kr.spring.order.vo.OrderVO;

@Controller
public class OrderController {
	private static final Logger logger = 
			LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private CourseService courseService;
	@Autowired
	private CartService cartService;
	@Autowired
	private ItemsService itemService;
	@Autowired
	private OrderService orderService;
	
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
	List<ItemCartVO> itemQuan =
			cartService.getItemQuan(user.getMem_num());
	
	Integer courseTotal = cartService.courseTotal(user.getMem_num());
	if(courseTotal==null) courseTotal = 0;
	Integer itemTotal = cartService.itemTotal(user.getMem_num());
	if(itemTotal==null) itemTotal = 0;
	
	Integer allTotal = courseTotal + itemTotal;
	
	
	model.addAttribute("courseCount", courseCount);
	model.addAttribute("courseCart", courseCart);
	model.addAttribute("itemCount", itemCount);
	model.addAttribute("itemCart", itemCart);
	model.addAttribute("itemQuan", itemQuan);
	model.addAttribute("allTotal", allTotal);

	return "orderForm";
}
	
	//폼에서 전송된 데이터 처리
	@PostMapping("/order/order.do")
	public String submit(OrderVO orderVO,
			HttpSession session,Model model,
			HttpServletRequest request,
			HttpServletResponse response) {

		/* 장바구니 상품 개별 선택 추가 후 수정하기
		 * if(orderVO.getCart_numbers()==null || orderVO.getCart_numbers().length==0) {
		 * model.addAttribute("message", "정상적인 주문이 아닙니다."); model.addAttribute("url",
		 * request.getContextPath()+"/cart/list.do"); return "common/resultView"; }
		 */

		MemberVO user = 
				(MemberVO)session.getAttribute("user");
		Map<String,Object> map = 
				new HashMap<String,Object>();
		map.put("mem_num", user.getMem_num());
		/*
		 * map.put("cart_numbers", orderVO.getCart_numbers());
		 */
		
		Integer courseTotal = cartService.courseTotal(user.getMem_num());
		if(courseTotal==null) courseTotal = 0;
		Integer itemTotal = cartService.itemTotal(user.getMem_num());
		if(itemTotal==null) itemTotal = 0;
		Integer allTotal = courseTotal + itemTotal;
		
		if(allTotal<=0) {
			model.addAttribute("message", 
					"정상적인 주문이 아니거나 상품의 수량이 부족합니다.");
			model.addAttribute("url", 
					request.getContextPath()+"/cart/cartList.do");
			return "common/resultView";
		}
		
		List<CourseCartVO> courseCart = 
				cartService.getCourseCart(user.getMem_num());
		List<ItemCartVO> itemCart = 
				cartService.getItemCart(user.getMem_num());
		List<ItemCartVO> itemQuan = 
				cartService.getItemQuan(user.getMem_num());
		
		//주문 상품의 대표 상품명 생성
		String detail_name = "";
		int order_count = courseCart.size() + itemCart.size();
		
		if(courseCart.size() > 0) {
			if(order_count == 1) {
				detail_name = courseCart.get(0).getCourse_name();
			}
			else {
			detail_name = courseCart.get(0).getCourse_name()+"외 "+(order_count-1)+"건";
			}
		}
		else {
			if(itemCart.size() == 1) {
				detail_name = itemCart.get(0).getItems_name();
			}
			if(itemCart.size() > 1) {
			detail_name = itemCart.get(0).getItems_name()+"외 "+(itemCart.size()-1)+"건";
		}}
		
		//개별 주문 상품 저장
		List<OrderDetailVO> orderDetailList = 
				new ArrayList<OrderDetailVO>();
		
		
		/*for(CourseCartVO ccart : courseCart) {
			CourseVO course = courseService.selectCourse(
			           ccart.getCourse_num());
			logger.debug("<<selectCourse후>> : " + ccart.getCourse_num()); 
			logger.debug("<<selectCourse후>> : " + courseCart); 
			
			
			 * if(course.getCourse_limit()==0) { model.addAttribute("message",
			 * "["+course.getCourse_name()+"] 최대 수강인원 제한으로 인해 구매가 불가합니다");
			 * model.addAttribute("url", request.getContextPath()+"/cart/cartList.do");
			 * return "common/resultView"; }
			 
			
			OrderDetailVO orderDetail = 
					            new OrderDetailVO();
			orderDetail.setDetail_name(
					        ccart.getCourse_name());
			orderDetail.setPrice(
					       ccart.getCourse_price());
			orderDetailList.add(orderDetail);
			*/
		
		for(int i=0, size=1;i<=size;i++){
			logger.debug("<<????~~~~~~~~~~~~i j k>> : " + i); 
			logger.debug("<<courseCart.size()>> : " + courseCart.size()); 
			logger.debug("<<itemCart.size()>> : " + itemCart.size()); 
			
			for(int j=0, sizej=1;j<sizej;j++) {
				if(courseCart.size()>0) {sizej=courseCart.size();};
						
				
				logger.debug("course = 0 i:" + i +
							"j:" + j +
							" sizej:" + sizej); 
				
				for(int k=0, sizek=1;k<sizek;k++) {
					if(itemCart.size()>0) {sizek=itemCart.size();};
					
					logger.debug("gggggitem = 0 i:" + i +
							"k:" + k +
							" sizek:" + sizek); 
				

					OrderDetailVO orderDetail = 
			            new OrderDetailVO();
						logger.debug("<<zzz ssizej>> : " + sizej); 
						logger.debug("item = 0 i:" + i +
								"k:" + k +
								" sizek:" + sizek); 
					
				if(i==0) {logger.debug("<<ffffffitemCart.size()>> : " 
				+ itemCart.size()); };
						
						
			if(i==0&&k==0) {	
				logger.debug("<<ㅎㅎ~i j k>> : " + i); 
				
				if(courseCart.size()==0) {break;}
				logger.debug("<<course i j k >> : " + i + j + k); 
				
			CourseCartVO ccart = courseCart.get(j);
			CourseVO course = courseService.selectCourse(
					ccart.getCourse_num());
		
			
			orderDetail.setDetail_name(
					ccart.getCourse_name());
			orderDetail.setPrice(
					ccart.getCourse_price());

			orderDetail.setCourse_num(
					ccart.getCourse_num());
			
			
			
			logger.debug("<<orderDetail>> : " + orderDetail); 
			logger.debug("<<orderDetail>> : " + orderDetailList); 
			logger.debug("<<!!selectCourse iiii>> : " + i); 
			logger.debug("<<!!selectCourse size>> : " + size); 
			/*
			 * logger.debug("<<!!selectCourse name>> : " + ccart.getCourse_name());
			 * logger.debug("<<!!selectCourse price>> : " + ccart.getCourse_price());
			 * logger.debug("<<!!selectCourse후>> : " + ccart);
			 * logger.debug("<<!!selectCourse후>> : " + courseCart);
			 */
			orderDetailList.add(orderDetail);
			}
			else if(i==0&&k>0) {break;}
			logger.debug("<<~~~~~~~~~~~~~i j k>> : " + i + j + k); 
			
			if(i==1&&j==0) {
				if(itemCart.size()==0) {break;}
				logger.debug("<<item i j k >> : " + i + j + k); 
				
			ItemCartVO icart = itemCart.get(k);
			ItemCartVO iquan = itemQuan.get(k);
			ItemsVO item = itemService.selectItems(
					icart.getItems_num());
			
			orderDetail.setDetail_name(
					icart.getItems_name());
			orderDetail.setPrice(
					iquan.getItems_total());
			orderDetail.setQuantity(
					iquan.getQuantity());
			orderDetail.setItems_total(
					iquan.getItems_total());
			
			orderDetail.setItems_num(
					icart.getItems_num());
			
			
			logger.debug("<<iiorderDetail>> : " + orderDetail); 
			logger.debug("<<iiorderDetail>> : " + orderDetailList); 
			logger.debug("<<!!selectItems j>> : " + j); 
			logger.debug("<<!!selectItems size>> : " + size); 
			logger.debug("<<!!selectItems후>> : " + order_count); 
			/*
			 * logger.debug("<<!!selectItems후>> : " + icart.getItems_num());
			 * logger.debug("<<!!selectItems후>> : " + itemCart);
			 * logger.debug("<<!!selectItems후>> : " + itemQuan);
			 */
			
			/*
			 * if(j==sizej) { break; }
			 */
			
			orderDetailList.add(orderDetail);
			}
			else if(i==1&&j>0) {break;}
			/*
			 * if(item.getItems_quantity() < cart.getQuantity()) {
			 * model.addAttribute("message",
			 * "["+item.getItems_name()+"] 재고가 부족하여 구매가 불가합니다"); model.addAttribute("url",
			 * request.getContextPath()+"/cart/cartList.do"); return "common/resultView"; }
			 */
			
			/*OrderDetailVO orderDetail = 
		            new OrderDetailVO();
			
			if(icart!=null) {
			orderDetail.setDetail_name(
					icart.getItems_name());
			orderDetail.setPrice(
					iquan.getItems_total());
			orderDetail.setQuantity(
					iquan.getQuantity());
			orderDetail.setItems_total(
					iquan.getItems_total());
			
			orderDetail.setItems_num(
					icart.getItems_num());
*/
			/*
			logger.debug("<<detail전>> : " + iquan.getQuantity()); 
			logger.debug("<<detail전>> : " + iquan.getItems_total()); 
			logger.debug("<<detail전>> : " + icart); */
			
			/*logger.debug("<<WWW>> : " + orderDetail);
			}*/
			/*
			 * orderDetailList.add(orderDetail); }
			 */
		 } }
			
		}
		orderVO.setOrder_name(detail_name);//대표 상품명
		orderVO.setOrder_price(allTotal);//총주문금액
		orderVO.setMem_num(user.getMem_num());//주문자
		logger.debug("<<insertOrder 전 detail_name>> : " + detail_name);
		
		orderService.insertOrder(
				         orderVO,orderDetailList);
		
		//refresh 정보를 응답 헤더에 추가
		response.addHeader("Refresh", 
				          "2;url=../main/main.do");
		model.addAttribute("accessMsg", 
				              "주문이 완료되었습니다.");
		return "common/notice";
	}
}
