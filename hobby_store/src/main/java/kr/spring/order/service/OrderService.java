package kr.spring.order.service;

import java.util.List;

import kr.spring.order.vo.OrderDetailVO;
import kr.spring.order.vo.OrderVO;
import kr.spring.points.vo.PointsVO;

public interface OrderService {
	public void insertOrder(OrderVO order, PointsVO points,
            List<OrderDetailVO> list);

}
