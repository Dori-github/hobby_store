package kr.spring.course.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.spring.course.vo.CourseVO;

@Mapper
public interface CourseMapper {
	//부모글
	public List<CourseVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	public void insertCourse(CourseVO course);
	public CourseVO selectCourse(Integer course_num);
	public void updateHit(Integer course_num);
	public void updateCourse(CourseVO course);
	public void deleteCourse(Integer course_num);
	public void deletePhoto(Integer course_num);
	
	
	//좋아요
	
	
	
	//후기
	
	
}
