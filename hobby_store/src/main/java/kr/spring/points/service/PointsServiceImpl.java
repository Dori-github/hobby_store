package kr.spring.points.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.points.dao.PointsMapper;
import kr.spring.points.vo.PointsVO;

@Service
public class PointsServiceImpl implements PointsService{

	@Autowired
	private PointsMapper pointsmapper;
	
	@Override
	public List<PointsVO> getPointsList(int num) {
		return pointsmapper.getPointsList(num);
	}

	@Override
	public int getPointsCount(int num) {
		return pointsmapper.getPointsCount(num);
	}
}
