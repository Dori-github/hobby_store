package kr.spring.items.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import kr.spring.items.vo.ItemsVO;

@Mapper
public interface ItemsMapper {

	//상품 등록
	@Insert("INSERT INTO items (items_num, cate_num, items_name, price, quantity, photo1, photo2, photo3, items_content, mem_num ) "
			+ "VALUES (items_seq.nextval, #{cate_num}, {items_name}, #{price}, #{quantity}, #{photo1}, #{photo2}, #{photo3}, #{items_content}, #{mem_num} )")
	public void insertItems(ItemsVO items);
	
	//상품 목록
	public List<ItemsVO> selectList(Map<String, Object> map); 
	public int selectRowCount(Map<String, Object> map);
	
}
