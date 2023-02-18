package kr.spring.cart.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.cart.vo.CourseCartVO;

@Mapper
public interface CartMapper {
	//강의 장바구니 목록
	@Select("SELECT * FROM course_cart cc "
			+ "JOIN course c ON cc.course_num=c.course_num "
			+ "WHERE cc.mem_num=#{mem_num}")
	public List<CourseCartVO> getCartList(int num);
	//총 레코드 수
	@Select("SELECT COUNT(*) FROM course_cart")
	public int getCartCount();
	//강의 장바구니 등록
	@Insert("INSERT INTO course_cart (cart_num,quantity,"
			+ "mem_num,course_num) "
			+ "VALUES (course_cart_seq.nextval,#{quantity},"
			+ "#{mem_num},#{course_num})")
	public void insertCourseCart(CourseCartVO CourseCart);
	//강의 장바구니 상세
	//회원번호(mem_num)별 총 구입액
	//강의 장바구니 수정(개별 상품 수량 변경)
	//강의 장바구니 수정(상품번호와 회원변호 별 변경)
	//강의 장바구니 삭제
}
