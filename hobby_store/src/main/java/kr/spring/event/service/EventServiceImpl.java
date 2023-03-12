package kr.spring.event.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.course.vo.CourseVO;
import kr.spring.event.dao.EventMapper;
import kr.spring.event.vo.EventApplyVO;
import kr.spring.event.vo.EventVO;
import kr.spring.items.vo.ItemsVO;

@Service
@Transactional
public class EventServiceImpl implements EventService{

	@Autowired
	private EventMapper eventMapper;
	
	@Override
	public List<EventVO> selectList(Map<String, Object> map) {
		return eventMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return eventMapper.selectRowCount(map);
	}

	@Override
	public void insertEvent(EventVO eventVO) {
		eventVO.setEvent_num(eventMapper.selectEvent_num());
		eventMapper.insertEventDetail(eventVO);
		eventMapper.insertEvent(eventVO);
	}

	@Override
	public EventVO selectEvent(Integer event_num) {
		return eventMapper.selectEvent(event_num);
	}

	@Override
	public void updateHit(Integer event_num) {
		eventMapper.updateHit(event_num);
	}

	@Override
	public void updateEvent(EventVO eventVO) {
		eventMapper.updateEvent(eventVO);
		eventMapper.updateEventDetail(eventVO);
	}

	@Override
	public void deleteEvent(Integer event_num) {
		eventMapper.deleteEvent(event_num);
		eventMapper.deleteEventDetail(event_num);
	}

	@Override
	public List<ItemsVO> selectSearchItems(int mem_num) {
		return eventMapper.selectSearchItems(mem_num);
	}

	@Override
	public List<CourseVO> selectSearchCourse(int mem_num) {
		return eventMapper.selectSearchCourse(mem_num);
	}

	@Override
	public void insertEventApply(EventApplyVO eventApplyVO) {
		eventMapper.insertEventApply(eventApplyVO);
	}

	@Override
	public EventApplyVO selectEventApply(int mem_num) {
		return eventMapper.selectEventApply(mem_num);
	}

}
