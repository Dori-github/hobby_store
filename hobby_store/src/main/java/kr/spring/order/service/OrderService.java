package kr.spring.order.service;

import java.util.List;

import kr.spring.order.vo.OrderDetailVO;
import kr.spring.order.vo.OrderVO;
import kr.spring.points.vo.PointsVO;

public interface OrderService {
	public void insertOrder(OrderVO order, PointsVO points,
            List<OrderDetailVO> list);
	public int selectReservedNum(int course_num,String c_date,String c_time);
	public int selectReservedNum2(int course_num);
	public List<String> selectSoldOutTimes(int course_num,String c_date);
	public List<OrderDetailVO> selectSoldOutDates(int course_num);
	public void insertOrderNow(OrderVO order, PointsVO points, 
			List<OrderDetailVO> list);

}
