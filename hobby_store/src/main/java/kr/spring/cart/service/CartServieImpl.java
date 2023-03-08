package kr.spring.cart.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.cart.dao.CartMapper;
import kr.spring.cart.vo.CourseCartVO;
import kr.spring.cart.vo.ItemCartVO;
import kr.spring.course.vo.CourseVO;

@Service
@Transactional
public class CartServieImpl implements CartService{

	@Autowired
	private CartMapper cartmapper;
	
	@Override
	public List<CourseCartVO> getCourseCart(int num) {
		return cartmapper.getCourseCart(num);
	}

	@Override
	public int getCartCount() {
		return cartmapper.getCartCount();
	}

	@Override
	public void insertCourseCart(CourseVO Course) {
		return;
	}

	@Override
	public Integer courseTotal(int num) {
		return cartmapper.courseTotal(num);
	}

	@Override
	public List<ItemCartVO> getItemCart(int num) {
		return cartmapper.getItemCart(num);
	}

	@Override
	public int getItemCount() {
		return cartmapper.getItemCount();
	}

	@Override
	public void insertItemCart(ItemCartVO ItemCart) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer itemTotal(int num) {
		return cartmapper.itemTotal(num);
	}

	@Override
	public void updateCart(int quantity, int cart_num) {
		cartmapper.updateCart(quantity, cart_num);
	}

	@Override
	public List<ItemCartVO> getItemQuan(int num) {
		return cartmapper.getItemQuan(num);
	}

}
