package kr.spring.space.dao;

import java.util.List;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.space.vo.SpaceVO;


@Mapper
public interface SpaceMapper {//오라클 오류 뜸
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
	public void updateSpace(SpaceVO spaceVO);

}


