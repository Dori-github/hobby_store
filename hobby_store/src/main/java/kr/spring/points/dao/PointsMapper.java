package kr.spring.points.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.order.vo.OrderVO;
import kr.spring.points.vo.PointsVO;

@Mapper
public interface PointsMapper {
	@Select("SELECT * FROM points WHERE mem_num=#{mem_num}")
	public List<PointsVO> getPointsList(int num);
	@Select("SELECT COUNT(points_num) FROM points WHERE mem_num=#{mem_num}")
	public int getPointsCount(int num); /* 장바구니에는 안 넣나..? */
	public int getPoints(int num);
	/* 포인트 적립 시 소멸 일자를 1년 1일 후로 계산해서 넣기. expired_points db/vo에 추가. 형식 Date로 수정? */
	@Insert("INSERT INTO points (points_num, points_type, saved_points, saved_date, expired_date, mem_num) VALUES (points_seq.nextval, 0, #{saved_points}, #{saved_date}, #{expired_date}, #{mem_num})")
	public void savePoints(PointsVO points);	
	@Insert("INSERT INTO points (points_num, points_type, used_points, used_date, mem_num) VALUES (points_seq.nextval, 1, #{used_points}, SYSDATE, #{mem_num})")
	public void usePoints(PointsVO points);
	
}
