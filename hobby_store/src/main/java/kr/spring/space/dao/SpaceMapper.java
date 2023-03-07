package kr.spring.space.dao;

import java.util.List;

import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.course.vo.CourseVO;
import kr.spring.space.vo.SpaceFavVO;
import kr.spring.space.vo.SpaceVO;


@Mapper
public interface SpaceMapper {
	@Insert("INSERT INTO space (space_num,space_name,"
			  + "space_content,space_price,space_np,"
			  + "space_photo,space_photo_name,space_photo1,space_photo_name1,"
			  + "space_photo2,space_photo_name2,space_photo3,space_photo_name3,"
			  + "space_limit,mem_num,space_zipcode,space_address1,space_address2,cate_num) VALUES (space_seq.nextval,"
			  + "#{space_name},#{space_content},#{space_price},#{space_np},"
			  + "#{space_photo},#{space_photo_name},#{space_photo1},#{space_photo_name1},"
			  + "#{space_photo2},#{space_photo_name2},#{space_photo3},#{space_photo_name3},"
			  + "#{space_limit},#{mem_num},#{space_zipcode},#{space_address1},#{space_address2},#{cate_num})")
	public void insertspace(SpaceVO spaceVO);
	public int selectSpaceCount(Map<String,Object> map);
	
	public List<SpaceVO> selectSpaceList(Map<String,Object> map);
	@Select("SELECT * FROM space WHERE space_num=#{space_num}")
	public SpaceVO selectSpace(Integer space_num);
	@Update("UPDATE space SET space_hit=space_hit+1 WHERE space_num=#{space_num}")
	public void updateHit(Integer space_num);
	public void updateSpace(SpaceVO spaceVO);
	
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
	
	

}


