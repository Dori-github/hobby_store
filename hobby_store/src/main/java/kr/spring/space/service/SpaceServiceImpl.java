package kr.spring.space.service;

import java.util.Arrays;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.space.dao.SpaceMapper;
import kr.spring.space.vo.SpaceFavVO;
import kr.spring.space.vo.SpaceReplyFavVO;
import kr.spring.space.vo.SpaceReplyVO;
import kr.spring.space.vo.SpaceTimeVO;
import kr.spring.space.vo.SpaceVO;

@Service
@Transactional
public class SpaceServiceImpl implements SpaceService{
	@Autowired
	private SpaceMapper spaceMapper;

	@Override
	public void insertspace(SpaceVO spaceVO) {
		spaceMapper.insertspace(spaceVO);
		
	}

	@Override
	public int selectSpaceCount(Map<String, Object> map) {

		return spaceMapper.selectSpaceCount(map);
	}
	


	@Override
	public List<SpaceVO> selectSpaceList(Map<String, Object> map) {
		
		return spaceMapper.selectSpaceList(map);
	}



	@Override
	public SpaceVO selectSpace(Integer space_num) {
		
		return spaceMapper.selectSpace(space_num);
	}

	@Override
	public void updateSpace(SpaceVO spaceVO) {
		spaceMapper.updateSpace(spaceVO);
		
	}
	
	@Override
	public void updateHit(Integer space_num) {
		spaceMapper.updateHit(space_num);
		
	}
	
	
	//공간게시글 좋아요

	@Override
	public SpaceFavVO selectFav(SpaceFavVO fav) {
		
		return spaceMapper.selectFav(fav);
	}

	@Override
	public int selectFavCount(Integer space_num) {
		return spaceMapper.selectFavCount(space_num);
	}

	@Override
	public void insertFav(SpaceFavVO fav) {
		spaceMapper.insertFav(fav);
		
	}

	@Override
	public void deleteFav(Integer fav_num) {
		spaceMapper.deleteFav(fav_num);
		
	}

	@Override
	public List<SpaceVO> selectFavCheck() {
		
		return spaceMapper.selectFavCheck();
	}

	@Override
	public void deleteFavBySpaceNum(Integer space_num) {
		spaceMapper.deleteFavBySpaceNum(space_num);
		
	}

	@Override
	public void deleteSpace(Integer space_num) {
		spaceMapper.deleteSpace(space_num);
		
	}

	@Override
	public void deletePhoto(Integer space_num) {
		spaceMapper.deletePhoto(space_num);
		
	}

	//후기
	@Override
	public float selectStarAvg(Integer space_num) {
		return spaceMapper.selectStarAvg(space_num);
	}
	
	@Override
	public List<SpaceReplyVO> selectListReply(Map<String, Object> map) {
		return spaceMapper.selectListReply(map);
	}
	@Override
	public int selectReplyCount(Map<String, Object> map) {
		return spaceMapper.selectReplyCount(map);
	}
	@Override
	public SpaceReplyVO selectReply(Integer reply_num) {
		return spaceMapper.selectReply(reply_num);
	}
	@Override
	public void insertReply(SpaceReplyVO spaceReply) {
		spaceMapper.insertReply(spaceReply);
	}
	@Override
	public void updateReply(SpaceReplyVO spaceReply) {
		spaceMapper.updateReply(spaceReply);
	}
	@Override
	public void deleteReply(Integer reply_num) {
		spaceMapper.deleteReply(reply_num);
	}
	@Override
	public void deleteReplyBySpaceNum(Integer space_num) {
		spaceMapper.deleteReplyBySpaceNum(space_num);
	}

	@Override
	public float selectStar(Integer space_num) {
		return spaceMapper.selectStar(space_num);
	}

	@Override
	public int select5star(Integer space_num) {
		return spaceMapper.select5star();
	}

	@Override
	public int selectallstar(Integer space_num) {
		return spaceMapper.selectallstar(space_num);
	}
    
	
	//후기 좋아요
	@Override
	public SpaceReplyFavVO selectReplyFav(SpaceReplyFavVO fav) {
		return spaceMapper.selectReplyFav(fav);
	}

	@Override
	public int selectReplyFavCount(Integer reply_num) {
		return spaceMapper.selectReplyFavCount(reply_num);
	}

	@Override
	public void insertReplyFav(SpaceReplyFavVO fav) {
		spaceMapper.insertReplyFav(fav);
		
	}

	@Override
	public void deleteReplyFav(Integer fav_num) {
		spaceMapper.deleteReplyFav(fav_num);
		
	}

	@Override
	public void deleteReplyFavByReplyNum(Integer reply_num) {
		spaceMapper.deleteReplyFavByReplyNum(reply_num);
		
	}

	@Override
	public SpaceReplyFavVO selectReplyFavCheck() {
		return spaceMapper.selectReplyFavCheck();
	}

}




