package kr.spring.order.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.cart.dao.CartMapper;
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
import kr.spring.points.vo.PointsVO;

@Controller
public class OrderController {
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private CourseService courseService;
	@Autowired
	private CartService cartService;
	@Autowired
	private ItemsService itemService;
	@Autowired
	private OrderService orderService;
	@ModelAttribute // 컨트롤러 접근 후 VO 초기화
	public OrderVO initCommend() {
		return new OrderVO();
	}
	// =====주문하기=====//
	// 주문등록 폼 호출
	@RequestMapping("/order/orderForm.do")
	public String orderForm(OrderVO orderVO, HttpSession session, Model model,
			HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "c_chk") List<String> c_Arr,
			@RequestParam(value = "i_chk") List<String> i_Arr) {
		
		MemberVO user = (MemberVO) session.getAttribute("user");
		
		// 글의 총 개수
		int courseCount = cartService.getCartCount(user.getMem_num());
		int itemCount = cartService.getItemCount(user.getMem_num());
		
		List<ItemCartVO> itemQuan = cartService.getItemQuan(user.getMem_num());
		
		int courseTotal = 0;
		int itemSum = 0;
		int itemTotal = 0;
		
		ArrayList<Integer> c_chkArr = new ArrayList<Integer>();
		ArrayList<CourseCartVO> courseCart = new ArrayList<CourseCartVO>();
		
		ArrayList<Integer> i_chkArr = new ArrayList<Integer>();
		ArrayList<ItemCartVO> itemCart = new ArrayList<ItemCartVO>();
		
		int i = 0;
		for(String num:c_Arr) {
			int n = Integer.parseInt(num);
			c_chkArr.add(n);
			courseCart.addAll(cartService.getCourseCartNum(
					user.getMem_num(),n));

			courseTotal += courseCart.get(i).getCourse_price();
			i++;
		}

		int j = 0;
		for(String num:i_Arr) {
			int n = Integer.parseInt(num);
			i_chkArr.add(n);//잇어ㅑ하나?
			itemCart.addAll(cartService.getItemCartNum(
					user.getMem_num(),n));

			itemSum += itemCart.get(j).getItems_price()
					* itemQuan.get(j).getQuantity();
			itemTotal += itemSum;
			j++;
		}
		
		Integer allTotal = courseTotal + itemTotal;
		 
		model.addAttribute("courseCount", courseCount);
		model.addAttribute("courseCart", courseCart);
		model.addAttribute("itemCount", itemCount);
		model.addAttribute("itemCart", itemCart);
		model.addAttribute("itemQuan", itemQuan);
		model.addAttribute("courseTotal", courseTotal);
		model.addAttribute("itemSum", itemSum); 
		model.addAttribute("itemTotal", itemTotal); 
		model.addAttribute("allTotal", allTotal);
		model.addAttribute("c_Arr", c_Arr);
		model.addAttribute("i_Arr", i_Arr);
		model.addAttribute("c_chkArr", c_chkArr);
		model.addAttribute("i_chkArr", i_chkArr);
		
		
		return "orderForm";
	}

	// 폼에서 전송된 데이터 처리
	@PostMapping("/order/order.do")
	public String submit(OrderVO orderVO, PointsVO pointsVO, HttpSession session, Model model,
			HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "c_chkNum[]") List<String> c_Arr,
			@RequestParam(value = "i_chkNum[]") List<String> i_Arr) {

		logger.debug("cccccc : " + c_Arr);
		logger.debug("iiiiii : " + i_Arr);
		
		/*
		 @RequestMapping("/alarmCustomerList.do") 
		 public String alarmCustomerList(){
		 //프로세스 생략 
		 commonMethod(...); 
		 
		 return "alarmCustomerList"; 
		 }
		 
		 
		 @RequestMapping("/customerList.do") 
		 public String customerList(){ 
		 //프로세스 생략
		 commonMethod(...); 
		 
		 return "customerList"; 
		 } 
		 
		 private ... commonMethod(...) {
		 //todo 
		 }
		 */
		 
		
		/*
		 * 장바구니 상품 개별 선택 추가 후 수정하기 if(orderVO.getCart_numbers()==null ||
		 * orderVO.getCart_numbers().length==0) { model.addAttribute("message",
		 * "정상적인 주문이 아닙니다."); model.addAttribute("url",
		 * request.getContextPath()+"/cart/list.do"); return "common/resultView"; }
		 */

		
		
		
		
		
		 MemberVO user = (MemberVO) session.getAttribute("user");
		
		 // 글의 총 개수
		 int courseCount = cartService.getCartCount(user.getMem_num()); 
		 int itemCount = cartService.getItemCount(user.getMem_num());
		 
		 List<ItemCartVO> itemQuan = cartService.getItemQuan(user.getMem_num());
		 
		 int courseTotal = 0; int itemSum = 0; int itemTotal = 0;
		 
		 ArrayList<Integer> c_chkArr = new ArrayList<Integer>();
		 ArrayList<CourseCartVO> courseCart = new ArrayList<CourseCartVO>();
		 
		 ArrayList<Integer> i_chkArr = new ArrayList<Integer>(); ArrayList<ItemCartVO>
		 itemCart = new ArrayList<ItemCartVO>();
		 
		 int i = 0; 
		 for(String num:c_Arr) { 
			 int n = Integer.parseInt(num);
			 c_chkArr.add(n); 
			 courseCart.addAll(cartService.
					 getCourseCartNum(user.getMem_num(),n));
		 
		 courseTotal += courseCart.get(i).getCourse_price();
		 i++; 
		 }
		 
		 int j = 0; 
		 for(String num:i_Arr) { 
			 int n = Integer.parseInt(num);
		 i_chkArr.add(n); 
		 itemCart.addAll(cartService.
				 getItemCartNum(user.getMem_num(),n));
		 
		 itemSum += itemCart.get(j).getItems_price() 
				 * itemQuan.get(j).getQuantity();
		 itemTotal += itemSum; 
		 j++; 
		 }
		
		 Integer allTotal = courseTotal + itemTotal;
		  	
		
		
		
		
		
		
		
		
		
		/*
		 * Map<String,Object> map = new HashMap<String,Object>(); map.put("mem_num",
		 * user.getMem_num());
		 */
		/*
		 * map.put("cart_numbers", orderVO.getCart_numbers());
		 */

		
		/*
		 * Integer courseTotal = cartService.courseTotal(user.getMem_num()); if
		 * (courseTotal == null) courseTotal = 0; Integer itemTotal =
		 * cartService.itemTotal(user.getMem_num()); if (itemTotal == null) itemTotal =
		 * 0; Integer allTotal = courseTotal + itemTotal;
		 */

		/*
		 * if (allTotal <= 0) { model.addAttribute("message",
		 * "정상적인 주문이 아니거나 상품의 수량이 부족합니다."); model.addAttribute("url",
		 * request.getContextPath() + "/cart/cartList.do"); return "common/resultView";
		 * }
		 */

		/*
		 * List<CourseCartVO> courseCart = cartService.getCourseCart(user.getMem_num());
		 * 
		 * List<ItemCartVO> itemCart = cartService.getItemCart(user.getMem_num());
		 * List<ItemCartVO> itemQuan = cartService.getItemQuan(user.getMem_num());
		 */
		// 주문 상품의 대표 상품명 생성
		
		String order_name = "";
		int order_count = courseCart.size() + itemCart.size();
		if (courseCart.size() > 0) {
			if (order_count == 1) {
				order_name = courseCart.get(0).getCourse_name();
			} else {
				order_name = courseCart.get(0).getCourse_name() + "외 " + (order_count - 1) + "건";
			}
		}else if (itemCart.size() == 1) {
			order_name = itemCart.get(0).getItems_name();
		}else if (itemCart.size() > 1) {
			order_name = itemCart.get(0).getItems_name() + "외 " + (itemCart.size() - 1) + "건";
		}

		
		// 개별 주문 상품 저장
		List<OrderDetailVO> orderDetailList = new ArrayList<OrderDetailVO>();

		/*
		 * for(CourseCartVO ccart : courseCart) { CourseVO course =
		 * courseService.selectCourse( ccart.getCourse_num());
		 * logger.debug("<<selectCourse후>> : " + ccart.getCourse_num());
		 * logger.debug("<<selectCourse후>> : " + courseCart);
		 * 
		 * 
		 * if(course.getCourse_limit()==0) { model.addAttribute("message",
		 * "["+course.getCourse_name()+"] 최대 수강인원 제한으로 인해 구매가 불가합니다");
		 * model.addAttribute("url", request.getContextPath()+"/cart/cartList.do");
		 * return "common/resultView"; }
		 * 
		 * 
		 * OrderDetailVO orderDetail = new OrderDetailVO(); orderDetail.setDetail_name(
		 * ccart.getCourse_name()); orderDetail.setPrice( ccart.getCourse_price());
		 * orderDetailList.add(orderDetail);
		 */

		
		for (int k = 0, size = 1; k <= size; k++) {
			
			for (int l = 0, sizel = 1; l < sizel; l++) {
				if (courseCart.size() > 0) {
					sizel = courseCart.size();
				}
				;

				
				for (int m = 0, sizem = 1; m < sizem; m++) {
					if (itemCart.size() > 0) {
						sizem = itemCart.size();
					}
					;

					OrderDetailVO orderDetail = new OrderDetailVO();
					
					if (k == 0) {
					}
					;

					if (k == 0 && m == 0) {
					
						if (courseCart.size() == 0) {
							break;
						}
						
						CourseCartVO ccart = courseCart.get(l);
						CourseVO course = courseService.selectCourse(ccart.getCourse_num());

						orderDetail.setDetail_name(ccart.getCourse_name());
						orderDetail.setPrice(ccart.getCourse_price());

						orderDetail.setCourse_num(ccart.getCourse_num());
						orderDetailList.add(orderDetail);
					} else if (k == 0 && m > 0) {
						break;
					}
					
					if (k == 1 && l == 0) {
						if (itemCart.size() == 0) {
							break;
						}
						
						ItemCartVO icart = itemCart.get(m);
						ItemCartVO iquan = itemQuan.get(m);
						ItemsVO item = itemService.selectItems(icart.getItems_num());

						orderDetail.setDetail_name(icart.getItems_name());
						orderDetail.setPrice(iquan.getItems_total());
						orderDetail.setQuantity(iquan.getQuantity());
						orderDetail.setItems_total(iquan.getItems_total());

						orderDetail.setItems_num(icart.getItems_num());

						logger.debug("<<iiorderDetail>> : " + orderDetail);
						logger.debug("<<iiorderDetail>> : " + orderDetailList);
						logger.debug("<<!!selectItems l>> : " + l);
						logger.debug("<<!!selectItems size>> : " + size);
						logger.debug("<<!!selectItems후>> : " + order_count);
						

						orderDetailList.add(orderDetail);
					} else if (k == 1 && l > 0) {
						break;
					}
					
				}
			}

		}
		
		
		orderVO.setOrder_name(order_name);// 대표 상품명
		orderVO.setOrder_price(allTotal);// 총주문금액
		orderVO.setMem_num(user.getMem_num());// 주문자
		orderVO.setOrder_status(0);
		pointsVO.setUsed_points(allTotal);// 주문자
		pointsVO.setMem_num(user.getMem_num());
		
		orderService.insertOrder(orderVO, pointsVO, orderDetailList);

		// refresh 정보를 응답 헤더에 추가
		response.addHeader("Refresh", "2;url=../main/main.do");
		model.addAttribute("accessMsg", "주문이 완료되었습니다.");
		return "common/notice";
	}
		

	// =====바로주문하기=====//
	// 주문등록 폼 호출
	@PostMapping("/order/orderNowForm.do")
	public String nowForm(OrderVO orderVO, HttpSession session, Model model, HttpServletRequest request) {
		
		String[] course_price = request.getParameterValues("course_price");
		String[] course_quan = request.getParameterValues("course_quan");
		String[] items_price = request.getParameterValues("items_price");
		String[] items_quan = request.getParameterValues("items_quan");
		String[] space_price = request.getParameterValues("space_price");
		String[] order_quantity = request.getParameterValues("order_quantity");

		if(course_price != null) {
			int coursePrice = Integer.parseInt(course_price[0]);
			int courseQuantity = Integer.parseInt(course_quan[0]);
			int courseTotal = coursePrice * courseQuantity;
			model.addAttribute("courseTotal", courseTotal);
		}

		if(items_price != null) {
			int itemsPrice = Integer.parseInt(items_price[0]);
			int itemsQuantity = Integer.parseInt(items_quan[0]);
			int itemsTotal = itemsPrice * itemsQuantity;
			model.addAttribute("itemsTotal", itemsTotal);
		}

		if(space_price != null) {	
			int spacePrice = Integer.parseInt(space_price[0]);
			int orderQuantity = Integer.parseInt(order_quantity[0]);
			int spaceTotal = spacePrice * orderQuantity;
			model.addAttribute("spaceTotal", spaceTotal);
		}

		return "orderNowForm";
	}


	// 폼에서 전송된 데이터 처리
	@PostMapping("/order/nowOrder.do") public String nowSubmit(OrderVO orderVO,
			PointsVO pointsVO, HttpSession session, Model model, HttpServletRequest
			request, HttpServletResponse response) {

		MemberVO user = (MemberVO) session.getAttribute("user");

		OrderDetailVO orderDetail = new OrderDetailVO();
		List<OrderDetailVO> orderDetailList = new ArrayList<OrderDetailVO>();
		
		//클래스 바로구매
		String[] course_onoff = request.getParameterValues("course_onoff");
		String[] course_num = request.getParameterValues("course_num");
		String[] course_name = request.getParameterValues("course_name");
		String[] course_price = request.getParameterValues("course_price");
		String[] space_num = request.getParameterValues("space_num");
		String[] items_num = request.getParameterValues("items_num");
		
		if(course_onoff[0].equals("on")) {//온라인 클래스
			int courseNum = Integer.parseInt(course_num[0]);
			int coursePrice = Integer.parseInt(course_price[0]);
			
			orderDetail.setCourse_num(courseNum);
			orderDetail.setDetail_name(course_name[0]);
			orderDetail.setPrice(coursePrice);
			orderDetail.setQuantity(1);
			
			orderDetailList.add(orderDetail);
			
			orderVO.setOrder_name(course_name[0]);// 대표 상품명
			orderVO.setOrder_price(coursePrice);// 총주문금액
			orderVO.setMem_num(user.getMem_num());// 주문자
			orderVO.setOrder_status(0);
			pointsVO.setUsed_points(coursePrice);
			pointsVO.setMem_num(user.getMem_num());
		}
	
		
		if(course_onoff[0].equals("off")) {//오프라인 클래스
			logger.debug("<<course_onoff>> : "+course_onoff[0]);
			String[] course_quan = request.getParameterValues("course_quan");
			String[] course_total = request.getParameterValues("course_total");
			String[] course_date = request.getParameterValues("course_date");
			String[] course_time = request.getParameterValues("course_time");
			
			
			int courseNum = Integer.parseInt(course_num[0]);
			int courseQuan = Integer.parseInt(course_quan[0]);
			int courseTotal = Integer.parseInt(course_total[0]);
			
			orderDetail.setCourse_num(courseNum);
			orderDetail.setDetail_name(course_name[0]);
			orderDetail.setPrice(courseTotal);
			orderDetail.setQuantity(courseQuan);
			orderDetail.setC_date(course_date[0]);
			orderDetail.setC_time(course_time[0]);

			orderDetailList.add(orderDetail);

			orderVO.setOrder_name(course_name[0]);// 대표 상품명
			orderVO.setOrder_price(courseTotal);// 총주문금액
			orderVO.setMem_num(user.getMem_num());// 주문자
			orderVO.setOrder_status(1);
			pointsVO.setUsed_points(courseTotal);
			pointsVO.setMem_num(user.getMem_num());
			logger.debug("<<orderVO : >>" + orderVO);
		}
		
		//스토어 바로구매
		
		if((items_num[0]) != "" && Integer.parseInt(items_num[0]) > 0 ) {
			String[] items_name = request.getParameterValues("items_name");
			String[] items_quan = request.getParameterValues("items_quan");
			String[] items_total = request.getParameterValues("items_total");
			
			int itemsNum = Integer.parseInt(items_num[0]);
			int itemsQuan = Integer.parseInt(items_quan[0]);
			int itemsTotal = Integer.parseInt(items_total[0]);
			
			orderDetail.setItems_num(itemsNum);
			orderDetail.setDetail_name(items_name[0]);
			orderDetail.setPrice(itemsTotal);
			orderDetail.setQuantity(itemsQuan);

			orderDetailList.add(orderDetail);

					orderVO.setOrder_name(items_name[0]);// 대표 상품명
					orderVO.setOrder_price(itemsTotal);// 총주문금액
					orderVO.setMem_num(user.getMem_num());// 주문자
					orderVO.setOrder_status(0);
					pointsVO.setUsed_points(itemsTotal);
					pointsVO.setMem_num(user.getMem_num());
				}
				
				
		//장소대여 바로구매
		if((space_num[0]) != "" && Integer.parseInt(space_num[0]) > 0 ) {
			String[] space_name = request.getParameterValues("space_name");
			String[] order_quantity = request.getParameterValues("order_quantity");
			String[] space_total = request.getParameterValues("space_total");
			
			int spaceNum = Integer.parseInt(space_num[0]);
			int spaceQuan = Integer.parseInt(order_quantity[0]);
			int spaceTotal = Integer.parseInt(space_total[0]);
			
			orderDetail.setSpace_num(spaceNum);
			orderDetail.setDetail_name(space_name[0]);
			orderDetail.setPrice(spaceTotal);
			orderDetail.setQuantity(spaceQuan);

			orderDetailList.add(orderDetail);

			orderVO.setOrder_name(space_name[0]);// 대표 상품명
			orderVO.setOrder_price(spaceTotal);// 총주문금액
			orderVO.setMem_num(user.getMem_num());// 주문자
			orderVO.setOrder_status(1);
			pointsVO.setUsed_points(spaceTotal);
			pointsVO.setMem_num(user.getMem_num());
		}
		
		orderService.insertOrderNow(orderVO, pointsVO, orderDetailList);

		// refresh 정보를 응답 헤더에 추가
		response.addHeader("Refresh",
				"2;url=../main/main.do");
		model.addAttribute("accessMsg", "주문이 완료되었습니다.");
				return "common/notice"; }

}
