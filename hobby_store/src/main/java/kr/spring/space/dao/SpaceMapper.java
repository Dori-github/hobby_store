package kr.spring.space.dao;

import java.util.List;


import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.space.vo.SpaceReplyVO;
import kr.spring.space.vo.SpaceTimeVO;

import kr.spring.space.vo.SpaceFavVO;
import kr.spring.space.vo.SpaceReplyFavVO;
import kr.spring.space.vo.SpaceVO;


@Mapper
public interface SpaceMapper {
	@Insert("INSERT INTO space (space_num,space_name,"
			  + "space_content,space_price,space_np,"
			  + "space_photo,space_photo_name,space_photo1,space_photo_name1,"
			  + "space_photo2,space_photo_name2,"
			  + "space_limit,mem_num,space_zipcode,space_address1,space_address2,cate_num) VALUES (space_seq.nextval,"
			  + "#{space_name},#{space_content},#{space_price},#{space_np},"
			  + "#{space_photo},#{space_photo_name},#{space_photo1},#{space_photo_name1},"
			  + "#{space_photo2},#{space_photo_name2},"
			  + "#{space_limit},#{mem_num},#{space_zipcode},#{space_address1},#{space_address2},#{cate_num})")
	public void insertspace(SpaceVO spaceVO);
	@Insert("INSERT INTO space_time (ctime_num,space_num,mem_num,space_reg_date,space_reg_time) VALUES "
			+ "(space_time.nextval,#{space_num},#{mem_num},#{space_reg_date},#{space_reg_time})")
	public void insertSpace_time(SpaceTimeVO vo);
	public int selectSpaceCount(Map<String,Object> map);
	@Select("SELECT space_seq.nextval FROM dual")
	public int selectSpace_num();
	
	@Select("SELECT * FROM space_cate")
	public List<SpaceVO> selectCate();
	@Select("SELECT cate_num FROM space_cate WHERE cate_name=#{cate_name}")
	public int selectCate_num(SpaceVO space);
	
	
	public List<SpaceVO> selectSpaceList(Map<String,Object> map);
	@Select("SELECT * FROM space WHERE space_num=#{space_num}")
	public SpaceVO selectSpace(Integer space_num);
	@Update("UPDATE space SET space_hit=space_hit+1 WHERE space_num=#{space_num}")
	public void updateHit(Integer space_num);
	public void updateSpace(SpaceVO spaceVO);
	@Delete("DELETE FROM space WHERE space_num=#{space_num}")
	public void deleteSpace(Integer space_num);

	@Update("UPDATE space SET uploadfile='',filename='' WHERE space_num=#{space_num}")
	public void deletePhoto(Integer space_num);
	
	
	//좋아요
	@Select("SELECT * FROM space FULL OUTER JOIN space_fav USING(space_num)")
	public List<SpaceVO> selectFavCheck();
	@Select("SELECT * FROM space_fav WHERE"
			+ " space_num=#{space_num} AND fmem_num=#{fmem_num}")
	public SpaceFavVO selectFav(SpaceFavVO fav);
	@Select("SELECT COUNT(*) FROM space_fav WHERE space_num=#{space_num}")
	public int selectFavCount(Integer space_num);
	@Insert("INSERT INTO space_fav (fav_num,space_num,fmem_num) VALUES (space_fav_seq.nextval,#{space_num},#{fmem_num})")
	public void insertFav(SpaceFavVO fav);
	@Delete("DELETE FROM space_fav WHERE fav_num=#{fav_num}")
	public void deleteFav(Integer fav_num);
	@Delete("DELETE FROM space_fav WHERE space_num=#{space_num}")
	public void deleteFavBySpaceNum(Integer fav_num);
	
	//후기
	
