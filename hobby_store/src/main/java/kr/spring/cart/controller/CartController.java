package kr.spring.cart.controller;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.cart.controller.CartController;
import kr.spring.cart.service.CartService;
import kr.spring.cart.vo.CourseCartVO;
import kr.spring.cart.vo.ItemCartVO;
import kr.spring.course.service.CourseService;
import kr.spring.course.vo.CourseVO;
import kr.spring.items.service.ItemsService;
import kr.spring.items.vo.ItemsVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.order.vo.OrderVO;
import kr.spring.points.vo.PointsVO;

@Controller
public class CartController {//메서드 생성, 데이터 처리
	@Autowired
	private CartService cartService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private ItemsService itemsService;
	
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
	
	@RequestMapping("/cart/getQuan.do")
	@ResponseBody
	public Map<String,Object> getQuan(ItemCartVO cart,
			HttpSession session){
		logger.debug("<<zzzzItemCartVO>> : " + cart);

		Map<String,Object> mapJson = 
				new HashMap<String,Object>();

		MemberVO user = 
				(MemberVO)session.getAttribute("user");
		if(user==null) {
//			mapJson.put("status", "noFav");
		}else {
			//로그인된 아이디 셋팅
			cart.setMem_num(user.getMem_num());

			ItemCartVO storedQuan = null;
			storedQuan = cartService.getStoredQuan(user.getMem_num(), cart.getItems_num());
			logger.debug("storedQuan.getQuantity()" + storedQuan);
			logger.debug("storedQuan.getQuantity()" + storedQuan.getQuantity());
			
			/*
			 * if(boardFav!=null) { mapJson.put("status", "yesFav"); }else {
			 * mapJson.put("status", "noFav"); }
			 */
		}
		/*
		 * logger.debug("<<zxzxzx1>> : " + itemQuan); logger.debug("<<zxzxzx2>> : " +
		 * itemQuan.getQuantity());
		 */
//		logger.debug("<<zzz>> : " + cartService.getItemQuan(user.getMem_num()).getQuantity());
		/*
		 * mapJson.put("itemQuan",
		 * cartService.getItemQuan(user.getMem_num()).getQuantity());
		 */
		/*
		 * mapJson.put("storedQuan", storedQuan);
		 */
		return mapJson;
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
	
	//장바구니에 클래스, 상품 추가
	@RequestMapping("/cart/insert.do")
	public String insert(CourseCartVO courseCart, ItemCartVO itemCart, 
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) {
		
		MemberVO user = 
				(MemberVO)session.getAttribute("user");

		String[] items_num = request.getParameterValues("items_num");
		String[] items_quan = request.getParameterValues("items_quan");
		
		
		if(items_num != null && (items_num[0]) != "") {
			logger.debug("<<itemsaaa>> : "+ items_num[0]);

			itemCart.setMem_num(user.getMem_num());
			itemCart.setQuantity(Integer.parseInt(items_quan[0]));

			ItemCartVO db_cart = 
					cartService.selectItemCart(itemCart);
			logger.debug("<<db_cart>> : "+ itemCart);
			logger.debug("<<db_cart>> : "+ db_cart);

			
			if(db_cart==null) {//등록된 동일 상품 없음
				logger.debug("dd");
				cartService.insertItemCart(itemCart);
				logger.debug("ff");
			}else {//등록된 동일 상품 있음
				//재고수를 구하기 위해서 클래스 정보 호출
				ItemsVO item =
				itemsService.selectItems(db_cart.getItems_num());

				logger.debug("<<item>> : "+ item);

				//구매수량 합산 (기존 구매 수량 + 새로운 구매 수량)
				int order_quantity = 
						db_cart.getQuantity() + itemCart.getQuantity();
				
				logger.debug("<<order_quantity>> : "
						+ order_quantity);

				
				if(item.getItems_quantity()<order_quantity) {
					//상품 재고 수량보다 장바구니에 담은
					//구매 수량이 더 많음
					//mapAjax 추가
				}else {
					itemCart.setQuantity(order_quantity);
					cartService.updateCartByItems_num(itemCart);
					logger.debug("<<itemCart>> : "+ itemCart);
					logger.debug("<<item>> : "+ item);

				}
			}
			model.addAttribute("accessMsg", "장바구니에 성공적으로 담겼습니다.");
			  
		}
		
		String[] course_onoff = request.getParameterValues("course_onoff");
		logger.debug("<<cㅇ>> : "+ course_onoff);
		
		if(course_onoff != null) {
			courseCart.setMem_num(user.getMem_num());
			
			CourseCartVO db_cart = 
					cartService.selectCourseCart(courseCart);
			if(db_cart==null) {//등록된 동일 클래스 없음
				logger.debug("<<##@@@ㅇ>> : "+ courseCart);
				
				cartService.insertCourseCart(courseCart);

				logger.debug("<<!<FFFF:" + courseCart);
				logger.debug("<<!@!@!@!@");
			}else {//등록된 동일 클래스 있음
				
				model.addAttribute("accessMsg", "장바구니에 이미 담긴 클래스입니다.");
				  
			}
		 
	}
		// refresh 정보를 응답 헤더에 추가
		response.addHeader("Refresh",
				"2;url=../cart/cartList.do");
		//장바구니로 이동할 지 알림?
		
		  return "common/notice"; }
	
	
	/*
	 * public void insertItemCart(ItemCartVO itemCart, HttpServletRequest request,
	 * HttpSession session) {
	 * 
	 * logger.debug("<<ㅅ>> : "); MemberVO user =
	 * (MemberVO)session.getAttribute("user");
	 * 
	 * String[] items_num = request.getParameterValues("itmes_num"); String[]
	 * items_quan = request.getParameterValues("items_quan");
	 * 
	 * logger.debug("<<items_num>> : " + request.getParameterValues("itmes_num"));
	 * logger.debug("장바구니에 상품 추가" + itemCart);
	 * 
	 * int itemsNum = Integer.parseInt(items_num[0]); int itemsQuan =
	 * Integer.parseInt(items_quan[0]);
	 * 
	 * itemCart.setItems_num(itemsNum); itemCart.setQuantity(itemsQuan);
	 * itemCart.setMem_num(user.getMem_num()); cartService.insertItemCart(itemCart);
	 * logger.debug("장바구니에 상품 추가" + itemCart); }
	 */

	//상품 장바구니 수정(개별 상품 수량 변경)
	@ResponseBody
	@RequestMapping(value = "/updateCart", method = RequestMethod.POST)
	public void updateCart(int quantity, int cart_num) throws Exception {
		 cartService.updateCart(quantity, cart_num);
		 logger.debug("cart : " + quantity + "," + cart_num);
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateCart", method = RequestMethod.GET)
	public Object updateValue(@ModelAttribute("cartVO") ItemCartVO cartVO, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception  {
		
		MemberVO user = 
				 (MemberVO)session.getAttribute("user");
		int ququ = cartService.itemTotal(user.getMem_num());
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("kor", "Korea");
	    map.put("cartVO", cartVO);
	    map.put("ququ", ququ);
	    map.put("bb", cartVO.getQuantity());

		System.out.println("eeeee : " + cartVO);
	        
		return map;
		
	}
}
