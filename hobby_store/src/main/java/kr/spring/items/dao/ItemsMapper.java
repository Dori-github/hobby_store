package kr.spring.items.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.items.vo.ItemsVO;

@Mapper
public interface ItemsMapper {

	//상품 등록
	@Insert("INSERT INTO items (items_num, cate_num, items_name, items_price, items_quantity, items_photo1, items_photo2, items_photo3, items_photo_name1, items_content, mem_num, status ) "
			+ "VALUES (items_seq.nextval, #{cate_num}, #{items_name}, #{items_price}, #{items_quantity}, #{items_photo1}, #{items_photo2}, #{items_photo3},#{items_photo_name1}, #{items_content}, #{mem_num}, #{status})")
	public void insertItems(ItemsVO items);
	
	//상품 목록 //xml
	public List<ItemsVO> selectItemsList(Map<String, Object> map);
	//상품 카운트 //xml
	public int selectItemsCount(Map<String, Object> map); 
	//상품 선택
	@Select("SELECT * FROM items WHERE items_num = #{items_num}")
	public ItemsVO selectItems(Integer items_num);
	//상품 수정
	public void updateItems(ItemsVO itemsVO);
	//상품 삭제
	public void deleteItems(ItemsVO itemsVO);
	//카테고리 이름 
	@Select("SELECT cate_name, cate_num FROM items_cate WHERE cate_parent is NULL")
	public List<ItemsVO> selectCate1();
	@Select("SELECT cate_name, cate_num FROM items_cate WHERE cate_parent = #{cate_num}")
	public List<ItemsVO> selectCate2(Integer cate_num);
	


	
	

	
}
