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
	//원데이클래스 예약인원수
	//sum이 null이면 0 저장
	@Select("Select NVL(sum(quantity),0) FROM orders_detail WHERE course_num=#{course_num} "
			+ "AND c_date=#{c_date} AND c_time=#{c_time}")
	public int selectReservedNum(int course_num,String c_date,String c_time);
	//정기클래스 예약인원수
	@Select("select NVL(sum(quantity),0) FROM orders_detail WHERE course_num=#{course_num}")
	public int selectReservedNum2(int course_num);
	//날짜별 매진된 시간 저장
	@Select("select c_time from (select course_num,c_date,c_time,sum(quantity) sum from orders_detail "
			+ "group by course_num,c_date,c_time having course_num=#{course_num} and c_date=#{c_date})d "
			+ "left join course c on d.course_num=c.course_num where sum=course_limit order by c_time")
	public List<String> selectSoldOutTimes(int course_num,String c_date);
	//모든 시간이 매진된 날짜 저장(달력에 표시)
	@Select("select * from (select course_num,c_date,sum(quantity) AS res_sum from orders_detail "
			+ "group by course_num,c_date having course_num=#{course_num}) "
			+ "join (select course_num,course_reg_date from course_time) using (course_num) "
			+ "where to_char(to_date(c_date,'YYYY-MM-DD'),'DY')=course_reg_date")
	public List<OrderDetailVO> selectSoldOutDates(int course_num);
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
 