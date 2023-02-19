package kr.spring.cart.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.cart.vo.CourseCartVO;
import kr.spring.cart.vo.ItemCartVO;

@Mapper
public interface CartMapper {
	//=====클래스 장바구니=====//
	//클래스 장바구니 목록
	@Select("SELECT t.cate_parent, t.cate_name, c.course_photo1, "
			+ "c.course_name, c.course_onoff, c.course_price "
			+ "FROM course_cart cc "
            + "JOIN course c ON cc.course_num=c.course_num "
            + "JOIN course_cate t ON c.cate_num=t.cate_num "            
            + "WHERE cc.mem_num=#{mem_num}")
	public List<CourseCartVO> getCourseCart(int num);
	//총 레코드 수
	@Select("SELECT COUNT(*) FROM course_cart")
	public int getCartCount();
	//클래스 장바구니 등록
	@Insert("INSERT INTO course_cart (cart_num,quantity,"
			+ "mem_num,course_num) "
			+ "VALUES (course_cart_seq.nextval,#{quantity},"
			+ "#{mem_num},#{course_num})")
	public void insertCourseCart(CourseCartVO CourseCart);
	//회원번호(mem_num)별 총 구입액
	public int courseTotal(int num);
	//클래스 장바구니 삭제

	//=====상품 장바구니=====//
	//상품 장바구니 목록
	public List<ItemCartVO> getItemList(int num);
	//총 레코드 수
	@Select("SELECT COUNT(*) FROM item_cart")
	public int getItemCount();
	//상품 장바구니 등록
	@Insert("")
	public void insertItemCart(ItemCartVO ItemCart);
	//회원번호(mem_num)별 총 구입액
	public int itemTotal(int num);
	//상품 장바구니 수정(개별 상품 수량 변경)
	//상품 장바구니 수정(상품번호와 회원변호별 변경)
	//상품 장바구니 삭제
}
