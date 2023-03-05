package kr.spring.event.service;

import java.util.List;
import java.util.Map;

import kr.spring.course.vo.CourseVO;
import kr.spring.event.vo.EventVO;
import kr.spring.items.vo.ItemsVO;

public interface EventService {
		//이벤트 등록
		public void insertEvent(EventVO eventVO);
		//이벤트 목록
		public List<EventVO> selectList(Map<String,Object> map);
		//이벤트 레코드 수
		public int selectRowCount(Map<String,Object> map);
		//이벤트 상세
		public EventVO selectEvent(Integer event_num);
		//이벤트 조회수 등록
		public void updateHit(Integer event_num);
		//이벤트 수정
		public void updateEvent(EventVO eventVO);
		//이벤트 삭제
		public void deleteEvent(Integer event_num);
		
		//이벤트 관련 클래스/상품 목록 조회
		public List<ItemsVO> selectSearchItems(int mem_num);
		public List<CourseVO> selectSearchCourse(int mem_num);
}
