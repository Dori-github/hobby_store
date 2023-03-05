package kr.spring.event.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.course.vo.CourseVO;
import kr.spring.event.vo.EventApplyVO;
import kr.spring.event.vo.EventVO;
import kr.spring.items.vo.ItemsVO;

@Mapper
public interface EventMapper {
	//이벤트 번호 생성
	@Select("SELECT event_seq.nextval FROM dual")
	public int selectEvent_num();
	//이벤트 등록
	public void insertEvent(EventVO eventVO);
	//이벤트 디테일 등록
	@Insert("INSERT INTO event_detail (event_num,event_detail,mem_num) VALUES (#{event_num},#{event_detail},#{mem_num})")
	public void insertEventDetail(EventVO eventVO);
	//이벤트 목록
	public List<EventVO> selectList(Map<String,Object> map);
	//이벤트 레코드 수
	public int selectRowCount(Map<String,Object> map);
	//이벤트 상세
	@Select("SELECT * FROM event JOIN event_detail USING(event_num) WHERE event_num=#{event_num}")
	public EventVO selectEvent(Integer event_num);
	//이벤트 조회수 등록
	@Update("UPDATE event SET event_hit=event_hit+1 WHERE event_num=#{event_num}")
	public void updateHit(Integer event_num);
	//이벤트 수정
	public void updateEvent(EventVO eventVO);
	//이벤트 수정
	@Update("UPDATE event_detail SET event_detail=#{event_detail} WHERE event_num=#{event_num}")
	public void updateEventDetail(EventVO eventVO);
	//이벤트 삭제
	@Delete("DELETE FROM event WHERE event_num=#{event_num}")
	public void deleteEvent(Integer event_num);
	@Delete("DELETE FROM event_detail WHERE event_num=#{event_num}")
	public void deleteEventDetail(Integer event_num);
	
	//이벤트 관련 클래스/상품 목록 조회
	@Select("SELECT items_num, items_name FROM items WHERE mem_num=#{mem_num}")
	public List<ItemsVO> selectSearchItems(int mem_num);
	@Select("SELECT course_num, course_name FROM course WHERE mem_num=#{mem_num}")
	public List<CourseVO> selectSearchCourse(int mem_num);
	
	//이벤트 신청
	@Insert("INSERT INTO event_apply (event_a_num,event_num,mem_num) VALUES(event_apply_seq.nextval,#{event_num},#{mem_num})")
	public void insertEventApply(EventApplyVO eventApplyVO);
}
