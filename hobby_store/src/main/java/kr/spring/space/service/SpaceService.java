package kr.spring.space.service;

import java.util.List;



import java.util.Map;

import kr.spring.course.vo.CourseVO;
import kr.spring.space.vo.SpaceFavVO;
import kr.spring.space.vo.SpaceVO;

public interface SpaceService {

	
	public void insertspace(SpaceVO spaceVO);
	public int selectSpaceCount(Map<String,Object> map);
	public List<SpaceVO> selectSpaceList(Map<String,Object> map);
	public SpaceVO selectSpace(Integer space_num);
	public void updateHit(Integer space_num);
	public void updateSpace(SpaceVO spaceVO);
	
	//좋아요
	public List<SpaceVO> selectFavCheck();
	public SpaceFavVO selectFav(SpaceFavVO fav);
	public int selectFavCount(Integer space_num);
	public void insertFav(SpaceFavVO fav);
	public void deleteFav(Integer fav_num);
	public void deleteFavBySpaceNum(Integer space_num);
	

	




}




