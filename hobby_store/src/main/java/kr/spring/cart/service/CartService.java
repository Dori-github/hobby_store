package kr.spring.cart.service;

import java.util.List;
import java.util.Map;

import kr.spring.cart.vo.CourseCartVO;

public interface CartService {
	//강의 장바구니 목록
	public List<CourseCartVO> getCartList(int num);
	//총 레코드 수
	public int getCartCount();
	//강의 장바구니 등록
	public void insertCourseCart(CourseCartVO CourseCart);
	//강의 장바구니 상세
	//회원번호(mem_num)별 총 구입액
	//강의 장바구니 수정(개별 상품 수량 변경)
	//강의 장바구니 수정(상품번호와 회원변호 별 변경)
	//강의 장바구니 삭제
}
