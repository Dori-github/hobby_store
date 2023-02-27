package kr.spring.course.service;

import java.util.List;
import java.util.Map;

import kr.spring.course.vo.CourseVO;

public interface CourseService { 
	//부모글
	public List<CourseVO> selectCourseList(Map<String,Object> map);
	public int selectCourseCount(Map<String,Object> map);
	public void insertCourse(CourseVO course);
	public void insertCourse_time(CourseVO cou);
	public List<CourseVO> selectCate();
	public int selectCate_num(CourseVO course);
	public CourseVO selectCourse(Integer course_num);
	public void updateHit(Integer course_num);
	public void updateCourse(CourseVO course);
	public void deleteCourse(Integer course_num);
	public void deletePhoto(Integer course_num);
	
	
	//좋아요
	public int selectFavCount(Integer course_num);


	//후기
	public int selectReplyCount(Integer course_num);
	public int selectStarAvg(Integer course_num);

}
