package kr.spring.order.dao;

import java.util.List;

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
	//sum이 null이면 0 저장
	@Select("Select NVL(sum(quantity),0) FROM orders_detail WHERE course_num=#{course_num} AND c_date=#{c_date} AND c_time=#{c_time}")
	public int selectReservedNum(int course_num,String c_date,String c_time);
	@Select("select c_time from (select course_num,c_date,c_time,sum(quantity) sum from orders_detail "
			+ "group by course_num,c_date,c_time having course_num=#{course_num} and c_date=#{c_date})d "
			+ "left join course c on d.course_num=c.course_num where sum=course_limit")
	public List<String> selectSoldOutTimes(int course_num,String c_date);
	public void insertOrder(OrderVO order);
	public void insertOrderNow(OrderVO order);
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
	@Delete("DELETE FROM items_cart WHERE "
			+ "items_num=#{items_num} AND mem_num=#{mem_num}")
	public void deleteCartItem(
			@Param(value="items_num") Integer items_num,
			@Param(value="mem_num") Integer mem_num);

}
 