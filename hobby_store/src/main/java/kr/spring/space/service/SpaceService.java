package kr.spring.space.service;

import java.util.List;


import java.util.Map;

import kr.spring.space.vo.SpaceVO;

public interface SpaceService {

	
	public void insertspace(SpaceVO spaceVO);
	public int selectSpaceCount(Map<String,Object> map);
	public List<SpaceVO> selectSpaceList(Map<String,Object> map);
	public SpaceVO selectSpace(Integer space_num);
	public void updateSpace(SpaceVO spaceVO);



}




