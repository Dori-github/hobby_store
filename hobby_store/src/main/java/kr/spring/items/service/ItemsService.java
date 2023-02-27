package kr.spring.items.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import kr.spring.items.vo.ItemsVO;

public interface ItemsService {
	//상품 등록
	public void insertItems(ItemsVO items);
	
	//상품 목록
	public List<ItemsVO> selectItemsList(Map<String, Object> map);
	//상품 카운트
	public int selectItemsCount(Map<String, Object> map); 
	//상품 선택
	public ItemsVO selectItems(Integer items_num);
	//상품 수정
	public void updateItems(ItemsVO itemsVO);
	//상품 삭제
	public void deleteItems(ItemsVO itemsVO);
	
	//부모 카테고리 
	public List<ItemsVO> selectCate1();
	
	public List<ItemsVO> selectCate2(Integer cate_num);


}
