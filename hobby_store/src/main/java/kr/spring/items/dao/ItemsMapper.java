package kr.spring.items.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.items.vo.ItemsFavVO;
import kr.spring.items.vo.ItemsReplyFavVO;
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
	public void updateItemsAll(ItemsVO itemsVO);
	//상품 삭제 
	@Delete("DELETE FROM items WHERE items_num = #{items_num}")
	public void deleteItems(Integer items_num);
	//카테고리 이름 
	@Select("SELECT cate_name, cate_num FROM items_cate WHERE cate_parent is NULL")
	public List<ItemsVO> selectCate1();
	
	@Select("SELECT cate_name, cate_num FROM items_cate WHERE cate_parent is NULL")
	public ItemsVO selectParentCate1();
	
	@Select("SELECT cate_num FROM items WHERE items_num = #{items_num}")
	public int searchCateName(Integer items_num);
	@Select("select cate_name, cate_num FROM items_cate WHERE cate_num = (select cate_parent FROM items_cate WHERE cate_num = #{cate_num}) ")
	public ItemsVO searchCateParent(Integer cate_num);
	
	@Select("SELECT cate_name, cate_num FROM items_cate WHERE cate_parent = #{cate_num}")
	public List<ItemsVO> selectCate2(Integer cate_num);
	@Select("SELECT cate_name, cate_num FROM items_cate WHERE cate_num = #{cate_num}")
	public ItemsVO selectChildCate2(Integer cate_num);
	
	@Select("SELECT * FROM items_cate")
	public List<ItemsVO> selectCate();
	//상품 조회수
	@Update("UPDATE items SET items_hit = items_hit+1 WHERE items_num = #{items_num}")
	public void updateHit(Integer items_num);
	//상품 등록한 사람 
	@Select("SELECT mem_num FROM items WHERE items_num = #{items_num}")	
	public int itemsOner(Integer items_num);
	//상품 좋아요
	@Select("SELECT * FROM items_fav WHERE items_num = #{items_num} AND fmem_num = #{fmem_num} ")
	public ItemsFavVO selectItemsFav(ItemsFavVO fav);
	//상품 좋아요 삭제 
	@Select("DELETE FROM items_fav WHERE fav_num = #{fav_num}")
	public void deleteItemsFav(Integer fav_num);
	//상품 좋아요 등록
	@Insert("INSERT INTO items_fav (fav_num, fmem_num, items_num) VALUES (items_fav_seq.nextval, #{fmem_num}, #{items_num})")
	public void insertItemsFav(ItemsFavVO fav);
	//좋아요 수 췤
	@Select("SELECT COUNT(*) FROM items_fav WHERE items_num = #{items_num}")
	public int selectItemsFavCount(Integer items_num);
	//상품 좋아요를 누른 사람 찾기 
	@Select("select * from items a join items_fav b ON a.items_num = b.items_num  ")
	public List<ItemsVO> selectFavMem();
	//상품의 모든 좋아요 삭제
	@Select("DELETE FROM items_fav WHERE items_num = #{items_num}")
	public void deleteItemsAllFav(Integer items_num);
	@Delete("DELETE FROM items_reply WHERE reply_num = #{reply_num}")
	public void deleteReply(ItemsVO reply);
	
	
	//후기
	public List<ItemsReplyVO> selectListReply(Map<String, Object> map);
	@Select("SELECT COUNT(*) FROM items_reply JOIN member USING(mem_num) WHERE items_num = #{items_num}")
	public int selectRowCountReply(Map<String, Object> map);
	@Insert("INSERT INTO items_reply(reply_num, star_auth, reply_content, items_num, mem_num, reply_photo1, reply_photo_name1 ,reply_photo2, reply_photo_name2 ,reply_photo3, reply_photo_name3) VALUES (items_reply_seq.nextval, #{star_auth}, #{reply_content}, #{items_num}, #{mem_num}, #{reply_photo1, jdbcType=BLOB}, #{reply_photo_name1, jdbcType=VARCHAR},#{reply_photo2, jdbcType=BLOB}, #{reply_photo_name2, jdbcType=VARCHAR}, #{reply_photo3, jdbcType=BLOB}, #{reply_photo_name3, jdbcType=VARCHAR})")
	public void insertReply(ItemsReplyVO itemsReply);
	@Select("SELECT * FROM items_reply WHERE reply_num = #{reply_num}")
	public ItemsReplyVO selectReply(Integer reply_num);
	//후기 사진 갱신 
	@Update("UPDATE items_reply SET reply_content = #{reply_content}, reply_mdate = SYSDATE, reply_photo1 = #{reply_photo1,jdbcType=BLOB}, reply_photo_name1 = #{reply_photo_name1,jdbcType=VARCHAR}, reply_photo2 = #{reply_photo2,jdbcType=BLOB}, reply_photo_name2 = #{reply_photo_name2,jdbcType=VARCHAR}, reply_photo3 = #{reply_photo3,jdbcType=BLOB}, reply_photo_name3 = #{reply_photo_name3,jdbcType=VARCHAR} WHERE reply_num = #{reply_num}")
	public void updateReply(ItemsReplyVO itemsReply);
	@Update("UPDATE items_reply SET reply_content = #{reply_content}, reply_mdate = SYSDATE WHERE reply_num = #{reply_num}")
	public void updateReplyContent(ItemsReplyVO itemsReply);
	public void updateReplyAll(ItemsReplyVO itemsReply);
	public void updateFormDelete(ItemsReplyVO itemsReply);
	
	
	@Delete("DELETE FROM items_reply WHERE items_num = #{items_num}")
	public void deleteReply2(Integer items_num);
	
	@Delete("DELETE FROM items_reply WHERE items_num = #{items_num}")
	public void deleteReplyByItemsNum(Integer items_num);
	
	//상세페이지 별점
	@Select("SELECT ROUND(AVG(star_auth),1) AS starcount  FROM items_reply  WHERE items_num = #{items_num}")
	public Float selectStar(Integer items_num);
	
	@Select("SELECT COUNT(reply_num) AS replycount FROM items_reply  WHERE items_num = #{items_num}")
	public int selectReplyCount(Integer items_num);
	////별점 평균, 후기 개수, 5점 비율 구하기 
	@Select("SELECT COUNT(reply_num) AS star5 FROM items_reply WHERE star_auth = 5 AND items_num =#{items_num}")
	public int select5star(Integer items_num);
	@Select("SELECT COUNT(reply_num) AS starall FROM items_reply WHERE items_num = #{items_num}")
	public int selectallstar(Integer items_num);
	
	
	//후기 좋아요
	@Select("SELECT * FROM items_reply_fav WHERE reply_num = #{reply_num} AND fmem_num = #{fmem_num} ")
	public ItemsReplyFavVO selectReplyFav(ItemsReplyFavVO rfav);
	//후기 좋아요 삭제 
	@Select("DELETE FROM items_reply_fav WHERE fav_num = #{fav_num}")
	public void deleteReplyFav(Integer fav_num);
	//후기 좋아요 등록
	@Insert("INSERT INTO items_reply_fav (fav_num, fmem_num, reply_num ) VALUES (items_reply_fav_seq.nextval, #{fmem_num}, #{reply_num})")
	public void insertReplyFav(ItemsReplyFavVO fav);
	//후기 좋아요 췍
	@Select("SELECT COUNT(*) FROM items_reply_fav WHERE reply_num = #{reply_num}")
	public int selectReplyFavCount(Integer reply_num);
	
	@Delete("DELETE FROM items_reply_fav WHERE reply_num = #{reply_num}")
	public void deleteFavByReplyNum(ItemsVO reply_num);
	@Select("SELECT reply_num FROM items_reply WHERE items_num = #{items_num}")
	public ItemsVO selectReplyByItemsNum(Integer items_num);
	
	//후기 좋아요를 누른 사람 찾기 
	@Select("select * from items_reply a join items_reply_fav b ON a.reply_num = b.reply_num ")
	public List<ItemsReplyFavVO> selectReplyFavMem();
	//일단 써보는 쿼리
	@Select("SELECT * FROM items_reply_fav WHERE reply_num = #{reply_num}")
	public int selectReplyFavCheck(Integer reply_num);
	//후기삭제 시 후기 좋아요 PK 뽑아오기
	@Select("SELECT fav_num FROM items_reply_fav WHERE reply_num = #{reply_num}")
	public ItemsReplyVO deleteFav(ItemsReplyVO reply);
	//삭제할 후기와 연결된 모든 좋아요 삭제 
	@Delete("DELETE FROM items_reply_fav WHERE fav_num = #{fav_num}")
	public void deleteAllFav(ItemsReplyVO fav);

	@Select("SELECT reply_num FROM items_reply WHERE items_num = #{items_num}")
	public ItemsVO searchFav(Integer items_num);
	///////////////////////////////////////////////////////////////////////////////////////
	@Select("SELECT reply_num FROM items_reply WHERE items_num = #{items_num}")
	public List<Integer> selectReplyNum(Integer items_num);
	public void deleteReplyNum(Integer items_num);
	@Delete("DELETE FROM items_reply WHERE items_num = #{items_num}")
	public void deleteReplyItems(Integer items_num);
	@Delete("DELETE FROM items_reply_fav WHERE reply_num = #{reply_num}")
	public void deleteReplytoFav(Integer reply_num);
	@Delete("DELETE FROM items_cart WHERE items_num = #{items_num}")
	public void deleteItemsCart(Integer items_num);
	
	//상품 수정시 필요한 사진 정보
	@Select("SELECT items_photo_name1, items_photo_name2, items_photo_name3 FROM items WHERE items_num = #{items_num}")
	public ItemsVO selectPhoto(Integer items_num);
	
	//후기의 사진 정보
	@Select("SELECT reply_photo1, reply_photo_name1, reply_photo2, reply_photo_name2, reply_photo3, reply_photo_name3 FROM items_reply WHERE reply_num = #{reply_num}")
	public ItemsReplyVO replyPhoto(Integer reply_num);
	
	
}

