package kr.spring.cart.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.cart.dao.CartMapper;
import kr.spring.cart.vo.CourseCartVO;
import kr.spring.cart.vo.ItemCartVO;

@Service
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
	public void insertCourseCart(CourseCartVO CourseCart) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int courseTotal(int num) {
		return cartmapper.courseTotal(num);
	}

	@Override
	public List<ItemCartVO> getItemList(int num) {
		return cartmapper.getItemList(num);
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
	public int itemTotal(int num) {
		return cartmapper.itemTotal(num);
	}

	@Override
	public void updateCart(int quantity, int cart_num) {
		cartmapper.updateCart(quantity, cart_num);
	}

}
