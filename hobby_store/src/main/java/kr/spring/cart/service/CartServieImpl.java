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
import kr.spring.items.vo.ItemsVO;

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
	public void insertCourseCart(CourseCartVO courseCart) {
		cartmapper.insertCourseCart(courseCart);
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
	public int getItemCount(int num) {
		return cartmapper.getItemCount(num);
	}

	@Override
	public void insertItemCart(ItemCartVO itemCart) {
		cartmapper.insertItemCart(itemCart);
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

	@Override
	public ItemCartVO getStoredQuan(int mem_num, int items_num) {
		return cartmapper.getStoredQuan(mem_num, items_num);
	}

	@Override
	public CourseCartVO selectCourseCart(CourseCartVO courseCart) {
		return cartmapper.selectCourseCart(courseCart);
	}

	@Override
	public ItemCartVO selectItemCart(ItemCartVO itemCart) {
		return cartmapper.selectItemCart(itemCart);
	}

	@Override
	public void updateCartByItems_num(ItemCartVO itemCart) {
		cartmapper.updateCartByItems_num(itemCart);
	}

	@Override
	public void deleteCourseCart(Integer c_cart_num) {
		cartmapper.deleteCourseCart(c_cart_num);
	}

	@Override
	public void deleteItemCart(Integer i_cart_num) {
		cartmapper.deleteItemCart(i_cart_num);
	}

	@Override
	public int getCartCount(int num) {
		return cartmapper.getCartCount(num);
	}

}
