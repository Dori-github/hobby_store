package kr.spring.event.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.event.vo.EventVO;

@Transactional
@Service
public class EventServiceImpl implements EventService{

	@Override
	public List<EventVO> selectList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void insertEvent(EventVO eventVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EventVO selectEvent(Integer event_num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateHit(Integer event_num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateEvent(Integer event_num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteEvent(Integer event_num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFile(Integer event_num) {
		// TODO Auto-generated method stub
		
	}

}
