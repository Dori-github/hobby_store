package kr.spring.space.service;

import java.util.List;






import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import kr.spring.space.dao.SpaceMapper;
import kr.spring.space.vo.SpaceFavVO;
import kr.spring.space.vo.SpaceReplyVO;
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








	



	


}




