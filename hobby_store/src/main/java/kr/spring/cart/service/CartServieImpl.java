package kr.spring.cart.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.cart.dao.CartMapper;
import kr.spring.cart.vo.CourseCartVO;

@Service
public class CartServieImpl implements CartService{

	@Autowired
	private CartMapper cartmapper;
	
	@Override
	public List<CourseCartVO> getCartList(int num) {
		return cartmapper.getCartList(num);
	}

	@Override
	public int getCartCount() {
		return cartmapper.getCartCount();
	}

	@Override
	public void insertCourseCart(CourseCartVO CourseCart) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int courseTotal(int num) {
		return cartmapper.courseTotal(num);
	}

}
