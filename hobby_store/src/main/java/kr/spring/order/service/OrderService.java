package kr.spring.order.service;

import java.util.List;

import kr.spring.order.vo.OrderDetailVO;
import kr.spring.order.vo.OrderVO;

public interface OrderService {
	public void insertOrder(OrderVO order,
            List<OrderDetailVO> list);

}
