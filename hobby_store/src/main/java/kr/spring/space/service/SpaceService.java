package kr.spring.space.service;

import java.util.List;




import java.util.Map;

import kr.spring.course.vo.CourseReplyFavVO;
import kr.spring.course.vo.CourseReplyVO;
import kr.spring.course.vo.CourseVO;
import kr.spring.items.vo.ItemsVO;
import kr.spring.space.vo.SpaceFavVO;
import kr.spring.space.vo.SpaceReplyFavVO;
import kr.spring.space.vo.SpaceReplyVO;
import kr.spring.space.vo.SpaceVO;

public interface SpaceService {


	public void insertspace(SpaceVO spaceVO);
	public int selectSpaceCount(Map<String,Object> map);
	public List<SpaceVO> selectSpaceList(Map<String,Object> map);
	
	public List<SpaceVO> selectCate();
	public int selectCate_num(SpaceVO space);
	
	public SpaceVO selectSpace(Integer space_num);
	public void updateHit(Integer space_num);
	public void updateSpace(SpaceVO spaceVO);
	public void deleteSpace(Integer space_num);
	public void deletePhoto(Integer space_num);


	//좋아요
	public List<SpaceVO> selectFavCheck();
	public SpaceFavVO selectFav(SpaceFavVO fav);
	public int selectFavCount(Integer space_num);
	public void insertFav(SpaceFavVO fav);
	public void deleteFav(Integer fav_num);
	public void deleteFavBySpaceNum(Integer space_num);

	//후기
	public List<SpaceReplyVO> selectListReply(Map<String,Object> map);
	public int selectReplyCount(Map<String,Object> map);
	public SpaceReplyVO selectReply(Integer reply_num);
	public void insertReply(SpaceReplyVO spaceReply);
	public void updateReply(SpaceReplyVO spaceReply);
	public void deleteReplyNum(Integer space_num);
	public void deleteReplyBySpaceNum(Integer space_num);
	public void deleteReplyPhoto(Integer reply_num);

    //별점
	public float selectStar(Integer space_num);
	public int select5star(Integer space_num);
	public int selectallstar(Integer space_num);
	
	//후기 좋아요
	public SpaceReplyFavVO selectReplyFavCheck();
	public SpaceReplyFavVO selectReplyFav(SpaceReplyFavVO fav);
	public int selectReplyFavCount(Integer reply_num);
	public void insertReplyFav(SpaceReplyFavVO fav);
	public void deleteReplyFav(Integer fav_num);
	public void deleteReplyFavByReplyNum(Integer reply_num);
	public void deleteFavByReplyNum(SpaceVO reply_num);
	public void deleteReply(SpaceVO reply);
	void deleteReply2(Integer space_num);
	

}




