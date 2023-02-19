package kr.spring.course.service;

import java.util.List;
import java.util.Map;

import kr.spring.course.vo.CourseVO;

public interface CourseService { 
	public List<CourseVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	public void insertCourse(CourseVO course);
	public CourseVO selectCourse(Integer course_num);
	public void updateHit(Integer course_num);
	public void updateCourse(CourseVO course);
	public void deleteCourse(Integer course_num);
	public void deletePhoto(Integer course_num);
}
