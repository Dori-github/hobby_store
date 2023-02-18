package kr.spring.items.service;

import java.util.List;
import java.util.Map;

import kr.spring.items.vo.ItemsVO;

public interface ItemsService {
	//상품 등록
	public void insertItems(ItemsVO items);
	
	//상품 목록
	public List<ItemsVO> selectList(Map<String, Object> map);
	public int selectRowCount(Map<String, Object> map);
}
