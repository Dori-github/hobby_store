package kr.spring.points.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.cart.vo.CourseCartVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.order.controller.OrderController;
import kr.spring.points.service.PointsService;
import kr.spring.points.vo.PointsVO;

@Controller
public class PointsController {
	private static final Logger logger = 
			LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private PointsService pointsService;
	
	@RequestMapping("/mypage/pointsList.do")
	public ModelAndView getList(HttpSession session) {
		
		MemberVO user = 
				 (MemberVO)session.getAttribute("user");
		
		int pointsCount = pointsService.getPointsCount(user.getMem_num());
		
		logger.debug("<<pointsCount>> : " + pointsCount);
			
		List<PointsVO> pointsList = null;
		if(pointsCount > 0) {
			pointsList = pointsService.getPointsList(user.getMem_num());
		}
		
		ModelAndView mav = new ModelAndView();
		//뷰 이름 설정(tiles-definition name)
		mav.setViewName("pointsList");
		//데이터 저장
		mav.addObject("pointsCount", pointsCount);
		mav.addObject("pointsList", pointsList);
		
		return mav;
	}
}
