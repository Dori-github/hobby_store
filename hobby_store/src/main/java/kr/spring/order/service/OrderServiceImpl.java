package kr.spring.order.service;

import java.awt.Point;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.course.dao.CourseMapper;
import kr.spring.order.controller.OrderController;
import kr.spring.order.dao.OrderMapper;
import kr.spring.order.vo.OrderDetailVO;
import kr.spring.order.vo.OrderVO;
import kr.spring.points.dao.PointsMapper;
import kr.spring.points.vo.PointsVO;

@Service
public class OrderServiceImpl implements OrderService{
	private static final Logger logger = 
			LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private PointsMapper pointsMapper;
	
	@Override
	public void insertOrder(OrderVO order, PointsVO points,
							List<OrderDetailVO> list) {
		//주문 정보 추가
		order.setOrder_num(orderMapper.selectOrderNum());
		orderMapper.insertOrder(order);
		
		for(OrderDetailVO vo : list) {
			//주문 상세 정보 추가
			vo.setOrder_num(order.getOrder_num());
			orderMapper.insertOrderDetail(vo);
			//재고 업데이트
			logger.debug("<<업뎃전>> : " + vo); 
			logger.debug("<<업뎃전>> : " + vo.getItems_num()); 
			
			orderMapper.updateQuantity(vo);
			

			logger.debug("<<업뎃후>> : " + vo); 
			logger.debug("<<업뎃후>> : " + vo.getItems_num());
			//장바구니에서 주문 상품 삭제
			orderMapper.deleteCartCourse( vo.getCourse_num(), order.getMem_num());
			orderMapper.deleteCartItem( vo.getItems_num(), order.getMem_num());
			logger.debug("<<상품 삭제>> : " + vo.getCourse_num(), order.getMem_num());
			logger.debug("<<상품 삭제>> : " + vo.getItems_num(), order.getMem_num());
			
			//포인트 차감
			logger.debug("<<포인트 차감>> : " + points.getUsed_points());
			pointsMapper.usePoints(points);
		}
		
			
	}

	@Override
	public void insertOrderNow(OrderVO order, PointsVO points, List<OrderDetailVO> list) {
		//주문 정보 추가
				order.setOrder_num(orderMapper.selectOrderNum());
				orderMapper.insertOrderNow(order);
				
				for(OrderDetailVO vo : list) {
					//주문 상세 정보 추가
					vo.setOrder_num(order.getOrder_num());
					orderMapper.insertOrderDetail(vo);
					//재고 업데이트
					logger.debug("<<now업뎃전>> : " + vo); 
					logger.debug("<<now업뎃전>> : " + vo.getItems_num()); 
					
					orderMapper.updateQuantity(vo);
					

					logger.debug("<<now업뎃후>> : " + vo); 
					logger.debug("<<now업뎃후>> : " + vo.getItems_num());
					//장바구니에서 주문 상품 삭제
					orderMapper.deleteCartCourse( vo.getCourse_num(), order.getMem_num());
					orderMapper.deleteCartItem( vo.getItems_num(), order.getMem_num());
					logger.debug("<<상품 삭제>> : " + vo.getCourse_num(), order.getMem_num());
					logger.debug("<<상품 삭제>> : " + vo.getItems_num(), order.getMem_num());
					
					//포인트 차감
					logger.debug("<<포인트 차감>> : " + points.getUsed_points());
					pointsMapper.usePoints(points);
				}
	}

	@Override
	public int selectReservedNum(int course_num,String c_date,String c_time) {
		return orderMapper.selectReservedNum(course_num,c_date,c_time);
	}
	
	@Override
	public int selectReservedNum2(int course_num) {
		return orderMapper.selectReservedNum2(course_num);
	}

	@Override
	public List<String> selectSoldOutTimes(int course_num, String c_date) {
		return orderMapper.selectSoldOutTimes(course_num,c_date);
	}

	@Override
	public List<OrderDetailVO> selectSoldOutDates(int course_num) {
		return orderMapper.selectSoldOutDates(course_num);
	}

	
}
