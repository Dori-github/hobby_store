package kr.spring.cart.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.cart.vo.CourseCartVO;
import kr.spring.cart.vo.ItemCartVO;

public interface CartService {
	//클래스 장바구니 목록
	public List<CourseCartVO> getCourseCart(int num);
	//총 레코드 수
	public int getCartCount();
	//클래스 장바구니 등록
	public void insertCourseCart(CourseCartVO CourseCart);
	//회원번호(mem_num)별 총 구입액
	public Integer courseTotal(int num);
	//클래스 장바구니 삭제

	//=====상품 장바구니=====//
	//상품 장바구니 목록
	public List<ItemCartVO> getItemCart(int num);
	//상품 수량, 구입액
	public List<ItemCartVO> getItemQuan(int num);
	//총 레코드 수
	public int getItemCount();
	//상품 장바구니 등록
	public void insertItemCart(ItemCartVO ItemCart);
	//회원번호(mem_num)별 총 구입액
	public Integer itemTotal(int num);
	//상품 장바구니 수정(개별 상품 수량 변경)
	public void updateCart(int quantity, int cart_num);
	//상품 장바구니 수정(상품번호와 회원변호별 변경)
	//상품 장바구니 삭제
}
