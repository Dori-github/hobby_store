package kr.spring.cart.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.cart.vo.CourseCartVO;
import kr.spring.cart.vo.ItemCartVO;
import kr.spring.course.vo.CourseVO;
import kr.spring.items.vo.ItemsVO;

@Mapper
public interface CartMapper {
	//=====클래스 장바구니=====//
	//클래스 장바구니 목록
	/*
	 * @Select("SELECT cc.cart_num, t.cate_parent, t.cate_name, c.course_photo_name1, "
	 * + "c.course_name, c.course_onoff, c.course_price " + "FROM course_cart cc " +
	 * "JOIN course c ON cc.course_num=c.course_num " +
	 * "JOIN course_cate t ON c.cate_nums=t.cate_num " +
	 * "WHERE cc.mem_num=#{mem_num}" + "ORDER BY cc.cart_num DESC")
	 */

	 @Select("SELECT cc.cart_num, c.course_num, c.course_photo_name1, "
	 + "c.course_name, c.course_onoff, c.course_price " 
	 + "FROM course_cart cc " 
	 + "JOIN course c ON cc.course_num=c.course_num "
	 + "WHERE cc.mem_num=#{mem_num}" 
	 + "ORDER BY cc.cart_num DESC")
	 
	public List<CourseCartVO> getCourseCart(int num);
	//총 레코드 수
	@Select("SELECT COUNT(*) FROM course_cart")
	public int getCartCount();
	//클래스 장바구니 정보
	@Select("SELECT * FROM course_cart WHERE "
			+ "course_num=#{course_num} AND "
			+ "mem_num=#{mem_num}")
	public CourseCartVO selectCourseCart(CourseCartVO courseCart);
	//클래스 장바구니 등록
	@Insert("INSERT INTO course_cart (cart_num, quantity, mem_num, course_num) "
			+ "VALUES (course_cart_seq.nextval, 1, #{mem_num}, #{course_num})")
	public void insertCourseCart(CourseCartVO courseCart);
	//회원번호(mem_num)별 총 구입액
	public Integer courseTotal(int num);
	//클래스 장바구니 삭제

	//=====상품 장바구니=====//
	//상품 장바구니 목록
	public List<ItemCartVO> getItemCart(int num);
	//상품 수량, 구입액
	public List<ItemCartVO> getItemQuan(int num);
	
	@Select("SELECT * FROM item_cart WHERE mem_num = #{mem_num} AND items_num=#{items_num}")
	public ItemCartVO getStoredQuan(int mem_num, int items_num);
	
	//총 레코드 수
	@Select("SELECT COUNT(*) FROM item_cart")
	public int getItemCount();
	//상품 장바구니 정보
	@Select("SELECT * FROM item_cart WHERE "
			+ "items_num=#{items_num} AND "
			+ "mem_num=#{mem_num}")
	public ItemCartVO selectItemCart(ItemCartVO itemCart);
	//상품 장바구니 등록
	@Insert("INSERT INTO item_cart (cart_num, quantity, mem_num, items_num) "
			+ "VALUES (item_cart_seq.nextval, #{quantity}, #{mem_num}, #{items_num})")
	public void insertItemCart(ItemCartVO itemCart);
	//회원번호(mem_num)별 총 구입액
	public Integer itemTotal(int num);
	//상품 장바구니 수정(개별 상품 수량 변경)
	@Update("UPDATE item_cart SET quantity=#{quantity} "
			+ "WHERE cart_num=#{cart_num}")
	public void updateCart(int quantity, int cart_num);
	//상품 장바구니 수정(상품번호와 회원변호별 변경)
	@Update("UPDATE item_cart SET "
			+ "quantity=#{quantity} "
			+ "WHERE items_num=#{items_num} AND "
			+ "mem_num=#{mem_num}")
	public void updateCartByItems_num(ItemCartVO itemCart);
	//상품 장바구니 삭제
}
