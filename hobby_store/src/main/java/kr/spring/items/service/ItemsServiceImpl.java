package kr.spring.items.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.spring.items.vo.ItemsVO;

 
@Service
public class ItemsServiceImpl implements ItemsService  {

	@Override
	public void insertItems(ItemsVO items) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ItemsVO> selectList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
