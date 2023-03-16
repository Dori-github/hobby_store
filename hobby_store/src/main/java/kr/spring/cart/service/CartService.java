package kr.spring.cart.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.cart.vo.CourseCartVO;
import kr.spring.cart.vo.ItemCartVO;
import kr.spring.course.vo.CourseVO;
import kr.spring.items.vo.ItemsVO;
import kr.spring.member.vo.MemberVO;

public interface CartService {
	//클래스 장바구니 목록
	public List<CourseCartVO> getCourseCart(int num);
	//클래스 장바구니 목록(클래스 번호)
	public List<CourseCartVO> getCourseCartNum(int mem_num, int course_num);
	//총 레코드 수
	public int getCartCount(int num);
	//클래스 장바구니 정보
	public CourseCartVO selectCourseCart(CourseCartVO courseCart);
	//클래스 장바구니 등록
	public void insertCourseCart(CourseCartVO courseCart);
	//회원번호(mem_num)별 총 구입액
	public Integer courseTotal(int num);
	//클래스 장바구니 삭제

	//=====상품 장바구니=====//
	//상품 장바구니 목록
	public List<ItemCartVO> getItemCart(int num);
	//상품 장바구니 목록(상품 번호)
	public List<ItemCartVO> getItemCartNum(int mem_num, int items_num);
	public List<ItemCartVO> getItemQuan(int num);
	public ItemCartVO getStoredQuan(int mem_num, int items_num);
	//총 레코드 수
	public int getItemCount(int num);
	//상품 장바구니 정보
	public ItemCartVO selectItemCart(ItemCartVO itemCart);
	//상품 장바구니 등록
	public void insertItemCart(ItemCartVO itemCart);
	//회원번호(mem_num)별 총 구입액
	public Integer itemTotal(int num);
	//상품 장바구니 수정(개별 상품 수량 변경)
	public void updateCart(int quantity, int cart_num);
	//상품 장바구니 수정(상품번호와 회원변호별 변경)
	public void updateCartByItems_num(ItemCartVO itemCart);
	//상품 장바구니 삭제
}
