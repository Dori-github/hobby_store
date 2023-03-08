package kr.spring.items.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.items.vo.ItemsFavVO;
import kr.spring.items.vo.ItemsReplyVO;
import kr.spring.items.vo.ItemsVO;

@Mapper
public interface ItemsMapper {

	//상품 등록
	@Insert("INSERT INTO items (items_num, cate_num, items_name, items_price, items_quantity, items_photo1, items_photo2, items_photo3, items_photo_name1, items_photo_name2, items_photo_name3, items_content, mem_num, status, packaging, items_zipcode, items_address1, items_address2 ) "
			+ "VALUES (items_seq.nextval, #{cate_num}, #{items_name}, #{items_price}, #{items_quantity}, #{items_photo1}, #{items_photo2}, #{items_photo3},#{items_photo_name1},#{items_photo_name2}, #{items_photo_name3}, #{items_content}, #{mem_num}, #{status}, #{packaging}, #{items_zipcode}, #{items_address1}, #{items_address2})")
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
	@Select("SELECT * FROM items_cate")
	public List<ItemsVO> selectCate();
	//상품 조회수
	@Update("UPDATE items SET items_hit = items_hit+1 WHERE items_num = #{items_num}")
	public void updateHit(Integer items_num);
	
	//상품 좋아요
	@Select("SELECT * FROM items_fav WHERE items_num = #{items_num} AND fmem_num = #{fmem_num} ")
	public ItemsFavVO selectItemsFav(ItemsFavVO fav);
	//상품 좋아요 삭제 
	@Select("DELETE FROM items_fav WHERE fav_num = #{fav_num}")
	public void deleteItemsFav(Integer fav_num);
	//상품 좋아요 등록
	@Insert("INSERT INTO items_fav (fav_num, fmem_num, items_num ) VALUES (items_fav_seq.nextval, #{fmem_num}, #{items_num} )")
	public void insertItemsFav(ItemsFavVO fav);
	//좋아요 수 췤
	@Select("SELECT COUNT(*) FROM items_fav WHERE items_num = #{items_num}")
	public int selectItemsFavCount(Integer items_num);
	//상품 좋아요를 누른 사람 찾기 
	@Select("select * from items a join items_fav b ON a.items_num = b.items_num  ")
	public List<ItemsVO> selectFavMem();
	
	//리뷰
	public List<ItemsReplyVO> selectListReply(Map<String, Object> map);
	@Select("SELECT COUNT(*) FROM items_reply JOIN member USING(mem_num) WHERE items_num = #{items_num}")
	public int selectRowCountReply(Map<String, Object> map);
	@Insert("INSERT INTO items_reply(reply_num, star_auth, reply_content, items_num, mem_num, reply_photo1, reply_photo_name1 ,reply_photo2, reply_photo_name2 ,reply_photo3, reply_photo_name3) VALUES (items_reply_seq.nextval, #{star_auth}, #{reply_content}, #{items_num}, #{mem_num}, #{reply_photo1, jdbcType=BLOB}, #{reply_photo_name1, jdbcType=VARCHAR},#{reply_photo2, jdbcType=BLOB}, #{reply_photo_name2, jdbcType=VARCHAR}, #{reply_photo3, jdbcType=BLOB}, #{reply_photo_name3, jdbcType=VARCHAR})")
	public void insertReply(ItemsReplyVO itemsReply);
	@Select("SELECT * FROM items_reply WHERE reply_num = #{reply_num}")
	public ItemsReplyVO selectReply(Integer reply_num);
	@Update("UPDATE items_reply SET reply_content = {reply_content}, reply_mdate = SYSDATE, reply_photo1 = #{reply_photo1,jdbcType=BLOB}, reply_photo_name1 = #{reply_photo_name1,jdbcType=VARCHAR}, reply_photo2 = #{reply_photo2,jdbcType=BLOB}, reply_photo_name2 = #{reply_photo_name2,jdbcType=VARCHAR}, reply_photo3 = #{reply_photo3,jdbcType=BLOB}, reply_photo_name3 = #{reply_photo_name3,jdbcType=VARCHAR}")
	public void updateReply(ItemsReplyVO itemsReply);
	public void deleteReply(Integer reply_num);
	public void deleteReplyByItemsNum(Integer items_num);
	
	//상세페이지 별점
	@Select("SELECT items_num, ROUND(AVG(star_auth),2) AS starcount  FROM items JOIN items_reply USING(items_num) WHERE items_num = #{items_num} GROUP BY items_num")
	public ItemsVO selectStar(Integer items_num);
	
	@Select("select items_num, count(reply_num) AS replycount FROM items join items_reply USING(items_num) WHERE items_num = #{items_num} GROUP BY items_num")
	public ItemsVO selectReplyCount(Integer items_num);
	

	
}
