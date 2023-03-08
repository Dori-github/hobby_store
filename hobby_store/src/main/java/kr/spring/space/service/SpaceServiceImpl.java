package kr.spring.space.service;

import java.util.Arrays;
import java.util.List;







import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import kr.spring.space.vo.SpaceVO;
import kr.spring.space.dao.SpaceMapper;
import kr.spring.space.vo.SpaceFavVO;
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
	public void insertSpace(SpaceVO space) {
		space.setSpace_num(spaceMapper.selectSpace_num());
		spaceMapper.insertspace(space);
		for(SpaceTimeVO vo : space.getSpaceTimeVO()) {
			if(vo.getspace_reg_date()!=null) {
				vo.setspace_num(space.getSpace_num());
				vo.setMem_num(space.getMem_num());
				String output = "";
				List<String> time = vo.getspace_reg_times();
				//공백 또는 null 제거
				time.removeAll(Arrays.asList("",null));
				for(int i=0;i<vo.getspace_reg_times().size();i++) {
					output += vo.getspace_reg_times().get(i);
					if(i<vo.getspace_reg_times().size()-1) output += ",";
				}
				System.out.println("~~~~~~~~~~~~~~~"+output);
				vo.setspace_reg_time(output);
				spaceMapper.insertSpace_time(vo);
			}
		}
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePhoto(Integer space_num) {
		// TODO Auto-generated method stub
		
	}

	//후기
	@Override
	public float selectStarAvg(Integer space_num) {
		return 0;
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
	public void deleteReply(Integer re_num) {
		spaceMapper.deleteReply(re_num);
	}
	@Override
	public void deleteReplyBySpaceNum(Integer space_num) {
		spaceMapper.deleteReplyBySpaceNum(space_num);
	}








	



	


}




