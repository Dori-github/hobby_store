package kr.spring.order.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.order.vo.OrderDetailVO;
import kr.spring.order.vo.OrderVO;

@Mapper
public interface OrderMapper {
	@Select("SELECT orders_seq.nextval FROM dual")
	public int selectOrderNum();
	public void insertOrder(OrderVO order);
	public void insertOrderDetail(OrderDetailVO vo);
	@Update("UPDATE items SET "
			+ "items_quantity = "
			+ "items_quantity - #{quantity} "
			+ "WHERE items_num=#{items_num}")
	public void updateQuantity(OrderDetailVO vo);
	@Delete("DELETE FROM course_cart WHERE "
			+ "course_num=#{course_num} AND mem_num=#{mem_num}")
	public void deleteCartCourse(
			@Param(value="course_num") Integer course_num,
			@Param(value="mem_num") Integer mem_num);
	@Delete("DELETE FROM item_cart WHERE "
			+ "items_num=#{items_num} AND mem_num=#{mem_num}")
	public void deleteCartItem(
			@Param(value="items_num") Integer items_num,
			@Param(value="mem_num") Integer mem_num);

}
 