	public List<SpaceReplyVO> selectListReply(Map<String, Object> map);
	public int selectReplyCount(Map<String,Object> map);
	@Select("SELECT * FROM space_reply WHERE reply_num=#{reply_num}")
	public SpaceReplyVO selectReply(Integer reply_num);
	@Insert("INSERT INTO space_reply (reply_num,reply_content,reply_photo1,reply_photo2,reply_photo3,reply_photo_name1,reply_photo_name2,reply_photo_name3,space_num,mem_num,star_auth) "
			+ "VALUES (space_reply_seq.nextval,#{reply_content, jdbcType=VARCHAR},#{reply_photo1,jdbcType=BLOB},#{reply_photo2,jdbcType=BLOB},#{reply_photo3,jdbcType=BLOB},"
			+ "#{reply_photo_name1,jdbcType=VARCHAR},#{reply_photo_name2,jdbcType=VARCHAR},#{reply_photo_name3,jdbcType=VARCHAR},#{space_num},#{mem_num},#{star_auth})")
	public void insertReply(SpaceReplyVO spaceReply);
	@Update("UPDATE space_reply SET reply_content=#{reply_content},reply_photo1=#{reply_photo1},reply_photo2=#{reply_photo2},"
			+ "reply_photo3=#{reply_photo3},reply_mdate=SYSDATE WHERE reply_num=#{reply_num}")

	public void updateReply(SpaceReplyVO spaceReply);
	//후기삭제
	@Delete("DELETE FROM space_reply WHERE reply_num=#{reply_num}")
	public void deleteReply(SpaceVO reply);//2
	@Delete("DELETE FROM space_reply WHERE space_num=#{space_num}")
	public void deleteReplyBySpaceNum(Integer space_num);
	public void deleteReplyNum(Integer space_num);
	
	//별점
	@Select("SELECT ROUND(AVG(star_auth),1) AS star_avg  FROM space_reply  WHERE space_num = #{space_num}")
	public Float selectStar(Integer space_num);

	@Select("SELECT COUNT(reply_num) AS star5 FROM space_reply WHERE star_auth = 5")
	public int select5star();
	@Select("SELECT COUNT(reply_num) AS starall FROM space_reply WHERE space_num = #{space_num}")
	public int selectallstar(Integer space_num);
	

	
	//후기 좋아요
	
	    @Select("SELECT * FROM space_reply_fav WHERE reply_num=#{reply_num} AND fmem_num=#{fmem_num}")
		public SpaceReplyFavVO selectReplyFav(SpaceReplyFavVO fav);
	
	    @Select("SELECT * FROM space_reply_fav WHERE fmem_num=#{mem_num} AND reply_num=#{mem_num}")
		public SpaceReplyFavVO selectReplyFavCheck();
	
		@Select("SELECT COUNT(*) FROM space_reply_fav WHERE reply_num=#{reply_num}")
		public int selectReplyFavCount(Integer reply_num);
		@Insert("INSERT INTO space_reply_fav(fav_num,reply_num,fmem_num) VALUES (space_reply_fav_seq.nextval,#{reply_num},#{fmem_num})")
		public void insertReplyFav(SpaceReplyFavVO fav);
		//후기 좋아요 삭제
		@Delete("DELETE FROM space_reply_fav WHERE fav_num=#{fav_num}")
		public void deleteReplyFav(Integer fav_num);
		@Delete("DELETE FROM space_reply_fav WHERE reply_num=#{reply_num}")
		public void deleteReplyFavByReplyNum(Integer reply_num);
	    //후기사진삭제
		@Update("UPDATE space SET uploadfile='',filename='' WHERE reply_num=#{reply_num}")
		public void deleteReplyPhoto(Integer reply_num);
		@Delete("DELETE FROM space_reply_fav WHERE reply_num = #{reply_num}")
		public void deleteFavByReplyNum(SpaceVO reply_num);
		@Delete("DELETE FROM space_reply WHERE space_num = #{space_num}")
		public void deleteReplySpace(Integer space_num);//

		@Delete("DELETE FROM space_reply WHERE space_num = #{space_num}")
		public void deleteReply2(Integer space_num);
	

}


