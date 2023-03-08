package kr.spring.points.service;

import java.util.List;

import kr.spring.points.vo.PointsVO;

public interface PointsService {
	public List<PointsVO> getPointsList(int num);
	public int getPointsCount(int num);
	public int getPoints(int num);
	
}
